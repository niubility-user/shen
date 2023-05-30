package com.jingdong.sdk.jdupgrade.inner.ui;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.jingdong.sdk.jdupgrade.R;
import com.jingdong.sdk.jdupgrade.RemindType;
import com.jingdong.sdk.jdupgrade.UpgradeDialogPopupRequest;
import com.jingdong.sdk.jdupgrade.UpgradeEventListener;
import com.jingdong.sdk.jdupgrade.a.h.f;
import com.jingdong.sdk.jdupgrade.a.i.j;
import com.jingdong.sdk.jdupgrade.a.j.h;
import com.jingdong.sdk.jdupgrade.a.j.j;
import com.jingdong.sdk.jdupgrade.a.j.k;
import com.jingdong.sdk.jdupgrade.a.j.l;
import java.io.File;

/* loaded from: classes7.dex */
public class DownloadService extends Service {
    private static volatile int d;

    /* renamed from: e  reason: collision with root package name */
    private static volatile boolean f15114e;

    /* renamed from: f  reason: collision with root package name */
    private static volatile boolean f15115f;

    /* renamed from: g  reason: collision with root package name */
    private static volatile boolean f15116g;

    /* renamed from: h  reason: collision with root package name */
    private static volatile String f15117h;

    /* renamed from: i  reason: collision with root package name */
    private static j f15118i;
    private NotificationManagerCompat a;
    private NotificationCompat.Builder b;

    /* renamed from: c  reason: collision with root package name */
    private volatile f f15119c;

