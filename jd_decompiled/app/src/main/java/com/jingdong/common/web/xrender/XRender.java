package com.jingdong.common.web.xrender;

import android.app.Application;
import android.content.Context;
import android.content.MutableContextWrapper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.util.HybridUrlUtils;
import com.jd.libs.hybrid.base.util.VersionUtils;
import com.jd.libs.xconsole.XLog;
import com.jingdong.common.utils.BackForegroundWatcher;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.WebHybridUtils;
import com.jingdong.common.web.WebOfflineLoaderManager;
import com.jingdong.common.web.WebPreLoadHelper;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.ui.JDWebViewBuilder;
import com.jingdong.common.web.util.ChannelPrivacyConfirmUtil;
import com.jingdong.common.web.util.WebSwitchQueryFetcher;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.jdsdk.utils.URLParamMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class XRender {
    public static final String PRERENDER_TYPE_BUSINESS = "1";
    public static final String TAG = "\u9884\u6e32\u67d3";
    private volatile boolean inited;
    private int lastPreRenderDataType;
    private int lastPreRenderDataTypeTemp;
    private ActivityLifeCycle mActivityLifeCycle;
    private Map<String, JDWebView> mCachedWebViewMap;
    private Map<String, String> mDefaultRequireAppModeMap;
    private Map<String, String> mDefaultTypeMap;
    private final Map<String, Boolean> mOutOfCountExceps;
    private Map<String, Integer> mPreRenderWebViewCount;
    private Map<String, String> mRequireAppModeMap;
    private Map<String, String> mTypeMap;
    private final Map<String, Boolean> mWrongABExceps;
    private Handler mainHandler;
    private int maxUseTimes;
    private boolean needRender;
    private HybridSDK.PreRender preRender;
    private boolean useXRenderType;

    /* loaded from: classes12.dex */
    public static class Holder {
        private static final XRender INSTANCE = new XRender();

        private Holder() {
        }
    }

    public static void Log(String str) {
        Log(str, null);
    }

    public static /* synthetic */ void a(JDWebView jDWebView) {
        if (jDWebView != null) {
            jDWebView.onDestory();
        }
    }

    private void addPreRenderCount(String str) {
        Log("\u9884\u6e32\u67d3\u6b21\u6570 +1");
        this.mPreRenderWebViewCount.put(str, Integer.valueOf(getPreRenderCount(str).intValue() + 1));
    }

    private String addXRenderParam(String str, String str2, int i2) {
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        if (!this.useXRenderType) {
            if (TextUtils.isEmpty(str2)) {
                str2 = "1";
            }
            if ("2".equals(str2)) {
                Log("url\u6dfb\u52a0\u6807\u8bc6xrender=2");
                buildUpon.appendQueryParameter(WebWhiteScreenHolder.X_RENDER, "2");
            } else {
                Log("url\u6dfb\u52a0\u6807\u8bc6xrender=1\uff0cframework=1");
                buildUpon.appendQueryParameter(WebWhiteScreenHolder.X_RENDER, "1");
                buildUpon.appendQueryParameter("framework", "1");
            }
        } else if (i2 == 0 || i2 == 1) {
            Log("renderType= " + i2 + ", url\u6dfb\u52a0\u6807\u8bc6xrender=1\uff0cframework=1");
            buildUpon.appendQueryParameter(WebWhiteScreenHolder.X_RENDER, "1");
            buildUpon.appendQueryParameter("framework", "1");
        } else if (i2 == 2) {
            Log("renderType= " + i2 + ",url\u6dfb\u52a0\u6807\u8bc6xrender=2");
            buildUpon.appendQueryParameter(WebWhiteScreenHolder.X_RENDER, "2");
        }
        return buildUpon.build().toString();
    }

    /* renamed from: b */
    public /* synthetic */ void c(HybridSDK.PreRenderModule preRenderModule) {
        preload(preRenderModule.getOriginalUrl(), preRenderModule, "2", "\u63a5\u53e3");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0076, code lost:
        if (r0.equals(com.jingdong.common.cart.clean.CartCleanConstants.CART_CLEAN_DIALOG_AB) == false) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean canPreRenderInThisMode(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            r1 = 0
            if (r0 == 0) goto Le
            java.lang.String r10 = "\u5f53\u524dUrl\u53d6host,path\u5931\u8d25"
            Log(r10, r9)
            return r1
        Le:
            java.util.Map<java.lang.String, java.lang.String> r0 = r8.mRequireAppModeMap
            java.lang.String r2 = "5"
            java.lang.String r3 = "ab\u7248\u4e0d\u7b26\uff0c\u5f53\u524d=%s\uff0c\u8981\u6c42=%s"
            java.lang.String r4 = "null"
            r5 = 2
            r6 = 1
            if (r0 == 0) goto La7
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L22
            goto La7
        L22:
            java.util.Map<java.lang.String, java.lang.String> r0 = r8.mRequireAppModeMap
            java.lang.Object r0 = r0.get(r9)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L40
            java.lang.String r0 = "\u5f53\u524dUrl\u914d\u7f6e\u7684App\u6a21\u5f0f\u8981\u6c42\u4e3a\u7a7a"
            Log(r0, r9)
            java.lang.Object[] r9 = new java.lang.Object[r5]
            r9[r1] = r4
            r9[r6] = r4
            java.lang.String r9 = java.lang.String.format(r3, r9)
            r8.reportWrongABError(r10, r2, r9)
            return r1
        L40:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r7 = "\u5f53\u524dUrl\u914d\u7f6e\u7684App\u6a21\u5f0f\u8981\u6c42:"
            r4.append(r7)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            Log(r4, r9)
            java.lang.String r9 = com.jingdong.sdk.bmode.util.JDBModeUtils.getCurrentMode()
            java.lang.Object[] r4 = new java.lang.Object[r5]
            r4[r1] = r9
            r4[r6] = r0
            java.lang.String r3 = java.lang.String.format(r3, r4)
            r0.hashCode()
            r4 = -1
            int r7 = r0.hashCode()
            switch(r7) {
                case 97: goto L84;
                case 98: goto L79;
                case 3105: goto L70;
                default: goto L6e;
            }
        L6e:
            r5 = -1
            goto L8e
        L70:
            java.lang.String r7 = "ab"
            boolean r0 = r0.equals(r7)
            if (r0 != 0) goto L8e
            goto L6e
        L79:
            java.lang.String r5 = "b"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L82
            goto L6e
        L82:
            r5 = 1
            goto L8e
        L84:
            java.lang.String r5 = "a"
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L8d
            goto L6e
        L8d:
            r5 = 0
        L8e:
            java.lang.String r0 = "0"
            switch(r5) {
                case 0: goto L9d;
                case 1: goto L96;
                case 2: goto L94;
                default: goto L93;
            }
        L93:
            goto La1
        L94:
            r1 = 1
            goto La1
        L96:
            boolean r9 = r0.equals(r9)
            r1 = r9 ^ 1
            goto La1
        L9d:
            boolean r1 = r0.equals(r9)
        La1:
            if (r1 != 0) goto La6
            r8.reportWrongABError(r10, r2, r3)
        La6:
            return r1
        La7:
            java.lang.String r9 = "\u4e0b\u53d1\u7684APP\u6a21\u5f0f\u8981\u6c42\u4e3a\u7a7a"
            Log(r9)
            java.lang.Object[] r9 = new java.lang.Object[r5]
            r9[r1] = r4
            r9[r6] = r4
            java.lang.String r9 = java.lang.String.format(r3, r9)
            r8.reportWrongABError(r10, r2, r9)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.xrender.XRender.canPreRenderInThisMode(java.lang.String, java.lang.String):boolean");
    }

    private void checkModules() {
        try {
            if (this.preRender == null) {
                Log("\u9884\u6e32\u67d3\u6570\u636e\u4e3a\u7a7a");
                return;
            }
            Iterator<Map.Entry<String, JDWebView>> it = this.mCachedWebViewMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, JDWebView> next = it.next();
                boolean z = false;
                for (int i2 = 0; i2 < this.preRender.getModules().size(); i2++) {
                    HybridSDK.PreRenderModule preRenderModule = this.preRender.getModules().get(i2);
                    if (preRenderModule != null) {
                        if (next.getKey().equals(preRenderModule.getOriginalUrl())) {
                            z = true;
                        }
                    }
                }
                if (!z) {
                    String key = next.getKey();
                    Log("\u5220\u9664\u7f13\u5b58\u4e2d modules\u65b0\u6570\u636e\u4e0d\u5339\u914d\u7684\u7f13\u5b58  \uff1a " + key);
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    jDJSONObject.put("url", (Object) key);
                    jDJSONObject.put("destroy_type", (Object) "2");
                    sendExposure("xrender_destroy", jDJSONObject.toJSONString());
                    final JDWebView value = next.getValue();
                    if (value.getPerformanceHolder() != null) {
                        value.getPerformanceHolder().appendExtraToCurrRecord("xrender_destroy", "2");
                    }
                    ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", key, "2", "\u914d\u7f6e\u53d8\u5316");
                    this.mainHandler.post(new Runnable() { // from class: com.jingdong.common.web.xrender.b
                        @Override // java.lang.Runnable
                        public final void run() {
                            XRender.a(value);
                        }
                    });
                    it.remove();
                }
            }
        } catch (Exception unused) {
            Log("checkModules \u9884\u6e32\u67d3\u6570\u636e\u5f02\u5e38");
        }
    }

    private void checkPage() {
        ArrayList<String> arrayList = LifeCycleDispatcher.pageList;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        checkRender(null);
    }

    private void createWebView(String str, HybridSDK.PreRenderModule preRenderModule, String str2, String str3) {
        Log("2-> createWebView");
        if (this.preRender == null) {
            Log("\u9884\u6e32\u67d3\u6570\u636e\u4e3a\u7a7a");
            return;
        }
        JDWebView jDWebView = this.mCachedWebViewMap.get(str);
        if (jDWebView != null) {
            if (!jDWebView.getXRenderManager().isTimeValid()) {
                Log("\u9884\u6e32\u67d3\u672a\u5b8c\u6210\uff0c\uff0c\u8d85\u8fc7\u914d\u7f6e\u6709\u6548\u65f6\u95f4\uff0c\u9500\u6bc1\u7f13\u5b58\uff0c\u91cd\u65b0\u9884\u6e32\u67d3");
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("url", (Object) str);
                jDJSONObject.put("destroy_type", (Object) "4");
                sendExposure("xrender_destroy", jDJSONObject.toJSONString());
                if (jDWebView.getPerformanceHolder() != null) {
                    jDWebView.getPerformanceHolder().appendExtraToCurrRecord("xrender_destroy", "4");
                }
                long currentTimeMillis = System.currentTimeMillis();
                long startTime = jDWebView.getXRenderManager().getStartTime();
                ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", str, "4", "\u8d85\u65f6(\u52a0\u8f7d)-" + str3 + ", now=" + currentTimeMillis + ", start=" + startTime, String.valueOf(currentTimeMillis - startTime));
                jDWebView.onDestory();
                removeCachedWebViewMap(str);
                preload(str, preRenderModule, str2, "\u8d85\u65f6\u91cd\u65b0\u89e6\u53d1");
                return;
            }
            Log("\u9884\u6e32\u67d3\u672a\u5b8c\u6210\uff0c\u6b64url\u5df2\u7ecf\u6e32\u67d3\u8fc7\u6709\u7f13\u5b58");
            return;
        }
        if (!isCountValid(str, preRenderModule.getMaxUseTimes() > 0 ? preRenderModule.getMaxUseTimes() : this.maxUseTimes)) {
            Log("\u9884\u6e32\u67d3\u672a\u5b8c\u6210\uff0c\u9884\u6e32\u67d3\u6b21\u6570 > \u914d\u7f6e\u6b21\u6570");
            reportOutOfCountError(str, "6", "\u8d85\u6b21\u6570");
            return;
        }
        String excludeQuery = HybridUrlUtils.excludeQuery(str);
        if (!this.useXRenderType && !canPreRenderInThisMode(excludeQuery, str)) {
            Log("\u9884\u6e32\u67d3\u672a\u5b8c\u6210\uff0c\u5f53\u524dAPP\u6a21\u5f0f\u4e0d\u7b26\u5408\u4e0b\u53d1\u8981\u6c42");
        } else if (ChannelPrivacyConfirmUtil.checkIsNeedPrivacyConfirmWithoutDialog(str)) {
            ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", str, "9", "\u9700\u8981\u9690\u79c1\u5f39\u7a97");
            Log("\u9884\u6e32\u67d3\u672a\u5b8c\u6210\uff0c\u6b64url\u9700\u8981\u9690\u79c1\u5f39\u7a97");
        } else {
            String preRenderType = getPreRenderType(excludeQuery);
            String addXRenderParam = addXRenderParam(str, preRenderType, preRenderModule.getXrenderType());
            Log("\u52a0\u53c2\u7684url = " + addXRenderParam);
            this.lastPreRenderDataType = this.lastPreRenderDataTypeTemp;
            String createOfflineLoader = WebOfflineLoaderManager.createOfflineLoader(addXRenderParam);
            JDWebViewBuilder jDWebViewBuilder = new JDWebViewBuilder(new MutableContextWrapper(JdSdk.getInstance().getApplication()));
            jDWebViewBuilder.setIsPreRender(true).setOfflineId(createOfflineLoader).setEnableHybrid(true).setHybridStarted(!TextUtils.isEmpty(createOfflineLoader)).setHybridUrl(addXRenderParam).setLoadingBg(WebUtils.getLoadingPlaceHolder(addXRenderParam)).addExtraSetting("xrenderType", preRenderType);
            JDWebView preRenderBuild = jDWebViewBuilder.preRenderBuild();
            preRenderBuild.appendPerformanceData(WebPerfManager.PAGE_NAME, (preRenderBuild.getContext() != null ? preRenderBuild.getContext().getClass().getSimpleName() : "") + "/" + str3);
            preRenderBuild.appendPerformanceData(WebPerfManager.IS_PRE_RENDER, "1");
            preRenderBuild.setUseCache(true);
            JDJSONObject jDJSONObject2 = new JDJSONObject();
            jDJSONObject2.put("url", (Object) str);
            jDJSONObject2.put("trigger_type", (Object) str2);
            sendExposure("xrender_start", jDJSONObject2.toJSONString());
            preRenderBuild.setxRenderUrl(str);
            Log("2.1->webview\u7f13\u5b58 key=" + str);
            this.mCachedWebViewMap.put(str, preRenderBuild);
            preRenderBuild.getXRenderManager().recordTime();
            preRenderBuild.getXRenderManager().setReserveTime(preRenderModule.getReserveTime() > 0 ? preRenderModule.getReserveTime() : this.preRender.getReserveTime());
            preRenderBuild.getXRenderManager().setModule(preRenderModule);
            preRenderBuild.getXRenderManager().setIsClass(str2);
            preRenderBuild.getXRenderManager().setConsumeXRender(false);
            Log("2.2-> \u8bb0\u5f55\u5f53\u524d\u65f6\u95f4\u6233");
            addPreRenderCount(str);
            Log("2.4-> \u767b\u5f55\u9884\u6253\u901a+loadUrl");
            URLParamMap uRLParamMap = new URLParamMap();
            Bundle bundle = new Bundle();
            bundle.putString("url", addXRenderParam);
            String addStatusBarHeightParams = WebUtils.addStatusBarHeightParams(addXRenderParam);
            Log("\u62fc\u63a5\u5bfc\u822a\u680f\u548c\u72b6\u6001\u680f\u9ad8\u5ea6\u53c2\u6570\u7684url = " + addStatusBarHeightParams);
            String addBabelCustomParams = WebUtils.addBabelCustomParams(addStatusBarHeightParams, bundle);
            Log("\u62fc\u63a5\u901a\u5929\u5854\u53c2\u6570tttparams\u7684url = " + addBabelCustomParams);
            uRLParamMap.put(RemoteMessageConst.TO, addBabelCustomParams);
            WebEntity webEntity = new WebEntity();
            webEntity.action = RemoteMessageConst.TO;
            webEntity.urlMap = uRLParamMap;
            preRenderBuild.addLoadEvent("gentokenStart");
            if (WebSwitchQueryFetcher.newGentoken(webEntity.urlMap)) {
                WebUtils.newGentoken(webEntity, preRenderBuild, null, this, true);
            } else {
                WebUtils.gentoken(webEntity, preRenderBuild, null, this);
            }
        }
    }

    /* renamed from: d */
    public /* synthetic */ boolean e(String str, HybridSDK.PreRenderModule preRenderModule, String str2, String str3) {
        createWebView(str, preRenderModule, str2, str3);
        return false;
    }

    private boolean exceptionForMultiScreen() {
        return !WebUtils.privacyAgreedOnAppStart && WebUtils.isMultiScreen();
    }

    public static /* synthetic */ void f(JDWebView jDWebView) {
        if (jDWebView != null) {
            jDWebView.onDestory();
        }
    }

    public static XRender getInstance() {
        return Holder.INSTANCE;
    }

    private Integer getPreRenderCount(String str) {
        if (!TextUtils.isEmpty(str) && this.mPreRenderWebViewCount.get(str) != null) {
            return this.mPreRenderWebViewCount.get(str);
        }
        return 1;
    }

    private String getPreRenderType(String str) {
        Map<String, String> map = this.mTypeMap;
        if (map == null || map.isEmpty() || TextUtils.isEmpty(str)) {
            return "1";
        }
        String str2 = this.mTypeMap.get(str);
        Log("\u5f53\u524dUrl\u914d\u7f6e\u7684\u9884\u6e32\u67d3\u7c7b\u578b:" + str2, str);
        return "2".equals(str2) ? "2" : "1";
    }

    private Pair<JDWebView, String> getWebViewInternal(Context context, String str) {
        Log("3-> \u83b7\u53d6\u9884\u6e32\u67d3webview--------------");
        Log("\u4e1a\u52a1url= " + str);
        if (TextUtils.isEmpty(str)) {
            Log("\u672a\u547d\u4e2d\u9884\u6e32\u67d3\uff0curl\u4e3a\u7a7a");
            return new Pair<>(null, "3.3.1");
        } else if (!this.needRender) {
            Log("\u672a\u547d\u4e2d\u9884\u6e32\u67d3\uff0c\u5f53\u524d\u5904\u4e8e\u540e\u53f0");
            ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", str, "8", "APP\u5904\u4e8e\u540e\u53f01(\u6d88\u8d39)");
            return new Pair<>(null, "3.3.2");
        } else if (isHybridRender(str)) {
            Log("\u672a\u547d\u4e2d\u9884\u6e32\u67d3\uff0curl\u53c2\u6570\u964d\u7ea7,\u76f4\u63a5\u8fd4\u56dewebview");
            return new Pair<>(null, "3.3.3");
        } else {
            Pair<JDWebView, String> cacheWebView = getCacheWebView(str);
            JDWebView jDWebView = cacheWebView != null ? (JDWebView) cacheWebView.first : null;
            if (jDWebView == null) {
                Log("3.1-> \u672a\u547d\u4e2d\u9884\u6e32\u67d3\uff0curl\u5339\u914d\u5931\u8d25\uff0c\u76f4\u63a5\u8fd4\u56dewebview");
                return cacheWebView;
            }
            removeCachedWebViewMap(jDWebView.getxRenderUrl());
            if (!jDWebView.getXRenderManager().isTimeValid()) {
                Log("3.4->\u672a\u547d\u4e2d\u9884\u6e32\u67d3\uff0c\u8d85\u8fc7\u914d\u7f6e\u6709\u6548\u65f6\u95f4\uff0c\u76f4\u63a5\u8fd4\u56dewebview");
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("url", (Object) jDWebView.getxRenderUrl());
                jDJSONObject.put("destroy_type", (Object) "4");
                sendExposure("xrender_destroy", jDJSONObject.toJSONString());
                if (jDWebView.getPerformanceHolder() != null) {
                    jDWebView.getPerformanceHolder().appendExtraToCurrRecord("xrender_destroy", "4");
                }
                long currentTimeMillis = System.currentTimeMillis();
                long startTime = jDWebView.getXRenderManager().getStartTime();
                ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", str, "4", "\u8d85\u65f6(\u6d88\u8d39), now=" + currentTimeMillis + ", start=" + startTime, String.valueOf(currentTimeMillis - startTime));
                jDWebView.onDestory();
                return new Pair<>(null, "3.4");
            }
            if (!SwitchQueryFetcher.getSwitchBooleanValue("isReturnXrenderImmediately", false) && !exceptionForMultiScreen()) {
                if (!jDWebView.isPageFinished()) {
                    Log("\u672a\u547d\u4e2d\u9884\u6e32\u67d3,pageFinished\u8fd8\u672a\u6267\u884c\u5b8c");
                    jDWebView.onDestory();
                    ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", str, "7", "\u7f51\u9875\u672a\u52a0\u8f7d\u5b8c");
                    return new Pair<>(null, "3.2.2");
                }
            } else {
                Log("isReturnXrenderImmediately \u5f00\u5173\u6253\u5f00");
            }
            Log("3.5-> \u547d\u4e2d\u9884\u6e32\u67d3--------------");
            ((MutableContextWrapper) jDWebView.getContext()).setBaseContext(context);
            jDWebView.setContext(context);
            jDWebView.getNavigatorHolder().setContext(context);
            jDWebView.getXRenderManager().setBusinessUrl(str);
            jDWebView.getXRenderManager().setConsumeXRender(true);
            return new Pair<>(jDWebView, "3.5");
        }
    }

    private boolean isCountValid(String str, int i2) {
        Log(" isCountValid \u9884\u6e32\u67d3\u6b21\u6570 = " + getPreRenderCount(str));
        return getPreRenderCount(str).intValue() <= i2;
    }

    private boolean isHybridRender(String str) {
        try {
            Uri parse = Uri.parse(str);
            String queryParameter = parse.getQueryParameter("hybridrender");
            if (!WebHybridUtils.degradeOfflineFromQuery(parse)) {
                if (!"0".equals(queryParameter)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e2) {
            Log(e2.toString());
            return false;
        }
    }

    public static void sendExposure(String str, String str2) {
        JDMtaUtils.sendExposureDataWithExt(JdSdk.getInstance().getApplication(), str, "", "", "", "", str2, null);
    }

    private void webViewOnDestroy() {
        Iterator<Map.Entry<String, JDWebView>> it = this.mCachedWebViewMap.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue();
            this.mainHandler.post(new Runnable
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0023: INVOKE 
                  (wrap: android.os.Handler : 0x001c: IGET (r4v0 'this' com.jingdong.common.web.xrender.XRender A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:3) com.jingdong.common.web.xrender.XRender.mainHandler android.os.Handler)
                  (wrap: java.lang.Runnable : 0x0020: CONSTRUCTOR (r1 I:com.jingdong.common.web.ui.JDWebView A[DONT_GENERATE, DONT_INLINE, REMOVE]) A[MD:(com.jingdong.common.web.ui.JDWebView):void (m), WRAPPED] call: com.jingdong.common.web.xrender.c.<init>(com.jingdong.common.web.ui.JDWebView):void type: CONSTRUCTOR)
                 type: VIRTUAL call: android.os.Handler.post(java.lang.Runnable):boolean A[MD:(java.lang.Runnable):boolean (c)] (LINE:3) in method: com.jingdong.common.web.xrender.XRender.webViewOnDestroy():void, file: classes12.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:226)
                	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:171)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                */
            /*
                this = this;
                java.util.Map<java.lang.String, com.jingdong.common.web.ui.JDWebView> r0 = r4.mCachedWebViewMap
                java.util.Set r0 = r0.entrySet()
                java.util.Iterator r0 = r0.iterator()
            La:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L27
                java.lang.Object r1 = r0.next()
                java.util.Map$Entry r1 = (java.util.Map.Entry) r1
                java.lang.Object r1 = r1.getValue()
                com.jingdong.common.web.ui.JDWebView r1 = (com.jingdong.common.web.ui.JDWebView) r1
                android.os.Handler r2 = r4.mainHandler
                com.jingdong.common.web.xrender.c r3 = new com.jingdong.common.web.xrender.c
                r3.<init>()
                r2.post(r3)
                goto La
            L27:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.xrender.XRender.webViewOnDestroy():void");
        }

        public void checkRender(String str) {
            try {
                HybridSDK.PreRender preRender = this.preRender;
                if (preRender == null || preRender.getModules() == null || this.preRender.getModules().isEmpty()) {
                    return;
                }
                List<HybridSDK.PreRenderModule> modules = this.preRender.getModules();
                for (int i2 = 0; i2 < modules.size(); i2++) {
                    final HybridSDK.PreRenderModule preRenderModule = modules.get(i2);
                    if (preRenderModule != null && preRenderModule.getDemandClasses() != null) {
                        for (String str2 : preRenderModule.getDemandClasses()) {
                            if (!TextUtils.isEmpty(str)) {
                                if (!TextUtils.isEmpty(str2) && str2.equals(str)) {
                                    Log("\u9884\u6e32\u67d3\u5339\u914d\u573a\u666f----------\u76d1\u542cActivity/Fragment-onResume");
                                    preload(preRenderModule.getOriginalUrl(), preRenderModule, "2", "\u9875\u9762" + str);
                                }
                            } else if (LifeCycleDispatcher.pageList.contains(str2)) {
                                Log("\u9884\u6e32\u67d3\u5339\u914d\u573a\u666f----------\u63a5\u53e3\u4e3b\u52a8\u89e6\u53d1\u9884\u6e32\u67d3");
                                this.mainHandler.post(new Runnable() { // from class: com.jingdong.common.web.xrender.a
                                    {
                                        XRender.this = this;
                                    }

                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        XRender.this.c(preRenderModule);
                                    }
                                });
                            }
                        }
                    }
                }
            } catch (Exception unused) {
                Log("checkRender \u9884\u6e32\u67d3\u6570\u636e\u5f02\u5e38");
            }
        }

        public Pair<JDWebView, String> getCacheWebView(String str) {
            if (TextUtils.isEmpty(str)) {
                Log("url\u4e3a\u7a7a");
                return new Pair<>(null, "3.1.1");
            }
            Uri parse = Uri.parse(str);
            for (Map.Entry<String, JDWebView> entry : this.mCachedWebViewMap.entrySet()) {
                Uri parse2 = Uri.parse(entry.getKey());
                if (HybridUrlUtils.uriMatchHostPath(parse2, parse)) {
                    String query = parse.getQuery();
                    String query2 = parse2.getQuery();
                    if (TextUtils.isEmpty(query2) && TextUtils.isEmpty(query)) {
                        Log("3.1.2 cms\u6ca1\u53c2\u6570\uff0c\u4e1a\u52a1\u6ca1\u53c2\u6570");
                        return new Pair<>(entry.getValue(), "3.1.2");
                    } else if (!TextUtils.isEmpty(query2) && TextUtils.isEmpty(query)) {
                        Log("3.1.3 cms\u6709\u53c2\u6570\uff0c\u4e1a\u52a1\u6ca1\u53c2\u6570");
                        return new Pair<>(null, "3.1.3");
                    } else if (TextUtils.isEmpty(query2) && !TextUtils.isEmpty(query)) {
                        Log("3.1.4 cms\u6ca1\u53c2\u6570 \u4e1a\u52a1\u6709\u53c2\u6570");
                        return new Pair<>(entry.getValue(), "3.1.4");
                    } else {
                        Log("\u4e1a\u52a1 \u53c2\u6570 \uff1a " + query);
                        Log("cms \u53c2\u6570 \uff1a " + query2);
                        String[] split = query.split(ContainerUtils.FIELD_DELIMITER);
                        String[] split2 = query2.split(ContainerUtils.FIELD_DELIMITER);
                        if (split.length >= split2.length) {
                            ArrayList arrayList = new ArrayList();
                            Collections.addAll(arrayList, split2);
                            ArrayList arrayList2 = new ArrayList();
                            Collections.addAll(arrayList2, split);
                            if (arrayList2.containsAll(arrayList)) {
                                Log("3.1.5 url\u5339\u914d\u6210\u529f-----------");
                                return new Pair<>(entry.getValue(), "3.1.5");
                            }
                        } else {
                            Log("3.1.6 cms\u53c2\u6570\u957f\u5ea6 > \u4e1a\u52a1\u53c2\u6570\u957f\u5ea6");
                            return new Pair<>(null, "3.1.6");
                        }
                    }
                }
            }
            if (this.mOutOfCountExceps.containsKey(HybridUrlUtils.excludeQuery(str))) {
                Log("3.1.8 url\u5339\u914d\u5931\u8d25\uff0c\u5f53\u524d\u7f13\u5b58\u4e2d\u6ca1\u6709\u6b64url\u7684\u9884\u6e32\u67d3\uff0c\u6b64url\u4e4b\u524d\u5df2\u7ecf\u8d85\u8fc7\u6b21\u6570");
                return new Pair<>(null, "3.1.8");
            }
            Log("3.1.7 url\u5339\u914d\u5931\u8d25\uff0c\u5f53\u524d\u7f13\u5b58\u4e2d\u6ca1\u6709\u6b64url\u7684\u9884\u6e32\u67d3");
            return new Pair<>(null, "3.1.7");
        }

        public int getLastPreRenderDataType() {
            return this.lastPreRenderDataType;
        }

        public JDWebView getWebView(Context context, String str) {
            Pair<JDWebView, String> webViewInternal = getWebViewInternal(context, str);
            if (webViewInternal != null) {
                return (JDWebView) webViewInternal.first;
            }
            return null;
        }

        public Pair<JDWebView, String> getWebViewWithMta(Context context, String str) {
            return getWebViewInternal(context, str);
        }

        public Pair<Boolean, Integer> hasPreRenderData(String str) {
            HybridSDK.PreRender preRender = this.preRender;
            List<HybridSDK.PreRenderModule> modules = preRender != null ? preRender.getModules() : null;
            if (modules != null && !modules.isEmpty()) {
                int size = modules.size();
                if (TextUtils.isEmpty(str)) {
                    return new Pair<>(Boolean.FALSE, Integer.valueOf(size));
                }
                try {
                    Uri parse = Uri.parse(str);
                    for (HybridSDK.PreRenderModule preRenderModule : modules) {
                        if (preRenderModule != null && !TextUtils.isEmpty(preRenderModule.getOriginalUrl()) && HybridUrlUtils.uriMatchHostPath(Uri.parse(preRenderModule.getOriginalUrl()), parse)) {
                            return new Pair<>(Boolean.TRUE, Integer.valueOf(size));
                        }
                    }
                    return new Pair<>(Boolean.FALSE, Integer.valueOf(size));
                } catch (Exception unused) {
                    return new Pair<>(Boolean.FALSE, Integer.valueOf(size));
                }
            }
            return new Pair<>(Boolean.FALSE, 0);
        }

        public void initXRender() {
            if (this.inited) {
                Log("\u5df2\u7ecf\u521d\u59cb\u5316\u8fc7\uff0c\u4e0d\u518d\u6267\u884c\u521d\u59cb\u5316");
            } else if (!isPreRender()) {
                Log("switchQuery\u5f00\u5173\u5173\u95ed\uff0c\u4e0d\u6267\u884c\u521d\u59cb\u5316");
            } else if (!JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext())) {
                Log("\u672a\u540c\u610f\u9690\u79c1\u534f\u8bae\uff0c\u4e0d\u6267\u884c\u521d\u59cb\u5316");
            } else {
                this.inited = true;
                try {
                    Log("initXRender");
                    Application application = JdSdk.getInstance().getApplication();
                    if (application != null) {
                        ActivityLifeCycle activityLifeCycle = new ActivityLifeCycle();
                        this.mActivityLifeCycle = activityLifeCycle;
                        application.registerActivityLifecycleCallbacks(activityLifeCycle);
                    }
                    String string = SharedPreferencesUtil.getString("lastPreRenderData", "");
                    if (!TextUtils.isEmpty(string)) {
                        this.lastPreRenderDataTypeTemp = 1;
                        setPreRenderData((HybridSDK.PreRender) JDJSON.parseObject(string, HybridSDK.PreRender.class));
                    }
                    BackForegroundWatcher.getInstance().registerListener(new BackForegroundWatcher.BackForegroundListener() { // from class: com.jingdong.common.web.xrender.XRender.1
                        {
                            XRender.this = this;
                        }

                        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
                        public void onBackToForeground() {
                            XRender.Log("\u5207\u6362\u524d\u53f0 needRender = true");
                            XRender.this.needRender = true;
                        }

                        @Override // com.jingdong.common.utils.BackForegroundWatcher.BackForegroundListener
                        public void onForeToBackground() {
                            XRender.Log("\u5207\u6362\u540e\u53f0 needRender = false");
                            XRender.this.needRender = false;
                        }
                    });
                } catch (Exception e2) {
                    Log("\u9884\u6e32\u67d3\u521d\u59cb\u5316\u51fa\u9519\uff1a" + e2);
                }
            }
        }

        public boolean isPreRender() {
            return !SwitchQueryFetcher.getSwitchBooleanValue("xrenderDisable", false);
        }

        public boolean isUrlPreRendered(String str) {
            if (isPreRender()) {
                Log("isUrlPreRendered 3-> \u83b7\u53d6\u9884\u6e32\u67d3webview--------------");
                Log("\u4e1a\u52a1url= " + str);
                if (TextUtils.isEmpty(str)) {
                    Log("isUrlPreRendered \u672a\u547d\u4e2d\u9884\u6e32\u67d3\uff0curl\u4e3a\u7a7a");
                    return false;
                } else if (!this.needRender) {
                    Log("isUrlPreRendered \u672a\u547d\u4e2d\u9884\u6e32\u67d3\uff0c\u5f53\u524d\u5904\u4e8e\u540e\u53f0");
                    return false;
                } else if (isHybridRender(str)) {
                    Log("isUrlPreRendered \u672a\u547d\u4e2d\u9884\u6e32\u67d3\uff0curl\u53c2\u6570\u964d\u7ea7,\u76f4\u63a5\u8fd4\u56dewebview");
                    return false;
                } else {
                    Pair<JDWebView, String> cacheWebView = getCacheWebView(str);
                    if ((cacheWebView != null ? (JDWebView) cacheWebView.first : null) != null) {
                        return true;
                    }
                    Log("isUrlPreRendered 3.1-> \u672a\u547d\u4e2d\u9884\u6e32\u67d3\uff0curl\u5339\u914d\u5931\u8d25\uff0c\u76f4\u63a5\u8fd4\u56dewebview");
                }
            }
            return false;
        }

        public void preload(final String str, final HybridSDK.PreRenderModule preRenderModule, final String str2, final String str3) {
            Log("\u9884\u6e32\u67d3 \u7c7b\u578b = " + str2 + " url = " + str);
            if (!isPreRender()) {
                Log("\u9884\u6e32\u67d3\u672a\u5b8c\u6210\uff0cswitchQuery\u964d\u7ea7");
            } else if (!this.needRender) {
                Log("\u9884\u6e32\u67d3\u672a\u5b8c\u6210\uff0c\u5f53\u524d\u5904\u4e8e\u540e\u53f0");
                ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", str, "8", "APP\u5904\u4e8e\u540e\u53f01(\u52a0\u8f7d)-" + str3);
            } else if (!VersionUtils.isAppVersionBetween(JdSdk.getInstance().getApplication(), preRenderModule.getAppMin(), preRenderModule.getAppMax())) {
                Log("\u9884\u6e32\u67d3\u672a\u5b8c\u6210\uff0c\u4e0d\u5728\u914d\u7f6e\u7684\u7248\u672c\u533a\u95f4");
            } else if (TextUtils.isEmpty(str)) {
                Log("\u9884\u6e32\u67d3\u672a\u5b8c\u6210\uff0curl\u4e3a\u7a7a");
            } else if (!BackForegroundWatcher.getInstance().isAppForeground()) {
                Log("\u9884\u6e32\u67d3\u672a\u5b8c\u6210\uff0c\u540e\u53f0\u4e0d\u8fdb\u884c\u9884\u6e32\u67d3");
                ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", str, "8", "APP\u5904\u4e8e\u540e\u53f02(\u52a0\u8f7d)-" + str3);
            } else if (isHybridRender(str)) {
                Log("\u9884\u6e32\u67d3\u672a\u5b8c\u6210\uff0chybridrender=0 \u5f00\u5173\u89e6\u53d1");
            } else if (!SwitchQueryFetcher.getSwitchBooleanValue("xRenderIdleHandler", false)) {
                if ("1".equals(str2)) {
                    Log("1-> preload \uff08\u4e0d\u7b49\u5f85\u76f4\u63a5\u6267\u884c\uff09----------------");
                    createWebView(str, preRenderModule, str2, str3);
                    return;
                }
                Log("1-> preload \uff08\u7b49\u5f85\u7ebf\u7a0b\u7a7a\u95f2\uff09----------------");
                Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.jingdong.common.web.xrender.d
                    {
                        XRender.this = this;
                    }

                    @Override // android.os.MessageQueue.IdleHandler
                    public final boolean queueIdle() {
                        return XRender.this.e(str, preRenderModule, str2, str3);
                    }
                });
            } else {
                Log("1-> preload \uff08\u4e0d\u7b49\u5f85\u76f4\u63a5\u6267\u884c\uff09----------------");
                createWebView(str, preRenderModule, str2, str3);
            }
        }

        public void removeCachedWebViewMap(String str) {
            if (this.mCachedWebViewMap.get(str) == null) {
                return;
            }
            Log("\u5220\u9664\u7f13\u5b58webview   url = " + str);
            this.mCachedWebViewMap.remove(str);
        }

        public void reportOutOfCountError(String str, String str2, String str3) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String excludeQuery = HybridUrlUtils.excludeQuery(str);
            if (this.mOutOfCountExceps.containsKey(excludeQuery)) {
                return;
            }
            this.mOutOfCountExceps.put(excludeQuery, Boolean.TRUE);
            ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", str, str2, str3);
        }

        public void reportWrongABError(String str, String str2, String str3) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String excludeQuery = HybridUrlUtils.excludeQuery(str);
            if (this.mWrongABExceps.containsKey(excludeQuery)) {
                return;
            }
            this.mWrongABExceps.put(excludeQuery, Boolean.TRUE);
            ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", str, str2, str3);
        }

        public boolean sendClickEvent(String str) {
            if (!TextUtils.isEmpty(str) && isPreRender()) {
                Pair<JDWebView, String> cacheWebView = getCacheWebView(str);
                JDWebView jDWebView = cacheWebView != null ? (JDWebView) cacheWebView.first : null;
                if (jDWebView == null || jDWebView.getXRenderManager() == null || jDWebView.getXRenderManager().isSendClickEvent()) {
                    return false;
                }
                Log("\u6ce8\u518c\u63a5\u53e3\u9884\u52a0\u8f7dJS\u6865");
                jDWebView.setXRenderPreload(WebPreLoadHelper.preLoad(str));
                jDWebView.initPreloadFunction();
                Log("\u5f00\u59cb\u53d1\u9001xrenderClick \u4e8b\u4ef6\u53caJS\u6865\u5185\u5bb9\u8d4b\u503c");
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put("url", (Object) XRenderManager.addQueryParams(jDWebView.getFinalUrl(), str));
                Log("xrenderClick params = " + jDJSONObject.toJSONString());
                XRenderUtils.dispatchJSEvent(jDWebView, WebPerfManager.XRENDER_CLICK, jDJSONObject);
                if (jDWebView.getXUiBinder() != null && jDWebView.getXUiBinder().getxRenderJS() != null) {
                    Log("\u7ed9xRenderClick JS\u6865\u8fd4\u56de\u5185\u5bb9\u8d4b\u503c  = " + jDJSONObject.toJSONString());
                    jDWebView.getXUiBinder().getxRenderJS().setxRenderClickParams(jDJSONObject.toJSONString());
                }
                jDWebView.getXRenderManager().setSendClickEvent(true);
                return true;
            }
            return false;
        }

        public void setLastPreRenderDataTypeTemp(int i2) {
            this.lastPreRenderDataTypeTemp = i2;
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x004d A[Catch: Exception -> 0x0059, TryCatch #0 {Exception -> 0x0059, blocks: (B:24:0x0003, B:26:0x0009, B:28:0x0013, B:30:0x0041, B:32:0x004d, B:33:0x0051, B:29:0x0028), top: B:37:0x0003 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void setPreRenderData(com.jd.libs.hybrid.HybridSDK.PreRender r4) {
            /*
                r3 = this;
                r0 = 0
                if (r4 == 0) goto L28
                java.util.List r1 = r4.getModules()     // Catch: java.lang.Exception -> L59
                if (r1 == 0) goto L28
                java.util.List r1 = r4.getModules()     // Catch: java.lang.Exception -> L59
                boolean r1 = r1.isEmpty()     // Catch: java.lang.Exception -> L59
                if (r1 != 0) goto L28
                java.lang.String r1 = "\u9884\u6e32\u67d3\u6570\u636e\u6b63\u5e38"
                Log(r1)     // Catch: java.lang.Exception -> L59
                r3.preRender = r4     // Catch: java.lang.Exception -> L59
                int r1 = r4.getMaxUseTimes()     // Catch: java.lang.Exception -> L59
                r3.maxUseTimes = r1     // Catch: java.lang.Exception -> L59
                r3.checkModules()     // Catch: java.lang.Exception -> L59
                r3.checkPage()     // Catch: java.lang.Exception -> L59
                goto L41
            L28:
                java.lang.String r1 = "\u9884\u6e32\u67d3\u6570\u636e\u4e3a\u7a7a"
                Log(r1)     // Catch: java.lang.Exception -> L59
                r3.preRender = r0     // Catch: java.lang.Exception -> L59
                r1 = 0
                r3.maxUseTimes = r1     // Catch: java.lang.Exception -> L59
                java.lang.String r1 = "\u6e05\u7a7a\u7f13\u5b58webview"
                Log(r1)     // Catch: java.lang.Exception -> L59
                r3.webViewOnDestroy()     // Catch: java.lang.Exception -> L59
                java.util.Map<java.lang.String, com.jingdong.common.web.ui.JDWebView> r1 = r3.mCachedWebViewMap     // Catch: java.lang.Exception -> L59
                r1.clear()     // Catch: java.lang.Exception -> L59
            L41:
                android.content.SharedPreferences r1 = com.jingdong.common.utils.CommonBase.getJdSharedPreferences()     // Catch: java.lang.Exception -> L59
                android.content.SharedPreferences$Editor r1 = r1.edit()     // Catch: java.lang.Exception -> L59
                java.lang.String r2 = "lastPreRenderData"
                if (r4 == 0) goto L51
                java.lang.String r0 = com.jd.framework.json.JDJSON.toJSONString(r4)     // Catch: java.lang.Exception -> L59
            L51:
                android.content.SharedPreferences$Editor r4 = r1.putString(r2, r0)     // Catch: java.lang.Exception -> L59
                r4.apply()     // Catch: java.lang.Exception -> L59
                goto L5e
            L59:
                java.lang.String r4 = "setPreRenderData \u9884\u6e32\u67d3\u6570\u636e\u5f02\u5e38"
                Log(r4)
            L5e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.xrender.XRender.setPreRenderData(com.jd.libs.hybrid.HybridSDK$PreRender):void");
        }

        public void setRenderUrl(String str) {
            if (this.needRender && !TextUtils.isEmpty(str)) {
                if (this.preRender == null) {
                    Log("\u9884\u6e32\u67d3\u6570\u636e\u4e3a\u7a7a");
                    return;
                }
                Uri parse = Uri.parse(str);
                for (HybridSDK.PreRenderModule preRenderModule : this.preRender.getModules()) {
                    if (!TextUtils.isEmpty(preRenderModule.getOriginalUrl()) && HybridUrlUtils.uriMatchHostPath(Uri.parse(preRenderModule.getOriginalUrl()), parse)) {
                        preload(preRenderModule.getOriginalUrl(), preRenderModule, "1", "\u4e1a\u52a1\u89e6\u53d1");
                    }
                }
                return;
            }
            Log("\u9884\u6e32\u67d3\u672a\u5b8c\u6210\uff0csetRenderUrl\u6570\u636e\u4e3a\u7a7a ");
        }

        public void updateModeAndType(String str) {
            updateModeAndType(str, false);
        }

        private XRender() {
            this.inited = false;
            this.useXRenderType = false;
            this.mCachedWebViewMap = new ConcurrentHashMap();
            this.mPreRenderWebViewCount = new ConcurrentHashMap();
            this.mRequireAppModeMap = null;
            this.mTypeMap = null;
            this.mDefaultRequireAppModeMap = null;
            this.mDefaultTypeMap = null;
            this.mOutOfCountExceps = new ConcurrentHashMap();
            this.mWrongABExceps = new ConcurrentHashMap();
            this.needRender = true;
            this.mainHandler = new Handler(Looper.getMainLooper());
            this.lastPreRenderDataTypeTemp = 0;
            this.lastPreRenderDataType = 0;
        }

        public static void Log(String str, String str2) {
            String str3;
            if (TextUtils.isEmpty(str)) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            if (TextUtils.isEmpty(str2)) {
                str3 = "";
            } else {
                str3 = ", url:" + str2;
            }
            sb.append(str3);
            XLog.d(TAG, null, sb.toString(), TAG);
        }

        private void updateModeAndType(String str, boolean z) {
            try {
                if (TextUtils.isEmpty(str)) {
                    this.useXRenderType = true;
                    return;
                }
                JSONObject jSONObject = new JSONObject(str);
                int length = jSONObject.length();
                if (jSONObject.length() == 0) {
                    this.mRequireAppModeMap = null;
                    this.mTypeMap = null;
                    if (z) {
                        this.mDefaultRequireAppModeMap = null;
                        this.mDefaultTypeMap = null;
                        return;
                    }
                    return;
                }
                this.mRequireAppModeMap = new ConcurrentHashMap(length);
                this.mTypeMap = new ConcurrentHashMap(length);
                if (z) {
                    this.mDefaultRequireAppModeMap = new ConcurrentHashMap(length);
                    this.mDefaultTypeMap = new ConcurrentHashMap(length);
                }
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String optString = jSONObject.optString(next);
                    if (!TextUtils.isEmpty(optString)) {
                        String[] split = optString.split(DYConstants.DY_REGEX_COMMA);
                        if (split.length == 2) {
                            String str2 = split[0];
                            String str3 = split[1];
                            this.mRequireAppModeMap.put(next, str2);
                            this.mTypeMap.put(next, str3);
                            if (z) {
                                this.mDefaultRequireAppModeMap.put(next, str2);
                                this.mDefaultTypeMap.put(next, str3);
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                Log("Err in updateModeAndType" + e2);
                ExceptionReporter.reportWebViewCommonError("xRender-updateModeAndType", "", "forDefault:" + z + ", jsonStr:" + str, ExceptionReporter.getStackStringFromException(e2));
            }
        }
    }
