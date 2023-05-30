package com.jingdong.aura.sdk.update;

import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;
import com.jingdong.aura.provided.api.IAuraInstallCallBack;
import com.jingdong.aura.provided.api.IAuraInstallManager;
import com.jingdong.aura.sdk.update.AuraBundleResult;
import com.jingdong.aura.sdk.update.b.c;
import com.jingdong.aura.sdk.update.b.g;
import com.jingdong.aura.sdk.update.b.m;
import com.jingdong.aura.serviceloder.AuraServiceLoader;
import com.jingdong.aura.wrapper.AuraConfig;
import com.jingdong.aura.wrapper.AuraInitializer;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.jdsdk.constant.CartConstant;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: e */
    static com.jingdong.aura.sdk.update.b.b f12234e = new com.jingdong.aura.sdk.update.b.b();
    private static volatile a o;
    public AuraUpdateConfig a;
    ArrayList<AuraBundleResult> b;

    /* renamed from: c */
    List<AuraBundleResult> f12235c;
    com.jingdong.aura.sdk.update.updater.a d;

    /* renamed from: g */
    Handler f12237g;

    /* renamed from: h */
    public SharedPreferences f12238h;

    /* renamed from: i */
    public SharedPreferences f12239i;

    /* renamed from: k */
    public File f12241k;

    /* renamed from: l */
    public com.jingdong.aura.sdk.update.downloader.a f12242l;

    /* renamed from: m */
    public com.jingdong.aura.sdk.update.report.a f12243m;

    /* renamed from: n */
    com.jingdong.aura.sdk.update.a.a f12244n;

    /* renamed from: f */
    boolean f12236f = false;

    /* renamed from: j */
    boolean f12240j = false;

    private a() {
    }

    private static AuraBundleResult a(List<AuraBundleResult> list, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (AuraBundleResult auraBundleResult : list) {
            if (str.equals(auraBundleResult.updateId)) {
                return auraBundleResult;
            }
        }
        return null;
    }

    public static a a() {
        if (o == null) {
            synchronized (a.class) {
                if (o == null) {
                    o = new a();
                }
            }
        }
        return o;
    }

    static /* synthetic */ void b(a aVar) {
        List<AuraBundleResult> list;
        if (!m.a(aVar.a.context) || (list = aVar.f12235c) == null || list.size() <= 0) {
            return;
        }
        for (AuraBundleResult auraBundleResult : aVar.f12235c) {
            if (new File(auraBundleResult.downloadedFilePath).exists()) {
                aVar.a(auraBundleResult);
            }
        }
    }

    static /* synthetic */ void b(a aVar, List list) {
        boolean z;
        try {
            File file = aVar.f12241k;
            if (file.exists() && file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        AuraBundleResult auraBundleResult = (AuraBundleResult) it.next();
                        if (!file2.getName().equals(auraBundleResult.downloadedFileName)) {
                            if (!file2.getName().equals(auraBundleResult.downloadedFileName + ".aurahttp") && !file2.getName().equals("auraUpdateObj")) {
                            }
                        }
                        z = false;
                    }
                    z = true;
                    if (z) {
                        file2.delete();
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static List<AuraBundleResult> c() {
        try {
            Object a = g.a();
            com.jingdong.aura.sdk.update.b.c.a("restore BundleResults");
            return (List) a;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void e() {
        HashMap hashMap = new HashMap();
        hashMap.put(IAuraInstallManager.class.getName(), com.jingdong.aura.sdk.provided.b.class.getName());
        hashMap.put(IAuraInstallCallBack.class.getName(), com.jingdong.aura.sdk.provided.a.class.getName());
        AuraServiceLoader.setServiceInfo(hashMap);
    }

    public final List<AuraBundleResult> a(ArrayList<AuraBundleResult> arrayList, List<AuraBundleResult> list) {
        ArrayList<AuraBundleResult> arrayList2;
        String format;
        if (arrayList == null && list == null) {
            com.jingdong.aura.sdk.update.b.c.a("no bundle download info find");
            return null;
        }
        if (arrayList == null) {
            arrayList2 = new ArrayList(list);
        } else if (list == null) {
            arrayList2 = new ArrayList(arrayList);
        } else {
            ArrayList arrayList3 = new ArrayList(list);
            Iterator<AuraBundleResult> it = arrayList.iterator();
            while (it.hasNext()) {
                AuraBundleResult next = it.next();
                AuraBundleResult a = a(arrayList3, next.updateId);
                if (a == null) {
                    arrayList3.add(next);
                } else {
                    com.jingdong.aura.sdk.update.b.c.a(String.format("provided bundle %s upgrade configured", next.updateId));
                    int i2 = a.bundleVersionCode;
                    int i3 = next.bundleVersionCode;
                    if (i2 < i3) {
                        arrayList3.remove(a);
                        arrayList3.add(next);
                        format = String.format("%s upgrade versionCode is less than current versionCode, ignore", next.updateId);
                    } else if (i2 == i3 && !a.md5.equals(next.md5)) {
                        arrayList3.remove(a);
                        arrayList3.add(next);
                        format = String.format("%s upgrade versionCode equals current versionCode,but md5 not same, ignore", next.updateId);
                    }
                    com.jingdong.aura.sdk.update.b.c.b(format);
                }
            }
            arrayList2 = arrayList3;
        }
        ArrayList arrayList4 = new ArrayList();
        for (AuraBundleResult auraBundleResult : arrayList2) {
            auraBundleResult.downloadedFileName = auraBundleResult.md5;
            auraBundleResult.downloadedFilePath = new File(this.f12241k, auraBundleResult.downloadedFileName).getAbsolutePath();
            auraBundleResult.updateListener = new com.jingdong.aura.sdk.update.updater.b();
            arrayList4.add(new AuraBundleResult(auraBundleResult));
        }
        return arrayList4;
    }

    public final void a(final AuraBundleResult auraBundleResult) {
        try {
            com.jingdong.aura.sdk.update.b.c.a("updateBundle:".concat(String.valueOf(auraBundleResult)));
            final com.jingdong.aura.sdk.update.updater.a aVar = this.d;
            aVar.a().execute(new Runnable() { // from class: com.jingdong.aura.sdk.update.updater.a.2
                @Override // java.lang.Runnable
                public final void run() {
                    AuraBundleResult auraBundleResult2 = auraBundleResult;
                    if (auraBundleResult2 == null) {
                        c.b("BundleUpdater", "bundleinfo is null!");
                        return;
                    }
                    IUpdateListener iUpdateListener = auraBundleResult2.updateListener;
                    if (iUpdateListener != null) {
                        iUpdateListener.onInstallStart();
                    }
                    String bundleNameFromUpdateID = com.jingdong.aura.sdk.update.a.a().a.bundleInfoProvider.getBundleNameFromUpdateID(auraBundleResult.updateId);
                    c.a("BundleUpdater", "update bundle " + auraBundleResult.updateId + ",bundleName:" + bundleNameFromUpdateID);
                    com.jingdong.aura.sdk.update.b.b("bundle_update_install_start", com.jingdong.aura.sdk.update.c.a(bundleNameFromUpdateID, auraBundleResult.bundleVersionCode, "update install start", "update"));
                    File file = new File(auraBundleResult.downloadedFilePath);
                    if (!file.exists()) {
                        c.b("BundleUpdater", "can not find bundleFile:".concat(String.valueOf(file)));
                        IUpdateListener iUpdateListener2 = auraBundleResult.updateListener;
                        if (iUpdateListener2 != null) {
                            iUpdateListener2.onInstallFinish(false);
                        }
                        com.jingdong.aura.sdk.update.b.b("bundle_update_install_fail", com.jingdong.aura.sdk.update.c.a(bundleNameFromUpdateID, auraBundleResult.bundleVersionCode, "not fond download bundle", "update"));
                    } else if (!g.a(auraBundleResult)) {
                        c.b("BundleUpdater", "md5 not equal, not download completed!!");
                        IUpdateListener iUpdateListener3 = auraBundleResult.updateListener;
                        if (iUpdateListener3 != null) {
                            iUpdateListener3.onInstallFinish(false);
                        }
                        com.jingdong.aura.sdk.update.b.b("bundle_update_install_fail", com.jingdong.aura.sdk.update.c.a(bundleNameFromUpdateID, auraBundleResult.bundleVersionCode, "md5 check fail", "update"));
                    } else {
                        File file2 = new File(file.getParentFile(), file.getName() + ".dasec");
                        if (a.a(file2)) {
                            c.a("BundleUpdater", "transformed bundle file exist");
                        } else {
                            c.a("BundleUpdater", "start transform bundle file...");
                            file2.delete();
                            int a = com.jingdong.aura.sdk.a.a.a(file.getAbsolutePath(), file2.getAbsolutePath());
                            c.a("BundleUpdater", "code:".concat(String.valueOf(a)));
                            if (a == 1) {
                                c.a("BundleUpdater", "use origin bundle file");
                            } else {
                                file = file2;
                            }
                            if (!a.a(file)) {
                                c.b("BundleUpdater", "de transfrom bundle error!");
                                IUpdateListener iUpdateListener4 = auraBundleResult.updateListener;
                                if (iUpdateListener4 != null) {
                                    iUpdateListener4.onInstallFinish(false);
                                }
                                com.jingdong.aura.sdk.update.b.b("bundle_update_install_fail", com.jingdong.aura.sdk.update.c.a(bundleNameFromUpdateID, auraBundleResult.bundleVersionCode, "bundle decrypt error", "update"));
                                return;
                            }
                            file2 = file;
                        }
                        boolean update = AuraInitializer.update(bundleNameFromUpdateID, file2.getAbsolutePath(), auraBundleResult.bundleVersionCode, g.a(file2.getAbsolutePath()));
                        int i2 = auraBundleResult.bundleVersionCode;
                        if (update) {
                            com.jingdong.aura.sdk.update.b.b("bundle_update_install_success", com.jingdong.aura.sdk.update.c.a(bundleNameFromUpdateID, i2, "bundle update success", "update"));
                            c.a("BundleUpdater", String.format("update bundle %s success", auraBundleResult.updateId));
                        } else {
                            com.jingdong.aura.sdk.update.b.b("bundle_update_install_fail", com.jingdong.aura.sdk.update.c.a(bundleNameFromUpdateID, i2, "bundle update fail", "update"));
                            c.b("BundleUpdater", String.format("update bundle %s failed", auraBundleResult.updateId));
                        }
                        IUpdateListener iUpdateListener5 = auraBundleResult.updateListener;
                        if (iUpdateListener5 != null) {
                            iUpdateListener5.onInstallFinish(update);
                        }
                        try {
                            String str = bundleNameFromUpdateID + CartConstant.KEY_YB_INFO_LINK + auraBundleResult.bundleVersionCode + CartConstant.KEY_YB_INFO_LINK + update;
                            com.jingdong.aura.sdk.update.report.a aVar2 = com.jingdong.aura.sdk.update.a.a().f12243m;
                            if (TextUtils.isEmpty(bundleNameFromUpdateID)) {
                                bundleNameFromUpdateID = "";
                            }
                            aVar2.onTrace("AuraMaiDianUpdatePlugin", bundleNameFromUpdateID, auraBundleResult.bundleVersionCode, str, "BundleUpdater");
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final boolean a(int i2) {
        if (m.a(this.a.context)) {
            try {
                int i3 = this.f12239i.getInt("last_version_code", 0);
                com.jingdong.aura.sdk.update.b.c.a("last_version_code:".concat(String.valueOf(i3)));
                return i2 > i3;
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public final void b() {
        if (this.a.mobileConfig.isCloseUpdate()) {
            com.jingdong.aura.sdk.update.b.c.a("bundle update switch closed");
        } else if (this.f12236f) {
            if (!f12234e.a()) {
                com.jingdong.aura.sdk.update.b.c.b("AuraUpdateManager", "requestUpdateBundles timer is not ok");
                return;
            }
            com.jingdong.aura.sdk.update.b.c.a("AuraUpdateManager", "requestUpdateBundles timer is ok");
            this.f12244n.a(new com.jingdong.aura.sdk.update.a.b() { // from class: com.jingdong.aura.sdk.update.a.2
                {
                    a.this = this;
                }

                @Override // com.jingdong.aura.sdk.update.a.b
                public final void a(List<AuraBundleResult> list) {
                    if (list == null || list.size() <= 0) {
                        return;
                    }
                    a aVar = a.this;
                    aVar.f12235c = aVar.a(aVar.b, list);
                    a aVar2 = a.this;
                    a.b(aVar2, aVar2.f12235c);
                    a.this.f12242l.a(a.this.f12235c);
                }
            });
        }
    }

    public final ArrayList<AuraBundleResult> d() {
        List<Map<String, String>> providedBundleInfos = AuraConfig.getProvidedBundleInfos();
        if (providedBundleInfos == null || providedBundleInfos.size() <= 0) {
            return null;
        }
        ArrayList<AuraBundleResult> arrayList = new ArrayList<>();
        List<String> providedWifiDownloadList = this.a.bundleInfoProvider.getProvidedWifiDownloadList();
        for (Map<String, String> map : providedBundleInfos) {
            AuraBundleResult auraBundleResult = new AuraBundleResult();
            auraBundleResult.updateId = this.a.bundleInfoProvider.getUpdateIdFromBundleName(map.get("bundleName"));
            auraBundleResult.md5 = map.get("md5");
            auraBundleResult.bundleVersionCode = Integer.parseInt(map.get("versionCode"));
            auraBundleResult.size = Long.parseLong(map.get(ApkDownloadTable.FIELD_SIZE));
            auraBundleResult.downloadUrl = map.get("downloadUrl");
            auraBundleResult.downloadType = 0;
            if (providedWifiDownloadList != null && providedWifiDownloadList.contains(auraBundleResult.updateId)) {
                auraBundleResult.downloadType = 1;
            }
            auraBundleResult.bundleType = 1;
            if (TextUtils.isEmpty(auraBundleResult.updateId) || TextUtils.isEmpty(auraBundleResult.md5) || TextUtils.isEmpty(auraBundleResult.downloadUrl)) {
                com.jingdong.aura.sdk.update.b.c.b("provided bundleinfo is illegal:".concat(String.valueOf(auraBundleResult)));
            } else {
                arrayList.add(auraBundleResult);
                com.jingdong.aura.sdk.update.b.c.a("parse provided bundleinfo:".concat(String.valueOf(auraBundleResult)));
            }
        }
        return arrayList;
    }
}
