package com.jingdong.common.model.smarttablayout;

import com.jd.dynamic.DYConstants;

/* loaded from: classes5.dex */
public class AttrEnumPath {
    public static int getIndicatorGravity(String str) {
        if ("bottom".equals(str)) {
            return 0;
        }
        if ("top".equals(str)) {
            return 1;
        }
        return DYConstants.DY_CENTER.equals(str) ? 2 : 0;
    }

    public static int getIndicatorInterpolation(String str) {
        return "linear".equals(str) ? 1 : 0;
    }
}
