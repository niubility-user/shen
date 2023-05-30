package com.jingdong.common.XView2.strategy;

import android.app.Activity;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import java.lang.ref.SoftReference;

/* loaded from: classes5.dex */
public class PopStrategyManager {
    BasePopStrategy popStrategy;

    public BasePopStrategy buildPopStrategy(XViewConfigEntity.TargetsEntity targetsEntity, SoftReference<Activity> softReference, XView2 xView2) {
        if (targetsEntity == null) {
            return null;
        }
        int i2 = targetsEntity.targetType;
        if (i2 == 1) {
            this.popStrategy = new NativePopStrategy(targetsEntity, softReference, xView2);
        } else if (i2 == 2) {
            this.popStrategy = new WebPopStrategy(targetsEntity, softReference, xView2);
        } else if (i2 == 3) {
            this.popStrategy = new RNPopStrategy(targetsEntity, softReference, xView2);
        }
        return this.popStrategy;
    }

    public void destroy() {
        BasePopStrategy basePopStrategy = this.popStrategy;
        if (basePopStrategy != null) {
            basePopStrategy.destroy();
            this.popStrategy = null;
        }
    }

    public void reset() {
        BasePopStrategy basePopStrategy = this.popStrategy;
        if (basePopStrategy != null) {
            basePopStrategy.reset();
        }
    }

    public void start() {
        BasePopStrategy basePopStrategy = this.popStrategy;
        if (basePopStrategy != null) {
            basePopStrategy.start();
        }
    }

    public void stop() {
        BasePopStrategy basePopStrategy = this.popStrategy;
        if (basePopStrategy != null) {
            basePopStrategy.stop(true);
        }
    }

    public void upDateTarget(XViewConfigEntity.TargetsEntity targetsEntity) {
        BasePopStrategy basePopStrategy = this.popStrategy;
        if (basePopStrategy != null) {
            basePopStrategy.upDateTarget(targetsEntity);
        }
    }
}
