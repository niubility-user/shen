package com.meizu.cloud.pushsdk.f.c;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.meizu.cloud.pushsdk.e.d.i;
import com.meizu.cloud.pushsdk.e.d.j;
import com.meizu.cloud.pushsdk.e.d.k;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: classes14.dex */
public abstract class a {
    private final String a;
    private final com.meizu.cloud.pushsdk.e.d.g b;

    /* renamed from: c  reason: collision with root package name */
    protected final Context f15862c;
    private Uri.Builder d;

    /* renamed from: e  reason: collision with root package name */
    protected final f f15863e;

    /* renamed from: f  reason: collision with root package name */
    private d f15864f;

    /* renamed from: g  reason: collision with root package name */
    private b f15865g;

    /* renamed from: h  reason: collision with root package name */
    private String f15866h;

    /* renamed from: i  reason: collision with root package name */
    protected final int f15867i;

    /* renamed from: j  reason: collision with root package name */
    protected final int f15868j;

    /* renamed from: k  reason: collision with root package name */
    protected final int f15869k;

    /* renamed from: l  reason: collision with root package name */
    private final long f15870l;

    /* renamed from: m  reason: collision with root package name */
    private final long f15871m;

    /* renamed from: n  reason: collision with root package name */
    protected final TimeUnit f15872n;
    private final com.meizu.cloud.pushsdk.e.d.a o;
    protected final AtomicBoolean p;

    /* renamed from: com.meizu.cloud.pushsdk.f.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public static class C0761a {
        protected final String a;
        protected final Context b;

        /* renamed from: l  reason: collision with root package name */
        protected SSLSocketFactory f15881l;

        /* renamed from: m  reason: collision with root package name */
        protected HostnameVerifier f15882m;

        /* renamed from: c  reason: collision with root package name */
        protected f f15873c = null;
        protected d d = d.POST;

        /* renamed from: e  reason: collision with root package name */
        protected b f15874e = b.Single;

        /* renamed from: f  reason: collision with root package name */
        protected int f15875f = 5;

        /* renamed from: g  reason: collision with root package name */
        protected int f15876g = 250;

        /* renamed from: h  reason: collision with root package name */
        protected int f15877h = 5;

        /* renamed from: i  reason: collision with root package name */
        protected long f15878i = 40000;

        /* renamed from: j  reason: collision with root package name */
        protected long f15879j = 40000;

        /* renamed from: k  reason: collision with root package name */
        protected TimeUnit f15880k = TimeUnit.SECONDS;

        /* renamed from: n  reason: collision with root package name */
        protected com.meizu.cloud.pushsdk.e.d.a f15883n = new com.meizu.cloud.pushsdk.e.d.e();

        public C0761a(String str, Context context, Class<? extends a> cls) {
            this.a = str;
            this.b = context;
        }

        public C0761a a(int i2) {
            this.f15877h = i2;
            return this;
        }

        public C0761a b(com.meizu.cloud.pushsdk.e.d.a aVar) {
            if (aVar != null) {
                this.f15883n = aVar;
                com.meizu.cloud.pushsdk.f.g.c.g(C0761a.class.getSimpleName(), "set new call " + aVar, new Object[0]);
            }
            return this;
        }

        public C0761a c(b bVar) {
            this.f15874e = bVar;
            return this;
        }

        public C0761a d(f fVar) {
            this.f15873c = fVar;
            return this;
        }

        public C0761a e(int i2) {
            this.f15876g = i2;
            return this;
        }

