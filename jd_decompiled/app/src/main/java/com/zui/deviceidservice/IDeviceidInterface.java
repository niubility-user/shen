package com.zui.deviceidservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes11.dex */
public interface IDeviceidInterface extends IInterface {

    /* loaded from: classes11.dex */
    public static class Default implements IDeviceidInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.zui.deviceidservice.IDeviceidInterface
        public String createAAIDForPackageName(String str) {
            return null;
        }

        @Override // com.zui.deviceidservice.IDeviceidInterface
        public String getAAID(String str) {
            return null;
        }

        @Override // com.zui.deviceidservice.IDeviceidInterface
        public String getOAID() {
            return null;
        }

        @Override // com.zui.deviceidservice.IDeviceidInterface
        public String getUDID() {
            return null;
        }

        @Override // com.zui.deviceidservice.IDeviceidInterface
        public String getVAID(String str) {
            return null;
        }

        @Override // com.zui.deviceidservice.IDeviceidInterface
        public boolean isSupport() {
            return false;
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class Stub extends Binder implements IDeviceidInterface {
        private static final String DESCRIPTOR = "com.zui.deviceidservice.IDeviceidInterface";
        static final int TRANSACTION_createAAIDForPackageName = 6;
        static final int TRANSACTION_getAAID = 5;
        static final int TRANSACTION_getOAID = 1;
        static final int TRANSACTION_getUDID = 2;
        static final int TRANSACTION_getVAID = 4;
        static final int TRANSACTION_isSupport = 3;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes11.dex */
        public static class Proxy implements IDeviceidInterface {
            public static IDeviceidInterface sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.zui.deviceidservice.IDeviceidInterface
            public String createAAIDForPackageName(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().createAAIDForPackageName(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zui.deviceidservice.IDeviceidInterface
            public String getAAID(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getAAID(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.zui.deviceidservice.IDeviceidInterface
            public String getOAID() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getOAID();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zui.deviceidservice.IDeviceidInterface
            public String getUDID() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getUDID();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zui.deviceidservice.IDeviceidInterface
            public String getVAID(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getVAID(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.zui.deviceidservice.IDeviceidInterface
            public boolean isSupport() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return Stub.getDefaultImpl().isSupport();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDeviceidInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IDeviceidInterface)) ? new Proxy(iBinder) : (IDeviceidInterface) queryLocalInterface;
        }

        public static IDeviceidInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IDeviceidInterface iDeviceidInterface) {
            if (Proxy.sDefaultImpl == null) {
                if (iDeviceidInterface != null) {
                    Proxy.sDefaultImpl = iDeviceidInterface;
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
            String oaid;
            if (i2 == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i2) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    oaid = getOAID();
                    break;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    oaid = getUDID();
                    break;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isSupport = isSupport();
                    parcel2.writeNoException();
                    parcel2.writeInt(isSupport ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    oaid = getVAID(parcel.readString());
                    break;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    oaid = getAAID(parcel.readString());
                    break;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    oaid = createAAIDForPackageName(parcel.readString());
                    break;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
            parcel2.writeNoException();
            parcel2.writeString(oaid);
            return true;
        }
    }

    String createAAIDForPackageName(String str);

    String getAAID(String str);

    String getOAID();

    String getUDID();

    String getVAID(String str);

    boolean isSupport();
}
