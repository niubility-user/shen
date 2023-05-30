package com.jingdong.aura.wrapper.listener;

import android.app.Activity;

/* loaded from: classes.dex */
public interface AuraDebugTimeListener {
    void afterCallActivityOnCreate(Activity activity);

    void beforeCallActivityOnCreate(Activity activity);
}
