package com.example.jatinder_assessment_impint;

import androidx.appcompat.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;
import java.util.Date;

public class CalendarEventActivity extends AppCompatActivity {

    EditText eTitle, eDescription, eEventInviteeEmails, et_startTime, et_endTime;
    Button btnAddEvent;
    CheckBox cbAllDayEvent;
    Switch sw_accessType;
    TimePickerDialog timePickerDialog;
    Calendar calendarStart, calendarEnd;
    int currentHour, currentMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event);

        eTitle = findViewById(R.id.et_eventTitle);
        eDescription = findViewById(R.id.et_eventDescription);
        btnAddEvent = findViewById(R.id.btnAddEvent);
        sw_accessType = findViewById(R.id.sw_accessType);
        eEventInviteeEmails = findViewById(R.id.et_eventInviteeEmails);
        et_startTime = findViewById(R.id.et_startTime);
        et_endTime = findViewById(R.id.et_endTime);
        cbAllDayEvent = findViewById(R.id.cb_AllDay);

        et_startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarStart = Calendar.getInstance();
                currentHour = calendarStart.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendarStart.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(CalendarEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                 @Override
                 public  void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                     et_startTime.setText(String.format("%02d:%02d", hours, minutes));
                 }
                }, currentHour,currentMinute,true);
                timePickerDialog.show();
            }
        });


        et_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarEnd = Calendar.getInstance();
                currentHour = calendarEnd.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendarEnd.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(CalendarEventActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public  void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                        et_endTime.setText(String.format("%02d:%02d", hours, minutes));
                    }
                }, currentHour,currentMinute,true);
                timePickerDialog.show();
            }
        });
    }

    public void AddEventButtonClicked(View view){
        int hourStart, minuteStart, hourEnd, minuteEnd;

        hourStart = Integer.parseInt(et_startTime.getText().toString().substring(0, 2));
        minuteStart = Integer.parseInt(et_startTime.getText().toString().substring(3, 5));
        hourEnd = Integer.parseInt(et_endTime.getText().toString().substring(0, 2));
        minuteEnd = Integer.parseInt(et_endTime.getText().toString().substring(3, 5));

        Calendar startTime = Calendar.getInstance();
        Date date = startTime.getTime();

        startTime.set(date.getYear() + 1900, date.getMonth() , date.getDate(), hourStart, minuteStart);
        Calendar endTime = Calendar.getInstance();
        endTime.set(date.getYear() + 1900, date.getMonth() , date.getDate(), hourEnd, minuteEnd);

        if(!eTitle.getText().toString().isEmpty() && !eDescription.getText().toString().isEmpty()
                    && !eEventInviteeEmails.getText().toString().isEmpty()){

            String[] invitees = eEventInviteeEmails.getText().toString().split(",");

            Intent intent = new Intent(Intent.ACTION_INSERT);
            intent.setData(CalendarContract.Events.CONTENT_URI);
            intent.putExtra(CalendarContract.Events.TITLE, eTitle.getText().toString());
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.getTimeInMillis());
            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis());
            intent.putExtra(CalendarContract.Events.DESCRIPTION, eDescription.getText().toString());
            intent.putExtra(CalendarContract.Events.ALL_DAY, cbAllDayEvent.isChecked() ? true : false);
            intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, sw_accessType.isChecked() ? CalendarContract.Events.ACCESS_PRIVATE : CalendarContract.Events.ACCESS_PUBLIC);
            intent.putExtra(Intent.EXTRA_EMAIL, invitees);

            if(intent.resolveActivity(getPackageManager()) != null){
                startActivity(intent);
            } else{
                Toast.makeText(CalendarEventActivity.this, "Calendar App not supported", Toast.LENGTH_LONG).show();
            }
        } else{
            Toast.makeText(CalendarEventActivity.this, "Please enter event details", Toast.LENGTH_LONG).show();
        }
    }
}