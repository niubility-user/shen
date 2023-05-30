package com.jingdong.common.utils;

import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.runTimeConfig.ConfigUtil;
import com.jingdong.common.search.SearchConstants;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class AdvertUtils {
    private static String EVENT_ID_UNPL_ADC = "jingdongunionsdk_1626424295026|17";
    private static String EVENT_ID_UNPL_TOO_LONG = "updateunpl__union1_ck";
    private static final String KEY_BASETIME = "KEPLER_BASETIME";
    private static final String KEY_CHANNEL = "KEPLER_CHANNEL";
    private static final String KEY_CHANNEL_SOURCE_VALIDTIME = "channelsourceValidTime";
    public static final String KEY_JDVTIME = "AVERT_JDVTIME";
    private static final String KEY_KEPLERPARAM_ENABLE = "keplerparamEnable";
    private static final String KEY_MOPENBP5 = "KEPLER_MOPENBP5";
    private static final String KEY_M_PARAM = "AVERT_M_PARAM";
    public static final String KEY_M_PARAMTIME = "AVERT_M_PARAMTIME";
    private static final String KEY_SE = "AVERT_SE";
    private static final String KEY_SI = "AVERT_SI";
    private static final String KEY_SK = "KEPLER_SK";
    private static final String KEY_SOURCE = "KEPLER_SOURCE";
    private static final String KEY_TIMESTAMP = "KEPLER_TIMESTAMP";
    private static String REPORT_SITEID = "ja2021_7123928";
    private static String REPORT_URL = "https://knicks.jd.com/log/server";
    private static final String TAG = "AdvertUtils";
    private static final int UNPL_LENGTH_LIMIT = 2048;
    private static String flt = "";
    private static boolean isNeedADCReport;
    private static boolean isNeedTooLongReport;
    private static String m_param;
    private static String m_paramTime;
    private static String se;
    private static String si;
    private static String sk;
    public static final String[] ENCODE_TAG = {"%7C", "%257C", "%25257C"};
    private static final String[] NON_DIRTY_PAGE_NAME = {"WebActivity", "ProductDetailActivity", "LikeMoreActivity", "JshopMainShopActivity", SearchConstants.PAGE_PRODUCTLIST, "NewFillOrderActivity", "BabelActivity"};

    public static synchronized void changeJdvToMParam(String str) {
        synchronized (AdvertUtils.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "changeJdvToMParam : " + str);
            }
            if (str == null) {
                str = "";
            }
            try {
                String mParam = getMParam();
                if (TextUtils.isEmpty(mParam)) {
                    mParam = "{}";
                }
                JSONObject jSONObject = new JSONObject(mParam);
                jSONObject.put("jdv", str);
                jSONObject.put("m_source", "2");
                if (OKLog.D) {
                    OKLog.d(TAG, "changeJdvToMParam : " + jSONObject.toString());
                }
                saveMParam(jSONObject.toString());
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
    }

    private static void checkADC_(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("ADC_")) {
            return;
        }
        isNeedADCReport = true;
    }

    private static String checkMParamUnpl(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("unpl");
            if (StringUtil.isEmpty(optString)) {
                return str;
            }
            checkADC_(optString);
            if (optString.length() <= 2048) {
                return str;
            }
            String replaceEncodeTag = replaceEncodeTag(optString);
            if (replaceEncodeTag.contains("|")) {
                replaceEncodeTag = replaceEncodeTag.split(DYConstants.DY_REGEX_VERTICAL_LINE)[0];
            }
            if (replaceEncodeTag.length() > 2048) {
                isNeedTooLongReport = true;
                if (!updataUnplWhenOver2048()) {
                    return "";
                }
            }
            jSONObject.put("unpl", replaceEncodeTag);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return str;
        }
    }

    private static String checkSeUnpl(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("unpl");
            if (!TextUtils.isEmpty(optString)) {
                checkADC_(optString);
                if (optString.length() <= 2048) {
                    return str;
                }
                String replaceEncodeTag = replaceEncodeTag(optString);
                if (replaceEncodeTag.contains("|")) {
                    replaceEncodeTag = replaceEncodeTag.split(DYConstants.DY_REGEX_VERTICAL_LINE)[0];
                }
                if (replaceEncodeTag.length() > 2048) {
                    isNeedTooLongReport = true;
                    if (!updataUnplWhenOver2048()) {
                        return "";
                    }
                }
                jSONObject.put("unpl", replaceEncodeTag);
                str = jSONObject.toString();
            }
        } catch (JSONException unused) {
            checkADC_(str);
            if (str.length() <= 2048) {
                return str;
            }
            str = replaceEncodeTag(str);
            if (str.contains("|")) {
                str = str.split(DYConstants.DY_REGEX_VERTICAL_LINE)[0];
            }
            if (str.length() > 2048) {
                isNeedTooLongReport = true;
                if (!updataUnplWhenOver2048()) {
                    return "";
                }
            }
        }
        return str;
    }

    private static String checkSiUnpl(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        checkADC_(str);
        if (str.length() <= 2048) {
            return str;
        }
        String replaceEncodeTag = replaceEncodeTag(str);
        if (replaceEncodeTag.contains("|")) {
            replaceEncodeTag = replaceEncodeTag.split(DYConstants.DY_REGEX_VERTICAL_LINE)[0];
        }
        if (replaceEncodeTag.length() > 2048) {
            isNeedTooLongReport = true;
            return !updataUnplWhenOver2048() ? "" : replaceEncodeTag;
        }
        return replaceEncodeTag;
    }

    public static synchronized void dealFltOnPageStart(String str) {
        synchronized (AdvertUtils.class) {
            if (isDirtyPage(str)) {
                flt = "";
            }
        }
    }

    public static synchronized String getBusinessId() {
        String str;
        synchronized (AdvertUtils.class) {
            str = "";
            String sk2 = getSk();
            if (!TextUtils.isEmpty(sk2)) {
                JSONObject jSONObject = null;
                try {
                    jSONObject = new JSONObject(sk2).optJSONObject("otherData");
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
                }
                if (jSONObject != null) {
                    str = jSONObject.optString("mopenbp8");
                }
            }
        }
        return str;
    }

    private static String[] getEncodeTag() {
        try {
            String config = JDMobileConfig.getInstance().getConfig("JingdongUnion", "unplEncode", "arr", "");
            if (TextUtils.isEmpty(config)) {
                return ENCODE_TAG;
            }
            return (String[]) JDJSON.parseArray(config).toArray(new String[0]);
        } catch (Throwable unused) {
            return ENCODE_TAG;
        }
    }

    public static synchronized String getFlt() {
        String str;
        synchronized (AdvertUtils.class) {
            str = flt;
        }
        return str;
    }

    public static synchronized String getJdvTime() {
        String str;
        synchronized (AdvertUtils.class) {
            long j2 = CommonBase.getJdSharedPreferences().getLong(KEY_JDVTIME, -1L);
            if (OKLog.D) {
                OKLog.d(TAG, "getJdvTime : " + j2);
            }
            if (j2 > 0) {
                str = j2 + "";
            } else {
                str = "";
            }
        }
        return str;
    }

    public static synchronized JSONObject getKeplerParams(IRouterParams iRouterParams) {
        synchronized (AdvertUtils.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "getKeplerParams");
            }
            JSONObject jSONObject = new JSONObject();
            if (iRouterParams == null || iRouterParams.getContext() == null) {
                return jSONObject;
            }
            try {
                return new JSONObject(getSk());
            } catch (Exception e2) {
                e2.printStackTrace();
                return jSONObject;
            }
        }
    }

    public static synchronized String getMParam() {
        String str;
        synchronized (AdvertUtils.class) {
            if (TextUtils.isEmpty(m_param)) {
                m_param = CommonBase.getJdSharedPreferences().getString(KEY_M_PARAM, "");
            }
            if (OKLog.D) {
                OKLog.d(TAG, "getMParam : " + m_param);
            }
            str = m_param;
        }
        return str;
    }

    public static synchronized String getMParamTime() {
        String str;
        synchronized (AdvertUtils.class) {
            if (TextUtils.isEmpty(m_paramTime)) {
                m_paramTime = CommonBase.getJdSharedPreferences().getString(KEY_M_PARAMTIME, "");
            }
            if (OKLog.D) {
                OKLog.d(TAG, "getMParamTime : " + m_paramTime);
            }
            str = m_paramTime;
        }
        return str;
    }

    public static synchronized String getSe() {
        String str;
        synchronized (AdvertUtils.class) {
            if (TextUtils.isEmpty(se)) {
                se = CommonBase.getJdSharedPreferences().getString(KEY_SE, "");
            }
            if (OKLog.D) {
                OKLog.d(TAG, "getSe : " + se);
            }
            str = se;
        }
        return str;
    }

    public static synchronized String getSi() {
        String str;
        synchronized (AdvertUtils.class) {
            if (TextUtils.isEmpty(si)) {
                si = CommonBase.getJdSharedPreferences().getString(KEY_SI, "");
            }
            if (OKLog.D) {
                OKLog.d(TAG, "getSi : " + si);
            }
            str = si;
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0184 A[Catch: Exception -> 0x01b2, all -> 0x024d, TryCatch #4 {Exception -> 0x01b2, blocks: (B:59:0x017e, B:61:0x0184, B:63:0x018f, B:64:0x0194, B:66:0x019a, B:68:0x01a1, B:69:0x01a6, B:71:0x01ac), top: B:119:0x017e, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x019a A[Catch: Exception -> 0x01b2, all -> 0x024d, TryCatch #4 {Exception -> 0x01b2, blocks: (B:59:0x017e, B:61:0x0184, B:63:0x018f, B:64:0x0194, B:66:0x019a, B:68:0x01a1, B:69:0x01a6, B:71:0x01ac), top: B:119:0x017e, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01a1 A[Catch: Exception -> 0x01b2, all -> 0x024d, TryCatch #4 {Exception -> 0x01b2, blocks: (B:59:0x017e, B:61:0x0184, B:63:0x018f, B:64:0x0194, B:66:0x019a, B:68:0x01a1, B:69:0x01a6, B:71:0x01ac), top: B:119:0x017e, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01ac A[Catch: Exception -> 0x01b2, all -> 0x024d, TRY_LEAVE, TryCatch #4 {Exception -> 0x01b2, blocks: (B:59:0x017e, B:61:0x0184, B:63:0x018f, B:64:0x0194, B:66:0x019a, B:68:0x01a1, B:69:0x01a6, B:71:0x01ac), top: B:119:0x017e, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01be A[Catch: all -> 0x024d, TryCatch #3 {, blocks: (B:4:0x0003, B:6:0x0007, B:7:0x001f, B:9:0x0029, B:13:0x003c, B:14:0x0046, B:16:0x0056, B:18:0x006e, B:20:0x007a, B:21:0x0090, B:28:0x00a1, B:30:0x00b4, B:31:0x00ca, B:33:0x00d8, B:34:0x00ee, B:36:0x00f3, B:38:0x011b, B:39:0x0131, B:41:0x013e, B:43:0x014d, B:45:0x0159, B:58:0x0179, B:59:0x017e, B:61:0x0184, B:63:0x018f, B:64:0x0194, B:66:0x019a, B:68:0x01a1, B:69:0x01a6, B:71:0x01ac, B:77:0x01ba, B:79:0x01be, B:80:0x01d8, B:74:0x01b3, B:76:0x01b7, B:44:0x0153, B:52:0x016b, B:48:0x0161, B:50:0x0165, B:55:0x0172, B:57:0x0176, B:83:0x01de, B:85:0x01e6, B:88:0x01ec, B:90:0x01f2, B:93:0x01f6, B:95:0x0209, B:97:0x0211, B:98:0x021b, B:99:0x0220, B:103:0x022d, B:105:0x0231, B:106:0x0249, B:102:0x0228, B:24:0x0096, B:26:0x009a), top: B:118:0x0003, inners: #0, #1, #2, #4, #5 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized String getSk() {
        long j2;
        synchronized (AdvertUtils.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "getSk : " + sk);
            }
            boolean keySwitchState = ConfigUtil.getKeySwitchState(KEY_KEPLERPARAM_ENABLE);
            if (OKLog.D) {
                StringBuilder sb = new StringBuilder();
                sb.append("getSk === keplerparamEnable : ");
                sb.append(keySwitchState ? DYConstants.DY_TRUE : DYConstants.DY_FALSE);
                OKLog.d(TAG, sb.toString());
            }
            String string = CommonBase.getJdSharedPreferences().getString(KEY_TIMESTAMP, "");
            if (OKLog.D) {
                OKLog.d(TAG, "getSk === timestamp \uff1a" + string);
            }
            if (keySwitchState) {
                try {
                    String stringFromPreference = ConfigUtil.getStringFromPreference(KEY_CHANNEL_SOURCE_VALIDTIME, "3600");
                    if (OKLog.D) {
                        OKLog.d(TAG, "channelsourceValidTime :" + stringFromPreference);
                    }
                    j2 = Long.parseLong(stringFromPreference);
                } catch (NumberFormatException e2) {
                    if (OKLog.D) {
                        OKLog.d(TAG, e2);
                    }
                    j2 = 3600;
                }
                String str = "";
                String str2 = "";
                String str3 = "";
                String str4 = "";
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                if (OKLog.D) {
                    OKLog.d(TAG, "currentTime \uff1a" + currentTimeMillis);
                }
                long j3 = CommonBase.getJdSharedPreferences().getLong(KEY_BASETIME, currentTimeMillis);
                if (OKLog.D) {
                    OKLog.d(TAG, "baseTime : " + j3);
                }
                if (currentTimeMillis <= j3 + j2) {
                    str = CommonBase.getJdSharedPreferences().getString(KEY_SOURCE, "");
                    str2 = CommonBase.getJdSharedPreferences().getString(KEY_CHANNEL, "");
                    str3 = CommonBase.getJdSharedPreferences().getString(KEY_MOPENBP5, "");
                    if (OKLog.D) {
                        OKLog.d(TAG, "\u6570\u636e\u5e93\u4e2d\u5b58\u50a8\u7684mopenbp5:" + str3);
                    }
                }
                JSONObject jSONObject = new JSONObject();
                if (!TextUtils.isEmpty(sk)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(sk);
                        jSONObject = jSONObject2.optJSONObject("otherData");
                        if (jSONObject != null) {
                            jSONObject.put("mopenbp5", str3);
                        } else {
                            jSONObject = new JSONObject();
                        }
                        str4 = jSONObject2.optString("_mkjdcn");
                    } catch (Exception e3) {
                        if (OKLog.D) {
                            OKLog.e(TAG, e3);
                        }
                    }
                    JSONObject jSONObject3 = new JSONObject();
                    try {
                        if (!TextUtils.isEmpty(str)) {
                            jSONObject3.put("source", str);
                            if (!TextUtils.isEmpty(string)) {
                                jSONObject.put("comeInTime", string);
                            }
                        }
                        if (!TextUtils.isEmpty(str2)) {
                            jSONObject3.put("channel", str2);
                        }
                        if (jSONObject != null) {
                            jSONObject3.put("otherData", jSONObject);
                        }
                        if (!TextUtils.isEmpty(str4)) {
                            jSONObject3.put("_mkjdcn", str4);
                        }
                    } catch (Exception e4) {
                        if (OKLog.D) {
                            e4.printStackTrace();
                        }
                    }
                    if (OKLog.D) {
                        OKLog.d(TAG, "kpJsonObject : " + jSONObject3.toString());
                    }
                    return jSONObject3.toString();
                }
                try {
                    jSONObject.put("mopenbp5", str3);
                } catch (Exception e5) {
                    if (OKLog.D) {
                        e5.printStackTrace();
                    }
                }
                JSONObject jSONObject32 = new JSONObject();
                if (!TextUtils.isEmpty(str)) {
                }
                if (!TextUtils.isEmpty(str2)) {
                }
                if (jSONObject != null) {
                }
                if (!TextUtils.isEmpty(str4)) {
                }
                if (OKLog.D) {
                }
                return jSONObject32.toString();
            } else if (TextUtils.isEmpty(sk)) {
                sk = "";
                return "";
            } else if (StringUtil.isEmpty(string)) {
                return sk;
            } else {
                try {
                    JSONObject jSONObject4 = new JSONObject(sk);
                    if (!StringUtil.isEmpty(jSONObject4.optString("source"))) {
                        JSONObject optJSONObject = jSONObject4.optJSONObject("otherData");
                        if (optJSONObject == null) {
                            optJSONObject = new JSONObject();
                            jSONObject4.put("otherData", optJSONObject);
                        }
                        optJSONObject.put("comeInTime", string);
                    }
                    sk = jSONObject4.toString();
                } catch (Exception e6) {
                    OKLog.e(TAG, e6);
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "\u6dfb\u52a0\u65f6\u95f4\u6233\u540e getSk :" + sk);
                }
                return sk;
            }
        }
    }

    @Deprecated
    public static synchronized void initData(String str, String str2) {
        synchronized (AdvertUtils.class) {
            initDataNew(str, str2, "");
        }
    }

    public static synchronized void initDataNew(String str, String str2, String str3) {
        synchronized (AdvertUtils.class) {
            initDataNew(str, str2, "", str3);
        }
    }

    public static synchronized void initFlt(String str) {
        synchronized (AdvertUtils.class) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            try {
                String queryParameter = Uri.parse(new JSONObject(str).optString("ref", "")).getQueryParameter(PDConstant.EXTRA_FLT);
                if (!TextUtils.isEmpty(queryParameter)) {
                    flt = queryParameter;
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
    }

    private static synchronized boolean isDirtyPage(String str) {
        synchronized (AdvertUtils.class) {
            for (String str2 : NON_DIRTY_PAGE_NAME) {
                if (str2.equals(str)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static String replaceEncodeTag(String str) {
        if (StringUtil.isEmpty(str)) {
            return str;
        }
        for (String str2 : getEncodeTag()) {
            if (str.contains(str2)) {
                str = str.replaceAll(str2, "|");
            }
        }
        return str;
    }

    private static void reportUnplError(String str, String str2, String str3, String str4, String str5, String str6) {
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("se", (Object) str2);
        jDJSONObject.put("si", (Object) str3);
        jDJSONObject.put("sk", (Object) str4);
        jDJSONObject.put(JshopConst.JSHOP_M_PARAM, (Object) str5);
        jDJSONObject.put("unplsource", (Object) str6);
        JDMtaUtils.sendCommonData(JdSdk.getInstance().getApplicationContext(), str, jDJSONObject.toJSONString(), "", "", "", "", "", "");
    }

    public static synchronized void saveMParam(String str) {
        synchronized (AdvertUtils.class) {
            if (OKLog.D) {
                OKLog.d(TAG, "saveMParam : " + str);
            }
            if (!TextUtils.isEmpty(str)) {
                m_param = str;
                SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
                edit.putString(KEY_M_PARAM, str);
                String str2 = (System.currentTimeMillis() / 1000) + "";
                m_paramTime = str2;
                edit.putString(KEY_M_PARAMTIME, str2);
                edit.apply();
                JDMtaUtils.updateUnpl(str, null, null);
            }
        }
    }

    public static synchronized void setFlt(String str) {
        synchronized (AdvertUtils.class) {
            if (!TextUtils.isEmpty(str) && !str.equals(flt)) {
                flt = str;
            }
        }
    }

    public static boolean updataUnplWhenOver2048() {
        return "1".equals(JDMobileConfig.getInstance().getConfig("JingdongUnion", "unplSwitch", "updateWarningSwitch", "1"));
    }

    @Deprecated
    public static synchronized void initData(String str, String str2, String str3) {
        synchronized (AdvertUtils.class) {
            initDataNew(str, str2, str3, "");
        }
    }

    public static synchronized void initDataNew(String str, String str2, String str3, String str4) {
        synchronized (AdvertUtils.class) {
            initDataNew(str, str2, str3, "", str4);
        }
    }

    @Deprecated
    public static synchronized void initData(String str, String str2, String str3, String str4) {
        synchronized (AdvertUtils.class) {
            initDataNew(str, str2, str3, str4, "");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:80:0x024b A[Catch: all -> 0x0288, TryCatch #2 {, blocks: (B:4:0x0005, B:6:0x0009, B:8:0x0088, B:16:0x00a9, B:19:0x00b8, B:22:0x00c9, B:24:0x00d8, B:26:0x00dc, B:28:0x00f6, B:30:0x00fa, B:31:0x010b, B:32:0x0113, B:35:0x011f, B:42:0x0137, B:47:0x0146, B:49:0x0157, B:50:0x016d, B:52:0x0173, B:54:0x0177, B:55:0x018d, B:56:0x01a3, B:58:0x01ad, B:62:0x01c0, B:64:0x01cc, B:66:0x01e0, B:67:0x01eb, B:69:0x01f1, B:71:0x01f7, B:72:0x020c, B:74:0x0222, B:81:0x0250, B:83:0x0254, B:84:0x0280, B:78:0x0247, B:80:0x024b), top: B:94:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0254 A[Catch: all -> 0x0288, TryCatch #2 {, blocks: (B:4:0x0005, B:6:0x0009, B:8:0x0088, B:16:0x00a9, B:19:0x00b8, B:22:0x00c9, B:24:0x00d8, B:26:0x00dc, B:28:0x00f6, B:30:0x00fa, B:31:0x010b, B:32:0x0113, B:35:0x011f, B:42:0x0137, B:47:0x0146, B:49:0x0157, B:50:0x016d, B:52:0x0173, B:54:0x0177, B:55:0x018d, B:56:0x01a3, B:58:0x01ad, B:62:0x01c0, B:64:0x01cc, B:66:0x01e0, B:67:0x01eb, B:69:0x01f1, B:71:0x01f7, B:72:0x020c, B:74:0x0222, B:81:0x0250, B:83:0x0254, B:84:0x0280, B:78:0x0247, B:80:0x024b), top: B:94:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized void initDataNew(String str, String str2, String str3, String str4, String str5) {
        String str6;
        String str7;
        String str8;
        boolean z;
        String str9;
        boolean z2;
        String str10;
        boolean z3;
        String str11;
        String str12;
        String str13;
        String str14;
        String str15;
        synchronized (AdvertUtils.class) {
            if (OKLog.D) {
                StringBuilder sb = new StringBuilder();
                sb.append("init old data seSrc : ");
                str6 = str;
                sb.append(str6);
                OKLog.d(TAG, sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("init old data siSrc : ");
                str7 = str2;
                sb2.append(str7);
                OKLog.d(TAG, sb2.toString());
                OKLog.d(TAG, "init old data skSrc : " + str3);
                StringBuilder sb3 = new StringBuilder();
                sb3.append("init old data m_paramSrc : ");
                str8 = str4;
                sb3.append(str8);
                OKLog.d(TAG, sb3.toString());
                OKLog.d(TAG, "init old data fromSrc : " + str5);
            } else {
                str6 = str;
                str7 = str2;
                str8 = str4;
            }
            isNeedTooLongReport = false;
            isNeedADCReport = false;
            boolean isEmpty = TextUtils.isEmpty(str);
            boolean isEmpty2 = TextUtils.isEmpty(str2);
            boolean isEmpty3 = TextUtils.isEmpty(str3);
            boolean isEmpty4 = TextUtils.isEmpty(str4);
            if (isEmpty && isEmpty2 && isEmpty3 && isEmpty4) {
                return;
            }
            if (isEmpty4) {
                z = isEmpty4;
                str9 = str8;
            } else {
                String checkMParamUnpl = checkMParamUnpl(str4);
                str9 = checkMParamUnpl;
                z = TextUtils.isEmpty(checkMParamUnpl);
            }
            if (isEmpty) {
                z2 = isEmpty;
                str10 = str6;
            } else {
                String checkSeUnpl = checkSeUnpl(str);
                str10 = checkSeUnpl;
                z2 = TextUtils.isEmpty(checkSeUnpl);
            }
            if (isEmpty2) {
                z3 = isEmpty2;
                str11 = str7;
            } else {
                String checkSiUnpl = checkSiUnpl(str2);
                str11 = checkSiUnpl;
                z3 = TextUtils.isEmpty(checkSiUnpl);
            }
            if (isNeedTooLongReport) {
                str12 = str11;
                str13 = str10;
                reportUnplError(EVENT_ID_UNPL_TOO_LONG, str, str2, str3, str4, str5);
                isNeedTooLongReport = false;
            } else {
                str12 = str11;
                str13 = str10;
            }
            if (isNeedADCReport) {
                reportUnplError(EVENT_ID_UNPL_ADC, str, str2, str3, str4, str5);
                isNeedADCReport = false;
            }
            SharedPreferences.Editor edit = CommonBase.getJdSharedPreferences().edit();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                long j2 = currentTimeMillis / 1000;
                if (z2) {
                    str15 = str13;
                } else {
                    str15 = str13;
                    try {
                        se = str15;
                        edit.putString(KEY_SE, str15);
                        edit.putLong(KEY_JDVTIME, j2);
                    } catch (Exception e2) {
                        e = e2;
                        str14 = str12;
                        if (OKLog.E) {
                        }
                        if (OKLog.D) {
                        }
                        edit.apply();
                        JDMtaUtils.updateUnpl(str9, str15, str14);
                    }
                }
                if (z3) {
                    str14 = str12;
                } else {
                    str14 = str12;
                    try {
                        si = str14;
                        edit.putString(KEY_SI, str14);
                    } catch (Exception e3) {
                        e = e3;
                        if (OKLog.E) {
                            OKLog.e(TAG, e);
                        }
                        if (OKLog.D) {
                        }
                        edit.apply();
                        JDMtaUtils.updateUnpl(str9, str15, str14);
                    }
                }
                if (!isEmpty3) {
                    sk = str3;
                    JSONObject jSONObject = new JSONObject(str3);
                    String optString = jSONObject.optString("source");
                    if (OKLog.D) {
                        OKLog.d(TAG, "initData === source : " + optString);
                    }
                    if (!TextUtils.isEmpty(optString)) {
                        if (OKLog.D) {
                            OKLog.d(TAG, "initData === currentTimestamp : " + currentTimeMillis);
                        }
                        edit.putString(KEY_TIMESTAMP, currentTimeMillis + "");
                    }
                    boolean keySwitchState = ConfigUtil.getKeySwitchState(KEY_KEPLERPARAM_ENABLE);
                    if (OKLog.D) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("initData === keplerparamEnable : ");
                        sb4.append(keySwitchState ? DYConstants.DY_TRUE : DYConstants.DY_FALSE);
                        OKLog.d(TAG, sb4.toString());
                    }
                    if (keySwitchState) {
                        String optString2 = jSONObject.optString("channel");
                        String optString3 = jSONObject.optString("otherData");
                        String optString4 = TextUtils.isEmpty(optString3) ? "" : new JSONObject(optString3).optString("mopenbp5");
                        if (!TextUtils.isEmpty(optString) || !TextUtils.isEmpty(optString2)) {
                            edit.putString(KEY_SOURCE, optString);
                            edit.putString(KEY_CHANNEL, optString2);
                            edit.putString(KEY_MOPENBP5, optString4);
                            edit.putLong(KEY_BASETIME, j2);
                        }
                    } else {
                        edit.remove(KEY_SOURCE);
                        edit.remove(KEY_CHANNEL);
                        edit.remove(KEY_MOPENBP5);
                        edit.remove(KEY_BASETIME);
                    }
                }
                if (!z) {
                    m_param = str9;
                    edit.putString(KEY_M_PARAM, str9);
                    String str16 = j2 + "";
                    m_paramTime = str16;
                    edit.putString(KEY_M_PARAMTIME, str16);
                }
            } catch (Exception e4) {
                e = e4;
                str14 = str12;
                str15 = str13;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "init encode data se : " + str15);
                OKLog.d(TAG, "init encode data si : " + str14);
            }
            edit.apply();
            JDMtaUtils.updateUnpl(str9, str15, str14);
        }
    }
}
