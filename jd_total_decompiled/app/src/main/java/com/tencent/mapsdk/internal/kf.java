package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.tencent.mapsdk.internal.lf;
import com.tencent.mapsdk.internal.o4;
import com.tencent.tencentmap.mapsdk.maps.TencentMapContext;

/* loaded from: classes9.dex */
public class kf extends m4 {
    private Context d;

    /* renamed from: e  reason: collision with root package name */
    private TextView f16771e;

    /* renamed from: f  reason: collision with root package name */
    private lf.a f16772f;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ ViewGroup a;

        public a(ViewGroup viewGroup) {
            this.a = viewGroup;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.addView(kf.this.f16771e);
        }
    }

    public kf(Context context, TencentMapContext tencentMapContext) {
        this.d = context;
        this.f16771e = new nc(this.d, tencentMapContext);
    }

    private Bitmap e() {
        this.f16771e.setTextSize(18.0f);
        this.f16771e.setTextColor(-16777216);
        this.f16771e.setText("\u9274\u6743\u5931\u8d25,\u8bf7\u68c0\u67e5\u4f60\u7684key");
        return a7.a(this.f16771e);
    }

    @Override // com.tencent.mapsdk.internal.o4
    public void a() {
    }

    @Override // com.tencent.mapsdk.internal.h5
    public void a(int i2, int i3) {
    }

    public void a(lf.a aVar) {
        this.f16772f = aVar;
    }

    @Override // com.tencent.mapsdk.internal.o4
    public void a(o4.b bVar) {
    }

    @Override // com.tencent.mapsdk.internal.o4
    public boolean a(ViewGroup viewGroup, Bundle bundle) {
        if (viewGroup == null || this.f16771e == null) {
            return false;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.f16771e.setLayoutParams(layoutParams);
        int measuredWidth = viewGroup.getMeasuredWidth();
        int measuredHeight = viewGroup.getMeasuredHeight();
        Bitmap e2 = e();
        ba.b(new a(viewGroup));
        lf.a aVar = this.f16772f;
        if (aVar != null) {
            aVar.a(e2, measuredWidth, measuredHeight);
            return true;
        }
        return true;
    }

    @Override // com.tencent.mapsdk.internal.m4
    public View[] c() {
        return new View[0];
    }

    @Override // com.tencent.mapsdk.internal.o4
    public o4.b getPosition() {
        return null;
    }
}
