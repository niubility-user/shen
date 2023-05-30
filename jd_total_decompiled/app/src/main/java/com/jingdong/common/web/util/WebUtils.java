package com.jingdong.common.web.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.utils.UnAndroidUtils;
import com.jd.libs.hybrid.HybridGenTokenSupporter;
import com.jd.libs.xconsole.XLog;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.ActivityNumController;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.GraySwitch;
import com.jingdong.common.QQWallet.QQWalletPayUtil;
import com.jingdong.common.R;
import com.jingdong.common.deeplinkhelper.DeepLinkOrderCenterHelper;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.login.LoginConstans;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.WebLoginUtil;
import com.jingdong.common.login.WebReqLoginTokenUtil;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.CommonUtil;
import com.jingdong.common.utils.JDPrivacyHelper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.common.utils.ProcessUtil;
import com.jingdong.common.utils.StringUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WebViewHelper;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.common.utils.pay.PayUtils;
import com.jingdong.common.web.Web64BitFixer;
import com.jingdong.common.web.WebDebug;
import com.jingdong.common.web.WebLoginHelper;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.WebEntity;
import com.jingdong.common.web.javainterface.impl.JDAppUnite;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.common.web.ui.ErrCallback;
import com.jingdong.common.web.ui.GenTokenCallback;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.ui.X5WebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.common.weixin.WeiXinEntity;
import com.jingdong.common.weixin.WeiXinPayUtil;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.constant.Constants;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.secure.Base64;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.jdsdk.utils.URLParamMap;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.jdtoast.ToastUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.mobileqq.openpay.data.pay.PayApi;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebBackForwardList;
import com.tencent.smtt.sdk.WebView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import jd.wjweblogin.common.listener.WJReqWebCookieCallBack;
import jd.wjweblogin.model.WJErrorResult;
import jd.wjweblogin.model.WJFailResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Keep
/* loaded from: classes12.dex */
public class WebUtils {
    public static String CALLBACK_NAME = null;
    private static final String DEFAULT_URL = "https://plogin.m.jd.com/jd-mlogin/static/html/appjmp_blank.html";
    public static final String HIT_BLACKLIST_REDIRECT_URL = "https://un.m.jd.com/st/html/gentokenWarning.html";
    private static final String JS_DISPATCH_EVENT = "try{;(function(){var event = new CustomEvent('%s', {'detail': %s}); window.dispatchEvent(event);})();} catch (e) {console && console.error('ERROR in dispatchEvent:'+eventName, e);}";
    public static final int RESULT_CODE_CONTACTS = 1009;
    public static final int RESULT_CODE_REFRESH = 101;
    public static final int RESULT_CODE_UNREFRESH = 100;
    private static List<Pair<String, String>> schemeNoReportList;
    public static boolean privacyAgreedOnAppStart = JDPrivacyHelper.isAcceptPrivacy(JdSdk.getInstance().getApplicationContext());
    private static final String TAG = WebUtils.class.getSimpleName();

    /* renamed from: com.jingdong.common.web.util.WebUtils$14 */
    /* loaded from: classes12.dex */
    public class AnonymousClass14 implements CommonBase.BrowserAllUrlListener {
        final /* synthetic */ BaseActivity val$activity;
        final /* synthetic */ String val$finalDecodedUrl;
        final /* synthetic */ JDWebView val$mJdWebView;
        final /* synthetic */ Object val$object;
        final /* synthetic */ WebEntity val$webEntity;

        AnonymousClass14(JDWebView jDWebView, Object obj, String str, BaseActivity baseActivity, WebEntity webEntity) {
            this.val$mJdWebView = jDWebView;
            this.val$object = obj;
            this.val$finalDecodedUrl = str;
            this.val$activity = baseActivity;
            this.val$webEntity = webEntity;
        }

        public static /* synthetic */ void a(JDWebView jDWebView, String str) {
            if (Log.D || WebLogHelper.showXLog) {
                jDWebView.xLogD("[genToken]\u76f4\u63a5\u4f7f\u7528webview\u52a0\u8f7dgentokenUrl\uff0c\u539f\u56e0\uff1aSwitchQuery\u5f00\u5173\u5173\u95ed");
            }
            jDWebView.loadUrl(str);
        }

        public static /* synthetic */ void b(String str, WebEntity webEntity, JDWebView jDWebView, BaseActivity baseActivity, Object obj) {
            WebUtils.gentokenRequest(str, webEntity, jDWebView, baseActivity, obj);
            jDWebView.showProgress();
        }

        public static /* synthetic */ void d(String str, WebEntity webEntity, JDWebView jDWebView, BaseActivity baseActivity, Object obj) {
            WebUtils.gentokenRequest(str, webEntity, jDWebView, baseActivity, obj);
            jDWebView.showProgress();
        }

