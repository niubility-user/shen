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
    */
    public static void addOneDataNative(Context context, String str, JSONObject jSONObject) {
        JSONObject optJSONObject;
        int i2;
        SharedPreferences sharedPreferences;
        JSONArray jSONArray;
        int i3;
        int i4;
        if (context == null) {
            return;
        }
        String optString = jSONObject.optString("index");
        optJSONObject = jSONObject.optJSONObject("map");
        if (optJSONObject == null || TextUtils.isEmpty(optJSONObject.toString()) || TextUtils.equals(DYConstants.DY_NULL_STR, optJSONObject.toString())) {
            return;
        }
        sharedPreferences = context.getSharedPreferences(str, 0);
        try {
            jSONArray = new JSONArray(sharedPreferences.getString("data", "[]"));
            i3 = sharedPreferences.getInt(AlbumListActivity.KEY_MAX_COUNT, 20);
            try {
                i4 = Integer.parseInt(optString);
            } catch (Exception unused) {
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            return;
        }
        i4 = 0;
        JSONArray jSONArray2 = new JSONArray();
        if (jSONArray.length() < 1) {
            jSONArray2.put(optJSONObject);
        } else {
            for (i2 = 0; i2 < Math.min(jSONArray.length(), i3); i2++) {
                if (i4 == i2) {
                    jSONArray2.put(optJSONObject);
                }
                jSONArray2.put(jSONArray.optJSONObject(i2));
            }
        }
        sharedPreferences.edit().putString("data", jSONArray2.toString()).apply();
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
