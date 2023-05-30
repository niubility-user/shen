package com.jingdong.sdk.platform.business.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes10.dex */
public class PdAutoChangeTextSize extends AppCompatTextView {
    public PdAutoChangeTextSize(Context context) {
        super(context);
        init();
    }

    private void init() {
        TextScaleModeHelper.Companion companion = TextScaleModeHelper.INSTANCE;
        if (TextUtils.equals(companion.getInstance().getTextSizeScaleMode(), ScaleModeConstants.TEXT_SCALE_MODE_LARGE)) {
            super.setTextSize(2, companion.getInstance().getScaleSize(getContext(), DPIUtil.px2sp(getContext(), getTextSize())));
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(float f2) {
        TextScaleModeHelper.Companion companion = TextScaleModeHelper.INSTANCE;
        if (TextUtils.equals(companion.getInstance().getTextSizeScaleMode(), ScaleModeConstants.TEXT_SCALE_MODE_LARGE)) {
            f2 = companion.getInstance().getScaleSize(getContext(), f2);
        }
        super.setTextSize(f2);
    }

    public PdAutoChangeTextSize(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public void setTextSize(int i2, float f2) {
        TextScaleModeHelper.Companion companion = TextScaleModeHelper.INSTANCE;
        if (TextUtils.equals(companion.getInstance().getTextSizeScaleMode(), ScaleModeConstants.TEXT_SCALE_MODE_LARGE)) {
            f2 = companion.getInstance().getScaleSize(getContext(), f2);
        }
        super.setTextSize(i2, f2);
    }

    public PdAutoChangeTextSize(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init();
    }
}
