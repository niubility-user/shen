package com.jd.lib.productdetail.core.protocol;

import androidx.lifecycle.MutableLiveData;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.PDOperAppointEntity;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes15.dex */
public class PDLiveDataAppointProtocol extends PDAppointProtocol {
    public MutableLiveData<PDOperAppointEntity> retData;

    public PDLiveDataAppointProtocol(String str) {
        super(str);
        this.retData = new MutableLiveData<>();
    }

    @Override // com.jd.lib.productdetail.core.protocol.PDAppointProtocol, com.jd.lib.productdetail.core.protocol.PDBaseProtocol
    protected Object parseResponse(String str, ExceptionReporter exceptionReporter) {
        PDOperAppointEntity pDOperAppointEntity = (PDOperAppointEntity) JDJSON.parseObject(str, PDOperAppointEntity.class);
        if (pDOperAppointEntity != null) {
            pDOperAppointEntity.needVerify = this.needVerify;
            pDOperAppointEntity.yuShouNewYR = this.yuShouNewYR;
            this.retData.postValue(pDOperAppointEntity);
        }
        return pDOperAppointEntity;
    }
}