        @Override // com.jingdong.common.utils.CommonBase.BrowserUrlListener
        public void onComplete(final String str) {
            if (this.val$mJdWebView == null) {
                return;
            }
            String str2 = "[genToken] function:gentoken completed: " + str;
            if (WebDebug.report) {
                WebDebug.log("webview", str2, this.val$object);
            }
            if (Log.D || WebLogHelper.showXLog) {
                this.val$mJdWebView.xLogD(str2);
            }
            if (SwitchQueryFetcher.getSwitchBooleanValue("hybridGentoken", false)) {
                if (WebDebug.report) {
                    WebDebug.log("webview", "[genToken] use hybrid's http to sync cookie", this.val$object);
                }
                System.currentTimeMillis();
                HybridGenTokenSupporter.loadGenTokenUrl(str, this.val$finalDecodedUrl, this.val$mJdWebView.getHybridOfflineLoader(), Looper.getMainLooper(), new HybridGenTokenSupporter.GenTokenCallback
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0058: INVOKE 
                      (r7v0 'str' java.lang.String)
                      (wrap: java.lang.String : 0x0047: IGET (r6v0 'this' com.jingdong.common.web.util.WebUtils$14 A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:11) com.jingdong.common.web.util.WebUtils.14.val$finalDecodedUrl java.lang.String)
                      (wrap: com.jd.libs.hybrid.HybridOfflineLoader : 0x004b: INVOKE 
                      (wrap: com.jingdong.common.web.ui.JDWebView : 0x0049: IGET (r6v0 'this' com.jingdong.common.web.util.WebUtils$14 A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] com.jingdong.common.web.util.WebUtils.14.val$mJdWebView com.jingdong.common.web.ui.JDWebView)
                     type: VIRTUAL call: com.jingdong.common.web.ui.JDWebView.getHybridOfflineLoader():com.jd.libs.hybrid.HybridOfflineLoader A[MD:():com.jd.libs.hybrid.HybridOfflineLoader (m), WRAPPED])
                      (wrap: android.os.Looper : 0x004f: INVOKE  type: STATIC call: android.os.Looper.getMainLooper():android.os.Looper A[MD:():android.os.Looper (c), WRAPPED])
                      (wrap: com.jd.libs.hybrid.HybridGenTokenSupporter$GenTokenCallback : 0x0055: CONSTRUCTOR 
                      (r6v0 'this' com.jingdong.common.web.util.WebUtils$14 A[IMMUTABLE_TYPE, THIS])
                      (r0 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r7v0 'str' java.lang.String A[DONT_INLINE])
                     A[MD:(com.jingdong.common.web.util.WebUtils$14, long, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.web.util.WebUtils.14.1.<init>(com.jingdong.common.web.util.WebUtils$14, long, java.lang.String):void type: CONSTRUCTOR)
                     type: STATIC call: com.jd.libs.hybrid.HybridGenTokenSupporter.loadGenTokenUrl(java.lang.String, java.lang.String, com.jd.libs.hybrid.HybridOfflineLoader, android.os.Looper, com.jd.libs.hybrid.HybridGenTokenSupporter$GenTokenCallback):void A[MD:(java.lang.String, java.lang.String, com.jd.libs.hybrid.HybridOfflineLoader, android.os.Looper, com.jd.libs.hybrid.HybridGenTokenSupporter$GenTokenCallback):void (m)] (LINE:11) in method: com.jingdong.common.web.util.WebUtils.14.onComplete(java.lang.String):void, file: classes12.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                    Method dump skipped, instructions count: 274
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.util.WebUtils.AnonymousClass14.onComplete(java.lang.String):void");
            }

            @Override // com.jingdong.common.utils.CommonBase.BrowserNewUrlListener
            public void onError(HttpError httpError) {
                final JDWebView jDWebView;
                if (WebDebug.report) {
                    WebDebug.log("webview", "[genToken] gentoken net error", this.val$object);
                }
                String str = "unknown";
                if (Log.E || WebLogHelper.showXLog) {
                    JDWebView jDWebView2 = this.val$mJdWebView;
                    StringBuilder sb = new StringBuilder();
                    sb.append("[genToken] gentoken net error code = ");
                    sb.append(httpError == null ? "unknown" : Integer.valueOf(httpError.getErrorCode()));
                    jDWebView2.xLogE(sb.toString());
                }
                URLParamMap uRLParamMap = this.val$webEntity.urlMap;
                String decodeUrl = WebUtils.decodeUrl(uRLParamMap == null ? "" : uRLParamMap.get((Object) RemoteMessageConst.TO));
                JDWebView jDWebView3 = this.val$mJdWebView;
                if (jDWebView3 != null && jDWebView3.getExistedXRenderManager() != null) {
                    XRender.Log("gentoken \u5931\u8d25\uff0c\u5220\u9664\u9884\u6e32\u67d3\u7f13\u5b58\u5e76\u9500\u6bc1webview");
                    this.val$mJdWebView.getExistedXRenderManager().clearData();
                    ExceptionReporter.reportWebViewCommonErrorNoIp("XRender_Error", decodeUrl, "3", "gentoken\u5931\u8d25");
                }
                WebLoginHelper.onGentokenFail();
                JDWebView jDWebView4 = this.val$mJdWebView;
                if (jDWebView4 != null) {
                    jDWebView4.addLoadEvent(WebWhiteScreenHolder.GENTOKEN_ERROR);
                    if (this.val$mJdWebView.getGenTokenCallback() != null) {
                        this.val$mJdWebView.getGenTokenCallback().onCallback(GenTokenCallback.State.GEN_TOKEN_FAIL);
                    }
                }
                BaseActivity baseActivity = this.val$activity;
                if (baseActivity != null && !baseActivity.isFinishing()) {
                    ToastUtils.shortToast(this.val$activity, this.val$activity.getString(R.string.m_error_tip) + "(gentoken)");
                }
                WebEntity webEntity = this.val$webEntity;
                double currentTimeMillis = System.currentTimeMillis();
                Double.isNaN(currentTimeMillis);
                webEntity.genToken_end = currentTimeMillis / 1000.0d;
                this.val$webEntity.genTokenFinished = true;
                JDWebView jDWebView5 = this.val$mJdWebView;
                if (jDWebView5 != null) {
                    jDWebView5.setLoadInterrupted(null);
                }
                if (httpError != null) {
                    str = httpError.getJsonCode() + "";
                }
                ExceptionReporter.reportWebPageError("Gentoken_Error", "gentoken request error", decodeUrl, str);
                PerformanceManager.getInstance().appendData(PerformanceManager.MERROR_CODE, str);
                PerformanceManager.getInstance().appendData("mloadType", "gentoken");
                PerformanceManager.getInstance().appendData(PerformanceManager.LOAD_URL, decodeUrl);
                PerformanceManager performanceManager = PerformanceManager.getInstance();
                WebEntity webEntity2 = this.val$webEntity;
                performanceManager.appendData("mloadingTime", String.valueOf(Math.round((webEntity2.genToken_end - webEntity2.genToken_start) * 1000.0d)));
                PerformanceManager.getInstance().appendData("isError", "1");
                PerformanceManager.getInstance().appendData("errMsg", "genToken\u5931\u8d25");
                PerformanceManager.getInstance().report();
                JDWebView jDWebView6 = this.val$mJdWebView;
                if (jDWebView6 != null) {
                    jDWebView6.appendWhiteScreenData(WebWhiteScreenHolder.GENTOKEN_ERR_MSG, "gentoken- errorCode= " + str);
                }
                final WebEntity webEntity3 = this.val$webEntity;
                if (!webEntity3.showErrView || (jDWebView = this.val$mJdWebView) == null) {
                    return;
                }
                final BaseActivity baseActivity2 = this.val$activity;
                if (baseActivity2 != null) {
                    final String str2 = this.val$finalDecodedUrl;
                    final Object obj = this.val$object;
                    baseActivity2.post(new Runnable() { // from class: com.jingdong.common.web.util.e
                        @Override // java.lang.Runnable
                        public final void run() {
                            r0.showErrView(1, null, new ErrCallback() { // from class: com.jingdong.common.web.util.g
                                @Override // com.jingdong.common.web.ui.ErrCallback
                                public final void onErrReload() {
                                    WebUtils.AnonymousClass14.b(r1, r2, r3, r4, r5);
                                }
                            });
                        }
                    });
                    return;
                }
                Handler handler = jDWebView.getHandler();
                final JDWebView jDWebView7 = this.val$mJdWebView;
                final String str3 = this.val$finalDecodedUrl;
                final WebEntity webEntity4 = this.val$webEntity;
                final BaseActivity baseActivity3 = this.val$activity;
                final Object obj2 = this.val$object;
                handler.post(new Runnable() { // from class: com.jingdong.common.web.util.h
                    @Override // java.lang.Runnable
                    public final void run() {
                        r0.showErrView(1, null, new ErrCallback() { // from class: com.jingdong.common.web.util.d
                            @Override // com.jingdong.common.web.ui.ErrCallback
                            public final void onErrReload() {
                                WebUtils.AnonymousClass14.d(r1, r2, r3, r4, r5);
                            }
                        });
                    }
                });
            }

            @Override // com.jingdong.common.utils.CommonBase.BrowserAllUrlListener
            public void onReady() {
                WebEntity webEntity = this.val$webEntity;
                double currentTimeMillis = System.currentTimeMillis();
                Double.isNaN(currentTimeMillis);
                webEntity.genToken_start = currentTimeMillis / 1000.0d;
                this.val$webEntity.genTokenFinished = false;
                JDWebView jDWebView = this.val$mJdWebView;
                if (jDWebView != null) {
                    jDWebView.setLoadInterrupted("gentoken");
                    this.val$mJdWebView.appendPerformanceData("gentokenStart", String.valueOf(System.currentTimeMillis()));
                }
            }
        }

        /* renamed from: com.jingdong.common.web.util.WebUtils$16 */
        /* loaded from: classes12.dex */
        public static /* synthetic */ class AnonymousClass16 {
            static final /* synthetic */ int[] $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel;

            static {
                int[] iArr = new int[ConsoleMessage.MessageLevel.values().length];
                $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel = iArr;
                try {
                    iArr[ConsoleMessage.MessageLevel.DEBUG.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.TIP.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.ERROR.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    $SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[ConsoleMessage.MessageLevel.WARNING.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
            }
        }

        static {
            schemeNoReportList = null;
            ArrayList arrayList = new ArrayList();
            schemeNoReportList = arrayList;
            arrayList.add(new Pair("http", ""));
            schemeNoReportList.add(new Pair<>("https", ""));
            schemeNoReportList.add(new Pair<>("openapp.jdmobile", ""));
            schemeNoReportList.add(new Pair<>("openjd", ""));
        }

        /* JADX WARN: Removed duplicated region for block: B:142:0x005b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static String addBabelCustomParams(String str, Bundle bundle) {
            String str2;
            URLParamMap uRLParamMap;
            String str3 = null;
            try {
                str2 = (String) Class.forName("com.jingdong.common.babelrn.utils.M2BabelUtil").getMethod("getBabelParam", new Class[0]).invoke(null, new Object[0]);
            } catch (Throwable th) {
                th.printStackTrace();
                str2 = "";
            }
            if (!TextUtils.isEmpty(str2)) {
                String mergeUrlAndQuery = !TextUtils.isEmpty(str) ? mergeUrlAndQuery(str, "tttparams", str2) : null;
                if (bundle != null) {
                    try {
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e(TAG, e2.getMessage(), e2);
                        }
                    }
                    if (bundle.containsKey("urlParamMap")) {
                        uRLParamMap = ((SerializableContainer) bundle.getSerializable("urlParamMap")).getMap();
                        if (uRLParamMap != null) {
                            String string = bundle.getString("urlAction");
                            if (TextUtils.isEmpty(string)) {
                                string = RemoteMessageConst.TO;
                            }
                            if (TextUtils.isEmpty(mergeUrlAndQuery)) {
                                try {
                                    str3 = URLDecoder.decode(uRLParamMap.get((Object) string), "utf-8");
                                } catch (Exception e3) {
                                    if (OKLog.E) {
                                        OKLog.e(TAG, e3.getMessage(), e3);
                                    }
                                }
                                if (!TextUtils.isEmpty(str3)) {
                                    mergeUrlAndQuery = mergeUrlAndQuery(str3, "tttparams", str2);
                                }
                            }
                            if (!TextUtils.isEmpty(mergeUrlAndQuery)) {
                                uRLParamMap.put(string, mergeUrlAndQuery);
                                SerializableContainer serializableContainer = new SerializableContainer();
                                serializableContainer.setMap(uRLParamMap);
                                bundle.putSerializable("urlParamMap", serializableContainer);
                            }
                        }
                        str3 = mergeUrlAndQuery;
                        if (bundle.containsKey("url") && !TextUtils.isEmpty(str3)) {
                            bundle.putString("url", str3);
                        }
                    }
                    uRLParamMap = null;
                    if (uRLParamMap != null) {
                    }
                    str3 = mergeUrlAndQuery;
                    if (bundle.containsKey("url")) {
                        bundle.putString("url", str3);
                    }
                } else {
                    str3 = mergeUrlAndQuery;
                }
            }
            return TextUtils.isEmpty(str3) ? str : str3;
        }

        public static String addCustomParams(String str, Bundle bundle) {
            if (TextUtils.isEmpty(str)) {
                str = getUrl(bundle);
            }
            String babelActivityId = WebViewHelper.getBabelActivityId(str);
            if (bundle != null) {
                bundle.putBoolean(WebEntity.ADD_CUSTOM_PARAMS_CHECKED, true);
            }
            if (SwitchQueryFetcher.getSwitchBooleanValue("addHeightCustomParamsSwitch", false)) {
                str = addHeightCustomParams(str, bundle);
            }
            return !TextUtils.isEmpty(babelActivityId) ? addBabelCustomParams(str, bundle) : str;
        }

        public static String addHeightCustomParams(String str, Bundle bundle) {
            String str2;
            if (bundle == null) {
                return addStatusBarHeightParams(str);
            }
            String string = bundle.getString("url");
            if (TextUtils.isEmpty(string)) {
                str2 = "";
            } else {
                str2 = addStatusBarHeightParams(string);
                bundle.putString("url", str2);
            }
            URLParamMap uRLParamMap = null;
            String string2 = bundle.getString("urlAction");
            try {
                if (bundle.containsKey("urlParamMap")) {
                    uRLParamMap = ((SerializableContainer) bundle.getSerializable("urlParamMap")).getMap();
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2.getMessage(), e2);
                }
            }
            if (uRLParamMap != null) {
                try {
                    if (TextUtils.isEmpty(string2)) {
                        string2 = RemoteMessageConst.TO;
                    }
                    if (TextUtils.isEmpty(str2)) {
                        str2 = addStatusBarHeightParams(URLDecoder.decode(uRLParamMap.get((Object) string2), "utf-8"));
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        uRLParamMap.put(string2, str2);
                        SerializableContainer serializableContainer = new SerializableContainer();
                        serializableContainer.setMap(uRLParamMap);
                        bundle.putSerializable("urlParamMap", serializableContainer);
                    }
                } catch (Exception e3) {
                    if (OKLog.E) {
                        OKLog.e(TAG, e3.getMessage(), e3);
                    }
                }
            }
            if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(string)) {
                return addStatusBarHeightParams(str);
            }
            return TextUtils.isEmpty(str2) ? string : str2;
        }

        public static String addStatusBarHeightParams(String str) {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            if (hostListWithKeyWord(str, Uri.parse(str).getHost(), SwitchQueryFetcher.getSwitchStringValue("addStatusBarHeightList", "").split(";"))) {
                return mergeUrlAndQuery(mergeUrlAndQuery(str, "navh", DPIUtil.px2dip(NavigatorHolder.NAVI_BAR_HEIGHT) + ""), "stath", DPIUtil.px2dip((float) UnStatusBarTintUtil.getStatusBarHeight(JdSdk.getInstance().getApplicationContext())) + "");
            }
            XRender.Log(str, "\u672a\u914d\u7f6eaddStatusBarHeightList\u5f00\u5173\u767d\u540d\u5355");
            return str;
        }

        private static JSONArray arrayToJsonArray(Object obj) throws JSONException {
            if (obj.getClass().isArray()) {
                int length = Array.getLength(obj);
                JSONArray jSONArray = new JSONArray();
                for (int i2 = 0; i2 < length; i2++) {
                    jSONArray.put(jsonObjectWrap(Array.get(obj, i2)));
                }
                return jSONArray;
            }
            throw new JSONException("Not a primitive array: " + obj.getClass());
        }

        /* JADX WARN: Removed duplicated region for block: B:298:0x001c A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static /* synthetic */ void b(JDWebView jDWebView, Bitmap bitmap, double d) {
            Bitmap bitmap2;
            Bitmap createBitmap;
            WebWhiteScreenHolder whiteScreenHolder;
            if (jDWebView == null) {
                return;
            }
            Bitmap bitmap3 = null;
            try {
                if (bitmap != null) {
                    try {
                        if (!bitmap.isRecycled()) {
                            createBitmap = Bitmap.createBitmap(bitmap);
                            if (createBitmap != null) {
                                try {
                                    if (!createBitmap.isRecycled()) {
                                        Matrix matrix = new Matrix();
                                        matrix.setScale(0.1f, 0.1f);
                                        bitmap3 = Bitmap.createBitmap(createBitmap, 0, 0, createBitmap.getWidth(), createBitmap.getHeight(), matrix, true);
                                        if (bitmap3 != null && !bitmap3.isRecycled()) {
                                            int width = bitmap3.getWidth();
                                            int height = bitmap3.getHeight();
                                            float f2 = 0.0f;
                                            float f3 = 0.0f;
                                            for (int i2 = 0; i2 < width; i2++) {
                                                for (int i3 = 0; i3 < height; i3++) {
                                                    int pixel = bitmap3.getPixel(i2, i3);
                                                    int red = Color.red(pixel);
                                                    int green = Color.green(pixel);
                                                    int blue = Color.blue(pixel);
                                                    if (red > 244 && green > 244 && blue > 244) {
                                                        f3 += 1.0f;
                                                    }
                                                    f2 += 1.0f;
                                                }
                                            }
                                            double d2 = f2 != 0.0f ? f3 / f2 : 0.0d;
                                            WebWhiteScreenHolder.xLog("blankThreshold = " + d2 + ",switch - blankThreshold" + d);
                                            if (d2 > d && (whiteScreenHolder = jDWebView.getWhiteScreenHolder()) != null) {
                                                whiteScreenHolder.sendMta(jDWebView);
                                            }
                                        }
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    bitmap2 = bitmap3;
                                    bitmap3 = createBitmap;
                                    try {
                                        WebWhiteScreenHolder.sendException(th, 1);
                                        if (bitmap3 != null) {
                                            bitmap3.recycle();
                                        }
                                        if (bitmap2 != null) {
                                            return;
                                        }
                                        return;
                                    } catch (Throwable th2) {
                                        if (bitmap3 != null) {
                                            try {
                                                if (!bitmap3.isRecycled()) {
                                                    bitmap3.recycle();
                                                }
                                            } catch (Throwable th3) {
                                                WebWhiteScreenHolder.sendException(th3, 2);
                                                throw th2;
                                            }
                                        }
                                        if (bitmap2 != null && !bitmap2.isRecycled()) {
                                            bitmap2.recycle();
                                        }
                                        throw th2;
                                    }
                                }
                            }
                            if (createBitmap != null && !createBitmap.isRecycled()) {
                                createBitmap.recycle();
                            }
                            if (bitmap3 != null || bitmap3.isRecycled()) {
                            }
                            bitmap3.recycle();
                            return;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        bitmap2 = null;
                        WebWhiteScreenHolder.sendException(th, 1);
                        if (bitmap3 != null && !bitmap3.isRecycled()) {
                            bitmap3.recycle();
                        }
                        if (bitmap2 != null || bitmap2.isRecycled()) {
                            return;
                        }
                        bitmap2.recycle();
                        return;
                    }
                }
                createBitmap = null;
                if (createBitmap != null) {
                }
                if (createBitmap != null) {
                    createBitmap.recycle();
                }
                if (bitmap3 != null) {
                }
            } catch (Throwable th5) {
                WebWhiteScreenHolder.sendException(th5, 2);
            }
        }

        public static boolean canPassGentoken(URLParamMap uRLParamMap) {
            String str = TAG;
            Log.d(str, "check pass Gentoken");
            if (!privacyAgreedOnAppStart && !LoginUserBase.hasLogin() && WebLoginHelper.getApiLoginCount() == 0 && isMultiScreen()) {
                if (Log.D || WebLogHelper.showXLog) {
                    XLog.d(str, null, "[genToken]\u4e0d\u6253\u901a\uff1a\u542f\u52a8\u65f6\u672a\u540c\u610f\u9690\u79c1\uff0c\u672a\u767b\u5f55\uff0c\u53ef\u4e0d\u6253\u901a", "webview");
                }
                return true;
            } else if (!WebReqLoginTokenUtil.reqLoginTokenConfig()) {
                if (Log.D || WebLogHelper.showXLog) {
                    XLog.d(str, null, "[genToken]\u4e0d\u6253\u901a\uff1a\u767b\u5f55\u4fa7\u63a7\u5236\u4e0d\u6253\u901a", "webview");
                }
                return true;
            } else if (uRLParamMap != null) {
                String str2 = uRLParamMap.get(RemoteMessageConst.TO);
                if (TextUtils.isEmpty(str2)) {
                    if (Log.D || WebLogHelper.showXLog) {
                        XLog.d(str, null, "[genToken]\u6253\u901a\uff1aurl\u662f\u7a7a", "webview");
                    }
                    return false;
                }
                try {
                    String decode = Uri.decode(str2);
                    Uri parse = Uri.parse(decode);
                    if ("1".equals(parse.getQueryParameter("passgtk"))) {
                        if (Log.D || WebLogHelper.showXLog) {
                            XLog.d(str, null, "[genToken]\u4e0d\u6253\u901a\uff1apassgtk=1", "webview");
                        }
                        return true;
                    }
                    String host = parse.getHost();
                    if (TextUtils.isEmpty(host)) {
                        if (Log.D || WebLogHelper.showXLog) {
                            XLog.d(str, null, "[genToken]\u6253\u901a\uff1ahost\u662f\u7a7a", "webview");
                        }
                        return false;
                    } else if (hostEndWithListKeyWord(decode, host, SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.WEB_GENTOKEN_BLACK_LIST, "").split(";"))) {
                        if (Log.D || WebLogHelper.showXLog) {
                            XLog.d(str, null, "[genToken]\u6253\u901a\uff1a\u547d\u4e2dgentokenBlackList", "webview");
                        }
                        return false;
                    } else if (hostEndWithListKeyWord(decode, host, SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.WEB_PASS_GENTOKEN_LIST, "").split(";"))) {
                        if (Log.D || WebLogHelper.showXLog) {
                            XLog.d(str, null, "[genToken]\u4e0d\u6253\u901a\uff1a\u547d\u4e2dpassGentokenList", "webview");
                        }
                        return true;
                    } else {
                        int switchIntValue = SwitchQueryFetcher.getSwitchIntValue(SwitchQueryFetcher.WEB_COOKIE_EXPIRE, 0);
                        if (switchIntValue <= 0 || System.currentTimeMillis() - WebLoginHelper.getLastGentokenTime() >= switchIntValue) {
                            return false;
                        }
                        if (hostEndWithListKeyWord(decode, host, SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.WEB_PASS_GENTOKEN_LIST1, "").split(";"))) {
                            if (!Log.D) {
                                if (WebLogHelper.showXLog) {
                                }
                                return true;
                            }
                            XLog.d(str, null, "[genToken]\u4e0d\u6253\u901a\uff1a\u547d\u4e2dpassGentokenList1", "webview");
                            return true;
                        } else if (hostEndWithListKeyWord(decode, host, SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.WEB_PASS_GENTOKEN_LIST2, "").split(";"))) {
                            if (!Log.D) {
                                if (WebLogHelper.showXLog) {
                                }
                                return true;
                            }
                            XLog.d(str, null, "[genToken]\u4e0d\u6253\u901a\uff1a\u547d\u4e2dpassGentokenList2", "webview");
                            return true;
                        } else if (uRLParamMap.containsKey("webHybridHasOffConfig") && "1".equals(uRLParamMap.get("webHybridHasOffConfig"))) {
                            if (!Log.D) {
                                if (WebLogHelper.showXLog) {
                                }
                                return true;
                            }
                            XLog.d(str, null, "[genToken]\u4e0d\u6253\u901a\uff1ahybrid\u63a7\u5236\u514d\u6253\u901a", "webview");
                            return true;
                        } else {
                            return false;
                        }
                    }
                } catch (Exception e2) {
                    String str3 = TAG;
                    Log.e(str3, e2.getMessage(), e2);
                    if (Log.E || WebLogHelper.showXLog) {
                        XLog.e(str3, null, "[genToken]\u6253\u901a\uff1a\u5224\u65ad\u51fa\u9519\uff0ce: " + e2.toString(), "webview");
                        return false;
                    }
                    return false;
                }
            } else {
                return false;
            }
        }

        public static boolean canUseDarkMode(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                return hostListWithKeyWord(str, Uri.parse(str).getHost(), SwitchQueryFetcher.getSwitchStringValue("DarkModeWhiteList", "").split(";"));
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }

        public static boolean checkHostCloseListForNative(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                Uri parse = Uri.parse(str);
                return exactHostList(parse.getHost(), SwitchQueryFetcher.getSwitchStringValue("wbNativeCloseList", "3.cn").split(DYConstants.DY_REGEX_COMMA));
            } catch (Exception e2) {
                Log.d(TAG, e2.getMessage());
                return false;
            }
        }

        public static boolean checkUrlInBlackList(String str) {
            Uri parse;
            if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null) {
                return false;
            }
            try {
                String switchStringValue = SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.WEB_HOST_BLACK_LIST, "");
                if (TextUtils.isEmpty(switchStringValue)) {
                    return false;
                }
                return hostList(parse.getHost(), switchStringValue.split(DYConstants.DY_REGEX_COMMA));
            } catch (Exception e2) {
                Log.d(TAG, e2.getMessage());
                return false;
            }
        }

        public static boolean checkUrlInIllegalList(String str) {
            Uri parse;
            if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null) {
                return false;
            }
            try {
                String switchStringValue = SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.WEB_HOST_ILLEGALURL_BLCK_LIST, "");
                if (TextUtils.isEmpty(switchStringValue)) {
                    return false;
                }
                return hostListWithKeyWord(str, parse.getHost(), switchStringValue.split(";"));
            } catch (Exception e2) {
                Log.d(TAG, e2.getMessage());
                return false;
            }
        }

