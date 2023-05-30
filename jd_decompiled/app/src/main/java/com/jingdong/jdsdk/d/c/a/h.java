package com.jingdong.jdsdk.d.c.a;

import com.jingdong.common.BaseFrameUtil;
import com.jingdong.common.frame.IMainActivity;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.platform.lib.openapi.ui.ICartIcon;

/* loaded from: classes14.dex */
public class h implements ICartIcon {
    private static h a = new h();

    private h() {
    }

    public static synchronized h a() {
        h hVar;
        synchronized (h.class) {
            if (a == null) {
                a = new h();
            }
            hVar = a;
        }
        return hVar;
    }

    @Override // com.jingdong.sdk.platform.lib.openapi.ui.ICartIcon
    public void validateCartIcon() {
        try {
            IMainActivity mainFrameActivity = BaseFrameUtil.getInstance().getMainFrameActivity();
            if (mainFrameActivity != null) {
                mainFrameActivity.validateCartIcon();
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("PlatformCartIcon", e2);
            }
        }
    }
}
