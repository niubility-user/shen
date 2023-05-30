package c.t.m.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.google.common.net.HttpHeaders;

/* loaded from: classes.dex */
public class r {
    public Context a;

    public r(Context context) {
        this.a = context;
    }

    @SuppressLint({HttpHeaders.RANGE})
    public String a() {
        try {
            Cursor query = this.a.getContentResolver().query(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), null, null, null, null);
            if (query != null) {
                r0 = query.moveToNext() ? query.getString(query.getColumnIndex("value")) : null;
                query.close();
            }
        } catch (Exception unused) {
        }
        return r0;
    }
}
