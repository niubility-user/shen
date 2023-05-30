package com.jd.lib.productdetail.core.repository;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.jd.framework.json.JDJSON;
import com.jd.lib.productdetail.core.entitys.gift.PdGiftResultData;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;

/* loaded from: classes15.dex */
public class PdGiftSaveRepository extends BaseRepositoryNew<PdGiftResultData> {
    private MutableLiveData<PdGiftResultData> mJsonData;
    private Object obj;

    /* loaded from: classes15.dex */
    public static class Builder {
        private Object object;

        public PdGiftSaveRepository build(String str) {
            return new PdGiftSaveRepository(str, this);
        }

        public Object getObject() {
            return this.object;
        }

        public Builder setPdCommentRepositoryEntity(Object obj) {
            this.object = obj;
            return this;
        }
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepositoryNew
    protected int getEffect() {
        return 0;
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepositoryNew
    protected String getFunctionId() {
        return "gift";
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepositoryNew
    public LiveData<PdGiftResultData> getRepository() {
        try {
            Object obj = this.obj;
            if (obj != null) {
                addRequest(obj);
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        return this.mJsonData;
    }

    public MutableLiveData<PdGiftResultData> getmJsonData() {
        return this.mJsonData;
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepositoryNew
    protected void parseError(HttpError httpError) {
        MutableLiveData<PdGiftResultData> mutableLiveData = this.mJsonData;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(null);
        }
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepositoryNew
    protected void parseResponse(String str, ExceptionReporter exceptionReporter) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mJsonData.postValue((PdGiftResultData) JDJSON.parseObject(str, PdGiftResultData.class));
    }

    private PdGiftSaveRepository(String str, Builder builder) {
        super(str);
        this.mJsonData = new MutableLiveData<>();
        this.obj = builder.getObject();
    }
}
