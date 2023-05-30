package com.jingdong.sdk.talos.inner.c;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class a {
    public boolean a;
    public String b;

    /* renamed from: c  reason: collision with root package name */
    public List<String> f15496c = new ArrayList();
    public String d;

    /* renamed from: e  reason: collision with root package name */
    public int f15497e;

    /* renamed from: f  reason: collision with root package name */
    public int f15498f;

    /* renamed from: g  reason: collision with root package name */
    public int f15499g;

    /* renamed from: h  reason: collision with root package name */
    public int f15500h;

    /* renamed from: i  reason: collision with root package name */
    public int f15501i;

    /* renamed from: j  reason: collision with root package name */
    public String f15502j;

    /* renamed from: com.jingdong.sdk.talos.inner.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0747a {
        boolean a = false;
        String b = "";

        /* renamed from: c  reason: collision with root package name */
        String f15503c = "wifi";
        String d = "";

        /* renamed from: e  reason: collision with root package name */
        int f15504e = 7;

        /* renamed from: f  reason: collision with root package name */
        int f15505f = 20000;

        /* renamed from: g  reason: collision with root package name */
        int f15506g = 50;

        /* renamed from: h  reason: collision with root package name */
        int f15507h = 500;

        /* renamed from: i  reason: collision with root package name */
        int f15508i = 3;

        /* renamed from: j  reason: collision with root package name */
        String f15509j = "https://talos-transfer.jd.com/upload";

        public final a a() {
            return new a(this);
        }

        public final a b() {
            return new a(this);
        }
    }

    public a(C0747a c0747a) {
        this.a = c0747a.a;
        this.b = c0747a.b;
        this.d = c0747a.d;
        this.f15497e = c0747a.f15504e;
        this.f15498f = c0747a.f15505f;
        this.f15499g = c0747a.f15506g;
        this.f15500h = c0747a.f15507h;
        this.f15501i = c0747a.f15508i;
        this.f15502j = c0747a.f15509j;
        a(c0747a.f15503c);
    }

    public final void a(String str) {
        String[] split;
        if (TextUtils.isEmpty(str) || (split = str.split(DYConstants.DY_REGEX_COMMA)) == null || split.length <= 0) {
            return;
        }
        for (String str2 : split) {
            this.f15496c.add(str2);
        }
        if (str.contains("wifi")) {
            this.f15496c.add(BaseInfo.NETWORK_TYPE_ETHERNET);
        }
    }
}
