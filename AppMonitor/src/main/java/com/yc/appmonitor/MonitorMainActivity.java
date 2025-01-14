package com.yc.appmonitor;


import android.content.Intent;
import android.view.View;

import com.yc.library.base.mvp.BaseActivity;
import com.yc.monitorfilelib.FileExplorerActivity;
import com.yc.monitorphone.MonitorPhoneActivity;
import com.yc.monitorpinglib.MonitorPingActivity;
import com.yc.monitorspeed.ConnectionActivity;
import com.yc.roundcorner.view.RoundTextView;
import com.yc.toollib.crash.CrashListActivity;
import com.yc.toolutils.click.PerfectClickListener;

public class MonitorMainActivity extends BaseActivity {

    private RoundTextView tvPhoneMonitor;
    private RoundTextView tvPingMonitor;
    private RoundTextView tvFileMonitor;
    private RoundTextView tvCrashMonitor;
    private RoundTextView tvCrashTest;
    private RoundTextView tvNetSpeed;

    @Override
    public int getContentView() {
        return R.layout.activity_monitor_main;
    }

    @Override
    public void initView() {
        tvPhoneMonitor = findViewById(R.id.tv_phone_monitor);
        tvPingMonitor = findViewById(R.id.tv_ping_monitor);
        tvFileMonitor = findViewById(R.id.tv_file_monitor);
        tvCrashMonitor = findViewById(R.id.tv_crash_monitor);
        tvCrashTest = findViewById(R.id.tv_crash_test);
        tvNetSpeed = findViewById(R.id.tv_net_speed);
    }

    @Override
    public void initListener() {
        tvPhoneMonitor.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                MonitorPhoneActivity.startActivity(MonitorMainActivity.this);
            }
        });
        tvPingMonitor.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                MonitorPingActivity.startActivity(MonitorMainActivity.this
                        ,"https://www.wanandroid.com/banner/json");
            }
        });
        tvFileMonitor.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                FileExplorerActivity.startActivity(MonitorMainActivity.this);
            }
        });
        tvCrashMonitor.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                CrashListActivity.startActivity(MonitorMainActivity.this);
            }
        });
        tvCrashTest.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent = new Intent(MonitorMainActivity.this, CrashTestActivity.class);
                startActivity(intent);
            }
        });
        tvNetSpeed.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                ConnectionActivity.startActivity(MonitorMainActivity.this);
            }
        });
    }

    @Override
    public void initData() {

    }

}