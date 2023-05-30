package com.jingdong.app.mall.home.shakeandshow;

import android.widget.RelativeLayout;
import com.jingdong.cleanmvp.common.BaseEvent;
import com.jingdong.sdk.log.Log;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* loaded from: classes4.dex */
public class d {

    /* renamed from: e  reason: collision with root package name */
    public static AtomicBoolean f10843e = new AtomicBoolean(false);

    /* renamed from: f  reason: collision with root package name */
    private static volatile d f10844f = null;

    /* renamed from: g  reason: collision with root package name */
    private static final ReentrantReadWriteLock f10845g = new ReentrantReadWriteLock();
    private e a = null;
    private c b = null;

    /* renamed from: c  reason: collision with root package name */
    private boolean f10846c = false;
    private boolean d;

    private d() {
    }

    public static void a() {
        d f2 = f();
        if (f2 == null || f2.h()) {
            return;
        }
        m();
    }

    private void b(RelativeLayout relativeLayout) {
        if (Log.D) {
            Log.i("ShakeCtrl", "createShakeActionCtrl");
        }
        ReentrantReadWriteLock reentrantReadWriteLock = f10845g;
        reentrantReadWriteLock.writeLock().lock();
        try {
            if (this.b == null) {
                this.b = new c(relativeLayout);
            }
            this.b.p(this.a);
            reentrantReadWriteLock.writeLock().unlock();
        } catch (Throwable th) {
            f10845g.writeLock().unlock();
            throw th;
        }
    }

    private void c() {
        f10843e.set(true);
        com.jingdong.app.mall.home.o.a.f.G0(this);
    }

    private void d() {
        f10843e.set(false);
        com.jingdong.app.mall.home.o.a.f.H0(this);
    }

    public static d e(e eVar) {
        if (f10844f == null) {
            synchronized (d.class) {
                if (f10844f == null) {
                    f10844f = new d();
                }
            }
        }
        if (eVar != null) {
            f10844f.n(eVar);
        }
        return f10844f;
    }

    public static d f() {
        return f10844f;
    }

    public static d g(com.jingdong.app.mall.home.r.e.h hVar, RelativeLayout relativeLayout) {
        if (hVar == null) {
            m();
            return null;
        }
        e b = e.b(hVar);
        if (Log.D) {
            Log.i("ShakeCtrl", "getShakeCtrl:" + b);
        }
        if (b != null && b.f10853j != null) {
            d e2 = e(b);
            if (e2 != null) {
                e2.k();
                e2.b(relativeLayout);
                e2.c();
            }
            return e2;
        }
        m();
        return null;
    }

    private boolean h() {
        ReentrantReadWriteLock reentrantReadWriteLock = f10845g;
        reentrantReadWriteLock.readLock().lock();
        try {
            boolean z = this.b != null;
            reentrantReadWriteLock.readLock().unlock();
            return z;
        } catch (Throwable th) {
            f10845g.readLock().unlock();
            throw th;
        }
    }

    public static void m() {
        if (f10844f != null) {
            synchronized (d.class) {
                if (f10844f != null) {
                    f10844f.o();
                    f10844f = null;
                }
            }
        }
    }

    public void i() {
        ReentrantReadWriteLock reentrantReadWriteLock = f10845g;
        reentrantReadWriteLock.readLock().lock();
        try {
            this.d = false;
            c cVar = this.b;
            if (cVar == null) {
                reentrantReadWriteLock.readLock().unlock();
                return;
            }
            cVar.l();
            reentrantReadWriteLock.readLock().unlock();
            l();
        } catch (Throwable th) {
            f10845g.readLock().unlock();
            throw th;
        }
    }

    public void j() {
        ReentrantReadWriteLock reentrantReadWriteLock = f10845g;
        reentrantReadWriteLock.readLock().lock();
        try {
            this.d = true;
            c cVar = this.b;
            if (cVar == null) {
                reentrantReadWriteLock.readLock().unlock();
                return;
            }
            cVar.m();
            reentrantReadWriteLock.readLock().unlock();
            p();
        } catch (Throwable th) {
            f10845g.readLock().unlock();
            throw th;
        }
    }

    public void k() {
        e eVar = this.a;
        h.c(eVar == null ? "" : eVar.d);
    }

    public void l() {
        if (Log.D) {
            Log.i("ShakeCtrl", "registShake:" + this.f10846c);
        }
        if (this.f10846c || this.d) {
            return;
        }
        ReentrantReadWriteLock reentrantReadWriteLock = f10845g;
        reentrantReadWriteLock.readLock().lock();
        try {
            c cVar = this.b;
            if (cVar == null) {
                reentrantReadWriteLock.readLock().unlock();
                return;
            }
            cVar.o();
            reentrantReadWriteLock.readLock().unlock();
        } catch (Throwable th) {
            f10845g.readLock().unlock();
            throw th;
        }
    }

    public void n(e eVar) {
        this.a = eVar;
    }

    public void o() {
        ReentrantReadWriteLock reentrantReadWriteLock = f10845g;
        reentrantReadWriteLock.writeLock().lock();
        try {
            c cVar = this.b;
            if (cVar != null) {
                cVar.r();
            }
            this.b = null;
            reentrantReadWriteLock.writeLock().unlock();
            d();
        } catch (Throwable th) {
            f10845g.writeLock().unlock();
            throw th;
        }
    }

    public void onEventMainThread(BaseEvent baseEvent) {
        String type = baseEvent.getType();
        type.hashCode();
        char c2 = '\uffff';
        switch (type.hashCode()) {
            case -1158331917:
                if (type.equals("homePageXViewDisplay")) {
                    c2 = 0;
                    break;
                }
                break;
            case -277321843:
                if (type.equals("home_resume")) {
                    c2 = 1;
                    break;
                }
                break;
            case 322633389:
                if (type.equals("overseas_dialog_show")) {
                    c2 = 2;
                    break;
                }
                break;
            case 815832937:
                if (type.equals("homePageXViewClose")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1397043272:
                if (type.equals("overseas_dialog_close")) {
                    c2 = 4;
                    break;
                }
                break;
            case 2118188898:
                if (type.equals("home_stop")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 2:
                if (Log.D) {
                    Log.d("ShakeCtrl", "XView display, shake close; to unregister ...");
                }
                this.f10846c = true;
                p();
                return;
            case 1:
                i();
                return;
            case 3:
            case 4:
                if (Log.D) {
                    Log.d("ShakeCtrl", " XView close, shake open, to register ...");
                }
                this.f10846c = false;
                l();
                return;
            case 5:
                j();
                return;
            default:
                return;
        }
    }

    public void p() {
        if (Log.D) {
            Log.i("ShakeCtrl", "unregistShake");
        }
        ReentrantReadWriteLock reentrantReadWriteLock = f10845g;
        reentrantReadWriteLock.readLock().lock();
        try {
            c cVar = this.b;
            if (cVar == null) {
                reentrantReadWriteLock.readLock().unlock();
                return;
            }
            cVar.r();
            reentrantReadWriteLock.readLock().unlock();
        } catch (Throwable th) {
            f10845g.readLock().unlock();
            throw th;
        }
    }
}
