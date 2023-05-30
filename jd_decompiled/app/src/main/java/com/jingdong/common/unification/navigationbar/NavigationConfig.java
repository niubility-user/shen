package com.jingdong.common.unification.navigationbar;

/* loaded from: classes6.dex */
public class NavigationConfig {
    private int bucketType;
    private int currentMode;
    private String functionId;
    public boolean isNewIconState;
    private boolean isShowRedPoint;
    private String label;
    private int navigationId;

    public NavigationConfig() {
    }

    public int getBucketType() {
        return this.bucketType;
    }

    public int getCurrentMode() {
        return this.currentMode;
    }

    public String getFunctionId() {
        return this.functionId;
    }

    public String getLabel() {
        return this.label;
    }

    public int getNavigationId() {
        return this.navigationId;
    }

    public boolean isNewIconState() {
        return this.isNewIconState;
    }

    public boolean isShowRedPoint() {
        return this.isShowRedPoint;
    }

    public void setBucketType(int i2) {
        this.bucketType = i2;
    }

    public void setCurrentMode(int i2) {
        this.currentMode = i2;
    }

    public void setFunctionId(String str) {
        this.functionId = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setNavigationId(int i2) {
        this.navigationId = i2;
    }

    public void setNewIconState(boolean z) {
        this.isNewIconState = z;
    }

    public void setShowRedPoint(boolean z) {
        this.isShowRedPoint = z;
    }

    public String toString() {
        return "NavigationConfig{navigationId=" + this.navigationId + ", functionId='" + this.functionId + "', isShowRedPoint=" + this.isShowRedPoint + ", label='" + this.label + "', bucketType=" + this.bucketType + ", currentMode=" + this.currentMode + ", isNewIconState=" + this.isNewIconState + '}';
    }

    public NavigationConfig(int i2, String str, boolean z) {
        this.navigationId = i2;
        this.functionId = str;
        this.isShowRedPoint = z;
        this.label = "";
    }

    public NavigationConfig(int i2, String str, String str2) {
        this.navigationId = i2;
        this.functionId = str;
        this.label = str2;
        this.isShowRedPoint = false;
    }

    public NavigationConfig(int i2, String str, String str2, int i3) {
        this.navigationId = i2;
        this.functionId = str;
        this.label = str2;
        this.isShowRedPoint = false;
        this.bucketType = i3;
    }
}
