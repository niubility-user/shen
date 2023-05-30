package com.jd.jdsec.a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes13.dex */
public class c {
    public static String a(Context context) {
        PackageInfo b = b(context);
        if (b == null) {
            return "";
        }
        String str = b.versionName;
        return !TextUtils.isEmpty(str) ? str : "";
    }

    private static PackageInfo b(Context context) {
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    public static List<String> c(Context context) {
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
