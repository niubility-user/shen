package com.jd.lib.productdetail.mainimage.bigimage;

import android.view.View;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;
import com.jingdong.common.ui.JDBottomDialog;

/* loaded from: classes15.dex */
public class j implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ JDBottomDialog f4611g;

    public j(PdBigImageActivity.ImageFragment imageFragment, JDBottomDialog jDBottomDialog) {
        this.f4611g = jDBottomDialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f4611g.dismiss();
    }
}
