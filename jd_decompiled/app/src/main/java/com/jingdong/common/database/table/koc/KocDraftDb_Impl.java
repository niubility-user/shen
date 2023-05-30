package com.jingdong.common.database.table.koc;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes5.dex */
public final class KocDraftDb_Impl extends KocDraftDb {
    private volatile KocDraftDao _kocDraftDao;

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `KocDraftEntity`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // com.jingdong.common.database.table.koc.KocDraftDb, androidx.room.RoomDatabase
    public /* bridge */ /* synthetic */ void close() {
        super.close();
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "KocDraftEntity");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(2) { // from class: com.jingdong.common.database.table.koc.KocDraftDb_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `KocDraftEntity` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `createTime` INTEGER NOT NULL, `lastModifyTime` INTEGER NOT NULL, `pin` TEXT, `data` TEXT, `bId` TEXT, `bType` TEXT)");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e69b9ec4ab9e222d2a142d91d83981f3')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `KocDraftEntity`");
                if (((RoomDatabase) KocDraftDb_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) KocDraftDb_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) KocDraftDb_Impl.this).mCallbacks.get(i2)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (((RoomDatabase) KocDraftDb_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) KocDraftDb_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) KocDraftDb_Impl.this).mCallbacks.get(i2)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                ((RoomDatabase) KocDraftDb_Impl.this).mDatabase = supportSQLiteDatabase;
                KocDraftDb_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (((RoomDatabase) KocDraftDb_Impl.this).mCallbacks != null) {
                    int size = ((RoomDatabase) KocDraftDb_Impl.this).mCallbacks.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        ((RoomDatabase.Callback) ((RoomDatabase) KocDraftDb_Impl.this).mCallbacks.get(i2)).onOpen(supportSQLiteDatabase);
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
                HashMap hashMap = new HashMap(7);
                hashMap.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap.put("createTime", new TableInfo.Column("createTime", "INTEGER", true, 0, null, 1));
                hashMap.put("lastModifyTime", new TableInfo.Column("lastModifyTime", "INTEGER", true, 0, null, 1));
                hashMap.put("pin", new TableInfo.Column("pin", "TEXT", false, 0, null, 1));
                hashMap.put("data", new TableInfo.Column("data", "TEXT", false, 0, null, 1));
                hashMap.put("bId", new TableInfo.Column("bId", "TEXT", false, 0, null, 1));
                hashMap.put("bType", new TableInfo.Column("bType", "TEXT", false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("KocDraftEntity", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "KocDraftEntity");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "KocDraftEntity(com.jingdong.common.database.table.koc.KocDraftEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "e69b9ec4ab9e222d2a142d91d83981f3", "0068e11e2b62a68c34f70a50368ddd95")).build());
    }

    @Override // com.jingdong.common.database.table.koc.KocDraftDb
    public KocDraftDao getKocDraftDao() {
        KocDraftDao kocDraftDao;
        if (this._kocDraftDao != null) {
            return this._kocDraftDao;
        }
        synchronized (this) {
            if (this._kocDraftDao == null) {
                this._kocDraftDao = new KocDraftDao_Impl(this);
            }
            kocDraftDao = this._kocDraftDao;
        }
        return kocDraftDao;
    }
}
