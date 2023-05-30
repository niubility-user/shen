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
import android.view.MotionEvent;
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
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ActionCallBack actionCallBack;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int actionMasked = motionEvent.getActionMasked();
        boolean z = false;
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                if (this.isGoingCancel && this.cancelRegion.contains(x, y)) {
                    ActionCallBack actionCallBack2 = this.actionCallBack;
                    if (actionCallBack2 != null) {
                        actionCallBack2.onCancel();
                    }
                } else if (this.isGoingConfirm && this.confirmRegion.contains(x, y) && (actionCallBack = this.actionCallBack) != null) {
                    actionCallBack.onConfirm();
                }
                onPressState(false);
                this.isGoingConfirm = false;
                this.isGoingCancel = false;
            } else if (actionMasked != 2) {
                if (actionMasked == 3) {
                    this.isGoingConfirm = false;
                    this.isGoingCancel = false;
                    onPressState(false);
                }
            }
            if (z) {
                return true;
            }
            return super.onTouchEvent(motionEvent);
        }
        if (this.cancelRegion.contains(x, y)) {
            this.isGoingCancel = true;
        } else {
            if (this.confirmRegion.contains(x, y)) {
                this.isGoingConfirm = true;
            }
            if (z) {
            }
        }
        onPressState(true);
        z = true;
        if (z) {
        }
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
