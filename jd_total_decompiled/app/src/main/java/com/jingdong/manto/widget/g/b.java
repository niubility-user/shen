package com.jingdong.manto.widget.g;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.jingdong.manto.R;
import com.jingdong.manto.sdk.MantoSdkManager;
import com.jingdong.manto.sdk.api.IActionBarAnchor;
import com.jingdong.manto.utils.MantoThreadUtils;

/* loaded from: classes16.dex */
public class b extends com.jingdong.manto.widget.g.a implements d {

    /* renamed from: k  reason: collision with root package name */
    private View f14371k;

    /* renamed from: l  reason: collision with root package name */
    private ImageView f14372l;

    /* renamed from: m  reason: collision with root package name */
    private ImageView f14373m;

    /* renamed from: n  reason: collision with root package name */
    private TextView f14374n;
    private ProgressBar o;
    private float p;
    private int q;
    private long r;
    private RelativeLayout s;
    private com.jingdong.manto.f t;

    /* loaded from: classes16.dex */
    class a implements Runnable {
        final /* synthetic */ boolean a;

        /* renamed from: com.jingdong.manto.widget.g.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes16.dex */
        class ViewOnClickListenerC0693a implements View.OnClickListener {
            ViewOnClickListenerC0693a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (b.this.t != null) {
                    ((IActionBarAnchor) MantoSdkManager.instanceOf(IActionBarAnchor.class)).onClicked(b.this.t.i(), b.this.t.j());
                }
            }
        }

