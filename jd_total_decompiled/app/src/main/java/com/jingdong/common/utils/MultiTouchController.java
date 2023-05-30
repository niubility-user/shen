package com.jingdong.common.utils;

import android.view.MotionEvent;
import com.jingdong.sdk.oklog.OKLog;
import java.lang.reflect.Method;

/* loaded from: classes6.dex */
public class MultiTouchController<T> {
    private static int ACTION_POINTER_INDEX_SHIFT = 8;
    private static int ACTION_POINTER_UP = 6;
    public static final boolean DEBUG = false;
    private static final long EVENT_SETTLE_TIME_INTERVAL = 20;
    private static final float MAX_MULTITOUCH_DIM_JUMP_SIZE = 40.0f;
    private static final float MAX_MULTITOUCH_POS_JUMP_SIZE = 30.0f;
    public static final int MAX_TOUCH_POINTS = 20;
    private static final float MIN_MULTITOUCH_SEPARATION = 30.0f;
    private static final int MODE_DRAG = 1;
    private static final int MODE_NOTHING = 0;
    private static final int MODE_PINCH = 2;
    private static Method m_getHistoricalPressure;
    private static Method m_getHistoricalX;
    private static Method m_getHistoricalY;
    private static Method m_getPointerCount;
    private static Method m_getPointerId;
    private static Method m_getPressure;
    private static Method m_getX;
    private static Method m_getY;
    public static final boolean multiTouchSupported;
    private static final int[] pointerIds;
    private static final float[] pressureVals;
    private static final float[] xVals;
    private static final float[] yVals;
    private boolean handleSingleTouchEvents;
    private PointInfo mCurrPt;
    private float mCurrPtAng;
    private float mCurrPtDiam;
    private float mCurrPtHeight;
    private float mCurrPtWidth;
    private float mCurrPtX;
    private float mCurrPtY;
    private PositionAndScale mCurrXform;
    private int mMode;
    private PointInfo mPrevPt;
    private long mSettleEndTime;
    private long mSettleStartTime;
    MultiTouchObjectCanvas<T> objectCanvas;
    private T selectedObject;
    private float startPosX;
    private float startPosY;
    private float startScaleOverPinchDiam;
    private float startScaleXOverPinchWidth;
    private float startScaleYOverPinchHeight;

    /* loaded from: classes6.dex */
    public interface MultiTouchObjectCanvas<T> {
        T getDraggableObjectAtPoint(PointInfo pointInfo);

        void getPositionAndScale(T t, PositionAndScale positionAndScale);

        boolean onScale(float f2, float f3, float f4);

        boolean onScaleBegin(float f2, float f3);

        void onScaleEnd();
    }

    static {
        boolean z = true;
        try {
            m_getPointerCount = MotionEvent.class.getMethod("getPointerCount", new Class[0]);
            Class cls = Integer.TYPE;
            m_getPointerId = MotionEvent.class.getMethod("getPointerId", cls);
            m_getPressure = MotionEvent.class.getMethod("getPressure", cls);
            m_getHistoricalX = MotionEvent.class.getMethod("getHistoricalX", cls, cls);
            m_getHistoricalY = MotionEvent.class.getMethod("getHistoricalY", cls, cls);
            m_getHistoricalPressure = MotionEvent.class.getMethod("getHistoricalPressure", cls, cls);
            m_getX = MotionEvent.class.getMethod("getX", cls);
            m_getY = MotionEvent.class.getMethod("getY", cls);
        } catch (Exception e2) {
            OKLog.e("MultiTouchController", "static initializer failed", e2);
            z = false;
        }
        multiTouchSupported = z;
        if (z) {
            try {
                ACTION_POINTER_UP = MotionEvent.class.getField("ACTION_POINTER_UP").getInt(null);
                ACTION_POINTER_INDEX_SHIFT = MotionEvent.class.getField("ACTION_POINTER_INDEX_SHIFT").getInt(null);
            } catch (Exception unused) {
            }
        }
        xVals = new float[20];
        yVals = new float[20];
        pressureVals = new float[20];
        pointerIds = new int[20];
    }

