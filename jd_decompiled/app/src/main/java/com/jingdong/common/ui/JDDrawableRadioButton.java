package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import androidx.annotation.Nullable;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.UnLog;

/* loaded from: classes6.dex */
public class JDDrawableRadioButton extends RadioButton implements CompoundButton.OnCheckedChangeListener {
    private boolean checkIsbold;
    private CompoundButton.OnCheckedChangeListener listener;
    private Drawable mSelectedDrawableLeft;
    private Drawable mSelectedDrawableRight;

    public JDDrawableRadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.checkIsbold = false;
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        this.checkIsbold = context.obtainStyledAttributes(attributeSet, R.styleable.JDDrawableCheckBox).getBoolean(R.styleable.JDDrawableCheckBox_checked_is_bold, false);
        Drawable drawable = getCompoundDrawables()[0];
        this.mSelectedDrawableLeft = drawable;
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.mSelectedDrawableLeft.getIntrinsicHeight());
        }
        Drawable drawable2 = getCompoundDrawables()[2];
        this.mSelectedDrawableRight = drawable2;
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), this.mSelectedDrawableRight.getIntrinsicHeight());
        }
        setSelectedIconVisible(isChecked());
        super.setOnCheckedChangeListener(this);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        setTextViewBold(this.checkIsbold && z);
        setSelectedIconVisible(z);
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = this.listener;
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(compoundButton, z);
        }
    }

    public void setCheckIsbold(boolean z) {
        this.checkIsbold = z;
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        String str = "listener " + onCheckedChangeListener;
        this.listener = onCheckedChangeListener;
    }

    public void setSelectedIconVisible(boolean z) {
        Drawable drawable = z ? this.mSelectedDrawableLeft : null;
        Drawable drawable2 = z ? this.mSelectedDrawableRight : null;
        Drawable drawable3 = this.mSelectedDrawableLeft;
        if (drawable3 != null && this.mSelectedDrawableRight != null) {
            setCompoundDrawables(drawable, null, drawable2, null);
        } else if (drawable3 != null) {
            setCompoundDrawables(drawable, null, null, null);
        } else if (this.mSelectedDrawableRight != null) {
            setCompoundDrawables(null, null, drawable2, null);
        }
    }

    public void setTextViewBold(boolean z) {
        try {
            getPaint().setFakeBoldText(z);
        } catch (Exception e2) {
            if (UnLog.D) {
                e2.printStackTrace();
            }
        }
    }
}
