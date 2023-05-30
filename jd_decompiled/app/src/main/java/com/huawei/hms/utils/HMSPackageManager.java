package com.huawei.hms.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidException;
import android.util.Pair;
import com.huawei.hms.common.PackageConstants;
import com.huawei.hms.framework.common.ContainerUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.PackageManagerHelper;
import com.jd.dynamic.DYConstants;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes12.dex */
public class HMSPackageManager {

    /* renamed from: n  reason: collision with root package name */
    private static HMSPackageManager f1513n;
    private static final Object o = new Object();
    private static final Object p = new Object();
    private static final Object q = new Object();
    private final Context a;
    private final PackageManagerHelper b;

    /* renamed from: c  reason: collision with root package name */
    private String f1514c;
    private String d;

    /* renamed from: e  reason: collision with root package name */
    private int f1515e;

    /* renamed from: f  reason: collision with root package name */
    private String f1516f;

    /* renamed from: g  reason: collision with root package name */
    private String f1517g;

    /* renamed from: h  reason: collision with root package name */
    private String f1518h;

    /* renamed from: i  reason: collision with root package name */
    private String f1519i;

    /* renamed from: j  reason: collision with root package name */
    private int f1520j;

    /* renamed from: k  reason: collision with root package name */
    private int f1521k;

    /* renamed from: l  reason: collision with root package name */
    private long f1522l;

    /* renamed from: m  reason: collision with root package name */
    private boolean f1523m;

    /* loaded from: classes12.dex */
    public static class PackagePriorityInfo implements Comparable<PackagePriorityInfo> {
        private String a;
        private String b;

        /* renamed from: c  reason: collision with root package name */
        private String f1524c;
        private String d;

        /* renamed from: e  reason: collision with root package name */
        private String f1525e;

        /* renamed from: f  reason: collision with root package name */
        private Long f1526f;

        public PackagePriorityInfo(String str, String str2, String str3, String str4, String str5, long j2) {
            this.a = str;
            this.b = str2;
            this.f1524c = str3;
            this.d = str4;
            this.f1525e = str5;
            this.f1526f = Long.valueOf(j2);
        }

        @Override // java.lang.Comparable
        public int compareTo(PackagePriorityInfo packagePriorityInfo) {
            if (TextUtils.equals(this.f1525e, packagePriorityInfo.f1525e)) {
                return this.f1526f.compareTo(packagePriorityInfo.f1526f);
            }
            return this.f1525e.compareTo(packagePriorityInfo.f1525e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HMSLog.i("HMSPackageManager", "enter asyncOnceCheckMDMState");
            try {
                List<ResolveInfo> queryIntentServices = HMSPackageManager.this.a.getPackageManager().queryIntentServices(new Intent("com.huawei.hms.core.aidlservice"), 128);
                if (queryIntentServices == null || queryIntentServices.size() == 0) {
                    return;
                }
                Iterator<ResolveInfo> it = queryIntentServices.iterator();
                while (it.hasNext()) {
                    if ("com.huawei.hwid".equals(it.next().serviceInfo.applicationInfo.packageName)) {
                        HMSPackageManager.this.c();
                    }
                }
                HMSLog.i("HMSPackageManager", "quit asyncOnceCheckMDMState");
            } catch (Exception e2) {
                HMSLog.e("HMSPackageManager", "asyncOnceCheckMDMState query hms action failed. " + e2.getMessage());
            }
        }
    }