        public C0761a f(int i2) {
            this.f15875f = i2;
            return this;
        }
    }

    public a(C0761a c0761a) {
        String simpleName = a.class.getSimpleName();
        this.a = simpleName;
        this.b = com.meizu.cloud.pushsdk.e.d.g.a("application/json; charset=utf-8");
        this.p = new AtomicBoolean(false);
        this.f15864f = c0761a.d;
        this.f15863e = c0761a.f15873c;
        this.f15862c = c0761a.b;
        this.f15865g = c0761a.f15874e;
        SSLSocketFactory sSLSocketFactory = c0761a.f15881l;
        HostnameVerifier hostnameVerifier = c0761a.f15882m;
        this.f15867i = c0761a.f15875f;
        this.f15868j = c0761a.f15877h;
        this.f15869k = c0761a.f15876g;
        this.f15870l = c0761a.f15878i;
        this.f15871m = c0761a.f15879j;
        this.f15866h = c0761a.a;
        this.f15872n = c0761a.f15880k;
        this.o = c0761a.f15883n;
        e();
        com.meizu.cloud.pushsdk.f.g.c.g(simpleName, "Emitter created successfully!", new Object[0]);
    }

    private i b(com.meizu.cloud.pushsdk.f.b.a aVar) {
        g(aVar, "");
        this.d.clearQuery();
        HashMap hashMap = (HashMap) aVar.a();
        for (String str : hashMap.keySet()) {
            this.d.appendQueryParameter(str, (String) hashMap.get(str));
        }
        String uri = this.d.build().toString();
        i.b bVar = new i.b();
        bVar.e(uri);
        bVar.i();
        return bVar.h();
    }

    private i c(ArrayList<com.meizu.cloud.pushsdk.f.b.a> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<com.meizu.cloud.pushsdk.f.b.a> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(it.next().a());
        }
        com.meizu.cloud.pushsdk.f.b.b bVar = new com.meizu.cloud.pushsdk.f.b.b("push_group_data", arrayList2);
        com.meizu.cloud.pushsdk.f.g.c.e(this.a, "final SelfDescribingJson " + bVar, new Object[0]);
        String uri = this.d.build().toString();
        j c2 = j.c(this.b, bVar.toString());
        i.b bVar2 = new i.b();
        bVar2.e(uri);
        bVar2.n(c2);
        return bVar2.h();
    }

    private void e() {
        Uri.Builder buildUpon = Uri.parse("https://" + this.f15866h).buildUpon();
        this.d = buildUpon;
        if (this.f15864f == d.GET) {
            buildUpon.appendPath("i");
        } else {
            buildUpon.appendEncodedPath("push_data_report/mobile");
        }
    }

    private void f(k kVar) {
        if (kVar != null) {
            try {
                if (kVar.a() != null) {
                    kVar.a().close();
                }
            } catch (Exception unused) {
                com.meizu.cloud.pushsdk.f.g.c.e(this.a, "Unable to close source data", new Object[0]);
            }
        }
    }

    private void g(com.meizu.cloud.pushsdk.f.b.a aVar, String str) {
        if ("".equals(str)) {
            str = com.meizu.cloud.pushsdk.f.g.e.h();
        }
        aVar.a(EtModelMaker.KEY_STM, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int a(i iVar) {
        k kVar = null;
        try {
            try {
                com.meizu.cloud.pushsdk.f.g.c.e(this.a, "Sending request: %s", iVar);
                kVar = this.o.a(iVar);
                return kVar.b();
            } catch (IOException e2) {
                com.meizu.cloud.pushsdk.f.g.c.f(this.a, "Request sending failed: %s", Log.getStackTraceString(e2));
                f(kVar);
                return -1;
            }
        } finally {
            f(kVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LinkedList<e> d(c cVar) {
        int size = cVar.b().size();
        LinkedList<Long> a = cVar.a();
        LinkedList<e> linkedList = new LinkedList<>();
        long j2 = 22;
        if (this.f15864f == d.GET) {
            for (int i2 = 0; i2 < size; i2++) {
                LinkedList linkedList2 = new LinkedList();
                linkedList2.add(a.get(i2));
                com.meizu.cloud.pushsdk.f.b.a aVar = cVar.b().get(i2);
                linkedList.add(new e(aVar.b() + 22 > this.f15870l, b(aVar), linkedList2));
            }
        } else {
            int i3 = 0;
            while (i3 < size) {
                LinkedList linkedList3 = new LinkedList();
                ArrayList<com.meizu.cloud.pushsdk.f.b.a> arrayList = new ArrayList<>();
                long j3 = 0;
                int i4 = i3;
                while (i4 < this.f15865g.a() + i3 && i4 < size) {
                    com.meizu.cloud.pushsdk.f.b.a aVar2 = cVar.b().get(i4);
                    long b = aVar2.b() + j2;
                    if (b + 88 > this.f15871m) {
                        ArrayList<com.meizu.cloud.pushsdk.f.b.a> arrayList2 = new ArrayList<>();
                        LinkedList linkedList4 = new LinkedList();
                        arrayList2.add(aVar2);
                        linkedList4.add(a.get(i4));
                        linkedList.add(new e(true, c(arrayList2), linkedList4));
                    } else {
                        j3 += b;
                        if (j3 + 88 + (arrayList.size() - 1) > this.f15871m) {
                            linkedList.add(new e(false, c(arrayList), linkedList3));
                            ArrayList<com.meizu.cloud.pushsdk.f.b.a> arrayList3 = new ArrayList<>();
                            LinkedList linkedList5 = new LinkedList();
                            arrayList3.add(aVar2);
                            linkedList5.add(a.get(i4));
                            arrayList = arrayList3;
                            linkedList3 = linkedList5;
                            j3 = b;
                        } else {
                            arrayList.add(aVar2);
                            linkedList3.add(a.get(i4));
                        }
                    }
                    i4++;
                    j2 = 22;
                }
                if (!arrayList.isEmpty()) {
                    linkedList.add(new e(false, c(arrayList), linkedList3));
                }
                i3 += this.f15865g.a();
                j2 = 22;
            }
        }
        return linkedList;
    }

    public abstract void h(com.meizu.cloud.pushsdk.f.b.a aVar, boolean z);

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean i(int i2) {
        return i2 >= 200 && i2 < 300;
    }

    public abstract void j();

    public String k() {
        return this.d.clearQuery().build().toString();
    }
}
