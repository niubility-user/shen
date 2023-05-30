package com.jd.lib.un.global.theme;

/* loaded from: classes16.dex */
public interface OnViewThemeConfig<T> {
    T darkMode();

    T elderMode();

    boolean isAutoDarkMode();

    boolean isAutoElderMode();

    boolean isDarkMode();

    boolean isElderMode();

    T normalMode();

    void refresh();

    T setAutoDarkMode(boolean z);

    T setAutoElderMode(boolean z);

    T setDarkMode(boolean z);

    T setElderMode(boolean z);
}
