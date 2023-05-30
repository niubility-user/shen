package com.jingdong.manto.widget.g;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jingdong.manto.R;
import com.jingdong.manto.sdk.api.IActionBar;
import com.jingdong.manto.utils.MantoLog;
import com.jingdong.manto.widget.MantoStatusBarUtil;

/* loaded from: classes16.dex */
public abstract class a extends LinearLayout implements d {
    protected View a;
    protected View b;

    /* renamed from: c  reason: collision with root package name */
    protected ImageView f14364c;
    protected ImageView d;

    /* renamed from: e  reason: collision with root package name */
    protected int f14365e;

    /* renamed from: f  reason: collision with root package name */
    protected View f14366f;

    /* renamed from: g  reason: collision with root package name */
    protected ImageView f14367g;

    /* renamed from: h  reason: collision with root package name */
    protected TextView f14368h;

    /* renamed from: i  reason: collision with root package name */
    protected View f14369i;

    /* renamed from: j  reason: collision with root package name */
    protected int f14370j;

    public a(Context context, Activity activity) {
        super(context);
        a(activity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, com.jingdong.manto.utils.g.a(context));
        this.a = LayoutInflater.from(context).inflate(getActionBarLayoutId(), (ViewGroup) null);
        setGravity(16);
        addView(this.a, layoutParams);
        this.b = findViewById(R.id.menu_container);
        this.f14364c = (ImageView) findViewById(R.id.manto_actionbar_option);
        this.d = (ImageView) findViewById(R.id.manto_actionbar_home);
        View findViewById = findViewById(R.id.container_favorite);
        this.f14366f = findViewById;
        this.f14367g = (ImageView) findViewById.findViewById(R.id.iv_favorite_icon);
        this.f14368h = (TextView) this.f14366f.findViewById(R.id.tv_favorite);
    }

    private void a() {
        TextView textView;
        Resources resources;
        int i2;
        if (this.f14365e == -1) {
            this.d.setBackgroundResource(R.drawable.manto_selector_actionbar_close_black);
            this.f14364c.setBackgroundResource(R.drawable.manto_selector_actionbar_option_black);
            this.f14366f.setBackgroundResource(R.drawable.manto_selector_actionbar_favorite_black);
            this.f14367g.setBackgroundResource(R.drawable.manto_actionbar_heart_black);
            textView = this.f14368h;
            resources = getContext().getResources();
            i2 = R.color.manto_white;
        } else {
            this.d.setBackgroundResource(R.drawable.manto_selector_actionbar_close_white);
            this.f14364c.setBackgroundResource(R.drawable.manto_selector_actionbar_option_white);
            this.f14366f.setBackgroundResource(R.drawable.manto_selector_actionbar_favorite_white);
            this.f14367g.setBackgroundResource(R.drawable.manto_actionbar_heart_white);
            textView = this.f14368h;
            resources = getContext().getResources();
            i2 = R.color.manto_day_text_weight;
        }
        textView.setTextColor(resources.getColor(i2));
    }

    @Override // com.jingdong.manto.widget.g.d
    public void a(int i2, String str, boolean z) {
        if (this.f14366f != null) {
            this.f14368h.setText(str);
            this.f14367g.setVisibility(z ? 0 : 8);
            if (i2 != -1) {
                this.f14366f.setVisibility(0);
            }
        }
    }

    protected void a(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= 21) {
                this.f14370j = MantoStatusBarUtil.getStatusBarHeight(activity);
                try {
                    setLayoutParams(new RelativeLayout.LayoutParams(-1, com.jingdong.manto.utils.g.a(context) + this.f14370j));
                    setOrientation(1);
                    this.f14369i = new View(com.jingdong.a.g());
                    this.f14369i.setLayoutParams(new RelativeLayout.LayoutParams(-1, this.f14370j));
                    this.f14369i.setBackgroundColor(-1);
                    this.f14369i.setId(R.id.manto_fake_status_bar);
                    addView(this.f14369i);
                } catch (Exception e2) {
                    MantoLog.e("better", e2);
                }
            }
        }
    }

    @Override // com.jingdong.manto.widget.g.d
    public void a(boolean z) {
    }

    @Override // com.jingdong.manto.widget.g.d
    public void b(boolean z) {
    }

    public void c(boolean z) {
        ImageView imageView;
        IActionBar iActionBar = (IActionBar) com.jingdong.a.n(IActionBar.class);
        if ((z && (iActionBar == null || !iActionBar.hideCapsule())) || (imageView = this.d) == null || this.f14364c == null) {
            return;
        }
        imageView.setVisibility(8);
        this.f14364c.setVisibility(8);
    }

    protected abstract int getActionBarLayoutId();

    @Override // com.jingdong.manto.widget.g.d
    public View getActionView() {
        return this;
    }

    @Override // android.view.View, com.jingdong.manto.widget.g.d
    public float getAlpha() {
        return 1.0f;
    }

    @Override // com.jingdong.manto.widget.g.d
    public int getBackgroundColor() {
        return 0;
    }

    @Override // com.jingdong.manto.widget.g.d
    public int getForegroundColor() {
        return 0;
    }

    @Override // com.jingdong.manto.widget.g.d
    public View getMenuButtonContainer() {
        return this.b;
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setAlpha(double d) {
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setAnchorViewVisible(boolean z) {
    }

    @Override // android.view.View, com.jingdong.manto.widget.g.d
    public void setBackgroundColor(int i2) {
    }

    public void setBackgroundColor(String str) {
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setFakeStatusBarColor(int i2) {
        View view = this.f14369i;
        if (view != null) {
            view.setBackgroundColor(i2);
        }
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setForegroundColor(int i2) {
        this.f14365e = i2;
        a();
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setForegroundStyle(String str) {
        int i2;
        if (!"white".equals(str)) {
            i2 = "black".equals(str) ? -16777216 : -1;
            a();
        }
        this.f14365e = i2;
        a();
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setNavBarFavoriteClickListener(View.OnClickListener onClickListener) {
        View view = this.f14366f;
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setNavigationBarLoadingVisible(boolean z) {
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setOnBackClickListener(View.OnClickListener onClickListener) {
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setOnBackHomeClickListener(View.OnClickListener onClickListener) {
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setOnCloseClickListener(View.OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setOnHomeLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.d.setOnLongClickListener(onLongClickListener);
    }

    public void setOnMainPageClickListener(View.OnClickListener onClickListener) {
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setOnOptionClickListener(View.OnClickListener onClickListener) {
        this.f14364c.setOnClickListener(onClickListener);
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setOnStatusBarClickListener(View.OnClickListener onClickListener) {
    }

    @Override // com.jingdong.manto.widget.g.d
    public void setTitle(String str) {
    }
}
