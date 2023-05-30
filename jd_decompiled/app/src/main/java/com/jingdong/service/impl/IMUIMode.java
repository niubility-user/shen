package com.jingdong.service.impl;

import android.content.Context;
import com.jingdong.service.BaseService;
import com.jingdong.service.callback.UIModeChangeListener;
import com.jingdong.service.service.UIModeService;
import java.util.HashMap;

/* loaded from: classes10.dex */
public class IMUIMode extends BaseService implements UIModeService {
    public HashMap getResMapByMode(Context context) {
        return null;
    }

    public boolean isDarkMode() {
        return false;
    }

    public boolean isLightMode() {
        return true;
    }

    public void removeUIModeChangeListener(UIModeChangeListener uIModeChangeListener) {
    }

    public void setOnUIModeChangeListener(UIModeChangeListener uIModeChangeListener) {
    }
}
