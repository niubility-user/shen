package com.jd.aips.common.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.lang.reflect.Method;

/* loaded from: classes12.dex */
public class SystemBarTintManager {
    public static final int DEFAULT_TINT_COLOR = -1728053248;

    /* renamed from: h  reason: collision with root package name */
    private static String f1609h;
    private final SystemBarConfig a;
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1610c;
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f1611e;

    /* renamed from: f  reason: collision with root package name */
    private View f1612f;

    /* renamed from: g  reason: collision with root package name */
    private View f1613g;

    /* loaded from: classes12.dex */
    public static class SystemBarConfig {
        private final boolean a;
        private final boolean b;

        /* renamed from: c  reason: collision with root package name */
        private final int f1614c;
        private final int d;

        /* renamed from: e  reason: collision with root package name */
        private final boolean f1615e;

        /* renamed from: f  reason: collision with root package name */
        private final int f1616f;

        /* renamed from: g  reason: collision with root package name */
        private final int f1617g;

        /* renamed from: h  reason: collision with root package name */
        private final boolean f1618h;

        /* renamed from: i  reason: collision with root package name */
        private final float f1619i;

        @TargetApi(14)
        private int a(Context context) {
            if (Build.VERSION.SDK_INT >= 14) {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(16843499, typedValue, true);
                return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
            }
            return 0;
        }

        @TargetApi(14)
        private int b(Context context) {
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT < 14 || !d(context)) {
                return 0;
            }
            int identifier = resources.getIdentifier(this.f1618h ? "navigation_bar_height" : "navigation_bar_height_landscape", "dimen", "android");
            if (identifier > 0) {
                return resources.getDimensionPixelSize(identifier);
            }
            return 0;
        }

        @TargetApi(14)
        private int c(Context context) {
            int identifier;
            Resources resources = context.getResources();
            if (Build.VERSION.SDK_INT < 14 || !d(context) || (identifier = resources.getIdentifier("navigation_bar_width", "dimen", "android")) <= 0) {
                return 0;
            }
            return resources.getDimensionPixelSize(identifier);
        }

        @TargetApi(14)
        private boolean d(Context context) {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_showNavigationBar", "bool", "android");
            if (identifier != 0) {
                boolean z = resources.getBoolean(identifier);
                if ("1".equals(SystemBarTintManager.f1609h)) {
                    return false;
                }
                if ("0".equals(SystemBarTintManager.f1609h)) {
                    return true;
                }
                return z;
            }
            return !ViewConfiguration.get(context).hasPermanentMenuKey();
        }

        public int getActionBarHeight() {
            return this.d;
        }

        public int getNavigationBarHeight() {
            return this.f1616f;
        }

        public int getNavigationBarWidth() {
            return this.f1617g;
        }

        public int getPixelInsetBottom() {
            if (this.b && isNavigationAtBottom()) {
                return this.f1616f;
            }
            return 0;
        }

        public int getPixelInsetRight() {
            if (!this.b || isNavigationAtBottom()) {
                return 0;
            }
            return this.f1617g;
        }

        public int getPixelInsetTop(boolean z) {
            return (this.a ? this.f1614c : 0) + (z ? this.d : 0);
        }

        public int getStatusBarHeight() {
            return this.f1614c;
        }

        public boolean hasNavigtionBar() {
            return this.f1615e;
        }

        public boolean isNavigationAtBottom() {
            return this.f1619i >= 600.0f || this.f1618h;
        }

        private SystemBarConfig(Activity activity, boolean z, boolean z2) {
            Resources resources = activity.getResources();
            this.f1618h = resources.getConfiguration().orientation == 1;
            this.f1619i = a(activity);
            this.f1614c = a(resources, "status_bar_height");
            this.d = a((Context) activity);
            int b = b(activity);
            this.f1616f = b;
            this.f1617g = c(activity);
            this.f1615e = b > 0;
            this.a = z;
            this.b = z2;
        }

        private int a(Resources resources, String str) {
            int identifier = resources.getIdentifier(str, "dimen", "android");
            if (identifier > 0) {
                return resources.getDimensionPixelSize(identifier);
            }
            return 0;
        }

