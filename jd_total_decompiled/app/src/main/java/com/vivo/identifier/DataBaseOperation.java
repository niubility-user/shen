package com.vivo.identifier;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    */
    public boolean insert(int i2, String str, ContentValues[] contentValuesArr) {
        String valueOf;
        String str2;
        Uri parse;
        int bulkInsert;
        if (i2 == 6) {
            valueOf = String.valueOf(str);
            str2 = "content://com.vivo.vms.IdProvider/IdentifierId/OAIDBLACK_";
        } else if (i2 != 7) {
            parse = null;
            if (parse != null) {
                return false;
            }
            try {
                bulkInsert = this.mContext.getContentResolver().bulkInsert(parse, contentValuesArr);
                "insert:".concat(String.valueOf(bulkInsert));
            } catch (Exception unused) {
            }
            return bulkInsert != 0;
        } else {
            valueOf = String.valueOf(str);
            str2 = "content://com.vivo.vms.IdProvider/IdentifierId/STATISTICS_";
        }
        parse = Uri.parse(str2.concat(valueOf));
        if (parse != null) {
        }
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
    */
    public String query(int i2, String str) {
        String str2;
        Uri parse;
        Cursor cursor;
        String valueOf;
        String str3;
        Cursor cursor2 = null;
        r0 = null;
        r0 = null;
        r0 = null;
        String str4 = null;
        if (i2 != 0) {
            if (i2 == 1) {
                valueOf = String.valueOf(str);
                str3 = "content://com.vivo.vms.IdProvider/IdentifierId/VAID_";
            } else if (i2 == 2) {
                valueOf = String.valueOf(str);
                str3 = "content://com.vivo.vms.IdProvider/IdentifierId/AAID_";
            } else if (i2 == 3) {
                str2 = "content://com.vivo.vms.IdProvider/IdentifierId/UDID";
            } else if (i2 == 4) {
                valueOf = String.valueOf(str);
                str3 = "content://com.vivo.vms.IdProvider/IdentifierId/OAIDSTATUS_";
            } else if (i2 != 5) {
                parse = null;
                if (parse != null) {
                    return null;
                }
                try {
                    cursor = this.mContext.getContentResolver().query(parse, null, null, null, null);
                    if (cursor != null) {
                        try {
                            if (cursor.moveToNext()) {
                                str4 = cursor.getString(cursor.getColumnIndex("value"));
                            }
                        } catch (Exception unused) {
                        } catch (Throwable th) {
                            th = th;
                            cursor2 = cursor;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            throw th;
                        }
                    }
                } catch (Exception unused2) {
                    cursor = null;
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                str2 = URI_GUID;
            }
            str2 = str3.concat(valueOf);
        } else {
            str2 = "content://com.vivo.vms.IdProvider/IdentifierId/OAID";
        }
        parse = Uri.parse(str2);
        if (parse != null) {
        }
    }
}
