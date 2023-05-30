package com.jingdong.manto;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes15.dex */
public interface a extends IInterface {

    /* renamed from: com.jingdong.manto.a$a  reason: collision with other inner class name */
    /* loaded from: classes15.dex */
    public static abstract class AbstractBinderC0502a extends Binder implements a {

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: com.jingdong.manto.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes15.dex */
        public static class C0503a implements a {
            public static a b;
            private IBinder a;

            C0503a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // com.jingdong.manto.a
            public void a(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.jingdong.manto.IMantoBinderInterfaceNew");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.a.transact(3, obtain, obtain2, 0) || AbstractBinderC0502a.a() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle.readFromParcel(obtain2);
                        }
                    } else {
                        AbstractBinderC0502a.a().a(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.jingdong.manto.a
            public void a(IBinder iBinder, String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.jingdong.manto.IMantoBinderInterfaceNew");
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeString(str);
                    if (this.a.transact(1, obtain, obtain2, 0) || AbstractBinderC0502a.a() == null) {
                        obtain2.readException();
                    } else {
                        AbstractBinderC0502a.a().a(iBinder, str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }

            @Override // com.jingdong.manto.a
            public void b(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.jingdong.manto.IMantoBinderInterfaceNew");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.a.transact(2, obtain, obtain2, 0) || AbstractBinderC0502a.a() == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle.readFromParcel(obtain2);
                        }
                    } else {
                        AbstractBinderC0502a.a().b(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public AbstractBinderC0502a() {
            attachInterface(this, "com.jingdong.manto.IMantoBinderInterfaceNew");
        }

        public static a a() {
            return C0503a.b;
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.jingdong.manto.IMantoBinderInterfaceNew");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof a)) ? new C0503a(iBinder) : (a) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            Bundle bundle;
            if (i2 == 1) {
                parcel.enforceInterface("com.jingdong.manto.IMantoBinderInterfaceNew");
                a(parcel.readStrongBinder(), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            if (i2 == 2) {
                parcel.enforceInterface("com.jingdong.manto.IMantoBinderInterfaceNew");
                bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
                b(bundle);
                parcel2.writeNoException();
                if (bundle != null) {
                    parcel2.writeInt(1);
                    bundle.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            } else if (i2 != 3) {
                if (i2 != 1598968902) {
                    return super.onTransact(i2, parcel, parcel2, i3);
                }
                parcel2.writeString("com.jingdong.manto.IMantoBinderInterfaceNew");
                return true;
            } else {
                parcel.enforceInterface("com.jingdong.manto.IMantoBinderInterfaceNew");
                bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
                a(bundle);
                parcel2.writeNoException();
                if (bundle != null) {
                    parcel2.writeInt(1);
                    bundle.writeToParcel(parcel2, 1);
                } else {
                    parcel2.writeInt(0);
                }
                return true;
            }
        }
    }

    void a(Bundle bundle);

    void a(IBinder iBinder, String str);

    void b(Bundle bundle);
}
