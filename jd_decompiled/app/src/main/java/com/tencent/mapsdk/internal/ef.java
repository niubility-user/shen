package com.tencent.mapsdk.internal;

import android.os.Handler;
import android.view.View;
import com.tencent.map.lib.models.AccessibleTouchItem;
import com.tencent.map.lib.models.MapExploreByTouchHelper;
import com.tencent.tencentmap.mapsdk.maps.model.MapPoi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public class ef extends MapExploreByTouchHelper implements k5 {

    /* renamed from: g  reason: collision with root package name */
    private List<AccessibleTouchItem> f16472g;

    /* renamed from: h  reason: collision with root package name */
    private List<AccessibleTouchItem> f16473h;

    /* renamed from: i  reason: collision with root package name */
    private xi f16474i;

    /* renamed from: j  reason: collision with root package name */
    private Handler f16475j;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ef.this.accessibleTouchItems.clear();
            ef.this.f16472g.clear();
            ef.this.f16473h.clear();
            if (ef.this.f16474i != null) {
                List<o0> j0 = ef.this.f16474i.j0();
                List<MapPoi> l0 = ef.this.f16474i.l0();
                o0 o0Var = null;
                if (j0 != null) {
                    for (o0 o0Var2 : j0) {
                        String contentDescription = o0Var2.getContentDescription();
                        if (!e7.b(contentDescription)) {
                            if (contentDescription.startsWith(AccessibleTouchItem.MY_LOCATION_PREFIX)) {
                                o0Var = o0Var2;
                            } else {
                                ef.this.f16472g.add(new ff(ef.this.f16474i, o0Var2));
                            }
                        }
                    }
                    Collections.sort(ef.this.f16472g);
                    ef.this.accessibleTouchItems.addAll(ef.this.f16472g);
                }
                if (l0 != null) {
                    for (MapPoi mapPoi : l0) {
                        if (!e7.b(mapPoi.getName())) {
                            ef.this.f16473h.add(new gf(ef.this.f16474i, mapPoi));
                        }
                    }
                    Collections.sort(ef.this.f16473h);
                    ef.this.accessibleTouchItems.addAll(ef.this.f16473h);
                }
                if (o0Var != null) {
                    ef.this.accessibleTouchItems.add(new ff(ef.this.f16474i, o0Var));
                }
            }
        }
    }

    public ef(View view, xi xiVar) {
        super(view);
        this.f16472g = new ArrayList();
        this.f16473h = new ArrayList();
        this.f16475j = new Handler();
        this.f16474i = xiVar;
        xiVar.getMap().a(this);
    }

    private int a(float f2, float f3) {
        List<AccessibleTouchItem> list = this.accessibleTouchItems;
        if (list == null || list.size() <= 0) {
            return -1;
        }
        int size = this.accessibleTouchItems.size() - 1;
        AccessibleTouchItem accessibleTouchItem = this.accessibleTouchItems.get(size);
        if ((accessibleTouchItem instanceof ff) && accessibleTouchItem.getBounds().contains((int) f2, (int) f3)) {
            return size;
        }
        return -1;
    }

    public void a() {
        this.f16474i.getMap().b(this);
        this.accessibleTouchItems.clear();
        this.f16472g.clear();
        this.f16473h.clear();
    }

    public void a(af afVar) {
        if (afVar == null) {
            return;
        }
        String contentDescription = afVar.getContentDescription();
        if (!e7.b(contentDescription) && afVar.S()) {
            ff ffVar = new ff(this.f16474i, afVar);
            AccessibleTouchItem accessibleTouchItem = null;
            if (this.accessibleTouchItems.size() > 0) {
                AccessibleTouchItem accessibleTouchItem2 = this.accessibleTouchItems.get(r2.size() - 1);
                if (accessibleTouchItem2 != null && (accessibleTouchItem2 instanceof ff)) {
                    accessibleTouchItem = accessibleTouchItem2;
                }
            }
            if (contentDescription.startsWith(AccessibleTouchItem.MY_LOCATION_PREFIX)) {
                this.accessibleTouchItems.add(ffVar);
                return;
            }
            this.accessibleTouchItems.clear();
            this.f16472g.add(ffVar);
            Collections.sort(this.f16472g);
            this.accessibleTouchItems.addAll(this.f16472g);
            this.accessibleTouchItems.addAll(this.f16473h);
            if (accessibleTouchItem != null) {
                this.accessibleTouchItems.add(accessibleTouchItem);
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.k5
    public void b() {
        this.f16475j.post(new a());
    }

    @Override // com.tencent.map.lib.models.MapExploreByTouchHelper
    public int getTargetPoiItemIdx(float f2, float f3) {
        List<AccessibleTouchItem> list = this.accessibleTouchItems;
        if (list != null && list.size() > 0) {
            int a2 = a(f2, f3);
            if (a2 != -1) {
                return a2;
            }
            for (int i2 = 0; i2 < this.accessibleTouchItems.size(); i2++) {
                if (this.accessibleTouchItems.get(i2).getBounds().contains((int) f2, (int) f3)) {
                    return i2;
                }
            }
        }
        return -1;
    }

    @Override // com.tencent.map.lib.models.MapExploreByTouchHelper
    public boolean onItemClicked(int i2) {
        AccessibleTouchItem accessibleTouchItem;
        if (i2 < this.accessibleTouchItems.size() && (accessibleTouchItem = this.accessibleTouchItems.get(i2)) != null) {
            invalidateVirtualView(i2);
            sendEventForVirtualView(i2, 1);
            accessibleTouchItem.onClick();
            return true;
        }
        return false;
    }

    @Override // com.tencent.map.lib.models.MapExploreByTouchHelper
    public void onTalkBackActivate(View view) {
        super.onTalkBackActivate(view);
        this.f16474i.getMap().a(this);
        b();
    }

    @Override // com.tencent.map.lib.models.MapExploreByTouchHelper
    public void onTalkBackDeActivate(View view) {
        super.onTalkBackDeActivate(view);
        this.f16474i.getMap().b(this);
    }
}
