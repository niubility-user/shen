package com.jingdong.sdk.platform.lib.openapi.db;

import android.database.sqlite.SQLiteDatabase;

/* loaded from: classes10.dex */
public interface IPlatformTable {
    public static final String TABLE_SEARCH = "search";

    void create(SQLiteDatabase sQLiteDatabase);

    void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3);
}
