package com.jdjr.generalKeyboard.common;

import android.text.TextUtils;
import androidx.annotation.ColorRes;
import com.jdjr.generalKeyboard.JDJRFunctionKeyboard;

/* loaded from: classes18.dex */
public class JDJRKeyboardModel {
    private boolean autoCountDown;
    private String[] backgroundThemeColor;
    @ColorRes
    private int[] backgroundThemeResource;
    private long countButtonText;
    private String cryptoAlg;
    private CharSequence description;
    private boolean hasFinishButton;
    private CharSequence hint;
    private JDJRFunctionKeyboard.KeyboardType keyboardType;
    private CharSequence leftFuncText;
    private int maxInputLength;
    private CharSequence middleFuncText;
    private CharSequence oKButtonText;
    private String okButtonBackgroundColor;
    private CharSequence rightFuncText;
    private boolean showPressBg;
    private CharSequence title;

    public JDJRKeyboardModel() {
        this.countButtonText = 60L;
        this.autoCountDown = true;
        this.hasFinishButton = false;
        this.maxInputLength = 20;
        this.showPressBg = true;
        this.cryptoAlg = "0";
    }

    public String[] getBackgroundThemeColor() {
        return this.backgroundThemeColor;
    }

    public int[] getBackgroundThemeResource() {
        return this.backgroundThemeResource;
    }

    public long getCountButtonText() {
        return this.countButtonText;
    }

    public String getCryptoAlg() {
        return this.cryptoAlg;
    }

    public CharSequence getDescription() {
        CharSequence charSequence = this.description;
        if (charSequence != null) {
            return charSequence;
        }
        JDJRFunctionKeyboard.KeyboardType keyboardType = this.keyboardType;
        return keyboardType == JDJRFunctionKeyboard.KeyboardType.SHORT_PAY_PWD ? "" : keyboardType == JDJRFunctionKeyboard.KeyboardType.PAYMENT ? "\u5f53\u524d\u5168\u90e8\u8fd8\u6b3e 500.00 \u5143" : keyboardType == JDJRFunctionKeyboard.KeyboardType.IDENTITY ? "\u8f93\u5165\u8eab\u4efd\u8bc1\u540e6\u4f4d" : keyboardType == JDJRFunctionKeyboard.KeyboardType.LONG_PAY_PWD ? "\u501f\u6b3e500\u5143|5\u671f" : keyboardType == JDJRFunctionKeyboard.KeyboardType.UNFIXED_VERIFY_CODE ? "\u9a8c\u8bc1\u7801\u5df2\u53d1\u9001\u81f3167****2299" : "\u77ed\u4fe1\u9a8c\u8bc1\u7801\u5df2\u53d1\u9001\u81f3167****2288";
    }

