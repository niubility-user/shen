package com.jingdong.pdj.libcore.cart.entity;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001!B\u0007\u00a2\u0006\u0004\b\u001f\u0010 R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u001e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0018\u0010\n\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0018\u0010\r\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0004R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001e\u00a8\u0006\""}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/CartResult;", "", "", "cartVenderId", "Ljava/lang/String;", "", "Lcom/jingdong/pdj/libcore/cart/entity/SkuEntity;", "tinySkus", "Ljava/util/List;", "", "cartType", "Ljava/lang/Integer;", "", "canDrawCouponStatus", "Ljava/lang/Boolean;", "Lcom/jingdong/pdj/libcore/cart/entity/UpStepCouponInfo;", "upStepCouponInfo", "Lcom/jingdong/pdj/libcore/cart/entity/UpStepCouponInfo;", "Lcom/jingdong/pdj/libcore/cart/entity/PreferentialDetailInfo;", "preferentialDetail", "Lcom/jingdong/pdj/libcore/cart/entity/PreferentialDetailInfo;", "btnStr", "Lcom/jingdong/pdj/libcore/cart/entity/CartProductInfo;", "cartProductInfo", "Lcom/jingdong/pdj/libcore/cart/entity/CartProductInfo;", "Lcom/jingdong/pdj/libcore/cart/entity/StoreInfo;", "storeInfo", "Lcom/jingdong/pdj/libcore/cart/entity/StoreInfo;", "Lcom/jingdong/pdj/libcore/cart/entity/FreightInfo;", "freightInfo", "Lcom/jingdong/pdj/libcore/cart/entity/FreightInfo;", "<init>", "()V", "CartType", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class CartResult {
    @JvmField
    @Nullable
    public CartProductInfo cartProductInfo;
    @JvmField
    @Nullable
    public FreightInfo freightInfo;
    @JvmField
    @Nullable
    public PreferentialDetailInfo preferentialDetail;
    @JvmField
    @Nullable
    public StoreInfo storeInfo;
    @JvmField
    @Nullable
    public List<SkuEntity> tinySkus;
    @JvmField
    @Nullable
    public UpStepCouponInfo upStepCouponInfo;
    @JvmField
    @Nullable
    public Integer cartType = 0;
    @JvmField
    @Nullable
    public String cartVenderId = "";
    @JvmField
    @Nullable
    public String btnStr = "";
    @JvmField
    @Nullable
    public Boolean canDrawCouponStatus = Boolean.FALSE;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0004R\u0016\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0004\u00a8\u0006\n"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/CartResult$CartType;", "", "", "QUERY_EXIST_CART", "I", "CHANGE_CART", "ADD_CART", "QUERY_CART", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class CartType {
        public static final int ADD_CART = 2;
        public static final int CHANGE_CART = 3;
        public static final CartType INSTANCE = new CartType();
        public static final int QUERY_CART = 0;
        public static final int QUERY_EXIST_CART = 1;

        private CartType() {
        }
    }
}