    public MultiTouchController(MultiTouchObjectCanvas<T> multiTouchObjectCanvas) {
        this(multiTouchObjectCanvas, true);
    }

    private void anchorAtThisPositionAndScale() {
        T t = this.selectedObject;
        if (t == null) {
            return;
        }
        this.objectCanvas.getPositionAndScale(t, this.mCurrXform);
        float f2 = 1.0f / ((this.mCurrXform.updateScale && this.mCurrXform.scale != 0.0f) ? this.mCurrXform.scale : 1.0f);
        extractCurrPtInfo();
        this.startPosX = (this.mCurrPtX - this.mCurrXform.xOff) * f2;
        this.startPosY = (this.mCurrPtY - this.mCurrXform.yOff) * f2;
        this.startScaleOverPinchDiam = this.mCurrXform.scale / this.mCurrPtDiam;
        this.startScaleXOverPinchWidth = this.mCurrXform.scaleX / this.mCurrPtWidth;
        this.startScaleYOverPinchHeight = this.mCurrXform.scaleY / this.mCurrPtHeight;
    }

    private void decodeTouchEvent(int i2, float[] fArr, float[] fArr2, float[] fArr3, int[] iArr, int i3, boolean z, long j2) {
        PointInfo pointInfo = this.mPrevPt;
        this.mPrevPt = this.mCurrPt;
        this.mCurrPt = pointInfo;
        pointInfo.set(i2, fArr, fArr2, fArr3, iArr, i3, z, j2);
        multiTouchController();
    }

    private void extractCurrPtInfo() {
        this.mCurrPtX = this.mCurrPt.getX();
        this.mCurrPtY = this.mCurrPt.getY();
        this.mCurrPtDiam = Math.max(21.3f, !this.mCurrXform.updateScale ? 0.0f : this.mCurrPt.getMultiTouchDiameter());
        this.mCurrPtWidth = Math.max(30.0f, !this.mCurrXform.updateScaleXY ? 0.0f : this.mCurrPt.getMultiTouchWidth());
        this.mCurrPtHeight = Math.max(30.0f, !this.mCurrXform.updateScaleXY ? 0.0f : this.mCurrPt.getMultiTouchHeight());
        this.mCurrPtAng = this.mCurrXform.updateAngle ? this.mCurrPt.getMultiTouchAngle() : 0.0f;
    }

    private void multiTouchController() {
        int i2 = this.mMode;
        if (i2 == 0) {
            if (this.mCurrPt.isDown()) {
                T draggableObjectAtPoint = this.objectCanvas.getDraggableObjectAtPoint(this.mCurrPt);
                this.selectedObject = draggableObjectAtPoint;
                if (draggableObjectAtPoint != null) {
                    this.mMode = 1;
                    anchorAtThisPositionAndScale();
                    long eventTime = this.mCurrPt.getEventTime();
                    this.mSettleEndTime = eventTime;
                    this.mSettleStartTime = eventTime;
                }
            }
        } else if (i2 == 1) {
            if (!this.mCurrPt.isDown()) {
                this.mMode = 0;
            } else if (this.mCurrPt.isMultiTouch()) {
                this.mMode = 2;
                anchorAtThisPositionAndScale();
                long eventTime2 = this.mCurrPt.getEventTime();
                this.mSettleStartTime = eventTime2;
                this.mSettleEndTime = eventTime2 + 20;
                this.objectCanvas.onScaleBegin(this.mCurrPt.getX(), this.mCurrPt.getY());
            } else if (this.mCurrPt.getEventTime() < this.mSettleEndTime) {
                anchorAtThisPositionAndScale();
            } else {
                performDragOrPinch();
            }
        } else if (i2 != 2) {
        } else {
            if (this.mCurrPt.isMultiTouch() && this.mCurrPt.isDown()) {
                if (Math.abs(this.mCurrPt.getX() - this.mPrevPt.getX()) <= 30.0f && Math.abs(this.mCurrPt.getY() - this.mPrevPt.getY()) <= 30.0f && Math.abs(this.mCurrPt.getMultiTouchWidth() - this.mPrevPt.getMultiTouchWidth()) * 0.5f <= 40.0f && Math.abs(this.mCurrPt.getMultiTouchHeight() - this.mPrevPt.getMultiTouchHeight()) * 0.5f <= 40.0f) {
                    if (this.mCurrPt.eventTime < this.mSettleEndTime) {
                        anchorAtThisPositionAndScale();
                        return;
                    }
                    performDragOrPinch();
                    if (this.selectedObject != null) {
                        float f2 = 1.0f;
                        if (this.mCurrXform.updateScale && this.mCurrXform.scale != 0.0f) {
                            f2 = this.mCurrXform.scale;
                        }
                        extractCurrPtInfo();
                        this.objectCanvas.onScale(this.mCurrPtX - (this.startPosX * f2), this.mCurrPtY - (this.startPosY * f2), this.startScaleOverPinchDiam * this.mCurrPtDiam);
                        return;
                    }
                    return;
                }
                anchorAtThisPositionAndScale();
                long eventTime3 = this.mCurrPt.getEventTime();
                this.mSettleStartTime = eventTime3;
                this.mSettleEndTime = eventTime3 + 20;
            } else if (!this.mCurrPt.isDown()) {
                this.mMode = 0;
                this.objectCanvas.onScaleEnd();
            } else {
                this.mMode = 1;
                anchorAtThisPositionAndScale();
                long eventTime4 = this.mCurrPt.getEventTime();
                this.mSettleStartTime = eventTime4;
                this.mSettleEndTime = eventTime4 + 20;
            }
        }
    }

