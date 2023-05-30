package com.jd.dynamic.lib.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/* loaded from: classes13.dex */
public interface LifecycleEventObserverExtend extends LifecycleEventObserver {
    void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull EventExtend eventExtend);
}
