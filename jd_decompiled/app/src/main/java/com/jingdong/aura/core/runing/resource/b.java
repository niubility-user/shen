package com.jingdong.aura.core.runing.resource;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;

/* loaded from: classes4.dex */
public abstract class b extends Resources {
    public b(AssetManager assetManager, Resources resources) {
        super(assetManager, com.jingdong.aura.a.b.c.F().getOsDisplayMetrics(resources), com.jingdong.aura.a.b.c.F().getOsConfiguration(resources));
    }

    @Override // android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        return super.getIdentifier(str, str2, str3);
    }

    @Override // android.content.res.Resources
    public String getString(int i2) {
        return (Build.VERSION.SDK_INT < 21 || !(i2 == 33816578 || i2 == 262146 || i2 == 50593794)) ? super.getString(i2) : "Web View";
    }
}
