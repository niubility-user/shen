package com.jingdong.manto.launch;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.manto.launch.MantoPreLaunchProcess;
import com.jingdong.manto.launch.d;
import com.jingdong.manto.pkg.PkgManager;
import com.jingdong.manto.pkg.db.entity.PkgDetailEntity;
import com.jingdong.manto.utils.MantoStringUtils;
import com.jingdong.manto.utils.f0;
import com.jingdong.manto.utils.s;
import java.io.File;
import java.util.List;

/* loaded from: classes15.dex */
public class h implements Runnable {
    public PkgDetailEntity a;
    public com.jingdong.manto.i.c b;

    /* renamed from: c  reason: collision with root package name */
    public volatile e f13254c;
    public volatile f d;

    /* renamed from: e  reason: collision with root package name */
    public volatile c f13255e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f13256f;

    /* renamed from: g  reason: collision with root package name */
    public String f13257g;

    /* loaded from: classes15.dex */
    class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ File f13258c;
        final /* synthetic */ String d;

        a(String str, boolean z, File file, String str2) {
            this.a = str;
            this.b = z;
            this.f13258c = file;
            this.d = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            h hVar = h.this;
            new d(this.a, false, this.b, this.f13258c, null, this.d, hVar.f13256f).a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ String f13260c;
        final /* synthetic */ File d;

        /* renamed from: e  reason: collision with root package name */
        final /* synthetic */ String f13261e;

        /* loaded from: classes15.dex */
        class a implements d.b {
            a() {
            }

            @Override // com.jingdong.manto.launch.d.b
            public void a() {
                if (h.this.f13255e != null) {
                    h.this.f13255e.a();
                }
            }

            @Override // com.jingdong.manto.launch.d.b
            public void b() {
                if (h.this.f13255e != null) {
                    h.this.f13255e.b();
                }
            }
        }

        /* renamed from: com.jingdong.manto.launch.h$b$b  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        class RunnableC0540b implements Runnable {
            final /* synthetic */ d.b a;

            RunnableC0540b(d.b bVar) {
                this.a = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                d.b bVar = this.a;
                b bVar2 = b.this;
                new com.jingdong.manto.launch.d(bVar, bVar2.f13260c, false, true, bVar2.d, null, bVar2.f13261e, false).a();
            }
        }

