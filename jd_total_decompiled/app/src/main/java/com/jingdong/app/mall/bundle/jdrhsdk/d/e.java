package com.jingdong.app.mall.bundle.jdrhsdk.d;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.net.URL;

/* loaded from: classes2.dex */
public class e {
    private static int a;
    private static int b;

    /* renamed from: c  reason: collision with root package name */
    private static Point f8173c;
    private static Display d;

    /* loaded from: classes2.dex */
    class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f8174g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f8175h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ ImageView f8176i;

        a(Context context, String str, ImageView imageView) {
            this.f8174g = context;
            this.f8175h = str;
            this.f8176i = imageView;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Context context = this.f8174g;
                if (context != null) {
                    String string = context.getSharedPreferences("jdrhsdk_image_load", 0).getString(this.f8175h, "");
                    if (!TextUtils.isEmpty(string)) {
                        e.h(this.f8176i, BitmapFactory.decodeStream(this.f8174g.openFileInput(string)));
                        return;
                    }
                }
                Bitmap decodeStream = BitmapFactory.decodeStream(new URL(this.f8175h).openStream());
                e.r(this.f8175h, decodeStream);
                e.h(this.f8176i, decodeStream);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ ImageView f8177g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Bitmap f8178h;

        b(ImageView imageView, Bitmap bitmap) {
            this.f8177g = imageView;
            this.f8178h = bitmap;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ImageView imageView = this.f8177g;
                if (imageView != null) {
                    imageView.setImageBitmap(this.f8178h);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static int a(Activity activity) {
        if (activity != null) {
            try {
                Point point2 = new Point();
                activity.getWindowManager().getDefaultDisplay().getSize(point2);
                d.a("UiUtil", "getAppHeight var5.X=" + point2.x + " var5.y=" + point2.y);
                return point2.y;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (f8173c == null) {
            synchronized (e.class) {
                if (f8173c == null) {
                    q(c.a());
                }
            }
        }
        return f8173c.y;
    }

    public static int b(Activity activity, int i2) {
        return c(activity, R2.attr.internalMinHeight, i2);
    }

    public static int c(Activity activity, int i2, int i3) {
        if (b == 0) {
            b = a(activity);
        }
        return (int) (((b * i3) / i2) + 0.5f);
    }

    private static Display d(Context context) {
        try {
            if (d == null) {
                d = ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay();
            }
        } catch (Throwable th) {
            d.c("UiUtil", th);
        }
        return d;
    }

    private static String e(int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            double random = Math.random();
            double d2 = 63;
            Double.isNaN(d2);
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_".charAt((int) (random * d2)));
        }
        return sb.toString();
    }

    public static void f(Activity activity, View view) {
        ViewGroup.LayoutParams layoutParams;
        if (activity == null) {
            return;
        }
        if (view != null) {
            try {
                layoutParams = view.getLayoutParams();
            } catch (Throwable th) {
                d.c("UiUtil", th);
                return;
            }
        } else {
            layoutParams = null;
        }
        Window window = activity.getWindow();
        int i2 = Build.VERSION.SDK_INT;
        int i3 = 0;
        if (i2 >= 19) {
            int s = layoutParams != null ? s(activity) : 0;
            if (i2 < 21) {
                window.addFlags(67108864);
            } else {
                window.clearFlags(67108864);
                int i4 = R2.attr.lineSpacing;
                if (i2 >= 23) {
                    i4 = R2.drawable.lib_cashier_sdk_button_i_new_03_dark;
                }
                window.getDecorView().setSystemUiVisibility(i4);
                window.addFlags(Integer.MIN_VALUE);
                window.setStatusBarColor(0);
            }
            i3 = s;
        }
        p(activity, view);
        if (layoutParams != null) {
            layoutParams.height = i3;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void g(Button button) {
        Typeface a2;
        if (button == null || (a2 = com.jingdong.app.mall.bundle.jdrhsdk.d.b.a(button.getContext())) == null) {
            return;
        }
        button.setTypeface(a2);
    }

    public static void h(ImageView imageView, Bitmap bitmap) {
        c.c().post(new b(imageView, bitmap));
    }

    public static void i(TextView textView) {
        com.jingdong.app.mall.bundle.jdrhsdk.d.b.c(textView);
    }

    public static void k(String str, ImageView imageView) {
        new Thread(new a(c.a(), str, imageView)).start();
    }

    private static boolean l() {
        try {
            return Build.class.getMethod("hasSmartBar", new Class[0]) != null;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static int m(Activity activity) {
        if (activity != null) {
            try {
                Point point2 = new Point();
                activity.getWindowManager().getDefaultDisplay().getSize(point2);
                d.a("UiUtil", "getAppWidth var5.X=" + point2.x + " var5.y=" + point2.y);
                return point2.x;
            } catch (Exception e2) {
                d.c("UiUtil", e2);
            }
        }
        if (f8173c == null) {
            synchronized (e.class) {
                if (f8173c == null) {
                    q(c.a());
                }
            }
        }
        d.a("UiUtil", "getAppWidth outSize.X=" + f8173c.x + " outSize.y=" + f8173c.y);
        return f8173c.x;
    }

    public static int n(Activity activity, int i2) {
        return o(activity, R2.attr.ambientEnabled, i2);
    }

    public static int o(Activity activity, int i2, int i3) {
        if (a == 0) {
            a = m(activity);
        }
        return (int) (((a * i3) / i2) + 0.5f);
    }

    private static void p(Activity activity, View view) {
        Window window = activity.getWindow();
        if ("xiaomi".equalsIgnoreCase(BaseInfo.getDeviceManufacture())) {
            Class<?> cls = window.getClass();
            Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            int i2 = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
            Class<?> cls3 = Integer.TYPE;
            cls.getMethod("setExtraFlags", cls3, cls3).invoke(window, Integer.valueOf(i2), Integer.valueOf(i2));
        } else if (l()) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            try {
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                declaredField2.setInt(attributes, declaredField.getInt(null) | declaredField2.getInt(attributes));
                window.setAttributes(attributes);
                declaredField.setAccessible(false);
                declaredField2.setAccessible(false);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void q(Context context) {
        Display d2 = d(context);
        if (d2 != null) {
            Point point2 = new Point();
            f8173c = point2;
            d2.getSize(point2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void r(String str, Bitmap bitmap) {
        String str2 = System.currentTimeMillis() + e(10);
        Context a2 = c.a();
        if (a2 == null) {
            return;
        }
        try {
            FileOutputStream openFileOutput = a2.openFileOutput(str2, 0);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, openFileOutput);
            openFileOutput.flush();
            openFileOutput.close();
            a2.getSharedPreferences("jdrhsdk_image_load", 0).edit().putString(str, str2).apply();
        } catch (Throwable th) {
            d.c("UiUtil", th);
        }
    }

    public static int s(Activity activity) {
        int t = t(activity);
        return t > 0 ? t : u(activity);
    }

    private static int t(Activity activity) {
        try {
            int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (identifier > 0) {
                return activity.getResources().getDimensionPixelSize(identifier);
            }
            return 0;
        } catch (Throwable th) {
            d.f("UiUtil", "", th);
            return 0;
        }
    }

    private static int u(Activity activity) {
        try {
            Class<?> cls = Class.forName("com.android.internal.R$dimen");
            Integer num = (Integer) cls.getField("status_bar_height").get(cls.newInstance());
            if (num != null) {
                return activity.getResources().getDimensionPixelSize(num.intValue());
            }
        } catch (Throwable th) {
            d.f("UiUtil", "", th);
        }
        return 0;
    }

    public static boolean v(Activity activity) {
        return a(activity) > m(activity);
    }

    public static void w(Activity activity) {
        a = m(activity);
        b = a(activity);
        d.a("UiUtil", "onWidthChange APP_WIDTH=" + a + " APP_HEIGHT=" + b);
    }
}
