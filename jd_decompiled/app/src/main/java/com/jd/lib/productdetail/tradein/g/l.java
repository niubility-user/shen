package com.jd.lib.productdetail.tradein.g;

import android.view.View;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceSearchView;

/* loaded from: classes16.dex */
public class l implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ TradeInSelectDeviceSearchView f5366g;

    public l(TradeInSelectDeviceSearchView tradeInSelectDeviceSearchView) {
        this.f5366g = tradeInSelectDeviceSearchView;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        TradeInSelectDeviceSearchView.b(this.f5366g, false);
    }
}
