package com.jingdong.common.unification.uniconfig;

import android.database.sqlite.SQLiteDatabase;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;
import com.jingdong.common.UnLog;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class UnNewIconTable {
    public static final String CACHE_TABLE_NAME = "uni_icon_config_cache";
    public static final String FIELD_ID = "id";
    public static final String FIELD_IS_VAR = "var";
    public static final String FIELD_PADDING_L = "padding_L";
    public static final String FIELD_PADDING_R = "padding_R";
    public static final String FIELD_TAG_ICON_PATH = "icon_path";
    public static final String FIELD_TAG_ICON_URL = "icon_url";
    public static final String FIELD_TAG_ID = "tag_id";
    public static final String FIELD_TEXT_COLOR = "text_color";
    public static final String FIELD_TEXT_FONT_SIZE = "font_size";
    public static final String FIELD_TEXT_PADDING = "text_padding";
    public static final String TABLE_NAME = "uni_icon_config";

    private void alterTable(SQLiteDatabase sQLiteDatabase, String str) {
        sQLiteDatabase.execSQL("ALTER TABLE " + str + " ADD " + FIELD_TEXT_FONT_SIZE + " INTEGER;");
    }

    private String createTable(String str) {
        return "CREATE TABLE IF NOT EXISTS " + str + "('id' INTEGER PRIMARY  KEY AUTOINCREMENT NOT NULL ,tag_id VARCHAR(10) ,icon_url TEXT ,icon_path TEXT ,text_color TEXT ,text_padding TEXT ," + FIELD_PADDING_L + " TEXT ," + FIELD_PADDING_R + " TEXT ," + FIELD_IS_VAR + " INTEGER," + FIELD_TEXT_FONT_SIZE + " INTEGER)";
    }

    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(createTable("uni_icon_config"));
        sQLiteDatabase.execSQL(createTable(CACHE_TABLE_NAME));
    }

    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (i2 < 3) {
            try {
                sQLiteDatabase.beginTransaction();
                sQLiteDatabase.execSQL("drop table if exists uni_icon_config");
                create(sQLiteDatabase);
                sQLiteDatabase.setTransactionSuccessful();
                UnSharedPreferencesUtils.putLong(a.g().d(), UnIconConfigConstants.SHARED_UNI_CONFIG_DATA_VERSION, 0L);
                UnSharedPreferencesUtils.putLong(a.g().d(), UnIconConfigConstants.SHARED_UNI_CONFIG_DOWNLOAD_FINISH_DATA_VERSION, 0L);
                return;
            } catch (Throwable th) {
                UnLog.e("uni_icon_config", th.getMessage(), th);
                return;
            }
        }
        try {
            alterTable(sQLiteDatabase, "uni_icon_config");
            alterTable(sQLiteDatabase, CACHE_TABLE_NAME);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }
}
