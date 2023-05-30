package com.jdpaysdk.freechargesdk.entity;

import com.laser.open.nfc.model.entity.TrafficCardEntity;
import java.io.Serializable;

/* loaded from: classes18.dex */
public class DefaultCardParam implements Serializable {
    private TrafficCardEntity cardDetail;
    private String model;

    public TrafficCardEntity getCardDetail() {
        return this.cardDetail;
    }

    public String getModel() {
        return this.model;
    }

    public void setCardDetail(TrafficCardEntity trafficCardEntity) {
        this.cardDetail = trafficCardEntity;
    }

    public void setModel(String str) {
        this.model = str;
    }
}
