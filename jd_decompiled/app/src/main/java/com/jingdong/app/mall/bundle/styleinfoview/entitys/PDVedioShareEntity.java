package com.jingdong.app.mall.bundle.styleinfoview.entitys;

/* loaded from: classes3.dex */
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
