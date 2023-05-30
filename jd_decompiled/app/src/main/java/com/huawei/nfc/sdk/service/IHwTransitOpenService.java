package com.huawei.nfc.sdk.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* loaded from: classes12.dex */
public interface IHwTransitOpenService extends IInterface {

    /* loaded from: classes12.dex */
    public static class Default implements IHwTransitOpenService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
        public int checkIssueCardConditions(String str) throws RemoteException {
            return 0;
        }

        @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
        public String checkIssueConditions(Map map) throws RemoteException {
            return null;
        }

        @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
        public String checkServiceStatus(Map map) throws RemoteException {
            return null;
        }

        @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
        public String deleteCard(Map map) throws RemoteException {
            return null;
        }

        @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
        public String issueCard(Map map) throws RemoteException {
            return null;
        }

        @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
        public String preIssueCard(Map map) throws RemoteException {
            return null;
        }

        @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
        public String queryCplc() throws RemoteException {
            return null;
        }

        @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
        public String queryTrafficCardInfo(String str, int i2) throws RemoteException {
            return null;
        }

        @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
        public String recharge(Map map) throws RemoteException {
            return null;
        }

        @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
        public String startSetDefault(Map map) throws RemoteException {
            return null;
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class Stub extends Binder implements IHwTransitOpenService {
        private static final String DESCRIPTOR = "com.huawei.nfc.sdk.service.IHwTransitOpenService";
        static final int TRANSACTION_checkIssueCardConditions = 1;
        static final int TRANSACTION_checkIssueConditions = 10;
        static final int TRANSACTION_checkServiceStatus = 8;
        static final int TRANSACTION_deleteCard = 7;
        static final int TRANSACTION_issueCard = 4;
        static final int TRANSACTION_preIssueCard = 3;
        static final int TRANSACTION_queryCplc = 2;
        static final int TRANSACTION_queryTrafficCardInfo = 6;
        static final int TRANSACTION_recharge = 5;
        static final int TRANSACTION_startSetDefault = 9;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes12.dex */
        public static class Proxy implements IHwTransitOpenService {
            public static IHwTransitOpenService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
            public int checkIssueCardConditions(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkIssueCardConditions(str);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
            public String checkIssueConditions(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkIssueConditions(map);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
            public String checkServiceStatus(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().checkServiceStatus(map);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
            public String deleteCard(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteCard(map);
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

            @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
            public String issueCard(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().issueCard(map);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
            public String preIssueCard(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().preIssueCard(map);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
            public String queryCplc() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryCplc();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
            public String queryTrafficCardInfo(String str, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryTrafficCardInfo(str, i2);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
            public String recharge(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().recharge(map);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.huawei.nfc.sdk.service.IHwTransitOpenService
            public String startSetDefault(Map map) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeMap(map);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startSetDefault(map);
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IHwTransitOpenService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IHwTransitOpenService)) {
                return (IHwTransitOpenService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        public static IHwTransitOpenService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IHwTransitOpenService iHwTransitOpenService) {
            if (Proxy.sDefaultImpl != null || iHwTransitOpenService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iHwTransitOpenService;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            if (i2 != 1598968902) {
                switch (i2) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        int checkIssueCardConditions = checkIssueCardConditions(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(checkIssueCardConditions);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        String queryCplc = queryCplc();
                        parcel2.writeNoException();
                        parcel2.writeString(queryCplc);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        String preIssueCard = preIssueCard(parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeString(preIssueCard);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        String issueCard = issueCard(parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeString(issueCard);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        String recharge = recharge(parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeString(recharge);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        String queryTrafficCardInfo = queryTrafficCardInfo(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeString(queryTrafficCardInfo);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        String deleteCard = deleteCard(parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeString(deleteCard);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        String checkServiceStatus = checkServiceStatus(parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeString(checkServiceStatus);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        String startSetDefault = startSetDefault(parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeString(startSetDefault);
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        String checkIssueConditions = checkIssueConditions(parcel.readHashMap(getClass().getClassLoader()));
                        parcel2.writeNoException();
                        parcel2.writeString(checkIssueConditions);
                        return true;
                    default:
                        return super.onTransact(i2, parcel, parcel2, i3);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    int checkIssueCardConditions(String str) throws RemoteException;

    String checkIssueConditions(Map map) throws RemoteException;

    String checkServiceStatus(Map map) throws RemoteException;

    String deleteCard(Map map) throws RemoteException;

    String issueCard(Map map) throws RemoteException;

    String preIssueCard(Map map) throws RemoteException;

    String queryCplc() throws RemoteException;

    String queryTrafficCardInfo(String str, int i2) throws RemoteException;

    String recharge(Map map) throws RemoteException;

    String startSetDefault(Map map) throws RemoteException;
}
