package g.e.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes12.dex */
public interface a extends IInterface {

    /* renamed from: g.e.b.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public static abstract class AbstractBinderC0833a extends Binder implements a {

        /* renamed from: g.e.b.a.a$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        private static class C0834a implements a {
            public static a a;
        }

        public AbstractBinderC0833a() {
            attachInterface(this, "com.huawei.caasservice.thirdparty.HwCaasCallback");
        }

        public static a caasa() {
            return C0834a.a;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            if (i2 == 1) {
                parcel.enforceInterface("com.huawei.caasservice.thirdparty.HwCaasCallback");
                caasa(parcel.readInt());
                return true;
            } else if (i2 == 2) {
                parcel.enforceInterface("com.huawei.caasservice.thirdparty.HwCaasCallback");
                caasb(parcel.readInt());
                return true;
            } else if (i2 != 1598968902) {
                return super.onTransact(i2, parcel, parcel2, i3);
            } else {
                parcel2.writeString("com.huawei.caasservice.thirdparty.HwCaasCallback");
                return true;
            }
        }
    }

    void caasa(int i2);

    void caasb(int i2);
}
