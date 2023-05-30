package com.jingdong.common.database.table.koc;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.jingdong.jdsdk.JdSdk;

@Database(entities = {KocDraftEntity.class}, exportSchema = false, version = 2)
/* loaded from: classes5.dex */
abstract class KocDraftDb extends RoomDatabase {
    private static final String DB_NAME = "com.jd.lib.koc.db";
    static final Migration MIGRATION_1_2 = new Migration(1, 2) { // from class: com.jingdong.common.database.table.koc.KocDraftDb.1
        @Override // androidx.room.migration.Migration
        public void migrate(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE KocDraftEntity ADD COLUMN bId TEXT");
            supportSQLiteDatabase.execSQL("ALTER TABLE KocDraftEntity ADD COLUMN bType TEXT");
        }
    };
    private static volatile KocDraftDb kocDraftDb;

    public static KocDraftDb getInstance() {
        if (kocDraftDb == null) {
            synchronized (KocDraftDb.class) {
                if (kocDraftDb == null) {
                    kocDraftDb = (KocDraftDb) Room.databaseBuilder(JdSdk.getInstance().getApplicationContext(), KocDraftDb.class, DB_NAME).addMigrations(MIGRATION_1_2).build();
                }
            }
        }
        return kocDraftDb;
    }

    @Override // androidx.room.RoomDatabase
    public void close() {
        try {
            super.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public abstract KocDraftDao getKocDraftDao();
}
