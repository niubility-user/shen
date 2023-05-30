package com.jingdong.common.login;

import android.net.Uri;
import android.text.TextUtils;
import com.jd.fireeye.security.TrackOrder;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class SPUtil {
    private static final String TAG = "SPUtil";
    private static String callerPackage = "";

    public static Object getTrackOrderParams(IRouterParams iRouterParams) {
        if (iRouterParams != null && iRouterParams.getContext() != null) {
            return TrackOrder.getTrackOrderParams(iRouterParams.getContext());
        }
        return new JSONObject();
    }

    public static void handleTrackOrder(Uri uri) {
        String str;
        if (OKLog.D) {
            OKLog.d(TAG, "handleTrackOrder start");
        }
        if (uri != null && JumpUtil.isOpenAppScheme(uri.getScheme())) {
            String str2 = null;
            try {
                str = uri.getQueryParameter("params");
            } catch (Exception unused) {
                str = "{}";
            }
            JSONObject string2JsonObject = JumpUtil.string2JsonObject(str, uri);
            if (string2JsonObject != null) {
                if (OKLog.D) {
                    OKLog.d(TAG, "JsonObject = " + string2JsonObject);
                }
                try {
                    string2JsonObject.put("callerPackage", callerPackage);
                    String optString = string2JsonObject.optString("firstorderFlag");
                    if (TextUtils.isEmpty(optString)) {
                        return;
                    }
                    TrackOrder.getFirstOrderTracking(JdSdk.getInstance().getApplicationContext(), optString, string2JsonObject);
                    return;
                } catch (Exception e2) {
                    OKLog.e(TAG, e2);
                    return;
                }
            }
            try {
                str2 = uri.getQueryParameter("firstorderFlag");
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            if (OKLog.D) {
                OKLog.d(TAG, "firstorderFlag = " + str2);
            }
            try {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("firstorderFlag", str2);
                jSONObject.put("packageName", uri.getQueryParameter("packageName"));
                jSONObject.put(RecommendMtaUtils.TRACKING, uri.getQueryParameter(RecommendMtaUtils.TRACKING));
                jSONObject.put("firstorderUnion", uri.getQueryParameter("firstorderUnion"));
                jSONObject.put("firstorderChannel", uri.getQueryParameter("firstorderChannel"));
                jSONObject.put("firstorderExtended1", uri.getQueryParameter("firstorderExtended1"));
                jSONObject.put("firstorderExtended2", uri.getQueryParameter("firstorderExtended2"));
                jSONObject.put("firstorderExtended3", uri.getQueryParameter("firstorderExtended3"));
                jSONObject.put("firstorderExtended4", uri.getQueryParameter("firstorderExtended4"));
                jSONObject.put("callerPackage", callerPackage);
                if (OKLog.D) {
                    OKLog.d(TAG, "para = " + jSONObject);
                }
                TrackOrder.getFirstOrderTracking(JdSdk.getInstance().getApplicationContext(), str2, jSONObject);
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        } else if (OKLog.D) {
            OKLog.d(TAG, "handleTrackOrderOut,scheme = " + uri.getScheme());
        }
    }

    public static void setPackageName(String str) {
        callerPackage = str;
    }
}
