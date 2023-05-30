package com.eclipsesource.v8.utils;

/* loaded from: classes.dex */
public abstract class SingleTypeAdapter implements TypeAdapter {
    private int typeToAdapt;

    public SingleTypeAdapter(int i2) {
        this.typeToAdapt = i2;
    }

    @Override // com.eclipsesource.v8.utils.TypeAdapter
    public Object adapt(int i2, Object obj) {
        if (i2 == this.typeToAdapt) {
            return adapt(obj);
        }
        return TypeAdapter.DEFAULT;
    }

    public abstract Object adapt(Object obj);
}
