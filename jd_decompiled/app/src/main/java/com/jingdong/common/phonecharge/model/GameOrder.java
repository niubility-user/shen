package com.jingdong.common.phonecharge.model;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.phonecharge.game.DesUtil;
import com.jingdong.common.phonecharge.game.GameHttpAddr;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class GameOrder implements Serializable {
    public int buyNum;
    public String cardInfos;
    public ArrayList<CardInfo> cardInfoss;
    public int chargeType;
    public double couponPay;
    public String created;
    public String district;
    public String gameAccount;
    public String gameArea;
    public String gameServer;
    public long jBeanPay;
    public double jdPrice;
    public String logo;
    public double onlinePay;
    public long orderId;
    public int orderStatus;
    public String orderStatusStr;
    public int orgin;
    public String payBackUrl;
    public int payMode;
    public long skuId;
    public String title;
    public double totalPrice;

    public static GameOrder getGameOrder(JSONObjectProxy jSONObjectProxy) {
        GameOrder gameOrder = null;
        try {
            GameOrder gameOrder2 = (GameOrder) JDJSON.parseObject(jSONObjectProxy.toString(), GameOrder.class);
            try {
                gameOrder2.jBeanPay = jSONObjectProxy.optLong("jBeanPay");
                gameOrder2.cardInfoss = toListCard(jSONObjectProxy.optString("cardInfos"));
                return gameOrder2;
            } catch (Exception e2) {
                e = e2;
                gameOrder = gameOrder2;
                e.printStackTrace();
                return gameOrder;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static ArrayList<GameOrder> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<GameOrder> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<GameOrder> arrayList2 = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    if (jSONArrayPoxy.getJSONObject(i2) != null) {
                        arrayList2.add(getGameOrder(jSONArrayPoxy.getJSONObject(i2)));
                    }
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    e.printStackTrace();
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    public static ArrayList<CardInfo> toListCard(String str) {
        Exception e2;
        ArrayList<CardInfo> arrayList;
        if (str == null || "".equals(str)) {
            return null;
        }
        try {
            JSONArrayPoxy jSONArrayPoxy = new JSONArrayPoxy(new JSONArray(new DesUtil(GameHttpAddr.desKey).decryptUTF8(str)));
            arrayList = new ArrayList<>();
            for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
                try {
                    if (jSONArrayPoxy.getJSONObject(i2) != null) {
                        arrayList.add(new CardInfo(jSONArrayPoxy.getJSONObject(i2)));
                    }
                } catch (Exception e3) {
                    e2 = e3;
                    e2.printStackTrace();
                    return arrayList;
                }
            }
        } catch (Exception e4) {
            e2 = e4;
            arrayList = null;
        }
        return arrayList;
    }

    public static GameOrder getGameOrder(JDJSONObject jDJSONObject) {
        GameOrder gameOrder = null;
        try {
            GameOrder gameOrder2 = (GameOrder) JDJSON.parseObject(jDJSONObject.toString(), GameOrder.class);
            try {
                gameOrder2.jBeanPay = jDJSONObject.optLong("jBeanPay");
                gameOrder2.cardInfoss = toListCard(jDJSONObject.optString("cardInfos"));
                return gameOrder2;
            } catch (Exception e2) {
                e = e2;
                gameOrder = gameOrder2;
                e.printStackTrace();
                return gameOrder;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }
}
