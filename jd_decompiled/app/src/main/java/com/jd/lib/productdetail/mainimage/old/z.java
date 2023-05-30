package com.jd.lib.productdetail.mainimage.old;

import android.view.View;
import com.jd.lib.productdetail.mainimage.old.PdMImagePartsDpgIntegrateView;

/* loaded from: classes15.dex */
public class z implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ PdMImagePartsDpgIntegrateView f5209g;

    public z(PdMImagePartsDpgIntegrateView pdMImagePartsDpgIntegrateView) {
        this.f5209g = pdMImagePartsDpgIntegrateView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        PdMImagePartsDpgIntegrateView.a aVar = this.f5209g.f5043i;
        if (aVar != null) {
            aVar.a();
        }
    }
}
