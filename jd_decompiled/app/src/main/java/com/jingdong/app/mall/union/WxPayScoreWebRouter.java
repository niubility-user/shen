package com.jingdong.app.mall.union;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.hms.actions.SearchIntents;
import com.jingdong.common.web.IRouterParams;
import com.jingdong.corelib.utils.Log;
import com.tencent.mm.opensdk.modelbiz.WXOpenBusinessView;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class WxPayScoreWebRouter {
    private static final String TAG = "WxPayScoreWebRouter";
    public static LocalBroadcastManager localBroadcastManager;
    private static WeixinPayScoreReceiver weixinReceiver;

    /* loaded from: classes4.dex */
    public static class WeixinPayScoreReceiver extends BroadcastReceiver {
        private IRouterParams a;

        public WeixinPayScoreReceiver(IRouterParams iRouterParams) {
            this.a = iRouterParams;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("extraData");
            if (Log.D) {
                Log.d(WxPayScoreWebRouter.TAG, "WeixinPayScoreReceiver onReceive: " + stringExtra + ",router:" + this.a);
            }
            IRouterParams iRouterParams = this.a;
            if (iRouterParams != null) {
                try {
                    iRouterParams.onCallBack(new JSONObject(stringExtra));
                    WxPayScoreWebRouter.unRegisterWeixinReceiver();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public static void openWXPayScoreAsync(IRouterParams iRouterParams) {
        String routerParam = iRouterParams.getRouterParam();
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(iRouterParams.getContext(), "wxe75a2e68877315fb", false);
        if (createWXAPI.getWXAppSupportAPI() >= 620889344) {
            registerWeixinReceiver(iRouterParams);
            WXOpenBusinessView.Req req = new WXOpenBusinessView.Req();
            req.businessType = "wxpayScoreEnable";
            req.query = "apply_permissions_token=" + routerParam;
            req.extInfo = "{\"miniProgramType\": 0}";
            createWXAPI.sendReq(req);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errCode", "403");
            jSONObject.put("extMsg", "\u6ca1\u6709\u5b89\u88c5\u5fae\u4fe1\u6216\u8005\u5fae\u4fe1\u7248\u672c\u8fc7\u4f4e");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        iRouterParams.onCallBack(jSONObject);
    }

    public static void openWXPayScoreUseAsync(IRouterParams iRouterParams) {
        try {
            JSONObject jSONObject = new JSONObject(iRouterParams.getRouterParam());
            String string = jSONObject.getString("businessType");
            String string2 = jSONObject.getString(SearchIntents.EXTRA_QUERY);
            String string3 = jSONObject.getString("extInfo");
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(iRouterParams.getContext(), "wxe75a2e68877315fb", false);
            if (createWXAPI.getWXAppSupportAPI() >= 620889344) {
                registerWeixinReceiver(iRouterParams);
                WXOpenBusinessView.Req req = new WXOpenBusinessView.Req();
                req.businessType = string;
                req.query = string2;
                req.extInfo = string3;
                createWXAPI.sendReq(req);
            } else {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("errCode", "403");
                jSONObject2.put("extMsg", "\u6ca1\u6709\u5b89\u88c5\u5fae\u4fe1\u6216\u8005\u5fae\u4fe1\u7248\u672c\u8fc7\u4f4e");
                iRouterParams.onCallBack(jSONObject2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("errCode", "401");
                jSONObject3.put("extMsg", "\u5165\u53c2\u9519\u8bef\u6216\u7f3a\u5c11\u5165\u53c2");
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
            iRouterParams.onCallBack(jSONObject3);
        }
    }

    private static void registerWeixinReceiver(IRouterParams iRouterParams) {
        localBroadcastManager = LocalBroadcastManager.getInstance(iRouterParams.getContext());
        weixinReceiver = new WeixinPayScoreReceiver(iRouterParams);
        localBroadcastManager.registerReceiver(weixinReceiver, new IntentFilter("com.jd.wx.payscore.MiniProgram.action"));
    }

    public static void unRegisterWeixinReceiver() {
        LocalBroadcastManager localBroadcastManager2;
        WeixinPayScoreReceiver weixinPayScoreReceiver = weixinReceiver;
        if (weixinPayScoreReceiver == null || (localBroadcastManager2 = localBroadcastManager) == null) {
            return;
        }
        localBroadcastManager2.unregisterReceiver(weixinPayScoreReceiver);
        localBroadcastManager = null;
    }
}
