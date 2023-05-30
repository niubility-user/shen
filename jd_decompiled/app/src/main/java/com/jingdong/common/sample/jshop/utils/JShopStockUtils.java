package com.jingdong.common.sample.jshop.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.sample.jshop.Entity.JShopStock;

/* loaded from: classes6.dex */
public class JShopStockUtils {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.common.sample.jshop.utils.JShopStockUtils$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jingdong$common$sample$jshop$Entity$JShopStock;

        static {
            int[] iArr = new int[JShopStock.values().length];
            $SwitchMap$com$jingdong$common$sample$jshop$Entity$JShopStock = iArr;
            try {
                iArr[JShopStock.INSTOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jingdong$common$sample$jshop$Entity$JShopStock[JShopStock.OFFSHELF.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jingdong$common$sample$jshop$Entity$JShopStock[JShopStock.SOLDOUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static void setDrawableAlphaWithStockState(Drawable drawable, JShopStock jShopStock, boolean z) {
        if (drawable != null) {
            if (!z) {
                drawable.mutate();
            }
            int i2 = AnonymousClass1.$SwitchMap$com$jingdong$common$sample$jshop$Entity$JShopStock[jShopStock.ordinal()];
            if (i2 == 1) {
                drawable.setAlpha(255);
            } else if (i2 != 2 && i2 != 3) {
                drawable.setAlpha(255);
            } else {
                drawable.setAlpha(128);
            }
        }
    }

    public static void setImageViewAlphaWithStockState(ImageView imageView, JShopStock jShopStock) {
        if (imageView != null) {
            int i2 = AnonymousClass1.$SwitchMap$com$jingdong$common$sample$jshop$Entity$JShopStock[jShopStock.ordinal()];
            if (i2 == 1) {
                imageView.setAlpha(255);
            } else if (i2 != 2 && i2 != 3) {
                imageView.setAlpha(255);
            } else {
                imageView.setAlpha(128);
            }
        }
    }

    public static void setTextColorWithStockState(TextView textView, JShopStock jShopStock, int i2) {
        if (textView != null) {
            int i3 = AnonymousClass1.$SwitchMap$com$jingdong$common$sample$jshop$Entity$JShopStock[jShopStock.ordinal()];
            if (i3 == 1) {
                textView.setTextColor(i2);
            } else if (i3 != 2 && i3 != 3) {
                textView.setTextColor(i2);
            } else {
                textView.setTextColor(textView.getContext().getResources().getColor(R.color.e0));
            }
            textView.invalidate();
        }
    }

    public static void setTextLabelDrawableAlphaWithStockState(TextView textView, JShopStock jShopStock) {
        if (textView != null) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            if (compoundDrawables != null) {
                for (Drawable drawable : compoundDrawables) {
                    if (drawable != null) {
                        setDrawableAlphaWithStockState(drawable, jShopStock, false);
                    }
                }
            }
            textView.invalidate();
        }
    }
}
