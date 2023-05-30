package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.MainPicDpgInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDInternetHospitalEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PDMtaEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PdAhStoreEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PdBuyByMEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.PdToHandPriceEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.RecommendToast;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.buttoninfo.PDBottomInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.delivery.PDDeliveryInstallEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.ecard.PDECardInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.installment.PDInstallmentInfoEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.installment.PDInstallmentTipEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.loc.LocInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.loc.LocShopInfo;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.plusmember.PDPlusFreightEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.promotion.PdSuitEntry;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.regularbuy.PDRegularBuyEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.seckillcertify.PDSeckillCertifyEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.seckillcertify.PDSeckillTipEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.shop.PdShopEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.sizehelper.PDSizeHelperEntity;
import com.jingdong.app.mall.bundle.styleinfoview.entitys.sizehelper.PdShoesSizeHelperEntity;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class WareBusinessData {
    public WareYuShouInfo YuShouInfo;
    public boolean abTest800;
    public WareBusinessABTestInfo abTestInfo;
    public PdAcsEntity acsModel;
    public PdBuyByMEntity addCart;
    public AddFamilyListData addFamilyList;
    public WareAppletShare appletShare;
    public ArWakeUpData arMakeUpMap;
    public WareBusinessAttentInfo attentionInfo;
    public DaoShouPriceBannerInfo bannerInfo;
    public PDBottomInfo buttonInfo;
    public BusinessBybtInfo bybtBannerInfo;
    public WareBusinessCarAllInfo carAllInfo;
    public WareBusinessCarFinanceEntity carFinance;
    public SuperBrandEntity cjxpInfo;
    public String clothMark;
    public WareBusinessStyleEntity colorSizeInfo;
    public PdAhStoreEntity daojiaStoreInfo;
    public PdDdFatigueMechanism ddFatigueMechanism;
    public DefineSkinChange defineSkinChange;
    public PDDeliveryInstallEntity deliveryInstall;
    public WareBusinessDiscountPriceEntity discountPriceInfo;
    public String doublepriceUp;
    public WareBusinessDrugEntity drugInfo;
    public PDECardInfoEntity eCardInfo;
    public WareEventParam eventParam;
    public String extFlag;
    public FastChatEntity familyChatInfo;
    public WareBusinessProductFeeInfo feeInfo;
    public WareFlashInfoEntity flashInfo;
    public WareFurnitureInfo furnitureIncrementServiceInfo;
    public Map<String, String> generalTrackDic;
    public PdGiftShopEntity giftShopping;
    public String guiGeMUrlAppendUnit;
    public WareBusinessHwShareInfo hwShareInfo;
    public PDSeckillCertifyEntity idAuth;
    public String idAuthToast;
    public PDInstallmentInfoEntity installmentInfo;
    public PDInstallmentTipEntity installmentTip;
    public PDInternetHospitalEntity internetHospital;
    public boolean isDesCbc;
    public boolean isHyj;
    public boolean isNewProBanner;
    public boolean isOldAddress;
    public boolean isOpen;
    public boolean isOpenCar;
    public String isXnzt;
    public PdjServiceSpecialBtnInfo jServiceSpecialBtnInfo;
    public WareJdServerPlusEntity jdSerPlusInfo;
    public WareBusinessCollageJoinBuyInfoEntity joinBuyInfo;
    public JpsAgreementEntity jpsAgreement;
    public WareBusinessJxInfoEntity jxInfo;
    public PDSeckillTipEntity koInfo;
    public PdLiveFloatEntity liveInfo;
    public boolean locCouponFlag;
    public LocInfo locInfo;
    public LocShopInfo locShopInfo;
    public MainPicDpgInfo mainPicDpgInfo;
    public WareBusinessPlusForBuyMt makeMoreTime;
    public WareMiaoShaInfo miaoshaInfo;
    public WareShowStateBarEntity modelAdapterInfo;
    public PDMtaEntity mtaInfo;
    public boolean newStyle;
    public WareBusinessDSJPriceForBannerEntity otherUseBannerInfo;
    public WareBusinessPaiPaiReplacement paiPaiOld4New;
    public PDPlusFreightEntity plusFeeTips;
    public WarePlusInfo plusInfo;
    public boolean plusShieldLandedPriceFlag;
    public WareBusinessPointInfo pointInfo;
    public BusinessPaiPaiSmallImgStockInfo ppInfo;
    public WarePriceInfo priceInfo;
    public String priceIntr;
    public String priceLabel;
    public PdWhiteBarForStyleInfoEntity proPageWhiteBarInfo;
    public WarePromotionInfo promotionInfo;
    public BusinessPromotionPoint promotionPoint;
    public WarePropertyInfo property;
    public String rankName;
    public int rankType;
    public RecommendToast recommendToast;
    public PDRegularBuyEntity regularBuy;
    public String rxNewPrescriptInfoResult;
    public WareSamInfo samInfo;
    public BusinessSearchInfo searchInfo;
    public SecondPriceEntity secondPriceInfo;
    public WareBusinessShareImageInfo shareImgInfo;
    public PdShopEntity shopInfo;
    public PDSkinEntity skin;
    public WareBusinessSkuPropertyList skuPropertyList;
    public WareBusinessSoldOverSea soldOversea;
    public WareBusinessSpecialInfo specialInfo;
    public WareStockEntity stockNode;
    public PdSuitEntry suit;
    public boolean supportSale;
    public SurveyData surveyDO;
    public String tabUrl;
    public String templateType;
    public boolean termite;
    public boolean termiteCartShield;
    public ToHandData toHandsBannerInfo;
    public PdToHandPriceEntity toHandssSrengthen;
    public boolean topOn;
    public ArrayList<TreayNewInfo> treayNewInfo;
    public WareBusinessTurnToH5 turnToH5;
    public PdShoesSizeHelperEntity userShoesSizeInfo;
    public PDSizeHelperEntity userSizeInfo;
    public WareEyeSightInfo wareEyeSight;
    public List<WareBusinessWareImageEntity> wareImage;
    public WareBasicInfo wareInfo;
    public PDWebCastEntity webCast;
    public WareBusinessWeightEntity weightInfo;
    public WareYanBaoEntity yanBaoInfo;
    public YuShouBannerInfo yuShouBannerInfo;
    public WareYuYueInfo yuyueInfo;
    public boolean isOpenNode = true;
    public boolean isOpenH5 = true;

    public String getBusinessJPrice() {
        Double valueOf;
        try {
            WarePriceInfo warePriceInfo = this.priceInfo;
            if (warePriceInfo != null && (valueOf = Double.valueOf(warePriceInfo.jprice)) != null && valueOf.doubleValue() > 0.0d) {
                return new DecimalFormat("0.00").format(valueOf);
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("WareBusinessData", e2);
            }
        }
        return StringUtil.no_price;
    }

    public String getBusinessSkuId() {
        WareBasicInfo wareBasicInfo = this.wareInfo;
        return wareBasicInfo != null ? wareBasicInfo.skuId : "";
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
        WareBusinessDrugEntity wareBusinessDrugEntity = this.drugInfo;
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
