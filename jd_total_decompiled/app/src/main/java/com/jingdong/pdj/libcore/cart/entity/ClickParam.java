package com.jingdong.pdj.libcore.cart.entity;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\nB\u0007\u00a2\u0006\u0004\b\b\u0010\tR\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/ClickParam;", "", "", "where", "Ljava/lang/Integer;", "Lcom/jingdong/pdj/libcore/cart/entity/CartResult;", "cartResult", "Lcom/jingdong/pdj/libcore/cart/entity/CartResult;", "<init>", "()V", "Where", "libCore_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes7.dex */
public final class ClickParam {
    @JvmField
    @Nullable
    public CartResult cartResult;
    @JvmField
    @Nullable
    public Integer where = 0;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\b\u0010\tR\u0016\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0006\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0006\u0010\u0004R\u0016\u0010\u0007\u001a\u00020\u00028\u0006@\u0006X\u0086T\u00a2\u0006\u0006\n\u0004\b\u0007\u0010\u0004\u00a8\u0006\n"}, d2 = {"Lcom/jingdong/pdj/libcore/cart/entity/ClickParam$Where;", "", "", "CLICK_LOGO", "I", "CLICK_SETTLEMENT", "CLICK_ENTER_STORE", "CLICK_MAKE_UP_ORDER", "<init>", "()V", "libCore_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes7.dex */
    public static final class Where {
        public static final int CLICK_ENTER_STORE = 2;
        public static final int CLICK_LOGO = 1;
        public static final int CLICK_MAKE_UP_ORDER = 4;
        public static final int CLICK_SETTLEMENT = 3;
        public static final Where INSTANCE = new Where();

        private Where() {
        }
    }
}
