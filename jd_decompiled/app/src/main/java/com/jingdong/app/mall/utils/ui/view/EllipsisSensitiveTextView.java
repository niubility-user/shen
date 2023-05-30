package com.jingdong.app.mall.utils.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;
import com.jingdong.corelib.utils.Log;

/* loaded from: classes4.dex */
public class EllipsisSensitiveTextView extends TextView {

    /* renamed from: g  reason: collision with root package name */
    private int f11972g;

    /* renamed from: h  reason: collision with root package name */
    private a f11973h;

    /* loaded from: classes4.dex */
    public interface a {
        void onChanged(boolean z);
    }

    public EllipsisSensitiveTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f11972g = 1;
    }

    private int a() {
        Layout layout = getLayout();
        if (layout != null) {
            return layout.getEllipsisCount(this.f11972g - 1) > 0 ? 1 : 2;
        } else if (Log.D) {
            Log.d("SizeSensitiveTextView.checkOverLine", "layout is null");
            return 3;
        } else {
            return 3;
        }
    }

    private void b() {
        if (this.f11973h != null) {
            int a2 = a();
            if (a2 != 3) {
                this.f11973h.onChanged(a2 == 1);
            } else if (Log.D) {
                Log.d("SizeSensitiveTextView.notifyOverSizeChanged", "over size state is unknown, ignore and do not notify listener");
            }
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b();
    }

    public EllipsisSensitiveTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11972g = 1;
    }
}
