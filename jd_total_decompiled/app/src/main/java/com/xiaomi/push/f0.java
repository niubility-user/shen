package com.xiaomi.push;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
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
    */
    private String b(String str) {
        Throwable th;
        Cursor cursor;
        String str2 = null;
        try {
            cursor = this.f18599g.getContentResolver().query(Uri.parse(str), null, null, null, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToNext()) {
                        str2 = cursor.getString(cursor.getColumnIndex("value"));
                    }
                } catch (Exception unused) {
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        } catch (Exception unused2) {
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
        }
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
