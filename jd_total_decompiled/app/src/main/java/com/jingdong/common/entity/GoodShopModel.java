package com.jingdong.common.entity;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class GoodShopModel {
    public static final String TAG = "GoodShopModel";
    public int shopCategoriesCount;
    public int shopCategoriesId;
    public String shopCategoriesTitle;
    public int shopId;
    public String shopImage;
    public String shopName;
    public String sourceValue;
    private ArrayList<WareModel> wareList = new ArrayList<>();

    /* loaded from: classes5.dex */
    public class WareModel {
        public String imgPath;
        public long wareId;

        public WareModel(long j2, String str) {
            this.wareId = j2;
            this.imgPath = str;
        }
    }

    public GoodShopModel(JSONObjectProxy jSONObjectProxy) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()));
    }

    public static String getCategories(JSONArrayPoxy jSONArrayPoxy) {
        if (jSONArrayPoxy == null) {
            return null;
        }
        return getCategories(JDJSON.parseArray(jSONArrayPoxy.toString()));
    }

    public static ArrayList<GoodShopModel> toList(JSONArrayPoxy jSONArrayPoxy) {
        if (jSONArrayPoxy == null) {
            return null;
        }
        return toList(JDJSON.parseArray(jSONArrayPoxy.toString()));
    }

    private void update(JDJSONObject jDJSONObject) {
        int size;
        this.shopId = jDJSONObject.getIntValue("shopId");
        this.shopName = jDJSONObject.getString("shopName");
        this.sourceValue = jDJSONObject.getString("sourceValue");
        this.shopImage = jDJSONObject.getString("shopImage");
        JDJSONObject jSONObject = jDJSONObject.getJSONObject(JshopConst.JSKEY_SHOP_CATE);
        if (jSONObject != null) {
            this.shopCategoriesId = jSONObject.getIntValue("cid");
            this.shopCategoriesTitle = jSONObject.getString("name");
            this.shopCategoriesCount = jSONObject.getIntValue("shopCount");
        }
        JDJSONArray jSONArray = jDJSONObject.getJSONArray("wareList");
        if (jSONArray == null || (size = jSONArray.size()) <= 0) {
            return;
        }
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject jSONObject2 = jSONArray.getJSONObject(i2);
            if (jSONObject2 != null) {
                this.wareList.add(new WareModel(jSONObject2.getLongValue("wareId"), jSONObject2.getString(JshopConst.JSKEY_PRODUCT_IMGPATH)));
            }
        }
    }

    public ArrayList<WareModel> getWareList() {
        return this.wareList;
    }

    public void setWareList(ArrayList<WareModel> arrayList) {
        this.wareList = arrayList;
    }

    public static String getCategories(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.size() == 0) {
            return null;
        }
        JDJSONArray jDJSONArray2 = new JDJSONArray();
        int size = jDJSONArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
            if (jSONObject != null) {
                jDJSONArray2.add(jSONObject.getJSONObject(JshopConst.JSKEY_SHOP_CATE));
            }
        }
        return jDJSONArray2.toString();
    }

    public static ArrayList<GoodShopModel> toList(JDJSONArray jDJSONArray) {
        if (jDJSONArray == null || jDJSONArray.size() == 0) {
            return null;
        }
        ArrayList<GoodShopModel> arrayList = new ArrayList<>();
        int size = jDJSONArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
            if (jSONObject != null) {
                GoodShopModel goodShopModel = new GoodShopModel(jSONObject);
                if (i2 >= 11) {
                    break;
                }
                arrayList.add(goodShopModel);
            }
        }
        return arrayList;
    }

    public GoodShopModel(JDJSONObject jDJSONObject) {
        update(jDJSONObject);
    }
}
