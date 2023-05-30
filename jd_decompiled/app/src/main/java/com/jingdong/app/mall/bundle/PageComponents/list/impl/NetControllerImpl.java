package com.jingdong.app.mall.bundle.PageComponents.list.impl;

import android.os.Handler;
import com.jingdong.app.mall.bundle.PageComponents.list.net.BaseGeneralData;
import com.jingdong.app.mall.bundle.PageComponents.list.net.IDataProcessing;
import com.jingdong.app.mall.bundle.PageComponents.list.net.IFinalDataCallBack;
import com.jingdong.app.mall.bundle.PageComponents.list.net.INetController;
import com.jingdong.app.mall.bundle.PageComponents.list.net.entity.BaseOriginalEntity;
import com.jingdong.jdsdk.config.Configuration;
import com.jingdong.jdsdk.network.toolbox.HttpGroup;
import com.jingdong.jdsdk.network.toolbox.HttpSetting;

/* loaded from: classes19.dex */
public class NetControllerImpl implements INetController {
    private final NetBuilder nb;

    /* loaded from: classes19.dex */
    public static class NetBuilder {
        private IDataProcessing bottomIncreaseDataProcessing;
        private IDataProcessing firstDataProcessing;
        private IDataProcessing fullLoadDataProcessing;
        private String functionId;
        private BaseGeneralData generalData = new BaseGeneralData();
        private Handler handler;
        private HttpGroup httpGroup;
        private Class<BaseOriginalEntity> originalEntityClass;
        private IDataProcessing topIncreaseDataProcessing;

        public NetControllerImpl create() {
            return new NetControllerImpl(this);
        }

        public NetBuilder setBottomIncreaseDataProcessing(IDataProcessing iDataProcessing) {
            this.bottomIncreaseDataProcessing = iDataProcessing;
            return this;
        }

        public NetBuilder setFirstDataProcessing(IDataProcessing iDataProcessing) {
            this.firstDataProcessing = iDataProcessing;
            return this;
        }

        public NetBuilder setFullLoadDataProcessing(IDataProcessing iDataProcessing) {
            this.fullLoadDataProcessing = iDataProcessing;
            return this;
        }

        public NetBuilder setFunctionId(String str) {
            this.functionId = str;
            return this;
        }

        public NetBuilder setGeneralData(BaseGeneralData baseGeneralData) {
            this.generalData = baseGeneralData;
            return this;
        }

        public NetBuilder setHandler(Handler handler) {
            this.handler = handler;
            return this;
        }

        public NetBuilder setHttpGroup(HttpGroup httpGroup) {
            this.httpGroup = httpGroup;
            return this;
        }

        public NetBuilder setOriginalEntity(Class cls) {
            this.originalEntityClass = cls;
            return this;
        }

        public NetBuilder setTopIncreaseDataProcessing(IDataProcessing iDataProcessing) {
            this.topIncreaseDataProcessing = iDataProcessing;
            return this;
        }
    }

    private void fetch(IDataProcessing iDataProcessing, IFinalDataCallBack iFinalDataCallBack) {
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFunctionId(this.nb.functionId);
        httpSetting.setHost(Configuration.getPortalHost());
        httpSetting.setUseFastJsonParser(true);
        iDataProcessing.setFinalDataCallBack(iFinalDataCallBack);
        iDataProcessing.before(httpSetting, this.nb.generalData);
        httpSetting.setListener(iDataProcessing.getListener(this.nb.originalEntityClass, this.nb.handler, this.nb.generalData));
        this.nb.httpGroup.add(httpSetting);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.INetController
    public void bottomIncrease(IFinalDataCallBack iFinalDataCallBack) {
        fetch(this.nb.bottomIncreaseDataProcessing, iFinalDataCallBack);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.INetController
    public void firstFetch(IFinalDataCallBack iFinalDataCallBack) {
        fetch(this.nb.firstDataProcessing, iFinalDataCallBack);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.INetController
    public void fullLoad(IFinalDataCallBack iFinalDataCallBack) {
        fetch(this.nb.fullLoadDataProcessing, iFinalDataCallBack);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IGeneralData
    public BaseGeneralData getGeneralData() {
        return this.nb.generalData;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.INetController
    public void topIncrease(IFinalDataCallBack iFinalDataCallBack) {
        fetch(this.nb.topIncreaseDataProcessing, iFinalDataCallBack);
    }

    private NetControllerImpl(NetBuilder netBuilder) {
        this.nb = netBuilder;
    }
}
