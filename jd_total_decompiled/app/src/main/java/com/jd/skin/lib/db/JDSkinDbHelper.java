package com.jd.skin.lib.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.jd.skin.lib.JDSkinSDK;
import com.jd.skin.lib.Utils.ConstancesUtils;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes18.dex */
public class JDSkinDbHelper extends SQLiteOpenHelper {
    private static JDSkinDbHelper dbHelper;
    private SQLiteDatabase db;
    private AtomicInteger mOpenCounter;

    public JDSkinDbHelper() {
        super(JDSkinSDK.getInstance().getContext().getApplicationContext(), ConstancesUtils.dbName, (SQLiteDatabase.CursorFactory) null, 5);
        this.mOpenCounter = new AtomicInteger();
    }

    public static JDSkinDbHelper getInstance() {
        if (dbHelper == null) {
            synchronized (JDSkinDbHelper.class) {
                if (dbHelper == null) {
                    dbHelper = new JDSkinDbHelper();
                }
            }
        }
        return dbHelper;
    }

    public void closeSQLiteDatabase() {
        SQLiteDatabase sQLiteDatabase;
        if (this.mOpenCounter.decrementAndGet() != 0 || (sQLiteDatabase = this.db) == null) {
            return;
        }
        sQLiteDatabase.close();
    }

    public SQLiteDatabase getSQLiteDataBase() {
        if (this.mOpenCounter.incrementAndGet() == 1) {
            try {
                this.db = dbHelper.getWritableDatabase();
            } catch (Exception e2) {
                e2.printStackTrace();
                JDSkinSDK.getInstance().getContext().deleteDatabase(ConstancesUtils.dbName);
                this.db = dbHelper.getWritableDatabase();
            }
        }
        return this.db;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE skin_tab(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + ConstancesUtils.clumn_code + " TEXT," + ConstancesUtils.clumn_text + " TEXT," + ConstancesUtils.clumn_fontSize + " TEXT," + ConstancesUtils.clumn_fontSizeSelected + " TEXT," + ConstancesUtils.clumn_font + " TEXT," + ConstancesUtils.clumn_fontColor + " TEXT," + ConstancesUtils.clumn_fontColorSelected + " TEXT," + ConstancesUtils.clumn_bgColor + " TEXT," + ConstancesUtils.clumn_bgColorSelected + " TEXT," + ConstancesUtils.clumn_bgImage + " TEXT," + ConstancesUtils.clumn_bgImageSelected + " TEXT," + ConstancesUtils.clumn_extInfo + " TEXT," + ConstancesUtils.clumn_localBgImage + " TEXT," + ConstancesUtils.clumn_localgImageSelected + " TEXT," + ConstancesUtils.clumn_fileResource + " TEXT," + ConstancesUtils.clumn_localFileResource + " TEXT)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
    }
}
