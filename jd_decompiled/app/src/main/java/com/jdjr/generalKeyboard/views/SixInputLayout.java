package com.jdjr.generalKeyboard.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import com.jdjr.dns.R;
import com.jdjr.dns.databinding.SecuritySixSqaureInputLayoutBinding;
import com.jdjr.dns.databinding.SecuritySixUnderlineInputLayoutBinding;
import com.jdjr.generalKeyboard.common.KeyboardUiMode;

/* loaded from: classes18.dex */
public class SixInputLayout extends FrameLayout {
    private LinearLayout mRootView;
    LinearLayout six_layout;

    public SixInputLayout(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attributeSet, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SecureSixInputLayout, i2, 0);
        int integer = obtainStyledAttributes.getInteger(R.styleable.SecureSixInputLayout_security_sixInputLayoutItemStyle, 0);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (integer == 0) {
            SecuritySixSqaureInputLayoutBinding securitySixSqaureInputLayoutBinding = (SecuritySixSqaureInputLayoutBinding) DataBindingUtil.inflate(layoutInflater, R.layout.security_six_sqaure_input_layout, null, false);
            this.mRootView = (LinearLayout) securitySixSqaureInputLayoutBinding.getRoot();
            KeyboardUiMode.setKeyboardBindings(securitySixSqaureInputLayoutBinding);
        } else {
            SecuritySixUnderlineInputLayoutBinding securitySixUnderlineInputLayoutBinding = (SecuritySixUnderlineInputLayoutBinding) DataBindingUtil.inflate(layoutInflater, R.layout.security_six_underline_input_layout, null, false);
            this.mRootView = (LinearLayout) securitySixUnderlineInputLayoutBinding.getRoot();
            KeyboardUiMode.setKeyboardBindings(securitySixUnderlineInputLayoutBinding);
        }
        this.six_layout = (LinearLayout) this.mRootView.findViewById(R.id.six_input_endit);
        addView(this.mRootView);
        obtainStyledAttributes.recycle();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onInputChange(String str, boolean z) {
        if (str == null) {
            return;
        }
        int length = str.length();
        if (length < 0) {
            length = 0;
        }
        if (length > this.six_layout.getChildCount()) {
            length = this.six_layout.getChildCount();
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.six_layout.getChildCount(); i3++) {
            SixInputItemView sixInputItemView = (SixInputItemView) this.six_layout.getChildAt(i3);
            if (sixInputItemView != null) {
                if (i2 < length) {
                    sixInputItemView.setIsDrawn(true);
                    if (z) {
                        sixInputItemView.setPlainText(str.substring(i2, i2 + 1));
                    } else {
                        sixInputItemView.setIsCipher(true);
                    }
                } else {
                    sixInputItemView.setIsDrawn(false);
                    sixInputItemView.invalidate();
                }
                i2++;
            }
        }
    }

    public SixInputLayout(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SixInputLayout(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context, attributeSet, i2);
    }
}
