package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.lib.puppetlayout.ylayout.model.Action;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/* loaded from: classes5.dex */
public class Pack implements Serializable {
    public static final int CART = 1;
    private static final String CONST_NO_PRICE = "\u6682\u65e0\u62a5\u4ef7";
    public static final int PACKS = 0;
    private static final String TAG = "Pack";
    private static final long serialVersionUID = 1;
    private String discount;
    private String finalPrice;
    private Long id;
    private Long mainId;
    private Product mainProduct;
    private String mainSkuName;
    private String mainSkuPicUrl;
    private String name;
    private Integer num;
    private String originalPrice;

    /* renamed from: point  reason: collision with root package name */
    private Long f12330point;
    private Integer productCount;
    private String rePrice;
    private SourceEntity sourceEntity;
    private Integer suitType;
    private List<Product> productList = new ArrayList();
    private List<Product> giftList = new ArrayList();

    public Pack() {
    }

    public static ArrayList<Pack> toList(JSONArrayPoxy jSONArrayPoxy, int i2, Object[] objArr) {
        ArrayList<Pack> arrayList = null;
        if (jSONArrayPoxy == null) {
            return null;
        }
        try {
            ArrayList<Pack> arrayList2 = new ArrayList<>();
            for (int i3 = 0; i3 < jSONArrayPoxy.length(); i3++) {
                try {
                    arrayList2.add(new Pack(jSONArrayPoxy.getJSONObject(i3), i2, objArr));
                } catch (JSONException e2) {
                    e = e2;
                    arrayList = arrayList2;
                    OKLog.e(TAG, e);
                    return arrayList;
                }
            }
            return arrayList2;
        } catch (JSONException e3) {
            e = e3;
        }
    }

    public void addNum(int i2) {
        this.num = Integer.valueOf(getNum().intValue() + i2);
    }

    public void addProduct(Product product, boolean z) {
        if (z) {
            setMainProduct(product);
        }
        this.productList.add(product);
        if (OKLog.D) {
            OKLog.d("lwh_test", " -->> getProductCount():" + getProductCount());
            OKLog.d("lwh_test", " -->> product.getNum():" + product.getNum());
        }
        setProductCount(Integer.valueOf(getProductCount().intValue() + product.getNum().intValue()));
    }

    public String getDiscount() {
        try {
            String str = this.discount;
            return str != null ? str : "\u6682\u65e0\u62a5\u4ef7";
        } catch (Exception unused) {
            return "\u6682\u65e0\u62a5\u4ef7";
        }
    }

    public String getFinalPrice() {
        return this.finalPrice;
    }

    public List<Product> getGiftList() {
        return this.giftList;
    }

    public Long getId() {
        return this.id;
    }

    public Long getMainId() {
        return this.mainId;
    }

    public Product getMainProduct() {
        return this.mainProduct;
    }

    public String getMainSkuName() {
        return this.mainSkuName;
    }

    public String getMainSkuPicUrl() {
        return this.mainSkuPicUrl;
    }

    public String getName() {
        return this.name;
    }

    public Integer getNum() {
        Integer num = this.num;
        if (num != null && num.intValue() != 0) {
            return this.num;
        }
        return 1;
    }

    public String getOriginalPrice() {
        return this.originalPrice;
    }

    public String getPackSkuIds() {
        String str;
        if (this.productList != null) {
            int i2 = 0;
            StringBuilder sb = new StringBuilder();
            for (Product product : this.productList) {
                if (product != null) {
                    if (i2 != 0) {
                        sb.append(CartConstant.KEY_YB_INFO_LINK);
                    }
                    sb.append(product.getId());
                }
                i2++;
            }
            str = sb.toString();
        } else {
            str = "";
        }
        if (OKLog.D) {
            OKLog.d("ProductDetailActivity", "packId = " + str);
            OKLog.d("ProductDetailActivity", "mainId = " + this.mainId);
        }
        return str;
    }

    public Long getPoint() {
        return this.f12330point;
    }

    public String getPriceForAfterDiscount() {
        try {
            if (!TextUtils.isEmpty(this.finalPrice)) {
                return this.finalPrice;
            }
            float floatValue = Float.valueOf(this.originalPrice).floatValue() - Float.valueOf(this.discount).floatValue();
            if (floatValue <= 0.0f) {
                return "\u6682\u65e0\u62a5\u4ef7";
            }
            return "\u00a5" + new DecimalFormat("0.00").format(floatValue);
        } catch (Exception unused) {
            return "\u6682\u65e0\u62a5\u4ef7";
        }
    }

