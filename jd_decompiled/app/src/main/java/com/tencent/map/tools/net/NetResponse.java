package com.tencent.map.tools.net;

import com.tencent.map.tools.net.exception.NetErrorException;
import com.tencent.map.tools.net.processor.Processor;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class NetResponse {
    public String contentEncoding;
    public int contentLength;
    public byte[] data;
    public InputStream dataStream;
    public byte[] errorData;
    public Exception exception;
    public long mRequestId;
    public String requestUrl;
    private List<Processor> respProcessorSet;
    public int statusCode;
    public int errorCode = -1;
    public String charset = "GBK";
    public final Map<String, String> respHeads = new HashMap();

    public NetResponse() {
    }

    public NetResponse(NetRequest netRequest) {
        this.mRequestId = netRequest.mRequestId;
        this.respProcessorSet = netRequest.processors;
        this.requestUrl = netRequest.url;
    }

    public boolean available() {
        byte[] bArr;
        return (this.errorCode == 0 && this.statusCode == 200) || ((bArr = this.data) != null && bArr.length > 0);
    }

    public void clone(NetResponse netResponse) {
        if (netResponse != null) {
            this.errorCode = netResponse.errorCode;
            this.statusCode = netResponse.statusCode;
            this.data = netResponse.data;
            this.charset = netResponse.charset;
            this.exception = netResponse.exception;
            this.errorData = netResponse.errorData;
        }
    }

    public void exception(Exception exc) {
        this.exception = exc;
        if (exc instanceof NetErrorException) {
            NetErrorException netErrorException = (NetErrorException) exc;
            this.errorCode = netErrorException.errorCode;
            this.statusCode = netErrorException.statusCode;
        }
    }

    public String getData() {
        return this.data != null ? new String(this.data) : "";
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String getErrorMessage() {
        if (this.errorData != null) {
            return new String(this.errorData);
        }
        Exception exc = this.exception;
        return exc != null ? exc.getMessage() : "";
    }

    public String getHeaderField(String str) {
        return this.respHeads.get(str);
    }

    public InputStream getInputStream() {
        return this.dataStream;
    }

    public List<Processor> getProcessors() {
        return this.respProcessorSet;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String toHumanString() {
        StringBuilder sb;
        String str;
        if (this.data != null) {
            sb = new StringBuilder();
            sb.append("[DATA] ");
            sb.append(this.data.length / 1024.0f);
            sb.append("KB ");
            str = new String(this.data, Charset.forName("utf-8"));
        } else if (this.errorData == null) {
            return "";
        } else {
            Exception exc = this.exception;
            if (exc != null) {
                exc.printStackTrace();
            }
            sb = new StringBuilder();
            sb.append("[ERROR] ");
            str = new String(this.errorData);
        }
        sb.append(str);
        return sb.toString();
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