    public CharSequence getHint() {
        CharSequence charSequence = this.hint;
        if (charSequence != null) {
            return charSequence;
        }
        JDJRFunctionKeyboard.KeyboardType keyboardType = this.keyboardType;
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.SHORT_PAY_PWD) {
            return "";
        }
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.PAYMENT) {
            return "\u8f93\u5165\u91d1\u989d";
        }
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.IDENTITY) {
            return "";
        }
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.LONG_PAY_PWD) {
            return "\u7f51\u94f6\u94b1\u5305\u652f\u4ed8\u5bc6\u7801";
        }
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.UNFIXED_VERIFY_CODE) {
        }
        return "";
    }

    public JDJRFunctionKeyboard.KeyboardType getKeyboardType() {
        return this.keyboardType;
    }

    public CharSequence getLeftFuncText() {
        CharSequence charSequence = this.leftFuncText;
        if (charSequence != null) {
            return charSequence;
        }
        JDJRFunctionKeyboard.KeyboardType keyboardType = this.keyboardType;
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.SHORT_PAY_PWD || keyboardType == JDJRFunctionKeyboard.KeyboardType.PAYMENT || keyboardType == JDJRFunctionKeyboard.KeyboardType.IDENTITY || keyboardType == JDJRFunctionKeyboard.KeyboardType.LONG_PAY_PWD) {
            return "";
        }
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.UNFIXED_VERIFY_CODE) {
        }
        return "\u5176\u4ed6\u9a8c\u8bc1\u65b9\u5f0f";
    }

    public int getMaxInputLength() {
        JDJRFunctionKeyboard.KeyboardType keyboardType = this.keyboardType;
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.SHORT_PAY_PWD || keyboardType == JDJRFunctionKeyboard.KeyboardType.IDENTITY || keyboardType == JDJRFunctionKeyboard.KeyboardType.FIXED_VERIFY_CODE) {
            return 6;
        }
        return this.maxInputLength;
    }

    public CharSequence getMiddleFuncText() {
        CharSequence charSequence = this.middleFuncText;
        if (charSequence != null) {
            return charSequence;
        }
        JDJRFunctionKeyboard.KeyboardType keyboardType = this.keyboardType;
        return (keyboardType == JDJRFunctionKeyboard.KeyboardType.SHORT_PAY_PWD || keyboardType == JDJRFunctionKeyboard.KeyboardType.PAYMENT || keyboardType == JDJRFunctionKeyboard.KeyboardType.IDENTITY || keyboardType == JDJRFunctionKeyboard.KeyboardType.LONG_PAY_PWD || keyboardType == JDJRFunctionKeyboard.KeyboardType.UNFIXED_VERIFY_CODE) ? "" : "\u6536\u4e0d\u5230\u9a8c\u8bc1\u7801";
    }

    public String getOKButtonBackgroundColor() {
        String str = this.okButtonBackgroundColor;
        return str == null ? "blue" : str;
    }

    public CharSequence getOKButtonText() {
        return TextUtils.isEmpty(this.oKButtonText) ? "\u5b8c\u6210" : this.oKButtonText;
    }

    public CharSequence getRightFuncText() {
        CharSequence charSequence = this.rightFuncText;
        if (charSequence != null) {
            return charSequence;
        }
        JDJRFunctionKeyboard.KeyboardType keyboardType = this.keyboardType;
        return keyboardType == JDJRFunctionKeyboard.KeyboardType.SHORT_PAY_PWD ? "\u5fd8\u8bb0\u5bc6\u7801" : (keyboardType == JDJRFunctionKeyboard.KeyboardType.PAYMENT || keyboardType == JDJRFunctionKeyboard.KeyboardType.IDENTITY) ? "" : keyboardType == JDJRFunctionKeyboard.KeyboardType.LONG_PAY_PWD ? "\u5fd8\u8bb0\u5bc6\u7801" : keyboardType == JDJRFunctionKeyboard.KeyboardType.UNFIXED_VERIFY_CODE ? "\u6536\u4e0d\u5230\u9a8c\u8bc1\u7801" : "\u83b7\u53d6\u9a8c\u8bc1\u7801";
    }

    public CharSequence getTitle() {
        CharSequence charSequence = this.title;
        if (charSequence != null) {
            return charSequence;
        }
        JDJRFunctionKeyboard.KeyboardType keyboardType = this.keyboardType;
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.SHORT_PAY_PWD) {
            return "\u8f93\u5165\u652f\u4ed8\u5bc6\u7801";
        }
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.PAYMENT) {
            return "\u8f93\u5165\u8fd8\u6b3e\u91d1\u989d";
        }
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.IDENTITY) {
            return "\u8eab\u4efd\u8ba4\u8bc1";
        }
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.LONG_PAY_PWD) {
            return "\u8f93\u5165\u7f51\u94f6\u94b1\u5305\u652f\u4ed8\u5bc6\u7801";
        }
        if (keyboardType == JDJRFunctionKeyboard.KeyboardType.UNFIXED_VERIFY_CODE) {
        }
        return "\u8f93\u5165\u77ed\u4fe1\u9a8c\u8bc1\u7801";
    }

    public boolean isAutoCountDown() {
        return this.autoCountDown;
    }

    public boolean isHasFinishButton() {
        return this.hasFinishButton;
    }

    public boolean isShowPressBg() {
        return this.showPressBg;
    }

    public void setAutoCountDown(boolean z) {
        this.autoCountDown = z;
    }

    public void setBackgroundThemeColor(String... strArr) {
        this.backgroundThemeColor = strArr;
    }

    public void setBackgroundThemeResource(@ColorRes int... iArr) {
        this.backgroundThemeResource = iArr;
    }

    public void setCountButtonText(long j2) {
        if (j2 < 0 || j2 > 200) {
            return;
        }
        this.countButtonText = j2;
    }

    public void setCryptoAlg(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if ("0".equals(str) || "1".equals(str)) {
            this.cryptoAlg = str;
        }
    }

    public void setDescription(CharSequence charSequence) {
        this.description = charSequence;
    }

    public void setHasFinishButton(boolean z) {
        this.hasFinishButton = z;
    }

    public void setHint(CharSequence charSequence) {
        this.hint = charSequence;
    }

    public void setKeyboardType(JDJRFunctionKeyboard.KeyboardType keyboardType) {
        this.keyboardType = keyboardType;
    }

    public void setLeftFuncText(CharSequence charSequence) {
        this.leftFuncText = charSequence;
    }

    public void setMaxInputLength(int i2) {
        this.maxInputLength = i2;
    }

    public void setMiddleFuncText(CharSequence charSequence) {
        this.middleFuncText = charSequence;
    }

    public void setOKButtonBackgroundColor(String str) {
        this.okButtonBackgroundColor = str;
    }

    public void setOKButtonText(CharSequence charSequence) {
        this.oKButtonText = charSequence;
    }

    public void setRightFuncText(CharSequence charSequence) {
        this.rightFuncText = charSequence;
    }

    public void setTitle(CharSequence charSequence) {
        this.title = charSequence;
    }

    public void showPressBg(boolean z) {
        this.showPressBg = z;
    }

    public JDJRKeyboardModel(JDJRFunctionKeyboard.KeyboardType keyboardType, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, CharSequence charSequence5, CharSequence charSequence6, CharSequence charSequence7, long j2, boolean z, boolean z2, int i2, String str) {
        this.countButtonText = 60L;
        this.autoCountDown = true;
        this.hasFinishButton = false;
        this.maxInputLength = 20;
        this.showPressBg = true;
        this.cryptoAlg = "0";
        this.keyboardType = keyboardType;
        this.title = charSequence;
        this.description = charSequence2;
        this.hint = charSequence3;
        this.leftFuncText = charSequence4;
        this.middleFuncText = charSequence5;
        this.rightFuncText = charSequence6;
        this.oKButtonText = charSequence7;
        this.countButtonText = j2;
        this.autoCountDown = z;
        this.hasFinishButton = z2;
        this.maxInputLength = i2;
        this.okButtonBackgroundColor = str;
    }
}
