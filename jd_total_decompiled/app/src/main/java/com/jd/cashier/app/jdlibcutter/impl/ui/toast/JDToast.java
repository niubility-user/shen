package com.jd.cashier.app.jdlibcutter.impl.ui.toast;

import android.content.Context;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.protocol.ui.toast.IToast;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.sdk.jdtoast.ToastUtils;

/* loaded from: classes13.dex */
public class JDToast implements IToast {
    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.toast.IToast
    public void showToast(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            ToastUtils.showToastInCenter(JdSdk.getInstance().getApplicationContext(), str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.toast.IToast
    public void showToast(Context context, int i2) {
        try {
            String string = context.getString(i2);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            ToastUtils.showToastInCenter(context.getApplicationContext(), string);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.jd.cashier.app.jdlibcutter.protocol.ui.toast.IToast
    public void showToast(FragmentActivity fragmentActivity, String str) {
        if (fragmentActivity != null) {
            try {
                if (!fragmentActivity.isFinishing()) {
                    ToastUtils.showToastInCenter(fragmentActivity, str);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        }
        showToast(str);
    }
}
