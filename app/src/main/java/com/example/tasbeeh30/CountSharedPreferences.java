// CountSharedPreferences.java
package com.example.tasbeeh30;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CountSharedPreferences {
    private static final String PREF_NAME = "CountPreferences";
    private static final String COUNT_HISTORY_KEY = "countHistory";

    // Save count history to SharedPreferences
    public static void saveCountHistory(Context context, ArrayList<CountEntry> countHistory) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit();
        Set<String> countHistorySet = new HashSet<>();

        for (CountEntry entry : countHistory) {
            countHistorySet.add(serializeCountEntry(entry));
        }

        editor.putStringSet(COUNT_HISTORY_KEY, countHistorySet);
        editor.apply();
    }

    // Retrieve count history from SharedPreferences
    public static ArrayList<CountEntry> getCountHistory(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Set<String> countHistorySet = prefs.getStringSet(COUNT_HISTORY_KEY, null);

        if (countHistorySet != null) {
            ArrayList<CountEntry> countHistory = new ArrayList<>();

            for (String serializedEntry : countHistorySet) {
                countHistory.add(deserializeCountEntry(serializedEntry));
            }

            return countHistory;
        }

        return new ArrayList<>();
    }

    private static String serializeCountEntry(CountEntry entry) {
        return entry.getCount() + "," + entry.getTimestamp();
    }

    private static CountEntry deserializeCountEntry(String serializedEntry) {
        String[] parts = serializedEntry.split(",");
        int count = Integer.parseInt(parts[0]);
        long timestamp = Long.parseLong(parts[1]);
        return new CountEntry(count, timestamp);
    }
}
