package com.jingdong.app.mall.bundle.jd_component.pentagram.view;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.RadarColorData;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.RadarColorDataItem;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.RadarColorObject;
import com.jingdong.app.mall.bundle.jd_component.pentagram.entity.RaderViewData;
import com.jingdong.app.mall.bundle.jd_component.pentagram.utils.PentagramViewUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes2.dex */
public class RadarView extends View {
    private float angle;
    private String content;
    private int count;
    private int dimensionCount;
    private int mHeight;
    private int mWidth;
    private Paint mainPaint;
    private float maxValue;
    private float outLineWidth;
    private Paint radarBgPaint;
    private RadarColorData radarColorData;
    private List<RadarColorDataItem> radarColorDataItemList;
    private List<RadarColorData> radarColorDataList;
    private List<RaderViewData> raderViewDataList;
    private float radius;
    private List<String> scaleList;
    private Paint spiderLinePaint;
    private float spiderLineWidth;
    private Paint spiderOutPaint;
    private Paint spiderPointPaint;
    private Paint textPaint;
    private float textSize;
    private String title;
    private float valueLineWidth;
    private float valuePointRadius;

    public RadarView(Context context) {
        this(context, null);
    }

    private void drawRegion(Canvas canvas, List<RaderViewData> list) {
        int i2;
        int i3;
        List<RaderViewData> list2 = list;
        if (list2 == null || list.size() == 0) {
            return;
        }
        int min = Math.min(list.size(), 3);
        int i4 = 0;
        int i5 = 0;
        while (i5 < min) {
            if (list2.get(i5).scoreList == null || list2.get(i5).scoreList.size() != list2.get(i4).scoreList.size()) {
                i2 = min;
            } else {
                this.mainPaint.setStrokeWidth(DPIUtil.dip2px(this.valueLineWidth));
                Path path = new Path();
                path.reset();
                int size = this.scaleList.size();
                double[] dArr = new double[size];
                for (int i6 = 0; i6 < this.scaleList.size(); i6++) {
                    dArr[i6] = Double.parseDouble(this.scaleList.get(i6));
                }
                Arrays.sort(dArr);
                int i7 = 0;
                while (i7 < this.dimensionCount) {
                    double parseDouble = Double.parseDouble(list2.get(i5).scoreList.get(i7).getScore());
                    int i8 = size - 1;
                    double d = i8;
                    double d2 = 1.0d;
                    Double.isNaN(d);
                    double d3 = 1.0d / d;
                    while (i4 < size) {
                        double d4 = dArr[i4];
                        if (i4 != 0 || parseDouble > d4) {
                            if (parseDouble <= d4) {
                                int i9 = i4 - 1;
                                double d5 = dArr[i9];
                                i3 = min;
                                double d6 = i9;
                                Double.isNaN(d6);
                                d2 = (d6 * d3) + (((parseDouble - d5) * d3) / (d4 - d5));
                                break;
                            } else {
                                i3 = min;
                                if (i4 == i8 && parseDouble > d4) {
                                    break;
                                }
                                i4++;
                                min = i3;
                            }
                        } else {
                            break;
                        }
                    }
                    i3 = min;
                    d2 = 0.0d;
                    double d7 = this.radius;
                    float f2 = i7;
                    double d8 = this.angle * f2;
                    Double.isNaN(d8);
                    double sin = Math.sin(3.141592653589793d - d8);
                    Double.isNaN(d7);
                    float f3 = (float) (d7 * sin * d2);
                    double d9 = this.radius;
                    double d10 = this.angle * f2;
                    Double.isNaN(d10);
                    double cos = Math.cos(3.141592653589793d - d10);
                    Double.isNaN(d9);
                    float f4 = (float) (d9 * cos * d2);
                    if (i7 == 0) {
                        path.moveTo(f3, f4);
                    } else {
                        path.lineTo(f3, f4);
                    }
                    i7++;
                    list2 = list;
                    min = i3;
                    i4 = 0;
                }
                i2 = min;
                path.close();
                this.mainPaint.setStyle(Paint.Style.FILL);
                String str = PentagramViewUtils.isDarkConfig() ? this.radarColorDataItemList.get(i5).mainFillDarkColor : this.radarColorDataItemList.get(i5).mainFillColor;
                if (!TextUtils.isEmpty(str)) {
                    this.mainPaint.setColor(Color.parseColor(str));
                }
                canvas.drawPath(path, this.mainPaint);
                String str2 = PentagramViewUtils.isDarkConfig() ? this.radarColorDataItemList.get(i5).mainLineDarkColor : this.radarColorDataItemList.get(i5).mainLineColor;
                if (!TextUtils.isEmpty(str2)) {
                    this.mainPaint.setColor(Color.parseColor(str2));
                }
                this.mainPaint.setStyle(Paint.Style.STROKE);
                this.mainPaint.setPathEffect(new CornerPathEffect(10.0f));
                canvas.drawPath(path, this.mainPaint);
            }
            i5++;
            list2 = list;
            min = i2;
            i4 = 0;
        }
    }

