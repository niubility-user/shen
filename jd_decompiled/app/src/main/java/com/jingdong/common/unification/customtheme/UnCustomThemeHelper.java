package com.jingdong.common.unification.customtheme;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.un.business.widget.a;
import com.jd.lib.un.utils.UnFileUtils;
import com.jd.lib.un.utils.UnLibFileUtils;
import com.jd.lib.un.utils.UnSharedPreferencesUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.UnLog;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.jingdong.common.unification.customtheme.db.CustomDBController;
import com.jingdong.common.unification.customtheme.entity.ImageInfoEntity;
import com.jingdong.common.unification.customtheme.entity.NavigationInfo;
import com.jingdong.common.unification.customtheme.inter.DownloadThemeListener;
import com.jingdong.common.unification.customtheme.inter.OnThemeChangeListener;
import com.jingdong.common.unification.customtheme.receiver.LoginAndExitReceiver;
import com.jingdong.common.unification.navigationbar.NavigationBase;
import com.jingdong.common.unification.title.theme.ThemeTitleHelper;
import com.jingdong.common.utils.inter.JDOverseasUtil;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.network.JDHttpTookit;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.FileService;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.threadpool.ThreadManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/* loaded from: classes6.dex */
public class UnCustomThemeHelper {
    private static final int DELAYED_MESSAGE = 3;
    private static final int READ_THEME = 1;
    private static final int SEND_THEME = 2;
    private boolean canRequestSave;
    private String currentMode;
    private List<File> deleteImageFile;
    private volatile int downLoadFailCount;
    private volatile int downLoadSuccessCount;
    private DownloadThemeListener downloadThemeListener;
    private List<HttpRequest> downloads;
    private List<HttpRequest> downloadsPro;
    private HashMap<String, ImageInfoEntity> imageInfoMap;
    private boolean isLocalData;
    private int[] lock;
    public boolean login;
    private LoginAndExitReceiver loginAndExitReceiver;
    Runnable mDelayedRunnable;
    private Handler mainHandler;
    private String navigationIds;
    private List<NavigationInfo> navigationInfos;
    private String navigationMtaParams;
    private volatile boolean passiveRequest;
    private ExecutorService singleThreadExecutor;
    private int skinType;
    private List<OnThemeChangeListener> themeChangeListenerList;
    private volatile String themeId;
    private String version;
    private WorkHandler workHandler;
    private HandlerThread workThread;

    /* loaded from: classes6.dex */
    public static class UnCustomThemeHelperHolder {
        private static UnCustomThemeHelper instance = new UnCustomThemeHelper();
    }

    /* loaded from: classes6.dex */
    public class WorkHandler extends Handler {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WorkHandler(Looper looper) {
            super(looper);
            UnCustomThemeHelper.this = r1;
        }

