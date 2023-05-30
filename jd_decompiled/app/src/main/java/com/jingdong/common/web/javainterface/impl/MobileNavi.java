package com.jingdong.common.web.javainterface.impl;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import com.facebook.react.uimanager.ViewProps;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.deeplinkhelper.DeepLinkScanHelper;
import com.jingdong.common.entity.FaxianEntry;
import com.jingdong.common.unification.statusbar.UnStatusBarTintUtil;
import com.jingdong.common.unification.title.theme.ThemeTitleConstant;
import com.jingdong.common.unification.title.theme.ThemeTitleHelper;
import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.entity.JsBridgeEntity;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUtils;
import com.jingdong.common.web.xrender.ICallback;
import com.jingdong.common.web.xrender.XRender;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public final class MobileNavi extends BaseWebComponent implements IJavaInterface {
    private final String TAG;
    private boolean isPreRender;
    private JsBridgeEntity jsBridgeEntity;
    private JDWebView webView;

    public MobileNavi(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
        this.TAG = MobileNavi.class.getSimpleName();
        this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getH5ContentTopPadding(int i2, int i3) {
        return DPIUtil.px2dip(this.webUiBinder.getJdWebView().isNaviImmersive() ? i2 + i3 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getNaviHeightPx() {
        return DPIUtil.dip2px(49.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getStatusBarHeightPx() {
        return UnStatusBarTintUtil.getStatusBarHeight((Activity) this.webUiBinder.getBaseActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleJsonArray(NavigatorHolder navigatorHolder, JSONObject jSONObject, String str) {
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray(str);
            if (navigatorHolder == null || optJSONArray == null || optJSONArray.length() <= 0) {
                return;
            }
            int length = optJSONArray.length();
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    navigatorHolder.controlNavigationItems(optJSONObject);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleJsonObject(NavigatorHolder navigatorHolder, JSONObject jSONObject, String str) {
        try {
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (navigatorHolder == null || optJSONObject == null) {
                return;
            }
            navigatorHolder.controlNavigationItems(optJSONObject);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNaviWithoutSupportTran(JDJSONObject jDJSONObject) {
        String optString = jDJSONObject.optString(FaxianEntry.ICON_STYLE_PIC, "");
        boolean z = !TextUtils.isEmpty(optString);
        String optString2 = jDJSONObject.optString(ViewProps.BACKGROUND_COLOR, "");
        if (TextUtils.isEmpty(optString2)) {
            optString2 = "#FFFFFF";
        } else {
            z = true;
        }
        int i2 = jDJSONObject.optString("naviMenuType", "").startsWith(JshopConst.JSHOP_PROMOTIO_W) ? 2 : 1;
        String optString3 = jDJSONObject.optString("blackImg", "");
        String optString4 = jDJSONObject.optString("whiteImg", "");
        if (this.webUiBinder.getJdWebView().getNavigatorHolder() != null) {
            if (z) {
                this.webUiBinder.getJdWebView().setNaviBgCustomized(true);
                this.webUiBinder.getJdWebView().setNaviIconCustomized(true);
            }
            this.webUiBinder.getJdWebView().getNavigatorHolder().setNaviBackground(i2, optString2, optString);
            if (1 == jDJSONObject.optInt("normalNaviSubType", 0)) {
                this.webUiBinder.getJdWebView().clearScrollState(true);
                this.webUiBinder.getJdWebView().setNormalNaviUIChange(jDJSONObject.toJSONString());
                return;
            }
            this.webUiBinder.getJdWebView().clearScrollState(false);
            if (i2 == 1 && !optString3.isEmpty()) {
                this.webUiBinder.getJdWebView().getNavigatorHolder().setTitleTextOrImg("", optString3);
            } else if (i2 != 2 || optString4.isEmpty()) {
            } else {
                this.webUiBinder.getJdWebView().getNavigatorHolder().setTitleTextOrImg("", optString4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTopLogo(NavigatorHolder navigatorHolder, JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            navigatorHolder.setTopLogo(null, null);
            return;
        }
        final String optString = jDJSONObject.optString("clickCallBackName");
        navigatorHolder.setTopLogo(jDJSONObject.toString(), TextUtils.isEmpty(optString) ? null : new View.OnClickListener() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (((BaseWebComponent) MobileNavi.this).webUiBinder == null) {
                    return;
                }
                String str = optString;
                ((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView().injectJs(String.format("javascript:window.%s && window.%s();", str, str));
            }
        });
    }

    @JavascriptInterface
    /* renamed from: configBtn  reason: merged with bridge method [inline-methods] */
    public void b(final String str) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.m
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    MobileNavi.this.b(str);
                }
            }, getName() + ".configBtn");
        } else if (this.webUiBinder.getJdWebView().getNavigatorHolder() != null) {
            this.webUiBinder.getJdWebView().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.5
                @Override // java.lang.Runnable
                public void run() {
                    ((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView().getNavigatorHolder().configBtn(str);
                }
            });
        }
    }

    @JavascriptInterface
    /* renamed from: configBtnSince610  reason: merged with bridge method [inline-methods] */
    public void d(final String str) {
        Log.d(this.TAG, "configParams==:" + str);
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.f
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    MobileNavi.this.d(str);
                }
            }, "configBtnSince610");
        } else if (TextUtils.isEmpty(str)) {
        } else {
            this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.7
                @Override // java.lang.Runnable
                public void run() {
                    JSONObject jSONObject;
                    NavigatorHolder navigatorHolder = ((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView().getNavigatorHolder();
                    try {
                        jSONObject = new JSONObject(str);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        jSONObject = null;
                    }
                    if (jSONObject == null) {
                        return;
                    }
                    MobileNavi.this.handleJsonObject(navigatorHolder, jSONObject, NavigatorHolder.NaviEntity.TYPE_HOME);
                    MobileNavi.this.handleJsonObject(navigatorHolder, jSONObject, "search");
                    MobileNavi.this.handleJsonObject(navigatorHolder, jSONObject, "message");
                    MobileNavi.this.handleJsonObject(navigatorHolder, jSONObject, NavigatorHolder.NaviEntity.TYPE_CLEAR);
                    MobileNavi.this.handleJsonObject(navigatorHolder, jSONObject, "cart");
                    MobileNavi.this.handleJsonObject(navigatorHolder, jSONObject, "share");
                    MobileNavi.this.handleJsonObject(navigatorHolder, jSONObject, "feedback");
                    MobileNavi.this.handleJsonObject(navigatorHolder, jSONObject, "follow");
                    MobileNavi.this.handleJsonArray(navigatorHolder, jSONObject, NavigatorHolder.NaviEntity.TYPE_CUSTOM);
                    MobileNavi.this.handleJsonObject(navigatorHolder, jSONObject, NavigatorHolder.NaviEntity.TYPE_HIDEMORE);
                }
            });
        }
    }

    @JavascriptInterface
    /* renamed from: configNavigationBar  reason: merged with bridge method [inline-methods] */
    public void f(final String str) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.o
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    MobileNavi.this.f(str);
                }
            }, getName() + ".configNavigationBar");
        } else if (TextUtils.isEmpty(str)) {
        } else {
            if (OKLog.D) {
                OKLog.d(this.TAG, "MobileNavi--> configNavigationBar  json: " + str);
            }
            IWebUiBinder iWebUiBinder = this.webUiBinder;
            if (iWebUiBinder == null || iWebUiBinder.getBaseActivity() == null || this.webUiBinder.getJdWebView() == null) {
                return;
            }
            this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.3
                /* JADX WARN: Removed duplicated region for block: B:33:0x00d7  */
                /* JADX WARN: Removed duplicated region for block: B:38:0x00ef  */
                /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void run() {
                    /*
                        Method dump skipped, instructions count: 340
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.javainterface.impl.MobileNavi.AnonymousClass3.run():void");
                }
            });
        }
    }

    @JavascriptInterface
    /* renamed from: configThemeNavigator  reason: merged with bridge method [inline-methods] */
    public void h(final String str) {
        IWebUiBinder iWebUiBinder;
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.i
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    MobileNavi.this.h(str);
                }
            }, getName() + ".configThemeNavigator");
        } else if (TextUtils.isEmpty(str)) {
        } else {
            final String optString = JDJSON.parseObject(str).optString("callBackName", "");
            if (TextUtils.isEmpty(optString) || (iWebUiBinder = this.webUiBinder) == null || iWebUiBinder.getBaseActivity() == null) {
                return;
            }
            this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.14
                @Override // java.lang.Runnable
                public void run() {
                    if (!ThemeTitleHelper.isThemeTitleEffected(ThemeTitleConstant.WEBVIEW_MODULE_ID) || ((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView() == null) {
                        WebUtils.sendJSONStr2M(((BaseWebComponent) MobileNavi.this).webUiBinder, optString, WebUtils.stringfyJSonData("-1"));
                        return;
                    }
                    ((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView().registerTitleThemeChangeListener();
                    WebUtils.sendJSONStr2M(((BaseWebComponent) MobileNavi.this).webUiBinder, optString, WebUtils.stringfyJSonData("0"));
                }
            });
        }
    }

    @JavascriptInterface
    /* renamed from: enableTransparent  reason: merged with bridge method [inline-methods] */
    public void j(final String str) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.h
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    MobileNavi.this.j(str);
                }
            }, getName() + ".enableTransparent");
            return;
        }
        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.11
            @Override // java.lang.Runnable
            public void run() {
                if (((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView() != null) {
                    ((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView().setImmersive(true, str);
                }
            }
        });
    }

    @JavascriptInterface
    /* renamed from: enableWebControlBack  reason: merged with bridge method [inline-methods] */
    public void l(final String str) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.p
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    MobileNavi.this.l(str);
                }
            }, getName() + ".enableWebControlBack");
            return;
        }
        JDJSONObject parseObject = !TextUtils.isEmpty(str) ? JDJSON.parseObject(str) : null;
        if (this.jsBridgeEntity != null) {
            if (WebUtils.isLegalUrlToControlBack(this.webUiBinder)) {
                int i2 = 1;
                int optInt = parseObject != null ? parseObject.optInt("controlType", 1) : 1;
                if (SwitchQueryFetcher.getSwitchBooleanValue("wvBackCtrlSlide", false)) {
                    i2 = 2;
                } else if (optInt == 1 || optInt == 2) {
                    i2 = optInt;
                }
                this.jsBridgeEntity.canControlBackByWeb = i2;
                if (OKLog.D) {
                    OKLog.d(this.TAG, "MobileNavi--> enableWebControlBack: success, type=" + i2);
                }
            } else {
                this.jsBridgeEntity.canControlBackByWeb = 0;
            }
        }
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder == null || iWebUiBinder.getBaseActivity() == null) {
            return;
        }
        final String optString = parseObject != null ? parseObject.optString("callBackName", null) : null;
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.1
            @Override // java.lang.Runnable
            public void run() {
                String str2;
                if (TextUtils.isEmpty(optString)) {
                    return;
                }
                String str3 = "1";
                if (MobileNavi.this.jsBridgeEntity == null) {
                    str2 = "Internal Error";
                } else if (MobileNavi.this.jsBridgeEntity.canControlBackByWeb == 0) {
                    str2 = "No Permission";
                } else {
                    str3 = "0";
                    str2 = "Success";
                }
                WebUtils.sendJSONStr2M(((BaseWebComponent) MobileNavi.this).webUiBinder, optString, WebUtils.stringfyJSonData(str3, "", str2));
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007d  */
    @android.webkit.JavascriptInterface
    /* renamed from: getActualNaviStatusHeight  reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void n(final java.lang.String r5) {
        /*
            r4 = this;
            com.jingdong.common.web.ui.JDWebView r0 = r4.webView
            if (r0 == 0) goto L32
            boolean r1 = r4.isPreRender
            if (r1 == 0) goto L32
            boolean r0 = r0.isAttached()
            if (r0 != 0) goto L32
            com.jingdong.common.web.ui.JDWebView r0 = r4.webView
            com.jingdong.common.web.xrender.XRenderWebUiBinder r0 = r0.getXUiBinder()
            com.jingdong.common.web.javainterface.impl.j r1 = new com.jingdong.common.web.javainterface.impl.j
            r1.<init>()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r2 = r4.getName()
            r5.append(r2)
            java.lang.String r2 = ".getActualNaviStatusHeight"
            r5.append(r2)
            java.lang.String r5 = r5.toString()
            r0.addCacheJsBridgeInterface(r1, r5)
            return
        L32:
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto L39
            return
        L39:
            int r0 = r4.getStatusBarHeightPx()
            float r0 = (float) r0
            int r0 = com.jingdong.jdsdk.utils.DPIUtil.px2dip(r0)
            int r1 = r4.getNaviHeightPx()
            float r1 = (float) r1
            int r1 = com.jingdong.jdsdk.utils.DPIUtil.px2dip(r1)
            r2 = 0
            com.jd.framework.json.JDJSONObject r3 = new com.jd.framework.json.JDJSONObject     // Catch: java.lang.Exception -> L67
            r3.<init>()     // Catch: java.lang.Exception -> L67
            java.lang.String r2 = "statusBarHeight"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Exception -> L64
            r3.put(r2, r0)     // Catch: java.lang.Exception -> L64
            java.lang.String r0 = "navigationHeight"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Exception -> L64
            r3.put(r0, r1)     // Catch: java.lang.Exception -> L64
            goto L76
        L64:
            r0 = move-exception
            r2 = r3
            goto L68
        L67:
            r0 = move-exception
        L68:
            boolean r1 = com.jingdong.corelib.utils.Log.E
            if (r1 == 0) goto L75
            java.lang.String r1 = r4.TAG
            java.lang.String r3 = r0.getMessage()
            com.jingdong.corelib.utils.Log.e(r1, r3, r0)
        L75:
            r3 = r2
        L76:
            if (r3 == 0) goto L7d
            java.lang.String r0 = r3.toJSONString()
            goto L7f
        L7d:
            java.lang.String r0 = ""
        L7f:
            com.jingdong.common.web.uibinder.IWebUiBinder r1 = r4.webUiBinder
            com.jingdong.common.BaseActivity r1 = r1.getBaseActivity()
            com.jingdong.common.web.javainterface.impl.MobileNavi$13 r2 = new com.jingdong.common.web.javainterface.impl.MobileNavi$13
            r2.<init>()
            r1.post(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.javainterface.impl.MobileNavi.m(java.lang.String):void");
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.MOBILE_NAVI;
    }

    @JavascriptInterface
    /* renamed from: getNaviHeight  reason: merged with bridge method [inline-methods] */
    public void p(final String str) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.g
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    MobileNavi.this.p(str);
                }
            }, getName() + ".getNaviHeight");
            return;
        }
        final int h5ContentTopPadding = getH5ContentTopPadding(getStatusBarHeightPx(), getNaviHeightPx());
        XRender.Log("getNaviHeight height=" + h5ContentTopPadding);
        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.12
            @Override // java.lang.Runnable
            public void run() {
                ((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView().injectJs("javascript:" + str + "('" + h5ContentTopPadding + "');");
            }
        });
    }

    @JavascriptInterface
    /* renamed from: notifyPullToRefreshComplete  reason: merged with bridge method [inline-methods] */
    public void r() {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.n
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    MobileNavi.this.r();
                }
            }, getName() + ".notifyPullToRefreshComplete");
            return;
        }
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder == null || iWebUiBinder.getBaseActivity() == null || this.webUiBinder.getJdWebView() == null) {
            return;
        }
        if (OKLog.D) {
            OKLog.d(this.TAG, "MobileNavi--> notifyPullToRefreshComplete");
        }
        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.15
            @Override // java.lang.Runnable
            public void run() {
                if (((BaseWebComponent) MobileNavi.this).webUiBinder == null || ((BaseWebComponent) MobileNavi.this).webUiBinder.getBaseActivity() == null || ((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView() == null) {
                    return;
                }
                ((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView().onRefreshComplete();
            }
        });
    }

    @JavascriptInterface
    public void refreshViewCanPull(final boolean z) {
        IWebUiBinder iWebUiBinder = this.webUiBinder;
        if (iWebUiBinder == null || iWebUiBinder.getJdWebView() == null) {
            return;
        }
        this.webUiBinder.getJdWebView().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.2
            @Override // java.lang.Runnable
            public void run() {
                ((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView().setCanPullRefresh(z);
            }
        });
    }

    @JavascriptInterface
    /* renamed from: setNaviBackground  reason: merged with bridge method [inline-methods] */
    public void t(final String str) {
        Log.d(this.TAG, "setBackground:" + str);
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
            this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.k
                @Override // com.jingdong.common.web.xrender.ICallback
                public final void execute() {
                    MobileNavi.this.t(str);
                }
            }, getName() + ".setNaviBackground");
        } else if (TextUtils.isEmpty(str)) {
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str);
                Integer.parseInt(jSONObject.optString("naviIcon", ""));
                jSONObject.optString(ViewProps.BACKGROUND_COLOR, "");
                jSONObject.optString(FaxianEntry.ICON_STYLE_PIC, "");
                if (this.webUiBinder.getJdWebView().getNavigatorHolder() != null) {
                    this.webUiBinder.getBaseActivity().post(new Runnable
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0083: INVOKE 
                          (wrap: com.jingdong.common.BaseActivity : 0x007a: INVOKE 
                          (wrap: com.jingdong.common.web.uibinder.IWebUiBinder : 0x0078: IGET (r4v0 'this' com.jingdong.common.web.javainterface.impl.MobileNavi A[IMMUTABLE_TYPE, THIS]) A[Catch: Exception -> 0x0087, WRAPPED] (LINE:11) com.jingdong.common.web.BaseWebComponent.webUiBinder com.jingdong.common.web.uibinder.IWebUiBinder)
                         type: INTERFACE call: com.jingdong.common.web.uibinder.IWebUiBinder.getBaseActivity():com.jingdong.common.BaseActivity A[Catch: Exception -> 0x0087, MD:():com.jingdong.common.BaseActivity (m), WRAPPED] (LINE:11))
                          (wrap: java.lang.Runnable : 0x0080: CONSTRUCTOR 
                          (r4v0 'this' com.jingdong.common.web.javainterface.impl.MobileNavi A[IMMUTABLE_TYPE, THIS])
                          (r2 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r0 I:java.lang.String A[DONT_GENERATE, DONT_INLINE, REMOVE])
                          (r5 I:int A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[Catch: Exception -> 0x0087, MD:(com.jingdong.common.web.javainterface.impl.MobileNavi, java.lang.String, java.lang.String, int):void (m), WRAPPED] call: com.jingdong.common.web.javainterface.impl.MobileNavi.6.<init>(com.jingdong.common.web.javainterface.impl.MobileNavi, java.lang.String, java.lang.String, int):void type: CONSTRUCTOR)
                         type: VIRTUAL call: com.jingdong.common.BaseActivity.post(java.lang.Runnable):void A[Catch: Exception -> 0x0087, MD:(java.lang.Runnable):void (m), TRY_LEAVE] (LINE:11) in method: com.jingdong.common.web.javainterface.impl.MobileNavi.setNaviBackground(java.lang.String):void, file: classes12.dex
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
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:302)
                        	at jadx.core.dex.regions.TryCatchRegion.generate(TryCatchRegion.java:85)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:137)
                        	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:156)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:133)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
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
                        java.lang.String r0 = ""
                        java.lang.String r1 = r4.TAG
                        java.lang.StringBuilder r2 = new java.lang.StringBuilder
                        r2.<init>()
                        java.lang.String r3 = "setBackground:"
                        r2.append(r3)
                        r2.append(r5)
                        java.lang.String r2 = r2.toString()
                        com.jingdong.corelib.utils.Log.d(r1, r2)
                        com.jingdong.common.web.ui.JDWebView r1 = r4.webView
                        if (r1 == 0) goto L4a
                        boolean r2 = r4.isPreRender
                        if (r2 == 0) goto L4a
                        boolean r1 = r1.isAttached()
                        if (r1 != 0) goto L4a
                        com.jingdong.common.web.ui.JDWebView r0 = r4.webView
                        com.jingdong.common.web.xrender.XRenderWebUiBinder r0 = r0.getXUiBinder()
                        com.jingdong.common.web.javainterface.impl.k r1 = new com.jingdong.common.web.javainterface.impl.k
                        r1.<init>()
                        java.lang.StringBuilder r5 = new java.lang.StringBuilder
                        r5.<init>()
                        java.lang.String r2 = r4.getName()
                        r5.append(r2)
                        java.lang.String r2 = ".setNaviBackground"
                        r5.append(r2)
                        java.lang.String r5 = r5.toString()
                        r0.addCacheJsBridgeInterface(r1, r5)
                        return
                    L4a:
                        boolean r1 = android.text.TextUtils.isEmpty(r5)
                        if (r1 == 0) goto L51
                        return
                    L51:
                        org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> L87
                        r1.<init>(r5)     // Catch: java.lang.Exception -> L87
                        java.lang.String r5 = "naviIcon"
                        java.lang.String r5 = r1.optString(r5, r0)     // Catch: java.lang.Exception -> L87
                        int r5 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.Exception -> L87
                        java.lang.String r2 = "backgroundColor"
                        java.lang.String r2 = r1.optString(r2, r0)     // Catch: java.lang.Exception -> L87
                        java.lang.String r3 = "pic"
                        java.lang.String r0 = r1.optString(r3, r0)     // Catch: java.lang.Exception -> L87
                        com.jingdong.common.web.uibinder.IWebUiBinder r1 = r4.webUiBinder     // Catch: java.lang.Exception -> L87
                        com.jingdong.common.web.ui.JDWebView r1 = r1.getJdWebView()     // Catch: java.lang.Exception -> L87
                        com.jingdong.common.widget.NavigatorHolder r1 = r1.getNavigatorHolder()     // Catch: java.lang.Exception -> L87
                        if (r1 == 0) goto L8b
                        com.jingdong.common.web.uibinder.IWebUiBinder r1 = r4.webUiBinder     // Catch: java.lang.Exception -> L87
                        com.jingdong.common.BaseActivity r1 = r1.getBaseActivity()     // Catch: java.lang.Exception -> L87
                        com.jingdong.common.web.javainterface.impl.MobileNavi$6 r3 = new com.jingdong.common.web.javainterface.impl.MobileNavi$6     // Catch: java.lang.Exception -> L87
                        r3.<init>()     // Catch: java.lang.Exception -> L87
                        r1.post(r3)     // Catch: java.lang.Exception -> L87
                        goto L8b
                    L87:
                        r5 = move-exception
                        r5.printStackTrace()
                    L8b:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.web.javainterface.impl.MobileNavi.s(java.lang.String):void");
                }

                @JavascriptInterface
                /* renamed from: setTitle  reason: merged with bridge method [inline-methods] */
                public void v(final String str) {
                    JDWebView jDWebView = this.webView;
                    if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
                        this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.e
                            @Override // com.jingdong.common.web.xrender.ICallback
                            public final void execute() {
                                MobileNavi.this.v(str);
                            }
                        }, getName() + ".setTitle");
                    } else if (TextUtils.isEmpty(str)) {
                    } else {
                        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.8
                            @Override // java.lang.Runnable
                            public void run() {
                                if (((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView() != null) {
                                    ((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView().receivedTitle(str);
                                }
                            }
                        });
                    }
                }

                @Override // com.jingdong.common.web.BaseWebComponent
                public void setWebUiBinder(IWebUiBinder iWebUiBinder) {
                    super.setWebUiBinder(iWebUiBinder);
                    this.jsBridgeEntity = iWebUiBinder.getWebEntity().jsBridgeEntity;
                }

                @JavascriptInterface
                public void showCloseBtn() {
                    JDWebView jDWebView = this.webView;
                    if (jDWebView != null && this.isPreRender) {
                        jDWebView.post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.9
                            @Override // java.lang.Runnable
                            public void run() {
                                MobileNavi.this.webView.setCloseBtnVisible(true);
                            }
                        });
                    } else {
                        this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.MobileNavi.10
                            @Override // java.lang.Runnable
                            public void run() {
                                if (((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView() != null) {
                                    ((BaseWebComponent) MobileNavi.this).webUiBinder.getJdWebView().setCloseBtnVisible(true);
                                }
                            }
                        });
                    }
                }

                @JavascriptInterface
                /* renamed from: showNativeBarcodeScan  reason: merged with bridge method [inline-methods] */
                public void x(final String str) {
                    JDWebView jDWebView = this.webView;
                    if (jDWebView != null && this.isPreRender && !jDWebView.isAttached()) {
                        this.webView.getXUiBinder().addCacheJsBridgeInterface(new ICallback() { // from class: com.jingdong.common.web.javainterface.impl.l
                            @Override // com.jingdong.common.web.xrender.ICallback
                            public final void execute() {
                                MobileNavi.this.x(str);
                            }
                        }, getName() + ".showNativeBarcodeScan");
                    } else if (this.webUiBinder.getJdWebView().getNavigatorHolder() != null) {
                        Log.d(this.TAG, "showNativeBarcodeScan callback:" + str);
                        this.jsBridgeEntity.jsCallback = str;
                        DeepLinkScanHelper.startCaptureActivityForResultFromOrderScan(this.webUiBinder.getBaseActivity(), new Bundle(), 6667);
                    }
                }

                public MobileNavi(JDWebView jDWebView) {
                    this.TAG = MobileNavi.class.getSimpleName();
                    this.webView = jDWebView;
                    this.isPreRender = true;
                    this.jsBridgeEntity = new JsBridgeEntity();
                }
            }
