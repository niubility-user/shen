package com.jingdong.pdj.libcore.cart.entity;

import com.jingdong.jdsdk.constant.CartConstant;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\t\u0010\nR\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0004R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0004R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0004\u00a8\u0006\u000b"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/FreightInfo;", "", "", "baseFreightDesc", "Ljava/lang/String;", "freightDesc", CartConstant.KEY_SHOP_OVERWEIGHT, "baseFreight", "realFreight", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class FreightInfo {
    @JvmField
    @Nullable
    public String freightDesc = "";
    @JvmField
    @Nullable
    public String baseFreightDesc = "";
    @JvmField
    @Nullable
    public String baseFreight = "";
    @JvmField
    @Nullable
    public String realFreight = "";
    @JvmField
    @Nullable
    public String overWeight = "";
}
