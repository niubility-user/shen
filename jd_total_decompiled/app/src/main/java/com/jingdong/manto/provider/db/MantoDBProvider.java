package com.jingdong.manto.provider.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import com.jingdong.manto.utils.MantoLog;

/* loaded from: classes16.dex */
public class MantoDBProvider extends DatabaseProvider {
    @Override // com.jingdong.manto.provider.db.DatabaseProvider
    public SQLiteOpenHelper a(Context context, String str) {
        return b.a(context, str);
    }

    @Override // com.jingdong.manto.provider.db.DatabaseProvider
    public String a() {
        String str = com.jingdong.manto.b.m() + ".db";
        MantoLog.e("better", "dbName: " + str);
        return str;
    }
}
