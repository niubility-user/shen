package com.jingdong.common.recommend.entity;

/* loaded from: classes6.dex */
public class RecommendTipsEvent {
    public static final int RECOMMEND_TIPS_FORM = 1;
    public static final int RECOMMEND_TIPS_NOFORM = 0;
    public static final int RECOMMEND_TIPS_OTHER = 3;
    public boolean isClick;
    public int mKey;

    public RecommendTipsEvent(boolean z, int i2) {
        this.isClick = false;
        this.isClick = z;
        this.mKey = i2;
    }
}
