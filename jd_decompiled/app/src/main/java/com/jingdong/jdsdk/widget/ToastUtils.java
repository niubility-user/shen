package com.jingdong.jdsdk.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.jingdong.common.R;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.jdsdk.widget.a.f;
import com.jingdong.jdsdk.widget.a.g;

@Deprecated
/* loaded from: classes14.dex */
public class ToastUtils {
    private static JDToast centerToast;
    private static JDToast centerToastNoIcon;
    private static Handler mHandler;
    private static JDToast sToast;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class a implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f12935g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f12936h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f12937i;

        a(Context context, String str, int i2) {
            this.f12935g = context;
            this.f12936h = str;
            this.f12937i = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f12935g == null || TextUtils.isEmpty(this.f12936h)) {
                return;
            }
            if (ToastUtils.sToast != null) {
                ToastUtils.sToast.cancel();
            }
            JDToast unused = ToastUtils.sToast = new JDToast(this.f12935g.getApplicationContext(), (byte) 2);
            ToastUtils.sToast.setText(this.f12936h);
            ToastUtils.sToast.setDuration(this.f12937i);
            ToastUtils.sToast.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f12938g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f12939h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f12940i;

        b(Context context, String str, int i2) {
            this.f12938g = context;
            this.f12939h = str;
            this.f12940i = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f12938g == null || TextUtils.isEmpty(this.f12939h)) {
                return;
            }
            if (ToastUtils.sToast != null) {
                ToastUtils.sToast.cancel();
            }
            JDToast unused = ToastUtils.sToast = new JDToast(this.f12938g.getApplicationContext(), DPIUtil.dip2px(100.0f));
            ToastUtils.sToast.setText(this.f12939h);
            ToastUtils.sToast.setDuration(this.f12940i);
            ToastUtils.sToast.show();
        }
    }

    /* loaded from: classes14.dex */
    class c implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f12941g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f12942h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ byte f12943i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ int f12944j;

        c(Context context, String str, byte b, int i2) {
            this.f12941g = context;
            this.f12942h = str;
            this.f12943i = b;
            this.f12944j = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f12941g == null || TextUtils.isEmpty(this.f12942h)) {
                return;
            }
            if (ToastUtils.centerToast != null) {
                ToastUtils.centerToast.cancel();
            }
            JDToast unused = ToastUtils.centerToast = new JDToast(this.f12941g.getApplicationContext(), (byte) 1);
            ToastUtils.centerToast.setImage(this.f12943i);
            ToastUtils.centerToast.setText(this.f12942h);
            ToastUtils.centerToast.setDuration(this.f12944j);
            ToastUtils.centerToast.show();
        }
    }

    /* loaded from: classes14.dex */
    class d implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f12945g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f12946h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f12947i;

        /* renamed from: j  reason: collision with root package name */
        final /* synthetic */ int f12948j;

        d(Context context, String str, int i2, int i3) {
            this.f12945g = context;
            this.f12946h = str;
            this.f12947i = i2;
            this.f12948j = i3;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f12945g == null || TextUtils.isEmpty(this.f12946h)) {
                return;
            }
            if (ToastUtils.centerToast != null) {
                ToastUtils.centerToast.cancel();
            }
            JDToast unused = ToastUtils.centerToast = new JDToast(this.f12945g.getApplicationContext(), (byte) 1);
            ToastUtils.centerToast.setImageResource(this.f12947i);
            ToastUtils.centerToast.setText(this.f12946h);
            ToastUtils.centerToast.setDuration(this.f12948j);
            ToastUtils.centerToast.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public class e implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Context f12949g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ String f12950h;

        /* renamed from: i  reason: collision with root package name */
        final /* synthetic */ int f12951i;

        e(Context context, String str, int i2) {
            this.f12949g = context;
            this.f12950h = str;
            this.f12951i = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f12949g == null || TextUtils.isEmpty(this.f12950h)) {
                return;
            }
            if (ToastUtils.centerToastNoIcon != null) {
                ToastUtils.centerToastNoIcon.cancel();
            }
            JDToast unused = ToastUtils.centerToastNoIcon = new JDToast(this.f12949g.getApplicationContext(), (byte) 4);
            ToastUtils.centerToastNoIcon.setText(this.f12950h);
            ToastUtils.centerToastNoIcon.setDuration(this.f12951i);
            ToastUtils.centerToastNoIcon.show();
        }
    }

    private static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    public static void longToast(Context context, int i2) {
        showToastPrivate(context, i2, 1);
    }

    private static void newCenter(Context context, String str, int i2) throws Throwable {
        com.jingdong.jdsdk.widget.a.c.f(new f());
        View inflate = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.jd_common_toast_style_center, (ViewGroup) null);
        com.jingdong.jdsdk.widget.a.c.k(inflate);
        ((ImageView) inflate.findViewById(R.id.jd_toast_image)).setBackgroundResource(i2);
        com.jingdong.jdsdk.widget.a.c.l(str);
    }

    public static void shortToast(Context context, String str) {
        showToastPrivate(context, str, 0);
    }

    public static void showToast(Context context, String str) {
        longToast(str);
    }

    public static void showToastInCenter(Context context, byte b2, String str, int i2) {
        if (com.jingdong.jdsdk.widget.a.c.f12953e) {
            try {
                newCenter(context, str, b2);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        getHandler().post(new c(context, str, b2, i2));
    }

    private static void showToastPrivate(Context context, int i2, int i3) {
        if (context == null) {
            return;
        }
        showToastPrivate(context, context.getString(i2), i3);
    }

    private static void showToastPrivateY(Context context, String str, int i2) {
        if (!com.jingdong.jdsdk.widget.a.c.f12953e) {
            com.jingdong.jdsdk.widget.a.c.f(new g());
            com.jingdong.jdsdk.widget.a.c.l(str);
            return;
        }
        getHandler().post(new b(context, str, i2));
    }

    public static void showToastWithNetworkAvailable(Context context, String str) {
        if (NetUtils.isNetworkAvailable()) {
            longToast(str);
        }
    }

    public static void showToastY(String str) {
        showToastPrivateY(JdSdk.getInstance().getApplicationContext(), str, 0);
    }

    public static void longToast(Context context, String str) {
        showToastPrivate(context, str, 1);
    }

    public static void shortToast(String str) {
        shortToast(JdSdk.getInstance().getApplicationContext(), str);
    }

    public static void showToast(String str) {
        longToast(str);
    }

    private static void showToastPrivate(Context context, String str, int i2) {
        if (!com.jingdong.jdsdk.widget.a.c.f12953e) {
            com.jingdong.jdsdk.widget.a.c.f(new g());
            com.jingdong.jdsdk.widget.a.c.l(str);
            return;
        }
        getHandler().post(new a(context, str, i2));
    }

    public static void showToastY(int i2) {
        showToastPrivateY(JdSdk.getInstance().getApplicationContext(), JdSdk.getInstance().getApplicationContext().getString(i2), 0);
    }

    public static void longToast(int i2) {
        showToastPrivate(JdSdk.getInstance().getApplicationContext(), i2, 1);
    }

    public static void shortToast(int i2) {
        showToastPrivate(JdSdk.getInstance().getApplicationContext(), i2, 0);
    }

    public static void longToast(String str) {
        showToastPrivate(JdSdk.getInstance().getApplicationContext(), str, 1);
    }

    public static void shortToast(Context context, int i2) {
        showToastPrivate(context, i2, 0);
    }

    public static void showToastInCenter(Context context, int i2, String str, int i3) {
        if (!com.jingdong.jdsdk.widget.a.c.f12953e) {
            try {
                newCenter(context, str, i2);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        getHandler().post(new d(context, str, i2, i3));
    }

    private static void newCenter(Context context, String str, byte b2) throws Throwable {
        com.jingdong.jdsdk.widget.a.c.f(new f());
        View inflate = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.jd_common_toast_style_center, (ViewGroup) null);
        com.jingdong.jdsdk.widget.a.c.k(inflate);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.jd_toast_image);
        if (b2 == 1) {
            imageView.setBackgroundResource(R.drawable.jd_toast_exclamation);
        } else if (b2 == 2) {
            imageView.setBackgroundResource(R.drawable.jd_toast_tick);
        }
        com.jingdong.jdsdk.widget.a.c.l(str);
    }

    public static void showToastInCenter(Context context, String str, int i2) {
        if (!com.jingdong.jdsdk.widget.a.c.f12953e) {
            com.jingdong.jdsdk.widget.a.c.f(new f());
            com.jingdong.jdsdk.widget.a.c.j(R.layout.jd_common_toast_style_bottom);
            com.jingdong.jdsdk.widget.a.c.l(str);
            return;
        }
        getHandler().post(new e(context, str, i2));
    }

    public static void showToastInCenter(String str) {
        showToastInCenter(JdSdk.getInstance().getApplicationContext(), str, 0);
    }
}
