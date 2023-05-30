package com.jingdong.app.mall.widget.xiaomi;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.widget.WidgetUtils;

/* loaded from: classes4.dex */
public class MIUIMultiFunctionWidget extends AppWidgetProvider {
    @Override // android.appwidget.AppWidgetProvider
    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            String action = intent.getAction();
            if ("miui.appwidget.action.APPWIDGET_UPDATE".equals(action)) {
                onUpdate(context, AppWidgetManager.getInstance(context), intent.getIntArrayExtra("appWidgetIds"));
            } else if ("com.jingdong.app.mall.widget.editWidget.WIDGETSETTING_REFRESH".equals(action)) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    WidgetUtils.p(context, extras.getInt("settingSelect1"), extras.getInt("settingSelect2"), -1, "xiaomi");
                }
            } else if ("com.jingdong.app.mall.widget.WIDGET_XIAOMI_UPDATE".equals(action)) {
                WidgetUtils.m(context, AppWidgetManager.getInstance(context), -1, "xiaomi");
            } else {
                super.onReceive(context, intent);
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        for (int i2 : iArr) {
            WidgetUtils.m(context, appWidgetManager, i2, "xiaomi");
        }
    }
}
