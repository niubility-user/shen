package com.jdpay.net.http.converter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.net.http.HttpResponse;
import java.lang.reflect.Type;

/* loaded from: classes18.dex */
public class StringHttpResponseConverter implements HttpResponseConverter<String> {
    @Override // com.jdpay.net.http.converter.HttpResponseConverter
    public Type getType() {
        return null;
    }

    @Override // com.jdpay.net.http.converter.HttpResponseConverter
    public void setType(@NonNull Type type) {
    }

    @Override // com.jdpay.lib.converter.Converter
    public String convert(@Nullable HttpResponse httpResponse) {
        if (httpResponse != null) {
            return httpResponse.getString();
        }
        return null;
    }
}