        public NavigationInfo getNavigationInfoByNavigationId(int i2, UnCustomThemeHelper unCustomThemeHelper) {
            NavigationInfo queryNavigationByNavigationId = CustomDBController.queryNavigationByNavigationId(String.valueOf(i2), a.g().p());
            if (queryNavigationByNavigationId == null) {
                return null;
            }
            ImageInfoEntity imageInfo = unCustomThemeHelper.getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_LABEL_IMAGE, queryNavigationByNavigationId.isDark));
            ImageInfoEntity imageInfo2 = unCustomThemeHelper.getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_OPT_LABEL_IMAGE, queryNavigationByNavigationId.isDark));
            ImageInfoEntity imageInfo3 = unCustomThemeHelper.getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_LOTTIE_URL, queryNavigationByNavigationId.isDark));
            if (imageInfo != null) {
                queryNavigationByNavigationId.labelImagePath = imageInfo.localPath;
            }
            if (imageInfo2 != null) {
                queryNavigationByNavigationId.optLabelImagePath = imageInfo2.localPath;
            }
            if (imageInfo3 != null) {
                if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo3.localPath), queryNavigationByNavigationId.lottieMd5)) {
                    queryNavigationByNavigationId.lottiePath = imageInfo3.localPath;
                } else {
                    try {
                        UnFileUtils.deleteFile(imageInfo3.localPath);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            return queryNavigationByNavigationId;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message != null) {
                int i2 = message.what;
                if (i2 == 1) {
                    UnCustomThemeHelper.this.requestData((String) message.obj);
                    UnCustomThemeHelper.this.syncWait();
                } else if (i2 == 2) {
                    UnCustomThemeHelper.this.sendThemeInfo("0", null);
                    UnCustomThemeHelper.this.syncWait();
                }
            }
        }
    }

    private boolean checkChange(HashMap<String, ImageInfoEntity> hashMap) {
        if (this.imageInfoMap.size() != hashMap.size()) {
            return true;
        }
        for (ImageInfoEntity imageInfoEntity : hashMap.values()) {
            if (!imageInfoEntity.equals(this.imageInfoMap.get(imageInfoEntity.imageId))) {
                return true;
            }
        }
        return false;
    }

    private void countDownFresh(long j2) {
        try {
            Handler handler = this.mainHandler;
            if (handler != null) {
                handler.removeCallbacks(this.mDelayedRunnable);
                this.mainHandler.postDelayed(this.mDelayedRunnable, j2 * 1000);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private String creatFileName(String str) {
        return TextUtils.isEmpty(str) ? "" : str.substring(str.lastIndexOf("/") + 1);
    }

    private String creatFilePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(JDHttpTookit.getEngine().getApplicationContext().getFilesDir(), str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }

    private HashMap<String, List<ImageInfoEntity>> dataCategory(HashMap<String, ImageInfoEntity> hashMap) {
        List<ImageInfoEntity> arrayList;
        HashMap<String, List<ImageInfoEntity>> hashMap2 = new HashMap<>();
        for (ImageInfoEntity imageInfoEntity : hashMap.values()) {
            if (hashMap2.containsKey(imageInfoEntity.url)) {
                arrayList = hashMap2.get(imageInfoEntity.url);
            } else {
                arrayList = new ArrayList<>();
            }
            arrayList.add(imageInfoEntity);
            hashMap2.put(imageInfoEntity.url, arrayList);
        }
        return hashMap2;
    }

    private void delectForwardRes() {
        try {
            File filesDir = JDHttpTookit.getEngine().getApplicationContext().getFilesDir();
            File file = new File(filesDir, CustomThemeConstance.SAVE_DIR_FOR);
            if (!file.exists()) {
                file.mkdirs();
            }
            ArrayList arrayList = null;
            File[] listFiles = new File(filesDir.getAbsolutePath() + CustomThemeConstance.SAVE_DIR_FOR_PIC).isDirectory() ? file.listFiles() : null;
            if (listFiles != null && listFiles.length > 0) {
                arrayList = new ArrayList(Arrays.asList(listFiles));
            }
            if (arrayList == null || arrayList.size() <= 0) {
                return;
            }
            deletePics(arrayList);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void delectJson() {
        try {
            File filesDir = JDHttpTookit.getEngine().getApplicationContext().getFilesDir();
            File file = new File(filesDir, CustomThemeConstance.SAVE_DIR_FOR);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (new File(filesDir.getAbsolutePath() + CustomThemeConstance.FORWORD_FILE_NAME).exists()) {
                filesDir.delete();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void deletePics(List<File> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        try {
            ArrayList<File> arrayList = new ArrayList();
            arrayList.addAll(list);
            for (File file : arrayList) {
                if (file.exists()) {
                    file.delete();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private HttpRequest downloadImage(final int i2, final String str, final String str2, final List<ImageInfoEntity> list, final HashMap<String, ImageInfoEntity> hashMap) {
        final FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setChildDirName(CustomThemeConstance.SAVE_DIR);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(str + CartConstant.KEY_YB_INFO_LINK + creatFileName(str2));
        fileGuider.setMode(1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str2);
        httpSetting.setCacheMode(2);
        httpSetting.setType(500);
        httpSetting.setSavePath(fileGuider);
        httpSetting.setBreakpointTransmission(false);
        httpSetting.setAttempts(0);
        httpSetting.setReferer("Download_customTheme");
        httpSetting.setListener(new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.unification.customtheme.UnCustomThemeHelper.4
            {
                UnCustomThemeHelper.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null) {
                    UnCustomThemeHelper.this.downloadImageFail(str);
                    return;
                }
                UnCustomThemeHelper.this.verifyPic(httpResponse.getSaveFile().getAbsolutePath(), i2, str, list, hashMap);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (UnLog.D) {
                    UnLog.d("custom_theme", "download errorCode " + httpError.getErrorCode() + " msg " + httpError.getMessage());
                }
                if (httpError != null) {
                    try {
                        ExceptionReporter.reportExceptionToBugly(new Exception(str2 + CartConstant.KEY_YB_INFO_LINK + httpError.toString()));
                    } catch (Exception unused) {
                    }
                }
                try {
                    UnFileUtils.deleteFile(FileService.getFileAbsolutePath(fileGuider));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                UnCustomThemeHelper.this.downloadImageFail(str);
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
            public void onPause() {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
            public void onProgress(int i3, int i4) {
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
            public void onStart() {
            }
        });
        return HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public synchronized void downloadImageFail(String str) {
        if (this.downLoadFailCount > 0) {
            return;
        }
        try {
            this.downLoadFailCount++;
            for (HttpRequest httpRequest : this.downloads) {
                if (!httpRequest.isStop()) {
                    httpRequest.stop();
                }
            }
        } catch (Exception e2) {
            if (UnLog.D) {
                e2.printStackTrace();
            }
        }
        notifyDownloadThemeResult(str, false);
    }

    private synchronized void downloadImageSuccess(int i2, String str, HashMap<String, ImageInfoEntity> hashMap) {
        this.downLoadSuccessCount++;
        if (UnLog.D) {
            UnLog.d("custom_theme", "downLoadSuccessCount " + this.downLoadSuccessCount);
        }
        if (this.downLoadSuccessCount == i2) {
            sendThemeInfo(str, hashMap);
        }
    }

    private HttpRequest downloadOnlyImage(String str, String str2, final List<ImageInfoEntity> list) {
        final FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setChildDirName(CustomThemeConstance.SAVE_DIR_FOR_PIC);
        fileGuider.setImmutable(false);
        fileGuider.setFileName(str + CartConstant.KEY_YB_INFO_LINK + creatFileName(str2));
        fileGuider.setMode(1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(str2);
        httpSetting.setCacheMode(2);
        httpSetting.setType(500);
        httpSetting.setSavePath(fileGuider);
        httpSetting.setBreakpointTransmission(false);
        httpSetting.setAttempts(0);
        httpSetting.setReferer("Download_customTheme");
        httpSetting.setListener(new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.unification.customtheme.UnCustomThemeHelper.3
            {
                UnCustomThemeHelper.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                if (httpResponse == null) {
                    return;
                }
                String absolutePath = httpResponse.getSaveFile().getAbsolutePath();
                for (ImageInfoEntity imageInfoEntity : list) {
                    if (!TextUtils.isEmpty(imageInfoEntity.md5) && UnCustomThemeHelper.md5Open()) {
                        if (!TextUtils.equals(imageInfoEntity.md5, UnLibFileUtils.getMD5(absolutePath))) {
                            try {
                                File file = new File(absolutePath);
                                if (file.exists()) {
                                    file.delete();
                                    return;
                                }
                                return;
                            } catch (Exception e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }
                    }
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                if (UnLog.D) {
                    UnLog.d("custom_theme", "download errorCode " + httpError.getErrorCode() + " msg " + httpError.getMessage());
                }
                try {
                    UnFileUtils.deleteFile(FileService.getFileAbsolutePath(fileGuider));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
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
        return HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    private String getCurThemeId() {
        return UnSharedPreferencesUtils.getString(a.g().d(), CustomThemeConstance.CUR_THEME_ID, "");
    }

    public static UnCustomThemeHelper getInstance() {
        return UnCustomThemeHelperHolder.instance;
    }

    private String getSkinDataVersion() {
        return UnSharedPreferencesUtils.getString(a.g().d(), CustomThemeConstance.SKIN_DATA_VISON, "-100");
    }

    private String getVerifyCode() {
        String string = UnSharedPreferencesUtils.getString(a.g().d(), CustomThemeConstance.FORWARD_VERIFYCODE, "-100");
        UnLog.d("UnCustomThemeHelper", "verifyCode-s:" + string);
        return string;
    }

    private void initWorkQueue() {
        HandlerThread handlerThread = new HandlerThread("work_thread");
        this.workThread = handlerThread;
        handlerThread.start();
        this.workHandler = new WorkHandler(this.workThread.getLooper());
    }

    public static boolean md5Open() {
        String config = JDMobileConfig.getInstance().getConfig("unification", "customTheme", "md5Open");
        UnLog.d("UnCustomThemeHelper", "md5Open:" + config);
        return TextUtils.isEmpty(config) || TextUtils.equals("1", config);
    }

    private void moveFile(String str, String str2, int i2, String str3, String str4, List<ImageInfoEntity> list, HashMap<String, ImageInfoEntity> hashMap) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    fileInputStream.close();
                    verifyPic(str2, i2, str3, list, hashMap);
                    return;
                }
            }
        } catch (Exception e2) {
            if (UnLog.D) {
                UnLog.d("tianchuangxin---", "===" + e2.toString());
            }
        }
    }

    public void notifyDownloadThemeResult(String str, final boolean z) {
        if (UnLog.D) {
            UnLog.d("custom_theme", str + " \u901a\u77e5\u4e0b\u8f7d\u76ae\u80a4\u7684\u7ed3\u679c isSuccess " + z);
        }
        if (this.passiveRequest) {
            this.mainHandler.post(new Runnable() { // from class: com.jingdong.common.unification.customtheme.UnCustomThemeHelper.7
                {
                    UnCustomThemeHelper.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (UnCustomThemeHelper.this.downloadThemeListener != null) {
                        UnCustomThemeHelper.this.downloadThemeListener.downloadTheme(z);
                    }
                }
            });
            syncNotify();
        } else if (!TextUtils.equals(this.themeId, str)) {
            if (z) {
                return;
            }
            syncNotify();
        } else {
            this.mainHandler.post(new Runnable() { // from class: com.jingdong.common.unification.customtheme.UnCustomThemeHelper.8
                {
                    UnCustomThemeHelper.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (UnCustomThemeHelper.this.downloadThemeListener != null) {
                        UnCustomThemeHelper.this.downloadThemeListener.downloadTheme(z);
                    }
                }
            });
            if (z) {
                return;
            }
            this.workHandler.removeMessages(1);
            this.workHandler.removeMessages(2);
            this.themeId = "";
            syncNotify();
        }
    }

    private void notifyThemeChangeListener(HashMap<String, ImageInfoEntity> hashMap) {
        this.imageInfoMap.clear();
        if (hashMap != null) {
            this.imageInfoMap.putAll(hashMap);
        }
        this.themeId = "";
        CustomDBController.deleteDatas();
        List<NavigationInfo> list = this.navigationInfos;
        if (list != null && list.size() > 0) {
            CustomDBController.insertNavis(this.navigationInfos);
        }
        if (!TextUtils.isEmpty(this.version)) {
            setSkinDataVersion(this.version);
        }
        setNavigationIds(this.navigationIds);
        setSkinType(this.skinType);
        setNavigationMtaParams(this.navigationMtaParams);
        setCurVersionMoudle(this.currentMode);
        CustomDBController.insertDatas(hashMap);
        this.workHandler.removeMessages(1);
        this.workHandler.removeMessages(2);
        ThemeTitleHelper.notifyAllTitleChange();
        this.mainHandler.post(new Runnable() { // from class: com.jingdong.common.unification.customtheme.UnCustomThemeHelper.6
            {
                UnCustomThemeHelper.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (UnCustomThemeHelper.this.themeChangeListenerList != null && !UnCustomThemeHelper.this.themeChangeListenerList.isEmpty()) {
                    for (OnThemeChangeListener onThemeChangeListener : UnCustomThemeHelper.this.themeChangeListenerList) {
                        if (onThemeChangeListener != null) {
                            onThemeChangeListener.themeChange();
                        }
                    }
                }
                new Thread(new Runnable() { // from class: com.jingdong.common.unification.customtheme.UnCustomThemeHelper.6.1
                    {
                        AnonymousClass6.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        UnCustomThemeHelper unCustomThemeHelper = UnCustomThemeHelper.this;
                        unCustomThemeHelper.deletePics(unCustomThemeHelper.deleteImageFile);
                        if (UnCustomThemeHelper.this.isLocalData) {
                            UnCustomThemeHelper.this.delectJson();
                            UnCustomThemeHelper.this.setVerifyCode("");
                            UnCustomThemeHelper.this.isLocalData = false;
                        }
                        UnCustomThemeHelper.this.syncNotify();
                    }
                }).start();
            }
        });
    }

    private HashMap<String, ImageInfoEntity> parseImageData(JDJSONObject jDJSONObject) {
        JDJSONObject jSONObject;
        JDJSONObject jSONObject2;
        HashMap<String, ImageInfoEntity> parseImageDatas;
        if (jDJSONObject == null || (jSONObject = jDJSONObject.getJSONObject("normal")) == null) {
            return null;
        }
        HashMap<String, ImageInfoEntity> parseImageDatas2 = parseImageDatas(jSONObject, false);
        if (parseImageDatas2 != null && !parseImageDatas2.isEmpty() && (jSONObject2 = jDJSONObject.getJSONObject(CustomThemeConstance.NAVI_IMAGE_DARK_TAG)) != null && (parseImageDatas = parseImageDatas(jSONObject2, true)) != null && !parseImageDatas.isEmpty()) {
            parseImageDatas2.putAll(parseImageDatas);
        }
        return parseImageDatas2;
    }

    private HashMap<String, ImageInfoEntity> parseImageDatas(JDJSONObject jDJSONObject, boolean z) {
        String str;
        HashMap<String, ImageInfoEntity> hashMap = new HashMap<>();
        JDJSONArray jSONArray = jDJSONObject.getJSONArray(DataCompassUtils.MODULE_TYPE_HEAD);
        String str2 = "LIGHT";
        if (jSONArray != null && jSONArray.size() > 0) {
            for (int i2 = 0; i2 < jSONArray.size(); i2++) {
                JDJSONObject jSONObject = jSONArray.getJSONObject(i2);
                ImageInfoEntity imageInfoEntity = new ImageInfoEntity();
                imageInfoEntity.imageId = UnCustomThemeUtils.createImageId("title", jSONObject.optString("pageCode"), "imageUrl", z);
                imageInfoEntity.url = jSONObject.optString("imageUrl");
                imageInfoEntity.colorType = jSONObject.optString("colorType");
                imageInfoEntity.md5 = jSONObject.optString("imageUrlMd5");
                if (TextUtils.isEmpty(imageInfoEntity.colorType)) {
                    imageInfoEntity.colorType = z ? "LIGHT" : "DARK";
                }
                imageInfoEntity.isEffected = jSONObject.optBoolean("isEffected");
                if (!TextUtils.isEmpty(imageInfoEntity.url)) {
                    hashMap.put(imageInfoEntity.imageId, imageInfoEntity);
                }
            }
        }
        JDJSONArray jSONArray2 = jDJSONObject.getJSONArray("navigationAll");
        if (jSONArray2 != null && jSONArray2.size() > 0) {
            int i3 = 0;
            while (i3 < jSONArray2.size()) {
                JDJSONObject jSONObject2 = jSONArray2.getJSONObject(i3);
                int optInt = jSONObject2.optInt(CustomThemeConstance.NAVI_DISPLAY_TYPE);
                String optString = jSONObject2.optString("sort");
                String optString2 = jSONObject2.optString("lableName");
                ImageInfoEntity imageInfoEntity2 = new ImageInfoEntity();
                JDJSONArray jDJSONArray = jSONArray2;
                imageInfoEntity2.imageId = UnCustomThemeUtils.createImageId("navigation", jSONObject2.optString("functionId"), CustomThemeConstance.NAVI_LABEL_IMAGE, z);
                imageInfoEntity2.url = jSONObject2.optString(CustomThemeConstance.NAV_UNSELECT);
                imageInfoEntity2.displayType = optInt;
                imageInfoEntity2.sort = optString;
                imageInfoEntity2.lableName = optString2;
                imageInfoEntity2.md5 = jSONObject2.optString("lableImageMd5");
                hashMap.put(imageInfoEntity2.imageId, imageInfoEntity2);
                ImageInfoEntity imageInfoEntity3 = new ImageInfoEntity();
                imageInfoEntity3.imageId = UnCustomThemeUtils.createImageId("navigation", jSONObject2.optString("functionId"), CustomThemeConstance.NAVI_OPT_LABEL_IMAGE, z);
                imageInfoEntity3.url = jSONObject2.optString(CustomThemeConstance.NAV_SELECT_PATH);
                imageInfoEntity3.displayType = optInt;
                imageInfoEntity3.sort = optString;
                imageInfoEntity3.md5 = jSONObject2.optString("optlableImageMd5");
                imageInfoEntity3.lableName = optString2;
                hashMap.put(imageInfoEntity3.imageId, imageInfoEntity3);
                String string = jSONObject2.getString(CustomThemeConstance.NAVI_LOTTIE_URL);
                if (TextUtils.isEmpty(string)) {
                    str = str2;
                } else {
                    ImageInfoEntity imageInfoEntity4 = new ImageInfoEntity();
                    str = str2;
                    imageInfoEntity4.imageId = UnCustomThemeUtils.createImageId("navigation", jSONObject2.optString("functionId"), CustomThemeConstance.NAVI_LOTTIE_URL, z);
                    imageInfoEntity4.url = string;
                    imageInfoEntity4.displayType = optInt;
                    imageInfoEntity4.sort = optString;
                    imageInfoEntity4.lableName = optString2;
                    imageInfoEntity4.md5 = jSONObject2.optString(CustomThemeConstance.NAVI_LOTTIE_MD5);
                    hashMap.put(imageInfoEntity4.imageId, imageInfoEntity4);
                }
                String string2 = jSONObject2.getString(CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL);
                if (!TextUtils.isEmpty(string2)) {
                    ImageInfoEntity imageInfoEntity5 = new ImageInfoEntity();
                    imageInfoEntity5.imageId = UnCustomThemeUtils.createImageId("navigation", jSONObject2.optString("functionId"), CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL, z);
                    imageInfoEntity5.url = string2;
                    imageInfoEntity5.displayType = optInt;
                    imageInfoEntity5.sort = optString;
                    imageInfoEntity5.lableName = optString2;
                    imageInfoEntity5.md5 = jSONObject2.optString(CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL_MD5);
                    hashMap.put(imageInfoEntity5.imageId, imageInfoEntity5);
                }
                String string3 = jSONObject2.getString(CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_URL);
                if (!TextUtils.isEmpty(string3)) {
                    ImageInfoEntity imageInfoEntity6 = new ImageInfoEntity();
                    imageInfoEntity6.imageId = UnCustomThemeUtils.createImageId("navigation", jSONObject2.optString("functionId"), CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_URL, z);
                    imageInfoEntity6.url = string3;
                    imageInfoEntity6.displayType = optInt;
                    imageInfoEntity6.sort = optString;
                    imageInfoEntity6.lableName = optString2;
                    imageInfoEntity6.md5 = jSONObject2.optString(CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_MD5);
                    hashMap.put(imageInfoEntity6.imageId, imageInfoEntity6);
                }
                String string4 = jSONObject2.getString(CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL);
                if (!TextUtils.isEmpty(string4)) {
                    ImageInfoEntity imageInfoEntity7 = new ImageInfoEntity();
                    imageInfoEntity7.imageId = UnCustomThemeUtils.createImageId("navigation", jSONObject2.optString("functionId"), CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL, z);
                    imageInfoEntity7.url = string4;
                    imageInfoEntity7.displayType = optInt;
                    imageInfoEntity7.sort = optString;
                    imageInfoEntity7.lableName = optString2;
                    imageInfoEntity7.md5 = jSONObject2.optString(CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL_MD5);
                    hashMap.put(imageInfoEntity7.imageId, imageInfoEntity7);
                }
                String string5 = jSONObject2.getString(CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_URL);
                if (!TextUtils.isEmpty(string5)) {
                    ImageInfoEntity imageInfoEntity8 = new ImageInfoEntity();
                    imageInfoEntity8.imageId = UnCustomThemeUtils.createImageId("navigation", jSONObject2.optString("functionId"), CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_URL, z);
                    imageInfoEntity8.url = string5;
                    imageInfoEntity8.displayType = optInt;
                    imageInfoEntity8.sort = optString;
                    imageInfoEntity8.lableName = optString2;
                    imageInfoEntity8.md5 = jSONObject2.optString(CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_MD5);
                    hashMap.put(imageInfoEntity8.imageId, imageInfoEntity8);
                }
                String string6 = jSONObject2.getString(CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL);
                if (!TextUtils.isEmpty(string6)) {
                    ImageInfoEntity imageInfoEntity9 = new ImageInfoEntity();
                    imageInfoEntity9.imageId = UnCustomThemeUtils.createImageId("navigation", jSONObject2.optString("functionId"), CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL, z);
                    imageInfoEntity9.url = string6;
                    imageInfoEntity9.displayType = optInt;
                    imageInfoEntity9.sort = optString;
                    imageInfoEntity9.lableName = optString2;
                    imageInfoEntity9.md5 = jSONObject2.optString(CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL_MD5);
                    hashMap.put(imageInfoEntity9.imageId, imageInfoEntity9);
                }
                String string7 = jSONObject2.getString(CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL);
                if (!TextUtils.isEmpty(string7)) {
                    ImageInfoEntity imageInfoEntity10 = new ImageInfoEntity();
                    imageInfoEntity10.imageId = UnCustomThemeUtils.createImageId("navigation", jSONObject2.optString("functionId"), CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL, z);
                    imageInfoEntity10.url = string7;
                    imageInfoEntity10.displayType = optInt;
                    imageInfoEntity10.sort = optString;
                    imageInfoEntity10.lableName = optString2;
                    imageInfoEntity10.md5 = jSONObject2.optString(CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL_MD5);
                    hashMap.put(imageInfoEntity10.imageId, imageInfoEntity10);
                }
                String string8 = jSONObject2.getString(CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL);
                if (!TextUtils.isEmpty(string8)) {
                    ImageInfoEntity imageInfoEntity11 = new ImageInfoEntity();
                    imageInfoEntity11.imageId = UnCustomThemeUtils.createImageId("navigation", jSONObject2.optString("functionId"), CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL, z);
                    imageInfoEntity11.url = string8;
                    imageInfoEntity11.displayType = optInt;
                    imageInfoEntity11.sort = optString;
                    imageInfoEntity11.lableName = optString2;
                    imageInfoEntity11.md5 = jSONObject2.optString(CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL_MD5);
                    hashMap.put(imageInfoEntity11.imageId, imageInfoEntity11);
                }
                String string9 = jSONObject2.getString(CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL);
                if (!TextUtils.isEmpty(string9)) {
                    ImageInfoEntity imageInfoEntity12 = new ImageInfoEntity();
                    imageInfoEntity12.imageId = UnCustomThemeUtils.createImageId("navigation", jSONObject2.optString("functionId"), CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL, z);
                    imageInfoEntity12.url = string9;
                    imageInfoEntity12.displayType = optInt;
                    imageInfoEntity12.sort = optString;
                    imageInfoEntity12.lableName = optString2;
                    imageInfoEntity12.md5 = jSONObject2.optString(CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL_MD5);
                    hashMap.put(imageInfoEntity12.imageId, imageInfoEntity12);
                }
                i3++;
                jSONArray2 = jDJSONArray;
                str2 = str;
            }
        }
        String str3 = str2;
        JDJSONArray jSONArray3 = jDJSONObject.getJSONArray("bgImage");
        if (jSONArray3 != null && jSONArray3.size() > 0) {
            for (int i4 = 0; i4 < jSONArray3.size(); i4++) {
                JDJSONObject jSONObject3 = jSONArray3.getJSONObject(i4);
                ImageInfoEntity imageInfoEntity13 = new ImageInfoEntity();
                imageInfoEntity13.imageId = UnCustomThemeUtils.createImageId("bgImage", "bgImage", CustomThemeConstance.NAV_UNSELECT, z);
                imageInfoEntity13.url = jSONObject3.optString(CustomThemeConstance.NAV_UNSELECT);
                imageInfoEntity13.md5 = jSONObject3.optString("lableImageMd5");
                if (!TextUtils.isEmpty(imageInfoEntity13.url)) {
                    hashMap.put(imageInfoEntity13.imageId, imageInfoEntity13);
                }
            }
        }
        JDJSONArray jSONArray4 = jDJSONObject.getJSONArray(CustomThemeConstance.MOUDLE_MY_JD_BANNER);
        if (jSONArray4 != null && jSONArray4.size() > 0) {
            for (int i5 = 0; i5 < jSONArray4.size(); i5++) {
                JDJSONObject jSONObject4 = jSONArray4.getJSONObject(i5);
                String optString3 = jDJSONObject.optString("myjdBannerColorType");
                if (TextUtils.isEmpty(optString3)) {
                    optString3 = str3;
                }
                ImageInfoEntity imageInfoEntity14 = new ImageInfoEntity();
                imageInfoEntity14.imageId = UnCustomThemeUtils.createImageId(CustomThemeConstance.MOUDLE_MY_JD_BANNER, CustomThemeConstance.MOUDLE_MY_JD_BANNER, CustomThemeConstance.MYJD_BANNER_TOP_IMAGE_PATH, z);
                imageInfoEntity14.url = jSONObject4.optString(CustomThemeConstance.MYJD_BANNER_TOP_IMAGE_PATH);
                imageInfoEntity14.colorType = optString3;
                imageInfoEntity14.md5 = jSONObject4.optString("topImageMd5");
                if (!TextUtils.isEmpty(imageInfoEntity14.url)) {
                    hashMap.put(imageInfoEntity14.imageId, imageInfoEntity14);
                }
                ImageInfoEntity imageInfoEntity15 = new ImageInfoEntity();
                imageInfoEntity15.imageId = UnCustomThemeUtils.createImageId(CustomThemeConstance.MOUDLE_MY_JD_BANNER, CustomThemeConstance.MOUDLE_MY_JD_BANNER, CustomThemeConstance.MYJD_BANNER_BOTTOM_IMAGE_PATH, z);
                imageInfoEntity15.url = jSONObject4.optString(CustomThemeConstance.MYJD_BANNER_BOTTOM_IMAGE_PATH);
                imageInfoEntity15.colorType = optString3;
                imageInfoEntity15.md5 = jSONObject4.optString("bottomImageMd5");
                if (!TextUtils.isEmpty(imageInfoEntity15.url)) {
                    hashMap.put(imageInfoEntity15.imageId, imageInfoEntity15);
                }
            }
        }
        return hashMap;
    }

    private List<NavigationInfo> parseNaviData(JDJSONObject jDJSONObject, boolean z) {
        JDJSONArray jSONArray;
        if (jDJSONObject == null || (jSONArray = jDJSONObject.getJSONArray("navigationAll")) == null) {
            return null;
        }
        List<NavigationInfo> parseArray = JDJSON.parseArray(jSONArray.toJSONString(), NavigationInfo.class);
        if (z && parseArray != null && parseArray.size() > 0) {
            Iterator<NavigationInfo> it = parseArray.iterator();
            while (it.hasNext()) {
                it.next().isDark = true;
            }
        }
        return parseArray;
    }

    private void prepareDownload(String str, HashMap<String, List<ImageInfoEntity>> hashMap, HashMap<String, ImageInfoEntity> hashMap2, boolean z) {
        Set<String> keySet = hashMap.keySet();
        if (UnLog.D) {
            UnLog.d("custom_theme", "downloadImageSize  " + keySet.size());
        }
        for (String str2 : keySet) {
            if (z) {
                String str3 = str + CartConstant.KEY_YB_INFO_LINK + creatFileName(str2);
                String str4 = creatFilePath(CustomThemeConstance.SAVE_DIR_FOR_PIC) + "/" + str3;
                String str5 = creatFilePath(CustomThemeConstance.SAVE_DIR) + "/" + str3;
                if (new File(str4).exists()) {
                    if (UnLog.D) {
                        UnLog.d("tianchuangxin1", "downloadImageUrlLoacal----------" + str2);
                    }
                    moveFile(str4, str5, keySet.size(), str, str2, hashMap.get(str2), hashMap2);
                    this.deleteImageFile.add(new File(str4));
                } else {
                    if (UnLog.D) {
                        UnLog.d("tianchuangxin1", "downloadImageUrlRemote----------" + str2);
                    }
                    this.downloads.add(downloadImage(keySet.size(), str, str2, hashMap.get(str2), hashMap2));
                }
            } else {
                this.downloads.add(downloadImage(keySet.size(), str, str2, hashMap.get(str2), hashMap2));
            }
        }
    }

    private List<File> readLocalFile(String str) {
        File filesDir;
        if (TextUtils.isEmpty(str) || a.g().d() == null || (filesDir = a.g().d().getFilesDir()) == null) {
            return null;
        }
        File file = new File(filesDir.getPath() + "/" + str);
        File[] listFiles = file.isDirectory() ? file.listFiles() : null;
        if (listFiles == null || listFiles.length <= 0) {
            return null;
        }
        return new ArrayList(Arrays.asList(listFiles));
    }

    public String readLocalJson() {
        File filesDir;
        File file;
        StringBuilder sb = new StringBuilder("");
        try {
            filesDir = JDHttpTookit.getEngine().getApplicationContext().getFilesDir();
            file = new File(filesDir, CustomThemeConstance.SAVE_DIR_FOR);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!file.exists()) {
            file.mkdirs();
            return null;
        }
        String str = filesDir.getAbsolutePath() + CustomThemeConstance.FORWORD_FILE_NAME;
        if (new File(str).exists()) {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read <= 0) {
                    break;
                }
                sb.append(new String(bArr, 0, read));
            }
            fileInputStream.close();
            return sb.toString();
        }
        return null;
    }

    private void setNavigationIds(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-100";
        }
        UnLog.d("UnCustomThemeHelper", "navigationIds-s:" + str);
        UnSharedPreferencesUtils.putString(a.g().d(), CustomThemeConstance.NAVI_NAVIGATION_LIST, str);
    }

    private void setNavigationMtaParams(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-100";
        }
        UnSharedPreferencesUtils.putString(a.g().d(), CustomThemeConstance.NAVI_NAVIGATION_MTA_PARAMS, str);
    }

    private void setSkinDataVersion(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-100";
        }
        UnSharedPreferencesUtils.putString(a.g().d(), CustomThemeConstance.SKIN_DATA_VISON, str);
    }

    private void setSkinType(int i2) {
        UnLog.d("UnCustomThemeHelper", "skinType-s:" + i2);
        UnSharedPreferencesUtils.putInt(a.g().d(), CustomThemeConstance.NAVI_SKIN_TYPE, i2);
    }

    private void setUseCustomTheme(boolean z) {
        UnSharedPreferencesUtils.putBoolean(a.g().d(), CustomThemeConstance.USE_CUSTOM_THEME, z);
    }

    public void setVerifyCode(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "-100";
        }
        UnLog.d("UnCustomThemeHelper", "verifyCode-s:" + str);
        UnSharedPreferencesUtils.putString(a.g().d(), CustomThemeConstance.FORWARD_VERIFYCODE, str);
    }

    public void syncNotify() {
        synchronized (this.lock) {
            if (UnLog.D) {
                UnLog.d("custom_theme", "lock.notify  ");
            }
            this.lock.notify();
        }
    }

    public void syncWait() {
        synchronized (this.lock) {
            try {
                if (UnLog.D) {
                    UnLog.d("custom_theme", "lock.wait  ");
                }
                this.lock.wait();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    private boolean useCustomTheme() {
        if (a.g().q()) {
            return false;
        }
        return UnSharedPreferencesUtils.getBoolean(a.g().d(), CustomThemeConstance.USE_CUSTOM_THEME, false);
    }

    public void verifyPic(String str, int i2, String str2, List<ImageInfoEntity> list, HashMap<String, ImageInfoEntity> hashMap) {
        File file;
        for (ImageInfoEntity imageInfoEntity : list) {
            imageInfoEntity.localPath = str;
            if (!TextUtils.isEmpty(imageInfoEntity.md5) && md5Open()) {
                if (!TextUtils.equals(imageInfoEntity.md5, UnLibFileUtils.getMD5(str))) {
                    if (UnLog.D) {
                        UnLog.d("custom_theme", "download error md5");
                    }
                    try {
                        file = new File(str);
                        if (UnLog.D) {
                            UnLog.d("custom_theme", "error file :" + file);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (!file.exists()) {
                        if (UnLog.D) {
                            UnLog.d("custom_theme", "download error file null");
                            return;
                        }
                        return;
                    }
                    boolean delete = file.delete();
                    if (UnLog.D) {
                        UnLog.d("custom_theme", "download error file delete: " + delete);
                    }
                    downloadImageFail(str2);
                    return;
                }
            }
            hashMap.put(imageInfoEntity.imageId, imageInfoEntity);
        }
        if (UnLog.D) {
            UnLog.d("custom_theme", "localPath " + str);
        }
        downloadImageSuccess(i2, str2, hashMap);
    }

    private void writeJson(String str) {
        try {
            File filesDir = JDHttpTookit.getEngine().getApplicationContext().getFilesDir();
            File file = new File(filesDir, CustomThemeConstance.SAVE_DIR_FOR);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(filesDir.getAbsolutePath() + CustomThemeConstance.FORWORD_FILE_NAME);
            if (UnLog.D) {
                file2.setReadable(true);
                file2.setWritable(true);
                file2.setExecutable(true);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file2, false);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            if (UnLog.D) {
                UnLog.d("custom_theme", "json file is exists====== " + file2.exists());
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public String createImageId(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return "";
        }
        return str + CartConstant.KEY_YB_INFO_LINK + str2 + CartConstant.KEY_YB_INFO_LINK + str3;
    }

    public boolean customThemeEnable() {
        return useCustomTheme();
    }

    /* JADX WARN: Removed duplicated region for block: B:229:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x01e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void dealResponseData(java.lang.String r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 846
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.unification.customtheme.UnCustomThemeHelper.dealResponseData(java.lang.String, boolean):void");
    }

    public List<NavigationInfo> getAllNavigationList() {
        return null;
    }

    public String getCurVersionMoudle() {
        return UnSharedPreferencesUtils.getString(a.g().d(), CustomThemeConstance.CUR_VERSION_MOUDLE, "0");
    }

    public ImageInfoEntity getImageInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.imageInfoMap.isEmpty()) {
            HashMap<String, ImageInfoEntity> queryDatas = CustomDBController.queryDatas();
            this.imageInfoMap.clear();
            if (queryDatas != null) {
                this.imageInfoMap.putAll(queryDatas);
            }
        }
        ImageInfoEntity imageInfoEntity = this.imageInfoMap.get(str);
        if (imageInfoEntity == null) {
            return null;
        }
        if (TextUtils.isEmpty(imageInfoEntity.md5) || !md5Open() || TextUtils.equals(UnLibFileUtils.getMD5(imageInfoEntity.localPath), imageInfoEntity.md5)) {
            return imageInfoEntity;
        }
        return null;
    }

    public ImageInfoEntity getNaviBg() {
        ImageInfoEntity imageInfo = a.g().p() ? getImageInfo(UnCustomThemeUtils.createImageId("bgImage", "bgImage", CustomThemeConstance.NAV_UNSELECT, true)) : null;
        return imageInfo == null ? getImageInfo(UnCustomThemeUtils.createImageId("bgImage", "bgImage", CustomThemeConstance.NAV_UNSELECT)) : imageInfo;
    }

    public ImageInfoEntity getNaviBgByModel(boolean z) {
        return getImageInfo(UnCustomThemeUtils.createImageId("bgImage", "bgImage", CustomThemeConstance.NAV_UNSELECT, z));
    }

    public String getNavigationIds() {
        String string = UnSharedPreferencesUtils.getString(a.g().d(), CustomThemeConstance.NAVI_NAVIGATION_LIST, "-100");
        if ("-100".equals(string)) {
            string = "";
        }
        UnLog.d("UnCustomThemeHelper", "navigationIds-s:" + string);
        return string;
    }

    public NavigationInfo getNavigationInfoByFunctionId(String str) {
        return getNavigationInfoByFunctionId(str, a.g().p());
    }

    public NavigationInfo getNavigationInfoByNavigationId(int i2) {
        return getNavigationInfoByNavigationId(i2, a.g().p());
    }

    public String getNavigationMtaParamValue() {
        String string = UnSharedPreferencesUtils.getString(a.g().d(), CustomThemeConstance.NAVI_NAVIGATION_MTA_PARAMS, "-100");
        if ("-100".equals(string)) {
            string = "";
        }
        UnLog.d("UnCustomThemeHelper", "NavigationMtaParamValue-s:" + string);
        return string;
    }

    public int getSkinType() {
        int i2 = UnSharedPreferencesUtils.getInt(a.g().d(), CustomThemeConstance.NAVI_SKIN_TYPE, 2);
        UnLog.d("UnCustomThemeHelper", "skinType-s:" + i2);
        return i2;
    }

    public String getThemeId() {
        return getCurThemeId();
    }

    public ImageInfoEntity getTitleImageInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getImageInfo(createImageId("title", str, "imageUrl"));
    }

    public void passiveRequest() {
        this.passiveRequest = true;
        this.workHandler.removeMessages(1);
        this.workHandler.removeMessages(2);
        Message obtain = Message.obtain();
        obtain.obj = "";
        obtain.what = 1;
        this.workHandler.sendMessage(obtain);
    }

    public void registerLoginAndExitReceiver(Context context) {
        this.loginAndExitReceiver = new LoginAndExitReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jingdong.action.user.login.in");
        intentFilter.addAction("com.jingdong.action.user.login.out");
        context.registerReceiver(this.loginAndExitReceiver, intentFilter);
    }

    public void removeThemeChangeListener() {
        this.downloadThemeListener = null;
    }

    public void requestData(final String str) {
        if (a.g().q()) {
            return;
        }
        HttpSetting httpSetting = new HttpSetting();
        if (TextUtils.isEmpty(a.g().f())) {
            if (!TextUtils.isEmpty(a.g().e())) {
                httpSetting.setFunctionId(a.g().e());
            } else {
                httpSetting.setFunctionId(CustomThemeConstance.GET_THEME_INFO_FUNCTION_ID);
            }
            if (!TextUtils.isEmpty(a.g().k())) {
                httpSetting.setHost(a.g().k());
            }
        } else {
            httpSetting.setUrl(a.g().f());
        }
        httpSetting.setCacheMode(2);
        httpSetting.setUseFastJsonParser(true);
        httpSetting.setAttempts(0);
        httpSetting.putJsonParam("skinId", str);
        httpSetting.putJsonParam("siteType", Integer.valueOf(JDOverseasUtil.getCurrentOverseasArea()));
        httpSetting.putJsonParam("currentMode", NavigationBase.getInstance().getNavigationCurrentMode() + "");
        String skinDataVersion = getSkinDataVersion();
        if (UnLog.D) {
            UnLog.d("tianchuangxin1", "version=" + skinDataVersion + ", tid=" + str + ", currentThemeId=" + getCurThemeId() + ", currentMode=" + NavigationBase.getInstance().getNavigationCurrentMode());
        }
        if (!TextUtils.equals(skinDataVersion, "-100") && !TextUtils.isEmpty(skinDataVersion)) {
            httpSetting.putJsonParam("version", getSkinDataVersion());
        }
        if (UnLog.D) {
            UnLog.d("sitetype", "compare filePath " + JDOverseasUtil.getCurrentOverseasArea());
        }
        httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.unification.customtheme.UnCustomThemeHelper.1
            {
                UnCustomThemeHelper.this = this;
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                UnCustomThemeHelper.this.version = null;
                if (!fastJsonObject.optBoolean("success")) {
                    UnCustomThemeHelper.this.notifyDownloadThemeResult(str, false);
                    return;
                }
                String optString = fastJsonObject.optString("result");
                if (TextUtils.isEmpty(optString)) {
                    UnCustomThemeHelper.this.notifyDownloadThemeResult(str, false);
                } else {
                    UnCustomThemeHelper.this.dealResponseData(optString, false);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                UnCustomThemeHelper.this.notifyDownloadThemeResult(str, false);
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

    public void reset() {
        setUseCustomTheme(false);
        setCurThemeId("");
        this.themeId = "";
        setNavigationMtaParams("");
        setNavigationIds("");
        setCurVersionMoudle("");
        setSkinDataVersion("-100");
        setSkinType(2);
        CustomDBController.deleteDatas();
        this.imageInfoMap.clear();
        ThemeTitleHelper.notifyAllTitleChange();
        List<OnThemeChangeListener> list = this.themeChangeListenerList;
        if (list != null && !list.isEmpty()) {
            for (OnThemeChangeListener onThemeChangeListener : this.themeChangeListenerList) {
                if (onThemeChangeListener != null) {
                    onThemeChangeListener.themeChange();
                }
            }
        }
        passiveRequest();
    }

    public void sendThemeInfo(final String str, final HashMap<String, ImageInfoEntity> hashMap) {
        if (!this.login) {
            themeChange(str, hashMap);
        } else if (!this.canRequestSave) {
            themeChange(str, hashMap);
        } else {
            this.canRequestSave = false;
            HttpSetting httpSetting = new HttpSetting();
            if (TextUtils.isEmpty(a.g().i())) {
                if (!TextUtils.isEmpty(a.g().i())) {
                    httpSetting.setFunctionId(a.g().j());
                } else {
                    httpSetting.setFunctionId(CustomThemeConstance.SEND_THEME_INFO_FUNCTION_ID);
                }
                if (!TextUtils.isEmpty(a.g().k())) {
                    httpSetting.setHost(a.g().k());
                }
            } else {
                httpSetting.setUrl(a.g().i());
            }
            httpSetting.setCacheMode(2);
            httpSetting.setUseFastJsonParser(true);
            httpSetting.setAttempts(0);
            httpSetting.putJsonParam("skinId", str);
            httpSetting.putJsonParam("siteType", Integer.valueOf(JDOverseasUtil.getCurrentOverseasArea()));
            httpSetting.setListener(new HttpGroup.OnAllListener() { // from class: com.jingdong.common.unification.customtheme.UnCustomThemeHelper.5
                {
                    UnCustomThemeHelper.this = this;
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    JDJSONObject fastJsonObject = httpResponse.getFastJsonObject();
                    if (!fastJsonObject.optBoolean("success")) {
                        UnCustomThemeHelper.this.notifyDownloadThemeResult(str, false);
                    } else if (!TextUtils.equals("0", fastJsonObject.optString("code"))) {
                        UnCustomThemeHelper.this.notifyDownloadThemeResult(str, false);
                    } else {
                        UnCustomThemeHelper.this.themeChange(str, hashMap);
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    UnCustomThemeHelper.this.notifyDownloadThemeResult(str, false);
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
    }

    public void setCurThemeId(String str) {
        UnSharedPreferencesUtils.putString(a.g().d(), CustomThemeConstance.CUR_THEME_ID, str);
    }

    public void setCurVersionMoudle(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        UnSharedPreferencesUtils.putString(a.g().d(), CustomThemeConstance.CUR_VERSION_MOUDLE, str);
    }

    public void setDownloadThemeListener(DownloadThemeListener downloadThemeListener) {
        this.downloadThemeListener = downloadThemeListener;
    }

    public void setLogin(boolean z) {
        this.login = z;
    }

    public void setOnThemeChangeListener(OnThemeChangeListener onThemeChangeListener) {
        if (this.themeChangeListenerList == null) {
            this.themeChangeListenerList = new ArrayList();
        }
        this.themeChangeListenerList.add(onThemeChangeListener);
    }

    public void setThemeId(String str, DownloadThemeListener downloadThemeListener) {
        if (TextUtils.isEmpty(str)) {
            if (downloadThemeListener != null) {
                downloadThemeListener.downloadTheme(false);
                return;
            }
            return;
        }
        this.canRequestSave = true;
        this.themeId = str;
        this.downloadThemeListener = downloadThemeListener;
        this.passiveRequest = false;
        this.workHandler.removeMessages(1);
        this.workHandler.removeMessages(2);
        Message obtain = Message.obtain();
        obtain.obj = this.themeId;
        obtain.what = 1;
        this.workHandler.sendMessage(obtain);
    }

    public void themeChange(String str, HashMap<String, ImageInfoEntity> hashMap) {
        if (!TextUtils.isEmpty(this.themeId) && !TextUtils.equals(this.themeId, str)) {
            if (hashMap != null) {
                ArrayList arrayList = new ArrayList();
                Iterator<ImageInfoEntity> it = hashMap.values().iterator();
                while (it.hasNext()) {
                    arrayList.add(new File(it.next().localPath));
                }
                deletePics(arrayList);
            }
            syncNotify();
            return;
        }
        setCurThemeId(str);
        setUseCustomTheme(true);
        notifyDownloadThemeResult(str, true);
        notifyThemeChangeListener(hashMap);
    }

    public boolean transModuleOpenByImageId(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String[] split = str.split(CartConstant.KEY_YB_INFO_LINK);
        if (split.length != 3) {
            return false;
        }
        String str2 = split[1];
        String config = JDMobileConfig.getInstance().getConfig("unification", "customTheme", "transModule");
        return (TextUtils.isEmpty(config) || config.contains(str2)) ? false : true;
    }

    public boolean transModuleOpenByPageCode(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String config = JDMobileConfig.getInstance().getConfig("unification", "customTheme", "transModule");
        return (TextUtils.isEmpty(config) || config.contains(str)) ? false : true;
    }

    public void unregisterLoginAndExitReceiver(Context context) {
        context.unregisterReceiver(this.loginAndExitReceiver);
    }

    private UnCustomThemeHelper() {
        this.imageInfoMap = new HashMap<>();
        this.deleteImageFile = new ArrayList();
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.login = false;
        this.passiveRequest = false;
        this.canRequestSave = false;
        this.skinType = 2;
        this.isLocalData = false;
        this.lock = new int[0];
        this.mDelayedRunnable = new Runnable() { // from class: com.jingdong.common.unification.customtheme.UnCustomThemeHelper.2
            {
                UnCustomThemeHelper.this = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (UnCustomThemeHelper.this.downloads != null && UnCustomThemeHelper.this.downloads.size() > 0) {
                    for (HttpRequest httpRequest : UnCustomThemeHelper.this.downloads) {
                        if (!httpRequest.isStop()) {
                            httpRequest.stop();
                        }
                    }
                }
                if (UnCustomThemeHelper.this.downloadsPro != null && UnCustomThemeHelper.this.downloadsPro.size() > 0) {
                    for (HttpRequest httpRequest2 : UnCustomThemeHelper.this.downloadsPro) {
                        if (!httpRequest2.isStop()) {
                            httpRequest2.stop();
                        }
                    }
                }
                ThreadManager.light().post(new Runnable() { // from class: com.jingdong.common.unification.customtheme.UnCustomThemeHelper.2.1
                    {
                        AnonymousClass2.this = this;
                    }

                    @Override // java.lang.Runnable
                    public void run() {
                        String readLocalJson = UnCustomThemeHelper.this.readLocalJson();
                        UnCustomThemeHelper.this.isLocalData = true;
                        if (UnLog.D) {
                            UnLog.d("custom_theme", readLocalJson);
                        }
                        if (TextUtils.isEmpty(readLocalJson)) {
                            return;
                        }
                        UnCustomThemeHelper.this.dealResponseData(readLocalJson, true);
                    }
                });
            }
        };
        this.downLoadSuccessCount = 0;
        this.downLoadFailCount = 0;
        this.downloads = new ArrayList();
        this.downloadsPro = new ArrayList();
        initWorkQueue();
    }

    public void removeThemeChangeListener(OnThemeChangeListener onThemeChangeListener) {
        List<OnThemeChangeListener> list = this.themeChangeListenerList;
        if (list == null || !list.contains(onThemeChangeListener)) {
            return;
        }
        this.themeChangeListenerList.remove(onThemeChangeListener);
    }

    public NavigationInfo getNavigationInfoByFunctionId(String str, boolean z) {
        NavigationInfo queryNavigationByFunctionId = CustomDBController.queryNavigationByFunctionId(str, z);
        if (z && queryNavigationByFunctionId == null) {
            queryNavigationByFunctionId = CustomDBController.queryNavigationByFunctionId(str, false);
        }
        if (queryNavigationByFunctionId == null) {
            return null;
        }
        ImageInfoEntity imageInfo = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByFunctionId.functionId, CustomThemeConstance.NAVI_LABEL_IMAGE, queryNavigationByFunctionId.isDark));
        ImageInfoEntity imageInfo2 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByFunctionId.functionId, CustomThemeConstance.NAVI_OPT_LABEL_IMAGE, queryNavigationByFunctionId.isDark));
        ImageInfoEntity imageInfo3 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByFunctionId.functionId, CustomThemeConstance.NAVI_LOTTIE_URL, queryNavigationByFunctionId.isDark));
        ImageInfoEntity imageInfo4 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByFunctionId.functionId, CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL, queryNavigationByFunctionId.isDark));
        ImageInfoEntity imageInfo5 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByFunctionId.functionId, CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_URL, queryNavigationByFunctionId.isDark));
        ImageInfoEntity imageInfo6 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByFunctionId.functionId, CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL, queryNavigationByFunctionId.isDark));
        ImageInfoEntity imageInfo7 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByFunctionId.functionId, CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_URL, queryNavigationByFunctionId.isDark));
        ImageInfoEntity imageInfo8 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByFunctionId.functionId, CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL, queryNavigationByFunctionId.isDark));
        ImageInfoEntity imageInfo9 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByFunctionId.functionId, CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL, queryNavigationByFunctionId.isDark));
        ImageInfoEntity imageInfo10 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByFunctionId.functionId, CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL, queryNavigationByFunctionId.isDark));
        ImageInfoEntity imageInfo11 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByFunctionId.functionId, CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL, queryNavigationByFunctionId.isDark));
        if (imageInfo != null) {
            queryNavigationByFunctionId.labelImagePath = imageInfo.localPath;
        }
        if (imageInfo2 != null) {
            queryNavigationByFunctionId.optLabelImagePath = imageInfo2.localPath;
        }
        if (imageInfo3 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo3.localPath), queryNavigationByFunctionId.lottieMd5)) {
                queryNavigationByFunctionId.lottiePath = imageInfo3.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo3.localPath);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (imageInfo5 != null) {
            queryNavigationByFunctionId.middleFirstImgPath = imageInfo5.localPath;
        }
        if (imageInfo7 != null) {
            queryNavigationByFunctionId.middleSecondImgPath = imageInfo7.localPath;
        }
        if (imageInfo11 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo11.localPath), queryNavigationByFunctionId.marketingLottieUrlMd5)) {
                queryNavigationByFunctionId.marketingLottiePath = imageInfo11.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo11.localPath);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (imageInfo9 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo9.localPath), queryNavigationByFunctionId.effectStartLottieUrlMd5)) {
                queryNavigationByFunctionId.effectStartLottiePath = imageInfo9.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo9.localPath);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
        if (imageInfo10 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo10.localPath), queryNavigationByFunctionId.effectEndLottieUrlMd5)) {
                queryNavigationByFunctionId.effectEndLottiePath = imageInfo10.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo10.localPath);
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        }
        if (imageInfo4 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo4.localPath), queryNavigationByFunctionId.middleFirstLottieUrlMd5)) {
                queryNavigationByFunctionId.middleFirstLottiePath = imageInfo4.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo4.localPath);
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
        }
        if (imageInfo6 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo6.localPath), queryNavigationByFunctionId.middleSecondLottieUrlMd5)) {
                queryNavigationByFunctionId.middleSecondLottiePath = imageInfo6.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo6.localPath);
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
            }
        }
        if (imageInfo8 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo8.localPath), queryNavigationByFunctionId.middleThirdLottieUrlMd5)) {
                queryNavigationByFunctionId.middleThirdLottiePath = imageInfo8.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo8.localPath);
                } catch (Exception e8) {
                    e8.printStackTrace();
                }
            }
        }
        return queryNavigationByFunctionId;
    }

    public NavigationInfo getNavigationInfoByNavigationId(int i2, boolean z) {
        NavigationInfo queryNavigationByNavigationId = CustomDBController.queryNavigationByNavigationId(String.valueOf(i2), z);
        if (z && queryNavigationByNavigationId == null) {
            queryNavigationByNavigationId = CustomDBController.queryNavigationByNavigationId(String.valueOf(i2), false);
        }
        if (queryNavigationByNavigationId == null) {
            return null;
        }
        ImageInfoEntity imageInfo = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_LABEL_IMAGE, queryNavigationByNavigationId.isDark));
        ImageInfoEntity imageInfo2 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_OPT_LABEL_IMAGE, queryNavigationByNavigationId.isDark));
        ImageInfoEntity imageInfo3 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_LOTTIE_URL, queryNavigationByNavigationId.isDark));
        ImageInfoEntity imageInfo4 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_MIDDLE_FIRST_LOTTIE_URL, queryNavigationByNavigationId.isDark));
        ImageInfoEntity imageInfo5 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_MIDDLE_FIRST_IMG_URL, queryNavigationByNavigationId.isDark));
        ImageInfoEntity imageInfo6 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_MIDDLE_SECOND_LOTTIE_URL, queryNavigationByNavigationId.isDark));
        ImageInfoEntity imageInfo7 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_MIDDLE_SECOND_IMG_URL, queryNavigationByNavigationId.isDark));
        ImageInfoEntity imageInfo8 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_MIDDLE_THIRD_LOTTIE_URL, queryNavigationByNavigationId.isDark));
        ImageInfoEntity imageInfo9 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_MIDDLE_START_LOTTIE_URL, queryNavigationByNavigationId.isDark));
        ImageInfoEntity imageInfo10 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_MIDDLE_END_LOTTIE_URL, queryNavigationByNavigationId.isDark));
        ImageInfoEntity imageInfo11 = getImageInfo(UnCustomThemeUtils.createImageId("navigation", queryNavigationByNavigationId.functionId, CustomThemeConstance.NAVI_MIDDLE_MARKET_LOTTIE_URL, queryNavigationByNavigationId.isDark));
        if (imageInfo != null) {
            queryNavigationByNavigationId.labelImagePath = imageInfo.localPath;
        }
        if (imageInfo2 != null) {
            queryNavigationByNavigationId.optLabelImagePath = imageInfo2.localPath;
        }
        if (imageInfo3 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo3.localPath), queryNavigationByNavigationId.lottieMd5)) {
                queryNavigationByNavigationId.lottiePath = imageInfo3.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo3.localPath);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        if (imageInfo5 != null) {
            queryNavigationByNavigationId.middleFirstImgPath = imageInfo5.localPath;
        }
        if (imageInfo7 != null) {
            queryNavigationByNavigationId.middleSecondImgPath = imageInfo7.localPath;
        }
        if (imageInfo11 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo11.localPath), queryNavigationByNavigationId.effectStartLottieUrlMd5)) {
                queryNavigationByNavigationId.marketingLottiePath = imageInfo11.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo11.localPath);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        }
        if (imageInfo9 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo9.localPath), queryNavigationByNavigationId.effectStartLottieUrlMd5)) {
                queryNavigationByNavigationId.effectStartLottiePath = imageInfo9.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo9.localPath);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        }
        if (imageInfo10 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo10.localPath), queryNavigationByNavigationId.effectEndLottieUrlMd5)) {
                queryNavigationByNavigationId.effectEndLottiePath = imageInfo10.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo10.localPath);
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
            }
        }
        if (imageInfo4 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo4.localPath), queryNavigationByNavigationId.middleFirstLottieUrlMd5)) {
                queryNavigationByNavigationId.middleFirstLottiePath = imageInfo4.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo4.localPath);
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
        }
        if (imageInfo6 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo6.localPath), queryNavigationByNavigationId.middleSecondLottieUrlMd5)) {
                queryNavigationByNavigationId.middleSecondLottiePath = imageInfo6.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo6.localPath);
                } catch (Exception e7) {
                    e7.printStackTrace();
                }
            }
        }
        if (imageInfo8 != null) {
            if (TextUtils.equals(UnLibFileUtils.getMD5(imageInfo8.localPath), queryNavigationByNavigationId.middleThirdLottieUrlMd5)) {
                queryNavigationByNavigationId.middleThirdLottiePath = imageInfo8.localPath;
            } else {
                try {
                    UnFileUtils.deleteFile(imageInfo8.localPath);
                } catch (Exception e8) {
                    e8.printStackTrace();
                }
            }
        }
        UnLog.d("UnCustomThemeHelper", "info:" + queryNavigationByNavigationId.toString());
        return queryNavigationByNavigationId;
    }

    public ImageInfoEntity getTitleImageInfo(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getImageInfo(UnCustomThemeUtils.createImageId("title", str, "imageUrl", z));
    }

    public ImageInfoEntity getImageInfo(String str, String str2, String str3, boolean z) {
        ImageInfoEntity imageInfo = z ? getImageInfo(UnCustomThemeUtils.createImageId(str, str2, str3, z)) : null;
        return imageInfo == null ? getImageInfo(UnCustomThemeUtils.createImageId(str, str2, str3)) : imageInfo;
    }

    private void prepareDownload(String str, HashMap<String, List<ImageInfoEntity>> hashMap) {
        Set<String> keySet = hashMap.keySet();
        if (UnLog.D) {
            UnLog.d("custom_theme", "downloadImageSize  " + keySet.size());
        }
        for (String str2 : keySet) {
            this.downloadsPro.add(downloadOnlyImage(str, str2, hashMap.get(str2)));
        }
    }
}
