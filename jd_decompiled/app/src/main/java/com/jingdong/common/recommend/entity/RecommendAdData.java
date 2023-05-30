package com.jingdong.common.recommend.entity;

/* loaded from: classes.dex */
public class RecommendAdData {
    public String client_click_url;
    public String client_exposal_url;
    public String extension_id;
    public String iconAd;
    public boolean isMonetized;
    public String source = "0";
    public boolean hasRealExpo = false;
    public transient boolean isFromCache = false;

    public boolean showAdDot() {
        return "1".equals(this.source) && !this.isMonetized;
    }

    public boolean showMonetizedDot() {
        return "1".equals(this.source) && this.isMonetized;
    }
}
