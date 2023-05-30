package com.jingdong.common.XView2.strategy;

import android.app.Activity;
import android.os.Handler;
import android.os.HandlerThread;
import com.jingdong.common.XView2.XView2;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.XView2.entity.XViewConfigEntity;
import com.jingdong.common.XView2.layer.LayerTypeManager;
import com.jingdong.common.XView2.utils.XView2Utils;
import com.jingdong.common.XView2.utils.log.XViewLogPrint;
import java.lang.ref.SoftReference;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes5.dex */
public abstract class BasePopStrategy implements IXView2PopCallBack {
    private static Handler handler;
    protected static HandlerThread handlerThread;
    protected SoftReference<Activity> activity;
    protected String targetName;
    protected XViewConfigEntity.TargetsEntity targetsEntity;
    protected XView2 xView2;
    private AtomicBoolean isReading = new AtomicBoolean(false);
    private AtomicBoolean reportExc = new AtomicBoolean(false);
    protected int timeOutTimes = 0;
    private Runnable runnable = new Runnable() { // from class: com.jingdong.common.XView2.strategy.BasePopStrategy.1
        {
            BasePopStrategy.this = this;
        }

        @Override // java.lang.Runnable
        public void run() {
            LayerTypeManager layerTypeManager;
            LayerTypeManager layerTypeManager2;
            try {
                BasePopStrategy basePopStrategy = BasePopStrategy.this;
                int i2 = basePopStrategy.timeOutTimes + 1;
                basePopStrategy.timeOutTimes = i2;
                if (i2 > 15) {
                    basePopStrategy.stop(false);
                    BasePopStrategy basePopStrategy2 = BasePopStrategy.this;
                    XView2 xView2 = basePopStrategy2.xView2;
                    if (xView2 != null && (layerTypeManager2 = xView2.mLayerTypeManager) != null) {
                        XViewConfigEntity.TargetsEntity targetsEntity = basePopStrategy2.targetsEntity;
                        layerTypeManager2.reportException(1, "renderTimeOut", targetsEntity != null ? String.valueOf(targetsEntity.targetId) : "");
                    }
                }
                BasePopStrategy basePopStrategy3 = BasePopStrategy.this;
                XView2Utils.renderRatio(basePopStrategy3.activity, basePopStrategy3.xView2);
                if (BasePopStrategy.this.isPopXView2()) {
                    XViewLogPrint.JDXLog.e(XView2Constants.TAG, "checkIsCanPop");
                    XView2 xView22 = BasePopStrategy.this.xView2;
                    if (xView22 != null) {
                        xView22.startXView2();
                    }
                    BasePopStrategy.this.stop(false);
                }
            } catch (Exception e2) {
                BasePopStrategy.this.stop(true);
                e2.printStackTrace();
                XView2 xView23 = BasePopStrategy.this.xView2;
                if (xView23 != null && (layerTypeManager = xView23.mLayerTypeManager) != null) {
                    layerTypeManager.reportException(1, "popStrategyException", e2.getMessage());
                }
            }
            if (BasePopStrategy.this.isReading.get()) {
                BasePopStrategy.handler.postDelayed(BasePopStrategy.this.runnable, 1000L);
            } else {
                BasePopStrategy.this.stop(false);
            }
        }
    };

    public BasePopStrategy(XViewConfigEntity.TargetsEntity targetsEntity, SoftReference softReference, XView2 xView2) {
        if (handlerThread == null) {
            HandlerThread handlerThread2 = new HandlerThread("XView2-Pop");
            handlerThread = handlerThread2;
            handlerThread2.start();
            handler = new Handler(handlerThread.getLooper());
        }
        this.activity = softReference;
        this.targetsEntity = targetsEntity;
        this.xView2 = xView2;
        this.targetName = targetsEntity.targetName;
    }

    abstract boolean checkIsCanPop();

    public void destroy() {
        XView2 xView2;
        LayerTypeManager layerTypeManager;
        if (this.reportExc.get() && (xView2 = this.xView2) != null && (layerTypeManager = xView2.mLayerTypeManager) != null) {
            XViewConfigEntity.TargetsEntity targetsEntity = this.targetsEntity;
            layerTypeManager.reportException(1, "renderBreak", targetsEntity != null ? String.valueOf(targetsEntity.targetId) : "");
        }
        this.activity = null;
        this.targetsEntity = null;
        this.xView2 = null;
    }

    @Override // com.jingdong.common.XView2.strategy.IXView2PopCallBack
    public boolean isPopXView2() {
        return isSuccessRenderRatio() && checkIsCanPop();
    }

    @Override // com.jingdong.common.XView2.strategy.IXView2PopCallBack
    public boolean isSuccessRenderRatio() {
        XViewConfigEntity.TargetsEntity targetsEntity = this.targetsEntity;
        if (targetsEntity == null) {
            return false;
        }
        double d = XView2Utils.renderRatio;
        double d2 = targetsEntity.blankRateAfterLoad;
        Double.isNaN(d2);
        return d < d2 / 100.0d && d > 0.0d;
    }

    public void reset() {
        this.isReading.set(false);
        this.reportExc.set(false);
        handler.removeCallbacks(this.runnable);
        this.timeOutTimes = 0;
        XView2Utils.renderRatio = 0.0d;
    }

    public BasePopStrategy start() {
        if (!this.isReading.get()) {
            this.isReading.set(true);
            handler.removeCallbacks(this.runnable);
            handler.postDelayed(this.runnable, 1000L);
        }
        return this;
    }

    public void stop(boolean z) {
        if (this.isReading.get()) {
            this.isReading.set(false);
            handler.removeCallbacks(this.runnable);
            if (z) {
                this.reportExc.set(true);
            }
        }
        this.timeOutTimes = 0;
        XView2Utils.renderRatio = 0.0d;
    }

    public void upDateTarget(XViewConfigEntity.TargetsEntity targetsEntity) {
        this.targetsEntity = targetsEntity;
    }
}
