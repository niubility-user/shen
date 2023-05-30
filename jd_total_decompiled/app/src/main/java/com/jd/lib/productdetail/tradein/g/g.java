package com.jd.lib.productdetail.tradein.g;

import android.os.Bundle;
import androidx.lifecycle.Observer;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInStep;
import com.jd.lib.productdetail.tradein.estimate.TradeInSaveData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceFragment;

/* loaded from: classes16.dex */
public class g implements Observer<PdBaseProtocolLiveData.Result<TradeInSaveData>> {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ TradeInSelectDeviceFragment f5362g;

    public g(TradeInSelectDeviceFragment tradeInSelectDeviceFragment) {
        this.f5362g = tradeInSelectDeviceFragment;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<TradeInSaveData> result) {
        PdBaseProtocolLiveData.Result<TradeInSaveData> result2 = result;
        String str = "saveDevice result = " + result2;
        if (result2 != null) {
            PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
            if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                this.f5362g.D.setVisibility(8);
                if (this.f5362g.getContext() != null) {
                    PDUtils.showToastCenterNormal(this.f5362g.getContext(), this.f5362g.getString(R.string.tradein_save_device_fail));
                }
            } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                this.f5362g.D.setVisibility(8);
                TradeInSaveData tradeInSaveData = result2.mData;
                if (tradeInSaveData != null && tradeInSaveData.data) {
                    Bundle bundle = new Bundle();
                    TradeInSelectDeviceFragment tradeInSelectDeviceFragment = this.f5362g;
                    if (tradeInSelectDeviceFragment.z) {
                        tradeInSelectDeviceFragment.f5504h.moveToTradeInPage(bundle);
                        this.f5362g.z = false;
                        return;
                    }
                    tradeInSelectDeviceFragment.f5503g.u.setValue(null);
                    this.f5362g.f5504h.moveToStep(TradeInStep.TRADEIN, bundle);
                } else if (this.f5362g.getContext() != null) {
                    PDUtils.showToastCenterNormal(this.f5362g.getContext(), this.f5362g.getString(R.string.tradein_save_device_fail));
                }
            }
        }
    }
}
