package com.tencent.smtt.sdk;

import android.content.Context;
import android.util.Log;
import com.jingdong.common.XView2.common.XView2Constants;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import java.nio.channels.FileLock;

/* loaded from: classes9.dex */
public class u {
    private static u a;

    /* renamed from: f */
    private static FileLock f17876f;
    private v b;

    /* renamed from: c */
    private boolean f17877c;
    private boolean d = false;

    /* renamed from: e */
    private boolean f17878e;

    private u() {
    }

    public static u a() {
        if (a == null) {
            synchronized (u.class) {
                if (a == null) {
                    a = new u();
                }
            }
        }
        return a;
    }

    public v a(boolean z) {
        return z ? this.b : c();
    }

    /* JADX WARN: Removed duplicated region for block: B:174:0x00cf A[Catch: all -> 0x022f, TRY_LEAVE, TryCatch #3 {, blocks: (B:145:0x0001, B:147:0x0005, B:149:0x000b, B:152:0x0025, B:155:0x0050, B:157:0x0054, B:166:0x008c, B:168:0x009a, B:169:0x00a1, B:172:0x00b3, B:174:0x00cf, B:190:0x0130, B:192:0x013c, B:193:0x0176, B:202:0x022b, B:194:0x017b, B:195:0x01b6, B:197:0x01bc, B:198:0x01f8, B:199:0x020c, B:201:0x0228, B:163:0x0075, B:164:0x0084, B:170:0x00a4, B:178:0x00e0, B:180:0x00e6, B:181:0x00f2, B:183:0x00f6, B:184:0x0119, B:186:0x011d, B:158:0x0066, B:160:0x006e), top: B:210:0x0001, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x020c A[Catch: all -> 0x022f, TryCatch #3 {, blocks: (B:145:0x0001, B:147:0x0005, B:149:0x000b, B:152:0x0025, B:155:0x0050, B:157:0x0054, B:166:0x008c, B:168:0x009a, B:169:0x00a1, B:172:0x00b3, B:174:0x00cf, B:190:0x0130, B:192:0x013c, B:193:0x0176, B:202:0x022b, B:194:0x017b, B:195:0x01b6, B:197:0x01bc, B:198:0x01f8, B:199:0x020c, B:201:0x0228, B:163:0x0075, B:164:0x0084, B:170:0x00a4, B:178:0x00e0, B:180:0x00e6, B:181:0x00f2, B:183:0x00f6, B:184:0x0119, B:186:0x011d, B:158:0x0066, B:160:0x006e), top: B:210:0x0001, inners: #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized void a(Context context) {
        Throwable th;
        String str;
        String str2;
        if (this.d && !QbSdk.isEnableX5WithoutRestart()) {
            TbsLog.w("X5CoreEngine", "coreEngine has init, mCanUseX5=" + this.f17877c);
            return;
        }
        TbsLog.i("X5CoreEngine", XView2Constants.XVIEW2_ACTION_INIT, "#1# Start init");
        f a2 = f.a(true);
        a2.a(context, false, false);
        TbsLog.i("X5CoreEngine", XView2Constants.XVIEW2_ACTION_INIT, "#2# SDKEngine init finish");
        StringBuilder sb = new StringBuilder();
        s a3 = a2.a();
        if (!a2.b() || a3 == null) {
            this.f17877c = false;
            TbsLog.e("X5CoreEngine", XView2Constants.XVIEW2_ACTION_INIT, "SDKEngine tbs not available!");
            sb.append("SDKEngine tbs not available_");
        } else if (!this.f17878e) {
            TbsLog.i("X5CoreEngine", XView2Constants.XVIEW2_ACTION_INIT, "#3-1# X5CoreWizard start to load dex and so");
            v vVar = new v(a3.c());
            this.b = vVar;
            try {
                boolean a4 = vVar.a();
                this.f17877c = a4;
                if (!a4) {
                    sb.append("can not use X5 by x5corewizard return false");
                }
            } catch (NoSuchMethodException unused) {
                this.f17877c = true;
                sb.append("NoSuchMethodException");
            } catch (Throwable th2) {
                th = th2;
                this.f17877c = false;
                sb.append("can not use x5 by x5corewizard throwable ");
                sb.append(Log.getStackTraceString(th));
            }
            th = null;
            sb.append("mCanUseX5 is ");
            sb.append(this.f17877c);
            if (this.f17877c) {
                CookieManager.getInstance().a();
            }
            this.f17878e = true;
            TbsLog.i("X5CoreEngine", "init  mCanUseX5 is " + this.f17877c);
            if (this.f17877c) {
                TbsLog.e("X5CoreEngine", "mCanUseX5 is false --> report");
                if (a2.b() && a3 != null && th == null) {
                    DexLoader c2 = a3.c();
                    Object invokeStaticMethod = c2 != null ? c2.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "getLoadFailureDetails", new Class[0], new Object[0]) : null;
                    if (invokeStaticMethod instanceof Throwable) {
                        Throwable th3 = (Throwable) invokeStaticMethod;
                        sb.append("#");
                        sb.append(th3.getMessage());
                        sb.append("; cause: ");
                        sb.append(th3.getCause());
                        sb.append("; th: ");
                        sb.append(th3);
                    }
                    if (invokeStaticMethod instanceof String) {
                        sb.append("failure detail: ");
                        sb.append(invokeStaticMethod);
                    }
                    if (sb.toString().contains("isPreloadX5Disabled:-10000")) {
                        TbsCoreLoadStat.getInstance().a(context, 408, new Throwable("Core Crash, details: " + sb.toString()));
                        str = "X5CoreEngine";
                        str2 = "[LoadError] Core Crash, details: " + sb.toString();
                    } else {
                        TbsCoreLoadStat.getInstance().a(context, 407, new Throwable("Failed in Core, details: " + sb.toString()));
                        str = "X5CoreEngine";
                        str2 = "[LoadError] Failed in Core, details: " + sb.toString();
                    }
                } else if (a2.b()) {
                    TbsCoreLoadStat.getInstance().a(context, 409, new Throwable("mCanUseX5=false, available true, reason: " + sb.toString()));
                    str = "X5CoreEngine";
                    str2 = "[LoadError] details: " + sb.toString();
                } else {
                    TbsCoreLoadStat.getInstance().a(context, 410, new Throwable());
                    str = "X5CoreEngine";
                    str2 = "[LoadError] mCanUseX5=false, available=false";
                }
                TbsLog.e(str, XView2Constants.XVIEW2_ACTION_INIT, str2);
            } else {
                TbsLog.i("X5CoreEngine", XView2Constants.XVIEW2_ACTION_INIT, "#5# sTbsCoreLoadFileLock is " + f17876f);
                if (f17876f == null) {
                    b(context);
                }
            }
            this.d = true;
        }
        th = null;
        TbsLog.i("X5CoreEngine", "init  mCanUseX5 is " + this.f17877c);
        if (this.f17877c) {
        }
        this.d = true;
    }

    public FileLock b(Context context) {
        String str;
        String str2;
        TbsLog.i("X5CoreEngine", "tryTbsCoreLoadFileLock ##");
        FileLock fileLock = f17876f;
        if (fileLock != null) {
            return fileLock;
        }
        synchronized (u.class) {
            if (f17876f == null) {
                FileLock d = FileUtil.d(context);
                f17876f = d;
                if (d == null) {
                    str = "X5CoreEngine";
                    str2 = "init -- sTbsCoreLoadFileLock failed!";
                } else {
                    str = "X5CoreEngine";
                    str2 = "init -- sTbsCoreLoadFileLock succeeded: " + f17876f;
                }
                TbsLog.i(str, str2);
            }
        }
        return f17876f;
    }

    public boolean b() {
        if (QbSdk.a || QbSdk.getIsSysWebViewForcedByOuter()) {
            return false;
        }
        return this.f17877c;
    }

    public v c() {
        if (QbSdk.a) {
            return null;
        }
        return this.b;
    }

    public boolean d() {
        return this.d;
    }
}
