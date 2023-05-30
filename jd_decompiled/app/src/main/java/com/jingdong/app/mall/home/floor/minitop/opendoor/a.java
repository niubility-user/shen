package com.jingdong.app.mall.home.floor.minitop.opendoor;

import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.JDHomeFragment;
import com.jingdong.app.mall.home.floor.minitop.mintop.MiniTopVideo;
import com.jingdong.app.mall.home.floor.view.view.MallFloorLineMore;
import com.jingdong.app.mall.home.r.e.h;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class a extends com.jingdong.app.mall.home.r.d.a {

    /* renamed from: l  reason: collision with root package name */
    private final Map<MallFloorLineMore, Boolean> f9650l;

    /* renamed from: m  reason: collision with root package name */
    private MiniTopVideo f9651m;

    /* renamed from: n  reason: collision with root package name */
    private OpenDoorLayout f9652n;
    private com.jingdong.app.mall.home.o.a.b o;

    /* renamed from: com.jingdong.app.mall.home.floor.minitop.opendoor.a$a  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    class C0294a extends com.jingdong.app.mall.home.o.a.b {
        C0294a() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            if (a.this.p()) {
                return;
            }
            a.this.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class b extends com.jingdong.app.mall.home.o.a.b {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ boolean f9654g;

        b(boolean z) {
            this.f9654g = z;
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            a.this.c(this.f9654g);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c extends com.jingdong.app.mall.home.o.a.b {
        c() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            a.this.c(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d extends com.jingdong.app.mall.home.o.a.b {
        d() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            a.this.m();
        }
    }

    /* loaded from: classes4.dex */
    class e extends com.jingdong.app.mall.home.o.a.b {
        e() {
        }

        @Override // com.jingdong.app.mall.home.o.a.b
        protected void safeRun() {
            while (((com.jingdong.app.mall.home.r.d.a) a.this).f10664i != null && !((com.jingdong.app.mall.home.r.d.a) a.this).f10659c.get()) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                if (a.this.f()) {
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class f {
        static a a = new a(null);
    }

    /* synthetic */ a(C0294a c0294a) {
        this();
    }

    private void k() {
        if (this.f9650l.size() > 0) {
            this.f10659c.set(true);
            o(0.0f);
        }
        this.f9650l.clear();
    }

    private void l() {
        if (this.f10663h != null) {
            if (this.f10664i == null || this.f10659c.get()) {
                this.f10659c.set(true);
                this.f10663h.b(true);
                this.f10663h = null;
            }
        }
    }

    public static a n() {
        return f.a;
    }

    @Override // com.jingdong.app.mall.home.r.d.a
    public void a() {
        super.a();
        this.f10659c.set(true);
        d(false);
        OpenDoorLayout openDoorLayout = this.f9652n;
        if (openDoorLayout != null) {
            openDoorLayout.setTranslationX(-(com.jingdong.app.mall.home.floor.common.d.f9279g << 1));
            RelativeLayout relativeLayout = this.f10662g;
            if (relativeLayout != null) {
                relativeLayout.removeView(this.f9652n);
            }
        }
        MiniTopVideo miniTopVideo = this.f9651m;
        if (miniTopVideo != null) {
            miniTopVideo.pause();
            this.f9651m.releaseInThread(true);
        }
        o(0.0f);
        k();
        l();
        this.f9651m = null;
        this.f9652n = null;
    }

    @Override // com.jingdong.app.mall.home.r.d.a
    public void c(boolean z) {
        super.c(z);
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new b(z));
        } else if (!z) {
            this.d.set(true);
        } else {
            a();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.r.d.a
    public void e() {
        super.e();
        m();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.r.d.a
    public void g() {
        super.g();
        OpenDoorLayout openDoorLayout = this.f9652n;
        if (openDoorLayout != null) {
            openDoorLayout.h(this.f10664i);
        }
    }

    public void j(MallFloorLineMore mallFloorLineMore, com.jingdong.app.mall.home.r.e.d dVar) {
        if (this.f10664i == null) {
            return;
        }
        if (this.f9650l.containsKey(mallFloorLineMore)) {
            a();
        } else if (dVar != null && !dVar.isCacheData) {
            if (TextUtils.equals(this.f10664i.a, dVar.getFloorId())) {
                h hVar = dVar.mParentModel;
                if (hVar != null && hVar.G == 2) {
                    this.f9650l.put(mallFloorLineMore, Boolean.TRUE);
                } else {
                    k();
                }
            }
        } else {
            k();
        }
    }

    public void m() {
        if (com.jingdong.app.mall.home.o.a.f.p0()) {
            com.jingdong.app.mall.home.o.a.f.E0(new d());
            return;
        }
        OpenDoorLayout openDoorLayout = this.f9652n;
        if (openDoorLayout != null) {
            openDoorLayout.e();
        }
    }

    public void o(float f2) {
        try {
            if (this.f10659c.get()) {
                f2 = 0.0f;
            }
            Iterator<MallFloorLineMore> it = this.f9650l.keySet().iterator();
            while (it.hasNext()) {
                it.next().openDoorTranslation(f2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean p() {
        MiniTopVideo miniTopVideo;
        if (this.f9650l.size() == 2 && this.a == com.jingdong.app.mall.home.floor.common.d.f9279g && this.f10664i != null && (miniTopVideo = this.f9651m) != null && miniTopVideo.a() && this.f9652n != null && JDHomeFragment.O0()) {
            com.jingdong.app.mall.home.o.a.f.F0(new c(), this.f10664i.f10669c + 2000);
            this.f10659c.set(false);
            d(true);
            this.f9652n.setTranslationX(0.0f);
            this.f9652n.setAlpha(1.0f);
            this.f9651m.seekTo(0);
            this.f9651m.b();
            this.f9652n.g();
            this.f10664i.a("\u5f00\u95e8\u52a8\u6548\u66dd\u5149");
            throw null;
        }
        k();
        return false;
    }

    private a() {
        this.f9650l = new ConcurrentHashMap();
        new C0294a();
        this.o = new e();
    }
}
