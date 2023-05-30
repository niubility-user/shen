package com.jdpaysdk.freechargesdk.entity;

import com.laser.open.nfc.model.entity.TrafficCardEntity;
import java.io.Serializable;

/* loaded from: classes18.dex */
public class DeleteCardParam implements Serializable {
    private String accountId;
    private TrafficCardEntity cardDetail;
    private int operType;

    public String getAccountId() {
        return this.accountId;
    }

    public TrafficCardEntity getCardDetail() {
        return this.cardDetail;
    }

    public int getOperType() {
        return this.operType;
    }

    public void setAccountId(String str) {
        this.accountId = str;
    }

    public void setCardDetail(TrafficCardEntity trafficCardEntity) {
        this.cardDetail = trafficCardEntity;
    }

    public void setOperType(int i2) {
        this.operType = i2;
    }
}
