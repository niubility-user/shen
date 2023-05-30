package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.Bundle;
import com.tencent.tencentmap.mapsdk.maps.TencentMapComponent;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class n1 implements TencentMapComponent.Component {
    private final List<o1> a = new ArrayList();
    private volatile int b;

    public void a(Context context) {
    }

    public synchronized void a(o1 o1Var) {
        if (o1Var != null) {
            this.a.add(o1Var);
        }
    }

    public synchronized void a(o1 o1Var, Bundle bundle) {
        if (this.a.size() == 0) {
            return;
        }
        this.b = this.a.indexOf(o1Var);
    }

    public synchronized void b(o1 o1Var) {
        if (o1Var != null) {
            this.a.remove(o1Var);
        }
        if (this.a.size() == 0) {
            f();
        } else {
            this.b = this.a.size() - 1;
        }
    }

    public Context e() {
        o1 mapContext = getMapContext();
        if (mapContext != null) {
            return mapContext.getContext();
        }
        return null;
    }

    public synchronized void f() {
        this.a.clear();
        this.b = 0;
    }

    public synchronized o1 getMapContext() {
        if (this.b < 0 || this.b >= this.a.size()) {
            return null;
        }
        return this.a.get(this.b);
    }
}
