package com.jingdong.common.web.javainterface.impl;

import android.webkit.JavascriptInterface;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartResponse;
import com.jingdong.common.web.BaseWebComponent;
import com.jingdong.common.web.WebUiConstans;
import com.jingdong.common.web.javainterface.IJavaInterface;
import com.jingdong.common.web.managers.WebPerfManager;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.common.web.uibinder.IWebUiBinder;
import com.jingdong.common.web.util.WebUnifiedMtaUtil;
import com.jingdong.common.web.xrender.XRender;

/* loaded from: classes12.dex */
public final class AndroidNavi extends BaseWebComponent implements IJavaInterface {
    private boolean isPreRender;
    private JDWebView webView;

    /* renamed from: com.jingdong.common.web.javainterface.impl.AndroidNavi$3  reason: invalid class name */
    /* loaded from: classes12.dex */
    class AnonymousClass3 implements Runnable {
        AnonymousClass3() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ShoppingBaseController.syncCartWithNoResponse(((BaseWebComponent) AndroidNavi.this).webUiBinder.getBaseActivity(), new ShoppingBaseController.ShoppingListener() { // from class: com.jingdong.common.web.javainterface.impl.AndroidNavi.3.1
                @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
                public void onEnd(CartResponse cartResponse) {
                    if (cartResponse.getInfo() != null) {
                        final int i2 = cartResponse.getInfo().num;
                        if (((BaseWebComponent) AndroidNavi.this).webUiBinder.getBaseActivity() != null) {
                            ((BaseWebComponent) AndroidNavi.this).webUiBinder.getBaseActivity().runOnUiThread(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.AndroidNavi.3.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    ((BaseWebComponent) AndroidNavi.this).webUiBinder.getJdWebView().getNavigatorHolder().refreshCartWithAnim(i2);
                                }
                            });
                        }
                    }
                }

                @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
                public void onError(String str) {
                }

                @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
                public void onReady() {
                }
            });
        }
    }

    public AndroidNavi(IWebUiBinder iWebUiBinder) {
        super(iWebUiBinder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(String str) {
        XRender.Log(AndroidNavi.class + ".setTitle");
        this.webView.getNavigatorHolder().setTitleImg(str);
    }

    @Override // com.jingdong.common.web.javainterface.IJavaInterface
    public String getName() {
        return WebUiConstans.JavaInterfaceNames.ANDROID_NAVI;
    }

    @JavascriptInterface
    public void refreshCart() {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "AndroidNavi_refreshCart");
        if (this.webUiBinder.getJdWebView().getNavigatorHolder() != null) {
            this.webUiBinder.getBaseActivity().post(new AnonymousClass3());
        }
    }

    @JavascriptInterface
    public void setCart(final String str) {
        WebUnifiedMtaUtil.functionReport(this.webUiBinder, "AndroidNavi_setCart");
        if (this.webUiBinder.getJdWebView().getNavigatorHolder() != null) {
            this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.AndroidNavi.2
                @Override // java.lang.Runnable
                public void run() {
                    ((BaseWebComponent) AndroidNavi.this).webUiBinder.getJdWebView().getNavigatorHolder().setCart(str);
                }
            });
        }
    }

    @JavascriptInterface
    public void setTitle(final String str) {
        JDWebView jDWebView = this.webView;
        if (jDWebView != null && this.isPreRender) {
            jDWebView.post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.a
                @Override // java.lang.Runnable
                public final void run() {
                    AndroidNavi.this.b(str);
                }
            });
            return;
        }
        if (jDWebView != null) {
            jDWebView.appendPerformanceData(WebPerfManager.NAME, str);
        }
        if (this.webUiBinder.getJdWebView().getNavigatorHolder() != null) {
            this.webUiBinder.getBaseActivity().post(new Runnable() { // from class: com.jingdong.common.web.javainterface.impl.AndroidNavi.1
                @Override // java.lang.Runnable
                public void run() {
                    ((BaseWebComponent) AndroidNavi.this).webUiBinder.getJdWebView().getNavigatorHolder().setTitleImg(str);
                }
            });
        }
    }

    public AndroidNavi(JDWebView jDWebView) {
        this.webView = jDWebView;
        this.isPreRender = true;
    }
}
