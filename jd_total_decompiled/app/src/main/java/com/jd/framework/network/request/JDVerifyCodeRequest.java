package com.jd.framework.network.request;

import com.jd.framework.network.JDResponseListener;

/* loaded from: classes13.dex */
public class JDVerifyCodeRequest extends JDByteArrayRequest {
    public JDVerifyCodeRequest(int i2, String str) {
        super(i2, str);
    }

    public JDVerifyCodeRequest(int i2, String str, JDResponseListener<byte[]> jDResponseListener) {
        super(i2, str, jDResponseListener);
    }
}
