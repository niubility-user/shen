package com.jingdong.app.mall.bundle.jdweather.network;

import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.app.mall.bundle.jdweather.network.i;
import com.jingdong.app.mall.bundle.jdweather.network.j;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class h<P extends j, E extends i> implements b {
    private com.jingdong.app.mall.bundle.jdweather.b.a<E> a;

    public abstract void a(d dVar, P p);

    protected abstract E b();

    protected final void c() {
        if (this.a == null) {
            return;
        }
        E b = b();
        if (b != null) {
            b.a(4096);
        }
        this.a.callBack(b);
    }

    public void d(P p) {
        d dVar = new d();
        dVar.l(this);
        dVar.h("client", "android");
        if (p != null) {
            dVar.h(HybridSDK.APP_VERSION, p.a);
            Map<String, String> map = p.b;
            if (map != null && !map.isEmpty()) {
                for (String str : map.keySet()) {
                    dVar.i(str, map.get(str));
                }
            }
        }
        a(dVar, p);
        a.b().a(dVar);
    }

    public abstract E e(String str);

    public void f(com.jingdong.app.mall.bundle.jdweather.b.a<E> aVar) {
        this.a = aVar;
    }

    @Override // com.jingdong.app.mall.bundle.jdweather.network.e
    public void onError(String str) {
        com.jingdong.app.mall.bundle.jdweather.c.a.a("WeatherBaseAction", "onError:\t" + str);
    }

    @Override // com.jingdong.app.mall.bundle.jdweather.network.f
    public void onReady() {
        com.jingdong.app.mall.bundle.jdweather.c.a.a("WeatherBaseAction", "onReady:\t");
    }

    @Override // com.jingdong.app.mall.bundle.jdweather.network.g
    public void onSuccess(String str) {
        com.jingdong.app.mall.bundle.jdweather.c.a.a("WeatherBaseAction", "onSuccess:\t" + str);
        if (this.a == null) {
            return;
        }
        E e2 = e(str);
        if (e2 != null) {
            this.a.callBack(e2);
        } else {
            c();
        }
    }
}
