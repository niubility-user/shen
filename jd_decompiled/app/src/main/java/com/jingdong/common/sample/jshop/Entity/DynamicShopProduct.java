package com.jingdong.common.sample.jshop.Entity;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.JshopConst;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class DynamicShopProduct implements Serializable {
    private static final long serialVersionUID = -7151819381391492734L;
    public String imgPath;
    public String jdPrice;
    public String mPrice;
    private JDJSONObject promotionFlag;
    public int status;
    public String wareId;
    public String wareName;

    public DynamicShopProduct() {
    }

    public static ArrayList<DynamicShopProduct> toProductList(JDJSONArray jDJSONArray) {
        ArrayList<DynamicShopProduct> arrayList = new ArrayList<>();
        if (jDJSONArray != null && jDJSONArray.size() > 0) {
            for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                try {
                    JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
                    if (jSONObject != null) {
                        arrayList.add(new DynamicShopProduct(jSONObject));
                    }
                } catch (Exception e2) {
                    if (Log.D) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        return arrayList;
    }

    public JDJSONObject getPromotionFlag() {
        return this.promotionFlag;
    }

    public void setPromotionFlag(JDJSONObject jDJSONObject) {
        this.promotionFlag = jDJSONObject;
    }

    public DynamicShopProduct(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            return;
        }
        this.mPrice = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_MPRICE);
        this.jdPrice = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_JDPRICE);
        this.wareName = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_WARENAME);
        this.imgPath = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_IMGPATH);
        this.wareId = jDJSONObject.optString("wareId");
        this.status = jDJSONObject.optInt("status", 1);
        setPromotionFlag(jDJSONObject.optJSONObject(JshopConst.JSKEY_PRODUCT_PROMOFLAG));
    }
}
