package com.alibaba.android.patronus;

import android.content.Context;

/* loaded from: classes.dex */
public final class Patrons {
    private static volatile boolean hasInit;

    /* loaded from: classes.dex */
    public static class PatronsConfig {
        public boolean debuggable = false;
        public boolean auto = true;
        public float periodOfShrink = 0.76f;
        public int shrinkStep = 125;
        public int periodOfCheck = 30;
        public int lowerLimit = 384;
        public boolean fixHuaweiBinderAbort = false;
        public boolean recordInitResult = true;

        public String toString() {
            return "{ debuggable=" + this.debuggable + ", auto=" + this.auto + ", periodOfShrink=" + this.periodOfShrink + ", shrinkStep=" + this.shrinkStep + ", periodOfCheck=" + this.periodOfCheck + ", lowerLimit=" + this.lowerLimit + ", recordInitResult=" + this.recordInitResult + " }";
        }
    }

    private Patrons() {
    }

    public static String dumpNativeLogs() {
        return _Patrons.dumpNativeLogs(true);
    }

    public static long getRegionSpaceSize() {
        if (hasInit) {
            return _Patrons.getCurrentRegionSpaceSize();
        }
        return -1L;
    }

    public static void inBackground() {
        if (hasInit) {
            _Patrons.inBackground();
        }
    }

    public static int init(Context context, PatronsConfig patronsConfig) {
        if (hasInit) {
            return 0;
        }
        int init = _Patrons.init(context, patronsConfig);
        hasInit = init == 0;
        return init;
    }

    public static long readVssSize() {
        return _Patrons.readVssSize();
    }

    public static boolean shrinkRegionSpace(int i2) {
        if (hasInit) {
            return _Patrons.shrinkRegionSpace(i2);
        }
        return false;
    }

    public static void toForeground() {
        if (hasInit) {
            _Patrons.toForeground();
        }
    }
}
