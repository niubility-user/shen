package com.jingdong.common.database.table.koc;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public final class KocDraftDao_Impl implements KocDraftDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<KocDraftEntity> __deletionAdapterOfKocDraftEntity;
    private final EntityInsertionAdapter<KocDraftEntity> __insertionAdapterOfKocDraftEntity;
    private final EntityDeletionOrUpdateAdapter<KocDraftUpdateEntity> __updateAdapterOfKocDraftUpdateEntityAsKocDraftEntity;

    public KocDraftDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfKocDraftEntity = new EntityInsertionAdapter<KocDraftEntity>(roomDatabase) { // from class: com.jingdong.common.database.table.koc.KocDraftDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `KocDraftEntity` (`id`,`createTime`,`lastModifyTime`,`pin`,`data`,`bId`,`bType`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, KocDraftEntity kocDraftEntity) {
                supportSQLiteStatement.bindLong(1, kocDraftEntity.id);
                supportSQLiteStatement.bindLong(2, kocDraftEntity.createTime);
                supportSQLiteStatement.bindLong(3, kocDraftEntity.lastModifyTime);
                String str = kocDraftEntity.pin;
                if (str == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, str);
                }
                String str2 = kocDraftEntity.data;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, str2);
                }
                String str3 = kocDraftEntity.bId;
                if (str3 == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, str3);
                }
                String str4 = kocDraftEntity.bType;
                if (str4 == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, str4);
                }
            }
        };
        this.__deletionAdapterOfKocDraftEntity = new EntityDeletionOrUpdateAdapter<KocDraftEntity>(roomDatabase) { // from class: com.jingdong.common.database.table.koc.KocDraftDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `KocDraftEntity` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, KocDraftEntity kocDraftEntity) {
                supportSQLiteStatement.bindLong(1, kocDraftEntity.id);
            }
        };
        this.__updateAdapterOfKocDraftUpdateEntityAsKocDraftEntity = new EntityDeletionOrUpdateAdapter<KocDraftUpdateEntity>(roomDatabase) { // from class: com.jingdong.common.database.table.koc.KocDraftDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `KocDraftEntity` SET `id` = ?,`lastModifyTime` = ?,`data` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, KocDraftUpdateEntity kocDraftUpdateEntity) {
                supportSQLiteStatement.bindLong(1, kocDraftUpdateEntity.id);
                supportSQLiteStatement.bindLong(2, kocDraftUpdateEntity.lastModifyTime);
                String str = kocDraftUpdateEntity.data;
                if (str == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, str);
                }
                supportSQLiteStatement.bindLong(4, kocDraftUpdateEntity.id);
            }
        };
    }

    @Override // com.jingdong.common.database.table.koc.KocDraftDao
    public int delete(KocDraftEntity kocDraftEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int handle = this.__deletionAdapterOfKocDraftEntity.handle(kocDraftEntity) + 0;
            this.__db.setTransactionSuccessful();
            return handle;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.jingdong.common.database.table.koc.KocDraftDao
    public long insert(KocDraftEntity kocDraftEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfKocDraftEntity.insertAndReturnId(kocDraftEntity);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.jingdong.common.database.table.koc.KocDraftDao
    public List<KocDraftEntity> query(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from KocDraftEntity where pin = ? order by lastModifyTime DESC", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "createTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "lastModifyTime");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "pin");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "data");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "bId");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "bType");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                KocDraftEntity kocDraftEntity = new KocDraftEntity();
                kocDraftEntity.id = query.getLong(columnIndexOrThrow);
                kocDraftEntity.createTime = query.getLong(columnIndexOrThrow2);
                kocDraftEntity.lastModifyTime = query.getLong(columnIndexOrThrow3);
                kocDraftEntity.pin = query.getString(columnIndexOrThrow4);
                kocDraftEntity.data = query.getString(columnIndexOrThrow5);
                kocDraftEntity.bId = query.getString(columnIndexOrThrow6);
                kocDraftEntity.bType = query.getString(columnIndexOrThrow7);
                arrayList.add(kocDraftEntity);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.jingdong.common.database.table.koc.KocDraftDao
    public int queryCount(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select COUNT(*) from KocDraftEntity where pin = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.jingdong.common.database.table.koc.KocDraftDao
    public int update(KocDraftUpdateEntity kocDraftUpdateEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            int handle = this.__updateAdapterOfKocDraftUpdateEntityAsKocDraftEntity.handle(kocDraftUpdateEntity) + 0;
            this.__db.setTransactionSuccessful();
            return handle;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.jingdong.common.database.table.koc.KocDraftDao
    public KocDraftEntity query(String str, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from KocDraftEntity where pin = ? and id = ?", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j2);
        this.__db.assertNotSuspendingTransaction();
        KocDraftEntity kocDraftEntity = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "createTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "lastModifyTime");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "pin");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "data");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "bId");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "bType");
            if (query.moveToFirst()) {
                kocDraftEntity = new KocDraftEntity();
                kocDraftEntity.id = query.getLong(columnIndexOrThrow);
                kocDraftEntity.createTime = query.getLong(columnIndexOrThrow2);
                kocDraftEntity.lastModifyTime = query.getLong(columnIndexOrThrow3);
                kocDraftEntity.pin = query.getString(columnIndexOrThrow4);
                kocDraftEntity.data = query.getString(columnIndexOrThrow5);
                kocDraftEntity.bId = query.getString(columnIndexOrThrow6);
                kocDraftEntity.bType = query.getString(columnIndexOrThrow7);
            }
            return kocDraftEntity;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.jingdong.common.database.table.koc.KocDraftDao
    public KocDraftEntity query(String str, String str2, String str3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from KocDraftEntity where pin = ? and bType = ? and bId = ?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        if (str3 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str3);
        }
        this.__db.assertNotSuspendingTransaction();
        KocDraftEntity kocDraftEntity = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "createTime");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "lastModifyTime");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "pin");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "data");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "bId");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "bType");
            if (query.moveToFirst()) {
                kocDraftEntity = new KocDraftEntity();
                kocDraftEntity.id = query.getLong(columnIndexOrThrow);
                kocDraftEntity.createTime = query.getLong(columnIndexOrThrow2);
                kocDraftEntity.lastModifyTime = query.getLong(columnIndexOrThrow3);
                kocDraftEntity.pin = query.getString(columnIndexOrThrow4);
                kocDraftEntity.data = query.getString(columnIndexOrThrow5);
                kocDraftEntity.bId = query.getString(columnIndexOrThrow6);
                kocDraftEntity.bType = query.getString(columnIndexOrThrow7);
            }
            return kocDraftEntity;
        } finally {
            query.close();
            acquire.release();
        }
    }
}
