package com.jd.hybrid.downloader;

import androidx.annotation.Keep;
import java.io.File;

@Keep
/* loaded from: classes13.dex */
public class FileRequest {
    public String fileName;
    private int mMethod;
    private e<File> mResponseListener;
    private String mUrl;
    private String referer;
    public String relativeDirPath;
    private String savePath;

    public FileRequest(int i2, String str) {
        this.mMethod = i2;
        this.mUrl = str;
    }

    public int getMethod() {
        return this.mMethod;
    }

    public String getReferer() {
        return this.referer;
    }

    public e<File> getResponseListener() {
        return this.mResponseListener;
    }

    public String getSavePath() {
        return this.savePath;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setMethod(int i2) {
        this.mMethod = i2;
    }

    public void setReferer(String str) {
        this.referer = str;
    }

    public void setResponseListener(e<File> eVar) {
        this.mResponseListener = eVar;
    }

    public void setSavePath(String str) {
        this.savePath = str;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }
}
