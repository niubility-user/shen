package com.jd.lib.cashier.sdk.c.a;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.text.TextUtils;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.complete.aac.CashierCompleteViewModel;
import com.jd.lib.cashier.sdk.complete.entity.CashierCustomMessage;
import com.jd.lib.cashier.sdk.complete.view.CashierCompleteActivity;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import java.util.Map;

/* loaded from: classes14.dex */
public class a {
    private void b(CashierCompleteActivity cashierCompleteActivity, Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            return;
        }
        String str = map.get(CashierCustomMessage.KEY.MAP_KEY_COPY);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            ((ClipboardManager) cashierCompleteActivity.getSystemService(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD)).setPrimaryClip(ClipData.newPlainText(null, str));
            f0.c(cashierCompleteActivity.getString(R.string.lib_cashier_sdk_copy_to_clipboard_success_hint));
        } catch (Exception unused) {
        }
    }

    private void c(CashierCompleteActivity cashierCompleteActivity) {
        if (g0.a(cashierCompleteActivity)) {
            ((CashierCompleteViewModel) ViewModelProviders.of(cashierCompleteActivity).get(CashierCompleteViewModel.class)).b().f2910e = true;
        }
    }

    public void a(CashierCompleteActivity cashierCompleteActivity, CashierCustomMessage cashierCustomMessage) {
        if (cashierCustomMessage == null || TextUtils.isEmpty(cashierCustomMessage.businessChannel)) {
            return;
        }
        String str = cashierCustomMessage.businessChannel;
        str.hashCode();
        if (str.equals(CashierCustomMessage.KEY.CHANNEL_CLIP_BOARD)) {
            b(cashierCompleteActivity, cashierCustomMessage.message);
        } else if (str.equals(CashierCustomMessage.KEY.CHANNEL_JS_ON_ERROR)) {
            c(cashierCompleteActivity);
        }
    }
}
