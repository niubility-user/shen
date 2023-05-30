package com.jingdong.app.mall.qq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jingdong.common.QQWallet.QQWalletPayUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.tencent.mobileqq.openpay.api.IOpenApiListener;
import com.tencent.mobileqq.openpay.data.base.BaseResponse;
import com.tencent.mobileqq.openpay.data.pay.PayResponse;

/* loaded from: classes4.dex */
public class QQPayCallbackActivity extends Activity implements IOpenApiListener {
    private void a(PayResponse payResponse) {
        Intent intent = new Intent();
        intent.putExtra("retCode", payResponse.retCode);
        intent.setAction("com.jd.QQPayResult");
        LocalBroadcastManager.getInstance(JdSdk.getInstance().getApplication().getBaseContext()).sendBroadcast(intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        QQWalletPayUtil.setCallBackListener(getIntent(), this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        QQWalletPayUtil.setCallBackListener(intent, this);
    }

    @Override // com.tencent.mobileqq.openpay.api.IOpenApiListener
    public void onOpenResponse(BaseResponse baseResponse) {
        if (baseResponse != null && (baseResponse instanceof PayResponse)) {
            PayResponse payResponse = (PayResponse) baseResponse;
            if (Log.D) {
                Log.d("QQPayCallbackActivity", "message--> isSucess:" + payResponse.isSuccess() + " apiName:" + payResponse.apiName + " serialnumber:" + payResponse.serialNumber + " retCode:" + payResponse.retCode + " retMsg:" + payResponse.retMsg);
            }
            a(payResponse);
        }
        finish();
    }
}
