package com.jingdong.app.mall.bundle.jd_component.guide;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes2.dex */
public class GuideLayout extends FrameLayout {
    private GuideDrawable background;
    private Context mContext;

    public GuideLayout(@NonNull Context context) {
        this(context, null);
    }

    private void initView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        GuideDrawable guideDrawable = new GuideDrawable(getBackground());
        this.background = guideDrawable;
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(guideDrawable);
        } else {
            setBackgroundDrawable(guideDrawable);
        }
    }

    public static GuideLayout with(Context context) {
        return new GuideLayout(context);
    }

    public GuideLayout addGuideView(View view, Point point2) {
        return addGuideView(view, new FrameLayout.LayoutParams(-2, -2), point2);
    }

    public int dp2Px(Context context, float f2) {
        return (int) ((f2 * BaseInfo.getDensity()) + 0.5f);
    }

    public GuideLayout resetBackgroundHoleArea(RectF rectF) {
        resetBackgroundHoleArea(rectF, dp2Px(this.mContext, 10.0f));
        return this;
    }

    public GuideLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GuideLayout addGuideView(View view, FrameLayout.LayoutParams layoutParams, Point point2) {
        if (view != null && layoutParams != null && point2 != null) {
            addView(view, layoutParams);
            view.setX(point2.x);
            view.setY(point2.y);
        }
        return this;
    }

    public GuideLayout resetBackgroundHoleArea(RectF rectF, float f2) {
        Path srcPath = this.background.getSrcPath();
        srcPath.reset();
        if (rectF != null) {
            srcPath.addRoundRect(rectF, f2, f2, Path.Direction.CW);
        }
        return this;
    }

    public GuideLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        initView(context, attributeSet, i2);
    }
}
