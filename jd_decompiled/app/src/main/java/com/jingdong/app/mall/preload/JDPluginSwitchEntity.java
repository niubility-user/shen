package com.jingdong.app.mall.preload;

import java.util.List;

/* loaded from: classes4.dex */
public class JDPluginSwitchEntity {
    public Priority highPriority;
    public Priority lowPriority;
    public String mainSwitch = "no";
    public long idleTimesDelay = 10000;

    /* loaded from: classes4.dex */
    public static class Priority {
        public int minMemory;
        public List<String> plugins;

        public List<String> getPlugins() {
            return this.plugins;
        }
    }

    public Priority getHighPriority() {
        return this.highPriority;
    }

    public Priority getLowPriority() {
        return this.lowPriority;
    }
}
