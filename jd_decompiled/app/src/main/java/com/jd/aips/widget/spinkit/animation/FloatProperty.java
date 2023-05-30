package com.jd.aips.widget.spinkit.animation;

import android.util.Property;

/* loaded from: classes12.dex */
public abstract class FloatProperty<T> extends Property<T, Float> {
    public FloatProperty(String str) {
        super(Float.class, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.util.Property
    public /* bridge */ /* synthetic */ void set(Object obj, Float f2) {
        set2((FloatProperty<T>) obj, f2);
    }

    public abstract void setValue(T t, float f2);

    /* renamed from: set  reason: avoid collision after fix types in other method */
    public final void set2(T t, Float f2) {
        setValue(t, f2.floatValue());
    }
}
