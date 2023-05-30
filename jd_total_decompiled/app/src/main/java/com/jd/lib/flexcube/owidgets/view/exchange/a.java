package com.jd.lib.flexcube.owidgets.view.exchange;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.flexcube.owidgets.entity.exchange.ExchangeData;
import com.jd.lib.flexcube.owidgets.entity.exchange.ExchangeEntity;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes15.dex */
public class a {
    private b a;
    private Handler b = new Handler(Looper.getMainLooper());

    /* renamed from: c */
    private boolean f4429c;

    /* renamed from: com.jd.lib.flexcube.owidgets.view.exchange.a$a */
    /* loaded from: classes15.dex */
    public class C0148a implements HttpGroup.OnCommonListener {

        /* renamed from: com.jd.lib.flexcube.owidgets.view.exchange.a$a$a */
        /* loaded from: classes15.dex */
        class RunnableC0149a implements Runnable {
            RunnableC0149a() {
                C0148a.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.a.onError();
            }
        }

        /* renamed from: com.jd.lib.flexcube.owidgets.view.exchange.a$a$b */
        /* loaded from: classes15.dex */
        class b implements Runnable {

            /* renamed from: g */
            final /* synthetic */ ExchangeData f4432g;

            b(ExchangeData exchangeData) {
                C0148a.this = r1;
                this.f4432g = exchangeData;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.a.a(this.f4432g);
            }
        }

        C0148a() {
            a.this = r1;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            a.this.f4429c = false;
            JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
            if (fastJsonObject == null) {
                return;
            }
            a.this.b.post(new b((ExchangeData) JDJSON.parseObject(fastJsonObject.optString("exchangeData"), ExchangeData.class)));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            a.this.f4429c = false;
            a.this.b.post(new RunnableC0149a());
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    public a(b bVar) {
        this.a = bVar;
    }

    public void d(Context context, ExchangeEntity exchangeEntity) {
        if (this.f4429c || exchangeEntity == null) {
            return;
        }
        this.f4429c = true;
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId("venderPointsExchange");
        httpSetting.putJsonParam("venderId", exchangeEntity.venderId);
        httpSetting.putJsonParam("exchangeId", exchangeEntity.exchangeId);
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setLocalFileCache(false);
        httpSetting.setOnTouchEvent(true);
        httpSetting.setAttempts(1);
        httpSetting.setEffect(0);
        httpSetting.setListener(new C0148a());
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
