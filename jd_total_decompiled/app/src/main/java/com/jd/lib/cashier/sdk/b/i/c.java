package com.jd.lib.cashier.sdk.b.i;

import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.c0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.w;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class c {
    private static final String a = "c";
    public static final a b = new a(null);

    /* loaded from: classes14.dex */
    public static final class a {

        /* renamed from: com.jd.lib.cashier.sdk.b.i.c$a$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        public static final class ViewTreeObserverOnGlobalLayoutListenerC0103a implements ViewTreeObserver.OnGlobalLayoutListener {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ View f2878g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ FragmentActivity f2879h;

            /* renamed from: i  reason: collision with root package name */
            final /* synthetic */ String f2880i;

            /* renamed from: j  reason: collision with root package name */
            final /* synthetic */ View f2881j;

            ViewTreeObserverOnGlobalLayoutListenerC0103a(View view, FragmentActivity fragmentActivity, String str, View view2) {
                this.f2878g = view;
                this.f2879h = fragmentActivity;
                this.f2880i = str;
                this.f2881j = view2;
            }

            /* JADX WARN: Removed duplicated region for block: B:22:0x003d  */
            /* JADX WARN: Removed duplicated region for block: B:23:0x003f  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x009b  */
            /* JADX WARN: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onGlobalLayout() {
                ViewTreeObserver viewTreeObserver;
                int i2;
                boolean a;
                FragmentActivity fragmentActivity;
                Resources resources;
                int i3 = Build.VERSION.SDK_INT;
                if (i3 >= 16) {
                    ViewTreeObserver viewTreeObserver2 = this.f2878g.getViewTreeObserver();
                    if (viewTreeObserver2 != null) {
                        viewTreeObserver2.removeOnGlobalLayoutListener(this);
                    }
                } else if (i3 >= 14 && (viewTreeObserver = this.f2878g.getViewTreeObserver()) != null) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                boolean z = false;
                try {
                    fragmentActivity = this.f2879h;
                } catch (Resources.NotFoundException unused) {
                }
                if (fragmentActivity != null && (resources = fragmentActivity.getResources()) != null) {
                    i2 = resources.getDimensionPixelSize(R.dimen.lib_cashier_sdk_dp_225);
                    int width = this.f2878g.getWidth() - i2;
                    int i4 = width >= 0 ? width : 0;
                    r.b(c.a, "anchorView width = " + this.f2878g.getWidth());
                    w.b bVar = new w.b(this.f2878g, i4, 0, 4, null);
                    a = c0.a(this.f2879h, this.f2880i);
                    long e2 = b.e(b.a(c0.d(this.f2879h, this.f2880i, ""), "yyyy-MM-dd HH:mm:ss"), b.a(b.d("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
                    if (a || (a && e2 >= 30)) {
                        z = true;
                    }
                    if (z) {
                        return;
                    }
                    c0.f(this.f2879h, this.f2880i, b.d("yyyy-MM-dd HH:mm:ss"));
                    w.b.c(this.f2879h, this.f2881j, 10000L, bVar);
                    return;
                }
                i2 = 0;
                int width2 = this.f2878g.getWidth() - i2;
                if (width2 >= 0) {
                }
                r.b(c.a, "anchorView width = " + this.f2878g.getWidth());
                w.b bVar2 = new w.b(this.f2878g, i4, 0, 4, null);
                a = c0.a(this.f2879h, this.f2880i);
                long e22 = b.e(b.a(c0.d(this.f2879h, this.f2880i, ""), "yyyy-MM-dd HH:mm:ss"), b.a(b.d("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
                if (a) {
                }
                z = true;
                if (z) {
                }
            }
        }

        private a() {
        }

        @JvmStatic
        public final void a(@Nullable FragmentActivity fragmentActivity, @Nullable View view) {
            if (!g0.a(fragmentActivity) || view == null) {
                return;
            }
            View inflate = LayoutInflater.from(fragmentActivity).inflate(R.layout.lib_cashier_sdk_popup_gradual_payment_guidance, (ViewGroup) null);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(acti\u2026l_payment_guidance, null)");
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver != null) {
                try {
                    viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserverOnGlobalLayoutListenerC0103a(view, fragmentActivity, "Lib_Cashier_Gradual_Payment_Hint6_String", inflate));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
