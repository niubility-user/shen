package com.jingdong.common.unification.navigationbar.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class NavigationDbHelper extends SQLiteOpenHelper {
    public static final String TAG = NavigationDbHelper.class.getSimpleName();
    private static NavigationDbHelper dbHelper = null;
    private static final int versionCode = 2;

    public NavigationDbHelper() {
        super(JdSdk.getInstance().getApplicationContext(), NavigationDbConstants.dbName, (SQLiteDatabase.CursorFactory) null, 2);
    }

    private void createFrequencyTable(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                if (OKLog.D) {
                    OKLog.d(TAG, "onCreate-create table navigation_frequency_rule");
                }
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS navigation_frequency_rule('id' INTEGER PRIMARY  KEY AUTOINCREMENT NOT NULL ,frequency_rule_id TEXT ,frequency_rule_state INTEGER ,startTime LONG ,endTime LONG ,dataVersion TEXT ,frequency_rule_position TEXT ,statisticsCycle INTEGER ,sumNum INTEGER ,bubbleNum INTEGER ,angleNum INTEGER ,iconAbnormityNum INTEGER ,bubbleAbnormityNum INTEGER ,angleAbnormityNum INTEGER)");
            } catch (Exception e2) {
                if (OKLog.E) {
                    OKLog.e(TAG, e2);
                }
            }
        }
    }

    public static synchronized SQLiteDatabase getDatabase() {
        SQLiteDatabase writableDatabase;
        synchronized (NavigationDbHelper.class) {
            if (dbHelper == null) {
                dbHelper = new NavigationDbHelper();
            }
            try {
                writableDatabase = dbHelper.getWritableDatabase();
            } catch (Exception unused) {
                JdSdk.getInstance().getApplicationContext().deleteDatabase(NavigationDbConstants.dbName);
                return dbHelper.getWritableDatabase();
            }
        }
        return writableDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        if (OKLog.D) {
            OKLog.d(TAG, "onCreate-create table navigation_material_show");
        }
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS navigation_material_show('id' INTEGER PRIMARY  KEY AUTOINCREMENT NOT NULL ,material_id TEXT ,material_type INTEGER ,combination_type INTEGER ,material_position TEXT ,material_show_time LONG)");
        createFrequencyTable(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (OKLog.D) {
            OKLog.d(TAG, "onUpgrade-oldVersion=" + i2 + " newVersion=" + i3);
        }
        createFrequencyTable(sQLiteDatabase);
    }
}
