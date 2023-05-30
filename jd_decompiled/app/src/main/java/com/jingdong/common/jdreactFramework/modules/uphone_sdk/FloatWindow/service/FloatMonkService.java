package com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.receiver.HomeWatcherReceiver;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.view.FloatWindowManager;
import com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.view.TestCallBack;

/* loaded from: classes5.dex */
public class FloatMonkService extends Service {
    private static final String UPhoneFloatWTAG = FloatMonkService.class.getSimpleName();
    private TestCallBack mCallBack;
    private HomeWatcherReceiver mHomeKeyReceiver;
    private boolean mRunTimer;
    private int mShowTime;
    private PowerManager mUPhoneFloatWPowerManager;
    private PowerManager.WakeLock mUPhoneFloatWWakeLock;

    /* loaded from: classes5.dex */
    public class MsgBinder extends Binder {
        public MsgBinder() {
            FloatMonkService.this = r1;
        }

        public FloatMonkService getService() {
            return FloatMonkService.this;
        }
    }

    private void initWindowData() {
        FloatWindowManager.createFloatWindow(this);
    }

    public void click() {
        this.mCallBack.click();
    }

    public void hide() {
        this.mRunTimer = false;
        FloatWindowManager.hide();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new MsgBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mHomeKeyReceiver = new HomeWatcherReceiver();
        registerReceiver(this.mHomeKeyReceiver, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        initWindowData();
        PowerManager powerManager = (PowerManager) getSystemService("power");
        this.mUPhoneFloatWPowerManager = powerManager;
        if (powerManager != null) {
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(26, UPhoneFloatWTAG);
            this.mUPhoneFloatWWakeLock = newWakeLock;
            if (newWakeLock != null) {
                newWakeLock.setReferenceCounted(false);
                this.mUPhoneFloatWWakeLock.acquire();
                System.out.println("mUPhoneFloatWWakeLock UPhone SetWakeLock");
            }
        } else {
            System.out.println("mUPhoneFloatWWakeLock is null");
        }
        this.mRunTimer = false;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        FloatWindowManager.removeFloatWindowManager();
        HomeWatcherReceiver homeWatcherReceiver = this.mHomeKeyReceiver;
        if (homeWatcherReceiver != null) {
            unregisterReceiver(homeWatcherReceiver);
        }
        PowerManager.WakeLock wakeLock = this.mUPhoneFloatWWakeLock;
        if (wakeLock == null || !wakeLock.isHeld()) {
            return;
        }
        this.mUPhoneFloatWWakeLock.release();
    }

    public void setOnProgressListener(TestCallBack testCallBack) {
        this.mCallBack = testCallBack;
    }

    public void show(int i2) {
        this.mShowTime = i2;
        FloatWindowManager.show(i2);
    }
}
