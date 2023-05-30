package com.jingdong.jdsdk.db;

import android.database.sqlite.SQLiteDatabase;

/* loaded from: classes14.dex */
public interface IJdTable {
    void create(SQLiteDatabase sQLiteDatabase);

    void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3);
}
