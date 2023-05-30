package com.jingdong.app.mall.bundle.marketing_sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.sdk.jdtoast.ToastUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b {
    public static String a(String str) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return com.jingdong.app.mall.bundle.marketing_sdk.contacts.a.a("wio_*985r^&9ut+k".getBytes(), str);
    }

    public static JSONArray b(Context context, Intent intent) {
        JSONArray jSONArray = new JSONArray();
        if (context != null && intent != null) {
            Uri data = intent.getData();
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(data, new String[]{"display_name", "data1"}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    String string = cursor.getString(cursor.getColumnIndex("display_name"));
                    String string2 = cursor.getString(cursor.getColumnIndex("data1"));
                    if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string)) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("phoneNum", string2);
                        jSONObject.put("bookName", string);
                        jSONArray.put(jSONObject);
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                return jSONArray;
            } catch (Exception unused) {
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return jSONArray;
    }

    public static void c(Activity activity) {
        if (activity == null) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(Uri.parse("package:" + activity.getPackageName()));
            activity.startActivity(intent);
        } catch (Exception unused) {
            ToastUtils.shortToast(activity, "\u60a8\u53ef\u5230\u624b\u673a\u8bbe\u7f6e\u4e2d\u5f00\u542f\u76f8\u5173\u6743\u9650~");
        }
    }
}
