package com.jingdong.common.jdreactFramework.helper;

/* loaded from: classes5.dex */
public interface ElderModeHelper {

    /* loaded from: classes5.dex */
    public interface ElderModeChangeListener {
        void onChange(int i2);
    }

    /* loaded from: classes5.dex */
    public interface Unregister {
        void unregister();
    }

    String getColors();

    int getCurrentElderMode();

    float getTextSize(float f2);

    Unregister onRegisterElderModeChangeListener(ElderModeChangeListener elderModeChangeListener);
}
