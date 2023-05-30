package com.laser.open.nfc.model.entity;

import java.util.List;

/* loaded from: classes12.dex */
public class QueryTrafficCardsResp extends BaseResp {
    private List<TrafficCardEntity> trafficCards;

    public QueryTrafficCardsResp(int i2, String str) {
        super(i2, str);
    }

    public List<TrafficCardEntity> getTrafficCards() {
        return this.trafficCards;
    }

    public void setTrafficCards(List<TrafficCardEntity> list) {
        this.trafficCards = list;
    }

    @Override // com.laser.open.nfc.model.entity.BaseResp
    public String toString() {
        return "QueryTrafficCardsResp{trafficCards=" + this.trafficCards + '}';
    }

    public QueryTrafficCardsResp() {
    }
}
