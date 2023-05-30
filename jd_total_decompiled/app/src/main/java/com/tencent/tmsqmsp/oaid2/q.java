package com.tencent.tmsqmsp.oaid2;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes9.dex */
public interface q extends IInterface {

    /* loaded from: classes9.dex */
    public static abstract class a extends Binder implements q {

        /* renamed from: com.tencent.tmsqmsp.oaid2.q$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public static class C0820a implements q {
            public IBinder a;

            public C0820a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // com.tencent.tmsqmsp.oaid2.q
            public String a() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    try {
                        obtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                        this.a.transact(3, obtain, obtain2, 0);
                        obtain2.readException();
                        return obtain2.readString();
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        obtain2.recycle();
                        obtain.recycle();
                        return "";
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

            @Override // com.tencent.tmsqmsp.oaid2.q
            public boolean c() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                boolean z = false;
                try {
                    try {
                        obtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                        this.a.transact(2, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            z = true;
                        }
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.tencent.tmsqmsp.oaid2.q
            public String d() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    try {
                        obtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                        this.a.transact(5, obtain, obtain2, 0);
                        obtain2.readException();
                        return obtain2.readString();
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        obtain2.recycle();
                        obtain.recycle();
                        return "";
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.tencent.tmsqmsp.oaid2.q
            public void f() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    try {
                        obtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                        this.a.transact(6, obtain, obtain2, 0);
                        obtain2.readException();
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            /* JADX WARN: Code restructure failed: missing block: B:4:0x001b, code lost:
                if (r1.readInt() != 0) goto L11;
             */
            @Override // com.tencent.tmsqmsp.oaid2.q
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public boolean g() {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                boolean z = true;
                try {
                    try {
                        obtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                        this.a.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                        z = false;
                        return z;
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static q a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.bun.lib.MsaIdInterface");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof q)) ? new C0820a(iBinder) : (q) queryLocalInterface;
        }
    }

    String a();

    boolean c();

    String d();

    void f();

    boolean g();
}
