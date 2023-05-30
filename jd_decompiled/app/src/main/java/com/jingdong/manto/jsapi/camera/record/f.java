package com.jingdong.manto.jsapi.camera.record;

import androidx.collection.ArrayMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes15.dex */
public class f {
    private final ArrayMap<a, SortedSet<e>> a = new ArrayMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public SortedSet<e> a(a aVar) {
        return this.a.get(aVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.a.clear();
    }

    public boolean a(e eVar) {
        for (a aVar : this.a.keySet()) {
            if (aVar.a(eVar)) {
                SortedSet<e> sortedSet = this.a.get(aVar);
                if (sortedSet.contains(eVar)) {
                    return false;
                }
                sortedSet.add(eVar);
                return true;
            }
        }
        TreeSet treeSet = new TreeSet();
        treeSet.add(eVar);
        this.a.put(a.b(eVar.b(), eVar.a()), treeSet);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<a> b() {
        return this.a.keySet();
    }
}
