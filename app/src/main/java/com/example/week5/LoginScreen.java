package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class LoginScreen extends AppCompatActivity {
    public static final String MyPREFERENCES = "user_pref_login" ;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_login);

        final EditText uname = (EditText) findViewById(R.id.username_edittext);
        final EditText password = (EditText) findViewById(R.id.password_edittext);
        final Button loginButton = (Button) findViewById(R.id.login_button);
        final Button registerButton = (Button) findViewById(R.id.register_button);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUserName(uname.getText().toString());
                user.setUserName(password.getText().toString());

                String _UserName  = sharedpreferences.getString(user.getUserName()+"_key_username","");
                String _Password  = sharedpreferences.getString(user.getUserName()+"_key_password","");
                if(TextUtils.isEmpty(_UserName) == false && TextUtils.isEmpty(_Password) == false)
                {
                    if(user.getUserName().equals(_UserName) && user.getPassword().equals(_Password)){

                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString(getString(R.string.saved_user_name), uname.getText().toString());
                        editor.commit();

                        Intent userPreferences = new Intent(LoginScreen.this, SendEmail.class);
                        startActivity(userPreferences);
                    }else{
                        Toast.makeText(getApplicationContext(),"User not found. Try Again!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"User not found. Try Again!", Toast.LENGTH_LONG).show();
                }
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUserName(uname.getText().toString());
                user.setUserName(password.getText().toString());

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(user.getUserName()+"_key_username", user.getUserName());
                editor.putString(user.getUserName()+"_key_password", user.getPassword());
                editor.commit();
            }
        });
    }
    private boolean checkPassword(Editable username, Editable password){
        return true;
    }
}