    public Integer getProductCount() {
        List<Product> list = this.productList;
        if (list != null && !list.isEmpty()) {
            return Integer.valueOf(this.productList.size());
        }
        return this.productCount;
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public String getRePrice() {
        return this.rePrice;
    }

    public Pack getSimpleClone() {
        Pack pack = new Pack();
        pack.setId(getId());
        pack.setName(getName());
        pack.setNum(getNum());
        pack.setDiscount(getDiscount());
        pack.setRePrice(getRePrice());
        pack.setPoint(getPoint());
        pack.setProductCount(getProductCount());
        pack.setSourceEntity(getSourceEntity());
        return pack;
    }

    public SourceEntity getSourceEntity() {
        return this.sourceEntity;
    }

    public Integer getSuitType() {
        return this.suitType;
    }

    public void setDiscount(String str) {
        this.discount = str;
    }

    public void setFinalPrice(String str) {
        this.finalPrice = str;
    }

    public void setGiftList(List<Product> list) {
        this.giftList = list;
    }

    public void setId(Long l2) {
        this.id = l2;
    }

    public void setMainId(Long l2) {
        this.mainId = l2;
    }

    public void setMainProduct(Product product) {
        this.mainProduct = product;
    }

    public void setMainSkuName(String str) {
        this.mainSkuName = str;
    }

    public void setMainSkuPicUrl(String str) {
        this.mainSkuPicUrl = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setOriginalPrice(String str) {
        this.originalPrice = str;
    }

    public void setPoint(Long l2) {
        this.f12330point = l2;
    }

    public void setProductCount(Integer num) {
        this.productCount = num;
    }

    public void setProductList(List<Product> list) {
        this.productList = list;
    }

    public void setRePrice(String str) {
        this.rePrice = str;
    }

    public void setSourceEntity(SourceEntity sourceEntity) {
        this.sourceEntity = sourceEntity;
    }

    public void setSuitType(Integer num) {
        this.suitType = num;
    }

    public String toString() {
        return "Pack [discount=" + this.discount + ", finalPrice=" + this.finalPrice + ", giftList=" + this.giftList + ", id=" + this.id + ", mainProduct=" + this.mainProduct + ", name=" + this.name + ", num=" + this.num + ", originalPrice=" + this.originalPrice + ", point=" + this.f12330point + ", productCount=" + this.productCount + ", productList=" + this.productList + ", rePrice=" + this.rePrice + ", suitType=" + this.suitType + "]";
    }

    public Pack(JSONObjectProxy jSONObjectProxy, int i2, Object[] objArr) {
        if (i2 != 0) {
            if (i2 != 1) {
                return;
            }
            setId(jSONObjectProxy.getLongOrNull("Id"));
            setName(jSONObjectProxy.getStringOrNull("Name"));
            setNum(jSONObjectProxy.getIntOrNull(CartConstant.KEY_NUM_BIG));
            setOriginalPrice(jSONObjectProxy.getStringOrNull("Price"));
            setFinalPrice(jSONObjectProxy.getStringOrNull("PriceShow"));
            setPoint(jSONObjectProxy.getLongOrNull(Action.ActionType_Point));
            setSuitType(jSONObjectProxy.getIntOrNull("SuitType"));
            setDiscount(jSONObjectProxy.getStringOrNull(CartConstant.KEY_DISCOUNT));
            setProductList(Product.toList(jSONObjectProxy.getJSONArrayOrNull("Skus"), 9, objArr));
            setGiftList(Product.toList(jSONObjectProxy.getJSONArrayOrNull(CartConstant.KEY_GIFTS), 9, objArr));
            return;
        }
        setId(jSONObjectProxy.getLongOrNull("PackId"));
        setMainId(jSONObjectProxy.getLongOrNull("MainSkuId"));
        setMainSkuName(jSONObjectProxy.getStringOrNull("MainSkuName"));
        setName(jSONObjectProxy.getStringOrNull("PackName"));
        setNum(jSONObjectProxy.getIntOrNull(CartConstant.KEY_NUM_BIG));
        setRePrice(jSONObjectProxy.getStringOrNull(CartConstant.KEY_REPRICE));
        setMainSkuPicUrl(jSONObjectProxy.getStringOrNull("MainSkuPicUrl"));
        setOriginalPrice(jSONObjectProxy.getStringOrNull("PackListPrice"));
        setFinalPrice(jSONObjectProxy.getStringOrNull("PackPrice"));
        setDiscount(jSONObjectProxy.getStringOrNull(CartConstant.KEY_DISCOUNT));
        setProductList(Product.toList(jSONObjectProxy.getJSONArrayOrNull("ProductList"), 24, objArr));
        addProduct(new Product(jSONObjectProxy, 25, objArr), true);
    }
}
