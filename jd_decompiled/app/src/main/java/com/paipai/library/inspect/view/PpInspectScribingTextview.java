package com.paipai.library.inspect.view;

import android.content.Context;
import android.text.TextPaint;
import android.util.AttributeSet;
import com.jingdong.common.ui.PdAutoChangeTextSize;

/* loaded from: classes9.dex */
public class PpInspectScribingTextview extends PdAutoChangeTextSize {
    private int mOriginalPaintFlag;

    public PpInspectScribingTextview(Context context) {
        super(context);
        this.mOriginalPaintFlag = 0;
        setup();
    }

    private void setup() {
        this.mOriginalPaintFlag = getPaint().getFlags();
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        TextPaint paint = getPaint();
        if (z) {
            paint.setFlags(this.mOriginalPaintFlag);
        } else {
            paint.setFlags(17);
        }
    }

    public PpInspectScribingTextview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOriginalPaintFlag = 0;
        setup();
    }
}
