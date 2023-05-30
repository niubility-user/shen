package com.jingdong.common.unification.customtheme.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.unification.customtheme.entity.ImageInfoEntity;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class CustomDBController {
    /* JADX WARN: Removed duplicated region for block: B:20:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void deleteDatas() {
        /*
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = com.jingdong.common.unification.customtheme.db.UnCustomThemeDbHelper.getDatabase()     // Catch: java.lang.Throwable -> L18 java.lang.Exception -> L1d
            java.lang.String r2 = "1=1"
            java.lang.String r3 = "theme"
            r1.delete(r3, r2, r0)     // Catch: java.lang.Throwable -> L14 java.lang.Exception -> L16
            java.lang.String r3 = "navi_theme"
            r1.delete(r3, r2, r0)     // Catch: java.lang.Throwable -> L14 java.lang.Exception -> L16
            if (r1 == 0) goto L29
            goto L26
        L14:
            r0 = move-exception
            goto L2a
        L16:
            r0 = move-exception
            goto L21
        L18:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L2a
        L1d:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L21:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L14
            if (r1 == 0) goto L29
        L26:
            r1.close()
        L29:
            return
        L2a:
            if (r1 == 0) goto L2f
            r1.close()
        L2f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.customtheme.db.CustomDBController.deleteDatas():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void deleteNavi() {
        /*
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = com.jingdong.common.unification.customtheme.db.UnCustomThemeDbHelper.getDatabase()     // Catch: java.lang.Throwable -> L13 java.lang.Exception -> L18
            java.lang.String r2 = "1=1"
            java.lang.String r3 = "navi_theme"
            r1.delete(r3, r2, r0)     // Catch: java.lang.Throwable -> Lf java.lang.Exception -> L11
            if (r1 == 0) goto L24
            goto L21
        Lf:
            r0 = move-exception
            goto L25
        L11:
            r0 = move-exception
            goto L1c
        L13:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
            goto L25
        L18:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L1c:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> Lf
            if (r1 == 0) goto L24
        L21:
            r1.close()
        L24:
            return
        L25:
            if (r1 == 0) goto L2a
            r1.close()
        L2a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.customtheme.db.CustomDBController.deleteNavi():void");
    }

    private static boolean insertData(SQLiteDatabase sQLiteDatabase, ImageInfoEntity imageInfoEntity) {
        if (imageInfoEntity == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(CustomThemeConstance.IMAGE_ID, imageInfoEntity.imageId);
        contentValues.put(CustomThemeConstance.IMAGE_URL, imageInfoEntity.url);
        contentValues.put(CustomThemeConstance.LOCAL_PATH, imageInfoEntity.localPath);
        contentValues.put(CustomThemeConstance.COLOR_TYPE, imageInfoEntity.colorType);
        contentValues.put(CustomThemeConstance.IS_EFFECTED, Integer.valueOf(imageInfoEntity.isEffected ? 1 : 0));
        contentValues.put(CustomThemeConstance.DISPLAY_TYPE, Integer.valueOf(imageInfoEntity.displayType));
        contentValues.put(CustomThemeConstance.SORT, imageInfoEntity.sort);
        contentValues.put(CustomThemeConstance.LABLE_NAME, imageInfoEntity.lableName);
        contentValues.put(CustomThemeConstance.MD5, imageInfoEntity.md5);
        return sQLiteDatabase.insert(CustomThemeConstance.TABLE_NAME, null, contentValues) > 0;
    }

    public static boolean insertDatas(HashMap<String, ImageInfoEntity> hashMap) {
        if (hashMap == null) {
            return false;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = UnCustomThemeDbHelper.getDatabase();
            sQLiteDatabase.beginTransaction();
            Iterator<ImageInfoEntity> it = hashMap.values().iterator();
            while (it.hasNext()) {
                insertData(sQLiteDatabase, it.next());
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception unused) {
                }
            }
            return true;
        } catch (Exception unused2) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception unused3) {
                }
            }
            return false;
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception unused4) {
                }
            }
            throw th;
        }
    }

    private static boolean insertNavi(SQLiteDatabase sQLiteDatabase, NavigationInfo navigationInfo) {
        if (navigationInfo == null || sQLiteDatabase == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(CustomThemeConstance.NAVI_DISPLAY_TYPE, Integer.valueOf(navigationInfo.displayType));
        contentValues.put("functionId", navigationInfo.functionId);
        contentValues.put(CustomThemeConstance.NAVI_NAVIGATION_ID, Integer.valueOf(navigationInfo.navigationId));
        contentValues.put(CustomThemeConstance.NAVI_LABEL_IMAGE, navigationInfo.labelImage);
        contentValues.put(CustomThemeConstance.NAVI_LABEL_IMAGE_PATH, navigationInfo.labelImagePath);
        contentValues.put(CustomThemeConstance.NAVI_LABEL_COLOR, navigationInfo.labelColor);
        contentValues.put(CustomThemeConstance.NAVI_LABEL_NAME, navigationInfo.labelName);
        contentValues.put(CustomThemeConstance.NAVI_CUT_LABEL_NAME, navigationInfo.cutLabelName);
        contentValues.put(CustomThemeConstance.NAVI_OPT_LABEL_IMAGE, navigationInfo.optLabelImage);
        contentValues.put(CustomThemeConstance.NAVI_OPT_LABEL_IMAGE_PATH, navigationInfo.optLabelImagePath);
        contentValues.put(CustomThemeConstance.NAVI_OPT_LABEL_COLOR, navigationInfo.optLabelColor);
        contentValues.put(CustomThemeConstance.NAVI_USE_TYPE, Integer.valueOf(navigationInfo.useType));
        contentValues.put("url", navigationInfo.url);
        contentValues.put(CustomThemeConstance.NAVI_MODEL, navigationInfo.model);
        contentValues.put(CustomThemeConstance.NAVI_LOTTIE_URL, navigationInfo.lottieUrl);
        contentValues.put(CustomThemeConstance.NAVI_LOTTIE_PATH, navigationInfo.lottiePath);
        contentValues.put(CustomThemeConstance.NAVI_CLICK_EVENT_ID, navigationInfo.clickEventId);
        contentValues.put(CustomThemeConstance.NAVI_TAB_NAME_SELECTED, navigationInfo.tabNameSelected);
        contentValues.put(CustomThemeConstance.NAVI_LOTTIE_MD5, navigationInfo.lottieMd5);
        contentValues.put(CustomThemeConstance.NAVI_IMAGE_DARK_TAG, Integer.valueOf(navigationInfo.isDark ? 1 : 0));
        contentValues.put(CustomThemeConstance.NAVI_LABEL_IMAGE_MD5, navigationInfo.labelImageMd5);
        contentValues.put(CustomThemeConstance.NAVI_OPT_IMAGE_MD5, navigationInfo.optLabelImageMd5);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL_MD5, navigationInfo.middleFirstLottieUrlMd5);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_MD5, navigationInfo.middleFirstImgMd5);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL_MD5, navigationInfo.middleSecondLottieUrlMd5);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_MD5, navigationInfo.middleSecondImgMd5);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL, navigationInfo.middleFirstLottieUrl);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_URL, navigationInfo.middleFirstImg);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL, navigationInfo.middleSecondLottieUrl);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_URL, navigationInfo.middleSecondImg);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_PATH, navigationInfo.middleFirstLottiePath);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_PATH, navigationInfo.middleFirstImgPath);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_PATH, navigationInfo.middleSecondLottiePath);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_PATH, navigationInfo.middleSecondImgPath);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_PATH, navigationInfo.middleThirdLottiePath);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL, navigationInfo.middleThirdLottieUrl);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL_MD5, navigationInfo.middleThirdLottieUrlMd5);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL, navigationInfo.effectStartLottieUrl);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_PATH, navigationInfo.effectStartLottiePath);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL_MD5, navigationInfo.effectStartLottieUrlMd5);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL, navigationInfo.effectEndLottieUrl);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_PATH, navigationInfo.effectEndLottiePath);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL_MD5, navigationInfo.effectEndLottieUrlMd5);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL, navigationInfo.marketingLottieUrl);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_PATH, navigationInfo.marketingLottiePath);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL_MD5, navigationInfo.marketingLottieUrlMd5);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_MARKET_PLAY_TIMES, Integer.valueOf(navigationInfo.marketingPlayTimes));
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_MARKET_AUTO, Integer.valueOf(navigationInfo.marketingAuto));
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_MARKET_ID, navigationInfo.marketingId);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_BIG_ICON_IS_OPEN, navigationInfo.bigIconIsOpen);
        contentValues.put(CustomThemeConstance.NAVI_MIDDLE_BIG_LOTTIE_FLAG, Integer.valueOf(navigationInfo.bigLottieFlag));
        contentValues.put(CustomThemeConstance.NAVI_ANGLE_SWITCH, Integer.valueOf(navigationInfo.angleSwitch));
        return sQLiteDatabase.insert(CustomThemeConstance.NAVI_TABLE_NAME, null, contentValues) > 0;
    }

    public static boolean insertNavis(List<NavigationInfo> list) {
        if (list == null) {
            return false;
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = UnCustomThemeDbHelper.getDatabase();
            sQLiteDatabase.beginTransaction();
            Iterator<NavigationInfo> it = list.iterator();
            while (it.hasNext()) {
                if (!insertNavi(sQLiteDatabase, it.next())) {
                    if (sQLiteDatabase != null) {
                        try {
                            sQLiteDatabase.endTransaction();
                        } catch (Exception unused) {
                        }
                    }
                    return false;
                }
            }
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception unused2) {
                }
            }
            return true;
        } catch (Exception unused3) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception unused4) {
                }
            }
            return false;
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception unused5) {
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0111, code lost:
        if (r0 != null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0145, code lost:
        if (r0 != null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0147, code lost:
        com.jingdong.common.unification.customtheme.db.UnCustomThemeDbHelper.getDatabase().close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x014e, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.common.unification.customtheme.entity.ImageInfoEntity queryDataByImageId(java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.customtheme.db.CustomDBController.queryDataByImageId(java.lang.String):com.jingdong.common.unification.customtheme.entity.ImageInfoEntity");
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00fe, code lost:
        if (r10 != null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x012d, code lost:
        if (r10 != null) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x012f, code lost:
        com.jingdong.common.unification.customtheme.db.UnCustomThemeDbHelper.getDatabase().close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0136, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.HashMap<java.lang.String, com.jingdong.common.unification.customtheme.entity.ImageInfoEntity> queryDatas() {
        /*
            Method dump skipped, instructions count: 311
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.customtheme.db.CustomDBController.queryDatas():java.util.HashMap");
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x02ea, code lost:
        if (r0 != null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x031b, code lost:
        if (r0 != null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x031d, code lost:
        com.jingdong.common.unification.customtheme.db.UnCustomThemeDbHelper.getDatabase().close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0324, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.common.unification.customtheme.entity.NavigationInfo queryNavigationByFunctionId(java.lang.String r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 805
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.customtheme.db.CustomDBController.queryNavigationByFunctionId(java.lang.String, boolean):com.jingdong.common.unification.customtheme.entity.NavigationInfo");
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0307, code lost:
        if (r0 != null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0338, code lost:
        if (r0 != null) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x033a, code lost:
        com.jingdong.common.unification.customtheme.db.UnCustomThemeDbHelper.getDatabase().close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0341, code lost:
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.jingdong.common.unification.customtheme.entity.NavigationInfo queryNavigationByNavigationId(java.lang.String r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 834
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.customtheme.db.CustomDBController.queryNavigationByNavigationId(java.lang.String, boolean):com.jingdong.common.unification.customtheme.entity.NavigationInfo");
    }
}
