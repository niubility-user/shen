package com.jd.lib.cashier.sdk.pay.bean.coupon;

import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jingdong.jdsdk.auraSetting.AuraConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b \b\u0016\u0018\u00002\u00020\u0001B\t\b\u0016\u00a2\u0006\u0004\bQ\u0010\u0004J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0004R$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\t\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\rR$\u0010\u0018\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\t\u001a\u0004\b\u0019\u0010\u000b\"\u0004\b\u001a\u0010\rR$\u0010\u001b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001b\u0010\t\u001a\u0004\b\u001c\u0010\u000b\"\u0004\b\u001d\u0010\rR*\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u001e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010%\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b%\u0010\t\u001a\u0004\b&\u0010\u000b\"\u0004\b'\u0010\rR\"\u0010(\u001a\u00020\u000e8\u0016@\u0016X\u0096\u000e\u00a2\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R*\u0010/\u001a\n\u0012\u0004\u0012\u00020.\u0018\u00010\u001e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b/\u0010 \u001a\u0004\b0\u0010\"\"\u0004\b1\u0010$R$\u00103\u001a\u0004\u0018\u0001028\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R$\u00109\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b9\u0010\t\u001a\u0004\b:\u0010\u000b\"\u0004\b;\u0010\rR$\u0010<\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b<\u0010\t\u001a\u0004\b=\u0010\u000b\"\u0004\b>\u0010\rR$\u0010?\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b?\u0010\t\u001a\u0004\b@\u0010\u000b\"\u0004\bA\u0010\rR$\u0010B\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bB\u0010\t\u001a\u0004\bC\u0010\u000b\"\u0004\bD\u0010\rR$\u0010E\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bE\u0010\t\u001a\u0004\bF\u0010\u000b\"\u0004\bG\u0010\rR$\u0010H\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bH\u0010\t\u001a\u0004\bI\u0010\u000b\"\u0004\bJ\u0010\rR$\u0010K\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bK\u0010\t\u001a\u0004\bL\u0010\u000b\"\u0004\bM\u0010\rR$\u0010N\u001a\u0004\u0018\u00010\u000e8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\bN\u0010\u0010\u001a\u0004\bO\u0010\u0012\"\u0004\bP\u0010\u0014\u00a8\u0006R"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntityRequest;", "Lcom/jd/lib/cashier/sdk/core/model/ICheckNullObj;", "", "checkPlansList", "()V", "checkCouponList", "checkNullObjAndInit", "", AuraConstants.MESSAGE_COUPON_TYPE_FROM_NOTICE, "Ljava/lang/String;", "getCouponType", "()Ljava/lang/String;", "setCouponType", "(Ljava/lang/String;)V", "", DYConstants.DY_SELECTED, "Ljava/lang/Boolean;", "getSelected", "()Ljava/lang/Boolean;", "setSelected", "(Ljava/lang/Boolean;)V", "couponDesc", "getCouponDesc", "setCouponDesc", "cutOffType", "getCutOffType", "setCutOffType", "termOfValidity", "getTermOfValidity", "setTermOfValidity", "", "plans", "Ljava/util/List;", "getPlans", "()Ljava/util/List;", "setPlans", "(Ljava/util/List;)V", "activityId", "getActivityId", "setActivityId", "canUse", "Z", "getCanUse", "()Z", "setCanUse", "(Z)V", "Lcom/jd/lib/cashier/sdk/pay/bean/coupon/CouponEntity;", "combinationCouponList", "getCombinationCouponList", "setCombinationCouponList", "", "canCombinationActUuidList", "Ljava/lang/Object;", "getCanCombinationActUuidList", "()Ljava/lang/Object;", "setCanCombinationActUuidList", "(Ljava/lang/Object;)V", "couponInfo", "getCouponInfo", "setCouponInfo", "extInfo", "getExtInfo", "setExtInfo", "actUuId", "getActUuId", "setActUuId", "couponTypeDesc", "getCouponTypeDesc", "setCouponTypeDesc", "couponId", "getCouponId", "setCouponId", "defaultPlan", "getDefaultPlan", "setDefaultPlan", "couponClass", "getCouponClass", "setCouponClass", "stackableTag", "getStackableTag", "setStackableTag", "<init>", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public class CouponEntityRequest implements ICheckNullObj {
    @Nullable
    private Object canCombinationActUuidList;
    private boolean canUse;
    @Nullable
    private List<CouponEntity> combinationCouponList;
    @Nullable
    private Boolean selected;
    @Nullable
    private Boolean stackableTag;
    @Nullable
    private String couponClass = "";
    @Nullable
    private String actUuId = "";
    @Nullable
    private String couponId = "";
    @Nullable
    private String activityId = "";
    @Nullable
    private String couponType = "";
    @Nullable
    private List<String> plans = new ArrayList();
    @Nullable
    private String defaultPlan = "";
    @Nullable
    private String termOfValidity = "";
    @Nullable
    private String couponInfo = "";
    @Nullable
    private String couponDesc = "";
    @Nullable
    private String couponTypeDesc = "";
    @Nullable
    private String extInfo = "";
    @Nullable
    private String cutOffType = "";

    public CouponEntityRequest() {
        Boolean bool = Boolean.FALSE;
        this.selected = bool;
        this.stackableTag = bool;
        this.combinationCouponList = new ArrayList();
    }

    private final void checkCouponList() {
        if (this.combinationCouponList == null) {
            this.combinationCouponList = new ArrayList();
        }
        g0.f(this.combinationCouponList);
    }

    private final void checkPlansList() {
        if (this.plans == null) {
            this.plans = new ArrayList();
        }
        g0.f(this.plans);
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        checkCouponList();
        checkPlansList();
    }

    @Nullable
    public final String getActUuId() {
        return this.actUuId;
    }

    @Nullable
    public final String getActivityId() {
        return this.activityId;
    }

    @Nullable
    public final Object getCanCombinationActUuidList() {
        return this.canCombinationActUuidList;
    }

    public boolean getCanUse() {
        return this.canUse;
    }

    @Nullable
    public final List<CouponEntity> getCombinationCouponList() {
        return this.combinationCouponList;
    }

    @Nullable
    public final String getCouponClass() {
        return this.couponClass;
    }

    @Nullable
    public final String getCouponDesc() {
        return this.couponDesc;
    }

    @Nullable
    public final String getCouponId() {
        return this.couponId;
    }

    @Nullable
    public final String getCouponInfo() {
        return this.couponInfo;
    }

    @Nullable
    public final String getCouponType() {
        return this.couponType;
    }

    @Nullable
    public final String getCouponTypeDesc() {
        return this.couponTypeDesc;
    }

    @Nullable
    public final String getCutOffType() {
        return this.cutOffType;
    }

    @Nullable
    public final String getDefaultPlan() {
        return this.defaultPlan;
    }

    @Nullable
    public final String getExtInfo() {
        return this.extInfo;
    }

    @Nullable
    public final List<String> getPlans() {
        return this.plans;
    }

    @Nullable
    public final Boolean getSelected() {
        return this.selected;
    }

    @Nullable
    public final Boolean getStackableTag() {
        return this.stackableTag;
    }

    @Nullable
    public final String getTermOfValidity() {
        return this.termOfValidity;
    }

    public final void setActUuId(@Nullable String str) {
        this.actUuId = str;
    }

    public final void setActivityId(@Nullable String str) {
        this.activityId = str;
    }

    public final void setCanCombinationActUuidList(@Nullable Object obj) {
        this.canCombinationActUuidList = obj;
    }

    public void setCanUse(boolean z) {
        this.canUse = z;
    }

    public final void setCombinationCouponList(@Nullable List<CouponEntity> list) {
        this.combinationCouponList = list;
    }

    public final void setCouponClass(@Nullable String str) {
        this.couponClass = str;
    }

    public final void setCouponDesc(@Nullable String str) {
        this.couponDesc = str;
    }

    public final void setCouponId(@Nullable String str) {
        this.couponId = str;
    }

    public final void setCouponInfo(@Nullable String str) {
        this.couponInfo = str;
    }

    public final void setCouponType(@Nullable String str) {
        this.couponType = str;
    }

    public final void setCouponTypeDesc(@Nullable String str) {
        this.couponTypeDesc = str;
    }

    public final void setCutOffType(@Nullable String str) {
        this.cutOffType = str;
    }

    public final void setDefaultPlan(@Nullable String str) {
        this.defaultPlan = str;
    }

    public final void setExtInfo(@Nullable String str) {
        this.extInfo = str;
    }

    public final void setPlans(@Nullable List<String> list) {
        this.plans = list;
    }

    public final void setSelected(@Nullable Boolean bool) {
        this.selected = bool;
    }

    public final void setStackableTag(@Nullable Boolean bool) {
        this.stackableTag = bool;
    }

    public final void setTermOfValidity(@Nullable String str) {
        this.termOfValidity = str;
    }
}
