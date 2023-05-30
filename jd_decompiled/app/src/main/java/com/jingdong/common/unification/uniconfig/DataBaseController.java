package com.jingdong.common.unification.uniconfig;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jd.lib.un.business.widget.c;
import com.jingdong.common.UnLog;
import java.io.File;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isExist(java.lang.String r12, android.database.sqlite.SQLiteDatabase r13) {
        /*
            r11 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r12)
            r1 = 0
            if (r0 != 0) goto L5b
            if (r13 != 0) goto La
            goto L5b
        La:
            r0 = 0
            java.lang.String r3 = "uni_icon_config"
            r10 = 1
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r2 = "id"
            r4[r1] = r2     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r5 = "tag_id=?"
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r6[r1] = r12     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r13
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r0 != 0) goto L2a
            if (r0 == 0) goto L29
            r0.close()
        L29:
            return r1
        L2a:
            boolean r12 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r12 == 0) goto L3c
            int r12 = r0.getInt(r1)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r12 <= 0) goto L3c
            if (r0 == 0) goto L3b
            r0.close()
        L3b:
            return r10
        L3c:
            if (r0 == 0) goto L54
            goto L51
        L3f:
            r12 = move-exception
            goto L55
        L41:
            r12 = move-exception
            boolean r13 = com.jingdong.common.UnLog.E     // Catch: java.lang.Throwable -> L3f
            if (r13 == 0) goto L4f
            java.lang.String r13 = "DataBaseController"
            java.lang.String r12 = r12.toString()     // Catch: java.lang.Throwable -> L3f
            com.jingdong.common.UnLog.e(r13, r12)     // Catch: java.lang.Throwable -> L3f
        L4f:
            if (r0 == 0) goto L54
        L51:
            r0.close()
        L54:
            return r1
        L55:
            if (r0 == 0) goto L5a
            r0.close()
        L5a:
            throw r12
        L5b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniconfig.DataBaseController.isExist(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean checkDownloadFinish() {
        /*
            r6 = this;
            java.lang.String r0 = "DataBaseController"
            java.lang.String r1 = "select count(*) from uni_icon_config where icon_path is null or icon_path=''"
            r2 = 0
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = com.jd.lib.un.business.widget.c.g()     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            java.lang.String[] r5 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            android.database.Cursor r3 = r4.rawQuery(r1, r5)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            if (r3 != 0) goto L25
            if (r3 == 0) goto L24
            r3.close()     // Catch: java.lang.Exception -> L18
            goto L24
        L18:
            r1 = move-exception
            boolean r3 = com.jingdong.common.UnLog.E
            if (r3 == 0) goto L24
            java.lang.String r1 = r1.toString()
            com.jingdong.common.UnLog.e(r0, r1)
        L24:
            return r2
        L25:
            boolean r1 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            if (r1 == 0) goto L45
            int r1 = r3.getInt(r2)     // Catch: java.lang.Throwable -> L58 java.lang.Exception -> L5a
            if (r1 != 0) goto L45
            r1 = 1
            if (r3 == 0) goto L44
            r3.close()     // Catch: java.lang.Exception -> L38
            goto L44
        L38:
            r2 = move-exception
            boolean r3 = com.jingdong.common.UnLog.E
            if (r3 == 0) goto L44
            java.lang.String r2 = r2.toString()
            com.jingdong.common.UnLog.e(r0, r2)
        L44:
            return r1
        L45:
            if (r3 == 0) goto L72
            r3.close()     // Catch: java.lang.Exception -> L4b
            goto L72
        L4b:
            r1 = move-exception
            boolean r3 = com.jingdong.common.UnLog.E
            if (r3 == 0) goto L72
        L50:
            java.lang.String r1 = r1.toString()
            com.jingdong.common.UnLog.e(r0, r1)
            goto L72
        L58:
            r1 = move-exception
            goto L73
        L5a:
            r1 = move-exception
            boolean r4 = com.jingdong.common.UnLog.E     // Catch: java.lang.Throwable -> L58
            if (r4 == 0) goto L66
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L58
            com.jingdong.common.UnLog.e(r0, r1)     // Catch: java.lang.Throwable -> L58
        L66:
            if (r3 == 0) goto L72
            r3.close()     // Catch: java.lang.Exception -> L6c
            goto L72
        L6c:
            r1 = move-exception
            boolean r3 = com.jingdong.common.UnLog.E
            if (r3 == 0) goto L72
            goto L50
        L72:
            return r2
        L73:
            if (r3 == 0) goto L85
            r3.close()     // Catch: java.lang.Exception -> L79
            goto L85
        L79:
            r2 = move-exception
            boolean r3 = com.jingdong.common.UnLog.E
            if (r3 == 0) goto L85
            java.lang.String r2 = r2.toString()
            com.jingdong.common.UnLog.e(r0, r2)
        L85:
            goto L87
        L86:
            throw r1
        L87:
            goto L86
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniconfig.DataBaseController.checkDownloadFinish():boolean");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.jingdong.common.unification.uniconfig.IconConfigModel queryByIconId(java.lang.String r19, boolean r20) {
        /*
            Method dump skipped, instructions count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniconfig.DataBaseController.queryByIconId(java.lang.String, boolean):com.jingdong.common.unification.uniconfig.IconConfigModel");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int queryCount(boolean r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jd.lib.un.business.widget.c.g()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            if (r6 == 0) goto Lb
            java.lang.String r6 = "uni_icon_config_cache"
            goto Ld
        Lb:
            java.lang.String r6 = "uni_icon_config"
        Ld:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            r3.<init>()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            java.lang.String r4 = "select count(*) from "
            r3.append(r4)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            r3.append(r6)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            java.lang.String r6 = r3.toString()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            android.database.Cursor r0 = r2.rawQuery(r6, r0)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            if (r0 != 0) goto L2a
            if (r0 == 0) goto L29
            r0.close()
        L29:
            return r1
        L2a:
            boolean r6 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            if (r6 == 0) goto L3a
            int r6 = r0.getInt(r1)     // Catch: java.lang.Throwable -> L3d java.lang.Exception -> L3f
            if (r0 == 0) goto L39
            r0.close()
        L39:
            return r6
        L3a:
            if (r0 == 0) goto L52
            goto L4f
        L3d:
            r6 = move-exception
            goto L53
        L3f:
            r6 = move-exception
            boolean r2 = com.jingdong.common.UnLog.E     // Catch: java.lang.Throwable -> L3d
            if (r2 == 0) goto L4d
            java.lang.String r2 = "DataBaseController"
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L3d
            com.jingdong.common.UnLog.e(r2, r6)     // Catch: java.lang.Throwable -> L3d
        L4d:
            if (r0 == 0) goto L52
        L4f:
            r0.close()
        L52:
            return r1
        L53:
            if (r0 == 0) goto L58
            r0.close()
        L58:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniconfig.DataBaseController.queryCount(boolean):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.jingdong.common.unification.uniconfig.IconConfigModel> queryListNotDown() {
        /*
            Method dump skipped, instructions count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniconfig.DataBaseController.queryListNotDown():java.util.List");
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
