package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.common.bean.CashierGetSuccessUrlEntity;
import com.jd.lib.cashier.sdk.common.bean.GraduallyPayPopup;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.n;
import com.jingdong.jdsdk.a.a;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\u00a2\u0006\u0004\b\b\u0010\tJ\u0017\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\n"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/LargePaymentLiveData;", "Landroidx/lifecycle/LiveData;", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/n;", "Lcom/jd/lib/cashier/sdk/common/bean/CashierGetSuccessUrlEntity;", "successUrlEntity", "", a.a, "(Lcom/jd/lib/cashier/sdk/common/bean/CashierGetSuccessUrlEntity;)V", "<init>", "()V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class LargePaymentLiveData extends LiveData<n> {
    public final void a(@Nullable CashierGetSuccessUrlEntity successUrlEntity) {
        GraduallyPayPopup graduallyPayPopup;
        if (successUrlEntity == null || (graduallyPayPopup = successUrlEntity.graduallyPayPopup) == null) {
            return;
        }
        postValue(new n(graduallyPayPopup));
    }
}
