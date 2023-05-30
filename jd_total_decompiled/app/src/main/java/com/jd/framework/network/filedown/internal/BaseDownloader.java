package com.jd.framework.network.filedown.internal;

import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpStackFactory;
import com.jd.dynamic.DYConstants;
import com.jd.framework.network.JDResponse;
import com.jd.framework.network.error.JDError;
import com.jd.framework.network.error.JDFileDownloadError;
import com.jd.framework.network.file.JDFileGuider;
import com.jd.framework.network.file.JDFileResponseListener;
import com.jd.framework.network.file.JDStopController;
import com.jd.framework.network.filedown.JDFileService;
import com.jd.framework.network.request.JDFileRequest;
import com.jd.framework.network.toolbox.JDNetworkStatisticTool;
import com.jingdong.jdsdk.network.JDHttpTookit;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* loaded from: classes13.dex */
public abstract class BaseDownloader<T> implements IDownload {
    private static final int BUFFER_SIZE = 16384;
    public static final String TAG = "DownloadImpl";
    private static DownloadRouteSelector sDownloadRouteSelector = new DownloadRouteSelector();
    private HttpStackFactory mHttpStackFactory;

    /* loaded from: classes13.dex */
    public static class DownloadResponse {
        long contentLength;
        int httpCode;
        InputStream inputStream;

        public DownloadResponse(int i2, InputStream inputStream, long j2) {
            this.inputStream = inputStream;
            this.contentLength = j2;
            this.httpCode = i2;
        }
    }

    public BaseDownloader(HttpStackFactory httpStackFactory) {
        this.mHttpStackFactory = httpStackFactory;
    }

    public static void executeAction(Context context, JDFileRequest jDFileRequest, HttpStackFactory httpStackFactory) {
        IDownload urlConnDownloader;
        sDownloadRouteSelector.setupRoute();
        if (jDFileRequest.getUseOkhttpFlag()) {
            urlConnDownloader = new OkHttpDownloader(httpStackFactory);
        } else {
            urlConnDownloader = new UrlConnDownloader(httpStackFactory);
        }
        urlConnDownloader.performRequest(context, jDFileRequest);
    }

    public static DownloadRouteSelector getDownloadRouteSelector() {
        return sDownloadRouteSelector;
    }

