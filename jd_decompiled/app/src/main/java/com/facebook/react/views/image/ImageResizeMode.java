package com.facebook.react.views.image;

import android.graphics.Shader;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.jd.dynamic.DYConstants;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ImageResizeMode {
    public static Shader.TileMode defaultTileMode() {
        return Shader.TileMode.CLAMP;
    }

    public static ScalingUtils.ScaleType defaultValue() {
        return ScalingUtils.ScaleType.CENTER_CROP;
    }

    public static ScalingUtils.ScaleType toScaleType(@Nullable String str) {
        if ("contain".equals(str)) {
            return ScalingUtils.ScaleType.FIT_CENTER;
        }
        if ("cover".equals(str)) {
            return ScalingUtils.ScaleType.CENTER_CROP;
        }
        if (DYConstants.DY_STRETCH.equals(str)) {
            return ScalingUtils.ScaleType.FIT_XY;
        }
        if (DYConstants.DY_CENTER.equals(str)) {
            return ScalingUtils.ScaleType.CENTER_INSIDE;
        }
        if ("repeat".equals(str)) {
            return ScaleTypeStartInside.INSTANCE;
        }
        if (str == null) {
            return defaultValue();
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + str + "'");
    }

    public static Shader.TileMode toTileMode(@Nullable String str) {
        if (!"contain".equals(str) && !"cover".equals(str) && !DYConstants.DY_STRETCH.equals(str) && !DYConstants.DY_CENTER.equals(str)) {
            if ("repeat".equals(str)) {
                return Shader.TileMode.REPEAT;
            }
            if (str == null) {
                return defaultTileMode();
            }
            throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + str + "'");
        }
        return Shader.TileMode.CLAMP;
    }
}
