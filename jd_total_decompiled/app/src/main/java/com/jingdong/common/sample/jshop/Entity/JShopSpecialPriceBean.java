package com.jingdong.common.sample.jshop.Entity;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.JshopConst;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class JShopSpecialPriceBean implements Serializable {
    public String accessTime;
    public long batchId;
    public String beginTime;
    public int discount;
    public String endTime;
    public int expirationTime;
    public String imgUrl;
    public String prizeInfo;
    public String prizeName;
    public int prizeStatus;
    public int quota;
    public int type;
    public String wareId;
    public String zxPrice;

    public static ArrayList<JShopSpecialPriceBean> toList(JDJSONArray jDJSONArray, int i2) {
        ArrayList<JShopSpecialPriceBean> arrayList = new ArrayList<>();
        if (jDJSONArray != null && jDJSONArray.size() > 0) {
            for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
                try {
                    JDJSONObject jSONObject = jDJSONArray.getJSONObject(i3);
                    JShopSpecialPriceBean jShopSpecialPriceBean = new JShopSpecialPriceBean();
                    jShopSpecialPriceBean.imgUrl = jSONObject.optString("imgUrl");
                    jShopSpecialPriceBean.quota = jSONObject.optInt("quota");
                    jShopSpecialPriceBean.accessTime = jSONObject.optString("accessTime");
                    jShopSpecialPriceBean.expirationTime = jSONObject.optInt("expirationTime");
                    jShopSpecialPriceBean.prizeInfo = jSONObject.optString("prizeInfo");
                    jShopSpecialPriceBean.prizeStatus = jSONObject.optInt("prizeStatus");
                    jShopSpecialPriceBean.beginTime = jSONObject.optString(JshopConst.JSKEY_COUPON_BEGIN_TIME);
                    jShopSpecialPriceBean.endTime = jSONObject.optString("endTime");
                    jShopSpecialPriceBean.type = jSONObject.optInt("type");
                    jShopSpecialPriceBean.prizeName = jSONObject.optString("prizeName");
                    jShopSpecialPriceBean.discount = jSONObject.optInt("discount");
                    jShopSpecialPriceBean.wareId = jSONObject.optString("wareId");
                    jShopSpecialPriceBean.zxPrice = jSONObject.optString("zxPrice");
                    jShopSpecialPriceBean.batchId = jSONObject.optLong(JshopConst.JSKEY_BATCH_ID, 0L);
                    if (i2 == 1) {
                        if (jShopSpecialPriceBean.prizeStatus != 1) {
                            arrayList.add(jShopSpecialPriceBean);
                        }
                    } else if (i2 == 2) {
                        if (jShopSpecialPriceBean.prizeStatus == 1) {
                            arrayList.add(jShopSpecialPriceBean);
                        }
                    } else if (i2 == 3) {
                        if (jShopSpecialPriceBean.prizeStatus == 0) {
                            arrayList.add(jShopSpecialPriceBean);
                        }
                    } else if (i2 == 4 && jShopSpecialPriceBean.prizeStatus != 0) {
                        arrayList.add(jShopSpecialPriceBean);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return arrayList;
    }

    public String generateCouponTip() {
        StringBuilder sb = new StringBuilder();
        if (this.type == 0 || this.quota == 0) {
            sb.append(String.format("%s\u5143\u4eac\u52b5", this.discount + ""));
        } else {
            sb.append(String.format("\u6ee1%s\u51cf%s", this.quota + "", this.discount + ""));
        }
        return String.format("\u9650\u65f6\u4fc3\u9500\uff1a\u4ee5\u4e0b\u5546\u54c1\u53ef\u4f7f\u7528%s\u4f18\u60e0\u5238", sb.toString());
    }
}
