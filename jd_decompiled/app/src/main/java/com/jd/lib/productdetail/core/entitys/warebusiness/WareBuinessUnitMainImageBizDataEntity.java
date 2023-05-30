package com.jd.lib.productdetail.core.entitys.warebusiness;

import com.jd.lib.productdetail.core.PdOneToNPriceVo;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.wozhe.MatchItemsBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class WareBuinessUnitMainImageBizDataEntity {
    public PdArVrBizData arVrBizData;
    public WareImageRecommendRankEntity bangDanInfo;
    public PdCommentBizData commentBizData;
    public MatchItemsBean dpgHeadPicInfo;
    public PdDpgSuitBizData dpgSuitBizData;
    public PdDrugBizData drugInstructionsBizData;
    public WareImageQaEntity drugQuestionBizData;
    public HeadPicGiftInfoEntity headPicGiftInfo;
    public List<ImageComponentEntity> imageComponent;
    public PdOneToNPriceVo oneToNPriceVo;
    public PdServiceBizData serviceBizData;
    public PdTjBizData tjBizData;
    public PdVideoBizData videoBizData;
    public PdVrDpgBizData vrDpgBizData;

    /* loaded from: classes15.dex */
    public static class ImageComponentEntity {
        public String big;
        public int index;
        public String share;
        public String small;
    }

    /* loaded from: classes15.dex */
    public static class PdArVrBizData {
        public WareBusinessMainPictureCfInfo cfInfo;
        private String defaultImageUrl;
        public List<WareBusinessMagicPicItems> items;
    }

    /* loaded from: classes15.dex */
    public static class PdCommentBizData {
        public String buyersIcon;
        public boolean commentPriorityFlag;
        public String defaultImageUrl;
    }

    /* loaded from: classes15.dex */
    public static class PdDpgSuitBizData {
        public ArrayList<PdDpgSmallInfo> dpgDetails;
        public List<PdDpgListLayerInfo.DetailBean> dpgIntegration;
        public ArrayList<WareBusinessMainPictureDpgPops> mainPictureDpgPops;
        public PdMainSku mainProductShortDpgSuit;
        public PdRecommendRankShowMap recommendRankShowMap;
        public ArrayList<PdDpgSmallInfo> suitDetails;

        public ArrayList<PdDpgSmallInfo> getSuitDetails() {
            ArrayList<PdDpgSmallInfo> arrayList = this.dpgDetails;
            if (arrayList != null) {
                return arrayList;
            }
            ArrayList<PdDpgSmallInfo> arrayList2 = this.suitDetails;
            if (arrayList2 != null) {
                return arrayList2;
            }
            return null;
        }
    }

    /* loaded from: classes15.dex */
    public static class PdDrugBizData {
        public PdDrugInfo drugInfo;
        public String popData;
    }

    /* loaded from: classes15.dex */
    public static class PdRecommendRankShowMap {
        public String rankId;
        public String rankType;
    }

    /* loaded from: classes15.dex */
    public static class PdServiceBizData {
        public boolean hitFWNewStyle;
        public List<WareBusinessMagicPicIcons> icons;
        public String newStyleIcon;
        public WareBusinessMagicHeadPicInfoEntity.NewStyleText newStyleText;
        public String subTitle;
        public String title;
        public WareBusinessServiceIconTrustEntity trustworthy;
        public int ztfwTemplateType = -100;
        public List<String> ztfwTimeExpPoint;

        /* loaded from: classes15.dex */
        public static class NewStyleText {
            public String allText;
            public String specialText;
        }
    }

    /* loaded from: classes15.dex */
    public static class PdTjBizData {
        public ArrayList<WareBusinessMainPictureDpgPops> mainPictureDpgPops;
        public WareImageRecommendEntity tjInfo;
    }

    /* loaded from: classes15.dex */
    public static class PdVideoBizData {
        public WareBusinessTopVideoControl videoControl;
        public boolean videoPlayerFlag;
    }

    /* loaded from: classes15.dex */
    public static class PdVrDpgBizData {
        public List<PdDpgListLayerInfo.DetailBean> dpgIntegration;
        public ArrayList<WareBusinessMainPictureDpgPops> mainPictureDpgPops;
    }
}
