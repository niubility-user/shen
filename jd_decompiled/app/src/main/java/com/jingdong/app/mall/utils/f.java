package com.jingdong.app.mall.utils;

import android.content.Intent;
import android.text.TextUtils;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.common.BaseActivity;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.config.HostConfig;
import com.jingdong.jdsdk.config.HostConstants;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.SerializableContainer;
import com.jingdong.jdsdk.utils.URLParamMap;

/* loaded from: classes4.dex */
public class f {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a implements HttpGroup.OnCommonListener {

        /* renamed from: g */
        final /* synthetic */ String f11784g;

        /* renamed from: h */
        final /* synthetic */ BaseActivity f11785h;

        /* renamed from: i */
        final /* synthetic */ String f11786i;

        /* renamed from: j */
        final /* synthetic */ ExceptionReporter f11787j;

        /* renamed from: com.jingdong.app.mall.utils.f$a$a */
        /* loaded from: classes4.dex */
        class RunnableC0385a implements Runnable {

            /* renamed from: g */
            final /* synthetic */ String f11788g;

            /* renamed from: h */
            final /* synthetic */ String f11789h;

            RunnableC0385a(String str, String str2) {
                a.this = r1;
                this.f11788g = str;
                this.f11789h = str2;
            }

            @Override // java.lang.Runnable
            public void run() {
                URLParamMap uRLParamMap = new URLParamMap();
                String str = this.f11788g;
                if ("coupon".equals(a.this.f11784g)) {
                    str = str + "?jshopURIID=" + a.this.f11786i;
                }
                if (Log.D) {
                    Log.d("CouponUtil", " queryTakeCoupon -->> couponUrl " + str);
                }
                uRLParamMap.put(RemoteMessageConst.TO, str);
                Intent intent = new Intent(a.this.f11785h, WebActivity.class);
                SerializableContainer serializableContainer = new SerializableContainer();
                serializableContainer.setMap(uRLParamMap);
                intent.putExtra("urlParamMap", serializableContainer);
                intent.putExtra("urlAction", this.f11789h);
                a.this.f11785h.startActivity(intent);
            }
        }

        a(String str, BaseActivity baseActivity, String str2, ExceptionReporter exceptionReporter) {
            this.f11784g = str;
            this.f11785h = baseActivity;
            this.f11786i = str2;
            this.f11787j = exceptionReporter;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            if (Log.D) {
                Log.d("CouponUtil", " onEnd -->> ");
            }
            JSONObjectProxy jSONObject = httpResponse.getJSONObject();
            if (Log.D) {
                Log.d("CouponUtil", " onEnd -->> jsonObject : " + jSONObject);
            }
            JSONArrayPoxy jSONArrayOrNull = jSONObject.getJSONArrayOrNull("subItemList");
            if (jSONArrayOrNull != null && jSONArrayOrNull.length() > 0) {
                JSONObjectProxy jSONObjectOrNull = jSONArrayOrNull.getJSONObjectOrNull(0);
                String stringOrNull = jSONObjectOrNull.getStringOrNull("url");
                String stringOrNull2 = jSONObjectOrNull.getStringOrNull("functionId");
                if (!TextUtils.isEmpty(stringOrNull) && !TextUtils.isEmpty(stringOrNull2)) {
                    this.f11785h.post(new RunnableC0385a(stringOrNull, stringOrNull2));
                    return;
                } else {
                    this.f11787j.reportHttpBusinessException(httpResponse);
                    return;
                }
            }
            this.f11787j.reportHttpBusinessException(httpResponse);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
            httpSettingParams.putJsonParam("type", this.f11784g);
        }
    }

    public static void a(BaseActivity baseActivity, String str, String str2, String str3) {
        if (Log.D) {
            Log.d("CouponUtil", " queryTakeCoupon -->> ");
        }
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setHost(HostConfig.getInstance().getHost(HostConstants.PERSONAL_HOST));
        httpSetting.setFunctionId("jdHomeShowItem");
        httpSetting.setListener(new a(str, baseActivity, str3, new ExceptionReporter(httpSetting)));
        httpSetting.setNotifyUser(true);
        baseActivity.getHttpGroupaAsynPool().add(httpSetting);
    }
}
