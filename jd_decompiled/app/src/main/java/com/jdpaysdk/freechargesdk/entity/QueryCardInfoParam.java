package com.jdpaysdk.freechargesdk.entity;

import com.laser.open.nfc.model.entity.TrafficCardEntity;
import java.io.Serializable;

/* loaded from: classes18.dex */
public class QueryCardInfoParam implements Serializable {
    private TrafficCardEntity cardDetail;
    private int dataType;

    public TrafficCardEntity getCardDetail() {
        return this.cardDetail;
    }

    public int getDataType() {
        return this.dataType;
    }

    public void setCardDetail(TrafficCardEntity trafficCardEntity) {
        this.cardDetail = trafficCardEntity;
    }

    public void setDataType(int i2) {
        this.dataType = i2;
    }
}
