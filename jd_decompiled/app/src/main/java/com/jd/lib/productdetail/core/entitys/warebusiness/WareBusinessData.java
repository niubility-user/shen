package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.framework.json.anotation.JSONField;
import com.jd.lib.productdetail.core.entitys.AbBuriedExpLabelsEntity;
import com.jd.lib.productdetail.core.entitys.BusinessPlusDayDialogTitleBg;
import com.jd.lib.productdetail.core.entitys.MainPicDpgInfo;
import com.jd.lib.productdetail.core.entitys.NoStockRecommendHead;
import com.jd.lib.productdetail.core.entitys.PDInternetHospitalEntity;
import com.jd.lib.productdetail.core.entitys.PDMtaEntity;
import com.jd.lib.productdetail.core.entitys.PDRecommendRankEntity;
import com.jd.lib.productdetail.core.entitys.PdAhStoreBottomButtonEntity;
import com.jd.lib.productdetail.core.entitys.PdAhStoreEntity;
import com.jd.lib.productdetail.core.entitys.PdBbBombEntity;
import com.jd.lib.productdetail.core.entitys.PdBuyByMEntity;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.entitys.PdHealthBottomBtnEntity;
import com.jd.lib.productdetail.core.entitys.PdPaiMaiButton;
import com.jd.lib.productdetail.core.entitys.PdReportEntity;
import com.jd.lib.productdetail.core.entitys.PdStudentAuthenticateEntity;
import com.jd.lib.productdetail.core.entitys.PdToHandPriceEntity;
import com.jd.lib.productdetail.core.entitys.PreferentialGuideEntity;
import com.jd.lib.productdetail.core.entitys.RecommendToast;
import com.jd.lib.productdetail.core.entitys.WareGoldPurchaseEntity;
import com.jd.lib.productdetail.core.entitys.WareMtaCommon;
import com.jd.lib.productdetail.core.entitys.WareScfSkuInfo;
import com.jd.lib.productdetail.core.entitys.buttoninfo.PDBottomInfo;
import com.jd.lib.productdetail.core.entitys.delivery.PDDeliveryInstallEntity;
import com.jd.lib.productdetail.core.entitys.ecard.PDECardInfoEntity;
import com.jd.lib.productdetail.core.entitys.eut.PDEuBaguette;
import com.jd.lib.productdetail.core.entitys.installment.PDInstallmentInfoEntity;
import com.jd.lib.productdetail.core.entitys.installment.PDInstallmentTipEntity;
import com.jd.lib.productdetail.core.entitys.loc.LocInfo;
import com.jd.lib.productdetail.core.entitys.loc.LocShopInfo;
import com.jd.lib.productdetail.core.entitys.pgcarticle.PdPgcArticleEntity;
import com.jd.lib.productdetail.core.entitys.plusmember.PDPlusFreightEntity;
import com.jd.lib.productdetail.core.entitys.promotion.PdSuitEntry;
import com.jd.lib.productdetail.core.entitys.regularbuy.PDRegularBuyEntity;
import com.jd.lib.productdetail.core.entitys.seckillcertify.PDSeckillCertifyEntity;
import com.jd.lib.productdetail.core.entitys.seckillcertify.PDSeckillTipEntity;
import com.jd.lib.productdetail.core.entitys.shop.PdShopEntity;
import com.jd.lib.productdetail.core.entitys.sizehelper.PDSizeHelperEntity;
import com.jd.lib.productdetail.core.entitys.sizehelper.PdShoesSizeHelperEntity;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class WareBusinessData {
    public WareBusinessDiscountPriceEntity DiscountPrice;
    public FeedEvaluate FeedEvaluate;
    public WareBusinessNameEntity GodTitle;
    public PdPgcArticleEntity PGC;
    public WareBusinessTopImageEntity ProductImage;
    public WareBusinessAddrEntity StockInfo;
    public WareBusinessJingPriceEntity WarePrice;
    public WareYuShouInfo YuShouInfo;
    public AbBuriedExpLabelsEntity abBuriedExpLabels;
    public AbBuriedExpLabelsEntity abBuriedExpLabelsNEW;
    public boolean abTest800;
    public WareBusinessABTestInfo abTestInfo;
    public List<String> abTouchStones;
    public PdAcsEntity acsModel;
    public PdBuyByMEntity addCart;
    public String addCartShowNewRecommendPopup;
    public String addCartShowNewRecommendPopupExpLabel;
    public AddFamilyListData addFamilyList;
    public PdMtAiButton aiButton;
    public String appScene;
    public AppStaticInfo appStaticInfo;
    public WareAppletShare appletShare;
    public ArWakeUpData arMakeUpMap;
    public List<String> arcfAbResult;
    public ArchivedSku archiveSku;
    public String asyncInterfaceBody;
    public WareBusinessAttentInfo attentionInfo;
    public List<String> bangDanExperiment;
    public DaoShouPriceBannerInfo bannerInfo;
    public int bb301;
    public PdBbBombEntity bbBomb;
    public PdBannerMdEntity beltConfigCenter;
    public String bgImage;
    public WareBusinessBigPlus bigPlusBottom;
    public WareBusinessBigPlusJumpPlayer bigPlusJumpPlayer;
    public BusinessBigSaleCountDown bigSaleCountDown;
    public boolean boomer;
    public PdBottomShopJumpInfo bottomShopJumpInfo;
    public BrandMemberInfo brandMemberInfo;
    public String broker_info;
    public PDBottomInfo buttonInfo;
    public WareBusinessBuyMethodEntity buyMethod;
    public String bybtTitle;
    public WareBusinessCarAllInfo carAllInfo;
    public WareBusinessCarFinanceEntity carFinance;
    public boolean carGrayFlag;
    public boolean carStoreFlag;
    public boolean cccHighEnd;
    public SuperBrandEntity cjxpInfo;
    public String clothMark;
    public WareBusinessStyleEntity colorSizeInfo;
    public CommonBannerInfo commonBannerInfo;
    public String cpmsTip;
    public CustomizeInfoEntity customizeInfo;
    public PdAhStoreBottomButtonEntity daojiaSelfDeliveryBottomInfo;
    public PdAhStoreEntity daojiaStoreInfo;
    public PdDdFatigueMechanism ddFatigueMechanism;
    public DefineSkinChange defineSkinChange;
    public PDDeliveryInstallEntity deliveryInstall;
    public WareBusinessDepositAddCart depositAddCart;
    public boolean detail4Smart;
    public boolean digitalBottom;
    public WareBusinessDiscountPriceEntity discountPriceInfo;
    public String doublepriceUp;
    public List<PdDpgListLayerInfo.DetailBean> dpgIntegration;
    public com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessDrugEntity drugInfo;
    public PDECardInfoEntity eCardInfo;
    public PDEuBaguette euBaguette;
    public WareEventParam eventParam;
    public PdStudentAuthenticateEntity exclusive2Student;
    public String extFlag;
    public EyeSightQueryInfoEntity eyeSightQueryInfo;
    public FastChatEntity familyChatInfo;
    public WareBusinessFastMailEntity fastMailInfo;
    public WareBusinessProductFeeInfo feeInfo;
    public int firstPageMark;
    public WareFlashInfoEntity flashInfo;
    public String floorZoneStyle;
    public WareBusinessFreshBCMatchInfo freshBCMatchInfo;
    public long freshDelay;
    public WareFurnitureInfo furnitureIncrementServiceInfo;
    public WareBusinessFutureGuideAdvance futureGuideAdvance;
    public Map<String, String> generalTrackDic;
    public WareBusinessGiftInfo giftInfo;
    public PdGiftShopEntity giftShopping;
    public WareBusinessPaiPaiReplacement gjhsInfo;
    public WareGoldPurchaseEntity goldPurchase;
    public String goodsStateBuriedPoint;
    public String guiGeMUrlAppendUnit;
    public GuidePriceInfo guidePriceInfo;
    public boolean hasPlusDqg;
    public WareBusinessPaiPaiReplacement homeApplianceYjhxInfo;
    public HourPurchaseYjhxEntity hourPurchaseYjhx;
    public WareBusinessHwShareInfo hwShareInfo;
    public PDSeckillCertifyEntity idAuth;
    public String idAuthToast;
    public JDJSONArray imoutaiV2;
    public PDInstallmentInfoEntity installmentInfo;
    public PDInstallmentTipEntity installmentTip;
    public PDInternetHospitalEntity internetHospital;
    public WareBusinessIntroAlbumEntity introAlbum;
    public int isBigPlus;
    public int isCpsNoTuan;
    public boolean isDelayFresh;
    public boolean isDesCbc;
    public boolean isFactoryShip;
    public boolean isHealthImmediateBuy;
    public boolean isHyj;
    public boolean isIndustrialProducts;
    public boolean isNewPP;
    public boolean isNewProBanner;
    public boolean isOldAddress;
    public boolean isOpen;
    public boolean isOpenCar;
    public boolean isPaiMaiykj;
    public boolean isPlusUser;
    public boolean isScf;
    public boolean isShowStyleNoTermiteTip;
    public boolean isStockRecommend;
    public boolean isTimeOrderPrescriptCat;
    public String isXnzt;
    public WareBusinessHealthAppoint jHealthAppoint;
    public PdjServiceSpecialBtnInfo jServiceSpecialBtnInfo;
    public SuperBrandEntity jdMarketBrandInfo;
    public WareJdServerPlusEntity jdSerPlusInfo;
    public int jdwlType;
    public WareBusinessCollageJoinBuyInfoEntity joinBuyInfo;
    public JpsAgreementEntity jpsAgreement;
    public boolean jumpToProduct;
    public WareBusinessJxInfoEntity jxInfo;
    public PDSeckillTipEntity koInfo;
    public WareBusinessKoMapEntity koMap;
    public LayerFlagExperiment layerFlagExperiment;
    public boolean leVieuxFusil;
    public String liveExperimentAb;
    public PdLiveFloatEntity liveInfo;
    public boolean locCouponFlag;
    public LocInfo locInfo;
    public LocShopInfo locShopInfo;
    public String mCommentCount;
    public boolean mDJBusinessUseCache;
    public String mDJDefaultStoreID;
    public String mTouchstone_expids;
    public List<WareBusinessMagicAnchorEntity> magicAnchor;
    public String magicHeadAbTouchStone;
    public boolean magicHeadGifShow;
    public List<WareBusinessMagicHeadPicInfoEntity> magicHeadPicInfo;
    public int magicHeadPicType;
    public MainPicDpgInfo mainPicDpgInfo;
    public boolean mainPicSlide;
    public boolean mainPicV12;
    public boolean mainPictureDomainFlag;
    public ArrayList<WareBusinessMainPictureDpgPops> mainPictureDpgPops;
    public String main_sku;
    public WareBusinessPlusForBuyMt makeMoreTime;
    public WareMtaCommon mat;
    public String materialCoding;
    public WareMiaoShaInfo miaoshaInfo;
    public WareShowStateBarEntity modelAdapterInfo;
    public PDMtaEntity mtaInfo;
    public boolean needNotAppoint;
    public boolean netvirtaFlag;
    public boolean newStyle;
    public WareBusinessNoStockRecomBanner noStockRecomBanner;
    public NoStockRecommendHead noStockRecommendMap;
    public String normandyAbTouchStone;
    public String normandyUrl;
    public String numTitle;
    public boolean oldMoreStyle;
    public WareBusinessOnebox onebox;
    public Ware3cServerEntity originalFactoryServiceInfo;
    public boolean overseaBuyDegrade;
    public WareBusinessPaiPaiReplacement paiPaiOld4New;
    public PdPaiMaiButton paimaiButton;
    public PdPayGuidTips payGuidTips;
    public BusinessPlusDayDialogTitleBg plusDayMap;
    public PDPlusFreightEntity plusFeeTips;
    public WarePlusInfo plusInfo;
    public boolean plusShieldLandedPriceFlag;
    public WareBusinessPointInfo pointInfo;
    public JDJSONObject popupTemplate;
    public BusinessPaiPaiSmallImgStockInfo ppInfo;
    public boolean preSaleSpotDegrade;
    public WarePriceInfo priceInfo;
    public String priceIntr;
    public String priceLabel;
    public PdWhiteBarForStyleInfoEntity proPageWhiteBarInfo;
    public WarePromotionInfo promotionInfo;
    public BusinessPromotionPoint promotionPoint;
    public WarePropertyInfo property;
    public PdAhStoreEntity qzcShopInfo;
    public String rankName;
    public int rankType;
    public String rec_broker;
    public String rec_sku;
    public int receiveBiz;
    public String recommendPid;
    public PDRecommendRankEntity recommendRankShowMap;
    public RecommendToast recommendToast;
    public boolean refreshMe1;
    public RegionalMainPicBuried regionalPicBuried;
    public PDRegularBuyEntity regularBuy;
    public boolean regularMainProcess;
    public RegularMainProcessChoice regularMainProcessChoice;
    public PdReportEntity report;
    public RiskArchiveSku riskArchive;
    public PdHealthBottomBtnEntity rxNewPrescriptInfoResult;
    public WareSamInfo samInfo;
    public WareScfSkuInfo scfSkuInfo;
    public BusinessSearchInfo searchInfo;
    public SecondPriceEntity secondPriceInfo;
    public boolean selectedFloorSimplifySwitch;
    public WareBusinessSimpleServices serviceSimplify;
    public WareBusinessShareImageInfo shareImgInfo;
    public boolean shop12Style;
    public PdShopEntity shopInfo;
    public boolean shopNewStyle;
    public boolean showAddCartForYs;
    public boolean showBarterService;
    public List<String> showBarterServiceAbTouchStones;
    public boolean showFunctionBtn;
    public boolean showHongmengBtn;
    public PDSkinEntity skin;
    public WareBusinessSkuPropertyList skuPropertyList;
    public boolean smartIntro;
    public boolean smartWare;
    public boolean smartWareNotAB;
    public String smartWareTag;
    public WareBusinessSmartWare smartWares;
    public WareBusinessSoldOverSea soldOversea;
    public WareBusinessSpecialInfo specialInfo;
    public ArrayList<WareBusinessStyleSpu> spuInfos;
    public String spuTitle;
    public WareStockEntity stockNode;
    public boolean stockRecSource;
    public PDWebCastEntity storageAdvertise;
    public WareBusinessDJStoreEntity storeFloorInfo;
    public PdStrengThenPriceEntity strengThenPrice;
    public String styleSizeArType;
    public PdSuitEntry suit;
    public boolean supportSale;
    public SurveyData surveyDO;
    public String tabUrl;
    public String templateType;
    public boolean tenVersionPrice;
    public boolean termite;
    public boolean termiteCartShield;
    @JSONField(name = "3cServiceInfo")
    public Ware3cServerNewEntity threeCServiceInfo;
    public boolean title12style;
    public PdToHandPriceEntity toHandssSrengthen;
    public boolean topOn;
    public List<TopTabAnchor> topTabAnchor;
    public ArrayList<TreayNewInfo> treayNewInfo;
    public boolean tuijian12style;
    public WareBusinessTurnToH5 turnToH5;
    public boolean twComment;
    public boolean universalFlag;
    public PdShoesSizeHelperEntity userShoesSizeInfo;
    public PDSizeHelperEntity userSizeInfo;
    public boolean videoPlayerFlag;
    public WareEyeSightInfo wareEyeSight;
    public List<WareBusinessWareImageEntity> wareImage;
    public WareBasicInfo wareInfo;
    public PDWebCastEntity webCast;
    public String webcastExclusiveGiftToast;
    public WareBusinessWechatShareInfo wechatShareInfo;
    public WareBusinessWeightEntity weightInfo;
    public WzaCode wzacode;
    public String xpCommentTitle;
    public WareYanBaoEntity yanBaoInfo;
    public String yjhxToast;
    public String yjhxtabtype;
    public WareYureInfo yrqInfo;
    public List<String> ysAddCartExperiment;
    public WareYuYueInfo yuyueInfo;
    public boolean selever = false;
    public boolean isOpenNode = true;
    public boolean isOpenH5 = true;
    public boolean tenthRevision = true;
    public WareBusinessProductRefresh mRefreshData = null;
    public MutableLiveData<PreferentialGuideEntity> PreferentialGuide = new MutableLiveData<>();

    public String getBusinessJPrice() {
        Double valueOf;
        try {
            WarePriceInfo warePriceInfo = this.priceInfo;
            return (warePriceInfo == null || (valueOf = Double.valueOf(warePriceInfo.jprice)) == null || valueOf.doubleValue() <= 0.0d) ? StringUtil.no_price : new DecimalFormat("0.00").format(valueOf);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("WareBusinessData", e2);
                return StringUtil.no_price;
            }
            return StringUtil.no_price;
        }
    }

    public String getBusinessSkuId() {
        WareBasicInfo wareBasicInfo = this.wareInfo;
        return wareBasicInfo != null ? wareBasicInfo.skuId : "";
    }

    public List<WareBusinessDJStore> getDJStoreInfo() {
        List<WareBusinessDJStore> list;
        WareBusinessDJStoreEntity wareBusinessDJStoreEntity = this.storeFloorInfo;
        if (wareBusinessDJStoreEntity == null || (list = wareBusinessDJStoreEntity.storeList) == null || list.isEmpty()) {
            return null;
        }
        return this.storeFloorInfo.storeList;
    }

    public String getJoinBuyPrice() {
        WareBusinessCollageJoinBuyInfoEntity wareBusinessCollageJoinBuyInfoEntity = this.joinBuyInfo;
        return wareBusinessCollageJoinBuyInfoEntity != null ? wareBusinessCollageJoinBuyInfoEntity.bpPrice : "";
    }

    public String getLocShopId() {
        LocShopInfo locShopInfo = this.locShopInfo;
        return (locShopInfo == null || TextUtils.isEmpty(locShopInfo.locShopId)) ? "" : this.locShopInfo.locShopId;
    }

    public boolean getPdjServiceSpecialFlag() {
        PdjServiceSpecialBtnInfo pdjServiceSpecialBtnInfo = this.jServiceSpecialBtnInfo;
        return (pdjServiceSpecialBtnInfo == null || !TextUtils.equals(pdjServiceSpecialBtnInfo.buttonFlag, "1") || TextUtils.isEmpty(this.jServiceSpecialBtnInfo.buttonBizName)) ? false : true;
    }

    public int getStoreType() {
        PdAhStoreEntity pdAhStoreEntity = this.daojiaStoreInfo;
        if (pdAhStoreEntity != null) {
            return pdAhStoreEntity.storeType;
        }
        return -1;
    }

    public boolean isBusinessJX() {
        WarePropertyInfo warePropertyInfo = this.property;
        return warePropertyInfo != null && warePropertyInfo.isJx;
    }

    public boolean isBusinessOp() {
        WarePropertyInfo warePropertyInfo = this.property;
        return warePropertyInfo != null && warePropertyInfo.isOp;
    }

    public boolean isFreshTemplate() {
        return TextUtils.equals(this.templateType, "fresh");
    }

    public boolean isJoinBuyInfo() {
        WareBusinessCollageJoinBuyInfoEntity wareBusinessCollageJoinBuyInfoEntity = this.joinBuyInfo;
        return wareBusinessCollageJoinBuyInfoEntity != null && TextUtils.equals(wareBusinessCollageJoinBuyInfoEntity.groupBuyType, "1");
    }

    public boolean isNewBusinessJX() {
        WarePropertyInfo warePropertyInfo;
        return (this.abTest800 || (warePropertyInfo = this.property) == null || !warePropertyInfo.isJx) ? false : true;
    }

    public boolean isNormalPrice(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(this.priceLabel)) {
            return false;
        }
        return str.startsWith(this.priceLabel);
    }

    public boolean isPinTuan() {
        WareBusinessCollageJoinBuyInfoEntity wareBusinessCollageJoinBuyInfoEntity = this.joinBuyInfo;
        return wareBusinessCollageJoinBuyInfoEntity != null && TextUtils.equals(wareBusinessCollageJoinBuyInfoEntity.groupBuyType, "2");
    }

    public boolean isPrescription() {
        com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessDrugEntity wareBusinessDrugEntity = this.drugInfo;
        return wareBusinessDrugEntity != null && wareBusinessDrugEntity.drugBusinessId == 0;
    }

    public boolean isPrescriptionPOP() {
        WarePropertyInfo warePropertyInfo;
        return isPrescription() && (warePropertyInfo = this.property) != null && warePropertyInfo.isPop;
    }

    public boolean isShowJxNowBuyBtn() {
        WareBusinessJxInfoEntity wareBusinessJxInfoEntity = this.jxInfo;
        return wareBusinessJxInfoEntity != null && wareBusinessJxInfoEntity.showImmediately;
    }

    public boolean isSmoothBusiness() {
        PdBuyByMEntity pdBuyByMEntity = this.addCart;
        if (pdBuyByMEntity != null) {
            return pdBuyByMEntity.check();
        }
        return false;
    }

    public boolean isUseBatchReceiveCoupon() {
        WarePropertyInfo warePropertyInfo = this.property;
        return warePropertyInfo != null && warePropertyInfo.batchReceiveCoupon;
    }

    public boolean isUseReceiveNCoupon() {
        WarePropertyInfo warePropertyInfo = this.property;
        return warePropertyInfo != null && warePropertyInfo.newReceiveCoupon;
    }

    public String pinTuanActvityId() {
        WareBusinessCollageJoinBuyInfoEntity wareBusinessCollageJoinBuyInfoEntity = this.joinBuyInfo;
        if (wareBusinessCollageJoinBuyInfoEntity != null) {
            return wareBusinessCollageJoinBuyInfoEntity.activityId;
        }
        return null;
    }
}
