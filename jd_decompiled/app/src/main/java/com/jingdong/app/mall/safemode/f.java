package com.jingdong.app.mall.safemode;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.ui.JDDialog;
import com.jingdong.common.ui.JDDialogFactory;
import com.jingdong.common.ui.JDProgressBar;
import com.jingdong.jdsdk.JdSdk;
import java.io.File;

/* loaded from: classes4.dex */
public class f extends com.jingdong.app.mall.safemode.d {

    /* renamed from: h  reason: collision with root package name */
    private JDProgressBar f11620h;

    /* renamed from: i  reason: collision with root package name */
    private ViewGroup f11621i;

    /* renamed from: j  reason: collision with root package name */
    private ImageView f11622j;

    /* renamed from: k  reason: collision with root package name */
    private ImageView f11623k;

    /* loaded from: classes4.dex */
    class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f11624g;

        a(f fVar, JDDialog jDDialog) {
            this.f11624g = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f11624g.dismiss();
        }
    }

    /* loaded from: classes4.dex */
    class b implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ JDDialog f11625g;

        b(JDDialog jDDialog) {
            this.f11625g = jDDialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.f11625g.dismiss();
            f.this.l();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class c implements Runnable {

        /* loaded from: classes4.dex */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                f.this.g();
            }
        }

        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g.b("safemodefixing", "safeModeClean");
            i.g().i();
            try {
                Thread.sleep(800L);
                f.this.j();
                Thread.sleep(200L);
            } catch (Throwable unused) {
            }
            f fVar = f.this;
            fVar.f11615g = true;
            Activity activity = fVar.f11613e;
            if (activity != null && !activity.isFinishing()) {
                f.this.f11613e.runOnUiThread(new a());
            } else {
                f.this.a();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.this.a();
        }
    }

    public f(Activity activity, TextView textView, TextView textView2, TextView textView3, Button button, ImageView imageView, ImageView imageView2) {
        this.a = textView;
        this.b = textView2;
        this.f11612c = textView3;
        this.d = button;
        this.f11613e = activity;
        this.f11622j = imageView;
        this.f11623k = imageView2;
    }

    private void f(File file, com.jingdong.app.mall.safemode.c cVar) {
        if (cVar == null || file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            for (File file2 : file.listFiles(cVar)) {
                f(file2, cVar);
            }
            if (!cVar.accept(file, "") || file.delete()) {
                return;
            }
            try {
                file.deleteOnExit();
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        try {
            if (k(file)) {
                return;
            }
            file.deleteOnExit();
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        JDProgressBar jDProgressBar;
        this.f11614f = false;
        ViewGroup viewGroup = this.f11621i;
        if (viewGroup != null && (jDProgressBar = this.f11620h) != null) {
            viewGroup.removeView(jDProgressBar);
            this.f11621i.postInvalidate();
        }
        this.a.setText(R.string.safemode_state_fix_end);
        this.b.setText(R.string.safemode_info_fix_end);
        this.d.setText(R.string.safemode_btn_exit_app);
        this.d.setOnClickListener(new d());
        this.d.setEnabled(true);
        this.f11612c.setVisibility(8);
        this.f11622j.setVisibility(8);
        this.f11623k.setImageResource(R.drawable.safe_mode_joy_laugh);
    }

    private void h() {
        if (JdSdk.getInstance().getApplication() == null || JdSdk.getInstance().getApplication().getExternalCacheDir() == null) {
            return;
        }
        f(JdSdk.getInstance().getApplication().getExternalCacheDir().getParentFile(), new com.jingdong.app.mall.safemode.c());
    }

    private void i() {
        if (JdSdk.getInstance().getApplication() == null || JdSdk.getInstance().getApplication().getFilesDir() == null) {
            return;
        }
        f(JdSdk.getInstance().getApplication().getFilesDir().getParentFile(), new com.jingdong.app.mall.safemode.c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        i();
        h();
    }

    private boolean k(File file) {
        if (file != null) {
            File file2 = new File(file.getParent() + File.separator + System.currentTimeMillis());
            file.renameTo(file2);
            return file2.delete();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.f11614f = true;
        this.a.setText(R.string.safemode_state_fixing);
        this.d.setEnabled(false);
        this.f11612c.setEnabled(false);
        this.f11622j.setEnabled(false);
        try {
            this.f11621i = (ViewGroup) this.f11613e.getWindow().getDecorView();
            this.f11620h = new JDProgressBar(this.f11613e);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            this.f11621i.addView(this.f11620h, layoutParams);
            this.f11621i.postInvalidate();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        new Thread(new c()).start();
    }

    @Override // com.jingdong.app.mall.safemode.d
    public void b(String str) {
        JDDialogFactory jDDialogFactory = JDDialogFactory.getInstance();
        Activity activity = this.f11613e;
        JDDialog createJdDialogWithStyle2 = jDDialogFactory.createJdDialogWithStyle2(activity, activity.getString(R.string.safemode_clean_handler_info), this.f11613e.getString(R.string.safemode_no), this.f11613e.getString(R.string.safemode_yes));
        createJdDialogWithStyle2.setOnLeftButtonClickListener(new a(this, createJdDialogWithStyle2));
        createJdDialogWithStyle2.setOnRightButtonClickListener(new b(createJdDialogWithStyle2));
        createJdDialogWithStyle2.show();
    }
}
