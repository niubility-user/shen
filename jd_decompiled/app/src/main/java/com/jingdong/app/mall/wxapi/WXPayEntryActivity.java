package com.jingdong.app.mall.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jingdong.common.weixin.WeiXinPayUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/* loaded from: classes4.dex */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    /* renamed from: g  reason: collision with root package name */
    private IWXAPI f12063g;

    private void a() {
        Intent intent = new Intent();
        intent.setAction("com.jd.wxPay.invalid.action");
        LocalBroadcastManager.getInstance(JdSdk.getInstance().getApplication().getBaseContext()).sendBroadcast(intent);
    }

    private void b(BaseResp baseResp) {
        Intent intent = new Intent();
        intent.putExtra("errCode", baseResp.errCode);
        intent.putExtra("errStr", baseResp.errStr);
        intent.setAction("com.jd.wxPayResult");
        LocalBroadcastManager.getInstance(JdSdk.getInstance().getApplication().getBaseContext()).sendBroadcast(intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        if (Log.D) {
            Log.d("WXPayEntryActivity", "onCreate() -->> ");
        }
        requestWindowFeature(1);
        super.onCreate(bundle);
        IWXAPI weiXinApi = WeiXinPayUtil.getWeiXinApi();
        this.f12063g = weiXinApi;
        weiXinApi.handleIntent(getIntent(), this);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        if (Log.D) {
            Log.d("WXPayEntryActivity", "onNewIntent() -->> ");
        }
        super.onNewIntent(intent);
        setIntent(intent);
        this.f12063g.handleIntent(intent, this);
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
        if (Log.D) {
            Log.d("WXPayEntryActivity", "onReq() -->> ");
        }
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        if (Log.D) {
            Log.d("WXPayEntryActivity", "onResp() -->> errCode = " + baseResp.errCode + " type = " + baseResp.getType());
        }
        if (baseResp != null) {
            try {
                try {
                } catch (Exception unused) {
                    if (Log.D) {
                        Log.e("WXPayEntryActivity", "onResp() -->> exception ");
                    }
                }
                if (baseResp.getType() == 5) {
                    b(baseResp);
                }
            } finally {
                finish();
            }
        }
        a();
    }
}
