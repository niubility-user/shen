package com.jd.aips.common.utils;

import java.util.UUID;

/* loaded from: classes12.dex */
public class RandomSessionIdUtil {
    public static String buildSessionId() {
        return System.currentTimeMillis() + UUID.randomUUID().toString();
    }
}
