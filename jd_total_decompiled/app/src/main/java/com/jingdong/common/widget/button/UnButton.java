package com.jingdong.common.widget.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.common.UnLog;

/* loaded from: classes12.dex */
public class UnButton extends TextView {
    private boolean autoDarkMode;
    private boolean autoElderMode;
    private boolean autoSize;
    private int buttonStyle;
    private boolean isDarkMode;
    private boolean isElder;
    private int unButtonHeight;
    private int unSafePadding;
    private float unTextSize;

    public UnButton(Context context) {
        super(context);
        this.buttonStyle = -1;
        this.autoDarkMode = false;
    }

    private int getStyle(int i2, boolean z) {
        if (UnLog.D) {
            UnLog.d("UnButton", "style: " + i2 + "  isDark: " + z + "  isAutoDark: " + this.autoDarkMode);
        }
        if (i2 == UnButtonStyleType.A.getType()) {
            if (isElder()) {
                return R.style.un_button_a_elder;
            }
            if (z) {
                return R.style.un_button_a_dark;
            }
            return R.style.un_button_a;
        } else if (i2 == UnButtonStyleType.A_S.getType()) {
            if (isElder()) {
                return R.style.un_button_a_elder_s;
            }
            if (z) {
                return R.style.un_button_a_s_dark;
            }
            return R.style.un_button_a_s;
        } else if (i2 == UnButtonStyleType.B.getType()) {
            if (isElder()) {
                return R.style.un_button_b_elder;
            }
            if (z) {
                return R.style.un_button_b_dark;
            }
            return R.style.un_button_b;
        } else if (i2 == UnButtonStyleType.X_B.getType()) {
            if (isElder()) {
                return R.style.un_button_x_b_elder;
            }
            if (z) {
                return R.style.un_button_x_b_dark;
            }
            return R.style.un_button_x_b;
        } else if (i2 == UnButtonStyleType.X_B_S.getType()) {
            if (isElder()) {
                return R.style.un_button_x_b_elder_s;
            }
            if (z) {
                return R.style.un_button_x_b_s_dark;
            }
            return R.style.un_button_x_b_s;
        } else if (i2 == UnButtonStyleType.X_A.getType()) {
            if (isElder()) {
                return R.style.un_button_x_a_elder;
            }
            if (z) {
                return R.style.un_button_x_a_dark;
            }
            return R.style.un_button_x_a;
        } else if (i2 == UnButtonStyleType.X_A_X.getType()) {
            if (isElder()) {
                return R.style.un_button_x_a_x_elder;
            }
            if (z) {
                return R.style.un_button_x_a_x_dark;
            }
            return R.style.un_button_x_a_x;
        } else if (i2 == UnButtonStyleType.X_A_S.getType()) {
            if (isElder()) {
                return R.style.un_button_x_a_elder_s;
            }
            if (z) {
                return R.style.un_button_x_a_s_dark;
            }
            return R.style.un_button_x_a_s;
        } else if (i2 == UnButtonStyleType.D_A.getType()) {
            if (isElder()) {
                return R.style.un_button_d_a_elder;
            }
            if (z) {
                return R.style.un_button_d_a_dark;
            }
            return R.style.un_button_d_a;
        } else if (i2 == UnButtonStyleType.D_A_S.getType()) {
            if (isElder()) {
                return R.style.un_button_d_a_elder_s;
            }
            if (z) {
                return R.style.un_button_d_a_s_dark;
            }
            return R.style.un_button_d_a_s;
        } else if (i2 == UnButtonStyleType.E.getType()) {
            if (isElder()) {
                return R.style.un_button_e_elder;
            }
            if (z) {
                return R.style.un_button_e_dark;
            }
            return R.style.un_button_e;
        } else if (i2 == UnButtonStyleType.E_X.getType()) {
            if (isElder()) {
                return R.style.un_button_e_x_elder;
            }
            if (z) {
                return R.style.un_button_e_x_dark;
            }
            return R.style.un_button_e_x;
        } else if (i2 == UnButtonStyleType.E_S.getType()) {
            if (isElder()) {
                return R.style.un_button_e_elder_s;
            }
            if (z) {
                return R.style.un_button_e_s_dark;
            }
            return R.style.un_button_e_s;
        } else if (i2 == UnButtonStyleType.A_A.getType()) {
            if (isElder()) {
                return R.style.un_button_a_a_elder;
            }
            if (z) {
                return R.style.un_button_a_a_dark;
            }
            return R.style.un_button_a_a;
        } else if (i2 == UnButtonStyleType.A_A_S.getType()) {
            if (isElder()) {
                return R.style.un_button_a_a_elder_s;
            }
            if (z) {
                return R.style.un_button_a_a_s_dark;
            }
            return R.style.un_button_a_a_s;
        } else if (i2 == UnButtonStyleType.M_01.getType()) {
            if (isElder()) {
                return R.style.un_button_m_01_elder;
            }
            if (z) {
                return R.style.un_button_m_01_dark;
            }
            return R.style.un_button_m_01;
        } else if (i2 == UnButtonStyleType.M_02.getType()) {
            if (isElder()) {
                return R.style.un_button_m_02_elder;
            }
            if (z) {
                return R.style.un_button_m_02_dark;
            }
            return R.style.un_button_m_02;
        } else if (i2 == UnButtonStyleType.M_03.getType()) {
            if (isElder()) {
                return R.style.un_button_m_03_elder;
            }
            if (z) {
                return R.style.un_button_m_03_dark;
            }
            return R.style.un_button_m_03;
        } else if (i2 == UnButtonStyleType.M_04.getType()) {
            if (isElder()) {
                return R.style.un_button_m_04_elder;
            }
            if (z) {
                return R.style.un_button_m_04_dark;
            }
            return R.style.un_button_m_04;
        } else if (i2 == UnButtonStyleType.M_05.getType()) {
            if (isElder()) {
                return R.style.un_button_m_05_elder;
            }
            if (z) {
                return R.style.un_button_m_05_dark;
            }
            return R.style.un_button_m_05;
        } else if (i2 == UnButtonStyleType.DIALOG_RED.getType()) {
            if (isElder()) {
                return R.style.un_dialog_button_red_elder;
            }
            if (z) {
                return R.style.un_dialog_button_red_dark;
            }
            return R.style.un_dialog_button_red;
        } else if (i2 == UnButtonStyleType.DIALOG_WHITE.getType()) {
            if (isElder()) {
                return R.style.un_dialog_button_white_elder;
            }
            if (z) {
                return R.style.un_dialog_button_white_dark;
            }
            return R.style.un_dialog_button_white;
        } else if (i2 == UnButtonStyleType.YELLOW.getType()) {
            return R.style.un_button_yellow;
        } else {
            return -1;
        }
    }

