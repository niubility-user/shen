package com.jingdong.manto.card;

import android.text.TextUtils;
import java.util.Arrays;

/* loaded from: classes15.dex */
public class CardRequestParameter {
    private int cardHeight;
    private String cardID;
    private int cardWidth;
    private String debugType;
    private int scene;
    private String vendorId;
    private boolean dependsOnCardActivitySwitch = true;
    private String project_id = "";
    private String floorId = "";

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CardRequestParameter cardRequestParameter = (CardRequestParameter) obj;
        return this.scene == cardRequestParameter.scene && b.a(this.cardID, cardRequestParameter.cardID) && b.a(this.debugType, cardRequestParameter.debugType) && b.a(this.vendorId, cardRequestParameter.vendorId);
    }

    public int getCardHeight() {
        return this.cardHeight;
    }

    public String getCardID() {
        return this.cardID;
    }

    public int getCardWidth() {
        return this.cardWidth;
    }

    public String getDebugType() {
        return this.debugType;
    }

    public String getFloorId() {
        return this.floorId;
    }

    public String getProject_id() {
        return this.project_id;
    }

    public int getScene() {
        return this.scene;
    }

    public String getVendorId() {
        return this.vendorId;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.cardID, this.debugType, this.vendorId, Integer.valueOf(this.scene)});
    }

    public boolean isDependsOnCardActivitySwitch() {
        return this.dependsOnCardActivitySwitch;
    }

    public boolean isValid() {
        return (TextUtils.isEmpty(this.cardID) || TextUtils.isEmpty(this.vendorId) || TextUtils.isEmpty(this.debugType)) ? false : true;
    }

    public void setCardHeight(int i2) {
        this.cardHeight = i2;
    }

    public void setCardID(String str) {
        this.cardID = str;
    }

    public void setCardWidth(int i2) {
        this.cardWidth = i2;
    }

    public void setDebugType(String str) {
        this.debugType = str;
    }

    public void setDependsOnCardActivitySwitch(boolean z) {
        this.dependsOnCardActivitySwitch = z;
    }

    public void setFloorId(String str) {
        this.floorId = str;
    }

    public void setProject_id(String str) {
        this.project_id = str;
    }

    public void setScene(int i2) {
        this.scene = i2;
    }

    public void setVendorId(String str) {
        this.vendorId = str;
    }

    public String toString() {
        return "CardRequestParameter{cardID='" + this.cardID + "', cardWidth=" + this.cardWidth + ", cardHeight=" + this.cardHeight + ", vendorId='" + this.vendorId + "', scene=" + this.scene + ", project_id=" + this.project_id + ", floorId=" + this.floorId + ", dependsOnCardActivitySwitch=" + this.dependsOnCardActivitySwitch + '}';
    }
}
