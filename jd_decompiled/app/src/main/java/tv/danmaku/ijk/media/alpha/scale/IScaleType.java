package tv.danmaku.ijk.media.alpha.scale;

import android.util.Pair;
import android.widget.FrameLayout;

/* loaded from: classes11.dex */
public interface IScaleType {

    /* loaded from: classes11.dex */
    public enum ScaleType {
        FIT_XY,
        FIT_CENTER,
        CENTER_CROP
    }

    FrameLayout.LayoutParams getLayoutParams(int i2, int i3, int i4, int i5, FrameLayout.LayoutParams layoutParams);

    Pair<Integer, Integer> getRealSize();
}
