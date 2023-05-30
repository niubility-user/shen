package com.jingdong.common.unification.navigationbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.jingdong.common.R;
import com.jingdong.common.unification.navigationbar.newbar.EventListener;
import com.jingdong.common.unification.navigationbar.newbar.StateController;
import com.jingdong.common.unification.navigationbar.newbar.TabShowNew;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.eldermode.util.JDElderModeUtils;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class RadioStateDrawable extends Drawable {
    private static final String TAG = "RadioStateDrawable";
    public static int screen_width;
    public static int width;
    private Bitmap bitmap;
    private boolean bsAngleSwitch;
    Context context;
    public float drawCenterX;
    public float drawCenterY;
    public boolean drawLabel;
    public boolean drawNum;
    private EventListener eventListener;
    private boolean isBig;
    private boolean isNeedCut;
    private boolean isReDraw;
    private String label;
    private NavigationParam navigationParam;
    public boolean noIcon;
    private Bitmap redPointBmp;
    private StateController stateController;
    private TabShowNew tabShowNew;
    private int type;
    private Typeface typeface;

    public RadioStateDrawable(Context context, String str, String str2, boolean z, boolean z2) {
        this.type = -1;
        this.isReDraw = true;
        this.isBig = z2;
        this.label = str2;
        this.context = context;
        this.bitmap = BitmapFactory.decodeFile(str);
        if (OKLog.D) {
            OKLog.d(TAG, "RadioStateDrawable-imagePath=" + str + " bitmap=" + this.bitmap);
        }
    }

    private float changeToEven(float f2) {
        float ceil = (float) Math.ceil(f2);
        return ceil % 2.0f == 0.0f ? ceil : ceil + 1.0f;
    }

    private Bitmap createCircleBitmap(Bitmap bitmap) {
        int i2;
        try {
            int min = Math.min(bitmap.getHeight(), bitmap.getWidth());
            int height = bitmap.getHeight();
            int width2 = bitmap.getWidth();
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            Bitmap createBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawCircle(min / 2, min / 2, min / 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            if (height > width2) {
                i2 = (-(height - width2)) / 2;
            } else {
                r0 = height < width2 ? (-(width2 - height)) / 2 : 0;
                i2 = 0;
            }
            canvas.drawBitmap(bitmap, r0, i2, paint);
            return createBitmap;
        } catch (Exception unused) {
            return null;
        }
    }

    private Bitmap createRectBitmap(Bitmap bitmap, int i2) {
        try {
            if (bitmap != null) {
                float f2 = i2;
                float f3 = 1.0f * f2;
                Matrix matrix = new Matrix();
                matrix.setScale(f3 / bitmap.getWidth(), f3 / bitmap.getHeight());
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
                bitmapShader.setLocalMatrix(matrix);
                Bitmap createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                Paint paint = new Paint();
                paint.setAntiAlias(true);
                paint.setShader(bitmapShader);
                canvas.drawRoundRect(new RectF(0.0f, 0.0f, f2, f2), DPIUtil.dip2px(4.0f), DPIUtil.dip2px(4.0f), paint);
                return createBitmap;
            }
            throw new NullPointerException("Bitmap can't be null");
        } catch (Exception unused) {
            return null;
        }
    }

    public boolean angleSwitch() {
        if (NavigationBase.getInstance().angleSwitch()) {
            return true;
        }
        return this.bsAngleSwitch;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int dimensionPixelSize;
        String str;
        String str2;
        TabShowNew tabShowNew;
        TabShowNew tabShowNew2;
        int dip2px;
        int dip2px2;
        int dip2px3;
        int dip2px4;
        StateController stateController;
        int i2;
        int dip2px5;
        int i3;
        int dip2px6;
        int dip2px7;
        StateController stateController2;
        int i4;
        int dip2px8;
        int i5;
        int dip2px9;
        float f2;
        boolean z;
        int dip2px10;
        Bitmap createCircleBitmap;
        RectF rectF;
        RectF rectF2;
        int dimensionPixelSize2 = this.context.getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_icon_width);
        if (this.isBig) {
            dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_big_icon_height);
        } else {
            dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_icon_height);
        }
        int i6 = (width - dimensionPixelSize2) >> 1;
        canvas.drawColor(0);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        if (this.bitmap != null) {
            if (this.isNeedCut) {
                boolean z2 = DeepDarkChangeManager.getInstance().getUIMode() == 1 && NavigationBase.getInstance().navigationCurrentMode != 2;
                NavigationParam navigationParam = this.navigationParam;
                if (navigationParam != null && navigationParam.shapeType == 1) {
                    createCircleBitmap = createRectBitmap(this.bitmap, this.type == 1 ? DPIUtil.dip2px(40.0f) : DPIUtil.dip2px(26.0f));
                } else {
                    createCircleBitmap = createCircleBitmap(this.bitmap);
                }
                if (createCircleBitmap != null) {
                    try {
                        int i7 = this.type;
                        if (i7 == 0) {
                            int i8 = (dimensionPixelSize2 / 2) + i6;
                            int i9 = i8 + 1;
                            int i10 = i8 - 1;
                            rectF2 = new RectF(i9 - (DPIUtil.dip2px(26.0f) / 2), 1 + DPIUtil.dip2px(4.0f), i10 + (DPIUtil.dip2px(26.0f) / 2), (float) (((DPIUtil.dip2px(26.0f) + 0) + DPIUtil.dip2px(4.0f)) - 1));
                            rectF = new RectF((i9 - (DPIUtil.dip2px(26.0f) / 2)) - DPIUtil.dip2px(0.5f), (DPIUtil.dip2px(4.0f) + 1) - DPIUtil.dip2px(0.5f), i10 + (DPIUtil.dip2px(26.0f) / 2) + DPIUtil.dip2px(0.5f), (((DPIUtil.dip2px(26.0f) + 0) + DPIUtil.dip2px(4.0f)) - 1) + DPIUtil.dip2px(0.5f));
                            str = TAG;
                        } else if (i7 == 1) {
                            int i11 = (dimensionPixelSize2 / 2) + i6;
                            int dip2px11 = (dimensionPixelSize - (DPIUtil.dip2px(7.0f) * 2)) / 2;
                            float f3 = (i11 + 1) - dip2px11;
                            float dip2px12 = 1 + DPIUtil.dip2px(7.0f);
                            float f4 = (i11 - 1) + dip2px11;
                            int dip2px13 = (dip2px11 * 2) + 0 + DPIUtil.dip2px(7.0f);
                            str = TAG;
                            try {
                                RectF rectF3 = new RectF(f3, dip2px12, f4, (float) (dip2px13 - 1));
                                rectF = new RectF(r12 - DPIUtil.dip2px(0.5f), (DPIUtil.dip2px(7.0f) + 1) - DPIUtil.dip2px(0.5f), r6 + DPIUtil.dip2px(0.5f), ((r3 + DPIUtil.dip2px(7.0f)) - 1) + DPIUtil.dip2px(0.5f));
                                rectF2 = rectF3;
                            } catch (Exception unused) {
                            }
                        } else {
                            str = TAG;
                            rectF = null;
                            rectF2 = null;
                        }
                        if (!this.noIcon) {
                            Paint paint2 = new Paint();
                            paint.setAntiAlias(true);
                            paint.setFilterBitmap(true);
                            paint.setDither(true);
                            if (z2) {
                                paint2.setColor(this.context.getResources().getColor(R.color.navigation_icon_bg_dark));
                            } else {
                                paint2.setColor(this.context.getResources().getColor(R.color.navigation_icon_bg_nomal));
                            }
                            NavigationParam navigationParam2 = this.navigationParam;
                            if (navigationParam2 != null && navigationParam2.shapeType == 1) {
                                canvas.drawRoundRect(rectF2, DPIUtil.dip2px(4.0f), DPIUtil.dip2px(4.0f), paint2);
                            } else {
                                canvas.drawOval(rectF2, paint2);
                            }
                            canvas.drawBitmap(createCircleBitmap, (Rect) null, rectF2, paint);
                            if (z2) {
                                Paint paint3 = new Paint();
                                paint3.setAntiAlias(true);
                                paint3.setFilterBitmap(true);
                                paint3.setDither(true);
                                paint3.setColor(this.context.getResources().getColor(R.color.navigation_icon_mask_Dark));
                                NavigationParam navigationParam3 = this.navigationParam;
                                if (navigationParam3 != null && navigationParam3.shapeType == 1) {
                                    canvas.drawRoundRect(rectF2, DPIUtil.dip2px(4.0f), DPIUtil.dip2px(4.0f), paint3);
                                } else {
                                    canvas.drawOval(rectF2, paint3);
                                }
                            }
                            Paint paint4 = new Paint();
                            paint4.setAntiAlias(true);
                            paint4.setFilterBitmap(true);
                            paint4.setDither(true);
                            paint4.setStrokeWidth(DPIUtil.dip2px(0.5f));
                            paint4.setStyle(Paint.Style.STROKE);
                            if (z2) {
                                paint4.setColor(this.context.getResources().getColor(R.color.navigation_icon_border_dark));
                            } else {
                                paint4.setColor(this.context.getResources().getColor(R.color.navigation_icon_border_nomal));
                            }
                            NavigationParam navigationParam4 = this.navigationParam;
                            if (navigationParam4 != null && navigationParam4.shapeType == 1) {
                                canvas.drawRoundRect(rectF, DPIUtil.dip2px(4.0f), DPIUtil.dip2px(4.0f), paint4);
                            } else {
                                canvas.drawOval(rectF, paint4);
                            }
                        }
                    } catch (Exception unused2) {
                    }
                }
                str = TAG;
            } else {
                str = TAG;
                Rect rect = new Rect(i6 + 1, 1, (i6 + dimensionPixelSize2) - 1, (dimensionPixelSize + 0) - 1);
                if (!this.noIcon) {
                    canvas.drawBitmap(this.bitmap, (Rect) null, rect, paint);
                }
            }
        } else {
            str = TAG;
            if (!this.noIcon && this.eventListener != null && this.isReDraw) {
                ExceptionReporter.reportExceptionToBugly(new Exception("NavigationBar_Bitmap_null"));
                if (OKLog.D) {
                    str2 = str;
                    OKLog.d(str2, "RadioStateDrawable-eventListener.onEvent()+isReDraw=" + this.isReDraw);
                } else {
                    str2 = str;
                }
                this.isReDraw = false;
                this.eventListener.onEvent();
                if (!this.drawNum && (stateController2 = this.stateController) != null && stateController2.getNum() != null) {
                    boolean isElderMode = JDElderModeUtils.isElderMode();
                    boolean z3 = DeepDarkChangeManager.getInstance().getUIMode() == 1 && NavigationBase.getInstance().navigationCurrentMode != 2;
                    if (OKLog.D) {
                        OKLog.d(str2, "RadioStateDrawable-elderMode=" + isElderMode + " isDark=" + z3);
                    }
                    Paint paint5 = new Paint();
                    if (z3) {
                        paint5.setColor(this.context.getResources().getColor(R.color.navigation_text_dark));
                    } else {
                        paint5.setColor(this.context.getResources().getColor(R.color.navigation_text_normal));
                    }
                    paint5.setStyle(Paint.Style.FILL);
                    if (isElderMode) {
                        paint5.setTextSize(DPIUtil.dip2px(12.0f));
                    } else {
                        paint5.setTextSize(DPIUtil.dip2px(10.0f));
                    }
                    if (OKLog.D) {
                        OKLog.d(str2, "RadioStateDrawable-typeface=" + this.typeface);
                    }
                    if (this.typeface == null) {
                        this.typeface = FontsUtil.getTypeFace(this.context);
                    }
                    Typeface typeface = this.typeface;
                    if (typeface != null) {
                        paint5.setTypeface(typeface);
                    } else {
                        paint5.setTypeface(FontsUtil.getTypeFace(this.context));
                    }
                    paint5.setFakeBoldText(true);
                    paint5.setAntiAlias(true);
                    String num = this.stateController.getNum();
                    if (isElderMode) {
                        if (num != null && num.length() >= 3) {
                            i4 = i6 + dimensionPixelSize2;
                            dip2px8 = DPIUtil.dip2px(5.0f);
                        } else {
                            i4 = i6 + dimensionPixelSize2;
                            dip2px8 = DPIUtil.dip2px(0.5f);
                        }
                    } else {
                        i4 = i6 + dimensionPixelSize2;
                        dip2px8 = DPIUtil.dip2px(18.0f);
                    }
                    float f5 = i4 - dip2px8;
                    if (isElderMode) {
                        dip2px9 = DPIUtil.dip2px(21.0f) + 0;
                        i5 = 1;
                    } else {
                        i5 = 1;
                        if (this.isBig) {
                            dip2px9 = DPIUtil.dip2px(13.0f) + 0 + DPIUtil.dip2px(17.0f);
                        } else {
                            dip2px9 = DPIUtil.dip2px(13.0f) + 0;
                        }
                    }
                    float f6 = dip2px9 - i5;
                    if (OKLog.D) {
                        OKLog.d(str2, "isBig=" + this.isBig + " centerY=" + f6);
                    }
                    float f7 = 0.0f;
                    int length = num.length();
                    float[] fArr = new float[length];
                    paint5.getTextWidths(num, fArr);
                    for (int i12 = 0; i12 < length; i12++) {
                        f7 += fArr[i12];
                    }
                    Paint.FontMetrics fontMetrics = paint5.getFontMetrics();
                    float changeToEven = changeToEven(fontMetrics.descent - fontMetrics.top);
                    float max = Math.max(changeToEven, f7 + DPIUtil.dip2px(3.0f));
                    RectF rectF4 = new RectF();
                    int dip2px14 = DPIUtil.dip2px(9.0f);
                    if (num != null && num.length() == 1 && Integer.valueOf(num).intValue() == 1) {
                        dip2px14 = DPIUtil.dip2px(9.0f) + 1;
                    }
                    if (this.isBig) {
                        rectF4.left = f5 - DPIUtil.dip2px(5.0f);
                    } else {
                        rectF4.left = f5 - dip2px14;
                    }
                    if (OKLog.D) {
                        OKLog.d(str2, "isBig=" + this.isBig + " rectF.left=" + rectF4.left);
                    }
                    rectF4.left = changeToEven(rectF4.left);
                    rectF4.top = changeToEven(((f6 - DPIUtil.dip2px(4.0f)) - (changeToEven / 2.0f)) + 1.0f);
                    rectF4.right = changeToEven(rectF4.left + max);
                    float changeToEven2 = changeToEven(rectF4.top + changeToEven);
                    rectF4.bottom = changeToEven2;
                    float f8 = (changeToEven2 - rectF4.top) / 2.0f;
                    if (OKLog.D) {
                        OKLog.d(str2, "\u6c14\u6ce1=" + rectF4.toString());
                    }
                    int dip2px15 = DPIUtil.dip2px(1.0f);
                    Paint paint6 = new Paint();
                    paint6.setAntiAlias(true);
                    paint6.setStyle(Paint.Style.STROKE);
                    if (z3) {
                        paint6.setColor(this.context.getResources().getColor(R.color.navigation_stroke_dark));
                    } else {
                        paint6.setColor(this.context.getResources().getColor(R.color.navigation_stroke_normal));
                    }
                    paint6.setShader(null);
                    float f9 = dip2px15 * 2;
                    paint6.setStrokeWidth(f9);
                    if (isElderMode) {
                        double d = rectF4.left;
                        f2 = f8;
                        z = z3;
                        double d2 = dip2px15;
                        Double.isNaN(d2);
                        double d3 = d2 * 1.5d;
                        Double.isNaN(d);
                        rectF4.left = (float) (d - d3);
                        double d4 = rectF4.top;
                        Double.isNaN(d4);
                        rectF4.top = (float) (d4 - d3);
                        double d5 = rectF4.right;
                        Double.isNaN(d5);
                        rectF4.right = (float) (d5 + d3);
                        double d6 = rectF4.bottom;
                        Double.isNaN(d6);
                        rectF4.bottom = (float) (d6 + d3);
                    } else {
                        f2 = f8;
                        z = z3;
                        float f10 = dip2px15;
                        rectF4.left -= f10;
                        rectF4.top -= f10;
                        rectF4.right += f10;
                        rectF4.bottom += f10;
                    }
                    float f11 = (rectF4.bottom - rectF4.top) / 2.0f;
                    if (num != null && num.length() > 1) {
                        rectF4.right += DPIUtil.dip2px(2.5f);
                    }
                    Path path = new Path();
                    path.addRoundRect(rectF4, new float[]{f11, f11, f11, f11, f11, f11, f11, f11}, Path.Direction.CCW);
                    canvas.drawPath(path, paint6);
                    if (isElderMode) {
                        double d7 = rectF4.left;
                        double d8 = dip2px15;
                        Double.isNaN(d8);
                        double d9 = d8 * 1.2d;
                        Double.isNaN(d7);
                        rectF4.left = (float) (d7 + d9);
                        double d10 = rectF4.top;
                        Double.isNaN(d10);
                        rectF4.top = (float) (d10 + d9);
                        double d11 = rectF4.right;
                        Double.isNaN(d11);
                        rectF4.right = (float) (d11 - d9);
                        double d12 = rectF4.bottom;
                        Double.isNaN(d12);
                        rectF4.bottom = (float) (d12 - d9);
                    } else {
                        float f12 = dip2px15;
                        rectF4.left += f12;
                        rectF4.top += f12;
                        rectF4.right -= f12;
                        rectF4.bottom -= f12;
                    }
                    paint6.setStyle(Paint.Style.STROKE);
                    paint6.setStrokeWidth(f9);
                    if (z) {
                        paint6.setColor(this.context.getResources().getColor(R.color.navigation_fill_dark));
                    } else {
                        paint6.setColor(this.context.getResources().getColor(R.color.navigation_fill_normal));
                    }
                    Path path2 = new Path();
                    float[] fArr2 = {f2, f2, f2, f2, f2, f2, f2, f2};
                    path2.addRoundRect(rectF4, fArr2, Path.Direction.CCW);
                    canvas.drawPath(path2, paint6);
                    paint6.setStyle(Paint.Style.FILL);
                    if (z) {
                        paint6.setColor(this.context.getResources().getColor(R.color.navigation_fill_dark));
                    } else {
                        paint6.setColor(this.context.getResources().getColor(R.color.navigation_fill_normal));
                    }
                    Path path3 = new Path();
                    path3.addRoundRect(rectF4, fArr2, Path.Direction.CCW);
                    canvas.drawPath(path3, paint6);
                    if (this.isBig) {
                        dip2px10 = DPIUtil.dip2px(2.5f);
                    } else {
                        dip2px10 = DPIUtil.dip2px(6.5f);
                    }
                    float f13 = f5 - dip2px10;
                    if (isElderMode) {
                        f6 += DPIUtil.dip2px(0.8f);
                    }
                    canvas.drawText(num, f13, f6, paint5);
                    return;
                } else if (!this.drawLabel && (stateController = this.stateController) != null && !TextUtils.isEmpty(stateController.getButtonLabel()) && !JDElderModeUtils.isElderMode() && angleSwitch()) {
                    boolean z4 = DeepDarkChangeManager.getInstance().getUIMode() == 1 && NavigationBase.getInstance().navigationCurrentMode != 2;
                    Paint paint7 = new Paint();
                    if (z4) {
                        paint7.setColor(this.context.getResources().getColor(R.color.navigation_text_dark));
                    } else {
                        paint7.setColor(this.context.getResources().getColor(R.color.navigation_text_normal));
                    }
                    paint7.setStyle(Paint.Style.FILL);
                    paint7.setTextSize(DPIUtil.dip2px(10.0f));
                    if (this.typeface == null) {
                        this.typeface = FontsUtil.getTypeFace(this.context);
                    }
                    Typeface typeface2 = this.typeface;
                    if (typeface2 != null) {
                        paint7.setTypeface(typeface2);
                    } else {
                        paint7.setTypeface(FontsUtil.getTypeFace(this.context));
                    }
                    paint7.setFakeBoldText(true);
                    paint7.setAntiAlias(true);
                    String buttonLabel = this.stateController.getButtonLabel();
                    if (!TextUtils.isEmpty(buttonLabel) && buttonLabel.length() == 3) {
                        if (this.isBig) {
                            i2 = i6 + dimensionPixelSize2;
                            dip2px5 = DPIUtil.dip2px(24.0f);
                        } else {
                            i2 = i6 + dimensionPixelSize2;
                            dip2px5 = DPIUtil.dip2px(20.0f);
                        }
                    } else if (!TextUtils.isEmpty(buttonLabel) && buttonLabel.length() > 3) {
                        if (this.isBig) {
                            i2 = i6 + dimensionPixelSize2;
                            dip2px5 = DPIUtil.dip2px(28.0f);
                        } else {
                            i2 = i6 + dimensionPixelSize2;
                            dip2px5 = DPIUtil.dip2px(24.0f);
                        }
                    } else {
                        i2 = i6 + dimensionPixelSize2;
                        dip2px5 = DPIUtil.dip2px(17.0f);
                    }
                    float f14 = i2 - dip2px5;
                    if (this.isBig) {
                        dip2px6 = DPIUtil.dip2px(13.0f) + 0 + DPIUtil.dip2px(17.0f);
                        i3 = 1;
                    } else {
                        i3 = 1;
                        dip2px6 = DPIUtil.dip2px(14.0f) + 0;
                    }
                    float f15 = dip2px6 - i3;
                    float f16 = 0.0f;
                    int length2 = buttonLabel.length();
                    float[] fArr3 = new float[length2];
                    paint7.getTextWidths(buttonLabel, fArr3);
                    for (int i13 = 0; i13 < length2; i13++) {
                        f16 += fArr3[i13];
                    }
                    Paint.FontMetrics fontMetrics2 = paint7.getFontMetrics();
                    float f17 = fontMetrics2.descent - fontMetrics2.top;
                    float max2 = Math.max(f17, f16 + DPIUtil.dip2px(3.0f));
                    RectF rectF5 = new RectF();
                    int dip2px16 = DPIUtil.dip2px(9.0f);
                    if (this.isBig) {
                        rectF5.left = f14 - DPIUtil.dip2px(7.0f);
                    } else {
                        rectF5.left = f14 - dip2px16;
                    }
                    rectF5.left = changeToEven(rectF5.left);
                    rectF5.top = changeToEven(((f15 - DPIUtil.dip2px(4.0f)) - (f17 / 2.0f)) + 1.0f);
                    rectF5.right = changeToEven(rectF5.left + max2);
                    float changeToEven3 = changeToEven(rectF5.top + f17);
                    rectF5.bottom = changeToEven3;
                    float f18 = (changeToEven3 - rectF5.top) / 2.0f;
                    int dip2px17 = DPIUtil.dip2px(1.0f);
                    Paint paint8 = new Paint();
                    paint8.setAntiAlias(true);
                    paint8.setStyle(Paint.Style.STROKE);
                    if (z4) {
                        paint8.setColor(this.context.getResources().getColor(R.color.navigation_stroke_dark));
                    } else {
                        paint8.setColor(this.context.getResources().getColor(R.color.navigation_stroke_normal));
                    }
                    paint8.setShader(null);
                    float f19 = dip2px17 * 2;
                    paint8.setStrokeWidth(f19);
                    float f20 = dip2px17;
                    rectF5.left -= f20;
                    double d13 = rectF5.top;
                    boolean z5 = z4;
                    double d14 = dip2px17;
                    Double.isNaN(d14);
                    double d15 = d14 * 1.5d;
                    Double.isNaN(d13);
                    float f21 = (float) (d13 - d15);
                    rectF5.top = f21;
                    rectF5.right += f20;
                    double d16 = rectF5.bottom;
                    Double.isNaN(d16);
                    float f22 = (float) (d16 + d15);
                    rectF5.bottom = f22;
                    float f23 = (f22 - f21) / 2.0f;
                    boolean z6 = buttonLabel != null && buttonLabel.length() == 1 && this.stateController.isNumber(buttonLabel);
                    if (OKLog.D) {
                        OKLog.d(str2, "singleNum=" + z6);
                    }
                    if (!z6) {
                        rectF5.right += DPIUtil.dip2px(2.3f);
                    }
                    Path path4 = new Path();
                    path4.addRoundRect(rectF5, new float[]{f23, f23, f23, f23, f23, f23, f23, f23}, Path.Direction.CCW);
                    canvas.drawPath(path4, paint8);
                    rectF5.left += f20;
                    rectF5.top += f20;
                    rectF5.right -= f20;
                    rectF5.bottom -= f20;
                    paint8.setStyle(Paint.Style.STROKE);
                    paint8.setStrokeWidth(f19);
                    if (z5) {
                        paint8.setColor(this.context.getResources().getColor(R.color.navigation_fill_dark));
                    } else {
                        paint8.setColor(this.context.getResources().getColor(R.color.navigation_fill_normal));
                    }
                    Path path5 = new Path();
                    float[] fArr4 = {f18, f18, f18, f18, f18, f18, f18, f18};
                    path5.addRoundRect(rectF5, fArr4, Path.Direction.CCW);
                    canvas.drawPath(path5, paint8);
                    paint8.setStyle(Paint.Style.FILL);
                    if (z5) {
                        paint8.setColor(this.context.getResources().getColor(R.color.navigation_fill_dark));
                    } else {
                        paint8.setColor(this.context.getResources().getColor(R.color.navigation_fill_normal));
                    }
                    Path path6 = new Path();
                    path6.addRoundRect(rectF5, fArr4, Path.Direction.CCW);
                    canvas.drawPath(path6, paint8);
                    if (this.isBig) {
                        dip2px7 = DPIUtil.dip2px(4.5f);
                    } else {
                        dip2px7 = DPIUtil.dip2px(6.5f);
                    }
                    float f24 = rectF5.left;
                    this.drawCenterX = f24 + ((rectF5.right - f24) / 2.0f);
                    float f25 = rectF5.top;
                    this.drawCenterY = f25 + ((rectF5.bottom - f25) / 2.0f);
                    canvas.drawText(buttonLabel, (f14 - dip2px7) + DPIUtil.dip2px(0.2f), f15 + DPIUtil.dip2px(0.2f), paint7);
                    return;
                } else {
                    tabShowNew = this.tabShowNew;
                    if (tabShowNew == null && tabShowNew.getIsShowNew() != null && this.tabShowNew.getIsShowNew().booleanValue() && !JDElderModeUtils.isElderMode() && redPointSwitch()) {
                        int dimensionPixelSize3 = this.context.getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_item_red_point);
                        if (OKLog.D) {
                            OKLog.d(str2, "isShowNew isBig=" + this.isBig);
                        }
                        if (this.isBig) {
                            dip2px3 = DPIUtil.dip2px(12.0f);
                            dip2px4 = DPIUtil.dip2px(20.0f);
                        } else {
                            dip2px3 = DPIUtil.dip2px(13.0f);
                            dip2px4 = DPIUtil.dip2px(3.5f);
                        }
                        int i14 = i6 + dimensionPixelSize2;
                        Rect rect2 = new Rect((i14 - dimensionPixelSize3) - dip2px3, dip2px4, i14 - dip2px3, dimensionPixelSize3 + dip2px4);
                        if (this.redPointBmp == null) {
                            this.redPointBmp = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.navigation_red_point);
                        }
                        Bitmap bitmap = this.redPointBmp;
                        if (bitmap != null) {
                            canvas.drawBitmap(bitmap, (Rect) null, rect2, paint);
                            return;
                        }
                        return;
                    }
                    tabShowNew2 = this.tabShowNew;
                    if (tabShowNew2 == null && tabShowNew2.getIsShowRedPoint() != null && this.tabShowNew.getIsShowRedPoint().booleanValue() && !JDElderModeUtils.isElderMode() && redPointSwitch()) {
                        int dimensionPixelSize4 = this.context.getResources().getDimensionPixelSize(R.dimen.main_navigation_bottom_item_red_point);
                        if (OKLog.D) {
                            OKLog.d(str2, "isShowRedPoint isBig=" + this.isBig);
                        }
                        if (this.isBig) {
                            dip2px = DPIUtil.dip2px(12.0f);
                            dip2px2 = DPIUtil.dip2px(20.0f);
                        } else {
                            dip2px = DPIUtil.dip2px(13.0f);
                            dip2px2 = DPIUtil.dip2px(3.5f);
                        }
                        int i15 = i6 + dimensionPixelSize2;
                        Rect rect3 = new Rect((i15 - dimensionPixelSize4) - dip2px, dip2px2, i15 - dip2px, dimensionPixelSize4 + dip2px2);
                        Bitmap bitmap2 = this.redPointBmp;
                        if (bitmap2 == null || bitmap2.isRecycled()) {
                            this.redPointBmp = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.navigation_red_point);
                        }
                        Bitmap bitmap3 = this.redPointBmp;
                        if (bitmap3 != null) {
                            canvas.drawBitmap(bitmap3, (Rect) null, rect3, paint);
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
        }
        str2 = str;
        if (!this.drawNum) {
        }
        if (!this.drawLabel) {
        }
        tabShowNew = this.tabShowNew;
        if (tabShowNew == null) {
        }
        tabShowNew2 = this.tabShowNew;
        if (tabShowNew2 == null) {
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public TabShowNew getTabShowNew() {
        return this.tabShowNew;
    }

    public boolean redPointSwitch() {
        if (NavigationBase.getInstance().redPointSwitch()) {
            return true;
        }
        return this.bsAngleSwitch;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    public void setAngleSwitch(boolean z) {
        this.bsAngleSwitch = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void setStateController(StateController stateController) {
        this.stateController = stateController;
    }

    public void setTabShowNew(TabShowNew tabShowNew) {
        this.tabShowNew = tabShowNew;
    }

    public RadioStateDrawable(Context context, Bitmap bitmap, String str, boolean z) {
        this.type = -1;
        this.isReDraw = true;
        this.isBig = z;
        this.label = str;
        this.context = context;
        this.bitmap = bitmap;
    }

    public RadioStateDrawable(Context context, Bitmap bitmap, String str, boolean z, boolean z2, int i2, NavigationParam navigationParam) {
        this.type = -1;
        this.isReDraw = true;
        this.isBig = z;
        this.label = str;
        this.context = context;
        this.bitmap = bitmap;
        this.isNeedCut = z2;
        this.type = i2;
        this.navigationParam = navigationParam;
    }

    public RadioStateDrawable(Context context, int i2, String str) {
        this.type = -1;
        this.isReDraw = true;
        this.context = context;
        this.label = str;
        if (OKLog.D) {
            OKLog.d(TAG, "RadioStateDrawable-imageID=" + i2);
        }
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), i2);
    }

    public RadioStateDrawable(Context context, boolean z, boolean z2) {
        this.type = -1;
        this.isReDraw = true;
        this.context = context;
        this.isBig = z2;
        if (z) {
            this.noIcon = true;
        }
    }
}
