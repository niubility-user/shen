package com.jingdong.common.unification.uniconfig;

import android.text.TextUtils;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;
import com.jingdong.common.UnLog;
import java.io.IOException;
import java.util.List;

/* loaded from: classes6.dex */
public class DefaultDataController {
    private static final String SP_CACHE_DATA_VERSION = "un_sp_icon_cache_data_version";
    private static final String TAG = "DefaultDataController";
    public static final long cache_version = 1659420461051L;
    private static DefaultDataController controller;
    private boolean isFirstInit;
    private Object syncObject = new Object();

    private DefaultDataController() {
    }

    private Long getCacheDataVersion() {
        return Long.valueOf(UnSharedPreferencesUtils.getLong(a.g().d(), SP_CACHE_DATA_VERSION, 0L));
    }

    public static synchronized DefaultDataController getController() {
        DefaultDataController defaultDataController;
        synchronized (DefaultDataController.class) {
            DefaultDataController defaultDataController2 = controller;
            if (defaultDataController2 != null) {
                return defaultDataController2;
            }
            synchronized (DefaultDataController.class) {
                if (controller == null) {
                    controller = new DefaultDataController();
                }
                defaultDataController = controller;
            }
            return defaultDataController;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0099 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.jingdong.common.unification.uniconfig.IconConfigJsonModel parseCacheJson() {
        /*
            r7 = this;
            java.lang.String r0 = "DefaultDataController"
            java.lang.String r1 = "unicon.json"
            r2 = 0
            com.jd.lib.un.business.widget.a r3 = com.jd.lib.un.business.widget.a.g()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7e
            android.content.Context r3 = r3.d()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7e
            android.content.res.AssetManager r3 = r3.getAssets()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7e
            r4.<init>()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7e
            java.lang.String r5 = "unicon/"
            r4.append(r5)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7e
            r4.append(r1)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7e
            java.lang.String r1 = r4.toString()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7e
            java.io.InputStream r1 = r3.open(r1)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L96
            r3.<init>()     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L96
            if (r1 == 0) goto L71
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L96
            r4.<init>(r1)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L96
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L96
            r5.<init>(r4)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L96
        L37:
            java.lang.String r4 = r5.readLine()     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L96
            if (r4 == 0) goto L41
            r3.append(r4)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L96
            goto L37
        L41:
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L96
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L96
            if (r4 == 0) goto L5a
            if (r1 == 0) goto L59
            r1.close()     // Catch: java.io.IOException -> L51
            goto L59
        L51:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            com.jingdong.common.UnLog.e(r0, r1)
        L59:
            return r2
        L5a:
            java.lang.Class<com.jingdong.common.unification.uniconfig.IconConfigJsonModel> r4 = com.jingdong.common.unification.uniconfig.IconConfigJsonModel.class
            java.lang.Object r3 = com.jd.framework.json.JDJSON.parseObject(r3, r4)     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L96
            com.jingdong.common.unification.uniconfig.IconConfigJsonModel r3 = (com.jingdong.common.unification.uniconfig.IconConfigJsonModel) r3     // Catch: java.lang.Exception -> L77 java.lang.Throwable -> L96
            if (r1 == 0) goto L70
            r1.close()     // Catch: java.io.IOException -> L68
            goto L70
        L68:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            com.jingdong.common.UnLog.e(r0, r1)
        L70:
            return r3
        L71:
            if (r1 == 0) goto L95
            r1.close()     // Catch: java.io.IOException -> L8d
            goto L95
        L77:
            r3 = move-exception
            goto L80
        L79:
            r1 = move-exception
            r6 = r2
            r2 = r1
            r1 = r6
            goto L97
        L7e:
            r3 = move-exception
            r1 = r2
        L80:
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L96
            com.jingdong.common.UnLog.e(r0, r3)     // Catch: java.lang.Throwable -> L96
            if (r1 == 0) goto L95
            r1.close()     // Catch: java.io.IOException -> L8d
            goto L95
        L8d:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            com.jingdong.common.UnLog.e(r0, r1)
        L95:
            return r2
        L96:
            r2 = move-exception
        L97:
            if (r1 == 0) goto La5
            r1.close()     // Catch: java.io.IOException -> L9d
            goto La5
        L9d:
            r1 = move-exception
            java.lang.String r1 = r1.getMessage()
            com.jingdong.common.UnLog.e(r0, r1)
        La5:
            goto La7
        La6:
            throw r2
        La7:
            goto La6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniconfig.DefaultDataController.parseCacheJson():com.jingdong.common.unification.uniconfig.IconConfigJsonModel");
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Bitmap getAssetsBitmap(java.lang.String r6, android.graphics.Rect r7, android.graphics.BitmapFactory.Options r8) {
        /*
            r5 = this;
            java.lang.String r0 = "DefaultDataController"
            r1 = 0
            com.jd.lib.un.business.widget.a r2 = com.jd.lib.un.business.widget.a.g()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43
            android.content.Context r2 = r2.d()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43
            android.content.res.AssetManager r2 = r2.getAssets()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43
            r3.<init>()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43
            java.lang.String r4 = "unicon/"
            r3.append(r4)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43
            r3.append(r6)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43
            java.lang.String r6 = r3.toString()     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43
            java.io.InputStream r6 = r2.open(r6)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43
            if (r7 != 0) goto L2c
            android.graphics.Rect r7 = new android.graphics.Rect     // Catch: java.io.IOException -> L3f java.lang.Throwable -> L5b
            r2 = -1
            r7.<init>(r2, r2, r2, r2)     // Catch: java.io.IOException -> L3f java.lang.Throwable -> L5b
        L2c:
            android.graphics.Bitmap r7 = android.graphics.BitmapFactory.decodeStream(r6, r7, r8)     // Catch: java.io.IOException -> L3f java.lang.Throwable -> L5b
            if (r6 == 0) goto L3e
            r6.close()     // Catch: java.io.IOException -> L36
            goto L3e
        L36:
            r6 = move-exception
            java.lang.String r6 = r6.getMessage()
            com.jingdong.common.UnLog.e(r0, r6)
        L3e:
            return r7
        L3f:
            r7 = move-exception
            goto L45
        L41:
            r7 = move-exception
            goto L5d
        L43:
            r7 = move-exception
            r6 = r1
        L45:
            java.lang.String r7 = r7.getMessage()     // Catch: java.lang.Throwable -> L5b
            com.jingdong.common.UnLog.e(r0, r7)     // Catch: java.lang.Throwable -> L5b
            if (r6 == 0) goto L5a
            r6.close()     // Catch: java.io.IOException -> L52
            goto L5a
        L52:
            r6 = move-exception
            java.lang.String r6 = r6.getMessage()
            com.jingdong.common.UnLog.e(r0, r6)
        L5a:
            return r1
        L5b:
            r7 = move-exception
            r1 = r6
        L5d:
            if (r1 == 0) goto L6b
            r1.close()     // Catch: java.io.IOException -> L63
            goto L6b
        L63:
            r6 = move-exception
            java.lang.String r6 = r6.getMessage()
            com.jingdong.common.UnLog.e(r0, r6)
        L6b:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.uniconfig.DefaultDataController.getAssetsBitmap(java.lang.String, android.graphics.Rect, android.graphics.BitmapFactory$Options):android.graphics.Bitmap");
    }

    public String getIconPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = str + ".png";
        return isAssetsFileExists(str2) ? UnIconConfigConstants.DEFAULT_DIR + str2 : "";
    }

    public void initCache() {
        IconConfigWidgetJsonModel iconConfigWidgetJsonModel;
        List<IconConfigModel> list;
        if (getCacheDataVersion().longValue() == cache_version) {
            return;
        }
        DataBaseController.getController().deleteAllData(UnNewIconTable.CACHE_TABLE_NAME);
        IconConfigJsonModel parseCacheJson = parseCacheJson();
        if (parseCacheJson == null || (iconConfigWidgetJsonModel = parseCacheJson.data) == null || (list = iconConfigWidgetJsonModel.widget) == null || list.size() <= 0) {
            return;
        }
        if (DataBaseController.getController().insertForArr(list, true)) {
            UnSharedPreferencesUtils.putLong(a.g().d(), SP_CACHE_DATA_VERSION, parseCacheJson.dataVersion);
        }
        if (UnIconConfigHelper.getUniConfigDataVersion() == 0 && DataBaseController.getController().insertForArr(list, false)) {
            this.isFirstInit = true;
            UnSharedPreferencesUtils.putLong(a.g().d(), UnIconConfigConstants.SHARED_UNI_CONFIG_DATA_VERSION, parseCacheJson.dataVersion);
        }
    }

    public boolean isAssetsFileExists(String str) {
        try {
            for (String str2 : a.g().d().getAssets().list(UnIconConfigConstants.ICON_DIR)) {
                if (str.equals(str2)) {
                    return true;
                }
            }
            return false;
        } catch (IOException e2) {
            UnLog.e(TAG, e2.getMessage());
            return false;
        }
    }

    public boolean isFirstInit() {
        return this.isFirstInit;
    }

    public void setFirstInit(boolean z) {
        this.isFirstInit = z;
    }
}
