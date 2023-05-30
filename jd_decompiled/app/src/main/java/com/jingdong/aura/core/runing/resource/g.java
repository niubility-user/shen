package com.jingdong.aura.core.runing.resource;

import android.app.Application;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.text.TextUtils;
import com.jingdong.aura.a.c.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes4.dex */
public class g extends a {
    Set<String> d;

    /* renamed from: c */
    private final com.jingdong.aura.core.util.l.b f12164c = com.jingdong.aura.core.util.l.c.a("ResourceFactoryOriginal");

    /* renamed from: e */
    private List<String> f12165e = null;

    private void c(Application application, Resources resources, String str) {
        if (this.d != null && !DelegateResourcesUtils.isLowLevel()) {
            if (!TextUtils.isEmpty(str) && !this.d.contains(str)) {
                DelegateResourcesUtils.tryAddAssetPath(application.getAssets(), str);
                this.d.add(str);
            }
        } else {
            Set<String> a = a(application, str);
            if (a == null) {
                DelegateResourcesUtils.printAssetPath(str, this.d, this.f12164c);
                return;
            }
            this.d = a;
            AssetManager assetManager = (AssetManager) AssetManager.class.newInstance();
            Iterator<String> it = a.iterator();
            while (it.hasNext()) {
                DelegateResourcesUtils.tryAddAssetPath(assetManager, it.next());
            }
            Resources constructResources = DelegateResourcesUtils.constructResources(assetManager, resources, this);
            l.d = constructResources;
            com.jingdong.aura.core.reflection.a.a(application, constructResources);
        }
        DelegateResourcesUtils.printAssetPath(str, this.d, this.f12164c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.jingdong.aura.core.runing.resource.a
    public b a(AssetManager assetManager, Resources resources) {
        return new d(assetManager, resources);
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
            try {
                if (!DelegateResourcesUtils.isLowLevel() && this.f12165e == null) {
                    List<String> originAssetsPath = DelegateResourcesUtils.getOriginAssetsPath(application.getResources().getAssets());
                    if (originAssetsPath != null) {
                        this.f12165e = new ArrayList();
                        for (String str2 : originAssetsPath) {
                            if (str2 != null && !com.jingdong.aura.a.b.b.a(str2)) {
                                this.f12165e.add(str2);
                            }
                        }
                    }
                    List<String> list = this.f12165e;
                    if (list != null) {
                        linkedHashSet.addAll(list);
                    }
                }
            } catch (Throwable th) {
                this.f12164c.a("get original asset path exception:", th);
                com.jingdong.aura.a.b.e.a("com.jingdong.aura", "get original asset path exception", "DelegateResources.generateNewAssetPath", th);
            }
            Set<String> set2 = this.d;
            if (set2 != null) {
                linkedHashSet.addAll(set2);
            }
            if (str != null) {
                linkedHashSet.add(str);
            }
            return linkedHashSet;
        }
        return null;
    }
}
