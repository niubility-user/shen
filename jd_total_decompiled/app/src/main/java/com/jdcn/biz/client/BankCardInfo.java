package com.jdcn.biz.client;

import java.io.Serializable;

/* loaded from: classes18.dex */
public class BankCardInfo implements Serializable {
    static final long serialVersionUID = 7910645165587291997L;
    public String cardNumber;
    public String cardType;
    public String issuer;
    public String owner;
    public String validDate;

    public String getCardNumber() {
        return this.cardNumber;
    }

    public String getCardType() {
        return this.cardType;
    }

    public String getIssuer() {
        return this.issuer;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getValidDate() {
        return this.validDate;
    }

    public void setCardNumber(String str) {
        this.cardNumber = str;
    }

    public void setCardType(String str) {
        this.cardType = str;
    }

    public void setIssuer(String str) {
        this.issuer = str;
    }

    public void setOwner(String str) {
        this.owner = str;
    }

    public void setValidDate(String str) {
        this.validDate = str;
    }
}
