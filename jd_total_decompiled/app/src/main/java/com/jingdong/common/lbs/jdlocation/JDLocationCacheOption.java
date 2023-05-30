package com.jingdong.common.lbs.jdlocation;

/* loaded from: classes.dex */
public class JDLocationCacheOption extends JDLocationOption {
    private String provider;

    public String getProvider() {
        if (!"gps".equals(this.provider) && !"network".equals(this.provider)) {
            this.provider = "gps";
        }
        return this.provider;
    }

    public void setProvider(String str) {
        this.provider = str;
    }
}
