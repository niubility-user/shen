package com.jingdong.app.mall.bundle.familyhelper.entity;

/* loaded from: classes18.dex */
public class FamilyRelationDTO {
    public String imgUrl;
    public boolean isInvite;
    public String label;
    public String labelName;
    public String nickname;
    public String pin;
    public String type;
    public int unReadNum;

    public boolean isInvite() {
        return this.isInvite;
    }

    public void setInvite(boolean z) {
        this.isInvite = z;
    }

    public String toString() {
        return "FamilyRelationDTO{nickname='" + this.nickname + "', imgUrl='" + this.imgUrl + "', labelName='" + this.labelName + "', pin='" + this.pin + "', label='" + this.label + "', type='" + this.type + "', unReadNum=" + this.unReadNum + ", isInvite=" + this.isInvite + '}';
    }
}
