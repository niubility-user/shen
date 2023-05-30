package com.jd.framework.network.error;

/* loaded from: classes13.dex */
public class JDFileDownloadError extends JDError {
    public JDFileDownloadError(String str, Throwable th, boolean z) {
        super(th, str);
        this.isDowngradeError = z;
    }

    public JDFileDownloadError(JDError jDError, boolean z) {
        super(jDError.getCause(), jDError.getUrl());
        this.isDowngradeError = z;
    }
}
