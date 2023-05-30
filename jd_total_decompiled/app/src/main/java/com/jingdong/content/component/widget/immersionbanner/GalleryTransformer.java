package com.jingdong.content.component.widget.immersionbanner;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import com.jingdong.content.component.R;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes12.dex */
public class GalleryTransformer implements ViewPager.PageTransformer {
    public static final float D_SCALE = 0.18f;
    public static final float MAX_SCALE = 1.0f;
    private static final float MAX_TRANSLATIONX = DPIUtil.dip2px(12.0f);
    public static final float MIN_ALPHA = 0.5f;
    public static final float MIN_SCALE = 0.82f;
    private final String TAG = "GalleryTransformer";
    public float mViewPageWidth;
    public int showChildCount;

    public GalleryTransformer(float f2, int i2) {
        this.mViewPageWidth = 1.0f;
        this.showChildCount = 1;
        this.mViewPageWidth = f2;
        this.showChildCount = i2;
    }

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(@NonNull View view, float f2) {
        float f3;
        float f4;
        view.setClickable(true);
        float f5 = 1.0f;
        float f6 = f2 * (1.0f / this.mViewPageWidth);
        float abs = Math.abs(f6);
        View findViewById = view.findViewById(R.id.selected_view);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        if (abs >= 3.0f) {
            f3 = MAX_TRANSLATIONX;
            view.setClickable(false);
            f5 = 0.64f;
            f4 = 0.0f;
        } else if (abs > 2.0f) {
            int i2 = this.showChildCount;
            f4 = i2 < 5 ? 0.0f : 3.0f - abs;
            float f7 = MAX_TRANSLATIONX;
            view.setClickable(i2 >= 5);
            f3 = f7;
            f5 = 0.64f;
        } else {
            if (abs <= 1.0f) {
                f5 = ((1.0f - abs) * 0.18f) + 0.82f;
            } else if (abs <= 2.0f) {
                int i3 = this.showChildCount;
                float f8 = i3 < 5 ? 2.0f - abs : 1.0f;
                float f9 = ((2.0f - abs) * 0.18f) + 0.64f;
                float f10 = (abs - 1.0f) * MAX_TRANSLATIONX;
                view.setClickable(i3 >= 5);
                f3 = f10;
                f5 = f9;
                f4 = f8;
            }
            f3 = 0.0f;
            f4 = 1.0f;
        }
        if (abs <= 0.19d && findViewById != null) {
            findViewById.setVisibility(0);
        }
        view.setScaleX(f5);
        view.setScaleY(f5);
        view.setAlpha(f4);
        if (f6 > 0.0f) {
            view.setTranslationX(f3 * (-1.0f));
        } else {
            view.setTranslationX(f3);
        }
    }
}
