package com.jingdong.common.utils;

import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized java.lang.String getSk() {
        /*
            Method dump skipped, instructions count: 592
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.AdvertUtils.getSk():java.lang.String");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void initDataNew(java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
            Method dump skipped, instructions count: 651
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.AdvertUtils.initDataNew(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void");
    }
}
