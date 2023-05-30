package com.jingdong.common.sample.jshop;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.JshopConst;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class JShopDynamicMoreProductEntity implements Serializable {
    private static final long serialVersionUID = 3773658089727840873L;
    public long activityId;
    public int activityType;
    public boolean plugin;
    private JDJSONArray products;
    public int totalRecord;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public class Product {
        public String imgPath;
        public String jdPrice;
        JDJSONObject promotionJo;
        public int status;
        public String wareId;
        public String wareName;

        public Product(JDJSONObject jDJSONObject) {
            this.wareId = jDJSONObject.optString("wareId");
            this.jdPrice = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_JDPRICE);
            this.imgPath = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_IMGPATH);
            this.wareName = jDJSONObject.optString(JshopConst.JSKEY_PRODUCT_WARENAME);
            setPromotionJo(jDJSONObject.optJSONObject(JshopConst.JSKEY_PRODUCT_PROMOFLAG));
            this.status = jDJSONObject.optInt("status", 1);
        }

        public JDJSONObject getPromotionJo() {
            return this.promotionJo;
        }

        public void setPromotionJo(JDJSONObject jDJSONObject) {
            this.promotionJo = jDJSONObject;
        }
    }

    public JShopDynamicMoreProductEntity(JDJSONObject jDJSONObject) {
        this.activityId = jDJSONObject.optLong("activityId");
        this.totalRecord = jDJSONObject.optInt("totalRecord");
        this.activityType = jDJSONObject.optInt(JshopConst.JSHOP_ACTIVITY_TYPE);
        this.plugin = jDJSONObject.optBoolean("plugin");
        setProducts(jDJSONObject.optJSONArray("products"));
    }

    public JDJSONArray getProducts() {
        return this.products;
    }

    public void setProducts(JDJSONArray jDJSONArray) {
        this.products = jDJSONArray;
    }

    public ArrayList<Product> toProductList(JDJSONArray jDJSONArray) {
        ArrayList<Product> arrayList = new ArrayList<>();
        if (jDJSONArray != null) {
            try {
                if (jDJSONArray.size() > 0) {
                    for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
                        if (jDJSONArray.getJSONObject(i2) != null) {
                            arrayList.add(new Product(jDJSONArray.optJSONObject(i2)));
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }
}
