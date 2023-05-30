package com.jd.manto.pay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeeplinkJDpaySdkHelper;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.manto.sdk.api.IRequestPayment;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes17.dex */
public class MantoPayProxyActivity extends BaseActivity {

    /* renamed from: g  reason: collision with root package name */
    Bundle f6786g;

    /* renamed from: h  reason: collision with root package name */
    int f6787h;

    /* renamed from: i  reason: collision with root package name */
    int f6788i = 0;

    public static void u(Activity activity, Bundle bundle, int i2) {
        Intent intent = new Intent(activity, MantoPayProxyActivity.class);
        intent.putExtra("extras", bundle);
        intent.putExtra("requestCode", i2);
        activity.startActivityForResult(intent, i2);
    }

    public static void v(Activity activity, Bundle bundle, int i2, int i3) {
        Intent intent = new Intent(activity, MantoPayProxyActivity.class);
        intent.putExtra("extras", bundle);
        intent.putExtra("requestCode", i3);
        intent.putExtra("payType", i2);
        activity.startActivityForResult(intent, i3);
    }

    private final void w() {
        String string = this.f6786g.getString("mode", "Native");
        String string2 = this.f6786g.getString(IRequestPayment.V_PAY_TYPE, "4");
        String string3 = this.f6786g.getString("extraInfo", "");
        String a2 = UserUtil.getWJLoginHelper() != null ? UserUtil.getWJLoginHelper().getA2() : "";
        try {
            Bundle bundle = new Bundle();
            bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_FRONT_VERIFY_PAY");
            bundle.putString(JDPayApiKey.JDPAY_SESSION_KEY, a2);
            bundle.putString(JumpUtils.JDPAY_CODE_SOURCE, "0");
            bundle.putString(JumpUtils.ACCOUNT_MODE, string);
            bundle.putString("JDPAY_PAYMENT_CODE", string2);
            bundle.putString("JDPAY_VERIFY_TYPE", "0");
            bundle.putString("JDPAY_EXTRA_INFO", string3);
            DeeplinkJDpaySdkHelper.startJDPayActivityForResult(this, bundle, this.f6787h);
        } catch (Exception e2) {
            OKLog.e("mantoPay", e2);
        }
    }

    @Override // com.jingdong.common.BaseActivity, android.app.Activity, com.jingdong.common.frame.IMyActivity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        setResult(i3, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(0, 0);
        this.f6786g = getIntent().getBundleExtra("extras");
        this.f6787h = getIntent().getIntExtra("requestCode", 39);
        int intExtra = getIntent().getIntExtra("payType", 0);
        this.f6788i = intExtra;
        if (intExtra == 3) {
            w();
            return;
        }
        try {
            String string = this.f6786g.getString(IRequestPayment.OUT_merchant, "");
            String string2 = this.f6786g.getString("package", "");
            String string3 = this.f6786g.getString("paySign", "");
            String string4 = this.f6786g.getString("payParam", "");
            String string5 = this.f6786g.getString("appId", "");
            String a2 = UserUtil.getWJLoginHelper() != null ? UserUtil.getWJLoginHelper().getA2() : "";
            OKLog.d("mantoPay", String.format("pay invoke, merchant:%s, orderId:%s, paySign: %s, innerAppId: %s", string, string2, string3, string5));
            if (!TextUtils.isEmpty(string5) && !TextUtils.isEmpty(string4)) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_COUNTER");
                bundle2.putString("APP_ID", string5);
                bundle2.putString("PAY_PARAM", string4);
                bundle2.putString(JDPayApiKey.JDPAY_SESSION_KEY, a2);
                DeeplinkJDpaySdkHelper.startJDPayActivityForResult(this, bundle2, this.f6787h);
                return;
            }
            Bundle bundle3 = new Bundle();
            bundle3.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_ACCESS");
            bundle3.putString("ORDERID", string2);
            bundle3.putString("MERCHANT", string);
            bundle3.putString("SIGNDATA", string3);
            bundle3.putString(JumpUtils.JDPAY_CODE_SOURCE, "0");
            bundle3.putString(JDPayApiKey.JDPAY_SESSION_KEY, a2);
            bundle3.putString(JumpUtils.ACCOUNT_MODE, "Native");
            DeeplinkJDpaySdkHelper.startJDPayActivityForResult(this, bundle3, this.f6787h);
        } catch (Exception e2) {
            OKLog.e("mantoPay", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }
}
