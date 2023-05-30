package com.jingdong.common.widget.shadow.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import androidx.annotation.Nullable;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.widget.shadow.wrapper.CheckBoxWrapper;

/* loaded from: classes12.dex */
public class JDShadowCheckBox extends JDShadowView<CheckBoxWrapper> {
    private boolean mIsChecked;

    public JDShadowCheckBox(Context context) {
        this(context, null);
    }

    private void initConfig(AttributeSet attributeSet) {
        boolean z = getContext().obtainStyledAttributes(attributeSet, R.styleable.ShadowLayout).getBoolean(R.styleable.ShadowLayout_shadowOriginIsChecked, this.mIsChecked);
        this.mIsChecked = z;
        setChecked(z);
    }

    public boolean isChecked() {
        T t = this.mOriginView;
        if (t != 0) {
            return ((CheckBoxWrapper) t).isChecked();
        }
        return false;
    }

    public void setButtonDrawable(@Nullable Drawable drawable) {
        T t = this.mOriginView;
        if (t != 0) {
            ((CheckBoxWrapper) t).setButtonDrawable(drawable);
            ((CheckBoxWrapper) this.mOriginView).invalidate();
        }
    }

    public void setChecked(boolean z) {
        T t = this.mOriginView;
        if (t != 0) {
            ((CheckBoxWrapper) t).setChecked(z);
        }
    }

    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        T t = this.mOriginView;
        if (t != 0) {
            ((CheckBoxWrapper) t).setOnCheckedChangeListener(onCheckedChangeListener);
        }
    }

    public void toggle() {
        setChecked(!isChecked());
    }

    public JDShadowCheckBox(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // com.jingdong.common.widget.shadow.widget.JDShadowView
    public CheckBoxWrapper createOriginView() {
        return new CheckBoxWrapper(getContext());
    }

    public JDShadowCheckBox(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIsChecked = false;
        initConfig(attributeSet);
    }
}
