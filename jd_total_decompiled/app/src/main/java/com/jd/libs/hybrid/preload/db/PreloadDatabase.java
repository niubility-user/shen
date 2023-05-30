package com.jd.libs.hybrid.preload.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.jd.libs.hybrid.preload.db.converter.RoomJdJsonObjConverts;
import com.jd.libs.hybrid.preload.db.converter.RoomListStrConverts;
import com.jd.libs.hybrid.preload.db.converter.RoomMapConverts;
import com.jd.libs.hybrid.preload.db.dao.PreloadInfoDao;
import com.jd.libs.hybrid.preload.entity.PreloadInfoEntity;

@TypeConverters({RoomMapConverts.class, RoomJdJsonObjConverts.class, RoomListStrConverts.class})
@Database(entities = {PreloadInfoEntity.class}, version = 6)
/* loaded from: classes16.dex */
public abstract class PreloadDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "hybrid-preload.db";
    private static volatile PreloadDatabase a;

    private static PreloadDatabase a(Context context) {
        RoomDatabase.Builder databaseBuilder = Room.databaseBuilder(context, PreloadDatabase.class, DATABASE_NAME);
        databaseBuilder.fallbackToDestructiveMigration();
        return (PreloadDatabase) databaseBuilder.build();
    }

    public static PreloadDatabase getInstance(Context context) {
        if (a == null) {
            synchronized (PreloadDatabase.class) {
                if (a == null) {
                    a = a(context.getApplicationContext());
                }
            }
        }
        return a;
    }

    public abstract PreloadInfoDao getDao();
}
