package com.jingdong.jdpush_new.j;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/* loaded from: classes12.dex */
public class n {
    @SuppressLint({"NewApi"})
    public static void a(Context context, int i2, String str, String str2, String str3) {
        try {
            Intent intent = new Intent("com.jingdong.push.msg.receiver.action");
            if (Build.VERSION.SDK_INT >= 12) {
                intent.setFlags(32);
            }
            intent.putExtra("bc_app_id", str2);
            intent.putExtra("bc_app_action_type", i2);
            intent.putExtra("bc_app_action_msg", str);
            intent.setClassName(str3, "com.jingdong.jdpush_new.PushMessageReceiver");
            context.sendBroadcast(intent);
        } catch (Exception unused) {
        }
    }
}