        b(String str, String str2, String str3, File file, String str4) {
            this.a = str;
            this.b = str2;
            this.f13260c = str3;
            this.d = file;
            this.f13261e = str4;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (h.this.f13255e != null) {
                h.this.f13255e.a(true);
            }
            a aVar = new a();
            (!MantoStringUtils.isEmpty(this.a) ? new com.jingdong.manto.launch.d(aVar, this.a, true, true, new File(this.b), new RunnableC0540b(aVar), this.f13261e, false) : new com.jingdong.manto.launch.d(aVar, this.f13260c, false, true, this.d, null, this.f13261e, false)).a();
        }
    }

    /* loaded from: classes15.dex */
    public interface c {
        void a();

        void a(boolean z);

        void b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes15.dex */
    public class d {
        private final String a;
        private final boolean b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f13263c;
        private final File d;

        /* renamed from: e  reason: collision with root package name */
        private final Runnable f13264e;

        /* renamed from: f  reason: collision with root package name */
        private final String f13265f;

        /* renamed from: g  reason: collision with root package name */
        private final boolean f13266g;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes15.dex */
        public class a extends com.jingdong.manto.network.common.b {
            long a = System.currentTimeMillis();
            final /* synthetic */ long b;

            a(long j2) {
                this.b = j2;
            }

            private void a() {
                if (d.this.b && d.this.f13264e != null) {
                    d.this.f13264e.run();
                }
                s.b(d.this.d);
            }

            private void a(File file) {
                s.b(file);
                MantoPreLaunchProcess.LaunchError launchError = new MantoPreLaunchProcess.LaunchError();
                launchError.errorCode = PkgDetailEntity.OPEN_ERROR;
                launchError.msg = "\u6253\u5f00\u5c0f\u7a0b\u5e8f\u51fa\u9519";
                launchError.word = "\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5";
                launchError.title = "\u8fd4\u56de";
                if (h.this.f13254c != null) {
                    h.this.f13254c.a(launchError);
                }
            }

            private void b(File file) {
                PkgManager.delOldPkg(file);
                if (h.this.f13254c != null) {
                    h.this.f13254c.a(true);
                }
            }

            @Override // com.jingdong.manto.network.common.b
            public void a(long j2, long j3, boolean z) {
                super.a(j2, j3, z);
                if (h.this.f13254c == null || !com.jingdong.a.a) {
                    return;
                }
                if (System.currentTimeMillis() - this.a >= 150 || j2 == j3) {
                    h.this.f13254c.a(j2, j3, z);
                    this.a = System.currentTimeMillis();
                }
            }

            @Override // com.jingdong.manto.network.common.b
            public void a(com.jingdong.manto.network.mantorequests.b bVar) {
                if (h.this.d != null) {
                    h.this.d.a(System.currentTimeMillis() - this.b);
                }
                if (!d.this.b) {
                    d dVar = d.this;
                    if (h.this.a(dVar.d.getAbsolutePath())) {
                        b(d.this.d);
                        return;
                    } else {
                        a(d.this.d);
                        return;
                    }
                }
                long currentTimeMillis = System.currentTimeMillis();
                List<File> a = f0.a(d.this.d, d.this.d.getParent(), true);
                if (h.this.d != null) {
                    h.this.d.c(System.currentTimeMillis() - currentTimeMillis);
                }
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
                    if (file != null && h.this.a(file.getAbsolutePath())) {
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
                    return;
                }
                MantoPreLaunchProcess.LaunchError launchError = new MantoPreLaunchProcess.LaunchError();
                launchError.errorCode = PkgDetailEntity.DOWNLOAD_ERROR;
                launchError.msg = "\u4e0b\u8f7d\u5c0f\u7a0b\u5e8f\u51fa\u9519";
                launchError.word = "\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5";
                launchError.title = "\u8fd4\u56de";
                if (h.this.f13254c != null) {
                    h.this.f13254c.a(launchError);
                }
            }
        }

        public d(String str, boolean z, boolean z2, File file, Runnable runnable, String str2, boolean z3) {
            this.a = str;
            this.b = z;
            this.d = file;
            this.f13264e = runnable;
            this.f13263c = z2;
            this.f13265f = str2;
            this.f13266g = z3;
        }

        public void a() {
            com.jingdong.manto.network.common.c.a(new com.jingdong.manto.network.mantorequests.a(this.a, this.d.getParent(), this.d.getName(), true), this.f13263c, this.f13265f, this.f13266g, new a(System.currentTimeMillis()));
        }
    }

    /* loaded from: classes15.dex */
    public interface e {
        void a(long j2, long j3, boolean z);

        void a(MantoPreLaunchProcess.LaunchError launchError);

        void a(PkgDetailEntity pkgDetailEntity);

        void a(boolean z);
    }

    /* loaded from: classes15.dex */
    public interface f {
        void a(long j2);

        void b(long j2);

        void c(long j2);
    }

    public h(PkgDetailEntity pkgDetailEntity, com.jingdong.manto.i.c cVar, String str, boolean z) {
        this.a = pkgDetailEntity;
        this.b = cVar;
        this.f13257g = str;
        this.f13256f = z;
    }

    public h(PkgDetailEntity pkgDetailEntity, com.jingdong.manto.i.c cVar, boolean z) {
        this.a = pkgDetailEntity;
        this.b = cVar;
        this.f13256f = z;
    }

    private boolean a(PkgDetailEntity pkgDetailEntity, String str, String str2, String str3, String str4) {
        String pkgPath = PkgManager.getPkgPath(pkgDetailEntity);
        if (TextUtils.isEmpty(pkgPath)) {
            return false;
        }
        File file = new File(pkgPath);
        if (file.exists() && a(file.getAbsolutePath())) {
            if (this.f13254c != null) {
                this.f13254c.a(pkgDetailEntity);
            }
            com.jingdong.manto.b.d().networkIO().execute(new b(str, str2, str3, file, str4));
            return true;
        }
        return false;
    }

    protected boolean a(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        com.jingdong.manto.pkg.b.e eVar = new com.jingdong.manto.pkg.b.e(str);
        if (this.d != null) {
            this.d.b(System.currentTimeMillis() - currentTimeMillis);
        }
        return eVar.f13979c;
    }

    @Override // java.lang.Runnable
    public void run() {
        String pkgPath;
        String str;
        String str2;
        String str3;
        boolean z;
        if (!TextUtils.isEmpty(this.b.f13088k)) {
            if (this.f13254c != null) {
                this.f13254c.a(false);
                return;
            }
            return;
        }
        String str4 = this.a.appId;
        if (TextUtils.isEmpty(this.f13257g)) {
            PkgDetailEntity pkgDetailEntity = this.a;
            pkgPath = PkgManager.getPkgPath(pkgDetailEntity);
            String pkgZipPath = PkgManager.getPkgZipPath(pkgDetailEntity);
            String str5 = pkgDetailEntity.pkgUrl;
            str = pkgDetailEntity.zipUrl;
            str2 = pkgZipPath;
            str3 = str5;
            z = false;
        } else {
            PkgManager.l subPkg = PkgManager.getSubPkg(this.a, this.f13257g);
            if (subPkg == null) {
                MantoPreLaunchProcess.LaunchError launchError = new MantoPreLaunchProcess.LaunchError();
                launchError.errorCode = PkgDetailEntity.SUB_NO_INFO;
                launchError.msg = "\u4e0b\u8f7d\u5b50\u5305\u51fa\u9519";
                launchError.word = "\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5";
                launchError.title = "\u8fd4\u56de";
                if (this.f13254c != null) {
                    this.f13254c.a(launchError);
                    return;
                }
                return;
            }
            pkgPath = PkgManager.getPkgPath(this.a, subPkg.a);
            String pkgZipPath2 = PkgManager.getPkgZipPath(this.a, subPkg.a);
            String str6 = subPkg.b;
            str = subPkg.f13971c;
            str2 = pkgZipPath2;
            str3 = str6;
            z = true;
        }
        if (TextUtils.isEmpty(pkgPath)) {
            MantoPreLaunchProcess.LaunchError launchError2 = new MantoPreLaunchProcess.LaunchError();
            launchError2.errorCode = PkgDetailEntity.NO_INFO;
            launchError2.msg = "\u6682\u65f6\u65e0\u6cd5\u83b7\u53d6\u5c0f\u7a0b\u5e8f\u4fe1\u606f";
            launchError2.word = "\u8bf7\u67e5\u770b\u7f51\u7edc\u94fe\u63a5\u60c5\u51b5\uff0c\u5e76\u4f7f\u7528\u7cfb\u7edf\u9ed8\u8ba4\u65f6\u95f4\u540e\u518d\u6b21\u5c1d\u8bd5";
            launchError2.title = "\u8fd4\u56de";
            if (this.f13254c != null) {
                this.f13254c.a(launchError2);
                return;
            }
            return;
        }
        boolean z2 = TextUtils.equals("13", this.a.type) || TextUtils.equals("13", this.b.f13082e);
        boolean z3 = TextUtils.equals("5", this.a.type) || TextUtils.equals("5", this.b.f13082e);
        File file = new File(pkgPath);
        if (z2 || !file.exists() || !file.isFile() || z3) {
            boolean z4 = z2 || z3;
            if (com.jingdong.manto.launch.b.c()) {
                PkgDetailEntity pkgDetailEntity2 = this.b.f13087j;
                if ((this.f13256f || z || z4 || com.jingdong.manto.launch.b.a(this.b) || !com.jingdong.manto.launch.b.a(this.a) || pkgDetailEntity2 == null) ? false : true ? a(pkgDetailEntity2, str, str2, str3, str4) : false) {
                    return;
                }
            }
            (!MantoStringUtils.isEmpty(str) ? new d(str, true, z4, new File(str2), new a(str3, z4, file, str4), str4, this.f13256f) : new d(str3, false, z4, file, null, str4, this.f13256f)).a();
        } else if (a(file.getAbsolutePath())) {
            if (this.f13254c != null) {
                this.f13254c.a(false);
            }
        } else {
            s.b(file);
            MantoPreLaunchProcess.LaunchError launchError3 = new MantoPreLaunchProcess.LaunchError();
            launchError3.errorCode = PkgDetailEntity.OPEN_ERROR;
            launchError3.msg = "\u65e0\u6cd5\u6253\u5f00\u5c0f\u7a0b\u5e8f";
            launchError3.word = "\u8bf7\u68c0\u67e5\u60a8\u7684\u7f51\u7edc\u8bbe\u7f6e\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5";
            launchError3.title = "\u8fd4\u56de";
            if (this.f13254c != null) {
                this.f13254c.a(launchError3);
            }
        }
    }
}
