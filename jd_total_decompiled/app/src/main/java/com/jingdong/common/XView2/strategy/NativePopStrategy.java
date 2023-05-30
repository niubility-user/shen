package com.jingdong.common.XView2.strategy;

import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import java.lang.ref.SoftReference;

/* loaded from: classes5.dex */
public class NativePopStrategy extends BasePopStrategy {
    public NativePopStrategy(XViewConfigEntity.TargetsEntity targetsEntity, SoftReference softReference, XView2 xView2) {
        super(targetsEntity, softReference, xView2);
    }

    @Override // com.jingdong.common.XView2.strategy.BasePopStrategy
    boolean checkIsCanPop() {
        return true;
    }
}
