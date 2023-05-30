package com.jdpay.json.gson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.jdpay.json.JsonObjectConverter;
import java.lang.reflect.Type;

/* loaded from: classes18.dex */
public class GsonObjectConverter<OUTPUT> extends JsonObjectConverter<String, OUTPUT> {
    final Gson gson;

    public GsonObjectConverter(Gson gson) {
        this.gson = gson;
    }

    @Override // com.jdpay.lib.converter.Converter
    public OUTPUT convert(@Nullable String str) {
        return (OUTPUT) this.gson.fromJson(str, getType());
    }

    public GsonObjectConverter(@NonNull Type type, Gson gson) {
        super(type);
        this.gson = gson;
    }
}
