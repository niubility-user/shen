package com.jingdong.common.recommend.ui.homerecommend;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.RecommendImageUtils;
import com.jingdong.common.recommend.RecommendPathUtil;
import com.jingdong.sdk.utils.DPIUtil;
import javax.annotation.Nullable;

/* loaded from: classes6.dex */
public class HomeCardLabelLayout extends AppCompatTextView {
    private BaseActivity baseActivity;
    boolean isAnalysis;
    private int mNineHeight;
    private NinePatch mNinePatch;
    private Paint mPaint;
    private Path mPath;
    private int preWidth;

    public HomeCardLabelLayout(Context context) {
        super(context);
        this.mPaint = new Paint(1);
        this.mPath = new Path();
    }

    private void drawBg(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        if (!this.isAnalysis || this.mPath.isEmpty() || this.preWidth != width) {
            this.preWidth = width;
            initPath(width, height);
            this.isAnalysis = true;
        }
        if (this.mNinePatch != null) {
            int saveLayer = canvas.saveLayer(new RectF(0.0f, 0.0f, width, height), this.mPaint, 31);
            canvas.drawPath(this.mPath, this.mPaint);
            this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            this.mNinePatch.draw(canvas, new Rect(0, 0, width, height), this.mPaint);
            this.mPaint.setXfermode(null);
            canvas.restoreToCount(saveLayer);
            return;
        }
        canvas.drawPath(this.mPath, this.mPaint);
    }

    private void initPaint(int i2, @Nullable float[] fArr) {
        int[] iArr = {-381927, -381927};
        this.mPaint.setStyle(Paint.Style.FILL);
        if (fArr != null && 2 != fArr.length) {
            fArr = null;
        }
        this.mPaint.setShader(new LinearGradient(0.0f, 0.0f, i2, 0.0f, iArr, fArr, Shader.TileMode.CLAMP));
    }

    private void initPath(int i2, int i3) {
        initPaint(i2, new float[]{0.0f, 0.4f, 1.0f});
        Context context = this.baseActivity;
        if (context == null) {
            context = getContext();
        }
        DPIUtil.getWidthByDesignValue750(context, 4);
        float f2 = i3;
        this.mPath.reset();
        Context context2 = this.baseActivity;
        if (context2 == null) {
            context2 = getContext();
        }
        float widthByDesignValue750 = DPIUtil.getWidthByDesignValue750(context2, 4);
        float f3 = 0.5522848f * widthByDesignValue750;
        this.mPath.moveTo(widthByDesignValue750, 0.0f);
        float f4 = i2;
        this.mPath.lineTo(f4 - widthByDesignValue750, 0.0f);
        RecommendPathUtil.addCircleT2R(0.0f, f4, widthByDesignValue750, f3, this.mPath);
        this.mPath.lineTo(f4, f2 - widthByDesignValue750);
        RecommendPathUtil.addCircleR2B(f4, f2, widthByDesignValue750, f3, this.mPath);
        this.mPath.lineTo(widthByDesignValue750, f2);
        RecommendPathUtil.addCircleB2L(f2, 0.0f, widthByDesignValue750, f3, this.mPath);
        this.mPath.lineTo(0.0f, widthByDesignValue750);
        RecommendPathUtil.addCircleL2T(0.0f, 0.0f, widthByDesignValue750, f3, this.mPath);
        this.mPath.close();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        drawBg(canvas);
        super.onDraw(canvas);
    }

    public void setBackgroudWithFixedRadius(BaseActivity baseActivity, String str) {
        if (TextUtils.isEmpty(str) || baseActivity == null) {
            return;
        }
        this.baseActivity = baseActivity;
        JDImageLoader.getBitmap(str, null, new ImageRequestListener<Bitmap>() { // from class: com.jingdong.common.recommend.ui.homerecommend.HomeCardLabelLayout.1
            @Override // com.jd.mobile.image.ImageRequestListener
            public void onCancel() {
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onFailure(Throwable th) {
            }

            @Override // com.jd.mobile.image.ImageRequestListener
            public void onSuccess(Bitmap bitmap) {
                int widthByDesignValue750 = DPIUtil.getWidthByDesignValue750((Activity) HomeCardLabelLayout.this.baseActivity, 32);
                Bitmap scaleBitmap = RecommendImageUtils.getScaleBitmap(bitmap, widthByDesignValue750);
                byte[] xNinePatchChunk = RecommendImageUtils.getXNinePatchChunk(scaleBitmap, 0.4f);
                if (xNinePatchChunk != null) {
                    HomeCardLabelLayout.this.mNinePatch = new NinePatch(scaleBitmap, xNinePatchChunk, null);
                    HomeCardLabelLayout.this.mNineHeight = widthByDesignValue750;
                    HomeCardLabelLayout.this.postInvalidate();
                }
            }
        });
    }

    public HomeCardLabelLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPaint = new Paint(1);
        this.mPath = new Path();
    }

    public HomeCardLabelLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mPaint = new Paint(1);
        this.mPath = new Path();
    }
}
