package com.jd.framework.network.error;

import com.jd.framework.network.JDNetworkResponse;

/* loaded from: classes13.dex */
public class JDNetworkError extends JDError {
    public JDNetworkError() {
    }

    public JDNetworkError(Throwable th) {
        super(th);
    }

    public JDNetworkError(JDNetworkResponse jDNetworkResponse) {
        super(jDNetworkResponse);
    }
}
