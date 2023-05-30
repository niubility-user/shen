package com.jingdong.canvas.surface;

import android.content.Context;
import android.view.TextureView;
import com.jingdong.canvas.b.a;

/* loaded from: classes4.dex */
public class JDTextureView extends TextureView {

    /* renamed from: g */
    private JDTextureViewCallback f12308g;

    public JDTextureView(Context context, String str, int i2) {
        super(context);
        a(str, i2);
    }

    private void a(String str, int i2) {
        JDTextureViewCallback jDTextureViewCallback = new JDTextureViewCallback(this, str, i2);
        this.f12308g = jDTextureViewCallback;
        setSurfaceTextureListener(jDTextureViewCallback);
        setOpaque(false);
        setLayerType(2, null);
    }

    public void b() {
        a.a("on request Exit in GSurfaceView.");
        if (this.f12308g != null) {
            a.a("start to request Exit.");
            this.f12308g.a();
        }
    }

    public void c() {
    }

    public void d(String str) {
        JDTextureViewCallback jDTextureViewCallback = this.f12308g;
        if (jDTextureViewCallback != null) {
            jDTextureViewCallback.b(str);
        }
    }

    public void e(int i2, int i3) {
        this.f12308g.c(i2, i3);
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        a.a("on window visibility changed.visibility=" + i2);
    }
}