    private void readAsFile(InputStream inputStream, File file, long j2, JDFileResponseListener<File> jDFileResponseListener, long j3, JDStopController jDStopController) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            randomAccessFile.seek(j2);
            byte[] bArr = new byte[16384];
            int i2 = (int) j2;
            long j4 = 0;
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1 || jDStopController.isStop()) {
                    break;
                }
                randomAccessFile.write(bArr, 0, read);
                i2 += read;
                if (jDFileResponseListener != null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - j4 > 500) {
                        jDFileResponseListener.onProgress(Long.valueOf(j3).intValue(), i2);
                        j4 = currentTimeMillis;
                    }
                }
            }
        } finally {
            randomAccessFile.close();
        }
    }

    public abstract DownloadResponse doHttpRequest(Context context, JDFileRequest jDFileRequest) throws Exception;

    public abstract T getHttpStack();

    public HttpStackFactory getHttpStackFactory() {
        return this.mHttpStackFactory;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0177 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a9 A[Catch: Exception -> 0x00b4, all -> 0x010d, TryCatch #7 {Exception -> 0x00b4, all -> 0x010d, blocks: (B:30:0x009f, B:32:0x00a9, B:33:0x00ab, B:29:0x009c), top: B:117:0x009c }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0122 A[Catch: all -> 0x0171, TryCatch #8 {all -> 0x0171, blocks: (B:50:0x0117, B:52:0x0122, B:54:0x012b, B:55:0x0132, B:57:0x013b, B:59:0x013f, B:60:0x014e, B:66:0x0160, B:68:0x0167), top: B:115:0x0117 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x013b A[Catch: all -> 0x0171, TryCatch #8 {all -> 0x0171, blocks: (B:50:0x0117, B:52:0x0122, B:54:0x012b, B:55:0x0132, B:57:0x013b, B:59:0x013f, B:60:0x014e, B:66:0x0160, B:68:0x0167), top: B:115:0x0117 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0174  */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.jd.framework.network.file.JDFileResponseListener, com.jd.framework.network.JDResponseListener] */
    @Override // com.jd.framework.network.filedown.internal.IDownload
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void performRequest(Context context, JDFileRequest jDFileRequest) {
        boolean z;
        int i2;
        DownloadResponse downloadResponse;
        int i3;
        boolean z2;
        boolean z3;
        JDFileDownloadError jDFileDownloadError;
        if (jDFileRequest.isTopPriority()) {
            Process.setThreadPriority(-8);
        } else {
            Process.setThreadPriority(19);
        }
        if (VolleyLog.DEBUG) {
            String str = "Start Downloading----> RequestUrl:" + jDFileRequest.getUrl();
        }
        boolean isBreakpointTransmission = jDFileRequest.isBreakpointTransmission();
        ?? responseListener = jDFileRequest.getResponseListener();
        responseListener.onStart();
        DownloadResponse downloadResponse2 = null;
        if (jDFileRequest.isNoAttempts()) {
            z = true;
            i2 = 1;
        } else {
            z = false;
            i2 = 2;
        }
        String host = jDFileRequest.getHost();
        String url = jDFileRequest.getUrl();
        JDNetworkStatisticTool.getInstance().incrDownloadTotalReqCnt();
        boolean z4 = z;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i4 >= i2) {
                downloadResponse = downloadResponse2;
                i3 = i5;
                z2 = false;
                break;
            }
            try {
                if (VolleyLog.DEBUG) {
                    String str2 = "retry:" + i4 + " within total : " + i2 + " , RequestUrl:" + jDFileRequest.getUrl();
                }
                try {
                    String healthDomain = sDownloadRouteSelector.getHealthDomain(host);
                    if (!TextUtils.equals(host, healthDomain)) {
                        try {
                            jDFileRequest.setUrl(url.replace(host, healthDomain));
                        } catch (Throwable th) {
                            th = th;
                            try {
                                th.printStackTrace();
                                if (!TextUtils.equals(host, jDFileRequest.getHost())) {
                                }
                                downloadResponse = doHttpRequest(context, jDFileRequest);
                                i3 = i5;
                                z2 = true;
                                z3 = !TextUtils.equals(host, jDFileRequest.getHost());
                                if (z2) {
                                }
                                JDNetworkStatisticTool.getInstance().saveDownloadStatResult();
                                if (z3) {
                                }
                                if (z2) {
                                }
                            } catch (Exception e2) {
                                e = e2;
                                if (VolleyLog.DEBUG) {
                                    String str3 = "errorInfo:" + e.toString();
                                    String str4 = "FileRequest URL : " + jDFileRequest.getUrl();
                                }
                                if (e instanceof JDError) {
                                    jDFileDownloadError = new JDFileDownloadError((JDError) e, z4);
                                } else {
                                    jDFileDownloadError = new JDFileDownloadError(jDFileRequest.getUrl(), e, z4);
                                }
                                responseListener.onError(jDFileDownloadError);
                                sDownloadRouteSelector.updateDomainFailList(host, jDFileRequest.getHost(), e);
                                boolean z5 = VolleyLog.DEBUG;
                                z4 = true;
                                i4++;
                            } catch (Throwable unused) {
                                continue;
                            }
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
                if (!TextUtils.equals(host, jDFileRequest.getHost())) {
                    i5++;
                }
                downloadResponse = doHttpRequest(context, jDFileRequest);
                i3 = i5;
                z2 = true;
                break;
            } catch (Exception e3) {
                e = e3;
            } catch (Throwable unused2) {
            }
            i4++;
        }
        try {
            z3 = !TextUtils.equals(host, jDFileRequest.getHost());
            if (z2) {
                JDNetworkStatisticTool.getInstance().incrDownloadSuccessReqCnt();
                if (z3) {
                    JDNetworkStatisticTool.getInstance().incrDownloadBakDomainReqCnt();
                }
            }
            JDNetworkStatisticTool.getInstance().saveDownloadStatResult();
            if (z3) {
                if (VolleyLog.DEBUG) {
                    String.format("%s\u964d\u7ea7\u5230\u5907\u7528\u57df\u540d%s", host, jDFileRequest.getHost());
                }
                JDHttpTookit.getEngine().getExceptionReportDelegate().reportDownloadDowngradeData(url, z2 ? DYConstants.DY_TRUE : DYConstants.DY_FALSE, z2 ? jDFileRequest.getHost() : "", false, i3, url);
            }
        } catch (Throwable unused3) {
        }
        if (z2) {
            boolean z6 = VolleyLog.DEBUG;
            return;
        }
        try {
            if (downloadResponse != null) {
                InputStream inputStream = downloadResponse.inputStream;
                long j2 = downloadResponse.contentLength;
                if (jDFileRequest.isRetrieveInputStream()) {
                    if (!jDFileRequest.isStop()) {
                        JDResponse jDResponse = new JDResponse();
                        jDResponse.setData(inputStream);
                        jDResponse.setStatusCode(downloadResponse.httpCode);
                        responseListener.onEnd(jDResponse);
                        return;
                    }
                    responseListener.onPause();
                    return;
                }
                File saveFile = saveFile(jDFileRequest, context, isBreakpointTransmission, responseListener, j2, inputStream);
                if (VolleyLog.DEBUG) {
                    String str5 = "download success:" + jDFileRequest.getUrl();
                }
                inputStream.close();
                if (!jDFileRequest.isStop()) {
                    if (saveFile.exists() && saveFile != null) {
                        JDResponse jDResponse2 = new JDResponse();
                        jDResponse2.setData(saveFile);
                        jDResponse2.setStatusCode(downloadResponse.httpCode);
                        responseListener.onEnd(jDResponse2);
                        return;
                    }
                    throw new IllegalStateException("download finished but file not exist");
                }
                responseListener.onPause();
                return;
            }
            throw new IOException("cannot read from null response");
        } catch (Throwable th3) {
            if (VolleyLog.DEBUG) {
                th3.printStackTrace();
            }
            responseListener.onError(new JDFileDownloadError(jDFileRequest.getUrl(), th3, true));
        }
    }

    protected File saveFile(JDFileRequest jDFileRequest, Context context, boolean z, JDFileResponseListener<File> jDFileResponseListener, long j2, InputStream inputStream) throws IOException {
        JDFileGuider savePath = jDFileRequest.getSavePath();
        savePath.setAvailableSize(j2);
        JDFileService.resetSaveFileParam(jDFileRequest, context, savePath, savePath.isImmutable(), savePath.getSpace());
        File fileSavePath = JDFileService.getFileSavePath(savePath, context);
        if (fileSavePath == null) {
            if (VolleyLog.DEBUG) {
                String str = "requestID : " + jDFileRequest.getSequence() + ",\u4e0b\u8f7d\u5931\u8d25\uff0c\u5b58\u50a8\u7a7a\u95f4\u4e0d\u8db3\uff01";
            }
            throw new IOException("Not enough storage space\uff01");
        }
        if (z) {
            readAsFile(inputStream, fileSavePath, jDFileRequest.getStartPosBreakpointTransmission(), jDFileResponseListener, savePath.getAvailableSize(), jDFileRequest);
        } else {
            BufferedOutputStream openFileOutput = JDFileService.openFileOutput(jDFileRequest.getSavePath(), fileSavePath);
            try {
                readAsFile(inputStream, openFileOutput, jDFileResponseListener, savePath.getAvailableSize(), jDFileRequest);
                if (openFileOutput != null) {
                    try {
                        openFileOutput.flush();
                    } finally {
                    }
                }
                if (openFileOutput != null) {
                    openFileOutput.close();
                }
                if (jDFileRequest.isStop()) {
                    fileSavePath.delete();
                }
            } catch (Throwable th) {
                if (openFileOutput != null) {
                    try {
                        openFileOutput.flush();
                    } finally {
                    }
                }
                if (openFileOutput != null) {
                    openFileOutput.close();
                }
                throw th;
            }
        }
        return fileSavePath;
    }

    private void readAsFile(InputStream inputStream, BufferedOutputStream bufferedOutputStream, JDFileResponseListener<File> jDFileResponseListener, long j2, JDStopController jDStopController) throws IOException {
        byte[] bArr = new byte[16384];
        int i2 = 0;
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1 || jDStopController.isStop()) {
                return;
            }
            bufferedOutputStream.write(bArr, 0, read);
            i2 += read;
            if (jDFileResponseListener != null) {
                jDFileResponseListener.onProgress(Long.valueOf(j2).intValue(), i2);
            }
        }
    }
}
