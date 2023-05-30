package com.tencent.smtt.export.external.interfaces;

import android.net.http.SslCertificate;

/* loaded from: classes9.dex */
public interface SslError {
    boolean addError(int i2);

    SslCertificate getCertificate();

    int getPrimaryError();

    String getUrl();

    boolean hasError(int i2);
}
