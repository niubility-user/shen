package com.jd.framework.network.request;

import com.jd.framework.network.JDResponseListener;

/* loaded from: classes13.dex */
public class JDByteArrayRequest extends JDRequest<byte[]> {
    private JDResponseListener<byte[]> mResponseListener;

    public JDByteArrayRequest(int i2, String str) {
        super(i2, str);
    }

    @Override // com.jd.framework.network.request.JDRequest
    public JDResponseListener<byte[]> getResponseListener() {
        return this.mResponseListener;
    }

    @Override // com.jd.framework.network.request.JDRequest
    public void setResponseListener(JDResponseListener<byte[]> jDResponseListener) {
        this.mResponseListener = jDResponseListener;
    }

    public JDByteArrayRequest(int i2, String str, JDResponseListener<byte[]> jDResponseListener) {
        super(i2, str);
        this.mResponseListener = jDResponseListener;
    }
}
