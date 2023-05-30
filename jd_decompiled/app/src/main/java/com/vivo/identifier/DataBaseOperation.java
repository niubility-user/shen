package com.vivo.identifier;

import android.content.Context;
import android.net.Uri;

/* loaded from: classes11.dex */
public class DataBaseOperation {
    private static final String AAID_FLAG = "AAID";
    private static final String ID_VALUE = "value";
    private static final String OAIDBLACK_FLAG = "OAIDBLACK";
    private static final String OAIDSTATUS_FLAG = "OAIDSTATUS";
    private static final String OAID_FLAG = "OAID";
    private static final String REPORT_STATISTICS = "STATISTICS";
    private static final String TAG = "VMS_SDK_DB";
    private static final int TYPE_AAID = 2;
    private static final int TYPE_GUID = 5;
    private static final int TYPE_OAID = 0;
    private static final int TYPE_OAIDBLACK = 6;
    private static final int TYPE_OAIDSTATUS = 4;
    private static final int TYPE_REPORT_STATISTICS = 7;
    private static final int TYPE_UDID = 3;
    private static final int TYPE_VAID = 1;
    private static final String UDID_FLAG = "UDID";
    private static final String URI_BASE = "content://com.vivo.vms.IdProvider/IdentifierId";
    private static final String URI_GUID = "content://com.vivo.abe.exidentifier/guid";
    private static final String VAID_FLAG = "VAID";
    private Context mContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataBaseOperation(Context context) {
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean delete(int i2, String str, String str2, String str3) {
        int delete;
        Uri parse = i2 != 6 ? null : Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAIDBLACK_".concat(String.valueOf(str)));
        if (parse == null) {
            return false;
        }
        try {
            delete = this.mContext.getContentResolver().delete(parse, "packageName=? and uid=?", new String[]{str2, str3});
            "delete:".concat(String.valueOf(delete));
        } catch (Exception unused) {
        }
        return delete != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0020 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0021 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean insert(int r2, java.lang.String r3, android.content.ContentValues[] r4) {
        /*
            r1 = this;
            r0 = 6
            if (r2 == r0) goto Lf
            r0 = 7
            if (r2 == r0) goto L8
            r2 = 0
            goto L1d
        L8:
            java.lang.String r2 = java.lang.String.valueOf(r3)
            java.lang.String r3 = "content://com.vivo.vms.IdProvider/IdentifierId/STATISTICS_"
            goto L15
        Lf:
            java.lang.String r2 = java.lang.String.valueOf(r3)
            java.lang.String r3 = "content://com.vivo.vms.IdProvider/IdentifierId/OAIDBLACK_"
        L15:
            java.lang.String r2 = r3.concat(r2)
            android.net.Uri r2 = android.net.Uri.parse(r2)
        L1d:
            r3 = 0
            if (r2 != 0) goto L21
            return r3
        L21:
            android.content.Context r0 = r1.mContext     // Catch: java.lang.Exception -> L38
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Exception -> L38
            int r2 = r0.bulkInsert(r2, r4)     // Catch: java.lang.Exception -> L38
            java.lang.String r4 = "insert:"
            java.lang.String r0 = java.lang.String.valueOf(r2)     // Catch: java.lang.Exception -> L38
            r4.concat(r0)     // Catch: java.lang.Exception -> L38
            if (r2 == 0) goto L38
            r2 = 1
            return r2
        L38:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.identifier.DataBaseOperation.insert(int, java.lang.String, android.content.ContentValues[]):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0062, code lost:
        if (r8 != null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0064, code lost:
        r8.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0070, code lost:
        if (r8 == null) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0073, code lost:
        return r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String query(int r8, java.lang.String r9) {
        /*
            r7 = this;
            r0 = 0
            if (r8 == 0) goto L33
            r1 = 1
            if (r8 == r1) goto L28
            r1 = 2
            if (r8 == r1) goto L21
            r1 = 3
            if (r8 == r1) goto L1e
            r1 = 4
            if (r8 == r1) goto L17
            r9 = 5
            if (r8 == r9) goto L14
            r2 = r0
            goto L3a
        L14:
            java.lang.String r8 = "content://com.vivo.abe.exidentifier/guid"
            goto L35
        L17:
            java.lang.String r8 = java.lang.String.valueOf(r9)
            java.lang.String r9 = "content://com.vivo.vms.IdProvider/IdentifierId/OAIDSTATUS_"
            goto L2e
        L1e:
            java.lang.String r8 = "content://com.vivo.vms.IdProvider/IdentifierId/UDID"
            goto L35
        L21:
            java.lang.String r8 = java.lang.String.valueOf(r9)
            java.lang.String r9 = "content://com.vivo.vms.IdProvider/IdentifierId/AAID_"
            goto L2e
        L28:
            java.lang.String r8 = java.lang.String.valueOf(r9)
            java.lang.String r9 = "content://com.vivo.vms.IdProvider/IdentifierId/VAID_"
        L2e:
            java.lang.String r8 = r9.concat(r8)
            goto L35
        L33:
            java.lang.String r8 = "content://com.vivo.vms.IdProvider/IdentifierId/OAID"
        L35:
            android.net.Uri r8 = android.net.Uri.parse(r8)
            r2 = r8
        L3a:
            if (r2 != 0) goto L3d
            return r0
        L3d:
            android.content.Context r8 = r7.mContext     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6f
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6f
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L68 java.lang.Exception -> L6f
            if (r8 == 0) goto L62
            boolean r9 = r8.moveToNext()     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L70
            if (r9 == 0) goto L62
            java.lang.String r9 = "value"
            int r9 = r8.getColumnIndex(r9)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L70
            java.lang.String r9 = r8.getString(r9)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L70
            r0 = r9
            goto L62
        L5f:
            r9 = move-exception
            r0 = r8
            goto L69
        L62:
            if (r8 == 0) goto L73
        L64:
            r8.close()
            goto L73
        L68:
            r9 = move-exception
        L69:
            if (r0 == 0) goto L6e
            r0.close()
        L6e:
            throw r9
        L6f:
            r8 = r0
        L70:
            if (r8 == 0) goto L73
            goto L64
        L73:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.identifier.DataBaseOperation.query(int, java.lang.String):java.lang.String");
    }
}
