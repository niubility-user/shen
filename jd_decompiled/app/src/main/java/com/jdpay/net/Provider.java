package com.jdpay.net;

import androidx.annotation.NonNull;
import com.jdpay.lib.converter.Converter;
import com.jdpay.net.Request;
import com.jdpay.net.Request.Builder;
import com.jdpay.net.Response;
import com.jdpay.net.Result;
import java.io.IOException;

/* loaded from: classes18.dex */
public interface Provider<REQUEST extends Request, CONVERTER extends Converter, RESPONSE extends Response<REQUEST>, BUILDER extends Request.Builder<REQUEST, CONVERTER>, RESULT extends Result> {
    REQUEST cancel(int i2);

    void enqueue(@NonNull REQUEST request, @NonNull RESULT result);

    RESPONSE execute(@NonNull REQUEST request) throws IOException;

    BUILDER obtainBuilder();
}
