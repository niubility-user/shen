package com.jingdong.app.mall.bundle.updownload.download;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: classes3.dex */
public class BaseDownloadProgressListener implements IDownloadListener {
    private static final int REQUEST_UPDATE = 1;
    Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.jingdong.app.mall.bundle.updownload.download.BaseDownloadProgressListener.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            ProgressModel progressModel = (ProgressModel) message.obj;
            BaseDownloadProgressListener.this.onUIResponseProgress(progressModel.getCurrentBytes(), progressModel.getContentLength(), progressModel.isDone());
        }
    };
    protected BaseDownloadProgressListener mListener;
    private Object mMsgObject;

    public Object getMsgObject() {
        return this.mMsgObject;
    }

    @Override // com.jingdong.app.mall.bundle.updownload.download.IDownloadListener
    public void onCancel(Object obj, Bundle bundle) {
    }

    @Override // com.jingdong.app.mall.bundle.updownload.download.IDownloadListener
    public void onComplete(Object obj, String str, Bundle bundle) {
    }

    @Override // com.jingdong.app.mall.bundle.updownload.download.IDownloadListener
    public void onException(Object obj, Exception exc, Bundle bundle) {
    }

    @Override // com.jingdong.app.mall.bundle.updownload.download.IDownloadListener
    public void onFailure(Object obj, int i2, String str, Bundle bundle) {
    }

    @Override // com.jingdong.app.mall.bundle.updownload.download.IDownloadListener
    public void onProgress(Object obj, long j2, long j3, Bundle bundle) {
        Message obtain = Message.obtain();
        obtain.obj = new ProgressModel(j2, j3, j2 == j3);
        obtain.what = 1;
        this.mHandler.removeMessages(1);
        this.mHandler.sendMessage(obtain);
    }

    public void onUIResponseProgress(long j2, long j3, boolean z) {
    }

    public void setListener(BaseDownloadProgressListener baseDownloadProgressListener) {
        this.mListener = baseDownloadProgressListener;
    }

    public void setMsgObject(Object obj) {
        this.mMsgObject = obj;
    }
}
