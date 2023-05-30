package com.jingdong.sdk.platform.lib.openapi.utils;

import android.database.sqlite.SQLiteDatabase;

/* loaded from: classes10.dex */
public interface IDBHelperUtil {
    void closeDatabase();

    SQLiteDatabase getDatabase();
}
