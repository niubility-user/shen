package com.jd.lib.productdetail.tradein.g;

import android.view.View;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceFragment;

/* loaded from: classes16.dex */
public class e implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ TradeInSelectDeviceFragment f5361g;

    public e(TradeInSelectDeviceFragment tradeInSelectDeviceFragment) {
        this.f5361g = tradeInSelectDeviceFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getTag() instanceof TradeInSelectDeviceData.Data.CategoriesInfo.CateItem) {
            String str = "mBrandList onItemClick item = " + view.getTag();
            this.f5361g.n((TradeInSelectDeviceData.Data.CategoriesInfo.CateItem) view.getTag(), true);
        }
    }
}
