package com.jd.libs.xdog.g;

import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class d {
    public static String a(String str) {
        return TextUtils.isEmpty(str) ? str : str.replace("\\", "\\\\").replace("\"", "\\\"").replace("'", "\\'").replace(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE, "\\n").replace("\r", "\\r").replace("\f", "\\\f").replace("\u2028", "\\u2028").replace("\u2029", "\\u2029");
    }

    public static String b(String str, Object obj, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("status", str);
            jSONObject.put("data", obj);
            jSONObject.put("msg", str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return a(jSONObject.toString());
    }
}
