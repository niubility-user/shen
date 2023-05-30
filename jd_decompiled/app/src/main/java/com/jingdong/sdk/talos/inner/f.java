package com.jingdong.sdk.talos.inner;

import android.os.StatFs;
import android.text.TextUtils;
import com.jingdong.sdk.talos.inner.k;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
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
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:113:0x0035 -> B:173:0x0077). Please submit an issue!!! */
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

    /* JADX WARN: Removed duplicated region for block: B:163:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0059 A[Catch: InterruptedException -> 0x024d, all -> 0x0256, TryCatch #2 {InterruptedException -> 0x024d, blocks: (B:138:0x000e, B:140:0x0018, B:142:0x0025, B:144:0x0029, B:146:0x002d, B:148:0x0031, B:164:0x0059, B:166:0x005d, B:167:0x0094, B:169:0x009a, B:171:0x00a0, B:172:0x00ad, B:174:0x00b7, B:179:0x00c3, B:180:0x00dd, B:182:0x00eb, B:183:0x00f1, B:185:0x00fb, B:186:0x0110, B:188:0x0114, B:190:0x011a, B:191:0x011c, B:236:0x0244, B:237:0x0245, B:239:0x0249, B:151:0x0039, B:153:0x003f, B:155:0x0043, B:158:0x004d), top: B:253:0x000e, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0199 A[Catch: all -> 0x0242, TryCatch #0 {, blocks: (B:192:0x011d, B:194:0x0123, B:232:0x0240, B:195:0x012a, B:198:0x013a, B:201:0x0142, B:203:0x014b, B:205:0x0153, B:206:0x015b, B:215:0x0199, B:217:0x01c5, B:219:0x01f7, B:224:0x0204, B:226:0x020c, B:227:0x0214, B:228:0x021d, B:230:0x022e, B:231:0x0239, B:220:0x01fa, B:221:0x01fd, B:209:0x016b, B:211:0x0190), top: B:249:0x011d }] */
    /* JADX WARN: Removed duplicated region for block: B:221:0x01fd A[Catch: all -> 0x0242, TryCatch #0 {, blocks: (B:192:0x011d, B:194:0x0123, B:232:0x0240, B:195:0x012a, B:198:0x013a, B:201:0x0142, B:203:0x014b, B:205:0x0153, B:206:0x015b, B:215:0x0199, B:217:0x01c5, B:219:0x01f7, B:224:0x0204, B:226:0x020c, B:227:0x0214, B:228:0x021d, B:230:0x022e, B:231:0x0239, B:220:0x01fa, B:221:0x01fd, B:209:0x016b, B:211:0x0190), top: B:249:0x011d }] */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0204 A[Catch: all -> 0x0242, TryCatch #0 {, blocks: (B:192:0x011d, B:194:0x0123, B:232:0x0240, B:195:0x012a, B:198:0x013a, B:201:0x0142, B:203:0x014b, B:205:0x0153, B:206:0x015b, B:215:0x0199, B:217:0x01c5, B:219:0x01f7, B:224:0x0204, B:226:0x020c, B:227:0x0214, B:228:0x021d, B:230:0x022e, B:231:0x0239, B:220:0x01fa, B:221:0x01fd, B:209:0x016b, B:211:0x0190), top: B:249:0x011d }] */
    /* JADX WARN: Removed duplicated region for block: B:228:0x021d A[Catch: all -> 0x0242, TryCatch #0 {, blocks: (B:192:0x011d, B:194:0x0123, B:232:0x0240, B:195:0x012a, B:198:0x013a, B:201:0x0142, B:203:0x014b, B:205:0x0153, B:206:0x015b, B:215:0x0199, B:217:0x01c5, B:219:0x01f7, B:224:0x0204, B:226:0x020c, B:227:0x0214, B:228:0x021d, B:230:0x022e, B:231:0x0239, B:220:0x01fa, B:221:0x01fd, B:209:0x016b, B:211:0x0190), top: B:249:0x011d }] */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 602
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.talos.inner.f.run():void");
    }
}
