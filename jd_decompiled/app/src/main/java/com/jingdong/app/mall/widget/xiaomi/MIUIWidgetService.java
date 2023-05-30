package com.jingdong.app.mall.widget.xiaomi;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;
import com.jingdong.app.mall.widget.WidgetUtils;

/* loaded from: classes4.dex */
public class MIUIWidgetService extends Service {

    /* renamed from: g  reason: collision with root package name */
    private AppWidgetManager f12061g;

    /* loaded from: classes4.dex */
    public static class RefreshReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (context == null || intent == null) {
                return;
            }
            intent.getAction();
        }
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        this.f12061g = appWidgetManager;
        WidgetUtils.h(intent, "xiaomi", appWidgetManager, this);
        return super.onStartCommand(intent, i2, i3);
    }
}
