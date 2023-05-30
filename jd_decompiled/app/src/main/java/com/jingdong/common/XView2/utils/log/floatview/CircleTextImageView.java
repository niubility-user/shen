package com.jingdong.common.XView2.utils.log.floatview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.AppCompatImageView;
import com.jingdong.app.mall.mylib.R;

/* loaded from: classes5.dex */
public class CircleTextImageView extends AppCompatImageView {
    private static final int COLORDRAWABLE_DIMENSION = 2;
    private static final int DEFAULT_BORDER_COLOR = -16777216;
    private static final boolean DEFAULT_BORDER_OVERLAY = false;
    private static final int DEFAULT_BORDER_WIDTH = 0;
    private static final int DEFAULT_FILL_COLOR = 0;
    private static final int DEFAULT_TEXT_COLOR = -16777216;
    private static final int DEFAULT_TEXT_PADDING = 4;
    private static final int DEFAULT_TEXT_SIZE = 22;
    private Bitmap mBitmap;
    private int mBitmapHeight;
    private final Paint mBitmapPaint;
    private BitmapShader mBitmapShader;
    private int mBitmapWidth;
    private int mBorderColor;
    private boolean mBorderOverlay;
    private final Paint mBorderPaint;
    private float mBorderRadius;
    private final RectF mBorderRect;
    private int mBorderWidth;
    private ColorFilter mColorFilter;
    private float mDrawableRadius;
    private final RectF mDrawableRect;
    private int mFillColor;
    private final Paint mFillPaint;
    private boolean mReady;
    private boolean mSetupPending;
    private final Matrix mShaderMatrix;
    private int mTextColor;
    private int mTextPadding;
    private final Paint mTextPaint;
    private int mTextSize;
    private String mTextString;
    private static final ImageView.ScaleType SCALE_TYPE = ImageView.ScaleType.CENTER_CROP;
    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;

