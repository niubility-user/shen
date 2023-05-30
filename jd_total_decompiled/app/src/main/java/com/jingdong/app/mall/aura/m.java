package com.jingdong.app.mall.aura;

import com.jingdong.aura.sdk.update.AuraBundleResult;
import com.jingdong.aura.sdk.update.downloader.IDownloadCallback;
import com.jingdong.aura.sdk.update.downloader.IDownloader;
import com.jingdong.common.network.HttpGroupUtils;
import com.jingdong.common.utils.FileUtils;
import com.jingdong.jdsdk.network.toolbox.FileGuider;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpRequest;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import com.jingdong.sdk.log.Log;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes19.dex */
public class m implements IDownloader {
    private ConcurrentHashMap<String, HttpRequest> a = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes19.dex */
    public class a implements HttpGroup.OnAllAndPauseListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ AuraBundleResult f7944g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ IDownloadCallback f7945h;

        a(m mVar, AuraBundleResult auraBundleResult, IDownloadCallback iDownloadCallback) {
            this.f7944g = auraBundleResult;
            this.f7945h = iDownloadCallback;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            String path = httpResponse.getSaveFile().getPath();
            String headerField = httpResponse.getHeaderField(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_FILENAME);
            Log.d("BundleDownloder", "onEnd apkResult:  apkId:" + this.f7944g.updateId + " localPath:" + path + " fileName:" + headerField + " newFilePath:" + FileUtils.renameFile(path, headerField));
            IDownloadCallback iDownloadCallback = this.f7945h;
            if (iDownloadCallback != null) {
                iDownloadCallback.onFinish();
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            httpError.printStackTrace();
            Log.d("BundleDownloder", this.f7944g.updateId + " onError");
            IDownloadCallback iDownloadCallback = this.f7945h;
            if (iDownloadCallback != null) {
                iDownloadCallback.onError(httpError);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnPauseListener
        public void onPause() {
            Log.d("BundleDownloder", this.f7944g.updateId + " onPause");
            IDownloadCallback iDownloadCallback = this.f7945h;
            if (iDownloadCallback != null) {
                iDownloadCallback.onCanceled();
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnProgressListener
        public void onProgress(int i2, int i3) {
            Log.d("BundleDownloder", this.f7944g.updateId + " onProgress:" + i3);
            IDownloadCallback iDownloadCallback = this.f7945h;
            if (iDownloadCallback != null) {
                iDownloadCallback.onProgress(i3);
            }
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnStartListener
        public void onStart() {
            Log.d("BundleDownloder", this.f7944g.updateId + " onStart");
            IDownloadCallback iDownloadCallback = this.f7945h;
            if (iDownloadCallback != null) {
                iDownloadCallback.onStart();
            }
        }
    }

    public HttpSetting a(AuraBundleResult auraBundleResult, IDownloadCallback iDownloadCallback) {
        FileGuider fileGuider = new FileGuider();
        fileGuider.setSpace(1);
        fileGuider.setChildDirName("apkcenter");
        fileGuider.setImmutable(false);
        fileGuider.setFileName(auraBundleResult.downloadedFileName);
        fileGuider.setMode(1);
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setUrl(auraBundleResult.downloadUrl);
        httpSetting.setCacheMode(2);
        httpSetting.setListener(new a(this, auraBundleResult, iDownloadCallback));
        httpSetting.setType(500);
        httpSetting.setSavePath(fileGuider);
        httpSetting.setBreakpointTransmission(true);
        httpSetting.setIsExclusiveTask(true);
        Log.d("BundleDownloder", auraBundleResult.updateId + "--breakpoint--" + auraBundleResult.downloadedSize);
        httpSetting.setStartPosBreakpointTransmission((int) auraBundleResult.downloadedSize);
        httpSetting.setAttempts(1);
        return httpSetting;
    }

    @Override // com.jingdong.aura.sdk.update.downloader.IDownloader
    public boolean cancelAll(List<AuraBundleResult> list) {
        Iterator<HttpRequest> it = this.a.values().iterator();
        while (it.hasNext()) {
            it.next().stop();
        }
        this.a.clear();
        return false;
    }

    @Override // com.jingdong.aura.sdk.update.downloader.IDownloader
    public boolean cancelBySign(AuraBundleResult auraBundleResult) {
        HttpRequest httpRequest = this.a.get(auraBundleResult.updateId);
        if (httpRequest != null) {
            httpRequest.stop();
            this.a.remove(auraBundleResult.updateId);
            return true;
        }
        return true;
    }

    @Override // com.jingdong.aura.sdk.update.downloader.IDownloader
    public void download(AuraBundleResult auraBundleResult, IDownloadCallback iDownloadCallback) {
        if (auraBundleResult == null) {
            Log.e("BundleDownloder", "bundleResult is null");
            if (iDownloadCallback != null) {
                iDownloadCallback.onError(new IllegalArgumentException("bundleResult is null"));
                return;
            }
            return;
        }
        this.a.put(auraBundleResult.updateId, HttpGroupUtils.getHttpGroupaAsynPool().add(a(auraBundleResult, iDownloadCallback)));
    }
}
