package com.jd.lib.productdetail.tradein.g;

import android.view.View;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceFragment;

/* loaded from: classes16.dex */
public class h implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ TradeInSelectDeviceFragment f5363g;

    public h(TradeInSelectDeviceFragment tradeInSelectDeviceFragment) {
        this.f5363g = tradeInSelectDeviceFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getTag() instanceof TradeInSelectDeviceData.Data.TagsInfo.TagItem) {
            this.f5363g.f((TradeInSelectDeviceData.Data.TagsInfo.TagItem) view.getTag(), true);
        }
    }
}
