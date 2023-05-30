package com.jingdong.common.unification.uniconfig;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jd.lib.un.business.widget.a;
import com.jingdong.app.mall.bundle.mobileConfig.JDMobileConfig;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;

/* loaded from: classes6.dex */
public class UnTextNineDrawable extends NinePatchDrawable {
    private static final Rect EMPTY_RECT = new Rect();
    private int bitmapHeight;
    private int bitmapWidth;
    private Rect drawRect;
    private boolean isElderMode;
    private Paint mPaint;
    private String mText;
    private int mTextColor;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private Rect textRect;

    public UnTextNineDrawable(Bitmap bitmap, byte[] bArr, Rect rect, String str) {
        super(bitmap, bArr, rect, str);
        this.drawRect = new Rect();
        this.textRect = new Rect();
        this.mPaint = new Paint();
        init(bitmap);
    }

    private void drawText(Canvas canvas) {
        if (TextUtils.isEmpty(this.mText)) {
            return;
        }
        canvas.drawText(this.mText, this.paddingLeft, (this.drawRect.bottom - this.paddingBottom) - this.mPaint.getFontMetrics().descent, this.mPaint);
    }

    private void init(Bitmap bitmap) {
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(-16777216);
        if (bitmap != null) {
            this.bitmapWidth = bitmap.getWidth();
            this.bitmapHeight = bitmap.getHeight();
        }
    }

    private int scaleValue(int i2, boolean z) {
        float density = DpiUtil.getDensity(a.g().d());
        if (UnLog.D) {
            UnLog.d("Uniocn", "dpi:" + density);
        }
        DpiUtil.getWidth(a.g().d());
        float f2 = (density * (z ? 1.3f : 1.0f)) / 3.0f;
        if (UnLog.D) {
            UnLog.d("Uniocn", "scale:" + f2);
        }
        return (int) (i2 * f2);
    }

    private void superSetBounds(@NonNull Rect rect) {
        super.setBounds(rect.left, rect.top, rect.right, rect.bottom);
    }

    private void updateDrawBounds() {
        int width = this.textRect.width() + this.paddingLeft + this.paddingRight;
        int height = this.textRect.height() + this.paddingTop + this.paddingBottom;
        String str = "updateDrawBounds:" + this;
        String str2 = "start: height:" + height + "  bitmapHeight:" + this.bitmapHeight + "  paddingBottom:" + this.paddingBottom + "  paddingTop:" + this.paddingTop;
        int i2 = this.bitmapWidth;
        if (i2 > width) {
            width = i2;
        }
        if (this.textRect.height() > 0) {
            int i3 = this.bitmapHeight - height;
            if (i3 > 0) {
                int i4 = i3 / 2;
                this.paddingBottom += i4;
                this.paddingTop += i4;
            }
            int height2 = this.textRect.height() + this.paddingTop;
            int i5 = this.paddingBottom;
            height = height2 + i5;
            int i6 = this.bitmapHeight;
            if (i6 > height) {
                this.paddingBottom = (i5 + i6) - height;
                height = i6;
            }
        }
        String str3 = "updateDrawBounds:" + this;
        String str4 = "end: height:" + height + "  bitmapHeight:" + this.bitmapHeight + "  paddingBottom:" + this.paddingBottom + "  paddingTop:" + this.paddingTop;
        this.drawRect.set(0, 0, width, height);
        superSetBounds(this.drawRect);
    }

    private void updateTextBounds() {
        if (TextUtils.isEmpty(this.mText)) {
            this.textRect = EMPTY_RECT;
            return;
        }
        Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
        int i2 = (int) (fontMetrics.descent - fontMetrics.ascent);
        this.textRect.set(0, 0, (int) this.mPaint.measureText(this.mText), i2);
    }

    private float valueToPx(int i2, float f2, boolean z) {
        String config = JDMobileConfig.getInstance().getConfig("unification", UnIconConfigConstants.ICON_DIR, "fontScale");
        if (!TextUtils.isEmpty(config) && TextUtils.equals(config, "0")) {
            z = false;
        }
        return (!z || DpiUtil.getDensity(a.g().d()) > 3.0f) ? TypedValue.applyDimension(i2, f2, Resources.getSystem().getDisplayMetrics()) : f2 * 3.0f;
    }

    @Override // android.graphics.drawable.NinePatchDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawText(canvas);
    }

    @Override // android.graphics.drawable.NinePatchDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.drawRect.height();
    }

    @Override // android.graphics.drawable.NinePatchDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.drawRect.width();
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(@NonNull Rect rect) {
        super.setBounds(rect);
    }

    public void setPadding(int i2, int i3, int i4, int i5) {
        this.paddingLeft = i2;
        this.paddingTop = i3;
        this.paddingRight = i4;
        this.paddingBottom = i5;
        updateDrawBounds();
        invalidateSelf();
    }

    public void setText(String str) {
        if (TextUtils.equals(this.mText, str)) {
            return;
        }
        this.mText = str;
        updateTextBounds();
        updateDrawBounds();
        invalidateSelf();
    }

    public void setTextColor(@ColorInt int i2) {
        if (this.mTextColor == i2) {
            return;
        }
        this.mTextColor = i2;
        this.mPaint.setColor(i2);
        invalidateSelf();
    }

    public void setTextSize(float f2) {
        this.mPaint.setTextSize(valueToPx(2, f2, false));
        updateTextBounds();
        updateDrawBounds();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i2, int i3, int i4, int i5) {
        super.setBounds(i2, i3, i4, i5);
    }

    public UnTextNineDrawable(Resources resources, Bitmap bitmap, byte[] bArr, Rect rect, String str, boolean z) {
        super(resources, bitmap, bArr, rect, str);
        this.drawRect = new Rect();
        this.textRect = new Rect();
        this.mPaint = new Paint();
        this.isElderMode = z;
        init(bitmap);
    }

    public void setTextSize(int i2, float f2) {
        this.mPaint.setTextSize(valueToPx(i2, f2, false));
        updateTextBounds();
        updateDrawBounds();
        invalidateSelf();
    }

    public void setTextSize(int i2, float f2, boolean z) {
        this.mPaint.setTextSize(valueToPx(i2, f2, z));
        updateTextBounds();
        updateDrawBounds();
        invalidateSelf();
    }

    @RequiresApi(api = 19)
    public UnTextNineDrawable(@NonNull NinePatch ninePatch) {
        super(ninePatch);
        this.drawRect = new Rect();
        this.textRect = new Rect();
        this.mPaint = new Paint();
        init(ninePatch.getBitmap());
    }

    @RequiresApi(api = 19)
    public UnTextNineDrawable(@Nullable Resources resources, @NonNull NinePatch ninePatch) {
        super(resources, ninePatch);
        this.drawRect = new Rect();
        this.textRect = new Rect();
        this.mPaint = new Paint();
        init(ninePatch.getBitmap());
    }
}
