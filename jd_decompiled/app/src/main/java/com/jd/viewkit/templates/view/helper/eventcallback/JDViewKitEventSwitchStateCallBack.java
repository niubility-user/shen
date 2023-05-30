package com.jd.viewkit.templates.view.helper.eventcallback;

import android.view.View;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualChainEvent;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.view.JDViewKitMultistateView;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitEventSwitchStateCallBack extends JDViewKitEventAbstractCallBack {
    private JDViewKitMultistateView multistateView;

    public JDViewKitEventSwitchStateCallBack(JDViewKitVirtualChainEvent jDViewKitVirtualChainEvent, JDViewKitVirtualEvent jDViewKitVirtualEvent, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view, int i2, JDViewKitChannelModel jDViewKitChannelModel) {
        super(jDViewKitVirtualChainEvent, jDViewKitVirtualEvent, jDViewKitVirtualView, jDViewKitDataSourceModel, view, i2, jDViewKitChannelModel);
    }

    @Override // com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack
    public void failCallBack(int i2, Map<String, Object> map, String str) {
        super.failNextCallBack();
    }

    public JDViewKitMultistateView getMultistateView() {
        return this.multistateView;
    }

    public void setMultistateView(JDViewKitMultistateView jDViewKitMultistateView) {
        this.multistateView = jDViewKitMultistateView;
    }

    @Override // com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack
    public void successCallBack(int i2, Map<String, Object> map, String str) {
        super.successNextCallBack();
    }
}
