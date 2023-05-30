package com.jd.aips.common.utils;

import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/* loaded from: classes12.dex */
public class FsGsonUtil {
    private static final Gson a = new GsonBuilder().disableHtmlEscaping().create();

    private FsGsonUtil() {
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        return (T) a.fromJson(str, (Class) cls);
    }

    public static String toJson(@NonNull Object obj) {
        return a.toJson(obj);
    }
}
