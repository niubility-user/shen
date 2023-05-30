package com.jingdong.common.jdreactFramework.modules.uphone_sdk;

import org.pjsip.pjsua2.Endpoint;
import org.pjsip.pjsua2.OnTransportStateParam;
import org.pjsip.pjsua2.pjsip_transport_state;

/* loaded from: classes5.dex */
public class UphoneEndpoint extends Endpoint {
    public JDReactNativeUphoneModule backer;

    @Override // org.pjsip.pjsua2.Endpoint
    public void onTransportState(OnTransportStateParam onTransportStateParam) {
        super.onTransportState(onTransportStateParam);
        if (onTransportStateParam.getState() == pjsip_transport_state.PJSIP_TP_STATE_DISCONNECTED) {
            this.backer.onTransportState("disconnected");
        }
    }
}
