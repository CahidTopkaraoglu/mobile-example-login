package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class UserPreferences extends AppCompatActivity {
    EditText ed_username,ed_age,ed_weight,ed_height;
    Spinner sp_gender,sp_application_mode,sp_language;
    Button b_save;

    public static final String MyPREFERENCES = "user_pref_login" ;

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preferences);
        sp_gender=(Spinner)findViewById(R.id.gender_pref_spinner);
        sp_application_mode=(Spinner)findViewById(R.id.application_mode_spinner);
        sp_language=(Spinner)findViewById(R.id.language_spinner);

        ed_username=(EditText)findViewById(R.id.username_pref_edittext);
        ed_age=(EditText)findViewById(R.id.age_pref_edittext);
        ed_weight=(EditText)findViewById(R.id.weight_pref_edittext);
        ed_height=(EditText)findViewById(R.id.height_pref_edittext);

        b_save=(Button)findViewById(R.id.pref_submit_button);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String _Saved_UserName  = sharedpreferences.getString(getString(R.string.saved_user_name),"");
        Toast toast = Toast.makeText(getApplicationContext(), _Saved_UserName, Toast.LENGTH_SHORT);
        toast.show();
        if(TextUtils.isEmpty(_Saved_UserName) == false)
        {
            ed_username.setText(_Saved_UserName.toString());
        }
        String _Default_Gender = getResources().getString(R.string.default_gender);
        String _Saved_Gender  = sharedpreferences.getString(getString(R.string.saved_gender),_Default_Gender);
        if(TextUtils.isEmpty(_Saved_Gender) == false)
        {
            sp_gender.setSelection(((ArrayAdapter)sp_gender.getAdapter()).getPosition(_Saved_Gender));
        }
        Integer _Default_Age = getResources().getInteger(R.integer.default_age);
        Integer _Saved_Age  = sharedpreferences.getInt(getString(R.string.saved_gender),_Default_Age);
        if(_Saved_Age > 0)
        {
            ed_age.setText(_Saved_Age.toString());
        }
        TypedValue _Default_Weigh_typed_value = new TypedValue();
        getResources().getValue(R.dimen.default_weight, _Default_Weigh_typed_value, true);
        Float _Default_Weight = _Default_Weigh_typed_value.getFloat();
        if(_Default_Weight > 0)
        {
            ed_weight.setText(_Default_Weight.toString());
        }
        TypedValue _Default_Height_typed_value = new TypedValue();
        getResources().getValue(R.dimen.default_height, _Default_Height_typed_value, true);
        Float _Default_Height = _Default_Height_typed_value.getFloat();
        if(_Default_Height > 0)
        {
            ed_height.setText(_Default_Height.toString());
        }
        String _Default_ApplicationMode = getResources().getString(R.string.default_application_mode);
        String _Saved_ApplicationMode  = sharedpreferences.getString(getString(R.string.saved_application_mode),_Default_ApplicationMode);
        if(TextUtils.isEmpty(_Saved_ApplicationMode) == false)
        {
            sp_application_mode.setSelection(((ArrayAdapter)sp_application_mode.getAdapter()).getPosition(_Saved_ApplicationMode));
        }
        String _Default_Language = getResources().getString(R.string.default_language);
        String _Saved_Language  = sharedpreferences.getString(getString(R.string.saved_language),_Default_Language);
        if(TextUtils.isEmpty(_Saved_Language) == false)
        {
            sp_application_mode.setSelection(((ArrayAdapter)sp_application_mode.getAdapter()).getPosition(_Saved_Language));
        }

        b_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _UserName  = ed_username.getText().toString();
                String _Gender  = sp_gender.getSelectedItem().toString();
                String _Age  = ed_age.getText().toString();
                String _Weight  = ed_weight.getText().toString();
                String _Height  = ed_height.getText().toString();
                String _ApplicationMode  = sp_application_mode.getSelectedItem().toString();
                String _Language  = sp_language.getSelectedItem().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(getString(R.string.saved_user_name), _UserName);
                editor.putString(getString(R.string.saved_gender), _Gender);
                editor.putString(getString(R.string.saved_age), _Age);
                editor.putString(getString(R.string.saved_weight), _Weight);
                editor.putString(getString(R.string.saved_height), _Height);
                editor.putString(getString(R.string.saved_application_mode), _ApplicationMode);
                editor.putString(getString(R.string.saved_language), _Language);
                editor.commit();

                Toast.makeText(UserPreferences.this,"Thanks",Toast.LENGTH_LONG).show();
            }
        });
    }
}
