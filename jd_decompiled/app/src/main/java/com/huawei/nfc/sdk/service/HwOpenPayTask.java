package com.huawei.nfc.sdk.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.huawei.nfc.sdk.service.ICUPOnlinePayCallBackService;
import com.huawei.nfc.sdk.service.ICUPOnlinePayService;
import com.jingdong.common.bing.utils.JDBingConst;
import com.unionpay.utils.j;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;

/* loaded from: classes12.dex */
public class HwOpenPayTask {
    private static final String BANK_OPEN_API_PAY_ACTION = "com.huawei.nfc.action.OPEN_AIDL_API_PAY";
    private static final String TAG = "HwOpenPayTask";
    private static final String WALLET_PACKAGE_NAME = "com.huawei.wallet";
    private boolean haveInitService;
    private WeakReference mContext;
    private ICUPOnlinePayService mOpenService;
    private IHwPayResultCallBack payResultCallBack;
    private IHwResultCallBack resultCallBack;
    private final byte[] lock = new byte[0];
    private ServiceConnection mNfcServiceConnection = new MyServiceConnection();
    private ICUPOnlinePayCallBackService hwPayCallBackService = new ICUPOnlinePayCallBackService.Stub() { // from class: com.huawei.nfc.sdk.service.HwOpenPayTask.1
        @Override // com.huawei.nfc.sdk.service.ICUPOnlinePayCallBackService
        public void onError(String str, String str2) {
            j.b(HwOpenPayTask.TAG, "getUnionOnlinePayStatus---onError--- errorCode is " + str + " and errorMsg is " + str2);
            if (HwOpenPayTask.this.payResultCallBack != null) {
                HwOpenPayTask.this.payResultCallBack.onError(str, str2);
                HwOpenPayTask.this.payResultCallBack = null;
            }
            if (HwOpenPayTask.this.haveInitService) {
                HwOpenPayTask.this.disConnect();
            }
        }

        @Override // com.huawei.nfc.sdk.service.ICUPOnlinePayCallBackService
        public void onResult(Bundle bundle) {
            j.b(HwOpenPayTask.TAG, "getUnionOnlinePayStatus---onResult---");
            if (HwOpenPayTask.this.payResultCallBack != null) {
                HwOpenPayTask.this.payResultCallBack.onResult(bundle);
                HwOpenPayTask.this.payResultCallBack = null;
            }
            if (HwOpenPayTask.this.haveInitService) {
                HwOpenPayTask.this.disConnect();
            }
        }
    };

    /* loaded from: classes12.dex */
    public interface IHwPayResultCallBack {
        void onError(String str, String str2);

        void onResult(Bundle bundle);
    }

    /* loaded from: classes12.dex */
    public interface IHwResultCallBack {
        void onResult(int i2, Bundle bundle);
    }

    /* loaded from: classes12.dex */
    class MyServiceConnection implements ServiceConnection {
        private MyServiceConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            j.b(HwOpenPayTask.TAG, "---onServiceConnected---begin");
            synchronized (HwOpenPayTask.this.lock) {
                HwOpenPayTask.this.mOpenService = ICUPOnlinePayService.Stub.asInterface(iBinder);
                j.b(HwOpenPayTask.TAG, "---onServiceConnected---");
                HwOpenPayTask.this.lock.notifyAll();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            j.b(HwOpenPayTask.TAG, "---onServiceDisconnected---begin");
            synchronized (HwOpenPayTask.this.lock) {
                j.b(HwOpenPayTask.TAG, "---onServiceDisconnected---");
                HwOpenPayTask.this.mOpenService = null;
                HwOpenPayTask.this.lock.notifyAll();
            }
        }
    }

    /* loaded from: classes12.dex */
    public interface SupportCapacityResult {
        public static final int CAPACITY_RESULT_NOT_SUPPORT = 0;
        public static final int CAPACITY_RESULT_SUPPORT = 1;
    }

