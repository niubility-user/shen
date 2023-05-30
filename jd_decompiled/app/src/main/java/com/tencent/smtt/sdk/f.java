package com.tencent.smtt.sdk;

import android.content.Context;
import com.jingdong.common.XView2.common.XView2Constants;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;

/* loaded from: classes9.dex */
public class f {
    static int a = 0;
    static boolean b = false;

    /* renamed from: e */
    private static f f17823e = null;

    /* renamed from: h */
    private static int f17824h = 0;

    /* renamed from: j */
    private static int f17825j = 3;

    /* renamed from: l */
    private static String f17826l;

    /* renamed from: c */
    private s f17827c = null;
    private s d = null;

    /* renamed from: f */
    private boolean f17828f = false;

    /* renamed from: g */
    private boolean f17829g = false;

    /* renamed from: i */
    private String f17830i = "";

    /* renamed from: k */
    private File f17831k = null;

    private f() {
    }

    public static f a(boolean z) {
        if (f17823e == null && z) {
            synchronized (f.class) {
                if (f17823e == null) {
                    f17823e = new f();
                }
            }
        }
        return f17823e;
    }

    public static void a(int i2) {
        f17824h = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0034 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void b(int r6) {
        /*
            r5 = this;
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.util.Properties r0 = new java.util.Properties
            r0.<init>()
            java.lang.String r1 = com.tencent.smtt.sdk.f.f17826l
            r0.setProperty(r1, r6)
            r6 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.io.IOException -> L25 java.io.FileNotFoundException -> L2c
            java.io.File r2 = new java.io.File     // Catch: java.io.IOException -> L25 java.io.FileNotFoundException -> L2c
            java.io.File r3 = r5.f17831k     // Catch: java.io.IOException -> L25 java.io.FileNotFoundException -> L2c
            java.lang.String r4 = "count.prop"
            r2.<init>(r3, r4)     // Catch: java.io.IOException -> L25 java.io.FileNotFoundException -> L2c
            r1.<init>(r2)     // Catch: java.io.IOException -> L25 java.io.FileNotFoundException -> L2c
            r0.store(r1, r6)     // Catch: java.io.IOException -> L21 java.io.FileNotFoundException -> L23
            goto L32
        L21:
            r6 = move-exception
            goto L28
        L23:
            r6 = move-exception
            goto L2f
        L25:
            r0 = move-exception
            r1 = r6
            r6 = r0
        L28:
            r6.printStackTrace()
            goto L32
        L2c:
            r0 = move-exception
            r1 = r6
            r6 = r0
        L2f:
            r6.printStackTrace()
        L32:
            if (r1 == 0) goto L37
            r1.close()     // Catch: java.lang.Throwable -> L37
        L37:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.f.b(int):void");
    }

    public static int d() {
        return f17824h;
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0054: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:71:0x0054 */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int j() {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.io.File r3 = r6.f17831k     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r4 = "count.prop"
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            boolean r3 = r2.exists()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r3 != 0) goto L12
            return r0
        L12:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.util.Properties r1 = new java.util.Properties     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L53
            r1.<init>()     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L53
            r1.load(r2)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L53
            java.lang.String r3 = com.tencent.smtt.sdk.f.f17826l     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L53
            java.lang.String r4 = "1"
            java.lang.String r1 = r1.getProperty(r3, r4)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L53
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L53
            int r0 = r1.intValue()     // Catch: java.lang.Exception -> L3d java.lang.Throwable -> L53
            r2.close()     // Catch: java.io.IOException -> L38
            goto L3c
        L38:
            r1 = move-exception
            r1.printStackTrace()
        L3c:
            return r0
        L3d:
            r1 = move-exception
            goto L45
        L3f:
            r0 = move-exception
            goto L55
        L41:
            r2 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L45:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L53
            if (r2 == 0) goto L52
            r2.close()     // Catch: java.io.IOException -> L4e
            goto L52
        L4e:
            r1 = move-exception
            r1.printStackTrace()
        L52:
            return r0
        L53:
            r0 = move-exception
            r1 = r2
        L55:
            if (r1 == 0) goto L5f
            r1.close()     // Catch: java.io.IOException -> L5b
            goto L5f
        L5b:
            r1 = move-exception
            r1.printStackTrace()
        L5f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.f.j():int");
    }

    public s a() {
        if (this.f17828f) {
            return this.f17827c;
        }
        return null;
    }

    public synchronized void a(Context context, boolean z, boolean z2) {
        TbsLog.initIfNeed(context);
        a++;
        TbsLog.i("SDKEngine", XView2Constants.XVIEW2_ACTION_INIT, "#1# context: " + context + ", mInitCount: " + a);
        if (this.f17828f) {
            return;
        }
        m.a().b(context, a == 1);
        m.a().i(context);
        boolean a2 = QbSdk.a(context, z, z2);
        TbsLog.i("SDKEngine", XView2Constants.XVIEW2_ACTION_INIT, "#2# canLoadX5 is " + a2);
        if (a2) {
            TbsLog.i("SDKEngine", XView2Constants.XVIEW2_ACTION_INIT, "#3# start to load tbs");
            File n2 = m.a().n(context);
            Context applicationContext = context.getApplicationContext() != null ? context.getApplicationContext() : context;
            if (n2 == null) {
                this.f17828f = false;
                this.f17830i = "false03";
                TbsCoreLoadStat.getInstance().a(context, 312, new Throwable());
                QbSdk.a(context, "SDKEngine::useSystemWebView by tbs_core_share_dir null!");
                return;
            }
            String[] dexLoaderFileList = QbSdk.getDexLoaderFileList(context, applicationContext, n2.getAbsolutePath());
            for (int i2 = 0; i2 < dexLoaderFileList.length; i2++) {
                TbsLog.i("SDKEngine", "dexLoaderFileList[" + i2 + "]: " + dexLoaderFileList[i2]);
            }
            String absolutePath = n2.getAbsolutePath();
            TbsLog.i("SDKEngine", XView2Constants.XVIEW2_ACTION_INIT, "#4# optDir is " + absolutePath);
            s sVar = this.d;
            if (sVar != null) {
                this.f17827c = sVar;
                sVar.a(context, applicationContext, n2.getAbsolutePath(), absolutePath, dexLoaderFileList, QbSdk.d);
            } else {
                this.f17827c = new s(context, applicationContext, n2.getAbsolutePath(), absolutePath, dexLoaderFileList, QbSdk.d);
            }
            this.f17828f = true;
            this.f17830i = "true01";
        } else if (!QbSdk.a || !this.f17828f) {
            this.f17828f = false;
            this.f17830i = "false05";
            TbsLog.e("SDKEngine", XView2Constants.XVIEW2_ACTION_INIT, "[LoadError] check log upon for details");
        }
        this.f17831k = m.o(context);
        this.f17829g = true;
    }

    public void a(String str) {
        f17826l = str;
    }

    public boolean b() {
        return this.f17828f;
    }

    public boolean b(boolean z) {
        b = z;
        return z;
    }

    public s c() {
        return this.f17827c;
    }

    public String e() {
        s sVar = this.f17827c;
        return (sVar == null || QbSdk.a) ? "system webview get nothing..." : sVar.a();
    }

    public String f() {
        s sVar = this.f17827c;
        return (sVar == null || QbSdk.a) ? "system webview get nothing..." : sVar.b();
    }

    public boolean g() {
        if (b) {
            if (f17826l == null) {
                return false;
            }
            int j2 = j();
            if (j2 == 0) {
                b(1);
            } else {
                int i2 = j2 + 1;
                if (i2 > f17825j) {
                    return false;
                }
                b(i2);
            }
        }
        return b;
    }

    public boolean h() {
        return this.f17829g;
    }

    public boolean i() {
        return QbSdk.useSoftWare();
    }
}
