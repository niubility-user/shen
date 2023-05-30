package com.jingdong.common.sample.json;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.database.table.CommentEditTable;
import com.jingdong.common.entity.Product;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class JshopSearchItem {
    public boolean adShop;
    public String clickUrl;
    public String exposalUrl;
    public String extensionId;
    public Long followCount;
    public boolean hasCoupon;
    public boolean hasNewWare;
    public boolean isDiamond;
    public boolean isNeedShowPDetail;
    public boolean isPop;
    public String logid;
    public String logo;
    public String mainRange;
    public String mtest;
    public String p13nFeature1;
    private ArrayList<Product> products = new ArrayList<>();
    public Double score;
    public String scoreRankGrade;
    public Long shopId;
    public String shopName;
    public String shopTagIconId;
    public String signboardUrl;
    public Long venderId;

    public JshopSearchItem() {
    }

    public ArrayList<Product> getProducts() {
        return this.products;
    }

    public boolean hasMiaosha() {
        ArrayList<Product> arrayList = this.products;
        if (arrayList != null && !arrayList.isEmpty()) {
            for (int i2 = 0; i2 < this.products.size(); i2++) {
                Product product = this.products.get(i2);
                if (product != null && product.isHot()) {
                    return true;
                }
            }
        }
        return false;
    }

    public JshopSearchItem(JDJSONObject jDJSONObject) {
        if (jDJSONObject == null) {
            Log.d("JshopSearchItem", "jsonObject is null");
            return;
        }
        this.venderId = Long.valueOf(jDJSONObject.optLong("venderId"));
        this.shopId = Long.valueOf(jDJSONObject.optLong("shopId"));
        this.shopName = jDJSONObject.optString("shopName");
        this.score = Double.valueOf(jDJSONObject.optDouble(CommentEditTable.TB_COLUMN_SCORE));
        this.followCount = Long.valueOf(jDJSONObject.optLong(JshopConst.JSKEY_FLW_COUNT));
        this.hasNewWare = jDJSONObject.optBoolean("hasNewWare");
        this.hasCoupon = jDJSONObject.optBoolean(CartConstant.KEY_HAS_COUPON);
        this.logo = jDJSONObject.optString("logoUrl");
        this.signboardUrl = jDJSONObject.optString(JshopConst.JSHOP_YU_SIGN_BOARD_URL);
        this.isDiamond = jDJSONObject.optBoolean("isDiamond");
        this.shopTagIconId = jDJSONObject.optString("shopTagIconId");
        this.mainRange = jDJSONObject.optString(JshopConst.JSKEY_MAIN_RANGE);
        this.isPop = 1 != jDJSONObject.optInt(JshopConst.JSKEY_JDSHOP);
        this.scoreRankGrade = jDJSONObject.optString("scoreRankGrade");
        this.p13nFeature1 = jDJSONObject.optString("p13nFeature1");
        this.adShop = jDJSONObject.optBoolean("adShop");
        this.clickUrl = jDJSONObject.optString("clickUrl");
        this.exposalUrl = jDJSONObject.optString("exposalUrl");
        this.extensionId = jDJSONObject.optString("extensionId");
        JDJSONArray optJSONArray = jDJSONObject.optJSONArray("wareList");
        if (optJSONArray == null || optJSONArray.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
            JDJSONObject optJSONObject = optJSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                Product product = new Product();
                product.setId(Long.valueOf(optJSONObject.optLong("wareId")));
                product.setJdPrice(optJSONObject.optString(JshopConst.JSKEY_PRODUCT_JDPRICE));
                product.setImage(optJSONObject.optString(JshopConst.JSKEY_PRODUCT_IMGPATH), "");
                product.stock = optJSONObject.optInt("stock", -1);
                product.isUnderCarriage = optJSONObject.optInt("isUnderCarriage", 0);
                product.setName(optJSONObject.optString(JshopConst.JSKEY_PRODUCT_WARENAME));
                product.setShopId(this.shopId.longValue());
                product.setIsHot(optJSONObject.optBoolean("isSeckillWare"));
                product.setOpenAppUrl(optJSONObject.optString("openAppUrl"));
                this.products.add(product);
            }
        }
    }
}
