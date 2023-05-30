package com.tencent.xweb.util;

import com.jd.dynamic.DYConstants;

/* loaded from: classes9.dex */
public class BSpatch {
    static {
        LibraryEngine.loadLibrary("bspatch_utils");
    }

    public static int h(String str, String str2, String str3) {
        String str4 = "doPatch oldFile:" + str + ",patchFile:" + str2 + ",newFile:" + str3;
        System.currentTimeMillis();
        if (str.equals(str3)) {
            str3 = str + DYConstants.TEMP_NAME_PREFIX;
        }
        return new BSpatch().nativeDoPatch(str, str2, str3);
    }

    public native int nativeDoPatch(String str, String str2, String str3);
}
