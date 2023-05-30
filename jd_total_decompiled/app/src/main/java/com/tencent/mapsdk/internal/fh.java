package com.tencent.mapsdk.internal;

import androidx.annotation.NonNull;
import com.tencent.mapsdk.core.components.protocol.jce.trafficevent.Basic;
import com.tencent.mapsdk.core.components.protocol.jce.trafficevent.Detail;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class fh {
    private Map<String, a> a = new ConcurrentHashMap();
    private final j1 b;

    /* loaded from: classes9.dex */
    public static class a {
        public final od a;
        public final Detail b;

        public a(od odVar, Detail detail) {
            this.a = odVar;
            this.b = detail;
        }
    }

    public fh(j1 j1Var) {
        this.b = j1Var;
    }

    public void a() {
        Map<String, a> map = this.a;
        if (map == null) {
            return;
        }
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            a aVar = this.a.get(it.next());
            if (aVar != null) {
                aVar.a.remove();
            }
        }
        this.a.clear();
    }

    public void a(@NonNull List<Detail> list) {
        if (list.isEmpty()) {
            return;
        }
        for (Detail detail : list) {
            a aVar = this.a.get(detail.basic.eventid);
            if (aVar != null) {
                aVar.a.remove();
                this.a.remove(detail.basic.eventid);
            }
        }
    }

    public void b() {
        a();
        this.a = null;
    }

    public void b(@NonNull List<Detail> list) {
        int i2;
        if (this.b == null || list.isEmpty()) {
            return;
        }
        for (Detail detail : list) {
            String str = detail.basic.icon_normal;
            int lastIndexOf = str.lastIndexOf("/");
            if (lastIndexOf != -1 && (i2 = lastIndexOf + 1) <= str.length()) {
                String substring = str.substring(i2);
                ma.f(la.f16826m, "type:" + detail.basic.type + ", coord:" + detail.basic.coord_lat + ", " + detail.basic.coord_lon + ", minScale:" + detail.basic.min_scale + ", maxScale:" + detail.basic.max_scale);
                a aVar = this.a.get(detail.basic.eventid);
                if (aVar == null) {
                    Basic basic = detail.basic;
                    qd qdVar = new qd(basic.coord_lat, basic.coord_lon, substring);
                    Basic basic2 = detail.basic;
                    qdVar.anchor(basic2.anchor_x, basic2.anchor_y);
                    qdVar.minScaleLevel(detail.basic.min_scale);
                    qdVar.maxScaleLevel(detail.basic.max_scale);
                    qdVar.avoidAnnotation(true);
                    qdVar.avoidOtherMarker(true);
                    this.a.put(detail.basic.eventid, new a((od) this.b.a((j1) qdVar), detail));
                } else {
                    qd f2 = aVar.a.f();
                    Basic basic3 = detail.basic;
                    f2.position(basic3.coord_lat, basic3.coord_lon);
                    f2.iconName(substring);
                    Basic basic4 = detail.basic;
                    f2.anchor(basic4.anchor_x, basic4.anchor_y);
                    f2.minScaleLevel(detail.basic.min_scale);
                    f2.maxScaleLevel(detail.basic.max_scale);
                    aVar.a.a((od) f2);
                }
            }
        }
    }

    public Map<String, a> c() {
        return this.a;
    }
}
