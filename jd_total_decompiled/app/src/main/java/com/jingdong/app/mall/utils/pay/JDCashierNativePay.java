package com.jingdong.app.mall.utils.pay;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.CashierGenPayIdDomainCollector;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.CommonUtil;
import com.jingdong.common.utils.pay.AndroidPayConstants;
import com.jingdong.common.utils.pay.IPay;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.common.utils.pay.PayCallbackListener;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class JDCashierNativePay implements IPay {
    private static final String TAG = "JDCashierNativePay";

    /* loaded from: classes4.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f11810g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Activity f11811h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ String f11812i;

        a(String str, Activity activity, String str2) {
            this.f11810g = str;
            this.f11811h = activity;
            this.f11812i = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!JumpUtils.checkPayFinishHost(this.f11810g)) {
                JDCashierNativePay.this.jumpToCashierNative(this.f11811h, this.f11810g, this.f11812i);
            } else {
                JDCashierNativePay.this.showSuccessPage(this.f11811h, null, this.f11810g, this.f11812i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpToCashierNative(Activity activity, String str, String str2) {
        String str3;
        String str4;
        String str5;
        if (activity == null || activity.isFinishing() || TextUtils.isEmpty(str)) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("fromActivity", str2);
        String queryParameter = (TextUtils.isEmpty(str) || !str.contains(AndroidPayConstants.IS_FULL_SCREEN)) ? "" : Uri.parse(str).getQueryParameter(AndroidPayConstants.IS_FULL_SCREEN);
        String queryParameter2 = (TextUtils.isEmpty(str) || !str.contains(AndroidPayConstants.TID)) ? "" : Uri.parse(str).getQueryParameter(AndroidPayConstants.TID);
        String queryParameter3 = (TextUtils.isEmpty(str) || !str.contains("appId")) ? "" : Uri.parse(str).getQueryParameter("appId");
        String queryParameter4 = (TextUtils.isEmpty(str) || !str.contains("payId")) ? "" : Uri.parse(str).getQueryParameter("payId");
        String queryParameter5 = (TextUtils.isEmpty(str) || !str.contains("orderId")) ? "" : Uri.parse(str).getQueryParameter("orderId");
        String queryParameter6 = (TextUtils.isEmpty(str) || !str.contains("orderType")) ? "" : Uri.parse(str).getQueryParameter("orderType");
        String queryParameter7 = (TextUtils.isEmpty(str) || !str.contains("payablePrice")) ? "" : Uri.parse(str).getQueryParameter("payablePrice");
        if (TextUtils.isEmpty(str) || !str.contains("orderTypeCode")) {
            str3 = "payablePrice";
            str4 = "";
        } else {
            str3 = "payablePrice";
            str4 = Uri.parse(str).getQueryParameter("orderTypeCode");
        }
        if (TextUtils.isEmpty(queryParameter4) || TextUtils.isEmpty(queryParameter3)) {
            str5 = "orderTypeCode";
            bundle.putString("payUrl", str);
        } else {
            str5 = "orderTypeCode";
        }
        if (TextUtils.equals("0", queryParameter)) {
            bundle.putString(PairKey.CASHIER_SHOW_STYLE, "1");
        }
        if (!TextUtils.isEmpty(queryParameter2)) {
            bundle.putString(AndroidPayConstants.TID, queryParameter2);
        }
        bundle.putString("appId", queryParameter3);
        bundle.putString("payId", queryParameter4);
        bundle.putString("orderId", queryParameter5);
        bundle.putString("orderType", queryParameter6);
        bundle.putString(str3, queryParameter7);
        bundle.putString(str5, str4);
        bundle.putStringArrayList("lastWebViewUrls", CashierGenPayIdDomainCollector.getInstance().getUrlList());
        b.a(1024, activity, bundle, 404);
        CashierGenPayIdDomainCollector.getInstance().onDestroy();
    }

    @Override // com.jingdong.common.utils.pay.IPay
    public void doPay(Activity activity, JDJSONObject jDJSONObject, PayCallbackListener payCallbackListener) {
        try {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("appId", jDJSONObject.optString("appId"));
                bundle.putString("orderId", jDJSONObject.optString("orderId"));
                bundle.putString("orderType", jDJSONObject.optString("orderType"));
                bundle.putString("payablePrice", jDJSONObject.optString("payablePrice"));
                bundle.putString("paySourceId", jDJSONObject.optString("paySourceId"));
                bundle.putString("orderTypeCode", jDJSONObject.optString("orderTypeCode"));
                bundle.putString("back_url", jDJSONObject.optString("back_url"));
                bundle.putString("unJieSuan", jDJSONObject.optString("unJieSuan"));
                bundle.putString("baiTiaoNum", jDJSONObject.optString("baiTiaoNum"));
                bundle.putString("fromActivity", jDJSONObject.optString("fromActivity"));
                bundle.putString("businessTag", jDJSONObject.optString("businessTag"));
                bundle.putString("submitOrderExtFlag", jDJSONObject.optString("submitOrderExtFlag"));
                bundle.putString("isGoodsDetailBaiTiaoFlag", jDJSONObject.optString("isGoodsDetailBaiTiaoFlag"));
                bundle.putInt("requestCode", jDJSONObject.optInt("requestCode"));
                bundle.putString("orderDate", jDJSONObject.optString("orderDate"));
                Object obj = jDJSONObject.get("isRiskUser");
                if (obj instanceof String) {
                    bundle.putString("isRiskUser", jDJSONObject.optString("isRiskUser"));
                } else if (obj instanceof Boolean) {
                    bundle.putString("isRiskUser", jDJSONObject.optBoolean("isRiskUser", false) ? "1" : "0");
                }
                com.jingdong.app.mall.utils.pay.a.a(activity, bundle);
                if (payCallbackListener == null) {
                    return;
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    e2.printStackTrace();
                }
                if (payCallbackListener == null) {
                    return;
                }
            }
            payCallbackListener.succeed();
        } catch (Throwable th) {
            if (payCallbackListener != null) {
                payCallbackListener.succeed();
            }
            throw th;
        }
    }

    @Override // com.jingdong.common.utils.pay.IPay
    public void doPayFinishForward(String str, CommonBase.BrowserNewUrlListener browserNewUrlListener) {
        CommonUtil.doPayFinishForward(str, browserNewUrlListener);
    }

    @Override // com.jingdong.common.utils.pay.IPay
    public void doPayWithWebURL(Activity activity, String str, String str2) {
        StringBuilder sb;
        if (activity != null) {
            try {
                try {
                    activity.runOnUiThread(new a(str, activity, str2));
                } catch (Exception e2) {
                    if (Log.E) {
                        e2.printStackTrace();
                    }
                    if (!Log.D) {
                        return;
                    }
                    sb = new StringBuilder();
                }
            } catch (Throwable th) {
                if (Log.D) {
                    Log.d(TAG, "url from web " + str);
                }
                throw th;
            }
        }
        if (Log.D) {
            sb = new StringBuilder();
            sb.append("url from web ");
            sb.append(str);
            Log.d(TAG, sb.toString());
        }
    }

    @Override // com.jingdong.common.utils.pay.IPay
    public void showSuccessPage(Activity activity, Bundle bundle, String str, String str2) {
        if (activity == null || activity.isFinishing() || TextUtils.isEmpty(str)) {
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        String queryParameter = str.contains("appId") ? Uri.parse(str).getQueryParameter("appId") : "";
        if (!TextUtils.isEmpty(queryParameter)) {
            bundle.putString("appId", queryParameter);
        }
        bundle.putString("url", str);
        bundle.putString("fromActivity", str2);
        b.a(R2.dimen.HugerTextSize, activity, bundle, 404);
    }

    @Override // com.jingdong.common.utils.pay.IPay
    public void doPayFinishForward(String str, CommonBase.BrowserCashierUrlListener browserCashierUrlListener) {
        CommonUtil.doPayFinishForward(str, browserCashierUrlListener);
    }
}
