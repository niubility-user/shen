package com.jingdong.pdj.libcore.cart.entity;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002\u00a2\u0006\u0004\b\t\u0010\nR\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\b\u0010\u0004\u00a8\u0006\u000b"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/SkuOperDTO;", "", "", "skuId", "Ljava/lang/String;", "", "skuNum", "Ljava/lang/Integer;", "skuUuid", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class SkuOperDTO {
    @JvmField
    @Nullable
    public String skuId;
    @JvmField
    @Nullable
    public Integer skuNum;
    @JvmField
    @Nullable
    public String skuUuid;

    public SkuOperDTO() {
        this(null, null, null, 7, null);
    }

    public SkuOperDTO(@Nullable String str, @Nullable Integer num, @Nullable String str2) {
        this.skuId = str;
        this.skuNum = num;
        this.skuUuid = str2;
    }

    public /* synthetic */ SkuOperDTO(String str, Integer num, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? 0 : num, (i2 & 4) != 0 ? "" : str2);
    }
}
