package com.jd.libs.hybrid.preload.db;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao;
import com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao_Impl;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes16.dex */
public final class PreloadDatabase_Impl extends PreloadDatabase {
    private volatile PreloadInfoDao b;

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `HybridPreloadInfo`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "HybridPreloadInfo");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(6) { // from class: com.jd.libs.hybrid.preload.db.PreloadDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `HybridPreloadInfo` (`appid` TEXT NOT NULL, `url` TEXT, `originalUrl` TEXT, `requestUrl` TEXT, `requestType` TEXT, `functionId` TEXT, `method` TEXT, `header` TEXT, `params` TEXT, `body` TEXT, `extraKeys` TEXT, `mappings` TEXT, `urlParamsState` INTEGER NOT NULL, `appMin` TEXT, `appMax` TEXT, `originalUrlType` TEXT, `bConfig` TEXT DEFAULT '0', PRIMARY KEY(`appid`))");
                supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_HybridPreloadInfo_url` ON `HybridPreloadInfo` (`url`)");
                supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_HybridPreloadInfo_originalUrl` ON `HybridPreloadInfo` (`originalUrl`)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f7eb42ba84bb5f3c17b0bd8a32f88b63')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `HybridPreloadInfo`");
                if (((RoomDatabase) PreloadDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) PreloadDatabase_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) PreloadDatabase_Impl.this).mCallbacks.get(i2)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) PreloadDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) PreloadDatabase_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) PreloadDatabase_Impl.this).mCallbacks.get(i2)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) PreloadDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
                PreloadDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) PreloadDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) PreloadDatabase_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) PreloadDatabase_Impl.this).mCallbacks.get(i2)).onOpen(supportSQLiteDatabase);
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
                HashMap hashMap = new HashMap(17);
                hashMap.put("appid", new TableInfo.Column("appid", "TEXT", true, 1, null, 1));
                hashMap.put("url", new TableInfo.Column("url", "TEXT", false, 0, null, 1));
                hashMap.put("originalUrl", new TableInfo.Column("originalUrl", "TEXT", false, 0, null, 1));
                hashMap.put("requestUrl", new TableInfo.Column("requestUrl", "TEXT", false, 0, null, 1));
                hashMap.put("requestType", new TableInfo.Column("requestType", "TEXT", false, 0, null, 1));
                hashMap.put("functionId", new TableInfo.Column("functionId", "TEXT", false, 0, null, 1));
                hashMap.put("method", new TableInfo.Column("method", "TEXT", false, 0, null, 1));
                hashMap.put("header", new TableInfo.Column("header", "TEXT", false, 0, null, 1));
                hashMap.put("params", new TableInfo.Column("params", "TEXT", false, 0, null, 1));
                hashMap.put("body", new TableInfo.Column("body", "TEXT", false, 0, null, 1));
                hashMap.put("extraKeys", new TableInfo.Column("extraKeys", "TEXT", false, 0, null, 1));
                hashMap.put("mappings", new TableInfo.Column("mappings", "TEXT", false, 0, null, 1));
                hashMap.put("urlParamsState", new TableInfo.Column("urlParamsState", "INTEGER", true, 0, null, 1));
                hashMap.put("appMin", new TableInfo.Column("appMin", "TEXT", false, 0, null, 1));
                hashMap.put("appMax", new TableInfo.Column("appMax", "TEXT", false, 0, null, 1));
                hashMap.put("originalUrlType", new TableInfo.Column("originalUrlType", "TEXT", false, 0, null, 1));
                hashMap.put("bConfig", new TableInfo.Column("bConfig", "TEXT", false, 0, "'0'", 1));
                HashSet hashSet = new HashSet(0);
                HashSet hashSet2 = new HashSet(2);
                hashSet2.add(new TableInfo.Index("index_HybridPreloadInfo_url", false, Arrays.asList("url")));
                hashSet2.add(new TableInfo.Index("index_HybridPreloadInfo_originalUrl", false, Arrays.asList("originalUrl")));
                TableInfo tableInfo = new TableInfo("HybridPreloadInfo", hashMap, hashSet, hashSet2);
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "HybridPreloadInfo");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "HybridPreloadInfo(com.jd.libs.hybrid.preload.entity.PreloadInfoEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "f7eb42ba84bb5f3c17b0bd8a32f88b63", "3c8489364e31de7d5869015107aa7f8b")).build());
    }

    @Override // com.jd.libs.hybrid.preload.db.PreloadDatabase
    public PreloadInfoDao getDao() {
        PreloadInfoDao preloadInfoDao;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new PreloadInfoDao_Impl(this);
            }
            preloadInfoDao = this.b;
        }
        return preloadInfoDao;
    }
}
