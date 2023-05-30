package com.jingdong.common.XView2.strategy;

import android.app.Activity;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import java.lang.ref.SoftReference;

/* loaded from: classes5.dex */
public class RNPopStrategy extends BasePopStrategy {
    public RNPopStrategy(XViewConfigEntity.TargetsEntity targetsEntity, SoftReference<Activity> softReference, XView2 xView2) {
        super(targetsEntity, softReference, xView2);
    }

    @Override // com.jingdong.common.XView2.strategy.BasePopStrategy
    boolean checkIsCanPop() {
        XViewLogPrint.JDXLog.e(XView2Constants.TAG, "run-XView2-Pop---RN");
        return true;
    }
}
