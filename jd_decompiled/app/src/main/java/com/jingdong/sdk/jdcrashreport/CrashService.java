package com.jingdong.sdk.jdcrashreport;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import com.jingdong.sdk.jdcrashreport.b.j;
import com.jingdong.sdk.jdcrashreport.b.o;
import com.jingdong.sdk.jdcrashreport.b.p;
import com.jingdong.sdk.jdcrashreport.b.r;
import com.jingdong.sdk.jdcrashreport.b.x;
import com.jingdong.sdk.jdcrashreport.common.CrashInfo;
import java.io.File;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class CrashService extends Service {
    private Handler a;
    private b b;

    /* renamed from: c */
    private IBinder f14829c;

    /* loaded from: classes7.dex */
    public static class a extends Binder {
        CrashService a;

        a(CrashService crashService) {
            this.a = crashService;
        }

        public void a(String str) {
            this.a.b.a(str);
            this.a.a.post(this.a.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class b implements Runnable {

        /* renamed from: g */
        CrashInfo f14830g;

        /* renamed from: h */
        String f14831h;

        /* renamed from: i */
        boolean f14832i = false;

        /* renamed from: j */
        boolean f14833j;

        /* loaded from: classes7.dex */
        class a implements JDCrashReportListener {
            a() {
                b.this = r1;
            }

            @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
            public void onEnd(int i2, String str, CrashInfo crashInfo) {
                r.h("JDCrashReport", "CrashService onEnd: code is " + i2 + ", msg is " + str);
                o.b(new File(j.b(), String.format(Locale.getDefault(), "crash_info_%s_%d.txt", crashInfo.busiType, Long.valueOf(x.a(crashInfo.crashTime)))));
                com.jingdong.sdk.jdcrashreport.b.c.o();
            }

            @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
            public void onError(int i2, String str, CrashInfo crashInfo) {
                r.h("JDCrashReport", "CrashService onError: code is " + i2 + ", msg is " + str);
                File file = new File(j.b(), String.format(Locale.getDefault(), "crash_info_%s_%d.txt", crashInfo.busiType, Long.valueOf(x.a(crashInfo.crashTime))));
                if (!file.exists()) {
                    j.d(file, crashInfo);
                }
                com.jingdong.sdk.jdcrashreport.b.c.o();
            }

            @Override // com.jingdong.sdk.jdcrashreport.JDCrashReportListener
            public void onStart(CrashInfo crashInfo) {
                r.h("JDCrashReport", "CrashService onStart");
                if (d.S()) {
                    return;
                }
                o.c(b.this.f14831h);
            }
        }

        b(CrashInfo crashInfo, String str, boolean z) {
            this.f14830g = crashInfo;
            this.f14831h = str;
            this.f14833j = z;
        }

        void a(String str) {
            this.f14832i = true;
            this.f14830g.userErrorMsg = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f14833j) {
                r.b("JDCrashReport", "downgrade is enabled, not report crash");
                return;
            }
            try {
                r.h("JDCrashReport", "UploadTask run");
                if (this.f14832i) {
                    r.h("JDCrashReport", "UploadTask fromRecoverActivity");
                }
                j.c(this.f14830g, new a());
            } catch (Exception e2) {
                com.jingdong.sdk.jdcrashreport.b.c.o();
                r.c("JDCrashReport", "An exception happens when UploadTask run", e2);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        r.b("JDCrashReport", "CrashService onBind()");
        this.a.removeCallbacks(this.b);
        return this.f14829c;
    }

    @Override // android.app.Service
    public void onCreate() {
        r.b("JDCrashReport", "CrashService onCreate()");
        super.onCreate();
        this.f14829c = new a(this);
        HandlerThread handlerThread = new HandlerThread("UPLOAD");
        handlerThread.start();
        this.a = new Handler(handlerThread.getLooper());
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        try {
            r.b("JDCrashReport", "CrashService onStartCommand()");
            r.b("JDCrashReport", "from " + String.valueOf(intent.getStringExtra("from")));
            boolean booleanExtra = intent.getBooleanExtra("IS_RECOVER", false);
            r.b("JDCrashReport", "CrashService isRecover=" + booleanExtra);
            String stringExtra = intent.getStringExtra("logPath");
            JDCrashReportConfig jDCrashReportConfig = (JDCrashReportConfig) intent.getSerializableExtra("config");
            boolean booleanExtra2 = intent.getBooleanExtra("downgradeEnabled", false);
            r.b("JDCrashReport", "CrashService downgradeEnabled=" + booleanExtra2);
            if (!d.w()) {
                r.b("JDCrashReport", "CrashService init InnerApi");
                jDCrashReportConfig.setApplicationContext(getApplicationContext());
                d.n(jDCrashReportConfig);
            } else {
                r.b("JDCrashReport", "CrashService update config");
                d.y(jDCrashReportConfig.getUts());
                d.B(jDCrashReportConfig.getDeviceUniqueId());
                d.q(jDCrashReportConfig.getUserId());
            }
            b bVar = new b(CrashInfo.parse(new JSONObject(p.b(intent.getStringExtra("crashInfo")))), stringExtra, booleanExtra2);
            this.b = bVar;
            if (booleanExtra) {
                this.a.postDelayed(bVar, 3000L);
                return 2;
            }
            this.a.post(bVar);
            return 2;
        } catch (Throwable th) {
            r.c("JDCrashReport", "CrashService", th);
            return 2;
        }
    }
}
