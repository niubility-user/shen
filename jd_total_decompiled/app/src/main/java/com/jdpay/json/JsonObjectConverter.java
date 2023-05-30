package com.jdpay.json;

import androidx.annotation.NonNull;
import com.jdpay.lib.converter.Converter;
import java.lang.reflect.Type;

/* loaded from: classes18.dex */
public abstract class JsonObjectConverter<INPUT, OUTPUT> implements Converter<INPUT, OUTPUT> {
    private Type type;

    public JsonObjectConverter() {
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public JsonObjectConverter(@NonNull Type type) {
        this.type = type;
    }
}