    public HwOpenPayTask(Context context) {
        this.mContext = new WeakReference(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void disConnect() {
        if (this.haveInitService) {
            this.haveInitService = false;
            this.mOpenService = null;
            if (this.mContext == null || this.mNfcServiceConnection == null) {
                return;
            }
            j.b(TAG, "---unbindService---start");
            try {
                Context context = (Context) this.mContext.get();
                if (context != null) {
                    context.unbindService(this.mNfcServiceConnection);
                }
            } catch (Exception unused) {
            }
            j.b(TAG, "---unbindService---end");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void failResult() {
        j.b(TAG, "--failResult--:");
        IHwResultCallBack iHwResultCallBack = this.resultCallBack;
        if (iHwResultCallBack != null) {
            iHwResultCallBack.onResult(0, new Bundle());
        }
        IHwPayResultCallBack iHwPayResultCallBack = this.payResultCallBack;
        if (iHwPayResultCallBack != null) {
            iHwPayResultCallBack.onError(JDBingConst.UNLOGIN_ERROR_CODE, "WALLET VERSION LOWER");
        }
        disConnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initNfcService() {
        String str;
        String str2;
        Context context;
        synchronized (this.lock) {
            if (this.mOpenService == null) {
                Intent intent = new Intent(BANK_OPEN_API_PAY_ACTION);
                intent.setPackage(WALLET_PACKAGE_NAME);
                j.b(TAG, "---bindService---start");
                boolean z = false;
                WeakReference weakReference = this.mContext;
                if (weakReference != null && (context = (Context) weakReference.get()) != null) {
                    z = context.bindService(intent, this.mNfcServiceConnection, 1);
                }
                j.b(TAG, "---bindService---end:" + z);
                if (z) {
                    this.haveInitService = true;
                    if (this.mOpenService == null) {
                        try {
                            j.b(TAG, "--waiting--");
                            this.lock.wait();
                        } catch (Exception unused) {
                            j.c(TAG, "---InterruptedException--");
                        }
                    } else {
                        str = TAG;
                        str2 = "---initNfcService---isConnection mOpenService not null";
                    }
                }
                failResult();
            } else {
                str = TAG;
                str2 = "---initNfcService---mOpenService not null";
            }
            j.b(str, str2);
        }
    }

    public void getUnionOnlinePayStatus(final IHwPayResultCallBack iHwPayResultCallBack) {
        Executors.newCachedThreadPool().execute(new Runnable() { // from class: com.huawei.nfc.sdk.service.HwOpenPayTask.3
            @Override // java.lang.Runnable
            public void run() {
                synchronized (HwOpenPayTask.this.lock) {
                    HwOpenPayTask.this.payResultCallBack = iHwPayResultCallBack;
                    HwOpenPayTask.this.initNfcService();
                    if (HwOpenPayTask.this.mOpenService != null) {
                        try {
                            j.b(HwOpenPayTask.TAG, "getUnionOnlinePayStatus");
                            HwOpenPayTask.this.mOpenService.getUnionOnlinePayStatus(HwOpenPayTask.this.hwPayCallBackService);
                        } catch (Exception unused) {
                            j.c(HwOpenPayTask.TAG, "getUnionOnlinePayStatus---RemoteException--");
                            HwOpenPayTask.this.failResult();
                        }
                    } else {
                        j.b(HwOpenPayTask.TAG, "mOpenService is null");
                    }
                }
            }
        });
    }

    public void supportCapacity(final String str, final IHwResultCallBack iHwResultCallBack) {
        Executors.newCachedThreadPool().execute(new Runnable() { // from class: com.huawei.nfc.sdk.service.HwOpenPayTask.2
            @Override // java.lang.Runnable
            public void run() {
                HwOpenPayTask hwOpenPayTask;
                synchronized (HwOpenPayTask.this.lock) {
                    HwOpenPayTask.this.resultCallBack = iHwResultCallBack;
                    HwOpenPayTask.this.initNfcService();
                    if (HwOpenPayTask.this.mOpenService != null) {
                        try {
                            j.b(HwOpenPayTask.TAG, "supportCapacity capacity is " + str);
                            boolean supportCapacity = HwOpenPayTask.this.mOpenService.supportCapacity(str);
                            j.b(HwOpenPayTask.TAG, "supportCapacity result is " + supportCapacity);
                            IHwResultCallBack iHwResultCallBack2 = iHwResultCallBack;
                            if (iHwResultCallBack2 != null) {
                                iHwResultCallBack2.onResult(supportCapacity ? 1 : 0, new Bundle());
                            }
                            hwOpenPayTask = HwOpenPayTask.this;
                        } catch (Exception unused) {
                            j.c(HwOpenPayTask.TAG, "supportCapacity---RemoteException--");
                            iHwResultCallBack.onResult(0, new Bundle());
                            hwOpenPayTask = HwOpenPayTask.this;
                        }
                        hwOpenPayTask.disConnect();
                    } else {
                        j.b(HwOpenPayTask.TAG, "mOpenService is null");
                    }
                }
            }
        });
    }
}
