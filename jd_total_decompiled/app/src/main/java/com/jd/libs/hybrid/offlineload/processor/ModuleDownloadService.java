package com.jd.libs.hybrid.offlineload.processor;

import android.os.SystemClock;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.hybrid.downloader.FileError;
import com.jd.hybrid.downloader.FileResponse;
import com.jd.hybrid.downloader.c;
import com.jd.hybrid.downloader.p.a;
import com.jd.libs.hybrid.HybridSDK;
import com.jd.libs.hybrid.base.HybridBase;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.db.BuildInDataStore;
import com.jd.libs.hybrid.offlineload.entity.FileDetail;
import com.jd.libs.hybrid.offlineload.entity.OfflineEntityInfo;
import com.jd.libs.hybrid.offlineload.entity.OfflineModule;
import com.jd.libs.hybrid.offlineload.loader.RetryFailInfo;
import com.jd.libs.hybrid.offlineload.processor.ModuleDownloadService;
import com.jd.libs.hybrid.offlineload.processor.ModuleUnzipProcessor;
import com.jd.libs.hybrid.offlineload.temp.DownloadFileDisable;
import com.jd.libs.hybrid.offlineload.utils.GraySwitch;
import com.jd.libs.hybrid.offlineload.utils.ModuleHelper;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import com.jd.libs.xwin.http.BreakPointHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes16.dex */
public class ModuleDownloadService {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public static class ModuleDownloadCallback extends com.jd.hybrid.downloader.b {
        private final OfflineModule a;
        private final int b;

        /* renamed from: c  reason: collision with root package name */
        private final boolean f6087c;
        private final String d;

        /* renamed from: e  reason: collision with root package name */
        private long f6088e;

        /* renamed from: f  reason: collision with root package name */
        private Map<String, Object> f6089f;

        /* renamed from: g  reason: collision with root package name */
        private ProcessCallback f6090g;

