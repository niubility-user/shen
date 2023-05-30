package com.jingdong.common.jdreactFramework.modules.uphone_sdk;

import org.pjsip.pjsua2.Account;
import org.pjsip.pjsua2.OnInstantMessageParam;
import org.pjsip.pjsua2.OnRegStateParam;

/* loaded from: classes5.dex */
class UphoneAccount extends Account {
    public JDReactNativeUphoneModule backer;

    @Override // org.pjsip.pjsua2.Account
    public void onInstantMessage(OnInstantMessageParam onInstantMessageParam) {
        this.backer.recv_msg(onInstantMessageParam.getMsgBody());
    }

    @Override // org.pjsip.pjsua2.Account
    public void onRegState(OnRegStateParam onRegStateParam) {
        System.out.println("*** On registration state: " + onRegStateParam.getCode() + onRegStateParam.getReason());
        this.backer.reg_callback(onRegStateParam.getCode().swigValue(), onRegStateParam.getReason());
    }
}
