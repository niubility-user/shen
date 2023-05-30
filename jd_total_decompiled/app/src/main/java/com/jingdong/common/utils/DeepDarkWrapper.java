package com.jingdong.common.utils;

import androidx.lifecycle.Observer;

/* loaded from: classes6.dex */
public class DeepDarkWrapper implements Observer<Integer> {
    private DeepDarkObserver deepDarkObserver;
    private Object object;

    public DeepDarkWrapper(Object obj, DeepDarkObserver deepDarkObserver) {
        this.object = obj;
        this.deepDarkObserver = deepDarkObserver;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(Integer num) {
        DeepDarkObserver deepDarkObserver;
        Object obj = this.object;
        if (obj == null || (deepDarkObserver = this.deepDarkObserver) == null) {
            return;
        }
        deepDarkObserver.onUIModeChanged(obj, num.intValue());
    }
}
