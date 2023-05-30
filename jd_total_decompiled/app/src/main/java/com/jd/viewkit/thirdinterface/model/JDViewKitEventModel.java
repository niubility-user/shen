package com.jd.viewkit.thirdinterface.model;

import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitEventModel {
    public static int jumpTYpe_In = 1;
    public static int jumpType_Out;
    private int index;
    private String jumpJsonString;
    private int jumpType;
    private Map<String, String> otherParams;

    public JDViewKitEventModel(String str) {
        this.jumpType = -1;
        this.index = -1;
        this.jumpJsonString = str;
    }

    public int getIndex() {
        return this.index;
    }

    public String getJumpJsonString() {
        return this.jumpJsonString;
    }

    public int getJumpType() {
        return this.jumpType;
    }

    public Map<String, String> getOtherParams() {
        return this.otherParams;
    }

    public void setIndex(int i2) {
        this.index = i2;
    }

    public void setJumpJsonString(String str) {
        this.jumpJsonString = str;
    }

    public void setJumpType(int i2) {
        this.jumpType = i2;
    }

    public void setOtherParams(Map<String, String> map) {
        this.otherParams = map;
    }

    public JDViewKitEventModel(int i2, String str) {
        this.jumpType = -1;
        this.index = -1;
        this.index = i2;
        this.jumpJsonString = str;
    }

    public JDViewKitEventModel(int i2, int i3, String str) {
        this.jumpType = -1;
        this.index = -1;
        this.index = i2;
        this.jumpType = i3;
        this.jumpJsonString = str;
    }
}
