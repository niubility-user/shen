package com.jd.lib.un.utils.fold;

/* loaded from: classes16.dex */
public class Constants {
    static final String KEY_BOUNDS = "mBounds";
    static final String KEY_MAX_BOUNDS = "mMaxBounds";
    static final String SPLIT_SCREEN_CONFIG_STATUS_IN_VIVO = "multi-landscape";
    static final float SPLIT_THRESHOLDS = 0.1f;
    static final String STR_BOTTOM = "bottom";
    static final String STR_FULL = "full";
    static final String STR_LEFT = "left";
    static final String STR_RIGHT = "right";
    static final String STR_TOP = "top";
    static final String appBoundsRegex = "mBounds=Rect\\((-?\\d+),\\s(-?\\d+)\\s-\\s(\\d+),\\s(\\d+)\\)";
    static final String maxBoundsRegex = "mMaxBounds=Rect\\((\\d+),\\s(\\d+)\\s-\\s(\\d+),\\s(\\d+)\\)";
    static final String[] multiScreenTagArray = {"mWindowingMode=multi-window", "mWindowingMode=split-screen-primary", "mWindowingMode=split-screen-secondary"};
}
