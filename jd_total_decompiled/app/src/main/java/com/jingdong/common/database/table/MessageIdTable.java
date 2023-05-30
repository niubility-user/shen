package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
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
    */
    public static boolean checkRepeated(MessageId messageId) {
        SQLiteDatabase sQLiteDatabase = null;
        boolean z = false;
        try {
            try {
                sQLiteDatabase = DBHelperUtil.getDatabase();
                if (getMsgCount() >= 30) {
                    deleteOldMsg();
                }
                if (isExsit(messageId.getMsgId())) {
                    z = true;
                } else {
                    add(getContentValues(messageId));
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2.getMessage());
            }
        } finally {
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
        }
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
    */
    private static synchronized int getMsgCount() {
        int i2;
        synchronized (MessageIdTable.class) {
            Cursor cursor = null;
            try {
                cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, null, null, null, null, null, null);
                i2 = cursor != null ? cursor.getCount() : 0;
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
        return i2;
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
    */
    private static synchronized boolean isExsit(String str) {
        boolean z;
        synchronized (MessageIdTable.class) {
            z = false;
            Cursor cursor = null;
            try {
                cursor = DBHelperUtil.getDatabase().query(TABLE_NAME, null, "messageId = '" + str + "'", null, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    OKLog.d(TAG, "isExsit = true");
                    z = true;
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
        return z;
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS MessageIdTable (id INTEGER PRIMARY KEY AUTOINCREMENT,messageId VARCHAR)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }
}
