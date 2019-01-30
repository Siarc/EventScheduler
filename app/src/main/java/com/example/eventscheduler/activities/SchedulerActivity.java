package com.example.eventscheduler.activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eventscheduler.R;
import com.example.eventscheduler.utils.AlarmBroadCastReceiver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SchedulerActivity extends AppCompatActivity {

    private static final String TAG = "SchedulerActivity";

    String from_date_time = "";
    int mYear;
    int mMonth;
    int mDay;
    int mHour;
    int mMinute;

    String to_date_time ="";
    int nYear;
    int nMonth;
    int nDay;
    int nHour;
    int nMinute;

    private ImageView close, accept;
    private EditText eventName, eventNote;
    private TextView fromDate, toDate;
    private RelativeLayout rlFrom, rlTo, rlSetAlarm;
    private Spinner spinAlarmTimes;

    private List<String> alarmTimes = new ArrayList<>();

    private DatePickerDialog.OnDateSetListener fromDateSetListener;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);

        populateAlarmTimes();
        init();
        onClickListeners();
        spinnerSetUp();
        onTimeSelectedListener();

    }

    private void init() {

        close =  findViewById(R.id.close);
        accept =  findViewById(R.id.accept);
        eventName = findViewById(R.id.eventName);
        eventNote = findViewById(R.id.eventNote);
        fromDate = findViewById(R.id.fromDate);
        toDate = findViewById(R.id.toDate);
        rlFrom = findViewById(R.id.rlFrom);
        rlTo = findViewById(R.id.rlTo);
        rlSetAlarm = findViewById(R.id.rlSetAlarm);
        spinAlarmTimes = findViewById(R.id.spinAlarmTimes);


    }

    private void populateAlarmTimes() {

        alarmTimes.add(0,"None");
        alarmTimes.add("Before the event");
        alarmTimes.add("5 minutes before");
        alarmTimes.add("15 minutes before");
        alarmTimes.add("30 minutes before");
        alarmTimes.add("1 hour before");
        alarmTimes.add("1 day before");
        alarmTimes.add("1 week before");
    }

    private void onTimeSelectedListener() {

        spinAlarmTimes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, "onItemSelected: "+parent.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                //do nothing
            }
        });
    }

    private void spinnerSetUp() {

        Log.d(TAG, "spinnerFunction: "+alarmTimes);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,alarmTimes);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinAlarmTimes.setAdapter(spinnerAdapter);

    }




    private void setAlarm(String toString) {

        int requestCode = getRandomRequestCode(1,999999999);
        long time = formatTime();

        Log.d(TAG, "setAlarm: RequestCode: "+requestCode);
        Log.d(TAG, "setAlarm: time before format: "+ time);



        //Alarm Manager
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        //Intent for Broadcast receiver
        Intent intent = new Intent(this, AlarmBroadCastReceiver.class);
        //Pending intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,requestCode,intent,0);
        //setting up alarm




        Log.d(TAG, "setAlarm: time: "+time);

        alarmManager.set(AlarmManager.RTC_WAKEUP,time,pendingIntent);


    }

    private long formatTime() {

        long time = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date mDate;

        Log.d(TAG, "formatTime: Date trying to format: "+ fromDate.getText().toString());
        try {
            mDate = format.parse(fromDate.getText().toString());
            Log.d(TAG, "formatTime: mDate: "+mDate);
            time = mDate.getTime();
            Log.d(TAG, "formatTime: time: "+time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;

    }

    //Random number function
    private int getRandomRequestCode(int min, int max) {

        int randomNum = ThreadLocalRandom.current().nextInt(min,max+1);
        return randomNum;
    }

    private void onClickListeners() {

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SchedulerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setting up alarm
                setAlarm(spinAlarmTimes.getSelectedItem().toString());

//                if(spinAlarmTimes.getSelectedItem().toString().equals("None")){
//
//                    //does not do anything for the alarm itself
//                    Log.d(TAG, "onClick: doing nothing");
//
//                }else {
//
//                }

                //Intent intent = new Intent(SchedulerActivity.this, MainActivity.class);
                //startActivity(intent);
            }
        });

        rlFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setting the date
                fromDatePicker();


            }
        });
        rlTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setting the date
                toDatePicker();

                //Log.d(TAG, "onClick: "+mYear+"-"+mMonth+1+"-"+mDay+"-"+mHour+"-"+mMinute);

            }
        });

    }

    private void fromDatePicker() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {



                        if(dayOfMonth<10){
                            from_date_time = "0"+dayOfMonth;
                        }else {
                            from_date_time = String.valueOf(dayOfMonth);
                        }
                        if(monthOfYear<10){
                            from_date_time = from_date_time+"-0" + (monthOfYear + 1) + "-" + year;
                        }else{
                            from_date_time = from_date_time+"-" + (monthOfYear + 1) + "-" + year;
                        }


                        //*************Call Time Picker Here ********************
                        fromTimePicker();
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    private void fromTimePicker() {

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        mHour = hourOfDay;
                        mMinute = minute;
                        String date = from_date_time+" "+String.format("%02d:%02d", hourOfDay, minute);

                        fromDate.setText(date);
                        Log.d(TAG, "onTimeSet: "+date);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }

    private void toDatePicker() {

        // Get Current Date
        final Calendar c = Calendar.getInstance();
        nYear = c.get(Calendar.YEAR);
        nMonth = c.get(Calendar.MONTH);
        nDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth) {

                        to_date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        //*************Call Time Picker Here ********************
                        toTimePicker();
                    }
                }, nYear, nMonth, nDay);
        datePickerDialog.show();


    }

    private void toTimePicker() {

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        nHour = c.get(Calendar.HOUR_OF_DAY);
        nMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        nHour = hourOfDay;
                        nMinute = minute;

                        toDate.setText(to_date_time+" "+hourOfDay + ":" + minute);
                    }
                }, nHour, nMinute, false);
        timePickerDialog.show();


    }

}
