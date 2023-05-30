package com.tencent.map.geolocation;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import c.t.m.g.o4;

/* loaded from: classes9.dex */
public class s extends Service {
    public static boolean removeNotification = true;
    public volatile boolean isStartForeground = false;
    public MyBinder mBinder = new MyBinder();

    /* loaded from: classes9.dex */
    public class MyBinder extends Binder {
        public MyBinder() {
        }

        public s getService() {
            return s.this;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        try {
            Notification notification = (Notification) intent.getExtras().get("LocNotification");
            int intValue = ((Integer) intent.getExtras().get("LocNotificationId")).intValue();
            if (intValue > 0 && notification != null && !this.isStartForeground) {
                startForeground(intValue, notification);
                o4.o("LOC", "startForeground");
                this.isStartForeground = true;
            }
        } catch (Throwable unused) {
        }
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (this.isStartForeground) {
            this.isStartForeground = false;
            try {
                stopForeground(removeNotification);
            } catch (Throwable unused) {
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        return super.onStartCommand(intent, i2, i3);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        if (this.isStartForeground) {
            this.isStartForeground = false;
            try {
                stopForeground(removeNotification);
                o4.o("LOC", "stopForeground");
            } catch (Throwable unused) {
            }
        }
        return super.onUnbind(intent);
    }
}
