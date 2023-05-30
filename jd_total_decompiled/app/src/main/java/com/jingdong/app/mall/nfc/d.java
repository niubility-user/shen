package com.jingdong.app.mall.nfc;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.R;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.deeplinkhelper.DeepLinkCartHelper;
import com.jingdong.common.entity.JumpEntity;
import com.jingdong.common.entity.cart.CartResponse;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.entity.cart.methodEntity.CartAddUniformEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class d {
    private static Handler a = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements HttpGroup.OnAllListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ BaseActivity f11420g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f11421h;

        a(BaseActivity baseActivity, String str) {
            this.f11420g = baseActivity;
            this.f11421h = str;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            String str = "resolveNFCCode onEnd: " + httpResponse.getString();
            JumpEntity jumpEntity = new com.jingdong.app.mall.nfc.e.a(JDJSON.parseObject(httpResponse.getString())).a;
            int optInt = httpResponse.getFastJsonObject().optInt("subCode", 1);
            String optString = httpResponse.getFastJsonObject().optString("type", "UNKNOW");
            if (optInt == 0) {
                JumpUtil.execJump(this.f11420g, jumpEntity, -1);
            } else {
                d.i(this.f11420g, httpResponse.getFastJsonObject().optString("msg", ""));
            }
            d.g(this.f11420g, optString, this.f11421h);
            d.e(this.f11420g);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            String str = "JD-WJ onError: " + httpError.getErrorCode() + "msg:" + httpError.getMessage();
            BaseActivity baseActivity = this.f11420g;
            d.i(baseActivity, baseActivity.getString(R.string.nfc_net_err));
            d.e(this.f11420g);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f11422g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f11423h;

        b(Context context, String str) {
            this.f11422g = context;
            this.f11423h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Toast.makeText(this.f11422g, this.f11423h, 0).show();
        }
    }

    /* loaded from: classes4.dex */
    class c implements HttpGroup.OnAllListener {
        c() {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            String str = "WJ onEnd: " + httpResponse.getString();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            String str = "WJ onError: " + httpError.getErrorCode() + httpError.getMessage();
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
        }
    }

    /* renamed from: com.jingdong.app.mall.nfc.d$d  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0365d extends ShoppingBaseController.ShoppingMultiListener {
        final /* synthetic */ Context a;

        C0365d(Context context) {
            this.a = context;
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingMultiListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onEnd(CartResponse cartResponse) {
            super.onEnd(cartResponse);
            String resultMsg = cartResponse.getResultMsg();
            if (!TextUtils.isEmpty(resultMsg)) {
                d.i(this.a, resultMsg);
            }
            d.a(this.a, null);
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingMultiListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onError(String str) {
            String str2 = "onError: " + str;
            d.e(this.a);
        }

        @Override // com.jingdong.common.controller.ShoppingBaseController.ShoppingMultiListener, com.jingdong.common.controller.ShoppingBaseController.ShoppingListener
        public void onReady() {
        }
    }

    public static void a(Context context, Bundle bundle) {
        DeepLinkCartHelper.startCartMainUseFlag(context, bundle, 335544320);
        e(context);
    }

    public static void d(Context context, String str) {
        CartAddUniformEntity cartAddUniformEntity = new CartAddUniformEntity();
        ArrayList<CartSkuSummary> arrayList = new ArrayList<>();
        arrayList.add(new CartSkuSummary(str, 1));
        cartAddUniformEntity.myActivity = (IMyActivity) context;
        cartAddUniformEntity.skuList = arrayList;
        cartAddUniformEntity.isNotify = true;
        cartAddUniformEntity.isEffect = true;
        cartAddUniformEntity.isNoResponse = false;
        cartAddUniformEntity.listener = new C0365d(context);
        ShoppingBaseController.addCartUniform(cartAddUniformEntity);
    }

    public static void e(Context context) {
        if (context == null || !(context instanceof NfcIntentHandleActivity)) {
            return;
        }
        ((NfcIntentHandleActivity) context).finish();
    }

    public static void f(BaseActivity baseActivity, String str, String str2, String str3) {
        String str4 = "getNfcType: " + str + "--type:" + str2 + "--uid:" + str3;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("resolveNFCCode");
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.putJsonParam("code", str);
        httpSetting.putJsonParam("type", str2);
        httpSetting.putJsonParam("uid", str3);
        httpSetting.setAttempts(1);
        httpSetting.setListener(new a(baseActivity, str3));
        baseActivity.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g(Context context, String str, String str2) {
        JDMtaUtils.sendExposureData(context, context.getClass().getName(), "NFCScan_PV", "", "NFCScan_Virtual", str + "&&" + str2, "", "", "");
    }

    public static void h(BaseActivity baseActivity, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        String str3 = "sendToWJ: shopId:" + str + "  skuId:" + str2;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("recordNFCCodeRelation");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.putJsonParam("shopId", str);
        httpSetting.putJsonParam("skuId", str2);
        httpSetting.setAttempts(1);
        httpSetting.setEffect(0);
        httpSetting.setListener(new c());
        baseActivity.getHttpGroupaAsynPool().add(httpSetting);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void i(Context context, String str) {
        a.post(new b(context, str));
    }
}
