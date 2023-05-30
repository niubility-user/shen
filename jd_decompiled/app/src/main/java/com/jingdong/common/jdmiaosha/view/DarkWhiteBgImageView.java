package com.jingdong.common.jdmiaosha.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.common.jdmiaosha.utils.DarkUtil;

/* loaded from: classes5.dex */
public class DarkWhiteBgImageView extends SimpleDraweeView {
    private boolean drawWhiteBg;
    private int foregroundColor;

    public DarkWhiteBgImageView(Context context) {
        super(context);
        this.drawWhiteBg = false;
        this.foregroundColor = DarkUtil.PIC_FOREGROUND_COLOR;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        if (getBackground() == null && this.drawWhiteBg) {
            canvas.drawColor(-1);
        }
        super.onDraw(canvas);
        getContext();
        if (DarkUtil.isDarkTheme()) {
            canvas.drawColor(this.foregroundColor);
        }
    }

    public void setDrawWhiteBg(boolean z) {
        this.drawWhiteBg = z;
    }

    public void setForegroundColor(int i2) {
        this.foregroundColor = i2;
    }

    public DarkWhiteBgImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.drawWhiteBg = false;
        this.foregroundColor = DarkUtil.PIC_FOREGROUND_COLOR;
    }
}
