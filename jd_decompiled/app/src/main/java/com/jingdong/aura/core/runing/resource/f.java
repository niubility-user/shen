package com.jingdong.aura.core.runing.resource;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.aura.core.reflection.Hack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public class f extends a {
    Set<String> d;

    /* renamed from: c */
    private final com.jingdong.aura.core.util.l.b f12162c = com.jingdong.aura.core.util.l.c.a("ResourceFactoryImproved");

    /* renamed from: e */
    ArrayList<AssetManager> f12163e = new ArrayList<>();

    private void c(Application application, Resources resources, String str) {
        this.f12162c.a("addNewAssetPaths: resources: " + resources);
        this.f12162c.a("addNewAssetPaths: newPath: " + str);
        AssetManager b = b(application, str);
        if (b == null) {
            this.f12162c.a("addNewAssetPaths:newPath:" + str + " had been added, needn't update resource");
            return;
        }
        DelegateResourcesUtils.updateResources(application, b, DelegateResourcesUtils.constructResources(b, resources, this));
        a(str);
        DelegateResourcesUtils.printAssetPath(str, this.d, this.f12162c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.aura.core.runing.resource.a
    public b a(AssetManager assetManager, Resources resources) {
        return new c(assetManager, resources);
    }

    @Override // com.jingdong.aura.core.runing.resource.a
    protected void b(Application application, Resources resources, String str) {
        c(application, resources, str);
    }

    private Set<String> a(Application application, String str) {
        Set<String> set;
        if (str == null || (set = this.d) == null || !set.contains(str)) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            linkedHashSet.add(application.getApplicationInfo().sourceDir);
            Set<String> set2 = this.d;
            if (set2 != null) {
                linkedHashSet.addAll(set2);
            }
            if (str != null) {
                linkedHashSet.add(str);
            }
            if (Build.VERSION.SDK_INT > 20) {
                try {
                    Set<String> a = a(application);
                    if (a != null) {
                        linkedHashSet.addAll(a);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return linkedHashSet;
        }
        return null;
    }

    private AssetManager b(Application application, String str) {
        if (DelegateResourcesUtils.isLowLevel()) {
            Set<String> a = a(application, str);
            if (a == null) {
                DelegateResourcesUtils.printAssetPath(str, this.d, this.f12162c);
                return null;
            }
            this.d = a;
            AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
            Iterator<String> it = a.iterator();
            while (it.hasNext()) {
                DelegateResourcesUtils.tryAddAssetPath(assetManager, it.next());
            }
            return assetManager;
        }
        AssetManager assets = application.getAssets();
        if (this.d == null) {
            this.d = a(application);
            assets = (AssetManager) AssetManager.class.newInstance();
            Iterator<String> it2 = this.d.iterator();
            while (it2.hasNext()) {
                DelegateResourcesUtils.tryAddAssetPath(assets, it2.next());
            }
        } else if (!TextUtils.isEmpty(str) && !this.d.contains(str)) {
            DelegateResourcesUtils.tryAddAssetPath(assets, str);
            this.d.add(str);
        }
        return assets;
    }

    private Set<String> a(Application application) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        String str = application.getApplicationInfo().sourceDir;
        this.f12162c.a("base apk:" + str);
        linkedHashSet.add(str);
        if (com.jingdong.aura.a.b.c.R() && Build.VERSION.SDK_INT >= 21 && application.getApplicationInfo().splitSourceDirs != null) {
            List asList = Arrays.asList(application.getApplicationInfo().splitSourceDirs);
            this.f12162c.a("split Apks:" + asList);
            linkedHashSet.addAll(asList);
        }
        try {
            this.f12162c.a("generateNewAssetPathsForSystem");
            List<String> originAssetsPath = DelegateResourcesUtils.getOriginAssetsPath(application.getResources().getAssets());
            ArrayList arrayList = new ArrayList();
            if (originAssetsPath != null) {
                for (String str2 : originAssetsPath) {
                    if (str2 != null) {
                        this.f12162c.a("asset path: " + str2);
                        if (!com.jingdong.aura.a.b.b.a(str2) && (str2.toLowerCase().contains("/system/framework") || str2.toLowerCase().contains("/system/app") || str2.toLowerCase().contains("/product/app") || str2.toLowerCase().contains("webview") || str2.toLowerCase().contains("chrome"))) {
                            arrayList.add(str2);
                        }
                    }
                }
            }
            linkedHashSet.addAll(arrayList);
        } catch (Throwable th) {
            this.f12162c.a("get original asset path exception:", th);
            com.jingdong.aura.a.b.e.a("com.jingdong.aura", "get original asset path exception", "DelegateResources.generateNewAssetPath", th);
        }
        return linkedHashSet;
    }

    private void a(String str) {
        try {
            if (TextUtils.isEmpty(str) || com.jingdong.aura.core.reflection.b.f12159m == null || com.jingdong.aura.core.reflection.b.o == null || Build.VERSION.SDK_INT <= 20) {
                return;
            }
            AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
            com.jingdong.aura.core.reflection.b.f12159m.a(assetManager, str);
            Hack.e eVar = com.jingdong.aura.core.reflection.b.o;
            if (eVar != null && eVar.a() != null) {
                com.jingdong.aura.core.reflection.b.o.a(assetManager, new Object[0]);
            }
            this.f12163e.add(assetManager);
        } catch (Throwable unused) {
        }
    }
}
