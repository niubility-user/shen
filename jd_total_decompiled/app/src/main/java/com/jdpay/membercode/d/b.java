package com.jdpay.membercode.d;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import com.jdpay.membercode.R;
import com.jdpay.membercode.f.f;
import com.jdpay.widget.dialog.FullScreenDialog;

/* loaded from: classes18.dex */
public abstract class b extends FullScreenDialog {

    /* renamed from: g  reason: collision with root package name */
    protected com.jdpay.membercode.f.c f7526g;

    /* renamed from: h  reason: collision with root package name */
    protected final View.OnClickListener f7527h;

    /* loaded from: classes18.dex */
    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            b.this.dismiss();
        }
    }

    public b(@NonNull Context context) {
        super(context, R.style.cp_dialog);
        this.f7527h = new a();
    }

    public abstract void a(@NonNull com.jdpay.membercode.f.c cVar);

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        f.b(getWindow());
        super.dismiss();
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        f.a(getWindow());
    }
}
