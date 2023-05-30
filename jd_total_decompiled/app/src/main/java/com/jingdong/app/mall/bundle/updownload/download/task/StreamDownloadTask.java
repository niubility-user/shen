package com.jingdong.app.mall.bundle.updownload.download.task;

import com.google.common.net.HttpHeaders;
import com.jingdong.app.mall.bundle.updownload.HttpClient;
import com.jingdong.app.mall.bundle.updownload.download.DataProcessException;
import com.jingdong.app.mall.bundle.updownload.download.DownloadManager;
import com.jingdong.app.mall.bundle.updownload.download.DownloadTaskInfo;
import com.jingdong.app.mall.bundle.updownload.download.IDownloadListener;
import com.jingdong.app.mall.bundle.updownload.utils.FileUtils;
import com.jingdong.sdk.oklog.OKLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    /* JADX WARN: Removed duplicated region for block: B:467:0x01c6 A[Catch: all -> 0x0273, TryCatch #6 {all -> 0x0273, blocks: (B:465:0x01c2, B:467:0x01c6, B:469:0x01ca, B:472:0x01d0, B:473:0x0208), top: B:515:0x01c2 }] */
    /* JADX WARN: Removed duplicated region for block: B:472:0x01d0 A[Catch: all -> 0x0273, TRY_ENTER, TryCatch #6 {all -> 0x0273, blocks: (B:465:0x01c2, B:467:0x01c6, B:469:0x01ca, B:472:0x01d0, B:473:0x0208), top: B:515:0x01c2 }] */
    /* JADX WARN: Removed duplicated region for block: B:473:0x0208 A[Catch: all -> 0x0273, TRY_LEAVE, TryCatch #6 {all -> 0x0273, blocks: (B:465:0x01c2, B:467:0x01c6, B:469:0x01ca, B:472:0x01d0, B:473:0x0208), top: B:515:0x01c2 }] */
    /* JADX WARN: Removed duplicated region for block: B:479:0x0247 A[Catch: IOException -> 0x0243, TryCatch #8 {IOException -> 0x0243, blocks: (B:475:0x023f, B:479:0x0247, B:480:0x024a, B:482:0x024e), top: B:516:0x023f }] */
    /* JADX WARN: Removed duplicated region for block: B:482:0x024e A[Catch: IOException -> 0x0243, TRY_LEAVE, TryCatch #8 {IOException -> 0x0243, blocks: (B:475:0x023f, B:479:0x0247, B:480:0x024a, B:482:0x024e), top: B:516:0x023f }] */
    /* JADX WARN: Removed duplicated region for block: B:493:0x027f A[Catch: IOException -> 0x027b, TryCatch #15 {IOException -> 0x027b, blocks: (B:489:0x0277, B:493:0x027f, B:494:0x0282, B:496:0x0286), top: B:520:0x0277 }] */
    /* JADX WARN: Removed duplicated region for block: B:496:0x0286 A[Catch: IOException -> 0x027b, TRY_LEAVE, TryCatch #15 {IOException -> 0x027b, blocks: (B:489:0x0277, B:493:0x027f, B:494:0x0282, B:496:0x0286), top: B:520:0x0277 }] */
    /* JADX WARN: Removed duplicated region for block: B:516:0x023f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:520:0x0277 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:540:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onReponse(Response response) {
        Throwable th;
        FileOutputStream fileOutputStream;
        StringBuilder sb;
        IOException e2;
        IDownloadListener iDownloadListener;
        InputStream inputStream;
        int read;
        long j2;
        File file;
        long j3;
        if (this.mState == 4) {
            return;
        }
        if (response != null) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    byte[] bArr = new byte[2048];
                    InputStream inputStream2 = null;
                    try {
                        InputStream byteStream = response.body().byteStream();
                        try {
                            long contentLength = response.body().contentLength();
                            String str = this.mTaskInfo.tempPath + "/" + this.mTaskInfo.tempName;
                            File file2 = new File(str);
                            if (file2.exists()) {
                                try {
                                    file2.delete();
                                } catch (Exception e3) {
                                    e = e3;
                                    fileOutputStream = null;
                                    inputStream2 = byteStream;
                                    try {
                                        if (this.mState != 4) {
                                        }
                                        if (inputStream2 != null) {
                                        }
                                        if (fileOutputStream != null) {
                                        }
                                        if (this.mState != 4) {
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (inputStream2 != null) {
                                            try {
                                                inputStream2.close();
                                            } catch (IOException e4) {
                                                OKLog.d(TAG, "onResponse: " + e4.getMessage());
                                                throw th;
                                            }
                                        }
                                        if (fileOutputStream != null) {
                                            fileOutputStream.close();
                                        }
                                        if (this.mState != 4) {
                                            DownloadManager downloadManager = this.mDownloadManager;
                                            DownloadTaskInfo downloadTaskInfo = this.mTaskInfo;
                                            downloadManager.taskComplete(downloadTaskInfo.type, downloadTaskInfo.tag);
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    fileOutputStream = null;
                                    inputStream2 = byteStream;
                                    if (inputStream2 != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    if (this.mState != 4) {
                                    }
                                    throw th;
                                }
                            }
                            FileOutputStream fileOutputStream2 = new FileOutputStream(str);
                            long j4 = 0;
                            for (int i2 = -1; this.mState == 1 && (read = byteStream.read(bArr)) != i2; i2 = -1) {
                                try {
                                    fileOutputStream2.write(bArr, 0, read);
                                    inputStream = byteStream;
                                    j2 = read + j4;
                                } catch (Exception e5) {
                                    e = e5;
                                    inputStream = byteStream;
                                } catch (Throwable th4) {
                                    th = th4;
                                    inputStream = byteStream;
                                }
                                try {
                                    IDownloadListener iDownloadListener2 = this.mListener;
                                    if (iDownloadListener2 != null) {
                                        DownloadTaskInfo downloadTaskInfo2 = this.mTaskInfo;
                                        file = file2;
                                        fileOutputStream = fileOutputStream2;
                                        j3 = contentLength;
                                        try {
                                            iDownloadListener2.onProgress(downloadTaskInfo2.tag, j2, contentLength, downloadTaskInfo2.attachmentBundle);
                                        } catch (Exception e6) {
                                            e = e6;
                                            inputStream2 = inputStream;
                                            if (this.mState != 4 && (iDownloadListener = this.mListener) != null) {
                                                if (!(e instanceof DataProcessException)) {
                                                    DataProcessException dataProcessException = (DataProcessException) e;
                                                    iDownloadListener.onFailure(this.mTaskInfo.tag, dataProcessException.getCode(), dataProcessException.getMessage(), this.mTaskInfo.attachmentBundle);
                                                    OKLog.d(TAG, "data process exception: " + dataProcessException.getCode() + "exception: " + dataProcessException.getMessage());
                                                } else {
                                                    iDownloadListener.onFailure(this.mTaskInfo.tag, -1, "exception: " + e.getMessage(), this.mTaskInfo.attachmentBundle);
                                                    OKLog.d(TAG, "exception: " + e.getMessage());
                                                }
                                            }
                                            if (inputStream2 != null) {
                                                try {
                                                    inputStream2.close();
                                                } catch (IOException e7) {
                                                    e2 = e7;
                                                    sb = new StringBuilder();
                                                    sb.append("onResponse: ");
                                                    sb.append(e2.getMessage());
                                                    OKLog.d(TAG, sb.toString());
                                                    return;
                                                }
                                            }
                                            if (fileOutputStream != null) {
                                                fileOutputStream.close();
                                            }
                                            if (this.mState != 4) {
                                                DownloadManager downloadManager2 = this.mDownloadManager;
                                                DownloadTaskInfo downloadTaskInfo3 = this.mTaskInfo;
                                                downloadManager2.taskComplete(downloadTaskInfo3.type, downloadTaskInfo3.tag);
                                                return;
                                            }
                                            return;
                                        } catch (Throwable th5) {
                                            th = th5;
                                            th = th;
                                            inputStream2 = inputStream;
                                            if (inputStream2 != null) {
                                            }
                                            if (fileOutputStream != null) {
                                            }
                                            if (this.mState != 4) {
                                            }
                                            throw th;
                                        }
                                    } else {
                                        file = file2;
                                        fileOutputStream = fileOutputStream2;
                                        j3 = contentLength;
                                    }
                                    long j5 = j3;
                                    this.mCurrentProgress = (int) ((((float) j2) / ((float) j5)) * 100.0f);
                                    file2 = file;
                                    contentLength = j5;
                                    fileOutputStream2 = fileOutputStream;
                                    j4 = j2;
                                    byteStream = inputStream;
                                } catch (Exception e8) {
                                    e = e8;
                                    fileOutputStream = fileOutputStream2;
                                    inputStream2 = inputStream;
                                    if (this.mState != 4) {
                                        if (!(e instanceof DataProcessException)) {
                                        }
                                    }
                                    if (inputStream2 != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    if (this.mState != 4) {
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    fileOutputStream = fileOutputStream2;
                                    th = th;
                                    inputStream2 = inputStream;
                                    if (inputStream2 != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    if (this.mState != 4) {
                                    }
                                    throw th;
                                }
                            }
                            InputStream inputStream3 = byteStream;
                            File file3 = file2;
                            FileOutputStream fileOutputStream3 = fileOutputStream2;
                            fileOutputStream3.flush();
                            String str2 = this.mTaskInfo.filePath + "/" + this.mTaskInfo.fileName;
                            if (this.mState == 4) {
                                FileUtils.deleteDirWithFile(new File(this.mTaskInfo.tempPath));
                                File file4 = new File(str2);
                                if (file4.exists()) {
                                    file4.delete();
                                }
                                if (inputStream3 != null) {
                                    try {
                                        inputStream3.close();
                                    } catch (IOException e9) {
                                        OKLog.d(TAG, "onResponse: " + e9.getMessage());
                                        return;
                                    }
                                }
                                fileOutputStream3.close();
                                if (this.mState != 4) {
                                    DownloadManager downloadManager3 = this.mDownloadManager;
                                    DownloadTaskInfo downloadTaskInfo4 = this.mTaskInfo;
                                    downloadManager3.taskComplete(downloadTaskInfo4.type, downloadTaskInfo4.tag);
                                    return;
                                }
                                return;
                            }
                            File file5 = new File(str2);
                            if (file5.exists()) {
                                file5.delete();
                            }
                            if (file3.exists() && file3.renameTo(file5)) {
                                IDownloadListener iDownloadListener3 = this.mListener;
                                if (iDownloadListener3 != null) {
                                    DownloadTaskInfo downloadTaskInfo5 = this.mTaskInfo;
                                    iDownloadListener3.onComplete(downloadTaskInfo5.tag, str2, downloadTaskInfo5.attachmentBundle);
                                }
                                FileUtils.deleteDirWithFile(new File(this.mTaskInfo.tempPath));
                            } else {
                                IDownloadListener iDownloadListener4 = this.mListener;
                                if (iDownloadListener4 != null) {
                                    DownloadTaskInfo downloadTaskInfo6 = this.mTaskInfo;
                                    iDownloadListener4.onFailure(downloadTaskInfo6.tag, -1, "file rename fail", downloadTaskInfo6.attachmentBundle);
                                }
                                OKLog.d(TAG, "file rename fail");
                            }
                            if (inputStream3 != null) {
                                try {
                                    inputStream3.close();
                                } catch (IOException e10) {
                                    e2 = e10;
                                    sb = new StringBuilder();
                                    sb.append("onResponse: ");
                                    sb.append(e2.getMessage());
                                    OKLog.d(TAG, sb.toString());
                                    return;
                                }
                            }
                            fileOutputStream3.close();
                            if (this.mState != 4) {
                                DownloadManager downloadManager4 = this.mDownloadManager;
                                DownloadTaskInfo downloadTaskInfo7 = this.mTaskInfo;
                                downloadManager4.taskComplete(downloadTaskInfo7.type, downloadTaskInfo7.tag);
                            }
                        } catch (Exception e11) {
                            e = e11;
                            inputStream = byteStream;
                            fileOutputStream = null;
                        } catch (Throwable th7) {
                            inputStream = byteStream;
                            th = th7;
                            fileOutputStream = null;
                        }
                    } catch (Exception e12) {
                        e = e12;
                        fileOutputStream = null;
                    } catch (Throwable th8) {
                        th = th8;
                        fileOutputStream = null;
                    }
                } else {
                    IDownloadListener iDownloadListener5 = this.mListener;
                    if (iDownloadListener5 != null) {
                        DownloadTaskInfo downloadTaskInfo8 = this.mTaskInfo;
                        iDownloadListener5.onFailure(downloadTaskInfo8.tag, -2, "response body is null", downloadTaskInfo8.attachmentBundle);
                    }
                    OKLog.d(TAG, "response body is null");
                    DownloadManager downloadManager5 = this.mDownloadManager;
                    DownloadTaskInfo downloadTaskInfo9 = this.mTaskInfo;
                    downloadManager5.taskComplete(downloadTaskInfo9.type, downloadTaskInfo9.tag);
                }
            } else {
                IDownloadListener iDownloadListener6 = this.mListener;
                if (iDownloadListener6 != null) {
                    iDownloadListener6.onFailure(this.mTaskInfo.tag, response.code(), "response error: " + response.code() + " : " + response.message(), this.mTaskInfo.attachmentBundle);
                }
                OKLog.d(TAG, "response error: " + response.code() + " : " + response.message());
                DownloadManager downloadManager6 = this.mDownloadManager;
                DownloadTaskInfo downloadTaskInfo10 = this.mTaskInfo;
                downloadManager6.taskComplete(downloadTaskInfo10.type, downloadTaskInfo10.tag);
            }
        } else {
            IDownloadListener iDownloadListener7 = this.mListener;
            if (iDownloadListener7 != null) {
                DownloadTaskInfo downloadTaskInfo11 = this.mTaskInfo;
                iDownloadListener7.onFailure(downloadTaskInfo11.tag, -3, "response is null", downloadTaskInfo11.attachmentBundle);
            }
            OKLog.d(TAG, "response is null");
            DownloadManager downloadManager7 = this.mDownloadManager;
            DownloadTaskInfo downloadTaskInfo12 = this.mTaskInfo;
            downloadManager7.taskComplete(downloadTaskInfo12.type, downloadTaskInfo12.tag);
        }
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
