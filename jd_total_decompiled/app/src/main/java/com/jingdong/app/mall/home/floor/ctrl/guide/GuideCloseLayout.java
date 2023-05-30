package com.jingdong.app.mall.home.floor.ctrl.guide;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.common.R;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.wireless.iconfont.widget.IconImageView;

/* loaded from: classes4.dex */
public class GuideCloseLayout extends LinearLayout {
    public static final int HEIGHT = 32;
    private IconImageView mCloseView;
    private TextView mTimeText;

    public GuideCloseLayout(Context context) {
        super(context);
        setOrientation(0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(1325400064);
        gradientDrawable.setCornerRadius(d.d(32) >> 1);
        setBackgroundDrawable(gradientDrawable);
        TextView textView = new TextView(context);
        this.mTimeText = textView;
        textView.setGravity(17);
        this.mTimeText.setTextSize(0, d.d(20));
        this.mTimeText.setIncludeFontPadding(false);
        this.mTimeText.setTextColor(-1);
        this.mTimeText.setTypeface(FontsUtil.getTypeFace(context));
        f fVar = new f(32, 32);
        View view = this.mTimeText;
        addView(view, fVar.i(view));
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setColor(1593835520);
        gradientDrawable2.setCornerRadius(fVar.h() >> 1);
        this.mTimeText.setBackgroundDrawable(gradientDrawable2);
        IconImageView iconImageView = new IconImageView(context);
        this.mCloseView = iconImageView;
        iconImageView.setColor(-1);
        this.mCloseView.setResCode(R.string.jdif_common_guanbi);
        f fVar2 = new f(32, 32);
        fVar2.E(0, 0, 4, 0);
        fVar2.J(6, 6, 6, 6);
        View view2 = this.mCloseView;
        addView(view2, fVar2.i(view2));
    }

    public void tick(int i2) {
        this.mTimeText.setText(String.valueOf(Math.min(9, i2)));
    }
}
