package com.jd.lib.cashier.sdk.h.a.a;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack;
import com.jd.cashier.app.jdlibcutter.protocol.risk.IRisk;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;

/* loaded from: classes14.dex */
public class i {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements CommonCallBack<String> {
        final /* synthetic */ FragmentActivity a;

        a(FragmentActivity fragmentActivity) {
            this.a = fragmentActivity;
        }

        @Override // com.jd.cashier.app.jdlibcutter.protocol.callback.CommonCallBack
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void onCallBack(String str) {
            if (TextUtils.isEmpty(str) || !g0.a(this.a)) {
                return;
            }
            ((CashierPayViewModel) ViewModelProviders.of(this.a).get(CashierPayViewModel.class)).b().B = str;
        }
    }

    public static void a(FragmentActivity fragmentActivity) {
        try {
            IRisk risk = DependInitializer.getRisk();
            if (risk != null) {
                risk.genRiskToken(new a(fragmentActivity));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
