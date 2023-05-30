package com.tencent.tencentmap.net;

import com.tencent.map.tools.net.exception.NetErrorException;

/* loaded from: classes9.dex */
public class NetResponse {
    public String charset;
    public byte[] data;
    public int errorCode;
    public byte[] errorData;
    public Exception exception;
    public int statusCode;

    public NetResponse() {
        this.errorCode = -1;
        this.charset = "GBK";
    }

    public NetResponse(com.tencent.map.tools.net.NetResponse netResponse) {
        this.errorCode = -1;
        this.charset = "GBK";
        if (netResponse != null) {
            this.errorCode = netResponse.errorCode;
            this.statusCode = netResponse.statusCode;
            this.data = netResponse.data;
            this.charset = netResponse.charset;
            this.exception = netResponse.exception;
            this.errorData = netResponse.errorData;
        }
    }

    public NetResponse(Exception exc) {
        this.errorCode = -1;
        this.charset = "GBK";
        this.exception = exc;
        if (exc instanceof NetErrorException) {
            NetErrorException netErrorException = (NetErrorException) exc;
            this.errorCode = netErrorException.errorCode;
            this.statusCode = netErrorException.statusCode;
        }
    }

    public boolean available() {
        byte[] bArr;
        return (this.errorCode == 0 && this.statusCode == 200) || ((bArr = this.data) != null && bArr.length > 0);
    }

    public String toString() {
        try {
            return this.data != null ? new String(this.data, this.charset) : "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }
}
