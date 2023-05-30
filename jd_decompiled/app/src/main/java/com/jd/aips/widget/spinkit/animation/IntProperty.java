package com.jd.aips.widget.spinkit.animation;

import android.util.Property;

/* loaded from: classes12.dex */
public abstract class IntProperty<T> extends Property<T, Integer> {
    public IntProperty(String str) {
        super(Integer.class, str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.util.Property
    public /* bridge */ /* synthetic */ void set(Object obj, Integer num) {
        set2((IntProperty<T>) obj, num);
    }

    public abstract void setValue(T t, int i2);

    /* renamed from: set  reason: avoid collision after fix types in other method */
    public final void set2(T t, Integer num) {
        setValue(t, num.intValue());
    }
}
