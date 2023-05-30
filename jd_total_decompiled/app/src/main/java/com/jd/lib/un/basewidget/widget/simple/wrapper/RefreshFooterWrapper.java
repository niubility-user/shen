package com.jd.lib.un.basewidget.widget.simple.wrapper;

import android.view.View;
import com.jd.lib.un.basewidget.widget.simple.abs.AbsRefreshInternal;
import com.jd.lib.un.basewidget.widget.simple.c.b;
import com.jd.lib.un.basewidget.widget.simple.c.d;

/* loaded from: classes16.dex */
public class RefreshFooterWrapper extends AbsRefreshInternal implements b {
    public RefreshFooterWrapper(View view) {
        super(view);
    }

    @Override // com.jd.lib.un.basewidget.widget.simple.c.b
    public boolean a(boolean z) {
        d dVar = this.f5777i;
        return (dVar instanceof b) && ((b) dVar).a(z);
    }
}
