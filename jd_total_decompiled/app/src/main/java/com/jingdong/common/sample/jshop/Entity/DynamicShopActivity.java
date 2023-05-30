package com.jingdong.common.sample.jshop.Entity;

import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class DynamicShopActivity implements Serializable {
    private static final long serialVersionUID = 6904753821546688668L;
    public JDJSONObject activity;
    public String activityDesc;
    public long activityId;
    public String activityIds;
    public int activitySubType;
    public long activityType;
    public String activityTypeDes;
    public long bizId;
    public long brandId;
    public BuyerShowComment comment;
    public long commentCount;
    public String commentCounts;
    public String commentSwitch;
    public JDJSONObject couponJson;
    public int coverType;
    public String dynamicSetCover;
    public int edited;
    public int followAward;
    public boolean followed;
    public boolean hadPraised;
    public boolean isDynamicSet;
    public boolean isHeadLine;
    public boolean isLargePic;
    public boolean isSeckillOver;
    public boolean linkActivity;
    public long liveShowPublishTime;
    public int liveShowStatus;
    public String mUrl;
    public String modified;
    public long passTime;
    public boolean plugin;
    public long praiseCount;
    public String praiseCounts;
    private JDJSONArray products;
    public int promotionType;
    public long seckillTime;
    public JShopShareInfo shareInfo;
    public long shopId;
    public String shopName;
    public JDJSONArray signAwardDescs;
    public String signPic;
    public String signTime;
    public int signType;
    public int source;
    public String subjectUrl;
    public boolean top;
    public int totalRecord;
    public long validTime;
    public long venderId;
    public long viewCount;
    public String viewCounts;

    public DynamicShopActivity(JDJSONObject jDJSONObject) {
        this.isSeckillOver = false;
        this.activity = jDJSONObject;
        this.venderId = jDJSONObject.optLong("venderId");
        this.shopId = jDJSONObject.optLong("shopId");
        this.shopName = jDJSONObject.optString("shopName");
        this.followed = jDJSONObject.optBoolean(JshopConst.JSKEY_FOLLOWED);
        this.activityId = jDJSONObject.optLong("activityId");
        this.commentSwitch = jDJSONObject.optString("commentSwitch");
        this.activityType = jDJSONObject.optLong(JshopConst.JSHOP_ACTIVITY_TYPE, 1L);
        this.activityTypeDes = jDJSONObject.optString("activityTypeDes");
        this.plugin = jDJSONObject.optBoolean("plugin");
        this.totalRecord = jDJSONObject.optInt("totalRecord", 0);
        this.modified = jDJSONObject.optString("modified");
        this.activityDesc = jDJSONObject.optString("activityDesc");
        setProducts(jDJSONObject.optJSONArray("products"));
        this.mUrl = jDJSONObject.optString("murl");
        this.subjectUrl = jDJSONObject.optString("subjectUrl");
        this.brandId = jDJSONObject.optLong(CartConstant.KEY_SKU_BRANDID, -1L);
        this.validTime = jDJSONObject.optLong("validTime");
        this.edited = jDJSONObject.optInt("edited", 0);
        this.activitySubType = jDJSONObject.optInt("activitySubType", 1);
        this.seckillTime = jDJSONObject.optLong("seckillTime");
        this.passTime = jDJSONObject.optLong("passTime", 0L);
        this.praiseCounts = jDJSONObject.optString("praiseCounts");
        this.viewCounts = jDJSONObject.optString("viewCounts");
        this.hadPraised = jDJSONObject.optBoolean("hadPraised");
        this.isHeadLine = jDJSONObject.optBoolean("isHeadLine");
        this.isLargePic = jDJSONObject.optBoolean("isLargePic");
        this.isDynamicSet = jDJSONObject.optBoolean("isDynamicSet");
        this.dynamicSetCover = jDJSONObject.optString("dynamicSetCover");
        this.signTime = jDJSONObject.optString("signTime");
        this.signPic = jDJSONObject.optString("signPic");
        this.signType = jDJSONObject.optInt("signType", 0);
        this.activityIds = jDJSONObject.optString(JshopConst.JSHOP_ACTIVITY_IDS);
        this.commentCounts = jDJSONObject.optString("commentCounts");
        this.commentCount = jDJSONObject.optLong("commentCount");
        this.praiseCount = jDJSONObject.optLong("praiseCount");
        this.viewCount = jDJSONObject.optLong("viewCount");
        this.commentSwitch = jDJSONObject.optString("commentSwitch");
        this.top = jDJSONObject.optBoolean("top");
        this.source = jDJSONObject.optInt("source", 2);
        this.signAwardDescs = jDJSONObject.optJSONArray("signAwardDescs");
        this.linkActivity = jDJSONObject.optBoolean("linkActivity");
        this.promotionType = jDJSONObject.optInt("promotionType", 1);
        this.isSeckillOver = jDJSONObject.optBoolean("isSeckillOver", false);
        this.bizId = jDJSONObject.optLong("bizId");
        this.coverType = jDJSONObject.optInt("coverType");
        this.liveShowStatus = jDJSONObject.optInt("liveShowStatus", -1);
        this.liveShowPublishTime = jDJSONObject.optLong("liveShowPublishTime");
        this.couponJson = jDJSONObject.optJSONObject("coupon");
        this.followAward = jDJSONObject.optInt("followAward", 0);
        this.comment = new BuyerShowComment(jDJSONObject.optJSONObject("comment"));
        this.shareInfo = new JShopShareInfo(jDJSONObject.optJSONObject("shareInfo"));
    }

    public static ArrayList<DynamicShopActivity> toList(JDJSONObject jDJSONObject) {
        JDJSONArray optJSONArray;
        ArrayList<DynamicShopActivity> arrayList = new ArrayList<>();
        if (jDJSONObject != null) {
            try {
                if ("0".equals(jDJSONObject.optString("code")) && (optJSONArray = jDJSONObject.optJSONArray("activity")) != null && optJSONArray.size() > 0) {
                    for (int i2 = 0; i2 < optJSONArray.size(); i2++) {
                        if (optJSONArray.optJSONObject(i2) != null) {
                            arrayList.add(new DynamicShopActivity(optJSONArray.optJSONObject(i2)));
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public JDJSONArray getProducts() {
        return this.products;
    }

    public void setProducts(JDJSONArray jDJSONArray) {
        this.products = jDJSONArray;
    }
}
