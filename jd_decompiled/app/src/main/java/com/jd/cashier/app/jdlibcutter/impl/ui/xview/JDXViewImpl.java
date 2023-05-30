package com.jd.cashier.app.jdlibcutter.impl.ui.xview;

import android.view.View;
import com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack;
import com.jingdong.common.XView.XView;
import com.jingdong.common.XView.XViewCallBack;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onXViewRequest(com.jingdong.common.XView.XViewRequest r6) {
        /*
            r5 = this;
            if (r6 == 0) goto L5e
            java.lang.String r0 = r6.requestParams
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L5e
            java.lang.String r0 = r6.requestParams     // Catch: java.lang.Exception -> L5a
            com.jd.framework.json.JDJSONObject r0 = com.jd.framework.json.JDJSON.parseObject(r0)     // Catch: java.lang.Exception -> L5a
            java.lang.String r1 = "action"
            java.lang.String r0 = r0.getString(r1)     // Catch: java.lang.Exception -> L5a
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L5a
            if (r1 != 0) goto L5e
            r1 = -1
            int r2 = r0.hashCode()     // Catch: java.lang.Exception -> L5a
            r3 = 94750088(0x5a5c588, float:1.5589087E-35)
            r4 = 1
            if (r2 == r3) goto L37
            r3 = 1372359451(0x51cc8b1b, float:1.09813391E11)
            if (r2 == r3) goto L2d
            goto L40
        L2d:
            java.lang.String r2 = "needCallBack"
            boolean r0 = r0.equals(r2)     // Catch: java.lang.Exception -> L5a
            if (r0 == 0) goto L40
            r1 = 1
            goto L40
        L37:
            java.lang.String r2 = "click"
            boolean r0 = r0.equals(r2)     // Catch: java.lang.Exception -> L5a
            if (r0 == 0) goto L40
            r1 = 0
        L40:
            if (r1 == 0) goto L48
            if (r1 == r4) goto L45
            goto L5e
        L45:
            r5.mNotifyJsToAnimate = r4     // Catch: java.lang.Exception -> L5a
            goto L5e
        L48:
            java.lang.ref.WeakReference<com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack<java.lang.String>> r0 = r5.mXViewClickMta     // Catch: java.lang.Exception -> L5a
            if (r0 == 0) goto L5e
            java.lang.Object r0 = r0.get()     // Catch: java.lang.Exception -> L5a
            com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack r0 = (com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack) r0     // Catch: java.lang.Exception -> L5a
            if (r0 == 0) goto L5e
            java.lang.String r6 = r6.requestParams     // Catch: java.lang.Exception -> L5a
            r0.onCallBack(r6)     // Catch: java.lang.Exception -> L5a
            goto L5e
        L5a:
            r6 = move-exception
            r6.printStackTrace()
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.cashier.app.jdlibcutter.impl.ui.xview.JDXViewImpl.onXViewRequest(com.jingdong.common.XView.XViewRequest):void");
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
