package com.jd.cashier.app.jdlibcutter.protocol.darkmode;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

/* loaded from: classes13.dex */
public interface IDarkMode {
    void addDeepDarkChangeListener(LifecycleOwner lifecycleOwner, Observer<Integer> observer, boolean z);

    int getDarkMode();

    boolean isDarkMode();

    void removeDeepDarkChangeListener(Observer<Integer> observer);
}
