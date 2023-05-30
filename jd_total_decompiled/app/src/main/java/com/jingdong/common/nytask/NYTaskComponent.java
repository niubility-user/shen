package com.jingdong.common.nytask;

import android.content.Context;
import android.view.ViewGroup;
import com.jingdong.common.nytask.NYIconEntity;
import com.jingdong.common.nytask.inter.NYTaskExternalCallback;

/* loaded from: classes5.dex */
public class NYTaskComponent {
    private NYTaskComponentProxy mProxy;

    private NYTaskComponent(Context context) {
        this(context, null);
    }

    private void init(Context context, ViewGroup viewGroup, int i2, NYTaskParams nYTaskParams) {
        this.mProxy = new NYTaskComponentProxy(context, viewGroup, i2, nYTaskParams);
    }

    public void pauseTimeDown() {
        NYTaskComponentProxy nYTaskComponentProxy = this.mProxy;
        if (nYTaskComponentProxy != null) {
            nYTaskComponentProxy.pauseTimeDown();
        }
    }

    public void resumeTimeDown() {
        NYTaskComponentProxy nYTaskComponentProxy = this.mProxy;
        if (nYTaskComponentProxy != null) {
            nYTaskComponentProxy.resumeTimeDown();
        }
    }

    public void setExternalListener(NYTaskExternalCallback nYTaskExternalCallback) {
        NYTaskComponentProxy nYTaskComponentProxy = this.mProxy;
        if (nYTaskComponentProxy != null) {
            nYTaskComponentProxy.setExternalListener(nYTaskExternalCallback);
        }
    }

    public void setFloatViewGravity(int i2) {
        NYTaskComponentProxy nYTaskComponentProxy = this.mProxy;
        if (nYTaskComponentProxy != null) {
            nYTaskComponentProxy.setFloatViewGravity(i2);
        }
    }

    public void setIconData(NYIconEntity.Data data) {
        NYTaskComponentProxy nYTaskComponentProxy = this.mProxy;
        if (nYTaskComponentProxy != null) {
            nYTaskComponentProxy.setIconData(data);
        }
    }

    public void setInitPoint(float f2, float f3) {
        NYTaskComponentProxy nYTaskComponentProxy = this.mProxy;
        if (nYTaskComponentProxy != null) {
            nYTaskComponentProxy.setInitPoint(f2, f3);
        }
    }

    public void startTimeDown(long j2) {
        NYTaskComponentProxy nYTaskComponentProxy = this.mProxy;
        if (nYTaskComponentProxy != null) {
            nYTaskComponentProxy.startTimeDown(j2);
        }
    }

    private NYTaskComponent(Context context, ViewGroup viewGroup) {
        this(context, viewGroup, 1, null);
    }

    public NYTaskComponent(Context context, ViewGroup viewGroup, @NYTaskStyle int i2, NYTaskParams nYTaskParams) {
        init(context, viewGroup, i2, nYTaskParams);
    }
}
