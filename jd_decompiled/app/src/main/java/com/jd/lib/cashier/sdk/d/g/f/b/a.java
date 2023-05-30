package com.jd.lib.cashier.sdk.d.g.f.b;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.d.g.b.b;
import com.jingdong.common.utils.pay.OctopusUtils;

/* loaded from: classes14.dex */
public class a extends com.jd.lib.cashier.sdk.d.g.g.a<com.jd.lib.cashier.sdk.d.g.f.c.a> {
    private void e(Activity activity, com.jd.lib.cashier.sdk.d.g.f.c.a aVar) {
        if (aVar == null || TextUtils.isEmpty(aVar.b)) {
            return;
        }
        activity.startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse("octopus://payment?token=" + aVar.b)), 10000);
    }

    @Override // com.jd.lib.cashier.sdk.d.g.g.a
    /* renamed from: f  reason: merged with bridge method [inline-methods] */
    public void d(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.d.g.f.c.a aVar) {
        if (fragmentActivity != null && aVar != null) {
            try {
                if (aVar.a()) {
                    if (y.r(fragmentActivity, OctopusUtils.COM_OCTOPUSCARDS_NFC_READER)) {
                        e(fragmentActivity, aVar);
                    } else {
                        b.a().e(fragmentActivity);
                    }
                }
            } catch (Exception e2) {
                com.jd.lib.cashier.sdk.d.h.a.a("InItPaySdkFunction", "InItPaySdkNorException", "OctopusPayApi.executePay()", e2.getMessage());
                return;
            }
        }
        if (fragmentActivity != null) {
            f0.c(fragmentActivity.getString(R.string.lib_cashier_sdk_cyber_money_pay_failure));
        }
    }
}
