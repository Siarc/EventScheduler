package com.example.eventscheduler.activities;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.eventscheduler.roomDatabase.EventViewModel;
import com.example.eventscheduler.R;
import com.example.eventscheduler.dao.Event;
import com.example.eventscheduler.utils.EventAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private EventViewModel eventViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setRecyclerView();

    }

    public void init(){

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SchedulerActivity.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        Log.d(TAG, "onActivityResult: data result function");
//
//
//        if(requestCode == ADD_EVENT_REQUEST && resultCode == RESULT_OK){
//
//            Log.d(TAG, "onActivityResult: requestCode and resultCode matched");
//
//            String eventName = data.getStringExtra(SchedulerActivity.EXTRA_NAME);
//            String eventNote = data.getStringExtra(SchedulerActivity.EXTRA_NOTE);
//            String eventFromDate = data.getStringExtra(SchedulerActivity.EXTRA_FROM_DATE);
//            String eventToDate = data.getStringExtra(SchedulerActivity.EXTRA_TO_DATE);
//            String eventReminder = data.getStringExtra(SchedulerActivity.EXTRA_REMINDER);
//            int eventRequestCode = Integer.parseInt(data.getStringExtra(SchedulerActivity.EXTRA_REQUEST_CODE));
//
//
//            Event event = new Event(eventName,eventFromDate,eventToDate,eventReminder,eventNote,eventRequestCode);
//            eventViewModel.insert(event);
//
//            Toast.makeText(this, "Event Saved", Toast.LENGTH_SHORT).show();
//
//        }else {
//            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
//        }
//    }

    private void setRecyclerView() {

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final EventAdapter eventAdapter = new EventAdapter();
        recyclerView.setAdapter(eventAdapter);

        eventViewModel = ViewModelProviders.of(this).get(EventViewModel.class);
        eventViewModel.getAllEvents().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {

                //update recyclerView
                eventAdapter.setEvents(events);
                Log.d(TAG, "onChanged: "+events);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
