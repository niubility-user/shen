package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.j;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.l;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCardPayPlanResponse;
import com.jingdong.jdsdk.a.a;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ!\u0010\b\u001a\u00020\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\f"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/CreditCardPayPlanFailureLiveData;", "Landroidx/lifecycle/LiveData;", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/j;", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/l;", "requestParamsContainer", "Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCardPayPlanResponse;", "creditCardPayPlanResponse", "", a.a, "(Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/l;Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCardPayPlanResponse;)V", "<init>", "()V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CreditCardPayPlanFailureLiveData extends LiveData<j> {
    public final void a(@Nullable l requestParamsContainer, @Nullable CreditCardPayPlanResponse creditCardPayPlanResponse) {
        if (creditCardPayPlanResponse != null) {
            postValue(new j(requestParamsContainer, creditCardPayPlanResponse));
        }
    }
}
