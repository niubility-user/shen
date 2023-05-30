package com.jingdong.sdk.talos.inner;

import android.os.StatFs;
import android.text.TextUtils;
import com.jingdong.sdk.talos.inner.e;
import com.jingdong.sdk.talos.inner.k;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* loaded from: classes.dex */
public final class f extends Thread {

    /* renamed from: g */
    private ExecutorService f15514g;

    /* renamed from: h */
    private int f15515h;

    /* renamed from: m */
    ConcurrentLinkedQueue<e> f15520m;

    /* renamed from: n */
    com.jingdong.sdk.talos.b f15521n;
    private boolean o;
    private long p;
    private File q;
    private boolean r;
    private long s;
    private i t;
    private g u;

    /* renamed from: j */
    private final Object f15517j = new Object();

    /* renamed from: k */
    private final Object f15518k = new Object();

    /* renamed from: l */
    private volatile boolean f15519l = true;

    /* renamed from: i */
    private ConcurrentLinkedQueue<e> f15516i = new ConcurrentLinkedQueue<>();

    /* loaded from: classes.dex */
    final class a implements k.b {
        a() {
            f.this = r1;
        }

        @Override // com.jingdong.sdk.talos.inner.k.b
        public final void a() {
            synchronized (f.this.f15518k) {
                f.d(f.this);
                f fVar = f.this;
                fVar.f15520m.addAll(fVar.f15516i);
                f.this.f15516i.clear();
                f.this.a();
            }
        }
    }

