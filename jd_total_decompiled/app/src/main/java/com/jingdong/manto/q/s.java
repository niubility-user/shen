package com.jingdong.manto.q;

import android.app.Activity;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import com.jingdong.manto.R;
import com.jingdong.manto.sdk.api.IImageLoader;
import com.jingdong.manto.utils.MantoDensityUtils;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.utils.MantoThreadUtils;
import com.jingdong.manto.widget.CircleImageView;
import com.jingdong.manto.widget.GradualChangeTv;
import com.jingdong.manto.widget.MantoStatusBarUtil;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes16.dex */
public class s extends FrameLayout {
    GradualChangeTv a;
    private TextView b;

    /* renamed from: c  reason: collision with root package name */
    private View f14108c;
    private CircleImageView d;

    /* renamed from: e  reason: collision with root package name */
    private TextView f14109e;

    /* renamed from: f  reason: collision with root package name */
    private int f14110f;

    /* renamed from: g  reason: collision with root package name */
    private Timer f14111g;

    /* renamed from: h  reason: collision with root package name */
    float f14112h;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class a extends TimerTask {

        /* renamed from: com.jingdong.manto.q.s$a$a  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        class RunnableC0662a implements Runnable {
            RunnableC0662a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                float f2;
                s sVar = s.this;
                float f3 = sVar.f14112h;
                if (f3 >= 1.0f) {
                    f2 = 0.0f;
                } else {
                    double d = f3;
                    Double.isNaN(d);
                    f2 = (float) (d + 0.05d);
                }
                sVar.f14112h = f2;
                GradualChangeTv gradualChangeTv = sVar.a;
                if (gradualChangeTv != null) {
                    gradualChangeTv.setProgressAndInvalidate(sVar.f14112h);
                }
            }
        }

        a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            MantoThreadUtils.runOnUIThread(new RunnableC0662a());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes16.dex */
    public class b implements Runnable {
        final /* synthetic */ c a;

        b(s sVar, c cVar) {
            this.a = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a();
        }
    }

    /* loaded from: classes16.dex */
    public interface c {
        void a();
    }

    public s(@NonNull Activity activity, int i2) {
        super(activity);
        this.f14111g = new Timer();
        this.f14112h = 0.0f;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.manto_splash_view_layout, (ViewGroup) this, false);
        this.f14108c = inflate;
        addView(inflate);
        this.f14110f = i2;
        this.a = (GradualChangeTv) findViewById(R.id.tv_loading_title);
        this.b = (TextView) findViewById(R.id.tv_loading_text);
        this.d = (CircleImageView) findViewById(R.id.manto_icon);
        this.f14109e = (TextView) findViewById(R.id.tv_progress);
        this.a.setGradualTextSize(MantoDensityUtils.dip2pixel(20));
        Resources resources = getResources();
        int i3 = R.color.manto_dark_background_weight;
        int color = resources.getColor(i3);
        if (this.f14110f == 0) {
            MantoStatusBarUtil.setStatusBarColor(activity, -1, true);
            GradualChangeTv gradualChangeTv = this.a;
            int color2 = getResources().getColor(R.color.manto_day_text_light);
            Resources resources2 = getResources();
            int i4 = R.color.manto_day_text_weight;
            gradualChangeTv.a(color2, resources2.getColor(i4));
            this.b.setTextColor(getResources().getColor(i4));
            this.f14109e.setTextColor(getResources().getColor(i4));
            setBackgroundColor(-1);
            return;
        }
        MantoStatusBarUtil.setStatusBarColor(activity, color, false);
        GradualChangeTv gradualChangeTv2 = this.a;
        int color3 = getResources().getColor(R.color.manto_dark_text_light);
        Resources resources3 = getResources();
        int i5 = R.color.manto_dark_text_weight;
        gradualChangeTv2.a(color3, resources3.getColor(i5));
        this.b.setTextColor(getResources().getColor(i5));
        this.f14109e.setTextColor(getResources().getColor(i5));
        setBackgroundColor(getResources().getColor(i3));
    }

    public void a() {
        this.f14111g.schedule(new a(), 0L, 50L);
    }

    public void a(long j2, long j3) {
        if (j2 <= 0 || j3 <= 0) {
            return;
        }
        this.f14109e.setText(String.format("%s,%s, %s", Long.valueOf(j2), Long.valueOf(j3), Integer.valueOf((int) (((((float) j2) * 1.0f) / ((float) j3)) * 100.0f))) + "%");
    }

    public final void a(c cVar) {
        if (cVar != null) {
            this.f14111g.cancel();
            MantoThreadUtils.runOnUIThread(new b(this, cVar));
        }
    }

    @Override // android.view.View
    public boolean isShown() {
        return getVisibility() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MantoLog.w("splashView", "onDetachedFromWindow..");
    }

    public void setLoadingTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = "";
        }
        GradualChangeTv gradualChangeTv = this.a;
        if (gradualChangeTv == null) {
            return;
        }
        gradualChangeTv.setGradualText(charSequence.toString());
    }

    public void setMantoIcon(@DrawableRes int i2) {
        CircleImageView circleImageView = this.d;
        if (circleImageView == null) {
            return;
        }
        circleImageView.setImageDrawable(getResources().getDrawable(i2));
    }

    public void setMantoIcon(String str) {
        if (this.d == null || com.jingdong.a.n(IImageLoader.class) == null || this.d.getContext() == null) {
            return;
        }
        if (this.d.getContext() instanceof Activity) {
            ((Activity) this.d.getContext()).isFinishing();
        }
        ((IImageLoader) com.jingdong.a.n(IImageLoader.class)).loadImage(this.d, str);
    }
}
