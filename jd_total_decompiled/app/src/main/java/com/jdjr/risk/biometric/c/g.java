package com.jdjr.risk.biometric.c;

import android.content.Context;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.device.b.o;
import com.jdjr.risk.device.entity.n;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class g extends b {
    /* JADX WARN: Code restructure failed: missing block: B:101:0x0097, code lost:
        if (r8.size() > 0) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0099, code lost:
        com.jdjr.risk.device.entity.n.a.clear();
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0075, code lost:
        if (r8.size() > 0) goto L102;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void a(Context context, String str, String str2, String str3, String str4) {
        try {
            JSONObject jSONObject = new JSONObject();
            b.a(jSONObject, context.getPackageName(), str, str4, System.currentTimeMillis() + "", str2);
            jSONObject.put("token", BiometricManager.getInstance().a().b(context));
            ArrayList<n> arrayList = n.a;
            if (arrayList != null && arrayList.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                Iterator<n> it = n.a.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().a());
                }
                n.a.clear();
                jSONObject.put("sensorInfo", jSONArray);
            }
            b.b(context, str3, jSONObject, str);
            ArrayList<n> arrayList2 = n.a;
            if (arrayList2 != null) {
            }
        } catch (Exception unused) {
            ArrayList<n> arrayList3 = n.a;
            if (arrayList3 != null) {
            }
        } catch (Throwable th) {
            ArrayList<n> arrayList4 = n.a;
            if (arrayList4 != null && arrayList4.size() > 0) {
                n.a.clear();
            }
            o.a.set(false);
            throw th;
        }
        o.a.set(false);
    }
}
