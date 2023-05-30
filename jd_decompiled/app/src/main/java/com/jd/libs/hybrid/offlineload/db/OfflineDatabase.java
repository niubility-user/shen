package com.jd.libs.hybrid.offlineload.db;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.db.converter.RoomDateConverts;
import com.jd.libs.hybrid.offlineload.entity.BuildInOfflineEntity;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntity;
import com.jd.libs.hybrid.offlineload.entity.TestOfflineEntity;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;

@TypeConverters({RoomDateConverts.class})
@Database(entities = {OfflineEntity.class, BuildInOfflineEntity.class, TestOfflineEntity.class}, version = 10)
/* loaded from: classes16.dex */
public abstract class OfflineDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "hybrid-offline.db";
    private static volatile OfflineDatabase a;

    private static OfflineDatabase a(Context context, boolean z) {
        RoomDatabase.Builder databaseBuilder = Room.databaseBuilder(context, OfflineDatabase.class, DATABASE_NAME);
        databaseBuilder.addCallback(new RoomDatabase.Callback() { // from class: com.jd.libs.hybrid.offlineload.db.OfflineDatabase.1
            @Override // androidx.room.RoomDatabase.Callback
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
                super.onDestructiveMigration(supportSQLiteDatabase);
                Log.w("[Offline-file] database performed destructive migration, delete all files");
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append("db ver = ");
                    sb.append(supportSQLiteDatabase != null ? Integer.valueOf(supportSQLiteDatabase.getVersion()) : "");
                    OfflineExceptionUtils.reportDatabaseError("onDestructiveMigration", sb.toString());
                    OfflineFileUtils.deleteAllFiles(HybridSettings.getAppContext());
                } catch (Exception e2) {
                    Log.e("JDHybrid", e2);
                }
            }
        });
        databaseBuilder.fallbackToDestructiveMigrationOnDowngrade();
        databaseBuilder.fallbackToDestructiveMigration();
        if (z) {
            databaseBuilder.addMigrations(new Migration(9, 10) { // from class: com.jd.libs.hybrid.offlineload.db.OfflineDatabase.2
                @Override // androidx.room.migration.Migration
                public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
                    Log.d("[Offline-file] database migrates from 9 to 10");
                    try {
                        supportSQLiteDatabase.execSQL(OfflineEntity.SQL_MIGRATE_9_TO_10_PART1);
                        supportSQLiteDatabase.execSQL(OfflineEntity.SQL_MIGRATE_9_TO_10_PART2);
                        supportSQLiteDatabase.execSQL(OfflineEntity.SQL_MIGRATE_9_TO_10_PART3);
                        supportSQLiteDatabase.execSQL(BuildInOfflineEntity.SQL_MIGRATE_9_TO_10_PART1);
                        supportSQLiteDatabase.execSQL(BuildInOfflineEntity.SQL_MIGRATE_9_TO_10_PART2);
                        supportSQLiteDatabase.execSQL(BuildInOfflineEntity.SQL_MIGRATE_9_TO_10_PART3);
                        supportSQLiteDatabase.execSQL(TestOfflineEntity.SQL_MIGRATE_9_TO_10_PART1);
                        supportSQLiteDatabase.execSQL(TestOfflineEntity.SQL_MIGRATE_9_TO_10_PART2);
                        supportSQLiteDatabase.execSQL(TestOfflineEntity.SQL_MIGRATE_9_TO_10_PART3);
                    } catch (Throwable th) {
                        Log.e("OfflineDatabase", th);
                        OfflineExceptionUtils.reportDatabaseError("Error in OfflineDatabase migration 9-10", th);
                    }
                }
            }, new Migration(8, 9) { // from class: com.jd.libs.hybrid.offlineload.db.OfflineDatabase.3
                @Override // androidx.room.migration.Migration
                public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
                    Log.d("[Offline-file] database migrates from 8 to 9");
                    try {
                        supportSQLiteDatabase.execSQL(OfflineEntity.SQL_MIGRATE_8_TO_9_PART1);
                        supportSQLiteDatabase.execSQL(OfflineEntity.SQL_MIGRATE_8_TO_9_PART2);
                        supportSQLiteDatabase.execSQL(BuildInOfflineEntity.SQL_MIGRATE_8_TO_9_PART1);
                        supportSQLiteDatabase.execSQL(BuildInOfflineEntity.SQL_MIGRATE_8_TO_9_PART2);
                        supportSQLiteDatabase.execSQL(TestOfflineEntity.SQL_MIGRATE_8_TO_9_PART1);
                        supportSQLiteDatabase.execSQL(TestOfflineEntity.SQL_MIGRATE_8_TO_9_PART2);
                    } catch (Throwable th) {
                        Log.e("OfflineDatabase", th);
                        OfflineExceptionUtils.reportDatabaseError("Error in OfflineDatabase migration 8-9", th);
                    }
                }
            }, new Migration(7, 8) { // from class: com.jd.libs.hybrid.offlineload.db.OfflineDatabase.4
                @Override // androidx.room.migration.Migration
                public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
                    Log.d("[Offline-file] database migrates from 7 to 8");
                    try {
                        supportSQLiteDatabase.execSQL(OfflineEntity.SQL_MIGRATE_7_TO_8_PART1);
                        supportSQLiteDatabase.execSQL(BuildInOfflineEntity.SQL_MIGRATE_7_TO_8_PART1);
                        supportSQLiteDatabase.execSQL(TestOfflineEntity.SQL_MIGRATE_7_TO_8_PART1);
                    } catch (Throwable th) {
                        Log.e("OfflineDatabase", th);
                        OfflineExceptionUtils.reportDatabaseError("Error in OfflineDatabase migration 7-8", th);
                    }
                }
            }, new Migration(6, 7) { // from class: com.jd.libs.hybrid.offlineload.db.OfflineDatabase.5
                @Override // androidx.room.migration.Migration
                public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
                    Log.d("[Offline-file] database migrates from 6 to 7");
                    try {
                        supportSQLiteDatabase.execSQL(OfflineEntity.SQL_MIGRATE_6_TO_7_PART1);
                        supportSQLiteDatabase.execSQL(OfflineEntity.SQL_MIGRATE_6_TO_7_PART2);
                        supportSQLiteDatabase.execSQL(OfflineEntity.SQL_MIGRATE_6_TO_7_PART3);
                        supportSQLiteDatabase.execSQL(BuildInOfflineEntity.SQL_MIGRATE_6_TO_7_PART1);
                        supportSQLiteDatabase.execSQL(BuildInOfflineEntity.SQL_MIGRATE_6_TO_7_PART2);
                        supportSQLiteDatabase.execSQL(BuildInOfflineEntity.SQL_MIGRATE_6_TO_7_PART3);
                        supportSQLiteDatabase.execSQL(TestOfflineEntity.SQL_MIGRATE_6_TO_7_PART1);
                        supportSQLiteDatabase.execSQL(TestOfflineEntity.SQL_MIGRATE_6_TO_7_PART2);
                        supportSQLiteDatabase.execSQL(TestOfflineEntity.SQL_MIGRATE_6_TO_7_PART3);
                    } catch (Throwable th) {
                        Log.e("OfflineDatabase", th);
                        OfflineExceptionUtils.reportDatabaseError("Error in OfflineDatabase migration 6-7", th);
                    }
                }
            });
        }
        return (OfflineDatabase) databaseBuilder.build();
    }

    private static OfflineDatabase b(Context context) {
        OfflineDatabase a2 = a(context, true);
        try {
            a2.getOpenHelper().getWritableDatabase();
            return a2;
        } catch (Throwable th) {
            Log.e("OfflineDatabase", "Error in opening database, try to re-open using destructive migration.", th);
            OfflineExceptionUtils.reportDatabaseError("Error in open OfflineDatabase", th);
            a2.getOpenHelper().close();
            return a(context, false);
        }
    }

    public static OfflineDatabase getInstance(Context context) {
        if (a == null) {
            synchronized (OfflineDatabase.class) {
                if (a == null) {
                    a = b(context.getApplicationContext());
                }
            }
        }
        return a;
    }

    public abstract BuildInOfflineEntityDao getBuildInDao();

    public abstract OfflineEntityDao getDao();

    public abstract TestOfflineEntityDao getTestDao();
}
