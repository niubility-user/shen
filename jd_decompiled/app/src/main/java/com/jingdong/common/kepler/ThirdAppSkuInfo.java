package com.jingdong.common.kepler;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ThirdAppSkuInfo {
    private static ThirdAppSkuInfo instance;
    private List<String> skuIds;

    private ThirdAppSkuInfo() {
    }

    public static ThirdAppSkuInfo getInstance() {
        if (instance == null) {
            synchronized (ThirdAppSkuInfo.class) {
                if (instance == null) {
                    instance = new ThirdAppSkuInfo();
                }
            }
        }
        return instance;
    }

    public void clearSkuId() {
        List<String> list = this.skuIds;
        if (list != null) {
            list.clear();
        }
    }

    public String getSkuId() {
        List<String> list = this.skuIds;
        if (list == null || list.size() <= 0) {
            return "";
        }
        return this.skuIds.get(r0.size() - 1);
    }

    public int getSkuIdsSize() {
        List<String> list = this.skuIds;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void saveSkuId(String str) {
        if (this.skuIds == null) {
            this.skuIds = new ArrayList();
        }
        if (this.skuIds.contains(str)) {
            return;
        }
        this.skuIds.add(str);
    }
}
