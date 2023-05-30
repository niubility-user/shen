package com.jd.viewkit.templates.view.helper.eventcallback;

import android.view.View;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualChainEvent;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitEventCopyCallBack extends JDViewKitEventAbstractCallBack {
    public JDViewKitEventCopyCallBack(JDViewKitVirtualChainEvent jDViewKitVirtualChainEvent, JDViewKitVirtualEvent jDViewKitVirtualEvent, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view, int i2, JDViewKitChannelModel jDViewKitChannelModel) {
        super(jDViewKitVirtualChainEvent, jDViewKitVirtualEvent, jDViewKitVirtualView, jDViewKitDataSourceModel, view, i2, jDViewKitChannelModel);
    }

    @Override // com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack
    public void failCallBack(int i2, Map<String, Object> map, String str) {
        super.failNextCallBack();
    }

    @Override // com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack
    public void successCallBack(int i2, Map<String, Object> map, String str) {
        super.successNextCallBack();
    }
}
