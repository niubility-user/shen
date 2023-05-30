package com.jd.libs.xconsole;

import android.text.TextUtils;
import com.jingdong.common.messagecenter.NotificationMessageSummary;
import com.jingdong.manto.sdk.api.IAudioPlayer;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public class XLog {
    private static final String TAG = "XConsole";

    public static void d(String str) {
        d(TAG, str);
    }

    public static void e(String str) {
        e(TAG, str);
    }

    public static void i(String str) {
        i(TAG, str);
    }

    private static String parseMsg(String str, String str2) {
        return parseMsg(str, null, str2, "other");
    }

    public static void v(String str) {
        v(TAG, str);
    }

    public static void w(String str) {
        w(TAG, str);
    }

    public static void d(String str, String str2) {
        com.jd.libs.xconsole.d.a.b().d(parseMsg("Debug", str2));
    }

    public static void e(String str, String str2) {
        com.jd.libs.xconsole.d.a.b().d(parseMsg(IAudioPlayer.AUDIO_STATE_ERROR, str2));
    }

    public static void i(String str, String str2) {
        com.jd.libs.xconsole.d.a.b().d(parseMsg("Info", str2));
    }

    private static String parseMsg(String str, String str2, Object obj, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "log");
            jSONObject.put("msgId", com.jd.libs.xconsole.d.a.b().c());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(NotificationMessageSummary.MSGTYPE, str3);
            jSONObject2.put("level", str);
            jSONObject2.put("occurTime", System.currentTimeMillis());
            jSONObject2.put("msgName", str2);
            jSONObject2.put("msg", obj);
            jSONObject.put("data", jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public static void v(String str, String str2) {
        com.jd.libs.xconsole.d.a.b().d(parseMsg("Verbose", str2));
    }

    public static void w(String str, String str2) {
        com.jd.libs.xconsole.d.a.b().d(parseMsg("Warn", str2));
    }

    public static void d(String str, String str2, Object obj, String str3) {
        String str4;
        com.jd.libs.xconsole.d.a.b().d(parseMsg("Debug", str2, obj, str3));
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str2)) {
            str4 = "";
        } else {
            str4 = str2 + ": ";
        }
        sb.append(str4);
        sb.append(obj != null ? obj.toString() : "");
        sb.toString();
    }

    public static void e(String str, String str2, Object obj, String str3) {
        String str4;
        com.jd.libs.xconsole.d.a.b().d(parseMsg(IAudioPlayer.AUDIO_STATE_ERROR, str2, obj, str3));
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str2)) {
            str4 = "";
        } else {
            str4 = str2 + ": ";
        }
        sb.append(str4);
        sb.append(obj != null ? obj.toString() : "");
        sb.toString();
    }

    public static void i(String str, String str2, Object obj, String str3) {
        String str4;
        com.jd.libs.xconsole.d.a.b().d(parseMsg("Info", str2, obj, str3));
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str2)) {
            str4 = "";
        } else {
            str4 = str2 + ": ";
        }
        sb.append(str4);
        sb.append(obj != null ? obj.toString() : "");
        sb.toString();
    }

    public static void v(String str, String str2, Object obj, String str3) {
        String str4;
        com.jd.libs.xconsole.d.a.b().d(parseMsg("Verbose", str2, obj, str3));
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str2)) {
            str4 = "";
        } else {
            str4 = str2 + ": ";
        }
        sb.append(str4);
        sb.append(obj != null ? obj.toString() : "");
        sb.toString();
    }

    public static void w(String str, String str2, Object obj, String str3) {
        String str4;
        com.jd.libs.xconsole.d.a.b().d(parseMsg("Info", str2, obj, str3));
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str2)) {
            str4 = "";
        } else {
            str4 = str2 + ": ";
        }
        sb.append(str4);
        sb.append(obj != null ? obj.toString() : "");
        sb.toString();
    }
}
