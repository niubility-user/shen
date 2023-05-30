package com.jingdong.common.XView2.strategy.downloader;

import android.content.Context;
import com.jd.hybrid.downloader.FileError;
import com.jd.hybrid.downloader.FileRequest;
import com.jd.hybrid.downloader.FileResponse;
import com.jd.hybrid.downloader.b;
import com.jd.hybrid.downloader.d;
import com.jd.hybrid.downloader.e;
import com.jd.hybrid.downloader.f;
import com.jd.jdcache.util.UrlHelper;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.strategy.downloader.XViewSerialFileDownloader;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import com.jingdong.jdsdk.JdSdk;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class XViewDownloadClient {
    private static final String TAG = "XViewDownloadClient";
    private static XViewDownloadClient instance;
    private Map<d, FileRequest> allRequestMap;
    private Context mContext;

    /* loaded from: classes5.dex */
    public static class Builder {
        private Context mContext;

        /* JADX INFO: Access modifiers changed from: private */
        public XViewDownloadClient build() {
            return new XViewDownloadClient(this.mContext);
        }

        private Builder(Context context) {
            this.mContext = context;
        }
    }

    /* loaded from: classes5.dex */
    public static class FileCheckError extends FileError {
        public float fileSizeInKB;

        public FileCheckError(int i2, float f2, String str) {
            super(i2, str);
            this.fileSizeInKB = f2;
        }
    }

    /* loaded from: classes5.dex */
    private class MyProxyDownloadListener implements e<File> {
        private final b downloadListener;
        private final d downloader;
        private final FileRequest request;

        private void endRequest() {
            XViewDownloadClient.this.allRequestMap.remove(this.downloader);
        }

        public void onCancel() {
            XViewLogPrint.JDXLog.d(XViewDownloadClient.TAG, "Download cancel " + this.downloader.i());
            endRequest();
            b bVar = this.downloadListener;
            if (bVar != null) {
                bVar.onCancel();
            }
        }

        @Override // com.jd.hybrid.downloader.e
        public void onEnd(FileResponse<File> fileResponse) {
            XViewLogPrint.JDXLog.d(XViewDownloadClient.TAG, "Download complete " + this.downloader.i());
            endRequest();
            b bVar = this.downloadListener;
            if (bVar != null) {
                bVar.onEnd(fileResponse);
            }
        }

        @Override // com.jd.hybrid.downloader.e
        public void onError(FileError fileError) {
            XViewLogPrint.JDXLog.e(XViewDownloadClient.TAG, "Download error (" + this.downloader.i() + ") Error: code = " + fileError.getStatusCode() + ", msg = " + fileError.getMessage());
            endRequest();
            b bVar = this.downloadListener;
            if (bVar != null) {
                bVar.onError(fileError);
            }
        }

        public void onPause() {
            if (this.downloadListener != null) {
                this.downloader.b().onPause();
            }
        }

        @Override // com.jd.hybrid.downloader.e
        public void onProgress(int i2, int i3) {
            b bVar = this.downloadListener;
            if (bVar != null) {
                bVar.onProgress(i2, i3);
            }
        }

        @Override // com.jd.hybrid.downloader.e
        public void onStart() {
            XViewLogPrint.JDXLog.d(XViewDownloadClient.TAG, "Download start " + this.downloader.i());
            b bVar = this.downloadListener;
            if (bVar != null) {
                bVar.onStart();
            }
        }

        private MyProxyDownloadListener(FileRequest fileRequest, d dVar) {
            this.request = fileRequest;
            this.downloader = dVar;
            this.downloadListener = dVar.b();
        }
    }

    private void executeRequest(FileRequest fileRequest, int i2) {
        if (fileRequest != null) {
            XViewSerialFileDownloader.getInstance(this.mContext).add(new XViewSerialFileDownloader.XViewFileRequest(fileRequest, i2));
        }
    }

    public static float getFileSizeInKB(File file) {
        if (file != null) {
            try {
                if (file.exists()) {
                    return ((float) file.length()) / 1024.0f;
                }
            } catch (Exception e2) {
                XViewLogPrint.JDXLog.e(TAG, e2);
            }
        }
        return 0.0f;
    }

    public static XViewDownloadClient getInstance() {
        return instance;
    }

    public static void init(Builder builder) {
        if (instance == null) {
            synchronized (d.class) {
                if (instance == null) {
                    instance = builder.build();
                }
            }
        }
    }

    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }

    public FileRequest addDownloader(d dVar) {
        if (this.allRequestMap.containsKey(dVar)) {
            XViewLogPrint.JDXLog.d(TAG, "Existed download request, priority = " + dVar.f() + ", url: " + dVar.i());
            return this.allRequestMap.get(dVar);
        }
        try {
            FileRequest fileRequest = new FileRequest(UrlHelper.METHOD_HEAD.equals(dVar.h()) ? 261 : 257, dVar.i());
            fileRequest.setReferer(XView2Constants.CACHE_FILE_ROOT + dVar.e());
            fileRequest.setResponseListener(new MyProxyDownloadListener(fileRequest, dVar));
            fileRequest.setSavePath(f.b(JdSdk.getInstance().getApplicationContext(), true, dVar.g(), dVar.d()).getPath());
            this.allRequestMap.put(dVar, fileRequest);
            executeRequest(fileRequest, dVar.f());
            return fileRequest;
        } catch (Exception unused) {
            XViewLogPrint.JDXLog.d(TAG, "request is null");
            return null;
        }
    }

    private XViewDownloadClient(Context context) {
        this.allRequestMap = new ConcurrentHashMap();
        this.mContext = context;
    }
}
