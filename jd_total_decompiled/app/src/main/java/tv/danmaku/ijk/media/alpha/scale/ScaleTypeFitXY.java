package tv.danmaku.ijk.media.alpha.scale;

import android.util.Pair;
import android.widget.FrameLayout;

/* loaded from: classes11.dex */
public class ScaleTypeFitXY implements IScaleType {
    private int realWidth = 0;
    private int realHeight = 0;

    @Override // tv.danmaku.ijk.media.alpha.scale.IScaleType
    public FrameLayout.LayoutParams getLayoutParams(int i2, int i3, int i4, int i5, FrameLayout.LayoutParams layoutParams) {
        layoutParams.width = -1;
        layoutParams.height = -1;
        this.realWidth = i2;
        this.realHeight = i3;
        return layoutParams;
    }

    @Override // tv.danmaku.ijk.media.alpha.scale.IScaleType
    public Pair<Integer, Integer> getRealSize() {
        return new Pair<>(Integer.valueOf(this.realWidth), Integer.valueOf(this.realHeight));
    }
}
