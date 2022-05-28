package com.example.jatinder_assessment_impint;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Click of Add Calendar Event Button
    public void addCalendarEventButtonClicked(View view){
        startActivity(new Intent(this, CalendarEventActivity.class));
    }

    //Click of Capture a photo Event Button
    public void capturePhotoEventButtonClicked(View view){
        startActivity(new Intent(this, CapturePhotoEventActivity.class));
    }

    //Click of Make a phone call Event Button
    public void makePhoneCallEventButtonClicked(View view){
        startActivity(new Intent(this, PhoneCallEventActivity.class));
    }
}