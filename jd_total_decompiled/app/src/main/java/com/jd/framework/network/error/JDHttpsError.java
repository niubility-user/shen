package com.jd.framework.network.error;

import com.android.volley.error.HttpsError;
import com.android.volley.error.VolleyError;

/* loaded from: classes13.dex */
public class JDHttpsError extends JDError {

    /* loaded from: classes13.dex */
    public static class JDHttpsDomainError extends JDHttpsError {
        public JDHttpsDomainError(Throwable th) {
            super(th);
        }

        public JDHttpsDomainError(HttpsError.HttpsDomainError httpsDomainError) {
            super((HttpsError) httpsDomainError);
        }
    }

    /* loaded from: classes13.dex */
    public static class JDHttpsIPError extends JDHttpsError {
        public JDHttpsIPError(Throwable th) {
            super(th);
        }

        public JDHttpsIPError(HttpsError.HttpsIPError httpsIPError) {
            super((HttpsError) httpsIPError);
        }
    }

    public JDHttpsError(Throwable th) {
        super(th);
    }

    public JDHttpsError(HttpsError httpsError) {
        super((VolleyError) httpsError);
    }
}
