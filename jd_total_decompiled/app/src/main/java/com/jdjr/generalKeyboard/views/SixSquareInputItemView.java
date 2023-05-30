package com.jdjr.generalKeyboard.views;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import com.jdjr.dns.R;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class SixSquareInputItemView extends SixInputItemView {
    public SixSquareInputItemView(Context context) {
        super(context);
    }

    @Override // com.jdjr.generalKeyboard.views.SixInputItemView, android.view.View
    protected void onMeasure(int i2, int i3) {
        setMeasuredDimension((BaseInfo.getScreenWidth() - (((int) this.mContext.getResources().getDimension(R.dimen.security_six_square_input_item_margin_horizontal)) * 2)) / 6, (int) this.mContext.getResources().getDimension(R.dimen.security_six_square_input_item_height));
    }

    public SixSquareInputItemView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SixSquareInputItemView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
