package com.jingdong.common.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.common.R;

/* loaded from: classes12.dex */
public class FullScreenDialog extends Dialog {
    public FullScreenDialog(Context context) {
        super(context);
        init();
    }

    private void init() {
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    public void showContentView(View view) {
        getWindow().setFlags(1024, 1024);
        getWindow().setWindowAnimations(R.style.dialog_annim_bottom_style);
        setContentView(view, new ViewGroup.LayoutParams(-1, -1));
        show();
    }

    public FullScreenDialog(Context context, int i2) {
        super(context, i2);
        init();
    }

    protected FullScreenDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        init();
    }
}
