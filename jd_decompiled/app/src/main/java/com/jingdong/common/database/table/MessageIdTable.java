package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.utils.MessageId;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class MessageIdTable implements IJdTable {
    private static final String KEY_ID = "id";
    public static final int MAX_SAVE_NUM = 30;
    private static final String MESSAGE_ID = "messageId";
    private static final String TABLE_NAME = "MessageIdTable";
    private static final String TAG = "PushMessageIdTable";

    private static synchronized void add(ContentValues contentValues) {
        synchronized (MessageIdTable.class) {
            try {
                DBHelperUtil.getDatabase().insert(TABLE_NAME, null, contentValues);
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0036, code lost:
        if (r0 == null) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean checkRepeated(com.jingdong.common.utils.MessageId r4) {
        /*
            r0 = 0
            r1 = 0
            android.database.sqlite.SQLiteDatabase r0 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            int r2 = getMsgCount()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            r3 = 30
            if (r2 < r3) goto L11
            deleteOldMsg()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
        L11:
            java.lang.String r2 = r4.getMsgId()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            boolean r2 = isExsit(r2)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            if (r2 == 0) goto L1d
            r1 = 1
            goto L24
        L1d:
            android.content.ContentValues r4 = getContentValues(r4)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
            add(r4)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L2c
        L24:
            if (r0 == 0) goto L39
        L26:
            r0.close()
            goto L39
        L2a:
            r4 = move-exception
            goto L3a
        L2c:
            r4 = move-exception
            java.lang.String r2 = "PushMessageIdTable"
            java.lang.String r4 = r4.getMessage()     // Catch: java.lang.Throwable -> L2a
            com.jingdong.sdk.oklog.OKLog.e(r2, r4)     // Catch: java.lang.Throwable -> L2a
            if (r0 == 0) goto L39
            goto L26
        L39:
            return r1
        L3a:
            if (r0 == 0) goto L3f
            r0.close()
        L3f:
            goto L41
        L40:
            throw r4
        L41:
            goto L40
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MessageIdTable.checkRepeated(com.jingdong.common.utils.MessageId):boolean");
    }

    private static synchronized void deleteOldMsg() {
        synchronized (MessageIdTable.class) {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                OKLog.d(TAG, "sql = DELETE FROM MessageIdTable WHERE id =(SELECT MIN(id) FROM MessageIdTable);");
                database.execSQL("DELETE FROM MessageIdTable WHERE id =(SELECT MIN(id) FROM MessageIdTable);");
            } catch (Exception e2) {
                OKLog.e(TAG, e2.getMessage());
            }
        }
    }

    private static synchronized ContentValues getContentValues(MessageId messageId) throws JSONException {
        ContentValues contentValues;
        synchronized (MessageIdTable.class) {
            ContentValues contentValues2 = null;
            try {
                contentValues = new ContentValues();
                try {
                    contentValues.put("messageId", messageId.getMsgId());
                } catch (Exception e2) {
                    e = e2;
                    contentValues2 = contentValues;
                    OKLog.e(TAG, e);
                    contentValues = contentValues2;
                    return contentValues;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }
        return contentValues;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
        if (r1 != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0022, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002e, code lost:
        if (r1 == null) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static synchronized int getMsgCount() {
        /*
            java.lang.Class<com.jingdong.common.database.table.MessageIdTable> r0 = com.jingdong.common.database.table.MessageIdTable.class
            monitor-enter(r0)
            r1 = 0
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            java.lang.String r4 = "MessageIdTable"
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
            if (r1 == 0) goto L1b
            int r2 = r1.getCount()     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
        L1b:
            if (r1 == 0) goto L20
            r1.close()     // Catch: java.lang.Throwable -> L26 java.lang.Exception -> L28
        L20:
            if (r1 == 0) goto L31
        L22:
            r1.close()     // Catch: java.lang.Throwable -> L39
            goto L31
        L26:
            r2 = move-exception
            goto L33
        L28:
            r3 = move-exception
            java.lang.String r4 = "PushMessageIdTable"
            com.jingdong.sdk.oklog.OKLog.e(r4, r3)     // Catch: java.lang.Throwable -> L26
            if (r1 == 0) goto L31
            goto L22
        L31:
            monitor-exit(r0)
            return r2
        L33:
            if (r1 == 0) goto L38
            r1.close()     // Catch: java.lang.Throwable -> L39
        L38:
            throw r2     // Catch: java.lang.Throwable -> L39
        L39:
            r1 = move-exception
            monitor-exit(r0)
            goto L3d
        L3c:
            throw r1
        L3d:
            goto L3c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MessageIdTable.getMsgCount():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x003a, code lost:
        if (r11 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x003c, code lost:
        r11.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0048, code lost:
        if (r11 == null) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static synchronized boolean isExsit(java.lang.String r11) {
        /*
            java.lang.Class<com.jingdong.common.database.table.MessageIdTable> r0 = com.jingdong.common.database.table.MessageIdTable.class
            monitor-enter(r0)
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L53
            r2.<init>()     // Catch: java.lang.Throwable -> L53
            java.lang.String r3 = "messageId = '"
            r2.append(r3)     // Catch: java.lang.Throwable -> L53
            r2.append(r11)     // Catch: java.lang.Throwable -> L53
            java.lang.String r11 = "'"
            r2.append(r11)     // Catch: java.lang.Throwable -> L53
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> L53
            r11 = 0
            android.database.sqlite.SQLiteDatabase r3 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            java.lang.String r4 = "MessageIdTable"
            r5 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            android.database.Cursor r11 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            if (r11 == 0) goto L3a
            int r2 = r11.getCount()     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            if (r2 <= 0) goto L3a
            java.lang.String r2 = "PushMessageIdTable"
            java.lang.String r3 = "isExsit = true"
            com.jingdong.sdk.oklog.OKLog.d(r2, r3)     // Catch: java.lang.Throwable -> L40 java.lang.Exception -> L42
            r1 = 1
        L3a:
            if (r11 == 0) goto L4b
        L3c:
            r11.close()     // Catch: java.lang.Throwable -> L53
            goto L4b
        L40:
            r1 = move-exception
            goto L4d
        L42:
            r2 = move-exception
            java.lang.String r3 = "PushMessageIdTable"
            com.jingdong.sdk.oklog.OKLog.e(r3, r2)     // Catch: java.lang.Throwable -> L40
            if (r11 == 0) goto L4b
            goto L3c
        L4b:
            monitor-exit(r0)
            return r1
        L4d:
            if (r11 == 0) goto L52
            r11.close()     // Catch: java.lang.Throwable -> L53
        L52:
            throw r1     // Catch: java.lang.Throwable -> L53
        L53:
            r11 = move-exception
            monitor-exit(r0)
            goto L57
        L56:
            throw r11
        L57:
            goto L56
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.MessageIdTable.isExsit(java.lang.String):boolean");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS MessageIdTable (id INTEGER PRIMARY KEY AUTOINCREMENT,messageId VARCHAR)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }
}
