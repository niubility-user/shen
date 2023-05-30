package com.jingdong.common.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Keep;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.phc.e;
import com.jingdong.common.deeplinkhelper.DeepLinkBabelHelper;
import com.jingdong.common.deeplinkhelper.DeepLinkCommuneHelper;
import com.jingdong.common.entity.FaxianEntry;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.common.utils.pay.PayUtils;
import com.jingdong.common.utils.security.JDUUIDEncHelper;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.ui.X5WebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.JDWebCookieHelper;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.utils.SDKUtils;
import com.jingdong.lib.monitor.a;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Keep
/* loaded from: classes6.dex */
public class WebViewHelper {
    private static final String APP_CACAHE_DIRNAME = "/jdappwebcache";
    private static final String JSHOP_URL_ADAPTER_DEFAULT = "[{\"name\":\"toInventoryDetail\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/h5\\\\.m\\\\.jd\\\\.com\\\\/active\\\\/faxian\\\\/inventory\\\\/index\\\\.html\\\\?(?:.+&)*id=(\\\\d+)\"}]},{\"name\":\"toCommentDetail\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/share\\\\.m\\\\.jd\\\\.com\\\\/shareOrder\\\\/showSharePage\\\\.action\\\\?(?:.+&)*productId=(\\\\d+)\"}]},{\"name\":\"toFaxianAuthor\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/h5\\\\.m\\\\.jd\\\\.com\\\\/active\\\\/faxian\\\\/html\\\\/public\\\\.html\\\\?(?:.+&)*authorId=(\\\\d+)\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/eco\\\\.m\\\\.jd\\\\.com\\\\/content\\\\/dr_home\\\\/index\\\\.html\\\\?(?:.+&)*authorId=(\\\\d+)\"}]},{\"name\":\"toVideoBuy\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/h5\\\\.m\\\\.jd\\\\.com\\\\/active\\\\/faxian\\\\/video\\\\/index\\\\.html\\\\?(?:.+&)*id=(\\\\d+)\"}]},{\"name\":\"toPinGou\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/wqs\\\\.jd\\\\.com\\\\/pingou\\\\/item\\\\.shtml\\\\?sku=(\\\\d+)*\"}]},{\"name\":\"toCouponCenter\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/coupon\\\\.m\\\\.jd\\\\.com\\\\/center\\\\/getCouponCenter\\\\.action.*\"}]},{\"name\":\"toHdxSpDetail\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/h5\\\\.m\\\\.jd\\\\.com\\\\/active\\\\/haodongxi\\\\/nice-goods\\\\.html\\\\?(?:.+&)*id=(\\\\d+)\"}]},{\"name\":\"toLivePlayerRoom\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/lives\\\\.jd\\\\.com\\\\/(\\\\d+)\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/lives\\\\.jd\\\\.com\\\\/#\\\\/(\\\\d+)\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/h5\\\\.m\\\\.jd\\\\.com\\\\/active\\\\/3Py5mBpfcJux1LmBJb2gCQB2XFb7\\\\/live\\\\.html\\\\?(?:.+&)*id=(\\\\d+)\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/h5\\\\.m\\\\.jd\\\\.com\\\\/active\\\\/4PWVRtMJhmRHkFqHzfP94mJT4qmu\\\\/live\\\\.html\\\\?(?:.+&)*id=(\\\\d+)\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/h5\\\\.m\\\\.jd\\\\.com\\\\/dev\\\\/3pbY8ZuCx4ML99uttZKLHC2QcAMn\\\\/live\\\\.html\\\\?(?:.+&)*id=(\\\\d+)\"}]},{\"name\":\"webp\",\"rules\":[{\"type\":\"webpSwitch\",\"useWebp\":1}]},{\"name\":\"toJdMiniProg\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"http(s?)://mini-app-static\\\\.jd\\\\.com\\\\/apps\\\\/mpshare\\\\/index\\\\.html\\b(?=.*(&|\\\\?)category=jump(&|\\b))(?=.*(&|\\\\?)des=jdmp(&|\\b))(?=.*(&|\\\\?)vapptype=[0-9](&|\\b))(?=.*(&|\\\\?)appId=[A-Za-z0-9]{6,40}(&|\\b)).*\"}]},{\"name\":\"toVideoBuyPortrait\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/h5\\\\.m\\\\.jd\\\\.com\\\\/active\\\\/faxian\\\\/video\\\\/indexShu\\\\.html.*\"}]},{\"name\":\"URLException\",\"rules\":[{\"type\":\"exception\",\"contain\":[\"un.m.jd.com/cgi-bin/app/appjmp\"]}]},{\"name\":\"toHdxZtList\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/h5\\\\.m\\\\.jd\\\\.com\\\\/active\\\\/haodongxi\\\\/subjects-list\\\\.html\\\\?(?:.+&)*id=(\\\\d+)\"}]},{\"name\":\"toProductList\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/so\\\\.m\\\\.jd\\\\.com\\\\/ware\\\\/search\\\\.action\\\\?(?:.+&)*keyword=(.*)\"}]},{\"name\":\"toMyJD\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/wqs\\\\.jd\\\\.com\\\\/my\\\\/indexv2\\\\.shtml\"}]},{\"name\":\"toCart\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/wqdeal\\\\.jd\\\\.com\\\\/deal\\\\/mshopcart\\\\/mycart\"}]},{\"name\":\"toHomePage\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/m\\\\.jd\\\\.com(?:/)?\\\\?isopen=1.*\"}]},{\"name\":\"toCashier\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/pay\\\\.m\\\\.jd\\\\.com\\\\/.*\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/paybeta\\\\.m\\\\.jd\\\\.com\\\\/.*\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/beta-cashier\\\\.m\\\\.jd\\\\.com.*\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/cashier\\\\.m\\\\.jd\\\\.com.*\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/beta-mpay\\\\.m\\\\.jd\\\\.com\\\\/.((?!(useM=1)).)*$\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/mpay\\\\.m\\\\.jd\\\\.com\\\\/.((?!(useM=1)).)*$\"}]},{\"name\":\"toSkyTower\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/pro(?:\\\\.m)?\\\\.jd\\\\.com\\\\/mall\\\\/active\\\\/([a-zA-Z0-9]+)\\\\/index\\\\.html\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/h5(?:\\\\.m)?\\\\.jd\\\\.com\\\\/rn\\\\/([a-zA-Z0-9]+)\\\\/index\\\\.html\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/(?:[a-z]+)-pro\\\\.m\\\\.jd\\\\.com\\\\/mall\\\\/active\\\\/([a-zA-Z0-9]+)\\\\/index\\\\.html\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/probeta\\\\.m\\\\.jd\\\\.com\\\\/([a-zA-Z0-9]+)\\\\/index\\\\.html\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/probeta\\\\.m\\\\.jd\\\\.com\\\\/mall\\\\/active\\\\/([a-zA-Z0-9]+)\\\\/index\\\\.html\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/beta-babel\\\\.jd\\\\.com\\\\/rn\\\\/preview\\\\/([a-zA-Z0-9]+)\\\\/index\\\\.html\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/prodev(?:\\\\.m)?\\\\.jd\\\\.com\\\\/mall\\\\/active\\\\/([a-zA-Z0-9]+)\\\\/index\\\\.html\"}]},{\"name\":\"toMall\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/ok\\\\.jd\\\\.com\\\\/m\\\\/index\\\\-(\\\\d+)\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/mall\\\\.jd\\\\.com\\\\/index\\\\-(\\\\d+)\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/shop\\\\.m\\\\.jd\\\\.com\\\\/\\\\?(?:.+&)*shopId=(\\\\d+)\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/shop\\\\.m\\\\.jd\\\\.com\\\\?(?:.+&)*shopId=(\\\\d+)\"}]},{\"name\":\"toCpsUnion\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/union[-|.]click\\\\.jd\\\\.com\\\\/.*\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/u\\\\.jd\\\\.com\\\\/.*\"}]},{\"name\":\"toWareDetail\",\"rules\":[{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/(?:item\\\\.m\\\\.jd\\\\.com\\\\/product|m\\\\.jd\\\\.com\\\\/product|item\\\\.jd\\\\.com)\\\\/(\\\\d+)\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/(?:item\\\\.)?m\\\\.jd\\\\.com\\\\/ware\\\\/view\\\\.action\\\\?(?:.+&)*wareId=(\\\\d+)\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/(?:mitem\\\\.jd\\\\.hk|m\\\\.yiyaojd\\\\.com)\\\\/product\\\\/(\\\\d+)\\\\.html\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/(?:mitem\\\\.jd\\\\.hk|m\\\\.yiyaojd\\\\.com)\\\\/ware\\\\/view\\\\.action\\\\?(?:.+&)*wareId=(\\\\d+)\"},{\"type\":\"regexString\",\"regexString\":\"^http(?:s)?:\\\\/\\\\/(?:wqitem\\\\.jd\\\\.com|wqs\\\\.jd\\\\.com|wq\\\\.jd\\\\.com|wqitem\\\\.jd\\\\.hk)\\\\/item\\\\/view\\\\?(?:.+&)*sku=(\\\\d+)\"}]}]";
    public static final String WEB_URL_FILTER_RULE = "WEB_URL_FILTER_RULE";
    private static final AtomicBoolean fetchedRule = new AtomicBoolean(false);
    public static boolean firstX5WebViewCreateFinished;
    private static WebViewHelper instance;
    public ArrayList<UrlFilterRule> bableRules;
    public ArrayList<UrlFilterRule> commentDetailRules;
    public ArrayList<UrlFilterRule> cpsUnionRules;
    public ArrayList<UrlFilterRule> enjoyBuyRules;
    public ArrayList<UrlFilterRule> faxianArticleRules;
    public ArrayList<UrlFilterRule> faxianAuthorRules;
    public ArrayList<UrlFilterRule> inventoryDetailRules;
    public ArrayList<UrlFilterRule> jdCouponRules;
    public ArrayList<UrlFilterRule> jdPayRules;
    public ArrayList<UrlFilterRule> jshopRules;
    public ArrayList<UrlFilterRule> livePlayerRoomRules;
    public ArrayList<UrlFilterRule> miniProgUrlRule;
    public ArrayList<UrlFilterRule> myCartRules;
    public ArrayList<UrlFilterRule> myJDRules;
    public ArrayList<UrlFilterRule> openHomeRule;
    public ArrayList<UrlFilterRule> pingouUrlRule;
    public ArrayList<UrlFilterRule> productDetailRules;
    public ArrayList<UrlFilterRule> productListRules;
    private boolean showX5TipOnce;
    public ArrayList<UrlFilterRule> singleProductDetailRules;
    public ArrayList<UrlFilterRule> singleTopicDetailRules;
    public ArrayList<UrlFilterRule> videoBuyPortraitRules;
    public ArrayList<UrlFilterRule> videoBuyRules;

