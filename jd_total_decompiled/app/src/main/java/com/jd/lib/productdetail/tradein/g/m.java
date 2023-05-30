package com.jd.lib.productdetail.tradein.g;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.jd.dynamic.DYConstants;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceSearchView;

/* loaded from: classes16.dex */
public class m implements Observer<PdBaseProtocolLiveData.Result<TradeInSelectDeviceData>> {

    /* renamed from: g */
    public final /* synthetic */ MutableLiveData f5367g;

    /* renamed from: h */
    public final /* synthetic */ boolean f5368h;

    /* renamed from: i */
    public final /* synthetic */ TradeInSelectDeviceSearchView f5369i;

    public m(TradeInSelectDeviceSearchView tradeInSelectDeviceSearchView, MutableLiveData mutableLiveData, boolean z) {
        this.f5369i = tradeInSelectDeviceSearchView;
        this.f5367g = mutableLiveData;
        this.f5368h = z;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<TradeInSelectDeviceData> result) {
        TradeInSelectDeviceData.Data data;
        TradeInSelectDeviceData.Data.InquiriesInfo inquiriesInfo;
        PdBaseProtocolLiveData.Result<TradeInSelectDeviceData> result2 = result;
        StringBuilder sb = new StringBuilder();
        sb.append("updateSelectDeviceSearch onChanged result = ");
        sb.append(result2 != null ? result2.mStatus : DYConstants.DY_NULL_STR);
        sb.toString();
        if (result2 != null) {
            PdBaseProtocolLiveData.Result<TradeInSelectDeviceData.Data.InquiriesInfo> result3 = new PdBaseProtocolLiveData.Result<>(result2.mStatus, null, "");
            PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
            PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = PdBaseProtocolLiveData.Result.DataStatus.SUCCESS;
            if (dataStatus == dataStatus2) {
                TradeInSelectDeviceData tradeInSelectDeviceData = result2.mData;
                if (tradeInSelectDeviceData != null && (data = tradeInSelectDeviceData.data) != null && (inquiriesInfo = data.inquiriesInfo) != null) {
                    this.f5369i.x = inquiriesInfo.pageNo;
                    result3 = new PdBaseProtocolLiveData.Result<>(dataStatus2, inquiriesInfo, "");
                } else {
                    result3 = new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, "");
                }
                this.f5367g.removeObserver(this);
            } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                this.f5367g.removeObserver(this);
            }
            if (!this.f5368h || result3.mStatus == dataStatus2) {
                this.f5369i.u.setValue(result3);
            }
        }
    }
}
