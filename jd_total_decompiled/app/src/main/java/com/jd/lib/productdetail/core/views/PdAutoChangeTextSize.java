package com.jd.lib.productdetail.core.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.lib.productdetail.core.R;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes15.dex */
public class PdAutoChangeTextSize extends AppCompatTextView {
    private boolean isSetNormalStyle;

    public PdAutoChangeTextSize(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (isInEditMode() || this.isSetNormalStyle) {
            return;
        }
        float textSize = getTextSize();
        if (JDElderModeUtils.isElderMode()) {
            super.setTextSize(2, JDElderModeUtils.getElderTextSize(DPIUtil.px2sp(getContext(), textSize)));
            return;
        }
        TextScaleModeHelper.Companion companion = TextScaleModeHelper.INSTANCE;
        if (TextUtils.equals(companion.getInstance().getTextSizeScaleMode(), ScaleModeConstants.TEXT_SCALE_MODE_LARGE)) {
            super.setTextSize(2, companion.getInstance().getScaleSize(getContext(), DPIUtil.px2sp(getContext(), textSize)));
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(float f2) {
        if (!this.isSetNormalStyle) {
            if (JDElderModeUtils.isElderMode()) {
                f2 = JDElderModeUtils.getElderTextSize(f2);
            } else {
                TextScaleModeHelper.Companion companion = TextScaleModeHelper.INSTANCE;
                if (TextUtils.equals(companion.getInstance().getTextSizeScaleMode(), ScaleModeConstants.TEXT_SCALE_MODE_LARGE)) {
                    f2 = companion.getInstance().getScaleSize(getContext(), f2);
                }
            }
        }
        super.setTextSize(f2);
    }

    public void setTextSizeNomal(float f2) {
        super.setTextSize(f2);
    }

    public void setTextSizeNomal(int i2, float f2) {
        super.setTextSize(i2, f2);
    }

    public PdAutoChangeTextSize(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Lib_pd_AutoChange_TextSize);
            this.isSetNormalStyle = obtainStyledAttributes.getBoolean(R.styleable.Lib_pd_AutoChange_TextSize_isSetNormalStyle, false);
            obtainStyledAttributes.recycle();
        }
        init();
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public void setTextSize(int i2, float f2) {
        if (!this.isSetNormalStyle) {
            if (JDElderModeUtils.isElderMode()) {
                f2 = JDElderModeUtils.getElderTextSize(f2);
            } else {
                TextScaleModeHelper.Companion companion = TextScaleModeHelper.INSTANCE;
                if (TextUtils.equals(companion.getInstance().getTextSizeScaleMode(), ScaleModeConstants.TEXT_SCALE_MODE_LARGE)) {
                    f2 = companion.getInstance().getScaleSize(getContext(), f2);
                }
            }
        }
        super.setTextSize(i2, f2);
    }

    public PdAutoChangeTextSize(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Lib_pd_AutoChange_TextSize);
            this.isSetNormalStyle = obtainStyledAttributes.getBoolean(R.styleable.Lib_pd_AutoChange_TextSize_isSetNormalStyle, false);
            obtainStyledAttributes.recycle();
        }
        init();
    }
}
