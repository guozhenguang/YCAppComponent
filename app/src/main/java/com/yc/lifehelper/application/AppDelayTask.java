package com.yc.lifehelper.application;

import android.os.Looper;

import com.yc.appstart.AppStartTask;
import com.yc.appstatuslib.AppStatusManager;
import com.yc.appstatuslib.backgroud.AppStateMonitor;
import com.yc.appstatuslib.info.BatteryInfo;
import com.yc.appstatuslib.info.ThreadInfo;
import com.yc.appstatuslib.listener.BaseStatusListener;
import com.yc.toolutils.file.AppFileUtils;
import com.yc.toolutils.logger.AppLogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppDelayTask extends AppStartTask {
    @Override
    public void run() {
        long start = System.currentTimeMillis();
        //各种状态监听回调
        initAppStatusListener();
        //OOM上报日志操作库
        //DMemoryLeak.installLeakCanary(MainApplication.getInstance(),true);
        try {
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        boolean isMainThread = (Looper.myLooper() == Looper.getMainLooper());
        AppLogUtils.i("app init 3 task delay total time : " + (end-start)
                + " ; 线程是否是主线程" + isMainThread);
    }

    @Override
    public boolean isRunOnMainThread() {
        return true;
    }

    @Override
    public List<Class<? extends AppStartTask>> getDependsTaskList() {
        List<Class<? extends AppStartTask>> dependsTaskList = new ArrayList<>();
        dependsTaskList.add(AppMonitorTask.class);
        return dependsTaskList;
    }

    private void initAppStatusListener() {
        String cachePath = AppFileUtils.getSrcFilePath(MainApplication.getInstance(),"cache");
        String path = cachePath + File.separator + "status";
        File file = new File(path);
        AppStatusManager manager = new AppStatusManager.Builder()
                .context(MainApplication.getInstance())
                .interval(5)
                .file(file)
                .threadSwitchOn(false)
                .builder();
        manager.registerAppStatusListener(new BaseStatusListener() {
            @Override
            public void wifiStatusChange(boolean isWifiOn) {
                super.wifiStatusChange(isWifiOn);
                if (isWifiOn){
                    AppLogUtils.i("app status Wifi 打开");
                } else {
                    AppLogUtils.i("app status Wifi 关闭");
                }
            }

            @Override
            public void gpsStatusChange(boolean isGpsOn) {
                super.gpsStatusChange(isGpsOn);
                if (isGpsOn){
                    AppLogUtils.i("app status Gps 打开");
                } else {
                    AppLogUtils.i("app status Gps 关闭");
                }
            }

            @Override
            public void networkStatusChange(boolean isConnect) {
                super.networkStatusChange(isConnect);
                if (isConnect){
                    AppLogUtils.i("app status Network 打开");
                } else {
                    AppLogUtils.i("app status Network 关闭");
                }
            }

            @Override
            public void screenStatusChange(boolean isScreenOn) {
                super.screenStatusChange(isScreenOn);
                if (isScreenOn){
                    AppLogUtils.i("app status Screen 打开");
                } else {
                    AppLogUtils.i("app status Screen 关闭");
                }
            }

            @Override
            public void screenUserPresent() {
                super.screenUserPresent();
                AppLogUtils.i("app status Screen 使用了");
            }

            @Override
            public void appOnFrontOrBackChange(boolean isBack) {
                super.appOnFrontOrBackChange(isBack);
                if (isBack){
                    AppLogUtils.i("app status app 推到后台");
                } else {
                    AppLogUtils.i("app status app 回到前台");
                }
            }

            @Override
            public void bluetoothStatusChange(boolean isBluetoothOn) {
                super.bluetoothStatusChange(isBluetoothOn);
                if (isBluetoothOn){
                    AppLogUtils.i("app status 蓝牙 打开");
                } else {
                    AppLogUtils.i("app status 蓝牙 关闭");
                }
            }

            @Override
            public void batteryStatusChange(BatteryInfo batteryInfo) {
                super.batteryStatusChange(batteryInfo);
                AppLogUtils.i("app status 电量 " + batteryInfo.toStringInfo());
            }

            @Override
            public void appThreadStatusChange(ThreadInfo threadInfo) {
                super.appThreadStatusChange(threadInfo);
                AppLogUtils.i("app status 所有线程数量 " + threadInfo.getThreadCount());
                AppLogUtils.i("app status run线程数量 " + threadInfo.getRunningThreadCount().size());
                AppLogUtils.i("app status wait线程数量 " + threadInfo.getWaitingThreadCount().size());
                AppLogUtils.i("app status block线程数量 " + threadInfo.getBlockThreadCount().size());
                AppLogUtils.i("app status timewait线程数量 " + threadInfo.getTimeWaitingThreadCount().size());
            }
        });

        AppStateMonitor.getInstance().registerAppStateListener(new AppStateMonitor.AppStateListener() {
            @Override
            public void onInForeground() {
                AppLogUtils.i("app status 在后台");
            }

            @Override
            public void onInBackground() {
                AppLogUtils.i("app status 在前台");
            }
        });
    }

}