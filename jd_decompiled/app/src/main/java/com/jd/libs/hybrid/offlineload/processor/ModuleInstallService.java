package com.jd.libs.hybrid.offlineload.processor;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.db.OfflineDataStore;
import com.jd.libs.hybrid.offlineload.entity.OfflineModule;
import com.jd.libs.hybrid.offlineload.loader.RetryFailInfo;
import com.jd.libs.hybrid.offlineload.processor.ModuleDownloadService;
import com.jd.libs.hybrid.offlineload.processor.ModuleInstallService;
import com.jd.libs.hybrid.offlineload.processor.ModuleUnzipProcessor;
import com.jd.libs.hybrid.offlineload.utils.ModuleHelper;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes16.dex */
public class ModuleInstallService {
    private static final Map<String, List<ProcessCallback>> a = new ConcurrentHashMap();

    /* renamed from: com.jd.libs.hybrid.offlineload.processor.ModuleInstallService$2 */
    /* loaded from: classes16.dex */
    public class AnonymousClass2 implements ModuleDownloadService.ProcessCallback {
        final /* synthetic */ ProcessCallback a;

        AnonymousClass2(ProcessCallback processCallback) {
            this.a = processCallback;
        }

        public static /* synthetic */ void a(OfflineModule offlineModule, ProcessCallback processCallback) {
            OfflineModule offlineModule2 = (OfflineModule) OfflineDataStore.getInstance().get(offlineModule.getAppid());
            if (offlineModule2 != null && offlineModule2.getUnzipFile() != null && offlineModule.getUnzipFile().getPath().equals(offlineModule2.getUnzipFile().getPath())) {
                offlineModule.setOldUnzipFile(offlineModule2.getUnzipFile());
                offlineModule.setOldZipFile(offlineModule2.getZipFile());
            }
            String path = offlineModule.getOldUnzipFile() != null ? offlineModule.getOldUnzipFile().getPath() : "";
            if (OfflineFileHelper.isFileInUsingState(path)) {
                Log.d("ModuleInstallService", "[Offline-file](install) Old files are being used in WebView, will delete them after WebView closed, id: " + offlineModule.getAppid());
                OfflineFileHelper.addOldFilesToBeDeleted(String.valueOf(path.hashCode()), path, offlineModule.getOldZipFile() != null ? offlineModule.getOldZipFile().getPath() : null);
            } else {
                OfflineFileHelper.deleteEntityOldFile(offlineModule);
            }
            ModuleHelper.saveModule(offlineModule);
            if (Log.isDebug()) {
                Log.xLogD("ModuleInstallService", "\u9879\u76ee(id:" + offlineModule.getAppid() + ", url:" + offlineModule.getOriginalUrl() + ")\u7684\u79bb\u7ebf\u6587\u4ef6\u5df2\u6210\u529f\u4e0b\u8f7d\u548c\u89e3\u538b\uff0c\u5df2\u53ef\u4f7f\u7528\u3002");
                StringBuilder sb = new StringBuilder();
                sb.append("[Offline-file](install) Download and update module config success for id: ");
                sb.append(offlineModule.getAppid());
                sb.append(", url: ");
                sb.append(offlineModule.getOriginalUrl());
                Log.d("ModuleInstallService", sb.toString());
            }
            if (processCallback != null) {
                processCallback.onProcessSuccess(offlineModule);
            }
        }

        @Override // com.jd.libs.hybrid.offlineload.processor.ModuleDownloadService.ProcessCallback
        public void onProcessFail(OfflineModule offlineModule) {
            if (Log.isDebug()) {
                Log.d("ModuleInstallService", "[Offline-file](install) Fail to download and update module config for id: " + offlineModule.getAppid() + ", url: " + offlineModule.getOriginalUrl());
            }
            ProcessCallback processCallback = this.a;
            if (processCallback != null) {
                processCallback.onProcessFail(offlineModule);
            }
        }

