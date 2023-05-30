package com.jd.lib.cashier.sdk.pay.bean;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.model.PrismResult;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.f.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes14.dex */
public class CashierPayEntity extends b implements ICheckNullObj {
    public static final String DYNAMIC_FLAG_ON = "1";
    public static final String HAS_GRADUAL = "1";
    public static final String LARGE_PAYMENT_SUCCESS = "1";
    public static final String PRODUCT_A = "3";
    public static final String PRODUCT_B = "2";
    public static final String PRODUCT_ELDER = "1";
    public BottomMarketActivity bottomMarketInfo;
    public BottomRecommendInfo bottomRecommendInfo;
    public CashierCommonPopConfig commonPopupInfo;
    public CashierCommonPopConfig countdownPopInfo;
    public PrismResult defaultSelectABTest;
    public CashierCommonPopConfig dfExceptionInfo;
    public Map<String, Object> foldExpoMap;
    public GradualPayInfo graduallyPayInfo;
    public CashierCommonPopConfig indexPopupConfig;
    public List<Payment> jdOtherPayChannelList;
    public List<Payment> jdPayChannelList;
    public Map<String, Object> metricCommonParams;
    public String orderDetailPopUpSwitch;
    public CashierCommonPopConfig orderExceptionInfo;
    public List<Payment> otherPayChannelList;
    public List<Payment> payChannelList;
    public PlatPayFlagTag platPayFoldTag;
    public PublicGoodPlan publicGoodPlan;
    public volatile List<String> skuIdList;
    public CashierCommonPopConfig subsidyPopupConfig;
    public TopFloor topFloor;
    public VerifyInfoMap verifyInfoMap;
    public String paySource = "";
    public String orderType = "";
    public String orderId = "";
    public String orderPrice = "";
    public String isNewUser = "";
    public String tenBillionSubsidy = "";
    public String requireUUID = "";
    public String jdPayTheme = "";
    public String jdPayThemeBlack = "";
    public String reskinTag = "";
    public String reskinStrategyId = "";
    public String jdPayIcon = "";
    public String jdPayIconBlack = "";
    public String titleDesc = "";
    public String btnDesc = "";
    public String graduallyPay = "";
    public String defaultStrategy = "";
    public String isDefaultCode = "";
    public String payStrategy = "";
    public volatile String skuId = "";
    public String defaultSelectTag = "";
    public String useMCashier = "";
    public String mCashierUrl = "";
    public String defaultSelectToast = "";
    public String isActionSheet = "";
    public String combinedOrderId = "";
    public String payChannelTip = "";
    public String otherPayChannelTip = "";
    public String jdServiceProviderTip = "";
    public String thirdPayExpandTip = "";
    public int recommendJDPayChannelSize = 0;
    public String baitiaoAutoSlideFlag = "";
    public String thirdPayExpandUIFlag = "";
    public String thirdPaychannelFoldTag = "";
    public String thirdPaychannelFoldStrategyId = "";
    public String dynamicFlag = "";
    public String buttonStatus = "";
    public String platPayCashierType = "";
    public String isCertificated = "";

    private void checkBottomMarketActivityInfo() {
        if (this.bottomMarketInfo == null) {
            this.bottomMarketInfo = new BottomMarketActivity();
        }
        this.bottomMarketInfo.checkNullObjAndInit();
    }

    private void checkBottomRecommendInfo() {
        if (this.bottomRecommendInfo == null) {
            this.bottomRecommendInfo = new BottomRecommendInfo();
        }
        this.bottomRecommendInfo.checkNullObjAndInit();
    }

    private void checkCommonPop() {
        if (this.commonPopupInfo == null) {
            this.commonPopupInfo = new CashierCommonPopConfig();
        }
        this.commonPopupInfo.checkNullObjAndInit();
    }

    private void checkCountDown() {
        if (this.countdownPopInfo == null) {
            this.countdownPopInfo = new CashierCommonPopConfig();
        }
        this.countdownPopInfo.checkNullObjAndInit();
    }

    private void checkFoldTag() {
        if (TextUtils.isEmpty(this.thirdPaychannelFoldTag)) {
            this.thirdPaychannelFoldTag = "";
        }
    }

