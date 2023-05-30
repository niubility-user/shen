package com.jingdong.sdk.jdupgrade.a.i;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.jdupgrade.UpgradeEventListener;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes7.dex */
public abstract class j<DATA> implements com.jingdong.sdk.jdupgrade.a.i.a<DATA, Boolean> {

    /* renamed from: h  reason: collision with root package name */
    private static String f15088h = "TaskChain";

    /* renamed from: i  reason: collision with root package name */
    private static volatile Handler f15089i;
    private Handler b;
    private j<DATA>.b d;

    /* renamed from: e  reason: collision with root package name */
    protected UpgradeEventListener f15091e;

    /* renamed from: f  reason: collision with root package name */
    protected com.jingdong.sdk.jdupgrade.a.i.b f15092f;

    /* renamed from: g  reason: collision with root package name */
    protected boolean f15093g;
    private volatile AtomicReference<i> a = new AtomicReference<>();

    /* renamed from: c  reason: collision with root package name */
    private Handler f15090c = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public class b implements Runnable {
        private b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                i iVar = (i) j.this.a.get();
                if (iVar == null) {
                    j.this.g();
                    com.jingdong.sdk.jdupgrade.a.j.h.a(j.f15088h, "Task is null, nothing to do except finish.");
                    return;
                }
                com.jingdong.sdk.jdupgrade.a.j.h.a(j.f15088h, "execute:" + iVar.a());
                iVar.a(j.this);
                i b = iVar.b();
                String str = j.f15088h;
                StringBuilder sb = new StringBuilder();
                sb.append("next task:");
                sb.append(b == null ? DYConstants.DY_NULL_STR : b.a());
                com.jingdong.sdk.jdupgrade.a.j.h.a(str, sb.toString());
                j.this.a.set(b);
                j.this.f();
            } catch (Throwable th) {
                j.this.g();
                com.jingdong.sdk.jdupgrade.a.j.h.b(j.f15088h, "exception happened, " + th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public j() {
        HandlerThread handlerThread = new HandlerThread("JDUpgrade");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper());
        this.d = new b();
    }

    public j a(h hVar) {
        Handler handler;
        if (!hVar.equals(h.MAIN)) {
            if (hVar.equals(h.WORK)) {
                handler = this.b;
            }
            return this;
        }
        handler = this.f15090c;
        f15089i = handler;
        return this;
    }

    public void a(com.jingdong.sdk.jdupgrade.a.i.b bVar) {
        this.f15092f = bVar;
    }

    public void a(boolean z) {
        this.f15093g = z;
    }

    public com.jingdong.sdk.jdupgrade.a.i.b c() {
        return this.f15092f;
    }

    public UpgradeEventListener d() {
        return this.f15091e;
    }

    public boolean e() {
        return this.f15093g;
    }

    public void f() {
        f15089i.postDelayed(this.d, 100L);
    }

    public void g() {
        this.b.removeCallbacks(this.d);
        this.a.set(null);
    }

    public void h() {
        this.a.set(new c());
        a(h.WORK).f();
    }
}
