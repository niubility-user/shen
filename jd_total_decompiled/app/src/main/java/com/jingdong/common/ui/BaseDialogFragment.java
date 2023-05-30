package com.jingdong.common.ui;

import androidx.fragment.app.DialogFragment;

/* loaded from: classes6.dex */
public class BaseDialogFragment extends DialogFragment {
    ActionClickListener actionClickListener;

    /* loaded from: classes6.dex */
    public interface ActionClickListener {
        void onLeftClicked();

        void onRightClicked();
    }

    public void setOnActionClickListener(ActionClickListener actionClickListener) {
        this.actionClickListener = actionClickListener;
    }
}
