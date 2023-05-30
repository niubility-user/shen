package com.jingdong.common.web.javainterface.impl;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.push.AttributionReporter;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.phc.e;
import com.jd.sec.LogoManager;
import com.jdjr.mobilecert.MobileCertConstants;
import com.jdjr.risk.biometric.core.BiometricManager;
import com.jdjr.risk.biometric.core.JSCallback;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.voice.JDVoiceInputListener;
import com.jingdong.app.mall.voice.JDVoiceInputUtils;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.BaseApplication;
import com.jingdong.common.address.AddressConstant;
import com.jingdong.common.entity.AddressGlobal;
import com.jingdong.common.entity.JDReminderNewEntity;
import com.jingdong.common.jdmiaosha.preload.BottomNavigationConstant;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.jump.JumpCallbackListener;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.lbs.LocManager;
import com.jingdong.common.lbs.jdlocation.JDLocation;
import com.jingdong.common.lbs.jdlocation.JDLocationCache;
import com.jingdong.common.lbs.jdlocation.JDLocationCacheOption;
import com.jingdong.common.login.LoginUserBase;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.permission.PermissionHelper;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.utils.AddressUtil;
import com.jingdong.common.utils.DeviceInfoUtil;
import com.jingdong.common.utils.JDReminderNewUtils;
import com.jingdong.common.utils.StatisticsReportUtil;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.utils.WeixinUtil;
import com.jingdong.common.utils.security.JDUUIDEncHelper;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.common.web.R;
import com.jingdong.common.web.WebConstants;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.entity.JsInputEntity;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.managers.PerformanceManager;
import com.jingdong.common.web.managers.WebWhiteScreenHolder;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.urlcheck.impl.WxPayResultBroadCastReceiver;
import com.jingdong.common.web.util.MediaUtils;
import com.jingdong.common.web.util.MultiMedia;
import com.jingdong.common.web.util.SaveImageUtils;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.weixin.WeiXinEntity;
import com.jingdong.common.weixin.WeiXinPayUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.mm.opensdk.modelbiz.OpenWebview;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.un.lib.popup.JDTopPopupWindowHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class JDAppUnite extends BaseWebComponent implements IJavaInterface {
    private static final String TAG = "JDAppUnite";
    private HttpGroup httpGroup;
    private boolean isPreRender;
    private String jdVoiceCallbackName;
    private JDVoiceInputUtils jdVoiceInputUtils;
    private JsBridgeEntity jsBridgeEntity;
    public LocalBroadcastManager localBroadcastManager;
    private WxPayResultBroadCastReceiver mWxPayResultBroadCastReceiver;
    private ConcurrentHashMap<String, HttpRequest> requests;
    private JDWebView webView;
    private WeixinReceiver weixinReceiver;
    private String wxPayJscallbackname;

    /* loaded from: classes12.dex */
    public static class WeixinReceiver extends BroadcastReceiver {
        public static final String WX_MINIPROGRAM_CALLBACK_ACTION = "com.jd.wxMiniProgramAction";
        private String callbackName;
        private IWebUiBinder webUiBinder;

        public WeixinReceiver(IWebUiBinder iWebUiBinder, String str) {
            this.webUiBinder = iWebUiBinder;
            this.callbackName = str;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (this.webUiBinder != null) {
                try {
                    WebUtils.sendJSONStr2M(this.webUiBinder, this.callbackName, WebUtils.stringfyJSonData("0", intent.getStringExtra("extraData"), "success"));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public JDAppUnite(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(String str, String str2) {
        this.webView.injectJs("javascript:" + str + "('" + str2 + "');");
    }

    private void awakeJdVoiceActivity() {
        JumpUtil.execJumpByDes(JumpUtil.VALUE_DES_VOICEINPUT, this.webUiBinder.getUi().getContext(), null, new JumpCallbackListener() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.24
            @Override // com.jingdong.common.jump.JumpCallbackListener
            public void onError() {
                if (Log.D) {
                    Log.d(JDAppUnite.TAG, "jump to voiceinput sdk  error!");
                }
                if (((BaseWebComponent) JDAppUnite.this).webUiBinder == null || ((BaseWebComponent) JDAppUnite.this).webUiBinder.getBaseActivity().isFinishing()) {
                    return;
                }
                WebUtils.sendJSONStr2M(((BaseWebComponent) JDAppUnite.this).webUiBinder, JDAppUnite.this.jdVoiceCallbackName, WebUtils.stringfyJSonData("-1", "", "awake JDVoiceInputSdk fail(jump fail)"));
            }

            @Override // com.jingdong.common.jump.JumpCallbackListener
            public void onSuccess() {
                if (Log.D) {
                    Log.d(JDAppUnite.TAG, "jump to voiceinput sdk  success!");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d(String str, String str2) {
        this.webView.injectJs("javascript:" + str + "('" + str2 + "');");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callBackToH5(String str, String str2, String str3, Object obj, String str4) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (TextUtils.isEmpty(str3)) {
                str3 = "";
            }
            jSONObject.put("status", str3);
            if (obj == null) {
                obj = "";
            }
            jSONObject.put("data", obj);
            if (TextUtils.isEmpty(str4)) {
                str4 = "";
            }
            jSONObject.put("msg", str4);
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            jSONObject.put("callBackId", str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        if (Log.D) {
            Log.d(TAG, "callBackToH5, " + jSONObject.toString());
        }
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) {
            return;
        }
        this.webUiBinder.getJdWebView().injectJs("javascript:" + str + "('" + jSONObject.toString() + "');");
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x014c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:66:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void callRouterModule(String str, final boolean z) {
        JSONException jSONException;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        final String str7;
        Object invoke;
        String optString;
        String str8 = "";
        if (Log.D) {
            Log.d(TAG, "callRouterModule, jsonStr: " + str);
        }
        if (this.webUiBinder == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            optString = jSONObject.optString("routerURL", "");
            str3 = jSONObject.optString("routerParam", "");
            try {
                str6 = jSONObject.optString("callBackName", "");
                try {
                    str4 = jSONObject.optString("callBackId", "");
                    try {
                    } catch (JSONException e2) {
                        jSONException = e2;
                        str2 = "";
                        str5 = str2;
                    }
                } catch (JSONException e3) {
                    jSONException = e3;
                    str2 = "";
                    str4 = str2;
                    str5 = str4;
                }
            } catch (JSONException e4) {
                jSONException = e4;
                str2 = "";
                str4 = str2;
                str5 = str4;
                callBackToH5(str8, str4, "-1", "", "JSON\u89e3\u6790\u5f02\u5e38: " + str);
                jSONException.printStackTrace();
                str6 = str8;
                str7 = str3;
                str8 = str2;
                try {
                    final String str9 = str6;
                    final String str10 = str4;
                    invoke = Class.forName(str5.trim()).getMethod(str8.trim(), IRouterParams.class).invoke(null, new IRouterParams() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.22
                        @Override // com.jingdong.common.web.IRouterParams
                        public Context getContext() {
                            return ((BaseWebComponent) JDAppUnite.this).webUiBinder.getBaseActivity();
                        }

                        @Override // com.jingdong.common.web.IRouterParams
                        public String getRouterParam() {
                            return str7;
                        }

                        @Override // com.jingdong.common.web.IRouterParams
                        public void onCallBack(Object obj) {
                            if (z) {
                                return;
                            }
                            JDAppUnite.this.callBackToH5(str9, str10, "0", obj, "");
                        }
                    });
                    if (invoke == null) {
                    }
                } catch (Exception e5) {
                    callBackToH5(str6, str4, "-1", "", "\u7c7b\u6216\u65b9\u6cd5\u5f02\u5e38: " + e5.getMessage());
                    e5.printStackTrace();
                    return;
                }
            }
        } catch (JSONException e6) {
            jSONException = e6;
            str2 = "";
            str3 = str2;
            str4 = str3;
        }
        if (TextUtils.isEmpty(optString)) {
            callBackToH5(str6, str4, "-1", "", "routerUrl\u4e0d\u80fd\u4e3a\u7a7a");
            return;
        }
        Uri parse = Uri.parse(optString);
        String scheme = parse.getScheme();
        str5 = parse.getHost();
        try {
            str8 = parse.getLastPathSegment();
            if (Log.D) {
                Log.d(TAG, "callRouterModule, " + scheme + "-" + str5 + "-" + str8);
            }
        } catch (JSONException e7) {
            jSONException = e7;
            str2 = str8;
            str8 = str6;
            callBackToH5(str8, str4, "-1", "", "JSON\u89e3\u6790\u5f02\u5e38: " + str);
            jSONException.printStackTrace();
            str6 = str8;
            str7 = str3;
            str8 = str2;
            final String str92 = str6;
            final String str102 = str4;
            invoke = Class.forName(str5.trim()).getMethod(str8.trim(), IRouterParams.class).invoke(null, new IRouterParams() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.22
                @Override // com.jingdong.common.web.IRouterParams
                public Context getContext() {
                    return ((BaseWebComponent) JDAppUnite.this).webUiBinder.getBaseActivity();
                }

                @Override // com.jingdong.common.web.IRouterParams
                public String getRouterParam() {
                    return str7;
                }

                @Override // com.jingdong.common.web.IRouterParams
                public void onCallBack(Object obj) {
                    if (z) {
                        return;
                    }
                    JDAppUnite.this.callBackToH5(str92, str102, "0", obj, "");
                }
            });
            if (invoke == null) {
                return;
            }
            return;
        }
        if (!TextUtils.isEmpty(scheme) && JDTopPopupWindowHelper.CUSTOM_ROUTER_TYPE.equals(scheme.trim()) && !TextUtils.isEmpty(str5) && !TextUtils.isEmpty(str8)) {
            str7 = str3;
            final String str922 = str6;
            final String str1022 = str4;
            invoke = Class.forName(str5.trim()).getMethod(str8.trim(), IRouterParams.class).invoke(null, new IRouterParams() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.22
                @Override // com.jingdong.common.web.IRouterParams
                public Context getContext() {
                    return ((BaseWebComponent) JDAppUnite.this).webUiBinder.getBaseActivity();
                }

                @Override // com.jingdong.common.web.IRouterParams
                public String getRouterParam() {
                    return str7;
                }

                @Override // com.jingdong.common.web.IRouterParams
                public void onCallBack(Object obj) {
                    if (z) {
                        return;
                    }
                    JDAppUnite.this.callBackToH5(str922, str1022, "0", obj, "");
                }
            });
            if (invoke == null || !z) {
                return;
            }
            callBackToH5(str6, str4, "0", invoke, "");
            return;
        }
        callBackToH5(str6, str4, "-1", "", "routerUrl\u4e0d\u7b26\u5408\u6807\u51c6" + scheme + "--" + str5 + "--" + str8);
    }

    private void callback2Js(String str, String str2, String str3, String str4) {
        if (this.webUiBinder.getJdWebView() == null || TextUtils.isEmpty(str)) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            if (TextUtils.isEmpty(str3)) {
                str3 = "-1";
            }
            jSONObject.put("code", str3);
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put("callBackId", str2);
            }
            if (!TextUtils.isEmpty(str4)) {
                jSONObject.put("msg", str4);
            }
        } catch (JSONException unused) {
        }
        SaveImageUtils.logX("exec js:" + jSONObject);
        this.webUiBinder.getJdWebView().injectJs("javascript: window." + str + "('" + jSONObject + "');");
    }

    private void decryptParams(final String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        Map<String, String> a = com.jd.phc.e.c(JdSdk.getInstance().getApplication()).a(Uri.decode(str2));
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.putAll(a);
        WebUtils.stringfyJSonData("0", Uri.encode(jDJSONObject.toJSONString()), "");
        this.webUiBinder.getBaseActivity().post(new Runnable
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x003e: INVOKE 
              (wrap: com.jingdong.common.BaseActivity : 0x0035: INVOKE 
              (wrap: com.jingdong.common.web.uibinder.IWebUiBinder : 0x0033: IGET (r2v0 'this' com.jingdong.common.web.javainterface.impl.JDAppUnite A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:6) com.jingdong.common.web.BaseWebComponent.webUiBinder com.jingdong.common.web.uibinder.IWebUiBinder)
             type: INTERFACE call: com.jingdong.common.web.uibinder.IWebUiBinder.getBaseActivity():com.jingdong.common.BaseActivity A[MD:():com.jingdong.common.BaseActivity (m), WRAPPED] (LINE:6))
              (wrap: java.lang.Runnable : 0x003b: CONSTRUCTOR 
              (r2v0 'this' com.jingdong.common.web.javainterface.impl.JDAppUnite A[IMMUTABLE_TYPE, THIS])
              (r4 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r3v0 'str' java.lang.String A[DONT_INLINE])
             A[MD:(com.jingdong.common.web.javainterface.impl.JDAppUnite, java.lang.String, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.web.javainterface.impl.JDAppUnite.11.<init>(com.jingdong.common.web.javainterface.impl.JDAppUnite, java.lang.String, java.lang.String):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:6) in method: com.jingdong.common.web.javainterface.impl.JDAppUnite.decryptParams(java.lang.String, java.lang.String):void, file: classes12.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 19 more
            */
        /*
            this = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L7
            return
        L7:
            com.jingdong.jdsdk.JdSdk r0 = com.jingdong.jdsdk.JdSdk.getInstance()
            android.app.Application r0 = r0.getApplication()
            com.jd.phc.e r0 = com.jd.phc.e.c(r0)
            java.lang.String r4 = android.net.Uri.decode(r4)
            java.util.Map r4 = r0.a(r4)
            com.jd.framework.json.JDJSONObject r0 = new com.jd.framework.json.JDJSONObject
            r0.<init>()
            r0.putAll(r4)
            java.lang.String r4 = r0.toJSONString()
            java.lang.String r4 = android.net.Uri.encode(r4)
            java.lang.String r0 = "0"
            java.lang.String r1 = ""
            java.lang.String r4 = com.jingdong.common.web.util.WebUtils.stringfyJSonData(r0, r4, r1)
            com.jingdong.common.web.uibinder.IWebUiBinder r0 = r2.webUiBinder
            com.jingdong.common.BaseActivity r0 = r0.getBaseActivity()
            com.jingdong.common.web.javainterface.impl.JDAppUnite$11 r1 = new com.jingdong.common.web.javainterface.impl.JDAppUnite$11
            r1.<init>()
            r0.post(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.javainterface.impl.JDAppUnite.decryptParams(java.lang.String, java.lang.String):void");
    }

    private void downloadAndSaveImage(String str, final String str2, final String str3) {
        String str4;
        String str5;
        if (!MediaUtils.checkIfCanSaveFileToPublic()) {
            if (this.webUiBinder.getJdWebView() == null || TextUtils.isEmpty(str2)) {
                return;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("code", "-1");
                jSONObject.put("msg", "System version not support");
                if (!TextUtils.isEmpty(str3)) {
                    jSONObject.put("callBackId", str3);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            this.webUiBinder.getJdWebView().injectJs("javascript:" + str2 + "('" + jSONObject.toString() + "');");
            return;
        }
        try {
            String substring = str.substring(str.lastIndexOf("/") + 1);
            str4 = substring.substring(0, substring.lastIndexOf(OrderISVUtil.MONEY_DECIMAL));
        } catch (Exception e3) {
            e3.printStackTrace();
            str4 = "";
        }
        if (TextUtils.isEmpty(str4)) {
            str5 = System.currentTimeMillis() + ".png";
        } else {
            str5 = str4 + ".png";
        }
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(2);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(str5);
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
        httpSetting.setListener(new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.15
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null || httpResponse.getSaveFile() == null) {
                    return;
                }
                File saveFile = httpResponse.getSaveFile();
                Log.d(JDAppUnite.TAG, "get httpsetting save file path:" + saveFile.getPath());
                if (MediaUtils.savePictureToAlbum(((BaseWebComponent) JDAppUnite.this).webUiBinder.getBaseActivity(), saveFile) && ((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView() != null && !TextUtils.isEmpty(str2)) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("code", "0");
                        if (!TextUtils.isEmpty(str3)) {
                            jSONObject2.put("callBackId", str3);
                        }
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                    }
                    Log.d(JDAppUnite.TAG, "exec js:" + jSONObject2);
                    ((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView().injectJs("javascript:" + str2 + "('" + jSONObject2.toString() + "');");
                }
                if (saveFile.exists()) {
                    saveFile.delete();
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView() == null || TextUtils.isEmpty(str2)) {
                    return;
                }
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("code", "-1");
                    if (!TextUtils.isEmpty(str3)) {
                        jSONObject2.put("callBackId", str3);
                    }
                } catch (JSONException e4) {
                    e4.printStackTrace();
                }
                ((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView().injectJs("javascript:" + str2 + "('" + jSONObject2.toString() + "');");
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

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f(String str, String str2, boolean z, SaveImageUtils.CallBackType callBackType) {
        if (z) {
            callback2Js(str, str2, "0", "");
        } else if (callBackType == SaveImageUtils.CallBackType.PERMISSION_ERROR) {
            callback2Js(str, str2, "-1", "System version not support");
        } else {
            callback2Js(str, str2, "-1", "");
        }
    }

    private String encryptAddr(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            String trim = Base64.encodeToString(str.getBytes("utf-8"), 2).trim();
            int length = trim.length() - 2;
            if (length <= 0) {
                if (Log.E) {
                    Log.e(TAG, "encrypt addr error: encode to Base64 Error, after encoded: " + trim);
                }
                return "";
            }
            int nextInt = new Random().nextInt(5) + 5;
            return (getRandomString(nextInt) + trim.substring(0, length) + nextInt + trim.substring(length)).trim();
        } catch (Exception unused) {
            return "";
        }
    }

    private void encryptParams(final String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        HashMap hashMap = new HashMap();
        try {
            JDJSONObject parseObject = JDJSON.parseObject(Uri.decode(str2));
            for (String str3 : parseObject.keySet()) {
                hashMap.put(str3, TextUtils.isEmpty(parseObject.getString(str3)) ? "" : parseObject.getString(str3));
            }
        } catch (Exception unused) {
        }
        WebUtils.stringfyJSonData("0", Uri.encode(com.jd.phc.e.c(JdSdk.getInstance().getApplication()).b(hashMap, e.b.MODIFIED_BASE64)), "");
        this.webUiBinder.getBaseActivity().post(new Runnable
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0065: INVOKE 
              (wrap: com.jingdong.common.BaseActivity : 0x005c: INVOKE 
              (wrap: com.jingdong.common.web.uibinder.IWebUiBinder : 0x005a: IGET (r5v0 'this' com.jingdong.common.web.javainterface.impl.JDAppUnite A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:8) com.jingdong.common.web.BaseWebComponent.webUiBinder com.jingdong.common.web.uibinder.IWebUiBinder)
             type: INTERFACE call: com.jingdong.common.web.uibinder.IWebUiBinder.getBaseActivity():com.jingdong.common.BaseActivity A[MD:():com.jingdong.common.BaseActivity (m), WRAPPED] (LINE:8))
              (wrap: java.lang.Runnable : 0x0062: CONSTRUCTOR 
              (r5v0 'this' com.jingdong.common.web.javainterface.impl.JDAppUnite A[IMMUTABLE_TYPE, THIS])
              (r7 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r6v0 'str' java.lang.String A[DONT_INLINE])
             A[MD:(com.jingdong.common.web.javainterface.impl.JDAppUnite, java.lang.String, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.web.javainterface.impl.JDAppUnite.12.<init>(com.jingdong.common.web.javainterface.impl.JDAppUnite, java.lang.String, java.lang.String):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:8) in method: com.jingdong.common.web.javainterface.impl.JDAppUnite.encryptParams(java.lang.String, java.lang.String):void, file: classes12.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 19 more
            */
        /*
            this = this;
            java.lang.String r0 = ""
            boolean r1 = android.text.TextUtils.isEmpty(r7)
            if (r1 == 0) goto L9
            return
        L9:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            java.lang.String r7 = android.net.Uri.decode(r7)     // Catch: java.lang.Exception -> L3e
            com.jd.framework.json.JDJSONObject r7 = com.jd.framework.json.JDJSON.parseObject(r7)     // Catch: java.lang.Exception -> L3e
            java.util.Set r2 = r7.keySet()     // Catch: java.lang.Exception -> L3e
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Exception -> L3e
        L1e:
            boolean r3 = r2.hasNext()     // Catch: java.lang.Exception -> L3e
            if (r3 == 0) goto L3e
            java.lang.Object r3 = r2.next()     // Catch: java.lang.Exception -> L3e
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Exception -> L3e
            java.lang.String r4 = r7.getString(r3)     // Catch: java.lang.Exception -> L3e
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Exception -> L3e
            if (r4 == 0) goto L36
            r4 = r0
            goto L3a
        L36:
            java.lang.String r4 = r7.getString(r3)     // Catch: java.lang.Exception -> L3e
        L3a:
            r1.put(r3, r4)     // Catch: java.lang.Exception -> L3e
            goto L1e
        L3e:
            com.jingdong.jdsdk.JdSdk r7 = com.jingdong.jdsdk.JdSdk.getInstance()
            android.app.Application r7 = r7.getApplication()
            com.jd.phc.e r7 = com.jd.phc.e.c(r7)
            com.jd.phc.e$b r2 = com.jd.phc.e.b.MODIFIED_BASE64
            java.lang.String r7 = r7.b(r1, r2)
            java.lang.String r7 = android.net.Uri.encode(r7)
            java.lang.String r1 = "0"
            java.lang.String r7 = com.jingdong.common.web.util.WebUtils.stringfyJSonData(r1, r7, r0)
            com.jingdong.common.web.uibinder.IWebUiBinder r0 = r5.webUiBinder
            com.jingdong.common.BaseActivity r0 = r0.getBaseActivity()
            com.jingdong.common.web.javainterface.impl.JDAppUnite$12 r1 = new com.jingdong.common.web.javainterface.impl.JDAppUnite$12
            r1.<init>()
            r0.post(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.javainterface.impl.JDAppUnite.encryptParams(java.lang.String, java.lang.String):void");
    }

    private JDVoiceInputUtils getJdVoiceInputUtils(String str) {
        if (this.jdVoiceInputUtils == null) {
            JDVoiceInputUtils jDVoiceInputUtils = new JDVoiceInputUtils();
            this.jdVoiceInputUtils = jDVoiceInputUtils;
            jDVoiceInputUtils.setListener(new JDVoiceInputListener() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.23
                @Override // com.jingdong.app.mall.voice.JDVoiceInputListener
                public void onResult(String str2, boolean z) {
                    if (Log.D) {
                        Log.d(JDAppUnite.TAG, "onResult!   recognizerResult :" + str2 + "isLast: " + z);
                    }
                    if (TextUtils.isEmpty(str2) || ((BaseWebComponent) JDAppUnite.this).webUiBinder == null || ((BaseWebComponent) JDAppUnite.this).webUiBinder.getBaseActivity().isFinishing()) {
                        return;
                    }
                    WebUtils.sendJSONStr2M(((BaseWebComponent) JDAppUnite.this).webUiBinder, JDAppUnite.this.jdVoiceCallbackName, WebUtils.stringfyJSonData("0", WebUtils.strigfyPair2JSonObject("result", str2), ""));
                }

                @Override // com.jingdong.app.mall.voice.JDVoiceInputListener
                public void onVoiceInputCreate() {
                    if (Log.D) {
                        Log.d(JDAppUnite.TAG, "onVoiceInputCreate!");
                    }
                }

                @Override // com.jingdong.app.mall.voice.JDVoiceInputListener
                public void onVoiceInputDestroy() {
                    if (Log.D) {
                        Log.d(JDAppUnite.TAG, "onVoiceInputDestroy!");
                    }
                }
            });
        }
        this.jdVoiceCallbackName = str;
        return this.jdVoiceInputUtils;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getPackedData(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            jSONObject.put("status", str);
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            jSONObject.put("data", str2);
            if (TextUtils.isEmpty(str3)) {
                str3 = "";
            }
            jSONObject.put("msg", str3);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return jSONObject.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getPhoneBasicInfoEncryptObj() {
        HashMap hashMap = new HashMap();
        hashMap.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
        hashMap.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
        hashMap.put("un_area", TextUtils.isEmpty(LocManager.getCommonLbsParameter()) ? "" : LocManager.getCommonLbsParameter());
        hashMap.put(BottomNavigationConstant.KEY_SYSTEM_NAME, Configuration.getProperty("client"));
        hashMap.put("systemVersion", BaseInfo.getAndroidVersion());
        hashMap.put("appVersion", BaseInfo.getAppVersionName());
        hashMap.put("appBuild", String.valueOf(BaseInfo.getAppVersionCode()));
        hashMap.put("uuid", StatisticsReportUtil.readDeviceUUID());
        hashMap.put(Configuration.PARTNER, Configuration.getProperty(Configuration.PARTNER));
        hashMap.put("networkType", WebUtils.getNetworkType());
        hashMap.put("aid", BaseInfo.getAndroidId());
        hashMap.put("oaid", BaseInfo.getOAID());
        return Uri.encode(com.jd.phc.e.c(JdSdk.getInstance().getApplication()).b(hashMap, e.b.MODIFIED_BASE64));
    }

    private JSONObject getPhoneBasicInfoObj() {
        JSONObject jSONObject = new JSONObject();
        try {
            String encryptAddr = encryptAddr(AddressUtil.getAddressGlobal() == null ? "" : AddressUtil.getAddressGlobal().toOrderStr().toString());
            JDLocationCacheOption jDLocationCacheOption = new JDLocationCacheOption();
            jDLocationCacheOption.setBusinessId(WebConstants.LBS_ID);
            JDLocation location = JDLocationCache.getInstance().getLocation(WebUtils.getLBSOptionWithBaseSceneId(jDLocationCacheOption));
            IWebUiBinder iWebUiBinder = this.webUiBinder;
            if (iWebUiBinder != null) {
                WebUtils.reportDeprecatedLBSBridge(iWebUiBinder.getJdWebView(), "JDAppUnite#getPhoneBasicInfo");
            }
            JDUUIDEncHelper.EncryptResult encrypt = JDUUIDEncHelper.encrypt(StatisticsReportUtil.readDeviceUUID());
            String str = "-";
            if (encrypt != null) {
                str = encrypt.eu + "-" + encrypt.fv;
            }
            jSONObject.put("eufv", "1");
            jSONObject.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand()).put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel()).put(HybridSDK.LNG, location != null ? location.getLng() : 0.0d).put("lat", location != null ? location.getLat() : 0.0d).put("un_area", LocManager.getCommonLbsParameter()).put(BottomNavigationConstant.KEY_SYSTEM_NAME, Configuration.getProperty("client")).put("systemVersion", BaseInfo.getAndroidVersion()).put("appVersion", BaseInfo.getAppVersionName()).put("appBuild", String.valueOf(BaseInfo.getAppVersionCode())).put("uuid", str).put(Configuration.PARTNER, Configuration.getProperty(Configuration.PARTNER)).put("networkType", WebUtils.getNetworkType()).put(AddressConstant.INTENT_EXTAS_ADDGLOBAL, "").put("ip", DeviceInfoUtil.getIpAddress()).put("cAddressGlobal", encryptAddr).put("aid", BaseInfo.getAndroidId()).put("oaid", BaseInfo.getOAID());
        } catch (JSONException e2) {
            if (Log.E) {
                Log.e(TAG, e2.getMessage(), e2);
            }
        }
        return jSONObject;
    }

    private String getRandomString(int i2) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            int nextInt = random.nextInt(3);
            if (nextInt == 0) {
                sb.append((char) Math.round((Math.random() * 25.0d) + 65.0d));
            } else if (nextInt == 1) {
                sb.append((char) Math.round((Math.random() * 25.0d) + 97.0d));
            } else if (nextInt == 2) {
                sb.append(new Random().nextInt(10));
            }
        }
        return sb.toString();
    }

    private boolean isWechateAvailable(IWebUiBinder iWebUiBinder, String str, IWXAPI iwxapi) {
        if (!iwxapi.isWXAppInstalled()) {
            WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-2", "", "\u62b1\u6b49\uff0c\u60a8\u5c1a\u672a\u5b89\u88c5\u5fae\u4fe1"));
            return false;
        } else if (WeixinUtil.isWXAppSupportAPI()) {
            return true;
        } else {
            WebUtils.sendJSONStr2M(iWebUiBinder, str, WebUtils.stringfyJSonData("-1", "", "\u8bf7\u5347\u7ea7\u5fae\u4fe1\u5230\u6700\u65b0\u7248\u672c\u4f7f\u7528"));
            return false;
        }
    }

    private void jumpToMiniProgram(String str, final String str2) {
        if (Log.D) {
            Log.d(TAG, "webview jumpToMiniProgram: " + str);
        }
        if (this.webUiBinder != null && !TextUtils.isEmpty(str)) {
            ExceptionReporter.reportWebViewCommonError("jumpToWxMini", this.webUiBinder.getJdWebView() != null ? this.webUiBinder.getJdWebView().getFinalUrl() : null, "", str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                final IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this.webUiBinder.getBaseActivity(), "wxe75a2e68877315fb");
                final WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
                if (isWechateAvailable(this.webUiBinder, str2, createWXAPI)) {
                    req.userName = jSONObject.optString(MobileCertConstants.USERNAME, "");
                    req.path = jSONObject.optString("path", "");
                    req.miniprogramType = jSONObject.optInt("miniProgramType", 0);
                    if (SwitchQueryFetcher.getSwitchBooleanValue("wvJumpWxMain", false)) {
                        BaseActivity baseActivity = this.webUiBinder.getBaseActivity();
                        if (baseActivity != null) {
                            baseActivity.post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.25
                                @Override // java.lang.Runnable
                                public void run() {
                                    JDAppUnite.this.sendWechatJump(createWXAPI, req, str2);
                                }
                            });
                        }
                    } else {
                        sendWechatJump(createWXAPI, req, str2);
                    }
                    return;
                }
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        WebUtils.sendJSONStr2M(this.webUiBinder, str2, WebUtils.stringfyJSonData("1", "", "model parse failed"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void permissionBack(String str, String str2, String str3) {
        String stringfyJSonData = WebUtils.stringfyJSonData(str, "", str3);
        if (Log.D) {
            Log.d(TAG, stringfyJSonData);
        }
        WebUtils.sendJSONStr2M(this.webUiBinder, str2, stringfyJSonData);
    }

    private void registerWeixinReceiver(String str) {
        if (this.localBroadcastManager == null) {
            this.localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getInstance().getBaseContext());
        }
        if (this.weixinReceiver == null) {
            this.weixinReceiver = new WeixinReceiver(this.webUiBinder, str);
            this.localBroadcastManager.registerReceiver(this.weixinReceiver, new IntentFilter("com.jd.wxMiniProgramAction"));
        }
    }

    private void reportException(final String str, JsInputEntity jsInputEntity) {
        String str2;
        String str3 = (String) jsInputEntity.getParamValue("functionInfo");
        String str4 = (String) jsInputEntity.getParamValue("errMsg");
        String str5 = (String) jsInputEntity.getParamValue("exceptionInfo");
        String str6 = (String) jsInputEntity.getParamValue("url");
        String str7 = (String) jsInputEntity.getParamValue("extra");
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("errCode", "964");
            hashMap.put(PerformanceManager.ERR_TYPE, "2");
            hashMap.put("function", str3);
            hashMap.put("errMsg", str4);
            hashMap.put(jpbury.t.f20145j, str5);
            hashMap.put("occurTime", ExceptionReporter.getCurrentMicrosecond());
            if (str6 == null) {
                str6 = "";
            }
            hashMap.put("url", str6);
            hashMap.put("reserved1", str7);
            ExceptionReporter.sendExceptionData(JdSdk.getInstance().getApplication(), hashMap);
            str2 = "0";
        } catch (Throwable th) {
            if (OKLog.E) {
                OKLog.e("ExceptionReporter", th);
            }
            str2 = "-1";
        }
        WebUtils.stringfyJSonData(str2, "", "");
        this.webUiBinder.getBaseActivity().post(new Runnable
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0088: INVOKE 
              (wrap: com.jingdong.common.BaseActivity : 0x007f: INVOKE 
              (wrap: com.jingdong.common.web.uibinder.IWebUiBinder : 0x007d: IGET (r10v0 'this' com.jingdong.common.web.javainterface.impl.JDAppUnite A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:19) com.jingdong.common.web.BaseWebComponent.webUiBinder com.jingdong.common.web.uibinder.IWebUiBinder)
             type: INTERFACE call: com.jingdong.common.web.uibinder.IWebUiBinder.getBaseActivity():com.jingdong.common.BaseActivity A[MD:():com.jingdong.common.BaseActivity (m), WRAPPED] (LINE:19))
              (wrap: java.lang.Runnable : 0x0085: CONSTRUCTOR 
              (r10v0 'this' com.jingdong.common.web.javainterface.impl.JDAppUnite A[IMMUTABLE_TYPE, THIS])
              (r12 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
              (r11v0 'str' java.lang.String A[DONT_INLINE])
             A[MD:(com.jingdong.common.web.javainterface.impl.JDAppUnite, java.lang.String, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.web.javainterface.impl.JDAppUnite.10.<init>(com.jingdong.common.web.javainterface.impl.JDAppUnite, java.lang.String, java.lang.String):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:19) in method: com.jingdong.common.web.javainterface.impl.JDAppUnite.reportException(java.lang.String, com.jingdong.common.web.entity.JsInputEntity):void, file: classes12.dex
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
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            java.lang.String r0 = ""
            java.lang.String r1 = "functionInfo"
            java.lang.Object r1 = r12.getParamValue(r1)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "errMsg"
            java.lang.Object r3 = r12.getParamValue(r2)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.String r4 = "exceptionInfo"
            java.lang.Object r4 = r12.getParamValue(r4)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.String r5 = "url"
            java.lang.Object r6 = r12.getParamValue(r5)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.String r7 = "extra"
            java.lang.Object r12 = r12.getParamValue(r7)
            java.lang.String r12 = (java.lang.String) r12
            java.util.HashMap r7 = new java.util.HashMap     // Catch: java.lang.Throwable -> L6d
            r7.<init>()     // Catch: java.lang.Throwable -> L6d
            java.lang.String r8 = "errCode"
            java.lang.String r9 = "964"
            r7.put(r8, r9)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r8 = "errType"
            java.lang.String r9 = "2"
            r7.put(r8, r9)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r8 = "function"
            r7.put(r8, r1)     // Catch: java.lang.Throwable -> L6d
            r7.put(r2, r3)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r1 = "exception"
            r7.put(r1, r4)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r1 = "occurTime"
            java.lang.String r2 = com.jingdong.jdsdk.network.toolbox.ExceptionReporter.getCurrentMicrosecond()     // Catch: java.lang.Throwable -> L6d
            r7.put(r1, r2)     // Catch: java.lang.Throwable -> L6d
            if (r6 != 0) goto L57
            r6 = r0
        L57:
            r7.put(r5, r6)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r1 = "reserved1"
            r7.put(r1, r12)     // Catch: java.lang.Throwable -> L6d
            com.jingdong.jdsdk.JdSdk r12 = com.jingdong.jdsdk.JdSdk.getInstance()     // Catch: java.lang.Throwable -> L6d
            android.app.Application r12 = r12.getApplication()     // Catch: java.lang.Throwable -> L6d
            com.jingdong.jdsdk.network.toolbox.ExceptionReporter.sendExceptionData(r12, r7)     // Catch: java.lang.Throwable -> L6d
            java.lang.String r12 = "0"
            goto L79
        L6d:
            r12 = move-exception
            boolean r1 = com.jingdong.sdk.oklog.OKLog.E
            if (r1 == 0) goto L77
            java.lang.String r1 = "ExceptionReporter"
            com.jingdong.sdk.oklog.OKLog.e(r1, r12)
        L77:
            java.lang.String r12 = "-1"
        L79:
            java.lang.String r12 = com.jingdong.common.web.util.WebUtils.stringfyJSonData(r12, r0, r0)
            com.jingdong.common.web.uibinder.IWebUiBinder r0 = r10.webUiBinder
            com.jingdong.common.BaseActivity r0 = r0.getBaseActivity()
            com.jingdong.common.web.javainterface.impl.JDAppUnite$10 r1 = new com.jingdong.common.web.javainterface.impl.JDAppUnite$10
            r1.<init>()
            r0.post(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.javainterface.impl.JDAppUnite.reportException(java.lang.String, com.jingdong.common.web.entity.JsInputEntity):void");
    }

    private void requestDirectPermissions(final String str, String str2, String str3, boolean z, final String str4) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            IWebUiBinder iWebUiBinder = this.webUiBinder;
            Bundle generateBundle = PermissionHelper.generateBundle("webview", WebJavaScript.class.getSimpleName(), (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) ? "requestPermissions" : this.webUiBinder.getJdWebView().getFinalUrl(), z);
            PermissionHelper.PermissionResultCallBack permissionResultCallBack = new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.13
                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onCanceled() {
                    JDAppUnite.this.permissionBack("-2", str4, str);
                }

                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onDenied() {
                    JDAppUnite.this.permissionBack("-1", str4, str);
                }

                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onGranted() {
                    JDAppUnite.this.permissionBack("0", str4, str);
                }

                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onIgnored() {
                    JDAppUnite.this.permissionBack("-3", str4, str);
                }

                @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
                public void onOpenSetting() {
                    JDAppUnite.this.permissionBack("1", str4, str);
                }
            };
            if (!PermissionHelper.hasPermission(generateBundle, str)) {
                PermissionHelper.requestPermission(this.webUiBinder.getBaseActivity(), generateBundle, str, permissionResultCallBack, str2, str3);
                return;
            } else {
                permissionBack("0", str4, str);
                return;
            }
        }
        permissionBack("-4", str4, str);
    }

    private void requestPermissions(final String str, final String str2, String str3, boolean z) {
        String str4;
        String str5;
        String str6;
        String str7;
        if (TextUtils.isEmpty(str)) {
            permissionBack("-4", str2, str);
            return;
        }
        String str8 = null;
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1367751899:
                if (str.equals("camera")) {
                    c2 = 0;
                    break;
                }
                break;
            case 92896879:
                if (str.equals("album")) {
                    c2 = 1;
                    break;
                }
                break;
            case 93166550:
                if (str.equals("audio")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                if (TextUtils.isEmpty(str3)) {
                    str3 = "\u4eac\u4e1c\u9700\u8981\u7533\u8bf7\u76f8\u673a\u6743\u9650\uff0c\u4ee5\u4fbf\u60a8\u80fd\u62cd\u6444\u3001\u62cd\u7167\u3001\u626b\u7801\u3001\u5237\u8138\u767b\u5f55";
                }
                str4 = "\u76f8\u673a";
                str8 = "android.permission.CAMERA";
                str5 = str3;
                str7 = str8;
                str6 = str4;
                break;
            case 1:
                if (TextUtils.isEmpty(str3)) {
                    str3 = "\u4eac\u4e1c\u9700\u8981\u7533\u8bf7\u5b58\u50a8\u6743\u9650\uff0c\u4ee5\u8bfb\u53d6\u6216\u5199\u5165\u56fe\u7247\u3001\u6587\u4ef6\u3001\u5d29\u6e83\u65e5\u5fd7\u4fe1\u606f\uff0c\u4fdd\u969c\u5ba2\u6237\u7aef\u7a33\u5b9a\u8fd0\u884c";
                }
                str5 = str3;
                str6 = "\u5b58\u50a8";
                str7 = "noNeedRequest";
                break;
            case 2:
                if (TextUtils.isEmpty(str3)) {
                    str3 = "\u4eac\u4e1c\u9700\u8981\u7533\u8bf7\u9ea6\u514b\u98ce\u6743\u9650\uff0c\u4ee5\u4fbf\u60a8\u80fd\u4f7f\u7528\u8bed\u97f3\u8d2d\u7269\u6216\u4e0e\u5ba2\u670d\u8fdb\u884c\u54a8\u8be2\u548c\u4e92\u52a8";
                }
                str4 = "\u9ea6\u514b\u98ce";
                str8 = "android.permission.RECORD_AUDIO";
                str5 = str3;
                str7 = str8;
                str6 = str4;
                break;
            default:
                str4 = "";
                str5 = str3;
                str7 = str8;
                str6 = str4;
                break;
        }
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        Bundle generateBundle = PermissionHelper.generateBundle("webview", WebJavaScript.class.getSimpleName(), (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) ? "requestPermissions" : this.webUiBinder.getJdWebView().getFinalUrl(), z);
        PermissionHelper.PermissionResultCallBack permissionResultCallBack = new PermissionHelper.PermissionResultCallBack() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.14
            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onCanceled() {
                JDAppUnite.this.permissionBack("-2", str2, str);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onDenied() {
                JDAppUnite.this.permissionBack("-1", str2, str);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onGranted() {
                JDAppUnite.this.permissionBack("0", str2, str);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onIgnored() {
                JDAppUnite.this.permissionBack("-3", str2, str);
            }

            @Override // com.jingdong.common.permission.PermissionHelper.PermissionResultCallBack
            public void onOpenSetting() {
                JDAppUnite.this.permissionBack("1", str2, str);
            }
        };
        if (!TextUtils.isEmpty(str7)) {
            if (!("noNeedRequest".equalsIgnoreCase(str7) ? true : PermissionHelper.hasPermission(generateBundle, str7))) {
                PermissionHelper.requestPermission(this.webUiBinder.getBaseActivity(), generateBundle, str7, permissionResultCallBack, str6, str5);
                return;
            } else {
                permissionBack("0", str2, str);
                return;
            }
        }
        permissionBack("-4", str2, str);
    }

    private void saveVideo2Album(JsInputEntity jsInputEntity) {
        String str = jsInputEntity.getParamValue("url") == null ? "" : (String) jsInputEntity.getParamValue("url");
        String str2 = jsInputEntity.getParamValue("callBackId") != null ? (String) jsInputEntity.getParamValue("callBackId") : "";
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (this.httpGroup == null) {
            this.httpGroup = HttpGroupUtils.getHttpGroupaAsynPool();
        }
        if (this.requests == null) {
            this.requests = new ConcurrentHashMap<>();
        }
        if (this.requests.containsKey(str + str2)) {
            return;
        }
        HttpRequest saveVideoToAlbum = WebUtils.saveVideoToAlbum(this.webUiBinder, str, jsInputEntity.callBackName, str2, this.httpGroup);
        if (saveVideoToAlbum != null) {
            this.requests.put(str + str2, saveVideoToAlbum);
        }
        WebUnifiedMtaUtil.permissionReport(this.webUiBinder, "AndroidSound-saveVideo2Album", jsInputEntity.params);
    }

    private void sendDataToM(String str, String str2) {
        if (this.webUiBinder.getJdWebView() != null) {
            this.webUiBinder.getJdWebView().injectJs("javascript:" + str + "(" + str2 + ");");
            if (Log.D) {
                Log.d("HHH_JDAppUnite", "sendDataToM, injectJs--> javascript:" + str + "(" + str2 + ");");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendWechatJump(IWXAPI iwxapi, WXLaunchMiniProgram.Req req, String str) {
        try {
            if (!Boolean.valueOf(iwxapi.sendReq(req)).booleanValue()) {
                WebUtils.sendJSONStr2M(this.webUiBinder, str, WebUtils.stringfyJSonData("-3", "", "send request failed"));
            } else {
                WebUtils.sendJSONStr2M(this.webUiBinder, str, WebUtils.stringfyJSonData("0", "", "jumpSuccess"));
                registerWeixinReceiver(str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JavascriptInterface
    public void addReminder(String str) {
        Exception exc;
        String str2;
        String str3;
        JSONObject jSONObject;
        String optString;
        String optString2;
        String optString3;
        String optString4;
        String optString5;
        String optString6;
        String optString7;
        String optString8;
        String optString9;
        String optString10;
        String optString11;
        String str4 = "0";
        if (Log.D) {
            Log.d("HHH_JDAppUnite", "addReminder, jsonStr: " + str);
        }
        int i2 = 0;
        i2 = 0;
        String str5 = "";
        if (TextUtils.isEmpty(str)) {
            str3 = "";
        } else {
            try {
                jSONObject = new JSONObject(str);
                optString = jSONObject.optString("businessType", "");
                optString2 = jSONObject.optString("showTag", "");
                optString3 = jSONObject.optString("remindTitle", "");
                optString4 = jSONObject.optString(JshopConst.JSKEY_COUPON_BEGIN_TIME, "0");
                optString5 = jSONObject.optString("uniqueId", "");
                optString6 = jSONObject.optString("jumpStr", "");
                optString7 = jSONObject.optString("imgUrl", "");
                optString8 = jSONObject.optString("notifyTime", "0");
                optString9 = jSONObject.optString("extraStr1", "");
                optString10 = jSONObject.optString("extraStr2", "");
                optString11 = jSONObject.optString("callBackName", "");
            } catch (Exception e2) {
                exc = e2;
                str2 = "";
            }
            try {
                str5 = jSONObject.optString("callBackId", "");
                if (TextUtils.isEmpty(optString4)) {
                    optString4 = "0";
                }
                if (!TextUtils.isEmpty(optString8)) {
                    str4 = optString8;
                }
                long parseLong = Long.parseLong(optString4);
                str3 = optString11;
                i2 = JDReminderNewUtils.setReminder(new JDReminderNewEntity.ReminderBuilder(optString, optString2, optString5, optString3, parseLong, optString6).extra(optString9).more(optString10).reminderImgUrl(optString7).notificationTimeMillis(Long.parseLong(str4)).build());
            } catch (Exception e3) {
                exc = e3;
                str2 = str5;
                str5 = optString11;
                exc.printStackTrace();
                String str6 = str5;
                str5 = str2;
                str3 = str6;
                sendDataToM(str3, i2 + ",'" + str5 + "'");
            }
        }
        sendDataToM(str3, i2 + ",'" + str5 + "'");
    }

    @JavascriptInterface
    public void awakeVoiceInput(String str) {
        if (Log.D) {
            Log.d(TAG, "awakeVoiceInput  " + str);
        }
        getJdVoiceInputUtils(str);
        awakeJdVoiceActivity();
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDAppUnite-awakeVoiceInput");
    }

    @JavascriptInterface
    public void callRouterModuleWithParams(String str) {
        callRouterModule(str, false);
    }

    @JavascriptInterface
    public void callSyncRouterModuleWithParams(String str) {
        callRouterModule(str, true);
    }

    @JavascriptInterface
    public boolean canLaunchAppUri(String str) {
        List<ResolveInfo> queryIntentActivities;
        try {
            Uri parse = Uri.parse(str);
            Intent intent = new Intent();
            intent.setData(parse);
            if (this.webView != null && this.isPreRender) {
                queryIntentActivities = JdSdk.getInstance().getApplication().getPackageManager().queryIntentActivities(intent, 65536);
            } else {
                queryIntentActivities = this.webUiBinder.getBaseActivity().getPackageManager().queryIntentActivities(intent, 65536);
            }
            if (queryIntentActivities != null) {
                return queryIntentActivities.size() > 0;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            WebUnifiedMtaUtil.queryAppReport(this.webUiBinder, str);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JavascriptInterface
    public void checkReminder(String str) {
        String str2;
        String str3;
        String str4 = "0";
        if (Log.D) {
            Log.d("HHH_JDAppUnite", "checkReminder, jsonStr: " + str);
        }
        int i2 = 0;
        i2 = 0;
        String str5 = "";
        if (TextUtils.isEmpty(str)) {
            str3 = "";
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("businessType", "");
                String optString2 = jSONObject.optString(JshopConst.JSKEY_COUPON_BEGIN_TIME, "0");
                String optString3 = jSONObject.optString("uniqueId", "");
                str3 = jSONObject.optString("callBackName", "");
                try {
                    str5 = jSONObject.optString("callBackId", "");
                    if (!TextUtils.isEmpty(optString2)) {
                        str4 = optString2;
                    }
                    i2 = JDReminderNewUtils.checkReminder(optString, optString3, Long.parseLong(str4));
                } catch (Exception e2) {
                    e = e2;
                    str2 = str5;
                    str5 = str3;
                    e.printStackTrace();
                    str3 = str5;
                    str5 = str2;
                    sendDataToM(str3, i2 + ",'" + str5 + "'");
                }
            } catch (Exception e3) {
                e = e3;
                str2 = "";
            }
        }
        sendDataToM(str3, i2 + ",'" + str5 + "'");
    }

    public void downLoadCancel() {
        try {
            ConcurrentHashMap<String, HttpRequest> concurrentHashMap = this.requests;
            if (concurrentHashMap != null && concurrentHashMap.size() > 0) {
                for (Map.Entry<String, HttpRequest> entry : this.requests.entrySet()) {
                    if (entry.getValue() != null) {
                        entry.getValue().stop();
                        if (OKLog.D) {
                            OKLog.d(TAG, entry.getKey() + "is cancel");
                        }
                    }
                }
                this.requests.clear();
            }
            HttpGroup httpGroup = this.httpGroup;
            if (httpGroup != null) {
                httpGroup.onDestroy();
                this.httpGroup = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void feedbackWxPayCallback(String str, Object obj, String str2) {
        if (this.webUiBinder == null || TextUtils.isEmpty(this.wxPayJscallbackname)) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "feedbackWxPayCallback   status: " + str + "    msg: " + str2);
        }
        WebUtils.sendJSONStr2M(this.webUiBinder, this.wxPayJscallbackname, WebUtils.stringfyJSonData(str, obj, str2));
    }

    @JavascriptInterface
    public void getAllRemindersWithTimeSpanAndBusinessType(String str) {
        String str2;
        String str3;
        String str4 = "0";
        if (Log.D) {
            Log.d("HHH_JDAppUnite", "getAllRemindersWithTimeSpanAndBusinessType, jsonStr: " + str);
        }
        String str5 = "";
        if (TextUtils.isEmpty(str)) {
            str2 = "";
            str3 = str2;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("businessType", "");
                String optString2 = jSONObject.optString("fromTime", "0");
                String optString3 = jSONObject.optString("toTime", "0");
                str3 = jSONObject.optString("callBackName", "");
                try {
                    str2 = jSONObject.optString("callBackId", "");
                    try {
                        if (TextUtils.isEmpty(optString2)) {
                            optString2 = "0";
                        }
                        if (!TextUtils.isEmpty(optString3)) {
                            str4 = optString3;
                        }
                        ArrayList<JDReminderNewEntity> allRemindersByBusinessTypeDuringTimePeriod = JDReminderNewUtils.getAllRemindersByBusinessTypeDuringTimePeriod(optString, Long.parseLong(optString2), Long.parseLong(str4));
                        if (allRemindersByBusinessTypeDuringTimePeriod != null && allRemindersByBusinessTypeDuringTimePeriod.size() > 0) {
                            ArrayList arrayList = new ArrayList();
                            Iterator<JDReminderNewEntity> it = allRemindersByBusinessTypeDuringTimePeriod.iterator();
                            while (it.hasNext()) {
                                JDReminderNewEntity next = it.next();
                                if (next != null) {
                                    arrayList.add(next.getIdentificationId());
                                }
                            }
                            str5 = new JSONArray((Collection) arrayList).toString();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        sendDataToM(str3, "'" + str5 + "','" + str2 + "'");
                    }
                } catch (Exception e3) {
                    e = e3;
                    str2 = "";
                }
            } catch (Exception e4) {
                e = e4;
                str2 = "";
                str3 = str2;
            }
        }
        sendDataToM(str3, "'" + str5 + "','" + str2 + "'");
    }

    @JavascriptInterface
    public void getFingerInfo(final String str) {
        if (this.webView != null && this.isPreRender) {
            getFingerInfoXRender(str);
            return;
        }
        LogoManager.getInstance(this.webUiBinder.getBaseActivity()).getLogo();
        this.webUiBinder.getBaseActivity().post(new Runnable
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0025: INVOKE 
              (wrap: com.jingdong.common.BaseActivity : 0x001c: INVOKE 
              (wrap: com.jingdong.common.web.uibinder.IWebUiBinder : 0x001a: IGET (r3v0 'this' com.jingdong.common.web.javainterface.impl.JDAppUnite A[IMMUTABLE_TYPE, THIS]) A[WRAPPED] (LINE:4) com.jingdong.common.web.BaseWebComponent.webUiBinder com.jingdong.common.web.uibinder.IWebUiBinder)
             type: INTERFACE call: com.jingdong.common.web.uibinder.IWebUiBinder.getBaseActivity():com.jingdong.common.BaseActivity A[MD:():com.jingdong.common.BaseActivity (m), WRAPPED] (LINE:4))
              (wrap: java.lang.Runnable : 0x0022: CONSTRUCTOR 
              (r3v0 'this' com.jingdong.common.web.javainterface.impl.JDAppUnite A[IMMUTABLE_TYPE, THIS])
              (r4v0 'str' java.lang.String A[DONT_INLINE])
              (r0 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.jingdong.common.web.javainterface.impl.JDAppUnite, java.lang.String, java.lang.String):void (m), WRAPPED] call: com.jingdong.common.web.javainterface.impl.JDAppUnite.20.<init>(com.jingdong.common.web.javainterface.impl.JDAppUnite, java.lang.String, java.lang.String):void type: CONSTRUCTOR)
             type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (m)] (LINE:4) in method: com.jingdong.common.web.javainterface.impl.JDAppUnite.getFingerInfo(java.lang.String):void, file: classes12.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1105)
            	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:872)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:421)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 19 more
            */
        /*
            this = this;
            com.jingdong.common.web.ui.JDWebView r0 = r3.webView
            if (r0 == 0) goto Lc
            boolean r0 = r3.isPreRender
            if (r0 == 0) goto Lc
            r3.getFingerInfoXRender(r4)
            return
        Lc:
            com.jingdong.common.web.uibinder.IWebUiBinder r0 = r3.webUiBinder
            com.jingdong.common.BaseActivity r0 = r0.getBaseActivity()
            com.jd.sec.LogoManager r0 = com.jd.sec.LogoManager.getInstance(r0)
            java.lang.String r0 = r0.getLogo()
            com.jingdong.common.web.uibinder.IWebUiBinder r1 = r3.webUiBinder
            com.jingdong.common.BaseActivity r1 = r1.getBaseActivity()
            com.jingdong.common.web.javainterface.impl.JDAppUnite$20 r2 = new com.jingdong.common.web.javainterface.impl.JDAppUnite$20
            r2.<init>()
            r1.post(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.javainterface.impl.JDAppUnite.getFingerInfo(java.lang.String):void");
    }

    public void getFingerInfoXRender(final String str) {
        final String logo = LogoManager.getInstance(JdSdk.getInstance().getApplication()).getLogo();
        this.webView.post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.d
            @Override // java.lang.Runnable
            public final void run() {
                JDAppUnite.this.b(str, logo);
            }
        });
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.JDAPPUNITE;
    }

    @JavascriptInterface
    public void getNetWorkType(final String str) {
        if (this.webView != null && this.isPreRender) {
            getNetWorkTypeXRender(str);
            return;
        }
        final String networkType = WebUtils.getNetworkType();
        if (Log.D) {
            Log.d(TAG, "getNetWorkType:" + networkType);
        }
        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.21
            @Override // java.lang.Runnable
            public void run() {
                ((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView().injectJs("javascript:" + str + "('" + networkType + "');");
            }
        });
    }

    public void getNetWorkTypeXRender(final String str) {
        if (this.webView == null || !this.isPreRender) {
            return;
        }
        final String networkType = WebUtils.getNetworkType();
        if (Log.D) {
            Log.d(TAG, "getNetWorkType:" + networkType);
        }
        this.webView.post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.b
            @Override // java.lang.Runnable
            public final void run() {
                JDAppUnite.this.d(str, networkType);
            }
        });
    }

    @JavascriptInterface
    public void getPhoneBasicInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (Log.D) {
            Log.d(TAG, "getPhoneBasicInfo: callbackName= " + str);
        }
        String stringfyJSonData = WebUtils.stringfyJSonData("0", getPhoneBasicInfoObj(), "");
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender) {
            WebUtils.sendJSONStr2MXRender(jDWebView, str, stringfyJSonData);
        } else {
            WebUtils.sendJSONStr2M(this.webUiBinder, str, stringfyJSonData);
        }
    }

    public void getPhoneBasicInfoEncrypt(final String str) {
        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.16
            @Override // java.lang.Runnable
            public void run() {
                if (((BaseWebComponent) JDAppUnite.this).webUiBinder == null || ((BaseWebComponent) JDAppUnite.this).webUiBinder.getBaseActivity() == null || ((BaseWebComponent) JDAppUnite.this).webUiBinder.getBaseActivity().isFinishing() || TextUtils.isEmpty(str)) {
                    return;
                }
                if (Log.D) {
                    Log.d(JDAppUnite.TAG, "getPhoneBasicInfo: callbackName= " + str);
                }
                WebUtils.evaluateJavascript(((BaseWebComponent) JDAppUnite.this).webUiBinder, str, WebUtils.stringfyJSonData("0", JDAppUnite.this.getPhoneBasicInfoEncryptObj(), ""));
            }
        });
    }

    public ConcurrentHashMap<String, HttpRequest> getRequests() {
        return this.requests;
    }

    @JavascriptInterface
    public void isAppLogin(final String str) {
        if (Log.D) {
            Log.d(TAG, "isAppLogin:" + str);
        }
        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.17
            @Override // java.lang.Runnable
            public void run() {
                if (((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView() != null) {
                    String packedData = JDAppUnite.this.getPackedData("1", LoginUserBase.hasLogin() ? "1" : "0", "");
                    ((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView().injectJs("javascript:" + str + "('" + packedData + "');");
                }
            }
        });
    }

    @JavascriptInterface
    public void notifyCookie(String str) {
        IWebUiBinder iWebUiBinder;
        if (TextUtils.isEmpty(str) || !str.contains("domain=jd.com") || (iWebUiBinder = this.webUiBinder) == null || iWebUiBinder.getJdWebView() == null) {
            return;
        }
        String switchStringValue = SwitchQueryFetcher.getSwitchStringValue("wvJDCookieKeyList", "");
        int indexOf = str.trim().indexOf(ContainerUtils.KEY_VALUE_DELIMITER);
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            if (TextUtils.isEmpty(switchStringValue) || switchStringValue.contains(substring)) {
                return;
            }
            ExceptionReporter.reportWebPageError("WebViewSetCookie", str, this.webUiBinder.getJdWebView().getFinalUrl(), WebWhiteScreenHolder.ERR_CODE, substring);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:197:0x03b9 A[Catch: Exception -> 0x04a3, TryCatch #1 {Exception -> 0x04a3, blocks: (B:5:0x0013, B:8:0x0019, B:9:0x002d, B:12:0x003f, B:15:0x0046, B:19:0x0053, B:22:0x005e, B:25:0x006a, B:28:0x0076, B:31:0x0082, B:34:0x008e, B:37:0x009a, B:40:0x00a5, B:43:0x00b1, B:46:0x00bd, B:49:0x00c8, B:52:0x00d4, B:55:0x00dd, B:58:0x00e8, B:61:0x00f3, B:64:0x00fd, B:67:0x0108, B:70:0x0112, B:73:0x011c, B:76:0x0127, B:79:0x0130, B:82:0x013b, B:88:0x0154, B:90:0x0158, B:92:0x015e, B:94:0x0164, B:95:0x0182, B:96:0x0187, B:98:0x018d, B:99:0x019a, B:101:0x01a0, B:102:0x01ad, B:104:0x01b3, B:105:0x01b8, B:107:0x01bc, B:109:0x01c2, B:114:0x01e8, B:116:0x01ec, B:117:0x01f1, B:119:0x01f5, B:121:0x01fb, B:122:0x020b, B:124:0x020f, B:125:0x0214, B:127:0x021a, B:128:0x0221, B:130:0x0225, B:131:0x022a, B:135:0x0238, B:139:0x0243, B:141:0x0259, B:142:0x025d, B:138:0x023f, B:134:0x0232, B:143:0x0267, B:145:0x026b, B:146:0x0270, B:148:0x0276, B:149:0x027b, B:151:0x027f, B:152:0x0284, B:154:0x028a, B:155:0x029a, B:157:0x029e, B:158:0x02b2, B:160:0x02b8, B:161:0x02c8, B:163:0x02cc, B:164:0x02e0, B:166:0x02e6, B:167:0x02fd, B:169:0x0301, B:170:0x0315, B:172:0x031b, B:173:0x032b, B:175:0x032f, B:176:0x0334, B:177:0x0344, B:179:0x0348, B:180:0x034d, B:181:0x035d, B:184:0x0365, B:185:0x0391, B:189:0x03a6, B:195:0x03b3, B:197:0x03b9, B:198:0x03cb, B:188:0x03a4, B:199:0x03d4, B:201:0x03da, B:203:0x03e0, B:207:0x0405, B:208:0x0415, B:210:0x041b, B:212:0x0421, B:216:0x043c, B:217:0x0448, B:219:0x044e, B:220:0x0454, B:222:0x0468, B:228:0x0474, B:229:0x047c, B:232:0x0483, B:234:0x0489, B:238:0x049b, B:111:0x01d1), top: B:245:0x0013, inners: #0 }] */
    @JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void notifyMessageToNative(String str) {
        String str2;
        final boolean z;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "JDAppUnite-->> notifyMessageToNative = jsonString:  " + str);
            }
            final JsInputEntity jsInputEntity = (JsInputEntity) JDJSON.parseObject(str, JsInputEntity.class);
            String str3 = jsInputEntity.businessType;
            final String str4 = jsInputEntity.callBackName;
            if (this.webUiBinder != null || "bridgeBiologicalProbe".equals(str3)) {
                char c2 = '\uffff';
                switch (str3.hashCode()) {
                    case -1779344797:
                        if (str3.equals("exceptionReport")) {
                            c2 = 20;
                            break;
                        }
                        break;
                    case -1633083021:
                        if (str3.equals("decryptParams")) {
                            c2 = 19;
                            break;
                        }
                        break;
                    case -1532101786:
                        if (str3.equals("bridgeBiologicalProbe")) {
                            c2 = '\r';
                            break;
                        }
                        break;
                    case -1241591313:
                        if (str3.equals("goBack")) {
                            c2 = 15;
                            break;
                        }
                        break;
                    case -980654265:
                        if (str3.equals("requestDirectPermission")) {
                            c2 = 4;
                            break;
                        }
                        break;
                    case -957507804:
                        if (str3.equals("jumpToMiniProgram")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case -764741557:
                        if (str3.equals("encryptParams")) {
                            c2 = 18;
                            break;
                        }
                        break;
                    case -568049931:
                        if (str3.equals("changeCamera")) {
                            c2 = 7;
                            break;
                        }
                        break;
                    case -417400442:
                        if (str3.equals("screenShot")) {
                            c2 = 11;
                            break;
                        }
                        break;
                    case -128518914:
                        if (str3.equals("openUrlInBrowser")) {
                            c2 = 16;
                            break;
                        }
                        break;
                    case 3594837:
                        if (str3.equals("unpl")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case 294336216:
                        if (str3.equals("initVoiceSdk")) {
                            c2 = '\b';
                            break;
                        }
                        break;
                    case 307337346:
                        if (str3.equals("showCamera")) {
                            c2 = 5;
                            break;
                        }
                        break;
                    case 325855262:
                        if (str3.equals("stopVoiceRecognize")) {
                            c2 = '\n';
                            break;
                        }
                        break;
                    case 617818273:
                        if (str3.equals("getPhoneBasicInfoEncrypt")) {
                            c2 = 17;
                            break;
                        }
                        break;
                    case 746581438:
                        if (str3.equals("requestPermission")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case 1232537131:
                        if (str3.equals("multiMedia")) {
                            c2 = 14;
                            break;
                        }
                        break;
                    case 1259322494:
                        if (str3.equals("startVoiceRecognize")) {
                            c2 = '\t';
                            break;
                        }
                        break;
                    case 1361029590:
                        if (str3.equals("saveVideoToAlbum")) {
                            c2 = '\f';
                            break;
                        }
                        break;
                    case 1510448585:
                        if (str3.equals("getContacts")) {
                            c2 = 21;
                            break;
                        }
                        break;
                    case 1651324651:
                        if (str3.equals("updateAddress")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case 2000067135:
                        if (str3.equals("cancelCamera")) {
                            c2 = 6;
                            break;
                        }
                        break;
                }
                str2 = "1";
                Object obj = "";
                switch (c2) {
                    case 0:
                        String unpl = JDMtaUtils.getUnpl();
                        if (unpl == null) {
                            unpl = "";
                        }
                        if (TextUtils.isEmpty(str4)) {
                            return;
                        }
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("unpl", unpl);
                        IWebUiBinder iWebUiBinder = this.webUiBinder;
                        if (!TextUtils.isEmpty(unpl)) {
                            str2 = "0";
                        }
                        WebUtils.sendJSONStr2M(iWebUiBinder, str4, WebUtils.stringfyJSonData(str2, jSONObject, ""));
                        return;
                    case 1:
                        boolean updateAddressGlobal = AddressUtil.updateAddressGlobal((AddressGlobal) JDJSON.parseObject(jsInputEntity.params, AddressGlobal.class));
                        if (TextUtils.isEmpty(str4)) {
                            return;
                        }
                        WebUtils.sendJSONStr2M(this.webUiBinder, str4, WebUtils.stringfyJSonData(updateAddressGlobal ? "0" : "1", "", updateAddressGlobal ? "success" : "error"));
                        return;
                    case 2:
                        if (TextUtils.isEmpty(str4)) {
                            return;
                        }
                        jumpToMiniProgram(jsInputEntity.params, str4);
                        return;
                    case 3:
                        if (TextUtils.isEmpty(str4) || jsInputEntity.getParamValue(AttributionReporter.SYSTEM_PERMISSION) == null) {
                            return;
                        }
                        requestPermissions((String) jsInputEntity.getParamValue(AttributionReporter.SYSTEM_PERMISSION), str4, (String) jsInputEntity.getParamValue("permission_desc"), !"0".equals((String) jsInputEntity.getParamValue("initiative")));
                        WebUnifiedMtaUtil.permissionReport(this.webUiBinder, "JDAppUnite-requestPermissions", str);
                        return;
                    case 4:
                        if (TextUtils.isEmpty(str4) || jsInputEntity.getParamValue("permission_name") == null) {
                            return;
                        }
                        requestDirectPermissions((String) jsInputEntity.getParamValue("permission_name"), (String) jsInputEntity.getParamValue("permission_title"), (String) jsInputEntity.getParamValue("permission_desc"), !"0".equals((String) jsInputEntity.getParamValue("initiative")), str4);
                        WebUnifiedMtaUtil.permissionReport(this.webUiBinder, "JDAppUnite-requestDirectPermission", str);
                        return;
                    case 5:
                        if (OKLog.D) {
                            OKLog.d(TAG, "JDAppUnite-->> showCamera   location:" + jsInputEntity.getParamValue(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID) + "   isFront:" + jsInputEntity.getParamValue("isFront") + "   callBackName:" + str4);
                        }
                        Object paramValue = jsInputEntity.getParamValue("isFront");
                        Object paramValue2 = jsInputEntity.getParamValue(ThemeTitleConstant.TITLE_LOCATION_DRAWABLE_ID);
                        final String str5 = (String) jsInputEntity.getParamValue("permission_desc");
                        final String str6 = paramValue2 == null ? null : (String) paramValue2;
                        if (!"1".equals(paramValue) && paramValue != null) {
                            z = false;
                            if (!TextUtils.isEmpty(str4)) {
                                this.webUiBinder.getBaseActivity().runOnUiThread(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        WebMediaJSUtils.requestCameraPermissionAndShow(((BaseWebComponent) JDAppUnite.this).webUiBinder, str4, str6, z, str5);
                                    }
                                });
                            }
                            WebUnifiedMtaUtil.permissionReport(this.webUiBinder, "JDAppUnite-showCamera", str);
                            return;
                        }
                        z = true;
                        if (!TextUtils.isEmpty(str4)) {
                        }
                        WebUnifiedMtaUtil.permissionReport(this.webUiBinder, "JDAppUnite-showCamera", str);
                        return;
                    case 6:
                        if (OKLog.D) {
                            OKLog.d(TAG, "JDAppUnite-->> cancelCamera");
                        }
                        this.webUiBinder.getBaseActivity().runOnUiThread(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.2
                            @Override // java.lang.Runnable
                            public void run() {
                                WebMediaJSUtils.closeCamera(((BaseWebComponent) JDAppUnite.this).webUiBinder);
                            }
                        });
                        return;
                    case 7:
                        if (OKLog.D) {
                            OKLog.d(TAG, "JDAppUnite-->> changeCamera");
                        }
                        this.webUiBinder.getBaseActivity().runOnUiThread(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.3
                            @Override // java.lang.Runnable
                            public void run() {
                                WebMediaJSUtils.switchCamera(((BaseWebComponent) JDAppUnite.this).webUiBinder);
                            }
                        });
                        return;
                    case '\b':
                        if (OKLog.D) {
                            OKLog.d(TAG, "JDAppUnite-->> initVoiceSdk   callBackName:" + str4);
                        }
                        if (TextUtils.isEmpty(str4)) {
                            return;
                        }
                        this.webUiBinder.getBaseActivity().runOnUiThread(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.4
                            @Override // java.lang.Runnable
                            public void run() {
                                WebMediaJSUtils.initVoiceSdk(((BaseWebComponent) JDAppUnite.this).webUiBinder, str4);
                            }
                        });
                        return;
                    case '\t':
                        if (OKLog.D) {
                            OKLog.d(TAG, "JDAppUnite-->> startVoiceRecognize   callBackName:" + str4);
                        }
                        if (TextUtils.isEmpty(str4)) {
                            return;
                        }
                        this.webUiBinder.getBaseActivity().runOnUiThread(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.5
                            @Override // java.lang.Runnable
                            public void run() {
                                int i2;
                                Object paramValue3 = jsInputEntity.getParamValue("timeout");
                                if (paramValue3 != null) {
                                    if (paramValue3 instanceof String) {
                                        i2 = Integer.parseInt((String) paramValue3);
                                    } else if (paramValue3 instanceof Integer) {
                                        i2 = ((Integer) paramValue3).intValue();
                                    }
                                    WebMediaJSUtils.requestRecordPermissionAndRecord(((BaseWebComponent) JDAppUnite.this).webUiBinder, str4, i2, (String) jsInputEntity.getParamValue("permission_desc"));
                                }
                                i2 = 0;
                                WebMediaJSUtils.requestRecordPermissionAndRecord(((BaseWebComponent) JDAppUnite.this).webUiBinder, str4, i2, (String) jsInputEntity.getParamValue("permission_desc"));
                            }
                        });
                        WebUnifiedMtaUtil.permissionReport(this.webUiBinder, "JDAppUnite-startVoiceRecognize", str);
                        return;
                    case '\n':
                        if (OKLog.D) {
                            OKLog.d(TAG, "JDAppUnite-->> stopVoiceRecognize   callBackName:" + str4);
                        }
                        if (TextUtils.isEmpty(str4)) {
                            return;
                        }
                        this.webUiBinder.getBaseActivity().runOnUiThread(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.6
                            @Override // java.lang.Runnable
                            public void run() {
                                WebMediaJSUtils.closeRecoder(((BaseWebComponent) JDAppUnite.this).webUiBinder, str4);
                            }
                        });
                        return;
                    case 11:
                        if (OKLog.D) {
                            OKLog.d(TAG, "JDAppUnite-->> screenShot");
                        }
                        if (TextUtils.isEmpty(str4)) {
                            return;
                        }
                        this.webUiBinder.getBaseActivity().runOnUiThread(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.7
                            @Override // java.lang.Runnable
                            public void run() {
                                float f2 = 0.5f;
                                try {
                                    Object paramValue3 = jsInputEntity.getParamValue("quality");
                                    if (paramValue3 != null) {
                                        if (paramValue3 instanceof String) {
                                            f2 = Float.valueOf((String) paramValue3).floatValue();
                                        } else if ((paramValue3 instanceof Float) || (paramValue3 instanceof Double)) {
                                            f2 = ((Float) paramValue3).floatValue();
                                        }
                                    }
                                } catch (Exception unused) {
                                }
                                WebMediaJSUtils.screenShot(((BaseWebComponent) JDAppUnite.this).webUiBinder, str4, f2);
                            }
                        });
                        return;
                    case '\f':
                        if (OKLog.D) {
                            OKLog.d(TAG, "JDAppUnite-->> saveVideoToAlbum");
                        }
                        if (TextUtils.isEmpty(str4)) {
                            return;
                        }
                        saveVideo2Album(jsInputEntity);
                        return;
                    case '\r':
                        if (OKLog.D) {
                            OKLog.d(TAG, "JDAppUnite-->> bridgeBiologicalProbe");
                        }
                        String str7 = jsInputEntity.getParamValue("pin") == null ? "" : (String) jsInputEntity.getParamValue("pin");
                        if (jsInputEntity.getParamValue("jsonData") != null) {
                            obj = jsInputEntity.getParamValue("jsonData");
                        }
                        String obj2 = obj.toString();
                        BiometricManager biometricManager = BiometricManager.getInstance();
                        Application application = JdSdk.getInstance().getApplication();
                        if (TextUtils.isEmpty(str7)) {
                            str7 = LoginUserBase.getUserPin();
                        }
                        biometricManager.biometricForJS(application, obj2, str7, new JSCallback() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.8
                            @Override // com.jdjr.risk.biometric.core.JSCallback
                            public void onFinish(int i2, JSONObject jSONObject2) {
                                if (TextUtils.isEmpty(str4)) {
                                    return;
                                }
                                if (JDAppUnite.this.webView == null || !JDAppUnite.this.isPreRender) {
                                    if (((BaseWebComponent) JDAppUnite.this).webUiBinder != null) {
                                        WebUtils.sendJSONStr2M(((BaseWebComponent) JDAppUnite.this).webUiBinder, str4, WebUtils.stringfyJSonData("0", jSONObject2, ""));
                                        return;
                                    }
                                    return;
                                }
                                WebUtils.sendJSONStr2MXRender(JDAppUnite.this.webView, str4, WebUtils.stringfyJSonData("0", jSONObject2, ""));
                            }
                        });
                        return;
                    case 14:
                        if (OKLog.D) {
                            OKLog.d(TAG, "JDAppUnite-->> multiMedia");
                        }
                        if (TextUtils.isEmpty(str4)) {
                            return;
                        }
                        MultiMedia.getMediaFile(this.webUiBinder, jsInputEntity);
                        return;
                    case 15:
                        IWebUiBinder iWebUiBinder2 = this.webUiBinder;
                        if (iWebUiBinder2 == null || iWebUiBinder2.getJdWebView() == null) {
                            return;
                        }
                        this.webUiBinder.getBaseActivity().runOnUiThread(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.9
                            @Override // java.lang.Runnable
                            public void run() {
                                JDWebView jdWebView = ((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView();
                                if (jdWebView != null) {
                                    jdWebView.backOrClose();
                                }
                            }
                        });
                        return;
                    case 16:
                        IWebUiBinder iWebUiBinder3 = this.webUiBinder;
                        if (iWebUiBinder3 == null || iWebUiBinder3.getBaseActivity() == null) {
                            return;
                        }
                        String str8 = (String) jsInputEntity.getParamValue("url");
                        if (TextUtils.isEmpty(str8)) {
                            return;
                        }
                        try {
                            this.webUiBinder.getBaseActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str8)));
                            return;
                        } catch (Exception e2) {
                            if (OKLog.E) {
                                OKLog.e(TAG, e2);
                                return;
                            }
                            return;
                        }
                    case 17:
                        if (TextUtils.isEmpty(str4)) {
                            return;
                        }
                        getPhoneBasicInfoEncrypt(str4);
                        return;
                    case 18:
                        if (TextUtils.isEmpty(str4)) {
                            return;
                        }
                        encryptParams(str4, (String) jsInputEntity.getParamValue("plains"));
                        return;
                    case 19:
                        if (TextUtils.isEmpty(str4)) {
                            return;
                        }
                        decryptParams(str4, (String) jsInputEntity.getParamValue("cipher"));
                        return;
                    case 20:
                        reportException(str4, jsInputEntity);
                        return;
                    case 21:
                        IWebUiBinder iWebUiBinder4 = this.webUiBinder;
                        if (iWebUiBinder4 == null || iWebUiBinder4.getBaseActivity() == null || TextUtils.isEmpty(str4)) {
                            return;
                        }
                        WebUtils.CALLBACK_NAME = str4;
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.PICK");
                        intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                        this.webUiBinder.getBaseActivity().startActivityForResult(intent, 1009);
                        return;
                    default:
                        return;
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            WebUtils.reportCommonErr(this.webUiBinder, "notifyMessageToNative", "class JDAppUnite | methon notifyMessageToNative \u53c2\u6570\u9519\u8bef", str);
        }
    }

    public void onDestroy() {
        JDVoiceInputUtils jDVoiceInputUtils = this.jdVoiceInputUtils;
        if (jDVoiceInputUtils != null) {
            jDVoiceInputUtils.close();
        }
        this.jdVoiceInputUtils = null;
        unRegisterWeixinReceiver();
        unRegisterWXResultReceiver();
    }

    @JavascriptInterface
    public void openWeChatPay(String str, String str2) {
        if (this.webUiBinder == null || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            if (OKLog.D) {
                OKLog.d(TAG, "JDAppUnite-->> openWeChatPay = " + str + "   callback:" + str2);
            }
            this.wxPayJscallbackname = str2;
        } catch (Exception e2) {
            e2.printStackTrace();
            ExceptionReporter.reportWebViewCommonError("webViewjsBridgeError", "JDAppUnite-->openWeChatPay", "error happend while handling openWeChatPay()--> jsonStr: " + str + "     callbackName: " + str2, e2.toString());
        }
        if (!WeiXinPayUtil.isWeiXinInstalled()) {
            feedbackWxPayCallback("-2", "", JdSdk.getInstance().getApplication().getString(R.string.check_install_weixin));
        } else if (!WeiXinPayUtil.isWeixinPaySupported()) {
            feedbackWxPayCallback("-1", "", JdSdk.getInstance().getApplication().getString(R.string.check_support_weixin));
        } else {
            if (this.localBroadcastManager == null) {
                this.localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getInstance().getBaseContext());
            }
            if (this.mWxPayResultBroadCastReceiver == null) {
                this.mWxPayResultBroadCastReceiver = new WxPayResultBroadCastReceiver(this.webUiBinder);
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.jd.wxPayResult");
                this.localBroadcastManager.registerReceiver(this.mWxPayResultBroadCastReceiver, intentFilter);
            }
            this.mWxPayResultBroadCastReceiver.setFromH5JsFlag(true);
            WeiXinPayUtil.doWeiXinPay(new WeiXinEntity(JDJSON.parseObject(str)));
            WebUnifiedMtaUtil.functionReport(this.webUiBinder, "JDAppUnite-openWeChatPay");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JavascriptInterface
    public void removeReminder(String str) {
        String str2;
        String str3;
        String str4 = "0";
        if (Log.D) {
            Log.d("HHH_JDAppUnite", "removeReminder, jsonStr: " + str);
        }
        int i2 = 0;
        int i3 = 0;
        String str5 = "";
        if (TextUtils.isEmpty(str)) {
            str3 = "";
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("businessType", "");
                String optString2 = jSONObject.optString(JshopConst.JSKEY_COUPON_BEGIN_TIME, "0");
                str3 = jSONObject.optString("callBackName", "");
                try {
                    str2 = jSONObject.optString("callBackId", "");
                    try {
                        String optString3 = jSONObject.optString("uniqueId", "");
                        if (!TextUtils.isEmpty(optString2)) {
                            str4 = optString2;
                        }
                        i3 = JDReminderNewUtils.cancelReminder(optString, optString3, Long.parseLong(str4));
                    } catch (Exception e2) {
                        e = e2;
                        str5 = str3;
                        e.printStackTrace();
                        str3 = str5;
                        str5 = str2;
                        i2 = i3;
                        sendDataToM(str3, i2 + ",'" + str5 + "'");
                    }
                } catch (Exception e3) {
                    e = e3;
                    str2 = "";
                }
            } catch (Exception e4) {
                e = e4;
                str2 = "";
            }
            str5 = str2;
            i2 = i3;
        }
        sendDataToM(str3, i2 + ",'" + str5 + "'");
    }

    @JavascriptInterface
    public void requestIsvToken(String str, final String str2) {
        if (Log.D) {
            Log.d(TAG, "requestIsvToken:" + str + "  jscallback:" + str2);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setFunctionId("isvObfuscator");
            httpSetting.setHost(Configuration.getNgwHost());
            httpSetting.setEffect(0);
            httpSetting.setNotifyUser(false);
            httpSetting.setConnectTimeout(5000);
            httpSetting.setUseFastJsonParser(true);
            httpSetting.putJsonParam("url", jSONObject.optString("url"));
            httpSetting.putJsonParam("id", jSONObject.optString("id", ""));
            httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.19
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    if (httpResponse == null || httpResponse.getFastJsonObject() == null) {
                        return;
                    }
                    if (Log.D) {
                        Log.d(JDAppUnite.TAG, "requestIsvToken httprequest response:" + httpResponse.getFastJsonObject().toString());
                    }
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    final String optString = fastJsonObject.optString("errcode");
                    final String optString2 = fastJsonObject.optString("token");
                    if (!"0".equals(optString) || TextUtils.isEmpty(optString2)) {
                        ((BaseWebComponent) JDAppUnite.this).webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.19.3
                            @Override // java.lang.Runnable
                            public void run() {
                                if (((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView() != null) {
                                    String packedData = JDAppUnite.this.getPackedData("0", "", optString);
                                    ((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView().injectJs("javascript:" + str2 + "('" + packedData + "');");
                                }
                            }
                        });
                    } else {
                        ((BaseWebComponent) JDAppUnite.this).webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.19.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView() != null) {
                                    String packedData = JDAppUnite.this.getPackedData("1", optString2, "");
                                    ((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView().injectJs("javascript:" + str2 + "('" + packedData + "');");
                                }
                            }
                        });
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(final HttpError httpError) {
                    Log.d(JDAppUnite.TAG, "requestIsvToken httprequest error:" + httpError);
                    ((BaseWebComponent) JDAppUnite.this).webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.19.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView() != null) {
                                String packedData = JDAppUnite.this.getPackedData("0", "", "requestIsvToken error:" + httpError);
                                ((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView().injectJs("javascript:" + str2 + "('" + packedData + "');");
                            }
                        }
                    });
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
                public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                }
            });
            HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @JavascriptInterface
    public void requestLogin(final String str) {
        if (Log.D) {
            Log.d(TAG, "requestLogin:" + str);
        }
        LoginUserHelper.getInstance().executeLoginRunnable(this.webUiBinder.getBaseActivity(), new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.JDAppUnite.18
            @Override // java.lang.Runnable
            public void run() {
                if (((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView() != null) {
                    String packedData = JDAppUnite.this.getPackedData("1", LoginUserBase.hasLogin() ? "1" : "0", "");
                    ((BaseWebComponent) JDAppUnite.this).webUiBinder.getJdWebView().injectJs("javascript:" + str + "('" + packedData + "');");
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0057 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0058  */
    @JavascriptInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void saveImageToPhoteAlbum(String str) {
        String str2;
        final String str3;
        String str4;
        JSONObject jSONObject;
        Log.d(TAG, "saveImageToPhoteAlbum:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        final String str5 = null;
        try {
            jSONObject = new JSONObject(str);
            str4 = jSONObject.optString("imgUrl");
            try {
                str3 = jSONObject.optString("callBackName");
            } catch (JSONException e2) {
                e = e2;
                str2 = null;
                str3 = null;
            }
        } catch (JSONException e3) {
            e = e3;
            str2 = null;
            str3 = null;
        }
        try {
            str5 = jSONObject.optString("callBackId");
            if (jSONObject.has("permission_desc")) {
                jSONObject.optString("permission_desc");
            }
        } catch (JSONException e4) {
            e = e4;
            str2 = str5;
            str5 = str4;
            e.printStackTrace();
            str4 = str5;
            str5 = str2;
            if (TextUtils.isEmpty(str4)) {
            }
        }
        if (TextUtils.isEmpty(str4)) {
            if (SaveImageUtils.isSwitchOn()) {
                SaveImageUtils.saveImageToPhotoAlbum(str4, new SaveImageUtils.SaveImageCallback() { // from class: com.jingdong.common.web.javainterface.impl.c
                    @Override // com.jingdong.common.web.util.SaveImageUtils.SaveImageCallback
                    public final void onCallback(boolean z, SaveImageUtils.CallBackType callBackType) {
                        JDAppUnite.this.f(str3, str5, z, callBackType);
                    }
                });
            } else {
                downloadAndSaveImage(str4, str3, str5);
            }
            WebUnifiedMtaUtil.permissionReport(this.webUiBinder, "JDAppUnite-saveImageToPhoteAlbum", str);
        }
    }

    @Override // com.jingdong.common.web.BaseWebComponent
    public void setWebUiBinder(IWebUiBinder iWebUiBinder) {
        super.setWebUiBinder(iWebUiBinder);
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
    }

    @JavascriptInterface
    public void signWeixinPay(String str) {
        if (Log.D) {
            Log.d(TAG, "signWeixinPay:" + str);
        }
        OpenWebview.Req req = new OpenWebview.Req();
        req.url = str;
        WeiXinPayUtil.getWeiXinApi().sendReq(req);
    }

    public void unRegisterWXResultReceiver() {
        if (this.mWxPayResultBroadCastReceiver == null || this.localBroadcastManager == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "unRegisterWXResultReceiver");
        }
        this.localBroadcastManager.unregisterReceiver(this.mWxPayResultBroadCastReceiver);
        this.mWxPayResultBroadCastReceiver = null;
        this.localBroadcastManager = null;
    }

    public void unRegisterWeixinReceiver() {
        if (this.weixinReceiver == null || this.localBroadcastManager == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(TAG, "unRegisterWeixinReceiver");
        }
        this.localBroadcastManager.unregisterReceiver(this.weixinReceiver);
        this.weixinReceiver = null;
        this.localBroadcastManager = null;
    }

    public JDAppUnite(JDWebView jDWebView) {
        this.webView = jDWebView;
        this.isPreRender = true;
    }
}
