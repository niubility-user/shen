package com.jdcn.fido.utils;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.jdcn.fido.BuildConfig;
import com.jdcn.fido.constant.BasicInformation;
import com.jdjr.risk.tracker.TrackManger;
import org.json.JSONObject;

/* loaded from: classes18.dex */
public class TrackerUtil {
    public static String serialNumber = "";
    public static StringBuffer processNode = new StringBuffer();
    public static ThreadLocal<Bundle> threadLocal = new ThreadLocal<Bundle>() { // from class: com.jdcn.fido.utils.TrackerUtil.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public final Bundle initialValue() {
            return new Bundle();
        }
    };

    public static void append(String str) {
        processNode.append(str + "[" + System.currentTimeMillis() + "]---->");
    }

    public static void appendException(String str, Throwable th) {
        processNode.append(str + "[" + System.currentTimeMillis() + "][" + th.getMessage() + "][" + th.getStackTrace() + "]---->");
    }

    public static void appendResult(String str, int i2) {
        processNode.append(str + "[" + System.currentTimeMillis() + "][" + i2 + "]");
    }

    public static void appendResultException(String str, Throwable th) {
        processNode.append(str + "[" + System.currentTimeMillis() + "][" + th.getMessage() + "]");
    }

    public static void tracker(Context context, Bundle bundle, String str, String str2) {
        try {
            String orGenerateDeviceId = FingerDeviceIdManger.getOrGenerateDeviceId(context);
            Bundle bundle2 = bundle == null ? threadLocal.get() : bundle;
            String string = bundle2.containsKey("uuid") ? bundle2.getString("uuid") : "";
            String string2 = bundle2.containsKey("pin") ? bundle2.getString("pin") : "";
            String string3 = bundle2.containsKey("A2") ? bundle2.getString("A2") : "";
            String string4 = bundle2.containsKey("eytPin") ? bundle2.getString("eytPin") : "";
            String string5 = bundle2.containsKey("visa") ? bundle2.getString("visa") : "";
            String string6 = TextUtils.isEmpty(str) ? bundle2.containsKey("serialNumber") ? bundle2.getString("serialNumber") : "" : str;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("serialNumber", string6);
            jSONObject.put("uuid", string);
            jSONObject.put("userPin", string2);
            jSONObject.put("a2", string3);
            jSONObject.put("eytPin", string4);
            jSONObject.put("visa", string5);
            jSONObject.put("pin", orGenerateDeviceId);
            jSONObject.put("nodeInfo", str2);
            jSONObject.put("timeStamp", System.currentTimeMillis());
            TrackManger.uploadTrack(context, BasicInformation.SDK_NAME, BuildConfig.fidoVersionName, str2, jSONObject.toString());
        } catch (Throwable unused) {
        }
    }

    public static void tracker(Context context, String str) {
        try {
            String orGenerateDeviceId = FingerDeviceIdManger.getOrGenerateDeviceId(context);
            JSONObject jSONObject = new JSONObject();
            if (!serialNumber.equals("")) {
                jSONObject.put("serialNumber", serialNumber);
            }
            jSONObject.put("pin", orGenerateDeviceId);
            jSONObject.put("processNode", processNode.toString());
            jSONObject.put("timeStamp", System.currentTimeMillis());
            TrackManger.uploadTrack(context, BasicInformation.SDK_NAME, BuildConfig.fidoVersionName, str, jSONObject.toString());
            StringBuffer stringBuffer = processNode;
            stringBuffer.delete(0, stringBuffer.length());
        } catch (Throwable unused) {
        }
    }
}
