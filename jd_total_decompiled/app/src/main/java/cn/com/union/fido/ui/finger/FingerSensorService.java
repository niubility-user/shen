package cn.com.union.fido.ui.finger;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Looper;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import cn.com.union.fido.bean.authenticator.command.RegisterResponse;
import cn.com.union.fido.bean.authenticator.command.SignResponse;
import cn.com.union.fido.bean.uafclient.Operation;
import cn.com.union.fido.common.FingerAuthenticatorInfo;
import cn.com.union.fido.ui.FidoSignUtil;
import cn.com.union.fido.ui.IAuthPresenter;
import cn.com.union.fido.ui.IAuthSensorService;
import com.jdcn.fido.constant.BasicInformation;
import com.jdcn.fido.utils.TrackerUtil;

@TargetApi(23)
/* loaded from: classes.dex */
public class FingerSensorService implements IAuthSensorService {
    private FidoSignUtil fidoSignUtil = null;
    private CancellationSignal mCancellationSignal = new CancellationSignal();

    @TargetApi(23)
    /* loaded from: classes.dex */
    class MyAuthenticationCallback extends FingerprintManager.AuthenticationCallback {
        private Activity activity;
        private IAuthPresenter fidoPresenter;

        private MyAuthenticationCallback(Activity activity, IAuthPresenter iAuthPresenter) {
            this.activity = activity;
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
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(Build.VERSION.SDK_INT);
            if (this.fidoPresenter.getOpType().equals(Operation.Reg.name())) {
                try {
                    RegisterResponse doRegProcess = FingerSensorService.this.fidoSignUtil.doRegProcess(this.activity, authenticationResult.getCryptoObject().getSignature(), stringBuffer.toString(), FingerAuthenticatorInfo.authkeystoreName, FingerAuthenticatorInfo.authkeystoreType);
                    if (doRegProcess == null) {
                        doRegProcess = new RegisterResponse();
                        doRegProcess.statusCode = (short) 32;
                    }
                    this.fidoPresenter.handlerAuthResult(16, doRegProcess);
                } catch (Throwable th) {
                    new RegisterResponse().statusCode = (short) 32;
                    TrackerUtil.appendException(BasicInformation.SCENE_REG_SIG_EXCEPTION, th);
                }
            }
            if (this.fidoPresenter.getOpType().equals(Operation.Auth.name())) {
                SignResponse doSignProcess = FingerSensorService.this.fidoSignUtil.doSignProcess(this.activity, authenticationResult.getCryptoObject().getSignature(), stringBuffer.toString(), FingerAuthenticatorInfo.authkeystoreName);
                if (doSignProcess == null) {
                    FingerSensorService.this.fidoSignUtil.deregisterByName(this.activity, "004B#0001", FingerAuthenticatorInfo.authkeystoreName);
                    doSignProcess = new SignResponse();
                    doSignProcess.statusCode = (short) 32;
                }
                this.fidoPresenter.handlerAuthResult(16, doSignProcess);
            }
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
    @TargetApi(23)
    public void startAuthenticator(final Activity activity, final IAuthPresenter iAuthPresenter) {
        int i2 = 17;
        if (activity != null) {
            try {
                final FingerprintManager fingerprintManager = (FingerprintManager) activity.getSystemService("fingerprint");
                KeyguardManager keyguardManager = (KeyguardManager) activity.getSystemService("keyguard");
                if (fingerprintManager != null && keyguardManager != null) {
                    int isFinger = isFinger(activity, fingerprintManager, keyguardManager);
                    if (isFinger == 0) {
                        new Thread() { // from class: cn.com.union.fido.ui.finger.FingerSensorService.1
                            @Override // java.lang.Thread, java.lang.Runnable
                            public void run() {
                                IAuthPresenter iAuthPresenter2;
                                int i3;
                                try {
                                    if (Looper.myLooper() == null) {
                                        Looper.prepare();
                                    }
                                    FingerSensorService.this.fidoSignUtil = new FidoSignUtil();
                                    FingerprintManager.CryptoObject genCryptoObject = FingerSensorService.this.fidoSignUtil.genCryptoObject(activity, iAuthPresenter, FingerAuthenticatorInfo.authenticationAlg, FingerAuthenticatorInfo.authkeystoreName, FingerAuthenticatorInfo.authProviderName);
                                    MyAuthenticationCallback myAuthenticationCallback = new MyAuthenticationCallback(activity, iAuthPresenter);
                                    if (FingerSensorService.this.mCancellationSignal.isCanceled()) {
                                        return;
                                    }
                                    fingerprintManager.authenticate(genCryptoObject, FingerSensorService.this.mCancellationSignal, 0, myAuthenticationCallback, null);
                                    Thread.sleep(200L);
                                    iAuthPresenter.authViewDisplay();
                                } catch (KeyPermanentlyInvalidatedException unused) {
                                    iAuthPresenter2 = iAuthPresenter;
                                    i3 = 21;
                                    iAuthPresenter2.handlerAuthResult(i3, null);
                                } catch (Exception unused2) {
                                    iAuthPresenter2 = iAuthPresenter;
                                    i3 = 17;
                                    iAuthPresenter2.handlerAuthResult(i3, null);
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
