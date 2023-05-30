package com.jingdong.app.mall.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jingdong.app.mall.basic.ShareActivity;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.ordercenter.WeiXinManager;
import com.jingdong.common.utils.ShareUtil;
import com.jingdong.common.utils.WXEntryReqJump;
import com.jingdong.common.utils.WeixinUtil;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class WXEntryActivity extends MyActivity implements IWXAPIEventHandler {

    /* renamed from: g  reason: collision with root package name */
    private IWXAPI f12062g;

    private void u(BaseResp baseResp) {
        SendAuth.Resp resp = (SendAuth.Resp) baseResp;
        if (Log.D) {
            Log.d("JD_Smith", "Type: " + resp.getType());
            Log.d("JD_Smith", "Code: " + resp.code);
            Log.d("JD_Smith", "State: " + resp.state);
            Log.d("JD_Smith", "ErrCode: " + resp.errCode);
        }
        Bundle bundle = new Bundle();
        bundle.putInt("type", resp.getType());
        bundle.putString("code", resp.code);
        bundle.putString(XView2Constants.STATE, resp.state);
        bundle.putInt("errCode", resp.errCode);
        Intent intent = new Intent(Configuration.BROADCAST_FROM_WXLOGIN);
        intent.putExtras(bundle);
        intent.setPackage(JdSdk.getInstance().getApplicationContext().getPackageName());
        sendBroadcast(intent, Configuration.SLEF_BROADCAST_PERMISSION);
        if (Log.D) {
            Log.e("JD_Smith", "Broadcast has been send.");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.needSetOrientation = false;
        this.f12062g = WeixinUtil.getWXApi();
        if (getIntent() == null) {
            finish();
            return;
        }
        this.f12062g.handleIntent(getIntent(), this);
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.f12062g.handleIntent(intent, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        finish();
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
        if (Log.D) {
            Log.d("WXEntryActivity", " onReq -->> ");
        }
        WeiXinManager.getInstance().receivedReq(baseReq);
        WXEntryReqJump.onWXEntryReq(this, baseReq);
    }

    @Override // com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        if (Log.D) {
            Log.d("WXEntryActivity", " onResp -->> resp.errCode :  " + baseResp.errCode);
        }
        int type = baseResp.getType();
        if (type == 1) {
            u(baseResp);
        } else if (type != 2) {
            if (type == 19) {
                String str = ((WXLaunchMiniProgram.Resp) baseResp).extMsg;
                BaseActivity currentMyActivity = getCurrentMyActivity();
                if (Log.D) {
                    Log.d("WXEntryActivity", "MiniProgram Callback: " + str);
                }
                if (currentMyActivity != null) {
                    Intent intent = new Intent("com.jd.wxMiniProgramAction");
                    intent.putExtra("extraData", str);
                    LocalBroadcastManager.getInstance(JdSdk.getInstance().getApplication()).sendBroadcast(intent);
                }
            } else if (type == 26) {
                WXOpenBusinessView.Resp resp = (WXOpenBusinessView.Resp) baseResp;
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("errCode", resp.errCode);
                    jSONObject.put("errStr", resp.errStr);
                    jSONObject.put("extMsg", new JSONObject(resp.extMsg));
                    jSONObject.put("businessType", resp.businessType);
                    if (Log.D) {
                        Log.d("WXEntryActivity", "wxPayScoreMiniProgram Callback: " + jSONObject);
                    }
                    if (getCurrentMyActivity() != null) {
                        Intent intent2 = new Intent("com.jd.wx.payscore.MiniProgram.action");
                        intent2.putExtra("extraData", jSONObject.toString());
                        LocalBroadcastManager.getInstance(JdSdk.getInstance().getApplication()).sendBroadcast(intent2);
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        } else if (!TextUtils.isEmpty(baseResp.transaction)) {
            int i2 = -1;
            int i3 = baseResp.errCode;
            if (i3 == -6) {
                ToastUtils.showToast("Authorization failed");
            } else if (i3 == -4) {
                i2 = 12;
            } else if (i3 == -2) {
                i2 = 13;
            } else if (i3 != 0) {
                ToastUtils.showToast(baseResp.errCode + ":" + baseResp.errStr);
            } else {
                i2 = 11;
            }
            BaseActivity currentMyActivity2 = getCurrentMyActivity();
            if (currentMyActivity2 != null && (currentMyActivity2 instanceof ShareActivity)) {
                ShareActivity shareActivity = (ShareActivity) currentMyActivity2;
                shareActivity.a(i2, baseResp.transaction, baseResp.errStr);
                shareActivity.q0();
            } else {
                ShareUtil.backShareActivity(this, i2, baseResp.transaction, baseResp.errStr);
            }
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.utils.MyActivity, com.jingdong.common.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        finish();
    }
}
