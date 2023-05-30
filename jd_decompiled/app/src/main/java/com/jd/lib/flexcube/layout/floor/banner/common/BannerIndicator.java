package com.jd.lib.flexcube.layout.floor.banner.common;

import android.content.Context;
import android.widget.LinearLayout;
import androidx.core.internal.view.SupportMenu;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.flexcube.iwidget.b.b;

/* loaded from: classes14.dex */
public class BannerIndicator extends LinearLayout {
    public static int BannerCardSpace = 24;
    public static int BannerSelectWidth = 39;
    public static int BannerWidthHeight = 12;
    private int pointColor;
    private int pointSelectColor;
    private int pointSelectWidth;
    private int pointSpace;
    private int pointWH;

    public BannerIndicator(Context context) {
        super(context);
        this.pointWH = BannerWidthHeight;
        this.pointSpace = BannerCardSpace;
        this.pointSelectWidth = BannerSelectWidth;
        setGravity(17);
        this.pointColor = com.jd.lib.flexcube.iwidget.b.a.a("rgba(0,0,0,0.5)", -7829368);
        this.pointSelectColor = com.jd.lib.flexcube.iwidget.b.a.a(JDDarkUtil.COLOR_FA2C19, SupportMenu.CATEGORY_MASK);
    }

    public void initIndicatorCount(int i2, float f2, int i3, int i4) {
        setVisibility(i2 > 1 ? 0 : 8);
        removeAllViews();
        this.pointSelectColor = i3;
        this.pointColor = i4;
        this.pointWH = b.d(BannerWidthHeight, f2);
        this.pointSpace = b.d(BannerCardSpace, f2);
        this.pointSelectWidth = b.d(BannerSelectWidth, f2);
        for (int i5 = 0; i5 < i2; i5++) {
            if (i5 == 0) {
                BannerIndicatorPoint bannerIndicatorPoint = new BannerIndicatorPoint(getContext());
                bannerIndicatorPoint.a(i3);
                bannerIndicatorPoint.b(this.pointWH / 2);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.pointSelectWidth, this.pointWH);
                if (i5 != i2 - 1) {
                    layoutParams.rightMargin = this.pointSpace;
                }
                bannerIndicatorPoint.setLayoutParams(layoutParams);
                addView(bannerIndicatorPoint);
            } else {
                BannerIndicatorPoint bannerIndicatorPoint2 = new BannerIndicatorPoint(getContext());
                bannerIndicatorPoint2.a(this.pointColor);
                bannerIndicatorPoint2.b(this.pointWH / 2);
                int i6 = this.pointWH;
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i6, i6);
                if (i5 != i2 - 1) {
                    layoutParams2.rightMargin = this.pointSpace;
                }
                bannerIndicatorPoint2.setLayoutParams(layoutParams2);
                addView(bannerIndicatorPoint2);
            }
        }
    }

    public void selectIndicator(int i2) {
        int childCount = getChildCount();
        if (i2 < 0 || i2 >= childCount) {
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            BannerIndicatorPoint bannerIndicatorPoint = (BannerIndicatorPoint) getChildAt(i3);
            if (i2 == i3) {
                bannerIndicatorPoint.a(this.pointSelectColor);
                bannerIndicatorPoint.b(this.pointWH / 2);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.pointSelectWidth, this.pointWH);
                if (i3 != childCount - 1) {
                    layoutParams.rightMargin = this.pointSpace;
                }
                bannerIndicatorPoint.setLayoutParams(layoutParams);
            } else {
                bannerIndicatorPoint.a(this.pointColor);
                bannerIndicatorPoint.b(this.pointWH / 2);
                int i4 = this.pointWH;
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i4, i4);
                if (i3 != childCount - 1) {
                    layoutParams2.rightMargin = this.pointSpace;
                }
                bannerIndicatorPoint.setLayoutParams(layoutParams2);
            }
        }
    }

    public void setSelectColor(int i2) {
        this.pointSelectColor = i2;
    }
}