    private void drawSpiderweb(Canvas canvas) {
        List<RadarColorData> list = this.radarColorDataList;
        if (list == null || list.size() != 2) {
            return;
        }
        RadarColorData radarColorData = PentagramViewUtils.isDarkConfig() ? this.radarColorDataList.get(1) : this.radarColorDataList.get(0);
        this.radarColorData = radarColorData;
        if (!TextUtils.isEmpty(radarColorData.spiderLineColor)) {
            this.spiderLinePaint.setColor(Color.parseColor(this.radarColorData.spiderLineColor));
        }
        this.spiderLinePaint.setStrokeWidth(DPIUtil.dip2px(this.spiderLineWidth));
        float f2 = 0.0f;
        this.spiderLinePaint.setPathEffect(new DashPathEffect(new float[]{6.0f, 3.0f}, 0.0f));
        if (!TextUtils.isEmpty(this.radarColorData.outerLineColor)) {
            this.spiderOutPaint.setColor(Color.parseColor(this.radarColorData.outerLineColor));
        }
        if (!TextUtils.isEmpty(this.radarColorData.spiderPointColor)) {
            this.spiderPointPaint.setColor(Color.parseColor(this.radarColorData.spiderPointColor));
        }
        this.radarBgPaint.setColor(Color.parseColor(PentagramViewUtils.isDarkConfig() ? JDDarkUtil.COLOR_1D1B1B : "#B3FFFFFF"));
        canvas.drawCircle(0.0f, 0.0f, this.radius, this.radarBgPaint);
        Path path = new Path();
        float f3 = this.radius / (this.count - 1);
        int i2 = 0;
        while (true) {
            int i3 = this.count;
            if (i2 >= i3) {
                return;
            }
            float f4 = i2 * f3;
            if (i2 == i3 - 1) {
                canvas.drawCircle(f2, f2, f4, this.spiderOutPaint);
            } else {
                canvas.drawCircle(f2, f2, f4, this.spiderLinePaint);
            }
            int i4 = 0;
            while (i4 < this.dimensionCount) {
                double d = f4;
                float f5 = this.angle;
                float f6 = i4;
                int i5 = i4;
                double sin = Math.sin((f5 / 2.0f) + (f5 * f6));
                Double.isNaN(d);
                float f7 = (float) (sin * d);
                float f8 = this.angle;
                double cos = Math.cos((f8 / 2.0f) + (f8 * f6));
                Double.isNaN(d);
                float f9 = (float) (d * cos);
                if (i2 == this.count - 1) {
                    path.reset();
                    path.moveTo(0.0f, 0.0f);
                    path.lineTo(f7, f9);
                    canvas.drawPath(path, this.spiderLinePaint);
                    this.spiderPointPaint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(f7, f9, DPIUtil.dip2px(this.valuePointRadius), this.spiderPointPaint);
                }
                i4 = i5 + 1;
            }
            i2++;
            f2 = 0.0f;
        }
    }