    /* loaded from: classes6.dex */
    public static class UrlFilterRule {
        public String type = "";
        public String startsWith = "";
        public ArrayList<String> contain = new ArrayList<>();
        public String wareIdKey = "";
        public String regexString = "";
    }

    private WebViewHelper() {
    }

    public static void changeJdUaForDarkMode(X5WebView x5WebView, int i2) {
        String userAgentString;
        if (x5WebView == null) {
            return;
        }
        WebSettings settings = x5WebView.getSettings();
        try {
            userAgentString = URLDecoder.decode(settings.getUserAgentString(), "utf-8");
        } catch (Exception unused) {
            userAgentString = settings.getUserAgentString();
        }
        String updateJdUaKV = updateJdUaKV(userAgentString, "jdSupportDarkMode", String.valueOf(i2));
        if (updateJdUaKV != null) {
            settings.setUserAgentString(updateJdUaKV);
        }
    }

    @TargetApi(11)
    public static void clearBugJs(WebView webView) {
        if (SDKUtils.getSDKVersion() >= 11) {
            try {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
                return;
            } catch (Throwable unused) {
            }
        }
        try {
            Method method = webView.getClass().getMethod("removeJavascriptInterface", String.class);
            if (method != null) {
                method.invoke(webView, "searchBoxJavaBridge_");
                method.invoke(webView, "accessibility");
                method.invoke(webView, "accessibilityTraversal");
            }
        } catch (Throwable unused2) {
        }
    }

