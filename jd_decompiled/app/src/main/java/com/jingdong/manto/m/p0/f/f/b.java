package com.jingdong.manto.m.p0.f.f;

import com.jingdong.manto.m.p0.f.c;
import com.jingdong.manto.m.p0.f.f.c.c;
import com.jingdong.manto.m.p0.f.f.c.e;
import com.jingdong.manto.m.p0.f.f.c.f;
import com.jingdong.manto.m.p0.f.f.c.g;
import java.io.File;

/* loaded from: classes15.dex */
public class b implements c {
    private com.jingdong.manto.m.p0.f.b a;
    private com.jingdong.manto.m.p0.f.f.c.a b;

    /* renamed from: c  reason: collision with root package name */
    private String f13544c;

    /* loaded from: classes15.dex */
    class a implements f.d {
        a() {
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.f.d
        public void a(int i2) {
            if (i2 != 800 || b.this.a == null) {
                return;
            }
            b.this.a.a();
        }
    }

    /* renamed from: com.jingdong.manto.m.p0.f.f.b$b  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    class C0600b implements f.d {
        C0600b() {
        }

        @Override // com.jingdong.manto.m.p0.f.f.c.f.d
        public void a(int i2) {
            if (i2 != 800 || b.this.a == null) {
                return;
            }
            b.this.a.a();
        }
    }

    public b(com.jingdong.manto.m.p0.f.b bVar) {
        this.a = bVar;
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void a() {
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void a(String str, int i2, int i3, int i4, int i5) {
        com.jingdong.manto.m.p0.f.f.c.a aVar;
        f.b bVar;
        this.f13544c = str;
        this.b.b(new File(str));
        if (i5 == 1) {
            aVar = this.b;
            bVar = new f.b(new g.a(new c.a(0, 2, 16, i3)), i2, new a());
        } else {
            aVar = this.b;
            bVar = new f.b(new g.a(new c.a(0, 2, 16, i3)), i2, new C0600b());
        }
        aVar.a(bVar);
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void b() {
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public int getDuration() {
        return com.jingdong.manto.m.p0.f.a.a(this.f13544c);
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void init() {
        this.b = e.a();
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void pause() {
        try {
            this.b.a();
        } catch (Throwable th) {
            com.jingdong.manto.m.p0.f.b bVar = this.a;
            if (bVar != null) {
                bVar.a(th.getMessage());
            }
        }
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void release() {
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void resume() {
        try {
            this.b.b();
        } catch (Throwable th) {
            com.jingdong.manto.m.p0.f.b bVar = this.a;
            if (bVar != null) {
                bVar.a(th.getMessage());
            }
        }
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void start() {
        try {
            this.b.c();
        } catch (Throwable th) {
            com.jingdong.manto.m.p0.f.b bVar = this.a;
            if (bVar != null) {
                bVar.a(th.getMessage());
            }
        }
    }

    @Override // com.jingdong.manto.m.p0.f.c
    public void stop() {
        try {
            this.b.d();
        } catch (Throwable th) {
            com.jingdong.manto.m.p0.f.b bVar = this.a;
            if (bVar != null) {
                bVar.a(th.getMessage());
            }
        }
    }
}
