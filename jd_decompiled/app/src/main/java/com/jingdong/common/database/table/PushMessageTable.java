package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.common.entity.BaseMessage;
import com.jingdong.jdsdk.db.IJdTable;
import com.jingdong.jdsdk.utils.DBHelperUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class PushMessageTable implements IJdTable {
    public static final int MAX_SAVE_NUM = 1000;
    private static final String TAG = "PushMessageTable";
    public static final String TB_CLOUMN_MESSAGE_ID = "message_id";
    public static final String TB_CLOUMN_MESSAGE_STATUS = "status";
    public static final String TB_PUSH_MESSAGE_TABLE = "PushMessageTable";
    public static ArrayList<BaseMessage> existMessages = new ArrayList<>();

    private static void deleteMessage(BaseMessage baseMessage) {
        try {
            try {
                DBHelperUtil.getDatabase().delete("PushMessageTable", "message_id = ? ", new String[]{baseMessage.getMsgId()});
                existMessages.remove(baseMessage);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("PushMessageTable", e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0044, code lost:
        if (r1.isClosed() == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0059, code lost:
        if (r1.isClosed() == false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005b, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList<com.jingdong.common.entity.BaseMessage> getHasNotifyMessages() {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.jdsdk.utils.DBHelperUtil.getDatabase()     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            java.lang.String r3 = "SELECT message_id,status FROM PushMessageTable"
            android.database.Cursor r1 = r2.rawQuery(r3, r1)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            if (r1 != 0) goto L21
            if (r1 == 0) goto L1d
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L1d
            r1.close()
        L1d:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L21:
            boolean r2 = r1.moveToNext()     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            if (r2 == 0) goto L3e
            com.jingdong.common.entity.BaseMessage r2 = new com.jingdong.common.entity.BaseMessage     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r3 = 0
            java.lang.String r3 = r1.getString(r3)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r4 = 1
            int r4 = r1.getInt(r4)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            r0.add(r2)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L49
            goto L21
        L3e:
            if (r1 == 0) goto L5e
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L5e
            goto L5b
        L47:
            r0 = move-exception
            goto L62
        L49:
            r2 = move-exception
            boolean r3 = com.jingdong.sdk.oklog.OKLog.E     // Catch: java.lang.Throwable -> L47
            if (r3 == 0) goto L53
            java.lang.String r3 = "PushMessageTable"
            com.jingdong.sdk.oklog.OKLog.e(r3, r2)     // Catch: java.lang.Throwable -> L47
        L53:
            if (r1 == 0) goto L5e
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L5e
        L5b:
            r1.close()
        L5e:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            return r0
        L62:
            if (r1 == 0) goto L6d
            boolean r2 = r1.isClosed()
            if (r2 != 0) goto L6d
            r1.close()
        L6d:
            com.jingdong.jdsdk.utils.DBHelperUtil.closeDatabase()
            goto L72
        L71:
            throw r0
        L72:
            goto L71
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.database.table.PushMessageTable.getHasNotifyMessages():java.util.ArrayList");
    }

    public static void insertNotifyMessages(ArrayList<BaseMessage> arrayList) {
        if (arrayList == null) {
            return;
        }
        existMessages = getHasNotifyMessages();
        Iterator<BaseMessage> it = arrayList.iterator();
        while (it.hasNext()) {
            BaseMessage next = it.next();
            if (existMessages.size() >= 1000) {
                deleteMessage(existMessages.remove(0));
            }
            updateMessage(next);
        }
    }

    public static boolean isBroadcastExsit(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (existMessages.size() < 1) {
            existMessages = getHasNotifyMessages();
        }
        return existMessages.contains(new BaseMessage(str, null));
    }

    public static boolean isBroadcastMessageUnread(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (existMessages.size() < 1) {
            existMessages = getHasNotifyMessages();
        }
        if (existMessages.size() < 1) {
            return true;
        }
        Iterator<BaseMessage> it = existMessages.iterator();
        while (it.hasNext()) {
            BaseMessage next = it.next();
            if (str.equals(next.getMsgId()) && next.getStatus().intValue() == 0) {
                return true;
            }
        }
        return false;
    }

    public static void updateMessage(BaseMessage baseMessage) {
        try {
            try {
                SQLiteDatabase database = DBHelperUtil.getDatabase();
                if (existMessages.size() < 1) {
                    existMessages = getHasNotifyMessages();
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("status", baseMessage.getStatus());
                contentValues.put(TB_CLOUMN_MESSAGE_ID, baseMessage.getMsgId());
                if (!existMessages.contains(baseMessage)) {
                    database.insert("PushMessageTable", null, contentValues);
                    existMessages.add(baseMessage);
                } else {
                    database.update("PushMessageTable", contentValues, "message_id = ? ", new String[]{baseMessage.getMsgId()});
                    existMessages.remove(baseMessage);
                    existMessages.add(baseMessage);
                }
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("PushMessageTable", e2);
                }
            }
        } finally {
            DBHelperUtil.closeDatabase();
        }
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE PushMessageTable('_id' INTEGER PRIMARY KEY  NOT NULL ,message_id TEXT , status INTEGER ) ");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        sQLiteDatabase.execSQL("drop table if exists PushMessageTable");
    }
}
