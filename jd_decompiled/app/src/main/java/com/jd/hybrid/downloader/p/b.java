package com.jd.hybrid.downloader.p;

import android.net.Uri;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.HybridBase;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes13.dex */
public class b {
    public static void a(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null) {
                    return;
                }
                for (File file2 : listFiles) {
                    a(file2);
                }
            }
            file.delete();
        }
    }

    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a(new File(str));
    }

    public static String c(String str) {
        String e2 = e(str);
        if (TextUtils.isEmpty(e2)) {
            e2 = g();
        }
        return e2 + CartConstant.KEY_YB_INFO_LINK + h();
    }

    public static String d(long j2) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS", Locale.getDefault()).format(new Date(j2));
    }

    public static String e(String str) {
        int lastIndexOf;
        return (str.endsWith("/") || -1 == (lastIndexOf = str.lastIndexOf("/"))) ? "" : str.substring(lastIndexOf + 1);
    }

    public static String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Uri.parse(str).getHost();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String g() {
        return System.currentTimeMillis() + "" + (new Random().nextInt(9000) + 1000);
    }

    public static String h() {
        return System.currentTimeMillis() + "" + (new Random().nextInt(900) + 100) + "";
    }

    public static String i(String str) {
        String setting = HybridBase.getInstance().getSetting(HybridSDK.SWITCH_XCACHE_RETRYDOMAIN);
        if (TextUtils.isEmpty(setting)) {
            return str;
        }
        String str2 = "";
        Uri parse = Uri.parse(str);
        try {
            JSONArray optJSONArray = new JSONObject(setting).optJSONArray(parse.getHost());
            if (optJSONArray != null && optJSONArray.length() > 0) {
                str2 = optJSONArray.getString(0);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return TextUtils.isEmpty(str2) ? str : String.valueOf(parse.buildUpon().authority(str2).build());
    }
}
