package com.jingdong.app.mall.bundle.styleinfoview.entitys;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jd.framework.json.JDJSON;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.bundle.styleinfoview.entity.BusinessFloorEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entity.PDWareBusinessEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.buttoninfo.PDBottomBtn;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.buttoninfo.PDBottomInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.caro2o.PDCarEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.caro2o.PDCarItemEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.caro2o.PDCarShopEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.coupon.PDCouponCellEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.delivery.PDDeliveryInstallEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.delivery.PDDeliveryOptionEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ecard.PDECardInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.fansprice.PdFansPriceEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.installment.PDInstallmentInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.loc.LocInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.loc.PDLocBuyStepEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.plusmember.PDPlusFreightEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.regularbuy.PDRegularBuyEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.regularbuy.PDRegularBuyFrequencyEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.seckillcertify.PDSeckillCertifyEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.seckillcertify.PDSeckillTipEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.shop.PdShopChatInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.shop.PdShopDdDrag;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.shop.PdShopDdToastEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.shop.PdShopEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.shop.PdShopHotLineEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.shop.PdShopInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.shop.PdShopServiceEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.sizehelper.PDSizeHelperEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.skin.PDSkinDyInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.AddFamilyListData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.BusinessPaiPaiSmallImgStockInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.Gift3C;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.GiftPool3C;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PdAcsEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PdDdFatigueMechanism;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PdGiftShopEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PdLiveFloatEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PdStyleDialogEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PdTopWhiteBarEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PdWhiteBarForStyleInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.PdWhiteBarForStyleItemInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.SecondPriceEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.SurveyData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.TreayNewInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareAppletShare;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBasicInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessABTestInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessBenefitBeltEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessCarAllInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessCarTextEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessData;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessDrugEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessFeeInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessFsPriceEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessFurnitureGroupEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessGiftPools3C;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessHwShareInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessJingPriceEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessJumpInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessJxInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPlusForBuyMt;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPlusListEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPointInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPriceIconEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessProductFeeInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessPromotionNoticeEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessRechargeTypeInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessServiceIconEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessServiceIconInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessSkuPropertyItem;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessSkuPropertyList;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessSoldOverSea;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessSpecialInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessStyleEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessTurnToH5;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessWareImageEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareBusinessYanBaoGroupEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareEventParam;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareEyeSightInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareFlashInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareFurnitureInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareJdServerPlusEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareMiaoShaInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WarePlusInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WarePriceInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WarePromotionInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WarePropertyInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareSamInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareStockEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYanBaoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuShouAdvanceBuyMap;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuShouInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness.WareYuYueInfo;
import com.jingdong.app.mall.bundle.styleinfoview.floor.FloorBussinessName;
import com.jingdong.app.mall.bundle.styleinfoview.floor.FloorThemeEnum;
import com.jingdong.app.mall.bundle.styleinfoview.temp.FloorTemplate;
import com.jingdong.app.mall.bundle.styleinfoview.temp.FloorTemplateEntity;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import com.jingdong.common.entity.Image;
import com.jingdong.common.entity.ProductDetailEntityBase;
import com.jingdong.common.entity.ProductDetailPrice;
import com.jingdong.common.entity.ProductToJsNowBuyEntity;
import com.jingdong.common.entity.cart.NewGiftItem;
import com.jingdong.common.entity.cart.NewGiftPoolItem;
import com.jingdong.common.entity.productdetail.PDSopSkuInfoEntity;
import com.jingdong.common.entity.productdetail.PDStyleEntity;
import com.jingdong.common.entity.productdetail.PDStyleFilterEntity;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.constant.PDConstant;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.jdsdk.utils.SharedPreferencesUtil;
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

