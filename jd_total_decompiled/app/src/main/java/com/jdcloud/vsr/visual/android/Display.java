package com.jdcloud.vsr.visual.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.jdcloud.vsr.rendering.SceneRenderer;
import com.jdcloud.vsr.visual.GestureListener;

/* loaded from: classes18.dex */
public class Display extends BaseVSRTextureView {
    private GestureListener gestureListener;
    private TouchListener touchListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jdcloud.vsr.visual.android.Display$1  reason: invalid class name */
    /* loaded from: classes18.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$jdcloud$vsr$rendering$SceneRenderer$OutputMapping;

        static {
            int[] iArr = new int[SceneRenderer.OutputMapping.values().length];
            $SwitchMap$com$jdcloud$vsr$rendering$SceneRenderer$OutputMapping = iArr;
            try {
                iArr[SceneRenderer.OutputMapping.FIT_WIDTH_TO_TOP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$jdcloud$vsr$rendering$SceneRenderer$OutputMapping[SceneRenderer.OutputMapping.FIT_WIDTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$jdcloud$vsr$rendering$SceneRenderer$OutputMapping[SceneRenderer.OutputMapping.FIT_HEIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes18.dex */
    private class TouchListener implements View.OnTouchListener {
        static final int INVALID_POINTER_ID = -1;
        boolean userActionsEnabled = true;
        PointF mainStartPos = new PointF();
        PointF mainLastPos = new PointF();
        PointF secondStartPos = new PointF();
        PointF secondLastPos = new PointF();
        int secondPtrId = -1;
        int mainPtrId = -1;
        float scaleY = 1.0f;
        float scaleX = 1.0f;
        int shiftY = 0;
        int shiftX = 0;

        public TouchListener() {
        }

