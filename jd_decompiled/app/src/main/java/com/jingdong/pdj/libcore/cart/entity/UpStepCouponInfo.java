package com.jingdong.pdj.libcore.cart.entity;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0012B\u0007\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0004R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0004R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0004R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0004R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0013"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/UpStepCouponInfo;", "", "", "incrementDiscount", "Ljava/lang/String;", "golbalTip", "headTip", "middleTip", "tailTip", "upstepLessQuota", "Lcom/jingdong/pdj/libcore/cart/entity/CouponDisplayInfo;", "couponInfo", "Lcom/jingdong/pdj/libcore/cart/entity/CouponDisplayInfo;", "Lcom/jingdong/pdj/libcore/cart/entity/UpStepCouponInfo$ButtonType;", "buttonStep", "Lcom/jingdong/pdj/libcore/cart/entity/UpStepCouponInfo$ButtonType;", "<init>", "()V", "ButtonType", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class UpStepCouponInfo {
    @JvmField
    @Nullable
    public ButtonType buttonStep;
    @JvmField
    @Nullable
    public CouponDisplayInfo couponInfo;
    @JvmField
    @Nullable
    public String golbalTip = "";
    @JvmField
    @Nullable
    public String headTip = "";
    @JvmField
    @Nullable
    public String upstepLessQuota = "";
    @JvmField
    @Nullable
    public String middleTip = "";
    @JvmField
    @Nullable
    public String incrementDiscount = "";
    @JvmField
    @Nullable
    public String tailTip = "";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\b\u0010\tR\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\n"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/UpStepCouponInfo$ButtonType;", "", "", "buttonType", "Ljava/lang/Integer;", "", "buttonDesc", "Ljava/lang/String;", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class ButtonType {
        @JvmField
        @Nullable
        public Integer buttonType = 0;
        @JvmField
        @Nullable
        public String buttonDesc = "";
    }
}
