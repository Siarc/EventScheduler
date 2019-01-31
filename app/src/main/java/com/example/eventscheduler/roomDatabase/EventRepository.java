package com.example.eventscheduler.roomDatabase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.eventscheduler.dao.EventDao;
import com.example.eventscheduler.databaseModels.Event;

import java.util.List;

public class EventRepository {

    private static final String TAG = "EventRepository";

    public EventDao eventDao;
    private LiveData<List<Event>> allEvents;

    public EventRepository(Application application){

        EventDatabase eventDatabase = EventDatabase.getInstance(application);
        eventDao = eventDatabase.eventDao();
        allEvents = eventDao.getAllEvents();
        Log.d(TAG, "EventRepository: "+allEvents);
    }

    public void insert(Event event){

        new InsertEventAsyncTask(eventDao).execute(event);
    }

    public void update(Event event){

        new UpdateEventAsyncTask(eventDao).execute(event);
    }

    public void delete(Event event){

        new DeleteEventAsyncTask(eventDao).execute(event);
    }

    public  void deleteAllEvents(){

        new DeleteAllEventsAsyncTask(eventDao).execute();
    }

    public LiveData<List<Event>> getAllEvents(){
        return allEvents;
    }

    private static class InsertEventAsyncTask extends AsyncTask<Event,Void, Void>{

        private EventDao eventDao;

        private InsertEventAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {

            eventDao.insert(events[0]);
            return null;
        }
    }

    private static class UpdateEventAsyncTask extends AsyncTask<Event,Void, Void>{

        private EventDao eventDao;

        private UpdateEventAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {

            eventDao.update(events[0]);
            return null;
        }
    }

    private static class DeleteEventAsyncTask extends AsyncTask<Event,Void, Void>{

        private EventDao eventDao;

        private DeleteEventAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {

            eventDao.delete(events[0]);
            return null;
        }
    }

    private static class DeleteAllEventsAsyncTask extends AsyncTask<Void,Void, Void>{

        private EventDao eventDao;

        private DeleteAllEventsAsyncTask(EventDao eventDao){
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            eventDao.deleteAllEvents();
            return null;
        }
    }



}
