package com.example.jatinder_assessment_impint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PhoneCallEventActivity extends AppCompatActivity {

    EditText et_PhoneNumber;
    Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call_event);

        et_PhoneNumber = findViewById(R.id.et_PhoneNumber);
        btnCall = findViewById(R.id.btnCall);
    }

    public void MakeCallButtonClicked(View view){
        if(!et_PhoneNumber.getText().toString().isEmpty()){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + et_PhoneNumber.getText().toString()));
            startActivity(intent);
        }
    }
}