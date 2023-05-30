package com.jd.lib.flexcube.layout.entity.common;

/* loaded from: classes14.dex */
public class ScrollConfig {
    public String autoPlay;
    public String barBgColor;
    public String barBlockColor;
    public String barColor;
    public String showBar;

    public boolean canAutoPlay() {
        return "1".equals(this.autoPlay);
    }
}
