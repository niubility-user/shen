package com.jd.aips.detect.face.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface LiveMode {
    public static final int MODE_ACTION = 1001;
    public static final int MODE_SILENCE = 1000;
    public static final int MODE_SILENCE_AND_ACTION = 1002;
}