    private void drawText(Canvas canvas, List<RaderViewData> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        this.textPaint.setTextSize(sp2px(getContext(), this.textSize));
        Paint.FontMetrics fontMetrics = this.textPaint.getFontMetrics();
        float f2 = fontMetrics.descent - fontMetrics.ascent;
        int i2 = 0;
        int i3 = 0;
        while (i3 < this.dimensionCount) {
            String tagName = list.get(i2).scoreList.get(i3).getTagName();
            if (!TextUtils.isEmpty(tagName)) {
                this.title = tagName.substring(i2, Math.min(tagName.length(), 6));
            }
            float measureText = this.textPaint.measureText(this.title);
            StringBuilder sb = new StringBuilder();
            int min = Math.min(list.size(), 3);
            for (int i4 = 0; i4 < min; i4++) {
                if (list.get(i4).scoreList != null && list.get(i4).scoreList.size() == list.get(i2).scoreList.size()) {
                    if (i4 != 0) {
                        sb.append("/");
                    }
                    sb.append(list.get(i4).scoreList.get(i3).getScore());
                }
            }
            String sb2 = sb.toString();
            this.content = sb2;
            float measureText2 = this.textPaint.measureText(sb2);
            double max = Math.max(measureText, measureText2);
            double sin = Math.sin(this.angle / 2.0f);
            Double.isNaN(max);
            float f3 = (float) ((max * sin) / 2.0d);
            double d = this.radius + f3;
            float f4 = i3;
            float f5 = f2;
            double d2 = this.angle * f4;
            Double.isNaN(d2);
            double sin2 = Math.sin(3.141592653589793d - d2);
            Double.isNaN(d);
            float f6 = (float) (d * sin2);
            double d3 = this.radius + f3;
            double d4 = this.angle * f4;
            Double.isNaN(d4);
            double cos = Math.cos(3.141592653589793d - d4);
            Double.isNaN(d3);
            float f7 = (float) (d3 * cos);
            if (f6 < 0.0f) {
                double d5 = this.radius + f3;
                double d6 = this.angle * f4;
                Double.isNaN(d6);
                double sin3 = Math.sin(3.141592653589793d - d6);
                Double.isNaN(d5);
                f6 = ((float) (d5 * sin3)) - measureText;
            }
            if (i3 == 0) {
                float f8 = f5 * 2.0f;
                double d7 = this.radius + f8;
                double d8 = this.angle * f4;
                Double.isNaN(d8);
                double sin4 = Math.sin(3.141592653589793d - d8);
                Double.isNaN(d7);
                f6 = ((float) (d7 * sin4)) - (measureText / 2.0f);
                double d9 = this.radius + f8;
                double d10 = this.angle * f4;
                Double.isNaN(d10);
                double cos2 = Math.cos(3.141592653589793d - d10);
                Double.isNaN(d9);
                f7 = (float) (d9 * cos2);
            }
            this.textPaint.setTypeface(Typeface.DEFAULT);
            if (!TextUtils.isEmpty(this.radarColorData.titleColor)) {
                this.textPaint.setColor(Color.parseColor(this.radarColorData.titleColor));
            }
            canvas.drawText(this.title, f6, f7, this.textPaint);
            Rect rect = new Rect();
            Paint paint = this.textPaint;
            String str = this.title;
            paint.getTextBounds(str, 0, str.length(), rect);
            int height = rect.height();
            float width = f6 + ((rect.width() - measureText2) / 2.0f);
            double d11 = f7;
            double d12 = height;
            Double.isNaN(d12);
            Double.isNaN(d11);
            float f9 = (float) (d11 + (d12 * 1.3d));
            this.textPaint.setTypeface(FontsUtil.getTypeFace(getContext(), 4099));
            StringBuilder sb3 = new StringBuilder();
            for (int i5 = 0; i5 < min; i5++) {
                if (list.get(i5).scoreList != null && list.get(i5).scoreList.size() == list.get(0).scoreList.size()) {
                    if (i5 != 0) {
                        float measureText3 = this.textPaint.measureText(sb3.toString());
                        this.textPaint.setColor(Color.parseColor(JDDarkUtil.COLOR_CCCCCC));
                        canvas.drawText("/", measureText3 + width, f9, this.textPaint);
                        sb3.append("/");
                    }
                    String score = list.get(i5).scoreList.get(i3).getScore();
                    String str2 = PentagramViewUtils.isDarkConfig() ? this.radarColorDataItemList.get(i5).contentDarkColor : this.radarColorDataItemList.get(i5).contentColor;
                    if (!TextUtils.isEmpty(str2)) {
                        this.textPaint.setColor(Color.parseColor(str2));
                    }
                    canvas.drawText(score, this.textPaint.measureText(sb3.toString()) + width, f9, this.textPaint);
                    sb3.append(score);
                }
            }
            i3++;
            f2 = f5;
            i2 = 0;
        }
    }

