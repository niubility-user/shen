package com.jd.lib.cashier.sdk.complete.jsbridge;

import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack;
import com.jd.cashier.app.jdlibcutter.protocol.ui.xview.IXView;
import com.jd.cashier.app.jdlibcutter.protocol.ui.xview.XViewKey;
import com.jd.lib.cashier.sdk.c.c.a;
import com.jd.lib.cashier.sdk.complete.entity.CashierXViewCustomMta;
import com.jd.lib.cashier.sdk.complete.entity.CashierXViewEntity;
import com.jd.lib.cashier.sdk.core.ui.entity.CashierConfig;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.i0;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.y;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public final class PayFinishJavaScript {
    private static final String ACTION_CLICK = "click";
    private static final String BUSINESS_CART_CONFIRM_TYPE = "JDCashier_Cart_confirm";
    private static final String BUSINESS_RED_PACKET_ALERT_TYPE = "JDCashier_RedPacket_Alert";
    private static final String TAG = "PayFinishJavaScript";
    public static String WEBJAVASCRIPT = "JdAndroid";
    private String configJson;
    private WeakReference<f<a>> mCallBack;
    private FragmentActivity mContext;
    private View mWebView;
    private IXView mXView;

    public PayFinishJavaScript(FragmentActivity fragmentActivity, View view, f<a> fVar) {
        this.mContext = fragmentActivity;
        this.mWebView = view;
        this.mCallBack = new WeakReference<>(fVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doXViewClickMta(CashierXViewEntity cashierXViewEntity, CashierXViewCustomMta cashierXViewCustomMta) {
        boolean z = true;
        boolean z2 = (cashierXViewEntity == null || TextUtils.isEmpty(cashierXViewEntity.customClickEventId)) ? false : true;
        if (cashierXViewCustomMta == null || TextUtils.isEmpty(cashierXViewCustomMta.action) || (!TextUtils.equals(cashierXViewCustomMta.des, "JDCashier_RedPacket_Alert") && !TextUtils.equals(cashierXViewCustomMta.des, BUSINESS_CART_CONFIRM_TYPE))) {
            z = false;
        }
        if (z && z2 && TextUtils.equals("click", cashierXViewCustomMta.action)) {
            com.jd.lib.cashier.sdk.c.e.a.c().e(this.mContext, cashierXViewEntity.customClickEventId, cashierXViewEntity.mtaJsonStr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doXViewCloseButtonClickMta(CashierXViewEntity cashierXViewEntity) {
        if (cashierXViewEntity == null || TextUtils.isEmpty(cashierXViewEntity.closeClickEventId)) {
            return;
        }
        com.jd.lib.cashier.sdk.c.e.a.c().e(this.mContext, cashierXViewEntity.closeClickEventId, cashierXViewEntity.mtaJsonStr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doXViewExpoMta(CashierXViewEntity cashierXViewEntity) {
        if (cashierXViewEntity == null || TextUtils.isEmpty(cashierXViewEntity.closeClickEventId)) {
            return;
        }
        com.jd.lib.cashier.sdk.c.e.a.c().f(this.mContext, cashierXViewEntity.closeClickEventId, cashierXViewEntity.mtaJsonStr);
    }

    private void executeJsMethod(String str, String str2) {
        if (this.mContext == null || TextUtils.isEmpty(str)) {
            return;
        }
        r.b(TAG, "methodName = " + str);
        r.b(TAG, "jsonParam = " + str2);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("javascript:");
        stringBuffer.append(str);
        stringBuffer.append("('");
        stringBuffer.append(str2);
        stringBuffer.append("')");
        stringBuffer.append(";");
        final String stringBuffer2 = stringBuffer.toString();
        r.b(TAG, "executed uri = " + stringBuffer2);
        this.mContext.runOnUiThread(new Runnable() { // from class: com.jd.lib.cashier.sdk.complete.jsbridge.PayFinishJavaScript.6
            @Override // java.lang.Runnable
            public void run() {
                if (PayFinishJavaScript.this.mWebView != null) {
                    i0.g(PayFinishJavaScript.this.mWebView, stringBuffer2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showXView(final CashierXViewEntity cashierXViewEntity) {
        try {
            IXView xView = DependInitializer.getXView();
            this.mXView = xView;
            if (cashierXViewEntity == null || xView == null) {
                return;
            }
            HashMap hashMap = new HashMap();
            hashMap.put("url", cashierXViewEntity.url);
            hashMap.put(XViewKey.needCloseBtn, cashierXViewEntity.needCloseButton);
            r.b(TAG, "xView url -->" + cashierXViewEntity.url);
            r.b(TAG, "xView needCloseBtn-->" + cashierXViewEntity.needCloseButton);
            this.mXView.showXView(this.mContext, this.mWebView, hashMap, new CommonCallBack<String>() { // from class: com.jd.lib.cashier.sdk.complete.jsbridge.PayFinishJavaScript.3
                @Override // com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack
                public void onCallBack(String str) {
                    PayFinishJavaScript.this.doXViewExpoMta(cashierXViewEntity);
                }
            }, new CommonCallBack<String>() { // from class: com.jd.lib.cashier.sdk.complete.jsbridge.PayFinishJavaScript.4
                @Override // com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack
                public void onCallBack(String str) {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    PayFinishJavaScript.this.doXViewClickMta(cashierXViewEntity, (CashierXViewCustomMta) o.a(str, CashierXViewCustomMta.class));
                }
            }, new CommonCallBack<String>() { // from class: com.jd.lib.cashier.sdk.complete.jsbridge.PayFinishJavaScript.5
                @Override // com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack
                public void onCallBack(String str) {
                    PayFinishJavaScript.this.doXViewCloseButtonClickMta(cashierXViewEntity);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @JavascriptInterface
    public void finishCashierDeskActivity() {
        FragmentActivity fragmentActivity = this.mContext;
        if (fragmentActivity != null) {
            fragmentActivity.runOnUiThread(new Runnable() { // from class: com.jd.lib.cashier.sdk.complete.jsbridge.PayFinishJavaScript.2
                @Override // java.lang.Runnable
                public void run() {
                    if (PayFinishJavaScript.this.mContext != null) {
                        PayFinishJavaScript.this.mContext.finish();
                    }
                }
            });
        }
    }

    public CashierConfig getCashDeskConfig() {
        try {
            r.b(TAG, "get config json from js --->" + this.configJson);
            if (TextUtils.isEmpty(this.configJson)) {
                return null;
            }
            return (CashierConfig) o.a(this.configJson, CashierConfig.class);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    @JavascriptInterface
    public void getElderMode(String str) {
        try {
            if (this.mWebView == null || TextUtils.isEmpty(str)) {
                return;
            }
            executeJsMethod(new JSONObject(str).getString("callBackName"), "{\"data\": \"" + y.j() + "\"}");
        } catch (Exception e2) {
            r.d(TAG, e2.getMessage());
        }
    }

    public void onDestroy() {
        if (this.mContext != null) {
            this.mContext = null;
        }
        if (this.mCallBack != null) {
            this.mCallBack = null;
        }
        IXView iXView = this.mXView;
        if (iXView != null) {
            iXView.onDestroy();
            this.mXView = null;
        }
    }

    @JavascriptInterface
    public void setConfigJson(String str) {
        f<a> fVar;
        r.b(TAG, "on calling setConfigJson from js --->" + str);
        this.configJson = str;
        WeakReference<f<a>> weakReference = this.mCallBack;
        if (weakReference == null || (fVar = weakReference.get()) == null) {
            return;
        }
        fVar.callBack(new a(2000));
    }

    @JavascriptInterface
    public void setPageIndex(String str) {
        r.b(TAG, "on calling setPageIndex from js");
    }

    @JavascriptInterface
    public void setPayCompleted() {
        f<a> fVar;
        WeakReference<f<a>> weakReference = this.mCallBack;
        if (weakReference != null && (fVar = weakReference.get()) != null) {
            fVar.callBack(new a(1000));
        }
        r.b(TAG, "on calling setPayCompleted from js");
    }

    @JavascriptInterface
    public void setXViewJson(String str) {
        r.b(TAG, "on calling setXViewJson from js-->" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            final CashierXViewEntity cashierXViewEntity = (CashierXViewEntity) o.a(str, CashierXViewEntity.class);
            FragmentActivity fragmentActivity = this.mContext;
            if (fragmentActivity != null) {
                fragmentActivity.runOnUiThread(new Runnable() { // from class: com.jd.lib.cashier.sdk.complete.jsbridge.PayFinishJavaScript.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (PayFinishJavaScript.this.mContext != null) {
                            PayFinishJavaScript.this.showXView(cashierXViewEntity);
                        }
                    }
                });
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
