package com.example.week5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class SendEmail extends AppCompatActivity {
    public static final int PICKFILE_RESULT_CODE = 1;
    private TextView textFilePath;
    private EditText editTextTo, editTextSubjext, editTextMessage;
    private Button send, fileChoose;
    private Uri fileUri;
    private String filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        editTextTo = (EditText) findViewById(R.id.to_edittext);
        editTextSubjext = (EditText) findViewById(R.id.subject_edittext);
        editTextMessage = (EditText) findViewById(R.id.message_edittext);
        send = (Button) findViewById(R.id.send_button);
        fileChoose = (Button) findViewById(R.id.file_choose_button);
        textFilePath = (TextView) findViewById(R.id.file_path_textview);

        fileChoose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                chooseFile = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(chooseFile, PICKFILE_RESULT_CODE);
            }
        });

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String to = editTextTo.getText().toString();
                String subject = editTextSubjext.getText().toString();
                String message = editTextMessage.getText().toString();
                String filePath = textFilePath.getText().toString();
                File file = null;
                try {
                    File root = Environment.getExternalStorageDirectory();
                    file = new File(root, filePath);
                    if (!file.exists() || !file.canRead()) {
                        File locale = getApplicationContext().getFileStreamPath(filePath);
                        if (!locale.exists() || !locale.canRead()){
                            Toast.makeText(getApplicationContext(),"File does not exist or don't have permission to read it!", Toast.LENGTH_LONG).show();
                            if (ActivityCompat.checkSelfPermission(SendEmail.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(SendEmail.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICKFILE_RESULT_CODE );
                            }
                            return;
                        }
                        else{
                            file = locale;
                        }
                    }
                } catch (Exception e) {
                    Log.d("SendEmailFileDetect", e.getStackTrace().toString());
                }
                Uri uri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", file);
                //Implicit Intent İşimizi başka uygulamaya yaptırıyoruz
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);
                //TODO: Dosyayı bulamıyor incelenecek.
                email.putExtra(Intent.EXTRA_STREAM, uri);
                email.setType("message/rcf882");
                //Explicit intent
                startActivity(Intent.createChooser(email, "Choose an Email client: "));
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                if (resultCode == -1) {
                    fileUri = data.getData();
                    filePath = fileUri.getPath();
                    textFilePath.setText(filePath);
                }
                break;
        }

    }
}
