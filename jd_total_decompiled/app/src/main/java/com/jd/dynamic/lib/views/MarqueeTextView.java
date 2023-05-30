package com.jd.dynamic.lib.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* loaded from: classes13.dex */
public class MarqueeTextView extends AppCompatTextView {
    public MarqueeTextView(Context context) {
        super(context);
        a();
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public MarqueeTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    private void a() {
        setMarqueeRepeatLimit(-1);
        setGravity(19);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b() {
        setSelected(true);
    }

    public void finishSetting() {
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setSingleLine(true);
        post(new Runnable() { // from class: com.jd.dynamic.lib.views.o
            @Override // java.lang.Runnable
            public final void run() {
                MarqueeTextView.this.b();
            }
        });
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public void onWindowFocusChanged(boolean z) {
        if (z) {
            super.onWindowFocusChanged(z);
        }
    }
}
