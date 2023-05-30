package com.jd.lib.productdetail.core.repository;

import android.text.TextUtils;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.anotation.JSONField;
import com.jd.lib.productdetail.core.entitys.WareScfSkuInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.BusinessSearchInfo;
import com.jd.lib.productdetail.core.protocol.PdLVBody;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;

/* loaded from: classes15.dex */
public class PdSearchDataRepository extends BaseRepositoryNew<SearchInfoResult> {
    private MutableLiveData<Object> mInputParams;
    private MutableLiveData<SearchInfoResult> mSearchInfo;

    /* loaded from: classes.dex */
    public static class SearchFloorInputParams {
        @JSONField(name = "darkmode")
        public int darkModel;
        @JSONField(name = PdLVBody.GCLAT)
        public String gcLat;
        @JSONField(name = PdLVBody.GCLNG)
        public String gcLng;
        @JSONField(name = "intefaceType")
        public String intefaceType = "asynIntefaceType";
        @JSONField(name = PdLVBody.LATITUDE)
        public String latitude;
        @JSONField(name = PdLVBody.LONGITUDE)
        public String longitude;
        @JSONField(name = "skuId")
        public String skuId;

        public String toString() {
            return "SearchFloorInputParams{intefaceType='" + this.intefaceType + "', skuId='" + this.skuId + "', gcLat='" + this.gcLat + "', gcLng='" + this.gcLng + "', latitude='" + this.latitude + "', longitude='" + this.longitude + "'}";
        }
    }

    /* loaded from: classes.dex */
    public static class SearchInfoResult {
        @JSONField(name = "code")
        public int code;
        @JSONField(name = "b2cdata")
        public WareScfSkuInfo scfSkuInfo;
        @JSONField(name = "searchInfo")
        public BusinessSearchInfo searchInfo;
    }

    public PdSearchDataRepository(String str) {
        super(str);
        MutableLiveData<Object> mutableLiveData = new MutableLiveData<>();
        this.mInputParams = mutableLiveData;
        this.mSearchInfo = (MutableLiveData) Transformations.switchMap(mutableLiveData, new Function() { // from class: com.jd.lib.productdetail.core.repository.a
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return PdSearchDataRepository.this.b(obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ LiveData b(Object obj) {
        if (this.mInputParams.getValue() != null) {
            addRequest(this.mInputParams.getValue());
        }
        return new MutableLiveData();
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepositoryNew
    protected String getFunctionId() {
        return "asynInteface";
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepositoryNew
    public LiveData<SearchInfoResult> getRepository() {
        return this.mSearchInfo;
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepositoryNew
    protected void parseError(HttpError httpError) {
        MutableLiveData<SearchInfoResult> mutableLiveData = this.mSearchInfo;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(null);
        }
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepositoryNew
    protected void parseResponse(String str, ExceptionReporter exceptionReporter) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            this.mSearchInfo.postValue((SearchInfoResult) JDJSON.parseObject(str, SearchInfoResult.class));
        } catch (Exception unused) {
            this.mSearchInfo.postValue(null);
        }
    }

    public void setInputParams(Object obj) {
        this.mInputParams.postValue(obj);
    }
}
