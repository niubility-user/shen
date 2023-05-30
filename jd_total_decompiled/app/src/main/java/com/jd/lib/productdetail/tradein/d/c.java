package com.jd.lib.productdetail.tradein.d;

import android.os.Bundle;
import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.google.gson.JsonObject;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInStep;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimateFragment;
import com.jd.lib.productdetail.tradein.estimate.TradeInSaveData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;

/* loaded from: classes16.dex */
public class c implements View.OnClickListener {

    /* renamed from: g */
    public final /* synthetic */ d f5285g;

    /* loaded from: classes16.dex */
    public class a implements Observer<PdBaseProtocolLiveData.Result<TradeInSaveData>> {
        public a() {
            c.this = r1;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInSaveData> result) {
            PdBaseProtocolLiveData.Result<TradeInSaveData> result2 = result;
            String str = "saveDevice result = " + result2;
            if (result2 != null) {
                PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
                if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                    c.this.f5285g.f5287c.x.setVisibility(8);
                    if (c.this.f5285g.f5287c.getContext() != null) {
                        PDUtils.showToastCenterNormal(c.this.f5285g.f5287c.getContext(), c.this.f5285g.f5287c.getString(R.string.tradein_save_device_fail));
                    }
                } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                    c.this.f5285g.f5287c.x.setVisibility(8);
                    TradeInSaveData tradeInSaveData = result2.mData;
                    if (tradeInSaveData != null && tradeInSaveData.data) {
                        Bundle bundle = new Bundle();
                        TradeInEstimateFragment tradeInEstimateFragment = c.this.f5285g.f5287c;
                        if (tradeInEstimateFragment.s) {
                            tradeInEstimateFragment.f5295h.moveToTradeInPage(bundle);
                            return;
                        }
                        tradeInEstimateFragment.f5294g.u.setValue(null);
                        c.this.f5285g.f5287c.f5295h.moveToStep(TradeInStep.TRADEIN, bundle);
                    } else if (c.this.f5285g.f5287c.getContext() != null) {
                        PDUtils.showToastCenterNormal(c.this.f5285g.f5287c.getContext(), c.this.f5285g.f5287c.getString(R.string.tradein_save_device_fail));
                    }
                }
            }
        }
    }

    public c(d dVar) {
        this.f5285g = dVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (PDUtils.repeatClick()) {
            TradeInEstimateFragment tradeInEstimateFragment = this.f5285g.f5287c;
            MutableLiveData<PdBaseProtocolLiveData.Result<TradeInSaveData>> b = tradeInEstimateFragment.f5294g.b(tradeInEstimateFragment.f5299l, tradeInEstimateFragment.f5298k, tradeInEstimateFragment.f5297j, tradeInEstimateFragment.f5300m.getValue().mData.inquiryItemInfo.inquiryItemPropertiesMap, this.f5285g.f5287c.f5296i);
            if (b != null) {
                this.f5285g.f5287c.x.setVisibility(0);
                b.observe(this.f5285g.f5287c.getViewLifecycleOwner(), new a());
            }
            if (this.f5285g.f5287c.f5294g != null) {
                JsonObject jsonObject = new JsonObject();
                TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInquiries = this.f5285g.f5287c.f5297j;
                if (oldProductInquiries != null) {
                    jsonObject.addProperty("product_id", oldProductInquiries.oldProductId);
                }
                this.f5285g.f5287c.f5294g.e("Productdetail_yjhxViewPrice", jsonObject);
            }
        }
    }
}
