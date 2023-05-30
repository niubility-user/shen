package com.jdcn.biz.client;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.aips.common.utils.ThreadUtils;
import com.jd.aips.verify.BaseEngineLauncher;
import com.jd.aips.verify.VerifyCallback;
import com.jd.aips.verify.bankcard.SdkEngineLauncher;
import com.jd.aips.verify.tracker.TrackerCallback;

/* loaded from: classes18.dex */
public class BankCardManager {
    public static final String BANK_CARD_VERIFY_ENGINE = "com.jd.aips.verify.bankcard.BankCardVerifyEngine";
    private static volatile BankCardManager instance;
    private static long lastClickTime;
    private volatile Context applicationContext;
    private final Handler mainHandler;
    private final SdkEngineLauncher sdkEngineLauncher;
    private volatile VerifyCallback verifyCallback;

    private BankCardManager() {
        Handler handler = new Handler(Looper.getMainLooper());
        this.mainHandler = handler;
        this.sdkEngineLauncher = new SdkEngineLauncher(handler, new BaseEngineLauncher.LauncherCallback() { // from class: com.jdcn.biz.client.BankCardManager.1
            @Override // com.jd.aips.verify.BaseEngineLauncher.LauncherCallback
            public void onFailure() {
                BankCardManager.this.doCallback(1, "launch sdk failed");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BankCardResult buildResult(Bundle bundle) {
        BankCardResult bankCardResult = new BankCardResult();
        bankCardResult.setManualModified(false);
        bankCardResult.setConfigType(0);
        if (bundle != null) {
            BankCardInfo bankCardInfo = new BankCardInfo();
            bankCardInfo.setCardType(bundle.getString("cardType"));
            bankCardInfo.setCardNumber(bundle.getString("cardNumber"));
            bankCardInfo.setValidDate(bundle.getString(BankCardConstants.KEY_VALID_DATE));
            bankCardInfo.setIssuer(bundle.getString(BankCardConstants.KEY_ISSUER));
            bankCardInfo.setOwner(bundle.getString(BankCardConstants.KEY_OWNER));
            bankCardResult.setBankCardInfo(bankCardInfo);
        }
        return bankCardResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void doCallback(int i2, String str) {
        doCallback(i2, str, null);
    }

    @NonNull
    public static BankCardManager getInstance() {
        if (instance == null) {
            synchronized (BankCardManager.class) {
                if (instance == null) {
                    instance = new BankCardManager();
                }
            }
        }
        return instance;
    }

    public static synchronized boolean startBankCardModeDetect(@NonNull Activity activity, @NonNull Bundle bundle, @NonNull final BankCardScanListener bankCardScanListener) {
        synchronized (BankCardManager.class) {
            if (System.currentTimeMillis() - lastClickTime < 1000) {
                return true;
            }
            if (activity != null) {
                if (bankCardScanListener != null) {
                    lastClickTime = System.currentTimeMillis();
                    if (bundle != null) {
                        if (bundle.containsKey("appAuthorityKey") && bundle.containsKey("appName") && bundle.containsKey("businessId") && bundle.containsKey("token")) {
                            bundle.putString("verifyToken", bundle.getString("token"));
                            getInstance().launch(activity, bundle, new VerifyCallback() { // from class: com.jdcn.biz.client.BankCardManager.2
                                @Override // com.jd.aips.verify.VerifyCallback
                                public void onResult(int i2, String str, String str2, Bundle bundle2) {
                                    if (i2 == 0) {
                                        BankCardScanListener.this.onSuccess(BankCardManager.buildResult(bundle2));
                                    } else {
                                        BankCardScanListener.this.onFail(i2, str);
                                    }
                                }
                            });
                            return true;
                        }
                        bankCardScanListener.onFail(1003, "");
                        return false;
                    }
                    bankCardScanListener.onFail(1003, "");
                    return false;
                }
                throw new IllegalArgumentException("\u53c2\u6570\u4e0d\u5408\u6cd5\uff1abankCardScanListener \u4e3a\u7a7a\uff01");
            }
            throw new IllegalArgumentException("\u53c2\u6570\u4e0d\u5408\u6cd5\uff1acontext \u4e3a\u7a7a\uff01");
        }
    }

    public synchronized void launch(@NonNull Context context, @NonNull Bundle bundle, @NonNull VerifyCallback verifyCallback) {
        launch(context, bundle, verifyCallback, null);
    }

    private synchronized void doCallback(final int i2, final String str, final Bundle bundle) {
        if (this.verifyCallback != null) {
            try {
                if (ThreadUtils.isMainThread()) {
                    this.verifyCallback.onResult(i2, str, null, bundle);
                } else {
                    final VerifyCallback verifyCallback = this.verifyCallback;
                    this.mainHandler.post(new Runnable() { // from class: com.jdcn.biz.client.BankCardManager.3
                        @Override // java.lang.Runnable
                        public void run() {
                            verifyCallback.onResult(i2, str, null, bundle);
                        }
                    });
                }
            } catch (Throwable unused) {
            }
            this.verifyCallback = null;
        }
    }

    public synchronized void launch(@NonNull Context context, @NonNull Bundle bundle, @NonNull VerifyCallback verifyCallback, @Nullable TrackerCallback trackerCallback) {
        if (context == null) {
            throw new IllegalArgumentException("\u53c2\u6570\u4e0d\u5408\u6cd5\uff1acontext \u4e3a\u7a7a\uff01");
        }
        if (verifyCallback != null) {
            this.verifyCallback = verifyCallback;
            if (bundle == null) {
                doCallback(5, "\u53c2\u6570\u4e0d\u5408\u6cd5\uff1aparams \u4e3a\u7a7a\uff01");
                return;
            }
            this.applicationContext = context.getApplicationContext();
            try {
                this.sdkEngineLauncher.toBankCardVerify(this.applicationContext, bundle, verifyCallback, trackerCallback);
            } catch (Exception unused) {
                doCallback(1, "\u4eba\u8bc1\u6838\u9a8c Sdk \u542f\u52a8\u5931\u8d25");
            }
            return;
        }
        throw new IllegalArgumentException("\u53c2\u6570\u4e0d\u5408\u6cd5\uff1averifyCallback \u4e3a\u7a7a\uff01");
    }
}
