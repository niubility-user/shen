package com.laser.open.nfc.model.entity;

import java.io.Serializable;
import java.util.List;

/* loaded from: classes12.dex */
public class QueryTrafficCardInfoResp extends BaseResp implements Serializable {
    private String balance;
    private CardArtEntity cardArt;
    private String cardNo;
    private boolean isDefault;
    private String logicNo;
    private String metroStatus;
    private List<TrafficCardTradeInfoEntity> timesRecords;
    private List<TrafficCardTradeInfoEntity> transRecords;
    private String validateDate;

    public QueryTrafficCardInfoResp(int i2, String str) {
        super(i2, str);
    }

    public String getBalance() {
        return this.balance;
    }

    public CardArtEntity getCardArt() {
        return this.cardArt;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public String getLogicNo() {
        return this.logicNo;
    }

    public String getMetroStatus() {
        return this.metroStatus;
    }

    public List<TrafficCardTradeInfoEntity> getTimesRecords() {
        return this.timesRecords;
    }

    public List<TrafficCardTradeInfoEntity> getTransRecords() {
        return this.transRecords;
    }

    public String getValidateDate() {
        return this.validateDate;
    }

    public boolean isDefault() {
        return this.isDefault;
    }

    public void setBalance(String str) {
        this.balance = str;
    }

    public void setCardArt(CardArtEntity cardArtEntity) {
        this.cardArt = cardArtEntity;
    }

    public void setCardNo(String str) {
        this.cardNo = str;
    }

    public void setDefault(boolean z) {
        this.isDefault = z;
    }

    public void setLogicNo(String str) {
        this.logicNo = str;
    }

    public void setMetroStatus(String str) {
        this.metroStatus = str;
    }

    public void setTimesRecords(List<TrafficCardTradeInfoEntity> list) {
        this.timesRecords = list;
    }

    public void setTransRecords(List<TrafficCardTradeInfoEntity> list) {
        this.transRecords = list;
    }

    public void setValidateDate(String str) {
        this.validateDate = str;
    }

    public QueryTrafficCardInfoResp() {
    }
}