        @SuppressLint({"NewApi"})
        private float a(Activity activity) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= 16) {
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
            } else {
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            }
            float f2 = displayMetrics.density;
            return Math.min(displayMetrics.widthPixels / f2, displayMetrics.heightPixels / f2);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                Method declaredMethod = Class.forName("android.os.SystemProperties").getDeclaredMethod(IMantoServerRequester.GET, String.class);
                declaredMethod.setAccessible(true);
                f1609h = (String) declaredMethod.invoke(null, "qemu.hw.mainkeys");
            } catch (Throwable unused) {
                f1609h = null;
            }
        }
    }

    @TargetApi(19)
    public SystemBarTintManager(Activity activity) {
        Window window = activity.getWindow();
        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
        if (Build.VERSION.SDK_INT >= 19) {
            TypedArray obtainStyledAttributes = activity.obtainStyledAttributes(new int[]{16843759, 16843760});
            try {
                this.b = obtainStyledAttributes.getBoolean(0, false);
                this.f1610c = obtainStyledAttributes.getBoolean(1, false);
                obtainStyledAttributes.recycle();
                int i2 = window.getAttributes().flags;
                if ((67108864 & i2) != 0) {
                    this.b = true;
                }
                if ((i2 & 134217728) != 0) {
                    this.f1610c = true;
                }
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        }
        SystemBarConfig systemBarConfig = new SystemBarConfig(activity, this.b, this.f1610c);
        this.a = systemBarConfig;
        if (!systemBarConfig.hasNavigtionBar()) {
            this.f1610c = false;
        }
        if (this.b) {
            b(activity, viewGroup);
        }
        if (this.f1610c) {
            a(activity, viewGroup);
        }
    }

    private void b(Context context, ViewGroup viewGroup) {
        this.f1612f = new View(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, this.a.getStatusBarHeight());
        layoutParams.gravity = 48;
        if (this.f1610c && !this.a.isNavigationAtBottom()) {
            layoutParams.rightMargin = this.a.getNavigationBarWidth();
        }
        this.f1612f.setLayoutParams(layoutParams);
        this.f1612f.setBackgroundColor(DEFAULT_TINT_COLOR);
        this.f1612f.setVisibility(8);
        viewGroup.addView(this.f1612f);
    }

    public SystemBarConfig getConfig() {
        return this.a;
    }

    public boolean isNavBarTintEnabled() {
        return this.f1611e;
    }

    public boolean isStatusBarTintEnabled() {
        return this.d;
    }

    @TargetApi(11)
    public void setNavigationBarAlpha(float f2) {
        if (!this.f1610c || Build.VERSION.SDK_INT < 11) {
            return;
        }
        this.f1613g.setAlpha(f2);
    }

    public void setNavigationBarTintColor(int i2) {
        if (this.f1610c) {
            this.f1613g.setBackgroundColor(i2);
        }
    }

    public void setNavigationBarTintDrawable(Drawable drawable) {
        if (this.f1610c) {
            this.f1613g.setBackgroundDrawable(drawable);
        }
    }

    public void setNavigationBarTintEnabled(boolean z) {
        this.f1611e = z;
        if (this.f1610c) {
            this.f1613g.setVisibility(z ? 0 : 8);
        }
    }

    public void setNavigationBarTintResource(int i2) {
        if (this.f1610c) {
            this.f1613g.setBackgroundResource(i2);
        }
    }

    @TargetApi(11)
    public void setStatusBarAlpha(float f2) {
        if (!this.b || Build.VERSION.SDK_INT < 11) {
            return;
        }
        this.f1612f.setAlpha(f2);
    }

    public void setStatusBarTintColor(int i2) {
        if (this.b) {
            this.f1612f.setBackgroundColor(i2);
        }
    }

    public void setStatusBarTintDrawable(Drawable drawable) {
        if (this.b) {
            this.f1612f.setBackgroundDrawable(drawable);
        }
    }

    public void setStatusBarTintEnabled(boolean z) {
        this.d = z;
        if (this.b) {
            this.f1612f.setVisibility(z ? 0 : 8);
        }
    }

    public void setStatusBarTintResource(int i2) {
        if (this.b) {
            this.f1612f.setBackgroundResource(i2);
        }
    }

    public void setTintAlpha(float f2) {
        setStatusBarAlpha(f2);
        setNavigationBarAlpha(f2);
    }

    public void setTintColor(int i2) {
        setStatusBarTintColor(i2);
        setNavigationBarTintColor(i2);
    }

    public void setTintDrawable(Drawable drawable) {
        setStatusBarTintDrawable(drawable);
        setNavigationBarTintDrawable(drawable);
    }

    public void setTintResource(int i2) {
        setStatusBarTintResource(i2);
        setNavigationBarTintResource(i2);
    }

    private void a(Context context, ViewGroup viewGroup) {
        FrameLayout.LayoutParams layoutParams;
        this.f1613g = new View(context);
        if (this.a.isNavigationAtBottom()) {
            layoutParams = new FrameLayout.LayoutParams(-1, this.a.getNavigationBarHeight());
            layoutParams.gravity = 80;
        } else {
            layoutParams = new FrameLayout.LayoutParams(this.a.getNavigationBarWidth(), -1);
            layoutParams.gravity = 5;
        }
        this.f1613g.setLayoutParams(layoutParams);
        this.f1613g.setBackgroundColor(DEFAULT_TINT_COLOR);
        this.f1613g.setVisibility(8);
        viewGroup.addView(this.f1613g);
    }
}
