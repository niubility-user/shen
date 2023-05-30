package com.jd.libs.hybrid.offlineload.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes16.dex */
public final class CommonFileDatabase_Impl extends CommonFileDatabase {
    private volatile CommonEntityDao b;

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `HybridOfflineCommonEntity`");
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
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "HybridOfflineCommonEntity");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(3) { // from class: com.jd.libs.hybrid.offlineload.db.CommonFileDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `HybridOfflineCommonEntity` (`id` TEXT NOT NULL, `url` TEXT, `versionCode` INTEGER NOT NULL, `md5` TEXT, `bConfigCommon` TEXT DEFAULT '0', `available` INTEGER NOT NULL, `createTimestamp` INTEGER NOT NULL, `headersMap` TEXT, `localfile_path` TEXT, `localfile_lastModified` INTEGER, `localfile_totalSpace` INTEGER, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_HybridOfflineCommonEntity_url` ON `HybridOfflineCommonEntity` (`url`)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '2e379484b5994f5cf833011c98b4d549')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `HybridOfflineCommonEntity`");
                if (((RoomDatabase) CommonFileDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) CommonFileDatabase_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) CommonFileDatabase_Impl.this).mCallbacks.get(i2)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) CommonFileDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) CommonFileDatabase_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) CommonFileDatabase_Impl.this).mCallbacks.get(i2)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) CommonFileDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
                CommonFileDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) CommonFileDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) CommonFileDatabase_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) CommonFileDatabase_Impl.this).mCallbacks.get(i2)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                HashMap hashMap = new HashMap(11);
                hashMap.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                hashMap.put("url", new TableInfo.Column("url", "TEXT", false, 0, null, 1));
                hashMap.put("versionCode", new TableInfo.Column("versionCode", "INTEGER", true, 0, null, 1));
                hashMap.put("md5", new TableInfo.Column("md5", "TEXT", false, 0, null, 1));
                hashMap.put("bConfigCommon", new TableInfo.Column("bConfigCommon", "TEXT", false, 0, "'0'", 1));
                hashMap.put(Constant.KEY_PROMOTION_AVAILABLE, new TableInfo.Column(Constant.KEY_PROMOTION_AVAILABLE, "INTEGER", true, 0, null, 1));
                hashMap.put("createTimestamp", new TableInfo.Column("createTimestamp", "INTEGER", true, 0, null, 1));
                hashMap.put("headersMap", new TableInfo.Column("headersMap", "TEXT", false, 0, null, 1));
                hashMap.put("localfile_path", new TableInfo.Column("localfile_path", "TEXT", false, 0, null, 1));
                hashMap.put("localfile_lastModified", new TableInfo.Column("localfile_lastModified", "INTEGER", false, 0, null, 1));
                hashMap.put("localfile_totalSpace", new TableInfo.Column("localfile_totalSpace", "INTEGER", false, 0, null, 1));
                HashSet hashSet = new HashSet(0);
                HashSet hashSet2 = new HashSet(1);
                hashSet2.add(new TableInfo.Index("index_HybridOfflineCommonEntity_url", true, Arrays.asList("url")));
                TableInfo tableInfo = new TableInfo("HybridOfflineCommonEntity", hashMap, hashSet, hashSet2);
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "HybridOfflineCommonEntity");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "HybridOfflineCommonEntity(com.jd.libs.hybrid.offlineload.entity.CommonEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "2e379484b5994f5cf833011c98b4d549", "0c314987cd7ac270516519c1c23654d7")).build());
    }

    @Override // com.jd.libs.hybrid.offlineload.db.CommonFileDatabase
    public CommonEntityDao getDao() {
        CommonEntityDao commonEntityDao;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new CommonEntityDao_Impl(this);
            }
            commonEntityDao = this.b;
        }
        return commonEntityDao;
    }
}
