package com.jd.libs.xconsole.c;

import android.content.Intent;
import android.net.Uri;

/* loaded from: classes16.dex */
public class a {
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004a, code lost:
        if (r6 == 1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0051, code lost:
        if (android.text.TextUtils.isEmpty(r5) != false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0053, code lost:
        b(java.lang.String.format(java.util.Locale.getDefault(), "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"m\",\"url\":\"%s\"}", r5));
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.lang.String r5, org.json.JSONObject r6) {
        /*
            java.lang.String r5 = "msg"
            java.lang.String r0 = "eType"
            java.lang.String r0 = r6.getString(r0)     // Catch: org.json.JSONException -> L6f
            boolean r1 = r6.has(r5)     // Catch: org.json.JSONException -> L6f
            if (r1 == 0) goto L13
            java.lang.String r5 = r6.getString(r5)     // Catch: org.json.JSONException -> L6f
            goto L15
        L13:
            java.lang.String r5 = ""
        L15:
            r6 = -1
            int r1 = r0.hashCode()     // Catch: org.json.JSONException -> L6f
            r2 = -1263192169(0xffffffffb4b53797, float:-3.3754324E-7)
            r3 = 0
            r4 = 1
            if (r1 == r2) goto L3f
            r2 = 109(0x6d, float:1.53E-43)
            if (r1 == r2) goto L35
            r2 = 96415(0x1789f, float:1.35106E-40)
            if (r1 == r2) goto L2b
            goto L48
        L2b:
            java.lang.String r1 = "adb"
            boolean r0 = r0.equals(r1)     // Catch: org.json.JSONException -> L6f
            if (r0 == 0) goto L48
            r6 = 2
            goto L48
        L35:
            java.lang.String r1 = "m"
            boolean r0 = r0.equals(r1)     // Catch: org.json.JSONException -> L6f
            if (r0 == 0) goto L48
            r6 = 1
            goto L48
        L3f:
            java.lang.String r1 = "openapp"
            boolean r0 = r0.equals(r1)     // Catch: org.json.JSONException -> L6f
            if (r0 == 0) goto L48
            r6 = 0
        L48:
            if (r6 == 0) goto L65
            if (r6 == r4) goto L4d
            goto L73
        L4d:
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: org.json.JSONException -> L6f
            if (r6 != 0) goto L73
            java.util.Locale r6 = java.util.Locale.getDefault()     // Catch: org.json.JSONException -> L6f
            java.lang.String r0 = "openapp.jdmobile://virtual?params={\"category\":\"jump\",\"des\":\"m\",\"url\":\"%s\"}"
            java.lang.Object[] r1 = new java.lang.Object[r4]     // Catch: org.json.JSONException -> L6f
            r1[r3] = r5     // Catch: org.json.JSONException -> L6f
            java.lang.String r5 = java.lang.String.format(r6, r0, r1)     // Catch: org.json.JSONException -> L6f
            b(r5)     // Catch: org.json.JSONException -> L6f
            goto L73
        L65:
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch: org.json.JSONException -> L6f
            if (r6 != 0) goto L73
            b(r5)     // Catch: org.json.JSONException -> L6f
            goto L73
        L6f:
            r5 = move-exception
            r5.printStackTrace()
        L73:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.xconsole.c.a.a(java.lang.String, org.json.JSONObject):void");
    }

    private static void b(String str) {
        if (com.jd.libs.xconsole.b.a != null) {
            Intent intent = new Intent();
            intent.setData(Uri.parse(str));
            intent.setAction("android.intent.action.VIEW");
            intent.setFlags(268435456);
            com.jd.libs.xconsole.b.a.startActivity(intent);
            return;
        }
        throw new IllegalArgumentException("must exec XConsole.init method before any action!");
    }
}
