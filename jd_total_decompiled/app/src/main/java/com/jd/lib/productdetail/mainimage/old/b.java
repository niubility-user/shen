package com.jd.lib.productdetail.mainimage.old;

import androidx.lifecycle.Observer;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PdDpgRecalculateInfo;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.protocol.PdDpgLayerRecalculateProtocol;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes15.dex */
public class b implements Observer<PdBaseProtocolLiveData.Result<Object>> {

    /* renamed from: g  reason: collision with root package name */
    public final /* synthetic */ PdDpgLayerRecalculateProtocol f5139g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ y f5140h;

    public b(y yVar, PdDpgLayerRecalculateProtocol pdDpgLayerRecalculateProtocol) {
        this.f5140h = yVar;
        this.f5139g = pdDpgLayerRecalculateProtocol;
    }

    @Override // androidx.lifecycle.Observer
    public void onChanged(PdBaseProtocolLiveData.Result<Object> result) {
        PdBaseProtocolLiveData.Result<Object> result2 = result;
        if (result2 != null) {
            try {
                PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
                PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = PdBaseProtocolLiveData.Result.DataStatus.SUCCESS;
                if (dataStatus == dataStatus2) {
                    Object obj = result2.mData;
                    if (obj instanceof JDJSONObject) {
                        this.f5140h.f5208c.postValue(new PdBaseProtocolLiveData.Result<>(dataStatus2, (PdDpgRecalculateInfo) JDJSON.parseObject(((JDJSONObject) obj).toJSONString(), PdDpgRecalculateInfo.class), ""));
                        this.f5139g.mResult.removeObserver(this);
                    }
                }
                PdBaseProtocolLiveData.Result.DataStatus dataStatus3 = PdBaseProtocolLiveData.Result.DataStatus.FAIL;
                if (dataStatus == dataStatus3) {
                    this.f5140h.f5208c.postValue(new PdBaseProtocolLiveData.Result<>(dataStatus3, new PdDpgRecalculateInfo(), ""));
                    this.f5139g.mResult.removeObserver(this);
                }
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }
    }
}
