package com.asus.msa.SupplementaryDID;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public interface IDidAidlInterface extends IInterface {

    /* loaded from: classes.dex */
    public static class Default implements IDidAidlInterface {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
        public String getAAID() {
            return null;
        }

        @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
        public String getOAID() {
            return null;
        }

        @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
        public String getUDID() {
            return null;
        }

        @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
        public String getVAID() {
            return null;
        }

        @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
        public boolean isSupport() {
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IDidAidlInterface {
        private static final String DESCRIPTOR = "com.asus.msa.SupplementaryDID.IDidAidlInterface";
        static final int TRANSACTION_getAAID = 5;
        static final int TRANSACTION_getOAID = 3;
        static final int TRANSACTION_getUDID = 2;
        static final int TRANSACTION_getVAID = 4;
        static final int TRANSACTION_isSupport = 1;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes.dex */
        public static class Proxy implements IDidAidlInterface {
            public static IDidAidlInterface sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
            public String getAAID() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getAAID();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
            public String getOAID() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getOAID();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
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

            @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
            public String getVAID() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        return obtain2.readString();
                    }
                    return Stub.getDefaultImpl().getVAID();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
            public boolean isSupport() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
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

        public static IDidAidlInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IDidAidlInterface)) ? new Proxy(iBinder) : (IDidAidlInterface) queryLocalInterface;
        }

        public static IDidAidlInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        public static boolean setDefaultImpl(IDidAidlInterface iDidAidlInterface) {
            if (Proxy.sDefaultImpl == null) {
                if (iDidAidlInterface != null) {
                    Proxy.sDefaultImpl = iDidAidlInterface;
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
            if (i2 == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean isSupport = isSupport();
                parcel2.writeNoException();
                parcel2.writeInt(isSupport ? 1 : 0);
                return true;
            }
            if (i2 == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                udid = getUDID();
            } else if (i2 == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                udid = getOAID();
            } else if (i2 == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                udid = getVAID();
            } else if (i2 != 5) {
                if (i2 != 1598968902) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                parcel2.writeString(DESCRIPTOR);
                return true;
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                udid = getAAID();
            }
            parcel2.writeNoException();
            parcel2.writeString(udid);
            return true;
        }
    }

    String getAAID();

    String getOAID();

    String getUDID();

    String getVAID();

    boolean isSupport();
}
