package com.jingdong.common.cps;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.aips.verify.tracker.VerifyTracker;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.MBaseKeyNames;
import com.jingdong.common.jump.OpenAppConstant;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.openlinktime.OpenLinkTimeManager;
import com.jingdong.common.unification.router.JDRouter;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.bmode.util.JDBModeUtils;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.union.common.helper.JdUnionJumpHelper;
import com.jingdong.union.dependency.IJumpDispatchCallBack;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class JDUnionUtils {
    public static final String DEFAULT_RULES = "[\"^http(?:s)?:\\\\/\\\\/union[-|.]click\\\\.jd\\\\.com\\\\/.*\",\"^http(?:s)?:\\\\/\\\\/u\\\\.jd\\\\.com\\\\/.*\"]";
    public static final String TAG = "JDUnionUtils";
    public static final String TAG_SP = "JDUnionUtils_sp";

    @Deprecated
    public static final boolean abTest() {
        return true;
    }

    public static void addUnionMta(Context context, String str, String str2, Bundle bundle) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        if (!TextUtils.isEmpty(str2)) {
            bundle2.putString("eventTag", str2);
        }
        bundle2.putString("testUnionStatus", str);
        String jsonParamFromBundle = getJsonParamFromBundle(bundle);
        String bundleToJson = bundleToJson(bundle2);
        JDMtaUtils.sendClickDataWithExt(context, str, bundleToJson, "", "", TAG, "", "", jsonParamFromBundle, null);
        if ("0".equals(JDMobileConfig.getInstance().getConfig("JingdongUnion", "unionSdkABTest", "useUnionSdk", "0"))) {
            return;
        }
        setTagToSp(context, bundleToJson);
    }

    public static void addUnionWebMta(Context context, String str, Bundle bundle) {
        addUnionMta(context, "Webview_H5toCPS", str, bundle);
    }

    public static void appStartUp(Context context, HashMap<String, String> hashMap) {
        if (context != null) {
            String tagFromSp = getTagFromSp(context);
            if (TextUtils.isEmpty(tagFromSp)) {
                return;
            }
            if (hashMap == null) {
                hashMap = new HashMap<>();
            }
            hashMap.put("lastEventParam", tagFromSp);
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.putAll(hashMap);
            JDMtaUtils.sendCommonData(context, "jingdongunionsdk_1626424295026|16", jDJSONObject.toJSONString(), "", "", "", "", "", "");
        }
    }

    public static String bundleToJson(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            for (String str : bundle.keySet()) {
                try {
                    jSONObject.put(str, bundle.get(str));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return jSONObject.toString();
        } catch (Exception unused) {
            return LangUtils.SINGLE_SPACE;
        }
    }

    public static boolean checkCpsUnionUrl(String str) {
        Set<String> rules = getRules();
        if (rules == null) {
            return false;
        }
        for (String str2 : rules) {
            if (!TextUtils.isEmpty(str2) && isUrlMtach(str2, str)) {
                return true;
            }
        }
        return false;
    }

    public static String getConfigRules() {
        return JDMobileConfig.getInstance().getConfig("JingdongUnion", "unionFilter", "filter", "");
    }

    public static String getCurrentModeTag() {
        return "2".equals(String.valueOf(JDBModeUtils.getCurrentMode())) ? "7" : JDElderModeUtils.isElderMode() ? "1" : JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext()) ? "" : "2";
    }

    public static String getJsonParamFromBundle(Bundle bundle) {
        if (bundle != null) {
            try {
                JDJSONObject openJsonParam = OpenLinkTimeManager.getInstance().getOpenJsonParam(bundle.getInt(OpenAppJumpController.KEY_OPEN_LINK_PARAMS, 0));
                return openJsonParam != null ? openJsonParam.toString() : "";
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }

    private static Set<String> getRules() {
        JDJSONArray parseArray;
        HashSet hashSet = new HashSet();
        JDJSONArray parseArray2 = JDJSON.parseArray(DEFAULT_RULES);
        if (parseArray2 != null && parseArray2.size() > 0) {
            for (int i2 = 0; i2 < parseArray2.size(); i2++) {
                String string = parseArray2.getString(i2);
                if (!StringUtil.isEmpty(string)) {
                    hashSet.add(string);
                }
            }
        }
        String configRules = getConfigRules();
        if (!StringUtil.isEmpty(configRules) && (parseArray = JDJSON.parseArray(configRules)) != null && parseArray.size() > 0) {
            for (int i3 = 0; i3 < parseArray.size(); i3++) {
                String string2 = parseArray.getString(i3);
                if (!StringUtil.isEmpty(string2)) {
                    hashSet.add(string2);
                }
            }
        }
        return hashSet;
    }

    private static String getTagFromSp(Context context) {
        if (context == null) {
            return "";
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG_SP, 0);
        String string = sharedPreferences.getString("start_event_param", "");
        if (!TextUtils.isEmpty(string)) {
            sharedPreferences.edit().putString("start_event_param", "").apply();
        }
        return string;
    }

    public static boolean isCpsUnionMatch(Context context, String str, String str2, boolean z, Bundle bundle, Bundle bundle2, String str3) {
        if (checkCpsUnionUrl(str)) {
            Bundle bundle3 = new Bundle();
            if (bundle2 != null) {
                bundle3.putAll(bundle2);
            }
            if (bundle != null && z) {
                bundle3.putAll(bundle);
                bundle3.remove("urlParamMap");
                bundle3.remove("urlAction");
            }
            bundle3.putString("url", str);
            if (!StringUtil.isEmpty(str2)) {
                bundle3.putString(MBaseKeyNames.KEY_REFERER, str2);
            }
            webToUnion(context, str3, bundle3);
            if (bundle != null && bundle3.containsKey(OpenAppConstant.KEY_CLOSEACTIVITY)) {
                bundle.putBoolean(OpenAppConstant.KEY_CLOSEACTIVITY, bundle3.getBoolean(OpenAppConstant.KEY_CLOSEACTIVITY, true));
            }
            return true;
        }
        return false;
    }

    private static boolean isUrlMtach(String str, String str2) {
        try {
            return Pattern.compile(str).matcher(str2).find();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void jumpUnion(Context context, Bundle bundle) {
        jumpUnion(context, bundle, true, 0, null);
    }

    public static void jumpUnionNoLoading(Context context, Bundle bundle) {
        jumpUnion(context, bundle, false, 0, null);
    }

    public static void setTagToSp(Context context, String str) {
        if (context == null) {
            return;
        }
        context.getSharedPreferences(TAG_SP, 0).edit().putString("start_event_param", str).apply();
    }

    public static void toUnion(Context context, Bundle bundle) {
        if (context == null) {
            return;
        }
        JDRouter.to("JDUnionModule", "jumpUnion").extraData(bundle).jump(context);
    }

    public static void webToUnion(Context context, String str, Bundle bundle) {
        if (context == null) {
            return;
        }
        addUnionWebMta(context, str, bundle);
        toUnion(context, bundle);
    }

    public static void jumpUnion(Context context, Bundle bundle, IJumpDispatchCallBack iJumpDispatchCallBack) {
        jumpUnion(context, bundle, true, 0, iJumpDispatchCallBack);
    }

    public static void jumpUnionNoLoading(Context context, Bundle bundle, IJumpDispatchCallBack iJumpDispatchCallBack) {
        jumpUnion(context, bundle, false, 0, iJumpDispatchCallBack);
    }

    public static void jumpUnion(Context context, Bundle bundle, int i2, IJumpDispatchCallBack iJumpDispatchCallBack) {
        jumpUnion(context, bundle, true, i2, iJumpDispatchCallBack);
    }

    public static void jumpUnionNoLoading(Context context, Bundle bundle, int i2, IJumpDispatchCallBack iJumpDispatchCallBack) {
        jumpUnion(context, bundle, false, i2, iJumpDispatchCallBack);
    }

    public static void jumpUnion(Context context, Bundle bundle, boolean z, int i2, IJumpDispatchCallBack iJumpDispatchCallBack) {
        if (bundle != null) {
            if (!bundle.containsKey(WebPerfManager.PAGE_NAME) && context != null && context.getClass() != null) {
                bundle.putString(WebPerfManager.PAGE_NAME, context.getClass().getName());
            }
            if (!bundle.containsKey("modeTag")) {
                bundle.putString("modeTag", getCurrentModeTag());
            }
            if (!bundle.containsKey("localConfig")) {
                bundle.putString("localConfig", BaseInfo.isRoot() ? "1" : "0");
            }
            if (bundle.containsKey("category") && bundle.containsKey("des")) {
                bundle.putString("openappDes", bundle.getString("des", ""));
            }
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString(VerifyTracker.KEY_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        if (bundle != null) {
            bundle2.putAll(bundle);
        } else {
            bundle2.putString("msg", "jumpUnion \u7684 Bundle \u4e3a\u7a7a");
        }
        JDMtaUtils.sendClickDataWithExt(context, "jingdongunionsdk_1626424295026|10", bundleToJson(bundle2), "", "", context != null ? context.getClass().getName() : "", "", "", getJsonParamFromBundle(bundle), null);
        JdUnionJumpHelper.jumpUnion(context, null, bundle, z, i2, iJumpDispatchCallBack);
    }
}
