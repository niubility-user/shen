package com.jd.stat.common;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.jd.stat.common.process.LiveAppModel;
import java.util.List;
import java.util.Set;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class o {
    public static JSONObject a(Context context) {
        List<LiveAppModel> c2;
        JSONObject jSONObject = new JSONObject();
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        try {
            if (Build.VERSION.SDK_INT < 21) {
                c2 = com.jd.stat.common.process.b.b(context, null);
            } else if (com.jd.stat.security.d.a().i()) {
                c2 = com.jd.stat.common.process.b.a(context, (Set<String>) null);
            } else {
                c2 = com.jd.stat.common.process.b.c(context, null);
            }
            if (c2 != null && c2.size() > 0) {
                for (LiveAppModel liveAppModel : c2) {
                    if (!TextUtils.isEmpty(liveAppModel.f7012c) && (i2 = i2 + 1) < 100) {
                        sb.append(liveAppModel.toString());
                        sb.append("$$$");
                    }
                }
                if (sb.length() > 3) {
                    sb.delete(sb.length() - 3, sb.length());
                }
                jSONObject.put("processCount", String.valueOf(i2));
                jSONObject.put("processList", sb.toString());
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public static JSONObject b(Context context) {
        List<LiveAppModel> c2;
        JSONObject jSONObject = new JSONObject();
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        try {
            if (Build.VERSION.SDK_INT < 21) {
                c2 = com.jd.stat.common.process.b.b(context, com.jd.stat.security.d.a().z());
            } else if (com.jd.stat.security.d.a().i()) {
                c2 = com.jd.stat.common.process.b.a(context, com.jd.stat.security.d.a().z());
            } else {
                c2 = com.jd.stat.common.process.b.c(context, com.jd.stat.security.d.a().z());
            }
            if (c2 != null && c2.size() > 0) {
                for (LiveAppModel liveAppModel : c2) {
                    if (!TextUtils.isEmpty(liveAppModel.f7012c) && (i2 = i2 + 1) < 100) {
                        sb.append(liveAppModel.toString());
                        sb.append("$$$");
                    }
                }
                if (sb.length() > 3) {
                    sb.delete(sb.length() - 3, sb.length());
                }
                jSONObject.put("processCount", String.valueOf(i2));
                jSONObject.put("processList", sb.toString());
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }
}
