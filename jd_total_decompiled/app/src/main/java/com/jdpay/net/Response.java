package com.jdpay.net;

import androidx.annotation.NonNull;
import com.jdpay.lib.io.ProgressInputStream;
import com.jdpay.lib.listener.ProgressListener;
import com.jdpay.net.Request;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes18.dex */
public abstract class Response<REQUEST extends Request> {
    public static final int DEFAULT_PROGRESS_INTERVAL = 500;
    protected InputStream inputStream;
    protected ProgressListener listener;
    protected int progressInterval = 500;
    protected final REQUEST request;

    /* JADX INFO: Access modifiers changed from: protected */
    public Response(@NonNull REQUEST request) {
        this.request = request;
    }

    public void close() {
        InputStream inputStream = this.inputStream;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    public InputStream getInputStream() {
        if (this.inputStream == null) {
            this.inputStream = this.listener == null ? getRawInputStream() : new ProgressInputStream(getRawInputStream(), this.listener, this.progressInterval);
        }
        return this.inputStream;
    }

    public int getProgressInterval() {
        return this.progressInterval;
    }

    protected abstract InputStream getRawInputStream();

    public REQUEST getRequest() {
        return this.request;
    }

    public abstract String getString();

    public void setProgressInterval(int i2) {
        this.progressInterval = i2;
    }

    public void setProgressListener(ProgressListener progressListener) {
        this.listener = progressListener;
    }
}
