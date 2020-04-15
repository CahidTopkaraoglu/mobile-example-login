package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SensorAct extends AppCompatActivity implements SensorEventListener {

    //TODO: sensör lstesi gösterilirken ışık sesörünün verisine göre arka planı beyaz siyah yapıyoruz

    SensorManager sMgr;
    private Sensor light;
    private RelativeLayout rl;
    ListView listemiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor2);

        rl = (RelativeLayout)findViewById(R.id.sonsor_layout);

        sMgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        List<Sensor> list = sMgr.getSensorList(Sensor.TYPE_ALL);
        ArrayList<String> sensor_list = new ArrayList<String>();
        for(Sensor sensor : list){
            sensor_list.add(sensor.getName());
        }
        //(A) adımı
        listemiz=(ListView) findViewById(R.id.sensor_list);

        //(B) adımı
        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1 , android.R.id.text1, sensor_list);

        //(C) adımı
        listemiz.setAdapter(veriAdaptoru);


        light = sMgr.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float lux = event.values[0];
        if(lux > 0.5){
            rl.setBackgroundColor(Color.MAGENTA);
        }else{
            rl.setBackgroundColor(Color.GREEN);
        }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy){

    }
    @Override
    protected void onResume(){
        super.onResume();
        sMgr.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sMgr.unregisterListener(this);
    }
}