    private void checkGradualPayInfo() {
        if (this.graduallyPayInfo == null) {
            this.graduallyPayInfo = new GradualPayInfo();
        }
        this.graduallyPayInfo.checkNullObjAndInit();
    }

    private void checkIndexPop() {
        if (this.indexPopupConfig == null) {
            this.indexPopupConfig = new CashierCommonPopConfig();
        }
        this.indexPopupConfig.checkNullObjAndInit();
    }

    private void checkJdOtherPayChannelList() {
        if (this.jdOtherPayChannelList == null) {
            this.jdOtherPayChannelList = new ArrayList();
        }
        g0.f(this.jdOtherPayChannelList);
        if (this.jdOtherPayChannelList.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.jdOtherPayChannelList.size(); i2++) {
            Payment payment = this.jdOtherPayChannelList.get(i2);
            if (payment != null) {
                payment.checkNullObjAndInit();
            }
        }
    }

    private void checkJdPayChannelList() {
        if (this.jdPayChannelList == null) {
            this.jdPayChannelList = new ArrayList();
        }
        g0.f(this.jdPayChannelList);
        if (this.jdPayChannelList.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.jdPayChannelList.size(); i2++) {
            Payment payment = this.jdPayChannelList.get(i2);
            if (payment != null) {
                payment.checkNullObjAndInit();
            }
        }
    }

    private void checkOrderException() {
        if (this.orderExceptionInfo == null) {
            this.orderExceptionInfo = new CashierCommonPopConfig();
        }
        this.orderExceptionInfo.checkNullObjAndInit();
    }

    private void checkOtherPayChannelList() {
        if (this.otherPayChannelList == null) {
            this.otherPayChannelList = new ArrayList();
        }
        g0.f(this.otherPayChannelList);
        if (this.otherPayChannelList.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.otherPayChannelList.size(); i2++) {
            Payment payment = this.otherPayChannelList.get(i2);
            if (payment != null) {
                payment.checkNullObjAndInit();
            }
        }
    }

    private void checkPayChannelList() {
        if (this.payChannelList == null) {
            this.payChannelList = new ArrayList();
        }
        g0.f(this.payChannelList);
        if (this.payChannelList.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.payChannelList.size(); i2++) {
            Payment payment = this.payChannelList.get(i2);
            if (payment != null) {
                payment.checkNullObjAndInit();
            }
        }
    }

    private void checkPublicGoodPlan() {
        if (this.publicGoodPlan == null) {
            this.publicGoodPlan = new PublicGoodPlan();
        }
        this.publicGoodPlan.checkNullObjAndInit();
    }

    private void checkTopFloor() {
        if (this.topFloor == null) {
            this.topFloor = new TopFloor();
        }
        this.topFloor.checkNullObjAndInit();
    }

    private void checkVerifyMap() {
        if (this.verifyInfoMap == null) {
            this.verifyInfoMap = new VerifyInfoMap();
        }
        this.verifyInfoMap.checkNullObjAndInit();
    }

    public String checkIsNewUser() {
        return TextUtils.isEmpty(this.isNewUser) ? "" : this.isNewUser;
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        checkFoldTag();
        checkTopFloor();
        checkCommonPop();
        checkOrderException();
        checkIndexPop();
        checkCountDown();
        checkVerifyMap();
        checkGradualPayInfo();
        checkPayChannelList();
        checkJdPayChannelList();
        checkJdOtherPayChannelList();
        checkBottomRecommendInfo();
        checkBottomMarketActivityInfo();
        checkPublicGoodPlan();
        checkOtherPayChannelList();
    }

    public synchronized String getSkuId() {
        if (TextUtils.isEmpty(this.skuId)) {
            StringBuilder sb = new StringBuilder();
            if (this.skuIdList != null && !this.skuIdList.isEmpty()) {
                int size = this.skuIdList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    sb.append(this.skuIdList.get(i2));
                    if (i2 != size - 1) {
                        sb.append("#");
                    }
                }
            }
            this.skuId = sb.toString();
        }
        return this.skuId;
    }

    public String getTouchstoneExpids() {
        PrismResult prismResult = this.defaultSelectABTest;
        return prismResult != null ? prismResult.touchstone_expids : "";
    }

    public boolean showPayLogo() {
        return !TextUtils.isEmpty(this.jdPayIcon);
    }
}
