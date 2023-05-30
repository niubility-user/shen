package com.jd.dynamic.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.widget.photo.AlbumListActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: classes13.dex */
public class DynamicFileUtil {
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004a, code lost:
        if (r0 >= r3.length()) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void addOneDataNative(android.content.Context r7, java.lang.String r8, org.json.JSONObject r9) {
        /*
            if (r7 != 0) goto L3
            return
        L3:
            java.lang.String r0 = "index"
            java.lang.String r0 = r9.optString(r0)
            java.lang.String r1 = "map"
            org.json.JSONObject r9 = r9.optJSONObject(r1)
            if (r9 == 0) goto L8a
            java.lang.String r1 = r9.toString()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L8a
            java.lang.String r1 = r9.toString()
            java.lang.String r2 = "null"
            boolean r1 = android.text.TextUtils.equals(r2, r1)
            if (r1 == 0) goto L28
            goto L8a
        L28:
            r1 = 0
            android.content.SharedPreferences r7 = r7.getSharedPreferences(r8, r1)
            java.lang.String r8 = "data"
            java.lang.String r2 = "[]"
            java.lang.String r2 = r7.getString(r8, r2)
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch: org.json.JSONException -> L86
            r3.<init>(r2)     // Catch: org.json.JSONException -> L86
            java.lang.String r2 = "maxCount"
            r4 = 20
            int r2 = r7.getInt(r2, r4)     // Catch: org.json.JSONException -> L86
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> L4c
            int r4 = r3.length()     // Catch: java.lang.Exception -> L4c
            if (r0 < r4) goto L4d
        L4c:
            r0 = 0
        L4d:
            org.json.JSONArray r4 = new org.json.JSONArray     // Catch: org.json.JSONException -> L86
            r4.<init>()     // Catch: org.json.JSONException -> L86
            int r5 = r3.length()     // Catch: org.json.JSONException -> L86
            r6 = 1
            if (r5 >= r6) goto L5d
            r4.put(r9)     // Catch: org.json.JSONException -> L86
            goto L76
        L5d:
            int r5 = r3.length()     // Catch: org.json.JSONException -> L86
            int r5 = java.lang.Math.min(r5, r2)     // Catch: org.json.JSONException -> L86
            if (r1 >= r5) goto L76
            if (r0 != r1) goto L6c
            r4.put(r9)     // Catch: org.json.JSONException -> L86
        L6c:
            org.json.JSONObject r5 = r3.optJSONObject(r1)     // Catch: org.json.JSONException -> L86
            r4.put(r5)     // Catch: org.json.JSONException -> L86
            int r1 = r1 + 1
            goto L5d
        L76:
            android.content.SharedPreferences$Editor r7 = r7.edit()     // Catch: org.json.JSONException -> L86
            java.lang.String r9 = r4.toString()     // Catch: org.json.JSONException -> L86
            android.content.SharedPreferences$Editor r7 = r7.putString(r8, r9)     // Catch: org.json.JSONException -> L86
            r7.apply()     // Catch: org.json.JSONException -> L86
            goto L8a
        L86:
            r7 = move-exception
            r7.printStackTrace()
        L8a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.dynamic.base.DynamicFileUtil.addOneDataNative(android.content.Context, java.lang.String, org.json.JSONObject):void");
    }

    public static void deleteAllDataNative(Context context, String str) {
        if (context == null) {
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.remove("data");
        edit.apply();
    }

    public static void deleteOneNative(Context context, String str, JSONObject jSONObject) {
        JSONObject optJSONObject;
        if (context == null || (optJSONObject = jSONObject.optJSONObject("map")) == null || TextUtils.isEmpty(optJSONObject.toString()) || TextUtils.equals(DYConstants.DY_NULL_STR, optJSONObject.toString())) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        try {
            JSONArray jSONArray = new JSONArray(sharedPreferences.getString("data", "[]"));
            int i2 = -1;
            for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                if (TextUtils.equals(jSONArray.getJSONObject(i3).toString(), optJSONObject.toString())) {
                    i2 = i3;
                }
            }
            if (i2 != -1 && i2 < jSONArray.length()) {
                if (Build.VERSION.SDK_INT >= 19) {
                    jSONArray.remove(i2);
                } else {
                    Field declaredField = JSONArray.class.getDeclaredField("values");
                    declaredField.setAccessible(true);
                    List list = (List) declaredField.get(jSONArray);
                    if (list != null && i2 < list.size()) {
                        list.remove(i2);
                    }
                }
            }
            sharedPreferences.edit().putString("data", jSONArray.toString()).apply();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static JSONArray getAllDataNative(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            return new JSONArray(context.getSharedPreferences(str, 0).getString("data", "[]"));
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getAssetFile(Context context, String str) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return sb.toString();
                }
                sb.append(readLine);
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String getAssetFile(Context context, String str, boolean z) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getAssets().open(str)));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    return sb.toString();
                }
                sb.append(readLine);
                if (z) {
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void initTableNative(Context context, String str, String str2) {
        int i2;
        if (context == null) {
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        try {
            i2 = Integer.parseInt(str2);
        } catch (Exception unused) {
            i2 = 20;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(AlbumListActivity.KEY_MAX_COUNT, i2);
        edit.apply();
    }
}