    private void setup() {
        Paint paint = new Paint();
        this.radarBgPaint = paint;
        paint.setAntiAlias(true);
        this.radarBgPaint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.spiderLinePaint = paint2;
        paint2.setAntiAlias(true);
        this.spiderLinePaint.setStyle(Paint.Style.STROKE);
        Paint paint3 = new Paint();
        this.mainPaint = paint3;
        paint3.setAntiAlias(true);
        this.mainPaint.setStyle(Paint.Style.FILL);
        Paint paint4 = new Paint();
        this.spiderPointPaint = paint4;
        paint4.setAntiAlias(true);
        this.spiderPointPaint.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint();
        this.textPaint = paint5;
        paint5.setAntiAlias(true);
        this.textPaint.setStyle(Paint.Style.FILL);
        setLayerType(1, null);
        Paint paint6 = new Paint();
        this.spiderOutPaint = paint6;
        paint6.setAntiAlias(true);
        this.spiderOutPaint.setStrokeWidth(this.outLineWidth);
        this.spiderOutPaint.setStyle(Paint.Style.FILL);
        this.spiderOutPaint.setMaskFilter(new BlurMaskFilter(40.0f, BlurMaskFilter.Blur.OUTER));
    }

    public static int sp2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.translate(this.mWidth / 2, this.mHeight / 2);
        drawSpiderweb(canvas);
        drawText(canvas, this.raderViewDataList);
        drawRegion(canvas, this.raderViewDataList);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        this.radius = DPIUtil.dip2px(43.0f);
        this.mWidth = i2;
        this.mHeight = i3;
        postInvalidate();
        super.onSizeChanged(i2, i3, i4, i5);
    }

    public void setCircleCount(String str) {
        int parseInt = PentagramViewUtils.parseInt(str, 6);
        this.count = parseInt;
        if (parseInt <= 0) {
            this.count = 6;
        }
    }

    public void setDataList(List<RaderViewData> list, RadarColorObject radarColorObject, List<String> list2) {
        List<RadarColorDataItem> list3;
        if (list.get(0).scoreList == null || list.get(0).scoreList.size() <= 0 || (list3 = radarColorObject.radarColorDataList) == null || list3.size() <= 0) {
            return;
        }
        this.scaleList = list2;
        this.raderViewDataList = list;
        this.radarColorDataList = radarColorObject.radarBgColor;
        this.radarColorDataItemList = radarColorObject.radarColorDataList;
        int size = list.get(0).scoreList.size();
        this.dimensionCount = size;
        double d = size;
        Double.isNaN(d);
        this.angle = (float) (6.283185307179586d / d);
        invalidate();
    }

    public RadarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadarView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.dimensionCount = 5;
        this.maxValue = 500.0f;
        this.spiderLineWidth = 0.6f;
        this.valueLineWidth = 1.0f;
        this.outLineWidth = 4.0f;
        this.valuePointRadius = 2.0f;
        this.textSize = 9.0f;
        setup();
    }
}
