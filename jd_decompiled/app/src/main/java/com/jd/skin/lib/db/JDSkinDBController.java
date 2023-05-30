package com.jd.skin.lib.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jd.skin.lib.Utils.ConstancesUtils;
import com.jd.skin.lib.bean.ResourceItems;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDSkinDBController {
    /* JADX WARN: Removed duplicated region for block: B:23:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void deleteDatas() {
        /*
            r0 = 0
            com.jd.skin.lib.db.JDSkinDbHelper r1 = com.jd.skin.lib.db.JDSkinDbHelper.getInstance()     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L26
            android.database.sqlite.SQLiteDatabase r1 = r1.getSQLiteDataBase()     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L26
            if (r1 != 0) goto L15
            if (r1 == 0) goto L14
            com.jd.skin.lib.db.JDSkinDbHelper r0 = com.jd.skin.lib.db.JDSkinDbHelper.getInstance()
            r0.closeSQLiteDatabase()
        L14:
            return
        L15:
            java.lang.String r2 = "1=1"
            java.lang.String r3 = "skin_tab"
            r1.delete(r3, r2, r0)     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L37
            if (r1 == 0) goto L36
            goto L2f
        L1f:
            r0 = move-exception
            goto L2a
        L21:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L38
        L26:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L2a:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L37
            if (r1 == 0) goto L36
        L2f:
            com.jd.skin.lib.db.JDSkinDbHelper r0 = com.jd.skin.lib.db.JDSkinDbHelper.getInstance()
            r0.closeSQLiteDatabase()
        L36:
            return
        L37:
            r0 = move-exception
        L38:
            if (r1 == 0) goto L41
            com.jd.skin.lib.db.JDSkinDbHelper r1 = com.jd.skin.lib.db.JDSkinDbHelper.getInstance()
            r1.closeSQLiteDatabase()
        L41:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.skin.lib.db.JDSkinDBController.deleteDatas():void");
    }

    private static ResourceItems getByCodes(SQLiteDatabase sQLiteDatabase, Cursor cursor, String str) {
        Cursor query = sQLiteDatabase.query(ConstancesUtils.TABLE_NAME, new String[]{ConstancesUtils.clumn_code, ConstancesUtils.clumn_text, ConstancesUtils.clumn_fontSize, ConstancesUtils.clumn_fontSizeSelected, ConstancesUtils.clumn_font, ConstancesUtils.clumn_fontColor, ConstancesUtils.clumn_fontColorSelected, ConstancesUtils.clumn_bgColor, ConstancesUtils.clumn_bgColorSelected, ConstancesUtils.clumn_bgImage, ConstancesUtils.clumn_bgImageSelected, ConstancesUtils.clumn_extInfo, ConstancesUtils.clumn_localBgImage, ConstancesUtils.clumn_localgImageSelected, ConstancesUtils.clumn_fileResource, ConstancesUtils.clumn_localFileResource}, ConstancesUtils.clumn_code + "=?", new String[]{str}, null, null, null);
        if (query != null && query.moveToFirst()) {
            ResourceItems resourceItems = new ResourceItems();
            resourceItems.setCode(query.getString(query.getColumnIndex(ConstancesUtils.clumn_code)));
            resourceItems.setText(query.getString(query.getColumnIndex(ConstancesUtils.clumn_text)));
            resourceItems.setFontSize(query.getString(query.getColumnIndex(ConstancesUtils.clumn_fontSize)));
            resourceItems.setFontSizeSelected(query.getString(query.getColumnIndex(ConstancesUtils.clumn_fontSizeSelected)));
            resourceItems.setFontColor(query.getString(query.getColumnIndex(ConstancesUtils.clumn_fontColor)));
            resourceItems.setFontColorSelected(query.getString(query.getColumnIndex(ConstancesUtils.clumn_fontColorSelected)));
            resourceItems.setFont(query.getString(query.getColumnIndex(ConstancesUtils.clumn_font)));
            resourceItems.setBgColor(query.getString(query.getColumnIndex(ConstancesUtils.clumn_bgColor)));
            resourceItems.setBgColorSelected(query.getString(query.getColumnIndex(ConstancesUtils.clumn_bgColorSelected)));
            resourceItems.setBgImage(query.getString(query.getColumnIndex(ConstancesUtils.clumn_bgImage)));
            resourceItems.setBgImageSelected(query.getString(query.getColumnIndex(ConstancesUtils.clumn_bgImageSelected)));
            resourceItems.setLocalBgImage(query.getString(query.getColumnIndex(ConstancesUtils.clumn_localBgImage)));
            resourceItems.setLocalgImageSelected(query.getString(query.getColumnIndex(ConstancesUtils.clumn_localgImageSelected)));
            resourceItems.setExtInfo(query.getString(query.getColumnIndex(ConstancesUtils.clumn_extInfo)));
            resourceItems.setLocalResource(query.getString(query.getColumnIndex(ConstancesUtils.clumn_localFileResource)));
            resourceItems.setResource(query.getString(query.getColumnIndex(ConstancesUtils.clumn_fileResource)));
            return resourceItems;
        }
        return null;
    }

    private static boolean insertData(SQLiteDatabase sQLiteDatabase, ResourceItems resourceItems) {
        if (resourceItems == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstancesUtils.clumn_code, resourceItems.getCode());
        contentValues.put(ConstancesUtils.clumn_text, resourceItems.getText());
        contentValues.put(ConstancesUtils.clumn_fontSize, resourceItems.getFontSize());
        contentValues.put(ConstancesUtils.clumn_fontSizeSelected, resourceItems.getFontSizeSelected());
        contentValues.put(ConstancesUtils.clumn_font, resourceItems.getFont());
        contentValues.put(ConstancesUtils.clumn_fontColor, resourceItems.getFontColor());
        contentValues.put(ConstancesUtils.clumn_fontColorSelected, resourceItems.getFontColorSelected());
        contentValues.put(ConstancesUtils.clumn_bgColor, resourceItems.getBgColor());
        contentValues.put(ConstancesUtils.clumn_bgColorSelected, resourceItems.getBgColorSelected());
        contentValues.put(ConstancesUtils.clumn_bgImage, resourceItems.getBgImage());
        contentValues.put(ConstancesUtils.clumn_bgImageSelected, resourceItems.getBgImageSelected());
        contentValues.put(ConstancesUtils.clumn_extInfo, resourceItems.getExtInfo());
        contentValues.put(ConstancesUtils.clumn_localBgImage, resourceItems.getLocalBgImage());
        contentValues.put(ConstancesUtils.clumn_localgImageSelected, resourceItems.getLocalgImageSelected());
        contentValues.put(ConstancesUtils.clumn_fileResource, resourceItems.getResource());
        contentValues.put(ConstancesUtils.clumn_localFileResource, resourceItems.getLocalResource());
        return sQLiteDatabase.insert(ConstancesUtils.TABLE_NAME, null, contentValues) > 0;
    }

    public static boolean insertDatas(Map<String, ResourceItems> map) {
        if (map == null) {
            return false;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                SQLiteDatabase sQLiteDataBase = JDSkinDbHelper.getInstance().getSQLiteDataBase();
                if (sQLiteDataBase == null) {
                    if (sQLiteDataBase != null) {
                        try {
                            sQLiteDataBase.endTransaction();
                            JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                        } catch (Exception unused) {
                        }
                    }
                    return false;
                }
                sQLiteDataBase.beginTransaction();
                Iterator<ResourceItems> it = map.values().iterator();
                while (it.hasNext()) {
                    insertData(sQLiteDataBase, it.next());
                }
                sQLiteDataBase.setTransactionSuccessful();
                if (sQLiteDataBase != null) {
                    try {
                        sQLiteDataBase.endTransaction();
                        JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                    } catch (Exception unused2) {
                    }
                }
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (0 != 0) {
                    try {
                        sQLiteDatabase.endTransaction();
                        JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                    } catch (Exception unused3) {
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    sQLiteDatabase.endTransaction();
                    JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                } catch (Exception unused4) {
                }
            }
            throw th;
        }
    }

    public static synchronized ResourceItems queryDataByCode(String str, boolean z) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        synchronized (JDSkinDBController.class) {
            Cursor cursor2 = null;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                sQLiteDatabase = JDSkinDbHelper.getInstance().getSQLiteDataBase();
                if (sQLiteDatabase == null) {
                    if (sQLiteDatabase != null) {
                        JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                    }
                    return null;
                }
                try {
                    String[] strArr = {ConstancesUtils.clumn_code, ConstancesUtils.clumn_text, ConstancesUtils.clumn_fontSize, ConstancesUtils.clumn_fontSizeSelected, ConstancesUtils.clumn_font, ConstancesUtils.clumn_fontColor, ConstancesUtils.clumn_fontColorSelected, ConstancesUtils.clumn_bgColor, ConstancesUtils.clumn_bgColorSelected, ConstancesUtils.clumn_bgImage, ConstancesUtils.clumn_bgImageSelected, ConstancesUtils.clumn_extInfo, ConstancesUtils.clumn_localBgImage, ConstancesUtils.clumn_localgImageSelected, ConstancesUtils.clumn_fileResource, ConstancesUtils.clumn_localFileResource};
                    String str2 = ConstancesUtils.clumn_code + "=?";
                    if (z) {
                        str = str + "Dark";
                    }
                    if (OKLog.D) {
                        OKLog.d("JDSkinSDK", "-code-->" + str);
                    }
                    cursor = sQLiteDatabase.query(ConstancesUtils.TABLE_NAME, strArr, str2, new String[]{str}, null, null, null);
                    if (cursor == null) {
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                        }
                        return null;
                    }
                    try {
                        if (cursor.moveToFirst()) {
                            ResourceItems resourceItems = new ResourceItems();
                            resourceItems.setCode(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_code)));
                            resourceItems.setText(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_text)));
                            resourceItems.setFontSize(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_fontSize)));
                            resourceItems.setFontSizeSelected(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_fontSizeSelected)));
                            resourceItems.setFontColor(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_fontColor)));
                            resourceItems.setFontColorSelected(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_fontColorSelected)));
                            resourceItems.setFont(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_font)));
                            resourceItems.setBgColor(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_bgColor)));
                            resourceItems.setBgColorSelected(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_bgColorSelected)));
                            resourceItems.setBgImage(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_bgImage)));
                            resourceItems.setBgImageSelected(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_bgImageSelected)));
                            resourceItems.setLocalBgImage(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_localBgImage)));
                            resourceItems.setLocalgImageSelected(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_localgImageSelected)));
                            resourceItems.setExtInfo(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_extInfo)));
                            resourceItems.setLocalResource(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_localFileResource)));
                            resourceItems.setResource(cursor.getString(cursor.getColumnIndex(ConstancesUtils.clumn_fileResource)));
                            if (cursor != null && !cursor.isClosed()) {
                                cursor.close();
                            }
                            if (sQLiteDatabase != null) {
                                JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                            }
                            return resourceItems;
                        }
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                        }
                        return null;
                    } catch (Exception unused) {
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                        }
                        return null;
                    } catch (Throwable th) {
                        cursor2 = cursor;
                        th = th;
                        if (cursor2 != null && !cursor2.isClosed()) {
                            cursor2.close();
                        }
                        if (sQLiteDatabase != null) {
                            JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                        }
                        throw th;
                    }
                } catch (Exception unused2) {
                    cursor = null;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception unused3) {
                cursor = null;
                sQLiteDatabase = null;
            } catch (Throwable th3) {
                th = th3;
                sQLiteDatabase = null;
            }
        }
    }

    public static synchronized Map<String, ResourceItems> queryDataByCodes(List<String> list, boolean z) {
        SQLiteDatabase sQLiteDatabase;
        synchronized (JDSkinDBController.class) {
            SQLiteDatabase sQLiteDatabase2 = null;
            if (list == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            try {
                sQLiteDatabase = JDSkinDbHelper.getInstance().getSQLiteDataBase();
                if (sQLiteDatabase == null) {
                    if (sQLiteDatabase != null) {
                        JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                    }
                    return null;
                }
                try {
                    for (String str : list) {
                        if (z) {
                            str = str + "Dark";
                        }
                        ResourceItems byCodes = getByCodes(sQLiteDatabase, null, str);
                        if (byCodes != null) {
                            hashMap.put(str, byCodes);
                        }
                    }
                    if (sQLiteDatabase != null) {
                        JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                    }
                    return hashMap;
                } catch (Exception unused) {
                    if (sQLiteDatabase != null) {
                        JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                    }
                    return null;
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabase2 = sQLiteDatabase;
                    if (sQLiteDatabase2 != null) {
                        JDSkinDbHelper.getInstance().closeSQLiteDatabase();
                    }
                    throw th;
                }
            } catch (Exception unused2) {
                sQLiteDatabase = null;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }
}
