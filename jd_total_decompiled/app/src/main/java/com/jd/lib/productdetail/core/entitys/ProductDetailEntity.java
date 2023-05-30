package com.jd.lib.productdetail.core.entitys;

import android.os.Bundle;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.business.entity.BusinessFloorEntity;
import com.jd.lib.productdetail.core.business.entity.PDWareBusinessEntity;
import com.jd.lib.productdetail.core.entitys.buttoninfo.PDBottomBtn;
import com.jd.lib.productdetail.core.entitys.buttoninfo.PDBottomInfo;
import com.jd.lib.productdetail.core.entitys.caro2o.PDCarEntity;
import com.jd.lib.productdetail.core.entitys.caro2o.PDCarItemEntity;
import com.jd.lib.productdetail.core.entitys.caro2o.PDCarShopEntity;
import com.jd.lib.productdetail.core.entitys.coupon.PDCouponCellEntity;
import com.jd.lib.productdetail.core.entitys.delivery.PDDeliveryInstallEntity;
import com.jd.lib.productdetail.core.entitys.delivery.PDDeliveryOptionEntity;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdCommentInfo;
import com.jd.lib.productdetail.core.entitys.detailcomment.PdQuestionInfo;
import com.jd.lib.productdetail.core.entitys.discount.PdDiscountLayerEntity;
import com.jd.lib.productdetail.core.entitys.ecard.PDECardInfoEntity;
import com.jd.lib.productdetail.core.entitys.fansprice.PdFansPriceEntity;
import com.jd.lib.productdetail.core.entitys.installment.PDInstallmentInfoEntity;
import com.jd.lib.productdetail.core.entitys.loc.LocInfo;
import com.jd.lib.productdetail.core.entitys.loc.PDLocBuyStepEntity;
import com.jd.lib.productdetail.core.entitys.plusmember.PDPlusFreightEntity;
import com.jd.lib.productdetail.core.entitys.promotion.PdStyleSelectSuitItem;
import com.jd.lib.productdetail.core.entitys.regularbuy.PDRegularBuyEntity;
import com.jd.lib.productdetail.core.entitys.regularbuy.PDRegularBuyFrequencyEntity;
import com.jd.lib.productdetail.core.entitys.seckillcertify.PDSeckillCertifyEntity;
import com.jd.lib.productdetail.core.entitys.seckillcertify.PDSeckillTipEntity;
import com.jd.lib.productdetail.core.entitys.shop.PdShopChatInfoEntity;
import com.jd.lib.productdetail.core.entitys.shop.PdShopDdDrag;
import com.jd.lib.productdetail.core.entitys.shop.PdShopDdToastEntity;
import com.jd.lib.productdetail.core.entitys.shop.PdShopEntity;
import com.jd.lib.productdetail.core.entitys.shop.PdShopHotLineEntity;
import com.jd.lib.productdetail.core.entitys.shop.PdShopInfoEntity;
import com.jd.lib.productdetail.core.entitys.shop.PdShopServiceEntity;
import com.jd.lib.productdetail.core.entitys.sizehelper.PDSizeHelperEntity;
import com.jd.lib.productdetail.core.entitys.skin.PDSkinDyInfoEntity;
import com.jd.lib.productdetail.core.entitys.temp.FloorTemplate;
import com.jd.lib.productdetail.core.entitys.temp.FloorTemplateEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.AddFamilyListData;
import com.jd.lib.productdetail.core.entitys.warebusiness.AppStaticInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.BrandMemberInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.BusinessPaiPaiSmallImgStockInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.BusinessPromotionPoint;
import com.jd.lib.productdetail.core.entitys.warebusiness.CustomizeInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.CustomizeServicesEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.Gift3C;
import com.jd.lib.productdetail.core.entitys.warebusiness.GiftPool3C;
import com.jd.lib.productdetail.core.entitys.warebusiness.LayerFlagExperiment;
import com.jd.lib.productdetail.core.entitys.warebusiness.PDWebCastEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdAcsEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdBannerMdEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdDdFatigueMechanism;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdGiftShopEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdLiveFloatEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdPayGuidTips;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdShortFillOrderEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdStyleDialogEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdTopWhiteBarEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdWhiteBarForStyleInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PdWhiteBarForStyleItemInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.SecondPriceEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.SpotPreSaleMap;
import com.jd.lib.productdetail.core.entitys.warebusiness.SurveyData;
import com.jd.lib.productdetail.core.entitys.warebusiness.TopTabAnchor;
import com.jd.lib.productdetail.core.entitys.warebusiness.TreayNewInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.Ware3cServerEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.Ware3cServerNewEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareAppletShare;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBasicInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessABTestInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessAdWordEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessBenefitBeltEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessBigPlus;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessBigPlusJumpPlayer;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessCarAllInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessCarTextEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessCollageJoinBuyInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessCollageJoinEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessData;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessDefaultAddrEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessFastMailEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessFeeInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessFreshBCMatchInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessFsPriceEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessFurnitureGroupEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessFutureGuideAdvance;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessGiftInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessGiftPools3C;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessHealthAppoint;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessHwShareInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessIntroAlbumEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessJdServerProduct;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessJingPriceEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessJumpInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessJxInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessOnebox;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPaiPaiReplacement;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPlusForBuyMt;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPlusListEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPointInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPriceIconEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessProductFeeInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessPromotionNoticeEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessRechargeTypeInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessServiceIconEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessServiceIconInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessSkuPropertyItem;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessSkuPropertyList;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessSmartWare;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessSoldOverSea;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessSpecialInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessStyleEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessTurnToH5;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessWareImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessYanBaoGroupEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareEventParam;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareEyeSightInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareFlashInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareFurnitureInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareJdServerPlusEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareMiaoShaInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WarePlusInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WarePriceInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WarePromotionInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WarePropertyInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WarePropertyTipsDetailEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareSamInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareStockEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareYanBaoCoupon;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareYanBaoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareYuShouAdvanceBuyMap;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareYuShouInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareYuYueInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareYureInfo;
import com.jd.lib.productdetail.core.events.PDApiEvent;
import com.jd.lib.productdetail.core.floor.FloorBussinessName;
import com.jd.lib.productdetail.core.floor.FloorThemeEnum;
import com.jd.lib.productdetail.core.utils.PDCarO2oGiftUtils;
import com.jd.lib.productdetail.core.utils.PDManager;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessDrugEntity;
import com.jingdong.common.controller.SubShoppingBaseController;
import com.jingdong.common.entity.Image;
import com.jingdong.common.entity.ProductDetailEntityBase;
import com.jingdong.common.entity.ProductDetailPrice;
import com.jingdong.common.entity.ProductToJsNowBuyEntity;
import com.jingdong.common.entity.cart.NewGiftItem;
import com.jingdong.common.entity.cart.NewGiftPoolItem;
import com.jingdong.common.entity.productdetail.PDSopSkuInfoEntity;
import com.jingdong.common.entity.productdetail.PDStyleFilterEntity;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
import com.jingdong.pdj.libcore.isv.entity.ProcessServeAttrInfo;
import com.jingdong.sdk.platform.PlatformHelper;
import com.jingdong.sdk.platform.business.personal.R2;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jpbury.t;

/* loaded from: classes15.dex */
public class ProductDetailEntity extends ProductDetailEntityBase implements PDConstant {
    public static final String KEY_PD_PLUS_WEARY_DAY = "pd_plus_weary_day";
    public static final String KEY_PD_PLUS_WEARY_TIMES = "pd_plus_weary_times";
    private static final String TAG = "ProductDetailEntity";
    public String adBackUrl4Activity;
    public WareBusinessAdWordEntity adWordEntity;
    public JDJSONObject allwareJsonObject;
    public int appleCare;
    public String authorId;
    public boolean avoidLive;
    public String bbtf;
    public int btnFromCarOrBuy;
    public int buyMaxNum;
    public String bybt;
    public String carModelId;
    public String channelId;
    public String daoDianStoreId;
    public long dataTime;
    public WareBusinessDefaultAddrEntity defaultAddr;
    public String defaultImage;
    public String defaultName;
    public String defaultPrice;
    public PDDeliveryInstallEntity deliveryInstallEntity;
    public PdDiscountLayerEntity discountLayerEntity;
    public String floor3cTabType;
    public String flt;
    public String from;
    public ArrayList<String> furnitureIdsSelect;
    public long getPdDataTime;
    public ArrayList<String> giftPoolIdsSelect;
    public ArrayList<NewGiftItem> giftPoolSelect;
    public ArrayList<GiftPool3C> giftPoolsList;
    public boolean hasAdword;
    public String inspectSkuId;
    public boolean isClickMemberFloor;
    public boolean isClickXpTrailFloor;
    public boolean isFromOpenApp;
    public boolean isLocalRefresh;
    public boolean isOverseaWhiteBar;
    public String isShadowSku;
    public boolean isShowShopNameB;
    public boolean isSop;
    public boolean isStatusBarTintEnable;
    public boolean isTopFrame;
    public boolean isUseUnit;
    public Boolean jpsCheckStatus;
    public ArrayList<String> jsServerPlusIdsSelect;
    public String jumpOrderTypeCode;
    public String liveId;
    public ArrayList<PdQuestionInfo> mAskQuesInfos;
    public boolean mAutoAddCart;
    public PDCarGiftEntity mCarGiftEntity;
    public WareBusinessCollageJoinEntity mCollageJoinEntity;
    public PdCommentInfo mCommentInfo;
    public String mCurModelId;
    public String mCurrentSku;
    public String mDeliveryPrice;
    public PdDigitalBottomInfo mDigitalBottomInfo;
    public PdExpoData mExpoData;
    public PdFansPriceEntity mFansPrice;
    public String mFeedTouchstone_expids;
    public boolean mHaveHuaWeiShare;
    public boolean mIsInTradeInStep;
    public boolean mIsStyleChangeSku;
    public String mJingJiaIcon;
    public String mJsonString;
    public String mLocArea;
    public PDLocBuyStepEntity mLocBuyStepEntity;
    public String mLocChannel;
    public String mLocShopId;
    public String mManageKey;
    public MaxSales mMaxSales;
    public OTCInfo mOTCInfo;
    public String mPaiPaiInspectIds;
    public int mRegularEachNum;
    public PDRegularBuyFrequencyEntity mRegularFrequency;
    public int mRegularPhaseNum;
    public long mResponseTime;
    public PdStyleSelectSuitItem mSelectSuitItem;
    public PdShortFillOrderEntity mShortFillOrderEntity;
    public PDSkinDyInfoEntity mSkinDyInfo;
    private String mSkuTag;
    public String mStyleSelect;
    public String mStyleSelectV10;
    ArrayList<BaseTemplateEntity> mStyleViewEntity;
    public boolean mTextLarge;
    public int mTopWhiteBarPlanid;
    public ProductToJsNowBuyEntity mType4Entity;
    public ProductToJsNowBuyEntity mType5Entity;
    public WareBusinessData mWareBusinessData;
    public List<WareBusinessServiceIconEntity> mWareBusinessDetailServiceIcons;
    public WareBusinessServiceIconInfoEntity mWareBusinessServiceInfo;
    public MainPicDpgInfo mainPicDpgInfo;
    public String nativeBeforeSkuId;
    public String oldSkuId;
    public OneboxParams oneboxParams;
    public PdOneboxProductListInfo oneboxProductListInfo;
    private PDSmartRecommendParam pdSmartRecommendParam;
    public String personas;
    public ProcessServeAttrInfo processServeAttrInfo;
    public String pt;
    public List<PDRecommendEntity> recommendAccaList;
    public List<PDRecommendEntity> recommendHourPurchaseList;
    public List<PDRecommendEntity> recommendLikeList;
    public List<PDRecommendEntity> recommendOtherLikeList;
    public String recommendRankJumUrl;
    public List<PDRecommendEntity> recommendRankList;
    public List<PDRecommendEntity> recommendSameTypeList;
    public int recommendSameTypeListCount;
    public String searchParam;
    public PdWhiteBarForStyleItemInfoEntity selectItemInfo;
    public WareBusinessJdServerProduct selectServerProduct;
    public int selfDelivery;
    public String serviceInfoId;
    public String shareImage;
    public JDJSONObject skuDetailJson;
    public JDJSONObject skuDyInfoJson;
    public String storeId;
    public String supperRoomPromo;
    public FloorTemplateEntity templateEntity;
    public String templateType;
    public String toTab;
    public int transitionViewHeight;
    public int transitionViewLeft;
    public String transitionViewMarkId;
    public int transitionViewTop;
    public int transitionViewWidth;
    public ArrayList<String> ware3cServerIdsSelect;
    public String wareSourceNative;
    public ArrayList<String> yanbaoIdsSelect;
    public String test = "test";
    public boolean isFromHm = false;
    public boolean isForeground = true;
    public boolean isFromCar = false;
    public int fromType = 0;
    public boolean isLoaded = false;
    public boolean isUploadPv = false;
    public boolean isShowHealthBar = false;
    public BasicInfo mBasicInfo = new BasicInfo();
    public List<Image> imageList = new ArrayList();
    public int suitABTest = 0;
    public boolean isRecommenExpo = false;
    public boolean isRelateRecommendExpo = false;
    public boolean isRecommendUploadScrollY = false;
    public boolean isRecommendHourBuyUploadScrollY = false;
    public boolean isRankExpo = false;
    public boolean isAccessExpo = false;
    public boolean hasRankSale = false;
    public String jxGroupBuyFloorAndBar = "";
    public boolean lowestBuy = false;
    public int lowestBuyNum = 1;
    public boolean isRNCertify = false;
    public boolean hideSelectedFloor = true;
    public boolean isNoCarMatchState = false;
    public boolean hasShowBarrage = false;
    public Boolean isNeedPopCoudanGuide = Boolean.FALSE;
    public boolean isperformanceOpen = false;
    public String paipai_cache_str = null;
    public int themeStyle = -1;
    public boolean isHaveWhiteBarData = false;
    public boolean isHasSearchFloor = false;
    public boolean isHasActiveFloor = false;
    public boolean isHasRecommendFloor = false;
    public boolean isRefreshPdFromPlusH5 = false;
    public boolean isCartToastShow = false;
    public boolean isSecondWindowShow = false;
    public PdStyleDialogEntity mStyleDialogEntity = new PdStyleDialogEntity();
    public boolean isUploadTopSearch = false;
    public boolean mShowBottomCanTuan = true;
    public boolean initBottomBtn = false;
    public boolean mYhdBannerClick = false;
    public String mNeedShowFloorMid = null;
    public PdTempData mTempData = new PdTempData();
    public PdInputParams mInputParams = new PdInputParams();

    /* loaded from: classes15.dex */
    public class BasicInfo {
        public String brandId;
        public String[] categoryIds;
        public boolean display;
        public boolean isOldAddress;
        public boolean isRegisterUser;
        public String mLink;
        public String showClick;
        public int skuProperty;
        public String strCategoryIds;
        public String type;
        public String wareId = "";

        public BasicInfo() {
            ProductDetailEntity.this = r1;
        }
    }

    /* loaded from: classes15.dex */
    public class MaxSales {
        public String head;
        public String title;

        public MaxSales(JDJSONObject jDJSONObject) {
            ProductDetailEntity.this = r1;
            if (jDJSONObject != null) {
                this.head = jDJSONObject.optString(DataCompassUtils.MODULE_TYPE_HEAD);
                this.title = jDJSONObject.optString("title");
            }
        }
    }

    /* loaded from: classes15.dex */
    public class OTCInfo {
        public String OTCIcon;
        public String instruction;
        public String instructionText;
        public String shareLink;

        public OTCInfo(JDJSONObject jDJSONObject) {
            ProductDetailEntity.this = r1;
            this.instruction = jDJSONObject.optString("instruction");
            this.instructionText = jDJSONObject.optString("instructionText");
            this.shareLink = jDJSONObject.optString(JshopConst.JSKEY_SHARE_URL);
            this.OTCIcon = jDJSONObject.optString("OTCIcon");
        }

        public boolean check() {
            return (TextUtils.isEmpty(this.instruction) || TextUtils.isEmpty(this.instructionText)) ? false : true;
        }
    }

    /* loaded from: classes15.dex */
    public static class OneboxParams {
        public String oneboxKeyword;
        public String oneboxSource;
        public HashMap<String, String> passThroughMap;

        public OneboxParams(String str, String str2, String str3) {
            this.oneboxSource = str;
            this.oneboxKeyword = str2;
            try {
                this.passThroughMap = (HashMap) JDJSON.parseObject(str3, HashMap.class);
            } catch (Exception unused) {
            }
        }

