package com.jingdong.common.jdreactFramework.views.memberCode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jdpay.membercode.MemberCode;
import com.jingdong.common.deeplinkhelper.DeepLinkCommonHelper;
import com.jingdong.common.jdreactFramework.views.memberCode.JDReactMemberCodeViewManager;
import com.jingdong.jdsdk.widget.ToastUtils;
import com.jingdong.sdk.deeplink.DeepLinkDispatch;

/* loaded from: classes5.dex */
public class MemberCodeImpl extends MemberCode {
    private static final String TAG = "MemberCodeImpl";
    private Activity activity;
    private JDReactMemberCodeViewManager.RTCCodeView mCodeView;

    public MemberCodeImpl(Activity activity, JDReactMemberCodeViewManager.RTCCodeView rTCCodeView) {
        this.activity = activity;
        this.mCodeView = rTCCodeView;
    }

    @Override // com.jdpay.membercode.MemberCode, com.jdpay.membercode.MemberCodeInteractor
    public void onActivated() {
        super.onActivated();
        this.mCodeView.onActivated();
    }

    @Override // com.jdpay.membercode.MemberCode, com.jdpay.membercode.MemberCodeInteractor
    public void onError(int i2, @Nullable Throwable th) {
        super.onError(i2, th);
        String str = " i = " + i2;
        if (i2 == 4) {
            this.mCodeView.closeCodeView();
        } else if (i2 == 1) {
            this.mCodeView.hideSettingMenu();
        }
        this.mCodeView.onError(i2, th);
    }

    @Override // com.jdpay.membercode.MemberCode, com.jdpay.membercode.MemberCodeInteractor
    public void onInactivated() {
        super.onInactivated();
        this.mCodeView.onInactivated();
    }

    @Override // com.jdpay.membercode.MemberCode, com.jdpay.membercode.MemberCodeInteractor
    public void onInited() {
        super.onInited();
        Window window = this.activity.getWindow();
        if (window != null) {
            window.addFlags(8192);
        }
        this.mCodeView.onInited();
    }

    @Override // com.jdpay.membercode.MemberCode, com.jdpay.membercode.MemberCodeInteractor
    public boolean onToast(CharSequence charSequence) {
        ToastUtils.showToastInCenter(charSequence.toString());
        return true;
    }

    @Override // com.jdpay.membercode.MemberCodeInteractor
    public boolean startActivityForResult(@NonNull Intent intent, int i2) {
        String str = "startActivityForResult i = " + i2;
        this.activity.startActivityForResult(intent, i2);
        return true;
    }

    @Override // com.jdpay.membercode.MemberCodeInteractor
    public boolean startBrowser(@NonNull String str, int i2) {
        String str2 = "startBrowser, s = " + str + ", i = " + i2;
        if (TextUtils.equals("onlychecksales", str)) {
            this.mCodeView.startBrowser(str, i2);
            return true;
        }
        Bundle bundle = new Bundle();
        bundle.putString("url", str);
        DeepLinkCommonHelper.startActivityForResult(this.activity, DeepLinkCommonHelper.HOST_WEBACTIVITY, bundle, i2);
        return true;
    }

    @Override // com.jdpay.membercode.MemberCodeInteractor
    public boolean startCommonVerifyModule(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, int i2) {
        String str5 = "startCommonVerifyModule " + str + ", s1 " + str2 + ", s2 " + str3 + ", s3 " + str4 + ", i " + i2;
        Bundle bundle = new Bundle();
        bundle.putString("APP_SOURCE", str);
        bundle.putString("BIZ_SOURCE", str2);
        bundle.putString("SESSION_KEY", str3);
        bundle.putString("BUSINESS_TOKEN", str4);
        DeepLinkDispatch.startActivityForResult(this.activity, "jingdong://jdpayverifyactivity", bundle, i2);
        return true;
    }
}
