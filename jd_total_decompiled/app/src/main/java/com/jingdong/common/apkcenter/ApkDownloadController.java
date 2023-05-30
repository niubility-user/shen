package com.jingdong.common.apkcenter;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.libs.hybrid.HybridSDK;
import com.jingdong.common.apkcenter.ApkCenter;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.unification.navigationbar.db.NavigationDbConstants;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.auraSetting.AuraBundleConfig;
import com.jingdong.jdsdk.auraSetting.AuraBundleInfos;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.utils.PackageInfoUtil;
import com.jingdong.jdsdk.utils.SDKUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.tencent.mapsdk.internal.l4;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class ApkDownloadController {
    private static final String AURA_BUNDLE_DOWNLOAD_END = "AuraBundleDownloadEnd";
    private static final int DOWNLOAD_TYPE_FORCE_UPDATE = 2;
    private static final int DOWNLOAD_TYPE_SILENCE_UPDATE = 1;
    private static final String EXTENSION = ".apk";
    private static final String FUNCTIONID = "apkList";
    private static final String SHARE_DATAVERSION = "dataVersion_ApkDownload";
    private static final String TAG = "ApkDownload";
    private static final String childDir = "apkcenter_old";
    private static ApkDownloadController helper;
    private DownLoadInfo currentDownloadInfo;
    private List<DownLoadInfo> downLoadList;
    private BundleDownloadQueue downloadQueue;
    private List<Map<String, String>> providedBundleInfos;
    private boolean isRequest = false;
    private boolean isBreakRequest = false;
    private Map<String, ApkCenter.ApkListener> listenerMap = new ConcurrentHashMap();
    private boolean hasNextLoop = false;
    private boolean isWifiDownloadProvided = false;
    private SharedPreferences.OnSharedPreferenceChangeListener sharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.jingdong.common.apkcenter.ApkDownloadController.2
        @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            ApkDownloadController.this.isWifiDownloadProvided = sharedPreferences.getBoolean("bundle_provided_tip_button", false);
            if (ApkDownloadController.this.isWifiDownloadProvided) {
                ApkDownloadController.this.breakPointDownload();
            }
            OKLog.d(ApkDownloadController.TAG, "onSharedPreferenceChanged3");
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class BundleOrderComparator implements Comparator<ApkResult> {
        BundleOrderComparator() {
        }

        @Override // java.util.Comparator
        public int compare(ApkResult apkResult, ApkResult apkResult2) {
            return apkResult.downloadOrder - apkResult2.downloadOrder;
        }
    }

    /* loaded from: classes5.dex */
    public class DownLoadInfo {
        public static final int STATUS_EDN = 2;
        public static final int STATUS_ERROR = 3;
        public static final int STATUS_PAUSE = 4;
        public static final int STATUS_START = 1;
        private ApkCenter.ApkListener apkListener;
        private ApkResult apkResult;
        public int downloadStatus;
        private HttpRequest httpRequest;
        private HttpSetting httpSetting;
        private boolean isForce;
        public boolean isFromProvidedPage;

        public DownLoadInfo(ApkDownloadController apkDownloadController, ApkResult apkResult) {
            this(apkResult, null, null);
        }

        public HttpSetting createHttpSetting(final ApkResult apkResult) {
            FileGuider fileGuider = new FileGuider();
            fileGuider.setSpace(1);
            fileGuider.setChildDirName(ApkDownloadController.childDir);
            fileGuider.setImmutable(false);
            fileGuider.setFileName(apkResult.fileName);
            fileGuider.setMode(1);
            HttpSetting httpSetting = new HttpSetting();
            httpSetting.setUrl(apkResult.downloadUrl);
            httpSetting.setCacheMode(2);
            httpSetting.setListener(new HttpGroup.OnAllAndPauseListener() { // from class: com.jingdong.common.apkcenter.ApkDownloadController.DownLoadInfo.1
                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
                public void onEnd(HttpResponse httpResponse) {
                    if (OKLog.D) {
                        OKLog.d(ApkDownloadController.TAG, "onEnd apkResult: apkId:" + apkResult.id + " MD5:" + apkResult.md5);
                    }
                    String path = httpResponse.getSaveFile().getPath();
                    String headerField = httpResponse.getHeaderField(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME);
                    String renameFile = FileUtils.renameFile(path, headerField);
                    if (OKLog.D) {
                        OKLog.d(ApkDownloadController.TAG, "onEnd apkResult:  apkId:" + apkResult.id + " localPath:" + path + " fileName:" + headerField + " newFilePath:" + renameFile);
                    }
                    if (renameFile != null) {
                        apkResult.fileName = headerField;
                    } else {
                        renameFile = path;
                    }
                    ApkResult apkResult2 = apkResult;
                    apkResult2.currentSize = apkResult2.size;
                    apkResult2.localPath = renameFile;
                    boolean z = true;
                    if (ApkDownloadController.this.isCompleted(apkResult2)) {
                        if (OKLog.D) {
                            OKLog.e(ApkDownloadController.TAG, apkResult.id + " onEnd:  pass md5 verify, call OnFinish()");
                        }
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(ApkDownloadTable.FIELD_LOCAL_PATH, apkResult.localPath);
                        contentValues.put(ApkDownloadTable.FIELD_FILE_NAME, apkResult.fileName);
                        contentValues.put(ApkDownloadTable.FIELD_CURRENT_SIZE, Integer.valueOf(apkResult.currentSize));
                        ApkDownloadTable.updateByMd5(apkResult.md5, contentValues);
                        apkResult.status = 1;
                        try {
                            if (DownLoadInfo.this.apkListener != null) {
                                DownLoadInfo.this.apkListener.onFinish(apkResult);
                            }
                            if (apkResult.getUpdateListener() != null) {
                                apkResult.getUpdateListener().onDownloadFinish();
                            }
                        } catch (Exception e2) {
                            OKLog.e(ApkDownloadController.TAG, e2);
                        }
                        ApkDownloadController.this.removeFormQueue(apkResult);
                    } else {
                        if (OKLog.D) {
                            OKLog.e(ApkDownloadController.TAG, apkResult.id + " onEnd: do not pass md5 verify, delete file & call OnFailure()");
                        }
                        ApkResult apkResult3 = apkResult;
                        apkResult3.localPath = "";
                        apkResult3.currentSize = 0;
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put(ApkDownloadTable.FIELD_LOCAL_PATH, apkResult.localPath);
                        contentValues2.put(ApkDownloadTable.FIELD_FILE_NAME, apkResult.fileName);
                        contentValues2.put(ApkDownloadTable.FIELD_CURRENT_SIZE, Integer.valueOf(apkResult.currentSize));
                        ApkDownloadTable.updateByMd5(apkResult.md5, contentValues2);
                        apkResult.status = 0;
                        try {
                            if (DownLoadInfo.this.apkListener != null) {
                                DownLoadInfo.this.apkListener.onFailure("");
                            }
                            if (apkResult.getUpdateListener() != null) {
                                apkResult.getUpdateListener().onDownloadFailure(new RuntimeException("md5 is different, download error!! targetmd5:" + apkResult.md5 + " downlaodMd5:" + FileUtils.getMD5(apkResult.localPath)));
                            }
                        } catch (Exception e3) {
                            OKLog.e(ApkDownloadController.TAG, e3);
                        }
                        File file = new File(renameFile);
                        if (file.exists()) {
                            file.delete();
                        }
                        z = false;
                    }
                    DownLoadInfo downLoadInfo = DownLoadInfo.this;
                    downLoadInfo.downloadStatus = 2;
                    if (ApkDownloadController.this.downloadQueue != null) {
                        ApkDownloadController.this.nextDownload();
                    }
                    String str = AuraBundleInfos.getBundleNameFromUpdateID(apkResult.id) + CartConstant.KEY_YB_INFO_LINK + apkResult.bundleVersionCode + CartConstant.KEY_YB_INFO_LINK + z;
                    JDMtaUtils.sendExposureData(JdSdk.getInstance().getApplication().getApplicationContext(), "DownlaodListener.onEnd", "", "" + PackageInfoUtil.getVersionCode(), ApkDownloadController.AURA_BUNDLE_DOWNLOAD_END, str, "", "", "");
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
                public void onError(HttpError httpError) {
                    if (OKLog.D) {
                        OKLog.d(ApkDownloadController.TAG, apkResult.id + "---apk download error---" + httpError);
                    }
                    try {
                        if (DownLoadInfo.this.apkListener != null) {
                            DownLoadInfo.this.apkListener.onFailure("");
                        }
                        if (apkResult.getUpdateListener() != null) {
                            apkResult.getUpdateListener().onDownloadFailure(httpError);
                        }
                    } catch (Exception e2) {
                        OKLog.e(ApkDownloadController.TAG, e2);
                    }
                    DownLoadInfo downLoadInfo = DownLoadInfo.this;
                    downLoadInfo.downloadStatus = 3;
                    if (ApkDownloadController.this.downloadQueue != null) {
                        ApkDownloadController.this.nextDownload();
                    }
                    ApkResult apkResult2 = apkResult;
                    apkResult2.status = 0;
                    ApkDownloadTable.updateCurrentSizeByMd5(apkResult2.md5, apkResult2.currentSize);
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
                public void onPause() {
                    if (OKLog.D) {
                        OKLog.d(ApkDownloadController.TAG, apkResult.id + "---apk download pause---");
                    }
                    try {
                        if (apkResult.getUpdateListener() != null) {
                            apkResult.getUpdateListener().onPause(true);
                        }
                    } catch (Exception e2) {
                        OKLog.e(ApkDownloadController.TAG, e2);
                    }
                    DownLoadInfo.this.downloadStatus = 4;
                    ApkResult apkResult2 = apkResult;
                    apkResult2.status = 0;
                    ApkDownloadTable.updateCurrentSizeByMd5(apkResult2.md5, apkResult2.currentSize);
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
                public void onProgress(int i2, int i3) {
                    if (OKLog.D) {
                        OKLog.d(ApkDownloadController.TAG, apkResult.id + LangUtils.SINGLE_SPACE + apkResult.fileName + "----download progress---" + i3);
                    }
                    ApkResult apkResult2 = apkResult;
                    if (apkResult2.currentSize == 0 && apkResult2.size != i2) {
                        apkResult2.size = i2;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(ApkDownloadTable.FIELD_SIZE, Integer.valueOf(i2));
                        ApkDownloadTable.updateByMd5(apkResult.md5, contentValues);
                    }
                    ApkResult apkResult3 = apkResult;
                    apkResult3.currentSize = i3;
                    ApkDownloadTable.updateCurrentSizeByMd5(apkResult3.md5, i3);
                    try {
                        if (DownLoadInfo.this.apkListener != null) {
                            DownLoadInfo.this.apkListener.onDownloadProgressChanged(i3);
                        }
                        if (apkResult.getUpdateListener() != null) {
                            apkResult.getUpdateListener().onDownloadProgressChanged(i2, i3);
                        }
                    } catch (Exception e2) {
                        OKLog.e(ApkDownloadController.TAG, e2);
                    }
                }

                @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
                public void onStart() {
                    DownLoadInfo.this.downloadStatus = 1;
                    if (OKLog.D) {
                        OKLog.d(ApkDownloadController.TAG, apkResult.id + " onStart DOWNLOAD");
                    }
                    if (apkResult.getUpdateListener() != null) {
                        apkResult.getUpdateListener().onStart();
                    }
                }
            });
            httpSetting.setType(500);
            httpSetting.setSavePath(fileGuider);
            httpSetting.setBreakpointTransmission(true);
            httpSetting.setIsExclusiveTask(true);
            if (OKLog.D) {
                OKLog.d(ApkDownloadController.TAG, apkResult.id + "--breakpoint--" + apkResult.currentSize);
            }
            httpSetting.setStartPosBreakpointTransmission(apkResult.currentSize);
            httpSetting.setAttempts(1);
            return httpSetting;
        }

        public ApkCenter.ApkListener getApkListener() {
            return this.apkListener;
        }

        public ApkResult getApkResult() {
            return this.apkResult;
        }

        public HttpRequest getHttpRequest() {
            return this.httpRequest;
        }

        public HttpSetting getHttpSetting() {
            return this.httpSetting;
        }

        public boolean isForce() {
            return this.isForce;
        }

        public void setApkListener(ApkCenter.ApkListener apkListener) {
            this.apkListener = apkListener;
        }

        public void setForce(boolean z) {
            this.isForce = z;
        }

        public void setHttpRequest(HttpRequest httpRequest) {
            this.httpRequest = httpRequest;
        }

        public void setHttpSetting(HttpSetting httpSetting) {
            this.httpSetting = httpSetting;
        }

        public DownLoadInfo(ApkResult apkResult, HttpSetting httpSetting, ApkCenter.ApkListener apkListener) {
            this.isForce = false;
            this.isFromProvidedPage = false;
            this.apkResult = apkResult;
            this.httpSetting = httpSetting;
            this.apkListener = apkListener;
            if (apkResult != null && apkResult.downloadType == 2) {
                this.isForce = true;
            } else {
                this.isForce = false;
            }
            if (httpSetting == null) {
                this.httpSetting = createHttpSetting(apkResult);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void constructDownloadQueue(List<ApkResult> list) {
        LinkedList linkedList = new LinkedList();
        for (ApkResult apkResult : list) {
            if (!isCompleted(apkResult)) {
                if (!TextUtils.isEmpty(apkResult.localPath)) {
                    apkResult.localPath = "";
                    apkResult.currentSize = 0;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(ApkDownloadTable.FIELD_LOCAL_PATH, apkResult.localPath);
                    contentValues.put(ApkDownloadTable.FIELD_FILE_NAME, apkResult.fileName);
                    contentValues.put(ApkDownloadTable.FIELD_CURRENT_SIZE, Integer.valueOf(apkResult.currentSize));
                    ApkDownloadTable.updateByMd5(apkResult.md5, contentValues);
                }
                int indexOf = AuraBundleInfos.getBundleDownloadOrder().indexOf(apkResult.id);
                if (indexOf >= 0) {
                    apkResult.downloadOrder = indexOf;
                }
                if (isBundleProvided(apkResult.id)) {
                    apkResult.bundleType = 2;
                }
                linkedList.offer(apkResult);
            }
        }
        Collections.sort(linkedList, new BundleOrderComparator());
        this.downloadQueue = new BundleDownloadQueue(linkedList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteOtherFiles(List<ApkResult> list) {
        boolean z;
        try {
            File file = new File(JdSdk.getInstance().getApplication().getFilesDir(), childDir);
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    Iterator<ApkResult> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = true;
                            break;
                        } else if (file2.getName().equals(it.next().fileName)) {
                            z = false;
                            break;
                        }
                    }
                    if (z) {
                        file2.delete();
                    }
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    private void downloadBundle(DownLoadInfo downLoadInfo) {
        if (OKLog.D) {
            OKLog.d(TAG, "--addDownLoad--" + downLoadInfo.getApkResult().id + "--" + downLoadInfo.getApkResult().fileName);
        }
        HttpRequest add = HttpGroupUtils.getHttpGroupaAsynPool().add(downLoadInfo.httpSetting);
        downLoadInfo.getApkResult().status = 2;
        downLoadInfo.setHttpRequest(add);
    }

    public static ApkDownloadController getInStance() {
        ApkDownloadController apkDownloadController;
        ApkDownloadController apkDownloadController2 = helper;
        if (apkDownloadController2 != null) {
            return apkDownloadController2;
        }
        synchronized (ApkDownloadController.class) {
            helper = new ApkDownloadController();
            AuraBundleConfig.getInstance().addObserver(new AuraBundleConfig.b() { // from class: com.jingdong.common.apkcenter.ApkDownloadController.1
                @Override // com.jingdong.jdsdk.auraSetting.AuraBundleConfig.b
                public void onChanged() {
                    ApkDownloadController.helper.registSharedPreferenceChangeListener();
                }
            });
            apkDownloadController = helper;
        }
        return apkDownloadController;
    }

    private List<ApkResult> getProvidedApkResult() {
        if (this.providedBundleInfos == null) {
            this.providedBundleInfos = AuraBundleConfig.getInstance().getProvidedBundleInfos();
        }
        List<Map<String, String>> list = this.providedBundleInfos;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map<String, String> map : this.providedBundleInfos) {
            ApkResult apkResult = new ApkResult();
            apkResult.id = AuraBundleInfos.getUpdateIdFromBundleName(map.get("bundleName"));
            apkResult.md5 = map.get("md5");
            apkResult.hostVersionName = PackageInfoUtil.getVersionName();
            apkResult.bundleVersionCode = Integer.parseInt(map.get("versionCode"));
            apkResult.fileName = apkResult.md5 + EXTENSION;
            apkResult.size = Integer.parseInt(map.get(ApkDownloadTable.FIELD_SIZE));
            String str = map.get("downloadUrl");
            apkResult.downloadUrl = str;
            apkResult.downloadType = 1;
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(apkResult);
            } else if (OKLog.D) {
                OKLog.d(TAG, "provided bundle" + apkResult.id + " has no downloadurl, do not add downloadlist");
            }
        }
        return arrayList;
    }

    private boolean isBundleProvided(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (this.providedBundleInfos == null) {
            this.providedBundleInfos = AuraBundleConfig.getInstance().getProvidedBundleInfos();
        }
        List<Map<String, String>> list = this.providedBundleInfos;
        if (list != null && list.size() > 0) {
            Iterator<Map<String, String>> it = this.providedBundleInfos.iterator();
            while (it.hasNext()) {
                if (str.equals(AuraBundleInfos.getUpdateIdFromBundleName(it.next().get("bundleName")))) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isSoAlreadyDownload(DownLoadInfo downLoadInfo) {
        try {
            File file = new File(JdSdk.getInstance().getApplication().getFilesDir(), childDir);
            if (file.exists() && file.isDirectory()) {
                ApkResult apkResult = downLoadInfo.getApkResult();
                File file2 = new File(file, apkResult.fileName);
                if (!file2.exists()) {
                    return false;
                }
                apkResult.localPath = file2.getAbsolutePath();
                if (isCompleted(apkResult)) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "apk already exist, Id:" + apkResult.id);
                    }
                    apkResult.status = 1;
                    apkResult.currentSize = apkResult.size;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(ApkDownloadTable.FIELD_LOCAL_PATH, apkResult.localPath);
                    contentValues.put(ApkDownloadTable.FIELD_FILE_NAME, apkResult.fileName);
                    contentValues.put(ApkDownloadTable.FIELD_CURRENT_SIZE, Integer.valueOf(apkResult.currentSize));
                    ApkDownloadTable.updateByMd5(apkResult.md5, contentValues);
                    if (downLoadInfo.getApkListener() != null) {
                        downLoadInfo.getApkListener().onFinish(apkResult);
                    }
                    return true;
                }
                apkResult.localPath = "";
            }
            return false;
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void nextDownload() {
        if (this.currentDownloadInfo != null) {
            OKLog.d(TAG, "=========currentDownloadInfo:" + this.currentDownloadInfo.getApkResult().id + LangUtils.SINGLE_SPACE + this.currentDownloadInfo.downloadStatus);
            if (this.currentDownloadInfo.downloadStatus == 1) {
                OKLog.d(TAG, "downloadQueue is running, wait next download");
                return;
            }
        } else {
            OKLog.d(TAG, "-----currentDownloadInfo is null");
        }
        BundleDownloadQueue bundleDownloadQueue = this.downloadQueue;
        if (bundleDownloadQueue == null) {
            OKLog.d(TAG, "downloadQueue is null, nothing to download");
            return;
        }
        ApkResult next = bundleDownloadQueue.next();
        if (next == null) {
            OKLog.d(TAG, "head is null, downloadQueue has reach end, downloadQueue size:" + this.downloadQueue.size() + " downloadQueue.getCursor:" + this.downloadQueue.getCursor());
            return;
        }
        OKLog.d(TAG, "downloadQueue head:" + next.id + " downloadQueue size:" + this.downloadQueue.size() + " downloadQueue.getCursor:" + this.downloadQueue.getCursor());
        DownLoadInfo downLoadInfo = new DownLoadInfo(this, next);
        if (downLoadInfo.getApkListener() == null && !TextUtils.isEmpty(downLoadInfo.getApkResult().id)) {
            downLoadInfo.setApkListener(this.listenerMap.get(downLoadInfo.getApkResult().id));
        }
        if (shouldDownload(downLoadInfo)) {
            if (OKLog.D) {
                OKLog.d(TAG, "--addDownLoad--" + downLoadInfo.getApkResult().id + "--" + downLoadInfo.getApkResult().fileName);
            }
            HttpRequest add = HttpGroupUtils.getHttpGroupaAsynPool().add(downLoadInfo.httpSetting);
            downLoadInfo.getApkResult().status = 2;
            downLoadInfo.setHttpRequest(add);
            this.currentDownloadInfo = downLoadInfo;
            downLoadInfo.downloadStatus = 1;
        } else {
            nextDownload();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<ApkResult> parseApklist(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList arrayList = new ArrayList();
        if (jSONArrayPoxy != null && jSONArrayPoxy.length() > 0) {
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                ApkResult apkResult = new ApkResult();
                JSONObjectProxy jSONObjectOrNull = jSONArrayPoxy.getJSONObjectOrNull(i2);
                if (jSONObjectOrNull != null && "hotfix".equals(jSONObjectOrNull.optString("apkName"))) {
                    if (OKLog.W) {
                        OKLog.w(TAG, "onEnd: this is hotfix config,do not use ApkDownloadController...");
                    }
                } else {
                    String optString = jSONObjectOrNull.optString(HybridSDK.APP_VERSION);
                    String versionName = PackageInfoUtil.getVersionName();
                    if (!TextUtils.isEmpty(optString) && (TextUtils.isEmpty(versionName) || optString.equals(versionName))) {
                        String optString2 = jSONObjectOrNull.optString("apksign");
                        apkResult.md5 = optString2;
                        apkResult.hostVersionCode = jSONObjectOrNull.optString("buildId");
                        apkResult.hostVersionName = optString;
                        apkResult.bundleVersionCode = jSONObjectOrNull.optInt("versionCode");
                        apkResult.fileName = optString2 + EXTENSION;
                        apkResult.id = jSONObjectOrNull.optString("apkName");
                        apkResult.size = jSONObjectOrNull.optInt("apksize");
                        apkResult.downloadUrl = jSONObjectOrNull.optString("apkurl").trim();
                        apkResult.downloadType = jSONObjectOrNull.optInt("downloadType", 1);
                        arrayList.add(apkResult);
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void registSharedPreferenceChangeListener() {
        SharedPreferences sharedPreferences = AuraBundleConfig.getInstance().getSharedPreferences();
        OKLog.d(TAG, "onSharedPreferenceChanged1");
        if (sharedPreferences != null) {
            OKLog.d(TAG, "onSharedPreferenceChanged2");
            sharedPreferences.registerOnSharedPreferenceChangeListener(this.sharedPreferenceChangeListener);
        }
    }

    private void setIsBreakRequest(boolean z) {
        this.isBreakRequest = z;
    }

    private boolean shouldDownload(DownLoadInfo downLoadInfo) {
        if (downLoadInfo != null && downLoadInfo.getApkResult() != null) {
            ApkResult apkResult = downLoadInfo.getApkResult();
            if (isSoAlreadyDownload(downLoadInfo)) {
                return false;
            }
            if (apkResult.downloadType == 2) {
                return true;
            }
            if (apkResult.bundleType == 2) {
                if (NetUtils.isWifi()) {
                    if (downLoadInfo.apkResult.id.equals(AuraBundleInfos.getUpdateIdFromBundleName(AuraBundleInfos.getBundleNameFromBundleId(86)))) {
                        return true;
                    }
                    boolean z = AuraBundleConfig.getInstance().getSharedPreferences().getBoolean("bundle_provided_tip_button", false);
                    this.isWifiDownloadProvided = z;
                    if (z) {
                        return true;
                    }
                    if (AuraBundleConfig.getInstance().getOriBundleVersionCode(AuraBundleInfos.getBundleNameFromUpdateID(apkResult.id)) < apkResult.bundleVersionCode) {
                        return true;
                    }
                }
            } else if (NetUtils.isWifi()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void addListenerMap(String str, ApkCenter.ApkListener apkListener) {
        this.listenerMap.put(str, apkListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void breakPointDownload() {
        OKLog.d(TAG, "breakPointDownload");
        if (this.isRequest) {
            return;
        }
        if (this.downloadQueue == null) {
            List<ApkResult> queryApks = ApkDownloadTable.queryApks();
            if (queryApks != null && queryApks.size() > 0) {
                constructDownloadQueue(queryApks);
            }
            setIsBreakRequest(true);
            CommonBase.putLongToPreference(SHARE_DATAVERSION, 0L);
        }
        if (this.downloadQueue != null) {
            OKLog.d(TAG, "breakPointDownload:downloadQueue.resetCusor");
            this.downloadQueue.resetCusor();
            nextDownload();
        }
        DownLoadInfo downLoadInfo = this.currentDownloadInfo;
        if (downLoadInfo != null) {
            if (downLoadInfo.getApkListener() == null && !TextUtils.isEmpty(this.currentDownloadInfo.getApkResult().id)) {
                DownLoadInfo downLoadInfo2 = this.currentDownloadInfo;
                downLoadInfo2.setApkListener(this.listenerMap.get(downLoadInfo2.getApkResult().id));
            }
            if (isCompleted(this.currentDownloadInfo.getApkResult())) {
                return;
            }
            if (this.currentDownloadInfo.getApkResult().status == 1) {
                this.currentDownloadInfo.getApkResult().status = 0;
                this.currentDownloadInfo.getApkResult().currentSize = 0;
            }
            if (OKLog.D) {
                OKLog.d(TAG, "--\u7f51\u7edc\u5207\u6362--apkId:" + this.currentDownloadInfo.getApkResult().id + " status:" + this.currentDownloadInfo.getApkResult().status);
            }
            int i2 = this.currentDownloadInfo.getApkResult().status;
            if (i2 == 0) {
                nextDownload();
            } else if (i2 != 1) {
                if (i2 == 2 && !NetUtils.isWifi() && !this.currentDownloadInfo.isForce() && this.currentDownloadInfo.getHttpRequest() != null) {
                    if (OKLog.D) {
                        OKLog.d(TAG, "--\u6267\u884c \u505c\u6b62\u4e0b\u8f7d--" + this.currentDownloadInfo.getApkResult().id);
                    }
                    stopCurrentDownload();
                }
            } else if (OKLog.D) {
                OKLog.d(TAG, "completed-->" + this.currentDownloadInfo.getApkResult().id + LangUtils.SINGLE_SPACE + this.currentDownloadInfo.getApkResult().md5);
            }
        }
    }

    public void cleanApkCenter() {
        try {
            File file = new File(JdSdk.getInstance().getApplication().getFilesDir(), childDir);
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    file2.delete();
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deleteBundleCache(String str) {
        List<ApkResult> quryApkById;
        try {
            if (!TextUtils.isEmpty(str) && (quryApkById = ApkDownloadTable.quryApkById(str)) != null && quryApkById.size() > 0) {
                File file = new File(JdSdk.getInstance().getApplication().getFilesDir(), childDir);
                for (ApkResult apkResult : quryApkById) {
                    File file2 = new File(file, apkResult.fileName);
                    if (file2.exists()) {
                        file2.delete();
                        if (OKLog.D) {
                            OKLog.d(TAG, "delete apkfile, Id:" + apkResult.id + ", filename:" + apkResult.fileName);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
    }

    public synchronized void downloadProvidedBundle(String str, ApkCenter.AuraBundleUpdateListener auraBundleUpdateListener) {
        downloadProvidedBundle(str, auraBundleUpdateListener, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insetProvidedBundleInfoToDB() {
        ApkDownloadTable.deleteWithoutThisClientVersion();
        List<ApkResult> providedApkResult = getProvidedApkResult();
        if (providedApkResult == null || providedApkResult.size() <= 0) {
            return;
        }
        for (ApkResult apkResult : providedApkResult) {
            List<ApkResult> quryApkById = ApkDownloadTable.quryApkById(apkResult.id);
            if (quryApkById != null && quryApkById.size() > 0) {
                if (quryApkById.get(0).bundleVersionCode < apkResult.bundleVersionCode) {
                    ApkDownloadTable.deleteById(quryApkById.get(0).id);
                    ApkDownloadTable.insertData(apkResult);
                } else if (quryApkById.get(0).bundleVersionCode == apkResult.bundleVersionCode && !quryApkById.get(0).md5.equals(apkResult.md5)) {
                    ApkDownloadTable.deleteById(quryApkById.get(0).id);
                    ApkDownloadTable.insertData(apkResult);
                }
            } else {
                ApkDownloadTable.insertData(apkResult);
            }
        }
    }

    public boolean isBreakRequest() {
        return this.isBreakRequest;
    }

    public boolean isCompleted(ApkResult apkResult) {
        if (apkResult == null) {
            if (OKLog.D) {
                OKLog.d(TAG, "--apk md5  apkResult is null--");
            }
            return false;
        } else if (TextUtils.isEmpty(apkResult.localPath)) {
            if (OKLog.D) {
                OKLog.d(TAG, apkResult.id + "--apk md5  localpath is null--");
            }
            return false;
        } else {
            String md5 = FileUtils.getMD5(apkResult.localPath);
            if (OKLog.D) {
                OKLog.d(TAG, apkResult.id + "--apk md5  path--" + apkResult.localPath);
                OKLog.d(TAG, apkResult.id + LangUtils.SINGLE_SPACE + apkResult.md5 + "--apk md5--" + md5);
            }
            return (TextUtils.isEmpty(md5) || TextUtils.isEmpty(apkResult.md5) || !md5.toLowerCase().equals(apkResult.md5.toLowerCase())) ? false : true;
        }
    }

    synchronized void removeFormQueue(ApkResult apkResult) {
        BundleDownloadQueue bundleDownloadQueue = this.downloadQueue;
        if (bundleDownloadQueue != null) {
            bundleDownloadQueue.remove(apkResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reqeustDownLoadList(final HttpGroup.OnCommonListener onCommonListener) {
        if (this.isRequest) {
            return;
        }
        this.isRequest = true;
        final SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        final long j2 = jdSharedPreferences.getLong(SHARE_DATAVERSION, 0L);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(FUNCTIONID);
        httpSetting.setCacheMode(2);
        httpSetting.setAttempts(2);
        httpSetting.putJsonParam(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION, j2 + "");
        httpSetting.putJsonParam(l4.f16791e, SDKUtils.getSDKVersion() + "");
        httpSetting.setHost(Configuration.getNgwHost());
        httpSetting.setListener(new HttpGroup.OnCommonListener() { // from class: com.jingdong.common.apkcenter.ApkDownloadController.3
            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
            public void onEnd(HttpResponse httpResponse) {
                JSONObjectProxy jSONObject;
                List parseApklist;
                HttpGroup.OnCommonListener onCommonListener2 = onCommonListener;
                if (onCommonListener2 != null) {
                    onCommonListener2.onEnd(httpResponse);
                }
                try {
                    try {
                        jSONObject = httpResponse.getJSONObject();
                        if (OKLog.D) {
                            OKLog.d(ApkDownloadController.TAG, jSONObject.toString());
                        }
                    } catch (Exception e2) {
                        if (OKLog.E) {
                            OKLog.e(ApkDownloadController.TAG, e2);
                        }
                    }
                    if (jSONObject != null) {
                        String optString = jSONObject.optString("code");
                        if (!"0".equals(optString)) {
                            JDMtaUtils.sendCommonData(JdSdk.getInstance().getApplication().getApplicationContext(), "APKDownlad_Code_Status", optString, "", "", "", "", "");
                        } else {
                            long optLong = jSONObject.optLong(NavigationDbConstants.TB_COLUMN_FREQUENCY_RULE_DATAVERSION);
                            if (optLong != j2 && (parseApklist = ApkDownloadController.this.parseApklist(jSONObject.getJSONArrayOrNull(ApkDownloadController.FUNCTIONID))) != null && parseApklist.size() > 0 && ApkDownloadTable.insertDatas(parseApklist)) {
                                jdSharedPreferences.edit().putLong(ApkDownloadController.SHARE_DATAVERSION, optLong).commit();
                                synchronized (ApkDownloadController.this) {
                                    if (ApkDownloadController.this.currentDownloadInfo != null) {
                                        ApkDownloadController.this.currentDownloadInfo.setApkListener(new ApkCenter.ApkListener() { // from class: com.jingdong.common.apkcenter.ApkDownloadController.3.1
                                            @Override // com.jingdong.common.apkcenter.ApkCenter.ApkListener
                                            public void onDownloadProgressChanged(int i2) {
                                            }

                                            @Override // com.jingdong.common.apkcenter.ApkCenter.ApkListener
                                            public void onFailure(String str) {
                                            }

                                            @Override // com.jingdong.common.apkcenter.ApkCenter.ApkListener
                                            public void onFinish(ApkResult apkResult) {
                                            }
                                        });
                                        if (ApkDownloadController.this.currentDownloadInfo.getApkResult().status == 2) {
                                            ApkDownloadController.this.currentDownloadInfo.getHttpRequest().stop();
                                        }
                                        ApkDownloadController.this.currentDownloadInfo = null;
                                    }
                                    ApkDownloadController.this.deleteOtherFiles(parseApklist);
                                    ApkDownloadController.this.constructDownloadQueue(parseApklist);
                                    ApkDownloadController.this.nextDownload();
                                }
                            }
                        }
                    }
                } finally {
                    ApkDownloadController.this.isRequest = false;
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
            public void onError(HttpError httpError) {
                ApkDownloadController.this.isRequest = false;
                if (OKLog.D) {
                    OKLog.d(ApkDownloadController.TAG, httpError.toString());
                }
                HttpGroup.OnCommonListener onCommonListener2 = onCommonListener;
                if (onCommonListener2 != null) {
                    onCommonListener2.onError(httpError);
                }
            }

            @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
            public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
                HttpGroup.OnCommonListener onCommonListener2 = onCommonListener;
                if (onCommonListener2 != null) {
                    onCommonListener2.onReady(httpSettingParams);
                }
            }
        });
        HttpGroupUtils.getHttpGroupaAsynPool().add(httpSetting);
    }

    public synchronized void stopCurrentDownload() {
        DownLoadInfo downLoadInfo = this.currentDownloadInfo;
        if (downLoadInfo != null && downLoadInfo.getHttpRequest() != null) {
            this.currentDownloadInfo.getHttpRequest().stop();
            DownLoadInfo downLoadInfo2 = this.currentDownloadInfo;
            downLoadInfo2.downloadStatus = 4;
            downLoadInfo2.getApkResult().status = 0;
        }
    }

    public synchronized void stopDownloadBundle(String str) {
        DownLoadInfo downLoadInfo = this.currentDownloadInfo;
        if (downLoadInfo != null && downLoadInfo.getApkResult().id.equals(str)) {
            stopCurrentDownload();
        }
        nextDownload();
    }

    public synchronized void downloadProvidedBundle(String str, ApkCenter.AuraBundleUpdateListener auraBundleUpdateListener, boolean z) {
        OKLog.d(TAG, "downloadProvidedBundle:isForce:" + z);
        if (this.downloadQueue != null) {
            OKLog.d(TAG, "downloadProvidedBundle:downloadQueue.resetCusor");
            this.downloadQueue.resetCusor();
        }
        DownLoadInfo downLoadInfo = this.currentDownloadInfo;
        if (downLoadInfo != null && downLoadInfo.getApkResult().id.equals(str)) {
            DownLoadInfo downLoadInfo2 = this.currentDownloadInfo;
            if (downLoadInfo2.downloadStatus == 1) {
                downLoadInfo2.isForce = z;
                if (this.currentDownloadInfo.getApkListener() == null) {
                    this.currentDownloadInfo.setApkListener(this.listenerMap.get(str));
                }
                this.currentDownloadInfo.getApkResult().setUpdateListener(auraBundleUpdateListener);
                if (isCompleted(this.currentDownloadInfo.getApkResult())) {
                    this.currentDownloadInfo.getApkListener().onFinish(this.currentDownloadInfo.getApkResult());
                }
                return;
            }
        }
        List<ApkResult> quryApkById = ApkDownloadTable.quryApkById(str);
        DownLoadInfo downLoadInfo3 = (quryApkById == null || quryApkById.size() <= 0) ? null : new DownLoadInfo(this, quryApkById.get(0));
        if (downLoadInfo3 != null && downLoadInfo3.getApkResult() != null) {
            downLoadInfo3.isForce = z;
            downLoadInfo3.isFromProvidedPage = true;
            if (downLoadInfo3.getApkListener() == null) {
                downLoadInfo3.setApkListener(this.listenerMap.get(str));
            }
            downLoadInfo3.getApkResult().setUpdateListener(auraBundleUpdateListener);
            if (isCompleted(downLoadInfo3.getApkResult())) {
                downLoadInfo3.getApkListener().onFinish(downLoadInfo3.getApkResult());
                auraBundleUpdateListener.onDownloadFinish();
            } else if (!NetUtils.isWifi() && !z) {
                OKLog.d(TAG, "downloadProvidedBundle: not fource, not wifi, return!");
                auraBundleUpdateListener.onDownloadFailure(null);
                return;
            } else {
                stopCurrentDownload();
                BundleDownloadQueue bundleDownloadQueue = this.downloadQueue;
                if (bundleDownloadQueue != null) {
                    bundleDownloadQueue.moveToPrev();
                }
                if (OKLog.D) {
                    OKLog.d(TAG, "--addDownLoad--" + downLoadInfo3.getApkResult().id + "--" + downLoadInfo3.getApkResult().fileName);
                }
                if (!TextUtils.isEmpty(downLoadInfo3.apkResult.localPath)) {
                    downLoadInfo3.apkResult.localPath = "";
                    downLoadInfo3.apkResult.currentSize = 0;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(ApkDownloadTable.FIELD_LOCAL_PATH, downLoadInfo3.apkResult.localPath);
                    contentValues.put(ApkDownloadTable.FIELD_FILE_NAME, downLoadInfo3.apkResult.fileName);
                    contentValues.put(ApkDownloadTable.FIELD_CURRENT_SIZE, Integer.valueOf(downLoadInfo3.apkResult.currentSize));
                    ApkDownloadTable.updateByMd5(downLoadInfo3.apkResult.md5, contentValues);
                }
                downLoadInfo3.httpSetting.setStartPosBreakpointTransmission(downLoadInfo3.apkResult.currentSize);
                HttpRequest add = HttpGroupUtils.getHttpGroupaAsynPool().add(downLoadInfo3.httpSetting);
                downLoadInfo3.getApkResult().status = 2;
                downLoadInfo3.setHttpRequest(add);
                this.currentDownloadInfo = downLoadInfo3;
            }
            return;
        }
        auraBundleUpdateListener.onDownloadFailure(null);
    }
}
