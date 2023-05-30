package com.jingdong.sdk.jdcrashreport.recover;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.jingdong.sdk.jdcrashreport.CrashService;
import com.jingdong.sdk.jdcrashreport.JDCrashReportConfig;
import com.jingdong.sdk.jdcrashreport.b.r;
import java.util.Iterator;

/* loaded from: classes7.dex */
public class RecoverActivity extends Activity {

    /* renamed from: g  reason: collision with root package name */
    private com.jingdong.sdk.jdcrashreport.recover.b f14950g;

    /* renamed from: h  reason: collision with root package name */
    private CrashService.a f14951h;

    /* renamed from: i  reason: collision with root package name */
    private ServiceConnection f14952i;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public class b implements ServiceConnection {
        private b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            RecoverActivity.this.f14951h = (CrashService.a) iBinder;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    private void b() {
        r.b("JDCrashReport", "RecoverActivity tryBind()");
        this.f14952i = new b();
        bindService(new Intent(this, CrashService.class), this.f14952i, 4);
    }

    public final void c(String str, com.jingdong.sdk.jdcrashreport.recover.a aVar) {
        r.b("JDCrashReport", "RecoverActivity submit, mode: " + aVar);
        try {
            CrashService.a aVar2 = this.f14951h;
            if (aVar2 != null && aVar2.isBinderAlive()) {
                this.f14951h.a(str);
            }
        } catch (Throwable unused) {
        }
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("crashType");
        if (!TextUtils.isEmpty(stringExtra) && com.jingdong.sdk.jdcrashreport.d.R() != null && com.jingdong.sdk.jdcrashreport.d.R().size() > 0) {
            Iterator<String> it = com.jingdong.sdk.jdcrashreport.d.R().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (!TextUtils.isEmpty(next) && stringExtra.startsWith(next)) {
                    aVar = com.jingdong.sdk.jdcrashreport.recover.a.NONE;
                    r.b("JDCrashReport", "recover ignore exception: " + next);
                    break;
                }
            }
        }
        Intent[] intentArr = new Intent[0];
        if (intent != null) {
            try {
                if (intent.hasExtra("RECOVER_INTENTS")) {
                    intentArr = (Intent[]) intent.getParcelableArrayListExtra("RECOVER_INTENTS").toArray(new Intent[0]);
                }
            } catch (Throwable unused2) {
            }
        }
        r.b("JDCrashReport", "RecoverActivity recovery mode: " + aVar);
        com.jingdong.sdk.jdcrashreport.b.c.g(com.jingdong.sdk.jdcrashreport.d.I(), aVar, intentArr);
    }

    public final boolean d(int i2, KeyEvent keyEvent) {
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        r.b("JDCrashReport", "RecoverActivity onCreate()");
        super.onCreate(bundle);
        JDCrashReportConfig jDCrashReportConfig = (JDCrashReportConfig) getIntent().getSerializableExtra("config");
        if (!com.jingdong.sdk.jdcrashreport.d.w()) {
            r.b("JDCrashReport", "RecoverActivity init InnerApi");
            jDCrashReportConfig.setApplicationContext(getApplicationContext());
            com.jingdong.sdk.jdcrashreport.d.n(jDCrashReportConfig);
        } else {
            r.b("JDCrashReport", "RecoverActivity update config");
            com.jingdong.sdk.jdcrashreport.d.y(jDCrashReportConfig.getUts());
            com.jingdong.sdk.jdcrashreport.d.B(jDCrashReportConfig.getDeviceUniqueId());
            com.jingdong.sdk.jdcrashreport.d.q(jDCrashReportConfig.getUserId());
        }
        com.jingdong.sdk.jdcrashreport.recover.b f2 = com.jingdong.sdk.jdcrashreport.d.f();
        this.f14950g = f2;
        try {
            f2.b(this);
            setContentView(this.f14950g.c(this));
            b();
        } catch (Throwable th) {
            r.c("JDCrashReport", "RecoverActivity onCreate exception", th);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        r.b("JDCrashReport", "RecoverActivity onDestory()");
        super.onDestroy();
        ServiceConnection serviceConnection = this.f14952i;
        if (serviceConnection != null) {
            unbindService(serviceConnection);
            this.f14952i = null;
        }
        this.f14951h = null;
        this.f14950g.e();
        this.f14950g.d();
        this.f14950g = null;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return this.f14950g.f(i2, keyEvent);
    }

    @Override // android.app.Activity
    protected void onPause() {
        r.b("JDCrashReport", "RecoverActivity onPause()");
        super.onPause();
        this.f14950g.g();
    }

    @Override // android.app.Activity
    protected void onResume() {
        r.b("JDCrashReport", "RecoverActivity onResume()");
        super.onResume();
        this.f14950g.h();
    }

    @Override // android.app.Activity
    protected void onStop() {
        r.b("JDCrashReport", "RecoverActivity onStop()");
        super.onStop();
        this.f14950g.i();
    }
}
