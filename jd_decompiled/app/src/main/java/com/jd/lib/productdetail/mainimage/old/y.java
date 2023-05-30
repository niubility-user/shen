package com.jd.lib.productdetail.mainimage.old;

import androidx.lifecycle.MutableLiveData;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.entitys.PdDpgListMoreInfo;
import com.jd.lib.productdetail.core.entitys.PdDpgRecalculateInfo;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jingdong.common.BaseActivity;

/* loaded from: classes15.dex */
public class y {
    public MutableLiveData<PdBaseProtocolLiveData.Result<PdDpgListLayerInfo>> a = new MutableLiveData<>();
    public MutableLiveData<PdBaseProtocolLiveData.Result<PdDpgListMoreInfo>> b = new MutableLiveData<>();

    /* renamed from: c */
    public MutableLiveData<PdBaseProtocolLiveData.Result<PdDpgRecalculateInfo>> f5208c = new MutableLiveData<>();

    public void a(BaseActivity baseActivity) {
        this.a.removeObservers(baseActivity);
        PdBaseProtocolLiveData.Result.DataStatus dataStatus = PdBaseProtocolLiveData.Result.DataStatus.NONE;
        this.a = new MutableLiveData<>(new PdBaseProtocolLiveData.Result(dataStatus, null, null));
        this.f5208c.removeObservers(baseActivity);
        this.f5208c = new MutableLiveData<>(new PdBaseProtocolLiveData.Result(dataStatus, null, null));
        this.b.removeObservers(baseActivity);
        this.b = new MutableLiveData<>(new PdBaseProtocolLiveData.Result(dataStatus, null, null));
    }
}
