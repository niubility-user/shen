package com.jingdong.manto.jsapi.refact.rec;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.jingdong.manto.R;
import com.jingdong.manto.utils.MantoDensityUtils;

/* loaded from: classes15.dex */
public class MantoConfirmView extends View {
    private ActionCallBack actionCallBack;
    private Region cancelRegion;
    private int centerX;
    private int centerY;
    private int circleBgColor;
    private Bitmap confirmBitmap;
    private Region confirmRegion;
    private boolean isGoingCancel;
    private boolean isGoingConfirm;
    private boolean isPressed;
    private int leftX;
    private final Paint paint;
    private int pressedColor;
    private int radius;
    private Bitmap refreshBitmap;
    private int rightX;

    /* loaded from: classes15.dex */
    public interface ActionCallBack {
        void onCancel();

        void onConfirm();
    }

    public MantoConfirmView(Context context) {
        this(context, null);
    }

    public MantoConfirmView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MantoConfirmView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.paint = new Paint(1);
        this.circleBgColor = Color.parseColor("#edeef1");
        this.isPressed = false;
        init();
    }

    private void drawCancelCircle(Canvas canvas) {
        int i2 = this.leftX;
        if (this.isPressed && this.isGoingCancel) {
            this.paint.setColor(this.pressedColor);
        }
        canvas.drawCircle(i2, this.centerY, this.radius, this.paint);
        canvas.drawBitmap(this.refreshBitmap, i2 - (r1.getWidth() / 2), this.centerY - (this.refreshBitmap.getHeight() / 2), this.paint);
    }

    private void drawConfirmCircle(Canvas canvas) {
        int i2 = this.rightX;
        if (this.isPressed && this.isGoingConfirm) {
            this.paint.setColor(this.pressedColor);
        }
        canvas.drawCircle(i2, this.centerY, this.radius, this.paint);
        canvas.drawBitmap(this.confirmBitmap, i2 - (r1.getWidth() / 2), this.centerY - (this.confirmBitmap.getHeight() / 2), this.paint);
    }

    private void init() {
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.circleBgColor);
        this.pressedColor = MantoVideoUtil.getDarkColor(getContext(), this.circleBgColor, 0.88f);
        this.refreshBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.manto_video_refresh);
        this.confirmBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.manto_video_ok);
    }

    private void onPressState(boolean z) {
        this.isPressed = z;
        invalidate();
    }

    public void hideMenu() {
        int i2 = this.centerX;
        this.rightX = i2;
        this.leftX = i2;
        invalidate();
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        release();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setColor(this.circleBgColor);
        drawCancelCircle(canvas);
        this.paint.setColor(this.circleBgColor);
        drawConfirmCircle(canvas);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.radius = MantoDensityUtils.dip2pixel(getContext(), 28);
        int width = getWidth() / 2;
        this.centerX = width;
        this.rightX = width;
        this.leftX = width;
        this.centerY = getHeight() / 2;
        this.cancelRegion = new Region();
        Path path = new Path();
        path.addCircle((this.centerX / 2) * 1.0f, this.centerY * 1.0f, this.radius, Path.Direction.CCW);
        Region region = this.cancelRegion;
        int i6 = this.centerX / 2;
        int i7 = this.radius;
        int i8 = this.centerY;
        region.setPath(path, new Region(i6 - i7, i8 - i7, i6 + i7, i8 + i7));
        this.confirmRegion = new Region();
        Path path2 = new Path();
        int i9 = this.centerX;
        int i10 = i9 + (i9 / 2);
        path2.addCircle(i10 * 1.0f, this.centerY * 1.0f, this.radius, Path.Direction.CCW);
        Region region2 = this.confirmRegion;
        int i11 = this.radius;
        int i12 = this.centerY;
        region2.setPath(path2, new Region(i10 - i11, i12 - i11, i10 + i11, i12 + i11));
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            float r0 = r7.getX()
            int r0 = (int) r0
            float r1 = r7.getY()
            int r1 = (int) r1
            int r2 = r7.getActionMasked()
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L52
            if (r2 == r3) goto L23
            r5 = 2
            if (r2 == r5) goto L52
            r0 = 3
            if (r2 == r0) goto L1b
            goto L6c
        L1b:
            r6.isGoingConfirm = r4
            r6.isGoingCancel = r4
            r6.onPressState(r4)
            goto L6c
        L23:
            boolean r2 = r6.isGoingCancel
            if (r2 == 0) goto L37
            android.graphics.Region r2 = r6.cancelRegion
            boolean r2 = r2.contains(r0, r1)
            if (r2 == 0) goto L37
            com.jingdong.manto.jsapi.refact.rec.MantoConfirmView$ActionCallBack r0 = r6.actionCallBack
            if (r0 == 0) goto L4a
            r0.onCancel()
            goto L4a
        L37:
            boolean r2 = r6.isGoingConfirm
            if (r2 == 0) goto L4a
            android.graphics.Region r2 = r6.confirmRegion
            boolean r0 = r2.contains(r0, r1)
            if (r0 == 0) goto L4a
            com.jingdong.manto.jsapi.refact.rec.MantoConfirmView$ActionCallBack r0 = r6.actionCallBack
            if (r0 == 0) goto L4a
            r0.onConfirm()
        L4a:
            r6.onPressState(r4)
            r6.isGoingConfirm = r4
            r6.isGoingCancel = r4
            goto L6c
        L52:
            android.graphics.Region r2 = r6.cancelRegion
            boolean r2 = r2.contains(r0, r1)
            if (r2 == 0) goto L61
            r6.isGoingCancel = r3
        L5c:
            r6.onPressState(r3)
            r4 = 1
            goto L6c
        L61:
            android.graphics.Region r2 = r6.confirmRegion
            boolean r0 = r2.contains(r0, r1)
            if (r0 == 0) goto L6c
            r6.isGoingConfirm = r3
            goto L5c
        L6c:
            if (r4 == 0) goto L6f
            goto L73
        L6f:
            boolean r3 = super.onTouchEvent(r7)
        L73:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.manto.jsapi.refact.rec.MantoConfirmView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void release() {
        Bitmap bitmap = this.refreshBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.refreshBitmap.recycle();
        }
        Bitmap bitmap2 = this.confirmBitmap;
        if (bitmap2 != null && !bitmap2.isRecycled()) {
            this.confirmBitmap.recycle();
        }
        this.confirmBitmap = null;
        this.refreshBitmap = null;
    }

    public void setActionCallBack(ActionCallBack actionCallBack) {
        this.actionCallBack = actionCallBack;
    }

    public void showMenu() {
        if (getVisibility() != 0) {
            setVisibility(0);
        }
        int i2 = this.centerX;
        ValueAnimator duration = ValueAnimator.ofInt(i2, i2 / 2).setDuration(200L);
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.manto.jsapi.refact.rec.MantoConfirmView.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                MantoConfirmView.this.leftX = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                MantoConfirmView.this.invalidate();
            }
        });
        int i3 = this.centerX;
        ValueAnimator duration2 = ValueAnimator.ofInt(i3, i3 + (i3 / 2)).setDuration(200L);
        duration2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.jingdong.manto.jsapi.refact.rec.MantoConfirmView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                MantoConfirmView.this.rightX = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(duration, duration2);
        animatorSet.start();
    }
}
