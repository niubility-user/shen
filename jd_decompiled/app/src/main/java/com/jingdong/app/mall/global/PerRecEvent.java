package com.jingdong.app.mall.global;

import com.jingdong.cleanmvp.common.BaseEvent;

/* loaded from: classes3.dex */
public class PerRecEvent extends BaseEvent {
    public static final String PER_REC_TYPE = "PERSONAL_RECOMMEND_TYPE";
    public boolean currentStatus;

    public PerRecEvent(boolean z) {
        super(PER_REC_TYPE);
        this.currentStatus = true;
        this.currentStatus = z;
    }
}
