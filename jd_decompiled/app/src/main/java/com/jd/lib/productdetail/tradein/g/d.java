package com.jd.lib.productdetail.tradein.g;

import android.view.View;
import com.google.gson.JsonObject;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceFragment;

/* loaded from: classes16.dex */
public class d implements View.OnClickListener {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ TradeInSelectDeviceFragment f5360g;

    public d(TradeInSelectDeviceFragment tradeInSelectDeviceFragment) {
        this.f5360g = tradeInSelectDeviceFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getTag() instanceof TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem) {
            TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem cateItem = (TradeInSelectDeviceData.Data.CategoryIdListForClap.CateItem) view.getTag();
            String str = "mCateList onItemClick item = " + cateItem;
            TradeInSelectDeviceFragment tradeInSelectDeviceFragment = this.f5360g;
            tradeInSelectDeviceFragment.H.setValue(cateItem);
            if (cateItem != null) {
                tradeInSelectDeviceFragment.e(tradeInSelectDeviceFragment.t(), cateItem);
            }
            if (this.f5360g.f5503g != null) {
                JsonObject jsonObject = new JsonObject();
                if (cateItem != null) {
                    jsonObject.addProperty("cid3", cateItem.categoryId + "");
                }
                this.f5360g.f5503g.e("Productdetail_yjhxAllCategory", jsonObject);
            }
        }
    }
}
