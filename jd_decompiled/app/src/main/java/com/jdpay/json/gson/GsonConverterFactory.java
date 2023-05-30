package com.jdpay.json.gson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.GsonBuilder;
import com.jdpay.json.JsonConverterFactory;
import com.jdpay.json.JsonObjectConverter;
import com.jdpay.json.JsonStringConverter;
import com.jdpay.json.NameStrategy;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* loaded from: classes18.dex */
public class GsonConverterFactory implements JsonConverterFactory {
    @Override // com.jdpay.json.JsonConverterFactory
    public JsonObjectConverter convertObject(@NonNull Type type, @Nullable NameStrategy nameStrategy) {
        GsonBuilder excludeFieldsWithModifiers = new GsonBuilder().excludeFieldsWithModifiers(16, 128, 8);
        if (nameStrategy != null) {
            excludeFieldsWithModifiers.setFieldNamingStrategy((GsonNameStrategy) nameStrategy);
        }
        GsonObjectConverter gsonObjectConverter = new GsonObjectConverter(excludeFieldsWithModifiers.create());
        gsonObjectConverter.setType(type);
        return gsonObjectConverter;
    }

    @Override // com.jdpay.json.JsonConverterFactory
    public JsonStringConverter convertString(@Nullable NameStrategy nameStrategy, @Nullable Class<? extends Annotation>[] clsArr, @Nullable Class<? extends Annotation>[] clsArr2) {
        GsonBuilder excludeFieldsWithModifiers = new GsonBuilder().excludeFieldsWithModifiers(16, 128, 8);
        if (nameStrategy != null) {
            excludeFieldsWithModifiers.setFieldNamingStrategy((GsonNameStrategy) nameStrategy);
        }
        if (clsArr != null) {
            for (Class<? extends Annotation> cls : clsArr) {
                excludeFieldsWithModifiers.addSerializationExclusionStrategy(new GsonInclusionStrategy(cls));
            }
        }
        if (clsArr2 != null) {
            for (Class<? extends Annotation> cls2 : clsArr2) {
                excludeFieldsWithModifiers.addSerializationExclusionStrategy(new GsonExclusionStrategy(cls2));
            }
        }
        return new GsonStringConverter(excludeFieldsWithModifiers.create());
    }
}
