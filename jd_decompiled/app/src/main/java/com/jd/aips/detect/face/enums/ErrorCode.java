package com.jd.aips.detect.face.enums;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes.dex */
public @interface ErrorCode {
    public static final int AKS_ENCRYPT_FAIL = 2002;
    public static final int FAIL_INIT_SDK = 2003;
    public static final int FAIL_LOAD_SO = 2004;
    public static final int NO_SUPPORT_CAMERA = 2001;
}
