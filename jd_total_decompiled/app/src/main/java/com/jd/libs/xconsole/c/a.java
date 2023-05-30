package com.jd.libs.xconsole.c;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

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
    */
    public static void a(String str, JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("eType");
            String string2 = jSONObject.has("msg") ? jSONObject.getString("msg") : "";
            char c2 = '\uffff';
            int hashCode = string.hashCode();
            if (hashCode != -1263192169) {
                if (hashCode != 109) {
                    if (hashCode == 96415 && string.equals("adb")) {
                        c2 = 2;
                    }
                } else if (string.equals("m")) {
                    c2 = 1;
                }
            } else if (string.equals("openapp")) {
                c2 = 0;
            }
            if (TextUtils.isEmpty(string2)) {
                return;
            }
            b(string2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
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
