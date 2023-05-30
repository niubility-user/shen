package c.t.m.g;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import c.t.m.g.m5;

/* loaded from: classes.dex */
public class k6 {
    public Context a;

    public k6(Context context) {
        this.a = context;
    }

    public final String a(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex("value");
            r0 = columnIndex > 0 ? cursor.getString(columnIndex) : null;
            int columnIndex2 = cursor.getColumnIndex("code");
            if (columnIndex2 > 0) {
                cursor.getInt(columnIndex2);
            }
            int columnIndex3 = cursor.getColumnIndex("expired");
            if (columnIndex3 > 0) {
                cursor.getLong(columnIndex3);
            }
        }
        return r0;
    }

    public void b(m5.b bVar) {
        try {
            this.a.getPackageManager().getPackageInfo("com.meizu.flyme.openidsdk", 0);
        } catch (Exception unused) {
        }
        try {
            Cursor query = this.a.getContentResolver().query(Uri.parse("content://com.meizu.flyme.openidsdk/"), null, null, new String[]{"oaid"}, null);
            String a = a(query);
            boolean c2 = c();
            if (bVar != null) {
                bVar.a(a, c2);
            }
            query.close();
        } catch (Throwable unused2) {
        }
    }

    public boolean c() {
        try {
            PackageManager packageManager = this.a.getPackageManager();
            if (packageManager != null) {
                return packageManager.resolveContentProvider("com.meizu.flyme.openidsdk", 0) != null;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