    /* loaded from: classes.dex */
    final class b implements ThreadFactory {
        b(f fVar) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            Thread thread = new Thread(Thread.currentThread().getThreadGroup(), runnable, "logx-thread-send-log", 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    public f(ConcurrentLinkedQueue<e> concurrentLinkedQueue, com.jingdong.sdk.talos.b bVar, g gVar) {
        this.f15520m = concurrentLinkedQueue;
        this.f15521n = bVar;
        this.u = gVar;
    }

    private void b(long j2) {
        String[] list;
        File file = new File(this.f15521n.i());
        if (!file.isDirectory() || (list = file.list()) == null) {
            return;
        }
        for (String str : list) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    String[] split = str.split("\\.");
                    if (split.length > 0 && Long.valueOf(split[0]).longValue() <= j2 && split.length == 1) {
                        new File(this.f15521n.i(), str).delete();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r4v9, types: [java.io.FileOutputStream] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:207:0x0035 -> B:267:0x0077). Please submit an issue!!! */
    private static boolean c(String str, String str2) {
        int read;
        FileInputStream fileInputStream = null;
        boolean z = false;
        try {
            try {
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(new File((String) str));
                    try {
                        str = new FileOutputStream(new File(str2));
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        str = 0;
                    } catch (IOException e3) {
                        e = e3;
                        str = 0;
                    } catch (Throwable th) {
                        th = th;
                        str = 0;
                    }
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            read = fileInputStream2.read(bArr);
                            if (read < 0) {
                                break;
                            }
                            str.write(bArr, 0, read);
                            str.flush();
                        }
                        z = true;
                        try {
                            fileInputStream2.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        str.close();
                        fileInputStream = read;
                        str = str;
                    } catch (FileNotFoundException e5) {
                        e = e5;
                        fileInputStream = fileInputStream2;
                        str = str;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e6) {
                                e6.printStackTrace();
                            }
                        }
                        if (str != 0) {
                            str.close();
                            fileInputStream = fileInputStream;
                            str = str;
                        }
                        return z;
                    } catch (IOException e7) {
                        e = e7;
                        fileInputStream = fileInputStream2;
                        str = str;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e8) {
                                e8.printStackTrace();
                            }
                        }
                        if (str != 0) {
                            str.close();
                            fileInputStream = fileInputStream;
                            str = str;
                        }
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream = fileInputStream2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e9) {
                                e9.printStackTrace();
                            }
                        }
                        if (str != 0) {
                            try {
                                str.close();
                            } catch (Exception e10) {
                                e10.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e11) {
                    e = e11;
                    str = 0;
                } catch (IOException e12) {
                    e = e12;
                    str = 0;
                } catch (Throwable th3) {
                    th = th3;
                    str = 0;
                }
            } catch (Exception e13) {
                e13.printStackTrace();
                fileInputStream = fileInputStream;
                str = e13;
            }
            return z;
        } catch (Throwable th4) {
            th = th4;
        }
    }

    static /* synthetic */ int d(f fVar) {
        fVar.f15515h = 10002;
        return 10002;
    }

    private void e() {
        i iVar = this.t;
        if (iVar != null) {
            iVar.c();
        }
    }

    private boolean g() {
        try {
            StatFs statFs = new StatFs(this.f15521n.i());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()) > this.f15521n.p();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public final void a() {
        if (this.o) {
            return;
        }
        synchronized (this.f15517j) {
            this.f15517j.notify();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:291:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0059 A[Catch: InterruptedException -> 0x024d, all -> 0x0256, TryCatch #2 {InterruptedException -> 0x024d, blocks: (B:266:0x000e, B:268:0x0018, B:270:0x0025, B:272:0x0029, B:274:0x002d, B:276:0x0031, B:292:0x0059, B:294:0x005d, B:295:0x0094, B:297:0x009a, B:299:0x00a0, B:300:0x00ad, B:302:0x00b7, B:307:0x00c3, B:308:0x00dd, B:310:0x00eb, B:311:0x00f1, B:313:0x00fb, B:314:0x0110, B:316:0x0114, B:318:0x011a, B:319:0x011c, B:364:0x0244, B:365:0x0245, B:367:0x0249, B:279:0x0039, B:281:0x003f, B:283:0x0043, B:286:0x004d), top: B:381:0x000e, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0199 A[Catch: all -> 0x0242, TryCatch #0 {, blocks: (B:320:0x011d, B:322:0x0123, B:360:0x0240, B:323:0x012a, B:326:0x013a, B:329:0x0142, B:331:0x014b, B:333:0x0153, B:334:0x015b, B:343:0x0199, B:345:0x01c5, B:347:0x01f7, B:352:0x0204, B:354:0x020c, B:355:0x0214, B:356:0x021d, B:358:0x022e, B:359:0x0239, B:348:0x01fa, B:349:0x01fd, B:337:0x016b, B:339:0x0190), top: B:377:0x011d }] */
    /* JADX WARN: Removed duplicated region for block: B:349:0x01fd A[Catch: all -> 0x0242, TryCatch #0 {, blocks: (B:320:0x011d, B:322:0x0123, B:360:0x0240, B:323:0x012a, B:326:0x013a, B:329:0x0142, B:331:0x014b, B:333:0x0153, B:334:0x015b, B:343:0x0199, B:345:0x01c5, B:347:0x01f7, B:352:0x0204, B:354:0x020c, B:355:0x0214, B:356:0x021d, B:358:0x022e, B:359:0x0239, B:348:0x01fa, B:349:0x01fd, B:337:0x016b, B:339:0x0190), top: B:377:0x011d }] */
    /* JADX WARN: Removed duplicated region for block: B:352:0x0204 A[Catch: all -> 0x0242, TryCatch #0 {, blocks: (B:320:0x011d, B:322:0x0123, B:360:0x0240, B:323:0x012a, B:326:0x013a, B:329:0x0142, B:331:0x014b, B:333:0x0153, B:334:0x015b, B:343:0x0199, B:345:0x01c5, B:347:0x01f7, B:352:0x0204, B:354:0x020c, B:355:0x0214, B:356:0x021d, B:358:0x022e, B:359:0x0239, B:348:0x01fa, B:349:0x01fd, B:337:0x016b, B:339:0x0190), top: B:377:0x011d }] */
    /* JADX WARN: Removed duplicated region for block: B:356:0x021d A[Catch: all -> 0x0242, TryCatch #0 {, blocks: (B:320:0x011d, B:322:0x0123, B:360:0x0240, B:323:0x012a, B:326:0x013a, B:329:0x0142, B:331:0x014b, B:333:0x0153, B:334:0x015b, B:343:0x0199, B:345:0x01c5, B:347:0x01f7, B:352:0x0204, B:354:0x020c, B:355:0x0214, B:356:0x021d, B:358:0x022e, B:359:0x0239, B:348:0x01fa, B:349:0x01fd, B:337:0x016b, B:339:0x0190), top: B:377:0x011d }] */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        boolean z;
        boolean z2;
        l lVar;
        j jVar;
        super.run();
        while (this.f15519l) {
            synchronized (this.f15517j) {
                boolean z3 = true;
                this.o = true;
                try {
                    e poll = this.f15520m.poll();
                    if (poll == null) {
                        this.o = false;
                        this.f15517j.wait();
                        this.o = true;
                    } else if (poll != null) {
                        int i2 = poll.a;
                        if (i2 == 0 || ((i2 != e.a.b || (jVar = poll.f15511c) == null || !jVar.a()) && ((poll.a != e.a.a || (lVar = poll.b) == null || (!TextUtils.isEmpty(lVar.a)) == false) && poll.a != e.a.f15512c))) {
                            z = false;
                            if (z) {
                                if (this.t == null) {
                                    i d = i.d();
                                    this.t = d;
                                    d.c(this.f15521n.a(), this.f15521n.i(), (int) this.f15521n.n(), new String(this.f15521n.g()), new String(this.f15521n.f()));
                                    this.t.a(false);
                                }
                                int i3 = poll.a;
                                if (i3 == e.a.a) {
                                    l lVar2 = poll.b;
                                    if (this.q == null) {
                                        this.q = new File(this.f15521n.i());
                                    }
                                    long currentTimeMillis = System.currentTimeMillis();
                                    long j2 = this.p;
                                    if (j2 >= currentTimeMillis || j2 + 86400000 <= currentTimeMillis) {
                                        z3 = false;
                                    }
                                    if (!z3) {
                                        long a2 = e.f.a();
                                        b(a2 - this.f15521n.c());
                                        this.p = a2;
                                        this.t.a(String.valueOf(a2));
                                    }
                                    if (System.currentTimeMillis() - this.s > 60000) {
                                        this.r = g();
                                    }
                                    this.s = System.currentTimeMillis();
                                    if (this.r) {
                                        this.t.a(lVar2.f15535f, lVar2.a, lVar2.f15534e, lVar2.d, lVar2.f15533c, lVar2.b, lVar2.f15536g);
                                    }
                                } else if (i3 == e.a.b) {
                                    if (poll.f15511c.d != null) {
                                        synchronized (this.f15518k) {
                                            if (this.f15515h == 10001) {
                                                this.f15516i.add(poll);
                                            } else {
                                                j jVar2 = poll.f15511c;
                                                if (!TextUtils.isEmpty(this.f15521n.i()) && jVar2 != null && jVar2.a()) {
                                                    com.jingdong.sdk.talos.a.g();
                                                    if (e.C0748e.b()) {
                                                        if (this.f15521n.v()) {
                                                            e.d.b("OKLogThread", "prepare log file");
                                                        }
                                                        String str = jVar2.b;
                                                        if (!TextUtils.isEmpty(this.f15521n.i())) {
                                                            File file = new File(this.f15521n.i() + File.separator + str);
                                                            if (file.exists() && file.isFile()) {
                                                                z2 = true;
                                                                if (z2) {
                                                                    jVar2.f15527c = "";
                                                                } else {
                                                                    StringBuilder sb = new StringBuilder();
                                                                    sb.append(this.f15521n.i());
                                                                    String str2 = File.separator;
                                                                    sb.append(str2);
                                                                    sb.append(jVar2.b);
                                                                    String sb2 = sb.toString();
                                                                    if (jVar2.b.equals(String.valueOf(e.f.a()))) {
                                                                        e();
                                                                        com.jingdong.sdk.talos.a.g();
                                                                        String str3 = this.f15521n.i() + str2 + jVar2.b + e.C0748e.c() + ".copy";
                                                                        if (c(sb2, str3)) {
                                                                            jVar2.f15527c = str3;
                                                                        }
                                                                    } else {
                                                                        jVar2.f15527c = sb2;
                                                                    }
                                                                    if (z3) {
                                                                        k kVar = jVar2.d;
                                                                        kVar.f15530h = jVar2;
                                                                        kVar.f15531i = new a();
                                                                        this.f15515h = 10001;
                                                                        if (this.f15514g == null) {
                                                                            this.f15514g = Executors.newSingleThreadExecutor(new b(this));
                                                                        }
                                                                        this.f15514g.execute(jVar2.d);
                                                                    } else {
                                                                        if (this.f15521n.v()) {
                                                                            e.d.b("OKLogThread", "prepare log file failed, can't find log file");
                                                                        }
                                                                        this.u.f("", 4);
                                                                    }
                                                                }
                                                                z3 = false;
                                                                if (z3) {
                                                                }
                                                            }
                                                        }
                                                        z2 = false;
                                                        if (z2) {
                                                        }
                                                        z3 = false;
                                                        if (z3) {
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else if (i3 == e.a.f15512c) {
                                    e();
                                }
                            }
                        }
                        z = true;
                        if (z) {
                        }
                    }
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    this.o = false;
                }
            }
        }
    }
}
