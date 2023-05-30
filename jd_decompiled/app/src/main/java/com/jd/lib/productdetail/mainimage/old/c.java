package com.jd.lib.productdetail.mainimage.old;

import androidx.lifecycle.Observer;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PdDpgListMoreInfo;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes15.dex */
public class c implements Observer<PdBaseProtocolLiveData.Result<Object>> {

    /* renamed from: g */
    public final /* synthetic */ PdBaseProtocolLiveData f5142g;

    /* renamed from: h */
    public final /* synthetic */ y f5143h;

    public c(y yVar, PdBaseProtocolLiveData pdBaseProtocolLiveData) {
        this.f5143h = yVar;
        this.f5142g = pdBaseProtocolLiveData;
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
                        this.f5143h.b.postValue(new PdBaseProtocolLiveData.Result<>(dataStatus2, (PdDpgListMoreInfo) JDJSON.parseObject(((JDJSONObject) obj).toJSONString(), PdDpgListMoreInfo.class), ""));
                        this.f5142g.mResult.removeObserver(this);
                    }
                }
                PdBaseProtocolLiveData.Result.DataStatus dataStatus3 = PdBaseProtocolLiveData.Result.DataStatus.FAIL;
                if (dataStatus == dataStatus3) {
                    this.f5143h.b.postValue(new PdBaseProtocolLiveData.Result<>(dataStatus3, new PdDpgListMoreInfo(), ""));
                    this.f5142g.mResult.removeObserver(this);
                }
            } catch (Exception e2) {
                ExceptionReporter.reportExceptionToBugly(e2);
            }
        }
    }
}
