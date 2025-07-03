package com.example.timescreeningapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AppAdapter extends BaseAdapter {
    private Context context;
    private List<AppInfo> appList;

    public AppAdapter(Context context, List<AppInfo> appList) {
        this.context = context;
        this.appList = appList;
    }

    @Override
    public int getCount() {
        return appList.size();
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        ImageView appIcon = convertView.findViewById(R.id.appIcon);
        TextView appName = convertView.findViewById(R.id.appName);
        TextView packageName = convertView.findViewById(R.id.packageName);

        AppInfo appInfo = appList.get(position);

        appIcon.setImageDrawable(appInfo.getAppIcon());
        appName.setText(appInfo.getAppName());
        packageName.setText(appInfo.getPackageName());

        // Open UsageDetailsActivity on icon click
        appIcon.setOnClickListener(v -> {
            Intent intent = new Intent(context, UsageDetailsActivity.class);
            intent.putExtra("PACKAGE_NAME", appInfo.getPackageName());
            context.startActivity(intent);
        });

        return convertView;
    }
}
