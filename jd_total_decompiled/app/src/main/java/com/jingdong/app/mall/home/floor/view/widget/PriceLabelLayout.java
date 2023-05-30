package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.mall.home.floor.common.i.m;

/* loaded from: classes4.dex */
public class PriceLabelLayout extends LinearLayout {
    private PriceLabelView mLabelView;

    public PriceLabelLayout(Context context) {
        super(context);
        setOrientation(0);
        setGravity(17);
        PriceLabelView priceLabelView = new PriceLabelView(context);
        this.mLabelView = priceLabelView;
        priceLabelView.c(22, 18, 1, 7);
        addView(this.mLabelView, new RelativeLayout.LayoutParams(-2, -1));
    }

    public void setLabelPrice(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        this.mLabelView.d(m.o(str, -243846), com.jingdong.app.mall.home.category.floor.feedssub.a.d(str2, OrderISVUtil.MONEY_DECIMAL, 0.71f), 160);
    }
}
