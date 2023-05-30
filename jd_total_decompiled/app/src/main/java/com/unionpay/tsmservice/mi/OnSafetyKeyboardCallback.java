package com.unionpay.tsmservice.mi;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes11.dex */
public interface OnSafetyKeyboardCallback extends IInterface {

    /* loaded from: classes11.dex */
    public static abstract class Stub extends Binder implements OnSafetyKeyboardCallback {

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes11.dex */
        public static class a implements OnSafetyKeyboardCallback {
            public static OnSafetyKeyboardCallback a;
            private IBinder b;

            a(IBinder iBinder) {
                this.b = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.b;
            }

            @Override // com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback
            public final void onConfirmClicked() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
                    if (this.b.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onConfirmClicked();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback
            public final void onEditorChanged(int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
                    obtain.writeInt(i2);
                    if (this.b.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onEditorChanged(i2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback
            public final void onHide() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
                    if (this.b.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onHide();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback
            public final void onOutsideTouch(float f2, float f3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
                    obtain.writeFloat(f2);
                    obtain.writeFloat(f3);
                    if (this.b.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onOutsideTouch(f2, f3);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback
            public final void onShow() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
                    if (this.b.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                    } else {
                        Stub.getDefaultImpl().onShow();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
        }

        public static OnSafetyKeyboardCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof OnSafetyKeyboardCallback)) ? new a(iBinder) : (OnSafetyKeyboardCallback) queryLocalInterface;
        }

        public static OnSafetyKeyboardCallback getDefaultImpl() {
            return a.a;
        }

        public static boolean setDefaultImpl(OnSafetyKeyboardCallback onSafetyKeyboardCallback) {
            if (a.a != null || onSafetyKeyboardCallback == null) {
                return false;
            }
            a.a = onSafetyKeyboardCallback;
            return true;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            if (i2 == 1) {
                parcel.enforceInterface("com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
                onShow();
            } else if (i2 == 2) {
                parcel.enforceInterface("com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
                onHide();
            } else if (i2 == 3) {
                parcel.enforceInterface("com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
                onEditorChanged(parcel.readInt());
            } else if (i2 == 4) {
                parcel.enforceInterface("com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
                onOutsideTouch(parcel.readFloat(), parcel.readFloat());
            } else if (i2 != 5) {
                if (i2 != 1598968902) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                parcel2.writeString("com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
                return true;
            } else {
                parcel.enforceInterface("com.unionpay.tsmservice.mi.OnSafetyKeyboardCallback");
                onConfirmClicked();
            }
            parcel2.writeNoException();
            return true;
        }
    }

    void onConfirmClicked() throws RemoteException;

    void onEditorChanged(int i2) throws RemoteException;

    void onHide() throws RemoteException;

    void onOutsideTouch(float f2, float f3) throws RemoteException;

    void onShow() throws RemoteException;
}
