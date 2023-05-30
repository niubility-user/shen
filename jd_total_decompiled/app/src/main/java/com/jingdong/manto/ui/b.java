package com.jingdong.manto.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import com.jingdong.manto.R;
import com.jingdong.manto.k.a;
import com.jingdong.manto.utils.MantoLog;
import java.util.ArrayList;

/* loaded from: classes16.dex */
public abstract class b implements a.b {
    public View a;
    public View b;

    /* renamed from: c */
    Context f14285c;
    private LayoutInflater d;

    /* renamed from: e */
    FrameLayout f14286e;

    /* renamed from: f */
    public MantoBaseActivity f14287f;

    /* renamed from: g */
    private ArrayList<Dialog> f14288g;

    private void a(int i2) {
        View view;
        Resources resources;
        int i3;
        if (i2 == 0) {
            view = this.a;
            if (view == null) {
                return;
            }
            resources = this.f14285c.getResources();
            i3 = R.color.manto_day_background_weight;
        } else {
            view = this.a;
            if (view == null) {
                return;
            }
            resources = this.f14285c.getResources();
            i3 = R.color.manto_dark_background_weight;
        }
        view.setBackgroundColor(resources.getColor(i3));
    }

    protected abstract int a();

    public final void a(Dialog dialog) {
        if (dialog != null) {
            if (this.f14288g == null) {
                this.f14288g = new ArrayList<>();
            }
            this.f14288g.add(dialog);
        }
    }

    public final void a(Context context, Activity activity) {
        this.f14285c = context;
        this.f14287f = (MantoBaseActivity) activity;
        d();
        AudioManager audioManager = (AudioManager) this.f14285c.getSystemService("audio");
        int a = a();
        this.d = LayoutInflater.from(this.f14285c);
        View inflate = LayoutInflater.from(this.f14285c).inflate(R.layout.manto_activity, (ViewGroup) null);
        this.b = inflate;
        this.f14286e = (FrameLayout) inflate.findViewById(R.id.manto_child_view_container);
        if (a != -1) {
            View b = b();
            this.a = b;
            if (b == null) {
                this.a = this.d.inflate(a(), (ViewGroup) null);
            } else if (b.getParent() != null) {
                ((ViewGroup) this.a.getParent()).removeView(this.a);
            }
            this.f14286e.addView(this.a, 0);
        }
        a(this.b);
        com.jingdong.manto.k.a.b().a(this);
    }

    protected abstract void a(View view);

    public final boolean a(int i2, KeyEvent keyEvent) {
        return i2 == 82 && keyEvent.getAction() == 1;
    }

    protected abstract View b();

    public final boolean b(View view) {
        InputMethodManager inputMethodManager;
        IBinder windowToken;
        if (view == null || (inputMethodManager = (InputMethodManager) this.f14285c.getSystemService("input_method")) == null || (windowToken = view.getWindowToken()) == null) {
            return false;
        }
        try {
            return inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        } catch (IllegalArgumentException e2) {
            MantoLog.e("MantoActivityController", "hide VKB(View) exception %s", e2);
            return false;
        }
    }

    public final boolean c() {
        View currentFocus;
        IBinder windowToken;
        InputMethodManager inputMethodManager = (InputMethodManager) this.f14285c.getSystemService("input_method");
        if (inputMethodManager == null || (currentFocus = this.f14287f.getCurrentFocus()) == null || (windowToken = currentFocus.getWindowToken()) == null) {
            return false;
        }
        try {
            return inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        } catch (IllegalArgumentException e2) {
            MantoLog.e("MantoActivityController", "hide VKB exception %s", e2);
            return false;
        }
    }

    protected abstract void d();

    public final void e() {
        ArrayList<Dialog> arrayList = this.f14288g;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                Dialog dialog = this.f14288g.get(i2);
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
            this.f14288g.clear();
            this.f14288g = null;
        }
        com.jingdong.manto.k.a.b().b(this);
    }

    public final void f() {
        View currentFocus;
        MantoBaseActivity mantoBaseActivity = this.f14287f;
        InputMethodManager inputMethodManager = (InputMethodManager) mantoBaseActivity.getSystemService("input_method");
        if (inputMethodManager == null || (currentFocus = mantoBaseActivity.getCurrentFocus()) == null || currentFocus.getWindowToken() == null) {
            return;
        }
        inputMethodManager.toggleSoftInput(0, 2);
    }

    @Override // com.jingdong.manto.k.a.b
    public void onDeepModeChanged(int i2) {
        a(i2);
    }
}
