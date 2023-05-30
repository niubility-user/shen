package com.jingdong.common.widget.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import androidx.annotation.RequiresApi;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.common.UnLog;

/* loaded from: classes12.dex */
public class UnCheckBox extends CheckBox {
    private boolean autoDarkMode;
    private boolean autoElderMode;
    private boolean autoSize;
    private int buttonStyle;
    private boolean isDarkMode;
    private boolean isElder;
    private int unButtonHeight;
    private int unButtonWidth;
    private int unSafePadding;
    private float unTextSize;

    public UnCheckBox(Context context) {
        super(context);
        this.buttonStyle = -1;
    }

    private int getStyle(int i2, boolean z) {
        if (UnLog.D) {
            UnLog.d("UnCheckBox", "style: " + i2 + "  isDark: " + z);
        }
        if (i2 == UnCheckBoxStyleType.R_B.getType()) {
            if (isElder()) {
                return R.style.un_button_r_b_elder;
            }
            if (z) {
                return R.style.un_button_r_b_dark;
            }
            return R.style.un_button_r_b;
        } else if (i2 == UnCheckBoxStyleType.Y_A.getType()) {
            if (isElder()) {
                return R.style.un_button_y_a_elder;
            }
            if (z) {
                return R.style.un_button_y_a_dark;
            }
            return R.style.un_button_y_a;
        } else if (i2 == UnCheckBoxStyleType.R_C.getType()) {
            if (isElder()) {
                return R.style.un_button_r_c_elder;
            }
            if (z) {
                return R.style.un_button_r_c_dark;
            }
            return R.style.un_button_r_c;
        } else if (i2 == UnCheckBoxStyleType.I.getType()) {
            if (isElder()) {
                return R.style.un_button_i_elder;
            }
            if (z) {
                return R.style.un_button_i_dark;
            }
            return R.style.un_button_i;
        } else if (i2 == UnCheckBoxStyleType.J.getType()) {
            if (isElder()) {
                return R.style.un_button_j_elder;
            }
            if (z) {
                return R.style.un_button_j_dark;
            }
            return R.style.un_button_j;
        } else if (i2 == UnCheckBoxStyleType.H.getType()) {
            if (isElder()) {
                return R.style.un_button_h_elder;
            }
            if (z) {
                return R.style.un_button_h_dark;
            }
            return R.style.un_button_h;
        } else {
            return -1;
        }
    }

    private void init(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes;
        if (attributeSet != null && (obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.UnCheckBox)) != null) {
            this.buttonStyle = obtainStyledAttributes.getInteger(R.styleable.UnCheckBox_unCheckBoxStyleType, -1);
            this.autoDarkMode = obtainStyledAttributes.getBoolean(R.styleable.UnCheckBox_unCheckBoxAutoDarkMode, false);
            this.autoSize = obtainStyledAttributes.getBoolean(R.styleable.UnCheckBox_unCheckBoxAutoSize, false);
            this.isDarkMode = obtainStyledAttributes.getBoolean(R.styleable.UnCheckBox_unCheckBoxDarkMode, false);
            this.autoElderMode = obtainStyledAttributes.getBoolean(R.styleable.UnCheckBox_unCheckBoxAutoElderMode, false);
        }
        if (this.buttonStyle != -1) {
            if (this.autoDarkMode) {
                this.isDarkMode = UnWidgetThemeController.getInstance().isDarkMode();
            }
            parseStyle(getStyle(this.buttonStyle, this.isDarkMode));
        }
    }

    private void parseStyle(int i2) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(i2, R.styleable.UnCheckBox);
        if (obtainStyledAttributes != null) {
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.UnCheckBox_unCheckBoxBackground);
            ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.UnCheckBox_unCheckBoxTextColor);
            this.unTextSize = obtainStyledAttributes.getFloat(R.styleable.UnCheckBox_unCheckBoxTextSize, 0.0f);
            this.unButtonHeight = obtainStyledAttributes.getDimensionPixelSize(R.styleable.UnCheckBox_unCheckBoxHeight, 0);
            this.unButtonWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.UnCheckBox_unCheckBoxWidth, 0);
            this.unSafePadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.UnCheckBox_unCheckBoxSafePadding, 0);
            String str = "autoSize:" + this.autoSize + "  " + this.unButtonHeight;
            if (drawable != null) {
                setButtonDrawable(R.color.transparent);
                setBackgroundDrawable(drawable);
            }
            if (colorStateList != null) {
                setTextColor(colorStateList);
            }
            if (this.autoSize) {
                int i3 = this.unButtonHeight;
                if (i3 > 0) {
                    setHeight(i3);
                }
                int i4 = this.unButtonWidth;
                if (i4 > 0) {
                    setWidth(i4);
                }
                if (this.unSafePadding > 0) {
                    setGravity(16);
                    setPadding(this.unSafePadding, getPaddingTop(), this.unSafePadding, getPaddingBottom());
                }
                float f2 = this.unTextSize;
                if (f2 > 0.0f) {
                    setTextSize(f2);
                }
            }
        }
    }

    public void darkMode() {
        parseStyle(getStyle(this.buttonStyle, true));
    }

    public boolean isAutoDarkMode() {
        return this.autoDarkMode;
    }

    public boolean isDarkMode() {
        if (this.autoDarkMode) {
            return UnWidgetThemeController.getInstance().isDarkMode();
        }
        return this.isDarkMode;
    }

    public boolean isElder() {
        if (isAutoDarkMode()) {
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

    public UnCheckBox setAutoDarkMode(boolean z) {
        this.autoDarkMode = z;
        return this;
    }

    public UnCheckBox setAutoElderMode(boolean z) {
        this.autoElderMode = z;
        return this;
    }

    public UnCheckBox setCheckBoxStyle(UnCheckBoxStyleType unCheckBoxStyleType) {
        if (unCheckBoxStyleType != null) {
            this.buttonStyle = unCheckBoxStyleType.getType();
        }
        return this;
    }

    public UnCheckBox setDarkMode(boolean z) {
        this.isDarkMode = z;
        return this;
    }

    public UnCheckBox setIsElder(boolean z) {
        if (this.isElder != z) {
            refresh();
            this.isElder = z;
        }
        return this;
    }

    public UnCheckBox setCheckBoxStyle(int i2) {
        this.buttonStyle = i2;
        return this;
    }

    public UnCheckBox(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.buttonStyle = -1;
        init(attributeSet);
    }

    public UnCheckBox(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.buttonStyle = -1;
        init(attributeSet);
    }

    @RequiresApi(api = 21)
    public UnCheckBox(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.buttonStyle = -1;
        init(attributeSet);
    }
}