    private void init(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet != null && (obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.UnButton)) != null) {
            this.buttonStyle = obtainStyledAttributes.getInteger(R.styleable.UnButton_unButtonStyleType, -1);
            this.autoDarkMode = obtainStyledAttributes.getBoolean(R.styleable.UnButton_unButtonAutoDarkMode, false);
            this.autoSize = obtainStyledAttributes.getBoolean(R.styleable.UnButton_unButtonAutoSize, false);
            this.isDarkMode = obtainStyledAttributes.getBoolean(R.styleable.UnButton_unButtonDarkMode, false);
            this.autoElderMode = obtainStyledAttributes.getBoolean(R.styleable.UnButton_unButtonAutoElderMode, false);
        }
        if (this.buttonStyle != -1) {
            if (this.autoDarkMode) {
                this.isDarkMode = UnWidgetThemeController.getInstance().isDarkMode();
                String str = "isDarkMode:" + this.isDarkMode;
            }
            parseStyle(getStyle(this.buttonStyle, this.isDarkMode));
        }
    }

    private void parseStyle(int i2) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(i2, R.styleable.UnButton);
        if (obtainStyledAttributes != null) {
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.UnButton_unButtonBackground);
            ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.UnButton_unButtonTextColor);
            this.unTextSize = obtainStyledAttributes.getFloat(R.styleable.UnButton_unButtonTextSize, 0.0f);
            this.unButtonHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.UnButton_unButtonHeight, 0);
            this.unSafePadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.UnButton_unButtonSafePadding, 0);
            if (this.autoSize) {
                int i3 = this.unButtonHeight;
                if (i3 > 0) {
                    setHeight(i3);
                }
                if (this.unSafePadding > 0) {
                    setGravity(17);
                    setPadding(this.unSafePadding, getPaddingTop(), this.unSafePadding, getPaddingBottom());
                }
                float f2 = this.unTextSize;
                if (f2 > 0.0f) {
                    setTextSize(f2);
                }
            }
            if (drawable != null) {
                setBackgroundDrawable(drawable);
            }
            if (colorStateList != null) {
                setTextColor(colorStateList);
            }
        }
    }

    public void darkMode() {
        parseStyle(getStyle(this.buttonStyle, true));
    }

    public boolean isDarkMode() {
        if (this.autoDarkMode) {
            return UnWidgetThemeController.getInstance().isDarkMode();
        }
        return this.isDarkMode;
    }

    public boolean isElder() {
        if (this.autoElderMode) {
            return UnWidgetThemeController.getInstance().isElderModel();
        }
        return this.isElder;
    }

    public void normalMode() {
        parseStyle(getStyle(this.buttonStyle, false));
    }

    public void refresh() {
        parseStyle(getStyle(this.buttonStyle, isDarkMode()));
    }

    public UnButton setAutoDarkMode(boolean z) {
        this.autoDarkMode = z;
        return this;
    }

    public UnButton setAutoElderMode(boolean z) {
        this.autoElderMode = z;
        return this;
    }

    public UnButton setButtonStyle(UnButtonStyleType unButtonStyleType) {
        if (unButtonStyleType != null) {
            this.buttonStyle = unButtonStyleType.getType();
        }
        return this;
    }

    public UnButton setDarkMode(boolean z) {
        this.isDarkMode = z;
        return this;
    }

    public UnButton setIsElder(boolean z) {
        if (this.isElder != z) {
            refresh();
            this.isElder = z;
        }
        return this;
    }

    public UnButton setButtonStyle(int i2) {
        this.buttonStyle = i2;
        return this;
    }

    public UnButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.buttonStyle = -1;
        this.autoDarkMode = false;
        init(attributeSet);
    }

    public UnButton(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.buttonStyle = -1;
        this.autoDarkMode = false;
        init(attributeSet);
    }

    @RequiresApi(api = 21)
    public UnButton(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.buttonStyle = -1;
        this.autoDarkMode = false;
        init(attributeSet);
    }
}
