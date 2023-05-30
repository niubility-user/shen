package com.jingdong.app.mall.bundle.styleinfoview.protocol;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public final class PdLVBody {
    public static final String ACTIVITYID = "activityId";
    public static final String BUYNUM = "buyNum";
    public static final String CARIMGURL = "carImgUrl";
    public static final String CARSHOPID = "carShopId";
    public static final String COMEFROM = "comefrom";
    public static final String DELIVERNUM = "cDeliverNum";
    public static final String FIINFOIDS = "fiInfoIds";
    public static final String FT = "ft";
    public static final String GROUPID = "groupId";
    public static final String HE_YUE_JI_TYPE = "treatyType";
    public static final String JDSERIDS = "jdSerIds";
    public static final String JPRICE = "jprice";
    public static final String LOCSHOPID = "locShopId";
    public static final String LOCSTOREID = "storeId";
    public static final String LOCSTORENAME = "storeName";
    public static final String NUMBER = "number";
    public static final String SENDCYCLE = "cSendCycle";
    public static final String SHOPID = "shopId";
    public static final String SKUID = "skuId";
    public static final String SKUS = "skus";
    public static final String STAGENUM = "cStageNum";
    public static final String URL = "murl";
    public static final String YANBAOIDS = "yanbaoIds";
    public static final String YBLIST = "ybList";
    public static final String YB_SERVICE_ID = "ybServiceId";
    private HashMap<String, Object> bodyMap;

    /* loaded from: classes3.dex */
    public static final class Builder {
        private final HashMap<String, Object> hashMap = new HashMap<>();

        private PdLVBody build() {
            return new PdLVBody(this.hashMap);
        }

        public Builder add(String str, Object obj) {
            this.hashMap.put(str, obj);
            return this;
        }

        public HashMap<String, Object> getJsonValue() {
            return build().bodyMap;
        }

        public Builder add(Map<String, String> map) {
            if (map != null) {
                Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
                if (it.hasNext()) {
                    Map.Entry<String, String> next = it.next();
                    this.hashMap.put(next.getKey(), next.getValue());
                }
            }
            return this;
        }
    }

    PdLVBody(HashMap<String, Object> hashMap) {
        if (this.bodyMap == null) {
            this.bodyMap = new HashMap<>();
        }
        if (!this.bodyMap.isEmpty()) {
            this.bodyMap.clear();
        }
        if (hashMap != null) {
            this.bodyMap.putAll(hashMap);
        }
    }
}
