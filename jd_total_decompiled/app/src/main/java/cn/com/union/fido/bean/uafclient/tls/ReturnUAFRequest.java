package cn.com.union.fido.bean.uafclient.tls;

import cn.com.union.fido.bean.uafclient.Operation;

/* loaded from: classes.dex */
public class ReturnUAFRequest {
    private long lifetimeMillis;
    private Operation op;
    private long statusCode;
    private String uafRequest;

    public long getLifetimeMillis() {
        return this.lifetimeMillis;
    }

    public Operation getOp() {
        return this.op;
    }

    public long getStatusCode() {
        return this.statusCode;
    }

    public String getUafRequest() {
        return this.uafRequest;
    }

    public void setLifetimeMillis(long j2) {
        this.lifetimeMillis = j2;
    }

    public void setOp(Operation operation) {
        this.op = operation;
    }

    public void setStatusCode(long j2) {
        this.statusCode = j2;
    }

    public void setUafRequest(String str) {
        this.uafRequest = str;
    }
}
