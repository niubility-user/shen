package com.jingdong.manto.widget.input;

import android.content.Context;

/* loaded from: classes16.dex */
public final class f extends a {
    public f(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override // com.jingdong.manto.widget.input.y
    public final void h() {
        InputUtil.getInputManager(this).restartInput(this);
    }

    @Override // com.jingdong.manto.widget.input.y
    public final void setPasswordMode(boolean z) {
        j();
        int inputType = getInputType() | 1;
        setInputType(z ? inputType | 128 : inputType & (-129));
        super.setPasswordMode(z);
        i();
    }
}
