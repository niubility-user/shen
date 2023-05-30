package com.jd.viewkit.thirdinterface.model;

/* loaded from: classes18.dex */
public class JDViewKitMtaModel {
    private String jumpJsonString;
    private JDViewKitParamsModel paramsModel;

    public JDViewKitMtaModel(String str, JDViewKitParamsModel jDViewKitParamsModel) {
        this.jumpJsonString = str;
        this.paramsModel = jDViewKitParamsModel;
    }

    public String getJumpJsonString() {
        return this.jumpJsonString;
    }

    public JDViewKitParamsModel getParamsModel() {
        return this.paramsModel;
    }

    public void setJumpJsonString(String str) {
        this.jumpJsonString = str;
    }

    public void setParamsModel(JDViewKitParamsModel jDViewKitParamsModel) {
        this.paramsModel = jDViewKitParamsModel;
    }
}
