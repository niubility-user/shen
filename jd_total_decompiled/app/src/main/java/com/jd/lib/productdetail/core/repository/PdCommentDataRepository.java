package com.jd.lib.productdetail.core.repository;

import android.text.TextUtils;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.comment.PdCommentRepositoryEntity;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;

/* loaded from: classes15.dex */
public class PdCommentDataRepository extends BaseRepository {
    private static final String FUNCTIONID = "getLegoWareDetailComment";
    private MediatorLiveData<String> mJsonData;
    private PdCommentRepositoryEntity mPdCommentRepositoryEntity;

    /* loaded from: classes15.dex */
    public static class Builder {
        private PdCommentRepositoryEntity pdCommentRepositoryEntity;

        public PdCommentDataRepository build(String str) {
            return new PdCommentDataRepository(str, this);
        }

        public PdCommentRepositoryEntity getPdCommentRepositoryEntity() {
            return this.pdCommentRepositoryEntity;
        }

        public Builder setPdCommentRepositoryEntity(PdCommentRepositoryEntity pdCommentRepositoryEntity) {
            this.pdCommentRepositoryEntity = pdCommentRepositoryEntity;
            return this;
        }
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepository
    protected int getEffect() {
        return 0;
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepository
    protected String getFunctionId() {
        return FUNCTIONID;
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepository
    protected String getHost() {
        return Configuration.getCommentHost();
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepository
    public LiveData<String> getRepository() {
        return null;
    }

    public LiveData<String> loadCommentData() {
        if (this.mJsonData == null) {
            this.mJsonData = new MediatorLiveData<>();
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        PdCommentRepositoryEntity pdCommentRepositoryEntity = this.mPdCommentRepositoryEntity;
        if (pdCommentRepositoryEntity != null) {
            jDJSONObject.put("sku", (Object) pdCommentRepositoryEntity.getSku());
            jDJSONObject.put("venderId", (Object) this.mPdCommentRepositoryEntity.getVenderId());
            jDJSONObject.put("category", (Object) this.mPdCommentRepositoryEntity.getCategory());
            jDJSONObject.put("wareType", (Object) this.mPdCommentRepositoryEntity.getWareType());
            jDJSONObject.put("shadowMainSku", (Object) this.mPdCommentRepositoryEntity.getShadowMainSku());
            jDJSONObject.put("shieldCurrentComment", (Object) this.mPdCommentRepositoryEntity.getShieldCurrentComment());
            jDJSONObject.put("shopType", (Object) this.mPdCommentRepositoryEntity.getShopType());
            jDJSONObject.put("shopId", (Object) this.mPdCommentRepositoryEntity.getShopId());
            jDJSONObject.put("isNew", (Object) this.mPdCommentRepositoryEntity.isXinpin());
            jDJSONObject.put("newTitle", (Object) this.mPdCommentRepositoryEntity.getXinPinTitle());
            int i2 = this.mPdCommentRepositoryEntity.commentNum;
            if (i2 > 0) {
                jDJSONObject.put("commentNum", (Object) Integer.valueOf(i2));
            }
            jDJSONObject.put("extInfo", (Object) this.mPdCommentRepositoryEntity.getExtInfo());
        }
        addRequest(jDJSONObject);
        return Transformations.map(this.mJsonData, new Function<String, String>() { // from class: com.jd.lib.productdetail.core.repository.PdCommentDataRepository.1
            @Override // androidx.arch.core.util.Function
            public String apply(String str) {
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                return str;
            }
        });
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepository
    protected void parseResponse(String str, ExceptionReporter exceptionReporter) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mJsonData.postValue(str);
    }

    private PdCommentDataRepository(String str, Builder builder) {
        super(str);
        if (builder != null) {
            this.mPdCommentRepositoryEntity = builder.getPdCommentRepositoryEntity();
        }
    }
}
