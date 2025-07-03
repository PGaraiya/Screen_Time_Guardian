package com.example.timescreeningapp;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class UsageDetailsActivity extends AppCompatActivity {
    private TextView appNameText, todayUsageText, totalUsageText;
    private String packageName;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_details);

        b1=(Button)findViewById(R.id.startapp);

        appNameText = findViewById(R.id.app_name_text);
        todayUsageText = findViewById(R.id.today_usage_text);
        totalUsageText = findViewById(R.id.total_usage_text);

        Intent intent = getIntent();
        packageName = intent.getStringExtra("PACKAGE_NAME");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApp(packageName);
            }
        });



        if (packageName != null) {
            appNameText.setText("App: " + packageName);
            showUsageStats(packageName);
        } else {
            Toast.makeText(this, "Error fetching app usage", Toast.LENGTH_SHORT).show();
        }
    }

    private void showUsageStats(String packageName) {
        UsageStatsManager usageStatsManager = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        long currentTime = System.currentTimeMillis();

        // Get usage stats for today (from midnight to now)
        long startOfDay = getStartOfDayTimestamp();
        Map<String, UsageStats> statsMap = usageStatsManager.queryAndAggregateUsageStats(startOfDay, currentTime);
        UsageStats todayUsageStats = statsMap.get(packageName);

        // Get total usage stats (from the last month)
        long startOfMonth = currentTime - (30L * 24 * 60 * 60 * 1000);
        Map<String, UsageStats> totalStatsMap = usageStatsManager.queryAndAggregateUsageStats(startOfMonth, currentTime);
        UsageStats totalUsageStats = totalStatsMap.get(packageName);

        // Calculate usage time in minutes
        long todayUsageMinutes = todayUsageStats != null ? todayUsageStats.getTotalTimeInForeground() / (1000 * 60) : 0;
        long totalUsageMinutes = totalUsageStats != null ? totalUsageStats.getTotalTimeInForeground() / (1000 * 60) : 0;

        todayUsageText.setText("Today's Usage: " + todayUsageMinutes + " minutes");
        totalUsageText.setText("Total Usage: " + totalUsageMinutes + " minutes");
    }

    private long getStartOfDayTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        try {
            Date date = dateFormat.parse(dateFormat.format(new Date()));
            return date != null ? date.getTime() : System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
            return System.currentTimeMillis();
        }
    }

    private void openApp(String packageName) {
        PackageManager pm = getPackageManager();
        Intent launchIntent = pm.getLaunchIntentForPackage(packageName);

        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {
            Toast.makeText(this, "Unable to open app", Toast.LENGTH_SHORT).show();
        }
    }
}
