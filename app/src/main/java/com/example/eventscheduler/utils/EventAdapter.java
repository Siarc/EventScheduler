package com.example.eventscheduler.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eventscheduler.R;
import com.example.eventscheduler.databaseModels.Event;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder> {

    private List<Event> events = new ArrayList<>();

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_recycler,viewGroup,false);

        return new EventHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder eventHolder, int i) {

        Event currentEvent = events.get(i);
        eventHolder.tvEventName.setText(currentEvent.getEventName());
        eventHolder.tvEventNote.setText(currentEvent.getNote());
        eventHolder.tvEventDate.setText(currentEvent.getFromDate());
        eventHolder.tvEventReminder.setText(currentEvent.getAlarmReminder());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void setEvents(List<Event> events){

        this.events = events;
        notifyDataSetChanged();

    }

    class EventHolder extends RecyclerView.ViewHolder{

        private TextView tvEventName;
        private TextView tvEventNote;
        private TextView tvEventDate;
        private TextView tvEventReminder;

        public EventHolder(View itemView){
            super(itemView);
            tvEventName = itemView.findViewById(R.id.eventName);
            tvEventNote = itemView.findViewById(R.id.eventNote);
            tvEventDate = itemView.findViewById(R.id.eventDate);
            tvEventReminder = itemView.findViewById(R.id.eventReminder);
        }
    }



}
