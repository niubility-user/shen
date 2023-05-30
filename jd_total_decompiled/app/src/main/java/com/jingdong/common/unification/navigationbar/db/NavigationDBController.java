package com.jingdong.common.unification.navigationbar.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jingdong.common.unification.navigationbar.NavigationFrequencyRuleEntity;
import com.jingdong.common.unification.navigationbar.NavigationMaterialShowEntity;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class NavigationDBController {
    public static final String TAG = "NavigationDBController";

    public static void deleteAllFrequencyRuleData() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                sQLiteDatabase = NavigationDbHelper.getDatabase();
                sQLiteDatabase.execSQL("DELETE FROM navigation_frequency_rule");
                if (sQLiteDatabase == null) {
                    return;
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    OKLog.e(TAG, e2);
                }
                if (sQLiteDatabase == null) {
                    return;
                }
            }
            NavigationDbHelper.getDatabase().close();
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                NavigationDbHelper.getDatabase().close();
            }
            throw th;
        }
    }

    private static int deleteFrequencyRuleDataByState(SQLiteDatabase sQLiteDatabase, int i2) {
        int i3 = 0;
        if (sQLiteDatabase != null) {
            i3 = sQLiteDatabase.delete(NavigationDbConstants.NAVIGATION_FREQUENCY_RULE_TABLE_NAME, "frequency_rule_state =?", new String[]{i2 + ""});
            if (OKLog.D) {
                OKLog.d(TAG, "deleteFrequencyRuleDataByState-state=" + i2 + " count=" + i3);
            }
        }
        return i3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x005b, code lost:
        if (r0 != null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005e, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x003f, code lost:
        if (r2 != null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0041, code lost:
        com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase().close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int deleteMaterialShowDataByTime(long j2) {
        SQLiteDatabase sQLiteDatabase = null;
        int i2 = 0;
        try {
            try {
                SQLiteDatabase database = NavigationDbHelper.getDatabase();
                try {
                    i2 = database.delete(NavigationDbConstants.NAVIGATION_MATERIAL_SHOW_TABLE_NAME, "material_show_time<" + j2, null);
                    if (OKLog.D) {
                        OKLog.d(TAG, "deleteMaterialShowDataByTime-time=" + j2 + " count=" + i2);
                    }
                } catch (Exception e2) {
                    e = e2;
                    sQLiteDatabase = database;
                    if (OKLog.D) {
                        OKLog.e(TAG, e);
                    }
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabase = database;
                    if (sQLiteDatabase != null) {
                        NavigationDbHelper.getDatabase().close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static boolean insertOrUpdateFrequencyRuleData(List<NavigationFrequencyRuleEntity> list) {
        if (list != null && list.size() > 0) {
            if (OKLog.D) {
                OKLog.d(TAG, "insertOrUpdateFrequencyRuleData=" + list.size() + LangUtils.SINGLE_SPACE + list);
            }
            SQLiteDatabase sQLiteDatabase = null;
            try {
                try {
                    SQLiteDatabase database = NavigationDbHelper.getDatabase();
                    try {
                        database.beginTransaction();
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            ContentValues contentValues = new ContentValues();
                            NavigationFrequencyRuleEntity navigationFrequencyRuleEntity = list.get(i2);
                            contentValues.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_ID, navigationFrequencyRuleEntity.id);
                            contentValues.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_STATE, (Integer) 0);
                            contentValues.put("startTime", Long.valueOf(navigationFrequencyRuleEntity.startTime));
                            contentValues.put("endTime", Long.valueOf(navigationFrequencyRuleEntity.endTime));
                            contentValues.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION, navigationFrequencyRuleEntity.dataVersion);
                            contentValues.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION, navigationFrequencyRuleEntity.position);
                            contentValues.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_STATISTICSCYCLE, Integer.valueOf(navigationFrequencyRuleEntity.statisticsCycle));
                            contentValues.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_SUMNUM, Integer.valueOf(navigationFrequencyRuleEntity.sumNum));
                            contentValues.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_BUBBLENUM, Integer.valueOf(navigationFrequencyRuleEntity.bubbleNum));
                            contentValues.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_ANGLENUM, Integer.valueOf(navigationFrequencyRuleEntity.angleNum));
                            contentValues.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_ICONABNORMITYNUM, Integer.valueOf(navigationFrequencyRuleEntity.iconAbnormityNum));
                            contentValues.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_BUBBLEABNORMITYNUM, Integer.valueOf(navigationFrequencyRuleEntity.bubbleAbnormityNum));
                            contentValues.put(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_ANGLEABNORMITYNUM, Integer.valueOf(navigationFrequencyRuleEntity.angleAbnormityNum));
                            database.insert(NavigationDbConstants.NAVIGATION_FREQUENCY_RULE_TABLE_NAME, null, contentValues);
                        }
                        database.setTransactionSuccessful();
                        if (database != null) {
                            try {
                                database.endTransaction();
                                NavigationDbHelper.getDatabase().close();
                                return true;
                            } catch (Exception unused) {
                                return true;
                            }
                        }
                        return true;
                    } catch (Exception e2) {
                        e = e2;
                        sQLiteDatabase = database;
                        if (OKLog.E) {
                            OKLog.e(TAG, e);
                        }
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.endTransaction();
                                NavigationDbHelper.getDatabase().close();
                            } catch (Exception unused2) {
                            }
                        }
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        sQLiteDatabase = database;
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.endTransaction();
                                NavigationDbHelper.getDatabase().close();
                            } catch (Exception unused3) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } else {
            deleteAllFrequencyRuleData();
            return false;
        }
    }

    public static boolean insertOrUpdateMaterialShowData(NavigationMaterialShowEntity navigationMaterialShowEntity) {
        if (OKLog.D) {
            OKLog.d(TAG, "insertOrUpdateMaterialShowData=" + navigationMaterialShowEntity);
        }
        if (navigationMaterialShowEntity == null) {
            return false;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            try {
                SQLiteDatabase database = NavigationDbHelper.getDatabase();
                try {
                    database.beginTransaction();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(NavigationDbConstants.TB_COLUMN_MATERIAL_ID, navigationMaterialShowEntity.material_id);
                    contentValues.put(NavigationDbConstants.TB_COLUMN_MATERIAL_TYPE, Integer.valueOf(navigationMaterialShowEntity.material_type));
                    contentValues.put(NavigationDbConstants.TB_COLUMN_MATERIAL_COMBINATION_TYPE, Integer.valueOf(navigationMaterialShowEntity.combination_type));
                    contentValues.put(NavigationDbConstants.TB_COLUMN_MATERIAL_POSITION, navigationMaterialShowEntity.material_position);
                    contentValues.put(NavigationDbConstants.TB_COLUMN_MATERIAL_SHOW_TIME, Long.valueOf(navigationMaterialShowEntity.material_show_time));
                    database.insert(NavigationDbConstants.NAVIGATION_MATERIAL_SHOW_TABLE_NAME, null, contentValues);
                    database.setTransactionSuccessful();
                    if (database != null) {
                        try {
                            database.endTransaction();
                            NavigationDbHelper.getDatabase().close();
                        } catch (Exception unused) {
                        }
                    }
                    return true;
                } catch (Exception e2) {
                    e = e2;
                    sQLiteDatabase = database;
                    if (OKLog.E) {
                        OKLog.e(TAG, e);
                    }
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                            NavigationDbHelper.getDatabase().close();
                        } catch (Exception unused2) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    sQLiteDatabase = database;
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                            NavigationDbHelper.getDatabase().close();
                        } catch (Exception unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a0, code lost:
        if (r2 != 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00c4, code lost:
        if (r1 != null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c6, code lost:
        com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase().close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00cf, code lost:
        if (com.jingdong.sdk.oklog.OKLog.D == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d1, code lost:
        r1 = com.jingdong.common.unification.navigationbar.db.NavigationDBController.TAG;
        com.jingdong.sdk.oklog.OKLog.d(r1, "queryMaterialShowDataById-material_id=" + r9 + " list=" + r0);
        r9 = new java.lang.StringBuilder();
        r9.append("queryMaterialShowDataById-list.size=");
        r9.append(r0.size());
        com.jingdong.sdk.oklog.OKLog.d(r1, r9.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0107, code lost:
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0119  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v8, types: [android.database.sqlite.SQLiteDatabase] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<NavigationMaterialShowEntity> queryMaterialShowDataById(String str) {
        ?? r2;
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            r2 = NavigationDbHelper.getDatabase();
            try {
                Cursor rawQuery = r2.rawQuery("SELECT * FROM navigation_material_show WHERE material_id = '" + str + "'", null);
                if (rawQuery == null) {
                    if (rawQuery != null && !rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                    if (r2 != 0) {
                        NavigationDbHelper.getDatabase().close();
                    }
                    return arrayList;
                }
                rawQuery.moveToFirst();
                if (rawQuery.getCount() > 0) {
                    int count = rawQuery.getCount();
                    for (int i2 = 0; i2 < count; i2++) {
                        rawQuery.moveToPosition(i2);
                        NavigationMaterialShowEntity navigationMaterialShowEntity = new NavigationMaterialShowEntity();
                        navigationMaterialShowEntity.material_id = rawQuery.getString(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_ID));
                        navigationMaterialShowEntity.material_type = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_TYPE));
                        navigationMaterialShowEntity.combination_type = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_COMBINATION_TYPE));
                        navigationMaterialShowEntity.material_position = rawQuery.getString(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_POSITION));
                        navigationMaterialShowEntity.material_show_time = rawQuery.getLong(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_SHOW_TIME));
                        arrayList.add(navigationMaterialShowEntity);
                    }
                }
                if (rawQuery != null && !rawQuery.isClosed()) {
                    rawQuery.close();
                }
            } catch (Exception e2) {
                e = e2;
                cursor = null;
                cursor2 = r2;
                try {
                    if (OKLog.D) {
                        OKLog.e(TAG, e);
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    Cursor cursor3 = cursor;
                    r2 = cursor2;
                    cursor2 = cursor3;
                    if (cursor2 != null && !cursor2.isClosed()) {
                        cursor2.close();
                    }
                    if (r2 != 0) {
                        NavigationDbHelper.getDatabase().close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (r2 != 0) {
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            r2 = 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00be, code lost:
        if (r5 != 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e1, code lost:
        if (r4 != null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00e3, code lost:
        com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase().close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ec, code lost:
        if (com.jingdong.sdk.oklog.OKLog.D == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00ee, code lost:
        r0 = com.jingdong.common.unification.navigationbar.db.NavigationDBController.TAG;
        com.jingdong.sdk.oklog.OKLog.d(r0, "queryMaterialShowDataByIdAndEffectiveTime-material_id=" + r10 + " startTime=" + r11 + " endTime=" + r13 + " list=" + r3);
        r10 = new java.lang.StringBuilder();
        r10.append("queryMaterialShowDataByIdAndEffectiveTime-list.size=");
        r10.append(r3.size());
        com.jingdong.sdk.oklog.OKLog.d(r0, r10.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0134, code lost:
        return r3;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0145  */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.database.sqlite.SQLiteDatabase] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<NavigationMaterialShowEntity> queryMaterialShowDataByIdAndEffectiveTime(String str, long j2, long j3) {
        ?? r5;
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            r5 = NavigationDbHelper.getDatabase();
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            r5 = 0;
        }
        try {
            Cursor rawQuery = r5.rawQuery("SELECT * FROM navigation_material_show WHERE material_show_time <= " + j3 + " AND " + NavigationDbConstants.TB_COLUMN_MATERIAL_SHOW_TIME + " >=" + j2 + " AND " + NavigationDbConstants.TB_COLUMN_MATERIAL_ID + " = '" + str + "'", null);
            if (rawQuery == null) {
                if (rawQuery != null && !rawQuery.isClosed()) {
                    rawQuery.close();
                }
                if (r5 != 0) {
                    NavigationDbHelper.getDatabase().close();
                }
                return arrayList;
            }
            rawQuery.moveToFirst();
            if (rawQuery.getCount() > 0) {
                int count = rawQuery.getCount();
                for (int i2 = 0; i2 < count; i2++) {
                    rawQuery.moveToPosition(i2);
                    NavigationMaterialShowEntity navigationMaterialShowEntity = new NavigationMaterialShowEntity();
                    navigationMaterialShowEntity.material_id = rawQuery.getString(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_ID));
                    navigationMaterialShowEntity.material_type = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_TYPE));
                    navigationMaterialShowEntity.combination_type = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_COMBINATION_TYPE));
                    navigationMaterialShowEntity.material_position = rawQuery.getString(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_POSITION));
                    navigationMaterialShowEntity.material_show_time = rawQuery.getLong(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_SHOW_TIME));
                    arrayList.add(navigationMaterialShowEntity);
                }
            }
            if (rawQuery != null && !rawQuery.isClosed()) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
            cursor2 = r5;
            try {
                if (OKLog.D) {
                    OKLog.e(TAG, e);
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            } catch (Throwable th2) {
                th = th2;
                r5 = cursor2;
                cursor2 = cursor;
                if (cursor2 != null && !cursor2.isClosed()) {
                    cursor2.close();
                }
                if (r5 != 0) {
                    NavigationDbHelper.getDatabase().close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null) {
                cursor2.close();
            }
            if (r5 != 0) {
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00b0, code lost:
        if (r3 != 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00d4, code lost:
        if (r2 != null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00d6, code lost:
        com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase().close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00df, code lost:
        if (com.jingdong.sdk.oklog.OKLog.D == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00e1, code lost:
        r0 = com.jingdong.common.unification.navigationbar.db.NavigationDBController.TAG;
        com.jingdong.sdk.oklog.OKLog.d(r0, "queryMaterialShowDataByIdAndTime-material_id=" + r10 + " time=" + r11 + " list=" + r1);
        r10 = new java.lang.StringBuilder();
        r10.append("queryMaterialShowDataByIdAndTime-list.size=");
        r10.append(r1.size());
        com.jingdong.sdk.oklog.OKLog.d(r0, r10.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x011f, code lost:
        return r1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0131  */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v6, types: [android.database.sqlite.SQLiteDatabase] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<NavigationMaterialShowEntity> queryMaterialShowDataByIdAndTime(String str, long j2) {
        ?? r3;
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            r3 = NavigationDbHelper.getDatabase();
            try {
                Cursor rawQuery = r3.rawQuery("SELECT * FROM navigation_material_show WHERE material_show_time >= " + j2 + " AND " + NavigationDbConstants.TB_COLUMN_MATERIAL_ID + " = '" + str + "'", null);
                if (rawQuery == null) {
                    if (rawQuery != null && !rawQuery.isClosed()) {
                        rawQuery.close();
                    }
                    if (r3 != 0) {
                        NavigationDbHelper.getDatabase().close();
                    }
                    return arrayList;
                }
                rawQuery.moveToFirst();
                if (rawQuery.getCount() > 0) {
                    int count = rawQuery.getCount();
                    for (int i2 = 0; i2 < count; i2++) {
                        rawQuery.moveToPosition(i2);
                        NavigationMaterialShowEntity navigationMaterialShowEntity = new NavigationMaterialShowEntity();
                        navigationMaterialShowEntity.material_id = rawQuery.getString(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_ID));
                        navigationMaterialShowEntity.material_type = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_TYPE));
                        navigationMaterialShowEntity.combination_type = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_COMBINATION_TYPE));
                        navigationMaterialShowEntity.material_position = rawQuery.getString(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_POSITION));
                        navigationMaterialShowEntity.material_show_time = rawQuery.getLong(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_MATERIAL_SHOW_TIME));
                        arrayList.add(navigationMaterialShowEntity);
                    }
                }
                if (rawQuery != null && !rawQuery.isClosed()) {
                    rawQuery.close();
                }
            } catch (Exception e2) {
                e = e2;
                cursor = null;
                cursor2 = r3;
                try {
                    if (OKLog.D) {
                        OKLog.e(TAG, e);
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    Cursor cursor3 = cursor;
                    r3 = cursor2;
                    cursor2 = cursor3;
                    if (cursor2 != null && !cursor2.isClosed()) {
                        cursor2.close();
                    }
                    if (r3 != 0) {
                        NavigationDbHelper.getDatabase().close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (r3 != 0) {
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            r3 = 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x009b, code lost:
        if (r2 != 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x009d, code lost:
        com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase().close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00c5, code lost:
        if (r1 != null) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ca, code lost:
        if (com.jingdong.sdk.oklog.OKLog.D == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00cc, code lost:
        com.jingdong.sdk.oklog.OKLog.d(com.jingdong.common.unification.navigationbar.db.NavigationDBController.TAG, "queryMaterialShowDataByTypeAndPosition-type=" + r10 + " combinationTypes=" + r11 + " position=" + r12 + " time=" + r13 + " count=" + r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0102, code lost:
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0114  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v6, types: [android.database.sqlite.SQLiteDatabase] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int queryMaterialShowDataByTypeAndPosition(int i2, int i3, String str, long j2) {
        ?? r2;
        Cursor cursor;
        String str2;
        int i4 = 0;
        Cursor cursor2 = null;
        try {
            r2 = NavigationDbHelper.getDatabase();
            try {
                if (TextUtils.isEmpty(str)) {
                    str2 = "";
                } else if (str.equals(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION_ALL)) {
                    str2 = "SELECT * FROM navigation_material_show WHERE material_show_time >= " + j2 + " AND " + NavigationDbConstants.TB_COLUMN_MATERIAL_TYPE + " = " + i2 + " AND " + NavigationDbConstants.TB_COLUMN_MATERIAL_COMBINATION_TYPE + " = " + i3;
                } else {
                    str2 = "SELECT * FROM navigation_material_show WHERE material_show_time >= " + j2 + " AND " + NavigationDbConstants.TB_COLUMN_MATERIAL_TYPE + " = " + i2 + " AND " + NavigationDbConstants.TB_COLUMN_MATERIAL_COMBINATION_TYPE + " = " + i3 + " AND " + NavigationDbConstants.TB_COLUMN_MATERIAL_POSITION + " = '" + str + "'";
                }
                cursor2 = r2.rawQuery(str2, null);
                if (cursor2 != null) {
                    i4 = cursor2.getCount();
                }
                if (cursor2 != null && !cursor2.isClosed()) {
                    cursor2.close();
                }
            } catch (Exception e2) {
                e = e2;
                cursor = cursor2;
                cursor2 = r2;
                try {
                    if (OKLog.D) {
                        OKLog.e(TAG, e);
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    Cursor cursor3 = cursor;
                    r2 = cursor2;
                    cursor2 = cursor3;
                    if (cursor2 != null && !cursor2.isClosed()) {
                        cursor2.close();
                    }
                    if (r2 != 0) {
                        NavigationDbHelper.getDatabase().close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (r2 != 0) {
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            r2 = 0;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x00ed, code lost:
        if (r2 != 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0110, code lost:
        if (r1 != null) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0112, code lost:
        com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase().close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x011b, code lost:
        if (com.jingdong.sdk.oklog.OKLog.D == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x011d, code lost:
        r1 = com.jingdong.common.unification.navigationbar.db.NavigationDBController.TAG;
        com.jingdong.sdk.oklog.OKLog.d(r1, "queryUsableFrequencyRuleData-list=" + r0);
        com.jingdong.sdk.oklog.OKLog.d(r1, "queryUsableFrequencyRuleData-list.size=" + r0.size());
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x014b, code lost:
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x015d  */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v9, types: [android.database.sqlite.SQLiteDatabase] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static List<NavigationFrequencyRuleEntity> queryUsableFrequencyRuleData() {
        ?? r2;
        Cursor cursor;
        ArrayList arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            r2 = NavigationDbHelper.getDatabase();
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            r2 = 0;
        }
        try {
            Cursor rawQuery = r2.rawQuery("SELECT * FROM navigation_frequency_rule WHERE frequency_rule_state = 1", null);
            if (rawQuery == null) {
                if (rawQuery != null && !rawQuery.isClosed()) {
                    rawQuery.close();
                }
                if (r2 != 0) {
                    NavigationDbHelper.getDatabase().close();
                }
                return arrayList;
            }
            rawQuery.moveToFirst();
            if (rawQuery.getCount() > 0) {
                int count = rawQuery.getCount();
                for (int i2 = 0; i2 < count; i2++) {
                    rawQuery.moveToPosition(i2);
                    NavigationFrequencyRuleEntity navigationFrequencyRuleEntity = new NavigationFrequencyRuleEntity();
                    navigationFrequencyRuleEntity.id = rawQuery.getString(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_ID));
                    navigationFrequencyRuleEntity.state = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_STATE));
                    navigationFrequencyRuleEntity.startTime = rawQuery.getLong(rawQuery.getColumnIndex("startTime"));
                    navigationFrequencyRuleEntity.endTime = rawQuery.getLong(rawQuery.getColumnIndex("endTime"));
                    navigationFrequencyRuleEntity.dataVersion = rawQuery.getString(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION));
                    navigationFrequencyRuleEntity.position = rawQuery.getString(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_POSITION));
                    navigationFrequencyRuleEntity.statisticsCycle = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_STATISTICSCYCLE));
                    navigationFrequencyRuleEntity.sumNum = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_SUMNUM));
                    navigationFrequencyRuleEntity.bubbleNum = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_BUBBLENUM));
                    navigationFrequencyRuleEntity.angleNum = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_ANGLENUM));
                    navigationFrequencyRuleEntity.iconAbnormityNum = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_ICONABNORMITYNUM));
                    navigationFrequencyRuleEntity.bubbleAbnormityNum = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_BUBBLEABNORMITYNUM));
                    navigationFrequencyRuleEntity.angleAbnormityNum = rawQuery.getInt(rawQuery.getColumnIndex(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_ANGLEABNORMITYNUM));
                    arrayList.add(navigationFrequencyRuleEntity);
                }
            }
            if (rawQuery != null && !rawQuery.isClosed()) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
            cursor2 = r2;
            try {
                if (OKLog.D) {
                    OKLog.e(TAG, e);
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            } catch (Throwable th2) {
                th = th2;
                Cursor cursor3 = cursor;
                r2 = cursor2;
                cursor2 = cursor3;
                if (cursor2 != null && !cursor2.isClosed()) {
                    cursor2.close();
                }
                if (r2 != 0) {
                    NavigationDbHelper.getDatabase().close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null) {
                cursor2.close();
            }
            if (r2 != 0) {
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean updateAndDeleteFrequencyRuleDataByState() {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Cursor cursor2 = null;
        cursor2 = null;
        cursor2 = null;
        SQLiteDatabase sQLiteDatabase2 = null;
        try {
            sQLiteDatabase = NavigationDbHelper.getDatabase();
        } catch (Exception e2) {
            e = e2;
            cursor = null;
        } catch (Throwable th) {
            th = th;
            sQLiteDatabase = null;
        }
        try {
            sQLiteDatabase.beginTransaction();
            cursor2 = sQLiteDatabase.rawQuery("SELECT * FROM navigation_frequency_rule WHERE frequency_rule_state = 0", null);
            int count = cursor2 == null ? 0 : cursor2.getCount();
            if (OKLog.D) {
                OKLog.d(TAG, "updateAndDeleteFrequencyRuleDataByState-unavailableCount=" + count);
            }
            if (count > 0) {
                deleteFrequencyRuleDataByState(sQLiteDatabase, 1);
                sQLiteDatabase.execSQL("UPDATE navigation_frequency_rule SET frequency_rule_state=1 WHERE frequency_rule_state=0");
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (cursor2 != null && !cursor2.isClosed()) {
                cursor2.close();
            }
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                    NavigationDbHelper.getDatabase().close();
                } catch (Exception unused) {
                }
            }
            return true;
        } catch (Exception e3) {
            e = e3;
            cursor = cursor2;
            sQLiteDatabase2 = sQLiteDatabase;
            try {
                if (OKLog.D) {
                    OKLog.e(TAG, e);
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                if (sQLiteDatabase2 != null) {
                    try {
                        sQLiteDatabase2.endTransaction();
                        NavigationDbHelper.getDatabase().close();
                    } catch (Exception unused2) {
                    }
                }
                return false;
            } catch (Throwable th2) {
                th = th2;
                Cursor cursor3 = cursor;
                sQLiteDatabase = sQLiteDatabase2;
                cursor2 = cursor3;
                if (cursor2 != null && !cursor2.isClosed()) {
                    cursor2.close();
                }
                if (sQLiteDatabase != null) {
                    try {
                        sQLiteDatabase.endTransaction();
                        NavigationDbHelper.getDatabase().close();
                    } catch (Exception unused3) {
                    }
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null) {
                cursor2.close();
            }
            if (sQLiteDatabase != null) {
            }
            throw th;
        }
    }
}