    public CircleTextImageView(Context context) {
        super(context);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mFillPaint = new Paint();
        this.mTextPaint = new Paint();
        this.mBorderColor = -16777216;
        this.mBorderWidth = 0;
        this.mFillColor = 0;
        this.mTextColor = -16777216;
        this.mTextSize = 22;
        this.mTextPadding = 4;
        init();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        Bitmap createBitmap;
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            if (drawable instanceof ColorDrawable) {
                createBitmap = Bitmap.createBitmap(2, 2, BITMAP_CONFIG);
            } else {
                createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private void init() {
        super.setScaleType(SCALE_TYPE);
        this.mReady = true;
        if (this.mSetupPending) {
            setup();
            this.mSetupPending = false;
        }
    }

    private void setup() {
        if (!this.mReady) {
            this.mSetupPending = true;
        } else if (getWidth() == 0 && getHeight() == 0) {
        } else {
            if (this.mBitmap == null && TextUtils.isEmpty(this.mTextString)) {
                invalidate();
                return;
            }
            this.mTextPaint.setAntiAlias(true);
            this.mTextPaint.setColor(this.mTextColor);
            this.mTextPaint.setTextSize(this.mTextSize);
            this.mBorderPaint.setStyle(Paint.Style.STROKE);
            this.mBorderPaint.setAntiAlias(true);
            this.mBorderPaint.setColor(this.mBorderColor);
            this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
            this.mFillPaint.setStyle(Paint.Style.FILL);
            this.mFillPaint.setAntiAlias(true);
            this.mFillPaint.setColor(this.mFillColor);
            this.mBorderRect.set(0.0f, 0.0f, getWidth(), getHeight());
            this.mBorderRadius = Math.min((this.mBorderRect.height() - this.mBorderWidth) / 2.0f, (this.mBorderRect.width() - this.mBorderWidth) / 2.0f);
            this.mDrawableRect.set(this.mBorderRect);
            if (!this.mBorderOverlay) {
                RectF rectF = this.mDrawableRect;
                int i2 = this.mBorderWidth;
                rectF.inset(i2, i2);
            }
            this.mDrawableRadius = Math.min(this.mDrawableRect.height() / 2.0f, this.mDrawableRect.width() / 2.0f);
            if (this.mBitmap != null) {
                Bitmap bitmap = this.mBitmap;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                this.mBitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
                this.mBitmapHeight = this.mBitmap.getHeight();
                this.mBitmapWidth = this.mBitmap.getWidth();
                this.mBitmapPaint.setAntiAlias(true);
                this.mBitmapPaint.setShader(this.mBitmapShader);
                updateShaderMatrix();
            }
            invalidate();
        }
    }

    private void updateShaderMatrix() {
        float width;
        float height;
        this.mShaderMatrix.set(null);
        float f2 = 0.0f;
        if (this.mBitmapWidth * this.mDrawableRect.height() > this.mDrawableRect.width() * this.mBitmapHeight) {
            width = this.mDrawableRect.height() / this.mBitmapHeight;
            f2 = (this.mDrawableRect.width() - (this.mBitmapWidth * width)) * 0.5f;
            height = 0.0f;
        } else {
            width = this.mDrawableRect.width() / this.mBitmapWidth;
            height = (this.mDrawableRect.height() - (this.mBitmapHeight * width)) * 0.5f;
        }
        this.mShaderMatrix.setScale(width, width);
        Matrix matrix = this.mShaderMatrix;
        RectF rectF = this.mDrawableRect;
        matrix.postTranslate(((int) (f2 + 0.5f)) + rectF.left, ((int) (height + 0.5f)) + rectF.top);
        this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public int getBorderWidth() {
        return this.mBorderWidth;
    }

    public int getFillColor() {
        return this.mFillColor;
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public int getTextPadding() {
        return this.mTextPadding;
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    public String getTextString() {
        return this.mTextString;
    }

    public boolean isBorderOverlay() {
        return this.mBorderOverlay;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mBitmap == null && TextUtils.isEmpty(this.mTextString)) {
            return;
        }
        if (this.mFillColor != 0) {
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, this.mDrawableRadius, this.mFillPaint);
        }
        if (this.mBitmap != null) {
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, this.mDrawableRadius, this.mBitmapPaint);
        }
        if (this.mBorderWidth != 0) {
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, this.mBorderRadius, this.mBorderPaint);
        }
        if (TextUtils.isEmpty(this.mTextString)) {
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = this.mTextPaint.getFontMetricsInt();
        canvas.drawText(this.mTextString, (getWidth() / 2) - (this.mTextPaint.measureText(this.mTextString) / 2.0f), ((getHeight() / 2) - fontMetricsInt.descent) + ((fontMetricsInt.bottom - fontMetricsInt.top) / 2), this.mTextPaint);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i2);
        View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        View.MeasureSpec.getSize(i3);
        if (TextUtils.isEmpty(this.mTextString)) {
            return;
        }
        int measureText = ((int) this.mTextPaint.measureText(this.mTextString)) + (this.mTextPadding * 2);
        if (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
            if (measureText > getMeasuredWidth() || measureText > getMeasuredHeight()) {
                setMeasuredDimension(measureText, measureText);
            }
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        setup();
    }

    @Override // android.widget.ImageView
    public void setAdjustViewBounds(boolean z) {
        if (z) {
            throw new IllegalArgumentException("adjustViewBounds not supported.");
        }
    }

    public void setBorderColor(@ColorInt int i2) {
        if (i2 == this.mBorderColor) {
            return;
        }
        this.mBorderColor = i2;
        this.mBorderPaint.setColor(i2);
        invalidate();
    }

    public void setBorderColorResource(@ColorRes int i2) {
        setBorderColor(getContext().getResources().getColor(i2));
    }

    public void setBorderOverlay(boolean z) {
        if (z == this.mBorderOverlay) {
            return;
        }
        this.mBorderOverlay = z;
        setup();
    }

    public void setBorderWidth(int i2) {
        if (i2 == this.mBorderWidth) {
            return;
        }
        this.mBorderWidth = i2;
        setup();
    }

    @Override // android.widget.ImageView
    public void setColorFilter(ColorFilter colorFilter) {
        if (colorFilter == this.mColorFilter) {
            return;
        }
        this.mColorFilter = colorFilter;
        this.mBitmapPaint.setColorFilter(colorFilter);
        invalidate();
    }

    public void setFillColor(@ColorInt int i2) {
        if (i2 == this.mFillColor) {
            return;
        }
        this.mFillColor = i2;
        this.mFillPaint.setColor(i2);
        invalidate();
    }

    public void setFillColorResource(@ColorRes int i2) {
        setFillColor(getContext().getResources().getColor(i2));
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.mBitmap = bitmap;
        setup();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.mBitmap = getBitmapFromDrawable(drawable);
        setup();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(@DrawableRes int i2) {
        super.setImageResource(i2);
        this.mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        this.mBitmap = uri != null ? getBitmapFromDrawable(getDrawable()) : null;
        setup();
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (scaleType != SCALE_TYPE) {
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", scaleType));
        }
    }

    public void setText(@StringRes int i2) {
        setText(getResources().getString(i2));
    }

    public void setTextColor(@ColorInt int i2) {
        this.mTextColor = i2;
        this.mTextPaint.setColor(i2);
        invalidate();
    }

    public void setTextColorResource(@ColorRes int i2) {
        setTextColor(getResources().getColor(i2));
    }

    public void setTextPadding(int i2) {
        this.mTextPadding = i2;
        invalidate();
    }

    public void setTextSize(int i2) {
        this.mTextSize = i2;
        this.mTextPaint.setTextSize(i2);
        invalidate();
    }

    public void setText(String str) {
        this.mTextString = str;
        invalidate();
    }

    public CircleTextImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleTextImageView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mDrawableRect = new RectF();
        this.mBorderRect = new RectF();
        this.mShaderMatrix = new Matrix();
        this.mBitmapPaint = new Paint();
        this.mBorderPaint = new Paint();
        this.mFillPaint = new Paint();
        this.mTextPaint = new Paint();
        this.mBorderColor = -16777216;
        this.mBorderWidth = 0;
        this.mFillColor = 0;
        this.mTextColor = -16777216;
        this.mTextSize = 22;
        this.mTextPadding = 4;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleTextImageView, i2, 0);
        this.mBorderWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CircleTextImageView_border_width, 0);
        this.mBorderColor = obtainStyledAttributes.getColor(R.styleable.CircleTextImageView_border_color, -16777216);
        this.mBorderOverlay = obtainStyledAttributes.getBoolean(R.styleable.CircleTextImageView_border_overlay, false);
        this.mFillColor = obtainStyledAttributes.getColor(R.styleable.CircleTextImageView_fill_color, 0);
        this.mTextString = obtainStyledAttributes.getString(R.styleable.CircleTextImageView_text_content);
        this.mTextColor = obtainStyledAttributes.getColor(R.styleable.CircleTextImageView_text_color, -16777216);
        this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CircleTextImageView_text_size, 22);
        this.mTextPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.CircleTextImageView_text_padding, 4);
        obtainStyledAttributes.recycle();
        init();
    }
}
