package com.jingdong.common.nytask.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.common.nytask.NYIconEntity;
import com.jingdong.common.nytask.NYTaskFinishEntity;
import com.jingdong.common.nytask.NYTaskParams;
import com.jingdong.common.nytask.NYTaskStyle;
import com.jingdong.common.nytask.widget.NYTimeDownView;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public class NYTaskRootView extends FrameLayout {
    public static final String TAG = "NYTaskRootView";
    private Context mContext;
    private NYIconView mIconView;
    private NYTimeDownComponent mTimeDownView;

    public NYTaskRootView(@NonNull Context context) {
        this(context, null);
    }

    private void initTaskIconView() {
        if (Log.D) {
            Log.e(TAG, "initTaskIconView \u521d\u59cb\u5316");
        }
        NYIconView gravity = new NYIconView(this.mContext).gravity(85);
        this.mIconView = gravity;
        addView(gravity);
        this.mIconView.initWH();
    }

    private void initTimeDownView(int i2, NYTaskParams nYTaskParams) {
        NYTimeDownComponent nYTimeDownComponent = new NYTimeDownComponent(this.mContext);
        this.mTimeDownView = nYTimeDownComponent;
        nYTimeDownComponent.initBgImg(nYTaskParams.mTimeDownBgUrl);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(DPIUtil.dip2px(56.0f), DPIUtil.dip2px(56.0f));
        layoutParams.gravity = i2;
        addView(this.mTimeDownView, layoutParams);
    }

    public void finishTaskOkay(NYTaskFinishEntity nYTaskFinishEntity) {
        NYTimeDownComponent nYTimeDownComponent = this.mTimeDownView;
        if (nYTimeDownComponent != null) {
            nYTimeDownComponent.finishTaskOkay(nYTaskFinishEntity);
        }
    }

    public NYTaskRootView initWithStyle(@NYTaskStyle int i2, NYTaskParams nYTaskParams) {
        if (i2 == 1) {
            initTimeDownView(51, nYTaskParams);
        } else if (i2 == 2) {
            initTaskIconView();
        }
        return this;
    }

    public void pauseTimeDown(boolean z) {
        NYTimeDownComponent nYTimeDownComponent = this.mTimeDownView;
        if (nYTimeDownComponent != null) {
            nYTimeDownComponent.pauseTimeDown(z);
        }
    }

    public void releaseResource() {
        NYTimeDownComponent nYTimeDownComponent = this.mTimeDownView;
        if (nYTimeDownComponent != null) {
            nYTimeDownComponent.releaseResource();
        }
        if (this.mContext != null) {
            this.mContext = null;
        }
    }

    public void resumeTimeDown(boolean z) {
        NYTimeDownComponent nYTimeDownComponent = this.mTimeDownView;
        if (nYTimeDownComponent != null) {
            nYTimeDownComponent.resumeTimeDown(z);
        }
    }

    public void setFloatViewGravity(int i2) {
        NYTimeDownComponent nYTimeDownComponent = this.mTimeDownView;
        if (nYTimeDownComponent != null) {
            nYTimeDownComponent.gravity(i2);
        }
        NYIconView nYIconView = this.mIconView;
        if (nYIconView != null) {
            nYIconView.gravity(i2);
        }
    }

    public void setIconData(NYIconEntity.Data data) {
        NYIconView nYIconView = this.mIconView;
        if (nYIconView != null) {
            nYIconView.setIconData(data);
        }
    }

    public void setInitPoint(float f2, float f3) {
        NYTimeDownComponent nYTimeDownComponent = this.mTimeDownView;
        if (nYTimeDownComponent != null) {
            nYTimeDownComponent.setInitPoint(f2, f3);
        }
        NYIconView nYIconView = this.mIconView;
        if (nYIconView != null) {
            nYIconView.setInitPoint(f2, f3);
        }
    }

    public void setTimeDownClickToJump(View.OnClickListener onClickListener) {
        NYTimeDownComponent nYTimeDownComponent = this.mTimeDownView;
        if (nYTimeDownComponent != null) {
            nYTimeDownComponent.setOnClickListener(onClickListener);
        }
    }

    public void setTimeDownListener(NYTimeDownView.TimeDownListener timeDownListener) {
        NYTimeDownComponent nYTimeDownComponent = this.mTimeDownView;
        if (nYTimeDownComponent != null) {
            nYTimeDownComponent.setTimeDownListener(timeDownListener);
        }
    }

    public void startTimeDown(long j2) {
        NYTimeDownComponent nYTimeDownComponent = this.mTimeDownView;
        if (nYTimeDownComponent != null) {
            nYTimeDownComponent.startTimeDown(j2);
        }
    }

    public NYTaskRootView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NYTaskRootView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
    }
}
