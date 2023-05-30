package com.jdjr.generalKeyboard.common;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ViewDataBinding;
import com.jdjr.dns.databinding.SecurityFunctionalCommonPwdBinding;
import com.jdjr.dns.databinding.SecurityFunctionalPaymentBinding;
import com.jdjr.dns.databinding.SecurityFunctionalVerifyCodeBinding;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes18.dex */
public class KeyboardUiMode extends BaseObservable {
    private static boolean dark;
    private static List<ViewDataBinding> keyboardBindings = new ArrayList();

    public static void clearBindings() {
        if (keyboardBindings.size() > 0) {
            keyboardBindings.clear();
        }
    }

    public static void dynamicToDarkMode() {
        setDark(true);
        if (keyboardBindings.size() > 0) {
            switchFunctionalDarkMode();
            switchBasicDarkMode();
        }
    }

    public static void dynamicToNormalMode() {
        setDark(false);
        if (keyboardBindings.size() > 0) {
            switchFunctionalDarkMode();
            switchBasicDarkMode();
        }
    }

    @Bindable
    public static boolean isDark() {
        return dark;
    }

    public static void setDark(boolean z) {
        dark = z;
    }

    public static void setKeyboardBindings(ViewDataBinding viewDataBinding) {
        if (keyboardBindings.contains(viewDataBinding)) {
            return;
        }
        keyboardBindings.add(viewDataBinding);
    }

    private static void switchBasicDarkMode() {
        for (int i2 = 0; i2 < keyboardBindings.size(); i2++) {
            keyboardBindings.get(i2).invalidateAll();
        }
    }

    private static void switchFunctionalDarkMode() {
        for (int i2 = 0; i2 < keyboardBindings.size(); i2++) {
            try {
                keyboardBindings.get(i2).invalidateAll();
                if (keyboardBindings.get(i2) instanceof SecurityFunctionalVerifyCodeBinding) {
                    ((SecurityFunctionalVerifyCodeBinding) keyboardBindings.get(i2)).verifyCodeEditText.refresh();
                }
                if (keyboardBindings.get(i2) instanceof SecurityFunctionalCommonPwdBinding) {
                    ((SecurityFunctionalCommonPwdBinding) keyboardBindings.get(i2)).flexibleEditText.refresh();
                }
                if (keyboardBindings.get(i2) instanceof SecurityFunctionalPaymentBinding) {
                    ((SecurityFunctionalPaymentBinding) keyboardBindings.get(i2)).flexibleEditText.refresh();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
    }
}
