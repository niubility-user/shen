package com.jd.hybrid.downloader;

import androidx.annotation.Keep;

@Keep
/* loaded from: classes13.dex */
public class FileError extends Exception {
    protected boolean isDowngradeError;
    private int statusCode;
    private String url;

    public FileError(int i2, String str) {
        super(str);
        this.isDowngradeError = false;
        this.statusCode = 0;
        this.statusCode = i2;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isDowngradeError() {
        return this.isDowngradeError;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public FileError(int i2, String str, Throwable th) {
        super(str, th);
        this.isDowngradeError = false;
        this.statusCode = 0;
        this.statusCode = i2;
    }

    public FileError(int i2, Throwable th, String str) {
        super(th);
        this.isDowngradeError = false;
        this.statusCode = 0;
        this.statusCode = i2;
        this.url = str;
    }

    public FileError(int i2, Throwable th) {
        super(th);
        this.isDowngradeError = false;
        this.statusCode = 0;
        this.statusCode = i2;
        this.url = null;
    }
}
