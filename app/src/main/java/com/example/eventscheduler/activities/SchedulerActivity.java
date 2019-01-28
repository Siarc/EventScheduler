package com.example.eventscheduler.activities;

import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.eventscheduler.R;

import java.util.Calendar;

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
    private RelativeLayout rlFrom, rlTo;
    private Context context;

    private DatePickerDialog.OnDateSetListener orderDateSetListener;
    private DatePickerDialog.OnDateSetListener fromDateSetListener;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);

        init();
        onClickListeners();
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

                Intent intent = new Intent(SchedulerActivity.this, MainActivity.class);
                startActivity(intent);
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

                        from_date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
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

                        fromDate.setText(from_date_time+" "+hourOfDay + ":" + minute);
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
