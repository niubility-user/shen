package com.jd.framework.network.error;

import com.android.volley.error.VolleyError;

/* loaded from: classes13.dex */
public class JDJsonExceptionError extends JDError {
    public boolean isParseError;

    public JDJsonExceptionError(VolleyError volleyError, boolean z) {
        super(volleyError);
        this.isParseError = z;
    }

    public boolean isParseError() {
        return this.isParseError;
    }
}
