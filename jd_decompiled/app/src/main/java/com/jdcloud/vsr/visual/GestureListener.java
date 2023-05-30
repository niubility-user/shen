package com.jdcloud.vsr.visual;

import com.jdcloud.vsr.geometry.AffineMapping;
import com.jdcloud.vsr.visual.Animator;

/* loaded from: classes18.dex */
public class GestureListener {
    private static final int HOLD_TIME_MILLISEC = 500;
    private static final int TAP_TIME_MILLISEC = 200;
    private long mainTouchTime;
    private float mainX;
    private float mainY;
    private int pointerCount;
    private float squaredPointerTolerance;
    private AffineMapping cumulatedGesture = new AffineMapping();
    private AffineMapping lastMovement = new AffineMapping();
    private AffineMapping currentGesture = new AffineMapping();
    private boolean tapping = false;
    private float minGestureScale = 0.0f;
    private float maxGestureScale = Float.POSITIVE_INFINITY;
    private Animator animator = null;
    private int holdEventTicket = Animator.INVALID_TICKET;
    private Animator.Event holdEvent = new Animator.Event() { // from class: com.jdcloud.vsr.visual.GestureListener.1
        @Override // com.jdcloud.vsr.visual.Animator.Event
        public long run() {
            if (GestureListener.this.tapping) {
                GestureListener gestureListener = GestureListener.this;
                gestureListener.onHold(gestureListener.mainX, GestureListener.this.mainY);
                return -1L;
            }
            return -1L;
        }
    };

    public GestureListener(float f2) {
        this.squaredPointerTolerance = f2 * f2;
    }

    private void cancelPlannedEvents() {
        Animator animator = this.animator;
        if (animator != null) {
            animator.cancel(this.holdEvent, this.holdEventTicket);
        }
    }

    public final void cancelGesture() {
        if (this.pointerCount > 0) {
            onCancel();
        }
        this.pointerCount = 0;
        this.tapping = false;
    }

    public void cumulateGesture() {
        this.cumulatedGesture.assign(this.currentGesture);
        this.lastMovement.setIdentity();
    }

    public void enableHoldEvent(Animator animator) {
        this.animator = animator;
    }

    public void gestureUpdated(float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        resolveGesture(this.lastMovement, f2, f3, f4, f5, f6, f7, f8, f9);
        if (this.tapping) {
            float abs = Math.abs(this.mainX - f6);
            float abs2 = Math.abs(this.mainY - f7);
            if ((abs * abs) + (abs2 * abs2) > this.squaredPointerTolerance) {
                this.tapping = false;
                cancelPlannedEvents();
            }
        }
        AffineMapping.compose(this.currentGesture, this.lastMovement, this.cumulatedGesture);
        if (onGesture(this.currentGesture)) {
            return;
        }
        AffineMapping.compose(this.lastMovement, this.currentGesture, this.cumulatedGesture.getInverse());
    }

    public void onCancel() {
    }

    public void onFirstTouch(float f2, float f3) {
    }

    public boolean onGesture(AffineMapping affineMapping) {
        return true;
    }

    public void onHold(float f2, float f3) {
    }

    public void onPointerDown(int i2, float f2, float f3) {
    }

    public void onRelease(AffineMapping affineMapping) {
    }

    public void onTap(float f2, float f3) {
    }

    public final void pointerDown(float f2, float f3) {
        cancelPlannedEvents();
        if (this.pointerCount == 0) {
            this.mainTouchTime = System.currentTimeMillis();
            onFirstTouch(f2, f3);
            this.mainX = f2;
            this.mainY = f3;
            this.tapping = true;
            Animator animator = this.animator;
            if (animator != null) {
                this.holdEventTicket = animator.planify(this.holdEvent, 500);
            }
        } else {
            this.tapping = false;
        }
        onPointerDown(this.pointerCount, f2, f3);
        this.pointerCount++;
    }

    public final void pointerUp() {
        cancelPlannedEvents();
        if (this.pointerCount == 1 && System.currentTimeMillis() < this.mainTouchTime + 200 && this.tapping) {
            onTap(this.mainX, this.mainY);
        } else if (this.pointerCount == 1) {
            onRelease(this.currentGesture);
        }
        this.pointerCount = Math.max(this.pointerCount - 1, 0);
    }

    public void reset() {
        this.pointerCount = 0;
        this.tapping = false;
        cancelPlannedEvents();
    }

    protected void resolveGesture(AffineMapping affineMapping, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9) {
        if (!Float.isNaN(f4) && !Float.isNaN(f5)) {
            float sqrt = (float) Math.sqrt(this.cumulatedGesture.det());
            affineMapping.resolve(f2, f3, f4, f5, f6, f7, f8, f9, this.minGestureScale / sqrt, this.maxGestureScale / sqrt);
            return;
        }
        affineMapping.setTranslation(f6 - f2, f7 - f3);
    }

    public void setGesture(AffineMapping affineMapping, float f2, float f3) {
        if (affineMapping == null) {
            this.currentGesture.setIdentity();
        } else {
            this.currentGesture.assign(affineMapping);
        }
        this.minGestureScale = f2;
        this.maxGestureScale = f3;
        cumulateGesture();
    }
}
