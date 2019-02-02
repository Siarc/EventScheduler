package com.example.eventscheduler.roomDatabase;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.example.eventscheduler.dao.EventDao;
import com.example.eventscheduler.dao.Event;

import java.util.List;

public class EventRepository {

    private static final String TAG = "EventRepository";

    private EventDao eventDao;
    private LiveData<List<Event>> allEvents;

    public EventRepository(Application application) {
        EventDatabase database = EventDatabase.getInstance(application);
        eventDao = database.eventDao();
        allEvents = eventDao.getAllEvents();
    }

    public void insert(Event event) {
        new InsertNoteAsyncTask(eventDao).execute(event);
    }

    public void update(Event event) {
        new UpdateNoteAsyncTask(eventDao).execute(event);
    }

    public void delete(Event event) {
        new DeleteNoteAsyncTask(eventDao).execute(event);
    }

    public void deleteAllEvents() {
        new DeleteAllNotesAsyncTask(eventDao).execute();
    }

    public LiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao eventDao;

        private InsertNoteAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.insert(events[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao eventDao;

        private UpdateNoteAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.update(events[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Event, Void, Void> {
        private EventDao eventDao;

        private DeleteNoteAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.delete(events[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private EventDao eventDao;

        private DeleteAllNotesAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            eventDao.deleteAllEvents();
            return null;
        }
    }



}
