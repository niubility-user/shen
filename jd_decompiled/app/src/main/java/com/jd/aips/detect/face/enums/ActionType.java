package com.jd.aips.detect.face.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface ActionType {
    public static final int TYPE_BLINK = 1003;
    public static final int TYPE_MOUTH = 1002;
    public static final int TYPE_NODE = 1005;
    public static final int TYPE_NONE = 1000;
    public static final int TYPE_PREPARE = 1001;
    public static final int TYPE_SHAKE = 1004;
}
