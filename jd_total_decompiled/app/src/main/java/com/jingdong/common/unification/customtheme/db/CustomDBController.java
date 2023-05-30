package com.jingdong.common.unification.customtheme.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.jd.lib.un.business.widget.a;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.common.unification.customtheme.entity.ImageInfoEntity;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.sdk.oklog.OKLog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class CustomDBController {
    /* JADX WARN: Removed duplicated region for block: B:20:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void deleteDatas() {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Exception e2;
        try {
            try {
                sQLiteDatabase = UnCustomThemeDbHelper.getDatabase();
            } catch (Exception e3) {
                sQLiteDatabase = null;
                e2 = e3;
            } catch (Throwable th2) {
                sQLiteDatabase = null;
                th = th2;
                if (sQLiteDatabase != null) {
                }
                throw th;
            }
            try {
                sQLiteDatabase.delete(CustomThemeConstance.TABLE_NAME, "1=1", null);
                sQLiteDatabase.delete(CustomThemeConstance.NAVI_TABLE_NAME, "1=1", null);
                if (sQLiteDatabase == null) {
                    return;
                }
            } catch (Exception e4) {
                e2 = e4;
                e2.printStackTrace();
                if (sQLiteDatabase == null) {
                    return;
                }
                sQLiteDatabase.close();
            }
            sQLiteDatabase.close();
        } catch (Throwable th3) {
            th = th3;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void deleteNavi() {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Exception e2;
        try {
            sQLiteDatabase = UnCustomThemeDbHelper.getDatabase();
        } catch (Exception e3) {
            sQLiteDatabase = null;
            e2 = e3;
        } catch (Throwable th2) {
            sQLiteDatabase = null;
            th = th2;
            if (sQLiteDatabase != null) {
            }
            throw th;
        }
        try {
            try {
                sQLiteDatabase.delete(CustomThemeConstance.NAVI_TABLE_NAME, "1=1", null);
                if (sQLiteDatabase == null) {
                    return;
                }
            } catch (Throwable th3) {
                th = th3;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                throw th;
            }
        } catch (Exception e4) {
            e2 = e4;
            e2.printStackTrace();
            if (sQLiteDatabase == null) {
                return;
            }
            sQLiteDatabase.close();
        }
        sQLiteDatabase.close();
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
    */
    public static ImageInfoEntity queryDataByImageId(String str) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Cursor cursor;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            sQLiteDatabase = UnCustomThemeDbHelper.getDatabase();
            try {
                boolean z = false;
                cursor = sQLiteDatabase.query(CustomThemeConstance.TABLE_NAME, new String[]{CustomThemeConstance.IMAGE_ID, CustomThemeConstance.IMAGE_URL, CustomThemeConstance.LOCAL_PATH, CustomThemeConstance.COLOR_TYPE, CustomThemeConstance.IS_EFFECTED, CustomThemeConstance.DISPLAY_TYPE, CustomThemeConstance.SORT, CustomThemeConstance.LABLE_NAME, CustomThemeConstance.MD5}, CustomThemeConstance.IMAGE_ID + "=?", new String[]{str}, null, null, null);
                if (cursor == null) {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        UnCustomThemeDbHelper.getDatabase().close();
                    }
                    return null;
                }
                try {
                    if (cursor.moveToFirst()) {
                        ImageInfoEntity imageInfoEntity = new ImageInfoEntity();
                        imageInfoEntity.imageId = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.IMAGE_ID));
                        imageInfoEntity.url = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.IMAGE_URL));
                        imageInfoEntity.localPath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.LOCAL_PATH));
                        imageInfoEntity.colorType = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.COLOR_TYPE));
                        if (cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.IS_EFFECTED)) != 0) {
                            z = true;
                        }
                        imageInfoEntity.isEffected = z;
                        imageInfoEntity.displayType = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.DISPLAY_TYPE));
                        imageInfoEntity.sort = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.SORT));
                        imageInfoEntity.lableName = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.LABLE_NAME));
                        imageInfoEntity.md5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.MD5));
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            UnCustomThemeDbHelper.getDatabase().close();
                        }
                        return imageInfoEntity;
                    } else if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Exception unused) {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        UnCustomThemeDbHelper.getDatabase().close();
                    }
                    throw th;
                }
            } catch (Exception unused2) {
                cursor = null;
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
            }
        } catch (Exception unused3) {
            cursor = null;
            sQLiteDatabase = null;
        } catch (Throwable th4) {
            sQLiteDatabase = null;
            th = th4;
            cursor = null;
        }
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
    */
    public static HashMap<String, ImageInfoEntity> queryDatas() {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor = null;
        if (a.g().q()) {
            return null;
        }
        HashMap<String, ImageInfoEntity> hashMap = new HashMap<>();
        try {
            sQLiteDatabase = UnCustomThemeDbHelper.getDatabase();
            try {
                Cursor query = sQLiteDatabase.query(CustomThemeConstance.TABLE_NAME, new String[]{CustomThemeConstance.IMAGE_ID, CustomThemeConstance.IMAGE_URL, CustomThemeConstance.LOCAL_PATH, CustomThemeConstance.COLOR_TYPE, CustomThemeConstance.IS_EFFECTED, CustomThemeConstance.DISPLAY_TYPE, CustomThemeConstance.SORT, CustomThemeConstance.LABLE_NAME, CustomThemeConstance.MD5}, null, null, null, null, null);
                if (query == null) {
                    if (query != null && !query.isClosed()) {
                        query.close();
                    }
                    if (sQLiteDatabase != null) {
                        UnCustomThemeDbHelper.getDatabase().close();
                    }
                    return null;
                }
                try {
                    query.moveToFirst();
                    while (!query.isAfterLast()) {
                        ImageInfoEntity imageInfoEntity = new ImageInfoEntity();
                        imageInfoEntity.imageId = query.getString(query.getColumnIndex(CustomThemeConstance.IMAGE_ID));
                        imageInfoEntity.url = query.getString(query.getColumnIndex(CustomThemeConstance.IMAGE_URL));
                        imageInfoEntity.localPath = query.getString(query.getColumnIndex(CustomThemeConstance.LOCAL_PATH));
                        imageInfoEntity.colorType = query.getString(query.getColumnIndex(CustomThemeConstance.COLOR_TYPE));
                        imageInfoEntity.isEffected = query.getInt(query.getColumnIndex(CustomThemeConstance.IS_EFFECTED)) != 0;
                        imageInfoEntity.displayType = query.getInt(query.getColumnIndex(CustomThemeConstance.DISPLAY_TYPE));
                        imageInfoEntity.sort = query.getString(query.getColumnIndex(CustomThemeConstance.SORT));
                        imageInfoEntity.lableName = query.getString(query.getColumnIndex(CustomThemeConstance.LABLE_NAME));
                        imageInfoEntity.md5 = query.getString(query.getColumnIndex(CustomThemeConstance.MD5));
                        hashMap.put(imageInfoEntity.imageId, imageInfoEntity);
                        query.moveToNext();
                    }
                    if (query != null && !query.isClosed()) {
                        query.close();
                    }
                } catch (Exception unused) {
                    cursor = query;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        UnCustomThemeDbHelper.getDatabase().close();
                    }
                    throw th;
                }
            } catch (Exception unused2) {
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused3) {
            sQLiteDatabase = null;
        } catch (Throwable th3) {
            th = th3;
            sQLiteDatabase = null;
        }
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
    */
    public static NavigationInfo queryNavigationByFunctionId(String str, boolean z) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Cursor cursor2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            sQLiteDatabase = UnCustomThemeDbHelper.getDatabase();
            try {
                String[] strArr = new String[2];
                strArr[0] = str;
                strArr[1] = z ? "1" : "0";
                cursor = sQLiteDatabase.query(CustomThemeConstance.NAVI_TABLE_NAME, null, "functionId=? and dark =?", strArr, null, null, null);
                if (cursor == null) {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        UnCustomThemeDbHelper.getDatabase().close();
                    }
                    return null;
                }
                try {
                    if (cursor.moveToFirst()) {
                        NavigationInfo navigationInfo = new NavigationInfo();
                        navigationInfo.id = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.ID));
                        navigationInfo.displayType = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_DISPLAY_TYPE));
                        navigationInfo.functionId = cursor.getString(cursor.getColumnIndex("functionId"));
                        navigationInfo.navigationId = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_NAVIGATION_ID));
                        navigationInfo.labelImage = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LABEL_IMAGE));
                        navigationInfo.labelImagePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LABEL_IMAGE_PATH));
                        navigationInfo.labelColor = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LABEL_COLOR));
                        navigationInfo.labelName = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LABEL_NAME));
                        navigationInfo.cutLabelName = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_CUT_LABEL_NAME));
                        navigationInfo.labelImage = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_OPT_LABEL_IMAGE));
                        navigationInfo.optLabelImage = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_OPT_LABEL_IMAGE_PATH));
                        navigationInfo.optLabelColor = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_OPT_LABEL_COLOR));
                        navigationInfo.useType = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_USE_TYPE));
                        navigationInfo.url = cursor.getString(cursor.getColumnIndex("url"));
                        navigationInfo.model = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MODEL));
                        navigationInfo.lottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LOTTIE_URL));
                        navigationInfo.lottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LOTTIE_PATH));
                        navigationInfo.clickEventId = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_CLICK_EVENT_ID));
                        navigationInfo.tabNameSelected = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_TAB_NAME_SELECTED));
                        navigationInfo.lottieMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LOTTIE_MD5));
                        navigationInfo.isDark = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_IMAGE_DARK_TAG)) == 1;
                        navigationInfo.labelImageMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LABEL_IMAGE_MD5));
                        navigationInfo.optLabelImageMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_OPT_IMAGE_MD5));
                        navigationInfo.middleFirstLottieUrlMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL_MD5));
                        navigationInfo.middleFirstImgMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_MD5));
                        navigationInfo.middleSecondLottieUrlMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL_MD5));
                        navigationInfo.middleSecondImgMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_MD5));
                        navigationInfo.middleFirstLottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL));
                        navigationInfo.middleFirstImg = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_URL));
                        navigationInfo.middleSecondLottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL));
                        navigationInfo.middleSecondImg = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_URL));
                        navigationInfo.middleFirstLottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_PATH));
                        navigationInfo.middleFirstImgPath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_PATH));
                        navigationInfo.middleSecondLottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_PATH));
                        navigationInfo.middleSecondImgPath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_PATH));
                        navigationInfo.middleThirdLottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_PATH));
                        navigationInfo.middleThirdLottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL));
                        navigationInfo.middleThirdLottieUrlMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL_MD5));
                        navigationInfo.effectStartLottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_PATH));
                        navigationInfo.effectStartLottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL));
                        navigationInfo.effectStartLottieUrlMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL_MD5));
                        navigationInfo.effectEndLottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_PATH));
                        navigationInfo.effectEndLottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL));
                        navigationInfo.effectEndLottieUrlMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL_MD5));
                        navigationInfo.marketingLottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL));
                        navigationInfo.marketingLottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_PATH));
                        navigationInfo.marketingLottieUrlMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL_MD5));
                        navigationInfo.marketingAuto = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_MARKET_AUTO));
                        navigationInfo.marketingPlayTimes = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_MARKET_PLAY_TIMES));
                        navigationInfo.marketingId = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_MARKET_ID));
                        navigationInfo.bigIconIsOpen = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_BIG_ICON_IS_OPEN));
                        navigationInfo.bigLottieFlag = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_BIG_LOTTIE_FLAG));
                        navigationInfo.angleSwitch = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_ANGLE_SWITCH));
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            UnCustomThemeDbHelper.getDatabase().close();
                        }
                        return navigationInfo;
                    } else if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Exception unused) {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    cursor2 = cursor;
                    th = th;
                    if (cursor2 != null && !cursor2.isClosed()) {
                        cursor2.close();
                    }
                    if (sQLiteDatabase != null) {
                        UnCustomThemeDbHelper.getDatabase().close();
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
    */
    public static NavigationInfo queryNavigationByNavigationId(String str, boolean z) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor;
        Cursor cursor2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            sQLiteDatabase = UnCustomThemeDbHelper.getDatabase();
            try {
                String[] strArr = new String[2];
                strArr[0] = str;
                strArr[1] = z ? "1" : "0";
                cursor = sQLiteDatabase.query(CustomThemeConstance.NAVI_TABLE_NAME, null, "navigationId=? and dark =?", strArr, null, null, null);
                if (cursor == null) {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        UnCustomThemeDbHelper.getDatabase().close();
                    }
                    return null;
                }
                try {
                    if (cursor.moveToFirst()) {
                        NavigationInfo navigationInfo = new NavigationInfo();
                        navigationInfo.id = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.ID));
                        navigationInfo.displayType = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_DISPLAY_TYPE));
                        navigationInfo.functionId = cursor.getString(cursor.getColumnIndex("functionId"));
                        navigationInfo.navigationId = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_NAVIGATION_ID));
                        navigationInfo.labelImage = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LABEL_IMAGE));
                        navigationInfo.labelImagePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LABEL_IMAGE_PATH));
                        navigationInfo.labelColor = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LABEL_COLOR));
                        navigationInfo.labelName = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LABEL_NAME));
                        navigationInfo.cutLabelName = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_CUT_LABEL_NAME));
                        navigationInfo.labelImage = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_OPT_LABEL_IMAGE));
                        navigationInfo.optLabelImage = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_OPT_LABEL_IMAGE_PATH));
                        navigationInfo.optLabelColor = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_OPT_LABEL_COLOR));
                        navigationInfo.useType = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_USE_TYPE));
                        navigationInfo.url = cursor.getString(cursor.getColumnIndex("url"));
                        navigationInfo.model = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MODEL));
                        navigationInfo.lottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LOTTIE_URL));
                        navigationInfo.lottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LOTTIE_PATH));
                        navigationInfo.clickEventId = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_CLICK_EVENT_ID));
                        navigationInfo.tabNameSelected = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_TAB_NAME_SELECTED));
                        navigationInfo.lottieMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LOTTIE_MD5));
                        navigationInfo.isDark = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_IMAGE_DARK_TAG)) == 1;
                        navigationInfo.labelImageMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_LABEL_IMAGE_MD5));
                        navigationInfo.optLabelImageMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_OPT_IMAGE_MD5));
                        navigationInfo.middleFirstLottieUrlMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL_MD5));
                        navigationInfo.middleFirstImgMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_MD5));
                        navigationInfo.middleSecondLottieUrlMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL_MD5));
                        navigationInfo.middleSecondImgMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_MD5));
                        navigationInfo.middleFirstLottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL));
                        navigationInfo.middleFirstImg = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_URL));
                        navigationInfo.middleSecondLottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL));
                        navigationInfo.middleSecondImg = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_URL));
                        navigationInfo.middleFirstLottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_PATH));
                        navigationInfo.middleFirstImgPath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_PATH));
                        navigationInfo.middleSecondLottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_PATH));
                        navigationInfo.middleSecondImgPath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_PATH));
                        navigationInfo.middleThirdLottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_PATH));
                        navigationInfo.middleThirdLottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL));
                        navigationInfo.middleThirdLottieUrlMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL_MD5));
                        navigationInfo.effectStartLottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_PATH));
                        navigationInfo.effectStartLottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL));
                        navigationInfo.effectStartLottieUrlMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL_MD5));
                        navigationInfo.effectEndLottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_PATH));
                        navigationInfo.effectEndLottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL));
                        navigationInfo.effectEndLottieUrlMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL_MD5));
                        navigationInfo.marketingLottieUrl = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL));
                        navigationInfo.marketingLottiePath = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_PATH));
                        navigationInfo.marketingLottieUrlMd5 = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL_MD5));
                        navigationInfo.marketingAuto = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_MARKET_AUTO));
                        navigationInfo.marketingPlayTimes = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_MARKET_PLAY_TIMES));
                        navigationInfo.marketingId = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_MARKET_ID));
                        navigationInfo.bigIconIsOpen = cursor.getString(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_BIG_ICON_IS_OPEN));
                        navigationInfo.bigLottieFlag = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_MIDDLE_BIG_LOTTIE_FLAG));
                        navigationInfo.angleSwitch = cursor.getInt(cursor.getColumnIndex(CustomThemeConstance.NAVI_ANGLE_SWITCH));
                        OKLog.e("tianchuangxin1", navigationInfo.functionId + "--get->" + navigationInfo.angleSwitch);
                        if (cursor != null && !cursor.isClosed()) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            UnCustomThemeDbHelper.getDatabase().close();
                        }
                        return navigationInfo;
                    } else if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Exception unused) {
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    cursor2 = cursor;
                    th = th;
                    if (cursor2 != null && !cursor2.isClosed()) {
                        cursor2.close();
                    }
                    if (sQLiteDatabase != null) {
                        UnCustomThemeDbHelper.getDatabase().close();
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
