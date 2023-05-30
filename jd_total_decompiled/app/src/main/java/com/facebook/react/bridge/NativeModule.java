package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nonnull;

@DoNotStrip
/* loaded from: classes.dex */
public interface NativeModule {

    /* loaded from: classes.dex */
    public interface NativeMethod {
        String getType();

        void invoke(JSInstance jSInstance, ReadableArray readableArray);
    }

    boolean canOverrideExistingModule();

    @Nonnull
    String getName();

    void initialize();

    void onCatalystInstanceDestroy();
}
