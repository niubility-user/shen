package com.jingdong.app.mall.bundle.dolphinlib.common.util;

/* loaded from: classes19.dex */
public class BaseUtil {
    public static final boolean IS_DEBUG = false;

    public static final boolean checkNull(Object... objArr) {
        if (objArr == null || objArr.length == 0) {
            return true;
        }
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }
}
