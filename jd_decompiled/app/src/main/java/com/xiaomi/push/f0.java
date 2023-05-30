package com.xiaomi.push;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;

/* loaded from: classes11.dex */
class f0 implements t {

    /* renamed from: h  reason: collision with root package name */
    private static String f18596h = "content://com.vivo.vms.IdProvider/IdentifierId/";

    /* renamed from: i  reason: collision with root package name */
    private static String f18597i = f18596h + "OAID";

    /* renamed from: j  reason: collision with root package name */
    private static String f18598j;

    /* renamed from: g  reason: collision with root package name */
    private Context f18599g;

    static {
        String str = f18596h + "VAID_";
        String str2 = f18596h + "AAID_";
        String str3 = f18596h + "OAIDSTATUS";
        f18598j = "persist.sys.identifierid.supported";
    }

    public f0(Context context) {
        this.f18599g = context;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:
        if (r10 != null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003b, code lost:
        if (r10 == null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x003e, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String b(java.lang.String r10) {
        /*
            r9 = this;
            r0 = 0
            android.content.Context r1 = r9.f18599g     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L3a
            android.content.ContentResolver r2 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L3a
            android.net.Uri r3 = android.net.Uri.parse(r10)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L3a
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r10 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L3a
            if (r10 == 0) goto L2a
            boolean r1 = r10.moveToNext()     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            if (r1 == 0) goto L2a
            java.lang.String r1 = "value"
            int r1 = r10.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            java.lang.String r0 = r10.getString(r1)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            goto L2a
        L26:
            r0 = move-exception
            goto L34
        L28:
            goto L3b
        L2a:
            if (r10 == 0) goto L3e
        L2c:
            r10.close()
            goto L3e
        L30:
            r10 = move-exception
            r8 = r0
            r0 = r10
            r10 = r8
        L34:
            if (r10 == 0) goto L39
            r10.close()
        L39:
            throw r0
        L3a:
            r10 = r0
        L3b:
            if (r10 == 0) goto L3e
            goto L2c
        L3e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.f0.b(java.lang.String):java.lang.String");
    }

    public static boolean c(Context context) {
        try {
            ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(Uri.parse(f18596h).getAuthority(), 128);
            if (resolveContentProvider != null) {
                if ((resolveContentProvider.applicationInfo.flags & 1) != 0) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    @Override // com.xiaomi.push.t
    public String a() {
        return b(f18597i);
    }

    @Override // com.xiaomi.push.t
    /* renamed from: a */
    public boolean mo30a() {
        return "1".equals(q9.a(f18598j, "0"));
    }
}