    public static void clearWebViewCache(Context context) {
        try {
            context.deleteDatabase("webview.db");
            context.deleteDatabase("webviewCache.db");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        File file = new File(context.getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME);
        if (context.getCacheDir() != null) {
            File file2 = new File(context.getCacheDir().getAbsolutePath() + "/webviewCache");
            try {
                if (file2.exists()) {
                    context.deleteFile(file2.getName());
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        try {
            context.deleteDatabase("webview.db");
            context.deleteDatabase("webviewCache.db");
            if (file.exists()) {
                context.deleteFile(file.getName());
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    public static void customiseUA(X5WebView x5WebView, String str) {
        if (x5WebView == null || TextUtils.isEmpty(str)) {
            return;
        }
        StringBuffer jdUa = getJdUa(false);
        String extraUaString = x5WebView.getExtraUaString();
        if (!TextUtils.isEmpty(extraUaString)) {
            jdUa.append(extraUaString);
        }
        jdUa.append(str);
        jdUa.append(x5WebView.getOrgUserAgent());
        x5WebView.getSettings().setUserAgentString(jdUa.toString());
    }

    public static void disablePlatformNotifications() {
        try {
            Class.forName(WebView.class.getName()).getDeclaredMethod("disablePlatformNotifications", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void disableWebViewCache(WebView webView) {
        if (webView == null || webView.getSettings() == null) {
            return;
        }
        webView.getSettings().setAppCacheEnabled(false);
    }

    public static void enablePlatformNotifications() {
        try {
            Class.forName(WebView.class.getName()).getDeclaredMethod("enablePlatformNotifications", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void enableWebViewCache(WebView webView, Context context) {
        if (webView == null || webView.getSettings() == null) {
            return;
        }
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setAppCacheEnabled(false);
        try {
            String str = context.getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME;
            webView.getSettings().setDatabasePath(str);
            webView.getSettings().setAppCachePath(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static String filterParam(String str, String str2) {
        return !TextUtils.isEmpty(str) ? str : str2;
    }

    public static String getBabelActivityId(String str) {
        if (getInstance().bableRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), DeepLinkBabelHelper.HOST_BABEL_ACTIVITY);
        }
        if (getInstance().bableRules == null) {
            return "";
        }
        for (int i2 = 0; i2 < getInstance().bableRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().bableRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString)) {
                String matchGroup = getMatchGroup(urlFilterRule.regexString, str);
                if (!TextUtils.isEmpty(matchGroup)) {
                    return matchGroup;
                }
            }
        }
        return "";
    }

    public static Bundle getBundleFromUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Uri parse = Uri.parse(str);
            if (parse == null) {
                return null;
            }
            try {
                Set<String> queryParameterNames = parse.getQueryParameterNames();
                if (queryParameterNames != null && queryParameterNames.size() > 0) {
                    Bundle bundle = new Bundle();
                    for (String str2 : queryParameterNames) {
                        bundle.putString(str2, parse.getQueryParameter(str2));
                    }
                    bundle.putString("webUrl", str);
                    return bundle;
                }
                return null;
            } catch (Exception e2) {
                e2.printStackTrace();
                ExceptionReporter.reportWebViewCommonError("WebViewHelper->getBundleFromUrlError", str, e2.getMessage(), "webview\u5e2e\u52a9\u7c7b\u89e3\u6790url\u5f02\u5e38");
                return null;
            }
        } catch (Exception unused) {
        }
    }

    public static String[] getCommentDetailInfo(String str) {
        if (getInstance().commentDetailRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), JumpUtil.VALUE_DES_COMMENT_DETAIL);
        }
        if (getInstance().commentDetailRules == null) {
            return null;
        }
        for (int i2 = 0; i2 < getInstance().commentDetailRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().commentDetailRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString) && !TextUtils.isEmpty(getMatchGroup(urlFilterRule.regexString, str))) {
                Uri parse = Uri.parse(str);
                String queryParameter = parse.getQueryParameter("productId");
                String queryParameter2 = parse.getQueryParameter(DeepLinkCommuneHelper.COMMENT_ID);
                if (!TextUtils.isEmpty(queryParameter) && !TextUtils.isEmpty(queryParameter2)) {
                    return new String[]{queryParameter, queryParameter2};
                }
            }
        }
        return null;
    }

    private static String getEP() {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("sv", BaseInfo.getAndroidVersion());
            hashMap.put("ud", StatisticsReportUtil.readDeviceUUID());
            hashMap.put("ad", BaseInfo.getAndroidId());
            hashMap.put("ov", String.valueOf(Build.VERSION.SDK_INT));
            hashMap.put("od", BaseInfo.getOAID());
            return Uri.encode(e.c(JdSdk.getInstance().getApplication()).b(hashMap, e.b.MODIFIED_BASE64));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static String getFaxianArticleInfo(String str) {
        if (getInstance().faxianArticleRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "faxianArticle");
        }
        if (getInstance().faxianArticleRules == null) {
            return null;
        }
        for (int i2 = 0; i2 < getInstance().faxianArticleRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().faxianArticleRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString)) {
                String matchGroup = getMatchGroup(urlFilterRule.regexString, str);
                if (!TextUtils.isEmpty(matchGroup)) {
                    return matchGroup;
                }
            }
        }
        return null;
    }

    public static String getFaxianAuthorId(String str) {
        if (getInstance().faxianAuthorRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "faxianAuthor");
        }
        if (getInstance().faxianAuthorRules == null) {
            return "";
        }
        for (int i2 = 0; i2 < getInstance().faxianAuthorRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().faxianAuthorRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString)) {
                String matchGroup = getMatchGroup(urlFilterRule.regexString, str);
                if (!TextUtils.isEmpty(matchGroup)) {
                    return matchGroup;
                }
            }
        }
        return "";
    }

    public static WebViewHelper getInstance() {
        if (instance == null) {
            synchronized (WebViewHelper.class) {
                if (instance == null) {
                    instance = new WebViewHelper();
                }
            }
        }
        return instance;
    }

    public static String getInventoryId(String str) {
        if (getInstance().inventoryDetailRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), FaxianEntry.JD_ENTRY_INVENTORY);
        }
        if (getInstance().inventoryDetailRules == null) {
            return "";
        }
        for (int i2 = 0; i2 < getInstance().inventoryDetailRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().inventoryDetailRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString)) {
                String matchGroup = getMatchGroup(urlFilterRule.regexString, str);
                if (!TextUtils.isEmpty(matchGroup)) {
                    return matchGroup;
                }
            }
        }
        return "";
    }

    public static StringBuffer getJdUa(boolean z) {
        if (!z && SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.UAENCRYPT, false)) {
            return getJdUaInfo2();
        }
        return getJdUaInfoEncryptUuidWithoutMta();
    }

    public static StringBuffer getJdUaInfo2() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("jdapp;android;");
        stringBuffer.append(BaseInfo.getAppVersionName());
        stringBuffer.append(";;;");
        if (SwitchQueryFetcher.getSwitchBooleanValue("wvUaVerAdapt", false)) {
            stringBuffer.append("M/5.0;");
        }
        if (BaseInfo.getAppVersionCode() == 0) {
            stringBuffer.append("appBuild/");
        } else {
            stringBuffer.append("appBuild/" + BaseInfo.getAppVersionCode());
        }
        stringBuffer.append(";");
        stringBuffer.append("ef/1;ep/");
        stringBuffer.append(getEP());
        stringBuffer.append(";");
        return stringBuffer;
    }

    @Deprecated
    public static StringBuffer getJdUaInfoEncryptUuid() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("jdapp;android;");
        stringBuffer.append(BaseInfo.getAppVersionName());
        stringBuffer.append(";");
        stringBuffer.append(BaseInfo.getAndroidVersion());
        stringBuffer.append(";");
        JDUUIDEncHelper.EncryptResult encrypt = JDUUIDEncHelper.encrypt(StatisticsReportUtil.readDeviceUUID());
        String str = "-";
        if (encrypt != null) {
            str = encrypt.eu + "-" + encrypt.fv;
        }
        stringBuffer.append(str);
        stringBuffer.append(";");
        stringBuffer.append("network/" + NetUtils.getNetworkType());
        stringBuffer.append(";");
        stringBuffer.append("model/");
        stringBuffer.append(BaseInfo.getDeviceModel());
        stringBuffer.append(";");
        stringBuffer.append("addressid/");
        if (WebUtils.getAddressGlobalWithBaseSceneId() != null) {
            stringBuffer.append(WebUtils.getAddressGlobalWithBaseSceneId().getId());
        }
        stringBuffer.append(";");
        stringBuffer.append("aid/" + BaseInfo.getAndroidId());
        stringBuffer.append(";");
        stringBuffer.append("oaid/" + BaseInfo.getOAID());
        stringBuffer.append(";");
        stringBuffer.append("osVer/" + BaseInfo.getAndroidSDKVersion());
        stringBuffer.append(";");
        if (BaseInfo.getAppVersionCode() == 0) {
            stringBuffer.append("appBuild/");
        } else {
            stringBuffer.append("appBuild/" + BaseInfo.getAppVersionCode());
        }
        stringBuffer.append(";");
        stringBuffer.append(JDMtaUtils.getSessionInfo());
        stringBuffer.append("partner/" + Configuration.getProperty(Configuration.PARTNER));
        stringBuffer.append(";");
        stringBuffer.append("apprpd/" + JDMtaUtils.oldClassName);
        stringBuffer.append(";");
        stringBuffer.append("eufv/1;");
        return stringBuffer;
    }

    @Deprecated
    public static StringBuffer getJdUaInfoEncryptUuidWithoutMta() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("jdapp;android;");
        stringBuffer.append(BaseInfo.getAppVersionName());
        stringBuffer.append(";");
        stringBuffer.append(BaseInfo.getAndroidVersion());
        stringBuffer.append(";");
        JDUUIDEncHelper.EncryptResult encrypt = JDUUIDEncHelper.encrypt(StatisticsReportUtil.readDeviceUUID());
        String str = "-";
        if (encrypt != null) {
            str = encrypt.eu + "-" + encrypt.fv;
        }
        stringBuffer.append(str);
        stringBuffer.append(";");
        if (SwitchQueryFetcher.getSwitchBooleanValue("wvUaVerAdapt", false)) {
            stringBuffer.append("M/5.0;");
        }
        stringBuffer.append("model/");
        stringBuffer.append(BaseInfo.getDeviceModel());
        stringBuffer.append(";");
        stringBuffer.append("addressid/");
        if (WebUtils.getAddressGlobalWithBaseSceneId() != null) {
            stringBuffer.append(WebUtils.getAddressGlobalWithBaseSceneId().getId());
        }
        stringBuffer.append(";");
        stringBuffer.append("aid/" + BaseInfo.getAndroidId());
        stringBuffer.append(";");
        stringBuffer.append("oaid/" + BaseInfo.getOAID());
        stringBuffer.append(";");
        stringBuffer.append("osVer/" + Build.VERSION.SDK_INT);
        stringBuffer.append(";");
        if (BaseInfo.getAppVersionCode() == 0) {
            stringBuffer.append("appBuild/");
        } else {
            stringBuffer.append("appBuild/" + BaseInfo.getAppVersionCode());
        }
        stringBuffer.append(";");
        stringBuffer.append("partner/" + Configuration.getProperty(Configuration.PARTNER));
        stringBuffer.append(";");
        stringBuffer.append("eufv/1;");
        return stringBuffer;
    }

    @Deprecated
    public static StringBuffer getJdUaInfoWithoutMta() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("jdapp;android;");
        stringBuffer.append(BaseInfo.getAppVersionName());
        stringBuffer.append(";");
        stringBuffer.append(BaseInfo.getAndroidVersion());
        stringBuffer.append(";");
        stringBuffer.append(StatisticsReportUtil.readDeviceUUID());
        stringBuffer.append(";");
        stringBuffer.append("network/" + NetUtils.getNetworkType());
        stringBuffer.append(";");
        stringBuffer.append("model/");
        stringBuffer.append(BaseInfo.getDeviceModel());
        stringBuffer.append(";");
        stringBuffer.append("addressid/");
        if (WebUtils.getAddressGlobalWithBaseSceneId() != null) {
            stringBuffer.append(WebUtils.getAddressGlobalWithBaseSceneId().getId());
        }
        stringBuffer.append(";");
        stringBuffer.append("aid/" + BaseInfo.getAndroidId());
        stringBuffer.append(";");
        stringBuffer.append("oaid/" + BaseInfo.getOAID());
        stringBuffer.append(";");
        stringBuffer.append("osVer/" + BaseInfo.getAndroidSDKVersion());
        stringBuffer.append(";");
        if (BaseInfo.getAppVersionCode() == 0) {
            stringBuffer.append("appBuild/");
        } else {
            stringBuffer.append("appBuild/" + BaseInfo.getAppVersionCode());
        }
        stringBuffer.append(";");
        stringBuffer.append("partner/" + Configuration.getProperty(Configuration.PARTNER));
        stringBuffer.append(";");
        return stringBuffer;
    }

    public static String getLivePlayerRoomInfo(String str) {
        if (getInstance().livePlayerRoomRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "liveplayerroom");
        }
        if (getInstance().livePlayerRoomRules == null) {
            return null;
        }
        for (int i2 = 0; i2 < getInstance().livePlayerRoomRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().livePlayerRoomRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString)) {
                String matchGroup = getMatchGroup(urlFilterRule.regexString, str);
                if (!TextUtils.isEmpty(matchGroup)) {
                    return matchGroup;
                }
            }
        }
        return null;
    }

    private static String getMatchGroup(String str, String str2) {
        try {
            Matcher matcher = Pattern.compile(str).matcher(str2);
            return matcher.find() ? matcher.group(1) : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static int getMtaStartPosition(String str, int i2, String str2) {
        int i3 = 0;
        int i4 = 0;
        String str3 = str;
        while (str3.indexOf(str2) != -1) {
            i3++;
            i4 += str3.indexOf(str2) + 1;
            str3 = str3.substring(str.indexOf(";") + 1);
            if (i3 == i2) {
                break;
            }
        }
        return i4;
    }

    public static String getOrgUserAgent(String str) {
        int indexOf;
        return (!SwitchQueryFetcher.getSwitchBooleanValue("FixRepeatAppendUA", false) || TextUtils.isEmpty(str) || (indexOf = str.trim().indexOf("Mozilla/")) <= 0) ? str : str.trim().substring(indexOf);
    }

    public static String getPinGouId(String str) {
        if (!SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.WEB_PRODUCTDETAIL_GRAY, false)) {
            if (getInstance().pingouUrlRule == null) {
                parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "sku");
            }
            if (getInstance().pingouUrlRule == null) {
                return "";
            }
            for (int i2 = 0; i2 < getInstance().pingouUrlRule.size(); i2++) {
                UrlFilterRule urlFilterRule = getInstance().pingouUrlRule.get(i2);
                if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString)) {
                    String matchGroup = getMatchGroup(urlFilterRule.regexString, str);
                    if (!TextUtils.isEmpty(matchGroup)) {
                        return matchGroup;
                    }
                }
            }
        }
        return "";
    }

    public static String getSearchResultInfo(String str) {
        if (getInstance().productListRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "searchresult");
        }
        if (getInstance().productListRules == null) {
            return null;
        }
        for (int i2 = 0; i2 < getInstance().productListRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().productListRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString)) {
                String matchGroup = getMatchGroup(urlFilterRule.regexString, str);
                if (!TextUtils.isEmpty(matchGroup)) {
                    return matchGroup;
                }
            }
        }
        return null;
    }

    public static String getSessionInfoWithRegex(String str, String str2) {
        if (!str.isEmpty() && !str2.isEmpty()) {
            Matcher matcher = Pattern.compile(";" + str2 + "/(.*?);").matcher(str);
            if (matcher.find() && matcher.group(1) != null) {
                return matcher.group(1);
            }
        }
        return "";
    }

    public static String getShopId(String str) {
        if (getInstance().jshopRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), ThemeTitleConstant.TITLE_SHOP_DRAWABLE_ID);
        }
        if (getInstance().jshopRules == null) {
            return "";
        }
        for (int i2 = 0; i2 < getInstance().jshopRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().jshopRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString)) {
                String matchGroup = getMatchGroup(urlFilterRule.regexString, str);
                if (!TextUtils.isEmpty(matchGroup)) {
                    return matchGroup;
                }
            }
        }
        return "";
    }

    public static String getSkuId(String str) {
        if (getInstance().productDetailRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "sku");
        }
        if (getInstance().productDetailRules == null) {
            return "";
        }
        for (int i2 = 0; i2 < getInstance().productDetailRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().productDetailRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString)) {
                String matchGroup = getMatchGroup(urlFilterRule.regexString, str);
                if (!TextUtils.isEmpty(matchGroup)) {
                    return matchGroup;
                }
            }
        }
        return "";
    }

    public static void getUrlFilterRule() {
        if (fetchedRule.getAndSet(true)) {
            if (OKLog.D) {
                OKLog.d("WebViewHelper", "JShopUrlAdapter rule fetched before, skip this time.");
                return;
            }
            return;
        }
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("jshopAdapter", JSHOP_URL_ADAPTER_DEFAULT);
        if (TextUtils.isEmpty(switchStringValue)) {
            return;
        }
        CommonBase.getJdSharedPreferences().edit().putString(WEB_URL_FILTER_RULE, switchStringValue).commit();
        parseRule(switchStringValue, "jshopUrlAdapter");
    }

    public static String getVideoBuyInfo(String str) {
        if (getInstance().videoBuyRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "videobuy");
        }
        if (getInstance().videoBuyRules == null) {
            return null;
        }
        for (int i2 = 0; i2 < getInstance().videoBuyRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().videoBuyRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString)) {
                String matchGroup = getMatchGroup(urlFilterRule.regexString, str);
                if (!TextUtils.isEmpty(matchGroup)) {
                    return matchGroup;
                }
            }
        }
        return null;
    }

    public static Map<String, String> getWebViewJankInfo() {
        if (SwitchQueryFetcher.getSwitchBooleanValue("reportWebViewJank", false)) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("preGentokenType", String.valueOf(JDWebCookieHelper.preGentokenType));
            linkedHashMap.put("x5SdkInited", X5InitUtil.isInitFinished() ? "1" : "0");
            linkedHashMap.put("x5ViewCreated", firstX5WebViewCreateFinished ? "1" : "0");
            try {
                linkedHashMap.put("WebViewCreated", WebView.mWebViewCreated ? "1" : "0");
            } catch (Exception unused) {
            }
            linkedHashMap.put("x5CoreVer", String.valueOf(a.b().getInt("tbsCoreVersion", 0)));
            linkedHashMap.put("x5CoreUsing", a.b().getBoolean("userX5Core", false) ? "1" : "0");
            return linkedHashMap;
        }
        return null;
    }

    public static void initJdUaForDarkMode(X5WebView x5WebView, int i2, boolean z) {
        if (x5WebView == null) {
            return;
        }
        x5WebView.setExtraUaString("jdSupportDarkMode/" + i2 + ";");
        initUA(x5WebView, z);
    }

    public static void initUA(WebView webView) {
        initUA(webView, false);
    }

    @Deprecated
    public static void initUserAgent(WebView webView) {
        if (webView == null) {
            return;
        }
        StringBuffer jdUaInfoEncryptUuid = getJdUaInfoEncryptUuid();
        if (webView instanceof X5WebView) {
            X5WebView x5WebView = (X5WebView) webView;
            String extraUaString = x5WebView.getExtraUaString();
            if (!TextUtils.isEmpty(extraUaString)) {
                jdUaInfoEncryptUuid.append(extraUaString);
            }
            jdUaInfoEncryptUuid.append(x5WebView.getOrgUserAgent());
        } else {
            jdUaInfoEncryptUuid.append(getOrgUserAgent(webView.getSettings().getUserAgentString()));
        }
        webView.getSettings().setUserAgentString(jdUaInfoEncryptUuid.toString());
    }

    public static boolean isBuyVideoProtrait(String str) {
        if (getInstance().videoBuyPortraitRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "videobuyportrait");
        }
        if (getInstance().videoBuyPortraitRules == null) {
            return false;
        }
        Iterator<UrlFilterRule> it = getInstance().videoBuyPortraitRules.iterator();
        while (it.hasNext()) {
            UrlFilterRule next = it.next();
            if ("regexString".equals(next.type) && !TextUtils.isEmpty(next.regexString) && isUrlMtach(next.regexString, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCpsUnionMatch(String str) {
        if (getInstance().cpsUnionRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "cpsUnion");
        }
        if (getInstance().cpsUnionRules == null) {
            return false;
        }
        for (int i2 = 0; i2 < getInstance().cpsUnionRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().cpsUnionRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString) && isUrlMtach(urlFilterRule.regexString, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEnjoyBuyMatch(String str) {
        if (getInstance().enjoyBuyRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "enjoyBuy");
        }
        if (getInstance().enjoyBuyRules == null) {
            return false;
        }
        Iterator<UrlFilterRule> it = getInstance().enjoyBuyRules.iterator();
        while (it.hasNext()) {
            UrlFilterRule next = it.next();
            if ("regexString".equals(next.type) && !TextUtils.isEmpty(next.regexString) && isUrlMtach(next.regexString, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isJDCouponMatch(String str) {
        if (getInstance().jdCouponRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "jdCoupon");
        }
        if (getInstance().jdCouponRules == null) {
            return false;
        }
        for (int i2 = 0; i2 < getInstance().jdCouponRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().jdCouponRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString) && isUrlMtach(urlFilterRule.regexString, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isJdPayMatch(Activity activity, String str) {
        try {
            return isJdPayMatch(activity, str, "4");
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isMiniProgUrl(String str) {
        if (getInstance().miniProgUrlRule == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "toJdMiniProg");
        }
        if (getInstance().miniProgUrlRule == null) {
            return false;
        }
        Iterator<UrlFilterRule> it = getInstance().miniProgUrlRule.iterator();
        while (it.hasNext()) {
            UrlFilterRule next = it.next();
            if ("regexString".equals(next.type) && !TextUtils.isEmpty(next.regexString) && isUrlMtach(next.regexString, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMyCartMatch(String str) {
        if (getInstance().myCartRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "mycart");
        }
        if (getInstance().myCartRules == null) {
            return false;
        }
        for (int i2 = 0; i2 < getInstance().myCartRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().myCartRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString) && isUrlMtach(urlFilterRule.regexString, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMyJDMatch(String str) {
        if (getInstance().myJDRules == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "myjd");
        }
        if (getInstance().myJDRules == null) {
            return false;
        }
        for (int i2 = 0; i2 < getInstance().myJDRules.size(); i2++) {
            UrlFilterRule urlFilterRule = getInstance().myJDRules.get(i2);
            if ("regexString".equals(urlFilterRule.type) && !TextUtils.isEmpty(urlFilterRule.regexString) && isUrlMtach(urlFilterRule.regexString, str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNumber(String str) {
        return Pattern.compile("[0-9]{1,}").matcher(str).matches();
    }

    public static boolean isOpenHomeUrl(String str) {
        if (getInstance().openHomeRule == null) {
            parseRule(CommonBase.getJdSharedPreferences().getString(WEB_URL_FILTER_RULE, ""), "toHomePage");
        }
        if (getInstance().openHomeRule == null) {
            return false;
        }
        Iterator<UrlFilterRule> it = getInstance().openHomeRule.iterator();
        while (it.hasNext()) {
            UrlFilterRule next = it.next();
            if ("regexString".equals(next.type) && !TextUtils.isEmpty(next.regexString) && isUrlMtach(next.regexString, str)) {
                return true;
            }
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

    public static boolean isXTime() {
        return false;
    }

    public static boolean needShowX5Tip() {
        return !getInstance().showX5TipOnce;
    }

    public static boolean onBackControl(IWebUiBinder iWebUiBinder, int i2) {
        if (iWebUiBinder == null) {
            return false;
        }
        WebEntity webEntity = iWebUiBinder.getWebEntity();
        JsBridgeEntity jsBridgeEntity = webEntity != null ? webEntity.jsBridgeEntity : null;
        JDWebView jdWebView = iWebUiBinder.getJdWebView();
        int i3 = jsBridgeEntity != null ? jsBridgeEntity.canControlBackByWeb : 0;
        if (i3 == 0 || !((i2 == 1 || (i2 == 2 && i3 == 2)) && WebUtils.isLegalUrlToControlBack(iWebUiBinder) && jdWebView != null)) {
            return false;
        }
        jdWebView.injectJs("javascript:jdWebviewClickBackCallBack && jdWebviewClickBackCallBack();");
        return true;
    }

    public static boolean onKeyBackControl(IWebUiBinder iWebUiBinder) {
        return onBackControl(iWebUiBinder, 2);
    }

    public static boolean onTitleNaviBackControl(IWebUiBinder iWebUiBinder) {
        return onBackControl(iWebUiBinder, 1);
    }

    private static void parseRule(String str, String str2) {
        try {
            JDJSONArray parseArray = JDJSON.parseArray(str);
            if (parseArray != null) {
                for (int i2 = 0; i2 < parseArray.size(); i2++) {
                    JDJSONObject jSONObject = parseArray.getJSONObject(i2);
                    String optString = jSONObject.optString("name");
                    char c2 = '\uffff';
                    switch (optString.hashCode()) {
                        case -2096717137:
                            if (optString.equals("toHdxSpDetail")) {
                                c2 = 4;
                                break;
                            }
                            break;
                        case -1873119981:
                            if (optString.equals("toPinGou")) {
                                c2 = 20;
                                break;
                            }
                            break;
                        case -1736569341:
                            if (optString.equals("toSkyTower")) {
                                c2 = 2;
                                break;
                            }
                            break;
                        case -1538441887:
                            if (optString.equals("toVideoBuyPortrait")) {
                                c2 = 11;
                                break;
                            }
                            break;
                        case -1280299822:
                            if (optString.equals("toInventoryDetail")) {
                                c2 = 6;
                                break;
                            }
                            break;
                        case -1108229176:
                            if (optString.equals("toCashier")) {
                                c2 = 16;
                                break;
                            }
                            break;
                        case -869408565:
                            if (optString.equals("toBean")) {
                                c2 = 18;
                                break;
                            }
                            break;
                        case -869382085:
                            if (optString.equals("toCart")) {
                                c2 = '\r';
                                break;
                            }
                            break;
                        case -869084369:
                            if (optString.equals("toMall")) {
                                c2 = 0;
                                break;
                            }
                            break;
                        case -869062399:
                            if (optString.equals("toMyJD")) {
                                c2 = '\f';
                                break;
                            }
                            break;
                        case -833753630:
                            if (optString.equals("toFaxianArticle")) {
                                c2 = '\t';
                                break;
                            }
                            break;
                        case -646557175:
                            if (optString.equals("toWareDetail")) {
                                c2 = 1;
                                break;
                            }
                            break;
                        case -506393687:
                            if (optString.equals("toHomePage")) {
                                c2 = 19;
                                break;
                            }
                            break;
                        case 380929241:
                            if (optString.equals("toHdxZtList")) {
                                c2 = 3;
                                break;
                            }
                            break;
                        case 387964470:
                            if (optString.equals("toCouponCenter")) {
                                c2 = 17;
                                break;
                            }
                            break;
                        case 414893156:
                            if (optString.equals("toCpsUnion")) {
                                c2 = 15;
                                break;
                            }
                            break;
                        case 668611359:
                            if (optString.equals("toFaxianAuthor")) {
                                c2 = 5;
                                break;
                            }
                            break;
                        case 1131291558:
                            if (optString.equals("toVideoBuy")) {
                                c2 = '\n';
                                break;
                            }
                            break;
                        case 1181359238:
                            if (optString.equals("toJdMiniProg")) {
                                c2 = 21;
                                break;
                            }
                            break;
                        case 1269494979:
                            if (optString.equals("toLivePlayerRoom")) {
                                c2 = 7;
                                break;
                            }
                            break;
                        case 1701851349:
                            if (optString.equals("toCommentDetail")) {
                                c2 = '\b';
                                break;
                            }
                            break;
                        case 1944268978:
                            if (optString.equals("toProductList")) {
                                c2 = 14;
                                break;
                            }
                            break;
                    }
                    switch (c2) {
                        case 0:
                            getInstance().jshopRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 1:
                            getInstance().productDetailRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 2:
                            getInstance().bableRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 3:
                            getInstance().singleTopicDetailRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 4:
                            getInstance().singleProductDetailRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 5:
                            getInstance().faxianAuthorRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 6:
                            getInstance().inventoryDetailRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 7:
                            getInstance().livePlayerRoomRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case '\b':
                            getInstance().commentDetailRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case '\t':
                            getInstance().faxianArticleRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case '\n':
                            getInstance().videoBuyRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 11:
                            getInstance().videoBuyPortraitRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case '\f':
                            getInstance().myJDRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case '\r':
                            getInstance().myCartRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 14:
                            getInstance().productListRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 15:
                            getInstance().cpsUnionRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 16:
                            getInstance().jdPayRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 17:
                            getInstance().jdCouponRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 18:
                            getInstance().enjoyBuyRules = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 19:
                            getInstance().openHomeRule = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 20:
                            getInstance().pingouUrlRule = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                        case 21:
                            getInstance().miniProgUrlRule = parseUrlFilterRule(jSONObject.optJSONArray("rules"));
                            break;
                    }
                }
                return;
            }
            if (OKLog.D) {
                OKLog.e("WebViewHelper", "parseRule rule=null: " + str);
            }
            ExceptionReporter.reportWebViewCommonError("webViewParseRuleError", "", "rule: " + str + "     source: " + str2, "WebViewHelper parseRule  null==resultJson");
        } catch (Exception e2) {
            e2.printStackTrace();
            ExceptionReporter.reportWebViewCommonError("webViewParseRuleError", "", "rule: " + str + "     source: " + str2, e2.toString());
        }
    }

    private static ArrayList<UrlFilterRule> parseUrlFilterRule(JDJSONArray jDJSONArray) {
        if (jDJSONArray != null && jDJSONArray.size() != 0) {
            ArrayList<UrlFilterRule> arrayList = new ArrayList<>();
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                try {
                    JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
                    UrlFilterRule urlFilterRule = new UrlFilterRule();
                    String optString = jSONObject.optString("type");
                    urlFilterRule.type = optString;
                    if ("startsWith".equals(optString)) {
                        urlFilterRule.startsWith = jSONObject.optString("startsWith");
                        urlFilterRule.wareIdKey = jSONObject.optString("wareIdKey");
                    } else if ("startsWithAndContain".equals(urlFilterRule.type)) {
                        urlFilterRule.startsWith = jSONObject.optString("startsWith");
                        urlFilterRule.wareIdKey = jSONObject.optString("wareIdKey");
                        JDJSONArray optJSONArray = jSONObject.optJSONArray("contain");
                        for (int i3 = 0; i3 < optJSONArray.size(); i3++) {
                            urlFilterRule.contain.add(optJSONArray.optString(i3));
                        }
                    } else if ("regexString".equals(urlFilterRule.type)) {
                        urlFilterRule.regexString = jSONObject.optString("regexString");
                    }
                    arrayList.add(urlFilterRule);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return arrayList;
        }
        return new ArrayList<>();
    }

    public static void saveX5WebViewBasicInfo(WebView webView) {
        if (webView == null) {
            return;
        }
        int tbsSDKVersion = WebView.getTbsSDKVersion(webView.getContext());
        int tbsCoreVersion = WebView.getTbsCoreVersion(webView.getContext());
        boolean z = webView.getX5WebViewExtension() != null;
        if (Log.D) {
            Log.d("WebViewHelper", "saveX5WebViewBasicInfo : tbsSdkVersion: " + tbsSDKVersion + "  |tbsCoreVersion: " + tbsCoreVersion + "  |userX5Core: " + z);
        }
        SharedPreferences.Editor a = a.a();
        a.putBoolean("userX5Core", z);
        a.putInt("tbsSdkVersion", tbsSDKVersion);
        a.putInt("tbsCoreVersion", tbsCoreVersion);
        a.apply();
    }

    public static void showedX5Tip() {
        getInstance().showX5TipOnce = true;
    }

    private static String updateJdUaKV(String str, String str2, String str3) {
        if (str != null) {
            return str.replaceFirst(";" + str2 + "/(.*?);", ";" + str2 + "/" + str3 + ";");
        }
        return str;
    }

    private static String updateMtaKV(String str, String str2, String str3) {
        return str.replaceFirst(";" + str2 + "/(.*?);", ";" + str2 + "/" + str3 + ";");
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void updateMtaSeq(com.tencent.smtt.sdk.WebView r8) {
        /*
            java.lang.String r0 = "jdv"
            java.lang.String r1 = "psq"
            java.lang.String r2 = "pv"
            java.lang.String r3 = ""
            java.lang.String r4 = com.jingdong.jdsdk.mta.JDMtaUtils.getSessionInfo()
            java.lang.String r5 = getSessionInfoWithRegex(r4, r2)     // Catch: java.lang.Exception -> L1e
            java.lang.String r6 = getSessionInfoWithRegex(r4, r1)     // Catch: java.lang.Exception -> L1b
            java.lang.String r3 = getSessionInfoWithRegex(r4, r0)     // Catch: java.lang.Exception -> L19
            goto L24
        L19:
            r4 = move-exception
            goto L21
        L1b:
            r4 = move-exception
            r6 = r3
            goto L21
        L1e:
            r4 = move-exception
            r5 = r3
            r6 = r5
        L21:
            r4.printStackTrace()
        L24:
            boolean r4 = r8 instanceof com.jingdong.common.web.ui.X5WebView
            if (r4 == 0) goto L46
            com.jingdong.common.web.ui.X5WebView r8 = (com.jingdong.common.web.ui.X5WebView) r8
            java.lang.String r4 = r8.getMtaData()
            boolean r7 = android.text.TextUtils.isEmpty(r4)
            if (r7 != 0) goto L46
            java.lang.String r2 = updateMtaKV(r4, r2, r5)
            java.lang.String r1 = updateMtaKV(r2, r1, r6)
            java.lang.String r0 = updateMtaKV(r1, r0, r3)
            r8.updateMtaData(r0)
            r8.injectMtaData()
        L46:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.WebViewHelper.updateMtaSeq(com.tencent.smtt.sdk.WebView):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0026 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0027  */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void updateSeqInUA(com.tencent.smtt.sdk.WebView r10) {
        /*
            java.lang.String r0 = "jdv"
            java.lang.String r1 = "psq"
            java.lang.String r2 = "pv"
            java.lang.String r3 = ""
            java.lang.String r4 = com.jingdong.jdsdk.mta.JDMtaUtils.getSessionInfo()
            java.lang.String r5 = getSessionInfoWithRegex(r4, r2)     // Catch: java.lang.Exception -> L1e
            java.lang.String r6 = getSessionInfoWithRegex(r4, r1)     // Catch: java.lang.Exception -> L1b
            java.lang.String r3 = getSessionInfoWithRegex(r4, r0)     // Catch: java.lang.Exception -> L19
            goto L24
        L19:
            r4 = move-exception
            goto L21
        L1b:
            r4 = move-exception
            r6 = r3
            goto L21
        L1e:
            r4 = move-exception
            r5 = r3
            r6 = r5
        L21:
            r4.printStackTrace()
        L24:
            if (r10 != 0) goto L27
            return
        L27:
            com.tencent.smtt.sdk.WebSettings r4 = r10.getSettings()
            java.lang.String r7 = r4.getUserAgentString()     // Catch: java.lang.Exception -> L36
            java.lang.String r8 = "utf-8"
            java.lang.String r7 = java.net.URLDecoder.decode(r7, r8)     // Catch: java.lang.Exception -> L36
            goto L3a
        L36:
            java.lang.String r7 = r4.getUserAgentString()
        L3a:
            r8 = 6
            java.lang.String r9 = ";"
            int r8 = getMtaStartPosition(r7, r8, r9)
            r9 = 0
            java.lang.String r9 = r7.substring(r9, r8)
            java.lang.String r7 = r7.substring(r8)
            java.lang.String r7 = updateMtaKV(r7, r2, r5)
            java.lang.String r7 = updateMtaKV(r7, r1, r6)
            java.lang.String r7 = updateMtaKV(r7, r0, r3)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r9)
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            r4.setUserAgentString(r7)
            boolean r4 = r10 instanceof com.jingdong.common.web.ui.X5WebView
            if (r4 == 0) goto L8a
            com.jingdong.common.web.ui.X5WebView r10 = (com.jingdong.common.web.ui.X5WebView) r10
            java.lang.String r4 = r10.getMtaData()
            boolean r7 = android.text.TextUtils.isEmpty(r4)
            if (r7 != 0) goto L8a
            java.lang.String r2 = updateMtaKV(r4, r2, r5)
            java.lang.String r1 = updateMtaKV(r2, r1, r6)
            java.lang.String r0 = updateMtaKV(r1, r0, r3)
            r10.updateMtaData(r0)
            r10.injectMtaData()
        L8a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.utils.WebViewHelper.updateSeqInUA(com.tencent.smtt.sdk.WebView):void");
    }

    @Deprecated
    public static void initUA(WebView webView, boolean z) {
        if (webView == null) {
            return;
        }
        if (JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplication())) {
            StringBuffer jdUa = getJdUa(z);
            if (webView instanceof X5WebView) {
                X5WebView x5WebView = (X5WebView) webView;
                String extraUaString = x5WebView.getExtraUaString();
                if (!TextUtils.isEmpty(extraUaString)) {
                    jdUa.append(extraUaString);
                }
                jdUa.append(x5WebView.getOrgUserAgent());
            } else {
                jdUa.append(getOrgUserAgent(webView.getSettings().getUserAgentString()));
            }
            webView.getSettings().setUserAgentString(jdUa.toString());
            HybridSDK.updateSettings("userAgent", jdUa.toString());
            return;
        }
        webView.getSettings().setUserAgentString(getJdUaInfo2().toString());
    }

    public static boolean isJdPayMatch(Activity activity, String str, String str2) {
        try {
            if (JumpUtils.checkPayHttpHost(str)) {
                PayUtils.doPayWithWebURL(activity, str, str2);
                return true;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
