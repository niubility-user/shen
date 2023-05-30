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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void doMeasure(int r12, int r13) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.danmaku.ijk.media.example.widget.media.MeasureHelper.doMeasure(int, int):void");
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
