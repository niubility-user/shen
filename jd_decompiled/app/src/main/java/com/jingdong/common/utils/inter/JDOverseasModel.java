package com.jingdong.common.utils.inter;

import android.graphics.Color;
import android.text.TextUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSONObject;
import com.jd.jdsdk.security.AesCbcCrypto;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.net.URLEncoder;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class JDOverseasModel {
    private static final String KEY_OVERSEAS_LAST_GEO = "key_overseas_last_geo";
    private static final String KEY_OVERSEAS_LAST_SHOW_TIME = "key_overseas_last_show_time";
    private static final String SPLIT_SYMBOL = "##";
    private static final String S_KEY = "5yKhoqodQjuHGlKZ";
    private static final String S_PARAMETER = "7WwXmH2TKSCIEJQ3";
    private int mAreaCode;
    private int mAreaColor;
    private String mAreaIconImg1;
    private String mAreaIconImg2;
    private String mAreaIconImg3;
    private String mAreaIconImg4;
    private String mAreaIconText1;
    private String mAreaIconText2;
    private String mAreaIconText3;
    private String mAreaIconText4;
    private String mAreaImg;
    private String mAreaName;
    private String mAreaText;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public interface IOverseasListener {
        void showDialog(JDOverseasModel jDOverseasModel);

        void unShowDialog();
    }

    public JDOverseasModel(JDJSONObject jDJSONObject) {
        this.mAreaCode = jDJSONObject.optInt("homeAreaCode", 0);
        this.mAreaName = jDJSONObject.optString("areaName");
        this.mAreaText = jDJSONObject.optString("text");
        this.mAreaImg = jDJSONObject.optString("img");
        this.mAreaColor = getColor(jDJSONObject.optString("areaColor"), -460552);
        this.mAreaIconImg1 = jDJSONObject.optString("areaIconImg1");
        this.mAreaIconText1 = jDJSONObject.optString("areaIconText1");
        this.mAreaIconImg2 = jDJSONObject.optString("areaIconImg2");
        this.mAreaIconText2 = jDJSONObject.optString("areaIconText2");
        this.mAreaIconImg3 = jDJSONObject.optString("areaIconImg3");
        this.mAreaIconText3 = jDJSONObject.optString("areaIconText3");
        this.mAreaIconImg4 = jDJSONObject.optString("areaIconImg4");
        this.mAreaIconText4 = jDJSONObject.optString("areaIconText4");
        updateLastShowTime(jDJSONObject.optLong("intervalDay", 1L), getLastShowTime()[1]);
    }

    public static void addShowTimes() {
        updateLastShowTime(getLastShowTime()[0], System.currentTimeMillis());
    }

    private static String encrypt(String str) {
        String encrypt = AesCbcCrypto.encrypt(str, S_KEY, S_PARAMETER.getBytes());
        return encrypt == null ? "" : encrypt;
    }

    public static int getColor(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        try {
            return Color.parseColor(TextUtils.split(str.trim(), DYConstants.DY_REGEX_COMMA)[0].trim());
        } catch (Exception e2) {
            e2.printStackTrace();
            return i2;
        }
    }

    private static String getEncryptJSONParam(JSONObject jSONObject) {
        String str = "";
        try {
            str = encrypt(jSONObject.toString());
            return URLEncoder.encode(str, "utf-8");
        } catch (Exception e2) {
            e2.printStackTrace();
            return str;
        }
    }

    private static String getLastGeoInfo() {
        String stringFromPreference;
        try {
            stringFromPreference = CommonBase.getStringFromPreference(KEY_OVERSEAS_LAST_GEO, "");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (TextUtils.isEmpty(stringFromPreference)) {
            return "";
        }
        String[] split = TextUtils.split(stringFromPreference, ";");
        if (split.length == 3) {
            return getLbsEncryptStr(Double.parseDouble(split[0]), Double.parseDouble(split[1]), Long.parseLong(split[2]));
        }
        return "";
    }

    private static long[] getLastShowTime() {
        String[] split;
        long[] jArr = {1, 0};
        String stringFromPreference = CommonBase.getStringFromPreference(KEY_OVERSEAS_LAST_SHOW_TIME, "");
        if (!TextUtils.isEmpty(stringFromPreference) && stringFromPreference.contains("##") && (split = stringFromPreference.split("##")) != null && split.length == 2 && !TextUtils.isEmpty(split[0]) && !TextUtils.isEmpty(split[1])) {
            try {
                long parseLong = Long.parseLong(split[0]);
                long parseLong2 = Long.parseLong(split[1]);
                if (parseLong >= 1 && parseLong2 >= 0) {
                    jArr[0] = parseLong;
                    jArr[1] = parseLong2;
                    return jArr;
                }
            } catch (Throwable unused) {
            }
        }
        updateLastShowTime(1L, 0L);
        return jArr;
    }

    private static String getLbsEncryptStr(double d, double d2, long j2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(HybridSDK.LNG, String.valueOf(d2));
            jSONObject.put("lat", String.valueOf(d));
            jSONObject.put(VerifyTracker.KEY_TIMESTAMP, j2);
            return getEncryptJSONParam(jSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean hasShowTimes() {
        long[] lastShowTime = getLastShowTime();
        return System.currentTimeMillis() - lastShowTime[1] >= ((lastShowTime[0] * 24) * 3600) * 1000;
    }

    public static void initParams(HttpSetting httpSetting, JDLocation jDLocation) {
        long currentTimeMillis = System.currentTimeMillis();
        String lastGeoInfo = getLastGeoInfo();
        if (jDLocation != null) {
            updateSavedGeoInfo(jDLocation, currentTimeMillis);
        }
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setFunctionId("homeAreaPop");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setEffect(0);
        if (jDLocation != null) {
            httpSetting.putJsonParam("geo", getLbsEncryptStr(jDLocation.getLat(), jDLocation.getLng(), currentTimeMillis));
        }
        httpSetting.putJsonParam("geoLast", lastGeoInfo);
        httpSetting.putJsonParam("homeAreaCode", String.valueOf(Math.max(JDOverseasUtil.getCurrentOverseasArea(), 0)));
    }

    public static void requestDialogInfo(JDLocation jDLocation, final IOverseasListener iOverseasListener) {
        HttpSetting httpSetting = new HttpSetting();
        initParams(httpSetting, jDLocation);
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.utils.inter.JDOverseasModel.1
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null) {
                    onError(null);
                    return;
                }
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject == null) {
                    onError(null);
                    return;
                }
                JDJSONObject optJSONObject = fastJsonObject.optJSONObject("data");
                if (optJSONObject == null) {
                    onError(null);
                    return;
                }
                JDOverseasModel jDOverseasModel = new JDOverseasModel(optJSONObject);
                if (!jDOverseasModel.changed(JDOverseasUtil.getCurrentOverseasArea())) {
                    onError(null);
                } else {
                    IOverseasListener.this.showDialog(jDOverseasModel);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                IOverseasListener.this.unShowDialog();
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    private static void updateLastShowTime(long j2, long j3) {
        CommonBase.putStringToPreference(KEY_OVERSEAS_LAST_SHOW_TIME, Math.max(1L, j2) + "##" + j3);
    }

    private static void updateSavedGeoInfo(JDLocation jDLocation, long j2) {
        CommonBase.putStringToPreference(KEY_OVERSEAS_LAST_GEO, jDLocation.getLat() + ";" + jDLocation.getLng() + ";" + j2);
    }

    public boolean changed(int i2) {
        JDOverseasDialogUtil.log("check: currentCode = " + i2 + " requestCode = " + this.mAreaCode);
        int i3 = this.mAreaCode;
        return i3 >= 0 && i3 != i2;
    }

    public int getAreaCode() {
        return this.mAreaCode;
    }

    public int getAreaColor() {
        return this.mAreaColor;
    }

    public String getAreaIconImg1() {
        return this.mAreaIconImg1;
    }

    public String getAreaIconImg2() {
        return this.mAreaIconImg2;
    }

    public String getAreaIconImg3() {
        return this.mAreaIconImg3;
    }

    public String getAreaIconImg4() {
        return this.mAreaIconImg4;
    }

    public String getAreaIconText1() {
        return this.mAreaIconText1;
    }

    public String getAreaIconText2() {
        return this.mAreaIconText2;
    }

    public String getAreaIconText3() {
        return this.mAreaIconText3;
    }

    public String getAreaIconText4() {
        return this.mAreaIconText4;
    }

    public String getAreaImg() {
        return this.mAreaImg;
    }

    public String getAreaName() {
        return this.mAreaName;
    }

    public String getAreaText() {
        return this.mAreaText;
    }
}
