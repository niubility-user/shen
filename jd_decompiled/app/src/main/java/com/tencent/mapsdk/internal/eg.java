package com.tencent.mapsdk.internal;

import android.content.Context;
import android.widget.ListView;

/* loaded from: classes9.dex */
public class eg extends ListView {

    /* renamed from: g  reason: collision with root package name */
    private a f16476g;

    /* loaded from: classes9.dex */
    public interface a {
        void b();
    }

    public eg(Context context) {
        super(context);
    }

    @Override // android.widget.AbsListView
    public void handleDataChanged() {
        super.handleDataChanged();
        a aVar = this.f16476g;
        if (aVar != null) {
            aVar.b();
        }
    }

    public void setOnDataChangedListener(a aVar) {
        this.f16476g = aVar;
    }
}
