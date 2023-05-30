package com.jd.lib.productdetail.mainimage.old;

import android.view.View;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessServiceIconEntity;
import com.jd.lib.productdetail.mainimage.old.PdMImageRcServiceIconAdapterB;

/* loaded from: classes15.dex */
public class i0 implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ WareBusinessServiceIconEntity f5155g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ PdMTrustTitleHolderB f5156h;

    public i0(PdMTrustTitleHolderB pdMTrustTitleHolderB, WareBusinessServiceIconEntity wareBusinessServiceIconEntity) {
        this.f5156h = pdMTrustTitleHolderB;
        this.f5155g = wareBusinessServiceIconEntity;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        PdMImageRcServiceIconAdapterB.d dVar = this.f5156h.a;
        if (dVar != null) {
            dVar.a(this.f5155g);
        }
    }
}