/* loaded from: classes3.dex */
public class ProductDetailEntity extends ProductDetailEntityBase implements PDConstant {
    public static final String KEY_PD_PLUS_WEARY_DAY = "pd_plus_weary_day";
    public static final String KEY_PD_PLUS_WEARY_TIMES = "pd_plus_weary_times";
    private static final String TAG = "ProductDetailEntity";
    public int appleCare;
    public boolean avoidLive;
    public String bbtf;
    public String businessOrigin;
    public int buyMaxNum;
    public String carModelId;
    public String defaultImage;
    public String defaultName;
    public String defaultPrice;
    public PDDeliveryInstallEntity deliveryInstallEntity;
    public HashMap<String, Object> extensionMap;
    public String flt;
    public ArrayList<String> furnitureIdsSelect;
    public ArrayList<String> giftPoolIdsSelect;
    public ArrayList<NewGiftItem> giftPoolSelect;
    public ArrayList<GiftPool3C> giftPoolsList;
    public String inspectSkuId;
    public boolean isNeedChangeSkin;
    public boolean isOverseaWhiteBar;
    public String isShadowSku;
    public boolean isShowShopNameB;
    public boolean isSop;
    public boolean isStatusBarTintEnable;
    public Boolean jpsCheckStatus;
    public ArrayList<String> jsServerPlusIdsSelect;
    public boolean mAutoAddCart;
    public PDCarGiftEntity mCarGiftEntity;
    public String mCurModelId;
    public String mCurrentSku;
    public String mDeliveryPrice;
    public PdFansPriceEntity mFansPrice;
    public boolean mHaveHuaWeiShare;
    public boolean mIsStyleChangeSku;
    public String mJingJiaIcon;
    public String mLocArea;
    public PDLocBuyStepEntity mLocBuyStepEntity;
    public String mLocChannel;
    public String mLocShopId;
    public String mManageKey;
    public MaxSales mMaxSales;
    public OTCInfo mOTCInfo;
    private PreferentialGuideEntity mPreferentialGuideEntity;
    public int mRegularEachNum;
    public PDRegularBuyFrequencyEntity mRegularFrequency;
    public int mRegularPhaseNum;
    public PDSkinDyInfoEntity mSkinDyInfo;
    private String mSkuTag;
    public String mStyleSelect;
    public boolean mTextLarge;
    public int mTopWhiteBarPlanid;
    public ProductToJsNowBuyEntity mType4Entity;
    public ProductToJsNowBuyEntity mType5Entity;
    public WareBusinessData mWareBusinessData;
    public List<WareBusinessServiceIconEntity> mWareBusinessDetailServiceIcons;
    public WareBusinessServiceIconInfoEntity mWareBusinessServiceInfo;
    public MainPicDpgInfo mainPicDpgInfo;
    public String personas;
    public String pt;
    public List<PDRecommendEntity> recommendAccaList;
    public List<PDRecommendEntity> recommendLikeList;
    public List<PDRecommendEntity> recommendOtherLikeList;
    public String recommendRankJumUrl;
    public List<PDRecommendEntity> recommendRankList;
    public String roomId;
    public String searchParam;
    public PdWhiteBarForStyleItemInfoEntity selectItemInfo;
    public String serviceInfoId;
    public String shareImage;
    public JDJSONObject skuDetailJson;
    public JDJSONObject skuDyInfoJson;
    public String source;
    public String sourceType;
    public String storeId;
    public String supperRoomPromo;
    public FloorTemplateEntity templateEntity;
    public String toTab;
    public int transitionViewHeight;
    public int transitionViewLeft;
    public String transitionViewMarkId;
    public int transitionViewTop;
    public int transitionViewWidth;
    public ArrayList<String> yanbaoIdsSelect;
    public boolean isFromCar = false;
    public int fromType = 0;
    public boolean isLoaded = false;
    public BasicInfo mBasicInfo = new BasicInfo();
    public List<Image> imageList = new ArrayList();
    public int suitABTest = 0;
    public boolean isRecommenExpo = false;
    public boolean isRelateRecommendExpo = false;
    public boolean isRecommendUploadScrollY = false;
    public boolean isRankExpo = false;
    public boolean isAccessExpo = false;
    public boolean hasRankSale = false;
    public boolean lowestBuy = false;
    public int lowestBuyNum = 1;
    public boolean isRNCertify = false;
    public boolean hideSelectedFloor = true;
    public boolean hasShowBarrage = false;
    public Boolean isNeedPopCoudanGuide = Boolean.FALSE;
    public boolean isperformanceOpen = false;
    public String paipai_cache_str = null;
    public int themeStyle = -1;
    public boolean isHaveWhiteBarData = false;
    public boolean isHasSearchFloor = false;
    public PdStyleDialogEntity mStyleDialogEntity = new PdStyleDialogEntity();
    private ArrayList<PDCouponCellEntity> mResult = new ArrayList<>();

