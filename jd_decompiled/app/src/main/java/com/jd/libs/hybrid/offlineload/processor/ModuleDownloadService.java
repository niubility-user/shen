package com.jd.libs.hybrid.offlineload.processor;

import android.os.SystemClock;
import com.jd.hybrid.downloader.FileError;
import com.jd.hybrid.downloader.FileResponse;
import com.jd.hybrid.downloader.c;
import com.jd.hybrid.downloader.p.a;
import com.jd.libs.hybrid.base.HybridSettings;
import com.jd.libs.hybrid.base.util.DatabaseExecutors;
import com.jd.libs.hybrid.base.util.Log;
import com.jd.libs.hybrid.offlineload.entity.OfflineModule;
import com.jd.libs.hybrid.offlineload.loader.RetryFailInfo;
import com.jd.libs.hybrid.offlineload.processor.ModuleDownloadService;
import com.jd.libs.hybrid.offlineload.processor.ModuleUnzipProcessor;
import com.jd.libs.hybrid.offlineload.utils.OfflineExceptionUtils;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileHelper;
import java.io.File;
import java.util.Collections;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void download(java.util.Collection<com.jd.libs.hybrid.offlineload.entity.OfflineModule> r26, boolean r27, int r28, com.jd.libs.hybrid.offlineload.processor.ModuleDownloadService.ProcessCallback r29) {
        /*
            Method dump skipped, instructions count: 730
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.libs.hybrid.offlineload.processor.ModuleDownloadService.download(java.util.Collection, boolean, int, com.jd.libs.hybrid.offlineload.processor.ModuleDownloadService$ProcessCallback):void");
    }
}
