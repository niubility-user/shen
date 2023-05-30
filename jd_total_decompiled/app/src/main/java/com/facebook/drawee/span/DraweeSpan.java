package com.facebook.drawee.span;

import com.facebook.drawee.view.DraweeHolder;
import com.facebook.widget.text.span.BetterImageSpan;

/* loaded from: classes.dex */
public class DraweeSpan extends BetterImageSpan {
    private final DraweeHolder mDraweeHolder;

    public DraweeSpan(DraweeHolder draweeHolder, int i2) {
        super(draweeHolder.getTopLevelDrawable(), i2);
        this.mDraweeHolder = draweeHolder;
    }

    public DraweeHolder getDraweeHolder() {
        return this.mDraweeHolder;
    }

    public boolean isAttached() {
        return this.mDraweeHolder.isAttached();
    }

    public void onAttach() {
        this.mDraweeHolder.onAttach();
    }

    public void onDetach() {
        this.mDraweeHolder.onDetach();
    }
}
