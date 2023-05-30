package com.jingdong.aura.core.runing.resource;

import android.content.res.AssetManager;
import android.content.res.Resources;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class d extends b {
    private Map<String, Integer> a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(AssetManager assetManager, Resources resources) {
        super(assetManager, resources);
        this.a = new ConcurrentHashMap();
    }

    @Override // com.jingdong.aura.core.runing.resource.b, android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        int identifier = super.getIdentifier(str, str2, str3);
        return identifier != 0 ? identifier : DelegateResourcesUtils.getResIdentifier(str, str2, str3, this.a);
    }
}
