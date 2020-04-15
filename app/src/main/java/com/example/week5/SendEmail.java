package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SendEmail extends AppCompatActivity {
    EditText editTextTo, editTextSubjext, editTextMessage;
    Button send, userPreference, lightSensor, accelerationSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        editTextTo = (EditText) findViewById(R.id.to_edittext);
        editTextSubjext = (EditText) findViewById(R.id.subject_edittext);
        editTextMessage = (EditText) findViewById(R.id.message_edittext);
        send = (Button) findViewById(R.id.send_button);
        userPreference = (Button) findViewById(R.id.user_reference_button);
        lightSensor = (Button) findViewById(R.id.light_sensor_button);
        accelerationSensor = (Button) findViewById(R.id.acceleration_sensor_button);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String to = editTextTo.getText().toString();
                String subject = editTextSubjext.getText().toString();
                String message = editTextMessage.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                email.setType("message/rcf882");

                startActivity(Intent.createChooser(email, "Choose an Email client: "));
            }
        });
        userPreference.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent userPreferences = new Intent(SendEmail.this, UserPreferences.class);
                startActivity(userPreferences);
            }
        });
        lightSensor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent userPreferences = new Intent(SendEmail.this, SensorAct.class);
                startActivity(userPreferences);
            }
        });
        accelerationSensor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent userPreferences = new Intent(SendEmail.this, AccelerationSensor.class);
                startActivity(userPreferences);
            }
        });
    }
}
