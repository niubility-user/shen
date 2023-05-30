package com.jd.lib.flexcube.layout.entity.common;

import android.graphics.Rect;
import com.jd.lib.flexcube.iwidget.b.b;
import com.jd.lib.flexcube.widgets.entity.ViewStylePadding;
import com.jd.lib.flexcube.widgets.entity.common.CfInfo;

/* loaded from: classes14.dex */
public class ViewStyle extends CfInfo {
    public BgInfo bgInfo;
    public int bottomPadding;
    public int leftPadding;
    public int rightPadding;
    public int topPadding;

    public ViewStylePadding copyStyle() {
        ViewStylePadding viewStylePadding = new ViewStylePadding();
        viewStylePadding.leftPadding = this.leftPadding;
        viewStylePadding.rightPadding = this.rightPadding;
        viewStylePadding.topPadding = this.topPadding;
        viewStylePadding.bottomPadding = this.bottomPadding;
        return viewStylePadding;
    }

    public Rect getViewPadding(float f2) {
        return new Rect(b.d(this.leftPadding, f2), b.d(this.topPadding, f2), b.d(this.rightPadding, f2), b.d(this.bottomPadding, f2));
    }
}
