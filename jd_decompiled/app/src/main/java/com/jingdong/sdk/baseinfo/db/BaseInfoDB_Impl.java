package com.jingdong.sdk.baseinfo.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.jd.aips.verify.tracker.VerifyTracker;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes7.dex */
public final class BaseInfoDB_Impl extends BaseInfoDB {
    private volatile b a;

    @Override // com.jingdong.sdk.baseinfo.db.BaseInfoDB
    public final b a() {
        b bVar;
        if (this.a != null) {
            return this.a;
        }
        synchronized (this) {
            if (this.a == null) {
                this.a = new c(this);
            }
            bVar = this.a;
        }
        return bVar;
    }

    @Override // androidx.room.RoomDatabase
    public final void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `privacy_info`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected final InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "privacy_info");
    }

    @Override // androidx.room.RoomDatabase
    protected final SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate() { // from class: com.jingdong.sdk.baseinfo.db.BaseInfoDB_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public final void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `privacy_info` (`pid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `key` TEXT, `name` TEXT, `value` TEXT DEFAULT '', `pin` TEXT DEFAULT '', `timestamp` INTEGER NOT NULL)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'bbd56a35b3d83e19bfb46b09a39169b6')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public final void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `privacy_info`");
                if (((RoomDatabase) BaseInfoDB_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) BaseInfoDB_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) BaseInfoDB_Impl.this).mCallbacks.get(i2)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected final void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) BaseInfoDB_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) BaseInfoDB_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) BaseInfoDB_Impl.this).mCallbacks.get(i2)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public final void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) BaseInfoDB_Impl.this).mDatabase = supportSQLiteDatabase;
                BaseInfoDB_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) BaseInfoDB_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) BaseInfoDB_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) BaseInfoDB_Impl.this).mCallbacks.get(i2)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public final void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public final void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected final RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                HashMap hashMap = new HashMap(6);
                hashMap.put("pid", new TableInfo.Column("pid", "INTEGER", true, 1, null, 1));
                hashMap.put("key", new TableInfo.Column("key", "TEXT", false, 0, null, 1));
                hashMap.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                hashMap.put("value", new TableInfo.Column("value", "TEXT", false, 0, "''", 1));
                hashMap.put("pin", new TableInfo.Column("pin", "TEXT", false, 0, "''", 1));
                hashMap.put(VerifyTracker.KEY_TIMESTAMP, new TableInfo.Column(VerifyTracker.KEY_TIMESTAMP, "INTEGER", true, 0, null, 1));
                TableInfo tableInfo = new TableInfo("privacy_info", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "privacy_info");
                if (tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(true, null);
                }
                return new RoomOpenHelper.ValidationResult(false, "privacy_info(com.jingdong.sdk.baseinfo.db.PrivacyInfo).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
            }
        }, "bbd56a35b3d83e19bfb46b09a39169b6", "1bc4d4d2eeae4402d5cd557d88d95a9e")).build());
    }
}
