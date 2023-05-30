package com.tencent.tmsqmsp.oaid2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;

/* loaded from: classes9.dex */
public class x {

    /* renamed from: g */
    public static volatile x f18098g;

    /* renamed from: e */
    public Boolean f18100e;

    /* renamed from: f */
    public BroadcastReceiver f18101f;
    public w a = new w("udid");
    public w b = new w("oaid");
    public w d = new w("vaid");

    /* renamed from: c */
    public w f18099c = new w("aaid");

    public static final x a() {
        if (f18098g == null) {
            synchronized (x.class) {
                f18098g = new x();
            }
        }
        return f18098g;
    }

    public static z a(Cursor cursor) {
        String str;
        z zVar = new z(null, 0);
        if (cursor == null) {
            str = "parseValue fail, cursor is null.";
        } else if (cursor.isClosed()) {
            str = "parseValue fail, cursor is closed.";
        } else {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex("value");
            if (columnIndex >= 0) {
                zVar.f18102c = cursor.getString(columnIndex);
            } else {
                b("parseValue fail, index < 0.");
            }
            int columnIndex2 = cursor.getColumnIndex("code");
            if (columnIndex2 >= 0) {
                zVar.a = cursor.getInt(columnIndex2);
            } else {
                b("parseCode fail, index < 0.");
            }
            int columnIndex3 = cursor.getColumnIndex("expired");
            if (columnIndex3 >= 0) {
                zVar.b = cursor.getLong(columnIndex3);
                return zVar;
            }
            str = "parseExpired fail, index < 0.";
        }
        b(str);
        return zVar;
    }

    public static void b(String str) {
        c.a("MzOpenIdManager " + str);
    }

    public w a(String str) {
        if ("oaid".equals(str)) {
            return this.b;
        }
        if ("vaid".equals(str)) {
            return this.d;
        }
        if ("aaid".equals(str)) {
            return this.f18099c;
        }
        if ("udid".equals(str)) {
            return this.a;
        }
        return null;
    }

    public final String a(Context context, w wVar) {
        Cursor cursor;
        Cursor cursor2 = null;
        if (wVar == null) {
            return null;
        }
        if (wVar.a()) {
            return wVar.d;
        }
        b("queryId : " + wVar.f18097c);
        try {
            cursor = context.getContentResolver().query(Uri.parse("content://com.meizu.flyme.openidsdk/"), null, null, new String[]{wVar.f18097c}, null);
        } catch (Exception unused) {
            cursor = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (cursor == null) {
                try {
                    a(context, false);
                    b("forceQuery isSupported : " + a(context, true));
                    if (cursor != null) {
                        cursor.close();
                    }
                    return null;
                } catch (Exception unused2) {
                    if (cursor == null) {
                        return null;
                    }
                    cursor.close();
                    return null;
                }
            }
            try {
                z a = a(cursor);
                String str = a.f18102c;
                try {
                    wVar.a(str);
                    wVar.a(a.b);
                    wVar.a(a.a);
                    b(wVar.f18097c + " errorCode : " + wVar.a);
                    if (a.a == 1000) {
                        cursor.close();
                        return str;
                    }
                    a(context);
                    if (a(context, false)) {
                        cursor.close();
                        return str;
                    }
                    b("not support, forceQuery isSupported: " + a(context, true));
                    cursor.close();
                    return str;
                } catch (Exception unused3) {
                    cursor.close();
                    return null;
                }
            } catch (Exception unused4) {
                cursor.close();
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor2 = cursor;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
    }

    public final void a(Context context) {
        synchronized (this) {
            if (this.f18101f == null) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("com.meizu.flyme.openid.ACTION_OPEN_ID_CHANGE");
                v vVar = new v();
                this.f18101f = vVar;
                context.registerReceiver(vVar, intentFilter, "com.meizu.flyme.openid.permission.OPEN_ID_CHANGE", null);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0021, code lost:
        if (r0.resolveContentProvider("com.meizu.flyme.openidsdk", 0) == null) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean a(android.content.Context r9, boolean r10) {
        /*
            r8 = this;
            java.lang.Boolean r0 = r8.f18100e
            r1 = 0
            if (r0 == 0) goto Ld
            if (r10 != 0) goto Ld
            boolean r1 = r0.booleanValue()
            goto L8e
        Ld:
            r10 = 1
            if (r9 != 0) goto L12
        L10:
            r2 = 0
            goto L24
        L12:
            android.content.pm.PackageManager r0 = r9.getPackageManager()
            if (r0 != 0) goto L1a
            r2 = 0
            goto L1b
        L1a:
            r2 = 1
        L1b:
            java.lang.String r3 = "com.meizu.flyme.openidsdk"
            android.content.pm.ProviderInfo r0 = r0.resolveContentProvider(r3, r1)
            if (r0 != 0) goto L24
            goto L10
        L24:
            if (r2 != 0) goto L30
            java.lang.String r9 = "is not Supported, for isLegalProvider : false"
            b(r9)
            java.lang.Boolean r9 = java.lang.Boolean.FALSE
            r8.f18100e = r9
            goto L8e
        L30:
            java.lang.String r0 = "content://com.meizu.flyme.openidsdk/"
            android.net.Uri r3 = android.net.Uri.parse(r0)
            r0 = 0
            android.content.ContentResolver r2 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L89
            r4 = 0
            r5 = 0
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L89
            java.lang.String r9 = "supported"
            r6[r1] = r9     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L89
            r7 = 0
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L89
            if (r0 != 0) goto L4e
            java.lang.Boolean r9 = java.lang.Boolean.FALSE     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L89
            r8.f18100e = r9     // Catch: java.lang.Throwable -> L82 java.lang.Exception -> L89
        L4e:
            com.tencent.tmsqmsp.oaid2.z r9 = a(r0)     // Catch: java.lang.Exception -> L7e java.lang.Throwable -> L82
            java.lang.String r9 = r9.f18102c     // Catch: java.lang.Exception -> L7e java.lang.Throwable -> L82
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L7e java.lang.Throwable -> L82
            r10.<init>()     // Catch: java.lang.Exception -> L7e java.lang.Throwable -> L82
            java.lang.String r2 = "querySupport, result : "
            r10.append(r2)     // Catch: java.lang.Exception -> L7e java.lang.Throwable -> L82
            r10.append(r9)     // Catch: java.lang.Exception -> L7e java.lang.Throwable -> L82
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Exception -> L7e java.lang.Throwable -> L82
            b(r10)     // Catch: java.lang.Exception -> L7e java.lang.Throwable -> L82
            java.lang.String r10 = "0"
            boolean r9 = r10.equals(r9)     // Catch: java.lang.Exception -> L7e java.lang.Throwable -> L82
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)     // Catch: java.lang.Exception -> L7e java.lang.Throwable -> L82
            r8.f18100e = r9     // Catch: java.lang.Exception -> L7e java.lang.Throwable -> L82
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Exception -> L7e java.lang.Throwable -> L82
            if (r0 == 0) goto L7d
            r0.close()
        L7d:
            return r9
        L7e:
            if (r0 == 0) goto L8e
            goto L8b
        L82:
            r9 = move-exception
            if (r0 == 0) goto L88
            r0.close()
        L88:
            throw r9
        L89:
            if (r0 == 0) goto L8e
        L8b:
            r0.close()
        L8e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.tmsqmsp.oaid2.x.a(android.content.Context, boolean):boolean");
    }
}
