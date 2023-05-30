package tv.danmaku.ijk.media.alpha.scale;

import android.util.Pair;
import android.widget.FrameLayout;

/* loaded from: classes11.dex */
public class ScaleTypeFitCenter implements IScaleType {
    private int realWidth = 0;
    private int realHeight = 0;

    private Pair<Integer, Integer> getFitCenterSize(int i2, int i3, int i4, int i5) {
        float f2 = i2;
        float f3 = i3;
        float f4 = i4 / i5;
        if (f2 / f3 > f4) {
            i2 = (int) (f4 * f3);
        } else {
            i3 = (int) (f2 / f4);
        }
        return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i3));
    }

    @Override // tv.danmaku.ijk.media.alpha.scale.IScaleType
    public FrameLayout.LayoutParams getLayoutParams(int i2, int i3, int i4, int i5, FrameLayout.LayoutParams layoutParams) {
        Pair<Integer, Integer> fitCenterSize = getFitCenterSize(i2, i3, i4, i5);
        if (((Integer) fitCenterSize.first).intValue() > 0 || ((Integer) fitCenterSize.second).intValue() > 0) {
            this.realWidth = ((Integer) fitCenterSize.first).intValue();
            this.realHeight = ((Integer) fitCenterSize.second).intValue();
            layoutParams.width = ((Integer) fitCenterSize.first).intValue();
            layoutParams.height = ((Integer) fitCenterSize.second).intValue();
            layoutParams.gravity = 17;
            return layoutParams;
        }
        return layoutParams;
    }

    @Override // tv.danmaku.ijk.media.alpha.scale.IScaleType
    public Pair<Integer, Integer> getRealSize() {
        return new Pair<>(Integer.valueOf(this.realWidth), Integer.valueOf(this.realHeight));
    }
}
