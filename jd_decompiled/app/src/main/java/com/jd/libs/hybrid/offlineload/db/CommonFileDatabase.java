package com.jd.libs.hybrid.offlineload.db;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.entity.CommonEntity;
import com.jd.libs.hybrid.offlineload.utils.CommonFileUtils;

@Database(entities = {CommonEntity.class}, version = 3)
/* loaded from: classes16.dex */
public abstract class CommonFileDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "hybrid-offline-common.db";
    private static volatile CommonFileDatabase a;

    private static CommonFileDatabase a(Context context) {
        RoomDatabase.Builder databaseBuilder = Room.databaseBuilder(context, CommonFileDatabase.class, DATABASE_NAME);
        databaseBuilder.addCallback(new RoomDatabase.Callback() { // from class: com.jd.libs.hybrid.offlineload.db.CommonFileDatabase.1
            @Override // androidx.room.RoomDatabase.Callback
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase supportSQLiteDatabase) {
                super.onDestructiveMigration(supportSQLiteDatabase);
                Log.d("[Common-file] database performed destructive migration, delete all common local files");
                try {
                    CommonFileUtils.deleteDownloadedFiles(HybridSettings.getAppContext());
                } catch (Exception e2) {
                    Log.e("JDHybrid", e2);
                }
            }
        });
        databaseBuilder.fallbackToDestructiveMigration();
        return (CommonFileDatabase) databaseBuilder.build();
    }

    public static CommonFileDatabase getInstance(Context context) {
        if (a == null) {
            synchronized (CommonFileDatabase.class) {
                if (a == null) {
                    a = a(context.getApplicationContext());
                }
            }
        }
        return a;
    }

    public abstract CommonEntityDao getDao();
}
