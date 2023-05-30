package com.jingdong.common.web;

import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes6.dex */
public class WebLoginStateCount {
    private static AtomicInteger apiLoginCount = new AtomicInteger(0);
    private static AtomicInteger apiLogoutCount = new AtomicInteger(0);
    private static AtomicInteger broadcastLoginCount = new AtomicInteger(0);
    private static AtomicInteger broadcastLogoutCount = new AtomicInteger(0);

    public static void addApiLoginCount() {
        apiLoginCount.incrementAndGet();
    }

    public static void addApiLogoutCount() {
        apiLogoutCount.incrementAndGet();
    }

    public static void addBroadCastLoginCount() {
        broadcastLoginCount.incrementAndGet();
    }

    public static void addBroadCastLogoutCount() {
        broadcastLogoutCount.incrementAndGet();
    }
}