        a(boolean z) {
            this.a = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            RelativeLayout relativeLayout;
            ViewOnClickListenerC0693a viewOnClickListenerC0693a;
            if (b.this.s != null) {
                b.this.s.setVisibility(this.a ? 0 : 8);
                if (this.a) {
                    relativeLayout = b.this.s;
                    viewOnClickListenerC0693a = new ViewOnClickListenerC0693a();
                } else {
                    relativeLayout = b.this.s;
                    viewOnClickListenerC0693a = null;
                }
                relativeLayout.setOnClickListener(viewOnClickListenerC0693a);
            }
        }
    }

    /* renamed from: com.jingdong.manto.widget.g.b$b  reason: collision with other inner class name */
    /* loaded from: classes16.dex */
    class ViewOnClickListenerC0694b implements View.OnClickListener {
        final /* synthetic */ View.OnClickListener a;

        ViewOnClickListenerC0694b(View.OnClickListener onClickListener) {
            this.a = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (System.currentTimeMillis() - b.this.r < 250) {
                this.a.onClick(view);
                b.this.r = 0L;
            }
            b.this.r = System.currentTimeMillis();
        }
    }

    public b(Activity activity, com.jingdong.manto.f fVar) {
        super(activity.getApplicationContext(), activity);
        View anchorView;
        this.t = fVar;
        this.p = 1.0f;
        this.q = activity.getResources().getColor(R.color.manto_white);
        this.f14371k = findViewById(R.id.manto_actionbar_header);
        this.f14372l = (ImageView) findViewById(R.id.actionbar_back);
        this.f14373m = (ImageView) findViewById(R.id.actionbar_back_home);
        this.f14374n = (TextView) findViewById(R.id.manto_actionbar_title);
        this.f14364c = (ImageView) findViewById(R.id.manto_actionbar_option);
        this.d = (ImageView) findViewById(R.id.manto_actionbar_home);
        this.o = (ProgressBar) findViewById(R.id.manto_actionbar_progress);
        b();
        View findViewById = findViewById(R.id.container_favorite);
        this.f14366f = findViewById;
        this.f14367g = (ImageView) findViewById.findViewById(R.id.iv_favorite_icon);
        this.f14368h = (TextView) this.f14366f.findViewById(R.id.tv_favorite);
        this.s = (RelativeLayout) findViewById(R.id.common_action_bar_container_anchor);
        IActionBarAnchor iActionBarAnchor = (IActionBarAnchor) MantoSdkManager.instanceOf(IActionBarAnchor.class);
        if (iActionBarAnchor != null && (anchorView = iActionBarAnchor.getAnchorView()) != null) {
            this.s.setVisibility(8);
            this.s.addView(anchorView);
            this.s.setClickable(true);
        }
        c(fVar.A());
    }

    private void b() {
        ProgressBar progressBar = this.o;
        if (progressBar == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT < 21) {
                Drawable wrap = DrawableCompat.wrap(progressBar.getIndeterminateDrawable());
                DrawableCompat.setTint(wrap, ContextCompat.getColor(getContext(), R.color.manto_open_loading_color));
                this.o.setIndeterminateDrawable(DrawableCompat.unwrap(wrap));
            } else {
                progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.manto_open_loading_color), PorterDuff.Mode.SRC_IN);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public void a(boolean z) {
        ImageView imageView = this.f14373m;
        if (imageView != null) {
            imageView.setVisibility(z ? 8 : 0);
        }
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public void b(boolean z) {
        ImageView imageView = this.f14372l;
        if (imageView != null) {
            imageView.setVisibility(z ? 8 : 0);
        }
    }

    @Override // com.jingdong.manto.widget.g.a
    protected int getActionBarLayoutId() {
        return R.layout.manto_common_actionbar;
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public View getActionView() {
        return this;
    }

    @Override // com.jingdong.manto.widget.g.a, android.view.View, com.jingdong.manto.widget.g.d
    public float getAlpha() {
        return this.p;
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public int getBackgroundColor() {
        return this.q;
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public int getForegroundColor() {
        return this.f14365e;
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public void setAlpha(double d) {
        this.p = (float) d;
        getBackground().setAlpha((int) (d * 255.0d));
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public void setAnchorViewVisible(boolean z) {
        MantoThreadUtils.runOnUIThread(new a(z));
    }

    @Override // com.jingdong.manto.widget.g.a, android.view.View, com.jingdong.manto.widget.g.d
    public void setBackgroundColor(int i2) {
        this.q = i2;
        View view = this.a;
        if (view != null) {
            view.setBackgroundColor(i2);
        }
    }

    @Override // com.jingdong.manto.widget.g.a
    public void setBackgroundColor(String str) {
        setBackgroundColor(com.jingdong.manto.ui.d.a(str, this.q));
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public void setForegroundColor(int i2) {
        super.setForegroundColor(i2);
        ImageView imageView = this.f14372l;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.manto_actionbar_back);
            this.f14372l.setColorFilter(this.f14365e);
        }
        ImageView imageView2 = this.f14373m;
        if (imageView2 != null) {
            imageView2.setImageResource(R.drawable.manto_actionbar_main_page);
            this.f14373m.setColorFilter(this.f14365e);
        }
        TextView textView = this.f14374n;
        if (textView != null) {
            textView.setTextColor(this.f14365e);
        }
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public void setForegroundStyle(String str) {
        super.setForegroundStyle(str);
        ImageView imageView = this.f14372l;
        if (imageView != null) {
            imageView.setImageResource(R.drawable.manto_actionbar_back);
            this.f14372l.setColorFilter(this.f14365e);
        }
        ImageView imageView2 = this.f14373m;
        if (imageView2 != null) {
            imageView2.setImageResource(R.drawable.manto_actionbar_main_page);
            this.f14373m.setColorFilter(this.f14365e);
        }
        TextView textView = this.f14374n;
        if (textView != null) {
            textView.setTextColor(this.f14365e);
        }
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public void setNavigationBarLoadingVisible(boolean z) {
        ProgressBar progressBar = this.o;
        if (progressBar != null) {
            progressBar.setVisibility(z ? 0 : 8);
        }
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public void setOnBackClickListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.f14372l;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public void setOnBackHomeClickListener(View.OnClickListener onClickListener) {
        ImageView imageView = this.f14373m;
        if (imageView != null) {
            imageView.setOnClickListener(onClickListener);
        }
    }

    @Override // com.jingdong.manto.widget.g.a
    public void setOnMainPageClickListener(View.OnClickListener onClickListener) {
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public void setOnStatusBarClickListener(View.OnClickListener onClickListener) {
        View view = this.f14371k;
        if (view != null) {
            view.setOnClickListener(new ViewOnClickListenerC0694b(onClickListener));
        }
    }

    @Override // com.jingdong.manto.widget.g.a, com.jingdong.manto.widget.g.d
    public void setTitle(String str) {
        TextView textView = this.f14374n;
        if (textView != null) {
            textView.setText(str);
        }
    }
}
