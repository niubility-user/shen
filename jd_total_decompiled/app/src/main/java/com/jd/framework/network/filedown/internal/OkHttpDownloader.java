package com.jd.framework.network.filedown.internal;

import android.content.Context;
import com.android.volley.toolbox.HttpStackFactory;
import com.android.volley.toolbox.OkHttpStack;
import com.jd.framework.network.filedown.internal.BaseDownloader;
import com.jd.framework.network.request.JDFileRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: classes13.dex */
public class OkHttpDownloader extends BaseDownloader<OkHttpStack> {
    public OkHttpDownloader(HttpStackFactory httpStackFactory) {
        super(httpStackFactory);
    }

    @Override // com.jd.framework.network.filedown.internal.BaseDownloader
    public BaseDownloader.DownloadResponse doHttpRequest(Context context, JDFileRequest jDFileRequest) throws Exception {
        InputStream byteStream;
        Response response = getHttpStack().getResponse(jDFileRequest);
        if (response != null) {
            String header = response.header("Content-Encoding");
            ResponseBody body = response.body();
            InputStream inputStream = null;
            try {
                if ("gzip".equals(header)) {
                    byteStream = new GZIPInputStream(body.byteStream());
                } else {
                    byteStream = body.byteStream();
                }
                inputStream = byteStream;
            } catch (IOException unused) {
            }
            return new BaseDownloader.DownloadResponse(response.code(), inputStream, response.body().contentLength());
        }
        throw new IOException("cannot read from null response");
    }

    @Override // com.jd.framework.network.filedown.internal.BaseDownloader
    public OkHttpStack getHttpStack() {
        return (OkHttpStack) getHttpStackFactory().getHttpStack(true);
    }
}
