package com.example.timescreeningapp;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddRestrication1 extends AppCompatActivity {
    private ListView listView;
    private List<AppInfo> appList;
    private AppAdapter appAdapter;
    String packagename1[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allinstallapp);

        listView = findViewById(R.id.lst11);
        appList = getInstalledApps();
        appAdapter = new AppAdapter(this, appList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(AddRestrication1.this, AddRetrication2.class);
                intent.putExtra("PACKAGE_NAME", packagename1[position]);
                startActivity(intent);

            }
        });

        listView.setAdapter(appAdapter);
        // Inside onCreate()
        if (!hasUsageAccessPermission()) {
            startActivity(new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS));
            Toast.makeText(this, "Please grant usage access permission", Toast.LENGTH_LONG).show();
        }
    }

    private List<AppInfo> getInstalledApps() {
        List<AppInfo> apps = new ArrayList<>();
        int count=0;
        PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        packagename1=new String[packages.size()];
        for (ApplicationInfo packageInfo : packages) {
            String appName = (String) pm.getApplicationLabel(packageInfo);
            String packageName = packageInfo.packageName;
            Drawable appIcon = pm.getApplicationIcon(packageInfo);
            apps.add(new AppInfo(appName, packageName, appIcon));
            packagename1[count]= packageInfo.packageName;
            count=count+1;
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
