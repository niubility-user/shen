package com.jingdong.app.mall.home.n;

import android.os.SystemClock;
import com.facebook.react.modules.appstate.AppStateModule;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.home.floor.model.entity.CategoryEntity;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes4.dex */
public class e {
    public static AtomicInteger a = new AtomicInteger(0);
    private static CategoryEntity.DecorateInfo b;

    private static int a(List<com.jingdong.app.mall.home.n.g.u.c> list, int i2, long j2) {
        com.jingdong.app.mall.home.n.g.u.c typeModel = a.C_DIVIDER.getTypeModel(null, null, j2, -1);
        typeModel.z(i2);
        list.add(typeModel);
        return typeModel.getFloorHeight();
    }

    private static int b(List<com.jingdong.app.mall.home.n.g.u.c> list, JDJSONObject jDJSONObject, int i2, int i3, long j2) {
        com.jingdong.app.mall.home.n.g.u.c typeModel = a.C_TITLE.getTypeModel(jDJSONObject, null, j2, -1);
        typeModel.z(i2);
        list.add(typeModel);
        return typeModel.getFloorHeight();
    }

    public static CategoryEntity.DecorateInfo c() {
        CategoryEntity.DecorateInfo decorateInfo = b;
        return decorateInfo == null ? CategoryEntity.sDecorateInfo : decorateInfo;
    }

    public static int d() {
        return a.get();
    }

    public static List<com.jingdong.app.mall.home.n.g.u.c> e(@NotNull JDJSONObject jDJSONObject, CategoryEntity.CaItem caItem, int i2) {
        a a2;
        ArrayList arrayList = new ArrayList();
        JDJSONArray jSONArray = jDJSONObject.getJSONArray("cards");
        if (jSONArray != null && jSONArray.size() > 0) {
            int size = jSONArray.size();
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                int i3 = 0;
                for (int i4 = 0; i4 < size; i4++) {
                    JDJSONObject jSONObject = jSONArray.getJSONObject(i4);
                    if (jSONObject != null && (a2 = f.a(jSONObject.getString("cardType"))) != a.C_EMPTY) {
                        com.jingdong.app.mall.home.n.g.u.c typeModel = a2.getTypeModel(jSONObject, caItem, elapsedRealtime, i3 + i2);
                        if (typeModel.n()) {
                            if (typeModel instanceof com.jingdong.app.mall.home.n.g.u.a) {
                                ((com.jingdong.app.mall.home.n.g.u.a) typeModel).C(jDJSONObject);
                            }
                            if (a2.isFloorType()) {
                                i3++;
                            }
                            arrayList.add(typeModel);
                        }
                    }
                }
            } catch (Exception e2) {
                com.jingdong.app.mall.home.o.a.f.s0("CaParseUtil", e2);
            }
        }
        return arrayList;
    }

    public static List<com.jingdong.app.mall.home.n.g.u.c> f(@NotNull JDJSONObject jDJSONObject, CategoryEntity.CaItem caItem) {
        return g(jDJSONObject, caItem, SystemClock.elapsedRealtime());
    }

    public static List<com.jingdong.app.mall.home.n.g.u.c> g(@NotNull JDJSONObject jDJSONObject, CategoryEntity.CaItem caItem, long j2) {
        a a2;
        boolean z;
        com.jingdong.app.mall.home.n.g.u.c cVar;
        ArrayList arrayList = new ArrayList();
        JDJSONArray jSONArray = jDJSONObject.getJSONArray("floorList");
        if (jSONArray != null && jSONArray.size() > 0) {
            int size = jSONArray.size();
            a aVar = null;
            b = null;
            try {
                JDJSONObject jSONObject = jDJSONObject.getJSONObject(AppStateModule.APP_STATE_BACKGROUND);
                if (jSONObject != null && jSONObject.size() > 0) {
                    b = new CategoryEntity.DecorateInfo(jSONObject);
                }
                a.set(0);
                a aVar2 = null;
                boolean z2 = false;
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                while (i2 < size) {
                    JDJSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                    if (jSONObject2 != null && (a2 = f.a(jSONObject2.getString(DeeplinkProductDetailHelper.LAYER_STYLE))) != a.C_EMPTY) {
                        boolean z3 = a2 == a.C_SELECT;
                        if (!z3) {
                            z = z2;
                        } else if (!z2) {
                            z = true;
                        }
                        if (i4 == 0 && z3) {
                            jSONObject2.put("showHead", "1");
                        }
                        com.jingdong.app.mall.home.n.g.u.c typeModel = a2.getTypeModel(jSONObject2, caItem, j2, i4);
                        if (!typeModel.n()) {
                            if (z3 && typeModel.A()) {
                                int a3 = i3 + (i2 > 0 ? a(arrayList, i3, j2) : 0);
                                i3 = a3 + b(arrayList, jSONObject2, a3, i4, j2);
                            }
                            if (z3) {
                                i3 += a(arrayList, i3, j2);
                            }
                            aVar = a2;
                        } else {
                            if (a2.useTopDivider(i4, aVar2)) {
                                i3 += a(arrayList, i3, j2);
                            }
                            if (typeModel.A()) {
                                cVar = typeModel;
                                i3 += b(arrayList, jSONObject2, i3, i4, j2);
                            } else {
                                cVar = typeModel;
                            }
                            cVar.z(i3);
                            i3 += cVar.getFloorHeight() + cVar.i();
                            if (a2.isFloorType()) {
                                i4++;
                            }
                            arrayList.add(cVar);
                            aVar = a2;
                            aVar2 = aVar;
                        }
                        z2 = z;
                    }
                    i2++;
                }
                if (!z2 && aVar != null && aVar.c()) {
                    i3 += a(arrayList, i3, j2);
                }
                a.set(i3);
            } catch (Exception e2) {
                com.jingdong.app.mall.home.o.a.f.s0("CaParseUtil", e2);
            }
        }
        return arrayList;
    }
}
