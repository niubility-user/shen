package com.jingdong.service.service;

import android.content.Context;
import com.jingdong.service.callback.UIModeChangeListener;
import java.util.HashMap;

/* loaded from: classes10.dex */
public interface UIModeService {
    HashMap getResMapByMode(Context context);

    boolean isDarkMode();

    boolean isLightMode();

    void removeUIModeChangeListener(UIModeChangeListener uIModeChangeListener);

    void setOnUIModeChangeListener(UIModeChangeListener uIModeChangeListener);
}
