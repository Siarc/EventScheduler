package com.example.eventscheduler.roomDatabase;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import android.util.Log;

import com.example.eventscheduler.dao.EventDao;
import com.example.eventscheduler.dao.Event;

@Database(entities = {Event.class}, version = 1,exportSchema = false)
public abstract class EventDatabase extends RoomDatabase {

    private static final String TAG = "EventDatabase";

    private static EventDatabase instance;

    public abstract EventDao eventDao();

    public static synchronized EventDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EventDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private EventDao eventDao;

        private PopulateDbAsyncTask(EventDatabase db) {
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


