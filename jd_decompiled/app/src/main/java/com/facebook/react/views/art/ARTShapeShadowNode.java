package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jd.dynamic.DYConstants;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class ARTShapeShadowNode extends ARTVirtualNode {
    private static final int CAP_BUTT = 0;
    private static final int CAP_ROUND = 1;
    private static final int CAP_SQUARE = 2;
    private static final int COLOR_TYPE_LINEAR_GRADIENT = 1;
    private static final int COLOR_TYPE_PATTERN = 3;
    private static final int COLOR_TYPE_RADIAL_GRADIENT = 2;
    private static final int COLOR_TYPE_SOLID_COLOR = 0;
    private static final int JOIN_BEVEL = 2;
    private static final int JOIN_MITER = 0;
    private static final int JOIN_ROUND = 1;
    private static final int PATH_TYPE_ARC = 4;
    private static final int PATH_TYPE_CLOSE = 1;
    private static final int PATH_TYPE_CURVETO = 3;
    private static final int PATH_TYPE_LINETO = 2;
    private static final int PATH_TYPE_MOVETO = 0;
    @Nullable
    private float[] mBrushData;
    @Nullable
    protected Path mPath;
    @Nullable
    private float[] mStrokeColor;
    @Nullable
    private float[] mStrokeDash;
    private float mStrokeWidth = 1.0f;
    private int mStrokeCap = 1;
    private int mStrokeJoin = 1;

    private Path createPath(float[] fArr) {
        int i2;
        int i3;
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        int i4 = 0;
        while (i4 < fArr.length) {
            int i5 = i4 + 1;
            int i6 = (int) fArr[i4];
            if (i6 != 0) {
                if (i6 == 1) {
                    path.close();
                    i4 = i5;
                } else if (i6 != 2) {
                    if (i6 == 3) {
                        int i7 = i5 + 1;
                        float f2 = fArr[i5];
                        float f3 = this.mScale;
                        int i8 = i7 + 1;
                        float f4 = fArr[i7] * f3;
                        int i9 = i8 + 1;
                        int i10 = i9 + 1;
                        float f5 = fArr[i9] * f3;
                        int i11 = i10 + 1;
                        i3 = i11 + 1;
                        path.cubicTo(f2 * f3, f4, fArr[i8] * f3, f5, fArr[i10] * f3, fArr[i11] * f3);
                    } else if (i6 == 4) {
                        int i12 = i5 + 1;
                        float f6 = fArr[i5];
                        float f7 = this.mScale;
                        float f8 = f6 * f7;
                        int i13 = i12 + 1;
                        float f9 = fArr[i12] * f7;
                        int i14 = i13 + 1;
                        float f10 = fArr[i13] * f7;
                        int i15 = i14 + 1;
                        float degrees = (float) Math.toDegrees(fArr[i14]);
                        int i16 = i15 + 1;
                        float degrees2 = (float) Math.toDegrees(fArr[i15]);
                        i3 = i16 + 1;
                        boolean z = fArr[i16] != 1.0f;
                        float f11 = degrees2 - degrees;
                        if (Math.abs(f11) >= 360.0f) {
                            path.addCircle(f8, f9, f10, z ? Path.Direction.CCW : Path.Direction.CW);
                        } else {
                            float modulus = modulus(f11, 360.0f);
                            if (z && modulus < 360.0f) {
                                modulus = (360.0f - modulus) * (-1.0f);
                            }
                            path.arcTo(new RectF(f8 - f10, f9 - f10, f8 + f10, f9 + f10), degrees, modulus);
                        }
                    } else {
                        throw new JSApplicationIllegalArgumentException("Unrecognized drawing instruction " + i6);
                    }
                    i4 = i3;
                } else {
                    int i17 = i5 + 1;
                    float f12 = fArr[i5];
                    float f13 = this.mScale;
                    i2 = i17 + 1;
                    path.lineTo(f12 * f13, fArr[i17] * f13);
                }
            } else {
                int i18 = i5 + 1;
                float f14 = fArr[i5];
                float f15 = this.mScale;
                i2 = i18 + 1;
                path.moveTo(f14 * f15, fArr[i18] * f15);
            }
            i4 = i2;
        }
        return path;
    }

    private float modulus(float f2, float f3) {
        float f4 = f2 % f3;
        return f4 < 0.0f ? f4 + f3 : f4;
    }

    @Override // com.facebook.react.views.art.ARTVirtualNode
    public void draw(Canvas canvas, Paint paint, float f2) {
        float f3 = f2 * this.mOpacity;
        if (f3 > 0.01f) {
            saveAndSetupCanvas(canvas);
            if (this.mPath == null) {
                return;
            }
            if (setupFillPaint(paint, f3)) {
                canvas.drawPath(this.mPath, paint);
            }
            if (setupStrokePaint(paint, f3)) {
                canvas.drawPath(this.mPath, paint);
            }
            restoreCanvas(canvas);
        }
        markUpdateSeen();
    }

    @ReactProp(name = DYConstants.DY_FILL)
    public void setFill(@Nullable ReadableArray readableArray) {
        this.mBrushData = PropHelper.toFloatArray(readableArray);
        markUpdated();
    }

    @ReactProp(name = "d")
    public void setShapePath(@Nullable ReadableArray readableArray) {
        this.mPath = createPath(PropHelper.toFloatArray(readableArray));
        markUpdated();
    }

    @ReactProp(name = "stroke")
    public void setStroke(@Nullable ReadableArray readableArray) {
        this.mStrokeColor = PropHelper.toFloatArray(readableArray);
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "strokeCap")
    public void setStrokeCap(int i2) {
        this.mStrokeCap = i2;
        markUpdated();
    }

    @ReactProp(name = "strokeDash")
    public void setStrokeDash(@Nullable ReadableArray readableArray) {
        this.mStrokeDash = PropHelper.toFloatArray(readableArray);
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "strokeJoin")
    public void setStrokeJoin(int i2) {
        this.mStrokeJoin = i2;
        markUpdated();
    }

    @ReactProp(defaultFloat = 1.0f, name = "strokeWidth")
    public void setStrokeWidth(float f2) {
        this.mStrokeWidth = f2;
        markUpdated();
    }

    public boolean setupFillPaint(Paint paint, float f2) {
        int[] iArr;
        float[] fArr;
        float[] fArr2 = this.mBrushData;
        int i2 = 0;
        if (fArr2 == null || fArr2.length <= 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.FILL);
        float[] fArr3 = this.mBrushData;
        int i3 = (int) fArr3[0];
        if (i3 == 0) {
            paint.setARGB((int) (fArr3.length > 4 ? fArr3[4] * f2 * 255.0f : f2 * 255.0f), (int) (fArr3[1] * 255.0f), (int) (fArr3[2] * 255.0f), (int) (fArr3[3] * 255.0f));
            return true;
        }
        if (i3 != 1) {
            FLog.w(ReactConstants.TAG, "ART: Color type " + i3 + " not supported!");
        } else {
            int i4 = 5;
            if (fArr3.length < 5) {
                FLog.w(ReactConstants.TAG, "[ARTShapeShadowNode setupFillPaint] expects 5 elements, received " + this.mBrushData.length);
                return false;
            }
            float f3 = fArr3[1];
            float f4 = this.mScale;
            float f5 = f3 * f4;
            float f6 = fArr3[2] * f4;
            float f7 = fArr3[3] * f4;
            float f8 = fArr3[4] * f4;
            int length = (fArr3.length - 5) / 5;
            if (length > 0) {
                int[] iArr2 = new int[length];
                float[] fArr4 = new float[length];
                while (i2 < length) {
                    float[] fArr5 = this.mBrushData;
                    fArr4[i2] = fArr5[(length * 4) + i4 + i2];
                    int i5 = (i2 * 4) + i4;
                    iArr2[i2] = Color.argb((int) (fArr5[i5 + 3] * 255.0f), (int) (fArr5[i5 + 0] * 255.0f), (int) (fArr5[i5 + 1] * 255.0f), (int) (fArr5[i5 + 2] * 255.0f));
                    i2++;
                    i4 = 5;
                }
                iArr = iArr2;
                fArr = fArr4;
            } else {
                iArr = null;
                fArr = null;
            }
            paint.setShader(new LinearGradient(f5, f6, f7, f8, iArr, fArr, Shader.TileMode.CLAMP));
        }
        return true;
    }

    public boolean setupStrokePaint(Paint paint, float f2) {
        float[] fArr;
        if (this.mStrokeWidth == 0.0f || (fArr = this.mStrokeColor) == null || fArr.length == 0) {
            return false;
        }
        paint.reset();
        paint.setFlags(1);
        paint.setStyle(Paint.Style.STROKE);
        int i2 = this.mStrokeCap;
        if (i2 == 0) {
            paint.setStrokeCap(Paint.Cap.BUTT);
        } else if (i2 == 1) {
            paint.setStrokeCap(Paint.Cap.ROUND);
        } else if (i2 == 2) {
            paint.setStrokeCap(Paint.Cap.SQUARE);
        } else {
            throw new JSApplicationIllegalArgumentException("strokeCap " + this.mStrokeCap + " unrecognized");
        }
        int i3 = this.mStrokeJoin;
        if (i3 == 0) {
            paint.setStrokeJoin(Paint.Join.MITER);
        } else if (i3 == 1) {
            paint.setStrokeJoin(Paint.Join.ROUND);
        } else if (i3 == 2) {
            paint.setStrokeJoin(Paint.Join.BEVEL);
        } else {
            throw new JSApplicationIllegalArgumentException("strokeJoin " + this.mStrokeJoin + " unrecognized");
        }
        paint.setStrokeWidth(this.mStrokeWidth * this.mScale);
        float[] fArr2 = this.mStrokeColor;
        paint.setARGB((int) (fArr2.length > 3 ? fArr2[3] * f2 * 255.0f : f2 * 255.0f), (int) (fArr2[0] * 255.0f), (int) (fArr2[1] * 255.0f), (int) (fArr2[2] * 255.0f));
        float[] fArr3 = this.mStrokeDash;
        if (fArr3 != null && fArr3.length > 0) {
            paint.setPathEffect(new DashPathEffect(this.mStrokeDash, 0.0f));
        }
        return true;
    }
}
