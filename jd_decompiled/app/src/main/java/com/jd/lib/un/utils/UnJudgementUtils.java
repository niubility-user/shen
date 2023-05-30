package com.jd.lib.un.utils;

import java.util.Collection;

/* loaded from: classes16.dex */
public class UnJudgementUtils {
    public static boolean isNotEmptyArray(Object[] objArr) {
        return isNotNull(objArr) && objArr.length > 0;
    }

    public static boolean isNotEmptyCollection(Collection collection) {
        return isNotNull(collection) && collection.size() > 0;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }
}
