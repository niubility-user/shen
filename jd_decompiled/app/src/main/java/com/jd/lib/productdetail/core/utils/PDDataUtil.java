package com.jd.lib.productdetail.core.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes15.dex */
public class PDDataUtil {
    public static String buildSkuDetailUrl(String str, String str2) {
        String versionName = PackageInfoUtil.getVersionName();
        if (TextUtils.isEmpty(versionName)) {
            versionName = "5.0.1";
        }
        return String.format("https://%s/c1/%s/%s/%s/%s.json", getCDNHost(), str, "android", versionName, str2);
    }

    public static void downloadImage(final String str, final JDSimpleImageLoadingListener jDSimpleImageLoadingListener) {
        HttpGroup.OnCommonListener onCommonListener = new HttpGroup.OnCommonListener() { // from class: com.jd.lib.productdetail.core.utils.PDDataUtil.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                Bitmap parseBitmapFromResponse = PDDataUtil.parseBitmapFromResponse(httpResponse);
                JDSimpleImageLoadingListener jDSimpleImageLoadingListener2 = JDSimpleImageLoadingListener.this;
                if (jDSimpleImageLoadingListener2 != null) {
                    jDSimpleImageLoadingListener2.onLoadingComplete(str, null, parseBitmapFromResponse);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                JDSimpleImageLoadingListener jDSimpleImageLoadingListener2 = JDSimpleImageLoadingListener.this;
                if (jDSimpleImageLoadingListener2 != null) {
                    jDSimpleImageLoadingListener2.onLoadingFailed(str, null, null);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        };
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setConnectTimeout(5000);
        httpSetting.setAttempts(1);
        httpSetting.setCacheMode(0);
        httpSetting.setType(5000);
        httpSetting.setListener(onCommonListener);
        httpSetting.setNeedShareImage(false);
        HttpGroupUtils.getHttpGroupaAsynPool(5000).add(httpSetting);
    }

    public static String encode(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return str;
        }
    }

    public static String getAuthCodePDBPParm() {
        return CommonBase.getJdSharedPreferences().getString("lib_pd_authcode_pdbp", null);
    }

    public static String getAuthCodeTokenParam() {
        if (Log.D) {
            Log.d("\u5ba2\u6237\u7aef\u4e0a\u4f20\u7684\u53c2\u6570pdtk =", CommonBase.getJdSharedPreferences().getString("lib_pd_authcode_pdtk", null));
        }
        return CommonBase.getJdSharedPreferences().getString("lib_pd_authcode_pdtk", null);
    }

    private static String getCDNHost() {
        if (Configuration.isBeta()) {
        }
        return "cdnware.m.jd.com";
    }

    public static boolean isCDNEnable() {
        return CommonBase.getJdSharedPreferences().getBoolean("com_jd_pd_cdn_flag", false);
    }

    public static boolean isCDNReportData() {
        return CommonBase.getJdSharedPreferences().getBoolean("com_jd_pd_cdn_report_flag", false);
    }

    public static Bitmap parseBitmapFromResponse(HttpResponse httpResponse) {
        File saveFile;
        if (httpResponse != null && httpResponse != null) {
            try {
                byte[] inputData = httpResponse.getInputData();
                r2 = inputData != null ? BitmapFactory.decodeByteArray(inputData, 0, inputData.length) : null;
                if (r2 == null && (saveFile = httpResponse.getSaveFile()) != null) {
                    if (Log.D) {
                        Log.d("PDYuYueVerifyDialog", "bitmap file = " + saveFile.getAbsolutePath());
                    }
                    r2 = BitmapFactory.decodeFile(saveFile.getPath());
                }
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d("PDYuYueVerifyDialog", e2.getMessage());
                }
            }
        }
        if (Log.D) {
            StringBuilder sb = new StringBuilder();
            sb.append("bitmap == null ");
            sb.append(r2 == null);
            Log.d("PDYuYueVerifyDialog", sb.toString());
        }
        return r2;
    }

    public static void saveAuthCodePDBPParm(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonBase.getJdSharedPreferences().edit().putString("lib_pd_authcode_pdbp", str).commit();
    }

    public static void saveAuthSucTokenParam(String str) {
        CommonBase.getJdSharedPreferences().edit().putString("lib_pd_authcode_pdtk", str).commit();
        if (Log.D) {
            Log.d("\u670d\u52a1\u7aef\u4e0b\u53d1\u7684pdtk =", str);
        }
    }

    public static void updateCDNFlag(boolean z) {
        CommonBase.getJdSharedPreferences().edit().putBoolean("com_jd_pd_cdn_flag", z).commit();
    }

    public static void updateCDNReportFlag(boolean z) {
        CommonBase.getJdSharedPreferences().edit().putBoolean("com_jd_pd_cdn_report_flag", z).commit();
    }
}
