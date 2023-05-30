package com.jingdong.common.database.table;

import android.content.ContentValues;
import android.database.Cursor;
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
    */
    public static ArrayList<BaseMessage> getHasNotifyMessages() {
        ArrayList<BaseMessage> arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            try {
                cursor = DBHelperUtil.getDatabase().rawQuery("SELECT message_id,status FROM PushMessageTable", null);
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e("PushMessageTable", e2);
                }
                if (0 != 0) {
                }
            }
            if (cursor == null) {
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                DBHelperUtil.closeDatabase();
                return arrayList;
            }
            while (cursor.moveToNext()) {
                arrayList.add(new BaseMessage(cursor.getString(0), Integer.valueOf(cursor.getInt(1))));
            }
            if (cursor != null) {
            }
            DBHelperUtil.closeDatabase();
            return arrayList;
        } catch (Throwable th) {
            if (0 != 0 && !cursor.isClosed()) {
                cursor.close();
            }
            DBHelperUtil.closeDatabase();
            throw th;
        }
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
