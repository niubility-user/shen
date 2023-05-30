package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jingdong.common.database.table.CommentEditTable;
import com.jingdong.common.entity.cart.yanbao.CartResponseNewYBCategory;
import com.jingdong.common.utils.JDJSONObjectWithDefaultUtils;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.utils.JSONArrayPoxy;
import com.jingdong.jdsdk.utils.JSONObjectProxy;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ProductDetailCruxBasicInfo implements Serializable {
    private static final String TAG = "ProductDetailCruxBasicI";
    private static final long serialVersionUID = 7262879100961590686L;
    public String adLink;
    public String adLinkContent;
    public String adword;
    public String badCommentNum;
    public String comNum;
    public Boolean consult;
    public String cousultNum;
    public Boolean downPrice;
    public Boolean gift;
    public String good;
    public Boolean hasChat;
    public Boolean hasShop;
    public Boolean isPostByJd;
    public boolean isShowBadComments;
    public Boolean isStore;
    public Boolean online;
    public String orderNum;
    public String proInfo;
    public List<ProTextInfo> proTextList;
    public String proTitle;
    public Boolean read;
    public String readUrl;
    private List<Recommend> recommendList;
    public String score;
    public String service;
    private ShopInfo shopInfo;
    public String showClick;
    public Boolean suit;
    private ArrayList<CartResponseNewYBCategory> ybCategoryList;
    public Integer yuyueNumber;

    /* loaded from: classes5.dex */
    public static class ProTextInfo implements Serializable {
        private static final long serialVersionUID = -6500464256919570427L;
        private String proText;
        public String type;
    }

    /* loaded from: classes5.dex */
    public class Recommend implements Serializable {
        private static final long serialVersionUID = -48723030435078372L;
        public String expid;
        public String image;
        public String index;
        public String jdPrice;
        public String name;
        public String rid;
        public String skuId;

        public Recommend() {
        }

        public String getExpid() {
            return TextUtils.isEmpty(this.expid) ? "" : this.expid;
        }

        public String getImage() {
            return TextUtils.isEmpty(this.image) ? "" : this.image;
        }

        public String getIndex() {
            return TextUtils.isEmpty(this.index) ? "" : this.index;
        }

        public String getJdPrice() {
            return TextUtils.isEmpty(this.jdPrice) ? "" : this.jdPrice;
        }

        public String getName() {
            return TextUtils.isEmpty(this.name) ? "" : this.name;
        }

        public String getRid() {
            return TextUtils.isEmpty(this.rid) ? "" : this.rid;
        }

        public String getSkuId() {
            return TextUtils.isEmpty(this.skuId) ? "" : this.skuId;
        }

        public Recommend(JSONObjectProxy jSONObjectProxy) {
            if (OKLog.D && jSONObjectProxy != null) {
                OKLog.d("Recommend", jSONObjectProxy.toString());
            }
            if (jSONObjectProxy != null) {
                this.skuId = jSONObjectProxy.getStringOrNull("skuId");
                this.jdPrice = jSONObjectProxy.getStringOrNull("jprice");
                this.image = jSONObjectProxy.getStringOrNull("image");
                this.expid = jSONObjectProxy.getStringOrNull(PDConstant.EXTRA_EXPID);
                this.rid = jSONObjectProxy.getStringOrNull(PDConstant.EXTRA_RID);
                this.name = jSONObjectProxy.getStringOrNull("name");
                this.index = jSONObjectProxy.getStringOrNull("index");
            }
        }
    }

    /* loaded from: classes5.dex */
    public class ShopInfo implements Serializable {
        private static final long serialVersionUID = -48723030435078374L;
        public String logo;
        public String name;
        public String score;
        public String shopId;
        public String url;

        public ShopInfo() {
        }

        private void update(JDJSONObject jDJSONObject) {
            if (jDJSONObject != null) {
                this.score = jDJSONObject.getString(CommentEditTable.TB_COLUMN_SCORE);
                this.logo = jDJSONObject.getString("logo");
                this.url = jDJSONObject.getString("url");
                this.shopId = jDJSONObject.getString("shopId");
                this.name = jDJSONObject.getString("name");
            }
        }

        public String getLogo() {
            return TextUtils.isEmpty(this.logo) ? "" : this.logo;
        }

        public String getName() {
            return TextUtils.isEmpty(this.name) ? "" : this.name;
        }

        public String getScore() {
            return TextUtils.isEmpty(this.score) ? "" : this.score;
        }

        public String getShopId() {
            return TextUtils.isEmpty(this.shopId) ? "" : this.shopId;
        }

        public String getUrl() {
            return TextUtils.isEmpty(this.url) ? "" : this.url;
        }

        public ShopInfo(JSONObjectProxy jSONObjectProxy) {
            if (jSONObjectProxy == null) {
                return;
            }
            update(JDJSON.parseObject(jSONObjectProxy.toString()));
        }

        public ShopInfo(JDJSONObject jDJSONObject) {
            update(jDJSONObject);
        }
    }

    public ProductDetailCruxBasicInfo() {
    }

    public static List<ProTextInfo> toProList(JSONArrayPoxy jSONArrayPoxy) {
        if (jSONArrayPoxy == null) {
            return null;
        }
        return toProList(JDJSON.parseArray(jSONArrayPoxy.toString()));
    }

    private void update(JDJSONObject jDJSONObject, int i2) {
        if (i2 != 28) {
            return;
        }
        this.consult = jDJSONObject.getBoolean("consult");
        this.read = jDJSONObject.getBoolean("read");
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "adword")) {
            this.adword = jDJSONObject.getString("adword");
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "adLink")) {
            this.adLink = jDJSONObject.getString("adLink");
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "adLinkContent")) {
            this.adLinkContent = jDJSONObject.getString("adLinkContent");
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "proInfo")) {
            this.proInfo = jDJSONObject.getString("proInfo");
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "yuyueNum")) {
            this.yuyueNumber = jDJSONObject.getInteger("yuyueNum");
        }
        this.service = jDJSONObject.getString("service");
        this.comNum = jDJSONObject.getString("comNum");
        this.orderNum = jDJSONObject.getString("orderNum");
        this.cousultNum = jDJSONObject.getString("cousultNum");
        this.badCommentNum = jDJSONObject.getString("badCommentNum");
        this.score = jDJSONObject.getString(CommentEditTable.TB_COLUMN_SCORE);
        this.good = jDJSONObject.getString("good");
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "proTitle")) {
            this.proTitle = jDJSONObject.getString("proTitle");
        }
        this.readUrl = jDJSONObject.getString("readUrl");
        this.suit = jDJSONObject.getBoolean(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_SUIT);
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "isStore")) {
            this.isStore = jDJSONObject.getBoolean("isStore");
        }
        if (!JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "gift")) {
            this.gift = jDJSONObject.getBoolean("gift");
        }
        this.isPostByJd = jDJSONObject.getBoolean("isPostByJd");
        this.downPrice = jDJSONObject.getBoolean("downPrice");
        this.online = jDJSONObject.getBoolean("online");
        this.hasChat = jDJSONObject.getBoolean("hasChat");
        this.hasShop = jDJSONObject.getBoolean("hasShop");
        JDJSONObject jSONObject = jDJSONObject.getJSONObject(JshopConst.JSKEY_SHOP_INFO);
        if (jSONObject != null) {
            setShopInfo(new ShopInfo(jSONObject));
        }
        if (JDJSONObjectWithDefaultUtils.isNull(jDJSONObject, "proFlagList")) {
            return;
        }
        setProTextList(toProList(jDJSONObject.getJSONArray("proFlagList")));
    }

    public Boolean getDownPrice() {
        Boolean bool = this.downPrice;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getHasChat() {
        Boolean bool = this.hasChat;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getHasShop() {
        Boolean bool = this.hasShop;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public Boolean getIsPostByJd() {
        Boolean bool = this.isPostByJd;
        return bool == null ? Boolean.FALSE : bool;
    }

    public Boolean getOnline() {
        Boolean bool = this.online;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public List<ProTextInfo> getProTextList() {
        return this.proTextList;
    }

    public List<Recommend> getRecommendList() {
        return this.recommendList;
    }

    public ShopInfo getShopInfo() {
        ShopInfo shopInfo = this.shopInfo;
        return shopInfo == null ? new ShopInfo() : shopInfo;
    }

    public ArrayList<CartResponseNewYBCategory> getYbCategoryList() {
        return this.ybCategoryList;
    }

    public Boolean isConsult() {
        Boolean bool = this.consult;
        return bool == null ? Boolean.FALSE : bool;
    }

    public Boolean isGift() {
        Boolean bool = this.gift;
        return bool == null ? Boolean.FALSE : bool;
    }

    public Boolean isRead() {
        Boolean bool = this.read;
        return bool == null ? Boolean.FALSE : bool;
    }

    public Boolean isStore() {
        Boolean bool = this.isStore;
        return bool == null ? Boolean.FALSE : bool;
    }

    public Boolean isSuit() {
        Boolean bool = this.suit;
        return bool == null ? Boolean.FALSE : bool;
    }

    public void setProTextList(List<ProTextInfo> list) {
        this.proTextList = list;
    }

    public void setRecommendList(List<Recommend> list) {
        this.recommendList = list;
    }

    public void setShopInfo(ShopInfo shopInfo) {
        this.shopInfo = shopInfo;
    }

    public void setYbCategoryList(ArrayList<CartResponseNewYBCategory> arrayList) {
        this.ybCategoryList = arrayList;
    }

    public ArrayList<Recommend> toList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<Recommend> arrayList = new ArrayList<>();
        if (jSONArrayPoxy == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
            try {
                Recommend recommend = new Recommend(jSONArrayPoxy.getJSONObject(i2));
                if (!TextUtils.isEmpty(recommend.getName())) {
                    arrayList.add(recommend);
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    OKLog.e("ServerIcon", e2.getMessage());
                }
            }
        }
        return arrayList;
    }

    public ArrayList<CartResponseNewYBCategory> toYBDetailList(JSONArrayPoxy jSONArrayPoxy) {
        ArrayList<CartResponseNewYBCategory> arrayList = new ArrayList<>();
        if (jSONArrayPoxy == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jSONArrayPoxy.length(); i2++) {
            try {
                CartResponseNewYBCategory cartResponseNewYBCategory = new CartResponseNewYBCategory(jSONArrayPoxy.getJSONObject(i2), 1);
                if (!TextUtils.isEmpty(cartResponseNewYBCategory.getBrandName())) {
                    arrayList.add(cartResponseNewYBCategory);
                }
            } catch (Exception e2) {
                OKLog.e(TAG, e2);
            }
        }
        return arrayList;
    }

    public ProductDetailCruxBasicInfo(JSONObjectProxy jSONObjectProxy, int i2) {
        if (jSONObjectProxy == null) {
            return;
        }
        update(JDJSON.parseObject(jSONObjectProxy.toString()), i2);
    }

    public static List<ProTextInfo> toProList(JDJSONArray jDJSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jDJSONArray == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            try {
                ProTextInfo proTextInfo = new ProTextInfo();
                JDJSONObject jSONObject = jDJSONArray.getJSONObject(i2);
                if (jSONObject != null) {
                    proTextInfo.proText = jSONObject.getString("text");
                    proTextInfo.type = jSONObject.getString("type");
                }
                if (!TextUtils.isEmpty(proTextInfo.proText)) {
                    arrayList.add(proTextInfo);
                }
            } catch (Exception unused) {
            }
        }
        return arrayList;
    }

    public ProductDetailCruxBasicInfo(JDJSONObject jDJSONObject, int i2) {
        update(jDJSONObject, i2);
    }
}
