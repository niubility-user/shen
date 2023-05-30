package com.jingdong.common.unification.uniconfig;

import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.UnLog;
import com.jingdong.jdsdk.db.IJdTable;

/* loaded from: classes6.dex */
public class UnIconTable implements IJdTable {
    public static final String FIELD_ID = "id";
    public static final String FIELD_TAG_ICON_PATH = "icon_path";
    public static final String FIELD_TAG_ICON_URL = "icon_url";
    public static final String FIELD_TAG_ID = "tag_id";
    public static final String FIELD_TEXT_COLOR = "text_color";
    public static final String FIELD_TEXT_PADDING = "text_padding";
    public static final String TABLE_NAME = "uni_icon_config";

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS uni_icon_config('id' INTEGER PRIMARY  KEY AUTOINCREMENT NOT NULL ,tag_id VARCHAR(10) ,icon_url TEXT ,icon_path TEXT ,text_color TEXT ,text_padding TEXT)");
    }

    @Override // com.jingdong.jdsdk.db.IJdTable
    public void upgrade(SQLiteDatabase sQLiteDatabase, int i2, int i3) {
        if (i2 <= 25278) {
            sQLiteDatabase.beginTransaction();
            try {
                sQLiteDatabase.execSQL("ALTER TABLE uni_icon_config ADD COLUMN text_color TEXT");
                sQLiteDatabase.execSQL("ALTER TABLE uni_icon_config ADD COLUMN text_padding TEXT");
                sQLiteDatabase.setTransactionSuccessful();
            } finally {
                try {
                } finally {
                }
            }
        } else if (i2 <= 25278 || i2 > 25285) {
        } else {
            sQLiteDatabase.beginTransaction();
            try {
                sQLiteDatabase.execSQL("ALTER TABLE uni_icon_config ADD COLUMN text_padding TEXT");
                sQLiteDatabase.setTransactionSuccessful();
            } finally {
                try {
                    UnLog.e("uni_icon_config", th.getMessage(), th);
                } finally {
                }
            }
        }
    }
}
