package com.example.eventscheduler.roomDatabase;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.eventscheduler.databaseModels.Event;

import java.util.List;

public class EventViewModel extends AndroidViewModel {

    private static final String TAG = "EventViewModel";

    private EventRepository eventRepository;
    private LiveData<List<Event>> allEvents;

    public EventViewModel(@NonNull Application application) {
        super(application);

        eventRepository = new EventRepository(application);
        allEvents = eventRepository.getAllEvents();
        Log.d(TAG, "EventViewModel: "+allEvents);

    }

    public void insert(Event event){
        eventRepository.insert(event);
    }

    public void update(Event event){
        eventRepository.update(event);
    }

    public void delete(Event event){
        eventRepository.delete(event);
    }

    public void deleteAllEvents(){
        eventRepository.deleteAllEvents();
    }

    public LiveData<List<Event>> getAllEvents(){
        return allEvents;
    }


}
