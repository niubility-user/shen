package com.meizu.cloud.pushsdk.d;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes14.dex */
public class b implements f {

    /* renamed from: g  reason: collision with root package name */
    private String f15700g;

    /* renamed from: j  reason: collision with root package name */
    private ThreadPoolExecutor f15703j;
    private long d = 60;

    /* renamed from: e  reason: collision with root package name */
    private int f15698e = 10;

    /* renamed from: i  reason: collision with root package name */
    private boolean f15702i = false;
    private final SimpleDateFormat a = new SimpleDateFormat("MM-dd HH:mm:ss");
    private final List<c> b = Collections.synchronizedList(new ArrayList());

    /* renamed from: c  reason: collision with root package name */
    private final Handler f15697c = new Handler(Looper.getMainLooper());

    /* renamed from: f  reason: collision with root package name */
    private final e f15699f = new e();

    /* renamed from: h  reason: collision with root package name */
    private final String f15701h = String.valueOf(Process.myPid());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.b(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.meizu.cloud.pushsdk.d.b$b  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public class RunnableC0754b implements Runnable {
        RunnableC0754b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList<c> arrayList;
            synchronized (b.this.b) {
                b.this.f15697c.removeCallbacksAndMessages(null);
                arrayList = new ArrayList(b.this.b);
                b.this.b.clear();
            }
            try {
                if (b.this.f15700g != null) {
                    b.this.f15699f.c(b.this.f15700g);
                    for (c cVar : arrayList) {
                        b.this.f15699f.d(cVar.a, cVar.b, cVar.f15706c);
                    }
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                try {
                    b.this.f15699f.a();
                } catch (Exception unused2) {
                }
                throw th;
            }
            try {
                b.this.f15699f.a();
            } catch (Exception unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class c {
        final String a;
        final String b;

        /* renamed from: c  reason: collision with root package name */
        final String f15706c;

        public c(b bVar, String str, String str2, String str3) {
            this.a = bVar.a.format(new Date()) + LangUtils.SINGLE_SPACE + bVar.f15701h + "-" + Thread.currentThread().getId() + LangUtils.SINGLE_SPACE + str + "/";
            this.b = str2;
            this.f15706c = str3;
        }
    }

    public b() {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        j jVar = new j();
        jVar.a("log-pool-%d");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 30L, timeUnit, linkedBlockingQueue, jVar.b());
        this.f15703j = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    private void b(c cVar) {
        try {
            this.b.add(cVar);
        } catch (Exception e2) {
            String str = "add logInfo error " + e2.getMessage();
        }
    }

    private void d() {
        if (this.b.size() == this.f15698e) {
            b(true);
        }
    }

    private void f() {
        if (this.b.size() == 0) {
            this.f15697c.postDelayed(new a(), this.d * 1000);
        }
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void a(String str) {
        this.f15700g = str;
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void a(String str, String str2) {
        boolean z = this.f15702i;
        synchronized (this.b) {
            f();
            b(new c(this, "E", str, str2));
            d();
        }
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void a(String str, String str2, Throwable th) {
        boolean z = this.f15702i;
        synchronized (this.b) {
            f();
            b(new c(this, "E", str, str2 + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE + Log.getStackTraceString(th)));
            d();
        }
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void a(boolean z) {
        this.f15702i = z;
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public boolean a() {
        return this.f15702i;
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void b(String str, String str2) {
        boolean z = this.f15702i;
        synchronized (this.b) {
            f();
            b(new c(this, AuraConstants.MESSAGE_COUPON_TYPE_WILL_EXPIRE, str, str2));
            d();
        }
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void b(boolean z) {
        ThreadPoolExecutor threadPoolExecutor;
        RunnableC0754b runnableC0754b = new RunnableC0754b();
        if (!z || (threadPoolExecutor = this.f15703j) == null) {
            runnableC0754b.run();
        } else {
            threadPoolExecutor.execute(runnableC0754b);
        }
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void c(String str, String str2) {
        boolean z = this.f15702i;
        synchronized (this.b) {
            f();
            b(new c(this, "I", str, str2));
            d();
        }
    }

    @Override // com.meizu.cloud.pushsdk.d.f
    public void d(String str, String str2) {
        boolean z = this.f15702i;
        synchronized (this.b) {
            f();
            b(new c(this, "W", str, str2));
            d();
        }
    }
}
