package com.jd.lib.cashier.sdk.freindpay.floors;

import android.view.View;
import android.widget.ImageView;
import com.jd.cashier.app.jdlibcutter.protocol.imageloader.ImageLoaderOptions;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor;
import com.jd.lib.cashier.sdk.core.utils.k;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.f.c.a;
import com.jd.lib.cashier.sdk.f.f.l;

/* loaded from: classes14.dex */
public class FriendPayTopFloor extends AbstractFloor<a, l> {

    /* renamed from: i  reason: collision with root package name */
    private ImageView f3404i;

    public FriendPayTopFloor(View view) {
        super(view);
    }

    private void c(String str) {
        try {
            ImageLoaderOptions imageLoaderOptions = new ImageLoaderOptions();
            imageLoaderOptions.placeHolderType = 16;
            imageLoaderOptions.showDefault = true;
            k.b(this.f3404i, str, imageLoaderOptions, false);
        } catch (Exception e2) {
            r.d("FriendPayTopFloor", e2.getMessage());
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public void b(a aVar, l lVar) {
        if (lVar != null) {
            c(lVar.a);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.floor.AbstractFloor
    public void initView(View view) {
        this.f3404i = (ImageView) getView(R.id.lib_cashier_friend_pay_top_image);
    }
}
