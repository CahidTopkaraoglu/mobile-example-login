package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button sendMail, userPreference, lightSensor, accelerationSensor, incomingCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //View içerisinden buttonlarımızı idleri ile birlikte alıyoruz
        sendMail = (Button) findViewById(R.id.send_mail_button);
        userPreference = (Button) findViewById(R.id.user_reference_button);
        lightSensor = (Button) findViewById(R.id.light_sensor_button);
        accelerationSensor = (Button) findViewById(R.id.acceleration_sensor_button);
        incomingCall = (Button) findViewById(R.id.incomin_call_button);

        //Viewdeki butonlarımıza tıklanıldığında gerçekleşecek aksiyonları yazıyoruz
        sendMail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent userPreferences = new Intent(MenuActivity.this, SendEmail.class);
                startActivity(userPreferences);
            }
        });
        userPreference.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent userPreferences = new Intent(MenuActivity.this, UserPreferences.class);
                startActivity(userPreferences);
            }
        });
        lightSensor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent userPreferences = new Intent(MenuActivity.this, SensorAct.class);
                startActivity(userPreferences);
            }
        });
        accelerationSensor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent userPreferences = new Intent(MenuActivity.this, AccelerationSensor.class);
                startActivity(userPreferences);
            }
        });
        incomingCall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent userPreferences = new Intent(MenuActivity.this, IncomingCall.class);
                startActivity(userPreferences);
            }
        });
    }
}
