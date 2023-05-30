package com.wjlogin.onekey.sdk.common.a.b;

import com.huawei.hms.framework.common.ContainerUtils;
import java.net.URLEncoder;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public class e {
    public static String a(String str, boolean z, String str2) {
        if (h.m(str)) {
            return "";
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            JSONObject jSONObject = new JSONObject(str);
            StringBuilder sb = new StringBuilder();
            sb.append(" jsonobj = ");
            sb.append(jSONObject);
            sb.toString();
            a(jSONObject);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String optString = jSONObject.optString(next);
                if (z) {
                    if (h.m(optString)) {
                        optString = "";
                    } else {
                        optString = URLEncoder.encode(optString, h.m(str2) ? "UTF-8" : str2);
                    }
                }
                stringBuffer.append(ContainerUtils.FIELD_DELIMITER);
                stringBuffer.append(next);
                stringBuffer.append(ContainerUtils.KEY_VALUE_DELIMITER);
                stringBuffer.append(optString);
            }
            return stringBuffer.substring(1, stringBuffer.length());
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (h.m(next)) {
                    jSONObject.put(next, "");
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
