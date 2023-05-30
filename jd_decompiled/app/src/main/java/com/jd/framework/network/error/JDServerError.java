package com.jd.framework.network.error;

import com.jd.framework.network.JDNetworkResponse;

/* loaded from: classes13.dex */
public class JDServerError extends JDError {
    public JDServerError(JDNetworkResponse jDNetworkResponse) {
        super(jDNetworkResponse);
    }

    public JDServerError() {
    }
}
