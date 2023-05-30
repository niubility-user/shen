package com.jdpay.json;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* loaded from: classes18.dex */
public interface JsonConverterFactory {
    JsonObjectConverter convertObject(@NonNull Type type, @Nullable NameStrategy nameStrategy);

    JsonStringConverter convertString(@Nullable NameStrategy nameStrategy, @Nullable Class<? extends Annotation>[] clsArr, @Nullable Class<? extends Annotation>[] clsArr2);
}
