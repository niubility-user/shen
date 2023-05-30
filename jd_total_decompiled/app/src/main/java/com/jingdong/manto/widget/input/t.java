package com.jingdong.manto.widget.input;

import android.content.Context;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.jingdong.manto.widget.input.InputUtil;

/* loaded from: classes16.dex */
final class t extends a {
    public t(Context context) {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override // com.jingdong.manto.widget.input.y
    public final void h() {
        s.a(this).setInputEditText(this);
        InputUtil.a.a(this);
    }

    @Override // com.jingdong.manto.widget.input.y, androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return null;
    }

    @Override // com.jingdong.manto.widget.input.y
    public final void setPasswordMode(boolean z) {
        j();
        int inputType = getInputType() | 2;
        setInputType(z ? inputType | 16 : inputType & (-17));
        super.setPasswordMode(z);
        i();
    }
}
