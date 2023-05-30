package com.jdpaysdk.freechargesdk.entity;

import com.laser.open.nfc.model.entity.TrafficCardEntity;
import java.io.Serializable;

/* loaded from: classes18.dex */
public class IssueCardParam implements Serializable {
    private TrafficCardEntity cardDetail;
    private String orderNo;

    public TrafficCardEntity getCardDetail() {
        return this.cardDetail;
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setCardDetail(TrafficCardEntity trafficCardEntity) {
        this.cardDetail = trafficCardEntity;
    }

    public void setOrderNo(String str) {
        this.orderNo = str;
    }
}
