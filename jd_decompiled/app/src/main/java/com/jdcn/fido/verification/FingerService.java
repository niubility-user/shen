package com.jdcn.fido.verification;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Looper;
import cn.com.union.fido.ui.IAuthPresenter;
import cn.com.union.fido.ui.IAuthSensorService;

@TargetApi(23)
/* loaded from: classes18.dex */
public class FingerService implements IAuthSensorService {
    private CancellationSignal mCancellationSignal = new CancellationSignal();

    /* loaded from: classes18.dex */
    class MyAuthenticationCallback extends FingerprintManager.AuthenticationCallback {
        private IAuthPresenter fidoPresenter;

        private MyAuthenticationCallback(IAuthPresenter iAuthPresenter) {
            this.fidoPresenter = iAuthPresenter;
        }

        public void onAuthenticationAcquired(int i2) {
            this.fidoPresenter.authViewDisplay();
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationError(int i2, CharSequence charSequence) {
            this.fidoPresenter.handlerAuthResult(i2 != 5 ? i2 != 7 ? 17 : 19 : 18, null);
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationFailed() {
            this.fidoPresenter.onAuthenticationFailed();
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationHelp(int i2, CharSequence charSequence) {
            this.fidoPresenter.onAuthenticationHelp(charSequence);
        }

        @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
            this.fidoPresenter.handlerAuthResult(16, null);
        }
    }

    private int isFinger(Activity activity, FingerprintManager fingerprintManager, KeyguardManager keyguardManager) {
        if (Build.VERSION.SDK_INT < 23) {
            return 26;
        }
        if (activity.checkSelfPermission("android.permission.USE_FINGERPRINT") != 0) {
            return 27;
        }
        if (fingerprintManager.isHardwareDetected()) {
            if (keyguardManager.isKeyguardSecure()) {
                return !fingerprintManager.hasEnrolledFingerprints() ? 30 : 0;
            }
            return 29;
        }
        return 28;
    }

    @Override // cn.com.union.fido.ui.IAuthSensorService
    public void endAuthenticator() {
        if (this.mCancellationSignal.isCanceled()) {
            return;
        }
        this.mCancellationSignal.cancel();
    }

    @Override // cn.com.union.fido.ui.IAuthSensorService
    public void startAuthenticator(Activity activity, final IAuthPresenter iAuthPresenter) {
        int i2 = 17;
        if (activity != null) {
            try {
                final FingerprintManager fingerprintManager = (FingerprintManager) activity.getSystemService("fingerprint");
                KeyguardManager keyguardManager = (KeyguardManager) activity.getSystemService("keyguard");
                if (fingerprintManager != null && keyguardManager != null) {
                    int isFinger = isFinger(activity, fingerprintManager, keyguardManager);
                    if (isFinger == 0) {
                        new Thread() { // from class: com.jdcn.fido.verification.FingerService.1
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() {
                                try {
                                    if (Looper.myLooper() == null) {
                                        Looper.prepare();
                                    }
                                    fingerprintManager.authenticate(null, FingerService.this.mCancellationSignal, 0, new MyAuthenticationCallback(iAuthPresenter), null);
                                    Thread.sleep(200L);
                                    iAuthPresenter.authViewDisplay();
                                } catch (Exception unused) {
                                    iAuthPresenter.handlerAuthResult(17, null);
                                }
                            }
                        }.start();
                    }
                    i2 = isFinger;
                }
            } catch (Throwable unused) {
            }
        }
        if (i2 != 0) {
            iAuthPresenter.handlerAuthResult(i2, null);
        }
    }
}
