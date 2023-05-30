package com.jd.aips.verify.bankcard;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.jd.aips.verify.BaseEngineLauncher;
import com.jd.aips.verify.BundleEngineLauncher;
import com.jd.aips.verify.VerifyCallback;
import com.jd.aips.verify.tracker.TrackerCallback;
import com.jdcn.biz.client.BankCardManager;

/* loaded from: classes12.dex */
public class SdkEngineLauncher extends BundleEngineLauncher {
    public static final String BUNDLE_NAME_BANKCARD_OCR = "com.jd.lib.risk_bankcard_ocr";

    public SdkEngineLauncher(@NonNull Handler handler, @NonNull BaseEngineLauncher.LauncherCallback launcherCallback) {
        super(handler, launcherCallback);
    }

    public void toBankCardVerify(@NonNull Context context, @NonNull Bundle bundle, @NonNull VerifyCallback verifyCallback, TrackerCallback trackerCallback) {
        loadBundleToLaunch(context, BankCardManager.BANK_CARD_VERIFY_ENGINE, BUNDLE_NAME_BANKCARD_OCR, bundle, verifyCallback, trackerCallback);
    }
}
