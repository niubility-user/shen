package com.jd.lib.flexcube.pool;

import android.view.View;
import java.util.LinkedList;

/* loaded from: classes15.dex */
public class b {
    private LinkedList<View> a = new LinkedList<>();

    public void a(View view) {
        if (view == null || view.getParent() != null || this.a.size() >= 12) {
            return;
        }
        this.a.push(view);
    }

    public void b() {
        this.a.clear();
    }

    public synchronized View c() {
        if (this.a.isEmpty()) {
            return null;
        }
        return this.a.pop();
    }
}
