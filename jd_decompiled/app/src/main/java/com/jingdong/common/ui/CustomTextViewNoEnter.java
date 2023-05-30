package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import com.jd.dynamic.DYConstants;
import com.jd.lib.un.global.GlobalThemeController;
import com.jd.lib.un.global.IThemeChange;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.UnLog;

/* loaded from: classes6.dex */
public class CustomTextViewNoEnter extends TextView implements IThemeChange {
    private static final String TAG = "MyTextView";
    private String content;
    private GlobalThemeController controller;
    private Context mContext;
    private int maxLinesNum;
    private int width;

    public CustomTextViewNoEnter(Context context) {
        super(context);
        this.maxLinesNum = 2;
        this.mContext = context;
        init();
    }

    private void init() {
        GlobalThemeController newInstance = GlobalThemeController.newInstance();
        this.controller = newInstance;
        if (newInstance.isCustomTheme()) {
            customTheme();
        }
    }

    @Override // com.jd.lib.un.global.IThemeChange
    public void customTheme() {
        setTextColor(this.controller.getThemeConfig().e());
        setBackgroundColor(this.controller.getThemeConfig().a());
    }

    public int getMaxLinesNum() {
        return this.maxLinesNum;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        String substring;
        this.width = getWidth();
        this.content = getText().toString();
        TextPaint paint = getPaint();
        paint.setColor(getCurrentTextColor());
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f2 = fontMetrics.descent - fontMetrics.ascent;
        int i2 = this.width / 2;
        DpiUtil.dip2px(this.mContext, 2.0f);
        float dip2px = f2 - DpiUtil.dip2px(this.mContext, 3.5f);
        int length = this.content.length();
        float[] fArr = new float[length];
        paint.getTextWidths(this.content, fArr);
        boolean z = UnLog.D;
        float measureText = paint.measureText(this.content);
        if (UnLog.D) {
            UnLog.d(TAG, "  -->> content : " + this.content);
            UnLog.d(TAG, "  -->> measureText : " + measureText);
            UnLog.d(TAG, "  -->> width : " + this.width);
        }
        if (measureText <= this.width - 0) {
            paint.setTextAlign(Paint.Align.LEFT);
            int i3 = this.width / 2;
            DpiUtil.dip2px(this.mContext, 1.0f);
            canvas.drawText(this.content, 0.0f, dip2px, paint);
            if (UnLog.D) {
                UnLog.d(TAG, " one line -->> content : " + this.content);
                return;
            }
            return;
        }
        paint.setTextAlign(Paint.Align.LEFT);
        int maxLinesNum = getMaxLinesNum();
        int i4 = 0;
        for (int i5 = 0; i5 < maxLinesNum && i4 < length; i5++) {
            int i6 = i4;
            int i7 = i6;
            float f3 = 0.0f;
            while (i6 < length && f3 < this.width - 0) {
                f3 += fArr[i6];
                i7 = i6;
                i6++;
            }
            if (f3 < this.width - 0) {
                i7++;
            }
            if (UnLog.D) {
                UnLog.d(TAG, "onDraw --> ======num :" + i7 + DYConstants.DY_REGEX_COMMA + this.content.length());
            }
            int i8 = 1;
            if (i5 == 1) {
                if (f3 >= this.width - 0) {
                    if (i7 - 2 >= i4) {
                        i7 -= 2;
                    }
                    substring = this.content.substring(i4, i7) + "...";
                    i4 = i7;
                    canvas.drawText(substring, 0.0f, (i5 * f2) + dip2px, paint);
                } else {
                    i8 = 1;
                }
            }
            if (maxLinesNum == i8) {
                if (i7 - 2 >= i4) {
                    i7 -= 2;
                }
                substring = this.content.substring(i4, i7) + "...";
            } else {
                substring = this.content.substring(i4, i7);
            }
            i4 = i7;
            canvas.drawText(substring, 0.0f, (i5 * f2) + dip2px, paint);
        }
    }

    public void setMaxLinesNum(int i2) {
        this.maxLinesNum = i2;
    }

    public CustomTextViewNoEnter(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.maxLinesNum = 2;
        this.mContext = context;
        init();
    }

    public CustomTextViewNoEnter(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.maxLinesNum = 2;
        this.mContext = context;
        init();
    }
}
