package com.jd.mobile.image.utils;

import android.text.TextUtils;
import com.facebook.common.logging.FLog;
import com.jd.dynamic.DYConstants;
import com.jingdong.JdImageToolKit;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;

/* loaded from: classes17.dex */
public class c {
    private static ArrayList<String> a;
    private static ArrayList<String> b;

    public static String a(String str, boolean z, String str2) {
        FLog.d("GifImageUtil", "gif getTransformImageUri:" + str + " isRetry:" + z + " refer:" + str2);
        if (b() && !TextUtils.isEmpty(str) && str.startsWith("http") && !z && str.endsWith(".gif") && e(str) && c(str2)) {
            String str3 = str + ".webp";
            FLog.i("GifImageUtil", "gif trans url:" + str3);
            return str3;
        }
        return str;
    }

    private static boolean b() {
        return JdImageToolKit.getEngine().getGIFWhitePageEnable().isGIFWhitePageEnable();
    }

    private static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.contains(DYConstants.DY_REGEX_AT)) {
            str = str.substring(0, str.indexOf(DYConstants.DY_REGEX_AT));
        }
        if (a == null) {
            ArrayList<String> arrayList = new ArrayList<>();
            String config = JDMobileConfig.getInstance().getConfig("JDImageGif", "gifWhitePage", "pages");
            if (!TextUtils.isEmpty(config)) {
                try {
                    JSONArray jSONArray = new JSONArray(config);
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        arrayList.add(jSONArray.optString(i2));
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    FLog.e("GifImageUtil", "GIF_WHITE_PAGE_LIST:" + config);
                }
            }
            String config2 = JDMobileConfig.getInstance().getConfig("JDImageGif", "gifGrayPage", "pages");
            if (!TextUtils.isEmpty(config2)) {
                try {
                    JSONArray jSONArray2 = new JSONArray(config2);
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        arrayList.add(jSONArray2.optString(i3));
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    FLog.e("GifImageUtil", "GIF_GRAY_PAGE_LIST:" + config2);
                }
            }
            a = arrayList;
        }
        return a.contains(str);
    }

    public static boolean d() {
        return TextUtils.equals("1", JDMobileConfig.getInstance().getConfig("JDImageGif", "gifConfig", "gifRetry", "0"));
    }

    private static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            String host = new URL(str).getHost();
            if (b == null) {
                ArrayList<String> arrayList = new ArrayList<>();
                String config = JDMobileConfig.getInstance().getConfig("JDImageGif", "gifWhitePage", "hosts");
                if (!TextUtils.isEmpty(config)) {
                    try {
                        JSONArray jSONArray = new JSONArray(config);
                        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                            arrayList.add(jSONArray.optString(i2));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        FLog.e("GifImageUtil", "GIF_WHITE_PAGE_IMAGE_HOST_LIST:" + config);
                    }
                }
                String config2 = JDMobileConfig.getInstance().getConfig("JDImageGif", "gifGrayPage", "hosts");
                if (!TextUtils.isEmpty(config2)) {
                    try {
                        JSONArray jSONArray2 = new JSONArray(config2);
                        for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                            arrayList.add(jSONArray2.optString(i3));
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        FLog.e("GifImageUtil", "GIF_GRAY_PAGE_IMAGE_HOST_LIST:" + config2);
                    }
                }
                b = arrayList;
            }
            return b.contains(host);
        } catch (MalformedURLException e4) {
            FLog.e("GifImageUtil", "isGifWhitePageImageHost:" + e4.getMessage());
            return false;
        }
    }
}
