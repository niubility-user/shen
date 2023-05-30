package com.tencent.tmsqmsp.oaid2;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/* loaded from: classes9.dex */
public class l0 {
    public Context a;

    public l0(Context context) {
        this.a = context;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public String a(int i2, String str) {
        String str2;
        Uri parse;
        Cursor query;
        StringBuilder sb;
        String str3;
        if (i2 != 0) {
            if (i2 == 1) {
                sb = new StringBuilder();
                str3 = "content://com.vivo.vms.IdProvider/IdentifierId/VAID_";
            } else if (i2 != 2) {
                parse = null;
                query = this.a.getContentResolver().query(parse, null, null, null, null);
                if (query != null) {
                    c.b("return cursor is null,return");
                } else {
                    r0 = query.moveToNext() ? query.getString(query.getColumnIndex("value")) : null;
                    query.close();
                }
                return r0;
            } else {
                sb = new StringBuilder();
                str3 = "content://com.vivo.vms.IdProvider/IdentifierId/AAID_";
            }
            sb.append(str3);
            sb.append(str);
            str2 = sb.toString();
        } else {
            str2 = "content://com.vivo.vms.IdProvider/IdentifierId/OAID";
        }
        parse = Uri.parse(str2);
        query = this.a.getContentResolver().query(parse, null, null, null, null);
        if (query != null) {
        }
        return r0;
    }
}
