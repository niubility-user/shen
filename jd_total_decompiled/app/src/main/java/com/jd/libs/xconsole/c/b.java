package com.jd.libs.xconsole.c;

import android.text.TextUtils;
import okhttp3.WebSocket;
import okio.ByteString;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class b {
    /* JADX WARN: Code restructure failed: missing block: B:100:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void a(WebSocket webSocket, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("type");
            String string2 = jSONObject.getString("msgId");
            JSONObject jSONObject2 = jSONObject.getJSONObject("data");
            if (jSONObject2.length() != 0 && !TextUtils.isEmpty(string2)) {
                char c2 = '\uffff';
                int hashCode = string.hashCode();
                if (hashCode != -340323263) {
                    if (hashCode == 3127441 && string.equals("exec")) {
                        c2 = 0;
                    }
                } else if (string.equals("response")) {
                    c2 = 1;
                }
                a.a(string2, jSONObject2);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void b(WebSocket webSocket, ByteString byteString) {
        a(webSocket, byteString.utf8());
    }
}
