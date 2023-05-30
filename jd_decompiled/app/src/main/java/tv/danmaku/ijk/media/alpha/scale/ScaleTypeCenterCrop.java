package tv.danmaku.ijk.media.alpha.scale;

import android.util.Pair;
import android.widget.FrameLayout;

/* loaded from: classes11.dex */
public class ScaleTypeCenterCrop implements IScaleType {
    private static final String TAG = "ScaleTypeCenterCrop";
    private int realWidth = 0;
    private int realHeight = 0;

    private Pair<Integer, Integer> getCenterCropSize(int i2, int i3, int i4, int i5) {
        String str = "layoutWidth = " + i2 + ", layoutHeight = " + i3 + ", videoWidth = " + i4 + ", videoHeight = " + i5;
        float f2 = i2 * 1.0f;
        float f3 = i3;
        float f4 = (i4 * 1.0f) / i5;
        if (f2 / f3 > f4) {
            i3 = (int) (f2 / f4);
        } else {
            i2 = (int) (f4 * f3);
        }
        return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i3));
    }

    @Override // tv.danmaku.ijk.media.alpha.scale.IScaleType
    public FrameLayout.LayoutParams getLayoutParams(int i2, int i3, int i4, int i5, FrameLayout.LayoutParams layoutParams) {
        Pair<Integer, Integer> centerCropSize = getCenterCropSize(i2, i3, i4, i5);
        if (((Integer) centerCropSize.first).intValue() > 0 || ((Integer) centerCropSize.second).intValue() > 0) {
            this.realWidth = ((Integer) centerCropSize.first).intValue();
            this.realHeight = ((Integer) centerCropSize.second).intValue();
            layoutParams.width = ((Integer) centerCropSize.first).intValue();
            layoutParams.height = ((Integer) centerCropSize.second).intValue();
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
