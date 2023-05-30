package com.jdpay.membercode.d;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.jdpay.membercode.R;

/* loaded from: classes18.dex */
public class c extends b {

    /* renamed from: i  reason: collision with root package name */
    private ImageView f7529i;

    public c(@NonNull Context context) {
        super(context);
    }

    @Override // com.jdpay.membercode.d.b
    public void a(@NonNull com.jdpay.membercode.f.c cVar) {
        this.f7526g = cVar;
        ImageView imageView = this.f7529i;
        if (imageView != null) {
            imageView.setImageBitmap(cVar.e());
        }
    }

    @Override // com.jdpay.widget.dialog.FullScreenDialog
    protected int getLayoutId() {
        return R.layout.jdpay_mb_qrcode_dialog;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jdpay.widget.dialog.FullScreenDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ImageView imageView = (ImageView) findViewById(R.id.code);
        this.f7529i = imageView;
        ((View) imageView.getParent()).setOnClickListener(this.f7527h);
        com.jdpay.membercode.f.c cVar = this.f7526g;
        if (cVar != null) {
            a(cVar);
        }
    }
}
