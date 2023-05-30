package com.jingdong.manto.launch;

import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.manto.utils.f0;
import com.jingdong.manto.utils.s;
import java.io.File;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes15.dex */
public class d {
    private final String a;
    private final boolean b;

    /* renamed from: c  reason: collision with root package name */
    private final boolean f13246c;
    private final File d;

    /* renamed from: e  reason: collision with root package name */
    private final Runnable f13247e;

    /* renamed from: f  reason: collision with root package name */
    private final String f13248f;

    /* renamed from: g  reason: collision with root package name */
    private final boolean f13249g;

    /* renamed from: h  reason: collision with root package name */
    private b f13250h;

    /* loaded from: classes15.dex */
    class a extends com.jingdong.manto.network.common.b {
        a() {
        }

        private void a() {
            if (d.this.b && d.this.f13247e != null) {
                d.this.f13247e.run();
            } else if (d.this.f13250h != null) {
                d.this.f13250h.b();
            }
            s.b(d.this.d);
        }

        private void a(File file) {
            s.b(file);
            if (d.this.f13250h != null) {
                d.this.f13250h.b();
            }
        }

        private void b(File file) {
            if (d.this.f13250h != null) {
                d.this.f13250h.a();
            }
        }

        @Override // com.jingdong.manto.network.common.b
        public void a(long j2, long j3, boolean z) {
            super.a(j2, j3, z);
        }

        @Override // com.jingdong.manto.network.common.b
        public void a(com.jingdong.manto.network.mantorequests.b bVar) {
            if (!d.this.b) {
                d dVar = d.this;
                if (dVar.a(dVar.d.getAbsolutePath())) {
                    b(d.this.d);
                    return;
                } else {
                    a(d.this.d);
                    return;
                }
            }
            List<File> a = f0.a(d.this.d, d.this.d.getParent(), true);
            if (a != null && a.size() > 0) {
                File file = null;
                int i2 = 0;
                while (true) {
                    if (i2 >= a.size()) {
                        break;
                    }
                    File file2 = a.get(i2);
                    if (file2.getName().endsWith(".jdapkg")) {
                        file = new File(d.this.d.getParent(), d.this.d.getName().substring(0, d.this.d.getName().lastIndexOf(OrderISVUtil.MONEY_DECIMAL)) + OrderISVUtil.MONEY_DECIMAL + "jdapkg");
                        file2.renameTo(file);
                        break;
                    }
                    i2++;
                }
                if (file != null && d.this.a(file.getAbsolutePath())) {
                    b(file);
                    return;
                }
            }
            a();
        }

        @Override // com.jingdong.manto.network.common.b
        public void a(Throwable th) {
            super.a(th);
            if (d.this.b) {
                a();
            } else if (d.this.f13250h != null) {
                d.this.f13250h.b();
            }
        }
    }

    /* loaded from: classes15.dex */
    public interface b {
        void a();

        void b();
    }

    public d(b bVar, String str, boolean z, boolean z2, File file, Runnable runnable, String str2, boolean z3) {
        this.f13250h = bVar;
        this.a = str;
        this.b = z;
        this.d = file;
        this.f13247e = runnable;
        this.f13246c = z2;
        this.f13248f = str2;
        this.f13249g = z3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        return new com.jingdong.manto.pkg.b.e(str).f13979c;
    }

    public void a() {
        com.jingdong.manto.network.common.c.a(new com.jingdong.manto.network.mantorequests.a(this.a, this.d.getParent(), this.d.getName(), true), this.f13246c, this.f13248f, this.f13249g, new a());
    }
}
