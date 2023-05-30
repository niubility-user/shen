package com.jingdong.app.mall.home.floor.view.view.module;

import android.content.Context;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.i.m;
import com.jingdong.app.mall.home.floor.view.baseui.BaseMallFloor;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.app.mall.home.r.e.f;

/* loaded from: classes4.dex */
public class MallFloorModuleMixed extends MallFloorModuleCommon {
    public MallFloorModuleMixed(Context context, BaseMallFloor<?> baseMallFloor) {
        super(context, baseMallFloor);
    }

    public void addItemMixedShowName(f fVar, RelativeLayout relativeLayout) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(d.d(144), d.d(40));
        layoutParams.topMargin = d.d(40);
        layoutParams.leftMargin = d.d(20);
        RelativeLayout relativeLayout2 = new RelativeLayout(getContext());
        relativeLayout.addView(relativeLayout2, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13, 1);
        GradientTextView gradientTextView = new GradientTextView(getContext());
        gradientTextView.setMaxLines(1);
        gradientTextView.setText(fVar.O());
        gradientTextView.setTextSize(0, d.d(26));
        GradientTextView.GradientType gradientType = GradientTextView.GradientType.LeftToRight;
        gradientTextView.setTextGradient(gradientType, m.p(fVar.C(), -13684945, true));
        relativeLayout2.addView(gradientTextView, layoutParams2);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(d.d(144), d.d(40));
        layoutParams3.topMargin = d.d(100);
        layoutParams3.leftMargin = d.d(20);
        relativeLayout.addView(new RelativeLayout(getContext()), layoutParams3);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(13, 1);
        GradientTextView gradientTextView2 = new GradientTextView(getContext());
        gradientTextView2.setMaxLines(1);
        gradientTextView2.setText(fVar.P());
        gradientTextView2.setTextSize(0, d.d(26));
        gradientTextView2.setTextGradient(gradientType, m.p(fVar.C(), -13684945, true));
        relativeLayout.addView(gradientTextView2, layoutParams4);
    }
}
