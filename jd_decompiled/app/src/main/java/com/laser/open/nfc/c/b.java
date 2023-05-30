package com.laser.open.nfc.c;

import android.os.Build;
import com.google.gson.Gson;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.laser.open.nfc.model.entity.BaseReq;
import com.laser.utils.app.AppUtil;
import com.laser.utils.app.PhoneUtil;

/* loaded from: classes12.dex */
public class b {

    /* renamed from: c */
    protected static final int f15672c = 0;
    protected static final int d = 1;

    /* renamed from: e */
    protected static final String f15673e = "80086000020947869";

    /* renamed from: f */
    protected static final String f15674f = "http://192.168.11.98:8000/apkp/nfcOpenTsmServer/";

    /* renamed from: g */
    private static volatile b f15675g;

    /* renamed from: h */
    private static Object f15676h = new Object();
    private BaseReq a;
    private String b = "1.0";

    private b() {
    }

    public static b c() {
        if (f15675g == null) {
            synchronized (f15676h) {
                if (f15675g == null) {
                    f15675g = new b();
                }
            }
        }
        return f15675g;
    }

    public void a(String str, String str2) {
        b().setPartnerId(str);
        b().setMsisdn(str2);
    }

    public BaseReq b() {
        if (this.a == null) {
            BaseReq baseReq = new BaseReq();
            this.a = baseReq;
            baseReq.setCallAppPackageName(AppUtil.getPackageName());
            BaseReq baseReq2 = this.a;
            String deviceModel = BaseInfo.getDeviceModel();
            baseReq2.setDeviceModel(deviceModel);
            this.a.setCallAppHash(AppUtil.getAppHash(AppUtil.getPackageName()));
            this.a.setPhoneModel(deviceModel);
            this.a.setPhoneOsVersion(Build.VERSION.RELEASE);
            this.a.setPhoneType(1);
            this.a.setVersion(this.b);
            this.a.setBasePhoneVersion(PhoneUtil.getBasePhoneVersion());
            this.a.setAppVersion(AppUtil.getClientVersionName());
        }
        return this.a;
    }

    public String a() {
        return new Gson().toJson(b());
    }
}
