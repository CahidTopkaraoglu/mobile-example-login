package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_login);

        final EditText uname = (EditText) findViewById(R.id.username_edittext);
        final EditText password = (EditText) findViewById(R.id.password_edittext);
        final Button loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO: Bir tane kullanıcı class yap
                //TODO: Kullanıcı classlarından bir listeye 4 kulanıcı tanımla
                //TODO: Layouttan girilen kullanıcı adı parolayı listede ara
                //TODO: Eşleşen kayıtta mail göndere git
                if(checkPassword(uname.getText(),password.getText())){
                    Intent sendEmail = new Intent(LoginScreen.this, SendEmail.class);

                    startActivity(sendEmail);
                }else{
                    uname.setText("");
                    password.setText("");
                }
            }
        });
    }
    private boolean checkPassword(Editable username, Editable password){
        return new Random().nextBoolean();
    }
}
