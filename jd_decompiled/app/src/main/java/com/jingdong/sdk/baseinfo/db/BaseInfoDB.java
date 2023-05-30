package com.jingdong.sdk.baseinfo.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PrivacyInfo.class}, exportSchema = false, version = 1)
/* loaded from: classes.dex */
public abstract class BaseInfoDB extends RoomDatabase {
    public abstract b a();
}
