package com.jdjr.risk.identity.verify;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.jd.aips.verify.BaseEngineLauncher;
import com.jd.aips.verify.BundleEngineLauncher;
import com.jd.aips.verify.VerifyCallback;
import com.jd.aips.verify.face.FaceVerifyEngine;
import com.jd.aips.verify.tracker.TrackerCallback;

/* loaded from: classes18.dex */
public class SdkEngineLauncher extends BundleEngineLauncher {
    public static final String BUNDLE_NAME_IDCARD_NFC_VERIFY = "com.jd.lib.idcard_nfc_verify";
    public static final String BUNDLE_NAME_IDCARD_OCR_VERIFY = "com.jd.lib.idcard_ocr_verify";

    public SdkEngineLauncher(@NonNull Handler handler, @NonNull BaseEngineLauncher.LauncherCallback launcherCallback) {
        super(handler, launcherCallback);
    }

    public void toFaceVerify(@NonNull Context context, @NonNull Bundle bundle, @NonNull VerifyCallback verifyCallback, TrackerCallback trackerCallback) {
        FaceVerifyEngine.getInstance().launch(context, bundle, verifyCallback, trackerCallback);
    }

    public void toIdCardNfcVerify(@NonNull Context context, @NonNull Bundle bundle, @NonNull VerifyCallback verifyCallback, TrackerCallback trackerCallback) {
        loadBundleToLaunch(context, IdentityVerityEngine.ID_CARD_NFC_VERIFY_ENGINE, BUNDLE_NAME_IDCARD_NFC_VERIFY, bundle, verifyCallback, trackerCallback);
    }

    public void toIdCardVerify(@NonNull Context context, @NonNull Bundle bundle, @NonNull VerifyCallback verifyCallback, TrackerCallback trackerCallback) {
        loadBundleToLaunch(context, IdentityVerityEngine.ID_CARD_VERIFY_ENGINE, BUNDLE_NAME_IDCARD_OCR_VERIFY, bundle, verifyCallback, trackerCallback);
    }
}
