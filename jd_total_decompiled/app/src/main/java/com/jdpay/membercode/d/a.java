package com.jdpay.membercode.d;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import com.jdpay.membercode.R;
import com.jdpay.widget.BarCodeImageView;

/* loaded from: classes18.dex */
public class a extends b {

    /* renamed from: i  reason: collision with root package name */
    private BarCodeImageView f7525i;

    public a(@NonNull Context context) {
        super(context);
    }

    @Override // com.jdpay.membercode.d.b
    public void a(@NonNull com.jdpay.membercode.f.c cVar) {
        this.f7526g = cVar;
        BarCodeImageView barCodeImageView = this.f7525i;
        if (barCodeImageView != null) {
            barCodeImageView.setBarCode(cVar.d());
            this.f7525i.setBitmap(cVar.e());
            this.f7525i.update();
        }
    }

    @Override // com.jdpay.widget.dialog.FullScreenDialog
    protected int getLayoutId() {
        return R.layout.jdpay_mb_barcode_dialog;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jdpay.widget.dialog.FullScreenDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Resources resources = getContext().getResources();
        BarCodeImageView barCodeImageView = (BarCodeImageView) findViewById(R.id.code);
        this.f7525i = barCodeImageView;
        barCodeImageView.setTips(resources.getString(R.string.jdpay_mb_code_dialog_tip), BitmapFactory.decodeResource(resources, R.mipmap.ic_jdpay_mb_notice));
        ((View) this.f7525i.getParent()).setOnClickListener(this.f7527h);
        com.jdpay.membercode.f.c cVar = this.f7526g;
        if (cVar != null) {
            a(cVar);
        }
    }
}
