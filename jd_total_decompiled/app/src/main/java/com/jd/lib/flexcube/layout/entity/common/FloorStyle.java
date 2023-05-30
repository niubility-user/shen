package com.jd.lib.flexcube.layout.entity.common;

import android.graphics.Rect;
import com.jd.lib.flexcube.iwidget.b.b;

/* loaded from: classes14.dex */
public class FloorStyle {
    public int bottomPadding;
    public int cardHPadding;
    public int cardVPadding;
    public int leftPadding;
    public int rightPadding;
    public int topPadding;

    public int getCardHPadding(float f2) {
        return b.d(this.cardHPadding, f2);
    }

    public int getCardVPadding(float f2) {
        return b.d(this.cardVPadding, f2);
    }

    public Rect getFloorPadding(float f2) {
        return new Rect(b.d(this.leftPadding, f2), b.d(this.topPadding, f2), b.d(this.rightPadding, f2), b.d(this.bottomPadding, f2));
    }

    public boolean hasPadding() {
        return this.leftPadding > 0 || this.rightPadding > 0 || this.topPadding > 0 || this.bottomPadding > 0;
    }
}
