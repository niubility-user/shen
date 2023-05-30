package com.jd.lib.productdetail.core.entitys.warebusiness;

import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.AbBuriedExpLabelsEntity;
import com.jd.lib.productdetail.core.entitys.BarrageInfo;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.entitys.pgcarticle.PdPgcArticleEntity;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessUnitMainImageEntity {
    public AbBuriedExpLabelsEntity abBuriedExpLabels;
    public ExtMapEntity extMap;
    public List<WareBusinessMagicAnchorEntity> magicAnchor;
    public String magicHeadAbTouchStone;
    public List<WareBusinessMagicHeadPicInfoEntity> magicHeadPicInfo;
    public MarkerCollectEntity markerCollect;

    /* loaded from: classes15.dex */
    public static class ExtMapEntity {
        @Deprecated
        public AbBuriedExpLabelsEntity abBuriedExpLabels;
        public String activityId;
        public AppStaticInfo appStaticInfo;
        public BarrageInfo barrageInfo;
        public CategoryEntity category;
        public boolean daojiaFlag;
        public List<PdDpgListLayerInfo.DetailBean> dpgIntegration;
        public String groupId;
        public boolean hasCover;
        public boolean isPop;
        public String isShadowSku;
        public boolean isShowAR;
        public String isXnzt;
        public boolean mIsDefault;
        public int magicHeadPicType;
        public boolean mainPicSlide;
        public boolean mainPicV12;
        public boolean mainPictureDomainFlag;
        public String recommendPid;
        public String shopId;
        public String skuId;
        public String storeId;
        public boolean threeDSwitch;
        public String venderId;

        /* loaded from: classes15.dex */
        public static class CategoryEntity {
            public int fstId;
            public int sndId;
            public int thdId;
        }
    }

    /* loaded from: classes15.dex */
    public static class MarkerCollectEntity {
        public String goGymVideo;
        public String gymVideoImg;
        public WareBusinessMaxSales maxSales;
        public WareBusinessTopMpWareInfo mpWareInfo;
        public WareBusinessPaperBookEntity paperBook;
        public PdPgcArticleEntity pgc3cVideo;
    }

    public static void dealIsvImage(List<WareBusinessMagicHeadPicInfoEntity> list, JDJSONObject jDJSONObject) {
        if (list == null || jDJSONObject == null) {
            return;
        }
        try {
            JDJSONArray jSONArray = jDJSONObject.getJSONArray("magicHeadPicInfo");
            for (int i2 = 0; i2 < list.size(); i2++) {
                WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = list.get(i2);
                if (wareBusinessMagicHeadPicInfoEntity != null && wareBusinessMagicHeadPicInfoEntity.iViewType != 0 && i2 < jSONArray.size()) {
                    wareBusinessMagicHeadPicInfoEntity.mIsvData = jSONArray.getString(i2);
                }
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public static WareBusinessUnitMainImageEntity parse(JDJSONObject jDJSONObject) {
        if (jDJSONObject.getJSONObject("extMap") != null) {
            WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = (WareBusinessUnitMainImageEntity) JDJSON.parseObject(jDJSONObject.toString(), WareBusinessUnitMainImageEntity.class);
            dealIsvImage(wareBusinessUnitMainImageEntity.magicHeadPicInfo, jDJSONObject);
            return wareBusinessUnitMainImageEntity;
        }
        return null;
    }
}
