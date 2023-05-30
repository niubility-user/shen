package com.jingdong.app.mall.widget.vivo;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.jingdong.app.mall.widget.WidgetUtils;

/* loaded from: classes4.dex */
public class VIVOMultiFunctionWidget extends AppWidgetProvider {
    @Override // android.appwidget.AppWidgetProvider
    public void onDeleted(Context context, int[] iArr) {
        super.onDeleted(context, iArr);
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int[] intArray;
        if (intent == null) {
            return;
        }
        try {
            String action = intent.getAction();
            if ("android.appwidget.action.APPWIDGET_UPDATE".equals(action)) {
                Bundle extras = intent.getExtras();
                if (extras != null && (intArray = extras.getIntArray("appWidgetIds")) != null && intArray.length > 0) {
                    onUpdate(context, AppWidgetManager.getInstance(context), intArray);
                }
            } else if ("com.jingdong.app.mall.widget.editWidget.WIDGETSETTING_REFRESH".equals(action)) {
                Bundle extras2 = intent.getExtras();
                if (extras2 != null) {
                    WidgetUtils.p(context, extras2.getInt("settingSelect1"), extras2.getInt("settingSelect2"), -1, "vivo");
                }
            } else if ("com.jingdong.app.mall.widget.WIDGET_VIVO_UPDATE".equals(action)) {
                WidgetUtils.m(context, AppWidgetManager.getInstance(context), -1, "vivo");
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
            WidgetUtils.m(context, appWidgetManager, i2, "vivo");
        }
    }
}
