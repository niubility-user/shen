package com.jd.security.jdguard.d.c;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class h extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public h(Context context, SharedPreferences sharedPreferences, SharedPreferences.Editor editor, String str) {
        super(context, sharedPreferences, editor, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.security.jdguard.d.c.a
    public void h(JSONObject jSONObject) {
        try {
            super.h(jSONObject);
            boolean z = true;
            if (jSONObject.optInt("sw") != 1) {
                z = false;
            }
            i(z);
            l(jSONObject.optLong(AppIconSetting.DEFAULT_LARGE_ICON));
            k(jSONObject.optLong("dt"));
            JSONObject optJSONObject = jSONObject.optJSONObject("plc");
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject optJSONObject2 = optJSONObject.optJSONObject(next);
                if (optJSONObject2 != null && optJSONObject2.length() != 0) {
                    m(next, optJSONObject2.toString());
                }
            }
        } catch (Throwable unused) {
        }
    }

    public boolean n(String str) {
        if (g(str) == null) {
            return true;
        }
        return !TextUtils.isEmpty(r2);
    }

    public boolean o(String str, String str2) {
        try {
            String g2 = g(str);
            if (g2 == null) {
                return true;
            }
            return new JSONObject(g2).optInt(str2) == 1;
        } catch (Throwable unused) {
            return true;
        }
    }
}
