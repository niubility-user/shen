package com.tencent.mapsdk.internal;

import com.tencent.map.lib.models.DownloadPriority;
import java.io.Closeable;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/* loaded from: classes9.dex */
public class jb {
    private ExecutorService b;

    /* renamed from: c  reason: collision with root package name */
    private ExecutorService f16735c;
    private hb d;
    private final Map<String, d> a = new Hashtable();

    /* renamed from: e  reason: collision with root package name */
    private final Set<ib> f16736e = new CopyOnWriteArraySet();

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ hb b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ int f16737c;

        public a(String str, hb hbVar, int i2) {
            this.a = str;
            this.b = hbVar;
            this.f16737c = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            jb.this.b(this.a, this.b, this.f16737c);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ kb b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ byte[] f16738c;

        public b(String str, kb kbVar, byte[] bArr) {
            this.a = str;
            this.b = kbVar;
            this.f16738c = bArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                for (ib ibVar : jb.this.f16736e) {
                    if (!jb.this.f16735c.isShutdown() && !jb.this.f16735c.isTerminated()) {
                        ibVar.a(this.a, this.b);
                        int ordinal = this.b.ordinal();
                        if (ordinal != 0) {
                            if (ordinal != 1) {
                                if (ordinal == 2) {
                                    ibVar.c(this.a);
                                    ibVar.a(this.a, this.f16738c);
                                } else if (ordinal == 3) {
                                    if (this.f16738c == null) {
                                        ibVar.b(this.a);
                                    }
                                    ibVar.a(this.a, this.f16738c);
                                    ibVar.a(this.a);
                                } else if (ordinal != 4) {
                                }
                            }
                            if (this.f16738c == null) {
                                ibVar.b(this.a);
                            }
                            ibVar.a(this.a, this.f16738c);
                        } else {
                            ibVar.d(this.a);
                        }
                    }
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class c {
        public static final /* synthetic */ int[] a;

        static {
            kb.values();
            int[] iArr = new int[5];
            a = iArr;
            try {
                kb kbVar = kb.START;
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                kb kbVar2 = kb.CANCEL;
                iArr2[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = a;
                kb kbVar3 = kb.ERROR;
                iArr3[4] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                int[] iArr4 = a;
                kb kbVar4 = kb.RUNNING;
                iArr4[1] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                int[] iArr5 = a;
                kb kbVar5 = kb.FINISH;
                iArr5[3] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class d {
        public Runnable a;
        public Future b;

        /* renamed from: c  reason: collision with root package name */
        public hb f16739c;
        public kb d;

        private d() {
        }

        public /* synthetic */ d(a aVar) {
            this();
        }

        public synchronized void a() {
            kb kbVar = this.d;
            if (kbVar != null && kbVar != kb.RUNNING) {
                d();
            }
        }

        public synchronized void a(ExecutorService executorService) {
            if (this.d == null && this.a != null && executorService != null && !g7.a(executorService)) {
                this.d = kb.START;
                this.b = executorService.submit(this.a);
            }
        }

        public synchronized void b() {
            kb kbVar = this.d;
            if (kbVar != kb.FINISH && kbVar != kb.CANCEL) {
                this.d = kb.ERROR;
            }
        }

        public synchronized void c() {
            kb kbVar = this.d;
            if (kbVar == kb.RUNNING || kbVar == kb.FINISH) {
                this.d = kb.FINISH;
            }
        }

        public synchronized void d() {
            if (this.d == null) {
                return;
            }
            Future future = this.b;
            if (future != null) {
                future.cancel(true);
            }
            hb hbVar = this.f16739c;
            if (hbVar != null) {
                hbVar.a(true);
            }
            this.d = kb.CANCEL;
        }

        public boolean e() {
            return this.d == kb.CANCEL;
        }

        public synchronized void f() {
            if (this.d == kb.START) {
                this.d = kb.RUNNING;
            }
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("RequestBody{");
            stringBuffer.append("runnable=");
            stringBuffer.append(this.a);
            stringBuffer.append(", requestFuture=");
            stringBuffer.append(this.b);
            stringBuffer.append(", executor=");
            stringBuffer.append(this.f16739c);
            stringBuffer.append(", status=");
            stringBuffer.append(this.d);
            stringBuffer.append('}');
            return stringBuffer.toString();
        }
    }

    private void a(String str, byte[] bArr, kb kbVar) {
        if (this.f16736e.isEmpty() || kbVar == null) {
            return;
        }
        ExecutorService executorService = this.f16735c;
        if (executorService == null || g7.a(executorService)) {
            this.f16735c = g7.e();
        }
        if (this.f16735c.isShutdown()) {
            return;
        }
        this.f16735c.execute(new b(str, kbVar, bArr));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, hb hbVar, int i2) {
        d dVar;
        d dVar2;
        byte[] e2;
        try {
            try {
                if (DownloadPriority.get(i2) != DownloadPriority.NONE) {
                    Thread.currentThread().setPriority(DownloadPriority.getThreadPriority(i2));
                }
                dVar2 = this.a.get(str);
                try {
                } catch (Exception e3) {
                    dVar = dVar2;
                    e = e3;
                    e.printStackTrace();
                    if (dVar != null) {
                        dVar.b();
                    }
                    a(str, (byte[]) null, dVar != null ? dVar.d : kb.ERROR);
                }
            } finally {
                hbVar.a();
            }
        } catch (Exception e4) {
            e = e4;
            dVar = null;
        }
        if (dVar2 == null) {
            a(str, (byte[]) null, kb.ERROR);
        } else if (dVar2.e()) {
            a(str, (byte[]) null, kb.CANCEL);
        } else {
            InputStream f2 = hbVar.f(str);
            a(str, (byte[]) null, dVar2.d);
            dVar2.f();
            kb kbVar = dVar2.d;
            if (f2 != null) {
                e2 = new byte[102400];
                while (e2.length != 0) {
                    e2 = ga.a(f2, 102400);
                    if (e2 == null) {
                        throw new IllegalStateException("\u4e0b\u8f7d\u8fc7\u7a0b\u8bfb\u53d6\u5931\u8d25");
                    }
                    a(str, e2, kbVar);
                    if (dVar2.e()) {
                        a(str, (byte[]) null, kb.CANCEL);
                        return;
                    }
                }
                ga.a((Closeable) f2);
            } else {
                e2 = hbVar.e(str);
                if (e2 != null && e2.length == 0) {
                    e2 = null;
                }
            }
            if (dVar2.e()) {
                a(str, (byte[]) null, kb.CANCEL);
                return;
            }
            dVar2.c();
            a(str, e2, dVar2.d);
        }
    }

    public synchronized void a() {
        a((Runnable) null);
    }

    public void a(ib ibVar) {
        if (ibVar != null) {
            this.f16736e.remove(ibVar);
            this.f16736e.add(ibVar);
        }
    }

    public synchronized void a(Runnable runnable) {
        this.d = null;
        ExecutorService executorService = this.b;
        if (executorService != null) {
            executorService.shutdownNow();
            this.b = null;
        }
        ExecutorService executorService2 = this.f16735c;
        if (executorService2 != null) {
            executorService2.shutdownNow();
            this.f16735c = null;
        }
        this.f16736e.clear();
        if (runnable != null) {
            runnable.run();
        }
    }

    public synchronized void a(String str) {
        d remove = this.a.remove(str);
        if (remove != null) {
            remove.a();
        }
    }

    public synchronized void a(String str, hb hbVar) {
        a(str, hbVar, DownloadPriority.NONE.getValue());
    }

    public synchronized void a(String str, hb hbVar, int i2) {
        if (hbVar == null) {
            return;
        }
        ExecutorService executorService = this.b;
        if (executorService == null || g7.a(executorService)) {
            this.b = g7.b();
        }
        try {
            if (!g7.a(this.b)) {
                d dVar = new d(null);
                this.a.put(str, dVar);
                dVar.a = new a(str, hbVar, i2);
                dVar.f16739c = hbVar;
                dVar.a(this.b);
            }
        } catch (IllegalMonitorStateException e2) {
            e2.printStackTrace();
        }
    }

    public void a(ExecutorService executorService) {
        this.b = executorService;
    }

    public void b(ib ibVar) {
        this.f16736e.remove(ibVar);
    }

    public synchronized void b(String str) {
        d remove = this.a.remove(str);
        if (remove != null) {
            remove.d();
        }
    }

    public Runnable c(String str) {
        d dVar = this.a.get(str);
        if (dVar != null) {
            return dVar.a;
        }
        return null;
    }
}
