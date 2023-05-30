package com.jingdong.sdk.jdroom;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.jingdong.sdk.jdroom.a.b;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: classes7.dex */
public class JDDataBase_Impl extends JDDataBase {
    private volatile com.jingdong.sdk.jdroom.a.a b;

    /* loaded from: classes7.dex */
    class a extends RoomOpenHelper.Delegate {
        a(int i2) {
            super(i2);
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `JD_ReminderNewTable` (`_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `businessType` TEXT, `reminderShowTag` TEXT, `identificationId` TEXT, `reminderTitle` TEXT, `reminderImgUrl` TEXT, `startTimeMillis` REAL NOT NULL, `notificationTimeMillis` REAL NOT NULL, `insertTime` REAL NOT NULL, `jump` TEXT, `extra` TEXT, `more` TEXT, `requestCode` INTEGER NOT NULL)");
            supportSQLiteDatabase.execSQL("CREATE  INDEX `index_JD_ReminderNewTable__id` ON `JD_ReminderNewTable` (`_id`)");
            supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
            supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"f03488e7753cba1f966fd7fe87235832\")");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `JD_ReminderNewTable`");
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        protected void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (((RoomDatabase) JDDataBase_Impl.this).mCallbacks != null) {
                int size = ((RoomDatabase) JDDataBase_Impl.this).mCallbacks.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((RoomDatabase.Callback) ((RoomDatabase) JDDataBase_Impl.this).mCallbacks.get(i2)).onCreate(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
            ((RoomDatabase) JDDataBase_Impl.this).mDatabase = supportSQLiteDatabase;
            JDDataBase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
            if (((RoomDatabase) JDDataBase_Impl.this).mCallbacks != null) {
                int size = ((RoomDatabase) JDDataBase_Impl.this).mCallbacks.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((RoomDatabase.Callback) ((RoomDatabase) JDDataBase_Impl.this).mCallbacks.get(i2)).onOpen(supportSQLiteDatabase);
                }
            }
        }

        @Override // androidx.room.RoomOpenHelper.Delegate
        protected void validateMigration(SupportSQLiteDatabase supportSQLiteDatabase) {
            HashMap hashMap = new HashMap(13);
            hashMap.put("_id", new TableInfo.Column("_id", "INTEGER", true, 1));
            hashMap.put("businessType", new TableInfo.Column("businessType", "TEXT", false, 0));
            hashMap.put("reminderShowTag", new TableInfo.Column("reminderShowTag", "TEXT", false, 0));
            hashMap.put("identificationId", new TableInfo.Column("identificationId", "TEXT", false, 0));
            hashMap.put("reminderTitle", new TableInfo.Column("reminderTitle", "TEXT", false, 0));
            hashMap.put("reminderImgUrl", new TableInfo.Column("reminderImgUrl", "TEXT", false, 0));
            hashMap.put("startTimeMillis", new TableInfo.Column("startTimeMillis", "REAL", true, 0));
            hashMap.put("notificationTimeMillis", new TableInfo.Column("notificationTimeMillis", "REAL", true, 0));
            hashMap.put("insertTime", new TableInfo.Column("insertTime", "REAL", true, 0));
            hashMap.put("jump", new TableInfo.Column("jump", "TEXT", false, 0));
            hashMap.put("extra", new TableInfo.Column("extra", "TEXT", false, 0));
            hashMap.put("more", new TableInfo.Column("more", "TEXT", false, 0));
            hashMap.put("requestCode", new TableInfo.Column("requestCode", "INTEGER", true, 0));
            HashSet hashSet = new HashSet(0);
            HashSet hashSet2 = new HashSet(1);
            hashSet2.add(new TableInfo.Index("index_JD_ReminderNewTable__id", false, Arrays.asList("_id")));
            TableInfo tableInfo = new TableInfo("JD_ReminderNewTable", hashMap, hashSet, hashSet2);
            TableInfo read = TableInfo.read(supportSQLiteDatabase, "JD_ReminderNewTable");
            if (tableInfo.equals(read)) {
                return;
            }
            throw new IllegalStateException("Migration didn't properly handle JD_ReminderNewTable(com.jingdong.sdk.jdroom.reminder.ReminderEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
        }
    }

    @Override // com.jingdong.sdk.jdroom.JDDataBase
    public com.jingdong.sdk.jdroom.a.a b() {
        com.jingdong.sdk.jdroom.a.a aVar;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new b(this);
            }
            aVar = this.b;
        }
        return aVar;
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, "JD_ReminderNewTable");
    }

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new a(1), "f03488e7753cba1f966fd7fe87235832")).build());
    }
}
