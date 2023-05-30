package com.jd.lib.cashier.sdk.d.f;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.google.common.net.HttpHeaders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.config.IConfig;
import com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpCreator;
import com.jd.cashier.app.jdlibcutter.protocol.http.IHttpSetting;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.utils.f;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.d.f.b;
import com.jd.lib.cashier.sdk.d.f.c;

/* loaded from: classes14.dex */
public abstract class a<P extends c, E extends b> implements HttpListener {
    private f<E> a;

    private void a(IHttpSetting iHttpSetting, P p) {
        IConfig sdkConfig = DependInitializer.getSdkConfig();
        if (iHttpSetting == null || sdkConfig == null || p == null) {
            return;
        }
        iHttpSetting.putJsonParam(HttpHeaders.ReferrerPolicyValues.ORIGIN, "native");
        iHttpSetting.putJsonParam("client", "android");
        iHttpSetting.putJsonParam("source", sdkConfig.getAppSource());
        iHttpSetting.putJsonParam("appId", p.appId);
        iHttpSetting.putJsonParam("orderId", p.orderId);
        iHttpSetting.putJsonParam("orderType", p.orderType);
        iHttpSetting.putJsonParam("orderPrice", p.orderPrice);
        iHttpSetting.putJsonParam("orderTypeCode", p.orderTypeCode);
        iHttpSetting.putJsonParam("paySign", p.paySign);
        if (!TextUtils.isEmpty(p.groupOrders)) {
            iHttpSetting.putJsonParam(PairKey.GROUP_ORDERS, p.groupOrders);
        }
        if (!TextUtils.isEmpty(p.combinedOrderId)) {
            iHttpSetting.putJsonParam(PairKey.COMBINED_ORDER_ID, p.combinedOrderId);
        }
        String i2 = m.f().i();
        if (!TextUtils.isEmpty(i2)) {
            iHttpSetting.putJsonParam("payId", i2);
        }
        String j2 = m.f().j();
        if (!TextUtils.isEmpty(j2)) {
            iHttpSetting.putJsonParam("platPayCashierType", j2);
        }
        String h2 = m.f().h();
        if (TextUtils.isEmpty(h2)) {
            return;
        }
        iHttpSetting.putJsonParam("dynamicFlag", h2);
    }

    private void b(IHttpSetting iHttpSetting) {
        if (iHttpSetting != null) {
            String k2 = m.f().k();
            if (TextUtils.isEmpty(k2)) {
                return;
            }
            iHttpSetting.putQueryParam("scval", k2);
        }
    }

    private void f(String str) {
        E d;
        if (this.a == null || (d = d(str)) == null) {
            return;
        }
        d.setResultCode(com.jd.lib.cashier.sdk.d.b.b.FAILED);
        this.a.callBack(d);
    }

    private void g(String str) {
        if (this.a == null) {
            return;
        }
        E i2 = i(str);
        if (i2 != null && TextUtils.equals(i2.code, "0")) {
            if (!TextUtils.isEmpty(i2.payId)) {
                m.f().n(i2.payId);
            }
            i2.setResultCode(com.jd.lib.cashier.sdk.d.b.b.SUC);
            this.a.callBack(i2);
            return;
        }
        f(null);
    }

    public abstract void c(IHttpSetting iHttpSetting, P p);

    protected abstract E d(String str);

    public abstract void e(P p);

    public final void h(P p) {
        IHttpCreator httpCreator = DependInitializer.getHttpCreator();
        IHttpSetting createHttp = httpCreator != null ? httpCreator.createHttp() : null;
        if (createHttp != null) {
            createHttp.setListener(this);
            createHttp.setNotifyUser(false);
            createHttp.setEffect(1);
            b(createHttp);
            a(createHttp, p);
            c(createHttp, p);
            FragmentActivity activity = p != null ? p.getActivity() : null;
            if (activity == null) {
                createHttp.doRequest();
            } else {
                createHttp.doRequest(activity);
            }
        }
    }

    public abstract E i(String str);

    public void j(f<E> fVar) {
        this.a = fVar;
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onEnd(String str) {
        if (this.a == null) {
            return;
        }
        try {
            g(str);
        } catch (Exception e2) {
            f(null);
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.http.HttpListener
    public void onError(String str) {
        f(str);
    }
}
