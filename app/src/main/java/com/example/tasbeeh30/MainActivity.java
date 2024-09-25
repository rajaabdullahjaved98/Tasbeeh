// MainActivity.java
package com.example.tasbeeh30;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView countView;
    private Button countButton;
    private Button resetButton;
    private Button viewHistoryButton;
    private int count = 0;
    private ArrayList<CountEntry> countHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize countHistory from SharedPreferences
        countHistory = CountSharedPreferences.getCountHistory(this);

        // Define UI Components
        countView = findViewById(R.id.count);
        countButton = findViewById(R.id.count_btn);
        resetButton = findViewById(R.id.reset_btn);
        viewHistoryButton = findViewById(R.id.history_btn);

        // Set initial count
        updateCountView();

        // Button Listeners
        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                updateCountView();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countHistory.add(new CountEntry(count, System.currentTimeMillis()));
                CountSharedPreferences.saveCountHistory(MainActivity.this, countHistory);
                count = 0;
                updateCountView();
            }
        });

        viewHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent countIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(countIntent);
            }
        });
    }

    private void updateCountView() {
        countView.setText("Count: " + count);
    }
}
