package com.jingdong.common.entity.cart;

import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.XView2.common.XView2Constants;

/* loaded from: classes5.dex */
public class TimeOrderInfo {
    private String buttonText;
    private String contentText;
    private String frequencyCount;
    private String isTimeOrder;
    private String perTimeNum;
    private String planLink;
    private String price;
    private int state;
    private String timeOrderId;
    private String timeOrderNum;
    private String toActivityId;
    private String to_frequency;
    private String totalTimeOrderNum;

    public TimeOrderInfo(JDJSONObject jDJSONObject) {
        if (jDJSONObject != null) {
            this.timeOrderId = jDJSONObject.optString("timeOrderId");
            this.toActivityId = jDJSONObject.optString("toActivityId");
            this.timeOrderNum = jDJSONObject.optString("timeOrderNum");
            this.perTimeNum = jDJSONObject.optString("perTimeNum");
            this.to_frequency = jDJSONObject.optString("to_frequency");
            this.isTimeOrder = jDJSONObject.optString("isTimeOrder");
            this.planLink = jDJSONObject.optString("planLink");
            this.price = jDJSONObject.optString("price");
            this.contentText = jDJSONObject.optString("contentText");
            this.buttonText = jDJSONObject.optString("buttonText");
            this.state = jDJSONObject.optInt(XView2Constants.STATE);
            this.frequencyCount = jDJSONObject.optString("frequencyCount");
            this.totalTimeOrderNum = jDJSONObject.optString("totalTimeOrderNum");
        }
    }

    public String getButtonText() {
        return this.buttonText;
    }

    public String getContentText() {
        return this.contentText;
    }

    public String getFrequencyCount() {
        return this.frequencyCount;
    }

    public String getIsTimeOrder() {
        return this.isTimeOrder;
    }

    public String getPerTimeNum() {
        return this.perTimeNum;
    }

    public String getPlanLink() {
        return this.planLink;
    }

    public String getPrice() {
        return this.price;
    }

    public int getState() {
        return this.state;
    }

    public String getTimeOrderId() {
        return this.timeOrderId;
    }

    public String getTimeOrderNum() {
        return this.timeOrderNum;
    }

    public String getToActivityId() {
        return this.toActivityId;
    }

    public String getTo_frequency() {
        return this.to_frequency;
    }

    public String getTotalTimeOrderNum() {
        return this.totalTimeOrderNum;
    }

    public void setButtonText(String str) {
        this.buttonText = str;
    }

    public void setContentText(String str) {
        this.contentText = str;
    }

    public void setIsTimeOrder(String str) {
        this.isTimeOrder = str;
    }

    public void setPerTimeNum(String str) {
        this.perTimeNum = str;
    }

    public void setPlanLink(String str) {
        this.planLink = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setState(int i2) {
        this.state = i2;
    }

    public void setTimeOrderId(String str) {
        this.timeOrderId = str;
    }

    public void setTimeOrderNum(String str) {
        this.timeOrderNum = str;
    }

    public void setToActivityId(String str) {
        this.toActivityId = str;
    }

    public void setTo_frequency(String str) {
        this.to_frequency = str;
    }

    public void setTotalTimeOrderNum(String str) {
        this.totalTimeOrderNum = str;
    }
}
