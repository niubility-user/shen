package g.h.a;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes13.dex */
public interface a extends IInterface {

    /* renamed from: g.h.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static abstract class AbstractBinderC0840a extends Binder implements a {

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: g.h.a.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public static class C0841a implements a {
            public static a b;
            private IBinder a;

            C0841a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }

            @Override // g.h.a.a
            public void c(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.mcs.aidl.IMcsSdkService");
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.a.transact(1, obtain, obtain2, 0) && AbstractBinderC0840a.e() != null) {
                        AbstractBinderC0840a.e().c(bundle);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static a d(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.mcs.aidl.IMcsSdkService");
            if (queryLocalInterface != null && (queryLocalInterface instanceof a)) {
                return (a) queryLocalInterface;
            }
            return new C0841a(iBinder);
        }

        public static a e() {
            return C0841a.b;
        }
    }

    void c(Bundle bundle) throws RemoteException;
}
