package com.jdpay.membercode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.lib.util.Utils;
import com.jdpay.membercode.bean.CodeEnterInfoBean;
import com.jdpay.membercode.bean.CodeInfoBean;
import com.jdpay.membercode.bean.CodeResultInfoBean;
import com.jdpay.membercode.bean.QueryVerifyPayWay;
import com.jdpay.membercode.network.EnterRequestBean;
import com.jdpay.membercode.network.OpenRequestBean;
import com.jdpay.membercode.network.PayResultRequestBean;
import com.jdpay.membercode.network.QueryVerifyPayWayRequestBean;
import com.jdpay.membercode.network.RefreshCodeRequestBean;
import com.jdpay.membercode.network.ResponseBean;
import com.jdpay.membercode.network.SignAgainRequestBean;
import com.jdpay.membercode.network.StopRequestBean;
import com.jdpay.net.ResultObserver;
import com.jdpay.net.http.BaseHttpService;
import com.jdpay.net.http.HttpProvider;
import com.jdpay.net.http.HttpRequestAdapter;
import java.util.List;

/* loaded from: classes18.dex */
public class b extends BaseHttpService {
    private final a a;
    public volatile String b;

    /* renamed from: c */
    public volatile String f7522c;
    public volatile String d;

    /* renamed from: e */
    public volatile List<String> f7523e;

    /* renamed from: f */
    public volatile String f7524f;

    public b(@NonNull HttpProvider httpProvider) {
        super(httpProvider);
        this.a = (a) create(a.class);
        this.f7524f = "0";
        this.factory.addRequestConverter(100, com.jdpay.membercode.network.a.b.class);
        this.factory.addResponseConverter(1000, com.jdpay.membercode.network.a.a.class);
    }

    private void b(HttpRequestAdapter httpRequestAdapter, com.jdpay.membercode.bean.a aVar) {
        String createRandom = Utils.createRandom(16);
        aVar.setClientKey(createRandom);
        httpRequestAdapter.putExtra(1, createRandom);
    }

    public void a(@NonNull ResultObserver<ResponseBean<CodeEnterInfoBean>> resultObserver) {
        EnterRequestBean enterRequestBean = new EnterRequestBean();
        enterRequestBean.appSource = this.f7522c;
        enterRequestBean.sessionKey = this.b;
        enterRequestBean.deviceInfo.deviceToken = this.d;
        enterRequestBean.couponIds = this.f7523e;
        enterRequestBean.isSafe = this.f7524f;
        HttpRequestAdapter b = this.a.b(enterRequestBean);
        b(b, enterRequestBean);
        enqueue(b, resultObserver);
    }

    public void c(@NonNull String str, @NonNull ResultObserver<ResponseBean<CodeResultInfoBean>> resultObserver) {
        PayResultRequestBean payResultRequestBean = new PayResultRequestBean();
        payResultRequestBean.appSource = this.f7522c;
        payResultRequestBean.sessionKey = this.b;
        payResultRequestBean.code = str;
        HttpRequestAdapter f2 = this.a.f(payResultRequestBean);
        b(f2, payResultRequestBean);
        enqueue(f2, resultObserver);
    }

    public void d(@NonNull String str, @Nullable String str2, @NonNull String str3, @NonNull ResultObserver<ResponseBean<CodeEnterInfoBean>> resultObserver) {
        OpenRequestBean openRequestBean = new OpenRequestBean();
        openRequestBean.appSource = this.f7522c;
        openRequestBean.sessionKey = this.b;
        openRequestBean.deviceInfo.deviceToken = this.d;
        openRequestBean.couponIds = this.f7523e;
        openRequestBean.bizToken = str;
        openRequestBean.payWayType = str3;
        openRequestBean.signResult = str2;
        openRequestBean.isSafe = this.f7524f;
        HttpRequestAdapter c2 = this.a.c(openRequestBean);
        b(c2, openRequestBean);
        enqueue(c2, resultObserver);
    }

    public void e(@NonNull ResultObserver<ResponseBean<QueryVerifyPayWay>> resultObserver) {
        QueryVerifyPayWayRequestBean queryVerifyPayWayRequestBean = new QueryVerifyPayWayRequestBean();
        queryVerifyPayWayRequestBean.appSource = this.f7522c;
        queryVerifyPayWayRequestBean.sessionKey = this.b;
        queryVerifyPayWayRequestBean.deviceInfo.deviceToken = this.d;
        HttpRequestAdapter e2 = this.a.e(queryVerifyPayWayRequestBean);
        b(e2, queryVerifyPayWayRequestBean);
        enqueue(e2, resultObserver);
    }

    public void f(@NonNull ResultObserver<ResponseBean<CodeInfoBean>> resultObserver) {
        RefreshCodeRequestBean refreshCodeRequestBean = new RefreshCodeRequestBean();
        refreshCodeRequestBean.appSource = this.f7522c;
        refreshCodeRequestBean.sessionKey = this.b;
        refreshCodeRequestBean.deviceInfo.deviceToken = this.d;
        refreshCodeRequestBean.couponIds = this.f7523e;
        refreshCodeRequestBean.isSafe = this.f7524f;
        HttpRequestAdapter d = this.a.d(refreshCodeRequestBean);
        b(d, refreshCodeRequestBean);
        enqueue(d, resultObserver);
    }

    public void g(@NonNull ResultObserver<ResponseBean<CodeEnterInfoBean>> resultObserver) {
        SignAgainRequestBean signAgainRequestBean = new SignAgainRequestBean();
        signAgainRequestBean.appSource = this.f7522c;
        signAgainRequestBean.sessionKey = this.b;
        signAgainRequestBean.couponIds = this.f7523e;
        signAgainRequestBean.isSafe = this.f7524f;
        HttpRequestAdapter a = this.a.a(signAgainRequestBean);
        b(a, signAgainRequestBean);
        enqueue(a, resultObserver);
    }

    public void h(@NonNull ResultObserver<ResponseBean<CodeEnterInfoBean>> resultObserver) {
        StopRequestBean stopRequestBean = new StopRequestBean();
        stopRequestBean.appSource = this.f7522c;
        stopRequestBean.sessionKey = this.b;
        HttpRequestAdapter g2 = this.a.g(stopRequestBean);
        b(g2, stopRequestBean);
        enqueue(g2, resultObserver);
    }
}
