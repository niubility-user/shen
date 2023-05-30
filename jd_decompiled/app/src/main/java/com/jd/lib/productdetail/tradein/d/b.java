package com.jd.lib.productdetail.tradein.d;

import android.view.View;
import com.google.gson.JsonObject;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimateFragment;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;

/* loaded from: classes16.dex */
public class b implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ TradeInEstimateFragment f5284g;

    public b(TradeInEstimateFragment tradeInEstimateFragment) {
        this.f5284g = tradeInEstimateFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f5284g.f5294g != null) {
            JsonObject jsonObject = new JsonObject();
            TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInquiries = this.f5284g.f5297j;
            if (oldProductInquiries != null) {
                jsonObject.addProperty("product_id", oldProductInquiries.oldProductId);
            }
            this.f5284g.f5294g.e("Productdetail_yjhxAskChange", jsonObject);
        }
    }
}
