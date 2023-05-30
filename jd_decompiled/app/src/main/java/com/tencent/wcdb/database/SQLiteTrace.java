package com.tencent.wcdb.database;

import java.util.List;

/* loaded from: classes9.dex */
public interface SQLiteTrace {
    void onConnectionObtained(SQLiteDatabase sQLiteDatabase, String str, long j2, boolean z);

    void onConnectionPoolBusy(SQLiteDatabase sQLiteDatabase, String str, List<String> list, String str2);

    void onDatabaseCorrupted(SQLiteDatabase sQLiteDatabase);

    void onSQLExecuted(SQLiteDatabase sQLiteDatabase, String str, int i2, long j2);
}
