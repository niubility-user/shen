package com.huawei.caas.caasservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.huawei.caas.caasservice.HwCaasUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.tencent.mapsdk.internal.l4;
import g.e.b.a.a;
import g.e.b.a.b;
import g.e.b.a.c;
import g.e.b.a.d;

/* loaded from: classes12.dex */
public class HwCaasServiceManager {
    private static HwCaasServiceManager caasd;
    HwCallAbilityCallBack caasa;
    HwMakeCallCallBack caasb;
    HwCallStateCallBack caasc;
    private Context caase;
    private boolean caasf;
    private volatile boolean caasg;
    private HwCaasHandler caash;
    private HwCaasHandler caasi;
    private int caasj;
    private volatile b caask;
    private volatile boolean caasl;
    private volatile boolean caasm;
    private HwCaasServiceCallBack caasn;
    private volatile c caasq;
    private HwCaasServiceCallBack caasr;
    private final a caaso = new a.AbstractBinderC0833a() { // from class: com.huawei.caas.caasservice.HwCaasServiceManager.1
        {
            HwCaasServiceManager.this = this;
        }

        @Override // g.e.b.a.a
        public final void caasa(int i2) {
            String str = "retCode: " + i2;
            if (i2 != 0) {
                if (i2 != 3000) {
                    HwCaasServiceManager hwCaasServiceManager = HwCaasServiceManager.this;
                    hwCaasServiceManager.caasb(i2, hwCaasServiceManager.caasj);
                    return;
                } else if (HwCaasServiceManager.this.caasj == 0) {
                    HwCaasServiceManager.caasf(HwCaasServiceManager.this);
                    return;
                } else {
                    return;
                }
            }
            HwCaasServiceManager.this.caasa(100);
            if (HwCaasServiceManager.this.caasn != null) {
                HwCaasServiceManager.this.caasn.initSuccess(HwCaasServiceManager.this.caash);
            }
            if (HwCaasServiceManager.this.caasl) {
                HwCaasServiceManager.caasd(HwCaasServiceManager.this);
                HwCaasServiceManager.this.caasa(0);
            }
        }

        @Override // g.e.b.a.a
        public final void caasb(int i2) {
            HwCaasUtils.CallState callState;
            String str = "callState: " + i2;
            if (HwCaasServiceManager.this.caasc != null) {
                HwCallStateCallBack hwCallStateCallBack = HwCaasServiceManager.this.caasc;
                switch (i2) {
                    case 102:
                        callState = HwCaasUtils.CallState.NO_CALL;
                        break;
                    case 103:
                        callState = HwCaasUtils.CallState.OUTGOING_CALL;
                        break;
                    case 104:
                        callState = HwCaasUtils.CallState.ACTIVE_CALL;
                        break;
                    default:
                        callState = HwCaasUtils.CallState.INVALID;
                        break;
                }
                hwCallStateCallBack.notifyCallState(callState);
            }
        }
    };
    private final ServiceConnection caasp = new ServiceConnection() { // from class: com.huawei.caas.caasservice.HwCaasServiceManager.2
        {
            HwCaasServiceManager.this = this;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                HwCaasServiceManager.this.caask = b.a.d(iBinder);
                HwCaasServiceManager hwCaasServiceManager = HwCaasServiceManager.this;
                HwCaasServiceManager.caasa(hwCaasServiceManager, hwCaasServiceManager.caasj);
                HwCaasServiceManager hwCaasServiceManager2 = HwCaasServiceManager.this;
                HwCaasServiceManager.caasb(hwCaasServiceManager2, hwCaasServiceManager2.caasj);
                if (HwCaasServiceManager.this.caask != null) {
                    HwCaasServiceManager.this.caasg = true;
                }
            } catch (SecurityException unused) {
                HwCaasServiceManager.this.caask = null;
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            HwCaasServiceManager hwCaasServiceManager = HwCaasServiceManager.this;
            hwCaasServiceManager.caasb(2003, hwCaasServiceManager.caasj);
            if (HwCaasServiceManager.this.caasg) {
                HwCaasServiceManager.this.caask = null;
                HwCaasServiceManager.this.caasg = false;
            }
        }
    };
    private final d caass = new d.a() { // from class: com.huawei.caas.caasservice.HwCaasServiceManager.3
        {
            HwCaasServiceManager.this = this;
        }

        @Override // g.e.b.a.d
        public final void caasa(int i2) {
            String str = "quick call retCode: " + i2;
            if (i2 != 0) {
                HwCaasServiceManager hwCaasServiceManager = HwCaasServiceManager.this;
                hwCaasServiceManager.caasb(i2, hwCaasServiceManager.caasj);
            } else if (HwCaasServiceManager.this.caasr != null) {
                HwCaasServiceManager.this.caasr.initSuccess(HwCaasServiceManager.this.caasi);
            }
        }

        @Override // g.e.b.a.d
        public final void caasa(String str, int i2) {
            String str2 = "callAbilityResult retCode: " + i2;
            if (HwCaasServiceManager.this.caasa != null) {
                HwCaasServiceManager.this.caasa.callAbilityResult(str, i2);
            }
        }

        @Override // g.e.b.a.d
        public final void caasb(String str, int i2) {
            String str2 = "makeCallResult retCode: " + i2;
            if (HwCaasServiceManager.this.caasb != null) {
                HwCaasServiceManager.this.caasb.makeCallResult(str, i2);
            }
        }
    };
    private final ServiceConnection caast = new ServiceConnection() { // from class: com.huawei.caas.caasservice.HwCaasServiceManager.4
        {
            HwCaasServiceManager.this = this;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            HwCaasServiceManager.this.caasq = c.a.d(iBinder);
            HwCaasServiceManager hwCaasServiceManager = HwCaasServiceManager.this;
            HwCaasServiceManager.caasa(hwCaasServiceManager, hwCaasServiceManager.caasj);
            HwCaasServiceManager hwCaasServiceManager2 = HwCaasServiceManager.this;
            HwCaasServiceManager.caasb(hwCaasServiceManager2, hwCaasServiceManager2.caasj);
            if (HwCaasServiceManager.this.caasq != null) {
                HwCaasServiceManager.this.caasg = true;
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            HwCaasServiceManager hwCaasServiceManager = HwCaasServiceManager.this;
            hwCaasServiceManager.caasb(2003, hwCaasServiceManager.caasj);
            if (HwCaasServiceManager.this.caasg) {
                HwCaasServiceManager.this.caasq = null;
                HwCaasServiceManager.this.caasg = false;
            }
        }
    };

    private HwCaasServiceManager() {
    }

    private Bundle caasa(CustomDisplayInfo customDisplayInfo) {
        Bundle bundle = new Bundle();
        if (customDisplayInfo == null) {
            return bundle;
        }
        bundle.putString("caller_app_name", customDisplayInfo.caasa);
        if (this.caasj == 1) {
            bundle.putInt("third_party_call_type", 0);
            bundle.putString("caller_display_info1", customDisplayInfo.caasd);
            bundle.putString("caller_display_info2", customDisplayInfo.caasc);
            bundle.putString("callee_display_info", customDisplayInfo.caasb);
        } else {
            bundle.putInt("third_party_call_type", 1);
        }
        return bundle;
    }

    private static String caasa(Context context) {
        Bundle bundle;
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo == null || (bundle = applicationInfo.metaData) == null || (obj = bundle.get("com.huawei.hms.client.appid")) == null) {
                return "";
            }
            String valueOf = String.valueOf(obj);
            return valueOf.startsWith("appid=") ? valueOf.substring(6) : valueOf;
        } catch (Throwable unused) {
            return "";
        }
    }

    static /* synthetic */ void caasa(HwCaasServiceManager hwCaasServiceManager, int i2) {
        String str = "registerCallback: " + i2;
        try {
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            return;
                        }
                    }
                }
                if (hwCaasServiceManager.caasq != null) {
                    hwCaasServiceManager.caasq.b(hwCaasServiceManager.caass);
                }
            }
            if (hwCaasServiceManager.caask != null) {
                hwCaasServiceManager.caask.q(hwCaasServiceManager.caaso);
            }
        } catch (RemoteException unused) {
        }
    }

    public void caasb(int i2, int i3) {
        if (i3 != 0) {
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 != 3) {
                        return;
                    }
                }
            }
            HwCaasServiceCallBack hwCaasServiceCallBack = this.caasr;
            if (hwCaasServiceCallBack != null) {
                hwCaasServiceCallBack.initFail(i2);
                return;
            }
            return;
        }
        HwCaasServiceCallBack hwCaasServiceCallBack2 = this.caasn;
        if (hwCaasServiceCallBack2 != null) {
            hwCaasServiceCallBack2.initFail(i2);
        }
    }

    static /* synthetic */ void caasb(HwCaasServiceManager hwCaasServiceManager, int i2) {
        Bundle bundle;
        if (hwCaasServiceManager.caase == null) {
            bundle = null;
        } else {
            bundle = new Bundle();
            bundle.putString("callAppId", caasa(hwCaasServiceManager.caase));
            bundle.putString(l4.f16791e, "1.0.1.400");
            bundle.putString("callAppName", hwCaasServiceManager.caase.getPackageName());
            bundle.putInt("handlerType", i2);
        }
        try {
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            return;
                        }
                    }
                }
                if (hwCaasServiceManager.caasq != null) {
                    hwCaasServiceManager.caasq.o(bundle);
                }
            }
            if (hwCaasServiceManager.caask != null) {
                hwCaasServiceManager.caask.o(bundle);
            }
        } catch (RemoteException unused) {
        }
    }

    private void caasc(int i2) {
        String str = "removeCallback: " + i2;
        try {
            if (i2 != 0) {
                if (i2 != 1) {
                    if (i2 != 2) {
                        if (i2 != 3) {
                            return;
                        }
                    }
                }
                if (this.caasq != null) {
                    this.caasq.n();
                }
            }
            if (this.caask != null) {
                this.caask.n();
            }
        } catch (RemoteException unused) {
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:5|(14:(1:(0))|33|34|10|(1:12)|13|14|(7:(1:(0))|24|25|(1:20)|21|22|23)|27|25|(0)|21|22|23)|35|34|10|(0)|13|14|(0)|27|25|(0)|21|22|23) */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0013, code lost:
        if (r8 != 3) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x005f, code lost:
        if (r8 != 3) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0081, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0082, code lost:
        caasb(2000, r8);
        r0 = "isBind: " + r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0090, code lost:
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0091, code lost:
        caasb(2000, r8);
        r8 = new java.lang.StringBuilder("isBind: ");
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x009a, code lost:
        caasb(2000, r8);
        r8 = new java.lang.StringBuilder("isBind: ");
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void caasd(int r8) {
        /*
            r7 = this;
            android.content.Context r0 = r7.caase
            if (r0 != 0) goto L5
            return
        L5:
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            r1 = 3
            r2 = 2
            r3 = 1
            if (r8 == 0) goto L19
            if (r8 == r3) goto L16
            if (r8 == r2) goto L19
            if (r8 == r1) goto L16
            goto L1e
        L16:
            java.lang.String r4 = "com.huawei.caasservice.HwHideNumberService"
            goto L1b
        L19:
            java.lang.String r4 = "com.huawei.caasservice.HwCaasService"
        L1b:
            r0.setAction(r4)
        L1e:
            java.lang.String r4 = "com.huawei.hwvoipservice"
            r0.setPackage(r4)
            boolean r4 = r7.caasm
            if (r4 != 0) goto L54
            android.content.Context r4 = r7.caase
            java.lang.String r4 = r4.getPackageName()
            java.lang.String r5 = "callAppName"
            r0.putExtra(r5, r4)
            android.content.Context r4 = r7.caase
            java.lang.String r4 = caasa(r4)
            java.lang.String r5 = "callAppId"
            r0.putExtra(r5, r4)
            int r4 = android.os.Binder.getCallingUid()
            java.lang.String r5 = "callAppUid"
            r0.putExtra(r5, r4)
            int r4 = r7.caasj
            java.lang.String r5 = "handlerType"
            r0.putExtra(r5, r4)
            java.lang.String r4 = "sdkVersion"
            java.lang.String r5 = "1.0.1.400"
            r0.putExtra(r4, r5)
        L54:
            r4 = 0
            r5 = 2000(0x7d0, float:2.803E-42)
            java.lang.String r6 = "isBind: "
            if (r8 == 0) goto L6b
            if (r8 == r3) goto L62
            if (r8 == r2) goto L6b
            if (r8 == r1) goto L62
            goto L70
        L62:
            android.content.Context r1 = r7.caase     // Catch: java.lang.Throwable -> L81 java.lang.IllegalStateException -> L91 java.lang.SecurityException -> L9a
            android.content.ServiceConnection r2 = r7.caast     // Catch: java.lang.Throwable -> L81 java.lang.IllegalStateException -> L91 java.lang.SecurityException -> L9a
        L66:
            boolean r4 = r1.bindService(r0, r2, r3)     // Catch: java.lang.Throwable -> L81 java.lang.IllegalStateException -> L91 java.lang.SecurityException -> L9a
            goto L70
        L6b:
            android.content.Context r1 = r7.caase     // Catch: java.lang.Throwable -> L81 java.lang.IllegalStateException -> L91 java.lang.SecurityException -> L9a
            android.content.ServiceConnection r2 = r7.caasp     // Catch: java.lang.Throwable -> L81 java.lang.IllegalStateException -> L91 java.lang.SecurityException -> L9a
            goto L66
        L70:
            if (r4 != 0) goto L75
            r7.caasb(r5, r8)
        L75:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r6)
        L7a:
            r8.append(r4)
            r8.toString()
            return
        L81:
            r0 = move-exception
            r7.caasb(r5, r8)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r6)
            r8.append(r4)
            r8.toString()
            throw r0
        L91:
            r7.caasb(r5, r8)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r6)
            goto L7a
        L9a:
            r7.caasb(r5, r8)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r6)
            goto L7a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.caas.caasservice.HwCaasServiceManager.caasd(int):void");
    }

    static /* synthetic */ boolean caasd(HwCaasServiceManager hwCaasServiceManager) {
        hwCaasServiceManager.caasl = false;
        return false;
    }

    private void caase(int i2) {
        try {
            Context context = this.caase;
            if (context != null) {
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                            }
                        }
                    }
                    context.unbindService(this.caast);
                }
                context.unbindService(this.caasp);
            }
        } catch (IllegalArgumentException | IllegalStateException | SecurityException unused) {
        }
    }

    static /* synthetic */ boolean caasf(HwCaasServiceManager hwCaasServiceManager) {
        hwCaasServiceManager.caasm = true;
        return true;
    }

    public static synchronized HwCaasServiceManager init() {
        HwCaasServiceManager hwCaasServiceManager;
        synchronized (HwCaasServiceManager.class) {
            if (caasd == null) {
                caasd = new HwCaasServiceManager();
            }
            hwCaasServiceManager = caasd;
        }
        return hwCaasServiceManager;
    }

    public final int caasa(String str, HwCaasUtils.CallAbilityType callAbilityType) {
        if (str != null && callAbilityType != null) {
            try {
                if (this.caasq != null) {
                    return this.caasq.caasa(str, callAbilityType.ordinal());
                }
            } catch (RemoteException unused) {
            }
            return -1;
        }
        HwCallAbilityCallBack hwCallAbilityCallBack = this.caasa;
        if (hwCallAbilityCallBack != null) {
            hwCallAbilityCallBack.callAbilityResult(str, 3003);
        }
        return -1;
    }

    public final int caasa(String str, HwCaasUtils.CallType callType) {
        if (str != null && callType != null) {
            try {
                if (this.caasq != null) {
                    return this.caasq.caasb(str, callType.ordinal());
                }
            } catch (RemoteException unused) {
            }
            return -1;
        }
        HwMakeCallCallBack hwMakeCallCallBack = this.caasb;
        if (hwMakeCallCallBack != null) {
            hwMakeCallCallBack.makeCallResult(str, 3005);
        }
        return -1;
    }

    public final int caasa(String str, HwCaasUtils.CallType callType, CustomDisplayInfo customDisplayInfo) {
        if (customDisplayInfo == null || str == null || callType == null) {
            HwMakeCallCallBack hwMakeCallCallBack = this.caasb;
            if (hwMakeCallCallBack != null) {
                hwMakeCallCallBack.makeCallResult(str, 3005);
            }
            return -1;
        }
        Bundle caasa = caasa(customDisplayInfo);
        try {
            if (this.caasq != null) {
                return this.caasq.a(str, callType.ordinal(), caasa);
            }
        } catch (RemoteException unused) {
        }
        return -1;
    }

    public final int caasa(String str, String str2, int i2) {
        String str3 = "initOrRelaeseVirtualCamera type:" + i2;
        if (this.caasm) {
            if (this.caask != null) {
                try {
                    return this.caask.p(str, str2, i2);
                } catch (RemoteException unused) {
                }
            }
            return 1;
        }
        if (i2 != 102) {
            caasc(0);
            caasa(101);
            caase(0);
            if (this.caasg) {
                this.caask = null;
                this.caasg = false;
            }
        } else if (this.caask != null) {
            caasa(0);
        } else {
            this.caasl = true;
            caasd(0);
        }
        return 0;
    }

    public final boolean caasa(int i2) {
        if (this.caask != null) {
            try {
                String str = "send event:" + i2;
                return this.caask.caasa(i2);
            } catch (RemoteException unused) {
            }
        }
        return false;
    }

    public final boolean caasa(int i2, int i3) {
        if (this.caask != null) {
            try {
                String str = "set contactview size: " + i2 + ":" + i3;
                return this.caask.v(i2, i3);
            } catch (RemoteException unused) {
                return false;
            }
        }
        return false;
    }

    public final boolean caasa(int i2, int i3, int i4, int i5) {
        if (this.caask != null) {
            try {
                boolean s = this.caask.s(i2, i3, i4, i5);
                String str = "set location tpye: " + i2 + " x: " + i4 + " y: " + i5 + " isSetSuccess: " + s;
                return s;
            } catch (RemoteException unused) {
                return false;
            }
        }
        return false;
    }

    public final boolean caasa(HwCaasUtils.ContactsType contactsType) {
        if (this.caask != null) {
            try {
                return this.caask.u(contactsType.ordinal());
            } catch (RemoteException unused) {
                return false;
            }
        }
        return false;
    }

    public final boolean caasa(HwCaasUtils.ContactsViewStyle contactsViewStyle) {
        if (this.caask != null) {
            try {
                String str = "setContactViewStyle: " + contactsViewStyle;
                return this.caask.r(contactsViewStyle.ordinal());
            } catch (RemoteException unused) {
                return false;
            }
        }
        return false;
    }

    public final boolean caasa(String str) {
        if (this.caask != null) {
            try {
                return this.caask.t(caasa(new CustomDisplayInfo(str)));
            } catch (RemoteException unused) {
                return false;
            }
        }
        return false;
    }

    public final boolean caasb(int i2) {
        if (this.caask != null) {
            try {
                String str = "setAppMode: " + i2;
                return this.caask.caasb(i2);
            } catch (RemoteException unused) {
                return false;
            }
        }
        return false;
    }

    public void initHandler(Context context, int i2, HwCaasServiceCallBack hwCaasServiceCallBack) {
        boolean z;
        int i3;
        if (context != null && hwCaasServiceCallBack != null) {
            String deviceBrand = BaseInfo.getDeviceBrand();
            String str = "isHwaweiDevice: brand " + deviceBrand;
            if (deviceBrand == null || (!("HUAWEI".equalsIgnoreCase(deviceBrand) || "HONOR".equalsIgnoreCase(deviceBrand)) || (i3 = Build.VERSION.SDK_INT) < 28)) {
                z = false;
            } else {
                String str2 = "VERSION: " + i3;
                z = true;
            }
            if (z) {
                if (this.caasf && this.caasj != i2) {
                    release();
                }
                this.caase = context.getApplicationContext();
                this.caasj = i2;
                this.caasf = true;
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                this.caasf = false;
                                this.caash = null;
                                this.caasi = null;
                                hwCaasServiceCallBack.initFail(2005);
                                return;
                            }
                        }
                    }
                    this.caasr = hwCaasServiceCallBack;
                    caasd(i2);
                    if (this.caasi == null) {
                        this.caasi = new caasb(caasd, i2);
                        return;
                    }
                    return;
                }
                this.caasn = hwCaasServiceCallBack;
                caasd(i2);
                this.caasl = false;
                if (this.caash == null) {
                    if (i2 == 0) {
                        this.caash = new caasa(caasd);
                        return;
                    } else {
                        this.caash = new caasc(caasd);
                        return;
                    }
                }
                return;
            }
        }
        if (hwCaasServiceCallBack != null) {
            hwCaasServiceCallBack.initFail(2000);
        }
    }

    public void release() {
        caasa(101);
        caasc(this.caasj);
        caase(this.caasj);
        if (this.caasg) {
            this.caask = null;
            this.caash = null;
            this.caasq = null;
            this.caasi = null;
            this.caasg = false;
        }
        HwCaasServiceCallBack hwCaasServiceCallBack = this.caasn;
        if (hwCaasServiceCallBack != null) {
            hwCaasServiceCallBack.releaseSuccess();
            this.caasn = null;
        }
        HwCaasServiceCallBack hwCaasServiceCallBack2 = this.caasr;
        if (hwCaasServiceCallBack2 != null) {
            hwCaasServiceCallBack2.releaseSuccess();
            this.caasr = null;
        }
        this.caasf = false;
    }
}
