package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.wozhe.MatchItemsBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class WareBusinessMagicHeadPicInfoEntity {
    @Deprecated
    public static final String ANCHORTYPE_3D = "3D";
    public static final String ANCHORTYPE_3D_NEW = "3DNew";
    public static final String ANCHORTYPE_3D_OVERALL_VIEW = "overallView";
    public static final String ANCHORTYPE_AR_BBK = "arBBK";
    @Deprecated
    public static final String ANCHORTYPE_AR_CF = "arCF";
    public static final String ANCHORTYPE_AR_CF_AI = "arCFJK";
    public static final String ANCHORTYPE_AR_CF_NEW = "arCFNew";
    public static final String ANCHORTYPE_AR_RF = "arRF";
    public static final String ANCHORTYPE_AR_SD = "arSD";
    public static final String ANCHORTYPE_AR_SX = "arSX";
    public static final String ANCHORTYPE_AR_SZ = "arSZ";
    public static final String ANCHORTYPE_AR_XCC = "arXCC";
    public static final String ANCHORTYPE_ASK = "qa";
    public static final String ANCHORTYPE_ATLAS = "atlas";
    public static final String ANCHORTYPE_ATLAS_VIDEO = "atlasVideo";
    @Deprecated
    public static final String ANCHORTYPE_COMMENT = "comment";
    public static final String ANCHORTYPE_COMMENT_BANG_DAN = "bangDan";
    public static final String ANCHORTYPE_COMMENT_GOLD_RANK_BANG_DAN = "goldRank";
    public static final String ANCHORTYPE_COMMENT_NEW = "commentNew";
    public static final String ANCHORTYPE_DPG = "vrDPG";
    public static final String ANCHORTYPE_DPG_SMALL = "dpg_small";
    public static final String ANCHORTYPE_GIFT = "gift";
    public static final String ANCHORTYPE_HEADPIC = "headPic";
    public static final String ANCHORTYPE_ICON = "icon";
    public static final String ANCHORTYPE_ICON_NEW = "iconNew";
    public static final String ANCHORTYPE_ISV_ITEM = "dynView";
    public static final String ANCHORTYPE_LIVE = "live";
    public static final String ANCHORTYPE_SUIT = "suit";
    public static final String ANCHORTYPE_VIDEO = "video";
    @Deprecated
    public static final String ANCHORTYPE_VR_CHANGJING = "vrChangJ";
    public static final String ANCHORTYPE_VR_CHANGJING_NEW = "vrCJNew";
    public static final String ANCHORTYPE_VR_DPG = "vrDPG";
    public static final String ANCHORTYPE_WAREIMAGE = "wareImage";
    public static final String ANCHORTYPE_WAREIMAGESEC = "wareImageSec";
    public static final String ANCHORTYPE_YPSMS = "ypsms";
    public static final String ANCHORTYPE_ZHONGCAOXIU = "zhongcaoxiu";
    public static final String FB_TOP_IMAGE_RECOMMEND = "tuijian";
    public static final String IMAGE_TYPE_GIF = "gif";
    public String anchorName;
    public String anchorType;
    public WareImageRecommendRankEntity bangDanInfo;
    public WareBuinessUnitMainImageBizDataEntity bizData;
    public WareBusinessMainPictureCfInfo cfInfo;
    public boolean commentPriorityFlag;
    public String defaultImageUrl;
    public ArrayList<PdDpgSmallInfo> dpgDetails;
    public MatchItemsBean dpgHeadPicInfo;
    public PdDrugInfo drugInfo;
    public HeadPicGiftInfoEntity headPicGiftInfo;
    public boolean hitFWNewStyle;
    public int iViewType;
    public List<WareBusinessMagicPicIcons> icons;
    public String imageType;
    public String imageUrl;
    public List<WareBusinessMagicPicItems> items;
    public String mIsvData;
    public PdMainSku mainProductShortDpgSuit;
    public String mfStyleId;
    public String newStyleIcon;
    public NewStyleText newStyleText;
    public String popData;
    public WareImageQaEntity qaInfo;
    public String subTitle;
    public ArrayList<PdDpgSmallInfo> suitDetails;
    public String title;
    public WareImageRecommendEntity tjInfo;
    public WareBusinessServiceIconTrustEntity trustworthy;
    public WareBusinessWareImageEntity wareImage;
    public int ztfwTemplateType = -100;
    public List<String> ztfwTimeExpPoint;

    /* loaded from: classes15.dex */
    public static class NewStyleText {
        public String allText;
        public String specialText;
    }

    public String getAnchorTypeWithCommentInfo(PdCommentInfo pdCommentInfo) {
        PdCommentInfo.BuyersCommentInfo buyersCommentInfo;
        List<PdCommentInfo.BuyersCommentInfoList> list;
        String str = this.anchorType;
        return (!TextUtils.equals(str, "comment") || pdCommentInfo == null) ? str : (this.commentPriorityFlag || pdCommentInfo == null || (buyersCommentInfo = pdCommentInfo.buyersCommentInfo) == null || (list = buyersCommentInfo.buyersCommentInfoList) == null || list.size() < 4) ? "comment" : ANCHORTYPE_ZHONGCAOXIU;
    }

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
