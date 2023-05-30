package com.tencent.smtt.sdk;

import android.content.Context;
import com.jingdong.common.XView2.common.XView2Constants;
import com.tencent.smtt.utils.TbsLog;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

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

    /* JADX WARN: Removed duplicated region for block: B:76:0x0034 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void b(int i2) {
        FileOutputStream fileOutputStream;
        IOException e2;
        FileNotFoundException e3;
        String valueOf = String.valueOf(i2);
        Properties properties = new Properties();
        properties.setProperty(f17826l, valueOf);
        try {
            fileOutputStream = new FileOutputStream(new File(this.f17831k, "count.prop"));
            try {
                properties.store(fileOutputStream, (String) null);
            } catch (FileNotFoundException e4) {
                e3 = e4;
                e3.printStackTrace();
                if (fileOutputStream != null) {
                }
            } catch (IOException e5) {
                e2 = e5;
                e2.printStackTrace();
                if (fileOutputStream != null) {
                }
            }
        } catch (FileNotFoundException e6) {
            fileOutputStream = null;
            e3 = e6;
        } catch (IOException e7) {
            fileOutputStream = null;
            e2 = e7;
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
        }
    }

    public static int d() {
        return f17824h;
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0054: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:115:0x0054 */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int j() {
        BufferedInputStream bufferedInputStream;
        Exception e2;
        BufferedInputStream bufferedInputStream2;
        BufferedInputStream bufferedInputStream3 = null;
        try {
            try {
                File file = new File(this.f17831k, "count.prop");
                if (file.exists()) {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    try {
                        Properties properties = new Properties();
                        properties.load(bufferedInputStream);
                        int intValue = Integer.valueOf(properties.getProperty(f17826l, "1")).intValue();
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        return intValue;
                    } catch (Exception e4) {
                        e2 = e4;
                        e2.printStackTrace();
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        return 0;
                    }
                }
                return 0;
            } catch (Exception e6) {
                bufferedInputStream = null;
                e2 = e6;
            } catch (Throwable th) {
                th = th;
                if (bufferedInputStream3 != null) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream3 = bufferedInputStream2;
            if (bufferedInputStream3 != null) {
                try {
                    bufferedInputStream3.close();
                } catch (IOException e7) {
                    e7.printStackTrace();
                }
            }
            throw th;
        }
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
