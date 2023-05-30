package com.tencent.tmsqmsp.oaid2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class v extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        boolean z = false;
        int intExtra = intent.getIntExtra("openIdNotifyFlag", 0);
        x.b("shouldUpdateId, notifyFlag : " + intExtra);
        if (intExtra != 1) {
            if (intExtra == 2) {
                ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("openIdPackageList");
                if (stringArrayListExtra == null) {
                    return;
                }
                boolean contains = stringArrayListExtra.contains(context.getPackageName());
                if (contains) {
                    w a = x.a().a(intent.getStringExtra("openIdType"));
                    if (a != null) {
                        a.b();
                        return;
                    }
                    return;
                }
                z = contains;
            } else {
                z = true;
            }
            if (intExtra == 0 && z) {
                w a2 = x.a().a(intent.getStringExtra("openIdType"));
                if (a2 != null) {
                    a2.b();
                    return;
                }
                return;
            }
        } else if (TextUtils.equals(intent.getStringExtra("openIdPackage"), context.getPackageName())) {
            z = true;
        }
        if (z) {
            w a3 = x.a().a(intent.getStringExtra("openIdType"));
            if (a3 != null) {
                a3.b();
            }
        }
    }
}
