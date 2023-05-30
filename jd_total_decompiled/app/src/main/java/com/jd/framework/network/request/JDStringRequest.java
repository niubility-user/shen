package com.jd.framework.network.request;

import com.jd.framework.network.JDResponseListener;

/* loaded from: classes13.dex */
public class JDStringRequest extends JDRequest<String> {
    /* JADX WARN: Multi-variable type inference failed */
    public JDStringRequest(String str, JDResponseListener<String> jDResponseListener) {
        super(str);
        this.mResponseListener = jDResponseListener;
    }

    @Override // com.jd.framework.network.request.JDRequest
    public JDResponseListener<String> getResponseListener() {
        return this.mResponseListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jd.framework.network.request.JDRequest
    public void setResponseListener(JDResponseListener<String> jDResponseListener) {
        this.mResponseListener = jDResponseListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JDStringRequest(int i2, String str, JDResponseListener<String> jDResponseListener) {
        super(i2, str);
        this.mResponseListener = jDResponseListener;
    }
}