        public static void clearCookieWhenError(@Nullable Uri uri) {
            int switchIntValue = SwitchQueryFetcher.getSwitchIntValue("cookieMax", 0);
            if (switchIntValue <= 0) {
                if (Log.D) {
                    Log.d(TAG, "ClearWebCookie: cookieMax <= 0\uff0c\u4e0d\u6267\u884c\u6e05\u7406cookie");
                    return;
                }
                return;
            }
            String str = null;
            if (uri != null) {
                try {
                    str = uri.getHost();
                } catch (Throwable th) {
                    if (Log.E) {
                        Log.e(TAG, th.getMessage(), th);
                        return;
                    }
                    return;
                }
            }
            if (!TextUtils.isEmpty(str) && !str.endsWith(".jd.com")) {
                if (Log.D) {
                    Log.d(TAG, "ClearWebCookie: host\u975e\u7a7a\uff0c\u4f46\u4e0d\u662f.jd.com\u7ed3\u5c3e\uff0c\u4e0d\u6267\u884c\u6e05\u7406cookie\uff0c\u5f53\u524dhost = " + str);
                    return;
                }
                return;
            }
            clearJdComCookiesWhenTooLarge(switchIntValue);
        }

        public static void clearCookieWhenStartup() {
            if (!SwitchQueryFetcher.getSwitchBooleanValue("cookieStartupClean", false)) {
                if (Log.D) {
                    Log.d(TAG, "ClearWebCookie: cookieStartupClean\u5f00\u5173\u5df2\u5173\u95ed\uff0c\u4e0d\u6267\u884c\u542f\u52a8\u65f6\u6e05\u7406cookie");
                    return;
                }
                return;
            }
            int switchIntValue = SwitchQueryFetcher.getSwitchIntValue("cookieMax", 0);
            if (switchIntValue <= 0) {
                if (Log.D) {
                    Log.d(TAG, "ClearWebCookie: cookieMax <= 0\uff0c\u4e0d\u6267\u884c\u6e05\u7406cookie");
                    return;
                }
                return;
            }
            try {
                clearJdComCookiesWhenTooLarge(switchIntValue);
            } catch (Throwable th) {
                if (Log.E) {
                    Log.e(TAG, th.getMessage(), th);
                }
            }
        }

        public static void clearJdComCookies() {
            String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("cookieAllowKeys", "");
            String[] split = !TextUtils.isEmpty(switchStringValue) ? switchStringValue.split(DYConstants.DY_REGEX_COMMA) : null;
            if (split != null && split.length != 0) {
                if (Log.D) {
                    Log.d(TAG, "ClearWebCookie: \u4f7f\u7528cookie\u767d\u540d\u5355\u6267\u884c\u6e05\u7406");
                }
                CookieUtil.clearX5CookiesExceptThan("https://jd.com", split);
                return;
            }
            if (Log.D) {
                Log.d(TAG, "ClearWebCookie: cookie\u767d\u540d\u5355\u4e3a\u7a7a");
            }
            String switchStringValue2 = SwitchQueryFetcher.getSwitchStringValue("cookieBlackKeys", "");
            String[] split2 = TextUtils.isEmpty(switchStringValue2) ? null : switchStringValue2.split(DYConstants.DY_REGEX_COMMA);
            if (split2 != null && split2.length != 0) {
                if (Log.D) {
                    Log.d(TAG, "ClearWebCookie: \u4f7f\u7528cookie\u9ed1\u540d\u5355\u6267\u884c\u6e05\u7406");
                }
                CookieUtil.clearCertainX5Cookies("https://jd.com", split2);
            } else if (Log.D) {
                Log.d(TAG, "ClearWebCookie: cookie\u9ed1\u540d\u5355\u4e3a\u7a7a\uff0c\u4e0d\u6267\u884c\u6e05\u7406\u529f\u80fd");
            }
        }

        public static void clearJdComCookiesWhenTooLarge(int i2) throws UnsupportedEncodingException {
            String cookie = CookieManager.getInstance().getCookie("https://jd.com");
            int length = cookie != null ? cookie.getBytes("UTF-8").length : 0;
            if (Log.D) {
                Log.d(TAG, String.format(Locale.getDefault(), "ClearWebCookie: \u5f53\u524djd.com\u7684cookie\u957f\u5ea6: %d bytes, \u89e6\u53d1\u957f\u5ea6: %d bytes", Integer.valueOf(length), Integer.valueOf(i2)));
            }
            if (length <= i2) {
                if (Log.D) {
                    Log.d(TAG, "ClearWebCookie: \u65e0\u9700\u6e05\u7406cookie\uff0c\u957f\u5ea6\u672a\u8d85\u51fa");
                    return;
                }
                return;
            }
            if (Log.D) {
                Log.d(TAG, "ClearWebCookie: \u89e6\u53d1\u6e05\u7406cookie");
            }
            clearJdComCookies();
        }

        public static String decodeUrl(String str) {
            if (StringUtil.isEmpty(str)) {
                return "";
            }
            try {
                return URLDecoder.decode(str, "UTF-8");
            } catch (Exception unused) {
                return str;
            }
        }

        public static void dispatchEvent(JDWebView jDWebView, String str) {
            dispatchEvent(jDWebView, str, null);
        }

