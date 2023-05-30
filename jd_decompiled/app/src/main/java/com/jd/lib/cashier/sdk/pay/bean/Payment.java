package com.jd.lib.cashier.sdk.pay.bean;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.core.model.BaiTiaoExtraTip;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.ui.widget.IPlanItemViewEntity;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.b.a;
import com.jd.lib.cashier.sdk.h.h.l;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.BaiTiaoPlanInfo;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponAndCutOffs;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCard;
import com.jd.lib.cashier.sdk.pay.bean.digitalmoney.DigitalMoneyPayEntity;
import com.jd.lib.cashier.sdk.pay.dialog.e;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class Payment implements ICheckNullObj {
    public static final String COUPON_SELECTABLE = "1";
    public static final String COUPON_UNABLE_SELECTABLE = "";
    public static final String EXTEND_PAYMENT_SUCCESS = "1";
    public static final String RIGHT_ICON_TYPE_ARROW = "2";
    public static final String RIGHT_ICON_TYPE_CHECKBOX = "1";
    public static final String RIGHT_ICON_TYPE_NONE = "0";
    public static final String STATUS_COMBINATION_PAYMENT = "5";
    public static final String STATUS_DISABLE_CLICK = "9";
    public static final String STATUS_INVISIBILITY = "7";
    public static final String STATUS_NORMAL = "1";
    public static final String STATUS_UNAVAILABLE = "3";
    public String addNewCardScene;
    public AgreementServiceMapMap agreementServiceMap;
    public AllCoupons allCoupons;
    public String arrowPosition;
    public String availableBalance;
    public BaiTiaoExtraTip baiTiaoExtraTip;
    public String baiTiaoHighlightTip;
    public CouponEntity baiTiaoPlanInfoCoupon;
    public String baiTiaoTip;
    public BaiTiaoPlanInfo baitiaoPlanInfo;
    public String bgImage;
    public List<CreditCard> bindingCardArray;
    public String blackBgImage;
    private int canUseCount;
    public boolean canUsePaymentAcc;
    private boolean cardAd;
    public String changetag;
    public String channelDesc;
    public String choosePlanTip;
    public boolean clickEvent;
    public CouponEntity currentCoupon;
    public CreditCard currentCreditCardBank;
    public PlanFeeEntity currentPlanFee;
    public CouponEntity defaultCoupon;
    public PlanFeeEntity defaultPlanFee;
    public boolean defaultSelected;
    public String extraInfo;
    public GouWuJinModel gouWuJinModel;
    public boolean guidOpenHoneyPay;
    public boolean hasCouponExpo;
    public boolean hasPaymentExpo;
    public List<String> iconList;
    public String investorDoc;
    public boolean isSourceFromDialogSelected;
    public boolean jdPay;
    public String jdPayChannel;
    public String openXjkLargePayFlag;
    public String payToken;
    public String planButtonTitle;
    public List<ProductInfo> productInfos;
    public CouponEntity recommendCoupon;
    public String regulatorCantUseDesc;
    public String rightIconType;
    public int rootViewHeight;
    public String saleStr;
    public boolean selected;
    public CouponAndCutOffs selectedCommonCoupon;
    public CouponEntity selectedCoupon;
    public e selectedCouponEntity;
    public PlanFeeEntity selectedPlanFee;
    public PlanServiceMap serviceMap;
    public boolean showSkuList;
    public String subIrrDoc;
    public CouponEntity targetCoupon;
    public PlanFeeEntity targetPlanFee;
    public CashierCommonPopConfig toastModel;
    public DigitalMoneyPayEntity virtualPayModel;
    public String extendPayment = "";
    public String extendPaymentUrl = "";
    public String channelId = "";
    public String uniqueChannelId = "";
    public String status = "";
    public String channelName = "";
    public String channelSimpleName = "";
    public String code = "";
    public String logo = "";
    public String realAmount = "";
    public String preferentiaNum = "";
    public String tip = "";
    public String defaultCouponId = "";
    public String statusDesc = "";
    public String payWay = "";
    public String buttonTitle = "";
    public String moveDownFlag = "";
    private String channelCode = "";
    private String isNewFlag = "";
    public String moreInfoTip = "";
    public String canSelectCoupon = "";
    public String accountCode = "";
    public String skuSizeInfo = "";
    public String imgAddress = "";
    public String canUseBalance = "";
    public String moneyFlag = "";
    public String jxjMoneyText = "";
    public String jxjDesc = "";
    public String productCode = "";
    private String availableLimit = "";
    private String honeyCardId = "";
    private String donee = "";
    private String doneeName = "";
    private String doner = "";
    public String channelNameMiddle = "";
    public String channelNamePre = "";
    public String channelNameTail = "";
    public String planRate = "";
    public String combineType = "";
    public String buttonSubtitle = "";
    public String recommendPlan = "";
    public String jdServiceProviderTip = "";
    public String mianxiHighlight = "";
    public List<IPlanItemViewEntity> planFeeEntityList = new ArrayList(6);
    public String currentSelectedPlan = "";
    public a.b splitLineType = a.b.NORMAL;

    private void checkAllCoupon() {
        if (this.allCoupons == null) {
            this.allCoupons = new AllCoupons();
        }
        this.allCoupons.checkNullObjAndInit();
    }

    private void checkBTPlanInfo() {
        BaiTiaoPlanInfo baiTiaoPlanInfo = this.baitiaoPlanInfo;
        if (baiTiaoPlanInfo != null) {
            baiTiaoPlanInfo.checkNullObjAndInit();
        }
    }

    private void checkBindCardArray() {
        if (this.bindingCardArray == null) {
            this.bindingCardArray = new ArrayList();
        }
        g0.f(this.bindingCardArray);
        if (this.bindingCardArray.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.bindingCardArray.size(); i2++) {
            CreditCard creditCard = this.bindingCardArray.get(i2);
            if (creditCard != null) {
                creditCard.checkNullObjAndInit();
            }
        }
    }

    private void checkCoupon() {
        if (this.defaultCoupon == null) {
            this.defaultCoupon = new CouponEntity();
        }
        this.defaultCoupon.checkNullObjAndInit();
    }

    private void checkGouWujin() {
        if (this.gouWuJinModel == null) {
            this.gouWuJinModel = new GouWuJinModel();
        }
        this.gouWuJinModel.checkNullObjAndInit();
    }

    private void checkIconList() {
        if (this.iconList == null) {
            this.iconList = new ArrayList();
        }
        g0.f(this.iconList);
    }

    private void checkPlanFee() {
        if (this.defaultPlanFee == null) {
            this.defaultPlanFee = new PlanFeeEntity();
        }
        this.defaultPlanFee.checkNullObjAndInit();
    }

    private void checkProductInfo() {
        if (this.productInfos == null) {
            this.productInfos = new ArrayList();
        }
        g0.f(this.productInfos);
        if (this.productInfos.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.productInfos.size(); i2++) {
            ProductInfo productInfo = this.productInfos.get(i2);
            if (productInfo != null) {
                productInfo.checkNullObjAndInit();
            }
        }
    }

    private void initDefaultSelected() {
        this.selected = this.defaultSelected;
    }

    public boolean canUse() {
        return !TextUtils.equals(this.status, "3");
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        checkAllCoupon();
        checkIconList();
        checkProductInfo();
        checkBindCardArray();
        checkPlanFee();
        checkCoupon();
        checkGouWujin();
        checkBTPlanInfo();
        initDefaultSelected();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Payment) {
            Payment payment = (Payment) obj;
            return l.a(this.code, payment.code) && l.a(this.channelId, payment.channelId) && l.a(this.uniqueChannelId, payment.uniqueChannelId);
        }
        return false;
    }

    public int hashCode() {
        return l.b(this.channelId, this.uniqueChannelId, this.status, this.channelName, this.channelSimpleName, this.code, this.payWay, this.channelCode);
    }

    public boolean isCombine() {
        return TextUtils.equals(this.status, "5");
    }

    public String toString() {
        return "Payment{extendPayment='" + this.extendPayment + "', extendPaymentUrl='" + this.extendPaymentUrl + "', canUsePaymentAcc=" + this.canUsePaymentAcc + ", channelId='" + this.channelId + "', uniqueChannelId='" + this.uniqueChannelId + "', status='" + this.status + "', channelName='" + this.channelName + "', channelSimpleName='" + this.channelSimpleName + "', code='" + this.code + "', defaultSelected=" + this.defaultSelected + ", logo='" + this.logo + "', realAmount='" + this.realAmount + "', preferentiaNum='" + this.preferentiaNum + "', tip='" + this.tip + "', defaultCouponId='" + this.defaultCouponId + "', allCoupons=" + this.allCoupons + ", cardAd=" + this.cardAd + ", statusDesc='" + this.statusDesc + "', payWay='" + this.payWay + "', buttonTitle='" + this.buttonTitle + "', moveDownFlag='" + this.moveDownFlag + "', channelCode='" + this.channelCode + "', isNewFlag='" + this.isNewFlag + "', moreInfoTip='" + this.moreInfoTip + "', iconList=" + this.iconList + ", rightIconType='" + this.rightIconType + "', canSelectCoupon='" + this.canSelectCoupon + "', accountCode='" + this.accountCode + "', showSkuList=" + this.showSkuList + ", skuSizeInfo='" + this.skuSizeInfo + "', productInfos=" + this.productInfos + ", imgAddress='" + this.imgAddress + "', canUseBalance='" + this.canUseBalance + "', moneyFlag='" + this.moneyFlag + "', jxjMoneyText='" + this.jxjMoneyText + "', jxjDesc='" + this.jxjDesc + "', productCode='" + this.productCode + "', availableLimit='" + this.availableLimit + "', honeyCardId='" + this.honeyCardId + "', donee='" + this.donee + "', doneeName='" + this.doneeName + "', doner='" + this.doner + "', channelNameMiddle='" + this.channelNameMiddle + "', channelNamePre='" + this.channelNamePre + "', channelNameTail='" + this.channelNameTail + "', guidOpenHoneyPay=" + this.guidOpenHoneyPay + ", bindingCardArray=" + this.bindingCardArray + ", canUseCount=" + this.canUseCount + ", planRate='" + this.planRate + "', defaultPlanFee=" + this.defaultPlanFee + ", defaultCoupon=" + this.defaultCoupon + ", baitiaoPlanInfo=" + this.baitiaoPlanInfo + ", combineType='" + this.combineType + "', bgImage='" + this.bgImage + "', blackBgImage='" + this.blackBgImage + "', virtualPayModel=" + this.virtualPayModel + ", openXjkLargePayFlag='" + this.openXjkLargePayFlag + "', gouWuJinModel=" + this.gouWuJinModel + ", jdPayChannel='" + this.jdPayChannel + "', changetag='" + this.changetag + "', payToken='" + this.payToken + "', saleStr='" + this.saleStr + "', extraInfo='" + this.extraInfo + "', jdPay=" + this.jdPay + ", toastModel=" + this.toastModel + ", channelDesc='" + this.channelDesc + "', availableBalance='" + this.availableBalance + "', investorDoc='" + this.investorDoc + "', regulatorCantUseDesc='" + this.regulatorCantUseDesc + "', subIrrDoc='" + this.subIrrDoc + "', choosePlanTip='" + this.choosePlanTip + "', baiTiaoTip='" + this.baiTiaoTip + "', baiTiaoHighlightTip='" + this.baiTiaoHighlightTip + "'}";
    }
}
