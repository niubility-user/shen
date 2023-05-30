package com.facebook.drawee.backends.pipeline.debug;

import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;

/* loaded from: classes.dex */
public class DebugOverlayImageOriginColor {
    private static final Map<Integer, Integer> IMAGE_ORIGIN_COLOR_MAP;

    static {
        Integer valueOf = Integer.valueOf((int) SupportMenu.CATEGORY_MASK);
        Integer valueOf2 = Integer.valueOf((int) InputDeviceCompat.SOURCE_ANY);
        IMAGE_ORIGIN_COLOR_MAP = ImmutableMap.of(1, -7829368, 2, valueOf, 3, valueOf2, 4, valueOf2, 5, -16711936, 6, -16711936);
    }

    public static int getImageOriginColor(int i2) {
        Integer num = IMAGE_ORIGIN_COLOR_MAP.get(Integer.valueOf(i2));
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }
}
