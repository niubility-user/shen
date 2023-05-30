package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class ProductDetailBasicInfo implements Serializable {
    private static final long serialVersionUID = 1596522925443511843L;
    private boolean cartFlag;
    private String cartImag;
    private String cartTip;
    private String chatUrl;
    private boolean easyBuy;
    private String ebookId;
    private String ebookLink;
    private String ebookPrice;
    private String ebookType;
    private String fare;
    private String infoPageImag;
    private boolean isPop;
    private String mLink;
    private boolean miaosha;
    private long miaoshaRemainTime;
    private String name;
    private String stock;
    private String type;
    private String venderId;
    private String wareId;
    private boolean isOneHour = false;
    private boolean is7ToReturn = true;
    private String reasonTips = null;

    public ProductDetailBasicInfo() {
    }

    private void update(JDJSONObject jDJSONObject, int i2) {
        if (i2 != 3) {
            return;
        }
        setName(jDJSONObject.getString("name"));
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "stock")) {
            setStock(jDJSONObject.getString("stock"));
        }
        setCartTip(jDJSONObject.getString("cartTip"));
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "easyBuy")) {
            setEasyBuy(jDJSONObject.getBooleanValue("easyBuy"));
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "miaosha")) {
            setMiaosha(jDJSONObject.getBooleanValue("miaosha"));
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "cartFlag")) {
            setCartFlag(jDJSONObject.getBooleanValue("cartFlag"));
        }
        setWareId(jDJSONObject.getString("wareId"));
        setCartImag(jDJSONObject.getString("cartImag"));
        setInfoPageImag(jDJSONObject.getString("infoPageImag"));
        setChatUrl(jDJSONObject.getString("chatUrl"));
        setmLink(jDJSONObject.getString("mLink"));
        setVenderId(jDJSONObject.getString("venderId"));
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "fare")) {
            setFare(jDJSONObject.getString("fare"));
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "type")) {
            setType(jDJSONObject.getString("type"));
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "isOneHour")) {
            setOneHour(jDJSONObject.getBooleanValue("isOneHour"));
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "is7ToReturn")) {
            setIs7ToReturn(jDJSONObject.getBooleanValue("is7ToReturn"));
        } else {
            setIs7ToReturn(true);
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "miaoshaRemainTime")) {
            setMiaoshaRemainTime(jDJSONObject.getLongValue("miaoshaRemainTime"));
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "ebookType")) {
            setEbookType(jDJSONObject.getString("ebookType"));
            if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "ebookLink")) {
                setEbookLink(jDJSONObject.getString("ebookLink"));
            }
            if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "ebookPrice")) {
                setEbookPrice(jDJSONObject.getString("ebookPrice"));
            }
            if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "ebookId")) {
                setEbookId(jDJSONObject.getString("ebookId"));
            }
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "reasonTips")) {
            setReasonTips(jDJSONObject.getString("reasonTips"));
        }
        if (JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "isPop")) {
            return;
        }
        setPop(jDJSONObject.getBooleanValue("isPop"));
    }

    public String getCartImag() {
        return this.cartImag;
    }

    public String getCartTip() {
        return TextUtils.isEmpty(this.cartTip) ? "" : this.cartTip;
    }

    public String getChatUrl() {
        return TextUtils.isEmpty(this.chatUrl) ? "" : this.chatUrl;
    }

    public String getEbookId() {
        return this.ebookId;
    }

    public String getEbookLink() {
        return this.ebookLink;
    }

    public String getEbookPrice() {
        return this.ebookPrice;
    }

    public String getEbookType() {
        return this.ebookType;
    }

    public String getFare() {
        return this.fare;
    }

    public String getInfoPageImag() {
        return this.infoPageImag;
    }

    public long getMiaoshaRemainTime() {
        return this.miaoshaRemainTime;
    }

    public String getName() {
        return TextUtils.isEmpty(this.name) ? "" : this.name;
    }

    public String getReasonTips() {
        return this.reasonTips;
    }

    public String getStock() {
        return TextUtils.isEmpty(this.stock) ? "" : this.stock;
    }

    public String getType() {
        return this.type;
    }

    public String getVenderId() {
        return TextUtils.isEmpty(this.venderId) ? "" : this.venderId;
    }

    public String getWareId() {
        return TextUtils.isEmpty(this.wareId) ? "" : this.wareId;
    }

    public String getmLink() {
        return TextUtils.isEmpty(this.mLink) ? "" : this.mLink;
    }

    public boolean is7ToReturn() {
        return this.is7ToReturn;
    }

    public boolean isCartFlag() {
        return this.cartFlag;
    }

    public boolean isEasyBuy() {
        return this.easyBuy;
    }

    public boolean isMiaosha() {
        return this.miaosha;
    }

    public boolean isOneHour() {
        return this.isOneHour;
    }

    public boolean isPop() {
        return this.isPop;
    }

    public void setCartFlag(boolean z) {
        this.cartFlag = z;
    }

    public void setCartImag(String str) {
        this.cartImag = str;
    }

    public void setCartTip(String str) {
        this.cartTip = str;
    }

    public void setChatUrl(String str) {
        this.chatUrl = str;
    }

    public void setEasyBuy(boolean z) {
        this.easyBuy = z;
    }

    public void setEbookId(String str) {
        this.ebookId = str;
    }

    public void setEbookLink(String str) {
        this.ebookLink = str;
    }

    public void setEbookPrice(String str) {
        this.ebookPrice = str;
    }

    public void setEbookType(String str) {
        this.ebookType = str;
    }

    public void setFare(String str) {
        this.fare = str;
    }

    public void setInfoPageImag(String str) {
        this.infoPageImag = str;
    }

    public void setIs7ToReturn(boolean z) {
        this.is7ToReturn = z;
    }

    public void setMiaosha(boolean z) {
        this.miaosha = z;
    }

    public void setMiaoshaRemainTime(long j2) {
        this.miaoshaRemainTime = j2;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setOneHour(boolean z) {
        this.isOneHour = z;
    }

    public void setPop(boolean z) {
        this.isPop = z;
    }

    public void setReasonTips(String str) {
        this.reasonTips = str;
    }

    public void setStock(String str) {
        this.stock = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setVenderId(String str) {
        this.venderId = str;
    }

    public void setWareId(String str) {
        this.wareId = str;
    }

    public void setmLink(String str) {
        this.mLink = str;
    }

    public ProductDetailBasicInfo(JSONObjectProxy jSONObjectProxy, int i2) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()), i2);
    }

    public ProductDetailBasicInfo(JDJSONObject jDJSONObject, int i2) {
        update(jDJSONObject, i2);
    }
}
