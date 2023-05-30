package com.googlecode.mp4parser.util;

/* loaded from: classes12.dex */
public class CastUtils {
    public static int l2i(long j2) {
        if (j2 > 2147483647L || j2 < -2147483648L) {
            throw new RuntimeException("A cast to int has gone wrong. Please contact the mp4parser discussion group (" + j2 + ")");
        }
        return (int) j2;
    }
}
