package com.jingdong.sdk.baseinfo.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jd.aips.verify.tracker.VerifyTracker;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public final class c implements b {
    private final RoomDatabase a;
    private final EntityInsertionAdapter<PrivacyInfo> b;

    /* renamed from: c  reason: collision with root package name */
    private final EntityDeletionOrUpdateAdapter<PrivacyInfo> f14693c;

    public c(RoomDatabase roomDatabase) {
        this.a = roomDatabase;
        this.b = new EntityInsertionAdapter<PrivacyInfo>(roomDatabase) { // from class: com.jingdong.sdk.baseinfo.db.c.1
            @Override // androidx.room.EntityInsertionAdapter
            public final /* synthetic */ void bind(SupportSQLiteStatement supportSQLiteStatement, PrivacyInfo privacyInfo) {
                PrivacyInfo privacyInfo2 = privacyInfo;
                supportSQLiteStatement.bindLong(1, privacyInfo2.pid);
                String str = privacyInfo2.key;
                if (str == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, str);
                }
                String str2 = privacyInfo2.name;
                if (str2 == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, str2);
                }
                String str3 = privacyInfo2.value;
                if (str3 == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, str3);
                }
                String str4 = privacyInfo2.pin;
                if (str4 == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, str4);
                }
                supportSQLiteStatement.bindLong(6, privacyInfo2.timestamp);
            }

            @Override // androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "INSERT OR ABORT INTO `privacy_info` (`pid`,`key`,`name`,`value`,`pin`,`timestamp`) VALUES (nullif(?, 0),?,?,?,?,?)";
            }
        };
        this.f14693c = new EntityDeletionOrUpdateAdapter<PrivacyInfo>(roomDatabase) { // from class: com.jingdong.sdk.baseinfo.db.c.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public final /* synthetic */ void bind(SupportSQLiteStatement supportSQLiteStatement, PrivacyInfo privacyInfo) {
                supportSQLiteStatement.bindLong(1, privacyInfo.pid);
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public final String createQuery() {
                return "DELETE FROM `privacy_info` WHERE `pid` = ?";
            }
        };
    }

    @Override // com.jingdong.sdk.baseinfo.db.b
    public final List<PrivacyInfo> a(int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM privacy_info LIMIT ?, 500", 1);
        acquire.bindLong(1, i2);
        this.a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "pid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "key");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "value");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "pin");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, VerifyTracker.KEY_TIMESTAMP);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                PrivacyInfo privacyInfo = new PrivacyInfo();
                privacyInfo.pid = query.getLong(columnIndexOrThrow);
                privacyInfo.key = query.getString(columnIndexOrThrow2);
                privacyInfo.name = query.getString(columnIndexOrThrow3);
                privacyInfo.value = query.getString(columnIndexOrThrow4);
                privacyInfo.pin = query.getString(columnIndexOrThrow5);
                privacyInfo.timestamp = query.getLong(columnIndexOrThrow6);
                arrayList.add(privacyInfo);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.jingdong.sdk.baseinfo.db.b
    public final List<PrivacyInfo> a(long j2, long j3, int i2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM privacy_info WHERE timestamp >= ? AND timestamp < ? LIMIT ?, 500", 3);
        acquire.bindLong(1, j2);
        acquire.bindLong(2, j3);
        acquire.bindLong(3, i2);
        this.a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "pid");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "key");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "name");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "value");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "pin");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, VerifyTracker.KEY_TIMESTAMP);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                PrivacyInfo privacyInfo = new PrivacyInfo();
                privacyInfo.pid = query.getLong(columnIndexOrThrow);
                privacyInfo.key = query.getString(columnIndexOrThrow2);
                privacyInfo.name = query.getString(columnIndexOrThrow3);
                privacyInfo.value = query.getString(columnIndexOrThrow4);
                privacyInfo.pin = query.getString(columnIndexOrThrow5);
                privacyInfo.timestamp = query.getLong(columnIndexOrThrow6);
                arrayList.add(privacyInfo);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    @Override // com.jingdong.sdk.baseinfo.db.b
    public final long[] a(PrivacyInfo... privacyInfoArr) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            long[] insertAndReturnIdsArray = this.b.insertAndReturnIdsArray(privacyInfoArr);
            this.a.setTransactionSuccessful();
            return insertAndReturnIdsArray;
        } finally {
            this.a.endTransaction();
        }
    }

    @Override // com.jingdong.sdk.baseinfo.db.b
    public final int b(PrivacyInfo... privacyInfoArr) {
        this.a.assertNotSuspendingTransaction();
        this.a.beginTransaction();
        try {
            int handleMultiple = this.f14693c.handleMultiple(privacyInfoArr) + 0;
            this.a.setTransactionSuccessful();
            return handleMultiple;
        } finally {
            this.a.endTransaction();
        }
    }
}
