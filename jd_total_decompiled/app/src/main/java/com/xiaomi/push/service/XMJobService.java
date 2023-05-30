package com.xiaomi.push.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.xiaomi.push.i4;

/* loaded from: classes11.dex */
public class XMJobService extends Service {
    static Service a;

    /* renamed from: a  reason: collision with other field name */
    private IBinder f221a = null;

    @TargetApi(21)
    /* loaded from: classes11.dex */
    static class a extends JobService {

        /* renamed from: g  reason: collision with root package name */
        Binder f19027g;

        /* renamed from: h  reason: collision with root package name */
        private Handler f19028h;

        /* renamed from: com.xiaomi.push.service.XMJobService$a$a  reason: collision with other inner class name */
        /* loaded from: classes11.dex */
        private static class HandlerC0823a extends Handler {
            JobService a;

            HandlerC0823a(JobService jobService) {
                super(jobService.getMainLooper());
                this.a = jobService;
            }

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    return;
                }
                JobParameters jobParameters = (JobParameters) message.obj;
                g.j.a.a.a.c.o("Job finished " + jobParameters.getJobId());
                this.a.jobFinished(jobParameters, false);
                if (jobParameters.getJobId() == 1) {
                    i4.d(false);
                }
            }
        }

        a(Service service) {
            this.f19027g = null;
            this.f19027g = (Binder) com.xiaomi.push.k0.e(this, "onBind", new Intent());
            com.xiaomi.push.k0.e(this, "attachBaseContext", service);
        }

        @Override // android.app.job.JobService
        public boolean onStartJob(JobParameters jobParameters) {
            g.j.a.a.a.c.o("Job started " + jobParameters.getJobId());
            Intent intent = new Intent(this, XMPushService.class);
            intent.setAction("com.xiaomi.push.timer");
            intent.setPackage(getPackageName());
            startService(intent);
            if (this.f19028h == null) {
                this.f19028h = new HandlerC0823a(this);
            }
            Handler handler = this.f19028h;
            handler.sendMessage(Message.obtain(handler, 1, jobParameters));
            return true;
        }

        @Override // android.app.job.JobService
        public boolean onStopJob(JobParameters jobParameters) {
            g.j.a.a.a.c.o("Job stop " + jobParameters.getJobId());
            return false;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        IBinder iBinder = this.f221a;
        return iBinder != null ? iBinder : new Binder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 21) {
            this.f221a = new a(this).f19027g;
        }
        a = this;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        a = null;
    }
}
