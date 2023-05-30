package com.jd.lib.productdetail.tradein.g;

import android.view.View;
import com.google.gson.JsonObject;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceDeviceAdapter;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceFragment;

/* loaded from: classes16.dex */
public class a implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries f5354g;

    /* renamed from: h */
    public final /* synthetic */ TradeInSelectDeviceDeviceAdapter.b f5355h;

    public a(TradeInSelectDeviceDeviceAdapter.b bVar, TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInquiries) {
        this.f5355h = bVar;
        this.f5354g = oldProductInquiries;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInquiries;
        TradeInSelectDeviceDeviceAdapter.a aVar = TradeInSelectDeviceDeviceAdapter.this.a;
        if (aVar == null || (oldProductInquiries = this.f5354g) == null) {
            return;
        }
        f fVar = (f) aVar;
        TradeInSelectDeviceFragment tradeInSelectDeviceFragment = fVar.a;
        if (tradeInSelectDeviceFragment.f5503g == null || oldProductInquiries == null) {
            return;
        }
        TradeInSelectDeviceFragment.g(tradeInSelectDeviceFragment, oldProductInquiries);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("product_id", oldProductInquiries.oldProductId);
        fVar.a.f5503g.e("Productdetail_yjhxChooseToastRightBanner", jsonObject);
    }
}
