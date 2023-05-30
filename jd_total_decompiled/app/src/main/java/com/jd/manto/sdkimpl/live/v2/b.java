package com.jd.manto.sdkimpl.live.v2;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.widget.RelativeLayout;
import com.jd.manto.R;

/* loaded from: classes17.dex */
public class b {
    private RelativeLayout a;
    private MantoLivePlayerV2 b;

    /* renamed from: c  reason: collision with root package name */
    private Dialog f6814c;
    private InterfaceC0205b d;

    /* loaded from: classes17.dex */
    class a implements DialogInterface.OnCancelListener {
        a() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            b.this.c(false);
        }
    }

    /* renamed from: com.jd.manto.sdkimpl.live.v2.b$b  reason: collision with other inner class name */
    /* loaded from: classes17.dex */
    public interface InterfaceC0205b {
        void onDismiss(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        this.a.removeView(this.b);
        InterfaceC0205b interfaceC0205b = this.d;
        if (interfaceC0205b != null) {
            interfaceC0205b.onDismiss(z);
        }
    }

    public void b(Activity activity, boolean z) {
        Dialog dialog;
        c(z);
        if (activity == null || activity.isFinishing() || activity.isRestricted() || (dialog = this.f6814c) == null) {
            return;
        }
        dialog.dismiss();
    }

    public void d(InterfaceC0205b interfaceC0205b) {
        this.d = interfaceC0205b;
    }

    public void e(Activity activity, MantoLivePlayerV2 mantoLivePlayerV2) {
        this.b = mantoLivePlayerV2;
        RelativeLayout relativeLayout = new RelativeLayout(activity);
        this.a = relativeLayout;
        relativeLayout.addView(this.b, 0, new RelativeLayout.LayoutParams(-1, -1));
        if (activity == null || activity.isFinishing() || activity.isRestricted()) {
            return;
        }
        Dialog dialog = new Dialog(activity, R.style.manto_dialog_video_fullscreen);
        this.f6814c = dialog;
        mantoLivePlayerV2.C(dialog);
        this.f6814c.setCancelable(true);
        this.f6814c.setCanceledOnTouchOutside(true);
        this.f6814c.setOnCancelListener(new a());
        this.f6814c.getWindow().setFlags(1024, 1024);
        this.f6814c.getWindow().setWindowAnimations(R.style.manto_dialog_anim_bottom);
        this.f6814c.addContentView(this.a, new RelativeLayout.LayoutParams(-1, -1));
        this.f6814c.show();
    }
}
