package com.jingdong.common.unification.uniconfig;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jd.lib.un.business.widget.c;
import com.jingdong.common.UnLog;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class DataBaseController {
    private static final String TAG = "DataBaseController";
    private static DataBaseController controller;
    private Object syncObject = new Object();

    private DataBaseController() {
    }

    public static synchronized DataBaseController getController() {
        DataBaseController dataBaseController;
        synchronized (DataBaseController.class) {
            DataBaseController dataBaseController2 = controller;
            if (dataBaseController2 != null) {
                return dataBaseController2;
            }
            synchronized (DataBaseController.class) {
                if (controller == null) {
                    controller = new DataBaseController();
                }
                dataBaseController = controller;
            }
            return dataBaseController;
        }
    }

    private long insert(SQLiteDatabase sQLiteDatabase, IconConfigModel iconConfigModel, boolean z) {
        if (sQLiteDatabase == null || iconConfigModel == null || TextUtils.isEmpty(iconConfigModel.id) || TextUtils.isEmpty(iconConfigModel.url)) {
            return -1L;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("tag_id", iconConfigModel.id);
        contentValues.put("icon_url", iconConfigModel.url);
        IconExtraConfigModel iconExtraConfigModel = iconConfigModel.config;
        if (iconExtraConfigModel != null) {
            contentValues.put("text_color", iconExtraConfigModel.textColor);
            contentValues.put("text_padding", iconConfigModel.config.edge);
            contentValues.put(UnNewIconTable.FIELD_PADDING_L, iconConfigModel.config.edgeL);
            contentValues.put(UnNewIconTable.FIELD_PADDING_R, iconConfigModel.config.edgeR);
            contentValues.put(UnNewIconTable.FIELD_IS_VAR, Integer.valueOf(iconConfigModel.config.var ? 1 : 0));
            contentValues.put(UnNewIconTable.FIELD_TEXT_FONT_SIZE, Integer.valueOf(iconConfigModel.config.fontSize));
            if (UnLog.D) {
                UnLog.d("iconConfig", "insert config -->" + iconConfigModel.config.toString());
            }
        }
        return sQLiteDatabase.insert(z ? UnNewIconTable.CACHE_TABLE_NAME : "uni_icon_config", null, contentValues);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003c, code lost:
        if (r0 != null) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004f, code lost:
        if (0 != 0) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0051, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0054, code lost:
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean isExist(String str, SQLiteDatabase sQLiteDatabase) {
        if (TextUtils.isEmpty(str) || sQLiteDatabase == null) {
            return false;
        }
        Cursor cursor = null;
        try {
            try {
                cursor = sQLiteDatabase.query("uni_icon_config", new String[]{"id"}, "tag_id=?", new String[]{str}, null, null, null);
                if (cursor == null) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return false;
                } else if (cursor.moveToFirst()) {
                    if (cursor.getInt(0) > 0) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        return true;
                    }
                }
            } catch (Exception e2) {
                if (UnLog.E) {
                    UnLog.e(TAG, e2.toString());
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x004e, code lost:
        if (com.jingdong.common.UnLog.E == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0050, code lost:
        com.jingdong.common.UnLog.e(com.jingdong.common.unification.uniconfig.DataBaseController.TAG, r1.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x006f, code lost:
        if (com.jingdong.common.UnLog.E == false) goto L46;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean checkDownloadFinish() {
        Cursor rawQuery;
        Cursor cursor = null;
        try {
            try {
                rawQuery = c.g().rawQuery("select count(*) from uni_icon_config where icon_path is null or icon_path=''", new String[0]);
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        cursor.close();
                    } catch (Exception e2) {
                        if (UnLog.E) {
                            UnLog.e(TAG, e2.toString());
                        }
                    }
                }
                throw th;
            }
        } catch (Exception e3) {
            if (UnLog.E) {
                UnLog.e(TAG, e3.toString());
            }
            if (0 != 0) {
                try {
                    cursor.close();
                } catch (Exception e4) {
                    e = e4;
                }
            }
        }
        if (rawQuery == null) {
            if (rawQuery != null) {
                try {
                    rawQuery.close();
                } catch (Exception e5) {
                    if (UnLog.E) {
                        UnLog.e(TAG, e5.toString());
                    }
                }
            }
            return false;
        }
        if (rawQuery.moveToFirst()) {
            if (rawQuery.getInt(0) == 0) {
                if (rawQuery != null) {
                    try {
                        rawQuery.close();
                    } catch (Exception e6) {
                        if (UnLog.E) {
                            UnLog.e(TAG, e6.toString());
                        }
                    }
                }
                return true;
            }
        }
        if (rawQuery != null) {
            try {
                rawQuery.close();
            } catch (Exception e7) {
                e = e7;
            }
        }
        return false;
    }

    public void deleteAllData() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = c.g();
                sQLiteDatabase.execSQL("DELETE FROM uni_icon_config");
                if (sQLiteDatabase == null) {
                    return;
                }
            } catch (Exception e2) {
                if (UnLog.E) {
                    UnLog.e(TAG, e2.toString());
                }
                if (sQLiteDatabase == null) {
                    return;
                }
            }
            c.f();
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                c.f();
            }
            throw th;
        }
    }

    public boolean deleteModels(List<IconConfigModel> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = c.g();
                sQLiteDatabase.beginTransaction();
                Iterator<IconConfigModel> it = list.iterator();
                while (it.hasNext()) {
                    IconConfigModel queryByIconId = queryByIconId(it.next().id, false);
                    if (queryByIconId != null) {
                        sQLiteDatabase.delete("uni_icon_config", "tag_id=?", new String[]{queryByIconId.id});
                        File file = new File(queryByIconId.path);
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Exception e2) {
                        if (UnLog.E) {
                            UnLog.e(TAG, e2.toString());
                        }
                    }
                }
                return true;
            } catch (Exception e3) {
                if (UnLog.E) {
                    UnLog.e(TAG, e3.toString());
                }
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Exception e4) {
                        if (UnLog.E) {
                            UnLog.e(TAG, e4.toString());
                        }
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e5) {
                    if (UnLog.E) {
                        UnLog.e(TAG, e5.toString());
                    }
                }
            }
            throw th;
        }
    }

    public IconConfigModel getIconConfigModel(String str) {
        IconConfigModel queryByIconId = queryByIconId(str, false);
        return queryByIconId == null ? queryByIconId(str, true) : queryByIconId;
    }

    public boolean insertForArr(List<IconConfigModel> list, boolean z) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = c.g();
                sQLiteDatabase.beginTransaction();
                for (IconConfigModel iconConfigModel : list) {
                    if (UnLog.D) {
                        UnLog.d("iconConfig", "insert-->" + iconConfigModel.id);
                    }
                    insert(sQLiteDatabase, iconConfigModel, z);
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Exception e2) {
                        if (UnLog.E) {
                            UnLog.e(TAG, e2.toString());
                        }
                    }
                }
                return true;
            } catch (Exception e3) {
                if (UnLog.E) {
                    UnLog.e(TAG, e3.toString());
                }
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Exception e4) {
                        if (UnLog.E) {
                            UnLog.e(TAG, e4.toString());
                        }
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e5) {
                    if (UnLog.E) {
                        UnLog.e(TAG, e5.toString());
                    }
                }
            }
            throw th;
        }
    }

    public boolean insertOrUpdate(List<IconConfigModel> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = c.g();
                sQLiteDatabase.beginTransaction();
                for (IconConfigModel iconConfigModel : list) {
                    if (isExist(iconConfigModel.id, sQLiteDatabase)) {
                        update(iconConfigModel, sQLiteDatabase);
                    } else {
                        insert(sQLiteDatabase, iconConfigModel, false);
                    }
                }
                sQLiteDatabase.setTransactionSuccessful();
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Exception e2) {
                        if (UnLog.E) {
                            UnLog.e(TAG, e2.toString());
                        }
                    }
                }
                return true;
            } catch (Exception e3) {
                if (UnLog.E) {
                    UnLog.e(TAG, e3.toString());
                }
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                    } catch (Exception e4) {
                        if (UnLog.E) {
                            UnLog.e(TAG, e4.toString());
                        }
                    }
                }
                return false;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e5) {
                    if (UnLog.E) {
                        UnLog.e(TAG, e5.toString());
                    }
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00c2, code lost:
        if (r2 != null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00d8, code lost:
        if (r2 != null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00da, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00dd, code lost:
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public IconConfigModel queryByIconId(String str, boolean z) {
        Cursor cursor;
        Cursor cursor2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            cursor = c.g().query(z ? UnNewIconTable.CACHE_TABLE_NAME : "uni_icon_config", new String[]{"tag_id", "icon_url", "icon_path", "text_color", "text_padding", UnNewIconTable.FIELD_IS_VAR, UnNewIconTable.FIELD_PADDING_L, UnNewIconTable.FIELD_PADDING_R, UnNewIconTable.FIELD_TEXT_FONT_SIZE}, "tag_id=?", new String[]{str}, null, null, null);
            if (cursor == null) {
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            }
            try {
                try {
                    if (cursor.moveToFirst()) {
                        IconConfigModel iconConfigModel = new IconConfigModel();
                        iconConfigModel.id = cursor.getString(0);
                        iconConfigModel.url = cursor.getString(1);
                        iconConfigModel.path = cursor.getString(2);
                        IconExtraConfigModel iconExtraConfigModel = new IconExtraConfigModel();
                        iconConfigModel.config = iconExtraConfigModel;
                        iconExtraConfigModel.textColor = cursor.getString(3);
                        iconConfigModel.config.edge = cursor.getString(4);
                        iconConfigModel.config.var = cursor.getInt(5) == 1;
                        iconConfigModel.config.edgeL = cursor.getString(6);
                        iconConfigModel.config.edgeR = cursor.getString(7);
                        iconConfigModel.config.fontSize = cursor.getInt(8);
                        if (cursor != null) {
                            cursor.close();
                        }
                        return iconConfigModel;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor2 = cursor;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                if (UnLog.E) {
                    UnLog.e(TAG, e.toString());
                }
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0122: MOVE (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:39:0x0122 */
    public Map<String, IconConfigModel> queryByIconIdList(String str, boolean z) {
        Cursor cursor;
        Cursor cursor2;
        Cursor cursor3 = null;
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                cursor2 = c.g().query(z ? UnNewIconTable.CACHE_TABLE_NAME : "uni_icon_config", new String[]{"tag_id", "icon_url", "icon_path", "text_color", "text_padding", UnNewIconTable.FIELD_IS_VAR, UnNewIconTable.FIELD_PADDING_L, UnNewIconTable.FIELD_PADDING_R, UnNewIconTable.FIELD_TEXT_FONT_SIZE}, "tag_id in(?,?,?)", new String[]{str, str + UnIconConfigConstants.UN_ICON_ID_B, str + UnIconConfigConstants.UN_ICON_ID_B_S}, null, null, null);
                if (cursor2 == null) {
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    return null;
                }
                try {
                    HashMap hashMap = new HashMap();
                    cursor2.moveToFirst();
                    while (!cursor2.isAfterLast()) {
                        IconConfigModel iconConfigModel = new IconConfigModel();
                        iconConfigModel.id = cursor2.getString(0);
                        iconConfigModel.url = cursor2.getString(1);
                        iconConfigModel.path = cursor2.getString(2);
                        IconExtraConfigModel iconExtraConfigModel = new IconExtraConfigModel();
                        iconConfigModel.config = iconExtraConfigModel;
                        iconExtraConfigModel.textColor = cursor2.getString(3);
                        iconConfigModel.config.edge = cursor2.getString(4);
                        iconConfigModel.config.var = cursor2.getInt(5) == 1;
                        iconConfigModel.config.edgeL = cursor2.getString(6);
                        iconConfigModel.config.edgeR = cursor2.getString(7);
                        iconConfigModel.config.fontSize = cursor2.getInt(8);
                        hashMap.put(iconConfigModel.id, iconConfigModel);
                        cursor2.moveToNext();
                    }
                    UnIconCache.getInstance().putCache(str, hashMap);
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    return hashMap;
                } catch (Exception e2) {
                    e = e2;
                    if (UnLog.E) {
                        UnLog.e(TAG, e.toString());
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    return null;
                }
            } catch (Exception e3) {
                e = e3;
                cursor2 = null;
            } catch (Throwable th) {
                th = th;
                if (cursor3 != null) {
                    cursor3.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            cursor3 = cursor;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x003a, code lost:
        if (r0 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004d, code lost:
        if (0 != 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x004f, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0052, code lost:
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int queryCount(boolean z) {
        Cursor cursor = null;
        try {
            try {
                cursor = c.g().rawQuery("select count(*) from " + (z ? UnNewIconTable.CACHE_TABLE_NAME : "uni_icon_config"), null);
                if (cursor == null) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    return 0;
                } else if (cursor.moveToFirst()) {
                    int i2 = cursor.getInt(0);
                    if (cursor != null) {
                        cursor.close();
                    }
                    return i2;
                }
            } catch (Exception e2) {
                if (UnLog.E) {
                    UnLog.e(TAG, e2.toString());
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public List<IconConfigModel> queryListNotDown() {
        Cursor cursor;
        Cursor cursor2 = null;
        try {
            cursor = c.g().query("uni_icon_config", new String[]{"tag_id", "icon_url", "icon_path", "text_color", "text_padding", UnNewIconTable.FIELD_IS_VAR, UnNewIconTable.FIELD_PADDING_L, UnNewIconTable.FIELD_PADDING_R, UnNewIconTable.FIELD_TEXT_FONT_SIZE}, "icon_path='' or icon_path is null", new String[0], null, null, null);
            if (cursor == null) {
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            }
            try {
                try {
                    ArrayList arrayList = new ArrayList();
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        IconConfigModel iconConfigModel = new IconConfigModel();
                        iconConfigModel.id = cursor.getString(0);
                        iconConfigModel.url = cursor.getString(1);
                        iconConfigModel.path = cursor.getString(2);
                        IconExtraConfigModel iconExtraConfigModel = new IconExtraConfigModel();
                        iconConfigModel.config = iconExtraConfigModel;
                        iconExtraConfigModel.textColor = cursor.getString(3);
                        iconConfigModel.config.edge = cursor.getString(4);
                        iconConfigModel.config.var = cursor.getInt(5) == 1;
                        iconConfigModel.config.edgeL = cursor.getString(6);
                        iconConfigModel.config.edgeR = cursor.getString(7);
                        iconConfigModel.config.fontSize = cursor.getInt(8);
                        arrayList.add(iconConfigModel);
                        cursor.moveToNext();
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    cursor2 = cursor;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                if (UnLog.E) {
                    UnLog.e(TAG, e.toString());
                }
                if (cursor != null) {
                    cursor.close();
                }
                return null;
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th2) {
            th = th2;
            if (cursor2 != null) {
            }
            throw th;
        }
    }

    public boolean update(IconConfigModel iconConfigModel) {
        if (iconConfigModel == null || TextUtils.isEmpty(iconConfigModel.id) || TextUtils.isEmpty(iconConfigModel.url)) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("icon_url", iconConfigModel.url);
        contentValues.put("icon_path", iconConfigModel.path);
        IconExtraConfigModel iconExtraConfigModel = iconConfigModel.config;
        if (iconExtraConfigModel != null) {
            contentValues.put("text_color", iconExtraConfigModel.textColor);
            contentValues.put("text_padding", iconConfigModel.config.edge);
            contentValues.put(UnNewIconTable.FIELD_PADDING_L, iconConfigModel.config.edgeL);
            contentValues.put(UnNewIconTable.FIELD_PADDING_R, iconConfigModel.config.edgeR);
            contentValues.put(UnNewIconTable.FIELD_IS_VAR, Integer.valueOf(iconConfigModel.config.var ? 1 : 0));
            contentValues.put(UnNewIconTable.FIELD_TEXT_FONT_SIZE, Integer.valueOf(iconConfigModel.config.fontSize));
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = c.g();
                if (sQLiteDatabase.update("uni_icon_config", contentValues, "tag_id=?", new String[]{iconConfigModel.id}) <= 0) {
                    if (sQLiteDatabase != null) {
                        c.f();
                    }
                    return false;
                }
                if (sQLiteDatabase != null) {
                    c.f();
                }
                return true;
            } catch (Exception e2) {
                if (UnLog.E) {
                    UnLog.e(TAG, e2.toString());
                }
                if (sQLiteDatabase != null) {
                    c.f();
                }
                return false;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                c.f();
            }
            throw th;
        }
    }

    public IconConfigModel getIconConfigModel(String str, boolean z, boolean z2) {
        Map<String, IconConfigModel> queryByIconIdList = queryByIconIdList(str, false);
        if (queryByIconIdList == null || queryByIconIdList.isEmpty()) {
            queryByIconIdList = queryByIconIdList(str, true);
        }
        if (queryByIconIdList == null || queryByIconIdList.isEmpty()) {
            return null;
        }
        if (queryByIconIdList.size() == 1) {
            return queryByIconIdList.get(str);
        }
        IconConfigModel iconConfigModel = queryByIconIdList.get(UnIconConfigController.getController().getIdWithSwitch(str, z2, z));
        return iconConfigModel == null ? queryByIconIdList.get(str) : iconConfigModel;
    }

    public void deleteAllData(String str) {
        String str2 = "DELETE FROM " + str;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = c.g();
                sQLiteDatabase.execSQL(str2);
                if (sQLiteDatabase == null) {
                    return;
                }
            } catch (Exception e2) {
                if (UnLog.E) {
                    UnLog.e(TAG, e2.toString());
                }
                if (sQLiteDatabase == null) {
                    return;
                }
            }
            c.f();
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                c.f();
            }
            throw th;
        }
    }

    public boolean update(IconConfigModel iconConfigModel, SQLiteDatabase sQLiteDatabase) {
        if (iconConfigModel == null || TextUtils.isEmpty(iconConfigModel.id) || TextUtils.isEmpty(iconConfigModel.url)) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("icon_url", iconConfigModel.url);
        contentValues.put("icon_path", iconConfigModel.path);
        IconExtraConfigModel iconExtraConfigModel = iconConfigModel.config;
        if (iconExtraConfigModel != null) {
            contentValues.put("text_color", iconExtraConfigModel.textColor);
            contentValues.put("text_padding", iconConfigModel.config.edge);
            contentValues.put(UnNewIconTable.FIELD_PADDING_L, iconConfigModel.config.edgeL);
            contentValues.put(UnNewIconTable.FIELD_PADDING_R, iconConfigModel.config.edgeR);
            contentValues.put(UnNewIconTable.FIELD_IS_VAR, Integer.valueOf(iconConfigModel.config.var ? 1 : 0));
            contentValues.put(UnNewIconTable.FIELD_TEXT_FONT_SIZE, Integer.valueOf(iconConfigModel.config.fontSize));
        }
        return sQLiteDatabase.update("uni_icon_config", contentValues, "tag_id=?", new String[]{iconConfigModel.id}) > 0;
    }
}
