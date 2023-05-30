package com.jingdong.common.widget.shadow.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.jd.lib.un.basewidget.R;

/* loaded from: classes12.dex */
public class JDShadowButton extends JDShadowView<Button> {
    public JDShadowButton(@NonNull Context context) {
        this(context, null);
    }

    private void configButtonText(TypedArray typedArray) {
        int i2 = R.styleable.ShadowLayout_shadowOriginText;
        int resourceId = typedArray.getResourceId(i2, 0);
        if (resourceId != 0) {
            setText(resourceId);
        } else {
            setText(typedArray.getText(i2));
        }
    }

    private void configButtonTextColor(TypedArray typedArray) {
        setTextColor(typedArray.getColorStateList(R.styleable.ShadowLayout_shadowOriginTextColor));
    }

    private void configButtonTextSize(TypedArray typedArray) {
        int dimensionPixelSize = typedArray.getDimensionPixelSize(R.styleable.ShadowLayout_shadowOriginTextSize, -1);
        if (dimensionPixelSize >= 0) {
            setTextSize(0, dimensionPixelSize);
        }
    }

    private void init(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ShadowLayout);
        configButtonText(obtainStyledAttributes);
        configButtonTextColor(obtainStyledAttributes);
        configButtonTextSize(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    public final CharSequence getText() {
        T t = this.mOriginView;
        return t != 0 ? ((Button) t).getText() : "";
    }

    public ColorStateList getTextColor() {
        T t = this.mOriginView;
        return t != 0 ? ((Button) t).getTextColors() : ColorStateList.valueOf(-16777216);
    }

    public float getTextSize() {
        T t = this.mOriginView;
        if (t != 0) {
            return ((Button) t).getTextSize();
        }
        return 0.0f;
    }

    @Override // android.view.View
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        T t = this.mOriginView;
        if (t != 0) {
            ((Button) t).setOnClickListener(onClickListener);
        }
    }

    public final void setText(CharSequence charSequence) {
        T t = this.mOriginView;
        if (t != 0) {
            ((Button) t).setText(charSequence);
        }
    }

    public void setTextColor(ColorStateList colorStateList) {
        T t = this.mOriginView;
        if (t == 0 || colorStateList == null) {
            return;
        }
        ((Button) t).setTextColor(colorStateList);
    }

    public void setTextSize(float f2) {
        setTextSize(2, f2);
    }

    public JDShadowButton(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // com.jingdong.common.widget.shadow.widget.JDShadowView
    public Button createOriginView() {
        return new Button(getContext());
    }

    public void setTextSize(int i2, float f2) {
        T t = this.mOriginView;
        if (t != 0) {
            ((Button) t).setTextSize(i2, f2);
        }
    }

    public JDShadowButton(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(attributeSet);
    }

    public final void setText(@StringRes int i2) {
        T t = this.mOriginView;
        if (t != 0) {
            ((Button) t).setText(i2);
        }
    }

    public void setTextColor(@ColorInt int i2) {
        T t = this.mOriginView;
        if (t != 0) {
            ((Button) t).setTextColor(i2);
        }
    }
}