        ModuleDownloadCallback(OfflineModule offlineModule, String str, boolean z, int i2, ProcessCallback processCallback) {
            this.a = offlineModule;
            this.b = i2;
            this.f6087c = z;
            this.d = str;
            this.f6090g = processCallback;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: h  reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void i(final float f2, File file) {
            ModuleUnzipProcessor.ProcessCallback<OfflineModule> processCallback = new ModuleUnzipProcessor.ProcessCallback<OfflineModule>() { // from class: com.jd.libs.hybrid.offlineload.processor.ModuleDownloadService.ModuleDownloadCallback.1
                @Override // com.jd.libs.hybrid.offlineload.processor.ModuleUnzipProcessor.ProcessCallback
                public void onProcessFail(boolean z, boolean z2, Throwable th) {
                    if (!z) {
                        RetryFailInfo.addToOverRetry(ModuleDownloadCallback.this.a);
                        if (ModuleDownloadCallback.this.f6090g != null) {
                            ModuleDownloadCallback.this.f6090g.onProcessFail(ModuleDownloadCallback.this.a);
                        }
                    } else if (z2) {
                        ModuleDownloadCallback.this.j();
                    } else if (ModuleDownloadCallback.this.f6090g != null) {
                        ModuleDownloadCallback.this.f6090g.onProcessFail(ModuleDownloadCallback.this.a);
                    }
                }

                @Override // com.jd.libs.hybrid.offlineload.processor.ModuleUnzipProcessor.ProcessCallback
                public void onProcessSuccess(OfflineModule offlineModule) {
                    if (ModuleDownloadCallback.this.f6090g != null) {
                        ModuleDownloadCallback.this.f6090g.onProcessSuccess(offlineModule);
                    }
                    a.C0087a c0087a = new a.C0087a();
                    c0087a.a = offlineModule.getAppid();
                    c0087a.b = f2;
                    c0087a.f2701f = "0";
                    c0087a.f2702g = 1;
                    c0087a.f2703h = ModuleDownloadCallback.this.d;
                    c0087a.f2700e = ModuleDownloadCallback.this.f6087c;
                    c0087a.f2704i = SystemClock.elapsedRealtime() - ModuleDownloadCallback.this.f6088e;
                    c0087a.f2705j = offlineModule.getModuleCode();
                    c0087a.f2706k = offlineModule.getFileInfo() != null ? offlineModule.getFileInfo().getVersionCode() : 0;
                    c0087a.f2707l = ModuleDownloadCallback.this.f6089f != null ? ModuleDownloadCallback.this.f6089f.toString() : null;
                    com.jd.hybrid.downloader.p.a.b(c0087a);
                }
            };
            String sourceDir = OfflineFileHelper.getSourceDir(OfflineFileHelper.generateSaveDirName(this.a.getAppid()));
            ModuleUnzipProcessor moduleUnzipProcessor = new ModuleUnzipProcessor(this.a, file, this.d, this.f6087c, f2);
            moduleUnzipProcessor.e(processCallback);
            moduleUnzipProcessor.f(sourceDir);
            moduleUnzipProcessor.processZipFile();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void j() {
            if (this.f6087c) {
                Log.xLogDForDev("ModuleDownloadService", "\u5373\u5c06\u91cd\u8bd5\u4e0b\u8f7d\uff0cid: " + this.a.getAppid());
                ModuleDownloadService.download(this.a, true, 0, this.f6090g);
            } else if (this.b < HybridSettings.HYBRID_DOWNLOAD_RETRY) {
                Log.d("ModuleDownloadService", "[Offline-file](download) Retry to download. id: " + this.a.getAppid());
                Log.xLogDForDev("ModuleDownloadService", "\u5373\u5c06\u91cd\u8bd5\u4e0b\u8f7d\uff0cid: " + this.a.getAppid());
                ModuleDownloadService.download(this.a, true, this.b + 1, this.f6090g);
                RetryFailInfo.removeOverRetry(this.a);
            } else {
                RetryFailInfo.addToOverRetry(this.a);
                ProcessCallback processCallback = this.f6090g;
                if (processCallback != null) {
                    processCallback.onProcessFail(this.a);
                }
            }
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onEnd(FileResponse<File> fileResponse) {
            final File data = fileResponse.getData();
            final float e2 = com.jd.hybrid.downloader.c.e(data);
            DatabaseExecutors.getInstance().runOnIoThread(new Runnable() { // from class: com.jd.libs.hybrid.offlineload.processor.d
                @Override // java.lang.Runnable
                public final void run() {
                    ModuleDownloadService.ModuleDownloadCallback.this.i(e2, data);
                }
            });
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onError(FileError fileError) {
            String str;
            a.C0087a c0087a = new a.C0087a();
            c0087a.a = this.a.getAppid();
            c0087a.f2702g = 1;
            c0087a.f2703h = this.d;
            c0087a.f2700e = this.f6087c;
            Map<String, Object> map = this.f6089f;
            c0087a.f2707l = map != null ? map.toString() : null;
            if (fileError instanceof c.C0086c) {
                c0087a.b = ((c.C0086c) fileError).fileSizeInKB;
                c0087a.f2701f = "-2";
                OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_CHECK, "downloadCallback-onError", this.a.getAppid(), this.d, fileError.getMessage());
                str = "\u6587\u4ef6\u6821\u9a8c\u9519\u8bef\uff0c" + fileError.getMessage();
            } else {
                c0087a.f2699c = (this.b != 0 || HybridSettings.HYBRID_DOWNLOAD_RETRY < 2) ? "-1" : "-2";
                c0087a.f2701f = "";
                OfflineExceptionUtils.reportDownloadError(fileError.getStatusCode(), OfflineExceptionUtils.ERR_MSG_NET, "downloadCallback-onError", this.a.getAppid(), this.d, fileError.getMessage());
                str = "\u7f51\u7edc\u9519\u8bef\uff0c" + fileError.getMessage();
            }
            com.jd.hybrid.downloader.p.a.b(c0087a);
            if (Log.isDebug()) {
                Log.xLogE("ModuleDownloadService", "\u9879\u76ee(id:" + this.a.getAppid() + ", url:" + this.a.getOriginalUrl() + ")\u7684\u79bb\u7ebf\u6587\u4ef6\u4e0b\u8f7d\u5931\u8d25\uff0c\u539f\u56e0\uff1a" + str);
            }
            j();
        }

        @Override // com.jd.hybrid.downloader.b, com.jd.hybrid.downloader.e
        public void onStart() {
            super.onStart();
            this.f6088e = SystemClock.elapsedRealtime();
        }

        public void setExtraData(Map<String, Object> map) {
            this.f6089f = map;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public interface ProcessCallback {
        void onProcessFail(OfflineModule offlineModule);

        void onProcessSuccess(OfflineModule offlineModule);
    }

    public static void download(OfflineModule offlineModule, boolean z, int i2, ProcessCallback processCallback) {
        download(Collections.singletonList(offlineModule), z, i2, processCallback);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0293  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void download(Collection<OfflineModule> collection, boolean z, int i2, ProcessCallback processCallback) {
        int i3;
        int i4;
        OfflineEntityInfo fileInfo;
        String generateFileName;
        int i5;
        int i6;
        OfflineModule offlineModule;
        if (DownloadFileDisable.offlineDownloadDisable) {
            Log.d("ModuleDownloadService", "Downloading offline file function is disable by switch.");
            Log.xLogDForDev("ModuleDownloadService", "\u4e0b\u8f7d\u7ebf\u4e0a\u79bb\u7ebf\u5305\u529f\u80fd\u5df2\u5173\u95ed\uff0c\u53ea\u66f4\u65b0\u914d\u7f6e\uff0c\u4e0d\u4e0b\u8f7d\u65b0\u6587\u4ef6\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458");
            return;
        }
        com.jd.hybrid.downloader.c f2 = com.jd.hybrid.downloader.c.f();
        if (f2 == null) {
            return;
        }
        List<com.jd.hybrid.downloader.d> arrayList = new ArrayList<>(collection.size());
        String str = null;
        BreakPointHelper init = BreakPointHelper.breakPointSwitch ? BreakPointHelper.getInstance().init(HybridSettings.getAppContext()) : null;
        Iterator<OfflineModule> it = collection.iterator();
        StringBuilder sb = null;
        while (it.hasNext()) {
            OfflineModule next = it.next();
            if (next.isDownloadDegraded()) {
                if (Log.isDebug()) {
                    Log.d("ModuleDownloadService", "[Offline-file](download) Module(" + next.getAppid() + ") is download degrade, skip downloading this one.");
                    Log.xLogDForDev("ModuleDownloadService", "\u9879\u76ee(id:" + next.getAppid() + ", url:" + next.getOriginalUrl() + ")\u914d\u7f6e\u4e86\u6682\u4e0d\u4e0b\u8f7d\u8bbe\u7f6e\uff0c\u5c06\u5ffd\u7565\u6b64\u6b21\u4e0b\u8f7d\u8bf7\u6c42\u3002");
                }
            } else {
                int i7 = -1;
                if (z) {
                    i3 = -1;
                } else {
                    FileDetail zipFile = next.getZipFile();
                    if (zipFile != null && !zipFile.hasChanged()) {
                        int versionCode = zipFile.getVersionCode();
                        if (next.isPatchOf(versionCode)) {
                            i7 = versionCode;
                            i3 = i7;
                        } else {
                            i3 = versionCode;
                        }
                    } else {
                        if (!GraySwitch.fixBuildInPatch || (offlineModule = BuildInDataStore.getInstance().get(next.getAppid())) == null) {
                            i5 = -1;
                            i6 = -1;
                        } else {
                            i5 = offlineModule.getFileInfo().getVersionCode();
                            if (next.isPatchOf(i5)) {
                                File copyBuildInZipFromAsset = OfflineFileHelper.copyBuildInZipFromAsset(next, OfflineFileHelper.getZipDir());
                                File unzipFirstForBuildIn = new ModuleUnzipProcessor(offlineModule, copyBuildInZipFromAsset, offlineModule.getFileInfo() != null ? offlineModule.getFileInfo().getUrl() : "", false, com.jd.hybrid.downloader.c.e(copyBuildInZipFromAsset)).unzipFirstForBuildIn();
                                if (unzipFirstForBuildIn != null && unzipFirstForBuildIn.exists()) {
                                    next.setZipFile(new FileDetail(unzipFirstForBuildIn, i5));
                                    ModuleHelper.saveModule(next);
                                    i6 = i5;
                                } else {
                                    Log.e("ModuleDownloadService", "[Offline-file](download) fail to copy/unzip zip from build-in dir, id: " + next.getAppid());
                                    OfflineExceptionUtils.reportDownloadError(OfflineExceptionUtils.ERR_MSG_NET, "download#copyBeforeDownload", next.getAppid(), str, "build-in zip file is null or not exist after copied and unzip");
                                }
                            }
                            i6 = i5;
                            i5 = -1;
                        }
                        i7 = i5;
                        i4 = i6;
                        i3 = -1;
                        fileInfo = next.getFileInfo();
                        boolean z2 = i7 < 0;
                        boolean z3 = z2 && !(fileInfo == null && fileInfo.getFileType().equalsIgnoreCase(OfflineEntityInfo.FILE_TYPE_ZIP2));
                        if (!GraySwitch.zipNewNoUse && !DYConstants.DY_NULL_STR.equals(fileInfo.getFileUrlZip()) && !TextUtils.isEmpty(fileInfo.getFileUrlZip()) && !DYConstants.DY_NULL_STR.equals(fileInfo.getFileZipMd5()) && !TextUtils.isEmpty(fileInfo.getFileZipMd5())) {
                            fileInfo.setUseZip(true);
                        }
                        String patchUrl = !z2 ? next.getPatchUrl(i7) : fileInfo.getUrl();
                        if (init == null) {
                            String filePath = init.getFilePath(next.getAppid(), patchUrl);
                            generateFileName = TextUtils.isEmpty(filePath) ? OfflineFileHelper.generateFileName(patchUrl) : OfflineFileHelper.getFileNameFromPath(filePath);
                        } else {
                            generateFileName = OfflineFileHelper.generateFileName(patchUrl);
                        }
                        com.jd.hybrid.downloader.d dVar = new com.jd.hybrid.downloader.d("\u9879\u76ee(" + next.getAppid() + ")\u79bb\u7ebf\u6587\u4ef6\u4e0b\u8f7d", patchUrl, OfflineFileHelper.getZipRelativeDir(), generateFileName, false, (int) (next.getPriority() * 1000.0f), z3);
                        dVar.n(next.getAppid());
                        dVar.q(0);
                        dVar.m(new com.jd.hybrid.downloader.n.b(next.getFileInfo().getMd5()));
                        BreakPointHelper breakPointHelper = init;
                        Iterator<OfflineModule> it2 = it;
                        ModuleDownloadCallback moduleDownloadCallback = new ModuleDownloadCallback(next, patchUrl, z2, i2, processCallback);
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        linkedHashMap.put("buildIn", Integer.valueOf(i4));
                        linkedHashMap.put("oldZipVer", Integer.valueOf(i3));
                        linkedHashMap.put("fix", Integer.valueOf(GraySwitch.fixBuildInPatch ? 1 : 0));
                        linkedHashMap.put("noBuildIn", HybridBase.getInstance().getSetting(HybridSDK.SWITCH_CLEAR_BUILD_IN));
                        linkedHashMap.put("buildInNum", Integer.valueOf(BuildInDataStore.getInstance().size()));
                        if (!TextUtils.isEmpty(next.downloadFrom)) {
                            linkedHashMap.put("dFrom", next.downloadFrom);
                        }
                        moduleDownloadCallback.setExtraData(linkedHashMap);
                        dVar.l(moduleDownloadCallback);
                        arrayList.add(dVar);
                        if (Log.isDebug()) {
                            if (sb == null) {
                                sb = new StringBuilder();
                            } else {
                                sb.setLength(0);
                            }
                            sb.append("\u9879\u76ee(id:");
                            sb.append(next.getAppid());
                            sb.append("\uff0curl:");
                            sb.append(next.getOriginalUrl());
                            sb.append(")\u9700\u4e0b\u8f7d/\u66f4\u65b0\u79bb\u7ebf\u6587\u4ef6\uff0c\u5df2\u52a0\u5165\u4e0b\u8f7d\u5217\u8868\uff0c\u4e0b\u8f7d\u5730\u5740:");
                            sb.append(patchUrl);
                            sb.append("\uff0c\u8bf7\u7b49\u5f85\u4e0b\u8f7d\u5b8c\u6bd5\u540e\u4f7f\u7528\u3002");
                            Log.xLogD("ModuleDownloadService", sb.toString());
                            Log.d(sb.toString());
                        }
                        it = it2;
                        init = breakPointHelper;
                        str = null;
                    }
                }
                i4 = -1;
                fileInfo = next.getFileInfo();
                if (i7 < 0) {
                }
                if (z2) {
                }
                if (!GraySwitch.zipNewNoUse) {
                    fileInfo.setUseZip(true);
                }
                if (!z2) {
                }
                if (init == null) {
                }
                com.jd.hybrid.downloader.d dVar2 = new com.jd.hybrid.downloader.d("\u9879\u76ee(" + next.getAppid() + ")\u79bb\u7ebf\u6587\u4ef6\u4e0b\u8f7d", patchUrl, OfflineFileHelper.getZipRelativeDir(), generateFileName, false, (int) (next.getPriority() * 1000.0f), z3);
                dVar2.n(next.getAppid());
                dVar2.q(0);
                dVar2.m(new com.jd.hybrid.downloader.n.b(next.getFileInfo().getMd5()));
                BreakPointHelper breakPointHelper2 = init;
                Iterator<OfflineModule> it22 = it;
                ModuleDownloadCallback moduleDownloadCallback2 = new ModuleDownloadCallback(next, patchUrl, z2, i2, processCallback);
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                linkedHashMap2.put("buildIn", Integer.valueOf(i4));
                linkedHashMap2.put("oldZipVer", Integer.valueOf(i3));
                linkedHashMap2.put("fix", Integer.valueOf(GraySwitch.fixBuildInPatch ? 1 : 0));
                linkedHashMap2.put("noBuildIn", HybridBase.getInstance().getSetting(HybridSDK.SWITCH_CLEAR_BUILD_IN));
                linkedHashMap2.put("buildInNum", Integer.valueOf(BuildInDataStore.getInstance().size()));
                if (!TextUtils.isEmpty(next.downloadFrom)) {
                }
                moduleDownloadCallback2.setExtraData(linkedHashMap2);
                dVar2.l(moduleDownloadCallback2);
                arrayList.add(dVar2);
                if (Log.isDebug()) {
                }
                it = it22;
                init = breakPointHelper2;
                str = null;
            }
        }
        f2.c(arrayList, true);
    }
}
