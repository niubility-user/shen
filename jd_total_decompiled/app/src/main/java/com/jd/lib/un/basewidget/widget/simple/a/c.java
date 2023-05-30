package com.jd.lib.un.basewidget.widget.simple.a;

/* loaded from: classes16.dex */
public enum c {
    None(0, false, false, false),
    PullDownToRefresh(1, true, false, false),
    PullUpToLoad(2, true, false, false),
    PullDownCanceled(1, false, false, false),
    PullUpCanceled(2, false, false, false),
    ReleaseToRefresh(1, true, false, false),
    ReleaseToLoad(2, true, false, false),
    RefreshReleased(1, false, false, false),
    LoadReleased(2, false, false, false),
    Refreshing(1, false, true, false),
    Loading(2, false, true, false),
    RefreshFinish(1, false, false, true),
    LoadFinish(2, false, false, true);
    
    public final boolean isDragging;
    public final boolean isFinishing;
    public final boolean isFooter;
    public final boolean isHeader;
    public final boolean isOpening;

    c(int i2, boolean z, boolean z2, boolean z3) {
        this.isHeader = i2 == 1;
        this.isFooter = i2 == 2;
        this.isDragging = z;
        this.isOpening = z2;
        this.isFinishing = z3;
    }

    public c toFooter() {
        return this.isHeader ? values()[ordinal() + 1] : this;
    }

    public c toHeader() {
        if (this.isFooter) {
            return values()[ordinal() - 1];
        }
        return null;
    }
}
