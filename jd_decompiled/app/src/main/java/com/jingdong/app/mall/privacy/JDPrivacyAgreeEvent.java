package com.jingdong.app.mall.privacy;

import com.jingdong.cleanmvp.common.BaseEvent;

/* loaded from: classes4.dex */
public class JDPrivacyAgreeEvent extends BaseEvent {
    public static final String PRIVACY_AGREE_TYPE = "PRIVACY_AGREE_TYPE";

    public JDPrivacyAgreeEvent() {
        super(PRIVACY_AGREE_TYPE, PRIVACY_AGREE_TYPE);
    }
}
