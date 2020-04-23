package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {
    //Shared Predference dosyamızın adı
    public static final String MyPREFERENCES = "user_pref_login" ;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_login);

        //View içerisindeki editor ve buton elementlerini id değerleri ile alıyoruz
        final EditText uname = (EditText) findViewById(R.id.username_edittext);
        final EditText password = (EditText) findViewById(R.id.password_edittext);
        final Button loginButton = (Button) findViewById(R.id.login_button);
        final Button registerButton = (Button) findViewById(R.id.register_button);
        //SharedPreferences erişimini tanımlıyoruz
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        //Login butonuna tıklandığında girilen bilgilerin shared preference içerisinde tutulan
        //değerlerle eşleşmesini kontrol et. Eşleşme var ise Menüye git, yok ise hata ver.
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Viewde girilen bilgileri al
                User user = new User();
                user.setUserName(uname.getText().toString());
                user.setPassword(password.getText().toString());

                //SharedPreference ile aranan keydeki değerleri getir
                String _UserName  = sharedpreferences.getString(user.getUserName()+"_key_username",null);
                String _Password  = sharedpreferences.getString(user.getUserName()+"_key_password",null);
                //Bulunan değerler ile eşleşme kontrol et
                if(TextUtils.isEmpty(_UserName) == false && TextUtils.isEmpty(_Password) == false
                && user.getUserName().equals(_UserName) && user.getPassword().equals(_Password))
                {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(getString(R.string.saved_user_name), uname.getText().toString());
                    editor.commit();

                    Intent userPreferences = new Intent(LoginScreen.this, MenuActivity.class);
                    startActivity(userPreferences);
                }else{
                    Toast.makeText(getApplicationContext(),"User not found. Try Again!", Toast.LENGTH_LONG).show();
                }
            }
        });
        //Viewe girilen kullanıcı adı ve parolayı shared preference ekle.
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Girilen bilgileri al
                User user = new User();
                user.setUserName(uname.getText().toString());
                user.setPassword(password.getText().toString());

                SharedPreferences.Editor editor = sharedpreferences.edit();

                //Key oluştur ve değerini vererek kaydet
                editor.putString(user.getUserName()+"_key_username", user.getUserName());
                editor.putString(user.getUserName()+"_key_password", user.getPassword());
                editor.commit();

                Toast.makeText(getApplicationContext(),user.getUserName() + " user saved.", Toast.LENGTH_LONG).show();
            }
        });
    }
}