    /* loaded from: classes3.dex */
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
        }
    }

    /* loaded from: classes3.dex */
    public class MaxSales {
        public String head;
        public String title;

        public MaxSales(JDJSONObject jDJSONObject) {
            if (jDJSONObject != null) {
                this.head = jDJSONObject.optString(DataCompassUtils.MODULE_TYPE_HEAD);
                this.title = jDJSONObject.optString("title");
            }
        }
    }

    /* loaded from: classes3.dex */
    public class OTCInfo {
        public String OTCIcon;
        public String instruction;
        public String instructionText;
        public String shareLink;

        public OTCInfo(JDJSONObject jDJSONObject) {
            this.instruction = jDJSONObject.optString("instruction");
            this.instructionText = jDJSONObject.optString("instructionText");
            this.shareLink = jDJSONObject.optString(JshopConst.JSKEY_SHARE_URL);
            this.OTCIcon = jDJSONObject.optString("OTCIcon");
        }

        public boolean check() {
            return (TextUtils.isEmpty(this.instruction) || TextUtils.isEmpty(this.instructionText)) ? false : true;
        }
    }

    public ProductDetailEntity(String str) {
        this.mManageKey = str;
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

    private void copyBusinessFloorData(BaseTemplateEntity baseTemplateEntity) {
        PreferentialGuideEntity preferentialGuideEntity;
        List<PDCouponCellEntity> list;
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
        if (TextUtils.equals(baseTemplateEntity.mId, "bpTopWhiteBar") && (pdTopWhiteBarEntity = (PdTopWhiteBarEntity) JDJSON.parseObject(JDJSON.toJSONString(baseTemplateEntity.mData), PdTopWhiteBarEntity.class)) != null) {
            this.mTopWhiteBarPlanid = pdTopWhiteBarEntity.planId;
        }
        if (TextUtils.equals(baseTemplateEntity.mId, "bpAggrePromo") || TextUtils.equals(baseTemplateEntity.mId, FloorBussinessName.FB_BUSINESS_AGGRE_PROMO_NEW)) {
            JDJSONObject jDJSONObject = (JDJSONObject) baseTemplateEntity.mData;
            if (jDJSONObject.isNull("preferentialGuide") || (preferentialGuideEntity = (PreferentialGuideEntity) JDJSON.parseObject(jDJSONObject.optJSONObject("preferentialGuide").toJSONString(), PreferentialGuideEntity.class)) == null) {
                return;
            }
            this.mPreferentialGuideEntity = preferentialGuideEntity;
            List<String> list2 = preferentialGuideEntity.bestCouponId;
            if (list2 == null || list2.size() <= 0) {
                return;
            }
            for (String str : preferentialGuideEntity.bestCouponId) {
                if (!TextUtils.isEmpty(str) && (list = preferentialGuideEntity.couponInfo) != null && list.size() > 0) {
                    for (PDCouponCellEntity pDCouponCellEntity : preferentialGuideEntity.couponInfo) {
                        if (TextUtils.equals(pDCouponCellEntity.couponId, str) && pDCouponCellEntity.applicability && !pDCouponCellEntity.personalCoupon) {
                            this.mResult.add(pDCouponCellEntity);
                        }
                    }
                }
            }
        }
    }

    private void dealWareBusinessData(WareBusinessData wareBusinessData) {
        List<WareBusinessYanBaoGroupEntity> list;
        WareBusinessFsPriceEntity wareBusinessFsPriceEntity;
        if (isDarkTheme()) {
            this.themeStyle = 1;
        } else {
            this.themeStyle = 0;
        }
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
        this.mType4Entity = new ProductToJsNowBuyEntity();
        this.mType5Entity = new ProductToJsNowBuyEntity();
        BusinessPaiPaiSmallImgStockInfo businessPaiPaiSmallImgStockInfo = this.mWareBusinessData.ppInfo;
        if (businessPaiPaiSmallImgStockInfo != null) {
            this.inspectSkuId = businessPaiPaiSmallImgStockInfo.inspectSkuId;
        }
        PdWhiteBarForStyleInfoEntity pdWhiteBarForStyleInfoEntity = wareBusinessData.proPageWhiteBarInfo;
        if (pdWhiteBarForStyleInfoEntity != null && pdWhiteBarForStyleInfoEntity.planInfos != null) {
            this.isHaveWhiteBarData = true;
        } else {
            this.isHaveWhiteBarData = false;
        }
        if (wareBusinessData.plusShieldLandedPriceFlag) {
            addWearyCount();
        }
        this.serviceInfoId = null;
        WareYanBaoEntity wareYanBaoEntity = this.mWareBusinessData.yanBaoInfo;
        if (wareYanBaoEntity == null || (list = wareYanBaoEntity.yanBaoList) == null || list.size() <= 0) {
            return;
        }
        this.mWareBusinessData.yanBaoInfo.yanBaoList.get(0).expanded = true;
    }

    private static boolean formatBoolean(Boolean bool) {
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
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

    public static long getTimeValue(String str) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
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

    private String isTopLife() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !TextUtils.equals(warePropertyInfo.imgToWareNameFrom, "TOPLIFE")) ? "0" : "1";
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
        this.isRecommenExpo = false;
        this.isRankExpo = false;
        this.isAccessExpo = false;
        this.isRelateRecommendExpo = false;
        this.recommendOtherLikeList = null;
        this.isRecommendUploadScrollY = false;
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

    public boolean businessIsShowSpecialFloor(FloorTemplate floorTemplate) {
        WareBusinessData wareBusinessData = floorTemplate.mSkuBaseData;
        if (wareBusinessData != null) {
            WareBusinessSpecialInfo wareBusinessSpecialInfo = wareBusinessData.specialInfo;
            WareMiaoShaInfo wareMiaoShaInfo = wareBusinessData.miaoshaInfo;
            PDSeckillTipEntity pDSeckillTipEntity = wareBusinessData.koInfo;
            WarePromotionInfo warePromotionInfo = wareBusinessData.promotionInfo;
            WareBusinessPromotionNoticeEntity wareBusinessPromotionNoticeEntity = warePromotionInfo != null ? warePromotionInfo.proSalesInfo : null;
            WareBusinessSkuPropertyList wareBusinessSkuPropertyList = wareBusinessData.skuPropertyList;
            return (wareBusinessSpecialInfo != null && wareBusinessSpecialInfo.isSpecial) || !(wareMiaoShaInfo == null || TextUtils.isEmpty(wareMiaoShaInfo.msTrailer)) || (!(pDSeckillTipEntity == null || TextUtils.isEmpty(pDSeckillTipEntity.desc)) || isBusinessgroupGoods(wareBusinessSkuPropertyList != null ? wareBusinessSkuPropertyList.groupGoods : null) || getBusinessFlashPurchaseType(wareBusinessData.flashInfo) == 1 || !(warePromotionInfo == null || wareBusinessPromotionNoticeEntity == null));
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

    public String getAuthKey() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null) ? "" : wareBusinessABTestInfo.authKey;
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
        ArrayList<PDCouponCellEntity> arrayList;
        ArrayList arrayList2 = new ArrayList();
        PreferentialGuideEntity preferentialGuideEntity = this.mPreferentialGuideEntity;
        if (preferentialGuideEntity != null && (list = preferentialGuideEntity.bestCouponId) != null && list.size() > 0) {
            for (String str : this.mPreferentialGuideEntity.bestCouponId) {
                if (!TextUtils.isEmpty(str) && (arrayList = this.mResult) != null && arrayList.size() > 0) {
                    Iterator<PDCouponCellEntity> it = this.mResult.iterator();
                    while (it.hasNext()) {
                        PDCouponCellEntity next = it.next();
                        if (TextUtils.equals(next.couponId, str) && next.applicability && !next.personalCoupon) {
                            arrayList2.add(next);
                        }
                    }
                }
            }
        }
        return arrayList2;
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

    public PDStyleEntity getColorSizeInfo() {
        WareBusinessStyleEntity wareBusinessStyleEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessStyleEntity = wareBusinessData.colorSizeInfo) == null) {
            return null;
        }
        return wareBusinessStyleEntity;
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

    public String getIsXnzt() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null ? wareBusinessData.isXnzt : "";
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

    public PdLiveFloatEntity getLiveInfo() {
        PdLiveFloatEntity pdLiveFloatEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (pdLiveFloatEntity = wareBusinessData.liveInfo) == null) {
            return null;
        }
        return pdLiveFloatEntity;
    }

    public String getLiveInfoLivePic() {
        PdLiveFloatEntity pdLiveFloatEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdLiveFloatEntity = wareBusinessData.liveInfo) == null) ? "" : pdLiveFloatEntity.livePic;
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

    public String getShopABTest() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null) ? "" : wareBusinessABTestInfo.shopABTest;
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
        String sb2 = sb.toString();
        this.mSkuTag = sb2;
        return sb2;
    }

    public List<PDSopSkuInfoEntity> getSopSkuList() {
        WareBusinessStyleEntity wareBusinessStyleEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessStyleEntity = wareBusinessData.colorSizeInfo) == null) {
            return null;
        }
        return wareBusinessStyleEntity.skuList;
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

    /* JADX WARN: Removed duplicated region for block: B:10:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JDJSONObject getToOrderParam() {
        JDJSONObject jDJSONObject;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData != null && !TextUtils.isEmpty(wareBusinessData.extFlag)) {
            try {
                jDJSONObject = JDJSON.parseObject(this.mWareBusinessData.extFlag);
            } catch (Exception unused) {
            }
            if (jDJSONObject == null) {
                jDJSONObject = new JDJSONObject();
            }
            jDJSONObject.put("carModelId", (Object) this.mCurModelId);
            return jDJSONObject;
        }
        jDJSONObject = null;
        if (jDJSONObject == null) {
        }
        jDJSONObject.put("carModelId", (Object) this.mCurModelId);
        return jDJSONObject;
    }

    public String getTokenPrice() {
        WarePriceInfo warePriceInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePriceInfo = wareBusinessData.priceInfo) == null) ? "" : warePriceInfo.tokenPrice;
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

    public List<WareBusinessFeeInfo> getWareBusinessFeeInfo() {
        WareBusinessProductFeeInfo wareBusinessProductFeeInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (wareBusinessProductFeeInfo = wareBusinessData.feeInfo) == null) {
            return null;
        }
        return wareBusinessProductFeeInfo.treatyList;
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

    public String getYuyueAutoAddCartMtaParam() {
        return !isYuyue() ? "0" : isYuyueAutoAddCart() ? "2" : "1";
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

    public boolean hasAHPhone() {
        PdAhStoreEntity pdAhStoreEntity;
        List<String> list;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdAhStoreEntity = wareBusinessData.daojiaStoreInfo) == null || (list = pdAhStoreEntity.storeMobile) == null || list.isEmpty()) ? false : true;
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

    public void initPerformanceControlConfig() {
        SharedPreferences jdSharedPreferences = CommonBase.getJdSharedPreferences();
        if (jdSharedPreferences == null || !TextUtils.equals(jdSharedPreferences.getString("performanceMonitorSwitch", "0"), "1")) {
            return;
        }
        this.isperformanceOpen = true;
    }

    public boolean isAggrePromoFloorShowed() {
        return isFloorShowed("bpAggrePromo") || isFloorShowed(FloorBussinessName.FB_BUSINESS_AGGRE_PROMO_NEW);
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

    public boolean isBybtPbFlag() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        if (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null) {
            return false;
        }
        return warePropertyInfo.bybtPbFlag;
    }

    public boolean isCanBuy() {
        return this.mBasicInfo != null && cartFlag();
    }

    public boolean isCanShare() {
        WareBusinessTurnToH5 wareBusinessTurnToH5;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessTurnToH5 = wareBusinessData.turnToH5) == null || !wareBusinessTurnToH5.isCanShare) ? false : true;
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

    public boolean isDarkTheme() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.darkModel) ? false : true;
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
        return isCartRecommend() || isYuyueAutoAddCart() || this.isOverseaWhiteBar;
    }

    public boolean isJx() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isJx) ? false : true;
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

    public boolean isOpenPerformance() {
        return this.isperformanceOpen;
    }

    public boolean isOpenPlus() {
        WarePlusInfo warePlusInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePlusInfo = wareBusinessData.plusInfo) == null || !warePlusInfo.showButton || TextUtils.isEmpty(warePlusInfo.plusPrice) || TextUtils.isEmpty(this.mWareBusinessData.plusInfo.text) || TextUtils.isEmpty(this.mWareBusinessData.plusInfo.url)) ? false : true;
    }

    public boolean isPaiPaiSecond() {
        BusinessPaiPaiSmallImgStockInfo businessPaiPaiSmallImgStockInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (businessPaiPaiSmallImgStockInfo = wareBusinessData.ppInfo) == null || !businessPaiPaiSmallImgStockInfo.isPPSecondHands) ? false : true;
    }

    public boolean isPhone() {
        String[] strArr;
        BasicInfo basicInfo = this.mBasicInfo;
        return (basicInfo == null || (strArr = basicInfo.categoryIds) == null || !"9987".equals(strArr[0])) ? false : true;
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

    public boolean isSamMember() {
        WareSamInfo wareSamInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareSamInfo = wareBusinessData.samInfo) == null || !wareSamInfo.samMember) ? false : true;
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

    public boolean isShopService() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null || !pDCarShopEntity.isInShopService) ? false : true;
    }

    public boolean isShowBarrage() {
        WarePropertyInfo warePropertyInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (warePropertyInfo = wareBusinessData.property) == null || !warePropertyInfo.isShowBarrage) ? false : true;
    }

    public boolean isShowBtnHandPrice() {
        return hasToHandPrice() && this.mWareBusinessData.toHandssSrengthen.buttonShow;
    }

    public boolean isShowHealth() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null || !TextUtils.equals(pDCarShopEntity.jHealthHentaiEnum, "1")) ? false : true;
    }

    public boolean isShowHealthOnLine() {
        WareBusinessCarAllInfo wareBusinessCarAllInfo;
        PDCarShopEntity pDCarShopEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessCarAllInfo = wareBusinessData.carAllInfo) == null || (pDCarShopEntity = wareBusinessCarAllInfo.carShopInfo) == null || !TextUtils.equals(pDCarShopEntity.jHealthHentaiEnum, "2")) ? false : true;
    }

    public boolean isShowStyleHandPrice() {
        return hasToHandPrice() && this.mWareBusinessData.toHandssSrengthen.selectShow;
    }

    public boolean isStaging() {
        PdBuyByMEntity pdBuyByMEntity;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (pdBuyByMEntity = wareBusinessData.addCart) == null || !TextUtils.equals("staging", pdBuyByMEntity.addCartBusinessName)) ? false : true;
    }

    public boolean isStyleInputFloorHide() {
        return isVirtualGoods() || (this.hideSelectedFloor && isNumHide());
    }

    public boolean isTermite() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.termite;
    }

    public boolean isTermiteCartShield() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.termiteCartShield;
    }

    public boolean isToABTest() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.toABTest) ? false : true;
    }

    public boolean isUseBatchReceiveCoupon() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.isUseBatchReceiveCoupon();
    }

    public boolean isUseReceiveNCoupon() {
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return wareBusinessData != null && wareBusinessData.isUseReceiveNCoupon();
    }

    public boolean isVirtualGoods() {
        String[] strArr;
        BasicInfo basicInfo = this.mBasicInfo;
        if (basicInfo == null || (strArr = basicInfo.categoryIds) == null) {
            return false;
        }
        return Arrays.asList("4835", "4836", "12276", "12277", "100001553", "100002251", "4833", "12273", "7073", "9393", "12275", "12278", "12279", "13758", "15655").contains(strArr[2]);
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

    public boolean isYuyue() {
        WareYuYueInfo wareYuYueInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareYuYueInfo = wareBusinessData.yuyueInfo) == null || !wareYuYueInfo.isYuYue) ? false : true;
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

    public boolean recommendPopup() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.recommendPopup) ? false : true;
    }

    public void resetDataAfterChangeSku() {
        this.isNeedPopCoudanGuide = Boolean.TRUE;
        setNumber(1);
        resetRecommendData();
        this.mSkuTag = "";
        this.jpsCheckStatus = null;
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
        ArrayList<PDCouponCellEntity> arrayList;
        if (TextUtils.isEmpty(str) || (arrayList = this.mResult) == null || arrayList.size() <= 0) {
            return;
        }
        Iterator<PDCouponCellEntity> it = this.mResult.iterator();
        while (it.hasNext()) {
            PDCouponCellEntity next = it.next();
            if (TextUtils.equals(next.couponId, str)) {
                next.applicability = z;
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

    public boolean showBuyLayer() {
        WareBusinessABTestInfo wareBusinessABTestInfo;
        WareBusinessData wareBusinessData = this.mWareBusinessData;
        return (wareBusinessData == null || (wareBusinessABTestInfo = wareBusinessData.abTestInfo) == null || !wareBusinessABTestInfo.showBuyLayer) ? false : true;
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
                            i4 += next3.getNum();
                            next3.setNum(1);
                            next3.setSelect(false);
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

    public void updateWareBusinessData(PDWareBusinessEntity pDWareBusinessEntity) {
        this.mResult = new ArrayList<>();
        FloorTemplateEntity floorTemplateEntity = new FloorTemplateEntity(null, null);
        this.templateEntity = floorTemplateEntity;
        floorTemplateEntity.templates = new ArrayList<>();
        this.mWareBusinessData = null;
        ArrayList<BaseTemplateEntity> arrayList = pDWareBusinessEntity.businessFloorEntities;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        Iterator<BaseTemplateEntity> it = pDWareBusinessEntity.businessFloorEntities.iterator();
        while (it.hasNext()) {
            BaseTemplateEntity next = it.next();
            if (next != null) {
                if (PlatformHelper.isFloorRegister("productDetail", next.mId)) {
                    this.templateEntity.templates.add(next);
                }
                if ((TextUtils.equals(next.mId, "bpMasterdata") || TextUtils.equals(next.mId, FloorBussinessName.FB_BUSINESS_DATA_NEW)) && (next instanceof BusinessFloorEntity)) {
                    WareBusinessData wareBusinessData = (WareBusinessData) JDJSON.parseObject(JDJSON.toJSONString(((BusinessFloorEntity) next).mData), WareBusinessData.class);
                    this.mWareBusinessData = wareBusinessData;
                    if (wareBusinessData != null) {
                        dealWareBusinessData(wareBusinessData);
                    }
                }
                copyBusinessFloorData(next);
            }
        }
    }
}
