package com.jingdong.union.common.config;

import android.content.Context;
import com.jingdong.union.dependency.IAdvertUtils;
import com.jingdong.union.dependency.IAndroidId;
import com.jingdong.union.dependency.IDensity;
import com.jingdong.union.dependency.IJdAdvertUtils;
import com.jingdong.union.dependency.IJumpDispatchCallBack;
import com.jingdong.union.dependency.ILoadingView;
import com.jingdong.union.dependency.ILoginUser;
import com.jingdong.union.dependency.IMtaUtils;
import com.jingdong.union.dependency.IOaid;
import com.jingdong.union.dependency.IUnionExceptionReport;
import com.jingdong.union.dependency.IUuid;
import com.jingdong.union.dependency.IWebUa;

/* loaded from: classes12.dex */
public class JdUnionConfig {
    private IUuid a;
    private IAndroidId b;

    /* renamed from: c  reason: collision with root package name */
    private IDensity f15588c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f15589e;

    /* renamed from: f  reason: collision with root package name */
    private Context f15590f;

    /* renamed from: g  reason: collision with root package name */
    private IAdvertUtils f15591g;

    /* renamed from: h  reason: collision with root package name */
    private IUnionExceptionReport f15592h;

    /* renamed from: i  reason: collision with root package name */
    private IMtaUtils f15593i;

    /* renamed from: j  reason: collision with root package name */
    private ILoginUser f15594j;

    /* renamed from: k  reason: collision with root package name */
    private IJumpDispatchCallBack f15595k;

    /* renamed from: l  reason: collision with root package name */
    private IWebUa f15596l;

    /* renamed from: m  reason: collision with root package name */
    private IOaid f15597m;

    /* renamed from: n  reason: collision with root package name */
    private IJdAdvertUtils f15598n;
    private ILoadingView o;

    /* loaded from: classes12.dex */
    public static class Builder {
        private IUuid a;
        private IAndroidId b;

        /* renamed from: c  reason: collision with root package name */
        private IDensity f15599c;
        private String d;

        /* renamed from: e  reason: collision with root package name */
        private boolean f15600e;

        /* renamed from: f  reason: collision with root package name */
        private Context f15601f;

        /* renamed from: g  reason: collision with root package name */
        private IAdvertUtils f15602g;

        /* renamed from: h  reason: collision with root package name */
        private IUnionExceptionReport f15603h;

        /* renamed from: i  reason: collision with root package name */
        private IMtaUtils f15604i;

        /* renamed from: j  reason: collision with root package name */
        private ILoginUser f15605j;

        /* renamed from: k  reason: collision with root package name */
        private IJumpDispatchCallBack f15606k;

        /* renamed from: l  reason: collision with root package name */
        private IWebUa f15607l;

        /* renamed from: m  reason: collision with root package name */
        private IOaid f15608m;

        /* renamed from: n  reason: collision with root package name */
        private IJdAdvertUtils f15609n;
        private ILoadingView o;

        public JdUnionConfig build() {
            return new JdUnionConfig(this);
        }

        public Builder setAndroidId(IAndroidId iAndroidId) {
            this.b = iAndroidId;
            return this;
        }

        public Builder setContext(Context context) {
            this.f15601f = context;
            return this;
        }

        public Builder setDensity(IDensity iDensity) {
            this.f15599c = iDensity;
            return this;
        }

        public Builder setLog(boolean z) {
            this.f15600e = z;
            return this;
        }

        public Builder setOaId(IOaid iOaid) {
            this.f15608m = iOaid;
            return this;
        }

        public Builder setToken(String str) {
            this.d = str;
            return this;
        }

        public Builder setUuid(IUuid iUuid) {
            this.a = iUuid;
            return this;
        }

        public Builder setiAdvertUtils(IAdvertUtils iAdvertUtils) {
            this.f15602g = iAdvertUtils;
            return this;
        }

        public Builder setiJdAdvertUtils(IJdAdvertUtils iJdAdvertUtils) {
            this.f15609n = iJdAdvertUtils;
            return this;
        }

        public Builder setiJumpDispatchCallBack(IJumpDispatchCallBack iJumpDispatchCallBack) {
            this.f15606k = iJumpDispatchCallBack;
            return this;
        }

        public Builder setiLoadingView(ILoadingView iLoadingView) {
            this.o = iLoadingView;
            return this;
        }

        public Builder setiLoginUser(ILoginUser iLoginUser) {
            this.f15605j = iLoginUser;
            return this;
        }

        public Builder setiMtaUtils(IMtaUtils iMtaUtils) {
            this.f15604i = iMtaUtils;
            return this;
        }

        public Builder setiUnionExceptionReport(IUnionExceptionReport iUnionExceptionReport) {
            this.f15603h = iUnionExceptionReport;
            return this;
        }

        public Builder setiWebUa(IWebUa iWebUa) {
            this.f15607l = iWebUa;
            return this;
        }
    }

    public IAndroidId getAndroidId() {
        return this.b;
    }

    public Context getContext() {
        return this.f15590f;
    }

    public IDensity getDensity() {
        return this.f15588c;
    }

    public String getToken() {
        return this.d;
    }

    public IUuid getUuid() {
        return this.a;
    }

    public IAdvertUtils getiAdvertUtils() {
        return this.f15591g;
    }

    public IJdAdvertUtils getiJdAdvertUtils() {
        return this.f15598n;
    }

    public IJumpDispatchCallBack getiJumpDispatchCallBack() {
        return this.f15595k;
    }

    public ILoadingView getiLoadingView() {
        return this.o;
    }

    public ILoginUser getiLoginUser() {
        return this.f15594j;
    }

    public IMtaUtils getiMtaUtils() {
        return this.f15593i;
    }

    public IOaid getiOaid() {
        return this.f15597m;
    }

    public IUnionExceptionReport getiUnionExceptionReport() {
        return this.f15592h;
    }

    public IWebUa getiWebUa() {
        return this.f15596l;
    }

    public boolean isLog() {
        return this.f15589e;
    }

    private JdUnionConfig(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.f15588c = builder.f15599c;
        this.d = builder.d;
        this.f15589e = builder.f15600e;
        this.f15590f = builder.f15601f;
        this.o = builder.o;
        this.f15591g = builder.f15602g;
        this.f15592h = builder.f15603h;
        this.f15593i = builder.f15604i;
        this.f15594j = builder.f15605j;
        this.f15595k = builder.f15606k;
        this.f15596l = builder.f15607l;
        this.f15597m = builder.f15608m;
        this.f15598n = builder.f15609n;
    }
}
