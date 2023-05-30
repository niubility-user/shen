package com.jingdong.jdsdk.widget.a;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;

/* loaded from: classes14.dex */
public final class i extends Handler {
    private final Toast a;
    private final j b;

    /* renamed from: c */
    private final String f12955c;
    private boolean d;

    public i(Toast toast, Application application) {
        super(Looper.getMainLooper());
        this.a = toast;
        this.f12955c = application.getPackageName();
        this.b = j.d(this, application);
    }

    public void a() {
        removeMessages(0);
        if (this.d) {
            try {
                this.b.b().removeView(this.a.getView());
            } catch (IllegalArgumentException | NullPointerException unused) {
            }
            this.d = false;
        }
    }

    public void b() {
        if (this.d) {
            return;
        }
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 16973828;
        layoutParams.setTitle("Toast");
        layoutParams.flags = 152;
        layoutParams.packageName = this.f12955c;
        layoutParams.gravity = this.a.getGravity();
        layoutParams.x = this.a.getXOffset();
        layoutParams.y = this.a.getYOffset();
        try {
            this.b.b().addView(this.a.getView(), layoutParams);
            this.d = true;
            sendEmptyMessageDelayed(0, this.a.getDuration() == 1 ? 3500L : 2000L);
        } catch (WindowManager.BadTokenException | IllegalStateException | NullPointerException unused) {
        }
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        a();
    }
}
