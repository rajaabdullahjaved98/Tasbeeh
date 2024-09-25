// HistoryActivity.java
package com.example.tasbeeh30;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ListView historyList = findViewById(R.id.count_list);

        // Retrieve count history from SharedPreferences
        ArrayList<CountEntry> countHistory = CountSharedPreferences.getCountHistory(this);

        if (countHistory != null) {
            CountHistoryAdapter historyAdapter = new CountHistoryAdapter(this, R.layout.count_list_item, countHistory);
            historyList.setAdapter(historyAdapter);

            historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    showDateTimeDialog(countHistory.get(position));
                }
            });
        }
    }

    private void showDateTimeDialog(CountEntry entry) {
        String formattedDate = formatDate(entry.getTimestamp());
        String dialogMessage = "Count saved at: " + formattedDate;

        // Build and show the AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(dialogMessage)
                .setTitle("Count Information")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private String formatDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }

}
