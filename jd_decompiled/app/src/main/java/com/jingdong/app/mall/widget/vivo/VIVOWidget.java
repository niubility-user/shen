package com.jingdong.app.mall.widget.vivo;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.widget.WidgetUtils;

/* loaded from: classes4.dex */
public class VIVOWidget extends AppWidgetProvider {
    @Override // android.appwidget.AppWidgetProvider
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int i2, Bundle bundle) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, i2, bundle);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context != null && intent != null) {
            try {
                String action = intent.getAction();
                if ("android.appwidget.action.APPWIDGET_UPDATE".equals(action)) {
                    onUpdate(context, AppWidgetManager.getInstance(context), intent.getIntArrayExtra("appWidgetIds"));
                } else if ("com.jingdong.app.mall.widget.WIDGETGOODS_REFRESH".equals(action)) {
                    int i2 = 0;
                    SharedPreferences sharedPreferences = context.getSharedPreferences("widgetDateVIVO", 0);
                    if (sharedPreferences == null) {
                        return;
                    }
                    int i3 = sharedPreferences.getInt("i", 0);
                    if (i3 != 3) {
                        i2 = i3 + 1;
                    }
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putInt("i", i2);
                    edit.apply();
                    RemoteViews remoteViews = new RemoteViews(context.getPackageName(), (int) R.layout.widget_goodsrecommend);
                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                    ComponentName componentName = new ComponentName(context, VIVOWidget.class);
                    appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetManager.getAppWidgetIds(componentName), R.id.widget_gridview);
                    appWidgetManager.updateAppWidget(componentName, remoteViews);
                } else if ("com.jingdong.app.mall.widget.WIDGET_VIVO_UPDATE".equals(action)) {
                    WidgetUtils.i(context, AppWidgetManager.getInstance(context), -1, "vivo");
                } else {
                    super.onReceive(context, intent);
                }
            } catch (Exception unused) {
            }
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onRestored(Context context, int[] iArr, int[] iArr2) {
        super.onRestored(context, iArr, iArr2);
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        for (int i2 : iArr) {
            WidgetUtils.i(context, appWidgetManager, i2, "vivo");
        }
    }

    @Override // android.content.BroadcastReceiver
    public IBinder peekService(Context context, Intent intent) {
        return super.peekService(context, intent);
    }
}
