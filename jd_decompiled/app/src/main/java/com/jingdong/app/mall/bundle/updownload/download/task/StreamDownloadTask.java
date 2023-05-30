package com.jingdong.app.mall.bundle.updownload.download.task;

import com.google.common.net.HttpHeaders;
import com.jingdong.app.mall.bundle.updownload.HttpClient;
import com.jingdong.app.mall.bundle.updownload.download.DownloadManager;
import com.jingdong.app.mall.bundle.updownload.download.DownloadTaskInfo;
import com.jingdong.app.mall.bundle.updownload.download.IDownloadListener;
import com.jingdong.sdk.oklog.OKLog;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes3.dex */
public class StreamDownloadTask extends DownloadTask {
    public static final int HTTP_SUCCESS = 200;
    private static final String TAG = "StreamDownloadTask";
    private Call mCall;
    private Callback mResponseCallback;

    public StreamDownloadTask(DownloadManager downloadManager, DownloadTaskInfo downloadTaskInfo) {
        super(downloadManager, downloadTaskInfo);
        this.mResponseCallback = new Callback() { // from class: com.jingdong.app.mall.bundle.updownload.download.task.StreamDownloadTask.2
            {
                StreamDownloadTask.this = this;
            }

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                StreamDownloadTask.this.onFailure(call, iOException);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                StreamDownloadTask.this.onReponse(response);
            }
        };
    }

    public void onFailure(Call call, Exception exc) {
        if (call != null && call.isCanceled()) {
            OKLog.d(TAG, "user cancel");
            return;
        }
        IDownloadListener iDownloadListener = this.mListener;
        if (iDownloadListener != null) {
            iDownloadListener.onFailure(this.mTaskInfo.tag, -1, "exception: " + exc.getMessage(), this.mTaskInfo.attachmentBundle);
        }
        OKLog.d(TAG, "exception: " + exc.getMessage());
        DownloadManager downloadManager = this.mDownloadManager;
        DownloadTaskInfo downloadTaskInfo = this.mTaskInfo;
        downloadManager.taskComplete(downloadTaskInfo.type, downloadTaskInfo.tag);
    }

    /* JADX WARN: Removed duplicated region for block: B:285:0x01c6 A[Catch: all -> 0x0273, TryCatch #6 {all -> 0x0273, blocks: (B:283:0x01c2, B:285:0x01c6, B:287:0x01ca, B:290:0x01d0, B:291:0x0208), top: B:333:0x01c2 }] */
    /* JADX WARN: Removed duplicated region for block: B:290:0x01d0 A[Catch: all -> 0x0273, TRY_ENTER, TryCatch #6 {all -> 0x0273, blocks: (B:283:0x01c2, B:285:0x01c6, B:287:0x01ca, B:290:0x01d0, B:291:0x0208), top: B:333:0x01c2 }] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0208 A[Catch: all -> 0x0273, TRY_LEAVE, TryCatch #6 {all -> 0x0273, blocks: (B:283:0x01c2, B:285:0x01c6, B:287:0x01ca, B:290:0x01d0, B:291:0x0208), top: B:333:0x01c2 }] */
    /* JADX WARN: Removed duplicated region for block: B:297:0x0247 A[Catch: IOException -> 0x0243, TryCatch #8 {IOException -> 0x0243, blocks: (B:293:0x023f, B:297:0x0247, B:298:0x024a, B:300:0x024e), top: B:334:0x023f }] */
    /* JADX WARN: Removed duplicated region for block: B:300:0x024e A[Catch: IOException -> 0x0243, TRY_LEAVE, TryCatch #8 {IOException -> 0x0243, blocks: (B:293:0x023f, B:297:0x0247, B:298:0x024a, B:300:0x024e), top: B:334:0x023f }] */
    /* JADX WARN: Removed duplicated region for block: B:311:0x027f A[Catch: IOException -> 0x027b, TryCatch #15 {IOException -> 0x027b, blocks: (B:307:0x0277, B:311:0x027f, B:312:0x0282, B:314:0x0286), top: B:338:0x0277 }] */
    /* JADX WARN: Removed duplicated region for block: B:314:0x0286 A[Catch: IOException -> 0x027b, TRY_LEAVE, TryCatch #15 {IOException -> 0x027b, blocks: (B:307:0x0277, B:311:0x027f, B:312:0x0282, B:314:0x0286), top: B:338:0x0277 }] */
    /* JADX WARN: Removed duplicated region for block: B:334:0x023f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0277 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:358:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onReponse(okhttp3.Response r22) {
        /*
            Method dump skipped, instructions count: 840
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.bundle.updownload.download.task.StreamDownloadTask.onReponse(okhttp3.Response):void");
    }

    @Override // com.jingdong.app.mall.bundle.updownload.download.task.DownloadTask
    public void cancel() {
        this.mState = 4;
        Call call = this.mCall;
        if (call != null) {
            call.cancel();
        }
        IDownloadListener iDownloadListener = this.mListener;
        if (iDownloadListener != null) {
            DownloadTaskInfo downloadTaskInfo = this.mTaskInfo;
            iDownloadListener.onCancel(downloadTaskInfo.tag, downloadTaskInfo.attachmentBundle);
        }
        DownloadManager downloadManager = this.mDownloadManager;
        DownloadTaskInfo downloadTaskInfo2 = this.mTaskInfo;
        downloadManager.taskComplete(downloadTaskInfo2.type, downloadTaskInfo2.tag);
    }

    @Override // com.jingdong.app.mall.bundle.updownload.download.task.DownloadTask
    boolean copy() {
        return false;
    }

    @Override // com.jingdong.app.mall.bundle.updownload.download.task.DownloadTask
    public void pause() {
        this.mState = 3;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.mState == 4) {
                return;
            }
            this.mState = 1;
            this.mCall = HttpClient.getInstance().client().newCall(new Request.Builder().url(this.mTaskInfo.url).tag(this.mTaskInfo.tag).header(HttpHeaders.ACCEPT_ENCODING, "").build());
            HttpClient.getInstance().client().dispatcher().executorService().execute(new Runnable() { // from class: com.jingdong.app.mall.bundle.updownload.download.task.StreamDownloadTask.1
                {
                    StreamDownloadTask.this = this;
                }

                @Override // java.lang.Runnable
                public void run() {
                    try {
                        StreamDownloadTask.this.onReponse(StreamDownloadTask.this.mCall.execute());
                    } catch (Exception e2) {
                        StreamDownloadTask streamDownloadTask = StreamDownloadTask.this;
                        streamDownloadTask.onFailure(streamDownloadTask.mCall, e2);
                    }
                }
            });
        } catch (Exception e2) {
            IDownloadListener iDownloadListener = this.mListener;
            if (iDownloadListener != null) {
                DownloadTaskInfo downloadTaskInfo = this.mTaskInfo;
                iDownloadListener.onException(downloadTaskInfo.tag, e2, downloadTaskInfo.attachmentBundle);
            }
        }
    }
}
