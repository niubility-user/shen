package com.jingdong.sdk.jdupgrade;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.jingdong.sdk.jdupgrade.a.j.h;
import com.jingdong.sdk.jdupgrade.a.j.j;
import com.jingdong.sdk.jdupgrade.a.j.l;
import java.io.File;

/* loaded from: classes7.dex */
public abstract class DownloadView implements com.jingdong.sdk.jdupgrade.a.b {
    private static volatile boolean downloading;
    private com.jingdong.sdk.jdupgrade.a.h.f upgradeInfo;

    /* loaded from: classes7.dex */
    public class a implements Runnable {

        /* renamed from: g */
        final /* synthetic */ boolean f15026g;

        a(boolean z) {
            DownloadView.this = r1;
            this.f15026g = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            UpgradeEventListener d = com.jingdong.sdk.jdupgrade.inner.ui.c.f15146j.d();
            if (d != null) {
                try {
                    d.onDownloadStart(this.f15026g);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            DownloadView.this.onDownloadStart();
        }
    }

    /* loaded from: classes7.dex */
    public class b implements Runnable {

        /* renamed from: g */
        final /* synthetic */ int f15028g;

        b(int i2) {
            DownloadView.this = r1;
            this.f15028g = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            DownloadView.this.onDownloadRetry(this.f15028g);
        }
    }

    /* loaded from: classes7.dex */
    public class c implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f15030g;

        c(String str) {
            DownloadView.this = r1;
            this.f15030g = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            UpgradeEventListener d = com.jingdong.sdk.jdupgrade.inner.ui.c.f15146j.d();
            if (d != null) {
                try {
                    d.onDownloadFinish(true);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            DownloadView.this.onDownloadSuccess(this.f15030g);
        }
    }

    /* loaded from: classes7.dex */
    public class d implements Runnable {

        /* renamed from: g */
        final /* synthetic */ String f15032g;

        /* renamed from: h */
        final /* synthetic */ Throwable f15033h;

        d(String str, Throwable th) {
            DownloadView.this = r1;
            this.f15032g = str;
            this.f15033h = th;
        }

        @Override // java.lang.Runnable
        public void run() {
            UpgradeEventListener d = com.jingdong.sdk.jdupgrade.inner.ui.c.f15146j.d();
            if (d != null) {
                try {
                    d.onDownloadFinish(false);
                    String str = this.f15032g;
                    Throwable th = this.f15033h;
                    d.onMessage(str, th == null ? "" : th.getMessage());
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
            DownloadView.this.onDownloadError(this.f15032g);
        }
    }

    /* loaded from: classes7.dex */
    public class e implements Runnable {

        /* renamed from: g */
        final /* synthetic */ int f15035g;

        e(int i2) {
            DownloadView.this = r1;
            this.f15035g = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            DownloadView.this.onDownloadProgress(this.f15035g);
        }
    }

    /* loaded from: classes7.dex */
    public class f extends com.jingdong.sdk.jdupgrade.inner.ui.e {
        final /* synthetic */ String a;

        f(DownloadView downloadView, String str) {
            this.a = str;
        }

        @Override // com.jingdong.sdk.jdupgrade.inner.ui.e
        public void a() {
            com.jingdong.sdk.jdupgrade.a.j.f.a(this.a, com.jingdong.sdk.jdupgrade.inner.ui.c.f15146j.d());
        }
    }

    /* loaded from: classes7.dex */
    public class g implements Runnable {

        /* renamed from: g */
        final /* synthetic */ boolean f15037g;

        /* renamed from: h */
        final /* synthetic */ String f15038h;

        /* loaded from: classes7.dex */
        class a implements j.e {
            a() {
                g.this = r1;
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void a(int i2) {
                if (i2 > 1) {
                    DownloadView.this.callRetry(i2);
                }
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void a(Throwable th, String str) {
                DownloadView.this.callError(th, str);
                boolean unused = DownloadView.downloading = false;
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void onProgress(int i2, long j2, long j3) {
                DownloadView.this.callProgress(i2);
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void onStart() {
                boolean unused = DownloadView.downloading = true;
                g gVar = g.this;
                DownloadView.this.callStart(gVar.f15037g);
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void onSuccess(String str) {
                String a = com.jingdong.sdk.jdupgrade.a.j.d.a(new File(str));
                if (TextUtils.equals(a, DownloadView.this.upgradeInfo.f15070c.f15060e)) {
                    DownloadView.this.callSuccess(str);
                } else {
                    DownloadView.this.callError(new Exception("Md5 mismatch, serverMd5:" + DownloadView.this.upgradeInfo.f15070c.f15060e + ", localMd5:" + a), "3");
                    com.jingdong.sdk.jdupgrade.a.j.d.a(str);
                }
                boolean unused = DownloadView.downloading = false;
            }
        }

        g(boolean z, String str) {
            DownloadView.this = r1;
            this.f15037g = z;
            this.f15038h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean unused = DownloadView.downloading = true;
            try {
                if (this.f15037g) {
                    j.f();
                }
                j.a(DownloadView.this.upgradeInfo.f15070c.f15059c, this.f15038h, new a(), false, DownloadView.this.upgradeInfo.f15070c.f15060e, false);
            } catch (Throwable th) {
                DownloadView.this.callError(th, "5");
                boolean unused2 = DownloadView.downloading = false;
            }
        }
    }

    public void callError(Throwable th, String str) {
        if (th != null) {
            th.printStackTrace();
        }
        h.b("DownloadView", "errorCode:" + str);
        downloading = false;
        l.a().a(new d(str, th));
    }

    public void callProgress(int i2) {
        l.a().a(new e(i2));
    }

    public void callRetry(int i2) {
        l.a().a(new b(i2));
    }

    public void callStart(boolean z) {
        l.a().a(new a(z));
    }

    public void callSuccess(String str) {
        l.a().a(new c(str));
    }

    private void download(boolean z) {
        com.jingdong.sdk.jdupgrade.a.h.d dVar;
        if (downloading) {
            return;
        }
        com.jingdong.sdk.jdupgrade.a.h.f fVar = this.upgradeInfo;
        if (fVar == null || (dVar = fVar.f15070c) == null || TextUtils.isEmpty(dVar.f15059c)) {
            callError(new RuntimeException("upgradeInfo is null"), "4");
            return;
        }
        File s = com.jingdong.sdk.jdupgrade.a.c.s();
        if (s == null) {
            callError(new RuntimeException("download dir is null"), "6");
            return;
        }
        j.c().execute(new g(z, s.getAbsolutePath() + File.separator + com.jingdong.sdk.jdupgrade.a.c.a(this.upgradeInfo.f15070c.f15060e)));
    }

    public static boolean isDownloading() {
        return downloading;
    }

    public final void install(String str) {
        UpgradeDialogPopupRequest r;
        com.jingdong.sdk.jdupgrade.inner.ui.c.f15146j.b(false);
        if (!this.upgradeInfo.a() && (r = com.jingdong.sdk.jdupgrade.a.c.r()) != null && !r.canPopupInstallDialog()) {
            if (com.jingdong.sdk.jdupgrade.inner.ui.c.f15146j.d() != null) {
                try {
                    com.jingdong.sdk.jdupgrade.inner.ui.c.f15146j.d().onMessage("11", "install dialog can not pop");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            h.c("DownloadView", "install dialog can not pop");
            return;
        }
        f fVar = new f(this, str);
        com.jingdong.sdk.jdupgrade.a.h.f fVar2 = this.upgradeInfo;
        com.jingdong.sdk.jdupgrade.a.h.c cVar = fVar2.f15071e;
        String str2 = fVar2.f15072f;
        boolean a2 = cVar.a();
        RemindType remindType = RemindType.INSTALL_REMIND;
        com.jingdong.sdk.jdupgrade.a.h.f fVar3 = this.upgradeInfo;
        com.jingdong.sdk.jdupgrade.inner.ui.c.a(cVar, str2, fVar, a2, remindType, fVar3.f15073g, fVar3.a(), this.upgradeInfo.a, com.jingdong.sdk.jdupgrade.inner.ui.c.f15146j.d(), com.jingdong.sdk.jdupgrade.inner.ui.c.f15146j.j());
    }

    public abstract /* synthetic */ boolean isInstallView();

    @Override // com.jingdong.sdk.jdupgrade.a.b
    public final void onAttach() {
        this.upgradeInfo = com.jingdong.sdk.jdupgrade.inner.ui.c.f15146j.h();
    }

    @Override // com.jingdong.sdk.jdupgrade.a.b
    public abstract View onCreateView(Context context);

    @Override // com.jingdong.sdk.jdupgrade.a.b
    public final void onDetach() {
        if (com.jingdong.sdk.jdupgrade.a.j.b.b()) {
            return;
        }
        downloading = false;
    }

    public abstract void onDownloadError(String str);

    public abstract void onDownloadProgress(int i2);

    public abstract void onDownloadRetry(int i2);

    public abstract void onDownloadStart();

    public abstract void onDownloadSuccess(String str);

    @Override // com.jingdong.sdk.jdupgrade.a.b
    public final void onResume() {
        download(false);
    }

    public final void retry() {
        download(true);
    }
}
