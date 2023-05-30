package com.jingdong.app.mall.home.r.c;

import android.content.Context;
import android.text.TextUtils;
import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class d {
    private final String a;

    /* renamed from: e  reason: collision with root package name */
    private b f10656e;
    private String b = RecommendMtaUtils.Home_PageId;

    /* renamed from: c  reason: collision with root package name */
    private String f10655c = "";
    private String d = "";

    /* renamed from: f  reason: collision with root package name */
    private HashMap<String, String> f10657f = null;

    /* renamed from: g  reason: collision with root package name */
    private String f10658g = "";

    public d(String str) {
        this.a = str;
    }

    public static d b(String str) {
        return new d(str);
    }

    public d a(String str, String str2) {
        if (this.f10656e == null) {
            this.f10656e = b.c(this.d);
        }
        this.f10656e.a(str, str2);
        return this;
    }

    public void c() {
        if (TextUtils.isEmpty(this.a)) {
            return;
        }
        Context applicationContext = JdSdk.getInstance().getApplicationContext();
        String str = this.a;
        String str2 = this.f10655c;
        String str3 = this.b;
        String str4 = a.f10642k;
        String str5 = this.f10658g;
        b bVar = this.f10656e;
        JDMtaUtils.sendClickDataWithExt(applicationContext, str, str2, "", str3, str4, str5, "", bVar != null ? bVar.toString() : this.d, this.f10657f);
    }

    public void d() {
        if (TextUtils.isEmpty(this.a)) {
            return;
        }
        Context applicationContext = JdSdk.getInstance().getApplicationContext();
        String str = this.a;
        String str2 = this.f10655c;
        String str3 = this.b;
        String str4 = a.f10642k;
        String str5 = this.f10658g;
        b bVar = this.f10656e;
        JDMtaUtils.sendExposureDataWithExt(applicationContext, str, str2, str3, str4, str5, bVar != null ? bVar.toString() : this.d, this.f10657f);
    }

    public d e(String str) {
        this.f10655c = str;
        return this;
    }

    public d f(String str) {
        if (this.f10656e != null) {
            f.o("setJsonParam must use before addJsonParam");
        }
        this.d = str;
        return this;
    }
}