        public static void doPayFail(final IWebUiBinder iWebUiBinder, final String str) {
            String string = iWebUiBinder.getBaseActivity().getString(R.string.pay_failure);
            if ("4".equals(str)) {
                string = iWebUiBinder.getBaseActivity().getString(R.string.webview_unpay_failure);
            } else if ("12".equals(str)) {
                string = iWebUiBinder.getBaseActivity().getString(R.string.webview_jdpay_failure);
            } else if ("10".equals(str)) {
                string = iWebUiBinder.getBaseActivity().getString(R.string.webview_weixinpay_failure);
            } else if ("13".equals(str)) {
                string = iWebUiBinder.getBaseActivity().getString(R.string.webview_QQPay_failure);
            }
            final JDDialog createJdDialogWithStyle2 = JDDialogFactory.getInstance().createJdDialogWithStyle2(iWebUiBinder.getBaseActivity(), string, iWebUiBinder.getBaseActivity().getString(R.string.cancel), iWebUiBinder.getBaseActivity().getString(R.string.retry));
            createJdDialogWithStyle2.setOnRightButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.util.WebUtils.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PayApi payApi;
                    if ("4".equals(str)) {
                        CommonUtil.pay(iWebUiBinder.getBaseActivity(), CommonUtil.getUnpayTN());
                    } else if ("12".equals(str)) {
                        JumpUtils.reDoJDPay(iWebUiBinder.getBaseActivity());
                    } else if ("10".equals(str)) {
                        WeiXinEntity weiXinInfo = WeiXinPayUtil.getWeiXinInfo();
                        if (weiXinInfo != null) {
                            WeiXinPayUtil.doWeiXinPay(weiXinInfo);
                        }
                    } else if ("13".equals(str) && (payApi = QQWalletPayUtil.getPayApi()) != null) {
                        QQWalletPayUtil.doQQPay(payApi);
                    }
                    createJdDialogWithStyle2.cancel();
                }
            });
            createJdDialogWithStyle2.setOnLeftButtonClickListener(new View.OnClickListener() { // from class: com.jingdong.common.web.util.WebUtils.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    createJdDialogWithStyle2.cancel();
                }
            });
            createJdDialogWithStyle2.show();
        }

        private static void downloadAndSaveImage(BaseActivity baseActivity, String str) {
            String str2;
            String str3;
            try {
                String substring = str.substring(str.lastIndexOf("/") + 1);
                str2 = substring.substring(0, substring.lastIndexOf(OrderISVUtil.MONEY_DECIMAL));
            } catch (Exception unused) {
                str2 = "";
            }
            if (TextUtils.isEmpty(str2)) {
                str3 = System.currentTimeMillis() + ".png";
            } else {
                str3 = str2 + ".png";
            }
            if (str.startsWith("data:image/png;base64,")) {
                saveBase64Img(baseActivity, str.substring(22, str.length()), str3);
            } else if (str.startsWith("data:image/jpeg;base64,")) {
                saveBase64Img(baseActivity, str.substring(23, str.length()), str3);
            } else {
                FileGuider fileGuider = new FileGuider();
                fileGuider.setSpace(2);
                fileGuider.setImmutable(false);
                fileGuider.setFileName(str3);
                fileGuider.setMode(1);
                HttpSetting httpSetting = new HttpSetting();
                httpSetting.setUrl(str);
                httpSetting.setCacheMode(0);
                httpSetting.setType(500);
                httpSetting.setSavePath(fileGuider);
                httpSetting.setNotifyUser(true);
                httpSetting.setEffect(1);
                httpSetting.setBreakpointTransmission(false);
                httpSetting.setAttempts(1);
                httpSetting.setListener(new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.web.util.WebUtils.9
                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                    public void onEnd(HttpResponse httpResponse) {
                        if (httpResponse == null || httpResponse.getSaveFile() == null) {
                            return;
                        }
                        File saveFile = httpResponse.getSaveFile();
                        if (OKLog.D) {
                            OKLog.d(WebUtils.TAG, "get httpsetting save file path:" + saveFile.getPath());
                        }
                        if (MediaUtils.savePictureToAlbum(JdSdk.getInstance().getApplicationContext(), saveFile)) {
                            ToastUtils.shortToast(JdSdk.getInstance().getApplicationContext(), "\u5b58\u50a8\u6210\u529f,\u53ef\u5728\u76f8\u518c\u4e2d\u67e5\u770b");
                        }
                        if (saveFile.exists()) {
                            saveFile.delete();
                        }
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                    public void onError(HttpError httpError) {
                        ToastUtils.shortToast(JdSdk.getInstance().getApplicationContext(), "\u5b58\u50a8\u5931\u8d25,\u8bf7\u91cd\u8bd5");
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
                    public void onPause() {
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                    public void onProgress(int i2, int i3) {
                    }

                    @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                    public void onStart() {
                    }
                });
                HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
            }
        }

        private static HttpRequest downloadAndSaveMedia(final IWebUiBinder iWebUiBinder, final String str, final String str2, final String str3, HttpGroup httpGroup) {
            String str4 = System.currentTimeMillis() + "";
            FileGuider fileGuider = new FileGuider();
            fileGuider.setSpace(2);
            fileGuider.setImmutable(false);
            fileGuider.setFileName(str4);
            fileGuider.setMode(1);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setUrl(str);
            httpSetting.setCacheMode(0);
            httpSetting.setType(500);
            httpSetting.setSavePath(fileGuider);
            httpSetting.setNotifyUser(true);
            httpSetting.setEffect(1);
            httpSetting.setBreakpointTransmission(false);
            httpSetting.setAttempts(1);
            httpSetting.setListener(new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.web.util.WebUtils.11
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    if (httpResponse == null || httpResponse.getSaveFile() == null) {
                        return;
                    }
                    File saveFile = httpResponse.getSaveFile();
                    IWebUiBinder iWebUiBinder2 = iWebUiBinder;
                    if (!(iWebUiBinder2 != null && MediaUtils.saveVideoToAlbum(iWebUiBinder2.getBaseActivity(), saveFile))) {
                        IWebUiBinder iWebUiBinder3 = iWebUiBinder;
                        if (iWebUiBinder3 != null) {
                            WebUtils.sendJSONStr2M(iWebUiBinder3, str2, AlbumSaveHelper.stringfyJSonData("-1", "", "fail", str3));
                        }
                    } else {
                        IWebUiBinder iWebUiBinder4 = iWebUiBinder;
                        if (iWebUiBinder4 != null) {
                            WebUtils.sendJSONStr2M(iWebUiBinder4, str2, AlbumSaveHelper.stringfyJSonData("0", "", "success", str3));
                        }
                    }
                    IWebUiBinder iWebUiBinder5 = iWebUiBinder;
                    JDAppUnite jDAppUnite = iWebUiBinder5 != null ? (JDAppUnite) iWebUiBinder5.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.JDAPPUNITE) : null;
                    if (jDAppUnite != null) {
                        jDAppUnite.getRequests().remove(str + str3);
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    IWebUiBinder iWebUiBinder2 = iWebUiBinder;
                    if (iWebUiBinder2 != null) {
                        WebUtils.sendJSONStr2M(iWebUiBinder2, str2, AlbumSaveHelper.stringfyJSonData("0", "", httpError.toString(), str3));
                        JDAppUnite jDAppUnite = (JDAppUnite) iWebUiBinder.getJavaInterfaceObj(WebUiConstans.JavaInterfaceNames.JDAPPUNITE);
                        if (jDAppUnite != null) {
                            jDAppUnite.getRequests().remove(str + str3);
                        }
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
                public void onPause() {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                    IWebUiBinder iWebUiBinder2 = iWebUiBinder;
                    if (iWebUiBinder2 != null) {
                        WebUtils.sendJSONStr2M(iWebUiBinder2, str2, AlbumSaveHelper.stringfyJSonData("1", "", "start", str3));
                    }
                }
            });
            return httpGroup.add(httpSetting);
        }

        public static void evaluateJavascript(IWebUiBinder iWebUiBinder, String str, String str2) {
            if (iWebUiBinder.getJdWebView() == null || iWebUiBinder.getJdWebView().getWebView() == null) {
                return;
            }
            iWebUiBinder.getJdWebView().getWebView().evaluateJavascript(str + "('" + str2 + "');", (ValueCallback<String>) null);
            if (Log.D) {
                Log.d(TAG, "sendJSONToM, injectJs--> javascript:" + str + "('" + str2 + "');");
            }
        }

        private static boolean exactHostList(String str, String[] strArr) {
            if (strArr != null && strArr.length > 0) {
                for (String str2 : strArr) {
                    if (str != null && !TextUtils.isEmpty(str2) && str.equalsIgnoreCase(str2)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static void fix64() {
            boolean z;
            try {
                z = JdSdk.getInstance().isArm64Only();
            } catch (Throwable unused) {
                z = false;
            }
            fix64(z);
        }

        public static void forwardSuccessPage(final IWebUiBinder iWebUiBinder) {
            PayUtils.doPayFinishForward(iWebUiBinder.getWebEntity().payID, new CommonBase.BrowserNewUrlListener() { // from class: com.jingdong.common.web.util.WebUtils.8
                @Override // com.jingdong.common.utils.CommonBase.BrowserUrlListener
                public void onComplete(final String str) {
                    if (Log.I) {
                        Log.i(WebUtils.TAG, "doPayFinishForward.url=" + str);
                    }
                    iWebUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.util.WebUtils.8.1
                        {
                            AnonymousClass8.this = this;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                if (iWebUiBinder.getJdWebView() == null || TextUtils.isEmpty(str)) {
                                    return;
                                }
                                iWebUiBinder.getJdWebView().loadUrl(str);
                            } catch (Exception unused) {
                                WebUtils.gotoOrderListActivity(iWebUiBinder.getBaseActivity());
                            }
                        }
                    });
                }

                @Override // com.jingdong.common.utils.CommonBase.BrowserNewUrlListener
                public void onError(HttpError httpError) {
                    WebUtils.gotoOrderListActivity(iWebUiBinder.getBaseActivity());
                }
            });
        }

        public static void gentoken(WebEntity webEntity, JDWebView jDWebView, BaseActivity baseActivity, Object obj) {
            URLParamMap uRLParamMap;
            String str;
            if (webEntity == null || jDWebView == null) {
                return;
            }
            if (jDWebView.isHybridPassGenToken()) {
                webEntity.urlMap.put("webHybridHasOffConfig", "1");
            }
            if (canPassGentoken(webEntity.urlMap)) {
                String loadUrlIgnoreGentoken = getLoadUrlIgnoreGentoken(webEntity.urlMap);
                if (WebDebug.report) {
                    WebDebug.log("webview", "[genToken] Can pass gentoken: " + loadUrlIgnoreGentoken, obj);
                }
                if (Log.D || WebLogHelper.showXLog) {
                    jDWebView.xLogD("[genToken] Can pass gentoken, url: " + loadUrlIgnoreGentoken);
                }
                jDWebView.appendPerformanceData(WebPerfManager.GENTOKEN_TYPE, "1");
                jDWebView.loadUrl(loadUrlIgnoreGentoken);
                jDWebView.addLoadEvent(WebWhiteScreenHolder.CAN_PASS_GENTOKEN);
                return;
            }
            String str2 = null;
            if (webEntity.urlMap != null) {
                if (TextUtils.isEmpty(webEntity.action)) {
                    uRLParamMap = webEntity.urlMap;
                    str = RemoteMessageConst.TO;
                } else {
                    uRLParamMap = webEntity.urlMap;
                    str = webEntity.action;
                }
                str2 = uRLParamMap.get((Object) str);
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        str2 = Uri.decode(str2);
                    } catch (Exception unused) {
                    }
                }
            }
            if (WebDebug.report) {
                WebDebug.log("webview", "[genToken] Ready to gentoken: " + str2, obj);
            }
            if (Log.D || WebLogHelper.showXLog) {
                jDWebView.xLogD("[genToken] Ready to gentoken, url: " + str2);
            }
            jDWebView.appendPerformanceData(WebPerfManager.GENTOKEN_TYPE, "0");
            gentokenRequest(str2, webEntity, jDWebView, baseActivity, obj);
        }

        public static void gentokenRequest(String str, WebEntity webEntity, JDWebView jDWebView, BaseActivity baseActivity, Object obj) {
            if (webEntity == null || jDWebView == null) {
                return;
            }
            CommonBase.queryBrowserUrlSupportNoLbs(webEntity.action, webEntity.urlMap, new AnonymousClass14(jDWebView, obj, str, baseActivity, webEntity));
        }

        public static AddressGlobal getAddressGlobalWithBaseSceneId() {
            if (SwitchQueryFetcher.getSwitchBooleanValue("JDLBSSceneIdEnable", false)) {
                return AddressUtil.getAddressGlobal("basicShoppingProcess");
            }
            return AddressUtil.getAddressGlobal();
        }

        public static boolean getBinarySwitch(String str, int i2) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                return (Integer.parseInt(str) & Double.valueOf(Math.pow(2.0d, (double) (i2 - 1))).intValue()) != 0;
            } catch (Exception unused) {
                return false;
            }
        }

        public static String getDecodedUrl(WebEntity webEntity) {
            if (webEntity == null || webEntity.urlMap == null) {
                return null;
            }
            String str = !TextUtils.isEmpty(webEntity.action) ? webEntity.urlMap.get((Object) webEntity.action) : webEntity.urlMap.get((Object) RemoteMessageConst.TO);
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            try {
                return Uri.decode(str);
            } catch (Exception unused) {
                return str;
            }
        }

        public static boolean getErrViewSwitch(String str, boolean z) {
            if (TextUtils.isEmpty(str)) {
                return z;
            }
            if (str.contains("hybrid_err_view=1")) {
                return true;
            }
            if (str.contains("hybrid_err_view=0")) {
                return false;
            }
            return z;
        }

        public static JDWebView getJDWebView(View view) {
            if (view == null) {
                return null;
            }
            ViewParent parent = view.getParent();
            if (parent instanceof View) {
                if (parent instanceof JDWebView) {
                    return (JDWebView) parent;
                }
                return getJDWebView((View) parent);
            }
            return null;
        }

        public static JDLocationCacheOption getLBSOptionWithBaseSceneId(JDLocationCacheOption jDLocationCacheOption) {
            if (SwitchQueryFetcher.getSwitchBooleanValue("JDLBSSceneIdEnable", false) && jDLocationCacheOption != null) {
                jDLocationCacheOption.setSceneId("basicShoppingProcess");
            }
            return jDLocationCacheOption;
        }

        public static String getLoadUrlIgnoreGentoken(URLParamMap uRLParamMap) {
            String str = uRLParamMap != null ? uRLParamMap.get(RemoteMessageConst.TO) : "";
            try {
                return Uri.decode(str);
            } catch (Exception e2) {
                Log.d(TAG, e2.getMessage());
                return str;
            }
        }

        public static String getLoadingPlaceHolder(String str) {
            if (TextUtils.isEmpty(str) || !str.contains("native_placeholder")) {
                return null;
            }
            try {
                return Uri.parse(str).getQueryParameter("native_placeholder");
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public static String getNetworkType() {
            return NetUtils.isWifi() ? "wifi" : "UNKNOWN";
        }

        public static String getPhoneNumber(Activity activity, Uri uri) {
            String str = "";
            try {
                Cursor query = activity.getContentResolver().query(uri, new String[]{"display_name", "data1"}, null, null, null);
                if (query != null && query.getCount() > 0) {
                    while (query.moveToNext()) {
                        str = query.getString(query.getColumnIndexOrThrow("data1"));
                    }
                }
            } catch (Exception e2) {
                Log.d("JJJ", e2.getMessage());
            }
            return str;
        }

        public static String getSslErrMsg(SslError sslError, String str) {
            try {
                return "SSL primaryError: " + sslError.getPrimaryError() + ";\nSslCertificate:  " + sslError.getCertificate().toString() + ";\nIssued to: " + sslError.getCertificate().getIssuedTo().getDName() + ";\nIssued by: " + sslError.getCertificate().getIssuedBy().getDName() + ";\nValidNotBeforeDate: " + sslError.getCertificate().getValidNotBeforeDate() + ";\nValidNotAfterDate: " + sslError.getCertificate().getValidNotAfterDate() + ";\n" + str;
            } catch (Exception e2) {
                e2.printStackTrace();
                return "";
            }
        }

        public static String getUrl(Bundle bundle) {
            if (bundle == null) {
                return "";
            }
            String string = bundle.getString("url");
            if (TextUtils.isEmpty(string)) {
                URLParamMap uRLParamMap = null;
                String string2 = bundle.getString("urlAction");
                try {
                    SerializableContainer serializableContainer = (SerializableContainer) bundle.getSerializable("urlParamMap");
                    if (serializableContainer != null) {
                        uRLParamMap = serializableContainer.getMap();
                    }
                } catch (Exception unused) {
                }
                if (TextUtils.isEmpty(string2)) {
                    string2 = RemoteMessageConst.TO;
                }
                if (uRLParamMap != null) {
                    try {
                        return URLDecoder.decode(uRLParamMap.get((Object) string2), "utf-8");
                    } catch (Exception unused2) {
                    }
                }
                return "";
            }
            return string;
        }

        public static String getUrlHistory(X5WebView x5WebView) {
            StringBuilder sb = new StringBuilder();
            WebBackForwardList copyBackForwardList = x5WebView.copyBackForwardList();
            if (copyBackForwardList != null) {
                try {
                    if (copyBackForwardList.getSize() > 0) {
                        int size = copyBackForwardList.getSize();
                        int min = Math.min(size, 5);
                        sb.append("UrlHistory  \u5171 " + size + "\u6761\uff0c \u8bb0\u5f55\u524d " + min + "\u6761   | ");
                        for (int i2 = 0; i2 < min; i2++) {
                            sb.append("\u7b2c " + i2 + " \u6761\uff1a url: " + copyBackForwardList.getItemAtIndex(i2).getUrl() + "     |");
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return sb.toString();
        }

        /* JADX WARN: Removed duplicated region for block: B:104:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:98:0x0071  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public static String getWebDirList(Context context) {
            StringBuilder sb;
            if (context == null) {
                return null;
            }
            try {
                String parent = context.getDir("webview", 0).getParent();
                File file = new File(parent);
                if (file.exists() && file.isDirectory()) {
                    File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.jingdong.common.web.util.WebUtils.13
                        @Override // java.io.FilenameFilter
                        public boolean accept(File file2, String str) {
                            return str != null && str.startsWith("app_webview");
                        }
                    });
                    sb = new StringBuilder("webview dirs: ");
                    if (listFiles != null) {
                        try {
                            if (listFiles.length > 0) {
                                for (File file2 : listFiles) {
                                    sb.append("[");
                                    sb.append(file2.getPath().replace(parent, ""));
                                    sb.append("]");
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            th.printStackTrace();
                            if (OKLog.E) {
                                OKLog.e(TAG, th);
                            }
                            if (sb != null) {
                            }
                        }
                    }
                    sb.append("no directory starts with app_webview");
                } else {
                    sb = null;
                }
            } catch (Throwable th2) {
                th = th2;
                sb = null;
            }
            if (sb != null) {
                return null;
            }
            return sb.toString();
        }

        public static void gotoOrderListActivity(Activity activity) {
            ActivityNumController.exitActivityWithoutTop();
            Bundle bundle = new Bundle();
            bundle.putString("from", "pay");
            DeepLinkOrderCenterHelper.startOrderList(activity, bundle);
            activity.finish();
        }

        private static boolean hostEndWithListKeyWord(String str, String str2, String[] strArr) {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str) && strArr != null && strArr.length != 0) {
                int length = strArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    String str3 = strArr[i2];
                    String[] split = str3 != null ? str3.split(DYConstants.DY_REGEX_COMMA) : null;
                    if (split != null && split.length == 1 && !TextUtils.isEmpty(split[0])) {
                        if (str2.endsWith(split[0])) {
                            return true;
                        }
                    } else if (split != null && split.length == 2 && !TextUtils.isEmpty(split[0]) && str2.endsWith(split[0]) && str.contains(split[1])) {
                        return true;
                    }
                }
            }
            return false;
        }

        private static boolean hostList(String str, String[] strArr) {
            if (strArr != null && strArr.length > 0) {
                for (String str2 : strArr) {
                    if (str != null && !TextUtils.isEmpty(str2) && str.contains(str2)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static boolean hostListWithKeyWord(String str, String str2, String[] strArr) {
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str) && strArr != null && strArr.length != 0) {
                int length = strArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    String str3 = strArr[i2];
                    String[] split = str3 != null ? str3.split(DYConstants.DY_REGEX_COMMA) : null;
                    if (split != null && split.length == 1 && !TextUtils.isEmpty(split[0])) {
                        if (str2.contains(split[0])) {
                            return true;
                        }
                    } else if (split != null && split.length == 2 && !TextUtils.isEmpty(split[0]) && str2.contains(split[0]) && str.contains(split[1])) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static boolean immersiveWhiteBgBlackList(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                return hostListWithKeyWord(str, Uri.parse(str).getHost(), SwitchQueryFetcher.getSwitchStringValue("wbImBgBlackList", "").split(";"));
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }

        public static void initWeb() {
            GraySwitch.provider = new GraySwitch.SwitchProvider() { // from class: com.jingdong.common.web.util.WebUtils.1
                @Override // com.jingdong.common.GraySwitch.SwitchProvider
                public boolean topLogoEnable() {
                    return SwitchQueryFetcher.getSwitchBooleanValue("wvTopLogo", true);
                }
            };
        }

        public static boolean isBlankUrl(String str) {
            return "about:blank".equals(str) || (str != null && str.startsWith("data:text/html"));
        }

        public static boolean isCloseUri(Context context, Uri uri) {
            if (uri != null && "closejdapp".equalsIgnoreCase(uri.getScheme()) && "webview".equalsIgnoreCase(uri.getHost())) {
                String queryParameter = uri.getQueryParameter("refresh");
                String queryParameter2 = uri.getQueryParameter("returnplus");
                if (!TextUtils.isEmpty(queryParameter)) {
                    if (DYConstants.DY_TRUE.equals(queryParameter)) {
                        ((Activity) context).setResult(101);
                    } else if (DYConstants.DY_FALSE.equals(queryParameter)) {
                        ((Activity) context).setResult(100);
                    }
                    ((Activity) context).finish();
                    return true;
                } else if (!TextUtils.isEmpty(queryParameter2)) {
                    if (DYConstants.DY_TRUE.equals(queryParameter2)) {
                        ActivityNumController.removeActivity(0, 2);
                    } else {
                        ((Activity) context).finish();
                    }
                    return true;
                } else {
                    ((Activity) context).finish();
                    return true;
                }
            }
            return false;
        }

        public static boolean isHideProgress(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            return str.contains("jwebprog=0");
        }

        public static boolean isHttpOrHttps(String str) {
            return "http".equalsIgnoreCase(str) || "https".equalsIgnoreCase(str);
        }

        public static boolean isLegalUrlToControlBack(IWebUiBinder iWebUiBinder) {
            if (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) {
                return false;
            }
            String finalUrl = iWebUiBinder.getJdWebView().getFinalUrl();
            if (TextUtils.isEmpty(finalUrl)) {
                return false;
            }
            try {
                return hostListWithKeyWord(finalUrl, Uri.parse(finalUrl).getHost(), SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.WB_WEB_CONTROL_BACK, "").split(";"));
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }

        public static boolean isLegalUrlToControlBackXRender(JDWebView jDWebView) {
            if (jDWebView != null) {
                String finalUrl = jDWebView.getFinalUrl();
                if (TextUtils.isEmpty(finalUrl)) {
                    return false;
                }
                try {
                    return hostListWithKeyWord(finalUrl, Uri.parse(finalUrl).getHost(), SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.WB_WEB_CONTROL_BACK, "").split(";"));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return false;
                }
            }
            return false;
        }

        public static boolean isMultiScreen() {
            return UnAndroidUtils.mateXEasyClient(JdSdk.getInstance().getApplicationContext()) || UnAndroidUtils.mateXEasyClientNew(JdSdk.getInstance().getApplicationContext());
        }

        private static boolean isNumInRange(int i2, String str) {
            String[] split;
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            try {
                split = str.split(DYConstants.DY_REGEX_COMMA);
            } catch (Exception e2) {
                if (Log.E) {
                    Log.e(TAG, "isNumInRange error: " + e2.getMessage(), e2);
                }
            }
            if (split.length == 1 && !TextUtils.isEmpty(split[0])) {
                return i2 >= Integer.parseInt(split[0].trim());
            }
            if (split.length >= 2 && !TextUtils.isEmpty(split[0])) {
                int parseInt = Integer.parseInt(split[0].trim());
                if (TextUtils.isEmpty(split[1])) {
                    return i2 >= parseInt;
                }
                return i2 >= parseInt && i2 <= Integer.parseInt(split[1].trim());
            }
            return false;
        }

        public static boolean isSchemeInBlackList(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            String[] split = SwitchQueryFetcher.getSwitchStringValue(SwitchQueryFetcher.WEB_SCHEME_BLACKLIST, "").split(DYConstants.DY_REGEX_COMMA);
            if (split.length > 0) {
                for (String str2 : split) {
                    if (str.equals(str2)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static boolean isX5CoreNumInSwitch(Context context, String str) {
            String switchStringValue = SwitchQueryFetcher.getSwitchStringValue(str, "");
            if (TextUtils.isEmpty(switchStringValue)) {
                return false;
            }
            try {
                return isNumInRange(QbSdk.getTbsVersion(context), switchStringValue);
            } catch (Exception unused) {
                return false;
            }
        }

        public static boolean isX5CoreVerForbid(Context context) {
            int tbsVersion;
            String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("x5DisableVer", "");
            if (TextUtils.isEmpty(switchStringValue)) {
                return false;
            }
            try {
                tbsVersion = QbSdk.getTbsVersion(context);
            } catch (Exception e2) {
                if (Log.E) {
                    Log.e(TAG, "isX5CoreForbid error: " + e2.getMessage(), e2);
                }
            }
            if (tbsVersion == 0) {
                return false;
            }
            JSONObject jSONObject = new JSONObject(switchStringValue);
            JSONArray optJSONArray = jSONObject.optJSONArray("exact");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    int optInt = optJSONArray.optInt(i2);
                    if (optInt != 0 && tbsVersion == optInt) {
                        return true;
                    }
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("range");
            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                    if (isNumInRange(tbsVersion, optJSONArray2.optString(i3))) {
                        return true;
                    }
                }
            }
            return false;
        }

        public static void jdAuthNotifyThirdApp(IWebUiBinder iWebUiBinder) {
            if (iWebUiBinder.getWebEntity().isFromAuthSdk) {
                String string = iWebUiBinder.getWebEntity().mBundle.getString("packageName");
                String string2 = iWebUiBinder.getWebEntity().mBundle.getString(Constants.JLOG_ACTIVITYNAME_PARAM_KEY);
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(string, string2));
                intent.setFlags(268435456);
                Bundle bundle = new Bundle();
                bundle.putString("code", iWebUiBinder.getWebEntity().oauthCode);
                bundle.putInt("oautherror", iWebUiBinder.getWebEntity().oautherror);
                bundle.putString("msgcode", iWebUiBinder.getWebEntity().msgcode);
                intent.putExtras(bundle);
                try {
                    iWebUiBinder.startActivity(intent);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                iWebUiBinder.finishUi();
            }
        }

        private static Object jsonObjectWrap(Object obj) {
            if (obj == null) {
                return JSONObject.NULL;
            }
            if ((obj instanceof JSONArray) || (obj instanceof JSONObject) || obj.equals(JSONObject.NULL)) {
                return obj;
            }
            if (obj instanceof Collection) {
                return new JSONArray((Collection) obj);
            }
            if (obj.getClass().isArray()) {
                return arrayToJsonArray(obj);
            }
            if (obj instanceof Map) {
                return new JSONObject((Map) obj);
            }
            if (!(obj instanceof Boolean) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof Double) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Short) && !(obj instanceof String)) {
                if (obj.getClass().getPackage().getName().startsWith("java.")) {
                    return obj.toString();
                }
                return null;
            }
            return obj;
        }

        public static void loadGenTokenUrl(final IWebUiBinder iWebUiBinder, final String str) {
            final JDWebView jdWebView = iWebUiBinder.getJdWebView();
            if (jdWebView == null) {
                return;
            }
            String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("wvLoginReplace", "");
            String str2 = null;
            final String[] split = !TextUtils.isEmpty(switchStringValue) ? switchStringValue.split(";") : null;
            try {
                Uri parse = Uri.parse(str);
                if (parse != null) {
                    str2 = parse.getQueryParameter("returnurl");
                }
            } catch (Exception unused) {
            }
            final String str3 = str2;
            if (SwitchQueryFetcher.getSwitchBooleanValue("h_gt", false)) {
                final String simpleName = jdWebView.getClass().getSimpleName();
                if (WebDebug.report) {
                    WebDebug.log("webview", "[genToken][login requested by H5] use hybrid's http to sync cookie", simpleName);
                }
                System.currentTimeMillis();
                HybridGenTokenSupporter.loadGenTokenUrl(str, str3, jdWebView.getHybridOfflineLoader(), Looper.getMainLooper(), new HybridGenTokenSupporter.GenTokenCallback
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0060: INVOKE 
                      (r14v0 'str' java.lang.String)
                      (r9v0 'str3' java.lang.String)
                      (wrap: com.jd.libs.hybrid.HybridOfflineLoader : 0x004f: INVOKE (r3v0 'jdWebView' com.jingdong.common.web.ui.JDWebView) type: VIRTUAL call: com.jingdong.common.web.ui.JDWebView.getHybridOfflineLoader():com.jd.libs.hybrid.HybridOfflineLoader A[MD:():com.jd.libs.hybrid.HybridOfflineLoader (m), WRAPPED] (LINE:12))
                      (wrap: android.os.Looper : 0x0053: INVOKE  type: STATIC call: android.os.Looper.getMainLooper():android.os.Looper A[MD:():android.os.Looper (c), WRAPPED])
                      (wrap: com.jd.libs.hybrid.HybridGenTokenSupporter$GenTokenCallback : 0x005d: CONSTRUCTOR 
                      (r1v4 'simpleName' java.lang.String A[DONT_INLINE])
                      (r13v0 'iWebUiBinder' com.jingdong.common.web.uibinder.IWebUiBinder A[DONT_INLINE])
                      (r3v0 'jdWebView' com.jingdong.common.web.ui.JDWebView A[DONT_INLINE])
                      (r9v0 'str3' java.lang.String A[DONT_INLINE])
                      (r5v1 'split' java.lang.String[] A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r6 I:long A[DONT_GENERATE, DONT_INLINE, REMOVE])
                      (r14v0 'str' java.lang.String A[DONT_INLINE])
                     A[MD:(java.lang.String, com.jingdong.common.web.uibinder.IWebUiBinder, com.jingdong.common.web.ui.JDWebView, java.lang.String, java.lang.String[], long, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.web.util.WebUtils.4.<init>(java.lang.String, com.jingdong.common.web.uibinder.IWebUiBinder, com.jingdong.common.web.ui.JDWebView, java.lang.String, java.lang.String[], long, java.lang.String):void type: CONSTRUCTOR)
                     type: STATIC call: com.jd.libs.hybrid.HybridGenTokenSupporter.loadGenTokenUrl(java.lang.String, java.lang.String, com.jd.libs.hybrid.HybridOfflineLoader, android.os.Looper, com.jd.libs.hybrid.HybridGenTokenSupporter$GenTokenCallback):void A[MD:(java.lang.String, java.lang.String, com.jd.libs.hybrid.HybridOfflineLoader, android.os.Looper, com.jd.libs.hybrid.HybridGenTokenSupporter$GenTokenCallback):void (m)] (LINE:12) in method: com.jingdong.common.web.util.WebUtils.loadGenTokenUrl(com.jingdong.common.web.uibinder.IWebUiBinder, java.lang.String):void, file: classes12.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                    	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                    	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                    	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                    	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                    com.jingdong.common.web.ui.JDWebView r3 = r13.getJdWebView()
                    if (r3 != 0) goto L7
                    return
                L7:
                    java.lang.String r0 = "wvLoginReplace"
                    java.lang.String r1 = ""
                    java.lang.String r0 = com.jingdong.common.utils.SwitchQueryFetcher.getSwitchStringValue(r0, r1)
                    boolean r1 = android.text.TextUtils.isEmpty(r0)
                    r2 = 0
                    if (r1 != 0) goto L1f
                    java.lang.String r1 = ";"
                    java.lang.String[] r0 = r0.split(r1)
                    r5 = r0
                    goto L20
                L1f:
                    r5 = r2
                L20:
                    android.net.Uri r0 = android.net.Uri.parse(r14)     // Catch: java.lang.Exception -> L2d
                    if (r0 == 0) goto L2d
                    java.lang.String r1 = "returnurl"
                    java.lang.String r0 = r0.getQueryParameter(r1)     // Catch: java.lang.Exception -> L2d
                    r2 = r0
                L2d:
                    r9 = r2
                    r0 = 0
                    java.lang.String r1 = "h_gt"
                    boolean r0 = com.jingdong.common.utils.SwitchQueryFetcher.getSwitchBooleanValue(r1, r0)
                    if (r0 == 0) goto L64
                    java.lang.Class r0 = r3.getClass()
                    java.lang.String r1 = r0.getSimpleName()
                    boolean r0 = com.jingdong.common.web.WebDebug.report
                    if (r0 == 0) goto L4b
                    java.lang.String r0 = "webview"
                    java.lang.String r2 = "[genToken][login requested by H5] use hybrid's http to sync cookie"
                    com.jingdong.common.web.WebDebug.log(r0, r2, r1)
                L4b:
                    long r6 = java.lang.System.currentTimeMillis()
                    com.jd.libs.hybrid.HybridOfflineLoader r10 = r3.getHybridOfflineLoader()
                    android.os.Looper r11 = android.os.Looper.getMainLooper()
                    com.jingdong.common.web.util.WebUtils$4 r12 = new com.jingdong.common.web.util.WebUtils$4
                    r0 = r12
                    r2 = r13
                    r4 = r9
                    r8 = r14
                    r0.<init>()
                    com.jd.libs.hybrid.HybridGenTokenSupporter.loadGenTokenUrl(r14, r9, r10, r11, r12)
                    goto L70
                L64:
                    com.jingdong.common.BaseActivity r0 = r13.getBaseActivity()
                    com.jingdong.common.web.util.WebUtils$5 r1 = new com.jingdong.common.web.util.WebUtils$5
                    r1.<init>()
                    r0.post(r1)
                L70:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.util.WebUtils.loadGenTokenUrl(com.jingdong.common.web.uibinder.IWebUiBinder, java.lang.String):void");
            }

            public static void loadUrlReplace(JDWebView jDWebView, String str, String str2, String[] strArr) {
                if (jDWebView == null) {
                    return;
                }
                if (!TextUtils.isEmpty(str) && strArr != null) {
                    if (TextUtils.isEmpty(str2)) {
                        str2 = str;
                    }
                    Uri parse = Uri.parse(str2);
                    if (parse != null) {
                        if (hostListWithKeyWord(str2, parse.getHost(), strArr)) {
                            if (Log.D || WebLogHelper.showXLog) {
                                jDWebView.xLogD(" \u540c\u6b65M\u9875 replace url ");
                            }
                            jDWebView.replaceUrl(str, true);
                            return;
                        }
                        jDWebView.loadUrl(str);
                        return;
                    }
                    jDWebView.loadUrl(str);
                    return;
                }
                jDWebView.loadUrl(str);
            }

            public static void loginStateSynchro(IWebUiBinder iWebUiBinder, Uri uri) {
                loginStateSynchro(iWebUiBinder, uri, LoginConstans.FREGMENT_LOGIN_FLAG, null, false);
            }

            public static void makeShareMta(IWebUiBinder iWebUiBinder, String str) {
                if (TextUtils.isEmpty(iWebUiBinder.getWebEntity().jsBridgeEntity.event_id)) {
                    iWebUiBinder.getWebEntity().jsBridgeEntity.event_id = "MWebview_RightTopShare";
                }
                JDMtaUtils.onClick(iWebUiBinder.getBaseActivity(), iWebUiBinder.getWebEntity().jsBridgeEntity.event_id, iWebUiBinder.getBaseActivity().getClass().getSimpleName(), str, iWebUiBinder.getJdWebView().getFinalUrl());
            }

            private static String mergeUrlAndQuery(String str, String str2, String str3) {
                if (str != null && !str.isEmpty() && str2 != null && !str2.isEmpty()) {
                    try {
                        Uri parse = Uri.parse(str);
                        if (parse.getQueryParameter(str2) == null) {
                            return parse.buildUpon().appendQueryParameter(str2, str3).toString();
                        }
                        String encode = Uri.encode(str2, null);
                        int switchIntValue = SwitchQueryFetcher.getSwitchIntValue("newReplaceUrlQuerySwitch", 0);
                        if (switchIntValue == 1) {
                            return parse.buildUpon().encodedQuery(parse.getQuery().replaceAll("(" + encode + "=[^&]*)", encode + ContainerUtils.KEY_VALUE_DELIMITER + Uri.encode(str3, null))).toString();
                        } else if (switchIntValue == 2) {
                            return str;
                        } else {
                            return str.replaceAll("(" + encode + "=[^&]*)", encode + ContainerUtils.KEY_VALUE_DELIMITER + Uri.encode(str3, null));
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                return str;
            }

            public static boolean needReportUnknownScheme(Uri uri) {
                if (uri == null) {
                    return false;
                }
                String scheme = uri.getScheme();
                String host = uri.getHost();
                if (TextUtils.isEmpty(scheme)) {
                    return false;
                }
                List<Pair<String, String>> list = schemeNoReportList;
                if (list == null) {
                    return true;
                }
                for (Pair<String, String> pair : list) {
                    if (pair != null) {
                        String str = (String) pair.first;
                        String str2 = (String) pair.second;
                        if (!TextUtils.isEmpty(str2)) {
                            if (str2.equalsIgnoreCase(host) && scheme.equalsIgnoreCase(str)) {
                                return false;
                            }
                        } else if (scheme.equalsIgnoreCase(str)) {
                            return false;
                        }
                    }
                }
                return true;
            }

            public static void newGentoken(final WebEntity webEntity, final JDWebView jDWebView, final BaseActivity baseActivity, final Object obj, boolean z) {
                if (Log.D || WebLogHelper.showXLog) {
                    jDWebView.xLogD("[newGentoken] -> start...");
                }
                if (webEntity == null || jDWebView == null) {
                    return;
                }
                if (jDWebView.isHybridPassGenToken()) {
                    webEntity.urlMap.put("webHybridHasOffConfig", "1");
                }
                if (z && canPassGentoken(webEntity.urlMap)) {
                    String loadUrlIgnoreGentoken = getLoadUrlIgnoreGentoken(webEntity.urlMap);
                    if (WebDebug.report) {
                        WebDebug.log("webview", "[newGentoken] -> Can pass gentoken: " + loadUrlIgnoreGentoken, obj);
                    }
                    if (Log.D || WebLogHelper.showXLog) {
                        jDWebView.xLogD("[newGentoken] -> Can pass gentoken, url: " + loadUrlIgnoreGentoken);
                    }
                    jDWebView.appendPerformanceData(WebPerfManager.GENTOKEN_TYPE, "1");
                    jDWebView.loadUrl(loadUrlIgnoreGentoken);
                    jDWebView.addLoadEvent(WebWhiteScreenHolder.CAN_PASS_GENTOKEN);
                    return;
                }
                jDWebView.appendPerformanceData(WebPerfManager.GENTOKEN_TYPE, "2");
                WebLoginUtil.getWJLoginHelper().reqWebCookie(false, getDecodedUrl(webEntity), new WJReqWebCookieCallBack() { // from class: com.jingdong.common.web.util.WebUtils.15
                    @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
                    public void onFail(WJFailResult wJFailResult) {
                        if (Log.D || WebLogHelper.showXLog) {
                            jDWebView.xLogD("[newGentoken] -> reqWebLogin onFail code = " + wJFailResult.getErrorCode());
                        }
                        WebUtils.gentoken(webEntity, jDWebView, baseActivity, obj);
                    }

                    @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
                    public void onHttpTaskError(HttpError httpError) {
                        if (Log.D || WebLogHelper.showXLog) {
                            jDWebView.xLogD("[newGentoken] -> reqWebLogin onHttpTaskError code = " + httpError.getErrorCode());
                        }
                        WebUtils.gentoken(webEntity, jDWebView, baseActivity, obj);
                    }

                    @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
                    public void onLocalError(WJErrorResult wJErrorResult) {
                        if (Log.D || WebLogHelper.showXLog) {
                            jDWebView.xLogD("[newGentoken] -> reqWebLogin onLocalError code = " + wJErrorResult.getErrorCode());
                        }
                        WebUtils.gentoken(webEntity, jDWebView, baseActivity, obj);
                    }

                    @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
                    public void onSuccess(String str) {
                        if (Log.D || WebLogHelper.showXLog) {
                            jDWebView.xLogD("[newGentoken] -> reqWebLogin onSuccess url=" + str);
                        }
                        if (jDWebView.getGenTokenCallback() != null) {
                            jDWebView.getGenTokenCallback().onCallback(GenTokenCallback.State.GEN_TOKEN_SUCCESS);
                        } else {
                            jDWebView.loadUrl(str);
                        }
                        WebLoginHelper.onGentokenSuccess();
                        jDWebView.addLoadEvent(WebWhiteScreenHolder.GENTOKEN_SUCCESS);
                    }

                    @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
                    public void onWithinTheValidity(String str) {
                        if (Log.D || WebLogHelper.showXLog) {
                            jDWebView.xLogD("[newGentoken] -> reqWebLogin onWithinTheValidity url=" + str);
                        }
                        if (jDWebView.getGenTokenCallback() != null) {
                            jDWebView.getGenTokenCallback().onCallback(GenTokenCallback.State.GEN_TOKEN_READY);
                        } else {
                            jDWebView.loadUrl(str);
                        }
                        jDWebView.addLoadEvent(WebWhiteScreenHolder.GENTOKEN_SUCCESS);
                    }
                });
            }

            public static void onJSConsoleMessage(ConsoleMessage consoleMessage) {
                if (consoleMessage == null) {
                    return;
                }
                int i2 = AnonymousClass16.$SwitchMap$com$tencent$smtt$export$external$interfaces$ConsoleMessage$MessageLevel[consoleMessage.messageLevel().ordinal()];
                if (i2 == 1) {
                    XLog.d("JSConsoleMessage", null, consoleMessage.message(), IExceptionHandler.DynamicExceptionData.TYPE_JS);
                } else if (i2 == 2) {
                    XLog.i("JSConsoleMessage", null, consoleMessage.message(), IExceptionHandler.DynamicExceptionData.TYPE_JS);
                } else if (i2 == 3) {
                    XLog.e("JSConsoleMessage", null, consoleMessage.message(), IExceptionHandler.DynamicExceptionData.TYPE_JS);
                } else if (i2 != 4) {
                    XLog.v("JSConsoleMessage", null, consoleMessage.message(), IExceptionHandler.DynamicExceptionData.TYPE_JS);
                } else {
                    XLog.w("JSConsoleMessage", null, consoleMessage.message(), IExceptionHandler.DynamicExceptionData.TYPE_JS);
                }
            }

            public static void oneKeyLoginKelper(Activity activity) {
                if (activity != null) {
                    try {
                        activity.finish();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return;
                    }
                }
                IMainActivity mainFrameActivity = BaseFrameUtil.getInstance().getMainFrameActivity();
                if (mainFrameActivity == null || !(mainFrameActivity instanceof Activity)) {
                    return;
                }
                ((Activity) mainFrameActivity).moveTaskToBack(true);
            }

            public static void reportCommonErr(IWebUiBinder iWebUiBinder, String str, String str2, String str3) {
                if (iWebUiBinder != null && iWebUiBinder.getJdWebView() != null) {
                    ExceptionReporter.reportWebViewCommonError(str, iWebUiBinder.getJdWebView().getFinalUrl(), str2, str3);
                } else {
                    ExceptionReporter.reportWebViewCommonError(str, "", str2, str3);
                }
            }

            public static void reportDeprecatedLBSBridge(JDWebView jDWebView, String str) {
                if (jDWebView != null && !TextUtils.isEmpty(str)) {
                    try {
                        HashMap hashMap = new HashMap();
                        hashMap.put("function", "WebView_LBS_Bridge");
                        hashMap.put("errCode", WebWhiteScreenHolder.ERR_CODE);
                        hashMap.put(PerformanceManager.ERR_TYPE, "2");
                        hashMap.put("url", jDWebView.getUrl());
                        hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
                        JDJSONObject jDJSONObject = new JDJSONObject();
                        jDJSONObject.put("bridgeName", (Object) str);
                        hashMap.put("reserved1", jDJSONObject.toJSONString());
                        ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplicationContext(), hashMap);
                    } catch (Exception unused) {
                    }
                }
            }

            public static void reportOpenBrowser(String str, String str2, String str3) {
                try {
                    ExceptionReporter.reportWebViewCommonError("webView_openBrowser", str, str2, str3);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            public static void reportSslException(String str, SslError sslError, String str2) {
                try {
                    ExceptionReporter.reportWebViewSslError(str, " JDWebView  [onReceivedSslError] primaryError: " + sslError.getPrimaryError() + ";\nSslCertificate:  " + sslError.getCertificate().toString() + ";\n" + str2, "Issued to: " + sslError.getCertificate().getIssuedTo().getDName() + ";\nIssued by: " + sslError.getCertificate().getIssuedBy().getDName() + ";\nValidNotBeforeDate: " + sslError.getCertificate().getValidNotBeforeDate() + ";\nValidNotAfterDate: " + sslError.getCertificate().getValidNotAfterDate() + ";\n" + str2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            public static void returnThirdApp(Activity activity, Uri uri) {
                if (uri != null) {
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW", uri);
                        if (!TextUtils.isEmpty(OpenAppJumpController.JDTHIRDLOGIN_THRIDPACKAGENAME)) {
                            intent.setPackage(OpenAppJumpController.JDTHIRDLOGIN_THRIDPACKAGENAME);
                        }
                        intent.setFlags(268435456);
                        activity.startActivity(intent);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                activity.finish();
            }

            public static void runOnMain(JDWebView jDWebView, Runnable runnable) {
                Handler handler = new Handler(Looper.getMainLooper());
                Thread thread = handler.getLooper().getThread();
                if (jDWebView != null && jDWebView.getHandler() != null && jDWebView.getHandler().getLooper() != null && jDWebView.getHandler().getLooper().getThread() != null) {
                    thread = jDWebView.getHandler().getLooper().getThread();
                }
                if (Thread.currentThread() == thread) {
                    runnable.run();
                } else if (jDWebView == null || jDWebView.getHandler() == null || jDWebView.getHandler().post(runnable)) {
                } else {
                    handler.post(runnable);
                }
            }

            private static void saveBase64Img(final BaseActivity baseActivity, final String str, final String str2) {
                new Thread(new Runnable() { // from class: com.jingdong.common.web.util.WebUtils.10
                    /* JADX WARN: Removed duplicated region for block: B:136:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:104:0x0033 -> B:131:0x0048). Please submit an issue!!! */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public void run() {
                        BaseActivity baseActivity2;
                        Throwable th;
                        FileOutputStream fileOutputStream;
                        Exception e2;
                        byte[] decode;
                        File file;
                        FileOutputStream fileOutputStream2 = null;
                        boolean z = false;
                        try {
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        try {
                            try {
                                decode = Base64.decode(str);
                                file = new File(FileService.getInternalDirectory("webviewTemp", 1, true), str2);
                                fileOutputStream = new FileOutputStream(file);
                            } catch (Exception e4) {
                                fileOutputStream = null;
                                e2 = e4;
                            } catch (Throwable th2) {
                                th = th2;
                                if (0 != 0) {
                                }
                                throw th;
                            }
                            try {
                                fileOutputStream.write(decode);
                                z = MediaUtils.savePictureToAlbum(baseActivity, file);
                                if (file.exists()) {
                                    file.delete();
                                }
                                fileOutputStream.close();
                            } catch (Exception e5) {
                                e2 = e5;
                                e2.printStackTrace();
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                baseActivity2 = baseActivity;
                                if (baseActivity2 != null) {
                                    return;
                                }
                                return;
                            }
                            baseActivity2 = baseActivity;
                            if (baseActivity2 != null || baseActivity2.isFinishing()) {
                                return;
                            }
                            final String str3 = z ? "\u5b58\u50a8\u6210\u529f,\u53ef\u5728\u76f8\u518c\u4e2d\u67e5\u770b" : "\u5b58\u50a8\u5931\u8d25,\u8bf7\u91cd\u8bd5";
                            baseActivity.runOnUiThread(new Runnable() { // from class: com.jingdong.common.web.util.WebUtils.10.1
                                {
                                    AnonymousClass10.this = this;
                                }

                                @Override // java.lang.Runnable
                                public void run() {
                                    ToastUtils.showToast(baseActivity, str3);
                                }
                            });
                        } catch (Throwable th3) {
                            th = th3;
                            if (0 != 0) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e6) {
                                    e6.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                }).start();
            }

            public static void saveImageToPhotoAlbum(BaseActivity baseActivity, String str) {
                if (OKLog.D) {
                    OKLog.d(TAG, "saveImageToPhotoAlbum:" + str);
                }
                if (!TextUtils.isEmpty(str) && MediaUtils.checkIfCanSaveFileToPublic()) {
                    downloadAndSaveImage(baseActivity, str);
                }
            }

            public static HttpRequest saveVideoToAlbum(IWebUiBinder iWebUiBinder, String str, String str2, String str3, HttpGroup httpGroup) {
                if (iWebUiBinder == null || iWebUiBinder.getBaseActivity() == null) {
                    return null;
                }
                if (!MediaUtils.checkIfCanSaveFileToPublic()) {
                    sendJSONStr2M(iWebUiBinder, str2, AlbumSaveHelper.stringfyJSonData("-1", "", "System version not support", str3));
                    return null;
                }
                return downloadAndSaveMedia(iWebUiBinder, str, str2, str3, httpGroup);
            }

            public static void sendJSONStr2M(IWebUiBinder iWebUiBinder, String str, String str2) {
                if (iWebUiBinder.getJdWebView() != null) {
                    iWebUiBinder.getJdWebView().injectJs("javascript:" + str + "('" + str2 + "');");
                    if (Log.D) {
                        Log.d(TAG, "sendJSONToM, injectJs--> javascript:" + str + "('" + str2 + "');");
                    }
                }
            }

            public static void sendJSONStr2MXRender(JDWebView jDWebView, String str, String str2) {
                if (jDWebView != null) {
                    jDWebView.injectJs("javascript:" + str + "('" + str2 + "');");
                    if (Log.D) {
                        Log.d(TAG, "sendJSONToM, injectJs--> javascript:" + str + "('" + str2 + "');");
                    }
                }
            }

            public static void setAcceptThirdCookie(WebView webView, String str) {
                setAcceptThirdCookie(webView, !TextUtils.isEmpty(str) ? Uri.parse(str) : null);
            }

            public static JSONObject strigfyPair2JSonObject(String... strArr) {
                JSONObject jSONObject = new JSONObject();
                if (strArr != null && strArr.length > 1) {
                    int i2 = 0;
                    while (i2 < strArr.length) {
                        String str = strArr[i2];
                        int i3 = i2 + 1;
                        String str2 = strArr[i3];
                        try {
                            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                                jSONObject.put(str, str2);
                            }
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        i2 = i3 + 1;
                    }
                }
                return jSONObject;
            }

            public static String stringfyJSonData(String str, Object obj, String str2) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("status", str);
                    jSONObject.put("data", obj);
                    jSONObject.put("msg", str2);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                return jSONObject.toString();
            }

            public static String stringifyUrlHistory(List<String> list) {
                if (list == null || list.size() <= 0) {
                    return "";
                }
                StringBuilder sb = new StringBuilder();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (i2 != 0) {
                        sb.append("|||");
                    }
                    sb.append(list.get(i2));
                }
                return sb.toString();
            }

            public static void webViewSetPath(Context context) {
                if (BaseInfo.getAndroidSDKVersion() < 28 || ProcessUtil.isMainProcess()) {
                    return;
                }
                try {
                    String processName = ProcessUtil.getProcessName(context);
                    String packageName = context != null ? context.getPackageName() : "";
                    if (processName != null) {
                        if (!TextUtils.isEmpty(packageName) && processName.contains(packageName)) {
                            processName = processName.replaceAll(packageName, "");
                        }
                        if (processName.contains(":")) {
                            processName = processName.replaceAll(":", "");
                        }
                        if (processName.indexOf(File.separatorChar) >= 0) {
                            processName = processName.replaceAll(String.valueOf(File.separatorChar), "");
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 28) {
                        android.webkit.WebView.setDataDirectorySuffix(processName);
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    if (OKLog.E) {
                        OKLog.e(TAG, th);
                    }
                }
            }

            public static void whiteRenderRatio(final JDWebView jDWebView, final double d) {
                WebWhiteScreenHolder.xLog("whiteRenderRatio start");
                if (jDWebView == null) {
                    return;
                }
                try {
                    jDWebView.buildDrawingCache();
                    jDWebView.getDrawingCache();
                    new Thread(new Runnable
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x001a: INVOKE 
                          (wrap: java.lang.Thread : 0x0017: CONSTRUCTOR 
                          (wrap: java.lang.Runnable : 0x0014: CONSTRUCTOR 
                          (r3v0 'jDWebView' com.jingdong.common.web.ui.JDWebView A[DONT_INLINE])
                          (r0 I:android.graphics.Bitmap A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r4v0 'd' double A[DONT_INLINE])
                         A[Catch: all -> 0x001e, MD:(com.jingdong.common.web.ui.JDWebView, android.graphics.Bitmap, double):void (m), WRAPPED] call: com.jingdong.common.web.util.i.<init>(com.jingdong.common.web.ui.JDWebView, android.graphics.Bitmap, double):void type: CONSTRUCTOR)
                         A[Catch: all -> 0x001e, MD:(java.lang.Runnable):void (c), WRAPPED] (LINE:4) call: java.lang.Thread.<init>(java.lang.Runnable):void type: CONSTRUCTOR)
                         type: VIRTUAL call: java.lang.Thread.start():void A[Catch: all -> 0x001e, MD:():void (c), TRY_LEAVE] (LINE:5) in method: com.jingdong.common.web.util.WebUtils.whiteRenderRatio(com.jingdong.common.web.ui.JDWebView, double):void, file: classes12.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                        java.lang.String r0 = "whiteRenderRatio start"
                        com.jingdong.common.web.managers.WebWhiteScreenHolder.xLog(r0)
                        if (r3 != 0) goto L9
                        return
                    L9:
                        r3.buildDrawingCache()     // Catch: java.lang.Throwable -> L1e
                        android.graphics.Bitmap r0 = r3.getDrawingCache()     // Catch: java.lang.Throwable -> L1e
                        java.lang.Thread r1 = new java.lang.Thread     // Catch: java.lang.Throwable -> L1e
                        com.jingdong.common.web.util.i r2 = new com.jingdong.common.web.util.i     // Catch: java.lang.Throwable -> L1e
                        r2.<init>()     // Catch: java.lang.Throwable -> L1e
                        r1.<init>(r2)     // Catch: java.lang.Throwable -> L1e
                        r1.start()     // Catch: java.lang.Throwable -> L1e
                        goto L23
                    L1e:
                        r3 = move-exception
                        r4 = 3
                        com.jingdong.common.web.managers.WebWhiteScreenHolder.sendException(r3, r4)
                    L23:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.util.WebUtils.whiteRenderRatio(com.jingdong.common.web.ui.JDWebView, double):void");
                }

                public static void dispatchEvent(final JDWebView jDWebView, String str, Object obj) {
                    String str2 = "undefined";
                    if (obj != null) {
                        try {
                            if (!(obj instanceof JSONObject) && !(obj instanceof JSONArray) && !(obj instanceof JDJSONObject) && !(obj instanceof JDJSONArray)) {
                                if (obj instanceof Map) {
                                    str2 = obj.toString();
                                } else if (obj instanceof Collection) {
                                    str2 = obj.toString();
                                } else if (obj.getClass().isArray()) {
                                    str2 = arrayToJsonArray(obj).toString();
                                } else if (obj.getClass().isPrimitive()) {
                                    str2 = obj.toString();
                                } else {
                                    str2 = String.format("'%s'", obj);
                                }
                            }
                            str2 = obj.toString();
                        } catch (Exception e2) {
                            if (Log.E) {
                                String str3 = TAG;
                                Log.e(str3, "Error in dispatchEvent");
                                Log.e(str3, e2.getMessage(), e2);
                                return;
                            }
                            return;
                        }
                    }
                    String.format("try{;(function(){var event = new CustomEvent('%s', {'detail': %s}); window.dispatchEvent(event);})();} catch (e) {console && console.error('ERROR in dispatchEvent:'+eventName, e);}", str, str2);
                    runOnMain(jDWebView, new Runnable
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x006e: INVOKE 
                          (r4v0 'jDWebView' com.jingdong.common.web.ui.JDWebView)
                          (wrap: java.lang.Runnable : 0x006b: CONSTRUCTOR 
                          (r4v0 'jDWebView' com.jingdong.common.web.ui.JDWebView A[DONT_INLINE])
                          (r5 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[Catch: Exception -> 0x0072, MD:(com.jingdong.common.web.ui.JDWebView, java.lang.String):void (m), WRAPPED] (LINE:14) call: com.jingdong.common.web.util.j.<init>(com.jingdong.common.web.ui.JDWebView, java.lang.String):void type: CONSTRUCTOR)
                         type: STATIC call: com.jingdong.common.web.util.WebUtils.runOnMain(com.jingdong.common.web.ui.JDWebView, java.lang.Runnable):void A[Catch: Exception -> 0x0072, MD:(com.jingdong.common.web.ui.JDWebView, java.lang.Runnable):void (m), TRY_LEAVE] (LINE:14) in method: com.jingdong.common.web.util.WebUtils.dispatchEvent(com.jingdong.common.web.ui.JDWebView, java.lang.String, java.lang.Object):void, file: classes12.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
                        java.lang.String r0 = "undefined"
                        r1 = 1
                        r2 = 0
                        if (r6 == 0) goto L5b
                        boolean r0 = r6 instanceof org.json.JSONObject     // Catch: java.lang.Exception -> L72
                        if (r0 != 0) goto L57
                        boolean r0 = r6 instanceof org.json.JSONArray     // Catch: java.lang.Exception -> L72
                        if (r0 != 0) goto L57
                        boolean r0 = r6 instanceof com.jd.framework.json.JDJSONObject     // Catch: java.lang.Exception -> L72
                        if (r0 != 0) goto L57
                        boolean r0 = r6 instanceof com.jd.framework.json.JDJSONArray     // Catch: java.lang.Exception -> L72
                        if (r0 == 0) goto L18
                        goto L57
                    L18:
                        boolean r0 = r6 instanceof java.util.Map     // Catch: java.lang.Exception -> L72
                        if (r0 == 0) goto L21
                        java.lang.String r0 = r6.toString()     // Catch: java.lang.Exception -> L72
                        goto L5b
                    L21:
                        boolean r0 = r6 instanceof java.util.Collection     // Catch: java.lang.Exception -> L72
                        if (r0 == 0) goto L2a
                        java.lang.String r0 = r6.toString()     // Catch: java.lang.Exception -> L72
                        goto L5b
                    L2a:
                        java.lang.Class r0 = r6.getClass()     // Catch: java.lang.Exception -> L72
                        boolean r0 = r0.isArray()     // Catch: java.lang.Exception -> L72
                        if (r0 == 0) goto L3d
                        org.json.JSONArray r6 = arrayToJsonArray(r6)     // Catch: java.lang.Exception -> L72
                        java.lang.String r0 = r6.toString()     // Catch: java.lang.Exception -> L72
                        goto L5b
                    L3d:
                        java.lang.Class r0 = r6.getClass()     // Catch: java.lang.Exception -> L72
                        boolean r0 = r0.isPrimitive()     // Catch: java.lang.Exception -> L72
                        if (r0 == 0) goto L4c
                        java.lang.String r0 = r6.toString()     // Catch: java.lang.Exception -> L72
                        goto L5b
                    L4c:
                        java.lang.String r0 = "'%s'"
                        java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L72
                        r3[r2] = r6     // Catch: java.lang.Exception -> L72
                        java.lang.String r0 = java.lang.String.format(r0, r3)     // Catch: java.lang.Exception -> L72
                        goto L5b
                    L57:
                        java.lang.String r0 = r6.toString()     // Catch: java.lang.Exception -> L72
                    L5b:
                        java.lang.String r6 = "try{;(function(){var event = new CustomEvent('%s', {'detail': %s}); window.dispatchEvent(event);})();} catch (e) {console && console.error('ERROR in dispatchEvent:'+eventName, e);}"
                        r3 = 2
                        java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L72
                        r3[r2] = r5     // Catch: java.lang.Exception -> L72
                        r3[r1] = r0     // Catch: java.lang.Exception -> L72
                        java.lang.String r5 = java.lang.String.format(r6, r3)     // Catch: java.lang.Exception -> L72
                        com.jingdong.common.web.util.j r6 = new com.jingdong.common.web.util.j     // Catch: java.lang.Exception -> L72
                        r6.<init>()     // Catch: java.lang.Exception -> L72
                        runOnMain(r4, r6)     // Catch: java.lang.Exception -> L72
                        goto L85
                    L72:
                        r4 = move-exception
                        boolean r5 = com.jingdong.corelib.utils.Log.E
                        if (r5 == 0) goto L85
                        java.lang.String r5 = com.jingdong.common.web.util.WebUtils.TAG
                        java.lang.String r6 = "Error in dispatchEvent"
                        com.jingdong.corelib.utils.Log.e(r5, r6)
                        java.lang.String r6 = r4.getMessage()
                        com.jingdong.corelib.utils.Log.e(r5, r6, r4)
                    L85:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.util.WebUtils.dispatchEvent(com.jingdong.common.web.ui.JDWebView, java.lang.String, java.lang.Object):void");
                }

                public static void loginStateSynchro(IWebUiBinder iWebUiBinder, Uri uri, boolean z, CommonBase.BrowserAllUrlListener browserAllUrlListener) {
                    loginStateSynchro(iWebUiBinder, uri, LoginConstans.FREGMENT_LOGIN_FLAG, browserAllUrlListener, z);
                }

                public static void fix64(boolean z) {
                    if (z) {
                        boolean z2 = false;
                        if (BaseInfo.getAndroidSDKVersion() >= 24 && SwitchQueryFetcher.getSwitchBooleanValue(SwitchQueryFetcher.WB_DEL_FOR_64, false) && !SharedPreferencesUtil.getBoolean("isApp64BitBefore", false)) {
                            z2 = true;
                        }
                        if (z2) {
                            new Thread(new Runnable() { // from class: com.jingdong.common.web.util.WebUtils.12
                                @Override // java.lang.Runnable
                                public void run() {
                                    Web64BitFixer.deleteCacheFilesFor64();
                                }
                            }, "web_delete_for64").start();
                        }
                        SharedPreferencesUtil.putBoolean("isApp64BitBefore", true);
                        return;
                    }
                    SharedPreferencesUtil.remove("isApp64BitBefore");
                }

                public static void loginStateSynchro(IWebUiBinder iWebUiBinder, Uri uri, CommonBase.BrowserAllUrlListener browserAllUrlListener) {
                    loginStateSynchro(iWebUiBinder, uri, false, browserAllUrlListener);
                }

                public static String getLoadUrlIgnoreGentoken(String str) {
                    Log.d(TAG, "ignoreGentoken: " + str);
                    return str;
                }

                public static void loginStateSynchro(final IWebUiBinder iWebUiBinder, final Uri uri, final String str, final CommonBase.BrowserAllUrlListener browserAllUrlListener, boolean z) {
                    String str2;
                    final JDWebView jdWebView;
                    if (uri == null) {
                        return;
                    }
                    WebEntity webEntity = iWebUiBinder != null ? iWebUiBinder.getWebEntity() : null;
                    if (webEntity != null) {
                        webEntity.loginStateSync = true;
                        webEntity.syncingUri = uri;
                    }
                    try {
                        str2 = uri.getQueryParameter("returnurl");
                    } catch (Exception unused) {
                        str2 = null;
                    }
                    if (WebSwitchQueryFetcher.newGentoken(str2) && !z) {
                        if (Log.D || WebLogHelper.showXLog) {
                            XLog.d(TAG, null, "[newGentoken] -> \u540c\u6b65M\u9875  newGentoken \u5df2\u6253\u5f00 reqWebCookie start... ", "webview");
                        }
                        if (iWebUiBinder == null || (jdWebView = iWebUiBinder.getJdWebView()) == null) {
                            return;
                        }
                        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("wvLoginReplace", "");
                        final String[] split = TextUtils.isEmpty(switchStringValue) ? null : switchStringValue.split(";");
                        WebLoginUtil.getWJLoginHelper().reqWebCookie(false, str2, new WJReqWebCookieCallBack() { // from class: com.jingdong.common.web.util.WebUtils.2
                            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
                            public void onFail(WJFailResult wJFailResult) {
                                if (Log.D || WebLogHelper.showXLog) {
                                    jdWebView.xLogD("[newGentoken] -> \u540c\u6b65M\u9875 reqWebLogin onFail code = " + wJFailResult.getErrorCode());
                                }
                                WebUtils.loginStateSynchro(iWebUiBinder, uri, str, browserAllUrlListener, true);
                            }

                            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
                            public void onHttpTaskError(HttpError httpError) {
                                if (Log.D || WebLogHelper.showXLog) {
                                    jdWebView.xLogD("[newGentoken] -> \u540c\u6b65M\u9875 reqWebLogin onHttpTaskError code = " + httpError.getErrorCode());
                                }
                                WebUtils.loginStateSynchro(iWebUiBinder, uri, str, browserAllUrlListener, true);
                            }

                            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
                            public void onLocalError(WJErrorResult wJErrorResult) {
                                if (Log.D || WebLogHelper.showXLog) {
                                    jdWebView.xLogD("[newGentoken] -> \u540c\u6b65M\u9875 reqWebLogin onLocalError code = " + wJErrorResult.getErrorCode());
                                }
                                WebUtils.loginStateSynchro(iWebUiBinder, uri, str, browserAllUrlListener, true);
                            }

                            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
                            public void onSuccess(String str3) {
                                if (Log.D || WebLogHelper.showXLog) {
                                    jdWebView.xLogD("[newGentoken] -> \u540c\u6b65M\u9875 reqWebLogin onSuccess url=" + str3);
                                }
                                WebUtils.loadUrlReplace(jdWebView, str3, null, split);
                                if (iWebUiBinder.getWebEntity() != null) {
                                    iWebUiBinder.getWebEntity().syncingUri = null;
                                }
                                WebLoginHelper.onGentokenSuccess();
                            }

                            @Override // jd.wjweblogin.common.listener.WJReqWebCookieCallBack
                            public void onWithinTheValidity(String str3) {
                                if (Log.D || WebLogHelper.showXLog) {
                                    jdWebView.xLogD("[newGentoken] -> \u540c\u6b65M\u9875 reqWebLogin onWithinTheValidity url=" + str3);
                                }
                                WebUtils.loadUrlReplace(jdWebView, str3, null, split);
                                if (iWebUiBinder.getWebEntity() != null) {
                                    iWebUiBinder.getWebEntity().syncingUri = null;
                                }
                            }
                        });
                        return;
                    }
                    try {
                        URLParamMap uRLParamMap = new URLParamMap();
                        uRLParamMap.put(uri);
                        CommonBase.queryBrowserUrlSupportNoLbs(str, uRLParamMap, new CommonBase.BrowserAllUrlListener() { // from class: com.jingdong.common.web.util.WebUtils.3
                            @Override // com.jingdong.common.utils.CommonBase.BrowserUrlListener
                            public void onComplete(String str3) {
                                String simpleName = iWebUiBinder.getJdWebView() != null ? iWebUiBinder.getJdWebView().getClass().getSimpleName() : "JDWebView";
                                String str4 = "[genToken][login requested by H5] function:gentoken completed: " + str3;
                                if (WebDebug.report) {
                                    WebDebug.log("webview", str4, simpleName);
                                }
                                if (Log.D || WebLogHelper.showXLog) {
                                    XLog.d(WebUtils.TAG, null, str4, "webview");
                                }
                                WebUtils.loadGenTokenUrl(iWebUiBinder, str3);
                                CommonBase.BrowserAllUrlListener browserAllUrlListener2 = browserAllUrlListener;
                                if (browserAllUrlListener2 != null) {
                                    browserAllUrlListener2.onComplete(str3);
                                }
                            }

                            @Override // com.jingdong.common.utils.CommonBase.BrowserNewUrlListener
                            public void onError(HttpError httpError) {
                                if (iWebUiBinder.getWebEntity() != null) {
                                    iWebUiBinder.getWebEntity().loginStateSync = false;
                                    iWebUiBinder.getWebEntity().syncingUri = null;
                                }
                                CommonBase.BrowserAllUrlListener browserAllUrlListener2 = browserAllUrlListener;
                                if (browserAllUrlListener2 != null) {
                                    browserAllUrlListener2.onError(httpError);
                                }
                                if (iWebUiBinder.getJdWebView() != null && httpError != null) {
                                    httpError.getErrorCode();
                                }
                                WebLoginHelper.onGentokenFail();
                            }

                            @Override // com.jingdong.common.utils.CommonBase.BrowserAllUrlListener
                            public void onReady() {
                                CommonBase.BrowserAllUrlListener browserAllUrlListener2 = browserAllUrlListener;
                                if (browserAllUrlListener2 != null) {
                                    browserAllUrlListener2.onReady();
                                }
                                iWebUiBinder.getJdWebView();
                            }
                        });
                    } catch (Exception unused2) {
                    }
                }

                public static void reportCommonErr(JDWebView jDWebView, String str, String str2, String str3) {
                    ExceptionReporter.reportWebViewCommonError(str, jDWebView.getFinalUrl(), str2, str3);
                }

                public static void setAcceptThirdCookie(WebView webView, Uri uri) {
                    if (uri == null) {
                        return;
                    }
                    try {
                        String[] split = SwitchQueryFetcher.getSwitchStringValue("third_cookie_host", "").split(";");
                        if (hostListWithKeyWord(uri.toString(), uri.getHost(), split)) {
                            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                public static String stringfyJSonData(String str, Object obj, int i2, int i3) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("status", str);
                        jSONObject.put("data", obj);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("index", i2);
                        jSONObject2.put("count", i3);
                        jSONObject.put("msg", jSONObject2);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    return jSONObject.toString();
                }

                public static String addCustomParams(String str, URLParamMap uRLParamMap, String str2) {
                    String babelActivityId = WebViewHelper.getBabelActivityId(str);
                    if (SwitchQueryFetcher.getSwitchBooleanValue("addHeightCustomParamsSwitch", false)) {
                        str = addHeightCustomParams(str, uRLParamMap, str2);
                    }
                    return !TextUtils.isEmpty(babelActivityId) ? addBabelCustomParams(str, uRLParamMap, str2) : str;
                }

                public static void runOnMain(Runnable runnable) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    if (Thread.currentThread() == handler.getLooper().getThread()) {
                        runnable.run();
                    } else {
                        handler.post(runnable);
                    }
                }

                public static String stringfyJSonData(boolean z) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("status", z ? "0" : "-1");
                        jSONObject.put("data", LangUtils.SINGLE_SPACE);
                        jSONObject.put("msg", z ? "success" : "fail");
                    } catch (Exception unused) {
                    }
                    return jSONObject.toString();
                }

                public static String stringfyJSonData(String str) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("status", str);
                        jSONObject.put("data", LangUtils.SINGLE_SPACE);
                        jSONObject.put("msg", TextUtils.equals(str, "0") ? "success" : "fail");
                    } catch (Exception unused) {
                    }
                    return jSONObject.toString();
                }

                public static String addHeightCustomParams(String str, URLParamMap uRLParamMap, String str2, String str3) {
                    String addStatusBarHeightParams = !TextUtils.isEmpty(str) ? addStatusBarHeightParams(str) : "";
                    if (uRLParamMap != null) {
                        try {
                            if (TextUtils.isEmpty(str2)) {
                                str2 = RemoteMessageConst.TO;
                            }
                            if (TextUtils.isEmpty(addStatusBarHeightParams)) {
                                addStatusBarHeightParams = addStatusBarHeightParams(URLDecoder.decode(uRLParamMap.get((Object) str2), "utf-8"));
                            }
                            if (!TextUtils.isEmpty(addStatusBarHeightParams) && !"arguments".equals(str3)) {
                                uRLParamMap.put(str2, addStatusBarHeightParams);
                            }
                        } catch (Exception e2) {
                            if (OKLog.E) {
                                OKLog.e(TAG, e2.getMessage(), e2);
                            }
                        }
                    }
                    return TextUtils.isEmpty(addStatusBarHeightParams) ? str : addStatusBarHeightParams;
                }

                /* JADX WARN: Removed duplicated region for block: B:117:0x0070  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public static String addBabelCustomParams(String str, URLParamMap uRLParamMap, String str2) {
                    String str3;
                    String str4 = null;
                    try {
                        str3 = (String) Class.forName("com.jingdong.common.babelrn.utils.M2BabelUtil").getMethod("getBabelParam", new Class[0]).invoke(null, new Object[0]);
                    } catch (Throwable th) {
                        th.printStackTrace();
                        str3 = "";
                    }
                    if (!TextUtils.isEmpty(str3)) {
                        String mergeUrlAndQuery = !TextUtils.isEmpty(str) ? mergeUrlAndQuery(str, "tttparams", str3) : null;
                        if (uRLParamMap != null) {
                            if (TextUtils.isEmpty(str2)) {
                                str2 = RemoteMessageConst.TO;
                            }
                            if (TextUtils.isEmpty(mergeUrlAndQuery)) {
                                try {
                                    str4 = URLDecoder.decode(uRLParamMap.get((Object) str2), "utf-8");
                                } catch (Exception e2) {
                                    if (OKLog.E) {
                                        OKLog.e(TAG, e2.getMessage(), e2);
                                    }
                                }
                                if (!TextUtils.isEmpty(str4)) {
                                    str4 = mergeUrlAndQuery(str4, "tttparams", str3);
                                    if (!TextUtils.isEmpty(str4)) {
                                        uRLParamMap.put(str2, str4);
                                    }
                                }
                            }
                            str4 = mergeUrlAndQuery;
                            if (!TextUtils.isEmpty(str4)) {
                            }
                        } else {
                            str4 = mergeUrlAndQuery;
                        }
                    }
                    return TextUtils.isEmpty(str4) ? str : str4;
                }

                public static String addHeightCustomParams(String str, URLParamMap uRLParamMap, String str2) {
                    return addHeightCustomParams(str, uRLParamMap, str2, "");
                }
            }
