package com.jingdong.sdk.jdroom.a;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;

/* loaded from: classes7.dex */
public class b implements com.jingdong.sdk.jdroom.a.a {
    private final RoomDatabase a;
    private final EntityInsertionAdapter b;

    /* renamed from: c  reason: collision with root package name */
    private final SharedSQLiteStatement f14965c;
    private final SharedSQLiteStatement d;

    /* loaded from: classes7.dex */
    class a extends EntityInsertionAdapter<com.jingdong.sdk.jdroom.a.c> {
        a(b bVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityInsertionAdapter
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, com.jingdong.sdk.jdroom.a.c cVar) {
            supportSQLiteStatement.bindLong(1, cVar.a);
            String str = cVar.b;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            String str2 = cVar.f14966c;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str2);
            }
            String str3 = cVar.d;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, str3);
            }
            String str4 = cVar.f14967e;
            if (str4 == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, str4);
            }
            String str5 = cVar.f14968f;
            if (str5 == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, str5);
            }
            supportSQLiteStatement.bindDouble(7, cVar.f14969g);
            supportSQLiteStatement.bindDouble(8, cVar.f14970h);
            supportSQLiteStatement.bindDouble(9, cVar.f14971i);
            String str6 = cVar.f14972j;
            if (str6 == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, str6);
            }
            String str7 = cVar.f14973k;
            if (str7 == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, str7);
            }
            String str8 = cVar.f14974l;
            if (str8 == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, str8);
            }
            supportSQLiteStatement.bindLong(13, cVar.f14975m);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "INSERT OR REPLACE INTO `JD_ReminderNewTable`(`_id`,`businessType`,`reminderShowTag`,`identificationId`,`reminderTitle`,`reminderImgUrl`,`startTimeMillis`,`notificationTimeMillis`,`insertTime`,`jump`,`extra`,`more`,`requestCode`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
        }
    }

    /* renamed from: com.jingdong.sdk.jdroom.a.b$b  reason: collision with other inner class name */
    /* loaded from: classes7.dex */
    class C0719b extends EntityDeletionOrUpdateAdapter<com.jingdong.sdk.jdroom.a.c> {
        C0719b(b bVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, com.jingdong.sdk.jdroom.a.c cVar) {
            supportSQLiteStatement.bindLong(1, cVar.a);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM `JD_ReminderNewTable` WHERE `_id` = ?";
        }
    }

    /* loaded from: classes7.dex */
    class c extends EntityDeletionOrUpdateAdapter<com.jingdong.sdk.jdroom.a.c> {
        c(b bVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void bind(SupportSQLiteStatement supportSQLiteStatement, com.jingdong.sdk.jdroom.a.c cVar) {
            supportSQLiteStatement.bindLong(1, cVar.a);
            String str = cVar.b;
            if (str == null) {
                supportSQLiteStatement.bindNull(2);
            } else {
                supportSQLiteStatement.bindString(2, str);
            }
            String str2 = cVar.f14966c;
            if (str2 == null) {
                supportSQLiteStatement.bindNull(3);
            } else {
                supportSQLiteStatement.bindString(3, str2);
            }
            String str3 = cVar.d;
            if (str3 == null) {
                supportSQLiteStatement.bindNull(4);
            } else {
                supportSQLiteStatement.bindString(4, str3);
            }
            String str4 = cVar.f14967e;
            if (str4 == null) {
                supportSQLiteStatement.bindNull(5);
            } else {
                supportSQLiteStatement.bindString(5, str4);
            }
            String str5 = cVar.f14968f;
            if (str5 == null) {
                supportSQLiteStatement.bindNull(6);
            } else {
                supportSQLiteStatement.bindString(6, str5);
            }
            supportSQLiteStatement.bindDouble(7, cVar.f14969g);
            supportSQLiteStatement.bindDouble(8, cVar.f14970h);
            supportSQLiteStatement.bindDouble(9, cVar.f14971i);
            String str6 = cVar.f14972j;
            if (str6 == null) {
                supportSQLiteStatement.bindNull(10);
            } else {
                supportSQLiteStatement.bindString(10, str6);
            }
            String str7 = cVar.f14973k;
            if (str7 == null) {
                supportSQLiteStatement.bindNull(11);
            } else {
                supportSQLiteStatement.bindString(11, str7);
            }
            String str8 = cVar.f14974l;
            if (str8 == null) {
                supportSQLiteStatement.bindNull(12);
            } else {
                supportSQLiteStatement.bindString(12, str8);
            }
            supportSQLiteStatement.bindLong(13, cVar.f14975m);
            supportSQLiteStatement.bindLong(14, cVar.a);
        }

        @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "UPDATE OR ABORT `JD_ReminderNewTable` SET `_id` = ?,`businessType` = ?,`reminderShowTag` = ?,`identificationId` = ?,`reminderTitle` = ?,`reminderImgUrl` = ?,`startTimeMillis` = ?,`notificationTimeMillis` = ?,`insertTime` = ?,`jump` = ?,`extra` = ?,`more` = ?,`requestCode` = ? WHERE `_id` = ?";
        }
    }

    /* loaded from: classes7.dex */
    class d extends SharedSQLiteStatement {
        d(b bVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM JD_ReminderNewTable WHERE startTimeMillis < ?";
        }
    }

    /* loaded from: classes7.dex */
    class e extends SharedSQLiteStatement {
        e(b bVar, RoomDatabase roomDatabase) {
            super(roomDatabase);
        }

        @Override // androidx.room.SharedSQLiteStatement
        public String createQuery() {
            return "DELETE FROM JD_ReminderNewTable WHERE businessType = ? AND startTimeMillis = ? AND identificationId = ?";
        }
    }

    public b(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new a(this, roomDatabase);
        new C0719b(this, roomDatabase);
        new c(this, roomDatabase);
        this.f14965c = new d(this, roomDatabase);
        this.d = new e(this, roomDatabase);
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public Cursor a(long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT _id,requestCode FROM JD_ReminderNewTable WHERE notificationTimeMillis = ?", 1);
        acquire.bindLong(1, j2);
        return this.a.query(acquire);
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public long[] b(com.jingdong.sdk.jdroom.a.c[] cVarArr) {
        this.a.beginTransaction();
        try {
            long[] insertAndReturnIdsArray = this.b.insertAndReturnIdsArray(cVarArr);
            this.a.setTransactionSuccessful();
            return insertAndReturnIdsArray;
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public Cursor c(String str, long j2, long j3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM JD_ReminderNewTable WHERE businessType = ? AND startTimeMillis >= ? AND startTimeMillis < ?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j2);
        acquire.bindLong(3, j3);
        return this.a.query(acquire);
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public int d(long j2) {
        SupportSQLiteStatement acquire = this.f14965c.acquire();
        this.a.beginTransaction();
        try {
            acquire.bindLong(1, j2);
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.a.endTransaction();
            this.f14965c.release(acquire);
        }
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public Cursor e(long j2, long j3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT startTimeMillis FROM JD_ReminderNewTable WHERE startTimeMillis >= ? AND startTimeMillis < ?  ORDER BY startTimeMillis , insertTime ASC", 2);
        acquire.bindLong(1, j2);
        acquire.bindLong(2, j3);
        return this.a.query(acquire);
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public long f(com.jingdong.sdk.jdroom.a.c cVar) {
        this.a.beginTransaction();
        try {
            long insertAndReturnId = this.b.insertAndReturnId(cVar);
            this.a.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public Cursor g(long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM JD_ReminderNewTable WHERE notificationTimeMillis = ?  ORDER BY startTimeMillis , insertTime ASC", 1);
        acquire.bindLong(1, j2);
        return this.a.query(acquire);
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public int h(String str, String str2, long j2) {
        SupportSQLiteStatement acquire = this.d.acquire();
        this.a.beginTransaction();
        try {
            if (str == null) {
                acquire.bindNull(1);
            } else {
                acquire.bindString(1, str);
            }
            acquire.bindLong(2, j2);
            if (str2 == null) {
                acquire.bindNull(3);
            } else {
                acquire.bindString(3, str2);
            }
            int executeUpdateDelete = acquire.executeUpdateDelete();
            this.a.setTransactionSuccessful();
            return executeUpdateDelete;
        } finally {
            this.a.endTransaction();
            this.d.release(acquire);
        }
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public Cursor i(long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM JD_ReminderNewTable WHERE startTimeMillis >= ?  ORDER BY startTimeMillis , insertTime ASC", 1);
        acquire.bindLong(1, j2);
        return this.a.query(acquire);
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public Cursor j(String str, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM JD_ReminderNewTable WHERE businessType = ? AND startTimeMillis >= ? ", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j2);
        return this.a.query(acquire);
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public Cursor k() {
        return this.a.query(RoomSQLiteQuery.acquire("SELECT * FROM JD_ReminderNewTable ORDER BY requestCode DESC LIMIT 0,1", 0));
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public int l() {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM JD_ReminderNewTable", 0);
        Cursor query = this.a.query(acquire);
        try {
            return query.moveToFirst() ? query.getInt(0) : 0;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public Cursor m(long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT startTimeMillis,requestCode FROM JD_ReminderNewTable WHERE startTimeMillis >= ?  GROUP BY startTimeMillis", 1);
        acquire.bindLong(1, j2);
        return this.a.query(acquire);
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public Cursor n(long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT notificationTimeMillis,requestCode FROM JD_ReminderNewTable WHERE notificationTimeMillis >= ?  GROUP BY notificationTimeMillis", 1);
        acquire.bindLong(1, j2);
        return this.a.query(acquire);
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public Cursor o(String str, String str2, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT _id FROM JD_ReminderNewTable WHERE businessType = ? AND startTimeMillis = ? AND identificationId = ?", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j2);
        if (str2 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str2);
        }
        return this.a.query(acquire);
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public Cursor p(String str, String str2, String str3, String str4, String str5, double d2, double d3, String str6, String str7, String str8) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM JD_ReminderNewTable WHERE businessType = ? AND reminderShowTag = ? AND identificationId = ? AND reminderTitle = ? AND reminderImgUrl = ? AND startTimeMillis = ? AND notificationTimeMillis = ? AND jump = ? AND extra = ? AND more = ?", 10);
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
        if (str4 == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, str4);
        }
        if (str5 == null) {
            acquire.bindNull(5);
        } else {
            acquire.bindString(5, str5);
        }
        acquire.bindDouble(6, d2);
        acquire.bindDouble(7, d3);
        if (str6 == null) {
            acquire.bindNull(8);
        } else {
            acquire.bindString(8, str6);
        }
        if (str7 == null) {
            acquire.bindNull(9);
        } else {
            acquire.bindString(9, str7);
        }
        if (str8 == null) {
            acquire.bindNull(10);
        } else {
            acquire.bindString(10, str8);
        }
        return this.a.query(acquire);
    }

    @Override // com.jingdong.sdk.jdroom.a.a
    public Cursor q(long j2, long j3) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM JD_ReminderNewTable WHERE startTimeMillis >= ? AND startTimeMillis < ?  ORDER BY startTimeMillis , insertTime ASC", 2);
        acquire.bindLong(1, j2);
        acquire.bindLong(2, j3);
        return this.a.query(acquire);
    }
}
