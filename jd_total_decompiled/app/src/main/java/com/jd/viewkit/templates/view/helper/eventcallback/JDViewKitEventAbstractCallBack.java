package com.jd.viewkit.templates.view.helper.eventcallback;

import android.view.View;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualChainEvent;
import com.jd.viewkit.templates.model.event.JDViewKitVirtualEvent;
import com.jd.viewkit.templates.view.helper.JDViewKitEventHelper;
import com.jd.viewkit.thirdinterface.model.JDViewKitEventCallBack;
import com.jd.viewkit.tool.StringTool;

/* loaded from: classes18.dex */
public abstract class JDViewKitEventAbstractCallBack implements JDViewKitEventCallBack {
    public static int CallBackType_Chain = 1;
    public static int CallBackType_Single;
    protected int callBackType;
    protected JDViewKitChannelModel channelModel;
    protected JDViewKitDataSourceModel dataSourceModel;
    protected View view;
    protected JDViewKitVirtualChainEvent virtualChainEvent;
    protected JDViewKitVirtualEvent virtualEvent;
    protected JDViewKitVirtualView virtualView;

    public JDViewKitEventAbstractCallBack(JDViewKitVirtualChainEvent jDViewKitVirtualChainEvent, JDViewKitVirtualEvent jDViewKitVirtualEvent, JDViewKitVirtualView jDViewKitVirtualView, JDViewKitDataSourceModel jDViewKitDataSourceModel, View view, int i2, JDViewKitChannelModel jDViewKitChannelModel) {
        this.virtualChainEvent = jDViewKitVirtualChainEvent;
        this.virtualEvent = jDViewKitVirtualEvent;
        this.virtualView = jDViewKitVirtualView;
        this.dataSourceModel = jDViewKitDataSourceModel;
        this.view = view;
        this.callBackType = i2;
        this.channelModel = jDViewKitChannelModel;
    }

    public void failNextCallBack() {
        JDViewKitVirtualEvent jDViewKitVirtualEvent;
        if (this.callBackType == CallBackType_Chain && (jDViewKitVirtualEvent = this.virtualEvent) != null && StringTool.isNotEmpty(jDViewKitVirtualEvent.getFailStr())) {
            JDViewKitEventHelper.eventChainHandleInfo(this.virtualChainEvent, this.virtualEvent.getFailStr(), this.virtualView, this.dataSourceModel, this.view, this, this.channelModel);
        }
    }

    public int getCallBackType() {
        return this.callBackType;
    }

    public void successNextCallBack() {
        JDViewKitVirtualEvent jDViewKitVirtualEvent;
        if (this.callBackType == CallBackType_Chain && (jDViewKitVirtualEvent = this.virtualEvent) != null && StringTool.isNotEmpty(jDViewKitVirtualEvent.getSuccessStr())) {
            JDViewKitEventHelper.eventChainHandleInfo(this.virtualChainEvent, this.virtualEvent.getSuccessStr(), this.virtualView, this.dataSourceModel, this.view, this, this.channelModel);
        }
    }
}
