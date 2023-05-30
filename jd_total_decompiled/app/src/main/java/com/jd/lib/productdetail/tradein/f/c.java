package com.jd.lib.productdetail.tradein.f;

import androidx.lifecycle.Observer;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.estimate.TradeInSaveData;
import com.jd.lib.productdetail.tradein.result.TradeInResultFragment;

/* loaded from: classes16.dex */
public class c implements Observer<PdBaseProtocolLiveData.Result<TradeInSaveData>> {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ TradeInResultFragment.j f5350g;

    public c(TradeInResultFragment.j jVar) {
        this.f5350g = jVar;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<TradeInSaveData> result) {
        PdBaseProtocolLiveData.Result<TradeInSaveData> result2 = result;
        String str = "deleteDevice result = " + result2;
        if (result2 != null) {
            PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
            if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                TradeInResultFragment.this.K.setVisibility(8);
            } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                TradeInResultFragment.this.m(true);
            }
        }
    }
}
