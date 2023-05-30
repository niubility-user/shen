package com.jd.lib.productdetail.mainimage.utils;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager2.widget.ViewPager2;

/* loaded from: classes15.dex */
public class ZoomOutPageTransformer implements ViewPager2.PageTransformer {
    public View a;
    public int b = 0;

    @Override // androidx.viewpager2.widget.ViewPager2.PageTransformer
    public void transformPage(View view, float f2) {
        if (f2 >= -1.0f && f2 <= 1.0f) {
            int i2 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
            if (i2 == 0) {
                this.b = 0;
            }
            if (f2 < 0.0f && (view.getTag() instanceof Integer)) {
                this.b = ((Integer) view.getTag()).intValue();
            }
            if (i2 <= 0 || this.b <= 0) {
                return;
            }
            int intValue = ((Integer) view.getTag()).intValue();
            int i3 = this.b;
            if (intValue > i3) {
                int i4 = (int) ((intValue - i3) * (1.0f - f2));
                View view2 = this.a;
                if (view2 != null) {
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    layoutParams.height = this.b + i4;
                    this.a.setLayoutParams(layoutParams);
                }
            } else if (intValue < i3) {
                int i5 = (int) ((intValue - i3) * (1.0f - f2));
                View view3 = this.a;
                if (view3 != null) {
                    ViewGroup.LayoutParams layoutParams2 = view3.getLayoutParams();
                    layoutParams2.height = this.b + i5;
                    this.a.setLayoutParams(layoutParams2);
                }
            }
        }
    }
}
