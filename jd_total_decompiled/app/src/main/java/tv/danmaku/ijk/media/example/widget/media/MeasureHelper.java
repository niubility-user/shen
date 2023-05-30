package tv.danmaku.ijk.media.example.widget.media;

import android.view.View;
import java.lang.ref.WeakReference;

/* loaded from: classes11.dex */
public final class MeasureHelper {
    private int mCurrentAspectRatio = 0;
    private int mMeasuredHeight;
    private int mMeasuredWidth;
    private int mVideoHeight;
    private int mVideoRotationDegree;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;
    private WeakReference<View> mWeakView;

    public MeasureHelper(View view) {
        this.mWeakView = new WeakReference<>(view);
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ac, code lost:
        if (r4 != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00af, code lost:
        if (r4 != false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b1, code lost:
        r13 = (int) (r0 / r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b5, code lost:
        r12 = (int) (r3 * r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void doMeasure(int i2, int i3) {
        int i4;
        float f2;
        int i5;
        int i6 = this.mVideoRotationDegree;
        if (i6 == 90 || i6 == 270) {
            i3 = i2;
            i2 = i3;
        }
        int defaultSize = View.getDefaultSize(this.mVideoWidth, i2);
        int defaultSize2 = View.getDefaultSize(this.mVideoHeight, i3);
        if (this.mCurrentAspectRatio != 3) {
            if (this.mVideoWidth <= 0 || this.mVideoHeight <= 0) {
                i2 = defaultSize;
                i3 = defaultSize2;
            } else {
                int mode = View.MeasureSpec.getMode(i2);
                i2 = View.MeasureSpec.getSize(i2);
                int mode2 = View.MeasureSpec.getMode(i3);
                i3 = View.MeasureSpec.getSize(i3);
                if (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
                    float f3 = i2;
                    float f4 = i3;
                    float f5 = f3 / f4;
                    int i7 = this.mCurrentAspectRatio;
                    if (i7 == 4) {
                        int i8 = this.mVideoRotationDegree;
                        f2 = (i8 == 90 || i8 == 270) ? 0.5625f : 1.7777778f;
                    } else if (i7 != 5) {
                        f2 = this.mVideoWidth / this.mVideoHeight;
                        int i9 = this.mVideoSarNum;
                        if (i9 > 0 && (i5 = this.mVideoSarDen) > 0) {
                            f2 = (f2 * i9) / i5;
                        }
                    } else {
                        int i10 = this.mVideoRotationDegree;
                        f2 = (i10 == 90 || i10 == 270) ? 0.75f : 1.3333334f;
                    }
                    boolean z = f2 > f5;
                    if (i7 != 0) {
                        if (i7 != 1) {
                            if (i7 != 4 && i7 != 5) {
                                if (z) {
                                    i2 = Math.min(this.mVideoWidth, i2);
                                    i3 = (int) (i2 / f2);
                                } else {
                                    int min = Math.min(this.mVideoHeight, i3);
                                    i3 = min;
                                    i2 = (int) (min * f2);
                                }
                            }
                        }
                    }
                } else if (mode == 1073741824 && mode2 == 1073741824) {
                    int i11 = this.mVideoWidth;
                    int i12 = i11 * i3;
                    int i13 = this.mVideoHeight;
                    if (i12 < i2 * i13) {
                        i2 = (i11 * i3) / i13;
                    } else if (i11 * i3 > i2 * i13) {
                        i3 = (i13 * i2) / i11;
                    }
                } else if (mode == 1073741824) {
                    int i14 = (this.mVideoHeight * i2) / this.mVideoWidth;
                    if (mode2 != Integer.MIN_VALUE || i14 <= i3) {
                        i3 = i14;
                    }
                } else if (mode2 == 1073741824) {
                    int i15 = (this.mVideoWidth * i3) / this.mVideoHeight;
                    if (mode != Integer.MIN_VALUE || i15 <= i2) {
                        i2 = i15;
                    }
                } else {
                    int i16 = this.mVideoWidth;
                    int i17 = this.mVideoHeight;
                    if (mode2 != Integer.MIN_VALUE || i17 <= i3) {
                        i4 = i16;
                        i3 = i17;
                    } else {
                        i4 = (i3 * i16) / i17;
                    }
                    if (mode != Integer.MIN_VALUE || i4 <= i2) {
                        i2 = i4;
                    } else {
                        i3 = (i17 * i2) / i16;
                    }
                }
            }
        }
        this.mMeasuredWidth = i2;
        this.mMeasuredHeight = i3;
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public View getView() {
        WeakReference<View> weakReference = this.mWeakView;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public void setAspectRatio(int i2) {
        this.mCurrentAspectRatio = i2;
    }

    public void setVideoRotation(int i2) {
        this.mVideoRotationDegree = i2;
    }

    public void setVideoSampleAspectRatio(int i2, int i3) {
        this.mVideoSarNum = i2;
        this.mVideoSarDen = i3;
    }

    public void setVideoSize(int i2, int i3) {
        this.mVideoWidth = i2;
        this.mVideoHeight = i3;
    }
}
