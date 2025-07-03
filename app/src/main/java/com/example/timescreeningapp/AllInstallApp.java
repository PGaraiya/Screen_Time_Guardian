package com.example.timescreeningapp;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timescreeningapp.AppAdapter;
import com.example.timescreeningapp.AppInfo;

import java.util.ArrayList;
import java.util.List;

public class AllInstallApp extends AppCompatActivity {
    private ListView listView;
    private List<AppInfo> appList;
    private AppAdapter appAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allinstallapp);

        listView = findViewById(R.id.lst11);
        appList = getInstalledApps();
        appAdapter = new AppAdapter(this, appList);
        listView.setAdapter(appAdapter);
        // Inside onCreate()
        if (!hasUsageAccessPermission()) {
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
            Toast.makeText(this, "Please grant usage access permission", Toast.LENGTH_LONG).show();
        }
    }

    private List<AppInfo> getInstalledApps() {
        List<AppInfo> apps = new ArrayList<>();
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            String appName = (String) pm.getApplicationLabel(packageInfo);
            String packageName = packageInfo.packageName;
            Drawable appIcon = pm.getApplicationIcon(packageInfo);
            apps.add(new AppInfo(appName, packageName, appIcon));
        }
        return apps;
    }

    private boolean hasUsageAccessPermission() {
        AppOpsManager appOps = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), getPackageName());
        return mode == AppOpsManager.MODE_ALLOWED;
    }
}
