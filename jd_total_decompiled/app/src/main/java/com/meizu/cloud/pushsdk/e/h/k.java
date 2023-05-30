package com.meizu.cloud.pushsdk.e.h;

import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes14.dex */
public final class k {
    private static j a;
    private static long b;

    private k() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static j a() {
        synchronized (k.class) {
            j jVar = a;
            if (jVar != null) {
                a = jVar.f15860f;
                jVar.f15860f = null;
                b -= 2048;
                return jVar;
            }
            return new j();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(j jVar) {
        if (jVar.f15860f != null || jVar.f15861g != null) {
            throw new IllegalArgumentException();
        }
        if (jVar.d) {
            return;
        }
        synchronized (k.class) {
            long j2 = b + 2048;
            if (j2 > IjkMediaMeta.AV_CH_TOP_BACK_CENTER) {
                return;
            }
            b = j2;
            jVar.f15860f = a;
            jVar.f15858c = 0;
            jVar.b = 0;
            a = jVar;
        }
    }
}
