package com.tencent.open.c;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.widget.RelativeLayout;

/* loaded from: classes9.dex */
public class a extends RelativeLayout {
    private static final String a = a.class.getName();
    private Rect b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f17657c;
    private InterfaceC0809a d;

    /* renamed from: com.tencent.open.c.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public interface InterfaceC0809a {
        void a();

        void a(int i2);
    }

    public a(Context context) {
        super(context);
        this.b = null;
        this.f17657c = false;
        this.d = null;
        this.b = new Rect();
    }

    public void a(InterfaceC0809a interfaceC0809a) {
        this.d = interfaceC0809a;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        int size = View.MeasureSpec.getSize(i3);
        Activity activity = (Activity) getContext();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(this.b);
        int height = (activity.getWindowManager().getDefaultDisplay().getHeight() - this.b.top) - size;
        InterfaceC0809a interfaceC0809a = this.d;
        if (interfaceC0809a != null && size != 0) {
            if (height > 100) {
                interfaceC0809a.a((Math.abs(this.b.height()) - getPaddingBottom()) - getPaddingTop());
            } else {
                interfaceC0809a.a();
            }
        }
        super.onMeasure(i2, i3);
    }
}
