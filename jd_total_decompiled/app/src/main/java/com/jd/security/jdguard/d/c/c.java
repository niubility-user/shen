package com.jd.security.jdguard.d.c;

import android.content.Context;
import android.content.SharedPreferences;
import com.meizu.cloud.pushsdk.notification.model.AppIconSetting;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes17.dex */
public class c extends a {
    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context, SharedPreferences sharedPreferences, SharedPreferences.Editor editor, String str) {
        super(context, sharedPreferences, editor, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.security.jdguard.d.c.a
    public void h(JSONObject jSONObject) {
        super.h(jSONObject);
        i(jSONObject.optInt("sw") == 1);
        l(jSONObject.optLong(AppIconSetting.DEFAULT_LARGE_ICON));
        k(jSONObject.optLong("dt"));
        q(jSONObject.optLong("ut"));
        r(jSONObject.optLong("ui"));
        JSONObject optJSONObject = jSONObject.optJSONObject("plc");
        Iterator<String> keys = optJSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            JSONObject optJSONObject2 = optJSONObject.optJSONObject(next);
            if (optJSONObject2 != null && optJSONObject2.length() != 0) {
                m(next, optJSONObject2.toString());
            }
        }
    }

    public String n(String str, String str2) {
        String g2;
        if (o(str) && (g2 = g(str)) != null) {
            try {
                return new JSONObject(g2).optString(str2);
            } catch (Throwable unused) {
                return null;
            }
        }
        return null;
    }

    public boolean o(String str) {
        String g2 = g(str);
        if (g2 == null) {
            return true;
        }
        try {
            return new JSONObject(g2).optInt("sw") == 1;
        } catch (Throwable unused) {
            return true;
        }
    }

    public boolean p(String str, String str2) {
        String g2 = g(str);
        if (g2 == null) {
            return true;
        }
        try {
            JSONObject jSONObject = new JSONObject(g2);
            return (jSONObject.optInt("sw") == 1) && (jSONObject.optInt(str2) == 1);
        } catch (Throwable unused) {
            return true;
        }
    }

    public void q(long j2) {
        j(this.a + "_u_delay", j2);
    }

    public void r(long j2) {
        j(this.a + "_u_interval", j2);
    }

    public void s(long j2) {
        j(this.a + "_u_l_ts", j2);
    }

    public long t() {
        long f2 = f(this.a + "_u_delay", 3L);
        if (f2 == 0) {
            return 3L;
        }
        return f2;
    }

    public long u() {
        long f2 = f(this.a + "_u_interval", 20L);
        if (f2 == 0) {
            return 20L;
        }
        return f2;
    }

    public long v() {
        return f(this.a + "_u_l_ts", 0L);
    }
}
