package com.example.eventscheduler.roomDatabase;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.eventscheduler.dao.EventDao;
import com.example.eventscheduler.databaseModels.Event;

@Database(entities = {Event.class}, version = 1)
public abstract class EventDatabase extends RoomDatabase {

    private static final String TAG = "EventDatabase";

    private static EventDatabase instance;

    public abstract EventDao eventDao();

    public static synchronized EventDatabase getInstance(Context context){

        Log.d(TAG, "getInstance: Create database instance");
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EventDatabase.class,"event_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
            Log.d(TAG, "getInstance: instance was null");

        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {

            Log.d(TAG, "onCreate: populate database");
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();


        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private EventDao eventDao;

        private PopulateDbAsyncTask(EventDatabase db){

            Log.d(TAG, "PopulateDbAsyncTask: what is this doing?");
            eventDao = db.eventDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            Log.d(TAG, "doInBackground: database sample data insert");
            eventDao.insert(new Event("Group meeting","12-2-2019 10:10","12-2-2019 13:10","Before the event","Project discussion",20));
            eventDao.insert(new Event("Group meeting","13-2-2019 10:10","13-2-2019 13:10","Before the event","SRS discussion",21));
            eventDao.insert(new Event("Group meeting","14-2-2019 10:10","14-2-2019 13:10","Before the event","Function finalization",22));
            return null;
        }
    }


}
