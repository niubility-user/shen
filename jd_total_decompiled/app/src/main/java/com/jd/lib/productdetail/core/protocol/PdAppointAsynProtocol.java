package com.jd.lib.productdetail.core.protocol;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareYuYueInfo;
import com.jd.lib.productdetail.core.repository.BaseRepositoryNew;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.network.toolbox.HttpError;

/* loaded from: classes15.dex */
public class PdAppointAsynProtocol extends BaseRepositoryNew<WareYuYueInfo> {
    private MutableLiveData<WareYuYueInfo> mJsonData;
    private Object obj;

    /* loaded from: classes15.dex */
    public static class Builder {
        private Object object;

        public PdAppointAsynProtocol build(String str) {
            return new PdAppointAsynProtocol(str, this);
        }

        public Object getObject() {
            return this.object;
        }

        public Builder setData(Object obj) {
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
        return "asynInteface";
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepositoryNew
    public LiveData<WareYuYueInfo> getRepository() {
        try {
            Object obj = this.obj;
            if (obj instanceof String) {
                addRequest(JDJSON.parseObject((String) obj));
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
        return this.mJsonData;
    }

    public MutableLiveData<WareYuYueInfo> getmJsonData() {
        return this.mJsonData;
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepositoryNew
    protected void parseError(HttpError httpError) {
        MutableLiveData<WareYuYueInfo> mutableLiveData = this.mJsonData;
        if (mutableLiveData != null) {
            mutableLiveData.postValue(null);
        }
    }

    @Override // com.jd.lib.productdetail.core.repository.BaseRepositoryNew
    protected void parseResponse(String str, ExceptionReporter exceptionReporter) {
        if (!TextUtils.isEmpty(str)) {
            JDJSONObject parseObject = JDJSON.parseObject(str);
            if (!parseObject.isNull("yuyueInfo")) {
                this.mJsonData.postValue((WareYuYueInfo) JDJSON.parseObject(parseObject.optJSONObject("yuyueInfo").toJSONString(), WareYuYueInfo.class));
                return;
            }
            this.mJsonData.postValue(null);
            return;
        }
        this.mJsonData.postValue(null);
    }

    private PdAppointAsynProtocol(String str, Builder builder) {
        super(str);
        this.mJsonData = new MutableLiveData<>();
        this.obj = builder.getObject();
    }
}
