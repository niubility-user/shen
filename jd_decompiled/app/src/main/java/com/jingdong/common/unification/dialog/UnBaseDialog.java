package com.jingdong.common.unification.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.lib.un.global.theme.UnWidgetThemeController;

/* loaded from: classes6.dex */
public class UnBaseDialog extends Dialog {
    public boolean isAutoDarkMode;
    public boolean isAutoElderMode;
    public boolean isDarkMode;
    public boolean isElderMode;

    public UnBaseDialog(@NonNull Context context) {
        super(context);
        this.isAutoDarkMode = false;
    }

    public void darkMode() {
    }

    public void elderMode() {
    }

    public boolean isDarkMode() {
        if (this.isAutoDarkMode) {
            return UnWidgetThemeController.getInstance().isDarkMode();
        }
        return this.isDarkMode;
    }

    public boolean isElder() {
        if (this.isAutoElderMode) {
            return UnWidgetThemeController.getInstance().isElderModel();
        }
        return this.isElderMode;
    }

    public void normalMode() {
    }

    public void refresh() {
        Context context = getContext();
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return;
        }
        try {
            if (isElder()) {
                elderMode();
            } else if (isDarkMode()) {
                darkMode();
            } else {
                normalMode();
            }
        } catch (Exception unused) {
        }
    }

    public void setAutoDarkMode(boolean z) {
        this.isAutoDarkMode = z;
    }

    public void setAutoElderMode(boolean z) {
        this.isAutoElderMode = z;
    }

    public void setDarkMode(boolean z) {
        this.isDarkMode = z;
    }

    public void setIsElder(boolean z) {
        this.isElderMode = z;
    }

    public void setWindowBg(int i2) {
        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(i2);
        }
    }

    public UnBaseDialog(@NonNull Context context, int i2) {
        super(context, i2);
        this.isAutoDarkMode = false;
    }

    protected UnBaseDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.isAutoDarkMode = false;
    }
}
