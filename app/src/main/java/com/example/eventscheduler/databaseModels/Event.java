package com.example.eventscheduler.databaseModels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "event_table")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String eventName;
    private String fromDate;
    private String toDate;
    private String alarmReminder;
    private String note;
    private int requestCode;

    public Event(String eventName, String fromDate, String toDate, String alarmReminder, String note, int requestCode) {
        this.eventName = eventName;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.alarmReminder = alarmReminder;
        this.note = note;
        this.requestCode = requestCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public String getAlarmReminder() {
        return alarmReminder;
    }

    public String getNote() {
        return note;
    }

    public int getRequestCode() {
        return requestCode;
    }
}
