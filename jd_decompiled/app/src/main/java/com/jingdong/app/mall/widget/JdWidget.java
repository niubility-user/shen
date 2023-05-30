package com.jingdong.app.mall.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.basic.deshandler.a;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.widget.IJdWidget;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class JdWidget extends AppWidgetProvider implements IJdWidget {
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
        try {
            super.onReceive(context, intent);
            if (Log.D) {
                Log.d("Temp", "JdWidget onReceive() -->> ");
            }
            onUpdate(context, AppWidgetManager.getInstance(context), null);
        } catch (Exception e2) {
            if (Log.D) {
                e2.printStackTrace();
            }
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        if (Log.D) {
            Log.d("Temp", "JdWidget onUpdate() -->> ");
        }
        int i2 = Build.VERSION.SDK_INT >= 31 ? 201326592 : 134217728;
        String name = getClass().getName();
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), (int) R.layout.wedget);
        Bundle bundle = new Bundle();
        bundle.putString(LoginConstans.JUMP_DES, "search");
        bundle.putBoolean("isFromWidget", true);
        bundle.putString(WebPerfManager.PAGE_NAME, name);
        remoteViews.setOnClickPendingIntent(R.id.widget_search, PendingIntent.getActivity(context, 0, a.i(context, bundle), i2));
        Bundle bundle2 = new Bundle();
        bundle2.putString(LoginConstans.JUMP_DES, "scan");
        bundle2.putBoolean("isFromWidget", true);
        bundle2.putString(WebPerfManager.PAGE_NAME, name);
        remoteViews.setOnClickPendingIntent(R.id.widget_barcode, PendingIntent.getActivity(context, 1, a.i(context, bundle2), i2));
        Bundle bundle3 = new Bundle();
        bundle3.putString(LoginConstans.JUMP_DES, JumpUtil.VALUE_DES_CHONGZHI);
        bundle3.putBoolean("isFromWidget", true);
        bundle3.putString(WebPerfManager.PAGE_NAME, name);
        remoteViews.setOnClickPendingIntent(R.id.widget_recharge, PendingIntent.getActivity(context, 2, a.i(context, bundle3), i2));
        StringBuilder sb = new StringBuilder("openApp.jdMobile://virtual?params=");
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("des", (Object) JumpUtil.VALUE_DES_JDREACT_COMMON);
        jDJSONObject.put("transparentenable", (Object) DYConstants.DY_TRUE);
        jDJSONObject.put(JDReactConstant.IntentConstant.MODULE_NAME, (Object) "JDReactAirtickets");
        jDJSONObject.put("appname", (Object) "JDReactAirtickets");
        jDJSONObject.put("category", (Object) "jump");
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        jDJSONObject2.put("fromtype", (Object) "widget");
        jDJSONObject.put("param", (Object) jDJSONObject2.toJSONString());
        sb.append(jDJSONObject.toJSONString());
        Uri parse = Uri.parse(sb.toString());
        Intent intent = new Intent();
        intent.setData(parse);
        remoteViews.setOnClickPendingIntent(R.id.airline_text, PendingIntent.getActivity(context, 3, intent, i2));
        Bundle bundle4 = new Bundle();
        bundle4.putString(LoginConstans.JUMP_DES, JumpUtil.VALUE_DES_WAITING_GOODS);
        bundle4.putBoolean("isFromWidget", true);
        bundle4.putString("title", "\u5f85\u6536\u8d27");
        bundle4.putString("functionId", PersonalConstants.FUNCTION_ID_DAISHOUHUO);
        bundle4.putString(WebPerfManager.PAGE_NAME, name);
        remoteViews.setOnClickPendingIntent(R.id.widget_waitingfor_goods, PendingIntent.getActivity(context, 4, a.i(context, bundle4), i2));
        appWidgetManager.updateAppWidget(new ComponentName(context, JdWidget.class), remoteViews);
    }

    @Override // android.content.BroadcastReceiver
    public IBinder peekService(Context context, Intent intent) {
        return super.peekService(context, intent);
    }
}
