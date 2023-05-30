package com.jingdong.app.mall.mylib.CouponUnit;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.sdk.lib.couponunit.R;

/* loaded from: classes4.dex */
public class CouponLabelIconItem extends LinearLayout {
    public CouponLabelIconItem(Context context) {
        super(context);
    }

    public void init(String str, String str2) {
        setOrientation(0);
        if (!TextUtils.isEmpty(str)) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DpiUtil.dip2px(getContext(), 12.0f), DpiUtil.dip2px(getContext(), 12.0f));
            layoutParams.rightMargin = DpiUtil.dip2px(getContext(), 2.0f);
            simpleDraweeView.setLayoutParams(layoutParams);
            simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (!str.startsWith("http") && !str.startsWith("https")) {
                Bitmap bitmap = UnIconConfigHelper.getBitmap(str);
                if (bitmap != null) {
                    addView(simpleDraweeView);
                    simpleDraweeView.setImageBitmap(bitmap);
                }
            } else {
                addView(simpleDraweeView);
                JDImageUtils.displayImage(str, simpleDraweeView);
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            TextView textView = new TextView(getContext());
            textView.setTextSize(10.0f);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.coupon_2e2d2d));
            textView.setText(str2);
            addView(textView);
        }
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return;
        }
        setPadding(0, 0, DpiUtil.dip2px(getContext(), 12.0f), 0);
    }

    public CouponLabelIconItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
