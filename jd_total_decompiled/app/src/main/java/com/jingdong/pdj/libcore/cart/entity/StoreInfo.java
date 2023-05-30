package com.jingdong.pdj.libcore.cart.entity;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\r\u0010\u000eR\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0007R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\t\u0010\u0004R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010\u0007R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\u0007R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\f\u0010\u0007\u00a8\u0006\u000f"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/StoreInfo;", "", "", "showCoupon", "Ljava/lang/Boolean;", "", "storeId", "Ljava/lang/String;", "initialDeliveryPrice", "scfTag", "storeName", "logoUrl", "achievePrice", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class StoreInfo {
    @JvmField
    @Nullable
    public Boolean scfTag;
    @JvmField
    @Nullable
    public Boolean showCoupon;
    @JvmField
    @Nullable
    public String storeId = "";
    @JvmField
    @Nullable
    public String storeName = "";
    @JvmField
    @Nullable
    public String logoUrl = "";
    @JvmField
    @Nullable
    public String achievePrice = "";
    @JvmField
    @Nullable
    public String initialDeliveryPrice = "";

    public StoreInfo() {
        Boolean bool = Boolean.FALSE;
        this.showCoupon = bool;
        this.scfTag = bool;
    }
}
