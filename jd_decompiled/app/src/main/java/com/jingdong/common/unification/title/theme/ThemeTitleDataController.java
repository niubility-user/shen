package com.jingdong.common.unification.title.theme;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnFileUtils;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;
import com.jingdong.common.UnLog;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.customtheme.UnCustomThemeHelper;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public class ThemeTitleDataController {
    public static final int DELAY_TIME = 600000;
    public static final int FROM_APP_START = 0;
    public static final int FROM_CHANGE_ACTIVITY = 1;
    public static final int FROM_CHANGE_TO_BEFORE = 2;
    public static final String FUNCTION_ID = "goldenSurface";
    public static final int MAX_FAILURE_TIMES = 3;
    public static final String SHARED_LAST_REQEUST_TIME = "theme_title_last_request_time";
    public static final String SHARED_THEME_TITLE_DATA_VERSION = "theme_title_data_version";
    private static final String TAG = "ThemeTitleDataController";
    private static volatile ThemeTitleDataController instance;
    private AtomicInteger failureTime = new AtomicInteger(0);

    private ThemeTitleDataController() {
    }

    public Map<String, List<ThemeTitleSurface>> categorySurfaces(List<ThemeTitleSurface> list) {
        List list2;
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < list.size(); i2++) {
            String str = list.get(i2).imageUrl;
            if (hashMap.get(str) == null) {
                list2 = new ArrayList();
            } else {
                list2 = (List) hashMap.get(str);
            }
            list2.add(list.get(i2));
            hashMap.put(str, list2);
        }
        if (UnLog.D) {
            UnLog.d(TAG, "categorySurfaces map:" + hashMap.toString());
        }
        return hashMap;
    }

    private void checkFileSize(String str, final String str2, final List<ThemeTitleSurface> list, final String str3) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str + "!imginfo");
        httpSetting.setCacheMode(2);
        httpSetting.setNeedExtraStatisticParam(false);
        httpSetting.setPost(false);
        httpSetting.setAttempts(1);
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.unification.title.theme.ThemeTitleDataController.3
            {
                ThemeTitleDataController.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JSONObjectProxy jSONObject = httpResponse.getJSONObject();
                if (jSONObject == null) {
                    ThemeTitleDataController.this.downloadSuccess(list, str3, str2);
                    return;
                }
                long optLong = jSONObject.optLong("fileSize");
                if (optLong <= 0) {
                    ThemeTitleDataController.this.downloadSuccess(list, str3, str2);
                    return;
                }
                File file = new File(str2);
                if (!file.exists()) {
                    ThemeTitleDataController.this.downloadFail(list, str3);
                } else if (optLong == file.length()) {
                    ThemeTitleDataController.this.downloadSuccess(list, str3, str2);
                } else {
                    file.delete();
                    ThemeTitleDataController.this.downloadFail(list, str3);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                ThemeTitleDataController.this.downloadSuccess(list, str3, str2);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public void downImage(String str, final List<ThemeTitleSurface> list, final String str2) {
        if (UnLog.D) {
            UnLog.d(TAG, "downImage:" + str);
        }
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setChildDirName(ThemeTitleHelper.ICON_DIR);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(ThemeTitleHelper.creatFileName(str));
        fileGuider.setMode(1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str);
        httpSetting.setCacheMode(2);
        httpSetting.setType(500);
        httpSetting.setSavePath(fileGuider);
        httpSetting.setBreakpointTransmission(false);
        httpSetting.setAttempts(1);
        httpSetting.setListener(new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.unification.title.theme.ThemeTitleDataController.2
            {
                ThemeTitleDataController.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse != null) {
                    String absolutePath = httpResponse.getSaveFile().getAbsolutePath();
                    if (TextUtils.isEmpty(absolutePath)) {
                        ThemeTitleDataController.this.downloadFail(list, str2);
                        return;
                    }
                    File file = new File(absolutePath);
                    if (!file.exists() || !file.isFile()) {
                        ThemeTitleDataController.this.downloadFail(list, str2);
                    } else if (ThemeTitleHelper.bitmapHaveTransColor(absolutePath)) {
                        ThemeTitleDataController.this.downloadFail(list, str2);
                        try {
                            UnFileUtils.deleteFile(absolutePath);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } else {
                        ThemeTitleDataController.this.downloadSuccess(list, str2, absolutePath);
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                ThemeTitleDataController.this.downloadFail(list, str2);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
            public void onPause() {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public void downloadFail(List<ThemeTitleSurface> list, String str) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            list.get(i2).isEffected = false;
        }
        ThemeTitleHelper.changeSomeThemeTitleData(list, str);
    }

    public void downloadSuccess(List<ThemeTitleSurface> list, String str, String str2) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            list.get(i2).localImagePath = str2;
        }
        ThemeTitleHelper.changeSomeThemeTitleData(list, str);
    }

    public static ThemeTitleDataController getInstance() {
        if (instance == null) {
            synchronized (ThemeTitleDataController.class) {
                if (instance == null) {
                    instance = new ThemeTitleDataController();
                }
            }
        }
        return instance;
    }

    private void getNetData() {
        if (UnLog.D) {
            UnLog.d(TAG, "getNetData");
        }
        long themeTitleDataVersion = getThemeTitleDataVersion();
        HttpSetting httpSetting = new HttpSetting();
        if (TextUtils.isEmpty(a.g().n())) {
            httpSetting.setFunctionId(FUNCTION_ID);
            if (!TextUtils.isEmpty(a.g().k())) {
                httpSetting.setHost(a.g().k());
            }
        } else {
            httpSetting.setUrl(a.g().n());
        }
        httpSetting.setCacheMode(2);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setAttempts(2);
        httpSetting.putJsonParam("version", Long.valueOf(themeTitleDataVersion));
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.unification.title.theme.ThemeTitleDataController.1
            {
                ThemeTitleDataController.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                List<ThemeTitleSurface> list;
                String str = System.currentTimeMillis() + "";
                ThemeTitleDataController.this.failureTime.set(0);
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                if (fastJsonObject == null || fastJsonObject.size() <= 0) {
                    return;
                }
                String optString = fastJsonObject.optString("result");
                if (UnLog.D) {
                    UnLog.d(ThemeTitleDataController.TAG, "result:" + optString);
                }
                if (!TextUtils.isEmpty(optString)) {
                    ThemeTitleGoldenSurface themeTitleGoldenSurface = (ThemeTitleGoldenSurface) JDJSON.parseObject(optString, ThemeTitleGoldenSurface.class);
                    if (UnLog.D) {
                        UnLog.d(ThemeTitleDataController.TAG, "goldenSurface:" + themeTitleGoldenSurface);
                    }
                    if (themeTitleGoldenSurface != null) {
                        ThemeTitleDataController.this.setThemeTitleDataVersion(themeTitleGoldenSurface.version);
                        if (themeTitleGoldenSurface.isOpen && (list = themeTitleGoldenSurface.surfaces) != null && list.size() > 0) {
                            Map categorySurfaces = ThemeTitleDataController.this.categorySurfaces(themeTitleGoldenSurface.surfaces);
                            for (String str2 : categorySurfaces.keySet()) {
                                if (TextUtils.isEmpty(str2)) {
                                    List list2 = (List) categorySurfaces.get(str2);
                                    for (int i2 = 0; i2 < list2.size(); i2++) {
                                        ((ThemeTitleSurface) list2.get(i2)).isEffected = false;
                                    }
                                    ThemeTitleHelper.changeSomeThemeTitleData(list2, str);
                                } else {
                                    String str3 = ThemeTitleHelper.creatFilePath() + "/" + ThemeTitleHelper.creatFileName(str2);
                                    if (UnLog.D) {
                                        UnLog.d(ThemeTitleDataController.TAG, "surface local file path:" + str3);
                                    }
                                    File file = new File(str3);
                                    List list3 = (List) categorySurfaces.get(str2);
                                    if (!file.exists()) {
                                        ThemeTitleDataController.this.downImage(str2, list3, str);
                                    } else {
                                        for (int i3 = 0; i3 < list3.size(); i3++) {
                                            ((ThemeTitleSurface) list3.get(i3)).localImagePath = str3;
                                        }
                                        ThemeTitleHelper.changeSomeThemeTitleData(list3, str);
                                    }
                                }
                            }
                            return;
                        }
                        ThemeTitleHelper.changeAllThemeTitleData(themeTitleGoldenSurface);
                        return;
                    }
                }
                ThemeTitleHelper.changeAllThemeTitleData(null);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (ThemeTitleDataController.this.failureTime.incrementAndGet() >= 3) {
                    ThemeTitleHelper.changeAllThemeTitleData(null);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i2, int i3) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    private long getThemeTitleDataVersion() {
        return UnSharedPreferencesUtils.getLong(a.g().d(), SHARED_THEME_TITLE_DATA_VERSION, 0L);
    }

    private boolean isAllowCustomThemeRequest() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - UnSharedPreferencesUtils.getLong(a.g().d(), "custom_theme_last_request_time", 0L) > 600000) {
            UnSharedPreferencesUtils.putLong(a.g().d(), "custom_theme_last_request_time", currentTimeMillis);
            return true;
        }
        return false;
    }

    private boolean isAllowRequest() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - UnSharedPreferencesUtils.getLong(a.g().d(), SHARED_LAST_REQEUST_TIME, 0L) > 600000) {
            UnSharedPreferencesUtils.putLong(a.g().d(), SHARED_LAST_REQEUST_TIME, currentTimeMillis);
            return true;
        }
        return false;
    }

    public void setThemeTitleDataVersion(long j2) {
        UnSharedPreferencesUtils.putLong(a.g().d(), SHARED_THEME_TITLE_DATA_VERSION, j2);
    }

    public synchronized void getThemeTitleData(int i2) {
        try {
            if (i2 == 0) {
                UnSharedPreferencesUtils.putLong(a.g().d(), " custom_theme_last_request_time", System.currentTimeMillis());
                UnCustomThemeHelper.getInstance().passiveRequest();
            } else if (i2 == 1 || i2 == 2) {
                if (isAllowCustomThemeRequest()) {
                    UnCustomThemeHelper.getInstance().passiveRequest();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
