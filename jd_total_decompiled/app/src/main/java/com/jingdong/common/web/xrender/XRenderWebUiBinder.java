package com.jingdong.common.web.xrender;

import com.jingdong.common.utils.SwitchQueryFetcher;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.javainterface.impl.AndroidNavi;
import com.jingdong.common.web.javainterface.impl.AndroidSound;
import com.jingdong.common.web.javainterface.impl.AndroidUploadImg;
import com.jingdong.common.web.javainterface.impl.BindGiftcard;
import com.jingdong.common.web.javainterface.impl.ConsoleInterface;
import com.jingdong.common.web.javainterface.impl.EventSeries;
import com.jingdong.common.web.javainterface.impl.IdCard;
import com.jingdong.common.web.javainterface.impl.JDAppUnite;
import com.jingdong.common.web.javainterface.impl.JDFunction;
import com.jingdong.common.web.javainterface.impl.JDPaySDK;
import com.jingdong.common.web.javainterface.impl.JSControlHelper;
import com.jingdong.common.web.javainterface.impl.JSLoginUserBase;
import com.jingdong.common.web.javainterface.impl.JavaScriptCallIntelligentAssistance;
import com.jingdong.common.web.javainterface.impl.MobileLogin;
import com.jingdong.common.web.javainterface.impl.MobileNavi;
import com.jingdong.common.web.javainterface.impl.MtaHelper;
import com.jingdong.common.web.javainterface.impl.Performance;
import com.jingdong.common.web.javainterface.impl.ScreenConfig;
import com.jingdong.common.web.javainterface.impl.SettleAccounts;
import com.jingdong.common.web.javainterface.impl.ShareHelper;
import com.jingdong.common.web.javainterface.impl.WebJavaScript;
import com.jingdong.common.web.javainterface.impl.XRenderJS;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes12.dex */
public class XRenderWebUiBinder {
    private AndroidNavi androidNavi;
    private AndroidSound androidSound;
    private AndroidUploadImg androidUploadImg;
    private BindGiftcard bindGiftcard;
    private EventSeries eventSeries;
    private IdCard idCard;
    private JavaScriptCallIntelligentAssistance javaScriptCallIntelligentAssistance;
    private JDAppUnite jdAppUnite;
    private JDFunction jdFunction;
    private JDPaySDK jdPaySDK;
    private JSControlHelper jsControlHelper;
    private JSLoginUserBase jsLoginUserBase;
    private MobileLogin mobileLogin;
    private MobileNavi mobileNavi;
    private MtaHelper mtaHelper;
    private ScreenConfig screenConfig;
    private SettleAccounts settleAccounts;
    private ShareHelper shareHelper;
    private WebJavaScript webJavaScript;
    private JDWebView webView;
    private XRenderJS xRenderJS;
    private final String TAG = "XRenderWebUiBinder";
    private LinkedList<ICallback> mCacheJsList = new LinkedList<>();

    public synchronized void addCacheJsBridgeInterface(ICallback iCallback, String str) {
        XRender.Log("add JsBridge cache item :" + str);
        this.mCacheJsList.add(iCallback);
    }

