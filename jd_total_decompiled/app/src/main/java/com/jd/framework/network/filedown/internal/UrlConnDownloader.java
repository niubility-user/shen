package com.jd.framework.network.filedown.internal;

import android.content.Context;
import com.android.volley.toolbox.HttpStackFactory;
import com.android.volley.toolbox.HurlStack;
import com.jd.framework.network.filedown.internal.BaseDownloader;
import com.jd.framework.network.request.JDFileRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.zip.GZIPInputStream;

/* loaded from: classes13.dex */
public class UrlConnDownloader extends BaseDownloader<HurlStack> {
    public UrlConnDownloader(HttpStackFactory httpStackFactory) {
        super(httpStackFactory);
    }

    @Override // com.jd.framework.network.filedown.internal.BaseDownloader
    public BaseDownloader.DownloadResponse doHttpRequest(Context context, JDFileRequest jDFileRequest) throws Exception {
        InputStream errorStream;
        HttpURLConnection fileUrlConnection = getHttpStack().getFileUrlConnection(jDFileRequest);
        if (fileUrlConnection != null) {
            try {
                if ("gzip".equals(fileUrlConnection.getHeaderField("Content-Encoding"))) {
                    errorStream = new GZIPInputStream(fileUrlConnection.getInputStream());
                } else {
                    errorStream = fileUrlConnection.getInputStream();
                }
            } catch (IOException unused) {
                errorStream = fileUrlConnection.getErrorStream();
            }
            return new BaseDownloader.DownloadResponse(fileUrlConnection.getResponseCode(), errorStream, fileUrlConnection.getContentLength());
        }
        throw new IOException("cannot read from null conn");
    }

    @Override // com.jd.framework.network.filedown.internal.BaseDownloader
    public HurlStack getHttpStack() {
        return (HurlStack) getHttpStackFactory().getHttpStack(false);
    }
}
