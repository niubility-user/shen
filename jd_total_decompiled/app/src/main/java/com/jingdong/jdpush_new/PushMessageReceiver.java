package com.jingdong.jdpush_new;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.jingdong.jdpush_new.PushIntentService;
import com.jingdong.jdpush_new.j.i;

/* loaded from: classes12.dex */
public class PushMessageReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            PushIntentService.addJob(new PushIntentService.a(this, intent));
            if (TextUtils.isEmpty(String.valueOf(i.d().a(context, "jsp_intentService", "")))) {
                return;
            }
            context.startService(new Intent(context, Class.forName(String.valueOf(i.d().a(context, "jsp_intentService", "")))));
        } catch (Exception | IllegalAccessError unused) {
        }
    }
}
