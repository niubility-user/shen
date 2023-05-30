package com.jingdong.app.mall.home.n;

import android.content.Context;
import android.text.TextUtils;
import com.jd.framework.json.JDJSONObject;
import com.jd.stat.security.jma.JMA;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class d {

    /* renamed from: g  reason: collision with root package name */
    private static ReentrantReadWriteLock f10338g = new ReentrantReadWriteLock();

    /* renamed from: h  reason: collision with root package name */
    private static ReentrantReadWriteLock f10339h = new ReentrantReadWriteLock();
    private HttpRequest a;
    private HttpRequest b;

    /* renamed from: c  reason: collision with root package name */
    private AtomicInteger f10340c = new AtomicInteger(0);
    private AtomicBoolean d = new AtomicBoolean(true);

    /* renamed from: e  reason: collision with root package name */
    private AtomicInteger f10341e = new AtomicInteger(0);

    /* renamed from: f  reason: collision with root package name */
    private AtomicBoolean f10342f = new AtomicBoolean(true);

    /* loaded from: classes4.dex */
    class a extends C0309d {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ c f10343h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(int i2, c cVar) {
            super(d.this, i2);
            this.f10343h = cVar;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            d.f10338g.readLock().lock();
            try {
                d.this.d.set(false);
                if (d.this.f10340c.get() == this.f10349g) {
                    if (httpResponse == null) {
                        this.f10343h.j("Response is null");
                    } else {
                        JDJSONObject s = k.s("categoryHome.txt", httpResponse);
                        if (s == null) {
                            this.f10343h.j("ResponseJson is null");
                        } else {
                            this.f10343h.g(s);
                        }
                    }
                }
            } finally {
                d.f10338g.readLock().unlock();
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            d.f10338g.readLock().lock();
            try {
                d.this.d.set(false);
                if (d.this.f10340c.get() != this.f10349g) {
                    return;
                }
                this.f10343h.j(httpError.getMessage());
            } finally {
                d.f10338g.readLock().unlock();
            }
        }
    }

    /* loaded from: classes4.dex */
    class b extends C0309d {

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ c f10345h;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(int i2, c cVar) {
            super(d.this, i2);
            this.f10345h = cVar;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            d.f10339h.readLock().lock();
            try {
                if (d.this.f10341e.get() != this.f10349g) {
                    return;
                }
                if (httpResponse == null) {
                    this.f10345h.j("Response is null");
                    return;
                }
                JDJSONObject s = k.s("categoryFeeds.txt", httpResponse);
                if (s == null) {
                    this.f10345h.j("ResponseJson is null");
                    return;
                }
                this.f10345h.g(s);
                d.f10339h.readLock().unlock();
                d.this.f10342f.set(false);
            } finally {
                d.f10339h.readLock().unlock();
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            d.f10339h.readLock().lock();
            try {
                d.this.f10342f.set(false);
                if (d.this.f10341e.get() != this.f10349g) {
                    return;
                }
                this.f10345h.j(httpError.getMessage());
            } finally {
                d.f10339h.readLock().unlock();
            }
        }
    }

    /* loaded from: classes4.dex */
    public static abstract class c {
        private CategoryEntity.CaItem a;
        private String b;

        /* renamed from: c  reason: collision with root package name */
        private String f10347c;
        protected boolean d;

        /* renamed from: e  reason: collision with root package name */
        protected boolean f10348e;

        /* JADX INFO: Access modifiers changed from: protected */
        public c(boolean z, boolean z2, CategoryEntity.CaItem caItem) {
            this.d = z;
            this.f10348e = z2;
            this.a = caItem;
            this.b = caItem.getSort();
            this.f10347c = caItem.getSelf();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @NotNull
        public CategoryEntity.CaItem a() {
            return this.a;
        }

        public String b() {
            return this.f10347c;
        }

        public String c() {
            return this.b;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void d(@NotNull JDJSONObject jDJSONObject) {
            this.a.setPage(com.jingdong.app.mall.home.r.e.b.getJsonInt(jDJSONObject, "page", 1));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean e() {
            CategoryEntity.CaItem caItem = this.a;
            return caItem == null || caItem.isBottom();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public boolean f(CategoryEntity.CaItem caItem) {
            return caItem != null && this.a == caItem && TextUtils.equals(caItem.getSelf(), this.f10347c) && TextUtils.equals(caItem.getSort(), this.b);
        }

        final void g(@NotNull JDJSONObject jDJSONObject) {
            h(this.a, jDJSONObject);
        }

        public abstract void h(CategoryEntity.CaItem caItem, @NotNull JDJSONObject jDJSONObject);

        public abstract void i(CategoryEntity.CaItem caItem, String str);

        final void j(String str) {
            i(this.a, str);
        }
    }

    /* renamed from: com.jingdong.app.mall.home.n.d$d  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0309d implements HttpGroup.OnCommonListener {

        /* renamed from: g  reason: collision with root package name */
        int f10349g;

        C0309d(d dVar, int i2) {
            this.f10349g = i2;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    public void g() {
        i();
        h();
    }

    public void h() {
        f10339h.writeLock().lock();
        try {
            HttpRequest httpRequest = this.b;
            if (httpRequest != null) {
                httpRequest.stop();
            }
            this.f10341e.set(0);
            this.f10342f.set(false);
        } finally {
            f10339h.writeLock().unlock();
        }
    }

    public void i() {
        f10338g.writeLock().lock();
        try {
            HttpRequest httpRequest = this.a;
            if (httpRequest != null) {
                httpRequest.stop();
            }
            this.f10340c.set(0);
            this.d.set(false);
        } finally {
            f10338g.writeLock().unlock();
        }
    }

    public boolean j() {
        return this.f10342f.get();
    }

    public boolean k() {
        return this.d.get();
    }

    public void l(c cVar) {
        if (this.f10342f.get()) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        this.f10342f.set(true);
        this.f10341e.getAndIncrement();
        httpSetting.setListener(new b(this.f10341e.get(), cVar));
        CategoryEntity.CaItem a2 = cVar.a();
        String b2 = cVar.b();
        if (!TextUtils.isEmpty(b2)) {
            httpSetting.putJsonParam("self", b2);
        }
        httpSetting.putJsonParam("sort", cVar.c());
        httpSetting.putJsonParam("pcid", String.valueOf(a2.getPcId()));
        httpSetting.putJsonParam("page", String.valueOf(a2.getPage()));
        httpSetting.putJsonParam("tabOrder", String.valueOf(a2.getPosition()));
        httpSetting.putJsonParam(JshopConst.JSHOP_DYNAMIC_TAB_NAME, a2.getTabName());
        httpSetting.setFunctionId("categoryFeeds");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setPost(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(0);
        this.b = HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public void m(Context context, c cVar) {
        if (this.d.get()) {
            return;
        }
        this.d.set(true);
        this.f10340c.getAndIncrement();
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setListener(new a(this.f10340c.get(), cVar));
        httpSetting.putJsonParam("pageClickKey", com.jingdong.app.mall.home.n.g.v.b.a);
        httpSetting.putJsonParam("shshshfpb", JMA.getSoftFingerprint(context));
        CategoryEntity.CaItem a2 = cVar.a();
        httpSetting.putJsonParam("pcid", a2.getPcId());
        httpSetting.putJsonParam("catePoolId", a2.getPool());
        httpSetting.putJsonParam("tabOrder", Integer.valueOf(a2.getPosition()));
        httpSetting.setFunctionId("categoryHome");
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setPost(true);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setEffect(0);
        this.a = HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }
}
