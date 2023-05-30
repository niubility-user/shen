package com.jingdong.common.utils;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ReadAssetsJsonUtil {
    private static final String TAG = "ReadAssetsJsonUtil";

    public static JSONObject getJSONObject(String str, Context context) {
        try {
            return new JSONObject(getJson(str, context));
        } catch (JSONException e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
                return null;
            }
            return null;
        }
    }

    public static String getJson(String str, Context context) {
        if (TextUtils.isEmpty(str) || context == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
        } catch (IOException e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
        return sb.toString();
    }

    public static Map<String, Object> listKeyMaps(String str) {
        HashMap hashMap = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            HashMap hashMap2 = new HashMap();
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String lowerCase = keys.next().toString().toLowerCase();
                    Object obj = jSONObject.get(lowerCase);
                    if (obj == null) {
                        obj = "";
                    }
                    hashMap2.put(lowerCase, obj);
                }
                return hashMap2;
            } catch (Exception e2) {
                e = e2;
                hashMap = hashMap2;
                if (OKLog.E) {
                    OKLog.e(TAG, e);
                }
                return hashMap;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }
}
