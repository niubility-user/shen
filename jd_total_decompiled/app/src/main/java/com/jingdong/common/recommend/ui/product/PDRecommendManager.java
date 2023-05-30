package com.jingdong.common.recommend.ui.product;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.entity.RecommendData;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendOtherData;
import com.jingdong.common.recommend.entity.RecommendPdProductFor2;
import com.jingdong.common.recommend.entity.RecommendPdTitle;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.entity.RecommendTypeEntity;
import com.jingdong.common.recommend.forlist.RecommendProductManager;
import com.jingdong.jdsdk.network.toolbox.HttpResponse;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class PDRecommendManager extends RecommendProductManager {
    private boolean isAddedPdTitle;
    private String shopId;
    private ArrayList<RecommendItem> usedItemList;
    private String venderId;

    public PDRecommendManager(IMyActivity iMyActivity, int i2, String[] strArr) {
        super(iMyActivity, i2, strArr);
        this.isAddedPdTitle = false;
        this.usedItemList = new ArrayList<>();
        this.shopId = "";
        this.venderId = "";
    }

    public RecommendTypeEntity handleJsonObject(JDJSONObject jDJSONObject, JDJSONObject jDJSONObject2) {
        if (jDJSONObject == null) {
            return null;
        }
        RecommendTypeEntity recommendTypeEntity = new RecommendTypeEntity();
        ArrayList<RecommendItem> arrayList = new ArrayList<>();
        if ("501".equals(jDJSONObject.optString("reasonDesc"))) {
            recommendTypeEntity.type = "501";
            handleTongdianPaiPai(arrayList, jDJSONObject.toJSONString(), jDJSONObject2);
            recommendTypeEntity.recommendItemList = arrayList;
            Iterator<RecommendItem> it = arrayList.iterator();
            while (it.hasNext()) {
                RecommendPdTitle recommendPdTitle = it.next().recommendPdTitle;
                if (recommendPdTitle != null) {
                    recommendPdTitle.shopId = this.shopId;
                    recommendPdTitle.venderId = this.venderId;
                }
            }
        } else if ("502".equals(jDJSONObject.optString("reasonDesc"))) {
            recommendTypeEntity.type = "502";
            if (!this.isAddedPdTitle) {
                RecommendPdTitle recommendPdTitle2 = new RecommendPdTitle();
                recommendPdTitle2.titleTop = jDJSONObject.optString("titleTop");
                recommendPdTitle2.enterShopSourceValue = jDJSONObject.optString("enterShopSourceValue");
                recommendPdTitle2.shopName = jDJSONObject.optString("shopName");
                recommendPdTitle2.showStyle = 2;
                RecommendItem recommendItem = new RecommendItem();
                recommendItem.recommendPdTitle = recommendPdTitle2;
                recommendItem.type = 10001;
                arrayList.add(recommendItem);
                this.isAddedPdTitle = true;
            }
            arrayList.addAll(toRecomendList(jDJSONObject.optJSONArray("wareInfoList"), jDJSONObject2, false, false));
            recommendTypeEntity.recommendItemList = arrayList;
        } else if ("used".equals(jDJSONObject.optString("reasonDesc"))) {
            recommendTypeEntity.type = "used";
            handleTongdianPaiPai(arrayList, jDJSONObject.toJSONString(), jDJSONObject2);
            recommendTypeEntity.recommendItemList = arrayList;
        }
        return recommendTypeEntity;
    }

    public void handleTongdianPaiPai(ArrayList<RecommendItem> arrayList, String str, JDJSONObject jDJSONObject) {
        List<RecommendProduct> list;
        if (arrayList == null || str == null) {
            return;
        }
        RecommendPdTitle recommendPdTitle = (RecommendPdTitle) JDJSON.parseObject(str, RecommendPdTitle.class);
        recommendPdTitle.showStyle = 2;
        JDJSONArray jDJSONArray = null;
        if (jDJSONObject != null) {
            JDJSONObject parseObject = JDJSON.parseObject(str);
            if (parseObject.optJSONArray("wareInfoList") != null) {
                jDJSONArray = parseObject.getJSONArray("wareInfoList");
            }
        }
        RecommendItem recommendItem = new RecommendItem();
        recommendItem.type = 10001;
        recommendItem.recommendPdTitle = recommendPdTitle;
        arrayList.add(recommendItem);
        if (recommendPdTitle == null || (list = recommendPdTitle.wareInfoList) == null || list.isEmpty()) {
            return;
        }
        List<RecommendProduct> list2 = recommendPdTitle.wareInfoList;
        for (int i2 = 0; i2 < list2.size(); i2 += 2) {
            RecommendPdProductFor2 recommendPdProductFor2 = new RecommendPdProductFor2();
            RecommendProduct recommendProduct = list2.get(i2);
            recommendPdProductFor2.leftProduct = recommendProduct;
            if (recommendProduct != null && jDJSONArray != null && i2 < jDJSONArray.size()) {
                recommendPdProductFor2.leftProduct.jdjsonObject = jDJSONArray.getJSONObject(i2);
                recommendPdProductFor2.leftProduct.rootUETJson = jDJSONObject;
            }
            int i3 = i2 + 1;
            if (i3 < list2.size()) {
                RecommendProduct recommendProduct2 = list2.get(i3);
                recommendPdProductFor2.rightProduct = recommendProduct2;
                if (recommendProduct2 != null && jDJSONArray != null && i3 < jDJSONArray.size()) {
                    recommendPdProductFor2.rightProduct.jdjsonObject = jDJSONArray.getJSONObject(i3);
                    recommendPdProductFor2.rightProduct.rootUETJson = jDJSONObject;
                }
            }
            RecommendItem recommendItem2 = new RecommendItem();
            recommendItem2.type = 10002;
            recommendItem2.recommendPdProductFor2 = recommendPdProductFor2;
            arrayList.add(recommendItem2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
    public void onRefreshListData() {
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c4  */
    @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected RecommendData parseRecommendData(JDJSONObject jDJSONObject, HttpResponse httpResponse) {
        Map<String, Object> moreParams;
        int i2;
        RecommendData recommendData = new RecommendData();
        if (jDJSONObject != null) {
            JDJSONArray optJSONArray = jDJSONObject.optJSONArray("reasonFloorWareInfos");
            JDJSONObject jDJSONObject2 = null;
            try {
                if (jDJSONObject.optJSONObject(RecommendMtaUtils.UET) != null && jDJSONObject.optJSONObject(RecommendMtaUtils.UET).optJSONObject(RecommendMtaUtils.TRACKING) != null) {
                    JDJSONObject jDJSONObject3 = new JDJSONObject();
                    try {
                        jDJSONObject3.put(RecommendMtaUtils.UET, (Object) jDJSONObject.optJSONObject(RecommendMtaUtils.UET));
                        jDJSONObject2 = jDJSONObject3;
                    } catch (Exception e2) {
                        e = e2;
                        jDJSONObject2 = jDJSONObject3;
                        if (OKLog.D) {
                            e.printStackTrace();
                        }
                        moreParams = httpResponse.getMoreParams();
                        i2 = -1;
                        if (moreParams != null) {
                            i2 = ((Integer) moreParams.get(RecommendConstant.RECOM_pageNoParamKey)).intValue();
                        }
                        int i3 = 0;
                        if (i2 != 1) {
                        }
                        if (recommendData.getRecommendList() != null) {
                            return recommendData;
                        }
                        recommendData.setRecommendList(new ArrayList<>());
                        return recommendData;
                    }
                }
            } catch (Exception e3) {
                e = e3;
            }
            moreParams = httpResponse.getMoreParams();
            i2 = -1;
            if (moreParams != null && (moreParams.get(RecommendConstant.RECOM_pageNoParamKey) instanceof Integer)) {
                i2 = ((Integer) moreParams.get(RecommendConstant.RECOM_pageNoParamKey)).intValue();
            }
            int i32 = 0;
            if (i2 != 1) {
                parseVideoSwitch(jDJSONObject);
                if (optJSONArray != null) {
                    while (i32 < optJSONArray.size()) {
                        RecommendTypeEntity handleJsonObject = handleJsonObject(optJSONArray.getJSONObject(i32), jDJSONObject2);
                        if (handleJsonObject != null) {
                            if ("used".equals(handleJsonObject.type)) {
                                this.usedItemList.clear();
                                this.usedItemList.addAll(handleJsonObject.recommendItemList);
                            } else {
                                recommendData.addRecommend(handleJsonObject.recommendItemList);
                            }
                        }
                        i32++;
                    }
                    if ((recommendData.getRecommendList() == null || recommendData.getRecommendList().isEmpty()) && !this.usedItemList.isEmpty()) {
                        RecommendOtherData recommendOtherData = new RecommendOtherData();
                        recommendOtherData.isReachEnd = true;
                        recommendData.setRecommendOtherData(recommendOtherData);
                        recommendData.addRecommend(this.usedItemList);
                    }
                } else {
                    RecommendOtherData recommendOtherData2 = new RecommendOtherData();
                    recommendOtherData2.isReachEnd = true;
                    recommendData.setRecommendOtherData(recommendOtherData2);
                }
            } else if (optJSONArray != null && !optJSONArray.isEmpty()) {
                while (i32 < optJSONArray.size()) {
                    RecommendTypeEntity handleJsonObject2 = handleJsonObject(optJSONArray.getJSONObject(i32), jDJSONObject2);
                    if (handleJsonObject2 != null && !"501".equals(handleJsonObject2.type) && !"used".equals(handleJsonObject2.type)) {
                        recommendData.addRecommend(handleJsonObject2.recommendItemList);
                    }
                    i32++;
                }
            } else {
                if (!this.usedItemList.isEmpty()) {
                    recommendData.addRecommend(this.usedItemList);
                }
                RecommendOtherData recommendOtherData3 = new RecommendOtherData();
                recommendOtherData3.isReachEnd = true;
                recommendData.setRecommendOtherData(recommendOtherData3);
            }
        }
        if (recommendData.getRecommendList() != null && recommendData.getRecommendList() == null) {
            recommendData.setRecommendList(new ArrayList<>());
        }
        return recommendData;
    }

    @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
    public void reSet() {
        this.usedItemList.clear();
        this.isAddedPdTitle = false;
        super.reSet();
    }

    @Override // com.jingdong.common.recommend.forlist.RecommendProductManager
    public void setExtentParam(HashMap hashMap) {
        super.setExtentParam(hashMap);
        if (hashMap.containsKey(RecommendConstant.RECOM_SHOP_ID)) {
            this.shopId = (String) hashMap.get(RecommendConstant.RECOM_SHOP_ID);
        }
        if (hashMap.containsKey(RecommendConstant.RECOM_VENDER_ID)) {
            this.venderId = (String) hashMap.get(RecommendConstant.RECOM_VENDER_ID);
        }
    }
}
