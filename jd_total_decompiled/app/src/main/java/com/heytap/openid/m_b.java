package com.heytap.openid;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes12.dex */
public interface m_b extends IInterface {

    /* loaded from: classes12.dex */
    public static abstract class m_a extends Binder implements m_b {

        /* renamed from: com.heytap.openid.m_b$m_a$m_a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static class C0057m_a implements m_b {
            public IBinder m_a;

            public C0057m_a(IBinder iBinder) {
                this.m_a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.m_a;
            }

            @Override // com.heytap.openid.m_b
            public String m_b(String str, String str2, String str3) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oplus.stdid.IStdID");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    this.m_a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static m_b m_a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.oplus.stdid.IStdID");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof m_b)) ? new C0057m_a(iBinder) : (m_b) queryLocalInterface;
        }
    }

    String m_b(String str, String str2, String str3);
}