        public static OneboxParams createFromBundle(Bundle bundle) {
            if (bundle != null) {
                String string = bundle.getString("oneboxSource");
                String string2 = bundle.getString("oneboxKeyword");
                String string3 = bundle.getString("passThroughMap");
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
                    return null;
                }
                return new OneboxParams(string, string2, string3);
            }
            return null;
        }
    }

    /* loaded from: classes15.dex */
    public static class PdInputParams {
        public String addCartTime;
        public String secKillParams;
    }

    public ProductDetailEntity() {
    }

    private void addWearyCount() {
        String string = SharedPreferencesUtil.getString("pd_plus_weary_day", "0");
        int i2 = SharedPreferencesUtil.getInt("pd_plus_weary_times", 0);
        int i3 = 1;
        if (PDUtils.isToday(string)) {
            i3 = 1 + i2;
        } else {
            SharedPreferencesUtil.putString("pd_plus_weary_day", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
        }
        SharedPreferencesUtil.putInt("pd_plus_weary_times", Math.min(i3, 100));
    }

    public static String appModeToString(String str) {
        if (str != null) {
            str.hashCode();
            char c2 = '\uffff';
            switch (str.hashCode()) {
                case 48:
                    if (str.equals("0")) {
                        c2 = 0;
                        break;
                    }
                    break;
                case 49:
                    if (str.equals("1")) {
                        c2 = 1;
                        break;
                    }
                    break;
                case 50:
                    if (str.equals("2")) {
                        c2 = 2;
                        break;
                    }
                    break;
            }
            switch (c2) {
                case 0:
                default:
                    return "MODE_NORMAL";
                case 1:
                    return "MODE_ELDER";
                case 2:
                    return "MODE_B";
            }
        }
        return null;
    }

    private void copyBusinessFloorData(BaseTemplateEntity baseTemplateEntity) {
        PdTopWhiteBarEntity pdTopWhiteBarEntity;
        PDLocBuyStepEntity pDLocBuyStepEntity;
        WareBusinessJingPriceEntity wareBusinessJingPriceEntity;
        WareBusinessPriceIconEntity wareBusinessPriceIconEntity;
        if (TextUtils.equals(baseTemplateEntity.mId, "bpJPrice") && (wareBusinessJingPriceEntity = (WareBusinessJingPriceEntity) JDJSON.parseObject(JDJSON.toJSONString(baseTemplateEntity.mData), WareBusinessJingPriceEntity.class)) != null && (wareBusinessPriceIconEntity = wareBusinessJingPriceEntity.priceIcon) != null) {
            setPriceBusiness(wareBusinessPriceIconEntity.jiangJia);
        }
        if (TextUtils.equals(baseTemplateEntity.mId, "bpLOCGuide") && (pDLocBuyStepEntity = (PDLocBuyStepEntity) JDJSON.parseObject(JDJSON.toJSONString(baseTemplateEntity.mData), PDLocBuyStepEntity.class)) != null) {
            this.mLocBuyStepEntity = pDLocBuyStepEntity;
        }
        if ((TextUtils.equals(baseTemplateEntity.mId, "bpTopWhiteBar") || TextUtils.equals(baseTemplateEntity.mId, FloorBussinessName.FB_BUSINESS_WHITE_BAR)) && (pdTopWhiteBarEntity = (PdTopWhiteBarEntity) JDJSON.parseObject(JDJSON.toJSONString(baseTemplateEntity.mData), PdTopWhiteBarEntity.class)) != null) {
            this.mTopWhiteBarPlanid = pdTopWhiteBarEntity.planId;
        }
        if (TextUtils.equals(baseTemplateEntity.mId, "bpJoinbj")) {
            this.mCollageJoinEntity = (WareBusinessCollageJoinEntity) JDJSON.parseObject(JDJSON.toJSONString(baseTemplateEntity.mData), WareBusinessCollageJoinEntity.class);
        }
    }

    private void dealStyleFloorView() {
        JDJSONObject jDJSONObject;
        this.templateType = "0";
        this.mStyleViewEntity = null;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (jDJSONObject = wareBusinessData.popupTemplate) == null) {
            return;
        }
        this.templateType = jDJSONObject.optString("templateType", "0");
        JDJSONArray jSONArray = jDJSONObject.getJSONArray("floors");
        if (jSONArray != null) {
            int size = jSONArray.size();
            this.mStyleViewEntity = new ArrayList<>(size);
            for (int i2 = 0; i2 < size; i2++) {
                JDJSONObject optJSONObject = jSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    this.mStyleViewEntity.add(new BusinessFloorEntity(optJSONObject));
                }
            }
        }
        this.mWareBusinessData.popupTemplate = null;
    }

    private static boolean formatBoolean(Boolean bool) {
        return bool != null && bool.booleanValue();
    }

    private static double formatDouble(Double d) {
        if (d == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    private static int formatInteger(Integer num) {
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    private static long formatLong(Long l2) {
        if (l2 == null) {
            return 0L;
        }
        return l2.longValue();
    }

    private String getAVParam() {
        WareBusinessPointInfo wareBusinessPointInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessPointInfo = wareBusinessData.pointInfo) == null || !wareBusinessPointInfo.isShowAr || TextUtils.isEmpty(wareBusinessPointInfo.ARType)) ? "0" : this.mWareBusinessData.pointInfo.ARType;
    }

    private String getDiscountPrice() {
        WareBusinessPointInfo wareBusinessPointInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessPointInfo = wareBusinessData.pointInfo) == null || TextUtils.isEmpty(wareBusinessPointInfo.discountPrice)) ? "0" : this.mWareBusinessData.pointInfo.discountPrice;
    }

    private String getFansPriceParam() {
        PdFansPriceEntity pdFansPriceEntity = this.mFansPrice;
        return pdFansPriceEntity != null ? pdFansPriceEntity.isFocus ? "1" : "2" : "0";
    }

    private ProcessServeAttrInfo getHourlyServeAttribute() {
        this.processServeAttrInfo = null;
        if (getStyleFloors() != null && getStyleFloors().size() > 0) {
            for (int i2 = 0; i2 < getStyleFloors().size(); i2++) {
                BaseTemplateEntity baseTemplateEntity = getStyleFloors().get(i2);
                if (baseTemplateEntity != null && TextUtils.equals(baseTemplateEntity.mId, FloorBussinessName.FB_BUSINESS_HOURLY_SERVE)) {
                    Object obj = baseTemplateEntity.mData;
                    if (obj instanceof JDJSONObject) {
                        ProcessServeAttrInfo processServeAttrInfo = (ProcessServeAttrInfo) JDJSON.optParseObject(((JDJSONObject) obj).toJSONString(), ProcessServeAttrInfo.class);
                        this.processServeAttrInfo = processServeAttrInfo;
                        return processServeAttrInfo;
                    }
                }
            }
        }
        return this.processServeAttrInfo;
    }

    private int getIsOpType() {
        WareBusinessPointInfo wareBusinessPointInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessPointInfo = wareBusinessData.pointInfo) == null) {
            return 0;
        }
        return wareBusinessPointInfo.isOpType;
    }

    private String getJoinBuyParam() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        int i2 = 99;
        if (wareBusinessData != null && wareBusinessData.joinBuyInfo != null && wareBusinessData.isJoinBuyInfo()) {
            i2 = this.mWareBusinessData.joinBuyInfo.joinType;
        }
        return String.valueOf(i2);
    }

    private String getMiaoShaParam() {
        WareMiaoShaInfo wareMiaoShaInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareMiaoShaInfo = wareBusinessData.miaoshaInfo) == null) ? "0" : wareMiaoShaInfo.seckillType;
    }

    private String getPinTuanParam() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return String.valueOf((wareBusinessData == null || wareBusinessData.joinBuyInfo == null || !wareBusinessData.isPinTuan()) ? 0 : this.mWareBusinessData.joinBuyInfo.joinType);
    }

    private int getPlusProduct() {
        WareBusinessPlusForBuyMt wareBusinessPlusForBuyMt;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null && (wareBusinessPlusForBuyMt = wareBusinessData.makeMoreTime) != null) {
            if (wareBusinessPlusForBuyMt.plusShopFlag) {
                return 2;
            }
            if (isPlusSwaying() && isYuyue()) {
                return 4;
            }
            if (this.mWareBusinessData.makeMoreTime.makeMoreTimeFlag && isYuyue()) {
                return 3;
            }
            if (this.mWareBusinessData.makeMoreTime.makeMoreTimeFlag) {
                return 1;
            }
        }
        return 0;
    }

    private String getSmartWareNotAbTag() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || !wareBusinessData.smartWareNotAB) ? "0" : "1";
    }

    private String getSmartWareTag() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || TextUtils.isEmpty(wareBusinessData.smartWareTag)) ? "-100" : this.mWareBusinessData.smartWareTag;
    }

    public static long getTimeValue(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    private int getTopMainFloorAbTest() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null) {
            return 0;
        }
        return wareBusinessABTestInfo.magicHeadPicFlag;
    }

    private String getTrustworthy() {
        WareBusinessPointInfo wareBusinessPointInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessPointInfo = wareBusinessData.pointInfo) == null || TextUtils.isEmpty(wareBusinessPointInfo.trustworthy)) ? "0" : this.mWareBusinessData.pointInfo.trustworthy;
    }

    private boolean isFloorShowed(String str) {
        FloorTemplate templateById;
        FloorTemplateEntity floorTemplateEntity = this.templateEntity;
        return (floorTemplateEntity == null || (templateById = floorTemplateEntity.getTemplateById(str)) == null || !templateById.isAddToFloor()) ? false : true;
    }

    private String isJZ() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !TextUtils.equals(warePropertyInfo.imgToWareNameFrom, "JZ")) ? "0" : "1";
    }

    private boolean isOverseaPurchase() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || (!TextUtils.equals(warePropertyInfo.isOverseaPurchase, "1") && !TextUtils.equals(this.mWareBusinessData.property.isOverseaPurchase, "2") && !TextUtils.equals(this.mWareBusinessData.property.isOverseaPurchase, "3") && !TextUtils.equals(this.mWareBusinessData.property.isOverseaPurchase, "4") && !TextUtils.equals(this.mWareBusinessData.property.isOverseaPurchase, "5"))) ? false : true;
    }

    private String isPlus20Flag() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.plus20Flag) ? "0" : "1";
    }

    private String isScfSku() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || !wareBusinessData.isScf) ? "0" : "1";
    }

    private String isThreeSuperFlag() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.threeSuperFlag) ? "0" : "1";
    }

    private String isTopLife() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !TextUtils.equals(warePropertyInfo.imgToWareNameFrom, "TOPLIFE")) ? "0" : "1";
    }

    private String isV10Flag() {
        return isTenthRevision() ? "1" : "0";
    }

    private String isXSD() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !TextUtils.equals(warePropertyInfo.imgToWareNameFrom, "XSD")) ? "0" : "1";
    }

    private String jiangjia() {
        return !TextUtils.isEmpty(this.mJingJiaIcon) ? "1" : "0";
    }

    private void resetRecommendData() {
        this.recommendLikeList = null;
        this.recommendHourPurchaseList = null;
        this.isRecommenExpo = false;
        this.isRankExpo = false;
        this.isAccessExpo = false;
        this.isRelateRecommendExpo = false;
        this.recommendOtherLikeList = null;
        this.isRecommendUploadScrollY = false;
        this.isRecommendHourBuyUploadScrollY = false;
        this.hasRankSale = false;
    }

    private void updateGiftPoolSelect(ArrayList<NewGiftItem> arrayList) {
        ArrayList<NewGiftItem> arrayList2 = new ArrayList<>();
        Iterator<NewGiftItem> it = arrayList.iterator();
        while (it.hasNext()) {
            NewGiftItem next = it.next();
            if (next.isSelect()) {
                arrayList2.add(next);
            }
        }
        this.giftPoolSelect = arrayList2;
    }

    public boolean addrUpAB() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.addrUpAB) ? false : true;
    }

    public String buildAddCarParam(String str, String str2) {
        WarePropertyInfo warePropertyInfo;
        String selectServerSkuid = PDUtils.getSelectServerSkuid(this);
        StringBuilder sb = new StringBuilder();
        sb.append(this.skuId);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(str);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(this.lowestBuy ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(haveSamEntity() ? isSamMember() ? "2" : "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getOTCInfo());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getPlusPriceType());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getMaxSales());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getOverSeaEventParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(!TextUtils.isEmpty(getTokenPrice()) ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(!TextUtils.isEmpty(getStockNum()) ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isEyeSightDiscount());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(selectServerSkuid);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(str2);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(TextUtils.isEmpty(getDaojiaStoreId()) ? "-100" : getDaojiaStoreId());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getHealthPrescriptionDrugInfo() != null ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        sb.append((wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.noStockCartFlag) ? "0" : "1");
        return sb.toString();
    }

    public String buildPageParams() {
        String str;
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessABTestInfo wareBusinessABTestInfo2;
        WarePriceInfo warePriceInfo;
        BusinessPromotionPoint businessPromotionPoint;
        BusinessPromotionPoint businessPromotionPoint2;
        StringBuilder sb = new StringBuilder();
        if (isYuShou()) {
            str = TextUtils.equals(getYuShouladder(), "1") ? "1" : "2";
        } else {
            str = isYuyue() ? "3" : "0";
        }
        sb.append(str);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getFlashPurchaseType());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(this.lowestBuy ? 1 : 0);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(haveSamEntity() ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getDrugsParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getDeliveryInstallEntity() != null ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isCustomSize() ? getCustomize() : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getPlusPriceType());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isgroupGoods() ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        sb.append((wareBusinessData == null || (businessPromotionPoint2 = wareBusinessData.promotionPoint) == null) ? "0" : businessPromotionPoint2.promotionSkinPoint);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getMiaoShaState());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getRegularBuyType());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isStaging() ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isFxyl() ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getAVParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(!TextUtils.isEmpty(getTokenPrice()) ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getPaperBook());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getThreeDSwitch());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isShowBarrage() ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append("0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        WareBusinessData wareBusinessData2 = this.mWareBusinessData;
        sb.append((wareBusinessData2 == null || (businessPromotionPoint = wareBusinessData2.promotionPoint) == null) ? "0" : businessPromotionPoint.promotionTagPoint);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getFansPriceParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(jiangjia());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getJoinBuyParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        WareBusinessData wareBusinessData3 = this.mWareBusinessData;
        sb.append((wareBusinessData3 == null || (warePriceInfo = wareBusinessData3.priceInfo) == null || warePriceInfo.userNewPrice == null) ? "0" : "1");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        WareBusinessData wareBusinessData4 = this.mWareBusinessData;
        sb.append((wareBusinessData4 == null || (wareBusinessABTestInfo2 = wareBusinessData4.abTestInfo) == null || !wareBusinessABTestInfo2.newuser) ? "0" : "1");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        WareBusinessData wareBusinessData5 = this.mWareBusinessData;
        sb.append((wareBusinessData5 == null || (wareBusinessABTestInfo = wareBusinessData5.abTestInfo) == null || !wareBusinessABTestInfo.newuserFreeAb) ? "0" : "1");
        return sb.toString();
    }

    public String buildStyleAddCarParam() {
        WarePropertyInfo warePropertyInfo;
        StringBuilder sb = new StringBuilder();
        String str = "0";
        sb.append(this.lowestBuy ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        PDDeliveryInstallEntity deliveryInstallEntity = getDeliveryInstallEntity();
        String str2 = DYConstants.DY_NULL_STR;
        sb.append(deliveryInstallEntity != null ? this.deliveryId : DYConstants.DY_NULL_STR);
        sb.append("-");
        if (getDeliveryInstallEntity() != null) {
            str2 = this.mDeliveryPrice;
        }
        sb.append(str2);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(haveSamEntity() ? isSamMember() ? "2" : "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getOTCInfo());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getPlusPriceType());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getMaxSales());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getOverSeaEventParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(!TextUtils.isEmpty(getTokenPrice()) ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(!TextUtils.isEmpty(getStockNum()) ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isEyeSightDiscount());
        sb.append(PDUtils.getSelectServerSkuid(this));
        StringBuilder selectWhiteBarToMa = selectWhiteBarToMa(sb);
        WareBusinessPaiPaiReplacement wareBusinessPaiPaiReplacement = getWareBusinessPaiPaiReplacement();
        if (wareBusinessPaiPaiReplacement != null && TextUtils.equals("3cyjhx", wareBusinessPaiPaiReplacement.jgBuryPoint)) {
            selectWhiteBarToMa.append(CartConstant.KEY_YB_INFO_LINK);
            selectWhiteBarToMa.append("1");
        } else if (TextUtils.equals("3cyjhx", getAddCartBusinessName())) {
            selectWhiteBarToMa.append(CartConstant.KEY_YB_INFO_LINK);
            selectWhiteBarToMa.append("1");
        } else {
            selectWhiteBarToMa.append(CartConstant.KEY_YB_INFO_LINK);
            selectWhiteBarToMa.append("0");
        }
        selectWhiteBarToMa.append(CartConstant.KEY_YB_INFO_LINK);
        selectWhiteBarToMa.append(this.mSelectSuitItem != null ? "1" : "0");
        selectWhiteBarToMa.append(CartConstant.KEY_YB_INFO_LINK);
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null && (warePropertyInfo = wareBusinessData.property) != null && warePropertyInfo.noStockCartFlag) {
            str = "1";
        }
        selectWhiteBarToMa.append(str);
        return selectWhiteBarToMa.toString();
    }

    public boolean businessIsShowSpecialFloor(FloorTemplate floorTemplate) {
        WareBusinessData wareBusinessData = floorTemplate.mSkuBaseData;
        if (wareBusinessData != null) {
            WareBusinessSpecialInfo wareBusinessSpecialInfo = wareBusinessData.specialInfo;
            WareMiaoShaInfo wareMiaoShaInfo = wareBusinessData.miaoshaInfo;
            PDSeckillTipEntity pDSeckillTipEntity = wareBusinessData.koInfo;
            WarePromotionInfo warePromotionInfo = wareBusinessData.promotionInfo;
            WareBusinessPromotionNoticeEntity wareBusinessPromotionNoticeEntity = warePromotionInfo != null ? warePromotionInfo.proSalesInfo : null;
            WareBusinessSkuPropertyList wareBusinessSkuPropertyList = wareBusinessData.skuPropertyList;
            WareBusinessSkuPropertyItem wareBusinessSkuPropertyItem = wareBusinessSkuPropertyList != null ? wareBusinessSkuPropertyList.groupGoods : null;
            WareFlashInfoEntity wareFlashInfoEntity = wareBusinessData.flashInfo;
            WareBusinessFutureGuideAdvance wareBusinessFutureGuideAdvance = wareBusinessData.futureGuideAdvance;
            return (wareBusinessSpecialInfo != null && wareBusinessSpecialInfo.isSpecial) || !(wareMiaoShaInfo == null || TextUtils.isEmpty(wareMiaoShaInfo.msTrailer)) || (!(pDSeckillTipEntity == null || TextUtils.isEmpty(pDSeckillTipEntity.desc)) || isBusinessgroupGoods(wareBusinessSkuPropertyItem) || getBusinessFlashPurchaseType(wareFlashInfoEntity) == 1 || !((warePromotionInfo == null || wareBusinessPromotionNoticeEntity == null) && (wareBusinessFutureGuideAdvance == null || TextUtils.isEmpty(wareBusinessFutureGuideAdvance.advancePrice) || TextUtils.isEmpty(wareBusinessFutureGuideAdvance.advanceText))));
        }
        return false;
    }

    public boolean canChangeSkin() {
        return (isYuShou() || isMiaoSha() || isFlashBuying() || isYuyue()) ? false : true;
    }

    public boolean carAllInfoShowFlag() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarEntity pDCarEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarEntity = wareBusinessCarAllInfo.carInfo) == null || !pDCarEntity.showFlag) ? false : true;
    }

    public boolean carInfoNeedCarGift() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarEntity pDCarEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarEntity = wareBusinessCarAllInfo.carInfo) == null || !pDCarEntity.needCarGift) ? false : true;
    }

    public boolean cartFlag() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.cartFlag) ? false : true;
    }

    public boolean check() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null || TextUtils.isEmpty(pdBuyByMEntity.addCartBusinessName) || TextUtils.isEmpty(this.mWareBusinessData.addCart.addCartText)) ? false : true;
    }

    public void clearTempData() {
        this.mTempData.clear();
    }

    public boolean dealImoutaiV2Data() {
        JDJSONArray jDJSONArray;
        JDJSONObject jDJSONObject;
        JDJSONArray jSONArray;
        JDJSONObject jDJSONObject2;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (jDJSONArray = wareBusinessData.imoutaiV2) == null || (jDJSONObject = this.allwareJsonObject) == null || (jSONArray = jDJSONObject.getJSONArray("floors")) == null || jSONArray.isEmpty()) {
            return false;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= jSONArray.size()) {
                jDJSONObject2 = null;
                break;
            }
            JDJSONObject jSONObject = jSONArray.getJSONObject(i2);
            if (TextUtils.equals("bpMasterdata", jSONObject.getString("mId"))) {
                jDJSONObject2 = jSONObject.getJSONObject("data");
                break;
            }
            i2++;
        }
        if (jDJSONObject2 == null) {
            return false;
        }
        for (int i3 = 0; i3 < jDJSONArray.size(); i3++) {
            JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i3);
            String optString = optJSONObject.optString("mId");
            if (TextUtils.equals(optString, "bpMasterdata")) {
                JDJSONObject jSONObject2 = optJSONObject.getJSONObject("data");
                if (jSONObject2 != null) {
                    Iterator<Map.Entry<String, Object>> it = jSONObject2.entrySet().iterator();
                    while (it.hasNext()) {
                        String obj = it.next().getKey().toString();
                        if (jSONObject2.get(obj) instanceof JDJSONObject) {
                            jDJSONObject2.put(obj, (Object) jSONObject2.getJSONObject(obj));
                        } else if (jSONObject2.get(obj) instanceof JDJSONArray) {
                            jDJSONObject2.put(obj, (Object) jSONObject2.getJSONArray(obj));
                        } else if (jSONObject2.get(obj) instanceof Boolean) {
                            jDJSONObject2.put(obj, (Object) jSONObject2.getBoolean(obj));
                        } else if (jSONObject2.get(obj) instanceof Integer) {
                            jDJSONObject2.put(obj, (Object) jSONObject2.getInteger(obj));
                        } else if (jSONObject2.get(obj) instanceof String) {
                            jDJSONObject2.put(obj, (Object) jSONObject2.getString(obj));
                        }
                    }
                }
            } else {
                JDJSONObject jSONObject3 = optJSONObject.getJSONObject("data");
                int i4 = 0;
                while (true) {
                    if (i4 < jSONArray.size()) {
                        JDJSONObject jSONObject4 = jSONArray.getJSONObject(i4);
                        if (TextUtils.equals(optString, jSONObject4.getString("mId"))) {
                            jSONObject4.put("data", (Object) jSONObject3);
                            break;
                        }
                        i4++;
                    }
                }
            }
        }
        if (this.allwareJsonObject != null) {
            PDCarO2oGiftUtils.setCarGift(this, null);
            PDWareBusinessEntity pDWareBusinessEntity = new PDWareBusinessEntity(this.allwareJsonObject);
            pDWareBusinessEntity.mJsonString = this.allwareJsonObject.toJSONString();
            this.isLocalRefresh = true;
            PDManager.getEventBus().post(new PDApiEvent("detail_ware_business_new_key", pDWareBusinessEntity, this.mManageKey));
            this.allwareJsonObject = null;
            this.mWareBusinessData.imoutaiV2 = null;
            return true;
        }
        return false;
    }

    public void dealWareBusinessData(WareBusinessData wareBusinessData) {
        WareBusinessFsPriceEntity wareBusinessFsPriceEntity;
        if (isDarkTheme()) {
            this.themeStyle = 1;
        } else {
            this.themeStyle = 0;
        }
        this.mCommentInfo = null;
        this.mJdPrice = new ProductDetailPrice();
        WarePriceInfo warePriceInfo = wareBusinessData.priceInfo;
        if (warePriceInfo != null && !TextUtils.isEmpty(warePriceInfo.jprice)) {
            this.mJdPrice.setValue(wareBusinessData.priceInfo.jprice);
        } else {
            this.mJdPrice.setValue("");
        }
        SharedPreferencesUtil.putBoolean("isDesCbc", wareBusinessData.isDesCbc);
        PdFansPriceEntity pdFansPriceEntity = new PdFansPriceEntity();
        this.mFansPrice = pdFansPriceEntity;
        WarePriceInfo warePriceInfo2 = wareBusinessData.priceInfo;
        if (warePriceInfo2 != null && (wareBusinessFsPriceEntity = warePriceInfo2.fsPrice) != null) {
            pdFansPriceEntity.value = wareBusinessFsPriceEntity.value;
            pdFansPriceEntity.fsFloorColor = wareBusinessFsPriceEntity.color;
            pdFansPriceEntity.isFocus = wareBusinessFsPriceEntity.isFocus;
        } else {
            pdFansPriceEntity.value = null;
            pdFansPriceEntity.fsFloorColor = null;
            pdFansPriceEntity.isFocus = false;
            this.mFansPrice = null;
        }
        WarePromotionInfo warePromotionInfo = wareBusinessData.promotionInfo;
        if (warePromotionInfo != null) {
            setGiftPoolsListData(warePromotionInfo.giftPool3C);
        } else {
            setGiftPoolsListData(null);
        }
        setRegularBuy(wareBusinessData);
        this.deliveryId = "";
        setTopImageData(wareBusinessData.wareImage);
        setIsOldAddress(wareBusinessData.isOldAddress);
        WarePropertyInfo warePropertyInfo = wareBusinessData.property;
        setBuyMaxNum(0);
        setLowestBuyNum("0");
        setCategory(null);
        setIsShowShopNameB(false, wareBusinessData);
        setShadowSku(null);
        if (warePropertyInfo != null) {
            setBuyMaxNum(warePropertyInfo.buyMaxNum);
            setLowestBuyNum(warePropertyInfo.lowestBuyNum);
            setCategory(warePropertyInfo.category);
            setIsShowShopNameB(warePropertyInfo.isShowShopNameB, wareBusinessData);
            setEncrypt(warePropertyInfo.isEncrypt, wareBusinessData.isDesCbc);
            setShadowSku(warePropertyInfo.isShadowSku);
        }
        setPriceBusiness(null);
        this.mLocBuyStepEntity = null;
        setmSkuTag("");
        wareBusinessData.mtaInfo = getMtaInfo();
        this.mType4Entity = new ProductToJsNowBuyEntity();
        this.mType5Entity = new ProductToJsNowBuyEntity();
        BusinessPaiPaiSmallImgStockInfo businessPaiPaiSmallImgStockInfo = this.mWareBusinessData.ppInfo;
        if (businessPaiPaiSmallImgStockInfo != null) {
            this.inspectSkuId = businessPaiPaiSmallImgStockInfo.inspectSkuId;
        }
        PdWhiteBarForStyleInfoEntity pdWhiteBarForStyleInfoEntity = wareBusinessData.proPageWhiteBarInfo;
        this.isHaveWhiteBarData = (pdWhiteBarForStyleInfoEntity == null || pdWhiteBarForStyleInfoEntity.planInfos == null) ? false : true;
        if (wareBusinessData.plusShieldLandedPriceFlag) {
            addWearyCount();
        }
        this.serviceInfoId = null;
        this.mSelectSuitItem = null;
        dealStyleFloorView();
        getHourlyServeAttribute();
    }

    public boolean feedBackAB() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.feedBackAB) ? false : true;
    }

    public boolean freshBuyAB() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.freshBuyAB) ? false : true;
    }

    public List<WareBusinessPlusListEntity> get3cSerFactoryList() {
        Ware3cServerEntity ware3cServerEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (ware3cServerEntity = wareBusinessData.originalFactoryServiceInfo) == null) {
            return null;
        }
        return ware3cServerEntity.serviceList;
    }

    public ArrayList<String> get3cServiceSelectInternal() {
        List<WareBusinessJdServerProduct> list;
        List<WareBusinessJdServerProduct> list2;
        ArrayList<String> arrayList = null;
        if (has3cFactoryServer() && TextUtils.equals(this.mWareBusinessData.originalFactoryServiceInfo.type, "5")) {
            for (WareBusinessPlusListEntity wareBusinessPlusListEntity : get3cSerFactoryList()) {
                if (wareBusinessPlusListEntity != null && (list2 = wareBusinessPlusListEntity.products) != null && !list2.isEmpty()) {
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                    }
                    for (WareBusinessJdServerProduct wareBusinessJdServerProduct : wareBusinessPlusListEntity.products) {
                        if (wareBusinessJdServerProduct.isSelected && wareBusinessJdServerProduct.appendServiceSku) {
                            arrayList.add(wareBusinessJdServerProduct.serviceSku);
                        }
                    }
                }
            }
        } else if (has3cFactoryServerNew()) {
            for (Ware3cServerEntity ware3cServerEntity : this.mWareBusinessData.threeCServiceInfo.item) {
                if (ware3cServerEntity != null && ware3cServerEntity.serviceList != null && TextUtils.equals("5", ware3cServerEntity.type)) {
                    for (WareBusinessPlusListEntity wareBusinessPlusListEntity2 : ware3cServerEntity.serviceList) {
                        if (wareBusinessPlusListEntity2 != null && (list = wareBusinessPlusListEntity2.products) != null && !list.isEmpty()) {
                            if (arrayList == null) {
                                arrayList = new ArrayList<>();
                            }
                            for (WareBusinessJdServerProduct wareBusinessJdServerProduct2 : wareBusinessPlusListEntity2.products) {
                                if (wareBusinessJdServerProduct2.isSelected && wareBusinessJdServerProduct2.appendServiceSku) {
                                    arrayList.add(wareBusinessJdServerProduct2.serviceSku);
                                }
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public PdAcsEntity getAcsEntity() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.acsModel;
        }
        return null;
    }

    public String getAddCardListBtn() {
        PDECardInfoEntity pDECardInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDECardInfoEntity = wareBusinessData.eCardInfo) == null || TextUtils.isEmpty(pDECardInfoEntity.addCardListBtn)) ? "\u52a0\u5165\u5361\u6e05\u5355" : this.mWareBusinessData.eCardInfo.addCardListBtn;
    }

    public String getAddCartBtn() {
        PDECardInfoEntity pDECardInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDECardInfoEntity = wareBusinessData.eCardInfo) == null) ? "" : pDECardInfoEntity.addCartBtn;
    }

    public String getAddCartBusinessJumpType() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null) ? "" : pdBuyByMEntity.jumpType;
    }

    public String getAddCartBusinessName() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null) ? "" : pdBuyByMEntity.addCartBusinessName;
    }

    public String getAddCartButtonContent() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null) ? "" : pdBuyByMEntity.buttonContent;
    }

    public boolean getAddCartButtonMultiRow() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null || !pdBuyByMEntity.multiRow) ? false : true;
    }

    public String getAddCartText() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null) ? "" : pdBuyByMEntity.addCartText;
    }

    public String getAdvanceBuyTitle() {
        WareYuShouInfo wareYuShouInfo;
        WareYuShouAdvanceBuyMap wareYuShouAdvanceBuyMap;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || (wareYuShouAdvanceBuyMap = wareYuShouInfo.yuShouAdvanceBuyMap) == null || TextUtils.isEmpty(wareYuShouAdvanceBuyMap.advanceBuyTitle)) ? "" : this.mWareBusinessData.YuShouInfo.yuShouAdvanceBuyMap.advanceBuyTitle;
    }

    public String getAdvanceSkuSource() {
        WareYuShouInfo wareYuShouInfo;
        WareYuShouAdvanceBuyMap wareYuShouAdvanceBuyMap;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || (wareYuShouAdvanceBuyMap = wareYuShouInfo.yuShouAdvanceBuyMap) == null || TextUtils.isEmpty(wareYuShouAdvanceBuyMap.skuSource)) ? "" : this.mWareBusinessData.YuShouInfo.yuShouAdvanceBuyMap.skuSource;
    }

    public String getAdvanceSubTitle() {
        WareYuShouInfo wareYuShouInfo;
        WareYuShouAdvanceBuyMap wareYuShouAdvanceBuyMap;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || (wareYuShouAdvanceBuyMap = wareYuShouInfo.yuShouAdvanceBuyMap) == null || TextUtils.isEmpty(wareYuShouAdvanceBuyMap.subTitle)) ? "" : this.mWareBusinessData.YuShouInfo.yuShouAdvanceBuyMap.subTitle;
    }

    public String getAppletId() {
        WareAppletShare wareAppletShare;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareAppletShare = wareBusinessData.appletShare) == null) ? "" : wareAppletShare.appletId;
    }

    public String getAppletMpIconUrl() {
        WareAppletShare wareAppletShare;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareAppletShare = wareBusinessData.appletShare) == null) ? "" : wareAppletShare.mpIconUrl;
    }

    public String getAppletUrl() {
        WareAppletShare wareAppletShare;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareAppletShare = wareBusinessData.appletShare) == null) ? "" : wareAppletShare.url;
    }

    public String getAreaCartContext() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return null;
        }
        return warePropertyInfo.areaCartContext;
    }

    public String getAreaReasonTips() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return null;
        }
        return warePropertyInfo.areaReasonTips;
    }

    public String getAreaSkuId() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || TextUtils.equals(warePropertyInfo.areaSkuId, this.skuId)) ? "" : this.mWareBusinessData.property.areaSkuId;
    }

    public String getAreaToast() {
        WareSamInfo wareSamInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareSamInfo = wareBusinessData.samInfo) == null) ? "" : wareSamInfo.areaToast;
    }

    public ShoppingGuideService getAskDocEntity() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        ShoppingGuideInfo shoppingGuideInfo;
        ShoppingGuideService shoppingGuideService;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (shoppingGuideInfo = pdShopServiceEntity.advancedShoppingGuideInfo) == null || (shoppingGuideService = shoppingGuideInfo.askDoctor) == null) {
            return null;
        }
        return shoppingGuideService;
    }

    public PDInternetHospitalEntity getAskDocInfo() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.internetHospital;
        }
        return null;
    }

    public String getAskDocJumpUrl() {
        PDInternetHospitalEntity pDInternetHospitalEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDInternetHospitalEntity = wareBusinessData.internetHospital) == null) ? "" : pDInternetHospitalEntity.jumpUrl;
    }

    public String getBbtf() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || TextUtils.isEmpty(warePropertyInfo.bbtf)) ? "" : this.mWareBusinessData.property.bbtf;
    }

    public WareBusinessJumpInfo getBenefitBeltInfoJumpInfo() {
        WarePromotionInfo warePromotionInfo;
        WareBusinessBenefitBeltEntity wareBusinessBenefitBeltEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePromotionInfo = wareBusinessData.promotionInfo) == null || (wareBusinessBenefitBeltEntity = warePromotionInfo.benefitBeltInfo) == null) {
            return null;
        }
        return wareBusinessBenefitBeltEntity.jumpInfo;
    }

    public List<PDCouponCellEntity> getBestCoupon() {
        List<String> list;
        List<PDCouponCellEntity> list2;
        ArrayList arrayList = new ArrayList();
        PreferentialGuideEntity preferentialGuideEntity = getPreferentialGuideEntity();
        if (preferentialGuideEntity != null && (list = preferentialGuideEntity.bestCouponId) != null && list.size() > 0) {
            for (String str : preferentialGuideEntity.bestCouponId) {
                if (!TextUtils.isEmpty(str) && (list2 = preferentialGuideEntity.couponInfo) != null && list2.size() > 0) {
                    for (PDCouponCellEntity pDCouponCellEntity : preferentialGuideEntity.couponInfo) {
                        if (TextUtils.equals(pDCouponCellEntity.couponId, str) && pDCouponCellEntity.applicability && !pDCouponCellEntity.personalCoupon) {
                            arrayList.add(pDCouponCellEntity);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public List<JDJSONObject> getBestCouponReceiveData(boolean z) {
        FloorTemplateEntity floorTemplateEntity;
        FloorTemplate templateById;
        JDJSONObject jDJSONObject;
        ArrayList arrayList = new ArrayList();
        if (z && this.discountLayerEntity == null && (floorTemplateEntity = this.templateEntity) != null && (templateById = floorTemplateEntity.getTemplateById(FloorBussinessName.FB_BUSINESS_BPJPRICE_V12)) != null) {
            Object obj = templateById.mData;
            if ((obj instanceof JDJSONObject) && (jDJSONObject = (JDJSONObject) ((JDJSONObject) obj).get("layerPreference")) != null) {
                this.discountLayerEntity = (PdDiscountLayerEntity) JDJSON.parseObject(jDJSONObject.toJSONString(), PdDiscountLayerEntity.class);
            }
        }
        PdDiscountLayerEntity pdDiscountLayerEntity = this.discountLayerEntity;
        if (pdDiscountLayerEntity != null && pdDiscountLayerEntity.bestCouponInfo != null) {
            List<JDJSONObject> bestCanReceiveCoupon = pdDiscountLayerEntity.getBestCanReceiveCoupon();
            List<JDJSONObject> list = this.discountLayerEntity.bestCouponInfo.receiveData;
            if (list != null && !list.isEmpty() && bestCanReceiveCoupon != null && !bestCanReceiveCoupon.isEmpty()) {
                for (int i2 = 0; i2 < list.size(); i2++) {
                    JDJSONObject jDJSONObject2 = list.get(i2);
                    for (int i3 = 0; i3 < bestCanReceiveCoupon.size(); i3++) {
                        JDJSONObject jDJSONObject3 = bestCanReceiveCoupon.get(i3);
                        if (TextUtils.equals(jDJSONObject2.optString("gwcBatchId"), jDJSONObject3.optString(JshopConst.JSKEY_BATCH_ID)) || TextUtils.equals(jDJSONObject2.optString("gwcBatchId"), jDJSONObject3.optString("jrBatchId"))) {
                            arrayList.add(jDJSONObject2);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public int getBottomDdCount() {
        PdDdFatigueMechanism pdDdFatigueMechanism;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdDdFatigueMechanism = wareBusinessData.ddFatigueMechanism) == null) {
            return 5;
        }
        return PDUtils.stringToInt(pdDdFatigueMechanism.ddBottomToastCount);
    }

    public int getBottomDdDuration() {
        PdDdFatigueMechanism pdDdFatigueMechanism;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdDdFatigueMechanism = wareBusinessData.ddFatigueMechanism) == null) {
            return 10000;
        }
        return PDUtils.stringToInt(pdDdFatigueMechanism.ddBottomToastDuration) * 1000;
    }

    public String getBottomIconType() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        PdShopChatInfoEntity pdShopChatInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (pdShopChatInfoEntity = pdShopServiceEntity.chatInfo) == null) ? "" : pdShopChatInfoEntity.bottomIconType;
    }

    public String getBrandId() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || TextUtils.isEmpty(warePropertyInfo.brandId)) ? "" : this.mWareBusinessData.property.brandId;
    }

    public String getBtnSource() {
        PdStyleDialogEntity.OpenType openType;
        PdStyleDialogEntity pdStyleDialogEntity = this.mStyleDialogEntity;
        return (pdStyleDialogEntity == null || (openType = pdStyleDialogEntity.mOpenType) == null || openType == PdStyleDialogEntity.OpenType.FLOOR) ? "0" : openType == PdStyleDialogEntity.OpenType.ADDCART ? "1" : openType == PdStyleDialogEntity.OpenType.PAYDEPOSI ? "2" : openType == PdStyleDialogEntity.OpenType.NOWBUY ? "3" : "0";
    }

    public String getBubbleType() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        PdShopChatInfoEntity pdShopChatInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (pdShopChatInfoEntity = pdShopServiceEntity.chatInfo) == null) ? "" : pdShopChatInfoEntity.bubbleImgType;
    }

    public String getBundlingOrderId() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null) ? "" : pDCarShopEntity.bundlingOrderId;
    }

    public int getBusinessFlashPurchaseType(WareFlashInfoEntity wareFlashInfoEntity) {
        if (wareFlashInfoEntity == null) {
            return 0;
        }
        return wareFlashInfoEntity.state;
    }

    public long getBuyEndTime() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null) {
            return -1L;
        }
        return getTimeValue(wareYuYueInfo.buyEndTime);
    }

    public long getBuyStartTime() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null) {
            return -1L;
        }
        return getTimeValue(wareYuYueInfo.buyStartTime);
    }

    public String getCarAllInfoTextMapToast() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarEntity pDCarEntity;
        WareBusinessCarTextEntity wareBusinessCarTextEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarEntity = wareBusinessCarAllInfo.carInfo) == null || (wareBusinessCarTextEntity = pDCarEntity.textMap) == null) ? "" : wareBusinessCarTextEntity.toast;
    }

    public String getCarInfoCarContext() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarEntity pDCarEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarEntity = wareBusinessCarAllInfo.carInfo) == null) ? "" : pDCarEntity.carContext;
    }

    public PDCarEntity getCarInfoEntity() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null) {
            return null;
        }
        return wareBusinessCarAllInfo.carInfo;
    }

    public List<PDCarItemEntity> getCarModelInfo() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarEntity pDCarEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarEntity = wareBusinessCarAllInfo.carInfo) == null) {
            return null;
        }
        return pDCarEntity.carModelInfo;
    }

    public PDCarShopEntity getCarShoInfo() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null) {
            return null;
        }
        return wareBusinessCarAllInfo.carShopInfo;
    }

    public String getCarShopInfoCarText() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null) ? "" : pDCarShopEntity.carText;
    }

    public int getCarShopInfoCount() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null) {
            return 0;
        }
        return pDCarShopEntity.count;
    }

    public String getCarShopInfoLink() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null) ? "" : pDCarShopEntity.mLink;
    }

    public String getCardListBtn() {
        PDECardInfoEntity pDECardInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDECardInfoEntity = wareBusinessData.eCardInfo) == null || TextUtils.isEmpty(pDECardInfoEntity.cardListBtn)) ? "\u5361\u6e05\u5355" : this.mWareBusinessData.eCardInfo.cardListBtn;
    }

    public String getCardListLink() {
        PDECardInfoEntity pDECardInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDECardInfoEntity = wareBusinessData.eCardInfo) == null) ? "" : pDECardInfoEntity.cardListLink;
    }

    public String getCategroyId(int i2) {
        String[] strArr = this.mBasicInfo.categoryIds;
        return (strArr == null || strArr.length <= i2) ? "" : strArr[i2];
    }

    public String getChatUrl() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) ? "" : warePropertyInfo.chatUrl;
    }

    public String getClothesMark() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || TextUtils.isEmpty(wareBusinessData.clothMark)) ? "0" : TextUtils.equals("xp", this.mWareBusinessData.clothMark) ? "1" : TextUtils.equals("tk", this.mWareBusinessData.clothMark) ? "2" : "0";
    }

    public List<PDStyleFilterEntity> getColorSize() {
        WareBusinessStyleEntity wareBusinessStyleEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessStyleEntity = wareBusinessData.colorSizeInfo) == null) {
            return null;
        }
        return wareBusinessStyleEntity.colorSize;
    }

    public String getCoudanOrderMin() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) ? "" : warePropertyInfo.ordermin;
    }

    public String getCustomize() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null) ? "" : pdBuyByMEntity.customize;
    }

    public DJAskDoctorDetailInfo getDJAskDocInfo() {
        PdAhStoreEntity pdAhStoreEntity;
        DJAskDoctorDetailInfo dJAskDoctorDetailInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdAhStoreEntity = wareBusinessData.daojiaStoreInfo) == null || (dJAskDoctorDetailInfo = pdAhStoreEntity.internetHospital) == null) {
            return null;
        }
        return dJAskDoctorDetailInfo;
    }

    public PdAhStoreBottomButtonEntity getDJBottomButtonInfo() {
        PdAhStoreBottomButtonEntity pdAhStoreBottomButtonEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdAhStoreBottomButtonEntity = wareBusinessData.daojiaSelfDeliveryBottomInfo) == null) {
            return null;
        }
        return pdAhStoreBottomButtonEntity;
    }

    public long getDJPreSaleTime() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null) {
            return 0L;
        }
        return wareYuShouInfo.presaleStartTime;
    }

    public JDJSONObject getDJTemplateType() {
        WarePropertyInfo warePropertyInfo;
        JDJSONObject jDJSONObject = new JDJSONObject();
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null && !TextUtils.isEmpty(wareBusinessData.templateType)) {
            jDJSONObject.put("modetype", (Object) this.mWareBusinessData.templateType);
        }
        if (getHealthPrescriptionDrugInfo() != null) {
            jDJSONObject.put(SubShoppingBaseController.SHOPPING_DRUGLIST_CART_FLAG, (Object) "1");
        } else {
            jDJSONObject.put(SubShoppingBaseController.SHOPPING_DRUGLIST_CART_FLAG, (Object) "0");
        }
        WareBusinessData wareBusinessData2 = this.mWareBusinessData;
        if (wareBusinessData2 != null && (warePropertyInfo = wareBusinessData2.property) != null && warePropertyInfo.noStockCartFlag) {
            jDJSONObject.put("is_wh", (Object) "1");
        } else {
            jDJSONObject.put("is_wh", (Object) "0");
        }
        return jDJSONObject;
    }

    public boolean getDaojiaBtnFlag() {
        PdAhStoreEntity pdAhStoreEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdAhStoreEntity = wareBusinessData.daojiaStoreInfo) == null || TextUtils.isEmpty(pdAhStoreEntity.storeRightUrl)) ? false : true;
    }

    public boolean getDaojiaFlag() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.daojiaFlag) ? false : true;
    }

    public String getDaojiaStoreId() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return null;
        }
        return warePropertyInfo.storeId;
    }

    public String getDdAskDocUrl() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        ShoppingGuideInfo shoppingGuideInfo;
        ShoppingGuideService shoppingGuideService;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (shoppingGuideInfo = pdShopServiceEntity.advancedShoppingGuideInfo) == null || (shoppingGuideService = shoppingGuideInfo.askDoctor) == null) {
            return null;
        }
        return shoppingGuideService.jumpUrl;
    }

    public PdShopDdDrag getDdDragEntity() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        PdShopDdDrag pdShopDdDrag;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (pdShopDdDrag = pdShopServiceEntity.ddDrag) == null) {
            return null;
        }
        return pdShopDdDrag;
    }

    public PDDeliveryInstallEntity getDeliveryInstallEntity() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.deliveryInstall;
        }
        return null;
    }

    public ArrayList<PDDeliveryOptionEntity> getDeliveryInstallOptionList() {
        PDDeliveryInstallEntity pDDeliveryInstallEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pDDeliveryInstallEntity = wareBusinessData.deliveryInstall) == null) {
            return null;
        }
        return pDDeliveryInstallEntity.optionList;
    }

    public String getDongAdvanceType() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        ShoppingGuideInfo shoppingGuideInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (shoppingGuideInfo = pdShopServiceEntity.advancedShoppingGuideInfo) == null) ? "" : shoppingGuideInfo.type;
    }

    public String getDongDragType() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        PdShopDdDrag pdShopDdDrag;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (pdShopDdDrag = pdShopServiceEntity.ddDrag) == null) ? "" : pdShopDdDrag.type;
    }

    public String getDongToastType() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        PdShopDdToastEntity pdShopDdToastEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (pdShopDdToastEntity = pdShopServiceEntity.ddToast) == null) ? "" : pdShopDdToastEntity.type;
    }

    public String getDownPayDesc() {
        PDInstallmentInfoEntity pDInstallmentInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDInstallmentInfoEntity = wareBusinessData.installmentInfo) == null) ? "" : pDInstallmentInfoEntity.downPayDesc;
    }

    public int getDrugBusinessId() {
        WareBusinessDrugEntity wareBusinessDrugEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessDrugEntity = wareBusinessData.drugInfo) == null) {
            return 0;
        }
        return wareBusinessDrugEntity.drugBusinessId;
    }

    public String getDrugListUrl() {
        WareBusinessDrugEntity wareBusinessDrugEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessDrugEntity = wareBusinessData.drugInfo) == null) ? "" : wareBusinessDrugEntity.drugListUrl;
    }

    public String getDrugsParam() {
        return isOTCSelfSupport() ? "2" : isPrescriptionPOP() ? "4" : isPrescription() ? "1" : isOTCPOP() ? "3" : "0";
    }

    public String getEventParam() {
        WareEventParam wareEventParam;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareEventParam = wareBusinessData.eventParam) == null) ? "" : wareEventParam.sep;
    }

    public String getEyeSightCode() {
        WareEyeSightInfo wareEyeSightInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareEyeSightInfo = wareBusinessData.wareEyeSight) == null || TextUtils.isEmpty(wareEyeSightInfo.eyeSightCode)) ? "" : this.mWareBusinessData.wareEyeSight.eyeSightCode;
    }

    public String getEyeSightEndMessage() {
        WareEyeSightInfo wareEyeSightInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareEyeSightInfo = wareBusinessData.wareEyeSight) == null) ? "" : wareEyeSightInfo.eyeSightEndMessage;
    }

    public String getEyeSightImage() {
        WareEyeSightInfo wareEyeSightInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareEyeSightInfo = wareBusinessData.wareEyeSight) == null || TextUtils.isEmpty(wareEyeSightInfo.eyeSightImage)) ? "" : this.mWareBusinessData.wareEyeSight.eyeSightImage;
    }

    public String getEyeSightUrl() {
        WareEyeSightInfo wareEyeSightInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareEyeSightInfo = wareBusinessData.wareEyeSight) == null) ? "" : wareEyeSightInfo.eyeSightUrl;
    }

    public PdDdFatigueMechanism getFatigueMechanism() {
        PdDdFatigueMechanism pdDdFatigueMechanism;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdDdFatigueMechanism = wareBusinessData.ddFatigueMechanism) == null) {
            return null;
        }
        return pdDdFatigueMechanism;
    }

    public HashMap<String, Object> getFeedsProductMtaData() {
        HashMap<String, Object> hashMap = new HashMap<>();
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            hashMap.put("broker_info", wareBusinessData.broker_info);
            hashMap.put("rec_broker", this.mWareBusinessData.rec_broker);
            hashMap.put("main_sku", this.mWareBusinessData.main_sku);
            hashMap.put("sku", this.mWareBusinessData.rec_sku);
        }
        return hashMap;
    }

    public List<WareBusinessFurnitureGroupEntity> getFiInfo() {
        WareFurnitureInfo wareFurnitureInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareFurnitureInfo = wareBusinessData.furnitureIncrementServiceInfo) == null) {
            return null;
        }
        return wareFurnitureInfo.fiInfo;
    }

    public int getFlashPurchaseType() {
        WareFlashInfoEntity wareFlashInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareFlashInfoEntity = wareBusinessData.flashInfo) == null) {
            return 0;
        }
        return wareFlashInfoEntity.state;
    }

    public FloorThemeEnum getFloorTheme() {
        if (isNewJx()) {
            return FloorThemeEnum.FEATURE;
        }
        return FloorThemeEnum.NORMAL;
    }

    public WareBusinessFreshBCMatchInfo getFreshBCMatchInfo() {
        WareBusinessFreshBCMatchInfo wareBusinessFreshBCMatchInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessFreshBCMatchInfo = wareBusinessData.freshBCMatchInfo) == null) {
            return null;
        }
        return wareBusinessFreshBCMatchInfo;
    }

    public ArrayList<NewGiftPoolItem> getGiftPoolsData(ArrayList<GiftPool3C> arrayList) {
        ArrayList<NewGiftPoolItem> arrayList2 = new ArrayList<>();
        if (arrayList != null) {
            try {
                if (!arrayList.isEmpty()) {
                    Iterator<GiftPool3C> it = arrayList.iterator();
                    while (it.hasNext()) {
                        GiftPool3C next = it.next();
                        NewGiftPoolItem newGiftPoolItem = new NewGiftPoolItem();
                        newGiftPoolItem.setId(next.getId());
                        newGiftPoolItem.setName(next.getName());
                        ArrayList<Gift3C> gifts = next.getGifts();
                        ArrayList<NewGiftItem> arrayList3 = new ArrayList<>();
                        if (gifts != null && !gifts.isEmpty()) {
                            Iterator<Gift3C> it2 = gifts.iterator();
                            while (it2.hasNext()) {
                                Gift3C next2 = it2.next();
                                NewGiftItem newGiftItem = new NewGiftItem();
                                newGiftItem.setId(next2.getSkuId());
                                newGiftItem.setImgUrl(next2.getImgUrl());
                                newGiftItem.setName(next2.getName());
                                newGiftItem.setSelect(next2.isChecked());
                                newGiftItem.setNum(Integer.valueOf(next2.getNum()).intValue());
                                arrayList3.add(newGiftItem);
                            }
                        }
                        newGiftPoolItem.setGifts(arrayList3);
                        arrayList2.add(newGiftPoolItem);
                    }
                }
            } catch (Exception unused) {
            }
        }
        return arrayList2;
    }

    public WareGoldPurchaseEntity getGoldPurchaseData() {
        WareGoldPurchaseEntity wareGoldPurchaseEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareGoldPurchaseEntity = wareBusinessData.goldPurchase) == null) {
            return null;
        }
        return wareGoldPurchaseEntity;
    }

    public String getGoodsStateBuriedPoint() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || TextUtils.isEmpty(wareBusinessData.goodsStateBuriedPoint)) ? "3" : this.mWareBusinessData.goodsStateBuriedPoint;
    }

    public String getHasLargePromotionFlag() {
        WareBusinessPointInfo wareBusinessPointInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessPointInfo = wareBusinessData.pointInfo) == null || TextUtils.isEmpty(wareBusinessPointInfo.hasLargePromotionFlag)) ? "0" : this.mWareBusinessData.bannerInfo != null ? "1" : "99";
    }

    public boolean getHaveAskDoc() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.askMedicineInfoAb) ? false : true;
    }

    public PdPayGuidTips getHealthBottomBarInfo() {
        PdHealthBottomBtnEntity pdHealthBottomBtnEntity;
        PdPayGuidTips pdPayGuidTips;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdHealthBottomBtnEntity = wareBusinessData.rxNewPrescriptInfoResult) == null || (pdPayGuidTips = pdHealthBottomBtnEntity.payGuidTips) == null) {
            return null;
        }
        return pdPayGuidTips;
    }

    public PdHealthBottomBarEntity getHealthBottomTipIntroInfo() {
        PdHealthBottomBtnEntity pdHealthBottomBtnEntity;
        PdHealthBottomBarEntity pdHealthBottomBarEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdHealthBottomBtnEntity = wareBusinessData.rxNewPrescriptInfoResult) == null || (pdHealthBottomBarEntity = pdHealthBottomBtnEntity.rxNewPrescriptBottomHTInfo) == null) {
            return null;
        }
        return pdHealthBottomBarEntity;
    }

    public PdHealthExplanationEntity getHealthBottomTipIntroLayerInfo() {
        PdHealthBottomBtnEntity pdHealthBottomBtnEntity;
        PdHealthExplanationEntity pdHealthExplanationEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdHealthBottomBtnEntity = wareBusinessData.rxNewPrescriptInfoResult) == null || (pdHealthExplanationEntity = pdHealthBottomBtnEntity.rxNewFloorData) == null) {
            return null;
        }
        return pdHealthExplanationEntity;
    }

    public PdHealthBottomBtnEntity getHealthPrescriptionDrugInfo() {
        PdHealthBottomBtnEntity pdHealthBottomBtnEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdHealthBottomBtnEntity = wareBusinessData.rxNewPrescriptInfoResult) == null) {
            return null;
        }
        return pdHealthBottomBtnEntity;
    }

    public PdHeYuejiFloorEntity getHeyuejiEntity() {
        FloorTemplate templateById;
        if (isHyjFloorShowed() && isTemplateFormat()) {
            if (isElderTheme()) {
                templateById = this.templateEntity.getTemplateById("bpHeyuejiguige");
            } else if (isTenthRevision()) {
                templateById = this.templateEntity.getTemplateById(FloorBussinessName.FB_BUSINESS_HYJ_v10);
            } else {
                templateById = this.templateEntity.getTemplateById("bpHeyuejiguige");
            }
            Object obj = templateById.mData;
            if (obj != null && (obj instanceof PdHeYuejiFloorEntity)) {
                return (PdHeYuejiFloorEntity) obj;
            }
        }
        return null;
    }

    public String getHomeFloorExt() {
        WarePropertyInfo warePropertyInfo;
        JDJSONObject jDJSONObject = new JDJSONObject();
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null && (warePropertyInfo = wareBusinessData.property) != null) {
            jDJSONObject.put("decbrand1", (Object) warePropertyInfo.brandId);
            jDJSONObject.put("decbrand2", (Object) this.mWareBusinessData.property.brandId);
            jDJSONObject.put("decbrand3", (Object) this.mWareBusinessData.property.brandId);
            jDJSONObject.put("deccid1", (Object) this.mWareBusinessData.property.getCategroyId(0));
            jDJSONObject.put("deccid2", (Object) this.mWareBusinessData.property.getCategroyId(1));
            jDJSONObject.put("deccid3", (Object) this.mWareBusinessData.property.getCategroyId(2));
        }
        return jDJSONObject.toString();
    }

    public String getHospitalAB() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || TextUtils.isEmpty(wareBusinessABTestInfo.hospitalAB)) ? "A" : this.mWareBusinessData.abTestInfo.hospitalAB;
    }

    public String getHotLinePhone() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        PdShopHotLineEntity pdShopHotLineEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (pdShopHotLineEntity = pdShopServiceEntity.hotLineInfo) == null) ? "" : pdShopHotLineEntity.hotLinePhone;
    }

    public String getHotLineService() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        PdShopHotLineEntity pdShopHotLineEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (pdShopHotLineEntity = pdShopServiceEntity.hotLineInfo) == null) ? "" : pdShopHotLineEntity.hotLineService;
    }

    public boolean getHwShare() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.hwShare) ? false : true;
    }

    public WareBusinessHwShareInfo getHwShareInfo() {
        WareBusinessHwShareInfo wareBusinessHwShareInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessHwShareInfo = wareBusinessData.hwShareInfo) == null) {
            return null;
        }
        PDMtaEntity pDMtaEntity = wareBusinessData.mtaInfo;
        if (pDMtaEntity != null) {
            wareBusinessHwShareInfo.skuId = pDMtaEntity.skuId;
        }
        return wareBusinessHwShareInfo;
    }

    public String getHyjBottomLink() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null) ? "" : pdBuyByMEntity.link;
    }

    @Override // com.jingdong.common.entity.ProductDetailEntityBase
    public long getId() {
        return PDUtils.stringToLong(this.skuId);
    }

    public String getImageUrl() {
        if (this.imageList.size() > 0) {
            return this.imageList.get(0).small;
        }
        return null;
    }

    public boolean getIsTimeOrderPrescriptCat() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.isTimeOrderPrescriptCat;
    }

    public String getIsXnzt() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null ? wareBusinessData.isXnzt : "";
    }

    public ArrayList<String> getJdCarIds() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarEntity pDCarEntity;
        List<PDCarItemEntity> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarEntity = wareBusinessCarAllInfo.carInfo) == null || (list = pDCarEntity.carModelInfo) == null || list.isEmpty()) {
            return null;
        }
        List<PDCarItemEntity> list2 = this.mWareBusinessData.carAllInfo.carInfo.carModelInfo;
        if (TextUtils.isEmpty(this.mCurModelId)) {
            return null;
        }
        for (PDCarItemEntity pDCarItemEntity : list2) {
            if (pDCarItemEntity != null && TextUtils.equals(this.mCurModelId, pDCarItemEntity.carModelId)) {
                return pDCarItemEntity.jdCarIds;
            }
        }
        return null;
    }

    public List<WareBusinessPlusListEntity> getJdSerPlusList() {
        WareJdServerPlusEntity wareJdServerPlusEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareJdServerPlusEntity = wareBusinessData.jdSerPlusInfo) == null) {
            return null;
        }
        return wareJdServerPlusEntity.jdSerPlusList;
    }

    public String getJsonParam() {
        AddFamilyListData addFamilyListData;
        WareMiaoShaInfo wareMiaoShaInfo;
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        String str = "-100";
        String str2 = (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || TextUtils.isEmpty(warePropertyInfo.wareImageTest)) ? "-100" : this.mWareBusinessData.property.wareImageTest;
        WareBusinessData wareBusinessData2 = this.mWareBusinessData;
        String str3 = (wareBusinessData2 == null || (wareMiaoShaInfo = wareBusinessData2.miaoshaInfo) == null || TextUtils.isEmpty(wareMiaoShaInfo.msTrailer)) ? "0" : "1";
        WareBusinessData wareBusinessData3 = this.mWareBusinessData;
        String str4 = (wareBusinessData3 == null || (addFamilyListData = wareBusinessData3.addFamilyList) == null || !addFamilyListData.showFamilyButton) ? "0" : "1";
        if (wareBusinessData3 != null && !TextUtils.isEmpty(wareBusinessData3.doublepriceUp)) {
            str = this.mWareBusinessData.doublepriceUp;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("mainpicture", (Object) str2);
        jDJSONObject.put("seckillremind", str3);
        jDJSONObject.put("familyicon", (Object) str4);
        jDJSONObject.put("doubleprice", (Object) str);
        return jDJSONObject.toJSONString();
    }

    public String getJumpUrl(String str, String str2) {
        if (TextUtils.isEmpty(getCarShopInfoLink())) {
            return null;
        }
        StringBuilder sb = new StringBuilder(getCarShopInfoLink());
        sb.append("/num:");
        sb.append(this.number);
        PDCarGiftEntity pDCarGiftEntity = this.mCarGiftEntity;
        if (pDCarGiftEntity != null && !TextUtils.isEmpty(pDCarGiftEntity.giftSkuId)) {
            sb.append("/giftId:");
            sb.append(this.mCarGiftEntity.giftSkuId);
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append("/modelId:");
            sb.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append("/source:");
            sb.append(str2);
        }
        ArrayList<NewGiftItem> selectedGiftItems = getSelectedGiftItems();
        if (selectedGiftItems != null && !selectedGiftItems.isEmpty()) {
            if (!sb.toString().contains("/giftIds:")) {
                sb.append("/giftIds:");
            }
            for (int i2 = 0; i2 < selectedGiftItems.size(); i2++) {
                if (selectedGiftItems.get(i2) != null) {
                    sb.append(selectedGiftItems.get(i2).getId());
                    sb.append(CartConstant.KEY_YB_INFO_LINK);
                    sb.append(selectedGiftItems.get(i2).getNum());
                    sb.append(CartConstant.KEY_YB_INFO_LINK);
                }
            }
            if (sb.lastIndexOf(CartConstant.KEY_YB_INFO_LINK) != -1) {
                sb.deleteCharAt(sb.lastIndexOf(CartConstant.KEY_YB_INFO_LINK));
            }
        }
        ArrayList<String> arrayList = get3cServiceSelectInternal();
        if (arrayList != null && !arrayList.isEmpty()) {
            if (!sb.toString().contains("/lsServiceId:")) {
                sb.append("/lsServiceId:");
            }
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                if (arrayList.get(i3) != null) {
                    sb.append(arrayList.get(i3));
                    sb.append(CartConstant.KEY_YB_INFO_LINK);
                }
            }
            if (sb.lastIndexOf(CartConstant.KEY_YB_INFO_LINK) != -1) {
                sb.deleteCharAt(sb.lastIndexOf(CartConstant.KEY_YB_INFO_LINK));
            }
        }
        return sb.toString();
    }

    public String getJxOpenPlusText() {
        WareBusinessJxInfoEntity wareBusinessJxInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessJxInfoEntity = wareBusinessData.jxInfo) == null) ? "" : wareBusinessJxInfoEntity.jxOpenPlusText;
    }

    public int getKoInfoState() {
        PDSeckillTipEntity pDSeckillTipEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pDSeckillTipEntity = wareBusinessData.koInfo) == null) {
            return 0;
        }
        return pDSeckillTipEntity.state;
    }

    public int getLeftBottomDdCount() {
        PdDdFatigueMechanism pdDdFatigueMechanism;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdDdFatigueMechanism = wareBusinessData.ddFatigueMechanism) == null) {
            return 5;
        }
        return PDUtils.stringToInt(pdDdFatigueMechanism.leftBottomBubbleCount);
    }

    public int getLeftBottomDdDuration() {
        PdDdFatigueMechanism pdDdFatigueMechanism;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdDdFatigueMechanism = wareBusinessData.ddFatigueMechanism) == null) ? R2.dimen.pd_space_width_10 : PDUtils.stringToInt(pdDdFatigueMechanism.leftBottomBubbleDuration) * 1000;
    }

    public String getLink() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null) ? "" : pdBuyByMEntity.link;
    }

    public String getLiveBenefitIcon() {
        PdLiveFloatEntity pdLiveFloatEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdLiveFloatEntity = wareBusinessData.liveInfo) == null) ? "" : pdLiveFloatEntity.benefitIcon;
    }

    public PdLiveFloatEntity getLiveInfo() {
        PdLiveFloatEntity pdLiveFloatEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdLiveFloatEntity = wareBusinessData.liveInfo) == null) {
            return null;
        }
        return pdLiveFloatEntity;
    }

    public String getLiveInfoLiveCloseIconUrl() {
        PdLiveFloatEntity pdLiveFloatEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdLiveFloatEntity = wareBusinessData.liveInfo) == null) ? "" : pdLiveFloatEntity.shrinkIcon;
    }

    public String getLiveInfoLivePic() {
        PdLiveFloatEntity pdLiveFloatEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdLiveFloatEntity = wareBusinessData.liveInfo) == null) ? "" : pdLiveFloatEntity.livePic;
    }

    public String getLiveInfoLivePullUrl() {
        PdLiveFloatEntity pdLiveFloatEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdLiveFloatEntity = wareBusinessData.liveInfo) == null) ? "" : pdLiveFloatEntity.h5pullUrl;
    }

    public boolean getLiveInfoLiveStyle() {
        PdLiveFloatEntity pdLiveFloatEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdLiveFloatEntity = wareBusinessData.liveInfo) == null || !pdLiveFloatEntity.liveStyleNew) ? false : true;
    }

    public String getLiveInfoLiveType() {
        PdLiveFloatEntity pdLiveFloatEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdLiveFloatEntity = wareBusinessData.liveInfo) == null) ? "" : pdLiveFloatEntity.liveType;
    }

    public String getLiveInfoOpenApp() {
        PdLiveFloatEntity pdLiveFloatEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdLiveFloatEntity = wareBusinessData.liveInfo) == null) ? "" : pdLiveFloatEntity.openapp;
    }

    public String getLocAddCartText() {
        LocInfo locInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (locInfo = wareBusinessData.locInfo) == null) ? "" : locInfo.addCartText;
    }

    public boolean getLocIsJdDaoDian() {
        LocInfo locInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (locInfo = wareBusinessData.locInfo) == null || !locInfo.jingDongDaoDian) ? false : true;
    }

    public String getLocMta() {
        LocInfo locInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (locInfo = wareBusinessData.locInfo) == null) ? "" : locInfo.mta;
    }

    public String getLocServiceStatus() {
        LocInfo locInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (locInfo = wareBusinessData.locInfo) == null) ? "" : locInfo.serviceStatus;
    }

    public PDBottomBtn getMain() {
        PDBottomInfo pDBottomInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pDBottomInfo = wareBusinessData.buttonInfo) == null) {
            return null;
        }
        return pDBottomInfo.main;
    }

    public String getMainName() {
        PDBottomInfo pDBottomInfo;
        PDBottomBtn pDBottomBtn;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDBottomInfo = wareBusinessData.buttonInfo) == null || (pDBottomBtn = pDBottomInfo.main) == null) ? "" : pDBottomBtn.name;
    }

    public String getMainNameTermiteBottomText() {
        PDBottomInfo pDBottomInfo;
        PDBottomBtn pDBottomBtn;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDBottomInfo = wareBusinessData.buttonInfo) == null || (pDBottomBtn = pDBottomInfo.main) == null) ? "" : pDBottomBtn.bottomText;
    }

    public String getMainNameTopText() {
        PDBottomInfo pDBottomInfo;
        PDBottomBtn pDBottomBtn;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDBottomInfo = wareBusinessData.buttonInfo) == null || (pDBottomBtn = pDBottomInfo.main) == null) ? "" : pDBottomBtn.topText;
    }

    public int getMainSource() {
        PDBottomInfo pDBottomInfo;
        PDBottomBtn pDBottomBtn;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pDBottomInfo = wareBusinessData.buttonInfo) == null || (pDBottomBtn = pDBottomInfo.main) == null) {
            return 0;
        }
        return pDBottomBtn.source;
    }

    public int getMainType() {
        PDBottomInfo pDBottomInfo;
        PDBottomBtn pDBottomBtn;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pDBottomInfo = wareBusinessData.buttonInfo) == null || (pDBottomBtn = pDBottomInfo.main) == null) {
            return 0;
        }
        return pDBottomBtn.type;
    }

    public String getMaxSales() {
        WareBusinessPointInfo wareBusinessPointInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessPointInfo = wareBusinessData.pointInfo) == null || TextUtils.isEmpty(wareBusinessPointInfo.maxSales)) ? "0" : this.mWareBusinessData.pointInfo.maxSales;
    }

    public String getMergedBuyText() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null) ? "" : wareYuShouInfo.mergedBuyText;
    }

    public int getMiaoShaState() {
        WareMiaoShaInfo wareMiaoShaInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareMiaoShaInfo = wareBusinessData.miaoshaInfo) == null) {
            return 0;
        }
        return wareMiaoShaInfo.state;
    }

    public String getMonthlyPay() {
        PDInstallmentInfoEntity pDInstallmentInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDInstallmentInfoEntity = wareBusinessData.installmentInfo) == null) ? "" : pDInstallmentInfoEntity.monthlyPay;
    }

    public PDMtaEntity getMtaInfo() {
        return new PDMtaEntity(this.skuId, getSkuTag(), getShopId());
    }

    public ArrayList<NewGiftPoolItem> getNewGiftPoolData(ArrayList<GiftPool3C> arrayList) {
        ArrayList<NewGiftPoolItem> giftPoolsData = getGiftPoolsData(arrayList);
        updateSelectedGiftNum(giftPoolsData);
        return giftPoolsData;
    }

    public String getOTCInfo() {
        WareBusinessPointInfo wareBusinessPointInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessPointInfo = wareBusinessData.pointInfo) == null || TextUtils.isEmpty(wareBusinessPointInfo.OTCInfo)) ? "0" : this.mWareBusinessData.pointInfo.OTCInfo;
    }

    public String getOneBoxEntityId() {
        WareBusinessOnebox wareBusinessOnebox;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessOnebox = wareBusinessData.onebox) == null) ? "" : wareBusinessOnebox.entityId;
    }

    public String getOverSeaEventParam() {
        return "0";
    }

    public String getPDBenefitBeltImgUrl() {
        WarePromotionInfo warePromotionInfo;
        WareBusinessBenefitBeltEntity wareBusinessBenefitBeltEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePromotionInfo = wareBusinessData.promotionInfo) == null || (wareBusinessBenefitBeltEntity = warePromotionInfo.benefitBeltInfo) == null) ? "" : wareBusinessBenefitBeltEntity.imgUrl;
    }

    public PDInstallmentInfoEntity getPDInstallmentInfoEntity() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.installmentInfo;
        }
        return null;
    }

    public PDRegularBuyEntity getPDRegularBuyEntity() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.regularBuy;
        }
        return null;
    }

    public int getPackABTest() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null) {
            return 0;
        }
        return wareBusinessABTestInfo.packABTest;
    }

    public String getPaiPaiSecondLink() {
        BusinessPaiPaiSmallImgStockInfo businessPaiPaiSmallImgStockInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (businessPaiPaiSmallImgStockInfo = wareBusinessData.ppInfo) == null) ? "" : businessPaiPaiSmallImgStockInfo.ppBuyLink;
    }

    public String getPaperBook() {
        WareBusinessPointInfo wareBusinessPointInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessPointInfo = wareBusinessData.pointInfo) == null || TextUtils.isEmpty(wareBusinessPointInfo.paperBook)) ? "0" : this.mWareBusinessData.pointInfo.paperBook;
    }

    public JDJSONObject getPdBannerMdInfo() {
        PdBannerMdEntity pdBannerMdEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return JDJSON.parseObject((wareBusinessData == null || (pdBannerMdEntity = wareBusinessData.beltConfigCenter) == null) ? "" : pdBannerMdEntity.beltData);
    }

    public String getPdBannerMdInfoString() {
        PdBannerMdEntity pdBannerMdEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBannerMdEntity = wareBusinessData.beltConfigCenter) == null) ? "" : pdBannerMdEntity.beltData;
    }

    public String getPdCurrentMode() {
        return isElderTheme() ? "1" : isBoomer() ? "2" : "0";
    }

    public PDSizeHelperEntity getPdSizeHelperEntity() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.userSizeInfo;
        }
        return null;
    }

    public String getPlusFeeTipsStatus() {
        PDPlusFreightEntity pDPlusFreightEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDPlusFreightEntity = wareBusinessData.plusFeeTips) == null) ? "" : pDPlusFreightEntity.status;
    }

    public PDPlusFreightEntity getPlusFreightEntity() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.plusFeeTips;
        }
        return null;
    }

    public int getPlusIconType() {
        WarePlusInfo warePlusInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePlusInfo = wareBusinessData.plusInfo) == null) {
            return 0;
        }
        return warePlusInfo.iconType;
    }

    public String getPlusMakeMoreTimeShopId() {
        WareBusinessPlusForBuyMt wareBusinessPlusForBuyMt;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessPlusForBuyMt = wareBusinessData.makeMoreTime) == null || TextUtils.isEmpty(wareBusinessPlusForBuyMt.shopId)) {
            return null;
        }
        return this.mWareBusinessData.makeMoreTime.shopId;
    }

    public String getPlusPrice() {
        WarePlusInfo warePlusInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePlusInfo = wareBusinessData.plusInfo) == null || TextUtils.isEmpty(warePlusInfo.plusPrice)) ? "" : this.mWareBusinessData.plusInfo.plusPrice;
    }

    public int getPlusPriceType() {
        WarePlusInfo warePlusInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePlusInfo = wareBusinessData.plusInfo) == null) {
            return 0;
        }
        return warePlusInfo.priceType;
    }

    public String getPlusUrl() {
        WarePlusInfo warePlusInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePlusInfo = wareBusinessData.plusInfo) == null) ? "" : warePlusInfo.url;
    }

    public long getPreSaleTimeForYrq() {
        WareYureInfo wareYureInfo;
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null) {
            if (wareBusinessData == null || (wareYureInfo = wareBusinessData.yrqInfo) == null) {
                return 0L;
            }
            return wareYureInfo.presaleStartTime;
        }
        return wareYuShouInfo.presaleStartTime;
    }

    public PreferentialGuideEntity getPreferentialGuideEntity() {
        FloorTemplate templateById;
        if (isAggrePromoFloorShowed()) {
            if (isTemplateFormat()) {
                if (isElderTheme()) {
                    templateById = this.templateEntity.getTemplateById(FloorBussinessName.FB_BUSINESS_AGGRE_PROMO_ELDER);
                } else if (isTenthRevision()) {
                    templateById = this.templateEntity.getTemplateById(FloorBussinessName.FB_BUSINESS_AGGRE_PROMO_V10);
                    if (isFloorShowed(FloorBussinessName.FB_BUSINESS_AGGRE_PROMO_NEW)) {
                        templateById = this.templateEntity.getTemplateById(FloorBussinessName.FB_BUSINESS_AGGRE_PROMO_NEW);
                    }
                } else {
                    templateById = this.templateEntity.getTemplateById("bpAggrePromo");
                }
                if (templateById != null) {
                    Object obj = templateById.mData;
                    if (obj instanceof PreferentialGuideEntity) {
                        return (PreferentialGuideEntity) obj;
                    }
                    return null;
                }
                return null;
            }
            WareBusinessData wareBusinessData = this.mWareBusinessData;
            if (wareBusinessData == null || wareBusinessData.PreferentialGuide.getValue() == null) {
                return null;
            }
            return this.mWareBusinessData.PreferentialGuide.getValue();
        }
        return null;
    }

    public WarePriceInfo getPriceInfo() {
        WarePriceInfo warePriceInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePriceInfo = wareBusinessData.priceInfo) == null) {
            return null;
        }
        return warePriceInfo;
    }

    public String getPriceLabel() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || TextUtils.isEmpty(wareBusinessData.priceLabel)) ? "\u00a5" : this.mWareBusinessData.priceLabel;
    }

    public String getPrintBagParam() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || TextUtils.isEmpty(warePropertyInfo.isFlimPrint)) ? "0" : this.mWareBusinessData.property.isFlimPrint;
    }

    public String getPrintBagUrl() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return null;
        }
        return warePropertyInfo.printBagUrl;
    }

    public long getProSalesBeginTime() {
        WarePromotionInfo warePromotionInfo;
        WareBusinessPromotionNoticeEntity wareBusinessPromotionNoticeEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePromotionInfo = wareBusinessData.promotionInfo) == null || (wareBusinessPromotionNoticeEntity = warePromotionInfo.proSalesInfo) == null) {
            return 0L;
        }
        return wareBusinessPromotionNoticeEntity.beginTime;
    }

    public String getProSalesInfoImageUrl() {
        WarePromotionInfo warePromotionInfo;
        WareBusinessPromotionNoticeEntity wareBusinessPromotionNoticeEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePromotionInfo = wareBusinessData.promotionInfo) == null || (wareBusinessPromotionNoticeEntity = warePromotionInfo.proSalesInfo) == null) ? "" : wareBusinessPromotionNoticeEntity.imgUrl;
    }

    public WareBusinessJumpInfo getProSalesInfoJumpInfo() {
        WarePromotionInfo warePromotionInfo;
        WareBusinessPromotionNoticeEntity wareBusinessPromotionNoticeEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePromotionInfo = wareBusinessData.promotionInfo) == null || (wareBusinessPromotionNoticeEntity = warePromotionInfo.proSalesInfo) == null) {
            return null;
        }
        return wareBusinessPromotionNoticeEntity.jumpInfo;
    }

    public String getProSalesInfoToastSetover() {
        WarePromotionInfo warePromotionInfo;
        WareBusinessPromotionNoticeEntity wareBusinessPromotionNoticeEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePromotionInfo = wareBusinessData.promotionInfo) == null || (wareBusinessPromotionNoticeEntity = warePromotionInfo.proSalesInfo) == null) ? "" : wareBusinessPromotionNoticeEntity.toastSetover;
    }

    public String getProSalesInfoToastSuccess() {
        WarePromotionInfo warePromotionInfo;
        WareBusinessPromotionNoticeEntity wareBusinessPromotionNoticeEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePromotionInfo = wareBusinessData.promotionInfo) == null || (wareBusinessPromotionNoticeEntity = warePromotionInfo.proSalesInfo) == null) ? "" : wareBusinessPromotionNoticeEntity.toastSuccess;
    }

    public String getProductDetailName() {
        return !TextUtils.isEmpty(getProductName()) ? getProductName() : this.defaultName;
    }

    public String getProductName() {
        WareBasicInfo wareBasicInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBasicInfo = wareBusinessData.wareInfo) == null) ? "" : wareBasicInfo.name;
    }

    public String getPropertyType() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) ? "0" : warePropertyInfo.type;
    }

    public String getReasonTips() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return null;
        }
        return warePropertyInfo.reasonTips;
    }

    public WarePropertyTipsDetailEntity getReasonTipsDetail() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return null;
        }
        return warePropertyInfo.reasonTipsDetail;
    }

    public String getReasonTipsImg() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return null;
        }
        return warePropertyInfo.reasonTipsImg;
    }

    public String getReasonTipsUrl() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return null;
        }
        return warePropertyInfo.reasonTipsUrl;
    }

    public String getRechargeJumpLink() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessRechargeTypeInfo wareBusinessRechargeTypeInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || (wareBusinessRechargeTypeInfo = warePropertyInfo.rnMap) == null) ? "" : wareBusinessRechargeTypeInfo.rn;
    }

    public int getRechargeJumpType() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessRechargeTypeInfo wareBusinessRechargeTypeInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || (wareBusinessRechargeTypeInfo = warePropertyInfo.rnMap) == null || TextUtils.isEmpty(wareBusinessRechargeTypeInfo.type)) {
            return -1;
        }
        return PDUtils.getValue(this.mWareBusinessData.property.rnMap.type);
    }

    public String getRecommendType() {
        WareBusinessOnebox wareBusinessOnebox;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessOnebox = wareBusinessData.onebox) == null || TextUtils.isEmpty(wareBusinessOnebox.entityId) || !this.mWareBusinessData.onebox.isOneBoxEnable()) ? "" : this.mWareBusinessData.onebox.type;
    }

    public String getRegularBuyButtonContent() {
        PDRegularBuyEntity pDRegularBuyEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null) ? "" : pDRegularBuyEntity.buttonContent;
    }

    public String getRegularBuyButtonText() {
        PDRegularBuyEntity pDRegularBuyEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null) ? "" : pDRegularBuyEntity.buttonText;
    }

    public int getRegularBuyBuyRate() {
        PDRegularBuyEntity pDRegularBuyEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null) {
            return 0;
        }
        return pDRegularBuyEntity.buyRate;
    }

    public String getRegularBuyIsTimeOrder() {
        PDRegularBuyEntity pDRegularBuyEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null) ? "" : pDRegularBuyEntity.isTimeOrder;
    }

    public String getRegularBuyLeftButtonText() {
        PDRegularBuyEntity pDRegularBuyEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null) ? "" : pDRegularBuyEntity.leftButtonContent;
    }

    public String getRegularBuyType() {
        PDRegularBuyEntity pDRegularBuyEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null || TextUtils.isEmpty(pDRegularBuyEntity.isTimeOrder)) ? "0" : this.mWareBusinessData.regularBuy.isTimeOrder;
    }

    public long getRegularDate() {
        PDRegularBuyEntity pDRegularBuyEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null) {
            return 0L;
        }
        return pDRegularBuyEntity.date;
    }

    public String getRegularGoUrl() {
        PDRegularBuyEntity pDRegularBuyEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null) ? "" : pDRegularBuyEntity.goUrl;
    }

    public String getRegularTip() {
        PDRegularBuyEntity pDRegularBuyEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null) ? "" : pDRegularBuyEntity.tip;
    }

    public int getRightDdCount() {
        PdDdFatigueMechanism pdDdFatigueMechanism;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdDdFatigueMechanism = wareBusinessData.ddFatigueMechanism) == null) {
            return 5;
        }
        return PDUtils.stringToInt(pdDdFatigueMechanism.ddRightCount);
    }

    public String getSamNewSku() {
        WareSamInfo wareSamInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareSamInfo = wareBusinessData.samInfo) == null) ? "" : wareSamInfo.samNewSku;
    }

    public String getSamPrice() {
        WareSamInfo wareSamInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareSamInfo = wareBusinessData.samInfo) == null) ? "" : wareSamInfo.samPrice;
    }

    public WareScfSkuInfo getScfProductInfo() {
        WareScfSkuInfo wareScfSkuInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareScfSkuInfo = wareBusinessData.scfSkuInfo) == null) {
            return null;
        }
        return wareScfSkuInfo;
    }

    public String getSearchParam() {
        if (TextUtils.isEmpty(this.searchParam)) {
            return this.skuId;
        }
        return this.skuId + CartConstant.KEY_YB_INFO_LINK + this.searchParam;
    }

    public String getSeckillCertifyUrl() {
        PDSeckillCertifyEntity pDSeckillCertifyEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDSeckillCertifyEntity = wareBusinessData.idAuth) == null) ? "" : pDSeckillCertifyEntity.url;
    }

    public PDBottomBtn getSecond() {
        PDBottomInfo pDBottomInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pDBottomInfo = wareBusinessData.buttonInfo) == null) {
            return null;
        }
        return pDBottomInfo.second;
    }

    public String getSecondName() {
        PDBottomInfo pDBottomInfo;
        PDBottomBtn pDBottomBtn;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDBottomInfo = wareBusinessData.buttonInfo) == null || (pDBottomBtn = pDBottomInfo.second) == null) ? "" : pDBottomBtn.name;
    }

    public String getSecondNameBottomText() {
        PDBottomInfo pDBottomInfo;
        PDBottomBtn pDBottomBtn;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDBottomInfo = wareBusinessData.buttonInfo) == null || (pDBottomBtn = pDBottomInfo.second) == null) ? "" : pDBottomBtn.bottomText;
    }

    public String getSecondNameTopText() {
        PDBottomInfo pDBottomInfo;
        PDBottomBtn pDBottomBtn;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDBottomInfo = wareBusinessData.buttonInfo) == null || (pDBottomBtn = pDBottomInfo.second) == null) ? "" : pDBottomBtn.topText;
    }

    public ArrayList<NewGiftItem> getSelectedGiftItems() {
        updateSelectedGiftNum(getGiftPoolsData(this.giftPoolsList));
        return this.giftPoolSelect;
    }

    public boolean getSelectedPopUpAb() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.selectedPopUpAb) ? false : true;
    }

    public Map<String, String> getServiceIconType(JDJSONArray jDJSONArray) {
        HashMap hashMap = new HashMap();
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            try {
                JDJSONObject optJSONObject = jDJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    hashMap.put(optJSONObject.optString("iconType"), optJSONObject.optString("iconValue"));
                }
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(t.f20145j, e2.getMessage());
                }
            }
        }
        return hashMap;
    }

    public String getServiceSku() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null) ? "" : pDCarShopEntity.serviceSku;
    }

    public String getShareUrl() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) ? "" : warePropertyInfo.shareUrl;
    }

    public PdShopInfoEntity getShop() {
        PdShopEntity pdShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null) {
            return null;
        }
        return pdShopEntity.shop;
    }

    public String getShopABTest() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null) ? "" : wareBusinessABTestInfo.shopABTest;
    }

    public PdShopChatInfoEntity getShopChatInfo() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null) {
            return null;
        }
        return pdShopServiceEntity.chatInfo;
    }

    public PdShopEntity getShopEntity() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.shopInfo;
        }
        return null;
    }

    public String getShopId() {
        PdShopEntity pdShopEntity;
        PdShopInfoEntity pdShopInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopInfoEntity = pdShopEntity.shop) == null) {
            return null;
        }
        return pdShopInfoEntity.shopId;
    }

    public boolean getShowEntryHmIcon() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.showHongmengBtn;
    }

    public String getShowHotLinePhone() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        PdShopHotLineEntity pdShopHotLineEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (pdShopHotLineEntity = pdShopServiceEntity.hotLineInfo) == null) ? "" : pdShopHotLineEntity.hotLinePhoneExtend;
    }

    public String getShowPrice() {
        if (isYuShou()) {
            return getYuShouPrice();
        }
        String jdPrice = getJdPrice();
        if (jdPrice.equals(StringUtil.no_price)) {
            return jdPrice;
        }
        return "\u00a5" + jdPrice;
    }

    public int getSkuSource() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null) {
            return -1;
        }
        return wareBusinessABTestInfo.skuSource;
    }

    public String getSkuTag() {
        return getSkuTagType();
    }

    public String getSkuTagType() {
        String str;
        String str2;
        String str3;
        WareYuYueInfo wareYuYueInfo;
        WareBusinessSoldOverSea wareBusinessSoldOverSea;
        WareMiaoShaInfo wareMiaoShaInfo;
        if (!TextUtils.isEmpty(this.mSkuTag)) {
            return this.mSkuTag;
        }
        StringBuilder sb = new StringBuilder();
        if (isYuShou()) {
            String str4 = this.mWareBusinessData.YuShouInfo.yushouStepType;
            str4.hashCode();
            str = !str4.equals("5") ? this.mWareBusinessData.YuShouInfo.yushouStepType : "4";
            WareYuShouInfo wareYuShouInfo = this.mWareBusinessData.YuShouInfo;
            if (wareYuShouInfo.finalPaymentDecreaseFlag) {
                str2 = "1";
            } else {
                str2 = TextUtils.equals(wareYuShouInfo.yuShouladder, "1") ? "2" : "0";
            }
        } else {
            str = "0";
            str2 = str;
        }
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareMiaoShaInfo = wareBusinessData.miaoshaInfo) == null) {
            str3 = "0";
        } else {
            str3 = wareMiaoShaInfo.plusMiaoSha ? "3" : String.valueOf(getMiaoShaState());
        }
        sb.append(str3);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getIsOpType());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isJx() ? 1 : 0);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(str);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getFlashPurchaseType());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append("1".equals(getPropertyType()) ? 1 : 0);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isCustomSize() ? getCustomize() : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getDrugsParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isHeyue() ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(haveSamEntity() ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getPlusPriceType());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isgroupGoods() ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(!TextUtils.isEmpty(getLocServiceStatus()) ? getLocServiceStatus() : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(haveProSales() ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        WareBusinessData wareBusinessData2 = this.mWareBusinessData;
        sb.append((wareBusinessData2 == null || (wareBusinessSoldOverSea = wareBusinessData2.soldOversea) == null || wareBusinessSoldOverSea.soldOverseaService == null) ? "0" : "1");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getHasLargePromotionFlag());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(haveBenefitBelt() ? 1 : 0);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isYuyue() ? getYuyueType() : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getTrustworthy());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getYuyueAutoAddCartMtaParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isJZ());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getClothesMark());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getDiscountPrice());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isXSD());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        WareBusinessData wareBusinessData3 = this.mWareBusinessData;
        sb.append((wareBusinessData3 == null || !wareBusinessData3.isJoinBuyInfo()) ? "0" : "1");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isTopLife());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getJoinBuyParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getPinTuanParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getMiaoShaParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getPrintBagParam());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getYuYueWhitNowBuy());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getYuYueWhitAppointPrice());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isPop() ? "0" : "1");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(this.mLocChannel);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(str2);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getPlusProduct());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        WareBusinessData wareBusinessData4 = this.mWareBusinessData;
        sb.append((wareBusinessData4 == null || (wareYuYueInfo = wareBusinessData4.yuyueInfo) == null || !wareYuYueInfo.mad) ? 0 : 1);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getDaojiaFlag() ? "1" : "0");
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        WareBusinessData wareBusinessData5 = this.mWareBusinessData;
        sb.append((wareBusinessData5 == null || !wareBusinessData5.newStyle) ? 0 : 1);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        WareBusinessData wareBusinessData6 = this.mWareBusinessData;
        sb.append((wareBusinessData6 == null || !wareBusinessData6.isFreshTemplate()) ? 0 : 1);
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isPlus20Flag());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isThreeSuperFlag());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isV10Flag());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(isScfSku());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getGoodsStateBuriedPoint());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getTopMainFloorAbTest());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getSmartWareTag());
        sb.append(CartConstant.KEY_YB_INFO_LINK);
        sb.append(getSmartWareNotAbTag());
        String sb2 = sb.toString();
        this.mSkuTag = sb2;
        return sb2;
    }

    public PDSmartRecommendParam getSmartRecommendParam() {
        if (this.pdSmartRecommendParam == null) {
            this.pdSmartRecommendParam = new PDSmartRecommendParam();
        }
        return this.pdSmartRecommendParam;
    }

    public List<PDSopSkuInfoEntity> getSopSkuList() {
        WareBusinessStyleEntity wareBusinessStyleEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessStyleEntity = wareBusinessData.colorSizeInfo) == null) {
            return null;
        }
        return wareBusinessStyleEntity.skuList;
    }

    public String getSpotPreSaleAddCart() {
        WareYuShouInfo wareYuShouInfo;
        SpotPreSaleMap spotPreSaleMap;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || (spotPreSaleMap = wareYuShouInfo.yuShouSpotPreSaleMap) == null) ? "" : spotPreSaleMap.text1;
    }

    public String getSpotPreSaleDeposit() {
        WareYuShouInfo wareYuShouInfo;
        SpotPreSaleMap spotPreSaleMap;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || (spotPreSaleMap = wareYuShouInfo.yuShouSpotPreSaleMap) == null) ? "" : spotPreSaleMap.text2;
    }

    public long getStartTime() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null) {
            return 0L;
        }
        return wareYuYueInfo.startTime;
    }

    public String getStockNum() {
        WareStockEntity wareStockEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareStockEntity = wareBusinessData.stockNode) == null) ? "" : wareStockEntity.stockNum;
    }

    public String getStoreId() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null) ? "" : pDCarShopEntity.storeId;
    }

    public int getStoreType() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.getStoreType();
        }
        return -1;
    }

    public ArrayList<BaseTemplateEntity> getStyleFloors() {
        return this.mStyleViewEntity;
    }

    public int getSuitABTest() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return 0;
        }
        return warePropertyInfo.suitABTest;
    }

    public String getSurveyIcon() {
        SurveyData surveyData;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (surveyData = wareBusinessData.surveyDO) == null) ? "" : surveyData.icon;
    }

    public String getSurveyTxt() {
        SurveyData surveyData;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (surveyData = wareBusinessData.surveyDO) == null) ? "" : surveyData.text;
    }

    public String getSurveyType() {
        SurveyData surveyData;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (surveyData = wareBusinessData.surveyDO) == null) ? "" : surveyData.type;
    }

    public String getSurveyUrl() {
        SurveyData surveyData;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (surveyData = wareBusinessData.surveyDO) == null) ? "" : surveyData.url;
    }

    public String getThreeDSwitch() {
        WareBusinessPointInfo wareBusinessPointInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessPointInfo = wareBusinessData.pointInfo) == null || TextUtils.isEmpty(wareBusinessPointInfo.threeDSwitch)) ? "0" : this.mWareBusinessData.pointInfo.threeDSwitch;
    }

    public String getTipsBtn() {
        PDPlusFreightEntity pDPlusFreightEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDPlusFreightEntity = wareBusinessData.plusFeeTips) == null) ? "" : pDPlusFreightEntity.tipsBtn;
    }

    public String getTipsBtnLink() {
        PDPlusFreightEntity pDPlusFreightEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDPlusFreightEntity = wareBusinessData.plusFeeTips) == null) ? "" : pDPlusFreightEntity.tipsBtnLink;
    }

    public String getTipsTxt() {
        PDPlusFreightEntity pDPlusFreightEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDPlusFreightEntity = wareBusinessData.plusFeeTips) == null) ? "" : pDPlusFreightEntity.tipsTxt;
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0018  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x00b4 A[EDGE_INSN: B:150:0x00b4->B:145:0x00b4 BREAK  A[LOOP:0: B:137:0x0074->B:144:0x00b1], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JDJSONObject getToOrderParam() {
        JDJSONObject jDJSONObject;
        WareBusinessData wareBusinessData;
        WareBusinessData wareBusinessData2;
        WareBusinessFastMailEntity wareBusinessFastMailEntity;
        int i2;
        LayerFlagExperiment layerFlagExperiment;
        Map map;
        WareBusinessData wareBusinessData3 = this.mWareBusinessData;
        if (wareBusinessData3 != null && !TextUtils.isEmpty(wareBusinessData3.extFlag)) {
            try {
                jDJSONObject = JDJSON.parseObject(this.mWareBusinessData.extFlag);
            } catch (Exception unused) {
            }
            if (jDJSONObject == null) {
                jDJSONObject = new JDJSONObject();
            }
            jDJSONObject.put("carModelId", (Object) this.mCurModelId);
            if (isShowHealthOnLine() && !TextUtils.isEmpty(this.serviceInfoId)) {
                jDJSONObject.put("jkfw", (Object) this.serviceInfoId);
            }
            if (!TextUtils.isEmpty(this.jumpOrderTypeCode) && !TextUtils.equals(PdJumpOrderBusinessType.DEFAULT_CODE, this.jumpOrderTypeCode)) {
                jDJSONObject.put("skuBusinessType", (Object) String.valueOf(this.jumpOrderTypeCode));
            }
            wareBusinessData = this.mWareBusinessData;
            if (wareBusinessData != null && (layerFlagExperiment = wareBusinessData.layerFlagExperiment) != null && (map = layerFlagExperiment.quickTradeMap) != null) {
                jDJSONObject.put("quickTradeMap", (Object) map);
            }
            wareBusinessData2 = this.mWareBusinessData;
            if (wareBusinessData2 != null && (wareBusinessFastMailEntity = wareBusinessData2.fastMailInfo) != null && wareBusinessFastMailEntity.serviceList != null) {
                i2 = 0;
                while (true) {
                    if (i2 < this.mWareBusinessData.fastMailInfo.serviceList.size()) {
                        if (this.mWareBusinessData.fastMailInfo.serviceList.get(i2) != null && this.mWareBusinessData.fastMailInfo.serviceList.get(i2).selected == 1) {
                            jDJSONObject.put("deliveryMode", (Object) this.mWareBusinessData.fastMailInfo.serviceList.get(i2).deliveryMode);
                            break;
                        }
                        i2++;
                    } else {
                        break;
                    }
                }
            }
            return jDJSONObject;
        }
        jDJSONObject = null;
        if (jDJSONObject == null) {
        }
        jDJSONObject.put("carModelId", (Object) this.mCurModelId);
        if (isShowHealthOnLine()) {
            jDJSONObject.put("jkfw", (Object) this.serviceInfoId);
        }
        if (!TextUtils.isEmpty(this.jumpOrderTypeCode)) {
            jDJSONObject.put("skuBusinessType", (Object) String.valueOf(this.jumpOrderTypeCode));
        }
        wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            jDJSONObject.put("quickTradeMap", (Object) map);
        }
        wareBusinessData2 = this.mWareBusinessData;
        if (wareBusinessData2 != null) {
            i2 = 0;
            while (true) {
                if (i2 < this.mWareBusinessData.fastMailInfo.serviceList.size()) {
                }
                i2++;
            }
        }
        return jDJSONObject;
    }

    public String getTokenPrice() {
        WarePriceInfo warePriceInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePriceInfo = wareBusinessData.priceInfo) == null) ? "" : warePriceInfo.tokenPrice;
    }

    public WareBusinessMagicHeadPicInfoEntity getTopImageBanDanData() {
        return getTopImageBanDanData(WareBusinessMagicHeadPicInfoEntity.ANCHORTYPE_COMMENT_BANG_DAN);
    }

    public WareBusinessMagicHeadPicInfoEntity getTopImageGiftData() {
        List<WareBusinessMagicHeadPicInfoEntity> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = null;
        if (wareBusinessData != null && (list = wareBusinessData.magicHeadPicInfo) != null && !list.isEmpty()) {
            for (WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity2 : this.mWareBusinessData.magicHeadPicInfo) {
                if (wareBusinessMagicHeadPicInfoEntity2 != null && TextUtils.equals(wareBusinessMagicHeadPicInfoEntity2.anchorType, "gift")) {
                    wareBusinessMagicHeadPicInfoEntity = wareBusinessMagicHeadPicInfoEntity2;
                }
            }
        }
        return wareBusinessMagicHeadPicInfoEntity;
    }

    public List<TopTabAnchor> getTopTabAnchor() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.topTabAnchor;
        }
        return null;
    }

    public String getTradeInToastBiz() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            WareBusinessPaiPaiReplacement wareBusinessPaiPaiReplacement = null;
            WareBusinessPaiPaiReplacement wareBusinessPaiPaiReplacement2 = wareBusinessData.homeApplianceYjhxInfo;
            if (wareBusinessPaiPaiReplacement2 != null) {
                wareBusinessPaiPaiReplacement = wareBusinessPaiPaiReplacement2;
            } else {
                WareBusinessPaiPaiReplacement wareBusinessPaiPaiReplacement3 = wareBusinessData.paiPaiOld4New;
                if (wareBusinessPaiPaiReplacement3 != null) {
                    wareBusinessPaiPaiReplacement = wareBusinessPaiPaiReplacement3;
                }
            }
            if (wareBusinessPaiPaiReplacement != null) {
                return WareBusinessPaiPaiReplacement.getToastBiz(wareBusinessPaiPaiReplacement.bizCode, wareBusinessPaiPaiReplacement.tradeType);
            }
        }
        return "-100";
    }

    public String getTreatyInfo() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return null;
        }
        return warePropertyInfo.treatyIntroText;
    }

    public String getTreatyIntroUrl() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return null;
        }
        return warePropertyInfo.treatyIntroUrl;
    }

    public ArrayList<TreayNewInfo> getTreayNewInfo() {
        ArrayList<TreayNewInfo> arrayList;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (arrayList = wareBusinessData.treayNewInfo) == null) {
            return null;
        }
        return arrayList;
    }

    public String getTurnUrl() {
        WareBusinessTurnToH5 wareBusinessTurnToH5;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessTurnToH5 = wareBusinessData.turnToH5) == null) ? "" : wareBusinessTurnToH5.turnUrl;
    }

    public ProductToJsNowBuyEntity getType4Entity() {
        ProductToJsNowBuyEntity productToJsNowBuyEntity = this.mType4Entity;
        if (productToJsNowBuyEntity != null) {
            productToJsNowBuyEntity.type = 4;
            WareBusinessData wareBusinessData = this.mWareBusinessData;
            String str = "";
            productToJsNowBuyEntity.showMsg = (wareBusinessData == null || TextUtils.isEmpty(wareBusinessData.rankName)) ? "" : this.mWareBusinessData.rankName;
            ProductToJsNowBuyEntity productToJsNowBuyEntity2 = this.mType4Entity;
            WareBusinessData wareBusinessData2 = this.mWareBusinessData;
            if (wareBusinessData2 != null && !TextUtils.isEmpty(String.valueOf(wareBusinessData2.rankType))) {
                str = String.valueOf(this.mWareBusinessData.rankType);
            }
            productToJsNowBuyEntity2.necessaryKey = str;
        }
        return this.mType4Entity;
    }

    public String getUrlWithGift(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        ArrayList<NewGiftItem> selectedGiftItems = getSelectedGiftItems();
        if (selectedGiftItems != null && selectedGiftItems.size() > 0) {
            sb.append("&giftInfo=");
            for (int i2 = 0; i2 < selectedGiftItems.size(); i2++) {
                NewGiftItem newGiftItem = selectedGiftItems.get(i2);
                if (newGiftItem != null) {
                    if (i2 == selectedGiftItems.size() - 1) {
                        sb.append(newGiftItem.Id);
                        sb.append("|");
                        sb.append(newGiftItem.Num);
                    } else {
                        sb.append(newGiftItem.Id);
                        sb.append("|");
                        sb.append(newGiftItem.Num);
                        sb.append(DYConstants.DY_REGEX_COMMA);
                    }
                }
            }
        }
        return sb.toString();
    }

    public String getVenderId() {
        WareBasicInfo wareBasicInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBasicInfo = wareBusinessData.wareInfo) == null) ? "" : wareBasicInfo.venderId;
    }

    public String getVirtualPhoneNumber() {
        PdAhStoreEntity pdAhStoreEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdAhStoreEntity = wareBusinessData.daojiaStoreInfo) == null) ? "" : pdAhStoreEntity.virtualPhoneNumber;
    }

    public Ware3cServerEntity getWare3cFactoryServerEntity() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.originalFactoryServiceInfo;
        }
        return null;
    }

    public Ware3cServerNewEntity getWare3cFactoryServerEntityNew() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.threeCServiceInfo;
        }
        return null;
    }

    public String getWare3cServiceIds() {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList = this.ware3cServerIdsSelect;
        if (arrayList != null && !arrayList.isEmpty()) {
            int size = this.ware3cServerIdsSelect.size();
            for (int i2 = 0; i2 < size; i2++) {
                String str = this.ware3cServerIdsSelect.get(i2);
                if (!TextUtils.isEmpty(str)) {
                    sb.append(str);
                    if (i2 != size - 1) {
                        sb.append(DYConstants.DY_REGEX_COMMA);
                    }
                }
            }
        }
        return sb.toString();
    }

    public List<WareBusinessFeeInfo> getWareBusinessFeeInfo() {
        WareBusinessProductFeeInfo wareBusinessProductFeeInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessProductFeeInfo = wareBusinessData.feeInfo) == null) {
            return null;
        }
        return wareBusinessProductFeeInfo.treatyList;
    }

    public WareBusinessPaiPaiReplacement getWareBusinessPaiPaiReplacement() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.paiPaiOld4New;
        }
        return null;
    }

    public WareEyeSightInfo getWareEyeSightInfo() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.wareEyeSight;
        }
        return null;
    }

    public WareFurnitureInfo getWareFurnitureInfo() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.furnitureIncrementServiceInfo;
        }
        return null;
    }

    public WareJdServerPlusEntity getWareJdServerPlusEntity() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.jdSerPlusInfo;
        }
        return null;
    }

    public WareYanBaoEntity getWareYanBaoEntity() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.yanBaoInfo;
        }
        return null;
    }

    public String getXpFloorAbInfo(boolean z) {
        AbBuriedExpLabelsEntity abBuriedExpLabelsEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (abBuriedExpLabelsEntity = wareBusinessData.abBuriedExpLabelsNEW) == null) {
            return "";
        }
        if (z) {
            return abBuriedExpLabelsEntity.xpGrassShow;
        }
        return abBuriedExpLabelsEntity.xpBreakNews;
    }

    public String getYanBaoDetailUrl() {
        WareYanBaoEntity wareYanBaoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYanBaoEntity = wareBusinessData.yanBaoInfo) == null) ? "" : wareYanBaoEntity.yanBaoDetailUrl;
    }

    public List<WareBusinessYanBaoGroupEntity> getYanBaoList() {
        WareYanBaoEntity wareYanBaoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareYanBaoEntity = wareBusinessData.yanBaoInfo) == null) {
            return null;
        }
        return wareYanBaoEntity.yanBaoList;
    }

    public String getYuShouNumOfPeople() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null) ? "" : wareYuShouInfo.yuShouNumOfPeople;
    }

    public String getYuShouPrice() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null) ? "" : wareYuShouInfo.yuShouPrice;
    }

    public String getYuShouType() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || TextUtils.isEmpty(wareYuShouInfo.yuShouType)) ? "" : this.mWareBusinessData.YuShouInfo.yuShouType;
    }

    public String getYuShouladder() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null) ? "" : wareYuShouInfo.yuShouladder;
    }

    public long getYuYueEndTime() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null) {
            return -1L;
        }
        return getTimeValue(wareYuYueInfo.yuYueEndTime);
    }

    public long getYuYueStartTime() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null) {
            return -1L;
        }
        return getTimeValue(wareYuYueInfo.yuYueStartTime);
    }

    public String getYuYueWhitAppointPrice() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || TextUtils.isEmpty(wareYuYueInfo.appointPrice)) ? "0" : "1";
    }

    public String getYuYueWhitNowBuy() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || !wareYuYueInfo.isYuYue) ? "0" : (!wareYuYueInfo.isAdvanceBuy() || TextUtils.isEmpty(this.mWareBusinessData.yuyueInfo.appointPrice)) ? "1" : "2";
    }

    public String getYuyueAsynBody() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.asyncInterfaceBody;
        }
        return null;
    }

    public String getYuyueAutoAddCartMtaParam() {
        return !isYuyue() ? "0" : isYuyueAutoAddCart() ? "2" : "1";
    }

    public String getYuyueInfoBtn2ndText() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || TextUtils.isEmpty(wareYuYueInfo.btn2ndText)) ? "" : this.mWareBusinessData.yuyueInfo.btn2ndText;
    }

    public String getYuyueInfoType() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || TextUtils.isEmpty(wareYuYueInfo.type)) {
            return null;
        }
        return this.mWareBusinessData.yuyueInfo.type;
    }

    public String getYuyueShowCode() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null) ? "" : wareYuYueInfo.yuyueShowCode;
    }

    public String getYuyueType() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || TextUtils.isEmpty(wareYuYueInfo.yuyueType)) ? "5" : this.mWareBusinessData.yuyueInfo.yuyueType;
    }

    public String geteyeSightEndUrl() {
        WareEyeSightInfo wareEyeSightInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareEyeSightInfo = wareBusinessData.wareEyeSight) == null || TextUtils.isEmpty(wareEyeSightInfo.eyeSightEndUrl)) ? "" : this.mWareBusinessData.wareEyeSight.eyeSightEndUrl;
    }

    public boolean has3cFactoryServer() {
        Ware3cServerEntity ware3cServerEntity;
        List<WareBusinessPlusListEntity> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (ware3cServerEntity = wareBusinessData.originalFactoryServiceInfo) == null || (list = ware3cServerEntity.serviceList) == null || list.isEmpty()) ? false : true;
    }

    public boolean has3cFactoryServerNew() {
        Ware3cServerNewEntity ware3cServerNewEntity;
        List<Ware3cServerEntity> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (ware3cServerNewEntity = wareBusinessData.threeCServiceInfo) == null || (list = ware3cServerNewEntity.item) == null || list.isEmpty()) ? false : true;
    }

    public boolean hasAHPhone() {
        PdAhStoreEntity pdAhStoreEntity;
        List<CustomerServiceDetailInfo> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdAhStoreEntity = wareBusinessData.daojiaStoreInfo) == null || (list = pdAhStoreEntity.customerServiceList) == null || list.isEmpty()) ? false : true;
    }

    public boolean hasFurniture() {
        WareFurnitureInfo wareFurnitureInfo;
        List<WareBusinessFurnitureGroupEntity> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareFurnitureInfo = wareBusinessData.furnitureIncrementServiceInfo) == null || (list = wareFurnitureInfo.fiInfo) == null || list.isEmpty()) ? false : true;
    }

    public boolean hasJdServerPlus() {
        WareJdServerPlusEntity wareJdServerPlusEntity;
        List<WareBusinessPlusListEntity> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareJdServerPlusEntity = wareBusinessData.jdSerPlusInfo) == null || (list = wareJdServerPlusEntity.jdSerPlusList) == null || list.isEmpty()) ? false : true;
    }

    public boolean hasJpsAgreement() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || wareBusinessData.jpsAgreement == null) ? false : true;
    }

    public boolean hasShop() {
        PdShopEntity pdShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || pdShopEntity.shop == null) ? false : true;
    }

    public boolean hasToHandPrice() {
        PdToHandPriceEntity pdToHandPriceEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdToHandPriceEntity = wareBusinessData.toHandssSrengthen) == null || TextUtils.isEmpty(pdToHandPriceEntity.toHandsPrice)) ? false : true;
    }

    public boolean hasYanBao() {
        WareYanBaoEntity wareYanBaoEntity;
        List<WareBusinessYanBaoGroupEntity> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYanBaoEntity = wareBusinessData.yanBaoInfo) == null || (list = wareYanBaoEntity.yanBaoList) == null || list.isEmpty()) ? false : true;
    }

    public boolean haveBenefitBelt() {
        WarePromotionInfo warePromotionInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePromotionInfo = wareBusinessData.promotionInfo) == null || warePromotionInfo.benefitBeltInfo == null) ? false : true;
    }

    public boolean haveDdDrag() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || pdShopServiceEntity.ddDrag == null) ? false : true;
    }

    public boolean haveProSales() {
        WarePromotionInfo warePromotionInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePromotionInfo = wareBusinessData.promotionInfo) == null || warePromotionInfo.proSalesInfo == null) ? false : true;
    }

    public boolean haveSamEntity() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || wareBusinessData.samInfo == null) ? false : true;
    }

    public boolean haveWareBusinessFeeInfo() {
        WareBusinessProductFeeInfo wareBusinessProductFeeInfo;
        List<WareBusinessFeeInfo> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessProductFeeInfo = wareBusinessData.feeInfo) == null || (list = wareBusinessProductFeeInfo.treatyList) == null || list.isEmpty()) ? false : true;
    }

    public boolean haveWhiteBarInfo() {
        PdWhiteBarForStyleInfoEntity pdWhiteBarForStyleInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdWhiteBarForStyleInfoEntity = wareBusinessData.proPageWhiteBarInfo) == null || TextUtils.isEmpty(pdWhiteBarForStyleInfoEntity.mkt)) ? false : true;
    }

    public void initCustomizeSelectData() {
        CustomizeInfoEntity customizeInfoEntity;
        List<CustomizeServicesEntity> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (customizeInfoEntity = wareBusinessData.customizeInfo) == null || (list = customizeInfoEntity.customizeServices) == null) {
            return;
        }
        if (list.size() > 0 && customizeInfoEntity.customizeServices.get(0) != null) {
            customizeInfoEntity.customizeServices.get(0).nativeSelected = true;
        }
        if (customizeInfoEntity.customizeServices.size() <= 1 || customizeInfoEntity.customizeServices.get(1) == null) {
            return;
        }
        customizeInfoEntity.customizeServices.get(1).nativeSelected = false;
    }

    public void initPerformanceControlConfig() {
        if (TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDProductdetail", "monitorSwitch", "performanceMonitorSwitch"), "1")) {
            this.isperformanceOpen = true;
        }
    }

    public boolean isAddCardListBtnForceLayer() {
        PDECardInfoEntity pDECardInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDECardInfoEntity = wareBusinessData.eCardInfo) == null || !pDECardInfoEntity.listForceLayer) ? false : true;
    }

    public boolean isAddCartVirtual() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null || !pdBuyByMEntity.isVirtual) ? false : true;
    }

    public boolean isAggrePromoFloorShowed() {
        if (isTemplateFormat()) {
            if (isElderTheme()) {
                return isFloorShowed(FloorBussinessName.FB_BUSINESS_AGGRE_PROMO_ELDER);
            }
            if (isTenthRevision()) {
                if (isFloorShowed(FloorBussinessName.FB_BUSINESS_AGGRE_PROMO_NEW)) {
                    return true;
                }
                return isFloorShowed(FloorBussinessName.FB_BUSINESS_AGGRE_PROMO_V10);
            }
            return isFloorShowed("bpAggrePromo");
        }
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || wareBusinessData.PreferentialGuide.getValue() == null) ? false : true;
    }

    public boolean isArchived() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessData.archiveSku == null && wareBusinessData.riskArchive == null)) ? false : true;
    }

    public boolean isBoomer() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.boomer;
    }

    public boolean isBrandMember() {
        BrandMemberInfo brandMemberInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (brandMemberInfo = wareBusinessData.brandMemberInfo) == null || TextUtils.isEmpty(brandMemberInfo.btnText)) ? false : true;
    }

    public boolean isBubbleHv() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        PdShopChatInfoEntity pdShopChatInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (pdShopChatInfoEntity = pdShopServiceEntity.chatInfo) == null || !pdShopChatInfoEntity.isBubbleHV) ? false : true;
    }

    public boolean isBusinessgroupGoods(WareBusinessSkuPropertyItem wareBusinessSkuPropertyItem) {
        return wareBusinessSkuPropertyItem != null && wareBusinessSkuPropertyItem.type == 1;
    }

    public boolean isButtonEntrance() {
        PdGiftShopEntity pdGiftShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdGiftShopEntity = wareBusinessData.giftShopping) == null || pdGiftShopEntity.buttonEntrance == null) ? false : true;
    }

    public boolean isBuyTime() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || !wareYuYueInfo.isbuyTime) ? false : true;
    }

    public boolean isCanBuy() {
        return this.mBasicInfo != null && cartFlag();
    }

    public boolean isCanHasRecommendTab() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && (TextUtils.equals(wareBusinessData.floorZoneStyle, "1") || TextUtils.equals(this.mWareBusinessData.floorZoneStyle, "2"));
    }

    public boolean isCanShare() {
        WareBusinessTurnToH5 wareBusinessTurnToH5;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessTurnToH5 = wareBusinessData.turnToH5) == null || !wareBusinessTurnToH5.isCanShare) ? false : true;
    }

    public boolean isCarShopHashave() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null || !pDCarShopEntity.hasHave || pDCarShopEntity.recLocShop == null) ? false : true;
    }

    public boolean isCartRecommend() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isCartRecommend) ? false : true;
    }

    public boolean isCartShield() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isCartShield) ? false : true;
    }

    public boolean isChangeAddress() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || warePropertyInfo.fromType != 1) ? false : true;
    }

    public boolean isChangeArea(String str) {
        WareSamInfo wareSamInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareSamInfo = wareBusinessData.samInfo) == null || TextUtils.isEmpty(wareSamInfo.samNewSku) || TextUtils.equals(this.mWareBusinessData.samInfo.samNewSku, str)) ? false : true;
    }

    public boolean isCloseSmartWebDetail() {
        AppStaticInfo appStaticInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (appStaticInfo = wareBusinessData.appStaticInfo) == null || !appStaticInfo.androidIsCloseSmartDetail) ? false : true;
    }

    public boolean isCollect() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isCollect) ? false : true;
    }

    public boolean isCustomSize() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null || !TextUtils.equals("customize", pdBuyByMEntity.addCartBusinessName)) ? false : true;
    }

    public boolean isCustomizeSelect() {
        CustomizeInfoEntity customizeInfoEntity;
        List<CustomizeServicesEntity> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (customizeInfoEntity = wareBusinessData.customizeInfo) == null || (list = customizeInfoEntity.customizeServices) == null || list.size() <= 1 || customizeInfoEntity.customizeServices.get(1) == null) {
            return false;
        }
        return customizeInfoEntity.customizeServices.get(1).nativeSelected;
    }

    public boolean isDJBusiness() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.daojiaFlag || wareBusinessData.daojiaStoreInfo == null) ? false : true;
    }

    public boolean isDarkTheme() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.darkModel) ? false : true;
    }

    public boolean isDetailListOn() {
        WareBusinessIntroAlbumEntity wareBusinessIntroAlbumEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessIntroAlbumEntity = wareBusinessData.introAlbum) == null || !wareBusinessIntroAlbumEntity.on) ? false : true;
    }

    public boolean isDoubleButton() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null || !pdBuyByMEntity.doubleButton) ? false : true;
    }

    public boolean isDoublePriceBetween() {
        SecondPriceEntity secondPriceEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (secondPriceEntity = wareBusinessData.secondPriceInfo) == null || !secondPriceEntity.rangePriceFlag) ? false : true;
    }

    public boolean isDrug() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || wareBusinessData.drugInfo == null) ? false : true;
    }

    public boolean isECardBuy() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || wareBusinessData.eCardInfo == null) ? false : true;
    }

    public boolean isElderTheme() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.leVieuxFusil;
    }

    public String isEyeSightDiscount() {
        WareEyeSightInfo wareEyeSightInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareEyeSightInfo = wareBusinessData.wareEyeSight) == null || TextUtils.isEmpty(wareEyeSightInfo.priceReduce)) ? "0" : "1";
    }

    public boolean isFans() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isFans) ? false : true;
    }

    public boolean isFlashBuying() {
        WareFlashInfoEntity wareFlashInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareFlashInfoEntity = wareBusinessData.flashInfo) == null || wareFlashInfoEntity.state != 2) ? false : true;
    }

    public boolean isForceLayer() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null || !pdBuyByMEntity.forceLayer) ? false : true;
    }

    public boolean isFullPriceYuShou() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || !TextUtils.equals("1", wareYuShouInfo.yushouStepType)) ? false : true;
    }

    public boolean isFxyl() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isFxyl) ? false : true;
    }

    public boolean isGameTools() {
        String[] strArr;
        BasicInfo basicInfo = this.mBasicInfo;
        return (basicInfo == null || (strArr = basicInfo.categoryIds) == null || !"12276".equals(strArr[2])) ? false : true;
    }

    public boolean isGetCommentCount() {
        return isSmartWaresComment() || isLongImageStructure();
    }

    public boolean isGoldPurchaseSelect() {
        WareGoldPurchaseEntity wareGoldPurchaseEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareGoldPurchaseEntity = wareBusinessData.goldPurchase) == null || !wareGoldPurchaseEntity.isSelected) ? false : true;
    }

    public boolean isHasBestCoupon() {
        return getBestCoupon().size() > 0 || getBestCouponReceiveData(true).size() > 0;
    }

    public boolean isHasBottomMatchShopList() {
        WareBusinessFreshBCMatchInfo wareBusinessFreshBCMatchInfo;
        List<String> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessFreshBCMatchInfo = wareBusinessData.freshBCMatchInfo) == null || (list = wareBusinessFreshBCMatchInfo.imageList) == null || list.size() <= 0) ? false : true;
    }

    public boolean isHasYanBaoCoupon() {
        WareYanBaoEntity wareYanBaoEntity;
        WareYanBaoCoupon wareYanBaoCoupon;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYanBaoEntity = wareBusinessData.yanBaoInfo) == null || (wareYanBaoCoupon = wareYanBaoEntity.yanBaoCoupon) == null || TextUtils.isEmpty(wareYanBaoCoupon.yanBaoCouponLinkUrl) || TextUtils.isEmpty(this.mWareBusinessData.yanBaoInfo.yanBaoCoupon.yanBaoCouponText)) ? false : true;
    }

    public boolean isHaveDeliveryInstall() {
        PDDeliveryInstallEntity pDDeliveryInstallEntity;
        ArrayList<PDDeliveryOptionEntity> arrayList;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDDeliveryInstallEntity = wareBusinessData.deliveryInstall) == null || (arrayList = pDDeliveryInstallEntity.optionList) == null || arrayList.isEmpty()) ? false : true;
    }

    public boolean isHaveEyeSight() {
        WareEyeSightInfo wareEyeSightInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareEyeSightInfo = wareBusinessData.wareEyeSight) == null || !wareEyeSightInfo.isHaveEyeSight) ? false : true;
    }

    public boolean isHaveRecommend() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.recTabEnable) ? false : true;
    }

    public boolean isHealthImmediateBuy() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.isHealthImmediateBuy;
    }

    public boolean isHeyue() {
        WareBusinessProductFeeInfo wareBusinessProductFeeInfo;
        List<WareBusinessFeeInfo> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessProductFeeInfo = wareBusinessData.feeInfo) == null || (list = wareBusinessProductFeeInfo.treatyList) == null || list.isEmpty()) {
            return false;
        }
        for (WareBusinessFeeInfo wareBusinessFeeInfo : this.mWareBusinessData.feeInfo.treatyList) {
            if (wareBusinessFeeInfo != null && TextUtils.equals(wareBusinessFeeInfo.sku, this.skuId)) {
                return !TextUtils.equals(wareBusinessFeeInfo.type, "1");
            }
        }
        return false;
    }

    public boolean isHideAdd2CardErrorToast() {
        return isYuyueAutoAddCart();
    }

    public boolean isHideAdd2CardOKToast() {
        return isCartRecommend() || isYuyueAutoAddCart() || this.isOverseaWhiteBar || recommendPopup();
    }

    public boolean isHideAddCart() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.termiteCartShield;
    }

    public boolean isHighPd() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.cccHighEnd;
    }

    public boolean isHyjFloorShowed() {
        if (isTemplateFormat()) {
            if (isElderTheme()) {
                return isFloorShowed("bpHeyuejiguige");
            }
            if (isTenthRevision()) {
                return isFloorShowed(FloorBussinessName.FB_BUSINESS_HYJ_v10);
            }
            return isFloorShowed("bpHeyuejiguige");
        }
        return false;
    }

    public boolean isJbyCarNewStyle() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null || !pDCarShopEntity.carNewFlag) ? false : true;
    }

    public boolean isJbyarGrayFlagStyle() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.carGrayFlag;
    }

    public boolean isJingXi() {
        WareBusinessCollageJoinBuyInfoEntity wareBusinessCollageJoinBuyInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCollageJoinBuyInfoEntity = wareBusinessData.joinBuyInfo) == null || !wareBusinessCollageJoinBuyInfoEntity.jingxiFlag) ? false : true;
    }

    public boolean isJx() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isJx) ? false : true;
    }

    public boolean isLiveOnLine() {
        PDWebCastEntity pDWebCastEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDWebCastEntity = wareBusinessData.webCast) == null || (!TextUtils.equals(pDWebCastEntity.styleType, "1") && !TextUtils.equals(this.mWareBusinessData.webCast.styleType, "2") && !TextUtils.equals(this.mWareBusinessData.webCast.styleType, "3"))) ? false : true;
    }

    public boolean isLivePre() {
        PDWebCastEntity pDWebCastEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDWebCastEntity = wareBusinessData.webCast) == null || !TextUtils.equals(pDWebCastEntity.styleType, "4")) ? false : true;
    }

    public boolean isLiveReplay() {
        PDWebCastEntity pDWebCastEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDWebCastEntity = wareBusinessData.webCast) == null || !TextUtils.equals(pDWebCastEntity.styleType, "5")) ? false : true;
    }

    public boolean isLocAddCartflag() {
        LocInfo locInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (locInfo = wareBusinessData.locInfo) == null || !locInfo.locAddCartflag) ? false : true;
    }

    public boolean isLocFwlx() {
        LocInfo locInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (locInfo = wareBusinessData.locInfo) == null || !locInfo.isImmediatelyBuy) ? false : true;
    }

    public boolean isLocWithType2() {
        LocInfo locInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (locInfo = wareBusinessData.locInfo) == null || !"2".equals(locInfo.serviceStatus)) ? false : true;
    }

    public boolean isLocWithType3() {
        LocInfo locInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (locInfo = wareBusinessData.locInfo) == null || !"3".equals(locInfo.serviceStatus)) ? false : true;
    }

    public boolean isLocWithlType1() {
        LocInfo locInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (locInfo = wareBusinessData.locInfo) == null || !"1".equals(locInfo.serviceStatus)) ? false : true;
    }

    public boolean isLongHeadImage() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.magicHeadPicType == 1;
    }

    public boolean isLongImageStructure() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.magicHeadPicType == 2;
    }

    public boolean isLuxuryService() {
        PdShopEntity pdShopEntity;
        PdShopServiceEntity pdShopServiceEntity;
        PdShopHotLineEntity pdShopHotLineEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopServiceEntity = pdShopEntity.customerService) == null || (pdShopHotLineEntity = pdShopServiceEntity.hotLineInfo) == null || !pdShopHotLineEntity.isLuxuryService()) ? false : true;
    }

    public String isMad() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || !wareYuYueInfo.mad) ? "0" : "1";
    }

    public boolean isMainPriceBetween() {
        WarePriceInfo warePriceInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePriceInfo = wareBusinessData.priceInfo) == null || !warePriceInfo.rangePriceFlag) ? false : true;
    }

    public boolean isMedicalInstrument() {
        WareBusinessHealthAppoint wareBusinessHealthAppoint;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessHealthAppoint = wareBusinessData.jHealthAppoint) == null || TextUtils.isEmpty(wareBusinessHealthAppoint.buttonName) || TextUtils.isEmpty(this.mWareBusinessData.jHealthAppoint.jumpUrl)) ? false : true;
    }

    public boolean isMergedBuy() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || !wareYuShouInfo.isMergedBuy) ? false : true;
    }

    public boolean isMiaoSha() {
        WareMiaoShaInfo wareMiaoShaInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareMiaoShaInfo = wareBusinessData.miaoshaInfo) == null || !wareMiaoShaInfo.miaosha) ? false : true;
    }

    public boolean isNew12Style() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.isNew12Style) ? false : true;
    }

    public boolean isNewGiftPool() {
        WarePromotionInfo warePromotionInfo;
        WareBusinessGiftPools3C wareBusinessGiftPools3C;
        try {
            WareBusinessData wareBusinessData = this.mWareBusinessData;
            if (wareBusinessData == null || (warePromotionInfo = wareBusinessData.promotionInfo) == null || (wareBusinessGiftPools3C = warePromotionInfo.giftPool3C) == null) {
                return false;
            }
            return TextUtils.equals(wareBusinessGiftPools3C.promoTag, "1");
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean isNewJx() {
        return false;
    }

    public boolean isNewSharePlan() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !TextUtils.equals("b", wareBusinessABTestInfo.shareM)) ? false : true;
    }

    public boolean isNewStyleView() {
        return !TextUtils.equals(this.templateType, "0");
    }

    public boolean isNewYuyueColorStyle() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || wareYuYueInfo.isNewStyle != 1) ? false : true;
    }

    public boolean isNineRecommendLayout() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || wareBusinessABTestInfo.recommendLayoutType != 2) ? false : true;
    }

    public boolean isNoStcokRecommend() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.outOfStockRecommend) ? false : true;
    }

    public boolean isNumHide() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isNumHide) ? false : true;
    }

    public boolean isOTCPOP() {
        WareBusinessDrugEntity wareBusinessDrugEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessDrugEntity = wareBusinessData.drugInfo) == null || wareBusinessDrugEntity.drugBusinessId != 1) ? false : true;
    }

    public boolean isOTCSelfSupport() {
        return (this.mOTCInfo == null || isPop()) ? false : true;
    }

    public boolean isOffSale() {
        return StringUtil.no_price.equals(getJdPrice());
    }

    public boolean isOp() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isOp) ? false : true;
    }

    public boolean isOpenDetail4Smart() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.detail4Smart;
    }

    public boolean isOpenPerformance() {
        return this.isperformanceOpen;
    }

    public boolean isOpenPlus() {
        WarePlusInfo warePlusInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePlusInfo = wareBusinessData.plusInfo) == null || !warePlusInfo.showButton || TextUtils.isEmpty(warePlusInfo.plusPrice) || TextUtils.isEmpty(this.mWareBusinessData.plusInfo.text) || TextUtils.isEmpty(this.mWareBusinessData.plusInfo.url)) ? false : true;
    }

    public boolean isOpenYuyueFlag() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || !wareYuYueInfo.openYuYueFlag) ? false : true;
    }

    public boolean isPaiPaiSecond() {
        BusinessPaiPaiSmallImgStockInfo businessPaiPaiSmallImgStockInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (businessPaiPaiSmallImgStockInfo = wareBusinessData.ppInfo) == null || !businessPaiPaiSmallImgStockInfo.isPPSecondHands) ? false : true;
    }

    public boolean isPaiPaiSecondOpen() {
        BusinessPaiPaiSmallImgStockInfo businessPaiPaiSmallImgStockInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (businessPaiPaiSmallImgStockInfo = wareBusinessData.ppInfo) == null || !businessPaiPaiSmallImgStockInfo.newInspectSwitcher) ? false : true;
    }

    public boolean isPhone() {
        String[] strArr;
        BasicInfo basicInfo = this.mBasicInfo;
        return (basicInfo == null || (strArr = basicInfo.categoryIds) == null || !"9987".equals(strArr[0])) ? false : true;
    }

    public boolean isPinSkuYuYue() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || !wareYuYueInfo.pinSkuYuYue) ? false : true;
    }

    public boolean isPlusMakeTime() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || wareBusinessData.makeMoreTime == null) ? false : true;
    }

    public boolean isPlusSwaying() {
        WareBusinessPlusForBuyMt wareBusinessPlusForBuyMt;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessPlusForBuyMt = wareBusinessData.makeMoreTime) == null || !wareBusinessPlusForBuyMt.drawMoreTimeFlag) ? false : true;
    }

    public boolean isPop() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isPop) ? false : true;
    }

    public boolean isPreSalePriceChange() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null && wareBusinessData.preSaleSpotDegrade && wareBusinessData.overseaBuyDegrade && isOverseaPurchase()) {
            return true;
        }
        WareBusinessData wareBusinessData2 = this.mWareBusinessData;
        return (wareBusinessData2 == null || !wareBusinessData2.preSaleSpotDegrade || wareBusinessData2.overseaBuyDegrade) ? false : true;
    }

    public boolean isPrescription() {
        WareBusinessDrugEntity wareBusinessDrugEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessDrugEntity = wareBusinessData.drugInfo) == null || wareBusinessDrugEntity.drugBusinessId != 0) ? false : true;
    }

    public boolean isPrescriptionPOP() {
        return isPrescription() && isPop();
    }

    public boolean isProSalesInfoStage() {
        WarePromotionInfo warePromotionInfo;
        WareBusinessPromotionNoticeEntity wareBusinessPromotionNoticeEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePromotionInfo = wareBusinessData.promotionInfo) == null || (wareBusinessPromotionNoticeEntity = warePromotionInfo.proSalesInfo) == null || wareBusinessPromotionNoticeEntity.stage != 1) ? false : true;
    }

    public boolean isRefreshMeWhenModeChanged() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.refreshMe1;
    }

    public boolean isRegisterUser() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isRegisterUser) ? false : true;
    }

    public boolean isRegularBuy() {
        PDRegularBuyEntity pDRegularBuyEntity;
        ArrayList<PDRegularBuyFrequencyEntity> arrayList;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null || (arrayList = pDRegularBuyEntity.frequency) == null || arrayList.isEmpty()) ? false : true;
    }

    public boolean isRegularBuy4TimeOrder3() {
        PDRegularBuyEntity pDRegularBuyEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null || !"3".equals(pDRegularBuyEntity.isTimeOrder) || TextUtils.isEmpty(this.mWareBusinessData.regularBuy.buttonText) || TextUtils.isEmpty(this.mWareBusinessData.regularBuy.goUrl)) ? false : true;
    }

    public boolean isRegularBuyAb() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.regularMainProcess;
    }

    public boolean isReportOpen() {
        PdReportEntity pdReportEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdReportEntity = wareBusinessData.report) == null || !pdReportEntity.isOpen) ? false : true;
    }

    public boolean isSamMember() {
        WareSamInfo wareSamInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareSamInfo = wareBusinessData.samInfo) == null || !wareSamInfo.samMember) ? false : true;
    }

    public boolean isSdkOpen() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.sdkDegrade) ? false : true;
    }

    public boolean isSelectCarShop() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null || TextUtils.isEmpty(pDCarShopEntity.bundlingOrderId)) ? false : true;
    }

    public boolean isSelectWhiteBar() {
        PdWhiteBarForStyleInfoEntity pdWhiteBarForStyleInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdWhiteBarForStyleInfoEntity = wareBusinessData.proPageWhiteBarInfo) == null || pdWhiteBarForStyleInfoEntity.planInfos == null || this.selectItemInfo == null) ? false : true;
    }

    public boolean isSelever() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.selever;
    }

    public boolean isSelfDelivery() {
        return this.selfDelivery == 1;
    }

    public boolean isShopBigAtmosphere() {
        PdShopEntity pdShopEntity;
        PdShopInfoEntity pdShopInfoEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopInfoEntity = pdShopEntity.shop) == null || !pdShopInfoEntity.bigAtmosphere) ? false : true;
    }

    public boolean isShopService() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null || !pDCarShopEntity.isInShopService) ? false : true;
    }

    public boolean isShopV12() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.shop12Style;
    }

    public boolean isShow3cOldPlaceNewView() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.show3cService) ? false : true;
    }

    public boolean isShowBarrage() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isShowBarrage) ? false : true;
    }

    public boolean isShowBigPlus() {
        WareBusinessBigPlus wareBusinessBigPlus;
        WareBusinessBigPlusJumpPlayer wareBusinessBigPlusJumpPlayer;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessBigPlus = wareBusinessData.bigPlusBottom) == null || !wareBusinessBigPlus.isValid() || (wareBusinessBigPlusJumpPlayer = this.mWareBusinessData.bigPlusJumpPlayer) == null || !wareBusinessBigPlusJumpPlayer.isValid()) ? false : true;
    }

    public boolean isShowBottomHealthIntro() {
        return (getHealthBottomTipIntroInfo() == null || getHealthBottomTipIntroLayerInfo() == null) ? false : true;
    }

    public boolean isShowBtnHandPrice() {
        return hasToHandPrice() && this.mWareBusinessData.toHandssSrengthen.buttonShow;
    }

    public boolean isShowEuropeTip() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || wareBusinessData.euBaguette == null) ? false : true;
    }

    public boolean isShowGiftToast() {
        WareBusinessGiftInfo wareBusinessGiftInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessGiftInfo = wareBusinessData.giftInfo) == null || TextUtils.isEmpty(wareBusinessGiftInfo.bottomTxt) || !TextUtils.isEmpty(this.mWareBusinessData.property.reasonTips) || WareBusinessGiftInfo.mShowTimes >= WareBusinessGiftInfo.mMaxShowTime) ? false : true;
    }

    public boolean isShowHealth() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null || !TextUtils.equals(pDCarShopEntity.jHealthHentaiEnum, "1")) ? false : true;
    }

    public boolean isShowHealthBottomBar() {
        return getHealthBottomBarInfo() != null && this.isShowHealthBar;
    }

    public boolean isShowHealthOnLine() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null || !TextUtils.equals(pDCarShopEntity.jHealthHentaiEnum, "2")) ? false : true;
    }

    public boolean isShowMoreOldStyle() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.oldMoreStyle;
    }

    public boolean isShowPaiPaiBottomBar() {
        WareBusinessPaiPaiReplacement wareBusinessPaiPaiReplacement;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessPaiPaiReplacement = wareBusinessData.paiPaiOld4New) == null || wareBusinessPaiPaiReplacement.shieldBottomStrip) ? false : true;
    }

    public boolean isShowQuanQiuGouQingGuan() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || warePropertyInfo.qqgQgReasonTips == null) ? false : true;
    }

    public boolean isShowStyleHandPrice() {
        return hasToHandPrice() && this.mWareBusinessData.toHandssSrengthen.selectShow;
    }

    public boolean isShowStyleIdentify() {
        PdToHandPriceEntity pdToHandPriceEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdToHandPriceEntity = wareBusinessData.toHandssSrengthen) == null || !pdToHandPriceEntity.isSpecialGuide) ? false : true;
    }

    public boolean isSmartRecommend() {
        WareBusinessSmartWare wareBusinessSmartWare;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessSmartWare = wareBusinessData.smartWares) == null || !TextUtils.equals("1", wareBusinessSmartWare.recommend)) ? false : true;
    }

    public boolean isSmartWare() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.smartWare;
    }

    public boolean isSmartWaresComment() {
        WareBusinessSmartWare wareBusinessSmartWare;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessSmartWare = wareBusinessData.smartWares) == null || !wareBusinessSmartWare.comment) ? false : true;
    }

    public boolean isSmartWebDetail() {
        WareBusinessSmartWare wareBusinessSmartWare;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessSmartWare = wareBusinessData.smartWares) == null || !wareBusinessSmartWare.details || isCloseSmartWebDetail()) ? false : true;
    }

    public boolean isSpotPreSale() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || wareYuShouInfo.yuShouSpotPreSaleMap == null) ? false : true;
    }

    public boolean isStaging() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null || !TextUtils.equals("staging", pdBuyByMEntity.addCartBusinessName)) ? false : true;
    }

    public boolean isStudentAuth() {
        PdStudentAuthenticateEntity pdStudentAuthenticateEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdStudentAuthenticateEntity = wareBusinessData.exclusive2Student) == null || TextUtils.isEmpty(pdStudentAuthenticateEntity.btnText)) ? false : true;
    }

    public boolean isStyleInputFloorHide() {
        return isVirtualGoods() || (this.hideSelectedFloor && isNumHide());
    }

    public boolean isStyleInputFloorShowed() {
        return isFloorShowed("bpChoice") || isFloorShowed(FloorBussinessName.FB_BUSSINESS_STYLE_INPUT_V12);
    }

    public boolean isTemplateFormat() {
        return this.templateEntity != null;
    }

    public boolean isTenVersionPriceStyle() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.tenVersionPrice;
    }

    public boolean isTenthRevision() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.tenthRevision;
    }

    public boolean isTitleAndAdChange() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.titleAndAdChange) ? false : true;
    }

    public boolean isTitleV12() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.title12style;
    }

    public boolean isToABRecommend() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !TextUtils.equals(wareBusinessABTestInfo.tj, "huangtiao")) ? false : true;
    }

    public boolean isToABTest() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.toABTest) ? false : true;
    }

    public boolean isTuijian12style() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.tuijian12style;
    }

    public boolean isTwComment() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.twComment;
    }

    public boolean isUseBatchReceiveCoupon() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.isUseBatchReceiveCoupon();
    }

    public boolean isUseFloorDetail() {
        AppStaticInfo appStaticInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return !(wareBusinessData == null || (appStaticInfo = wareBusinessData.appStaticInfo) == null || !appStaticInfo.isUseDetailFloor4Android) || isSmartWebDetail() || isDetailListOn() || isOpenDetail4Smart() || isCanHasRecommendTab();
    }

    public boolean isUseReceiveNCoupon() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.isUseReceiveNCoupon();
    }

    public boolean isVideoPlayerFlag2() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.videoPlayerFlag;
    }

    public boolean isVirtualGoods() {
        String[] strArr;
        BasicInfo basicInfo = this.mBasicInfo;
        if (basicInfo == null || (strArr = basicInfo.categoryIds) == null) {
            return false;
        }
        return Arrays.asList("4835", "4836", "12276", "12277", "100001553", "100002251", "4833", "12273", "7073", "9393", "12275", "12278", "12279", "13758", "15655").contains(strArr[2]);
    }

    public boolean isWebDetailViewClose() {
        WareBusinessData wareBusinessData;
        return isSmartWebDetail() && (wareBusinessData = this.mWareBusinessData) != null && wareBusinessData.smartIntro;
    }

    public boolean isXiaJia() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.xiajia) ? false : true;
    }

    public boolean isXinPin() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && TextUtils.equals(wareBusinessData.clothMark, "xp");
    }

    public boolean isYanbaoShield() {
        WareBusinessSoldOverSea wareBusinessSoldOverSea;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessSoldOverSea = wareBusinessData.soldOversea) == null || !wareBusinessSoldOverSea.isYanbaoShield) ? false : true;
    }

    public boolean isYuShou() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || !wareYuShouInfo.isYuShou) ? false : true;
    }

    public boolean isYuShouAdvanceBuy() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || wareYuShouInfo.yuShouAdvanceBuyMap == null || !TextUtils.equals(wareYuShouInfo.bizType, "4")) ? false : true;
    }

    public boolean isYuShouNewStyle() {
        WareYuShouInfo wareYuShouInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuShouInfo = wareBusinessData.YuShouInfo) == null || !wareYuShouInfo.isYuShouNewStyle()) ? false : true;
    }

    public boolean isYuShouYrq() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || wareBusinessData.yrqInfo == null) ? false : true;
    }

    public boolean isYuyue() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || !wareYuYueInfo.isYuYue) ? false : true;
    }

    public boolean isYuyueAsynInterface() {
        if (this.mWareBusinessData != null) {
            return !TextUtils.isEmpty(r0.asyncInterfaceBody);
        }
        return false;
    }

    public boolean isYuyueAutoAddCart() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || !TextUtils.equals(wareYuYueInfo.autoAddCart, "1")) ? false : true;
    }

    public boolean isYuyueMask() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || !wareYuYueInfo.isMask()) ? false : true;
    }

    public boolean isaShortFillOrder() {
        LayerFlagExperiment layerFlagExperiment;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && (layerFlagExperiment = wareBusinessData.layerFlagExperiment) != null && TextUtils.equals(layerFlagExperiment.layerType, "3") && this.mWareBusinessData.layerFlagExperiment.layerFlag;
    }

    public boolean isgroupGoods() {
        WareBusinessSkuPropertyList wareBusinessSkuPropertyList;
        WareBusinessSkuPropertyItem wareBusinessSkuPropertyItem;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessSkuPropertyList = wareBusinessData.skuPropertyList) == null || (wareBusinessSkuPropertyItem = wareBusinessSkuPropertyList.groupGoods) == null || wareBusinessSkuPropertyItem.type != 1) ? false : true;
    }

    public boolean isloc() {
        LocInfo locInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (locInfo = wareBusinessData.locInfo) == null || !locInfo.isloc) ? false : true;
    }

    public String jHealthAppointMat() {
        WareBusinessHealthAppoint wareBusinessHealthAppoint;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessHealthAppoint = wareBusinessData.jHealthAppoint) == null || TextUtils.isEmpty(wareBusinessHealthAppoint.biz)) ? "" : this.mWareBusinessData.jHealthAppoint.biz;
    }

    public boolean limitBuyButtonNotClickable() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.limitBuyButtonNotClickable) ? false : true;
    }

    public boolean recommendPopup() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || (!wareBusinessABTestInfo.recommendPopup && !wareBusinessABTestInfo.cartRecomPopUpNew)) ? false : true;
    }

    public void resetDataAfterChangeSku() {
        this.isNeedPopCoudanGuide = Boolean.TRUE;
        setNumber(1);
        resetRecommendData();
        this.mSkuTag = "";
        this.jpsCheckStatus = null;
        this.isNoCarMatchState = false;
    }

    public StringBuilder selectWhiteBarToMa(StringBuilder sb) {
        PdWhiteBarForStyleInfoEntity pdWhiteBarForStyleInfoEntity;
        if (sb != null) {
            sb.append(CartConstant.KEY_YB_INFO_LINK);
            WareBusinessData wareBusinessData = this.mWareBusinessData;
            if (wareBusinessData != null && (pdWhiteBarForStyleInfoEntity = wareBusinessData.proPageWhiteBarInfo) != null && pdWhiteBarForStyleInfoEntity.planInfos != null) {
                if (this.selectItemInfo != null) {
                    sb.append(1);
                } else {
                    sb.append(0);
                }
            }
        }
        return sb;
    }

    public void setBuyMaxNum(int i2) {
        this.buyMaxNum = i2;
        if (i2 <= 0) {
            this.buyMaxNum = 200;
        }
    }

    public void setCategory(String str) {
        BasicInfo basicInfo = this.mBasicInfo;
        basicInfo.strCategoryIds = str;
        basicInfo.categoryIds = null;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mBasicInfo.categoryIds = str.split(";");
    }

    public void setCouponApplicability(String str, boolean z) {
        WareBusinessBigPlusJumpPlayer wareBusinessBigPlusJumpPlayer;
        List<PDCouponCellEntity> list;
        if (isAggrePromoFloorShowed()) {
            PreferentialGuideEntity preferentialGuideEntity = getPreferentialGuideEntity();
            if (!TextUtils.isEmpty(str) && preferentialGuideEntity != null && (list = preferentialGuideEntity.couponInfo) != null && list.size() > 0) {
                for (PDCouponCellEntity pDCouponCellEntity : preferentialGuideEntity.couponInfo) {
                    if (TextUtils.equals(pDCouponCellEntity.couponId, str)) {
                        pDCouponCellEntity.applicability = z;
                        if (!z && TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDProductdetail", "coupon-issettakenicon", "enable"), DYConstants.DY_TRUE)) {
                            pDCouponCellEntity.isSetTakenIcon = !z;
                        }
                    }
                }
            }
        }
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessBigPlusJumpPlayer = wareBusinessData.bigPlusJumpPlayer) == null || !wareBusinessBigPlusJumpPlayer.isValid()) {
            return;
        }
        for (PDCouponCellEntity pDCouponCellEntity2 : this.mWareBusinessData.bigPlusJumpPlayer.couponInfo) {
            if (TextUtils.equals(pDCouponCellEntity2.couponId, str)) {
                pDCouponCellEntity2.applicability = z;
                if (!z && TextUtils.equals(JDMobileConfig.getInstance().getConfig("JDProductdetail", "coupon-issettakenicon", "enable"), DYConstants.DY_TRUE)) {
                    pDCouponCellEntity2.isSetTakenIcon = !z;
                }
            }
        }
    }

    public void setEncrypt(boolean z, boolean z2) {
        PDSizeHelperEntity pDSizeHelperEntity;
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarEntity pDCarEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null && (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) != null && (pDCarEntity = wareBusinessCarAllInfo.carInfo) != null) {
            pDCarEntity.dealData(z, z2);
        }
        WareBusinessData wareBusinessData2 = this.mWareBusinessData;
        if (wareBusinessData2 == null || (pDSizeHelperEntity = wareBusinessData2.userSizeInfo) == null) {
            return;
        }
        pDSizeHelperEntity.dealData(z, z2);
    }

    public void setGiftPoolsListData(WareBusinessGiftPools3C wareBusinessGiftPools3C) {
        ArrayList<GiftPool3C> arrayList;
        this.giftPoolsList = null;
        this.giftPoolIdsSelect = null;
        if (wareBusinessGiftPools3C == null || (arrayList = wareBusinessGiftPools3C.giftPools) == null || arrayList.isEmpty()) {
            return;
        }
        this.giftPoolsList = wareBusinessGiftPools3C.giftPools;
    }

    public void setIsCollect(boolean z) {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return;
        }
        warePropertyInfo.isCollect = z;
    }

    public void setIsOldAddress(boolean z) {
        this.mBasicInfo.isOldAddress = z;
    }

    public void setIsShowShopNameB(boolean z, WareBusinessData wareBusinessData) {
        PdShopEntity pdShopEntity;
        PdShopInfoEntity pdShopInfoEntity;
        this.isShowShopNameB = z;
        if (wareBusinessData == null || (pdShopEntity = wareBusinessData.shopInfo) == null || (pdShopInfoEntity = pdShopEntity.shop) == null) {
            return;
        }
        pdShopInfoEntity.changeShopName(z);
    }

    public void setLowestBuyNum(String str) {
        try {
            this.lowestBuyNum = Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            this.lowestBuyNum = 0;
        }
        this.lowestBuy = true;
        if (this.lowestBuyNum <= 0) {
            this.lowestBuy = false;
            this.lowestBuyNum = 1;
        }
    }

    public void setNumber(int i2) {
        int i3;
        this.number = i2;
        if (this.lowestBuy && i2 < (i3 = this.lowestBuyNum)) {
            this.number = i3;
        }
        int i4 = this.number;
        int i5 = this.buyMaxNum;
        if (i4 > i5) {
            this.number = i5;
        } else if (i4 <= 1) {
            this.number = 1;
        }
    }

    public void setPriceBusiness(String str) {
        this.mJingJiaIcon = str;
    }

    public void setRegularBuy(WareBusinessData wareBusinessData) {
        PDRegularBuyEntity pDRegularBuyEntity;
        PDRegularBuyFrequencyEntity pDRegularBuyFrequencyEntity;
        this.mRegularFrequency = null;
        this.mRegularPhaseNum = 0;
        this.mRegularEachNum = 0;
        if (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null) {
            return;
        }
        this.mRegularPhaseNum = PDUtils.getValue(pDRegularBuyEntity.stageNumDef);
        this.mRegularEachNum = PDUtils.getValue(wareBusinessData.regularBuy.deliverNumDef);
        ArrayList<PDRegularBuyFrequencyEntity> arrayList = wareBusinessData.regularBuy.frequency;
        if (arrayList == null || arrayList.isEmpty() || (pDRegularBuyFrequencyEntity = arrayList.get(0)) == null) {
            return;
        }
        pDRegularBuyFrequencyEntity.isSelected = true;
        this.mRegularFrequency = pDRegularBuyFrequencyEntity;
    }

    public void setShadowSku(String str) {
        this.isShadowSku = str;
        if (TextUtils.isEmpty(str) || TextUtils.equals(DYConstants.DY_NULL_STR, this.isShadowSku)) {
            this.isShadowSku = "0";
        }
    }

    public void setTopImageData(List<WareBusinessWareImageEntity> list) {
        JDJSONArray parseArray;
        List<Image> list2 = this.imageList;
        if (list2 != null) {
            list2.clear();
        }
        this.shareImage = null;
        if (list == null || list.isEmpty() || (parseArray = JDJSON.parseArray(JDJSON.toJSONString(list))) == null) {
            return;
        }
        for (int i2 = 0; i2 < parseArray.size(); i2++) {
            JDJSONObject jSONObject = parseArray.getJSONObject(i2);
            if (i2 == 0) {
                this.shareImage = jSONObject.optString("share");
            }
            this.imageList.add(new Image(jSONObject, 1));
        }
    }

    public void setmSkuTag(String str) {
        this.mSkuTag = str;
    }

    public boolean showBarterService() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            return wareBusinessData.showBarterService;
        }
        return false;
    }

    public boolean showBuyLayer() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.showBuyLayer) ? false : true;
    }

    public boolean showReplaceNewDialog() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.isNewPP;
    }

    public boolean stockNotice() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.stockNotice) ? false : true;
    }

    public ArrayList<PDRecommendEntity> toList(JDJSONArray jDJSONArray) {
        ArrayList<PDRecommendEntity> arrayList = new ArrayList<>();
        if (jDJSONArray == null) {
            return arrayList;
        }
        for (int i2 = 0; i2 < jDJSONArray.size(); i2++) {
            try {
                PDRecommendEntity pDRecommendEntity = new PDRecommendEntity(jDJSONArray.getJSONObject(i2));
                if (!TextUtils.isEmpty(pDRecommendEntity.name)) {
                    arrayList.add(pDRecommendEntity);
                }
                if (!TextUtils.isEmpty(pDRecommendEntity.salesVolume)) {
                    this.hasRankSale = true;
                }
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d("ServerIcon", e2.getMessage());
                }
            }
        }
        return arrayList;
    }

    public boolean toM() {
        PDRegularBuyEntity pDRegularBuyEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pDRegularBuyEntity = wareBusinessData.regularBuy) == null || !pDRegularBuyEntity.toM) ? false : true;
    }

    public void updateSelectedGiftNum(ArrayList<NewGiftPoolItem> arrayList) {
        int i2;
        this.giftPoolSelect = new ArrayList<>();
        if (arrayList != null) {
            try {
                if (arrayList.isEmpty()) {
                    return;
                }
                ArrayList<NewGiftItem> arrayList2 = new ArrayList<>();
                Iterator<NewGiftPoolItem> it = arrayList.iterator();
                int i3 = 0;
                while (it.hasNext()) {
                    NewGiftPoolItem next = it.next();
                    if (next != null) {
                        Iterator<NewGiftItem> it2 = next.getGifts().iterator();
                        while (it2.hasNext()) {
                            NewGiftItem next2 = it2.next();
                            if (next2.isSelect()) {
                                arrayList2.add(next2);
                                i3 += next2.getNum();
                            }
                            if (!isNewGiftPool()) {
                                next2.setNum(this.number);
                            }
                        }
                    }
                }
                this.giftPoolSelect = arrayList2;
                if (!isNewGiftPool() || arrayList2.isEmpty() || i3 == (i2 = this.number)) {
                    return;
                }
                int i4 = i2 - i3;
                Iterator<NewGiftItem> it3 = arrayList2.iterator();
                while (it3.hasNext()) {
                    NewGiftItem next3 = it3.next();
                    if (i4 <= 0 && i4 != 0) {
                        int i5 = 1;
                        if (next3.getNum() + i4 < 0) {
                            next3.setNum(1);
                            next3.setSelect(false);
                            i4 = next3.getNum() + i4;
                        } else {
                            int num = next3.getNum() + i4;
                            if (num == 0) {
                                next3.setSelect(false);
                            } else {
                                i5 = num;
                            }
                            next3.setNum(i5);
                            i4 = 0;
                        }
                    }
                    next3.setNum(next3.getNum() + i4);
                    i4 = 0;
                }
                updateGiftPoolSelect(arrayList2);
            } catch (Exception e2) {
                if (Log.D) {
                    Log.d(t.f20145j, e2.getMessage());
                }
            }
        }
    }

    public void updateSmartRecommendParam(String str) {
        getSmartRecommendParam();
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null) {
            PDSmartRecommendParam pDSmartRecommendParam = this.pdSmartRecommendParam;
            pDSmartRecommendParam.isSmart = wareBusinessData.smartWare ? "1" : "0";
            WareBusinessSmartWare wareBusinessSmartWare = wareBusinessData.smartWares;
            pDSmartRecommendParam.isSmartRecommend = wareBusinessSmartWare != null ? wareBusinessSmartWare.recommend : "0";
        }
        this.pdSmartRecommendParam.recommend_ext = str;
    }

    public void updateWareBusinessData(PDWareBusinessEntity pDWareBusinessEntity) {
        FloorTemplateEntity floorTemplateEntity = new FloorTemplateEntity(null, null);
        this.templateEntity = floorTemplateEntity;
        floorTemplateEntity.templates = new ArrayList<>();
        this.mWareBusinessData = null;
        this.mNeedShowFloorMid = null;
        this.mResponseTime = System.currentTimeMillis();
        this.hasAdword = false;
        this.mJsonString = pDWareBusinessEntity.mJsonString;
        ArrayList<BaseTemplateEntity> arrayList = pDWareBusinessEntity.businessFloorEntities;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        Iterator<BaseTemplateEntity> it = pDWareBusinessEntity.businessFloorEntities.iterator();
        while (it.hasNext()) {
            BaseTemplateEntity next = it.next();
            if (next != null) {
                next.listenEdgeChange = true;
                if (PlatformHelper.isFloorRegister("productDetail", next.mId)) {
                    this.templateEntity.templates.add(next);
                }
                if (TextUtils.equals(next.mId, "bpAdword")) {
                    Object obj = next.mData;
                    if (obj instanceof JDJSONObject) {
                        JDJSONObject jDJSONObject = (JDJSONObject) obj;
                        if (jDJSONObject != null) {
                            this.adWordEntity = (WareBusinessAdWordEntity) JDJSON.parseObject(jDJSONObject.optString("ad"), WareBusinessAdWordEntity.class);
                        }
                        WareBusinessAdWordEntity wareBusinessAdWordEntity = this.adWordEntity;
                        this.hasAdword = (wareBusinessAdWordEntity == null || TextUtils.isEmpty(wareBusinessAdWordEntity.adword)) ? false : true;
                    }
                }
                if (TextUtils.equals(next.mId, "bpMasterdata") && (next instanceof BusinessFloorEntity)) {
                    String jSONString = JDJSON.toJSONString(((BusinessFloorEntity) next).mData);
                    JDJSONObject parseObject = JDJSON.parseObject(jSONString);
                    JDJSONObject optJSONObject = parseObject.optJSONObject("asyncInterfaceBody");
                    WareBusinessData wareBusinessData = (WareBusinessData) JDJSON.parseObject(jSONString, WareBusinessData.class);
                    this.mWareBusinessData = wareBusinessData;
                    if (optJSONObject != null) {
                        wareBusinessData.asyncInterfaceBody = optJSONObject.toJSONString();
                    }
                    WareBusinessData wareBusinessData2 = this.mWareBusinessData;
                    if (wareBusinessData2 != null) {
                        dealWareBusinessData(wareBusinessData2);
                        WareBusinessUnitMainImageEntity.dealIsvImage(this.mWareBusinessData.magicHeadPicInfo, parseObject);
                    }
                }
                copyBusinessFloorData(next);
            }
        }
    }

    public WareBusinessMagicHeadPicInfoEntity getTopImageBanDanData(String str) {
        List<WareBusinessMagicHeadPicInfoEntity> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = null;
        if (wareBusinessData != null && (list = wareBusinessData.magicHeadPicInfo) != null && !list.isEmpty()) {
            for (WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity2 : this.mWareBusinessData.magicHeadPicInfo) {
                if (wareBusinessMagicHeadPicInfoEntity2 != null && TextUtils.equals(wareBusinessMagicHeadPicInfoEntity2.anchorType, str)) {
                    wareBusinessMagicHeadPicInfoEntity = wareBusinessMagicHeadPicInfoEntity2;
                }
            }
        }
        return wareBusinessMagicHeadPicInfoEntity;
    }

    private void getBestCoupon(ArrayList<PDCouponCellEntity> arrayList, PreferentialGuideEntity preferentialGuideEntity) {
        List<PDCouponCellEntity> list;
        List<String> list2 = preferentialGuideEntity.bestCouponId;
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        for (String str : preferentialGuideEntity.bestCouponId) {
            if (!TextUtils.isEmpty(str) && (list = preferentialGuideEntity.couponInfo) != null && list.size() > 0) {
                for (PDCouponCellEntity pDCouponCellEntity : preferentialGuideEntity.couponInfo) {
                    if (TextUtils.equals(pDCouponCellEntity.couponId, str) && pDCouponCellEntity.applicability && !pDCouponCellEntity.personalCoupon) {
                        arrayList.add(pDCouponCellEntity);
                    }
                }
            }
        }
    }

    public ProductDetailEntity(String str) {
        this.mManageKey = str;
    }
}
