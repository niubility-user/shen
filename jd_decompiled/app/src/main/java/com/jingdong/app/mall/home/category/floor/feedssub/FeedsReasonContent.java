package com.jingdong.app.mall.home.category.floor.feedssub;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import com.jingdong.app.mall.home.floor.common.g;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes4.dex */
public class FeedsReasonContent extends LinearLayout {
    private GradientTextView mReasonText;

    public FeedsReasonContent(@NonNull Context context) {
        super(context);
        int dip2px = DPIUtil.dip2px(5.0f);
        int dip2px2 = DPIUtil.dip2px(1.0f);
        g gVar = new g(context, true);
        gVar.h();
        gVar.c(true);
        gVar.e(11);
        gVar.n(2, 10.0f);
        gVar.g(dip2px, dip2px2, dip2px, dip2px2);
        this.mReasonText = gVar.b();
        setOrientation(0);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = DPIUtil.dip2px(6.0f);
        addView(this.mReasonText, layoutParams);
    }

    public void bindData(com.jingdong.app.mall.home.n.g.g gVar) {
        String M = gVar.M();
        boolean isEmpty = TextUtils.isEmpty(M);
        a.a(this, isEmpty ? 0 : -2, -1, 0);
        if (isEmpty) {
            return;
        }
        this.mReasonText.setText(M);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, gVar.L());
        gradientDrawable.setCornerRadius(DPIUtil.dip2px(3.0f));
        this.mReasonText.setBackgroundDrawable(gradientDrawable);
        this.mReasonText.setTextGradient(GradientTextView.GradientType.LeftToRight, gVar.N());
    }
}
