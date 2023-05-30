package com.jdpay.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public class BarCodeImageView extends View {
    private final int CODE_TEXT_SIZE_IN_DP;
    private final int IMAGE_HEIGHT_IN_DP;
    private final int IMAGE_HORIZONTAL_PADDING_IN_PERCENT;
    private final int PADDING_IN_PX;
    private final int TIPS_TEXT_SIZE_IN_DP;
    private final int TIPS_TOP_IN_PERCENT;
    private Bitmap bmCode;
    private Bitmap bmTipsIcon;
    private float codeTextSize;
    private float codeTextWidth;
    private float contentHeightInPx;
    private final RectF imgRect;
    private String mBarCode;
    private Paint mPaintImage;
    private Paint paintCodeText;
    private Paint paintTipsText;
    private float screenHeightInDp;
    private float screenWidthInDp;
    private String tips;
    private int tipsIconHeight;
    private int tipsIconWidth;
    private float tipsTextSize;
    private float tipsTextWidth;

    public BarCodeImageView(Context context) {
        super(context);
        this.PADDING_IN_PX = 30;
        this.IMAGE_HEIGHT_IN_DP = 150;
        this.TIPS_TOP_IN_PERCENT = 15;
        this.IMAGE_HORIZONTAL_PADDING_IN_PERCENT = 10;
        this.TIPS_TEXT_SIZE_IN_DP = 14;
        this.CODE_TEXT_SIZE_IN_DP = 26;
        this.imgRect = new RectF();
        initView(context);
    }

    public BarCodeImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.PADDING_IN_PX = 30;
        this.IMAGE_HEIGHT_IN_DP = 150;
        this.TIPS_TOP_IN_PERCENT = 15;
        this.IMAGE_HORIZONTAL_PADDING_IN_PERCENT = 10;
        this.TIPS_TEXT_SIZE_IN_DP = 14;
        this.CODE_TEXT_SIZE_IN_DP = 26;
        this.imgRect = new RectF();
        initView(context);
    }

    private float dip2px(float f2) {
        return f2 * getContext().getResources().getDisplayMetrics().density;
    }

    private void initView(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.screenWidthInDp = px2dip(displayMetrics.widthPixels);
        this.screenHeightInDp = px2dip(displayMetrics.heightPixels);
        float f2 = displayMetrics.density;
        this.codeTextSize = 26.0f * f2;
        this.tipsTextSize = f2 * 14.0f;
        Paint paint = new Paint(1);
        this.paintCodeText = paint;
        paint.setColor(-14474461);
        this.paintCodeText.setStyle(Paint.Style.FILL);
        this.paintCodeText.setTextSize(this.codeTextSize);
        this.paintCodeText.setFakeBoldText(true);
        Paint paint2 = new Paint(this.paintCodeText);
        this.paintTipsText = paint2;
        paint2.setTextSize(this.tipsTextSize);
        this.paintTipsText.setColor(-32742);
        Paint paint3 = new Paint();
        this.mPaintImage = paint3;
        paint3.setStyle(Paint.Style.FILL);
        this.mPaintImage.setColor(-16711936);
    }

    private float px2dip(float f2) {
        return f2 / getContext().getResources().getDisplayMetrics().density;
    }

    public String formatQrCode(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        while (i2 < str.length()) {
            sb.append(str.charAt(i2));
            sb.append(' ');
            i2++;
            if (i2 % 4 == 0 && i2 != 16) {
                sb.append(' ');
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f2;
        float f3;
        super.onDraw(canvas);
        if (TextUtils.isEmpty(this.mBarCode) || this.bmCode == null) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        boolean z = width < height;
        if (z) {
            f2 = (width * 15) / 100;
            canvas.rotate(90.0f, width / 2, height / 2);
        } else {
            f2 = (height * 15) / 100;
        }
        if (!TextUtils.isEmpty(this.tips)) {
            if (z) {
                f2 += (height - width) / 2.0f;
            }
            if (this.bmTipsIcon == null) {
                f3 = (width - this.tipsTextWidth) / 2.0f;
            } else {
                float f4 = this.tipsIconHeight;
                float f5 = (f4 - this.tipsTextSize) / 2.0f;
                int i2 = this.tipsIconWidth;
                float f6 = (((float) ((width - i2) - 30)) - this.tipsTextWidth) / 2.0f;
                int i3 = (f5 > 0.0f ? 1 : (f5 == 0.0f ? 0 : -1));
                float f7 = f2 + (i3 >= 0 ? 0.0f : -f5);
                this.imgRect.set(f6, f7, i2 + f6, f4 + f7);
                canvas.drawBitmap(this.bmTipsIcon, (Rect) null, this.imgRect, this.mPaintImage);
                f3 = this.tipsIconWidth + 30 + f6;
                if (i3 <= 0) {
                    f5 = 0.0f;
                }
                f2 = f7 + f5;
            }
            canvas.drawText(this.tips, f3, f2 - this.paintTipsText.ascent(), this.paintTipsText);
        }
        float f8 = width;
        float f9 = (height - this.contentHeightInPx) / 2.0f;
        canvas.drawText(this.mBarCode, (f8 - this.codeTextWidth) / 2.0f, f9 - this.paintCodeText.ascent(), this.paintCodeText);
        int i4 = z ? height : width;
        float f10 = (i4 * 10) / 100;
        if ((2.0f * f10) + this.bmCode.getWidth() > i4) {
            f10 = (i4 - this.bmCode.getWidth()) / 2;
        }
        if (z) {
            f10 -= (height - width) / 2;
        }
        RectF rectF = this.imgRect;
        rectF.left = f10;
        rectF.right = f8 - f10;
        float f11 = f9 + this.codeTextSize + 30.0f;
        rectF.top = f11;
        rectF.bottom = f11 + dip2px(150.0f);
        canvas.drawBitmap(this.bmCode, (Rect) null, this.imgRect, this.mPaintImage);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        float mode = View.MeasureSpec.getMode(i2);
        float size = View.MeasureSpec.getSize(i2);
        float mode2 = View.MeasureSpec.getMode(i3);
        float size2 = View.MeasureSpec.getSize(i3);
        if (mode == 1.07374182E9f || mode == -2.14748365E9f) {
            this.screenWidthInDp = px2dip(size);
        } else {
            size = dip2px(this.screenWidthInDp);
        }
        if (mode2 == 1.07374182E9f || mode2 == -2.14748365E9f) {
            this.screenHeightInDp = px2dip(size2);
        } else {
            size2 = dip2px(this.screenHeightInDp);
        }
        setMeasuredDimension((int) size, (int) size2);
    }

    public void setBarCode(String str) {
        this.mBarCode = formatQrCode(str);
    }

    public void setBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        this.bmCode = bitmap;
    }

    public void setTips(@Nullable String str, @Nullable Bitmap bitmap) {
        this.tips = str;
        this.bmTipsIcon = bitmap;
    }

    public void update() {
        if (TextUtils.isEmpty(this.mBarCode) || this.bmCode == null) {
            invalidate();
            return;
        }
        this.codeTextWidth = this.paintCodeText.measureText(this.mBarCode);
        if (TextUtils.isEmpty(this.tips)) {
            this.tipsIconHeight = 0;
            this.tipsIconWidth = 0;
            this.tipsTextWidth = 0.0f;
        } else {
            this.tipsTextWidth = this.paintTipsText.measureText(this.tips);
            Bitmap bitmap = this.bmTipsIcon;
            if (bitmap == null) {
                this.tipsIconHeight = 0;
                this.tipsIconWidth = 0;
            } else {
                this.tipsIconWidth = bitmap.getWidth();
                this.tipsIconHeight = this.bmTipsIcon.getHeight();
            }
        }
        this.contentHeightInPx = this.codeTextSize + 30.0f + dip2px(150.0f);
        invalidate();
    }
}
