package com.jdpay.json.gson;

import androidx.annotation.NonNull;
import com.google.gson.FieldAttributes;
import java.lang.annotation.Annotation;

/* loaded from: classes18.dex */
public class GsonExclusionStrategy extends GsonBaseStrategy {
    public GsonExclusionStrategy(@NonNull Class<? extends Annotation> cls) {
        super(cls);
    }

    @Override // com.jdpay.json.gson.GsonBaseStrategy
    public boolean isSkip(@NonNull FieldAttributes fieldAttributes, @NonNull Class<? extends Annotation> cls) {
        return fieldAttributes.getAnnotation(cls) != null;
    }
}
