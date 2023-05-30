package com.jingdong.app.mall.home.floor.common.i;

import android.text.TextUtils;
import android.util.SparseArray;
import com.jd.libs.hybrid.offlineload.utils.OfflineFileUtils;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.app.mall.home.floor.view.view.MallFloorCategory;
import com.jingdong.app.mall.home.widget.HomeRecyclerAdapter;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class u {
    private static Map<String, t> a = new HashMap(256);
    private static SparseArray<t> b = new SparseArray<>(256);

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, com.jingdong.app.mall.home.floor.view.b.a> f9368c = new HashMap(32);
    private static final int d;

    /* renamed from: e  reason: collision with root package name */
    private static int f9369e;

    static {
        for (t tVar : t.values()) {
            tVar.parseFloorType(a, b);
        }
        for (com.jingdong.app.mall.home.floor.view.b.a aVar : com.jingdong.app.mall.home.floor.view.b.a.values()) {
            aVar.parseLineType(f9368c);
        }
        int i2 = HomeRecyclerAdapter.f11020k;
        HomeRecyclerAdapter.f11020k = i2 + 1;
        d = i2;
    }

    public static void a() {
        try {
            com.jingdong.app.mall.home.widget.b lastCreateView = t.FLOOR_CATEGORY.getLastCreateView();
            if (lastCreateView instanceof MallFloorCategory) {
                ((MallFloorCategory) lastCreateView).changeTabName();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized int b(t tVar) {
        int i2;
        synchronized (u.class) {
            i2 = f9369e;
            f9369e = i2 + 1;
            b.put(i2, tVar);
        }
        return i2;
    }

    public static t c(com.jingdong.app.mall.home.r.e.g gVar) {
        if (gVar == null) {
            return t.UNKNOWN;
        }
        com.jingdong.app.mall.home.r.e.h hVar = gVar.mParentModel;
        if (hVar != null && OfflineFileUtils.HYBRID_OFFLINE_ROOT_DIR.equals(hVar.f10698j)) {
            t g2 = g(gVar, gVar.a);
            return g2 != t.UNKNOWN ? g2 : g(gVar, gVar.f());
        } else if (hVar == null) {
            return t.UNKNOWN;
        } else {
            return g(gVar, hVar.f10698j);
        }
    }

    public static CategoryEntity.CaItem d() {
        try {
            com.jingdong.app.mall.home.widget.b lastCreateView = t.FLOOR_CATEGORY.getLastCreateView();
            if (lastCreateView instanceof MallFloorCategory) {
                return ((MallFloorCategory) lastCreateView).getFirstItem();
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static t e(com.jingdong.app.mall.home.r.e.h hVar) {
        if (hVar != null && !TextUtils.isEmpty(hVar.f10698j)) {
            String str = hVar.f10698j;
            t tVar = a.get(str);
            if (tVar == null) {
                return t.UNKNOWN;
            }
            return OfflineFileUtils.HYBRID_OFFLINE_ROOT_DIR.equals(str) ? t.WITHSUBFLOOR : tVar.convertType(hVar);
        }
        return t.UNKNOWN;
    }

    public static com.jingdong.app.mall.home.floor.view.b.a f(com.jingdong.app.mall.home.r.e.f fVar) {
        com.jingdong.app.mall.home.floor.view.b.a aVar = f9368c.get(fVar.getJsonString(DeeplinkProductDetailHelper.LAYER_STYLE));
        return aVar == null ? com.jingdong.app.mall.home.floor.view.b.a.UNKNOWN : aVar;
    }

    private static t g(com.jingdong.app.mall.home.r.e.g gVar, String str) {
        t tVar = a.get(str);
        if (tVar != null && gVar != null) {
            tVar = tVar.convertType(gVar.mParentModel, gVar);
        }
        return tVar == null ? t.UNKNOWN : tVar;
    }

    public static synchronized t h(int i2) {
        t tVar;
        synchronized (u.class) {
            tVar = b.get(i2);
        }
        return tVar;
    }

    public static void i() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void j() {
        f9369e = d;
    }

    public static void k() {
        try {
            com.jingdong.app.mall.home.widget.b lastCreateView = t.FLOOR_CATEGORY.getLastCreateView();
            if (lastCreateView instanceof MallFloorCategory) {
                ((MallFloorCategory) lastCreateView).onBackPressed();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
