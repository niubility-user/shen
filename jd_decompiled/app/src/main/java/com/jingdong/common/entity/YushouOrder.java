package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class YushouOrder implements Serializable {
    public static final int LADDER_TYPE_DEFAULT = 0;
    public static final int LADDER_TYPE_GENERATED = 1;
    public static final int LADDER_TYPE_NOT_GENERATE = 2;
    public static final int PAY_TYPE_DEFAULT = 0;
    public static final int PAY_TYPE_ENTIRE = 1;
    public static final int PAY_TYPE_NOT_ENTIRE = 2;
    public static final int YUSHOU_DEFAULT = -2;
    public static final int YUSHOU_EXCEP_STATUS = -1;
    public static final int YUSHOU_FULLPAY_SUCESS = 7;
    public static final int YUSHOU_PAYBARGAIN_AND_PAYBALANCE = 5;
    public static final int YUSHOU_PAYBARGAIN_LESS_BALANCESTARTPAYTIME = 2;
    public static final int YUSHOU_PAYBARGAIN_MORE_BALANCESTARTPAYTIME_LESS_BALANCEENDPAYTIME = 3;
    public static final int YUSHOU_PAYBARGAIN_MORE_BALANCESTARTPAYTIME_MORE_BALANCEENDPAYTIME = 4;
    public static final int YUSHOU_UNPAYBARGAIN_LESS30MINS = 0;
    public static final int YUSHOU_UNPAYBARGAIN_MORE30MINS = 1;
    private String yushouBalance;
    private String yushouBalanceShow;
    private String yushouBargin;
    private String yushouEndPayTime;
    private String yushouPayTime;
    private int yushouPayType;
    private int yushouState;
    private String sendPay = "";
    private int ladderType = 0;

    public static YushouOrder fromJson(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return null;
        }
        return fromJson(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    public boolean canPay() {
        int i2 = this.yushouState;
        return i2 == 0 || i2 == 3;
    }

    public int getLadderType() {
        return this.ladderType;
    }

    public String getYushouBalance() {
        return this.yushouBalance;
    }

    public String getYushouBalanceShow() {
        return this.yushouBalanceShow;
    }

    public String getYushouBargin() {
        return this.yushouBargin;
    }

    public String getYushouEndPayTime() {
        return this.yushouEndPayTime;
    }

    public String getYushouPayTime() {
        return this.yushouPayTime;
    }

    public int getYushouPayType() {
        return this.yushouPayType;
    }

    public int getYushouState() {
        return this.yushouState;
    }

    public boolean isBalacneTimoutCancel() {
        return this.yushouState == 4;
    }

    public boolean isBarginTimeoutCancel() {
        return this.yushouState == 1;
    }

    public boolean isCancel() {
        return isBarginTimeoutCancel() || isBalacneTimoutCancel();
    }

    public boolean isEntirePay() {
        return this.yushouPayType == 1;
    }

    public boolean isYushou() {
        try {
            return this.sendPay.substring(43, 44).equals("1");
        } catch (Exception unused) {
            return false;
        }
    }

    public void setLadderType(int i2) {
        this.ladderType = i2;
    }

    public void setYushouBalance(String str) {
        this.yushouBalance = str;
    }

    public void setYushouBalanceShow(String str) {
        this.yushouBalanceShow = str;
    }

    public void setYushouBargin(String str) {
        this.yushouBargin = str;
    }

    public void setYushouEndPayTime(String str) {
        this.yushouEndPayTime = str;
    }

    public void setYushouPayTime(String str) {
        this.yushouPayTime = str;
    }

    public void setYushouPayType(int i2) {
        this.yushouPayType = i2;
    }

    public void setYushouState(int i2) {
        this.yushouState = i2;
    }

    public static YushouOrder fromJson(JDJSONObject jDJSONObject) {
        YushouOrder yushouOrder = new YushouOrder();
        if (jDJSONObject == null) {
            return yushouOrder;
        }
        yushouOrder.sendPay = jDJSONObject.getString("sendPay");
        yushouOrder.setYushouState(JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "yushouState", -2));
        yushouOrder.setYushouBargin(jDJSONObject.getString("yushouBargin"));
        yushouOrder.setYushouBalance(jDJSONObject.getString("yushouBalance"));
        yushouOrder.setYushouPayTime(jDJSONObject.getString("yushouPayTime"));
        yushouOrder.setYushouEndPayTime(jDJSONObject.getString("yushouEndPayTime"));
        yushouOrder.setYushouPayType(JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "yushouPayType", 0));
        yushouOrder.setLadderType(JDJSONObjectWithDefaultUtils.getJSONIntWithDefault(jDJSONObject, "yushouLadderType", 0));
        yushouOrder.setYushouBalanceShow(JDJSONObjectWithDefaultUtils.getJSONStringWithDefault(jDJSONObject, "yushouBalanceShow", ""));
        return yushouOrder;
    }
}
