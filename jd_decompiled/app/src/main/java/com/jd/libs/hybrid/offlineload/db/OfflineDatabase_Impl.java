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
import com.jd.libs.hybrid.offlineload.entity.BuildInOfflineEntity;
import com.jd.libs.hybrid.offlineload.entity.TestOfflineEntity;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes16.dex */
public final class OfflineDatabase_Impl extends OfflineDatabase {
    private volatile OfflineEntityDao b;

    /* renamed from: c  reason: collision with root package name */
    private volatile BuildInOfflineEntityDao f5963c;
    private volatile TestOfflineEntityDao d;

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `HybridOfflineEntity`");
            writableDatabase.execSQL("DELETE FROM `HybridBuildInOfflineEntity`");
            writableDatabase.execSQL("DELETE FROM `HybridTestOfflineEntity`");
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
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "HybridOfflineEntity", "HybridBuildInOfflineEntity", "HybridTestOfflineEntity");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(10) { // from class: com.jd.libs.hybrid.offlineload.db.OfflineDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `HybridOfflineEntity` (`appid` TEXT NOT NULL, `moduleCode` INTEGER NOT NULL DEFAULT 0, `type` TEXT, `name` TEXT, `documentUrl` TEXT, `originalUrl` TEXT, `originalUrlType` TEXT, `patch_total` TEXT, `documentDir` TEXT, `sourceRoot` TEXT, `sourceDir` TEXT, `appMin` TEXT, `appMax` TEXT, `serverPriority` INTEGER NOT NULL, `checkType` TEXT, `fileRootPath` TEXT, `htmlPreload` INTEGER NOT NULL, `htmlStatic` INTEGER NOT NULL, `cacheable` INTEGER NOT NULL, `ungentoken` INTEGER NOT NULL, `bConfig` TEXT DEFAULT '0', `degradeType` INTEGER NOT NULL DEFAULT 2, `minFileVer` TEXT DEFAULT '0', `available` INTEGER NOT NULL, `createTimestamp` INTEGER NOT NULL, `lastVisitTimestamp` INTEGER NOT NULL, `localPriorityInfo` TEXT, `file_url` TEXT, `file_version` TEXT, `file_versionCode` INTEGER, `file_md5` TEXT, `file_fileType` TEXT, `file_password` TEXT, `file_patchTotal` INTEGER, `file_fileUrlZip` TEXT, `file_fileZipMd5` TEXT, `file_useZip` INTEGER, `document_path` TEXT, `document_lastModified` INTEGER, `document_totalSpace` INTEGER, `source_path` TEXT, `source_lastModified` INTEGER, `source_totalSpace` INTEGER, `zip_path` TEXT, `zip_lastModified` INTEGER, `zip_totalSpace` INTEGER, PRIMARY KEY(`appid`))");
                supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_HybridOfflineEntity_documentUrl` ON `HybridOfflineEntity` (`documentUrl`)");
                supportSQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS `index_HybridOfflineEntity_originalUrl` ON `HybridOfflineEntity` (`originalUrl`)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `HybridBuildInOfflineEntity` (`appid` TEXT NOT NULL, `moduleCode` INTEGER NOT NULL DEFAULT 0, `type` TEXT, `name` TEXT, `documentUrl` TEXT, `originalUrl` TEXT, `originalUrlType` TEXT, `patch_total` TEXT, `documentDir` TEXT, `sourceRoot` TEXT, `sourceDir` TEXT, `appMin` TEXT, `appMax` TEXT, `serverPriority` INTEGER NOT NULL, `checkType` TEXT, `fileRootPath` TEXT, `htmlPreload` INTEGER NOT NULL, `htmlStatic` INTEGER NOT NULL, `cacheable` INTEGER NOT NULL, `ungentoken` INTEGER NOT NULL, `bConfig` TEXT DEFAULT '0', `degradeType` INTEGER NOT NULL DEFAULT 2, `minFileVer` TEXT DEFAULT '0', `available` INTEGER NOT NULL, `createTimestamp` INTEGER NOT NULL, `lastVisitTimestamp` INTEGER NOT NULL, `localPriorityInfo` TEXT, `file_url` TEXT, `file_version` TEXT, `file_versionCode` INTEGER, `file_md5` TEXT, `file_fileType` TEXT, `file_password` TEXT, `file_patchTotal` INTEGER, `file_fileUrlZip` TEXT, `file_fileZipMd5` TEXT, `file_useZip` INTEGER, `document_path` TEXT, `document_lastModified` INTEGER, `document_totalSpace` INTEGER, `source_path` TEXT, `source_lastModified` INTEGER, `source_totalSpace` INTEGER, `zip_path` TEXT, `zip_lastModified` INTEGER, `zip_totalSpace` INTEGER, PRIMARY KEY(`appid`))");
                supportSQLiteDatabase.execSQL(BuildInOfflineEntity.SQL_MIGRATE_6_TO_7_PART2);
                supportSQLiteDatabase.execSQL(BuildInOfflineEntity.SQL_MIGRATE_6_TO_7_PART3);
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `HybridTestOfflineEntity` (`appid` TEXT NOT NULL, `moduleCode` INTEGER NOT NULL DEFAULT 0, `type` TEXT, `name` TEXT, `documentUrl` TEXT, `originalUrl` TEXT, `originalUrlType` TEXT, `patch_total` TEXT, `documentDir` TEXT, `sourceRoot` TEXT, `sourceDir` TEXT, `appMin` TEXT, `appMax` TEXT, `serverPriority` INTEGER NOT NULL, `checkType` TEXT, `fileRootPath` TEXT, `htmlPreload` INTEGER NOT NULL, `htmlStatic` INTEGER NOT NULL, `cacheable` INTEGER NOT NULL, `ungentoken` INTEGER NOT NULL, `bConfig` TEXT DEFAULT '0', `degradeType` INTEGER NOT NULL DEFAULT 2, `minFileVer` TEXT DEFAULT '0', `available` INTEGER NOT NULL, `createTimestamp` INTEGER NOT NULL, `lastVisitTimestamp` INTEGER NOT NULL, `localPriorityInfo` TEXT, `file_url` TEXT, `file_version` TEXT, `file_versionCode` INTEGER, `file_md5` TEXT, `file_fileType` TEXT, `file_password` TEXT, `file_patchTotal` INTEGER, `file_fileUrlZip` TEXT, `file_fileZipMd5` TEXT, `file_useZip` INTEGER, `document_path` TEXT, `document_lastModified` INTEGER, `document_totalSpace` INTEGER, `source_path` TEXT, `source_lastModified` INTEGER, `source_totalSpace` INTEGER, `zip_path` TEXT, `zip_lastModified` INTEGER, `zip_totalSpace` INTEGER, PRIMARY KEY(`appid`))");
                supportSQLiteDatabase.execSQL(TestOfflineEntity.SQL_MIGRATE_6_TO_7_PART2);
                supportSQLiteDatabase.execSQL(TestOfflineEntity.SQL_MIGRATE_6_TO_7_PART3);
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '44eaac7944d05f83d5492f5001729b1a')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `HybridOfflineEntity`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `HybridBuildInOfflineEntity`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `HybridTestOfflineEntity`");
                if (((RoomDatabase) OfflineDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) OfflineDatabase_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) OfflineDatabase_Impl.this).mCallbacks.get(i2)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) OfflineDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) OfflineDatabase_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) OfflineDatabase_Impl.this).mCallbacks.get(i2)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) OfflineDatabase_Impl.this).mDatabase = supportSQLiteDatabase;
                OfflineDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) OfflineDatabase_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) OfflineDatabase_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) OfflineDatabase_Impl.this).mCallbacks.get(i2)).onOpen(supportSQLiteDatabase);
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
                HashMap hashMap = new HashMap(46);
                hashMap.put("appid", new TableInfo.Column("appid", "TEXT", true, 1, null, 1));
                hashMap.put(JDReactConstant.ModuleCode, new TableInfo.Column(JDReactConstant.ModuleCode, "INTEGER", true, 0, "0", 1));
                hashMap.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, 1));
                hashMap.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                hashMap.put("documentUrl", new TableInfo.Column("documentUrl", "TEXT", false, 0, null, 1));
                hashMap.put("originalUrl", new TableInfo.Column("originalUrl", "TEXT", false, 0, null, 1));
                hashMap.put("originalUrlType", new TableInfo.Column("originalUrlType", "TEXT", false, 0, null, 1));
                hashMap.put("patch_total", new TableInfo.Column("patch_total", "TEXT", false, 0, null, 1));
                hashMap.put("documentDir", new TableInfo.Column("documentDir", "TEXT", false, 0, null, 1));
                hashMap.put("sourceRoot", new TableInfo.Column("sourceRoot", "TEXT", false, 0, null, 1));
                hashMap.put("sourceDir", new TableInfo.Column("sourceDir", "TEXT", false, 0, null, 1));
                hashMap.put("appMin", new TableInfo.Column("appMin", "TEXT", false, 0, null, 1));
                hashMap.put("appMax", new TableInfo.Column("appMax", "TEXT", false, 0, null, 1));
                hashMap.put("serverPriority", new TableInfo.Column("serverPriority", "INTEGER", true, 0, null, 1));
                hashMap.put("checkType", new TableInfo.Column("checkType", "TEXT", false, 0, null, 1));
                hashMap.put("fileRootPath", new TableInfo.Column("fileRootPath", "TEXT", false, 0, null, 1));
                hashMap.put("htmlPreload", new TableInfo.Column("htmlPreload", "INTEGER", true, 0, null, 1));
                hashMap.put("htmlStatic", new TableInfo.Column("htmlStatic", "INTEGER", true, 0, null, 1));
                hashMap.put("cacheable", new TableInfo.Column("cacheable", "INTEGER", true, 0, null, 1));
                hashMap.put("ungentoken", new TableInfo.Column("ungentoken", "INTEGER", true, 0, null, 1));
                hashMap.put("bConfig", new TableInfo.Column("bConfig", "TEXT", false, 0, "'0'", 1));
                hashMap.put("degradeType", new TableInfo.Column("degradeType", "INTEGER", true, 0, "2", 1));
                hashMap.put("minFileVer", new TableInfo.Column("minFileVer", "TEXT", false, 0, "'0'", 1));
                hashMap.put(Constant.KEY_PROMOTION_AVAILABLE, new TableInfo.Column(Constant.KEY_PROMOTION_AVAILABLE, "INTEGER", true, 0, null, 1));
                hashMap.put("createTimestamp", new TableInfo.Column("createTimestamp", "INTEGER", true, 0, null, 1));
                hashMap.put("lastVisitTimestamp", new TableInfo.Column("lastVisitTimestamp", "INTEGER", true, 0, null, 1));
                hashMap.put("localPriorityInfo", new TableInfo.Column("localPriorityInfo", "TEXT", false, 0, null, 1));
                hashMap.put("file_url", new TableInfo.Column("file_url", "TEXT", false, 0, null, 1));
                hashMap.put("file_version", new TableInfo.Column("file_version", "TEXT", false, 0, null, 1));
                hashMap.put("file_versionCode", new TableInfo.Column("file_versionCode", "INTEGER", false, 0, null, 1));
                hashMap.put("file_md5", new TableInfo.Column("file_md5", "TEXT", false, 0, null, 1));
                hashMap.put("file_fileType", new TableInfo.Column("file_fileType", "TEXT", false, 0, null, 1));
                hashMap.put("file_password", new TableInfo.Column("file_password", "TEXT", false, 0, null, 1));
                hashMap.put("file_patchTotal", new TableInfo.Column("file_patchTotal", "INTEGER", false, 0, null, 1));
                hashMap.put("file_fileUrlZip", new TableInfo.Column("file_fileUrlZip", "TEXT", false, 0, null, 1));
                hashMap.put("file_fileZipMd5", new TableInfo.Column("file_fileZipMd5", "TEXT", false, 0, null, 1));
                hashMap.put("file_useZip", new TableInfo.Column("file_useZip", "INTEGER", false, 0, null, 1));
                hashMap.put("document_path", new TableInfo.Column("document_path", "TEXT", false, 0, null, 1));
                hashMap.put("document_lastModified", new TableInfo.Column("document_lastModified", "INTEGER", false, 0, null, 1));
                hashMap.put("document_totalSpace", new TableInfo.Column("document_totalSpace", "INTEGER", false, 0, null, 1));
                hashMap.put("source_path", new TableInfo.Column("source_path", "TEXT", false, 0, null, 1));
                hashMap.put("source_lastModified", new TableInfo.Column("source_lastModified", "INTEGER", false, 0, null, 1));
                hashMap.put("source_totalSpace", new TableInfo.Column("source_totalSpace", "INTEGER", false, 0, null, 1));
                hashMap.put("zip_path", new TableInfo.Column("zip_path", "TEXT", false, 0, null, 1));
                hashMap.put("zip_lastModified", new TableInfo.Column("zip_lastModified", "INTEGER", false, 0, null, 1));
                hashMap.put("zip_totalSpace", new TableInfo.Column("zip_totalSpace", "INTEGER", false, 0, null, 1));
                HashSet hashSet = new HashSet(0);
                HashSet hashSet2 = new HashSet(2);
                hashSet2.add(new TableInfo.Index("index_HybridOfflineEntity_documentUrl", false, Arrays.asList("documentUrl")));
                hashSet2.add(new TableInfo.Index("index_HybridOfflineEntity_originalUrl", false, Arrays.asList("originalUrl")));
                TableInfo tableInfo = new TableInfo("HybridOfflineEntity", hashMap, hashSet, hashSet2);
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "HybridOfflineEntity");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "HybridOfflineEntity(com.jd.libs.hybrid.offlineload.entity.OfflineEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(46);
                hashMap2.put("appid", new TableInfo.Column("appid", "TEXT", true, 1, null, 1));
                hashMap2.put(JDReactConstant.ModuleCode, new TableInfo.Column(JDReactConstant.ModuleCode, "INTEGER", true, 0, "0", 1));
                hashMap2.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, 1));
                hashMap2.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                hashMap2.put("documentUrl", new TableInfo.Column("documentUrl", "TEXT", false, 0, null, 1));
                hashMap2.put("originalUrl", new TableInfo.Column("originalUrl", "TEXT", false, 0, null, 1));
                hashMap2.put("originalUrlType", new TableInfo.Column("originalUrlType", "TEXT", false, 0, null, 1));
                hashMap2.put("patch_total", new TableInfo.Column("patch_total", "TEXT", false, 0, null, 1));
                hashMap2.put("documentDir", new TableInfo.Column("documentDir", "TEXT", false, 0, null, 1));
                hashMap2.put("sourceRoot", new TableInfo.Column("sourceRoot", "TEXT", false, 0, null, 1));
                hashMap2.put("sourceDir", new TableInfo.Column("sourceDir", "TEXT", false, 0, null, 1));
                hashMap2.put("appMin", new TableInfo.Column("appMin", "TEXT", false, 0, null, 1));
                hashMap2.put("appMax", new TableInfo.Column("appMax", "TEXT", false, 0, null, 1));
                hashMap2.put("serverPriority", new TableInfo.Column("serverPriority", "INTEGER", true, 0, null, 1));
                hashMap2.put("checkType", new TableInfo.Column("checkType", "TEXT", false, 0, null, 1));
                hashMap2.put("fileRootPath", new TableInfo.Column("fileRootPath", "TEXT", false, 0, null, 1));
                hashMap2.put("htmlPreload", new TableInfo.Column("htmlPreload", "INTEGER", true, 0, null, 1));
                hashMap2.put("htmlStatic", new TableInfo.Column("htmlStatic", "INTEGER", true, 0, null, 1));
                hashMap2.put("cacheable", new TableInfo.Column("cacheable", "INTEGER", true, 0, null, 1));
                hashMap2.put("ungentoken", new TableInfo.Column("ungentoken", "INTEGER", true, 0, null, 1));
                hashMap2.put("bConfig", new TableInfo.Column("bConfig", "TEXT", false, 0, "'0'", 1));
                hashMap2.put("degradeType", new TableInfo.Column("degradeType", "INTEGER", true, 0, "2", 1));
                hashMap2.put("minFileVer", new TableInfo.Column("minFileVer", "TEXT", false, 0, "'0'", 1));
                hashMap2.put(Constant.KEY_PROMOTION_AVAILABLE, new TableInfo.Column(Constant.KEY_PROMOTION_AVAILABLE, "INTEGER", true, 0, null, 1));
                hashMap2.put("createTimestamp", new TableInfo.Column("createTimestamp", "INTEGER", true, 0, null, 1));
                hashMap2.put("lastVisitTimestamp", new TableInfo.Column("lastVisitTimestamp", "INTEGER", true, 0, null, 1));
                hashMap2.put("localPriorityInfo", new TableInfo.Column("localPriorityInfo", "TEXT", false, 0, null, 1));
                hashMap2.put("file_url", new TableInfo.Column("file_url", "TEXT", false, 0, null, 1));
                hashMap2.put("file_version", new TableInfo.Column("file_version", "TEXT", false, 0, null, 1));
                hashMap2.put("file_versionCode", new TableInfo.Column("file_versionCode", "INTEGER", false, 0, null, 1));
                hashMap2.put("file_md5", new TableInfo.Column("file_md5", "TEXT", false, 0, null, 1));
                hashMap2.put("file_fileType", new TableInfo.Column("file_fileType", "TEXT", false, 0, null, 1));
                hashMap2.put("file_password", new TableInfo.Column("file_password", "TEXT", false, 0, null, 1));
                hashMap2.put("file_patchTotal", new TableInfo.Column("file_patchTotal", "INTEGER", false, 0, null, 1));
                hashMap2.put("file_fileUrlZip", new TableInfo.Column("file_fileUrlZip", "TEXT", false, 0, null, 1));
                hashMap2.put("file_fileZipMd5", new TableInfo.Column("file_fileZipMd5", "TEXT", false, 0, null, 1));
                hashMap2.put("file_useZip", new TableInfo.Column("file_useZip", "INTEGER", false, 0, null, 1));
                hashMap2.put("document_path", new TableInfo.Column("document_path", "TEXT", false, 0, null, 1));
                hashMap2.put("document_lastModified", new TableInfo.Column("document_lastModified", "INTEGER", false, 0, null, 1));
                hashMap2.put("document_totalSpace", new TableInfo.Column("document_totalSpace", "INTEGER", false, 0, null, 1));
                hashMap2.put("source_path", new TableInfo.Column("source_path", "TEXT", false, 0, null, 1));
                hashMap2.put("source_lastModified", new TableInfo.Column("source_lastModified", "INTEGER", false, 0, null, 1));
                hashMap2.put("source_totalSpace", new TableInfo.Column("source_totalSpace", "INTEGER", false, 0, null, 1));
                hashMap2.put("zip_path", new TableInfo.Column("zip_path", "TEXT", false, 0, null, 1));
                hashMap2.put("zip_lastModified", new TableInfo.Column("zip_lastModified", "INTEGER", false, 0, null, 1));
                hashMap2.put("zip_totalSpace", new TableInfo.Column("zip_totalSpace", "INTEGER", false, 0, null, 1));
                HashSet hashSet3 = new HashSet(0);
                HashSet hashSet4 = new HashSet(2);
                hashSet4.add(new TableInfo.Index("index_HybridBuildInOfflineEntity_documentUrl", false, Arrays.asList("documentUrl")));
                hashSet4.add(new TableInfo.Index("index_HybridBuildInOfflineEntity_originalUrl", false, Arrays.asList("originalUrl")));
                TableInfo tableInfo2 = new TableInfo("HybridBuildInOfflineEntity", hashMap2, hashSet3, hashSet4);
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "HybridBuildInOfflineEntity");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "HybridBuildInOfflineEntity(com.jd.libs.hybrid.offlineload.entity.BuildInOfflineEntity).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                HashMap hashMap3 = new HashMap(46);
                hashMap3.put("appid", new TableInfo.Column("appid", "TEXT", true, 1, null, 1));
                hashMap3.put(JDReactConstant.ModuleCode, new TableInfo.Column(JDReactConstant.ModuleCode, "INTEGER", true, 0, "0", 1));
                hashMap3.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, 1));
                hashMap3.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, 1));
                hashMap3.put("documentUrl", new TableInfo.Column("documentUrl", "TEXT", false, 0, null, 1));
                hashMap3.put("originalUrl", new TableInfo.Column("originalUrl", "TEXT", false, 0, null, 1));
                hashMap3.put("originalUrlType", new TableInfo.Column("originalUrlType", "TEXT", false, 0, null, 1));
                hashMap3.put("patch_total", new TableInfo.Column("patch_total", "TEXT", false, 0, null, 1));
                hashMap3.put("documentDir", new TableInfo.Column("documentDir", "TEXT", false, 0, null, 1));
                hashMap3.put("sourceRoot", new TableInfo.Column("sourceRoot", "TEXT", false, 0, null, 1));
                hashMap3.put("sourceDir", new TableInfo.Column("sourceDir", "TEXT", false, 0, null, 1));
                hashMap3.put("appMin", new TableInfo.Column("appMin", "TEXT", false, 0, null, 1));
                hashMap3.put("appMax", new TableInfo.Column("appMax", "TEXT", false, 0, null, 1));
                hashMap3.put("serverPriority", new TableInfo.Column("serverPriority", "INTEGER", true, 0, null, 1));
                hashMap3.put("checkType", new TableInfo.Column("checkType", "TEXT", false, 0, null, 1));
                hashMap3.put("fileRootPath", new TableInfo.Column("fileRootPath", "TEXT", false, 0, null, 1));
                hashMap3.put("htmlPreload", new TableInfo.Column("htmlPreload", "INTEGER", true, 0, null, 1));
                hashMap3.put("htmlStatic", new TableInfo.Column("htmlStatic", "INTEGER", true, 0, null, 1));
                hashMap3.put("cacheable", new TableInfo.Column("cacheable", "INTEGER", true, 0, null, 1));
                hashMap3.put("ungentoken", new TableInfo.Column("ungentoken", "INTEGER", true, 0, null, 1));
                hashMap3.put("bConfig", new TableInfo.Column("bConfig", "TEXT", false, 0, "'0'", 1));
                hashMap3.put("degradeType", new TableInfo.Column("degradeType", "INTEGER", true, 0, "2", 1));
                hashMap3.put("minFileVer", new TableInfo.Column("minFileVer", "TEXT", false, 0, "'0'", 1));
                hashMap3.put(Constant.KEY_PROMOTION_AVAILABLE, new TableInfo.Column(Constant.KEY_PROMOTION_AVAILABLE, "INTEGER", true, 0, null, 1));
                hashMap3.put("createTimestamp", new TableInfo.Column("createTimestamp", "INTEGER", true, 0, null, 1));
                hashMap3.put("lastVisitTimestamp", new TableInfo.Column("lastVisitTimestamp", "INTEGER", true, 0, null, 1));
                hashMap3.put("localPriorityInfo", new TableInfo.Column("localPriorityInfo", "TEXT", false, 0, null, 1));
                hashMap3.put("file_url", new TableInfo.Column("file_url", "TEXT", false, 0, null, 1));
                hashMap3.put("file_version", new TableInfo.Column("file_version", "TEXT", false, 0, null, 1));
                hashMap3.put("file_versionCode", new TableInfo.Column("file_versionCode", "INTEGER", false, 0, null, 1));
                hashMap3.put("file_md5", new TableInfo.Column("file_md5", "TEXT", false, 0, null, 1));
                hashMap3.put("file_fileType", new TableInfo.Column("file_fileType", "TEXT", false, 0, null, 1));
                hashMap3.put("file_password", new TableInfo.Column("file_password", "TEXT", false, 0, null, 1));
                hashMap3.put("file_patchTotal", new TableInfo.Column("file_patchTotal", "INTEGER", false, 0, null, 1));
                hashMap3.put("file_fileUrlZip", new TableInfo.Column("file_fileUrlZip", "TEXT", false, 0, null, 1));
                hashMap3.put("file_fileZipMd5", new TableInfo.Column("file_fileZipMd5", "TEXT", false, 0, null, 1));
                hashMap3.put("file_useZip", new TableInfo.Column("file_useZip", "INTEGER", false, 0, null, 1));
                hashMap3.put("document_path", new TableInfo.Column("document_path", "TEXT", false, 0, null, 1));
                hashMap3.put("document_lastModified", new TableInfo.Column("document_lastModified", "INTEGER", false, 0, null, 1));
                hashMap3.put("document_totalSpace", new TableInfo.Column("document_totalSpace", "INTEGER", false, 0, null, 1));
                hashMap3.put("source_path", new TableInfo.Column("source_path", "TEXT", false, 0, null, 1));
                hashMap3.put("source_lastModified", new TableInfo.Column("source_lastModified", "INTEGER", false, 0, null, 1));
                hashMap3.put("source_totalSpace", new TableInfo.Column("source_totalSpace", "INTEGER", false, 0, null, 1));
                hashMap3.put("zip_path", new TableInfo.Column("zip_path", "TEXT", false, 0, null, 1));
                hashMap3.put("zip_lastModified", new TableInfo.Column("zip_lastModified", "INTEGER", false, 0, null, 1));
                hashMap3.put("zip_totalSpace", new TableInfo.Column("zip_totalSpace", "INTEGER", false, 0, null, 1));
                HashSet hashSet5 = new HashSet(0);
                HashSet hashSet6 = new HashSet(2);
                hashSet6.add(new TableInfo.Index("index_HybridTestOfflineEntity_documentUrl", false, Arrays.asList("documentUrl")));
                hashSet6.add(new TableInfo.Index("index_HybridTestOfflineEntity_originalUrl", false, Arrays.asList("originalUrl")));
                TableInfo tableInfo3 = new TableInfo("HybridTestOfflineEntity", hashMap3, hashSet5, hashSet6);
                TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "HybridTestOfflineEntity");
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, "HybridTestOfflineEntity(com.jd.libs.hybrid.offlineload.entity.TestOfflineEntity).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "44eaac7944d05f83d5492f5001729b1a", "c6cbdb2caf06bfc70c4e16f9c0b6ea11")).build());
    }

    @Override // com.jd.libs.hybrid.offlineload.db.OfflineDatabase
    public BuildInOfflineEntityDao getBuildInDao() {
        BuildInOfflineEntityDao buildInOfflineEntityDao;
        if (this.f5963c != null) {
            return this.f5963c;
        }
        synchronized (this) {
            if (this.f5963c == null) {
                this.f5963c = new BuildInOfflineEntityDao_Impl(this);
            }
            buildInOfflineEntityDao = this.f5963c;
        }
        return buildInOfflineEntityDao;
    }

    @Override // com.jd.libs.hybrid.offlineload.db.OfflineDatabase
    public OfflineEntityDao getDao() {
        OfflineEntityDao offlineEntityDao;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new OfflineEntityDao_Impl(this);
            }
            offlineEntityDao = this.b;
        }
        return offlineEntityDao;
    }

    @Override // com.jd.libs.hybrid.offlineload.db.OfflineDatabase
    public TestOfflineEntityDao getTestDao() {
        TestOfflineEntityDao testOfflineEntityDao;
        if (this.d != null) {
            return this.d;
        }
        synchronized (this) {
            if (this.d == null) {
                this.d = new TestOfflineEntityDao_Impl(this);
            }
            testOfflineEntityDao = this.d;
        }
        return testOfflineEntityDao;
    }
}
