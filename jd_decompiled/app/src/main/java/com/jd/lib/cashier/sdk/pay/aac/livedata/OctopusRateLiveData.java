package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.o;
import com.jd.lib.cashier.sdk.pay.bean.OctopusRateResponse;
import com.jingdong.jdsdk.a.a;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\u00a2\u0006\u0004\b\n\u0010\tJ\u0015\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\b\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\u000b"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/OctopusRateLiveData;", "Landroidx/lifecycle/LiveData;", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/o;", "Lcom/jd/lib/cashier/sdk/pay/bean/OctopusRateResponse;", "octopusRateResponse", "", a.a, "(Lcom/jd/lib/cashier/sdk/pay/bean/OctopusRateResponse;)V", "b", "()V", "<init>", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class OctopusRateLiveData extends LiveData<o> {
    public final void a(@NotNull OctopusRateResponse octopusRateResponse) {
        octopusRateResponse.setOctopusResult(OctopusRateResponse.Result.SUCCESS);
        postValue(new o(octopusRateResponse));
    }

    public final void b() {
        OctopusRateResponse octopusRateResponse = new OctopusRateResponse();
        octopusRateResponse.setOctopusResult(OctopusRateResponse.Result.FAILURE);
        postValue(new o(octopusRateResponse));
    }
}
