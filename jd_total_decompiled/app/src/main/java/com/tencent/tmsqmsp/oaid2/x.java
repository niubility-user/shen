package com.tencent.tmsqmsp.oaid2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

/* loaded from: classes9.dex */
public class x {

    /* renamed from: g  reason: collision with root package name */
    public static volatile x f18098g;

    /* renamed from: e  reason: collision with root package name */
    public Boolean f18100e;

    /* renamed from: f  reason: collision with root package name */
    public BroadcastReceiver f18101f;
    public w a = new w("udid");
    public w b = new w("oaid");
    public w d = new w("vaid");

    /* renamed from: c  reason: collision with root package name */
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

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0021, code lost:
        if (r0.resolveContentProvider("com.meizu.flyme.openidsdk", 0) == null) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean a(Context context, boolean z) {
        boolean z2;
        Boolean bool = this.f18100e;
        if (bool == null || z) {
            if (context != null) {
                PackageManager packageManager = context.getPackageManager();
                z2 = packageManager != null;
            }
            z2 = false;
            if (!z2) {
                b("is not Supported, for isLegalProvider : false");
                this.f18100e = Boolean.FALSE;
                return false;
            }
            Cursor cursor = null;
            try {
                try {
                    cursor = context.getContentResolver().query(Uri.parse("content://com.meizu.flyme.openidsdk/"), null, null, new String[]{"supported"}, null);
                    if (cursor == null) {
                        this.f18100e = Boolean.FALSE;
                    }
                } catch (Exception unused) {
                    if (cursor == null) {
                        return false;
                    }
                }
                try {
                    String str = a(cursor).f18102c;
                    b("querySupport, result : " + str);
                    Boolean valueOf = Boolean.valueOf("0".equals(str));
                    this.f18100e = valueOf;
                    boolean booleanValue = valueOf.booleanValue();
                    if (cursor != null) {
                        cursor.close();
                    }
                    return booleanValue;
                } catch (Exception unused2) {
                    if (cursor == null) {
                        return false;
                    }
                    return false;
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return bool.booleanValue();
    }
}