    protected void addJavascriptInterface(IJavaInterface iJavaInterface) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null) {
            jDWebView.addJavascriptInterface(iJavaInterface, iJavaInterface.getName());
        }
    }

    public void addJs(JDWebView jDWebView) {
        this.webView = jDWebView;
        XRender.Log("\u6ce8\u518cjs\u6865");
        EventSeries eventSeries = new EventSeries();
        this.eventSeries = eventSeries;
        addJavascriptInterface(eventSeries);
        if (WebPerfManager.getInstance().isEnable()) {
            Performance performance2 = new Performance();
            performance2.setWebView(jDWebView);
            addJavascriptInterface(performance2);
            jDWebView.setTimingReportEnable(true, performance2);
        }
        ShareHelper shareHelper = new ShareHelper(jDWebView);
        this.shareHelper = shareHelper;
        addJavascriptInterface(shareHelper);
        AndroidNavi androidNavi = new AndroidNavi(jDWebView);
        this.androidNavi = androidNavi;
        addJavascriptInterface(androidNavi);
        WebJavaScript webJavaScript = new WebJavaScript(jDWebView);
        this.webJavaScript = webJavaScript;
        addJavascriptInterface(webJavaScript);
        MobileNavi mobileNavi = new MobileNavi(jDWebView);
        this.mobileNavi = mobileNavi;
        addJavascriptInterface(mobileNavi);
        JDAppUnite jDAppUnite = new JDAppUnite(jDWebView);
        this.jdAppUnite = jDAppUnite;
        addJavascriptInterface(jDAppUnite);
        BindGiftcard bindGiftcard = new BindGiftcard();
        this.bindGiftcard = bindGiftcard;
        addJavascriptInterface(bindGiftcard);
        SettleAccounts settleAccounts = new SettleAccounts();
        this.settleAccounts = settleAccounts;
        addJavascriptInterface(settleAccounts);
        JDPaySDK jDPaySDK = new JDPaySDK();
        this.jdPaySDK = jDPaySDK;
        addJavascriptInterface(jDPaySDK);
        AndroidSound androidSound = new AndroidSound();
        this.androidSound = androidSound;
        addJavascriptInterface(androidSound);
        AndroidUploadImg androidUploadImg = new AndroidUploadImg();
        this.androidUploadImg = androidUploadImg;
        addJavascriptInterface(androidUploadImg);
        MtaHelper mtaHelper = new MtaHelper();
        this.mtaHelper = mtaHelper;
        addJavascriptInterface(mtaHelper);
        IdCard idCard = new IdCard();
        this.idCard = idCard;
        addJavascriptInterface(idCard);
        JSLoginUserBase jSLoginUserBase = new JSLoginUserBase();
        this.jsLoginUserBase = jSLoginUserBase;
        addJavascriptInterface(jSLoginUserBase);
        JSControlHelper jSControlHelper = new JSControlHelper();
        this.jsControlHelper = jSControlHelper;
        addJavascriptInterface(jSControlHelper);
        try {
            JavaScriptCallIntelligentAssistance javaScriptCallIntelligentAssistance = new JavaScriptCallIntelligentAssistance();
            this.javaScriptCallIntelligentAssistance = javaScriptCallIntelligentAssistance;
            addJavascriptInterface(javaScriptCallIntelligentAssistance);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        JDFunction jDFunction = new JDFunction();
        this.jdFunction = jDFunction;
        addJavascriptInterface(jDFunction);
        MobileLogin mobileLogin = new MobileLogin();
        this.mobileLogin = mobileLogin;
        addJavascriptInterface(mobileLogin);
        if (SwitchQueryFetcher.getSwitchBooleanValue(ScreenConfig.SWITCH_NAME, false)) {
            ScreenConfig screenConfig = new ScreenConfig();
            this.screenConfig = screenConfig;
            addJavascriptInterface(screenConfig);
        }
        addJavascriptInterface(new ConsoleInterface());
        XRenderJS xRenderJS = new XRenderJS();
        this.xRenderJS = xRenderJS;
        addJavascriptInterface(xRenderJS);
        XRender.Log("\u6ce8\u518cjs\u6865 \u7ed3\u675f");
    }

    public synchronized void executeCacheJsBridgeInterface() {
        if (isEmptyCacheJsList()) {
            XRender.Log("js\u6865\u7f13\u5b58\u4e3a\u7a7a\u4e0d\u6267\u884c");
            return;
        }
        Iterator<ICallback> it = this.mCacheJsList.iterator();
        while (it.hasNext()) {
            ICallback next = it.next();
            XRender.Log("execute JsBridge cache item: " + next.getClass());
            next.execute();
        }
    }

    public XRenderJS getxRenderJS() {
        return this.xRenderJS;
    }

    public boolean isEmptyCacheJsList() {
        return this.mCacheJsList.isEmpty();
    }

    public void setWebUiBinder(IWebUiBinder iWebUiBinder) {
        this.webJavaScript.setWebUiBinder(iWebUiBinder);
        this.androidNavi.setWebUiBinder(iWebUiBinder);
        this.mobileNavi.setWebUiBinder(iWebUiBinder);
        this.jdAppUnite.setWebUiBinder(iWebUiBinder);
        this.shareHelper.setWebUiBinder(iWebUiBinder);
        this.bindGiftcard.setWebUiBinder(iWebUiBinder);
        this.jdPaySDK.setWebUiBinder(iWebUiBinder);
        this.settleAccounts.setWebUiBinder(iWebUiBinder);
        this.androidSound.setWebUiBinder(iWebUiBinder);
        this.androidUploadImg.setWebUiBinder(iWebUiBinder);
        this.mtaHelper.setWebUiBinder(iWebUiBinder);
        this.idCard.setWebUiBinder(iWebUiBinder);
        this.jsLoginUserBase.setWebUiBinder(iWebUiBinder);
        this.jsControlHelper.setWebUiBinder(iWebUiBinder);
        this.javaScriptCallIntelligentAssistance.setWebUiBinder(iWebUiBinder);
        this.jdFunction.setWebUiBinder(iWebUiBinder);
        this.mobileLogin.setWebUiBinder(iWebUiBinder);
        ScreenConfig screenConfig = this.screenConfig;
        if (screenConfig != null) {
            screenConfig.setWebUiBinder(iWebUiBinder);
        }
        this.xRenderJS.setWebUiBinder(iWebUiBinder);
    }
}
