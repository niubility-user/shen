package com.jdpay.json.gson;

import androidx.annotation.NonNull;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.jdpay.json.JsonSerializerFilter;
import java.lang.annotation.Annotation;

/* loaded from: classes18.dex */
public abstract class GsonBaseStrategy implements ExclusionStrategy, JsonSerializerFilter<ExclusionStrategy> {
    protected final Class<? extends Annotation> targetAnnotation;

    public GsonBaseStrategy(@NonNull Class<? extends Annotation> cls) {
        this.targetAnnotation = cls;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.jdpay.json.JsonSerializerFilter
    public ExclusionStrategy getSerializerFilter() {
        return this;
    }

    protected abstract boolean isSkip(FieldAttributes fieldAttributes, Class<? extends Annotation> cls);

    @Override // com.google.gson.ExclusionStrategy
    public boolean shouldSkipClass(Class<?> cls) {
        return false;
    }

    @Override // com.google.gson.ExclusionStrategy
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return isSkip(fieldAttributes, this.targetAnnotation);
    }
}
