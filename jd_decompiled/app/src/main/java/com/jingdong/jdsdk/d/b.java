package com.jingdong.jdsdk.d;

import android.content.Context;
import com.jingdong.jdsdk.d.c.a.d;
import com.jingdong.jdsdk.d.c.a.e;
import com.jingdong.jdsdk.d.c.a.f;
import com.jingdong.jdsdk.d.c.a.g;
import com.jingdong.jdsdk.d.c.a.h;
import com.jingdong.jdsdk.d.c.a.i;
import com.jingdong.jdsdk.d.c.a.j;
import com.jingdong.jdsdk.d.c.a.k;
import com.jingdong.jdsdk.d.c.a.l;
import com.jingdong.jdsdk.d.c.a.m;
import com.jingdong.jdsdk.d.c.a.n;
import com.jingdong.jdsdk.d.c.a.o;
import com.jingdong.jdsdk.d.c.a.p;
import com.jingdong.jdsdk.d.c.a.q;
import com.jingdong.jdsdk.d.c.a.r;
import com.jingdong.sdk.platform.business.PlatformBusinessConfig;
import com.jingdong.sdk.platform.config.PlatformConfig;
import com.jingdong.sdk.platform.lib.openapi.OpenApiConfig;

/* loaded from: classes14.dex */
public class b {
    public static void a(boolean z, Context context) {
        PlatformConfig.config(PlatformBusinessConfig.getPlatformBuilder(z, context));
        a.a(z);
        OpenApiConfig.initOpenApiEngine(OpenApiConfig.Builder.newBuilder(context).setiAddressUtil(d.a()).setiAdvertUtils(e.a()).setiAuraBundleConfig(g.b()).setiApplicationContext(f.a()).setiLoginUserBase(com.jingdong.jdsdk.d.c.a.a.a()).setiCartIcon(h.a()).setiCartTable(i.c()).setiDeeplink(j.a()).setiDeviceInfo(k.a()).setiFavouritesHelper(l.a()).setiFontsUtil(m.a()).setiHostConfig(n.a()).setIjdMtaUtils(o.a()).setiLoginApi(q.a()).setIxView(r.a()).setPlatformLifecyleCompact(new p()).build());
    }
}
