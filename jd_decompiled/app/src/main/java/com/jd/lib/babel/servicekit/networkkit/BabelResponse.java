package com.jd.lib.babel.servicekit.networkkit;

import java.util.Map;

/* loaded from: classes13.dex */
public class BabelResponse {
    private int code;
    private byte[] inputData;
    private Map<String, Object> moreParams;
    private String resultJson;

    public BabelResponse(String str) {
        this.resultJson = str;
    }

    public int getCode() {
        return this.code;
    }

    public byte[] getInputData() {
        return this.inputData;
    }

    public Map<String, Object> getMoreParams() {
        return this.moreParams;
    }

    public String getResultJson() {
        return this.resultJson;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public void setInputData(byte[] bArr) {
        this.inputData = bArr;
    }

    public void setMoreParams(Map<String, Object> map) {
        this.moreParams = map;
    }

    public void setResultJson(String str) {
        this.resultJson = str;
    }
}
