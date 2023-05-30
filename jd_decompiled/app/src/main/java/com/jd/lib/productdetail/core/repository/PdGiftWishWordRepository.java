package com.jd.lib.productdetail.core.repository;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;

/* loaded from: classes15.dex */
public class PdGiftWishWordRepository extends BaseRepository {
    private MutableLiveData<String> mJsonData;
    private JDJSONObject mPdCouponRepositoryEntity;

    /* loaded from: classes15.dex */
    public static class Builder {
        private JDJSONObject jdjsonObject;

        public PdGiftWishWordRepository build(String str) {
            return new PdGiftWishWordRepository(str, this);
        }

        public JDJSONObject getJdjsonObject() {
            return this.jdjsonObject;
        }

        public Builder setPdCommentRepositoryEntity(JDJSONObject jDJSONObject) {
            this.jdjsonObject = jDJSONObject;
            return this;
        }
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepository
    protected int getEffect() {
        return 0;
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepository
    protected String getFunctionId() {
        return "queryMaterialAdverts";
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepository
    public LiveData<String> getRepository() {
        try {
            JDJSONObject jDJSONObject = this.mPdCouponRepositoryEntity;
            if (jDJSONObject != null) {
                addRequest(jDJSONObject);
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        return this.mJsonData;
    }

    public MutableLiveData<String> getmJsonData() {
        return this.mJsonData;
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepository
    protected void parseError(HttpError httpError) {
        MutableLiveData<String> mutableLiveData = this.mJsonData;
        if (mutableLiveData != null) {
            mutableLiveData.postValue("");
        }
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepository
    protected void parseResponse(String str, ExceptionReporter exceptionReporter) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mJsonData.postValue(str);
    }

    private PdGiftWishWordRepository(String str, Builder builder) {
        super(str);
        this.mJsonData = new MutableLiveData<>();
        if (builder != null) {
            this.mPdCouponRepositoryEntity = builder.getJdjsonObject();
        }
    }
}