    /* loaded from: classes7.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ com.jingdong.sdk.jdupgrade.a.h.d f15120g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f15121h;

        /* renamed from: com.jingdong.sdk.jdupgrade.inner.ui.DownloadService$a$a  reason: collision with other inner class name */
        /* loaded from: classes7.dex */
        class C0727a implements j.e {
            C0727a() {
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void a(int i2) {
                h.a("DownloadService", "onRetry:" + i2);
                if (i2 > 1) {
                    DownloadService.this.b.setAutoCancel(true).setOngoing(false).setContentText(com.jingdong.sdk.jdupgrade.a.c.j().getString(R.string.upgrade_retrying));
                    DownloadService.this.a.notify(309, DownloadService.this.b.build());
                }
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void a(Throwable th, String str) {
                DownloadService.this.a(th, str);
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void onProgress(int i2, long j2, long j3) {
                DownloadService downloadService = DownloadService.this;
                int unused = DownloadService.d = i2;
                h.a("", "onProgress: " + i2 + ", read:" + j2 + ", total:" + j3);
                if (i2 % 5 != 0) {
                    return;
                }
                DownloadService.this.b.setContentTitle(com.jingdong.sdk.jdupgrade.a.c.j().getString(R.string.upgrade_downloading_progress, DownloadService.this.f15119c.f15070c.a, Integer.valueOf(i2), "%")).setProgress(100, i2, false).setContentText(com.jingdong.sdk.jdupgrade.a.c.j().getString(R.string.upgrade_downloading));
                DownloadService.this.a.notify(309, DownloadService.this.b.build());
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void onStart() {
                DownloadService.this.a.notify(309, DownloadService.this.b.build());
                UpgradeEventListener a = DownloadService.a();
                if (a != null) {
                    try {
                        a.onDownloadStart(false);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }

            @Override // com.jingdong.sdk.jdupgrade.a.j.j.e
            public void onSuccess(String str) {
                boolean unused = DownloadService.f15114e = false;
                String a = com.jingdong.sdk.jdupgrade.a.j.d.a(new File(str));
                if (TextUtils.equals(a, a.this.f15120g.f15060e)) {
                    boolean unused2 = DownloadService.f15115f = true;
                    boolean unused3 = DownloadService.f15116g = true;
                    String unused4 = DownloadService.f15117h = str;
                    DownloadService.this.f();
                    return;
                }
                com.jingdong.sdk.jdupgrade.a.j.d.a(str);
                DownloadService.this.a(new Exception("Md5 mismatch, serverMd5:" + a.this.f15120g.f15060e + ", localMd5:" + a), "3");
            }
        }

        a(com.jingdong.sdk.jdupgrade.a.h.d dVar, String str) {
            this.f15120g = dVar;
            this.f15121h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.sdk.jdupgrade.a.h.d dVar = this.f15120g;
            com.jingdong.sdk.jdupgrade.a.j.j.a(dVar.f15059c, this.f15121h, new C0727a(), true, dVar.f15060e, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ String f15123g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Throwable f15124h;

        b(String str, Throwable th) {
            this.f15123g = str;
            this.f15124h = th;
        }

        @Override // java.lang.Runnable
        public void run() {
            UpgradeEventListener a = DownloadService.a();
            if (a != null) {
                try {
                    String str = this.f15123g;
                    Throwable th = this.f15124h;
                    a.onMessage(str, th == null ? "" : th.getMessage());
                    a.onDownloadFinish(false);
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
            Context j2 = com.jingdong.sdk.jdupgrade.a.c.j();
            if (com.jingdong.sdk.jdupgrade.a.c.U()) {
                Toast.makeText(j2, j2.getString(R.string.upgrade_download_fail_no_retry) + "(" + this.f15123g + ")", 0).show();
            }
            String str2 = j2.getString(R.string.upgrade_download_fail) + "(" + this.f15123g + ")";
            Intent intent = new Intent(j2, DownloadService.class);
            intent.addFlags(268435456);
            intent.putExtra("upgradeInfo", DownloadService.this.f15119c);
            DownloadService.this.b.setAutoCancel(true).setOngoing(false).setContentText(str2).setContentIntent(PendingIntent.getService(j2, 0, intent, Build.VERSION.SDK_INT >= 31 ? 201326592 : 0));
            DownloadService.this.a.notify(309, DownloadService.this.b.build());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class c extends com.jingdong.sdk.jdupgrade.inner.ui.e {
        final /* synthetic */ String a;
        final /* synthetic */ UpgradeEventListener b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ f f15126c;

        c(DownloadService downloadService, String str, UpgradeEventListener upgradeEventListener, f fVar) {
            this.a = str;
            this.b = upgradeEventListener;
            this.f15126c = fVar;
        }

        @Override // com.jingdong.sdk.jdupgrade.inner.ui.e
        public void a() {
            com.jingdong.sdk.jdupgrade.a.j.f.a(this.a, this.b);
        }

        @Override // com.jingdong.sdk.jdupgrade.inner.ui.e
        public void a(boolean z) {
            if (z) {
                k.b("USER_REJECT_VERSION", this.f15126c.f15070c.a + "(O\ufe4f0)" + this.f15126c.f15070c.b);
            }
        }
    }

    /* loaded from: classes7.dex */
    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Context j2 = com.jingdong.sdk.jdupgrade.a.c.j();
            String str = j2.getString(R.string.upgrade_download_fail) + "(10)";
            Intent intent = new Intent(j2, DownloadService.class);
            intent.addFlags(268435456);
            intent.putExtra("upgradeInfo", DownloadService.this.f15119c);
            DownloadService.this.b.setAutoCancel(true).setOngoing(false).setContentText(str).setContentIntent(PendingIntent.getService(j2, 0, intent, Build.VERSION.SDK_INT >= 31 ? 201326592 : 0));
            DownloadService.this.a.notify(309, DownloadService.this.b.build());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public class e implements Runnable {
        e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            String string = com.jingdong.sdk.jdupgrade.a.c.j().getString(R.string.upgrade_downloading_progress, DownloadService.this.f15119c.f15070c.a, 100, "%");
            String string2 = com.jingdong.sdk.jdupgrade.a.c.j().getString(R.string.upgrade_download_finish_click);
            Intent a = com.jingdong.sdk.jdupgrade.a.j.f.a(DownloadService.f15117h);
            if (a == null) {
                DownloadService.this.a(new Exception("get installIntent error"), "8");
                return;
            }
            DownloadService.this.b.setAutoCancel(true).setOngoing(false).setContentTitle(string).setProgress(100, 100, false).setContentText(string2).setContentIntent(PendingIntent.getActivity(DownloadService.this, 0, a, Build.VERSION.SDK_INT >= 31 ? 201326592 : 0));
            DownloadService.this.a.notify(309, DownloadService.this.b.build());
            if (com.jingdong.sdk.jdupgrade.a.c.U()) {
                Toast.makeText(com.jingdong.sdk.jdupgrade.a.c.j(), R.string.upgrade_download_finish, 0).show();
            }
            UpgradeEventListener a2 = DownloadService.a();
            if (a2 != null) {
                try {
                    a2.onDownloadFinish(true);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            if (com.jingdong.sdk.jdupgrade.a.c.N()) {
                DownloadService.this.a(DownloadService.f15117h, DownloadService.a());
            }
        }
    }

    static /* synthetic */ UpgradeEventListener a() {
        return c();
    }

    public static void a(f fVar, com.jingdong.sdk.jdupgrade.a.i.j jVar) {
        if (f15114e) {
            return;
        }
        f15118i = jVar;
        Context j2 = com.jingdong.sdk.jdupgrade.a.c.j();
        Intent intent = new Intent(j2, DownloadService.class);
        intent.putExtra("upgradeInfo", fVar);
        j2.startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, UpgradeEventListener upgradeEventListener) {
        com.jingdong.sdk.jdupgrade.a.h.c cVar;
        UpgradeDialogPopupRequest r = com.jingdong.sdk.jdupgrade.a.c.r();
        if (r != null && !r.canPopupInstallDialog()) {
            h.c("showInstallRemindDialog", "install dialog can not pop");
            return;
        }
        f fVar = this.f15119c;
        if (fVar == null || fVar.f15070c == null || (cVar = fVar.f15071e) == null) {
            h.c("showInstallRemindDialog", "upgrade info null");
        } else {
            com.jingdong.sdk.jdupgrade.inner.ui.c.a(cVar, fVar.f15072f, new c(this, str, upgradeEventListener, fVar), cVar.a(), RemindType.INSTALL_REMIND, fVar.f15073g, fVar.a(), fVar.a, upgradeEventListener, com.jingdong.sdk.jdupgrade.a.c.a(f15118i));
        }
    }

    @RequiresApi(api = 26)
    private void a(String str, String str2, int i2) {
        NotificationChannel notificationChannel = new NotificationChannel(str, str2, i2);
        notificationChannel.enableLights(true);
        notificationChannel.setShowBadge(true);
        notificationChannel.enableVibration(false);
        notificationChannel.setSound(null, null);
        NotificationManager notificationManager = (NotificationManager) getSystemService(RemoteMessageConst.NOTIFICATION);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Throwable th, String str) {
        f15116g = false;
        f15114e = false;
        if (th != null) {
            th.printStackTrace();
        }
        System.err.println("DownloadService errorCode:" + str);
        l.a().a(new b(str, th));
    }

    private static UpgradeEventListener c() {
        com.jingdong.sdk.jdupgrade.a.i.j jVar = f15118i;
        if (jVar != null) {
            return jVar.d();
        }
        return null;
    }

    private void d() {
        try {
            this.a = com.jingdong.sdk.jdupgrade.a.c.j() != null ? NotificationManagerCompat.from(com.jingdong.sdk.jdupgrade.a.c.j()) : NotificationManagerCompat.from(this);
            h.c("DownloadService", "notificationEnable:" + this.a.areNotificationsEnabled());
            if (Build.VERSION.SDK_INT >= 26) {
                a("UpgradeNotification", getString(R.string.upgrade_download), 3);
            }
            this.b = new NotificationCompat.Builder(this, "UpgradeNotification");
            String string = com.jingdong.sdk.jdupgrade.a.c.j().getString(R.string.upgrade_download_start);
            String string2 = com.jingdong.sdk.jdupgrade.a.c.j().getString(R.string.upgrade_download_connecting);
            Integer w = com.jingdong.sdk.jdupgrade.a.c.w();
            this.a.notify(309, this.b.setContentTitle(string).setContentText(string2).setProgress(100, d, false).setOngoing(true).setLargeIcon(com.jingdong.sdk.jdupgrade.a.j.b.a(w.intValue())).setSmallIcon(w.intValue()).setPriority(2).setCategory(NotificationCompat.CATEGORY_PROGRESS).build());
        } catch (Throwable th) {
            h.b("", "initNotification error," + th.getMessage());
            th.printStackTrace();
        }
    }

    public static boolean e() {
        return f15114e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        h.a("DownloadService", "to install from file:" + f15117h);
        if (TextUtils.isEmpty(f15117h)) {
            return;
        }
        f15116g = false;
        l.a().a(new e());
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        h.a("DownloadService", "onCreate: ");
        if (com.jingdong.sdk.jdupgrade.a.c.U()) {
            Toast.makeText(this, R.string.upgrade_download_start, 0).show();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        h.a("DownloadService", "onDestroy downloadSuccess:" + f15115f + ", upgradeInfo:" + this.f15119c + ", progress:" + d + ",downloading:" + f15114e);
        if (f15116g) {
            f();
        } else if (!f15115f && this.f15119c != null && d < 100) {
            try {
                l.a().a(new d());
            } catch (Throwable th) {
                h.b("DownloadService", th.getMessage());
            }
        }
        f15114e = false;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        if (intent == null) {
            return 2;
        }
        try {
            this.f15119c = (f) intent.getParcelableExtra("upgradeInfo");
            h.a("DownloadService", "onHandleIntent: " + this.f15119c);
        } catch (Throwable unused) {
        }
        if (this.f15119c == null) {
            f15114e = false;
            return 2;
        }
        com.jingdong.sdk.jdupgrade.a.h.d dVar = this.f15119c.f15070c;
        if (dVar != null && dVar.a()) {
            File s = com.jingdong.sdk.jdupgrade.a.c.s();
            if (s == null) {
                f15114e = false;
                a(new Exception("DownloadService onHandleIntent dir is null"), "6");
                return 2;
            }
            d();
            f15114e = true;
            String str = s.getAbsolutePath() + File.separator + com.jingdong.sdk.jdupgrade.a.c.a(dVar.f15060e);
            com.jingdong.sdk.jdupgrade.a.j.j.f();
            com.jingdong.sdk.jdupgrade.a.j.j.c().execute(new a(dVar, str));
            return 2;
        }
        f15114e = false;
        a(new Exception("DownloadService onHandleIntent packageEntity is invalid"), "7");
        return 2;
    }
}