        private void updateGestureMapping() {
            SceneRenderer renderer = Display.this.getRenderer();
            if (renderer != null) {
                int i2 = AnonymousClass1.$SwitchMap$com$jdcloud$vsr$rendering$SceneRenderer$OutputMapping[renderer.getOutputMapping().ordinal()];
                if (i2 == 1) {
                    float width = 1.0f / Display.this.getWidth();
                    this.scaleY = width;
                    this.scaleX = width;
                    this.shiftY = 0;
                    this.shiftX = 0;
                    return;
                } else if (i2 == 2) {
                    float width2 = 1.0f / Display.this.getWidth();
                    this.scaleY = width2;
                    this.scaleX = width2;
                    this.shiftX = 0;
                    this.shiftY = (Display.this.getWidth() - Display.this.getHeight()) / 2;
                    return;
                } else if (i2 == 3) {
                    float height = 1.0f / Display.this.getHeight();
                    this.scaleY = height;
                    this.scaleX = height;
                    this.shiftX = (Display.this.getHeight() - Display.this.getWidth()) / 2;
                    this.shiftY = 0;
                    return;
                }
            }
            float width3 = 1.0f / Display.this.getWidth();
            this.scaleY = width3;
            this.scaleX = width3;
            this.shiftY = 0;
            this.shiftX = 0;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int i2;
            if (this.userActionsEnabled && Display.this.gestureListener != null) {
                int actionMasked = motionEvent.getActionMasked();
                if (actionMasked == 0) {
                    updateGestureMapping();
                    int actionIndex = motionEvent.getActionIndex();
                    if (actionIndex >= 0) {
                        this.mainStartPos.set(this.scaleX * (motionEvent.getX(actionIndex) + this.shiftX), this.scaleY * (motionEvent.getY(actionIndex) + this.shiftY));
                        PointF pointF = this.mainLastPos;
                        PointF pointF2 = this.mainStartPos;
                        pointF.set(pointF2.x, pointF2.y);
                        this.mainPtrId = motionEvent.getPointerId(actionIndex);
                        GestureListener gestureListener = Display.this.gestureListener;
                        PointF pointF3 = this.mainStartPos;
                        gestureListener.pointerDown(pointF3.x, pointF3.y);
                    }
                } else if (actionMasked == 1) {
                    this.mainPtrId = -1;
                    this.secondPtrId = -1;
                    Display.this.gestureListener.pointerUp();
                    Display.this.gestureListener.cancelGesture();
                } else if (actionMasked == 2) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.mainPtrId);
                    if (findPointerIndex < 0) {
                        this.mainPtrId = -1;
                    } else {
                        this.mainLastPos.set(this.scaleX * (motionEvent.getX(findPointerIndex) + this.shiftX), this.scaleY * (motionEvent.getY(findPointerIndex) + this.shiftY));
                        int i3 = this.secondPtrId;
                        if (i3 == -1) {
                            GestureListener gestureListener2 = Display.this.gestureListener;
                            PointF pointF4 = this.mainStartPos;
                            float f2 = pointF4.x;
                            float f3 = pointF4.y;
                            PointF pointF5 = this.mainLastPos;
                            gestureListener2.gestureUpdated(f2, f3, Float.NaN, Float.NaN, pointF5.x, pointF5.y, Float.NaN, Float.NaN);
                        } else {
                            int findPointerIndex2 = motionEvent.findPointerIndex(i3);
                            if (findPointerIndex2 >= 0) {
                                this.secondLastPos.set(this.scaleX * (motionEvent.getX(findPointerIndex2) + this.shiftX), this.scaleY * (motionEvent.getY(findPointerIndex2) + this.shiftY));
                                GestureListener gestureListener3 = Display.this.gestureListener;
                                PointF pointF6 = this.mainStartPos;
                                float f4 = pointF6.x;
                                float f5 = pointF6.y;
                                PointF pointF7 = this.secondStartPos;
                                float f6 = pointF7.x;
                                float f7 = pointF7.y;
                                PointF pointF8 = this.mainLastPos;
                                float f8 = pointF8.x;
                                float f9 = pointF8.y;
                                PointF pointF9 = this.secondLastPos;
                                gestureListener3.gestureUpdated(f4, f5, f6, f7, f8, f9, pointF9.x, pointF9.y);
                            }
                        }
                    }
                } else if (actionMasked == 3) {
                    this.mainPtrId = -1;
                    this.secondPtrId = -1;
                    Display.this.gestureListener.cancelGesture();
                } else if (actionMasked == 5) {
                    int actionIndex2 = motionEvent.getActionIndex();
                    if (actionIndex2 >= 0) {
                        float x = this.scaleX * (motionEvent.getX(actionIndex2) + this.shiftX);
                        float y = this.scaleY * (motionEvent.getY(actionIndex2) + this.shiftY);
                        if (this.secondPtrId == -1) {
                            this.secondPtrId = motionEvent.getPointerId(actionIndex2);
                            this.secondStartPos.set(x, y);
                            PointF pointF10 = this.secondLastPos;
                            PointF pointF11 = this.secondStartPos;
                            pointF10.set(pointF11.x, pointF11.y);
                            PointF pointF12 = this.mainStartPos;
                            PointF pointF13 = this.mainLastPos;
                            pointF12.set(pointF13.x, pointF13.y);
                            Display.this.gestureListener.cumulateGesture();
                        }
                        Display.this.gestureListener.pointerDown(x, y);
                    }
                } else if (actionMasked == 6) {
                    if (motionEvent.getPointerId(motionEvent.getActionIndex()) == this.mainPtrId && (i2 = this.secondPtrId) != -1) {
                        this.mainPtrId = i2;
                        PointF pointF14 = this.mainLastPos;
                        PointF pointF15 = this.secondLastPos;
                        pointF14.set(pointF15.x, pointF15.y);
                    }
                    PointF pointF16 = this.mainStartPos;
                    PointF pointF17 = this.mainLastPos;
                    pointF16.set(pointF17.x, pointF17.y);
                    this.secondPtrId = -1;
                    Display.this.gestureListener.pointerUp();
                    Display.this.gestureListener.cumulateGesture();
                }
            }
            return true;
        }
    }

    public Display(Context context) {
        super(context);
        TouchListener touchListener = new TouchListener();
        this.touchListener = touchListener;
        setOnTouchListener(touchListener);
    }

    public GestureListener getGestureListener() {
        return this.gestureListener;
    }

    public boolean getUIActionsEnabled() {
        return this.touchListener.userActionsEnabled;
    }

    public void setGestureListener(GestureListener gestureListener) {
        this.gestureListener = gestureListener;
    }

    public void setUIActionsEnabled(boolean z) {
        this.touchListener.userActionsEnabled = z;
    }

    public Display(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TouchListener touchListener = new TouchListener();
        this.touchListener = touchListener;
        setOnTouchListener(touchListener);
    }

    public Display(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @TargetApi(21)
    public Display(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
