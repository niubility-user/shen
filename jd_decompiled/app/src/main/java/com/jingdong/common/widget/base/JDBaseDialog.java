package com.jingdong.common.widget.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.common.unification.statusbar.UnStatusBarWindowConfigUtils;

/* loaded from: classes12.dex */
public class JDBaseDialog extends Dialog {
    private boolean autoConfig;

    public JDBaseDialog(@NonNull Context context) {
        super(context);
        this.autoConfig = true;
    }

    private void onAfterShowConfig() {
        if (this.autoConfig) {
            UnStatusBarWindowConfigUtils.onAfterShowConfig(this);
        }
    }

    private void onPreShowConfig() {
        if (this.autoConfig) {
            UnStatusBarWindowConfigUtils.onPreShowConfig(this);
        }
    }

    public void setAutoConfig(boolean z) {
        this.autoConfig = z;
    }

    @Override // android.app.Dialog
    public void show() {
        onPreShowConfig();
        super.show();
        onAfterShowConfig();
    }

    public JDBaseDialog(@NonNull Context context, int i2) {
        super(context, i2);
        this.autoConfig = true;
    }

    protected JDBaseDialog(@NonNull Context context, boolean z, @Nullable DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.autoConfig = true;
    }
}
