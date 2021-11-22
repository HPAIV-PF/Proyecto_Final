package com.example.proyectofinal;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GravityFragment extends Fragment implements SensorEventListener {

    private Button gravBtn;
    private TextView gravValor;
    private TextView valueFields;
    private SensorManager senseManage;
    private Sensor envSense;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gravity, container, false);
        gravBtn = (Button) view.findViewById(R.id.btnGrav);
        gravValor = (TextView)view.findViewById(R.id.gravValor);
        valueFields=gravValor;
        senseManage = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        gravBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                envSense = senseManage.getDefaultSensor(Sensor.TYPE_GRAVITY);
                if(envSense==null)
                    Toast.makeText(getActivity(),
                            "Lo siento, tu dispositivo no tiene sensor de gravedad.",
                            Toast.LENGTH_SHORT).show();
                else
                    senseManage.registerListener(GravityFragment.this, envSense, SensorManager.SENSOR_DELAY_NORMAL);
            }
        });
        return view;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float sensorValue = event.values[0];
        TextView currValue = gravValor;
        String envInfo="";
        int currType=event.sensor.getType();
        envInfo=sensorValue+" m/s^2";
        currValue=valueFields;
        currValue.setText(envInfo);
        envSense=null;
        senseManage.unregisterListener((SensorEventListener) this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        String accuracyMsg = "";
        switch(accuracy){
            case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
                accuracyMsg="El sensor tiene alta precisión.";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
                accuracyMsg="El sensor tiene una precisión media.";
                break;
            case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
                accuracyMsg="El sensor tiene baja precisión.";
                break;
            case SensorManager.SENSOR_STATUS_UNRELIABLE:
                accuracyMsg="El sensor tiene una precisión poco confiable.";
                break;
            default:
                break;
        }
        Toast accuracyToast = Toast.makeText(getContext(), accuracyMsg, Toast.LENGTH_SHORT);
        accuracyToast.show();
    }
}
