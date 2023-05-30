package com.jd.libs.xconsole.c;

import okhttp3.WebSocket;
import okio.ByteString;

/* loaded from: classes16.dex */
public class b {
    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(okhttp3.WebSocket r4, java.lang.String r5) {
        /*
            boolean r4 = android.text.TextUtils.isEmpty(r5)
            if (r4 == 0) goto L7
            return
        L7:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: org.json.JSONException -> L56
            r4.<init>(r5)     // Catch: org.json.JSONException -> L56
            java.lang.String r5 = "type"
            java.lang.String r5 = r4.getString(r5)     // Catch: org.json.JSONException -> L56
            java.lang.String r0 = "msgId"
            java.lang.String r0 = r4.getString(r0)     // Catch: org.json.JSONException -> L56
            java.lang.String r1 = "data"
            org.json.JSONObject r4 = r4.getJSONObject(r1)     // Catch: org.json.JSONException -> L56
            int r1 = r4.length()     // Catch: org.json.JSONException -> L56
            if (r1 == 0) goto L55
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: org.json.JSONException -> L56
            if (r1 == 0) goto L2b
            goto L55
        L2b:
            r1 = -1
            int r2 = r5.hashCode()     // Catch: org.json.JSONException -> L56
            r3 = -340323263(0xffffffffebb71441, float:-4.4265814E26)
            if (r2 == r3) goto L45
            r3 = 3127441(0x2fb891, float:4.382478E-39)
            if (r2 == r3) goto L3b
            goto L4e
        L3b:
            java.lang.String r2 = "exec"
            boolean r5 = r5.equals(r2)     // Catch: org.json.JSONException -> L56
            if (r5 == 0) goto L4e
            r1 = 0
            goto L4e
        L45:
            java.lang.String r2 = "response"
            boolean r5 = r5.equals(r2)     // Catch: org.json.JSONException -> L56
            if (r5 == 0) goto L4e
            r1 = 1
        L4e:
            if (r1 == 0) goto L51
            goto L5a
        L51:
            com.jd.libs.xconsole.c.a.a(r0, r4)     // Catch: org.json.JSONException -> L56
            goto L5a
        L55:
            return
        L56:
            r4 = move-exception
            r4.printStackTrace()
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.xconsole.c.b.a(okhttp3.WebSocket, java.lang.String):void");
    }

    public static void b(WebSocket webSocket, ByteString byteString) {
        a(webSocket, byteString.utf8());
    }
}
