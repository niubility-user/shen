package com.jingdong.common.video.cache;

import com.jd.framework.json.JDJSONObject;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class MiniWareInfoEntity implements Serializable {
    public String code;
    public Data data;
    public boolean success;

    /* loaded from: classes6.dex */
    public static class Data implements Serializable {
        public JDJSONObject buttonMsg;
        public ArrayList<SkuInfo> skuInfos;
    }

    /* loaded from: classes6.dex */
    public static class SkuData implements Serializable {
        public String PreferentialGuide;
        public String WareBasicInfo;
    }

    /* loaded from: classes6.dex */
    public static class SkuInfo implements Serializable {
        public SkuData data;
        public String sku;
    }
}
