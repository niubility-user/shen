package com.jingdong.sdk.talos.inner;

import android.text.TextUtils;
import com.jingdong.sdk.talos.inner.e;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: classes.dex */
public class b {

    /* renamed from: f  reason: collision with root package name */
    private static b f15493f;
    public com.jingdong.sdk.talos.b a;
    public ConcurrentLinkedQueue<e> b;

    /* renamed from: c  reason: collision with root package name */
    public f f15494c;
    public g d;

    /* renamed from: e  reason: collision with root package name */
    private SimpleDateFormat f15495e;

    private b(com.jingdong.sdk.talos.b bVar) {
        if (!bVar.A()) {
            throw new IllegalArgumentException("config parameter is invalid");
        }
        this.a = bVar;
        if (TextUtils.isEmpty(bVar.i())) {
            this.a.C(bVar.b().getFilesDir().getAbsolutePath() + File.separator + "logx_file");
        }
        this.a.B(bVar.b().getFilesDir().getAbsolutePath() + File.separator + "logx_mmap");
        this.b = new ConcurrentLinkedQueue<>();
        this.f15495e = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        if (this.f15494c == null) {
            g gVar = new g(this);
            this.d = gVar;
            gVar.a();
            this.d.g();
            f fVar = new f(this.b, this.a, this.d);
            this.f15494c = fVar;
            fVar.setName("logx-thread");
            this.f15494c.start();
        }
    }

    private long a(String str) {
        try {
            return this.f15495e.parse(str).getTime();
        } catch (ParseException e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    public static b b(com.jingdong.sdk.talos.b bVar) {
        if (f15493f == null) {
            synchronized (b.class) {
                if (f15493f == null) {
                    f15493f = new b(bVar);
                }
            }
        }
        return f15493f;
    }

    public static String c() {
        return CProtocol.g();
    }

    public final void d(String[] strArr, boolean z) {
        if (TextUtils.isEmpty(this.a.i())) {
            return;
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                long a = a(str);
                if (a > 0) {
                    e eVar = new e();
                    j jVar = new j();
                    eVar.a = e.a.b;
                    jVar.b = String.valueOf(a);
                    jVar.d = new k(this.d);
                    jVar.f15528e = z;
                    eVar.f15511c = jVar;
                    this.b.add(eVar);
                    f fVar = this.f15494c;
                    if (fVar != null) {
                        fVar.a();
                    }
                }
            }
        }
    }
}