    private void performDragOrPinch() {
    }

    protected boolean getHandleSingleTouchEvents() {
        return this.handleSingleTouchEvents;
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0146 A[Catch: Exception -> 0x016b, TryCatch #0 {Exception -> 0x016b, blocks: (B:3:0x0005, B:5:0x000a, B:7:0x001b, B:9:0x001f, B:13:0x0026, B:18:0x0038, B:22:0x0041, B:24:0x004a, B:26:0x0066, B:28:0x0089, B:30:0x0095, B:32:0x00b8, B:34:0x00c4, B:36:0x00e7, B:35:0x00d9, B:31:0x00aa, B:27:0x007b, B:50:0x0120, B:64:0x0146, B:66:0x014f, B:65:0x014b, B:57:0x0134, B:37:0x00f3, B:39:0x00f7, B:41:0x0100, B:43:0x0106, B:45:0x010f, B:47:0x0115, B:49:0x011e, B:48:0x011a, B:44:0x010b, B:40:0x00fc), top: B:72:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x014b A[Catch: Exception -> 0x016b, TryCatch #0 {Exception -> 0x016b, blocks: (B:3:0x0005, B:5:0x000a, B:7:0x001b, B:9:0x001f, B:13:0x0026, B:18:0x0038, B:22:0x0041, B:24:0x004a, B:26:0x0066, B:28:0x0089, B:30:0x0095, B:32:0x00b8, B:34:0x00c4, B:36:0x00e7, B:35:0x00d9, B:31:0x00aa, B:27:0x007b, B:50:0x0120, B:64:0x0146, B:66:0x014f, B:65:0x014b, B:57:0x0134, B:37:0x00f3, B:39:0x00f7, B:41:0x0100, B:43:0x0106, B:45:0x010f, B:47:0x0115, B:49:0x011e, B:48:0x011a, B:44:0x010b, B:40:0x00fc), top: B:72:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z;
        try {
            int intValue = multiTouchSupported ? ((Integer) m_getPointerCount.invoke(motionEvent, new Object[0])).intValue() : 1;
            if (this.mMode == 0 && !this.handleSingleTouchEvents && intValue == 1) {
                return false;
            }
            int action = motionEvent.getAction();
            int historySize = motionEvent.getHistorySize() / intValue;
            int i2 = 0;
            while (i2 <= historySize) {
                boolean z2 = i2 < historySize;
                if (multiTouchSupported && intValue != 1) {
                    int min = Math.min(intValue, 20);
                    for (int i3 = 0; i3 < min; i3++) {
                        pointerIds[i3] = ((Integer) m_getPointerId.invoke(motionEvent, Integer.valueOf(i3))).intValue();
                        xVals[i3] = ((Float) (z2 ? m_getHistoricalX.invoke(motionEvent, Integer.valueOf(i3), Integer.valueOf(i2)) : m_getX.invoke(motionEvent, Integer.valueOf(i3)))).floatValue();
                        yVals[i3] = ((Float) (z2 ? m_getHistoricalY.invoke(motionEvent, Integer.valueOf(i3), Integer.valueOf(i2)) : m_getY.invoke(motionEvent, Integer.valueOf(i3)))).floatValue();
                        pressureVals[i3] = ((Float) (z2 ? m_getHistoricalPressure.invoke(motionEvent, Integer.valueOf(i3), Integer.valueOf(i2)) : m_getPressure.invoke(motionEvent, Integer.valueOf(i3)))).floatValue();
                    }
                    float[] fArr = xVals;
                    float[] fArr2 = yVals;
                    float[] fArr3 = pressureVals;
                    int[] iArr = pointerIds;
                    int i4 = !z2 ? 2 : action;
                    if (!z2 && (action == 1 || (((1 << ACTION_POINTER_INDEX_SHIFT) - 1) & action) == ACTION_POINTER_UP || action == 3)) {
                        z = false;
                        int i5 = i4;
                        boolean z3 = z;
                        int i6 = historySize;
                        int i7 = i2;
                        decodeTouchEvent(intValue, fArr, fArr2, fArr3, iArr, i5, z3, !z2 ? motionEvent.getHistoricalEventTime(i2) : motionEvent.getEventTime());
                        i2 = i7 + 1;
                        historySize = i6;
                    }
                    z = true;
                    int i52 = i4;
                    boolean z32 = z;
                    int i62 = historySize;
                    int i72 = i2;
                    decodeTouchEvent(intValue, fArr, fArr2, fArr3, iArr, i52, z32, !z2 ? motionEvent.getHistoricalEventTime(i2) : motionEvent.getEventTime());
                    i2 = i72 + 1;
                    historySize = i62;
                }
                xVals[0] = z2 ? motionEvent.getHistoricalX(i2) : motionEvent.getX();
                yVals[0] = z2 ? motionEvent.getHistoricalY(i2) : motionEvent.getY();
                pressureVals[0] = z2 ? motionEvent.getHistoricalPressure(i2) : motionEvent.getPressure();
                float[] fArr4 = xVals;
                float[] fArr22 = yVals;
                float[] fArr32 = pressureVals;
                int[] iArr2 = pointerIds;
                if (!z2) {
                }
                if (!z2) {
                    z = false;
                    int i522 = i4;
                    boolean z322 = z;
                    int i622 = historySize;
                    int i722 = i2;
                    decodeTouchEvent(intValue, fArr4, fArr22, fArr32, iArr2, i522, z322, !z2 ? motionEvent.getHistoricalEventTime(i2) : motionEvent.getEventTime());
                    i2 = i722 + 1;
                    historySize = i622;
                }
                z = true;
                int i5222 = i4;
                boolean z3222 = z;
                int i6222 = historySize;
                int i7222 = i2;
                decodeTouchEvent(intValue, fArr4, fArr22, fArr32, iArr2, i5222, z3222, !z2 ? motionEvent.getHistoricalEventTime(i2) : motionEvent.getEventTime());
                i2 = i7222 + 1;
                historySize = i6222;
            }
            return true;
        } catch (Exception e2) {
            OKLog.e("MultiTouchController", "onTouchEvent() failed", e2);
            return false;
        }
    }

    protected void setHandleSingleTouchEvents(boolean z) {
        this.handleSingleTouchEvents = z;
    }

    public MultiTouchController(MultiTouchObjectCanvas<T> multiTouchObjectCanvas, boolean z) {
        this.selectedObject = null;
        this.mCurrXform = new PositionAndScale();
        this.mMode = 0;
        this.mCurrPt = new PointInfo();
        this.mPrevPt = new PointInfo();
        this.handleSingleTouchEvents = z;
        this.objectCanvas = multiTouchObjectCanvas;
    }

    /* loaded from: classes6.dex */
    public static class PositionAndScale {
        private float scale;
        private float scaleX;
        private float scaleY;
        private boolean updateAngle;
        private boolean updateScale;
        private boolean updateScaleXY;
        private float xOff;
        private float yOff;

        public float getScale() {
            if (this.updateScale) {
                return this.scale;
            }
            return 1.0f;
        }

        public float getScaleX() {
            if (this.updateScaleXY) {
                return this.scaleX;
            }
            return 1.0f;
        }

        public float getScaleY() {
            if (this.updateScaleXY) {
                return this.scaleY;
            }
            return 1.0f;
        }

        public float getXOff() {
            return this.xOff;
        }

        public float getYOff() {
            return this.yOff;
        }

        public void set(float f2, float f3, boolean z, float f4, boolean z2, float f5, float f6, boolean z3) {
            this.xOff = f2;
            this.yOff = f3;
            this.updateScale = z;
            if (f4 == 0.0f) {
                f4 = 1.0f;
            }
            this.scale = f4;
            this.updateScaleXY = z2;
            if (f5 == 0.0f) {
                f5 = 1.0f;
            }
            this.scaleX = f5;
            if (f6 == 0.0f) {
                f6 = 1.0f;
            }
            this.scaleY = f6;
            this.updateAngle = z3;
        }

        protected void set(float f2, float f3, float f4, float f5, float f6) {
            this.xOff = f2;
            this.yOff = f3;
            if (f4 == 0.0f) {
                f4 = 1.0f;
            }
            this.scale = f4;
            if (f5 == 0.0f) {
                f5 = 1.0f;
            }
            this.scaleX = f5;
            if (f6 == 0.0f) {
                f6 = 1.0f;
            }
            this.scaleY = f6;
        }
    }

    /* loaded from: classes6.dex */
    public static class PointInfo {
        private int action;
        private float angle;
        private boolean angleIsCalculated;
        private float diameter;
        private boolean diameterIsCalculated;
        private float diameterSq;
        private boolean diameterSqIsCalculated;
        private float dx;
        private float dy;
        private long eventTime;
        private boolean isDown;
        private boolean isMultiTouch;
        private int numPoints;
        private float pressureMid;
        private float xMid;
        private float yMid;
        private float[] xs = new float[20];
        private float[] ys = new float[20];
        private float[] pressures = new float[20];
        private int[] pointerIds = new int[20];

        private int julery_isqrt(int i2) {
            int i3 = 0;
            int i4 = 32768;
            int i5 = 15;
            while (true) {
                int i6 = i5 - 1;
                int i7 = ((i3 << 1) + i4) << i5;
                if (i2 >= i7) {
                    i3 += i4;
                    i2 -= i7;
                }
                i4 >>= 1;
                if (i4 <= 0) {
                    return i3;
                }
                i5 = i6;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void set(int i2, float[] fArr, float[] fArr2, float[] fArr3, int[] iArr, int i3, boolean z, long j2) {
            this.eventTime = j2;
            this.action = i3;
            this.numPoints = i2;
            for (int i4 = 0; i4 < i2; i4++) {
                this.xs[i4] = fArr[i4];
                this.ys[i4] = fArr2[i4];
                this.pressures[i4] = fArr3[i4];
                this.pointerIds[i4] = iArr[i4];
            }
            this.isDown = z;
            boolean z2 = i2 >= 2;
            this.isMultiTouch = z2;
            if (z2) {
                this.xMid = (fArr[0] + fArr[1]) * 0.5f;
                this.yMid = (fArr2[0] + fArr2[1]) * 0.5f;
                this.pressureMid = (fArr3[0] + fArr3[1]) * 0.5f;
                this.dx = Math.abs(fArr[1] - fArr[0]);
                this.dy = Math.abs(fArr2[1] - fArr2[0]);
            } else {
                this.xMid = fArr[0];
                this.yMid = fArr2[0];
                this.pressureMid = fArr3[0];
                this.dy = 0.0f;
                this.dx = 0.0f;
            }
            this.angleIsCalculated = false;
            this.diameterIsCalculated = false;
            this.diameterSqIsCalculated = false;
        }

        public int getAction() {
            return this.action;
        }

        public long getEventTime() {
            return this.eventTime;
        }

        public float getMultiTouchAngle() {
            if (!this.angleIsCalculated) {
                if (!this.isMultiTouch) {
                    this.angle = 0.0f;
                } else {
                    float[] fArr = this.ys;
                    float[] fArr2 = this.xs;
                    this.angle = (float) Math.atan2(fArr[1] - fArr[0], fArr2[1] - fArr2[0]);
                }
                this.angleIsCalculated = true;
            }
            return this.angle;
        }

        public float getMultiTouchDiameter() {
            if (!this.diameterIsCalculated) {
                if (!this.isMultiTouch) {
                    this.diameter = 0.0f;
                } else {
                    float julery_isqrt = getMultiTouchDiameterSq() != 0.0f ? julery_isqrt((int) (r0 * 256.0f)) / 16.0f : 0.0f;
                    this.diameter = julery_isqrt;
                    float f2 = this.dx;
                    if (julery_isqrt < f2) {
                        this.diameter = f2;
                    }
                    float f3 = this.diameter;
                    float f4 = this.dy;
                    if (f3 < f4) {
                        this.diameter = f4;
                    }
                }
                this.diameterIsCalculated = true;
            }
            return this.diameter;
        }

        public float getMultiTouchDiameterSq() {
            float f2;
            if (!this.diameterSqIsCalculated) {
                if (this.isMultiTouch) {
                    float f3 = this.dx;
                    float f4 = this.dy;
                    f2 = (f3 * f3) + (f4 * f4);
                } else {
                    f2 = 0.0f;
                }
                this.diameterSq = f2;
                this.diameterSqIsCalculated = true;
            }
            return this.diameterSq;
        }

        public float getMultiTouchHeight() {
            if (this.isMultiTouch) {
                return this.dy;
            }
            return 0.0f;
        }

        public float getMultiTouchWidth() {
            if (this.isMultiTouch) {
                return this.dx;
            }
            return 0.0f;
        }

        public int getNumTouchPoints() {
            return this.numPoints;
        }

        public int[] getPointerIds() {
            return this.pointerIds;
        }

        public float getPressure() {
            return this.pressureMid;
        }

        public float[] getPressures() {
            return this.pressures;
        }

        public float getX() {
            return this.xMid;
        }

        public float[] getXs() {
            return this.xs;
        }

        public float getY() {
            return this.yMid;
        }

        public float[] getYs() {
            return this.ys;
        }

        public boolean isDown() {
            return this.isDown;
        }

        public boolean isMultiTouch() {
            return this.isMultiTouch;
        }

        public void set(PointInfo pointInfo) {
            this.numPoints = pointInfo.numPoints;
            for (int i2 = 0; i2 < this.numPoints; i2++) {
                this.xs[i2] = pointInfo.xs[i2];
                this.ys[i2] = pointInfo.ys[i2];
                this.pressures[i2] = pointInfo.pressures[i2];
                this.pointerIds[i2] = pointInfo.pointerIds[i2];
            }
            this.xMid = pointInfo.xMid;
            this.yMid = pointInfo.yMid;
            this.pressureMid = pointInfo.pressureMid;
            this.dx = pointInfo.dx;
            this.dy = pointInfo.dy;
            this.diameter = pointInfo.diameter;
            this.diameterSq = pointInfo.diameterSq;
            this.angle = pointInfo.angle;
            this.isDown = pointInfo.isDown;
            this.action = pointInfo.action;
            this.isMultiTouch = pointInfo.isMultiTouch;
            this.diameterIsCalculated = pointInfo.diameterIsCalculated;
            this.diameterSqIsCalculated = pointInfo.diameterSqIsCalculated;
            this.angleIsCalculated = pointInfo.angleIsCalculated;
            this.eventTime = pointInfo.eventTime;
        }
    }
}
