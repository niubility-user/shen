package com.jd.viewkit.templates.view.helper.eventcallback;

import android.view.View;
import com.jd.lib.babel.task.viewkit.EventModelKey;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualChainEvent;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.thirdinterface.model.JDViewKitMtaModel;
import com.jd.viewkit.tool.JSONTool;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitEventAppointRemindCallBack extends JDViewKitEventAbstractCallBack {
    private Map<String, Object> mParams;

    public JDViewKitEventAppointRemindCallBack(JDViewKitVirtualChainEvent jDViewKitVirtualChainEvent, JDViewKitVirtualEvent jDViewKitVirtualEvent, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view, int i2, JDViewKitChannelModel jDViewKitChannelModel) {
        super(jDViewKitVirtualChainEvent, jDViewKitVirtualEvent, jDViewKitVirtualView, jDViewKitDataSourceModel, view, i2, jDViewKitChannelModel);
    }

    @Override // com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack
    public void failCallBack(int i2, Map<String, Object> map, String str) {
        Map<String, Object> map2;
        try {
            if (this.virtualView != null && this.dataSourceModel != null && (map2 = this.mParams) != null && this.view != null) {
                Map map3 = (Map) map2.get(EventModelKey.SRV);
                if (map3 != null && (map3 instanceof Map)) {
                    map3.put("jud", "0");
                }
                this.virtualView.getServiceModel().getMtaService().click(new JDViewKitMtaModel(JSONTool.getJsonString(this.mParams), this.dataSourceModel.getParamsModel()), this.view.getContext());
            }
            super.failNextCallBack();
        } catch (Throwable unused) {
        }
    }

    public Map<String, Object> getmParams() {
        return this.mParams;
    }

    public void setmParams(Map<String, Object> map) {
        this.mParams = map;
    }

    @Override // com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack
    public void successCallBack(int i2, Map<String, Object> map, String str) {
        Map<String, Object> map2;
        try {
            if (this.virtualView != null && this.dataSourceModel != null && (map2 = this.mParams) != null && this.view != null) {
                Map map3 = (Map) map2.get(EventModelKey.SRV);
                if (map3 != null && (map3 instanceof Map)) {
                    map3.put("jud", "1");
                }
                this.virtualView.getServiceModel().getMtaService().click(new JDViewKitMtaModel(JSONTool.getJsonString(this.mParams), this.dataSourceModel.getParamsModel()), this.view.getContext());
            }
            super.successNextCallBack();
        } catch (Throwable unused) {
        }
    }
}
