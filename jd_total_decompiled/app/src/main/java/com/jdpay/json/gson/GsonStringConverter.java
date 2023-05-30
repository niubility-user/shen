package com.jdpay.json.gson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.jdpay.json.JsonStringConverter;
import java.lang.reflect.Type;

/* loaded from: classes18.dex */
public class GsonStringConverter<INPUT> implements JsonStringConverter<INPUT> {
    final Gson gson;
    final Type type;

    public GsonStringConverter(Gson gson) {
        this(gson, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jdpay.lib.converter.Converter
    public /* bridge */ /* synthetic */ String convert(@Nullable Object obj) throws Throwable {
        return convert2((GsonStringConverter<INPUT>) obj);
    }

    public GsonStringConverter(@NonNull Gson gson, @Nullable Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override // com.jdpay.lib.converter.Converter
    /* renamed from: convert  reason: avoid collision after fix types in other method */
    public String convert2(@Nullable INPUT input) {
        Gson gson = this.gson;
        Type type = this.type;
        if (type == null) {
            type = input.getClass();
        }
        return gson.toJson(input, type);
    }
}
