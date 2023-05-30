package com.jingdong.app.mall.home.floor.view.widget.newcomer;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.floor.model.entity.NewcomerFloorEntity;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;
import com.jingdong.jdsdk.utils.FontsUtil;

/* loaded from: classes4.dex */
public class NewcomerOrderItem extends LinearLayout {
    private TextView mBtnText;
    private Context mContext;
    private TextView mPrice;

    public NewcomerOrderItem(Context context) {
        super(context);
        this.mContext = context;
        setOrientation(1);
        setGravity(1);
    }

    public void bindData(String str, String str2, String str3, int i2) {
        f fVar = new f(-2, 52);
        TextView textView = this.mPrice;
        if (textView == null) {
            GradientTextView gradientTextView = new GradientTextView(this.mContext);
            this.mPrice = gradientTextView;
            gradientTextView.setIncludeFontPadding(false);
            this.mPrice.setMaxLines(1);
            this.mPrice.setGravity(17);
            TextView textView2 = this.mPrice;
            addView(textView2, fVar.u(textView2));
        } else {
            f.d(textView, fVar, true);
        }
        this.mPrice.setTypeface(FontsUtil.getTypeFace(this.mContext));
        this.mPrice.setTextColor(i2);
        com.jingdong.app.mall.home.o.a.f.I0(this.mPrice, 44);
        this.mPrice.setText(NewcomerFloorEntity.getPriceSpan(str, str2, 0.5f, 0.5f));
        f fVar2 = new f(-2, 40);
        TextView textView3 = this.mBtnText;
        if (textView3 == null) {
            GradientTextView gradientTextView2 = new GradientTextView(this.mContext);
            this.mBtnText = gradientTextView2;
            gradientTextView2.setIncludeFontPadding(false);
            this.mBtnText.setMaxLines(1);
            this.mBtnText.setGravity(17);
            TextView textView4 = this.mBtnText;
            addView(textView4, fVar2.u(textView4));
        } else {
            f.d(textView3, fVar2, true);
        }
        this.mBtnText.setTextColor(i2);
        com.jingdong.app.mall.home.o.a.f.I0(this.mBtnText, 22);
        this.mBtnText.setText(str3);
    }
}