    private HMSPackageManager(Context context) {
        this.a = context;
        this.b = new PackageManagerHelper(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0024 A[Catch: all -> 0x0077, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0018, B:12:0x0024, B:13:0x0042, B:15:0x0044, B:18:0x004b, B:19:0x0073), top: B:25:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0044 A[Catch: all -> 0x0077, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0018, B:12:0x0024, B:13:0x0042, B:15:0x0044, B:18:0x004b, B:19:0x0073), top: B:25:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int c() {
        /*
            r7 = this;
            java.lang.Object r0 = com.huawei.hms.utils.HMSPackageManager.q
            monitor-enter(r0)
            java.lang.String r1 = "HMSPackageManager"
            java.lang.String r2 = "enter checkHmsIsSpoof"
            com.huawei.hms.support.log.HMSLog.i(r1, r2)     // Catch: java.lang.Throwable -> L77
            com.huawei.hms.utils.PackageManagerHelper r1 = r7.b     // Catch: java.lang.Throwable -> L77
            java.lang.String r2 = "com.huawei.hwid"
            long r1 = r1.getPackageFirstInstallTime(r2)     // Catch: java.lang.Throwable -> L77
            int r3 = r7.f1521k     // Catch: java.lang.Throwable -> L77
            r4 = 3
            r5 = 1
            if (r3 == r4) goto L21
            long r3 = r7.f1522l     // Catch: java.lang.Throwable -> L77
            int r6 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r6 == 0) goto L1f
            goto L21
        L1f:
            r1 = 0
            goto L22
        L21:
            r1 = 1
        L22:
            if (r1 != 0) goto L44
            java.lang.String r1 = "HMSPackageManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L77
            r2.<init>()     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = "quit checkHmsIsSpoof cached state: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L77
            int r3 = r7.f1521k     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = a(r3)     // Catch: java.lang.Throwable -> L77
            r2.append(r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L77
            com.huawei.hms.support.log.HMSLog.i(r1, r2)     // Catch: java.lang.Throwable -> L77
            int r1 = r7.f1521k     // Catch: java.lang.Throwable -> L77
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
            return r1
        L44:
            boolean r1 = r7.b()     // Catch: java.lang.Throwable -> L77
            if (r1 == 0) goto L4b
            r5 = 2
        L4b:
            r7.f1521k = r5     // Catch: java.lang.Throwable -> L77
            com.huawei.hms.utils.PackageManagerHelper r1 = r7.b     // Catch: java.lang.Throwable -> L77
            java.lang.String r2 = "com.huawei.hwid"
            long r1 = r1.getPackageFirstInstallTime(r2)     // Catch: java.lang.Throwable -> L77
            r7.f1522l = r1     // Catch: java.lang.Throwable -> L77
            java.lang.String r1 = "HMSPackageManager"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L77
            r2.<init>()     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = "quit checkHmsIsSpoof state: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L77
            int r3 = r7.f1521k     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = a(r3)     // Catch: java.lang.Throwable -> L77
            r2.append(r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L77
            com.huawei.hms.support.log.HMSLog.i(r1, r2)     // Catch: java.lang.Throwable -> L77
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
            int r0 = r7.f1521k
            return r0
        L77:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L77
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.utils.HMSPackageManager.c():int");
    }

    private void d() {
        synchronized (p) {
            this.f1516f = null;
            this.f1517g = null;
            this.f1518h = null;
            this.f1519i = null;
            this.f1520j = 0;
        }
    }

    private void e() {
        synchronized (p) {
            this.f1514c = null;
            this.d = null;
            this.f1515e = 0;
        }
    }

    private Pair<String, String> f() {
        try {
            List<ResolveInfo> queryIntentServices = this.a.getPackageManager().queryIntentServices(new Intent("com.huawei.hms.core.aidlservice"), 128);
            if (queryIntentServices != null && queryIntentServices.size() != 0) {
                for (ResolveInfo resolveInfo : queryIntentServices) {
                    String str = resolveInfo.serviceInfo.applicationInfo.packageName;
                    String packageSignature = this.b.getPackageSignature(str);
                    if ("com.huawei.hwid".equals(str) && this.b.getPackageVersionCode(str) < 30000000) {
                        return new Pair<>(str, packageSignature);
                    }
                    Bundle bundle = resolveInfo.serviceInfo.metaData;
                    if (bundle == null) {
                        HMSLog.e("HMSPackageManager", "skip package " + str + " for metadata is null");
                    } else if (!bundle.containsKey("hms_app_signer")) {
                        HMSLog.e("HMSPackageManager", "skip package " + str + " for no signer");
                    } else if (bundle.containsKey("hms_app_cert_chain")) {
                        if (!a(str + ContainerUtils.FIELD_DELIMITER + packageSignature, bundle.getString("hms_app_signer"), bundle.getString("hms_app_cert_chain"))) {
                            HMSLog.e("HMSPackageManager", "checkSigner failed");
                        } else {
                            return new Pair<>(str, packageSignature);
                        }
                    } else {
                        HMSLog.e("HMSPackageManager", "skip package " + str + " for no cert chain");
                    }
                }
                return null;
            }
            HMSLog.e("HMSPackageManager", "query hms action, resolveInfoList is null or empty.");
            return null;
        } catch (Exception e2) {
            HMSLog.e("HMSPackageManager", "getHmsPackageName query hms action failed. " + e2.getMessage());
            return null;
        }
    }

    private Pair<String, String> g() {
        Pair<String, String> f2 = f();
        if (f2 != null) {
            HMSLog.i("HMSPackageManager", "aidlService pkgName: " + ((String) f2.first));
            this.f1518h = "com.huawei.hms.core.aidlservice";
            this.f1519i = null;
            return f2;
        }
        ArrayList<PackagePriorityInfo> h2 = h();
        if (h2 == null) {
            HMSLog.e("HMSPackageManager", "PackagePriorityInfo list is null");
            return null;
        }
        Iterator<PackagePriorityInfo> it = h2.iterator();
        while (it.hasNext()) {
            PackagePriorityInfo next = it.next();
            String str = next.a;
            String str2 = next.b;
            String str3 = next.f1524c;
            String str4 = next.d;
            String packageSignature = this.b.getPackageSignature(str);
            if (a(str + ContainerUtils.FIELD_DELIMITER + packageSignature + ContainerUtils.FIELD_DELIMITER + str2, str3, str4)) {
                HMSLog.i("HMSPackageManager", "result: " + str + ", " + str2 + ", " + next.f1526f);
                this.f1518h = PackageConstants.GENERAL_SERVICES_ACTION;
                b(str2);
                return new Pair<>(str, packageSignature);
            }
        }
        return null;
    }

    public static HMSPackageManager getInstance(Context context) {
        synchronized (o) {
            if (f1513n == null) {
                if (context.getApplicationContext() != null) {
                    f1513n = new HMSPackageManager(context.getApplicationContext());
                } else {
                    f1513n = new HMSPackageManager(context);
                }
                f1513n.j();
                f1513n.a();
            }
        }
        return f1513n;
    }

    private ArrayList<PackagePriorityInfo> h() {
        try {
            List<ResolveInfo> queryIntentServices = this.a.getPackageManager().queryIntentServices(new Intent(PackageConstants.GENERAL_SERVICES_ACTION), 128);
            if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
                ArrayList<PackagePriorityInfo> arrayList = new ArrayList<>();
                for (ResolveInfo resolveInfo : queryIntentServices) {
                    String str = resolveInfo.serviceInfo.applicationInfo.packageName;
                    long packageFirstInstallTime = this.b.getPackageFirstInstallTime(str);
                    Bundle bundle = resolveInfo.serviceInfo.metaData;
                    if (bundle == null) {
                        HMSLog.e("HMSPackageManager", "package " + str + " get metaData is null");
                    } else {
                        String a2 = a(bundle, "hms_app_checker_config");
                        String a3 = a(a2);
                        if (TextUtils.isEmpty(a3)) {
                            HMSLog.i("HMSPackageManager", "get priority fail. hmsCheckerCfg: " + a2);
                        } else {
                            String a4 = a(bundle, "hms_app_signer_v2");
                            if (TextUtils.isEmpty(a4)) {
                                HMSLog.i("HMSPackageManager", "get signerV2 fail.");
                            } else {
                                String a5 = a(bundle, "hms_app_cert_chain");
                                if (TextUtils.isEmpty(a5)) {
                                    HMSLog.i("HMSPackageManager", "get certChain fail.");
                                } else {
                                    HMSLog.i("HMSPackageManager", "add: " + str + ", " + a2 + ", " + packageFirstInstallTime);
                                    arrayList.add(new PackagePriorityInfo(str, a2, a4, a5, a3, packageFirstInstallTime));
                                }
                            }
                        }
                    }
                }
                Collections.sort(arrayList);
                return arrayList;
            }
            HMSLog.e("HMSPackageManager", "query aglite action, resolveInfoList is null or empty");
            return null;
        } catch (Exception e2) {
            HMSLog.e("HMSPackageManager", "query aglite action failed. " + e2.getMessage());
            return null;
        }
    }

    private void i() {
        synchronized (p) {
            Pair<String, String> f2 = f();
            if (f2 == null) {
                HMSLog.e("HMSPackageManager", "<initHmsPackageInfo> Failed to find HMS apk");
                e();
                return;
            }
            this.f1514c = (String) f2.first;
            this.d = (String) f2.second;
            this.f1515e = this.b.getPackageVersionCode(getHMSPackageName());
            HMSLog.i("HMSPackageManager", "<initHmsPackageInfo> Succeed to find HMS apk: " + this.f1514c + " version: " + this.f1515e);
        }
    }

    private void j() {
        synchronized (p) {
            Pair<String, String> g2 = g();
            if (g2 == null) {
                HMSLog.e("HMSPackageManager", "<initHmsPackageInfoForMultiService> Failed to find HMS apk");
                d();
                return;
            }
            this.f1516f = (String) g2.first;
            this.f1517g = (String) g2.second;
            this.f1520j = this.b.getPackageVersionCode(getHMSPackageNameForMultiService());
            HMSLog.i("HMSPackageManager", "<initHmsPackageInfoForMultiService> Succeed to find HMS apk: " + this.f1516f + " version: " + this.f1520j);
        }
    }

    private boolean k() {
        Bundle bundle;
        PackageManager packageManager = this.a.getPackageManager();
        if (packageManager == null) {
            HMSLog.e("HMSPackageManager", "In isMinApkVersionEffective, Failed to get 'PackageManager' instance.");
            return true;
        }
        try {
        } catch (AndroidException unused) {
            HMSLog.e("HMSPackageManager", "In isMinApkVersionEffective, Failed to read meta data for HMSCore API level.");
        } catch (RuntimeException e2) {
            HMSLog.e("HMSPackageManager", "In isMinApkVersionEffective, Failed to read meta data for HMSCore API level.", e2);
        }
        if (!TextUtils.isEmpty(this.f1518h) && (this.f1518h.equals(PackageConstants.GENERAL_SERVICES_ACTION) || this.f1518h.equals(PackageConstants.INTERNAL_SERVICES_ACTION))) {
            HMSLog.i("HMSPackageManager", "action = " + this.f1518h + " exist");
            return false;
        }
        ApplicationInfo applicationInfo = packageManager.getPackageInfo(getHMSPackageName(), 128).applicationInfo;
        if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && bundle.containsKey("com.huawei.hms.kit.api_level:hmscore") && (getHmsVersionCode() >= 50000000 || getHmsVersionCode() <= 19999999)) {
            HMSLog.i("HMSPackageManager", "MinApkVersion is disabled.");
            return false;
        }
        return true;
    }

    public String getHMSFingerprint() {
        String str = this.d;
        return str == null ? "B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05" : str;
    }

    public String getHMSPackageName() {
        HMSLog.i("HMSPackageManager", "Enter getHMSPackageName");
        refresh();
        String str = this.f1514c;
        if (str != null) {
            if (PackageManagerHelper.PackageStates.NOT_INSTALLED.equals(this.b.getPackageStates(str))) {
                HMSLog.i("HMSPackageManager", "The package name is not installed and needs to be refreshed again");
                i();
            }
            String str2 = this.f1514c;
            if (str2 != null) {
                return str2;
            }
        }
        if (!PackageManagerHelper.PackageStates.NOT_INSTALLED.equals(this.b.getPackageStates("com.huawei.hwid"))) {
            "B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05".equalsIgnoreCase(this.b.getPackageSignature("com.huawei.hwid"));
        }
        return "com.huawei.hwid";
    }

    public String getHMSPackageNameForMultiService() {
        HMSLog.i("HMSPackageManager", "Enter getHMSPackageNameForMultiService");
        refreshForMultiService();
        String str = this.f1516f;
        if (str != null) {
            if (PackageManagerHelper.PackageStates.NOT_INSTALLED.equals(this.b.getPackageStates(str))) {
                HMSLog.i("HMSPackageManager", "The package name is not installed and needs to be refreshed again");
                j();
            }
            String str2 = this.f1516f;
            return str2 != null ? str2 : "com.huawei.hwid";
        }
        return "com.huawei.hwid";
    }

    public PackageManagerHelper.PackageStates getHMSPackageStates() {
        synchronized (o) {
            refresh();
            PackageManagerHelper.PackageStates packageStates = this.b.getPackageStates(this.f1514c);
            PackageManagerHelper.PackageStates packageStates2 = PackageManagerHelper.PackageStates.NOT_INSTALLED;
            if (packageStates == packageStates2) {
                e();
                return packageStates2;
            }
            boolean z = false;
            if ("com.huawei.hwid".equals(this.f1514c) && c() == 1) {
                return PackageManagerHelper.PackageStates.SPOOF;
            }
            if (packageStates == PackageManagerHelper.PackageStates.ENABLED && !this.d.equals(this.b.getPackageSignature(this.f1514c))) {
                z = true;
            }
            return z ? packageStates2 : packageStates;
        }
    }

    public PackageManagerHelper.PackageStates getHMSPackageStatesForMultiService() {
        synchronized (o) {
            refreshForMultiService();
            PackageManagerHelper.PackageStates packageStates = this.b.getPackageStates(this.f1516f);
            PackageManagerHelper.PackageStates packageStates2 = PackageManagerHelper.PackageStates.NOT_INSTALLED;
            if (packageStates == packageStates2) {
                d();
                return packageStates2;
            }
            boolean z = false;
            if ("com.huawei.hwid".equals(this.f1516f) && c() == 1) {
                return PackageManagerHelper.PackageStates.SPOOF;
            }
            if (packageStates == PackageManagerHelper.PackageStates.ENABLED && !this.f1517g.equals(this.b.getPackageSignature(this.f1516f))) {
                z = true;
            }
            return z ? packageStates2 : packageStates;
        }
    }

    public int getHmsMultiServiceVersion() {
        return this.b.getPackageVersionCode(getHMSPackageNameForMultiService());
    }

    public int getHmsVersionCode() {
        return this.b.getPackageVersionCode(getHMSPackageName());
    }

    public String getInnerServiceAction() {
        return PackageConstants.INTERNAL_SERVICES_ACTION;
    }

    public String getServiceAction() {
        return !TextUtils.isEmpty(this.f1518h) ? this.f1518h : "com.huawei.hms.core.aidlservice";
    }

    public boolean hmsVerHigherThan(int i2) {
        if (this.f1515e >= i2 || !k()) {
            return true;
        }
        int packageVersionCode = this.b.getPackageVersionCode(getHMSPackageName());
        this.f1515e = packageVersionCode;
        return packageVersionCode >= i2;
    }

    public boolean isApkNeedUpdate(int i2) {
        int hmsVersionCode = getHmsVersionCode();
        HMSLog.i("HMSPackageManager", "current versionCode:" + hmsVersionCode + ", target version requirements: " + i2);
        return hmsVersionCode < i2;
    }

    public boolean isApkUpdateNecessary(int i2) {
        int hmsVersionCode = getHmsVersionCode();
        HMSLog.i("HMSPackageManager", "current versionCode:" + hmsVersionCode + ", minimum version requirements: " + i2);
        return k() && hmsVersionCode < i2;
    }

    public boolean isUseOldCertificate() {
        return this.f1523m;
    }

    public void refresh() {
        if (TextUtils.isEmpty(this.f1514c) || TextUtils.isEmpty(this.d)) {
            i();
        }
    }

    public void refreshForMultiService() {
        if (TextUtils.isEmpty(this.f1516f) || TextUtils.isEmpty(this.f1517g)) {
            j();
        }
    }

    public void resetMultiServiceState() {
        d();
    }

    public void setUseOldCertificate(boolean z) {
        this.f1523m = z;
    }

    private String a(Bundle bundle, String str) {
        if (!bundle.containsKey(str)) {
            HMSLog.e("HMSPackageManager", "no " + str + " in metaData");
            return null;
        }
        return bundle.getString(str);
    }

    private void b(String str) {
        String a2 = a(str);
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        this.f1519i = a2.substring(9);
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int indexOf = str.indexOf("priority=");
        if (indexOf == -1) {
            HMSLog.e("HMSPackageManager", "get indexOfIdentifier -1");
            return null;
        }
        int indexOf2 = str.indexOf(DYConstants.DY_REGEX_COMMA, indexOf);
        if (indexOf2 == -1) {
            indexOf2 = str.length();
        }
        return str.substring(indexOf, indexOf2);
    }

    private boolean b() {
        String hmsPath = ReadApkFileUtil.getHmsPath(this.a);
        if (hmsPath == null) {
            HMSLog.i("HMSPackageManager", "hmsPath is null!");
            return false;
        } else if (!ReadApkFileUtil.isCertFound(hmsPath)) {
            HMSLog.i("HMSPackageManager", "NO huawer.cer in HMS!");
            return false;
        } else if (!ReadApkFileUtil.checkSignature()) {
            HMSLog.i("HMSPackageManager", "checkSignature fail!");
            return false;
        } else if (ReadApkFileUtil.verifyApkHash(hmsPath)) {
            return true;
        } else {
            HMSLog.i("HMSPackageManager", "verifyApkHash fail!");
            return false;
        }
    }

    private boolean a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            List<X509Certificate> b = com.huawei.hms.device.a.b(str3);
            if (b.size() == 0) {
                HMSLog.e("HMSPackageManager", "certChain is empty");
                return false;
            } else if (!com.huawei.hms.device.a.a(com.huawei.hms.device.a.a(this.a), b)) {
                HMSLog.e("HMSPackageManager", "failed to verify cert chain");
                return false;
            } else {
                X509Certificate x509Certificate = b.get(b.size() - 1);
                if (!com.huawei.hms.device.a.a(x509Certificate, "Huawei CBG HMS")) {
                    HMSLog.e("HMSPackageManager", "CN is invalid");
                    return false;
                } else if (!com.huawei.hms.device.a.b(x509Certificate, "Huawei CBG Cloud Security Signer")) {
                    HMSLog.e("HMSPackageManager", "OU is invalid");
                    return false;
                } else if (com.huawei.hms.device.a.a(x509Certificate, str, str2)) {
                    return true;
                } else {
                    HMSLog.e("HMSPackageManager", "signature is invalid: " + str);
                    return false;
                }
            }
        }
        HMSLog.e("HMSPackageManager", "args is invalid");
        return false;
    }

    private void a() {
        new Thread(new a(), "Thread-asyncOnceCheckMDMState").start();
    }

    private static String a(int i2) {
        if (i2 == 1) {
            return "SPOOFED";
        }
        if (i2 == 2) {
            return "SUCCESS";
        }
        if (i2 == 3) {
            return "UNCHECKED";
        }
        HMSLog.e("HMSPackageManager", "invalid checkMDM state: " + i2);
        return "";
    }
}
