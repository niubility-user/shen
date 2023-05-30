package com.jingdong.sdk.jweb;

import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.Map;

/* loaded from: classes7.dex */
public class JWebResourceResponse {
    private String mEncoding;
    private boolean mImmutable;
    private InputStream mInputStream;
    private String mMimeType;
    private String mReasonPhrase;
    private Map<String, String> mResponseHeaders;
    private int mStatusCode;

    public JWebResourceResponse(String str, String str2, int i2, String str3, Map<String, String> map, InputStream inputStream) {
        this(str, str2, inputStream);
        setStatusCodeAndReasonPhrase(i2, str3);
        setResponseHeaders(map);
    }

    public JWebResourceResponse(String str, String str2, InputStream inputStream) {
        this.mMimeType = str;
        this.mEncoding = str2;
        setData(inputStream);
    }

    public JWebResourceResponse(boolean z, String str, String str2, int i2, String str3, Map<String, String> map, InputStream inputStream) {
        this.mImmutable = z;
        this.mMimeType = str;
        this.mEncoding = str2;
        this.mStatusCode = i2;
        this.mReasonPhrase = str3;
        this.mResponseHeaders = map;
        this.mInputStream = inputStream;
    }

    private void checkImmutable() {
    }

    public InputStream getData() {
        return this.mInputStream;
    }

    public String getEncoding() {
        return this.mEncoding;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public String getReasonPhrase() {
        return this.mReasonPhrase;
    }

    public Map<String, String> getResponseHeaders() {
        return this.mResponseHeaders;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public void setData(InputStream inputStream) {
        checkImmutable();
        if (inputStream != null) {
            StringBufferInputStream.class.isAssignableFrom(inputStream.getClass());
        }
        this.mInputStream = inputStream;
    }

    public void setEncoding(String str) {
        checkImmutable();
        this.mEncoding = str;
    }

    public void setMimeType(String str) {
        checkImmutable();
        this.mMimeType = str;
    }

    public void setResponseHeaders(Map<String, String> map) {
        checkImmutable();
        this.mResponseHeaders = map;
    }

    public void setStatusCodeAndReasonPhrase(int i2, String str) {
        this.mStatusCode = i2;
        this.mReasonPhrase = str;
    }
}
