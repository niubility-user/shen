package com.jd.lib.productdetail.mainimage.old;

import androidx.lifecycle.Observer;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes15.dex */
public class d implements Observer<PdBaseProtocolLiveData.Result<Object>> {

    /* renamed from: g */
    public final /* synthetic */ PdBaseProtocolLiveData f5146g;

    /* renamed from: h */
    public final /* synthetic */ y f5147h;

    public d(y yVar, PdBaseProtocolLiveData pdBaseProtocolLiveData) {
        this.f5147h = yVar;
        this.f5146g = pdBaseProtocolLiveData;
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
                        this.f5147h.a.postValue(new PdBaseProtocolLiveData.Result<>(dataStatus2, (PdDpgListLayerInfo) JDJSON.parseObject(((JDJSONObject) obj).toJSONString(), PdDpgListLayerInfo.class), ""));
                        this.f5146g.mResult.removeObserver(this);
                    }
                }
                PdBaseProtocolLiveData.Result.DataStatus dataStatus3 = PdBaseProtocolLiveData.Result.DataStatus.FAIL;
                if (dataStatus == dataStatus3) {
                    this.f5147h.a.postValue(new PdBaseProtocolLiveData.Result<>(dataStatus3, new PdDpgListLayerInfo(), ""));
                    this.f5146g.mResult.removeObserver(this);
                }
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }
    }
}
