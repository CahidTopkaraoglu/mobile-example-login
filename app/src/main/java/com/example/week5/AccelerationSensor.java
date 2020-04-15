package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class AccelerationSensor extends AppCompatActivity implements SensorEventListener {

    TextView x,y,z;

    SensorManager sMgr;
    private Sensor accelerometer;
    private static final String TAG = "AccelerationActivity";
    private static final int ADMIN_INTENT = 15;
    private static final String description = "Some Description About Your Admin";
    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mComponentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceleration_sensor);

        x = (TextView) findViewById(R.id.x_value_textview);
        y = (TextView) findViewById(R.id.y_value_textview);
        z = (TextView) findViewById(R.id.z_value_textview);

        mDevicePolicyManager = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        mComponentName = new ComponentName(this, AdminReceiver.class);

        sMgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        accelerometer = sMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[2] > 9.7F && event.values[2] < 9.8F) {
            Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,description);
            startActivityForResult(intent, ADMIN_INTENT);

            boolean isAdmin = mDevicePolicyManager.isAdminActive(mComponentName);
            if (isAdmin) {
                mDevicePolicyManager.lockNow();
            }else{
                Toast.makeText(getApplicationContext(), "Not Registered as admin", Toast.LENGTH_SHORT).show();
            }
        }
        x.setText(event.values[0]+"");
        y.setText(event.values[1]+"");
        z.setText(event.values[2]+"");
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy){

    }
    @Override
    protected void onResume(){
        super.onResume();
        sMgr.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sMgr.unregisterListener(this);
    }
}
