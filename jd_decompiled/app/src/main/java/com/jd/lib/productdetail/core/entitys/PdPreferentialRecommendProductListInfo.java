package com.jd.lib.productdetail.core.entitys;

import com.jd.lib.productdetail.core.entitys.warebusiness.PreferentialRecommendItemEntity;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/* loaded from: classes15.dex */
public class PdPreferentialRecommendProductListInfo implements Serializable {
    public String currentTabId;
    public PdPreferentialRecommendProductExtInfo extInfo;
    public String from;
    public Map<String, List<PreferentialRecommendItemEntity>> itemEntitiesMap;
    public List<PreferentialRecommendTabItemEntity> tabItemEntities;
}
