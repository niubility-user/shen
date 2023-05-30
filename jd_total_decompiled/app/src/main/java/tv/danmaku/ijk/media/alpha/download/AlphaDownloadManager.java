package tv.danmaku.ijk.media.alpha.download;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import tv.danmaku.ijk.media.alpha.IAlphaListener;
import tv.danmaku.ijk.media.alpha.download.AlphaDownloadManager;
import tv.danmaku.ijk.media.alpha.util.FileUtil;
import tv.danmaku.ijk.media.alpha.util.UrlUtil;
import tv.danmaku.ijk.media.domain.entity.AlphaAnimBean;
import tv.danmaku.ijk.media.ext.mta.AlphaDownloadMtaReport;
import tv.danmaku.ijk.media.utils.DebugLog;

/* loaded from: classes11.dex */
public class AlphaDownloadManager {
    private static AlphaDownloadManager INSTANCE = null;
    private static final String TAG = "AlphaDownloadManager";
    private AlphaDownloadConfig downloadConfig;
    private List<IAlphaListener.OnEventListener> eventListenerList = new ArrayList();
    private IAlphaListener.OnEventListener eventProxyListener = new IAlphaListener.OnEventListener() { // from class: tv.danmaku.ijk.media.alpha.download.a
        @Override // tv.danmaku.ijk.media.alpha.IAlphaListener.OnEventListener
        public final void onEvent(int i2) {
            AlphaDownloadManager.this.b(i2);
        }
    };
    private Handler uiHandler = new Handler(Looper.getMainLooper());
    private final ExecutorService mAnimDownloadExecutor = Executors.newSingleThreadExecutor();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class DownloadResultTask implements Runnable {
        AlphaAnimBean alphaAnimBean;
        OnAlphaResManagerCallback callback;
        FutureTask<String> downloadTask;
        AlphaDownloadMtaReport mtaReport;

        public DownloadResultTask(AlphaDownloadManager alphaDownloadManager, FutureTask<String> futureTask, AlphaAnimBean alphaAnimBean, OnAlphaResManagerCallback onAlphaResManagerCallback) {
            this(futureTask, alphaAnimBean, onAlphaResManagerCallback, null);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String str = this.downloadTask.get();
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                this.alphaAnimBean.setLocalCachePath(str);
                AlphaDownloadManager.this.notifyResult(this.alphaAnimBean, this.callback);
                AlphaDownloadMtaReport alphaDownloadMtaReport = this.mtaReport;
                if (alphaDownloadMtaReport != null && alphaDownloadMtaReport.getEventListener() != null) {
                    AlphaDownloadManager.this.eventListenerList.remove(this.mtaReport.getEventListener());
                }
                FileUtil.clearVideoCache(AlphaDownloadManager.this.downloadConfig.getCacheDirPath());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        public DownloadResultTask(FutureTask<String> futureTask, AlphaAnimBean alphaAnimBean, OnAlphaResManagerCallback onAlphaResManagerCallback, AlphaDownloadMtaReport alphaDownloadMtaReport) {
            this.downloadTask = futureTask;
            this.alphaAnimBean = alphaAnimBean;
            this.callback = onAlphaResManagerCallback;
            this.mtaReport = alphaDownloadMtaReport;
        }
    }

    /* loaded from: classes11.dex */
    public interface OnAlphaResManagerCallback {
        void onResLoaded(String str);
    }

    private AlphaDownloadManager() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b(int i2) {
        for (IAlphaListener.OnEventListener onEventListener : this.eventListenerList) {
            if (onEventListener != null) {
                onEventListener.onEvent(i2);
            }
        }
    }

    public static AlphaDownloadManager getInstance() {
        if (INSTANCE == null) {
            synchronized (AlphaDownloadManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AlphaDownloadManager();
                }
            }
        }
        return INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyResult(final AlphaAnimBean alphaAnimBean, final OnAlphaResManagerCallback onAlphaResManagerCallback) {
        Handler handler = this.uiHandler;
        if (handler == null || onAlphaResManagerCallback == null) {
            return;
        }
        handler.post(new Runnable() { // from class: tv.danmaku.ijk.media.alpha.download.b
            @Override // java.lang.Runnable
            public final void run() {
                AlphaDownloadManager.OnAlphaResManagerCallback.this.onResLoaded(alphaAnimBean.getLocalCachePath());
            }
        });
    }

    public void init(AlphaDownloadConfig alphaDownloadConfig) {
        this.downloadConfig = alphaDownloadConfig;
    }

    public void loadAnimByOption(DownloadOptions downloadOptions, OnAlphaResManagerCallback onAlphaResManagerCallback) {
        if (this.downloadConfig == null) {
            DebugLog.e(TAG, "downloadConfig is null");
        } else if (downloadOptions != null && !TextUtils.isEmpty(downloadOptions.getUrl())) {
            if (downloadOptions.isEnableMta()) {
                AlphaDownloadMtaReport alphaDownloadMtaReport = new AlphaDownloadMtaReport(this.downloadConfig.getContext(), downloadOptions);
                this.eventListenerList.add(alphaDownloadMtaReport.getEventListener());
                try {
                    AlphaAnimBean parseUrl = UrlUtil.parseUrl(downloadOptions.getUrl());
                    FutureTask futureTask = new FutureTask(new AlphaResDownloadCallable(parseUrl, this.downloadConfig, this.eventProxyListener));
                    this.mAnimDownloadExecutor.execute(futureTask);
                    this.mAnimDownloadExecutor.execute(new DownloadResultTask(futureTask, parseUrl, onAlphaResManagerCallback, alphaDownloadMtaReport));
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            loadAnimByUrl(downloadOptions.getUrl(), onAlphaResManagerCallback);
        } else {
            DebugLog.e(TAG, "downloadOptions is null");
        }
    }

    public void loadAnimByUrl(String str, OnAlphaResManagerCallback onAlphaResManagerCallback) {
        if (this.downloadConfig == null) {
            DebugLog.e(TAG, "downloadConfig is null");
        } else if (TextUtils.isEmpty(str)) {
            DebugLog.e(TAG, "download url is empty");
        } else {
            AlphaAnimBean parseUrl = UrlUtil.parseUrl(str);
            if (parseUrl != null && !TextUtils.isEmpty(parseUrl.getDownloadUrl())) {
                try {
                    FutureTask futureTask = new FutureTask(new AlphaResDownloadCallable(parseUrl, this.downloadConfig, this.eventProxyListener));
                    this.mAnimDownloadExecutor.execute(futureTask);
                    this.mAnimDownloadExecutor.execute(new DownloadResultTask(this, futureTask, parseUrl, onAlphaResManagerCallback));
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            DebugLog.e(TAG, "alphaAnimBean is null or download url is empty");
        }
    }
}
