package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.b;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.p;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoPayPlanResponse;
import com.jingdong.jdsdk.a.a;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\u00a2\u0006\u0004\b\n\u0010\u000bJ!\u0010\b\u001a\u00020\u00072\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\f"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/aac/livedata/BaiTiaoPayPlanFailureLiveData;", "Landroidx/lifecycle/LiveData;", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/b;", "Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/p;", "requestParamsContainer", "Lcom/jd/lib/cashier/sdk/pay/bean/BaiTiaoPayPlanResponse;", "baiTiaoPayPlanResponse", "", a.a, "(Lcom/jd/lib/cashier/sdk/pay/aac/livedata/a/p;Lcom/jd/lib/cashier/sdk/pay/bean/BaiTiaoPayPlanResponse;)V", "<init>", "()V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class BaiTiaoPayPlanFailureLiveData extends LiveData<b> {
    public final void a(@Nullable p requestParamsContainer, @Nullable BaiTiaoPayPlanResponse baiTiaoPayPlanResponse) {
        if (baiTiaoPayPlanResponse != null) {
            postValue(new b(requestParamsContainer, baiTiaoPayPlanResponse));
        }
    }
}
