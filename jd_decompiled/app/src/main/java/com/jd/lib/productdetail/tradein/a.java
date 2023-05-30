package com.jd.lib.productdetail.tradein;

import androidx.lifecycle.Observer;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.f.e;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;

/* loaded from: classes16.dex */
public class a implements Observer<PdBaseProtocolLiveData.Result<TradeInResultData>> {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ e f5263g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ TradeInViewModel f5264h;

    public a(TradeInViewModel tradeInViewModel, e eVar) {
        this.f5264h = tradeInViewModel;
        this.f5263g = eVar;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<TradeInResultData> result) {
        PdBaseProtocolLiveData.Result<TradeInResultData> result2 = result;
        PdBaseProtocolLiveData.Result<TradeInResultData> result3 = new PdBaseProtocolLiveData.Result<>(result2.mStatus, null, "");
        PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
        PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = PdBaseProtocolLiveData.Result.DataStatus.SUCCESS;
        if (dataStatus == dataStatus2) {
            TradeInResultData tradeInResultData = result2.mData;
            if (tradeInResultData != null && tradeInResultData.isValid()) {
                int tradeTypeRefreshTarget = result2.mData.getTradeTypeRefreshTarget();
                if (tradeTypeRefreshTarget != -1 && tradeTypeRefreshTarget != 0) {
                    TradeInViewModel tradeInViewModel = this.f5264h;
                    if (tradeInViewModel.f5256h != tradeTypeRefreshTarget) {
                        tradeInViewModel.f5256h = tradeTypeRefreshTarget;
                        String str = "mTradeType changed new value is " + this.f5264h.f5256h;
                    }
                }
                TradeInViewModel tradeInViewModel2 = this.f5264h;
                tradeInViewModel2.f5257i = 0;
                tradeInViewModel2.p = 2;
                TradeInResultData tradeInResultData2 = result2.mData;
                TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo = tradeInResultData2.oldWare1;
                tradeInViewModel2.f5262n = null;
                result3 = new PdBaseProtocolLiveData.Result<>(dataStatus2, tradeInResultData2, "");
            } else {
                result3 = new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, "");
            }
            this.f5263g.mResult.removeObserver(this);
        } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
            this.f5263g.mResult.removeObserver(this);
        }
        this.f5264h.u.setValue(result3);
    }
}
