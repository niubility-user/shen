package com.coolpad.deviceidsupport;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public interface IDeviceIdManager extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IDeviceIdManager {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.coolpad.deviceidsupport.IDeviceIdManager
        public String getAAID(String str) {
            return null;
        }

        @Override // com.coolpad.deviceidsupport.IDeviceIdManager
        public String getCoolOsVersion() {
            return null;
        }

        @Override // com.coolpad.deviceidsupport.IDeviceIdManager
        public String getIMEI(String str) {
            return null;
        }

        @Override // com.coolpad.deviceidsupport.IDeviceIdManager
        public String getOAID(String str) {
            return null;
        }

        @Override // com.coolpad.deviceidsupport.IDeviceIdManager
        public String getUDID(String str) {
            return null;
        }

        @Override // com.coolpad.deviceidsupport.IDeviceIdManager
        public String getVAID(String str) {
            return null;
        }

        @Override // com.coolpad.deviceidsupport.IDeviceIdManager
        public boolean isCoolOs() {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDeviceIdManager {
        private static final String DESCRIPTOR = "com.coolpad.deviceidsupport.IDeviceIdManager";
        static final int TRANSACTION_getAAID = 4;
        static final int TRANSACTION_getCoolOsVersion = 7;
        static final int TRANSACTION_getIMEI = 5;
        static final int TRANSACTION_getOAID = 2;
        static final int TRANSACTION_getUDID = 1;
        static final int TRANSACTION_getVAID = 3;
        static final int TRANSACTION_isCoolOs = 6;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDeviceIdManager {
            public static IDeviceIdManager sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.coolpad.deviceidsupport.IDeviceIdManager
            public String getAAID(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getAAID(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.coolpad.deviceidsupport.IDeviceIdManager
            public String getCoolOsVersion() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getCoolOsVersion();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.coolpad.deviceidsupport.IDeviceIdManager
            public String getIMEI(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getIMEI(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.coolpad.deviceidsupport.IDeviceIdManager
            public String getOAID(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getOAID(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.coolpad.deviceidsupport.IDeviceIdManager
            public String getUDID(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getUDID(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.coolpad.deviceidsupport.IDeviceIdManager
            public String getVAID(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getVAID(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.coolpad.deviceidsupport.IDeviceIdManager
            public boolean isCoolOs() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return Stub.getDefaultImpl().isCoolOs();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeviceIdManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IDeviceIdManager)) ? new Proxy(iBinder) : (IDeviceIdManager) queryLocalInterface;
        }

        public static IDeviceIdManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IDeviceIdManager iDeviceIdManager) {
            if (Proxy.sDefaultImpl == null) {
                if (iDeviceIdManager != null) {
                    Proxy.sDefaultImpl = iDeviceIdManager;
                    return true;
                }
                return false;
            }
            throw new IllegalStateException("setDefaultImpl() called twice");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            String udid;
            if (i2 == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i2) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    udid = getUDID(parcel.readString());
                    break;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    udid = getOAID(parcel.readString());
                    break;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    udid = getVAID(parcel.readString());
                    break;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    udid = getAAID(parcel.readString());
                    break;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    udid = getIMEI(parcel.readString());
                    break;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isCoolOs = isCoolOs();
                    parcel2.writeNoException();
                    parcel2.writeInt(isCoolOs ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    udid = getCoolOsVersion();
                    break;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel2.writeNoException();
            parcel2.writeString(udid);
            return true;
        }
    }

    String getAAID(String str);

    String getCoolOsVersion();

    String getIMEI(String str);

    String getOAID(String str);

    String getUDID(String str);

    String getVAID(String str);

    boolean isCoolOs();
}