        @Override // com.jd.libs.hybrid.offlineload.processor.ModuleDownloadService.ProcessCallback
        public void onProcessSuccess(final OfflineModule offlineModule) {
            DatabaseExecutors databaseExecutors = DatabaseExecutors.getInstance();
            final ProcessCallback processCallback = this.a;
            databaseExecutors.runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.processor.e
                @Override // java.lang.Runnable
                public final void run() {
                    ModuleInstallService.AnonymousClass2.a(offlineModule, processCallback);
                }
            });
        }
    }

    /* loaded from: classes16.dex */
    public interface ProcessCallback {
        void onProcessFail(OfflineModule offlineModule);

        void onProcessSuccess(OfflineModule offlineModule);
    }

    private ModuleInstallService() {
    }

    private static boolean b(@NonNull String str, ProcessCallback processCallback) {
        boolean z;
        Map<String, List<ProcessCallback>> map = a;
        synchronized (map) {
            List<ProcessCallback> list = map.get(str);
            if (list != null) {
                z = false;
            } else {
                list = new LinkedList<>();
                map.put(str, list);
                z = true;
            }
            if (processCallback != null) {
                list.add(processCallback);
            }
        }
        return z;
    }

    public static /* synthetic */ void c(OfflineModule offlineModule, ProcessCallback processCallback) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(offlineModule);
        installModulesFromNet(linkedList, processCallback);
    }

    private static Collection<OfflineModule> d(Map<String, OfflineModule> map, int i2) {
        Map<String, OfflineModule> all = OfflineDataStore.getInstance().getAll();
        HashSet hashSet = new HashSet(map.values());
        if (all != null && !all.isEmpty()) {
            hashSet.addAll(all.values());
        }
        LinkedList<OfflineModule> linkedList = new LinkedList(hashSet);
        LinkedList linkedList2 = new LinkedList();
        Collections.sort(linkedList);
        int i3 = 0;
        for (OfflineModule offlineModule : linkedList) {
            i3++;
            if (i3 <= i2) {
                if (map.containsKey(offlineModule.getAppid())) {
                    if (!RetryFailInfo.hasInOverRetry(offlineModule)) {
                        linkedList2.add(offlineModule);
                        if (Log.isDebug()) {
                            Log.d("ModuleInstallService", "[Offline-file](install) Download: Need to download and its priority is high enough within max count(" + i2 + "), priority = " + offlineModule.getPriority() + ", id: " + offlineModule.getAppid() + ", url: " + offlineModule.getOriginalUrl());
                        }
                    } else if (Log.isDebug()) {
                        Log.d("ModuleInstallService", "[Offline-file](install) Download: Cannot download because it has exceed the max retry count, priority = " + offlineModule.getPriority() + ", id: " + offlineModule.getAppid() + ", url: " + offlineModule.getOriginalUrl());
                    }
                }
            } else if (offlineModule.isAvailable() && !offlineModule.hasBuildIn()) {
                OfflineFileHelper.deleteEntityFile(offlineModule);
                ModuleHelper.saveModule(offlineModule);
                if (Log.isDebug()) {
                    Log.d("ModuleInstallService", "[Offline-file](install) Download: Delete existed files because of exceeding max module count(" + i2 + "), priority = " + offlineModule.getPriority() + ", id: " + offlineModule.getAppid() + ", url: " + offlineModule.getOriginalUrl());
                }
            } else if (Log.isDebug()) {
                Log.d("ModuleInstallService", "[Offline-file](install) Download: Won't download because of exceeding max count(" + i2 + "), priority = " + offlineModule.getPriority() + ", id: " + offlineModule.getAppid() + ", url: " + offlineModule.getOriginalUrl());
            }
        }
        return linkedList2;
    }

    public static Collection<OfflineModule> getDownloadCollection(String str) {
        return null;
    }

    public static void installModule(final OfflineModule offlineModule, final ProcessCallback processCallback) {
        DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.processor.f
            @Override // java.lang.Runnable
            public final void run() {
                ModuleInstallService.c(offlineModule, processCallback);
            }
        });
    }

    @Deprecated
    public static void installModules(Map<String, OfflineModule> map, int i2, boolean z) {
        if (map == null || map.isEmpty()) {
            if (Log.isDebug()) {
                Log.d("ModuleInstallService", "[Offline-file](install) No module need to install (install list empty).");
            }
        } else if (1 == i2) {
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            for (OfflineModule offlineModule : map.values()) {
                if (!z && offlineModule.getNoAutoInstall() == 1) {
                    boolean z2 = false;
                    if (offlineModule.getUnzipFile() != null || offlineModule.getZipFile() != null) {
                        OfflineFileHelper.deleteEntityFile(offlineModule);
                        z2 = true;
                    }
                    linkedList2.add(offlineModule);
                    if (Log.isDebug()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("[Offline-file](install) Module requests install only when needed");
                        sb.append(z2 ? ", so delete its old files" : "");
                        sb.append(". id: ");
                        sb.append(offlineModule.getAppid());
                        sb.append(", name: ");
                        sb.append(offlineModule.getName());
                        sb.append(", url: ");
                        sb.append(offlineModule.getOriginalUrl());
                        Log.d("ModuleInstallService", sb.toString());
                    }
                } else {
                    linkedList.add(offlineModule);
                }
            }
            ModuleHelper.saveModules(linkedList2);
            installModulesFromBuildIn(linkedList, null);
        } else if (i2 == 0) {
            Collection<OfflineModule> d = d(map, HybridSettings.MAX_OFFLINE_PACK_COUNT);
            if (d.isEmpty()) {
                if (Log.isDebug()) {
                    Log.d("ModuleInstallService", "[Offline-file](install) No module need to install (low priority).");
                    return;
                }
                return;
            }
            installModulesFromNet(d, null);
        } else if (Log.isDebug()) {
            Log.e("ModuleInstallService", "Wrong config type when trying to install modules.");
        }
    }

    public static void installModulesFromBuildIn(Collection<OfflineModule> collection, ProcessCallback processCallback) {
        final String str;
        List<ProcessCallback> remove;
        Iterator<OfflineModule> it = collection.iterator();
        while (it.hasNext()) {
            final OfflineModule next = it.next();
            String appid = next != null ? next.getAppid() : null;
            if (TextUtils.isEmpty(appid)) {
                str = null;
            } else {
                str = appid + CartConstant.KEY_YB_INFO_LINK + next.getModuleCode() + CartConstant.KEY_YB_INFO_LINK + next.getExtendedVersion();
            }
            if (TextUtils.isEmpty(str)) {
                Log.e("ModuleInstallService", "[Offline-file](install) fail to create install task id, module id: " + appid);
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_NET, "installModulesFromBuildIn", (String) null, (String) null, "fail to create install task id, module id: " + appid);
                if (processCallback != null) {
                    processCallback.onProcessFail(next);
                }
            } else if (!b(str, processCallback)) {
                Log.d("ModuleInstallService", String.format("[Offline-file](install) task id(%s) exists, callback will be called after previous task is finished, module id: %s", str, appid));
                Log.xLogD("ModuleInstallService", "\u9879\u76ee(" + appid + ")\u540c\u7248\u672c\u7684\u5185\u7f6e\u79bb\u7ebf\u5305\u5df2\u7ecf\u5728\u89e3\u538b\u5b89\u88c5\u8fc7\u7a0b\u4e2d\uff0c\u65e0\u9700\u91cd\u590d\u89e3\u538b\u3002");
            } else {
                File copyBuildInZipFromAsset = OfflineFileHelper.copyBuildInZipFromAsset(next, OfflineFileHelper.getZipDir());
                if (copyBuildInZipFromAsset != null && copyBuildInZipFromAsset.exists()) {
                    Log.d("ModuleInstallService", "[Offline-file](install) copy zip from build-in successfully, ready to unzip, id: " + next.getAppid() + ", file at: " + copyBuildInZipFromAsset.getAbsolutePath());
                    String sourceDir = OfflineFileHelper.getSourceDir(OfflineFileHelper.generateSaveDirName(next.getAppid()));
                    ModuleUnzipProcessor moduleUnzipProcessor = new ModuleUnzipProcessor(next, copyBuildInZipFromAsset, next.getFileInfo() != null ? next.getFileInfo().getUrl() : "", false, com.jd.hybrid.downloader.c.e(copyBuildInZipFromAsset));
                    moduleUnzipProcessor.e(new ModuleUnzipProcessor.ProcessCallback<OfflineModule>() { // from class: com.jd.libs.hybrid.offlineload.processor.ModuleInstallService.1
                        @Override // com.jd.libs.hybrid.offlineload.processor.ModuleUnzipProcessor.ProcessCallback
                        public void onProcessFail(boolean z, boolean z2, Throwable th) {
                            List list;
                            if (Log.isDebug()) {
                                Log.e("ModuleInstallService", "[Offline-file](install) Fail to unzip build-in file, won't update db for it, id: " + next.getAppid());
                            }
                            synchronized (ModuleInstallService.a) {
                                list = (List) ModuleInstallService.a.remove(str);
                            }
                            if (list == null || list.isEmpty()) {
                                return;
                            }
                            Iterator it2 = list.iterator();
                            while (it2.hasNext()) {
                                ((ProcessCallback) it2.next()).onProcessFail(next);
                            }
                        }

                        @Override // com.jd.libs.hybrid.offlineload.processor.ModuleUnzipProcessor.ProcessCallback
                        public void onProcessSuccess(OfflineModule offlineModule) {
                            List list;
                            OfflineModule offlineModule2 = (OfflineModule) OfflineDataStore.getInstance().get(offlineModule.getAppid());
                            if (offlineModule2 != null && offlineModule2.getUnzipFile() != null && offlineModule.getUnzipFile().getPath().equals(offlineModule2.getUnzipFile().getPath())) {
                                offlineModule.setOldUnzipFile(offlineModule2.getOldUnzipFile());
                                offlineModule.setOldZipFile(offlineModule2.getOldZipFile());
                            }
                            String path = offlineModule.getOldUnzipFile() != null ? offlineModule.getOldUnzipFile().getPath() : "";
                            if (OfflineFileHelper.isFileInUsingState(path)) {
                                Log.d("ModuleInstallService", "[Offline-file](install) Old files are being used in WebView, will delete them after WebView closed, id: " + offlineModule.getAppid());
                                OfflineFileHelper.addOldFilesToBeDeleted(String.valueOf(path.hashCode()), path, offlineModule.getOldZipFile() != null ? offlineModule.getOldZipFile().getPath() : null);
                            } else {
                                OfflineFileHelper.deleteEntityOldFile(offlineModule);
                            }
                            ModuleHelper.saveModule(offlineModule);
                            if (Log.isDebug()) {
                                Log.xLogD("ModuleInstallService", "\u9879\u76ee(" + offlineModule.getAppid() + ")\u5185\u7f6e\u79bb\u7ebf\u5305\u89e3\u538b\u5b8c\u6bd5\uff0c\u5df2\u53ef\u7528\u3002");
                                Log.d("ModuleInstallService", "[Offline-file](install) Copy and unzip asset build-in file successfully for id: " + offlineModule.getAppid() + ", url: " + offlineModule.getOriginalUrl());
                            }
                            synchronized (ModuleInstallService.a) {
                                list = (List) ModuleInstallService.a.remove(str);
                            }
                            if (list == null || list.isEmpty()) {
                                return;
                            }
                            Iterator it2 = list.iterator();
                            while (it2.hasNext()) {
                                ((ProcessCallback) it2.next()).onProcessSuccess(offlineModule);
                            }
                        }
                    });
                    moduleUnzipProcessor.f(sourceDir);
                    moduleUnzipProcessor.processZipFile();
                } else {
                    String appid2 = next != null ? next.getAppid() : null;
                    Log.e("ModuleInstallService", "[Offline-file](install) fail to copy zip from build-in dir, id: " + appid2);
                    OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_NET, "BuildInCopyError", (String) null, (String) null, "zip file is null or not exist after copied, id: " + appid2);
                    Map<String, List<ProcessCallback>> map = a;
                    synchronized (map) {
                        remove = map.remove(str);
                    }
                    if (remove != null && !remove.isEmpty()) {
                        Iterator<ProcessCallback> it2 = remove.iterator();
                        while (it2.hasNext()) {
                            it2.next().onProcessFail(next);
                        }
                    }
                }
            }
        }
    }

    public static void installModulesFromNet(Collection<OfflineModule> collection, ProcessCallback processCallback) {
        ModuleDownloadService.download(collection, false, 0, (ModuleDownloadService.ProcessCallback) new AnonymousClass2(processCallback));
    }
}
