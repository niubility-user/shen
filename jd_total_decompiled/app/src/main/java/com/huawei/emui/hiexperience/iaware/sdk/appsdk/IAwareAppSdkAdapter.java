package com.huawei.emui.hiexperience.iaware.sdk.appsdk;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteException;
import android.rms.iaware.IAwareSdkCore;
import com.huawei.emui.hiexperience.iaware.sdk.appsdk.IAwareAppSdk;

/* loaded from: classes12.dex */
public class IAwareAppSdkAdapter {
    private static final int APP_SDK_DATA_EVENT_ID = 3009;
    private static final int APP_SDK_VIP_EVENT_ID = 3010;
    private static int INTERFACE_ID_REGISTER_APP_CALLBACK = 9;
    private static int INTERFACE_ID_REPORT_DATA = 1;
    private static final String TAG = "IAwareAppSdkAdapter";
    private static final int mDataFromSDK = 1;
    private IAwareAppSdk.AppCallBack mAppCbk = null;
    private SDKCallback mSdkCbk = null;
    private String mPackageName = "";
    private int myPid = 0;
    private boolean isRegistedSuccess = false;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class SDKCallback extends Binder implements IInterface {
        private static final String SDK_CALLBACK_DESCRIPTOR = "com.huawei.iaware.sdk.ISDKCallbak";
        private static final int TRANSACTION_updatePhoneInfo = 1;

        public SDKCallback() {
            attachInterface(this, SDK_CALLBACK_DESCRIPTOR);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            if (i2 < 1 || i2 > 16777215) {
                return super.onTransact(i2, parcel, parcel2, i3);
            }
            if (i2 != 1) {
                return false;
            }
            try {
                parcel.enforceInterface(SDK_CALLBACK_DESCRIPTOR);
                String readString = parcel.readString();
                String str = "info: " + readString + " isRegistedSuccess: " + IAwareAppSdkAdapter.this.isRegistedSuccess;
                if (IAwareAppSdkAdapter.this.mAppCbk != null && IAwareAppSdkAdapter.this.isRegistedSuccess) {
                    IAwareAppSdkAdapter.this.mAppCbk.getPhoneInfo(readString);
                }
                return true;
            } catch (SecurityException unused) {
                return false;
            }
        }
    }

    private boolean registerSdkCallback(String str, SDKCallback sDKCallback) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        obtain.writeString(str);
        obtain.writeStrongBinder(sDKCallback);
        IAwareSdkCore.handleEvent(INTERFACE_ID_REGISTER_APP_CALLBACK, obtain, obtain2);
        int readInt = obtain2.readInt();
        StringBuilder sb = new StringBuilder();
        sb.append("registerSdkCallback ret: ");
        sb.append(readInt > 0);
        sb.toString();
        obtain2.recycle();
        obtain.recycle();
        return readInt > 0;
    }

    public boolean registerAppCallback(String str, IAwareAppSdk.AppCallBack appCallBack) {
        this.mAppCbk = appCallBack;
        this.mPackageName = str;
        this.myPid = Process.myPid();
        if (this.mAppCbk != null && this.mSdkCbk == null) {
            SDKCallback sDKCallback = new SDKCallback();
            this.mSdkCbk = sDKCallback;
            this.isRegistedSuccess = registerSdkCallback(str, sDKCallback);
        }
        return this.isRegistedSuccess;
    }

    public void reportScene(String str) {
        String str2 = "reportScene package:" + this.mPackageName;
        if (this.isRegistedSuccess) {
            Parcel obtain = Parcel.obtain();
            String str3 = String.valueOf(1) + "&&" + this.myPid + "&&" + this.mPackageName + "&&" + str;
            obtain.writeInt(3009);
            obtain.writeLong(0L);
            obtain.writeString(str3);
            IAwareSdkCore.handleEvent(INTERFACE_ID_REPORT_DATA, obtain, null, 3009);
            obtain.recycle();
        }
    }

    public void reportVip(String str) {
        String str2 = "reportVip package:" + this.mPackageName;
        if (this.isRegistedSuccess) {
            Parcel obtain = Parcel.obtain();
            String str3 = String.valueOf(1) + "&&" + this.myPid + "&&" + this.mPackageName + "&&" + str;
            obtain.writeInt(3010);
            obtain.writeLong(0L);
            obtain.writeString(str3);
            IAwareSdkCore.handleEvent(INTERFACE_ID_REPORT_DATA, obtain, null, 3010);
            obtain.recycle();
        }
    }
}
