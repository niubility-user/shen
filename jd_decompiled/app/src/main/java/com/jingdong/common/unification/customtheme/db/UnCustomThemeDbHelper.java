package com.jingdong.common.unification.customtheme.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jd.lib.un.business.widget.a;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;

/* loaded from: classes6.dex */
public class UnCustomThemeDbHelper extends SQLiteOpenHelper {
    private static final int DATA_VERSION = 19;
    private static UnCustomThemeDbHelper dbHelper;

    public UnCustomThemeDbHelper() {
        super(a.g().d(), CustomThemeConstance.dbName, (SQLiteDatabase.CursorFactory) null, 19);
    }

    private void createTable(SQLiteDatabase sQLiteDatabase) {
        String str = "CREATE TABLE theme(" + CustomThemeConstance.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + CustomThemeConstance.IMAGE_ID + " TEXT," + CustomThemeConstance.IMAGE_URL + " TEXT," + CustomThemeConstance.LOCAL_PATH + " TEXT," + CustomThemeConstance.COLOR_TYPE + " TEXT," + CustomThemeConstance.IS_EFFECTED + " INTEGER," + CustomThemeConstance.DISPLAY_TYPE + " INTEGER," + CustomThemeConstance.SORT + " TEXT," + CustomThemeConstance.MD5 + " TEXT," + CustomThemeConstance.LABLE_NAME + " TEXT)";
        String str2 = "CREATE TABLE navi_theme(" + CustomThemeConstance.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + CustomThemeConstance.NAVI_DISPLAY_TYPE + " TEXT," + CustomThemeConstance.NAVI_NAVIGATION_ID + " TEXT,functionId TEXT," + CustomThemeConstance.NAVI_LABEL_IMAGE + " TEXT," + CustomThemeConstance.NAVI_LABEL_IMAGE_PATH + " TEXT," + CustomThemeConstance.NAVI_LABEL_COLOR + " TEXT," + CustomThemeConstance.NAVI_LABEL_NAME + " TEXT," + CustomThemeConstance.NAVI_CUT_LABEL_NAME + " TEXT," + CustomThemeConstance.NAVI_OPT_LABEL_IMAGE + " TEXT," + CustomThemeConstance.NAVI_OPT_LABEL_IMAGE_PATH + " TEXT," + CustomThemeConstance.NAVI_OPT_LABEL_COLOR + " TEXT," + CustomThemeConstance.NAVI_USE_TYPE + " TEXT,url TEXT," + CustomThemeConstance.NAVI_MODEL + " TEXT," + CustomThemeConstance.NAVI_LOTTIE_URL + " TEXT," + CustomThemeConstance.NAVI_LOTTIE_PATH + " TEXT," + CustomThemeConstance.NAVI_CLICK_EVENT_ID + " TEXT," + CustomThemeConstance.NAVI_TAB_NAME_SELECTED + " TEXT, " + CustomThemeConstance.NAVI_LOTTIE_MD5 + " TEXT, " + CustomThemeConstance.NAVI_LABEL_IMAGE_MD5 + " TEXT, " + CustomThemeConstance.NAVI_OPT_IMAGE_MD5 + " TEXT, " + CustomThemeConstance.NAVI_IMAGE_DARK_TAG + " INTEGER," + CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL_MD5 + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_MD5 + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL_MD5 + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_MD5 + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_URL + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_URL + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_PATH + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_PATH + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_PATH + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_PATH + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL_MD5 + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_PATH + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_PATH + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL_MD5 + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_PATH + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL_MD5 + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_PATH + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL_MD5 + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_MARKET_PLAY_TIMES + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_MARKET_AUTO + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_MARKET_ID + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_BIG_ICON_IS_OPEN + " TEXT," + CustomThemeConstance.NAVI_MIDDLE_BIG_LOTTIE_FLAG + " TEXT," + CustomThemeConstance.NAVI_ANGLE_SWITCH + " TEXT)";
        sQLiteDatabase.execSQL(str);
        sQLiteDatabase.execSQL(str2);
    }

    public static synchronized SQLiteDatabase getDatabase() {
        SQLiteDatabase writableDatabase;
        synchronized (UnCustomThemeDbHelper.class) {
            if (dbHelper == null) {
                dbHelper = new UnCustomThemeDbHelper();
            }
            try {
                writableDatabase = dbHelper.getWritableDatabase();
            } catch (Exception unused) {
                a.g().d().deleteDatabase(CustomThemeConstance.dbName);
                return dbHelper.getWritableDatabase();
            }
        }
        return writableDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        createTable(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (i2 < 19) {
            sQLiteDatabase.execSQL("drop table if exists theme");
            sQLiteDatabase.execSQL("drop table if exists navi_theme");
            createTable(sQLiteDatabase);
        }
    }
}
