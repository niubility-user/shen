package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.engine.jni.models.TappedElement;
import com.tencent.mapsdk.internal.Cif;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.AoiLayer;
import com.tencent.tencentmap.mapsdk.maps.model.AoiLayerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.SubPoi;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class jf implements b5, AoiLayer.OnAoiLayerLoadListener {

    /* renamed from: e */
    private final qc f16741e;

    /* renamed from: f */
    private TencentMap.OnMapPoiClickListener f16742f;
    private final List<hf> d = new CopyOnWriteArrayList();

    /* renamed from: g */
    private final Map<String, AoiLayer.OnAoiLayerLoadListener> f16743g = new HashMap();

    public jf(qc qcVar) {
        this.f16741e = qcVar;
    }

    public o1 a() {
        return this.f16741e;
    }

    public AoiLayer a(String str, AoiLayerOptions aoiLayerOptions, AoiLayer.OnAoiLayerLoadListener onAoiLayerLoadListener) {
        hf hfVar;
        Iterator<hf> it = this.d.iterator();
        while (true) {
            if (!it.hasNext()) {
                hfVar = null;
                break;
            }
            hfVar = it.next();
            if (hfVar.getId() != null && hfVar.getId().equals(str)) {
                break;
            }
        }
        if (hfVar == null) {
            hfVar = new hf(this, str, aoiLayerOptions, this);
            this.d.add(hfVar);
            if (onAoiLayerLoadListener != null) {
                this.f16743g.put(str, onAoiLayerLoadListener);
            }
        }
        hfVar.a(aoiLayerOptions);
        return hfVar;
    }

    public void a(hf hfVar) {
        this.d.remove(hfVar);
    }

    @Override // com.tencent.mapsdk.internal.b5
    public void a(u5 u5Var) {
    }

    public void a(TencentMap.OnMapPoiClickListener onMapPoiClickListener) {
        this.f16742f = onMapPoiClickListener;
    }

    public boolean a(TappedElement tappedElement) {
        if (this.f16742f != null && tappedElement != null && tappedElement.type == 4) {
            long j2 = tappedElement.itemId;
            SubPoi subPoi = null;
            Iterator<hf> it = this.d.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                hf next = it.next();
                Cif.d a = next.a(j2);
                if (a != null) {
                    subPoi = next.a(next.getId(), a);
                    break;
                }
            }
            if (subPoi != null) {
                this.f16742f.onClicked(subPoi);
                return true;
            }
        }
        return false;
    }

    public qc b() {
        return this.f16741e;
    }

    public void c() {
        this.f16742f = null;
        this.d.clear();
        this.f16743g.clear();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.AoiLayer.OnAoiLayerLoadListener
    public void onAoiLayerLoaded(boolean z, AoiLayer aoiLayer) {
        if (aoiLayer == null || this.f16741e == null) {
            return;
        }
        AoiLayer.OnAoiLayerLoadListener onAoiLayerLoadListener = this.f16743g.get(aoiLayer.getId());
        if (onAoiLayerLoadListener != null) {
            onAoiLayerLoadListener.onAoiLayerLoaded(z, aoiLayer);
        }
        if (z) {
            this.f16741e.w().b().b();
        }
    }
}
