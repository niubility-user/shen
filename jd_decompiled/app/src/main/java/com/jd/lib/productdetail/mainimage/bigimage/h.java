package com.jd.lib.productdetail.mainimage.bigimage;

import android.view.View;
import android.view.ViewGroup;
import com.jd.lib.productdetail.mainimage.bigimage.PdBigImageActivity;

/* loaded from: classes15.dex */
public class h implements Runnable {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdBigImageActivity.ImageFragment f4599g;

    public h(PdBigImageActivity.ImageFragment imageFragment) {
        this.f4599g = imageFragment;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.jd.lib.productdetail.mainimage.old.k kVar;
        ViewGroup viewGroup;
        PdBigImageActivity.ImageFragment imageFragment = this.f4599g;
        PdBigImageActivity pdBigImageActivity = imageFragment.q;
        if (pdBigImageActivity.y || (kVar = imageFragment.u) == null) {
            return;
        }
        if (pdBigImageActivity != null) {
            View decorView = pdBigImageActivity.getWindow().getDecorView();
            if (decorView instanceof ViewGroup) {
                viewGroup = (ViewGroup) decorView;
                kVar.e(viewGroup);
            }
        }
        viewGroup = null;
        kVar.e(viewGroup);
    }
}
