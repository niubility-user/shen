package com.jd.lib.push.broadcastReceiver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.jd.lib.push.utils.d;
import com.jingdong.common.messagecenter.PushMessageHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class ManufacturePushMessageHandler {
    public static void handleMessage(Context context, String str, int i2) throws JSONException {
        if (d.m()) {
            startMessageNotificationActivity(context, str, i2);
        } else {
            PushMessageHandler.parseManufacturerPushMsg(context, new JSONObject(str), i2, 2);
        }
    }

    private static void startMessageNotificationActivity(Context context, String str, int i2) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, "com.jingdong.app.mall.open.MessageNotificationActivity"));
        intent.putExtra("summary", str);
        intent.putExtra("messageFlag", i2);
        intent.putExtra("fromInternalActivity", 1);
        intent.setFlags(337641472);
        context.startActivity(intent);
    }
}
