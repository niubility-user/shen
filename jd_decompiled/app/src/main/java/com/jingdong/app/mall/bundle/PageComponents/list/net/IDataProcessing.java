package com.jingdong.app.mall.bundle.PageComponents.list.net;

import android.os.Handler;
import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jingdong.app.mall.bundle.PageComponents.list.footer.States;
import com.jingdong.app.mall.bundle.PageComponents.list.net.BaseGeneralData;
import com.jingdong.app.mall.bundle.PageComponents.list.net.entity.BaseOriginalEntity;
import com.jingdong.app.mall.bundle.PageComponents.list.net.entity.DataChangeInfo;
import com.jingdong.jdsdk.network.toolbox.HttpError;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;
import java.util.List;

/* loaded from: classes19.dex */
public abstract class IDataProcessing<OriginalEntity extends BaseOriginalEntity, GeneralData extends BaseGeneralData> extends IOddItemProcess<GeneralData> {
    protected final DataChangeInfo changeInfo = new DataChangeInfo();
    public IFinalDataCallBack iFinalData;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes19.dex */
    public static class FailRunnable<OriginalEntity extends BaseOriginalEntity, GeneralData extends BaseGeneralData> implements Runnable {
        private final GeneralData generalData;
        private final IDataProcessing<OriginalEntity, GeneralData> iDataProcessing;

        public FailRunnable(IDataProcessing<OriginalEntity, GeneralData> iDataProcessing, GeneralData generaldata) {
            this.iDataProcessing = iDataProcessing;
            this.generalData = generaldata;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.iDataProcessing.onFail(this.generalData, null);
        }
    }

    /* loaded from: classes19.dex */
    private static class HttpListener<OriginalEntity extends BaseOriginalEntity, GeneralData extends BaseGeneralData> implements HttpGroup.OnCommonListener {
        private final GeneralData generalData;
        private final Handler handler;
        private final IDataProcessing<OriginalEntity, GeneralData> iDataProcessing;
        private final Class<OriginalEntity> originalEntityClass;

        public HttpListener(IDataProcessing<OriginalEntity, GeneralData> iDataProcessing, Class<OriginalEntity> cls, Handler handler, GeneralData generaldata) {
            this.iDataProcessing = iDataProcessing;
            this.originalEntityClass = cls;
            this.handler = handler;
            this.generalData = generaldata;
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnEndListener
        public void onEnd(HttpResponse httpResponse) {
            BaseOriginalEntity baseOriginalEntity;
            if (this.handler == null) {
                return;
            }
            try {
                baseOriginalEntity = (BaseOriginalEntity) JDJSON.parseObject(httpResponse.getString(), this.originalEntityClass);
            } catch (Exception unused) {
                baseOriginalEntity = null;
            }
            if (baseOriginalEntity != null && TextUtils.equals("0", baseOriginalEntity.code)) {
                baseOriginalEntity.isCache = httpResponse.isCache();
                this.handler.post(new SuccessRunnable(this.iDataProcessing, baseOriginalEntity, this.generalData));
                return;
            }
            onError(null);
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnErrorListener
        public void onError(HttpError httpError) {
            Handler handler = this.handler;
            if (handler == null) {
                return;
            }
            handler.post(new FailRunnable(this.iDataProcessing, this.generalData));
        }

        @Override // com.jingdong.jdsdk.network.toolbox.HttpGroup.OnReadyListener
        public void onReady(HttpGroup.HttpSettingParams httpSettingParams) {
        }
    }

    /* loaded from: classes19.dex */
    private static class SuccessRunnable<OriginalEntity extends BaseOriginalEntity, GeneralData extends BaseGeneralData> implements Runnable {

        /* renamed from: e  reason: collision with root package name */
        private final OriginalEntity f8095e;
        private final GeneralData generalData;
        private final IDataProcessing<OriginalEntity, GeneralData> iDataProcessing;

        public SuccessRunnable(IDataProcessing<OriginalEntity, GeneralData> iDataProcessing, OriginalEntity originalentity, GeneralData generaldata) {
            this.iDataProcessing = iDataProcessing;
            this.f8095e = originalentity;
            this.generalData = generaldata;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.iDataProcessing.onSuccess(this.f8095e, this.generalData);
        }
    }

    public abstract void addFooter(GeneralData generaldata, List list);

    public abstract void addLocalList(GeneralData generaldata, List list);

    public abstract void before(HttpSetting httpSetting, GeneralData generaldata);

    public HttpGroup.HttpTaskListener getListener(Class<OriginalEntity> cls, Handler handler, GeneralData generaldata) {
        return new HttpListener(this, cls, handler, generaldata);
    }

    public abstract boolean judgeListExp(GeneralData generaldata, List list);

    public abstract void onFail(GeneralData generaldata, States states);

    public abstract void onSuccess(OriginalEntity originalentity, GeneralData generaldata);

    public void setFinalDataCallBack(IFinalDataCallBack iFinalDataCallBack) {
        this.iFinalData = iFinalDataCallBack;
    }
}
