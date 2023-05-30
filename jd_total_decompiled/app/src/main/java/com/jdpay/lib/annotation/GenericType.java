package com.jdpay.lib.annotation;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes18.dex */
public class GenericType implements ParameterizedType {
    private final Class raw;
    private final Type[] types;

    public GenericType(Class cls, Type... typeArr) {
        this.raw = cls;
        this.types = typeArr;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return this.types;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return null;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return this.raw;
    }
}
