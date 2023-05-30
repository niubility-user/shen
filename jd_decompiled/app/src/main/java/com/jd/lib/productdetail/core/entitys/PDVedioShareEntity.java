package com.jd.lib.productdetail.core.entitys;

/* loaded from: classes15.dex */
public class PDVedioShareEntity {
    public boolean isPlay = false;
    public boolean isDefaultStyle = true;
    public boolean isInBar = true;
    public boolean isFirstFrame = true;
    public boolean isMianTab = true;

    public void reset() {
        this.isFirstFrame = true;
        this.isPlay = false;
        this.isDefaultStyle = true;
        this.isInBar = true;
    }
}
