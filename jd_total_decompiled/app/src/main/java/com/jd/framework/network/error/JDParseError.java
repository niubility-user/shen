package com.jd.framework.network.error;

import com.jd.framework.network.JDNetworkResponse;

/* loaded from: classes13.dex */
public class JDParseError extends JDError {
    public JDParseError() {
    }

    public JDParseError(JDNetworkResponse jDNetworkResponse) {
        super(jDNetworkResponse);
    }
}
