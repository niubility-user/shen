package com.jingdong.moutaibuy.lib.workflow;

import com.jingdong.moutaibuy.lib.f.b;
import com.jingdong.moutaibuy.lib.i.d;
import com.jingdong.moutaibuy.lib.view.MouTaiScanView;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

/* loaded from: classes16.dex */
public class b implements com.jingdong.moutaibuy.lib.e.b, b.c {
    MouTaiScanView a;
    com.jingdong.moutaibuy.lib.e.a b;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private long f14637e;

    /* renamed from: c  reason: collision with root package name */
    private int f14636c = 0;

    /* renamed from: h  reason: collision with root package name */
    private boolean f14640h = true;

    /* renamed from: i  reason: collision with root package name */
    private boolean f14641i = true;

    /* renamed from: j  reason: collision with root package name */
    private boolean f14642j = false;

    /* renamed from: f  reason: collision with root package name */
    private String f14638f = UUID.randomUUID().toString();

    /* renamed from: g  reason: collision with root package name */
    private LinkedList<String> f14639g = new LinkedList<>();

    /* loaded from: classes16.dex */
    class a implements b.c {
        a() {
        }

        @Override // com.jingdong.moutaibuy.lib.f.b.c
        public void b(int i2, b.C0706b c0706b) {
            b.this.f14642j = true;
        }

        @Override // com.jingdong.moutaibuy.lib.f.b.c
        public void d(int i2, Exception exc) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.moutaibuy.lib.workflow.b$b  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    public class C0710b implements b.c {
        C0710b() {
        }

        @Override // com.jingdong.moutaibuy.lib.f.b.c
        public void b(int i2, b.C0706b c0706b) {
            if (i2 == b.this.f14636c && b.this.f14641i) {
                if (c0706b == null) {
                    b.this.n(i2);
                    return;
                }
                int i3 = c0706b.a;
                if (i3 == 0) {
                    b.this.f14639g.clear();
                    b.this.a.r();
                    b.this.b.n(i2 + 1, c0706b.b);
                } else if (i3 == 91001) {
                    b.this.f14641i = false;
                    b.this.f14639g.clear();
                    b.this.a.r();
                    b.this.b.j(c0706b.f14593c);
                } else {
                    b.this.f14640h = true;
                    b.this.b.l(i2, c0706b.f14593c);
                    b.this.n(i2);
                }
            }
        }

        @Override // com.jingdong.moutaibuy.lib.f.b.c
        public void d(int i2, Exception exc) {
            if (i2 == b.this.f14636c && b.this.f14641i) {
                b.this.f14640h = true;
                b.this.n(i2);
            }
        }
    }

    public b(MouTaiScanView mouTaiScanView, com.jingdong.moutaibuy.lib.e.a aVar, int i2) {
        this.a = mouTaiScanView;
        this.b = aVar;
        this.d = i2;
    }

    private void o(String str) {
        com.jingdong.moutaibuy.lib.f.a.c(3, this.f14638f, str, new C0710b());
    }

    @Override // com.jingdong.moutaibuy.lib.e.b
    public void a(String str, String str2) {
        int i2 = this.f14636c;
        if (i2 == 0) {
            int indexOf = str.indexOf("?");
            String substring = str.substring(0, indexOf);
            String substring2 = substring.substring(substring.lastIndexOf("/") + 1, indexOf);
            this.b.o(substring2);
            com.jingdong.moutaibuy.lib.f.a.g(0, this.f14638f, str2, substring2, this);
        } else if (i2 == 1) {
            com.jingdong.moutaibuy.lib.f.a.d(1, this.f14638f, str2, this);
        } else if (i2 == 2) {
            com.jingdong.moutaibuy.lib.f.a.h(2, this.f14638f, str2, this);
        } else if (i2 == 3) {
            if (this.f14640h && this.f14642j) {
                this.f14640h = false;
                o(str2);
            }
        } else if (i2 == 4) {
            com.jingdong.moutaibuy.lib.f.a.e(4, this.f14638f, str2, str, this);
        } else if (i2 == 5) {
            com.jingdong.moutaibuy.lib.f.a.f(5, this.f14638f, str2, this);
        } else if (i2 == 6) {
            com.jingdong.moutaibuy.lib.f.a.a(6, this.f14638f, str2, this);
        } else if (i2 == 7) {
            com.jingdong.moutaibuy.lib.f.a.b(7, this.f14638f, str2, str, this);
        }
    }

    @Override // com.jingdong.moutaibuy.lib.f.b.c
    public void b(int i2, b.C0706b c0706b) {
        if (i2 == this.f14636c && this.f14641i) {
            if (c0706b == null) {
                n(i2);
            } else if (c0706b.a == 0) {
                this.b.n(i2 + 1, c0706b.b);
            } else {
                this.b.l(i2, c0706b.f14593c);
                n(i2);
            }
        }
    }

    @Override // com.jingdong.moutaibuy.lib.e.b
    public void c(String str) {
        long currentTimeMillis = System.currentTimeMillis() - this.f14637e;
        this.f14639g.addLast(str);
        if (this.f14639g.size() > 3) {
            this.f14639g.removeFirst();
        }
        if (currentTimeMillis <= this.d * 1000 || this.f14639g.size() != 3) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f14639g);
        this.f14639g.clear();
        com.jingdong.moutaibuy.lib.f.a.i(this.f14636c, this.f14638f, arrayList, new a());
        this.f14637e = System.currentTimeMillis();
    }

    @Override // com.jingdong.moutaibuy.lib.f.b.c
    public void d(int i2, Exception exc) {
        if (i2 == this.f14636c && this.f14641i) {
            n(i2);
        }
    }

    @Override // com.jingdong.moutaibuy.lib.e.b
    public void h() {
        this.b.h();
    }

    public void l(int i2) {
        this.f14636c = i2;
        switch (i2) {
            case 0:
                this.a.s(d.TWO_DIMENSION, null);
                this.a.p();
                return;
            case 1:
            case 2:
            case 5:
            case 6:
                this.a.o();
                return;
            case 3:
                this.a.n(200);
                return;
            case 4:
            case 7:
                this.a.s(d.ONE_DIMENSION, null);
                this.a.p();
                return;
            default:
                return;
        }
    }

    public void m() {
        this.f14641i = false;
    }

    public void n(int i2) {
        if (i2 == this.f14636c) {
            this.a.g();
        }
    }
}
