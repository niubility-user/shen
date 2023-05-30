package com.jdpay.net.http.converter;

import androidx.annotation.NonNull;
import com.jdpay.lib.converter.Converter;
import com.jdpay.net.http.HttpResponse;
import java.lang.reflect.Type;

/* loaded from: classes18.dex */
public interface HttpResponseConverter<OUTPUT> extends Converter<HttpResponse, OUTPUT> {
    Type getType();

    void setType(@NonNull Type type);
}
