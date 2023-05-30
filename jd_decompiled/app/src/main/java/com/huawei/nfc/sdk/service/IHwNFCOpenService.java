package com.huawei.nfc.sdk.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes12.dex */
public interface IHwNFCOpenService extends IInterface {

    /* loaded from: classes12.dex */
    public static class Default implements IHwNFCOpenService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.nfc.sdk.service.IHwNFCOpenService
        public int createSSD(String str, String str2, String str3, String str4) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.nfc.sdk.service.IHwNFCOpenService
        public int deleteSSD(String str, String str2, String str3, String str4) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.nfc.sdk.service.IHwNFCOpenService
        public int eSEInfoSync(String str, String str2, String str3) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.nfc.sdk.service.IHwNFCOpenService
        public String getCplc() throws RemoteException {
            return null;
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements IHwNFCOpenService {
        private static final String DESCRIPTOR = "com.huawei.nfc.sdk.service.IHwNFCOpenService";
        static final int TRANSACTION_createSSD = 1;
        static final int TRANSACTION_deleteSSD = 2;
        static final int TRANSACTION_eSEInfoSync = 4;
        static final int TRANSACTION_getCplc = 3;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class Proxy implements IHwNFCOpenService {
            public static IHwNFCOpenService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.nfc.sdk.service.IHwNFCOpenService
            public int createSSD(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createSSD(str, str2, str3, str4);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.IHwNFCOpenService
            public int deleteSSD(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    obtain.writeString(str4);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteSSD(str, str2, str3, str4);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.IHwNFCOpenService
            public int eSEInfoSync(String str, String str2, String str3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().eSEInfoSync(str, str2, str3);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.IHwNFCOpenService
            public String getCplc() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCplc();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHwNFCOpenService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHwNFCOpenService)) {
                return (IHwNFCOpenService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IHwNFCOpenService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IHwNFCOpenService iHwNFCOpenService) {
            if (Proxy.sDefaultImpl != null || iHwNFCOpenService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iHwNFCOpenService;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            if (i2 == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else if (i2 == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                int createSSD = createSSD(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(createSSD);
                return true;
            } else if (i2 == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                int deleteSSD = deleteSSD(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(deleteSSD);
                return true;
            } else if (i2 == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                String cplc = getCplc();
                parcel2.writeNoException();
                parcel2.writeString(cplc);
                return true;
            } else if (i2 != 4) {
                return super.onTransact(i2, parcel, parcel2, i3);
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                int eSEInfoSync = eSEInfoSync(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeInt(eSEInfoSync);
                return true;
            }
        }
    }

    int createSSD(String str, String str2, String str3, String str4) throws RemoteException;

    int deleteSSD(String str, String str2, String str3, String str4) throws RemoteException;

    int eSEInfoSync(String str, String str2, String str3) throws RemoteException;

    String getCplc() throws RemoteException;
}
