package g.e.b.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes12.dex */
public interface b extends IInterface {

    /* loaded from: classes12.dex */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g.e.b.a.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static class C0835a implements b {
            public static b b;
            private IBinder a;

            C0835a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.a;
            }

            @Override // g.e.b.a.b
            public final boolean caasa(int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.HwCaasServiceAidl");
                    obtain.writeInt(i2);
                    if (this.a.transact(1, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return a.e().caasa(i2);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.b
            public final boolean caasb(int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.HwCaasServiceAidl");
                    obtain.writeInt(i2);
                    if (this.a.transact(5, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return a.e().caasb(i2);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.b
            public final boolean n() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.HwCaasServiceAidl");
                    if (this.a.transact(7, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return a.e().n();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.b
            public final int o(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.HwCaasServiceAidl");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.a.transact(9, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt();
                    }
                    return a.e().o(bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.b
            public final int p(String str, String str2, int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.HwCaasServiceAidl");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i2);
                    if (this.a.transact(8, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt();
                    }
                    return a.e().p(str, str2, i2);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.b
            public final boolean q(g.e.b.a.a aVar) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.HwCaasServiceAidl");
                    obtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (this.a.transact(6, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return a.e().q(aVar);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.b
            public final boolean r(int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.HwCaasServiceAidl");
                    obtain.writeInt(i2);
                    if (this.a.transact(10, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return a.e().r(i2);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.b
            public final boolean s(int i2, int i3, int i4, int i5) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.HwCaasServiceAidl");
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    obtain.writeInt(i5);
                    if (this.a.transact(3, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return a.e().s(i2, i3, i4, i5);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.b
            public final boolean t(Bundle bundle) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.HwCaasServiceAidl");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.a.transact(11, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return a.e().t(bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.b
            public final boolean u(int i2) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.HwCaasServiceAidl");
                    obtain.writeInt(i2);
                    if (this.a.transact(12, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return a.e().u(i2);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // g.e.b.a.b
            public final boolean v(int i2, int i3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.huawei.caasservice.thirdparty.HwCaasServiceAidl");
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    if (this.a.transact(2, obtain, obtain2, 0) || a.e() == null) {
                        obtain2.readException();
                        return obtain2.readInt() != 0;
                    }
                    return a.e().v(i2, i3);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static b d(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.huawei.caasservice.thirdparty.HwCaasServiceAidl");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new C0835a(iBinder) : (b) queryLocalInterface;
        }

        public static b e() {
            return C0835a.b;
        }
    }

    boolean caasa(int i2);

    boolean caasb(int i2);

    boolean n();

    int o(Bundle bundle);

    int p(String str, String str2, int i2);

    boolean q(g.e.b.a.a aVar);

    boolean r(int i2);

    boolean s(int i2, int i3, int i4, int i5);

    boolean t(Bundle bundle);

    boolean u(int i2);

    boolean v(int i2, int i3);
}
