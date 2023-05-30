package com.jd.fireeye.a.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes13.dex */
public class b {
    public static List<String> a(Context context) {
        try {
            ArrayList arrayList = new ArrayList();
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SCREEN_ON");
            Iterator<ResolveInfo> it = context.getPackageManager().queryBroadcastReceivers(intent, 0).iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().activityInfo.packageName);
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }
}
