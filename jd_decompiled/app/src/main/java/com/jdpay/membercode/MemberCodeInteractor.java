package com.jdpay.membercode;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public interface MemberCodeInteractor {
    public static final int TYPE_ACTIVATE = 2;
    public static final int TYPE_INACTIVATE = 3;
    public static final int TYPE_INIT = 1;
    public static final int TYPE_PAY_RESULT = 5;
    public static final int TYPE_SIGN = 6;
    public static final int TYPE_VERIFY = 4;

    void onActivated();

    void onError(int i2, @Nullable Throwable th);

    void onInactivated();

    void onInited();

    boolean onToast(@NonNull CharSequence charSequence);

    boolean startActivityForResult(@NonNull Intent intent, int i2);

    boolean startBrowser(@NonNull String str, int i2);

    boolean startCommonVerifyModule(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, int i2);
}
