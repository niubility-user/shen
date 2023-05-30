package com.tencent.smtt.sdk;

import android.content.Context;
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

    /* JADX WARN: Removed duplicated region for block: B:103:0x00cf A[Catch: all -> 0x022f, TRY_LEAVE, TryCatch #3 {, blocks: (B:74:0x0001, B:76:0x0005, B:78:0x000b, B:81:0x0025, B:84:0x0050, B:86:0x0054, B:95:0x008c, B:97:0x009a, B:98:0x00a1, B:101:0x00b3, B:103:0x00cf, B:119:0x0130, B:121:0x013c, B:122:0x0176, B:131:0x022b, B:123:0x017b, B:124:0x01b6, B:126:0x01bc, B:127:0x01f8, B:128:0x020c, B:130:0x0228, B:92:0x0075, B:93:0x0084, B:99:0x00a4, B:107:0x00e0, B:109:0x00e6, B:110:0x00f2, B:112:0x00f6, B:113:0x0119, B:115:0x011d, B:87:0x0066, B:89:0x006e), top: B:139:0x0001, inners: #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x020c A[Catch: all -> 0x022f, TryCatch #3 {, blocks: (B:74:0x0001, B:76:0x0005, B:78:0x000b, B:81:0x0025, B:84:0x0050, B:86:0x0054, B:95:0x008c, B:97:0x009a, B:98:0x00a1, B:101:0x00b3, B:103:0x00cf, B:119:0x0130, B:121:0x013c, B:122:0x0176, B:131:0x022b, B:123:0x017b, B:124:0x01b6, B:126:0x01bc, B:127:0x01f8, B:128:0x020c, B:130:0x0228, B:92:0x0075, B:93:0x0084, B:99:0x00a4, B:107:0x00e0, B:109:0x00e6, B:110:0x00f2, B:112:0x00f6, B:113:0x0119, B:115:0x011d, B:87:0x0066, B:89:0x006e), top: B:139:0x0001, inners: #4 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void a(android.content.Context r12) {
        /*
            Method dump skipped, instructions count: 564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.u.a(android.content.Context):void");
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
