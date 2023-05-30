package com.jingdong.aura.core.runing.resource;

import android.content.res.AssetManager;
import android.content.res.Resources;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class c extends b {
    private Map<String, Integer> a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(AssetManager assetManager, Resources resources) {
        super(assetManager, resources);
        this.a = new ConcurrentHashMap();
    }

    @Override // com.jingdong.aura.core.runing.resource.b, android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        int identifier = super.getIdentifier(str, str2, str3);
        if (identifier != 0) {
            return identifier;
        }
        if (com.jingdong.aura.core.reflection.b.p == null) {
            return DelegateResourcesUtils.getResIdentifier(str, str2, str3, this.a);
        }
        a b = a.b();
        if (b == null || !(b instanceof f)) {
            return 0;
        }
        f fVar = (f) b;
        for (int i2 = 0; i2 < fVar.f12163e.size(); i2++) {
            try {
                identifier = ((Integer) com.jingdong.aura.core.reflection.b.p.a(fVar.f12163e.get(i2), str, str2, str3)).intValue();
            } catch (Exception unused) {
            }
            if (identifier != 0) {
                return identifier;
            }
        }
        return DelegateResourcesUtils.getResIdentifier(str, str2, str3, this.a);
    }
}
