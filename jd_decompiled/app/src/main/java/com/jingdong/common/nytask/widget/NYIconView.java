package com.jingdong.common.nytask.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.nytask.NYIconEntity;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public class NYIconView extends DraggableView {
    public static final String TAG = "NYIconView";
    private Context mContext;
    private SimpleDraweeView mSimpleDraweeView;

    public NYIconView(@NonNull Context context) {
        this(context, null);
    }

    private void init() {
    }

    @Override // com.jingdong.common.nytask.widget.DraggableView
    protected void drawContent(Canvas canvas) {
    }

    public NYIconView gravity(int i2) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).gravity = i2;
        }
        return this;
    }

    public void initWH() {
        setWH(DPIUtil.dip2px(50.0f), DPIUtil.dip2px(50.0f));
        setExtraHorizontalSpace(DPIUtil.dip2px(20.0f));
        setExtraVerticalSpace(DPIUtil.dip2px(20.0f));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (Log.D) {
            Log.e(TAG, "onAttachedToWindow: \u6267\u884c");
        }
        if (this.mSimpleDraweeView == null) {
            this.mSimpleDraweeView = new SimpleDraweeView(this.mContext);
            addView(this.mSimpleDraweeView, new ViewGroup.LayoutParams(-1, -1));
        }
    }

    public void setIconData(final NYIconEntity.Data data) {
        if (data == null) {
            return;
        }
        SimpleDraweeView simpleDraweeView = this.mSimpleDraweeView;
        if (simpleDraweeView != null) {
            JDImageUtils.displayImage(data.indexImage, simpleDraweeView);
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.nytask.widget.NYIconView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JumpUtil.execJump(NYIconView.this.mContext, data.ruleJump, 0);
            }
        });
    }

    public NYIconView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NYIconView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        init();
    }
}
