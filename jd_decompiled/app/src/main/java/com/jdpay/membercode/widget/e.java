package com.jdpay.membercode.widget;

import android.view.View;
import androidx.annotation.NonNull;
import com.jdpay.membercode.R;

/* loaded from: classes18.dex */
public class e extends a {
    public e(@NonNull CodeView codeView) {
        super(codeView);
    }

    @Override // com.jdpay.membercode.widget.a, android.view.View.OnClickListener
    public void onClick(View view) {
        if (view != this.f7560k) {
            if (view == this.f7561l) {
                j(!r0.isSelected());
            }
        } else if (this.f7561l.isSelected()) {
            this.f7566g.sign();
        } else {
            CodeView codeView = this.f7566g;
            codeView.showToast(codeView.getResources().getText(R.string.jdpay_mb_err_activate_unchecked_agreement));
        }
    }
}
