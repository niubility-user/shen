package com.jd.dynamic.lib.lifecycle;

import android.content.Intent;
import androidx.annotation.Keep;

@Keep
/* loaded from: classes13.dex */
public interface LifecycleOwnerExtend {
    void addLifecycleEventObserver(LifecycleEventObserverExtend lifecycleEventObserverExtend);

    Intent getResultIntent();
}
