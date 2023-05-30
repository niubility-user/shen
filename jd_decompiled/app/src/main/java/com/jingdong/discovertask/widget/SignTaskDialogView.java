package com.jingdong.discovertask.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.Nullable;
import com.jingdong.common.R;

/* loaded from: classes12.dex */
public class SignTaskDialogView extends FrameLayout {
    public static final String TAG = "SignTaskDialogView";

    public SignTaskDialogView(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.discover_task_middle_dialog, (ViewGroup) this, true);
    }

    public SignTaskDialogView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SignTaskDialogView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context);
    }
}
