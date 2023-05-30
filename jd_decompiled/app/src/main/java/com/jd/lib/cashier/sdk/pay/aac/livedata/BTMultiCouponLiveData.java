package com.jd.lib.cashier.sdk.pay.aac.livedata;

import androidx.lifecycle.LiveData;
import com.jd.lib.cashier.sdk.pay.aac.livedata.a.a;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoCouponResponse;

/* loaded from: classes14.dex */
public class BTMultiCouponLiveData extends LiveData<a> {
    public void a(BaiTiaoCouponResponse baiTiaoCouponResponse) {
        if (baiTiaoCouponResponse != null) {
            postValue(new a(Boolean.FALSE, baiTiaoCouponResponse));
        }
    }

    public void b(BaiTiaoCouponResponse baiTiaoCouponResponse) {
        if (baiTiaoCouponResponse != null) {
            postValue(new a(Boolean.TRUE, baiTiaoCouponResponse));
        }
    }
}
