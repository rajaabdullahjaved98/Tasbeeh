package com.example.tasbeeh30;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CountHistoryAdapter extends ArrayAdapter<CountEntry> {

    private Context context;
    private int resource;

    public CountHistoryAdapter(@NonNull Context context, int resource, @NonNull ArrayList<CountEntry> countHistory) {
        super(context, resource, countHistory);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, null);
        }

        CountEntry entry = getItem(position);
        if (entry != null) {
            int p = position + 1;
            TextView countText = view.findViewById(R.id.count_text);
            TextView dateTimeText = view.findViewById(R.id.date_time_text);

            countText.setText("Count  " + p + ": " + entry.getCount() + " Times");
            dateTimeText.setText("Saved at: " + formatDate(entry.getTimestamp()));
        }

        return view;
    }

    private String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(timestamp));
    }
}

