package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
public class CustomTextView extends TextView implements IThemeChange {
    private static final String TAG = "MyTextView";
    private String content;
    private GlobalThemeController controller;
    private Context mContext;
    private int width;

    public CustomTextView(Context context) {
        super(context);
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

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        this.width = getWidth();
        this.content = getText().toString();
        int dip2px = DpiUtil.dip2px(this.mContext, 10.0f);
        TextPaint paint = getPaint();
        int i2 = 0;
        paint.setColor(Color.argb(255, 0, 0, 0));
        paint.setTextSize(DpiUtil.dip2px(this.mContext, 14.0f));
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f2 = fontMetrics.descent - fontMetrics.ascent;
        int i3 = this.width / 2;
        DpiUtil.dip2px(this.mContext, 1.0f);
        float dip2px2 = f2 - DpiUtil.dip2px(this.mContext, 1.0f);
        int length = this.content.length();
        float[] fArr = new float[length];
        paint.getTextWidths(this.content, fArr);
        if (paint.measureText(this.content) <= this.width - dip2px) {
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(this.content, (this.width / 2) - DpiUtil.dip2px(this.mContext, 1.0f), dip2px2, paint);
            return;
        }
        paint.setTextAlign(Paint.Align.LEFT);
        int i4 = 0;
        while (i2 < 2 && i4 < length) {
            int i5 = i4;
            int i6 = i5;
            float f3 = 0.0f;
            while (i5 < length && f3 < this.width - dip2px) {
                f3 += fArr[i5];
                i6 = i5;
                i5++;
            }
            int i7 = i6 + 1;
            if (UnLog.D) {
                UnLog.d(TAG, "onDraw --> ======num :" + i7 + DYConstants.DY_REGEX_COMMA + this.content.length());
            }
            canvas.drawText(this.content.substring(i4, i7), 0.0f, (i2 * f2) + dip2px2, paint);
            i2++;
            i4 = i7;
        }
    }

    public CustomTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mContext = context;
        init();
    }

    public CustomTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }
}
