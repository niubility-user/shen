package com.jd.lib.productdetail.core.entitys;

import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public class SkinRecommendInfoEntity {
    public Data data;
    public String errorCode;
    public String errorMsg;
    public boolean success;

    /* loaded from: classes15.dex */
    public static class CompleteSkus {
        public String cate_name;
        public List<Functions> functions;
        public String image;
        public String name;
        public String price;
        public String skuId;
    }

    /* loaded from: classes15.dex */
    public static class Data {
        public List<CompleteSkus> completeSkus;
        public String completeSkusDesc;
        public String completeSkusLabel;
        public List<Diagnosis> diagnosis;
        public boolean isMatch;
        public String percentage;
    }

    /* loaded from: classes15.dex */
    public static class Diagnosis {
        public Map<String, List<CompleteSkus>> exclusiveSkus;
        public String label;
        public String proposal;
    }

    /* loaded from: classes15.dex */
    public static class Functions {
        public String function;
        public String ingredient;
    }
}
