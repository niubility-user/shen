package com.jingdong.sdk.jdroom;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.jingdong.sdk.jdroom.a.a;
import com.jingdong.sdk.jdroom.a.c;

@Database(entities = {c.class}, exportSchema = false, version = 1)
/* loaded from: classes7.dex */
public abstract class JDDataBase extends RoomDatabase {
    private static volatile JDDataBase a;

    public static JDDataBase a(Context context) {
        if (a == null) {
            synchronized (JDDataBase.class) {
                if (a == null) {
                    a = (JDDataBase) Room.databaseBuilder(context.getApplicationContext(), JDDataBase.class, "jd_room").allowMainThreadQueries().build();
                }
            }
        }
        return a;
    }

    public abstract a b();
}
