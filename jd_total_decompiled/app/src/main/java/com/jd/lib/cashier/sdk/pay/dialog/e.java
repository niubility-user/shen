package com.jd.lib.cashier.sdk.pay.dialog;

import com.jd.lib.cashier.sdk.pay.bean.TopPriceAnimationInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public interface e {

    /* loaded from: classes14.dex */
    public static final class a {
        static final /* synthetic */ a a = new a();

        private a() {
        }
    }

    static {
        a aVar = a.a;
    }

    boolean getChecked();

    @NotNull
    String getCouponAmount();

    @NotNull
    String getCouponEntityId();

    @NotNull
    String getCouponTypeForMta();

    @NotNull
    String getCouponTypeName();

    @NotNull
    String getExtraInfo();

    @Nullable
    TopPriceAnimationInfo getPriceAnimationInfo();

    @NotNull
    String getSubtitle();

    @NotNull
    String getTitleName();

    int getViewType();

    void setChecked(boolean z);
}
