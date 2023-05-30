package com.tencent.wcdb;

import com.tencent.wcdb.database.SQLiteDatabase;

/* loaded from: classes9.dex */
public interface DatabaseErrorHandler {
    void onCorruption(SQLiteDatabase sQLiteDatabase);
}
