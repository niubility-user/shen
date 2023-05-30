package com.jd.cashier.app.jdlibcutter.protocol.ui.toast;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;

/* loaded from: classes13.dex */
public interface IToast {
    void showToast(Context context, int i2);

    void showToast(FragmentActivity fragmentActivity, String str);

    void showToast(String str);
}
