package com.facebook.react.bridge;

import java.util.ArrayList;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface ReadableArray {
    @Nullable
    ReadableArray getArray(int i2);

    boolean getBoolean(int i2);

    double getDouble(int i2);

    @Nonnull
    Dynamic getDynamic(int i2);

    int getInt(int i2);

    @Nullable
    ReadableMap getMap(int i2);

    @Nullable
    String getString(int i2);

    @Nonnull
    ReadableType getType(int i2);

    boolean isNull(int i2);

    int size();

    @Nonnull
    ArrayList<Object> toArrayList();
}
