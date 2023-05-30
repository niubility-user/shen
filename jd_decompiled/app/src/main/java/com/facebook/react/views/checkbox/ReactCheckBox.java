package com.facebook.react.views.checkbox;

import android.content.Context;
import android.widget.CheckBox;

/* loaded from: classes12.dex */
class ReactCheckBox extends CheckBox {
    private boolean mAllowChange;

    public ReactCheckBox(Context context) {
        super(context);
        this.mAllowChange = true;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.mAllowChange) {
            this.mAllowChange = false;
            super.setChecked(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOn(boolean z) {
        if (isChecked() != z) {
            super.setChecked(z);
        }
        this.mAllowChange = true;
    }
}
