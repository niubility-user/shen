package com.jingdong.pdj.libdjbasecore.utils;

import androidx.annotation.Nullable;

/* loaded from: classes7.dex */
public class DJCastUtil {
    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static <T> T convert(Object obj) {
        if (obj != 0) {
            return obj;
        }
        return null;
    }
}
