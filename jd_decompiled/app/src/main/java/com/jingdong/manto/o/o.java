package com.jingdong.manto.o;

import android.util.SparseArray;
import com.jingdong.manto.sdk.api.ICustomMenuInterface;
import com.jingdong.manto.sdk.api.IHostMenuInterface;
import java.util.ArrayList;

/* loaded from: classes16.dex */
public class o {
    public SparseArray<m> a;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes16.dex */
    public static class b {
        static o a = new o();
    }

    private o() {
        ICustomMenuInterface iCustomMenuInterface = (ICustomMenuInterface) com.jingdong.a.n(ICustomMenuInterface.class);
        this.a = new SparseArray<>();
        if (((IHostMenuInterface) com.jingdong.a.n(IHostMenuInterface.class)) != null) {
            a(new f());
            a(new e());
            a(new j());
        }
        if (iCustomMenuInterface == null || !iCustomMenuInterface.disableShortCut()) {
            a(new h());
        }
        if (iCustomMenuInterface == null || !iCustomMenuInterface.disableAbout()) {
            a(new com.jingdong.manto.o.a());
        }
        if (iCustomMenuInterface == null || !iCustomMenuInterface.disableToggleFavor()) {
            a(new k());
        }
        if (iCustomMenuInterface != null && !iCustomMenuInterface.disableFeedBack()) {
            a(new d());
        }
        if (iCustomMenuInterface == null || !iCustomMenuInterface.disableShare()) {
            a(new i());
        }
        if (iCustomMenuInterface == null || !iCustomMenuInterface.disableDebugSwitch()) {
            a(new c());
        }
        if (iCustomMenuInterface == null || !iCustomMenuInterface.disablePerformanceSwitch()) {
            a(new g());
        }
        a();
    }

    private void a() {
        ArrayList<ICustomMenuInterface.CustomMenuData> customMenus;
        ICustomMenuInterface iCustomMenuInterface = (ICustomMenuInterface) com.jingdong.a.n(ICustomMenuInterface.class);
        if (iCustomMenuInterface == null || (customMenus = iCustomMenuInterface.getCustomMenus(com.jingdong.manto.c.a())) == null) {
            return;
        }
        for (int i2 = 0; i2 < customMenus.size(); i2++) {
            ICustomMenuInterface.CustomMenuData customMenuData = customMenus.get(i2);
            a(new com.jingdong.manto.o.b(false, i2, customMenuData.name, customMenuData.id, customMenuData.visible, customMenuData.iconResId));
        }
    }

    private void a(m mVar) {
        this.a.put(mVar.a, mVar);
    }

    public static o b() {
        return b.a;
    }
}
