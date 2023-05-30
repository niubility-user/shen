package com.jingdong.common.jdreactFramework.helper;

/* loaded from: classes5.dex */
public interface UIModeHelper {

    /* loaded from: classes5.dex */
    public interface UIModeChangeListener {
        void onChange(int i2, String str);
    }

    /* loaded from: classes5.dex */
    public interface Unregister {
        void unregister();
    }

    int getCurrentUIMode();

    String getUIModeName(int i2);

    Unregister onRegisterUIModeChangeListener(UIModeChangeListener uIModeChangeListener);
}
