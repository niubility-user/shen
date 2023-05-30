package tv.danmaku.ijk.media.alpha.scale;

import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;
import tv.danmaku.ijk.media.alpha.scale.IScaleType;

/* loaded from: classes11.dex */
public class ScaleTypeUtil {
    private static final String TAG = "ScaleTypeUtil";
    public int layoutHeight;
    public int layoutWidth;
    private IScaleType scaleTypeImpl;
    public int videoHeight;
    public int videoWidth;
    private IScaleType.ScaleType curScaleType = IScaleType.ScaleType.FIT_XY;
    private final ScaleTypeFitXY scaleTypeFitXY = new ScaleTypeFitXY();
    private final ScaleTypeCenterCrop scaleTypeCenterCrop = new ScaleTypeCenterCrop();
    private final ScaleTypeFitCenter scaleTypeFitCenter = new ScaleTypeFitCenter();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: tv.danmaku.ijk.media.alpha.scale.ScaleTypeUtil$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$tv$danmaku$ijk$media$alpha$scale$IScaleType$ScaleType;

        static {
            int[] iArr = new int[IScaleType.ScaleType.values().length];
            $SwitchMap$tv$danmaku$ijk$media$alpha$scale$IScaleType$ScaleType = iArr;
            try {
                iArr[IScaleType.ScaleType.FIT_CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$tv$danmaku$ijk$media$alpha$scale$IScaleType$ScaleType[IScaleType.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private boolean checkParams() {
        return this.layoutHeight > 0 && this.layoutWidth > 0 && this.videoWidth > 0 && this.videoHeight > 0;
    }

    private IScaleType getCurrentScaleType() {
        IScaleType iScaleType = this.scaleTypeImpl;
        if (iScaleType != null) {
            return iScaleType;
        }
        int i2 = AnonymousClass1.$SwitchMap$tv$danmaku$ijk$media$alpha$scale$IScaleType$ScaleType[this.curScaleType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                return this.scaleTypeFitXY;
            }
            return this.scaleTypeCenterCrop;
        }
        return this.scaleTypeFitCenter;
    }

    public static IScaleType.ScaleType getScaleTypeByPlayerOption(int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                return IScaleType.ScaleType.FIT_XY;
            }
            return IScaleType.ScaleType.CENTER_CROP;
        }
        return IScaleType.ScaleType.FIT_CENTER;
    }

    public FrameLayout.LayoutParams getLayoutParams(View view) {
        FrameLayout.LayoutParams layoutParams;
        if (view.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        } else {
            layoutParams = new FrameLayout.LayoutParams(-1, -1);
        }
        FrameLayout.LayoutParams layoutParams2 = layoutParams;
        return !checkParams() ? layoutParams2 : getCurrentScaleType().getLayoutParams(this.layoutWidth, this.layoutHeight, this.videoWidth, this.videoHeight, layoutParams2);
    }

    public Pair<Integer, Integer> getRealSize() {
        return getCurrentScaleType().getRealSize();
    }

    public void setScaleType(IScaleType iScaleType) {
        this.scaleTypeImpl = iScaleType;
    }

    public void setScaleType(IScaleType.ScaleType scaleType) {
        this.curScaleType = scaleType;
    }
}
