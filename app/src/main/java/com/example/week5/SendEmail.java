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
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        editTextTo = (EditText) findViewById(R.id.to_edittext);
        editTextSubjext = (EditText) findViewById(R.id.subject_edittext);
        editTextMessage = (EditText) findViewById(R.id.message_edittext);
        send = (Button) findViewById(R.id.send_button);

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
    }
}
