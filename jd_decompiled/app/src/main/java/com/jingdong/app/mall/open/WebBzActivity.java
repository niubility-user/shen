package com.jingdong.app.mall.open;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.deeplinkhelper.DeeplinkJDpaySdkHelper;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.utils.UserUtil;
import com.jingdong.common.utils.pay.JumpUtils;
import com.jingdong.manto.sdk.api.IRequestPayment;
import com.jingdong.sdk.platform.business.personal.R2;

/* loaded from: classes4.dex */
public class WebBzActivity extends MyActivity {

    /* renamed from: g  reason: collision with root package name */
    public String f11448g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Bundle f11449g;

        a(Bundle bundle) {
            this.f11449g = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bundle bundle = new Bundle();
            bundle.putString("JDPAY_ENTRANCE_VERIFY", "JDPAY_ACCESS");
            bundle.putString("ORDERID", this.f11449g.getString("orderId"));
            bundle.putString("MERCHANT", this.f11449g.getString(IRequestPayment.OUT_merchant));
            bundle.putString("SIGNDATA", this.f11449g.getString("signData"));
            bundle.putString(JDPayApiKey.JDPAY_SESSION_KEY, UserUtil.getWJLoginHelper().getA2());
            bundle.putString(JumpUtils.JDPAY_CODE_SOURCE, "0");
            bundle.putString(JumpUtils.ACCOUNT_MODE, "Native");
            bundle.putString("JDPAY_EXTRA_INFO", this.f11449g.getString("extraInfo"));
            bundle.putBoolean("JDPAY_EXTERNAL", true);
            DeeplinkJDpaySdkHelper.startJDPayActivityForResult(WebBzActivity.this, bundle, 5000);
        }
    }

    private void handleIntent(Intent intent) {
        u(intent.getStringExtra(OpenAppJumpController.KEY_SOURCE_WEBBZ), intent.getExtras());
    }

    private void u(String str, Bundle bundle) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f11448g = bundle.getString("thirdAppKey", "");
        if (TextUtils.equals("jdPay", str)) {
            v(bundle);
        }
    }

    private void v(Bundle bundle) {
        LoginUserHelper.getInstance().executeLoginRunnable(this, new a(bundle), R2.attr.range_v);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 1638) {
            if (i3 == 0) {
                finish();
            }
        } else if (i2 == 5000) {
            try {
                Uri parse = Uri.parse("jdpauth" + this.f11448g + "://?parameterKey=" + (intent != null ? intent.getStringExtra("jdpay_Result") : ""));
                Intent intent2 = new Intent();
                intent2.setData(parse);
                startActivity(intent2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        handleIntent(getIntent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }
}
