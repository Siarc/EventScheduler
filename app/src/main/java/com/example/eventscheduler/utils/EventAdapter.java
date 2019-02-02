package com.example.eventscheduler.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.eventscheduler.R;
import com.example.eventscheduler.dao.Event;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends ListAdapter<Event,EventAdapter.EventHolder> {

    private AdapterView.OnItemClickListener listener;

    public EventAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Event> DIFF_CALLBACK = new DiffUtil.ItemCallback<Event>() {
        @Override
        public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
            return false;
        }
    };

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_recycler,viewGroup,false);

        return new EventHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventHolder eventHolder, int i) {

        Event currentEvent = getItem(i);
        eventHolder.tvEventName.setText(currentEvent.getEventName());
        eventHolder.tvEventNote.setText(currentEvent.getNote());
        eventHolder.tvEventDate.setText(currentEvent.getFromDate());
        eventHolder.tvEventReminder.setText(currentEvent.getAlarmReminder());
    }

    //return event when item at position swiped
    public Event getEventAt(int position) {
        return getItem(position);
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
