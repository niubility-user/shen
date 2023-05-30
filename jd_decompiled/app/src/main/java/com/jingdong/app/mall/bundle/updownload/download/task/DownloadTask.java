package com.jingdong.app.mall.bundle.updownload.download.task;

import com.jingdong.app.mall.bundle.updownload.download.DownloadManager;
import com.jingdong.app.mall.bundle.updownload.download.DownloadTaskInfo;
import com.jingdong.app.mall.bundle.updownload.download.IDownloadListener;

/* loaded from: classes3.dex */
public abstract class DownloadTask implements Runnable {
    protected int mCurrentProgress;
    DownloadManager mDownloadManager;
    IDownloadListener mListener;
    int mState = 0;
    DownloadTaskInfo mTaskInfo;

    /* loaded from: classes3.dex */
    public interface STATE {
        public static final int CANCEL = 4;
        public static final int COMPLETE = 2;
        public static final int DOWNLOADING = 1;
        public static final int PAUSE = 3;
        public static final int RESET = 0;
    }

    public DownloadTask(DownloadManager downloadManager, DownloadTaskInfo downloadTaskInfo) {
        this.mDownloadManager = downloadManager;
        this.mTaskInfo = downloadTaskInfo;
    }

    public abstract void cancel();

    abstract boolean copy();

    public int getCurrentProgress() {
        return this.mCurrentProgress;
    }

    public IDownloadListener getListener() {
        return this.mListener;
    }

    public abstract void pause();

    public void setListener(IDownloadListener iDownloadListener) {
        this.mListener = iDownloadListener;
    }
}
