package com.jingdong.common.unification.navigationbar.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.unification.navigationbar.NavigationFrequencyRuleEntity;
import com.jingdong.common.unification.navigationbar.NavigationMaterialShowEntity;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.sdk.oklog.OKLog;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int deleteMaterialShowDataByTime(long r5) {
        /*
            r0 = 0
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase()     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            r3.<init>()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            java.lang.String r4 = "material_show_time<"
            r3.append(r4)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            r3.append(r5)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            java.lang.String r4 = "navigation_material_show"
            int r1 = r2.delete(r4, r3, r0)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            boolean r0 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            if (r0 == 0) goto L3f
            java.lang.String r0 = com.jingdong.common.unification.navigationbar.db.NavigationDBController.TAG     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            r3.<init>()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            java.lang.String r4 = "deleteMaterialShowDataByTime-time="
            r3.append(r4)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            r3.append(r5)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            java.lang.String r5 = " count="
            r3.append(r5)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            r3.append(r1)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            java.lang.String r5 = r3.toString()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
            com.jingdong.sdk.oklog.OKLog.d(r0, r5)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L4c
        L3f:
            if (r2 == 0) goto L5e
        L41:
            android.database.sqlite.SQLiteDatabase r5 = com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase()
            r5.close()
            goto L5e
        L49:
            r5 = move-exception
            r0 = r2
            goto L5f
        L4c:
            r5 = move-exception
            r0 = r2
            goto L52
        L4f:
            r5 = move-exception
            goto L5f
        L51:
            r5 = move-exception
        L52:
            boolean r6 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> L4f
            if (r6 == 0) goto L5b
            java.lang.String r6 = com.jingdong.common.unification.navigationbar.db.NavigationDBController.TAG     // Catch: java.lang.Throwable -> L4f
            com.jingdong.sdk.oklog.OKLog.e(r6, r5)     // Catch: java.lang.Throwable -> L4f
        L5b:
            if (r0 == 0) goto L5e
            goto L41
        L5e:
            return r1
        L5f:
            if (r0 == 0) goto L68
            android.database.sqlite.SQLiteDatabase r6 = com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase()
            r6.close()
        L68:
            goto L6a
        L69:
            throw r5
        L6a:
            goto L69
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.navigationbar.db.NavigationDBController.deleteMaterialShowDataByTime(long):int");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.jingdong.common.unification.navigationbar.NavigationMaterialShowEntity> queryMaterialShowDataById(java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.navigationbar.db.NavigationDBController.queryMaterialShowDataById(java.lang.String):java.util.List");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.jingdong.common.unification.navigationbar.NavigationMaterialShowEntity> queryMaterialShowDataByIdAndEffectiveTime(java.lang.String r10, long r11, long r13) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.navigationbar.db.NavigationDBController.queryMaterialShowDataByIdAndEffectiveTime(java.lang.String, long, long):java.util.List");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.jingdong.common.unification.navigationbar.NavigationMaterialShowEntity> queryMaterialShowDataByIdAndTime(java.lang.String r10, long r11) {
        /*
            Method dump skipped, instructions count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.navigationbar.db.NavigationDBController.queryMaterialShowDataByIdAndTime(java.lang.String, long):java.util.List");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int queryMaterialShowDataByTypeAndPosition(int r10, int r11, java.lang.String r12, long r13) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.navigationbar.db.NavigationDBController.queryMaterialShowDataByTypeAndPosition(int, int, java.lang.String, long):int");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.jingdong.common.unification.navigationbar.NavigationFrequencyRuleEntity> queryUsableFrequencyRuleData() {
        /*
            Method dump skipped, instructions count: 359
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.navigationbar.db.NavigationDBController.queryUsableFrequencyRuleData():java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean updateAndDeleteFrequencyRuleDataByState() {
        /*
            r0 = 0
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            r2.beginTransaction()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.String r3 = "SELECT * FROM navigation_frequency_rule WHERE frequency_rule_state = 0"
            android.database.Cursor r1 = r2.rawQuery(r3, r1)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            if (r1 != 0) goto L13
            r3 = 0
            goto L17
        L13:
            int r3 = r1.getCount()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
        L17:
            boolean r4 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            if (r4 == 0) goto L31
            java.lang.String r4 = com.jingdong.common.unification.navigationbar.db.NavigationDBController.TAG     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            r5.<init>()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.String r6 = "updateAndDeleteFrequencyRuleDataByState-unavailableCount="
            r5.append(r6)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            r5.append(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            com.jingdong.sdk.oklog.OKLog.d(r4, r5)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
        L31:
            r4 = 1
            if (r3 <= 0) goto L3c
            deleteFrequencyRuleDataByState(r2, r4)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            java.lang.String r3 = "UPDATE navigation_frequency_rule SET frequency_rule_state=1 WHERE frequency_rule_state=0"
            r2.execSQL(r3)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
        L3c:
            r2.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L59
            if (r1 == 0) goto L4a
            boolean r0 = r1.isClosed()
            if (r0 != 0) goto L4a
            r1.close()
        L4a:
            if (r2 == 0) goto L56
            r2.endTransaction()     // Catch: java.lang.Exception -> L56
            android.database.sqlite.SQLiteDatabase r0 = com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase()     // Catch: java.lang.Exception -> L56
            r0.close()     // Catch: java.lang.Exception -> L56
        L56:
            return r4
        L57:
            r0 = move-exception
            goto L88
        L59:
            r3 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
            goto L63
        L5e:
            r0 = move-exception
            r2 = r1
            goto L88
        L61:
            r3 = move-exception
            r2 = r1
        L63:
            boolean r4 = com.jingdong.sdk.oklog.OKLog.D     // Catch: java.lang.Throwable -> L84
            if (r4 == 0) goto L6c
            java.lang.String r4 = com.jingdong.common.unification.navigationbar.db.NavigationDBController.TAG     // Catch: java.lang.Throwable -> L84
            com.jingdong.sdk.oklog.OKLog.e(r4, r3)     // Catch: java.lang.Throwable -> L84
        L6c:
            if (r2 == 0) goto L77
            boolean r3 = r2.isClosed()
            if (r3 != 0) goto L77
            r2.close()
        L77:
            if (r1 == 0) goto L83
            r1.endTransaction()     // Catch: java.lang.Exception -> L83
            android.database.sqlite.SQLiteDatabase r1 = com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase()     // Catch: java.lang.Exception -> L83
            r1.close()     // Catch: java.lang.Exception -> L83
        L83:
            return r0
        L84:
            r0 = move-exception
            r7 = r2
            r2 = r1
            r1 = r7
        L88:
            if (r1 == 0) goto L93
            boolean r3 = r1.isClosed()
            if (r3 != 0) goto L93
            r1.close()
        L93:
            if (r2 == 0) goto L9f
            r2.endTransaction()     // Catch: java.lang.Exception -> L9f
            android.database.sqlite.SQLiteDatabase r1 = com.jingdong.common.unification.navigationbar.db.NavigationDbHelper.getDatabase()     // Catch: java.lang.Exception -> L9f
            r1.close()     // Catch: java.lang.Exception -> L9f
        L9f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.navigationbar.db.NavigationDBController.updateAndDeleteFrequencyRuleDataByState():boolean");
    }
}
