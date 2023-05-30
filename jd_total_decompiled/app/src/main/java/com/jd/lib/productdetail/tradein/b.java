package com.jd.lib.productdetail.tradein;

import androidx.lifecycle.Observer;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.inform.TradeInInformData;

/* loaded from: classes16.dex */
public class b implements Observer<PdBaseProtocolLiveData.Result<TradeInInformData>> {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ com.jd.lib.productdetail.tradein.k.c f5265g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ TradeInViewModel f5266h;

    public b(TradeInViewModel tradeInViewModel, com.jd.lib.productdetail.tradein.k.c cVar) {
        this.f5266h = tradeInViewModel;
        this.f5265g = cVar;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<TradeInInformData> result) {
        TradeInInformData.Data data;
        PdBaseProtocolLiveData.Result<TradeInInformData> result2 = result;
        if (result2 != null) {
            PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
            PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = PdBaseProtocolLiveData.Result.DataStatus.SUCCESS;
            if (dataStatus == dataStatus2) {
                this.f5266h.f5262n = null;
                TradeInInformData tradeInInformData = result2.mData;
                if (tradeInInformData != null && (data = tradeInInformData.data) != null) {
                    TradeInStep destStep = data.getDestStep();
                    if (destStep == TradeInStep.INFORM) {
                        TradeInInformData.Data data2 = result2.mData.data;
                        TradeInInformData.Data.TradeinInformInfo tradeinInformInfo = data2.noHaveLocalMachineInfo;
                        tradeinInformInfo.ruleInfo = data2.ruleInfo;
                        tradeinInformInfo.servicePointText = data2.servicePointText;
                    } else if (destStep != TradeInStep.ESTIMATE && destStep == TradeInStep.TRADEIN) {
                        this.f5266h.p = 2;
                        int tradeTypeRefreshTarget = result2.mData.data.oldProductListInfo.getTradeTypeRefreshTarget();
                        if (tradeTypeRefreshTarget != -1 && tradeTypeRefreshTarget != 0) {
                            TradeInViewModel tradeInViewModel = this.f5266h;
                            if (tradeInViewModel.f5256h != tradeTypeRefreshTarget) {
                                tradeInViewModel.f5256h = tradeTypeRefreshTarget;
                                String str = "mTradeType changed new value is " + this.f5266h.f5256h;
                            }
                        }
                        this.f5266h.u.setValue(new PdBaseProtocolLiveData.Result<>(dataStatus2, result2.mData.data.oldProductListInfo, ""));
                    }
                }
                this.f5265g.mResult.removeObserver(this);
            } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                this.f5265g.mResult.removeObserver(this);
            }
        }
    }
}
