package com.xiaomi.push.providers;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import g.j.a.a.a.c;

/* loaded from: classes11.dex */
public class a extends SQLiteOpenHelper {

    /* renamed from: g  reason: collision with root package name */
    private static int f18950g = 1;

    /* renamed from: h  reason: collision with root package name */
    public static final Object f18951h = new Object();

    /* renamed from: i  reason: collision with root package name */
    private static final String[] f18952i = {"package_name", "TEXT", "message_ts", " LONG DEFAULT 0 ", "bytes", " LONG DEFAULT 0 ", "network_type", " INT DEFAULT -1 ", "rcv", " INT DEFAULT -1 ", "imsi", "TEXT"};

    public a(Context context) {
        super(context, "traffic.db", (SQLiteDatabase.CursorFactory) null, f18950g);
    }

    private void f(SQLiteDatabase sQLiteDatabase) {
        StringBuilder sb = new StringBuilder("CREATE TABLE traffic(_id INTEGER  PRIMARY KEY ,");
        int i2 = 0;
        while (true) {
            String[] strArr = f18952i;
            if (i2 >= strArr.length - 1) {
                sb.append(");");
                sQLiteDatabase.execSQL(sb.toString());
                return;
            }
            if (i2 != 0) {
                sb.append(DYConstants.DY_REGEX_COMMA);
            }
            sb.append(strArr[i2]);
            sb.append(LangUtils.SINGLE_SPACE);
            sb.append(strArr[i2 + 1]);
            i2 += 2;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (f18951h) {
            try {
                f(sQLiteDatabase);
            } catch (SQLException e2) {
                c.s(e2);
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }
}
