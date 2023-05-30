package g.e.b.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes12.dex */
public interface c extends IInterface {

    /* loaded from: classes12.dex */
    public static abstract class a extends Binder implements c {

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g.e.b.a.c$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static class C0836a implements c {
            public static c b;
            private IBinder a;

            C0836a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // g.e.b.a.c
            public final int a(String str, int i2, Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.IHwCaasHideNumber");
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.a.transact(2, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt();
                    }
                    return a.e().a(str, i2, bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.a;
            }

            @Override // g.e.b.a.c
            public final boolean b(d dVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.IHwCaasHideNumber");
                    obtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (this.a.transact(3, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return a.e().b(dVar);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.c
            public final int caasa(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.IHwCaasHideNumber");
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    if (this.a.transact(1, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt();
                    }
                    return a.e().caasa(str, i2);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.c
            public final int caasb(String str, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.IHwCaasHideNumber");
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    if (this.a.transact(6, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt();
                    }
                    return a.e().caasb(str, i2);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.c
            public final boolean n() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.IHwCaasHideNumber");
                    if (this.a.transact(4, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return a.e().n();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.c
            public final int o(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.IHwCaasHideNumber");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.a.transact(5, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt();
                    }
                    return a.e().o(bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static c d(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.caasservice.thirdparty.IHwCaasHideNumber");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof c)) ? new C0836a(iBinder) : (c) queryLocalInterface;
        }

        public static c e() {
            return C0836a.b;
        }
    }

    int a(String str, int i2, Bundle bundle);

    boolean b(d dVar);

    int caasa(String str, int i2);

    int caasb(String str, int i2);

    boolean n();

    int o(Bundle bundle);
}
