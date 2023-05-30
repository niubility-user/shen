package com.jd.sec;

import com.jd.sec.LogoManager;

/* loaded from: classes17.dex */
public class InitParams {
    private boolean a;
    private String b;

    /* renamed from: c */
    private String f6863c;
    private String d;

    /* renamed from: e */
    private LogoManager.IEnv f6864e;

    /* loaded from: classes17.dex */
    public static class InitParamsBuilder {
        private boolean acceptPrivacy;
        private String deviceUUID;
        private LogoManager.IEnv env;
        private String imei;
        private String pin;

        public InitParamsBuilder acceptPrivacy(boolean z) {
            this.acceptPrivacy = z;
            return this;
        }

        public InitParams build() {
            return new InitParams(this);
        }

        public InitParamsBuilder deviceUUID(String str) {
            this.deviceUUID = str;
            return this;
        }

        public InitParamsBuilder env(LogoManager.IEnv iEnv) {
            this.env = iEnv;
            return this;
        }

        public InitParamsBuilder imei(String str) {
            this.imei = str;
            return this;
        }

        public InitParamsBuilder pin(String str) {
            this.pin = str;
            return this;
        }
    }

    private InitParams(InitParamsBuilder initParamsBuilder) {
        this.a = initParamsBuilder.acceptPrivacy;
        this.b = initParamsBuilder.deviceUUID;
        this.f6863c = initParamsBuilder.imei;
        this.d = initParamsBuilder.pin;
        this.f6864e = initParamsBuilder.env;
    }

    public String a() {
        return this.d;
    }

    public boolean b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.f6863c;
    }

    public LogoManager.IEnv e() {
        return this.f6864e;
    }
}
