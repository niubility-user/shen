package com.jd.cashier.app.jdlibcutter.impl.ui.xview;

import android.text.TextUtils;
import android.view.View;
import com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack;
import com.jd.framework.json.JDJSON;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewCallBack;
import com.jingdong.common.XView.XViewRequest;
import com.jingdong.common.web.ui.JDWebView;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.ref.WeakReference;

/* loaded from: classes13.dex */
public class JDXViewImpl implements XViewCallBack {
    private static final String ACTION_CLICK = "click";
    private static final String ACTION_NEED_CALLBACK = "needCallBack";
    private boolean mNotifyJsToAnimate;
    private View mWebView;
    private XView mXView;
    private WeakReference<CommonCallBack<String>> mXViewClickMta;
    private WeakReference<CommonCallBack<String>> mXViewCloseButtonClickMta;
    private WeakReference<CommonCallBack<String>> mXViewExpoMta;

    public JDXViewImpl(View view, CommonCallBack<String> commonCallBack, CommonCallBack<String> commonCallBack2, CommonCallBack<String> commonCallBack3) {
        this.mWebView = view;
        this.mXViewExpoMta = new WeakReference<>(commonCallBack);
        this.mXViewClickMta = new WeakReference<>(commonCallBack2);
        this.mXViewCloseButtonClickMta = new WeakReference<>(commonCallBack3);
    }

    private void executeClearCartAnimate() {
        try {
            View view = this.mWebView;
            if (view instanceof JDWebView) {
                view.post(new Runnable() { // from class: com.jd.cashier.app.jdlibcutter.impl.ui.xview.JDXViewImpl.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ((JDWebView) JDXViewImpl.this.mWebView).injectJs("javascript:nativeCallEmptyCartAnimate()");
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onCloseButtonClicked() {
        CommonCallBack<String> commonCallBack;
        WeakReference<CommonCallBack<String>> weakReference = this.mXViewCloseButtonClickMta;
        if (weakReference == null || (commonCallBack = weakReference.get()) == null) {
            return;
        }
        commonCallBack.onCallBack("");
    }

    public void onDestroy() {
        XView xView = this.mXView;
        if (xView != null) {
            xView.destroyXView();
            this.mXView = null;
        }
        if (this.mWebView != null) {
            this.mWebView = null;
        }
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onError(int i2) {
        this.mNotifyJsToAnimate = false;
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onStart() {
        this.mNotifyJsToAnimate = false;
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onXViewDisplayed() {
        CommonCallBack<String> commonCallBack;
        WeakReference<CommonCallBack<String>> weakReference = this.mXViewExpoMta;
        if (weakReference == null || (commonCallBack = weakReference.get()) == null) {
            return;
        }
        commonCallBack.onCallBack("");
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onXViewLoadingUrl(String str) {
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onXViewReady() {
        try {
            XView xView = this.mXView;
            if (xView != null) {
                xView.displayXView();
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
        if (r1 == 1) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0045, code lost:
        r5.mNotifyJsToAnimate = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:?, code lost:
        return;
     */
    @Override // com.jingdong.common.XView.XViewCallBack
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onXViewRequest(XViewRequest xViewRequest) {
        CommonCallBack<String> commonCallBack;
        if (xViewRequest == null || TextUtils.isEmpty(xViewRequest.requestParams)) {
            return;
        }
        try {
            String string = JDJSON.parseObject(xViewRequest.requestParams).getString("action");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            char c2 = '\uffff';
            int hashCode = string.hashCode();
            if (hashCode != 94750088) {
                if (hashCode == 1372359451 && string.equals(ACTION_NEED_CALLBACK)) {
                    c2 = 1;
                }
            } else if (string.equals("click")) {
                c2 = 0;
            }
            WeakReference<CommonCallBack<String>> weakReference = this.mXViewClickMta;
            if (weakReference == null || (commonCallBack = weakReference.get()) == null) {
                return;
            }
            commonCallBack.onCallBack(xViewRequest.requestParams);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onXViewVisibleChanged(boolean z) {
    }

    @Override // com.jingdong.common.XView.XViewCallBack
    public void onXVivewClosed() {
        if (this.mNotifyJsToAnimate) {
            this.mNotifyJsToAnimate = false;
            executeClearCartAnimate();
        }
    }

    public void setXView(XView xView) {
        this.mXView = xView;
    }
}
