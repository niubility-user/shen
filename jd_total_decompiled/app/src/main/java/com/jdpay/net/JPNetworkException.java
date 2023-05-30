package com.jdpay.net;

import androidx.annotation.NonNull;
import com.jdpay.exception.JPException;

/* loaded from: classes18.dex */
public class JPNetworkException extends JPException {
    protected Response response;

    public JPNetworkException(@NonNull String str) {
        super(str);
    }

    public Response getResponse() {
        return this.response;
    }

    public JPNetworkException(@NonNull Response response) {
        super(response.getString());
        this.response = response;
    }
}
