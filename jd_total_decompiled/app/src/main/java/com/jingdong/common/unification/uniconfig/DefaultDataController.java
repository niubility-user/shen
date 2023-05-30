package com.jingdong.common.unification.uniconfig;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;
import com.jingdong.common.UnLog;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    */
    private IconConfigJsonModel parseCacheJson() {
        Throwable th;
        InputStream inputStream;
        StringBuilder sb;
        ?? r1 = "unicon.json";
        try {
        } catch (IOException e2) {
            UnLog.e(TAG, e2.getMessage());
        }
        try {
            try {
                inputStream = a.g().d().getAssets().open("unicon/unicon.json");
            } catch (Exception e3) {
                e = e3;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                r1 = 0;
                if (r1 != 0) {
                }
                throw th;
            }
            try {
                sb = new StringBuilder();
            } catch (Exception e4) {
                e = e4;
                UnLog.e(TAG, e.getMessage());
                if (inputStream != null) {
                    inputStream.close();
                }
                return null;
            }
            if (inputStream == null) {
                if (inputStream != null) {
                    inputStream.close();
                }
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            String sb2 = sb.toString();
            if (TextUtils.isEmpty(sb2)) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e5) {
                        UnLog.e(TAG, e5.getMessage());
                    }
                }
                return null;
            }
            IconConfigJsonModel iconConfigJsonModel = (IconConfigJsonModel) JDJSON.parseObject(sb2, IconConfigJsonModel.class);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e6) {
                    UnLog.e(TAG, e6.getMessage());
                }
            }
            return iconConfigJsonModel;
        } catch (Throwable th3) {
            th = th3;
            if (r1 != 0) {
                try {
                    r1.close();
                } catch (IOException e7) {
                    UnLog.e(TAG, e7.getMessage());
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x005f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Bitmap getAssetsBitmap(String str, Rect rect, BitmapFactory.Options options) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            inputStream = a.g().d().getAssets().open("unicon/" + str);
            if (rect == null) {
                try {
                    try {
                        rect = new Rect(-1, -1, -1, -1);
                    } catch (Throwable th) {
                        th = th;
                        inputStream2 = inputStream;
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e2) {
                                UnLog.e(TAG, e2.getMessage());
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                    UnLog.e(TAG, e.getMessage());
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            UnLog.e(TAG, e4.getMessage());
                        }
                    }
                    return null;
                }
            }
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, rect, options);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    UnLog.e(TAG, e5.getMessage());
                }
            }
            return decodeStream;
        } catch (IOException e6) {
            e = e6;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            if (inputStream2 != null) {
            }
            throw th;
        }
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
