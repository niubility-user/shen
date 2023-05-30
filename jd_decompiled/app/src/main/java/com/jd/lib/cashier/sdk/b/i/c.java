package com.jd.lib.cashier.sdk.b.i;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.g0;
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
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onGlobalLayout() {
                /*
                    r12 = this;
                    int r0 = android.os.Build.VERSION.SDK_INT
                    r1 = 16
                    if (r0 < r1) goto L12
                    android.view.View r0 = r12.f2878g
                    android.view.ViewTreeObserver r0 = r0.getViewTreeObserver()
                    if (r0 == 0) goto L21
                    r0.removeOnGlobalLayoutListener(r12)
                    goto L21
                L12:
                    r1 = 14
                    if (r0 < r1) goto L21
                    android.view.View r0 = r12.f2878g
                    android.view.ViewTreeObserver r0 = r0.getViewTreeObserver()
                    if (r0 == 0) goto L21
                    r0.removeGlobalOnLayoutListener(r12)
                L21:
                    r0 = 0
                    androidx.fragment.app.FragmentActivity r1 = r12.f2879h     // Catch: android.content.res.Resources.NotFoundException -> L33
                    if (r1 == 0) goto L33
                    android.content.res.Resources r1 = r1.getResources()     // Catch: android.content.res.Resources.NotFoundException -> L33
                    if (r1 == 0) goto L33
                    int r2 = com.jd.lib.cashier.sdk.R.dimen.lib_cashier_sdk_dp_225     // Catch: android.content.res.Resources.NotFoundException -> L33
                    int r1 = r1.getDimensionPixelSize(r2)     // Catch: android.content.res.Resources.NotFoundException -> L33
                    goto L34
                L33:
                    r1 = 0
                L34:
                    android.view.View r2 = r12.f2878g
                    int r2 = r2.getWidth()
                    int r2 = r2 - r1
                    if (r2 >= 0) goto L3f
                    r5 = r2
                    goto L40
                L3f:
                    r5 = 0
                L40:
                    java.lang.String r1 = com.jd.lib.cashier.sdk.b.i.c.a()
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.String r3 = "anchorView width = "
                    r2.append(r3)
                    android.view.View r3 = r12.f2878g
                    int r3 = r3.getWidth()
                    r2.append(r3)
                    java.lang.String r2 = r2.toString()
                    com.jd.lib.cashier.sdk.core.utils.r.b(r1, r2)
                    com.jd.lib.cashier.sdk.core.utils.w$b r11 = new com.jd.lib.cashier.sdk.core.utils.w$b
                    android.view.View r4 = r12.f2878g
                    r6 = 0
                    r7 = 4
                    r8 = 0
                    r3 = r11
                    r3.<init>(r4, r5, r6, r7, r8)
                    androidx.fragment.app.FragmentActivity r1 = r12.f2879h
                    java.lang.String r2 = r12.f2880i
                    boolean r1 = com.jd.lib.cashier.sdk.core.utils.c0.a(r1, r2)
                    androidx.fragment.app.FragmentActivity r2 = r12.f2879h
                    java.lang.String r3 = r12.f2880i
                    java.lang.String r4 = ""
                    java.lang.String r2 = com.jd.lib.cashier.sdk.core.utils.c0.d(r2, r3, r4)
                    java.lang.String r3 = "yyyy-MM-dd HH:mm:ss"
                    java.util.Date r2 = com.jd.lib.cashier.sdk.b.i.b.a(r2, r3)
                    java.lang.String r4 = com.jd.lib.cashier.sdk.b.i.b.d(r3)
                    java.util.Date r4 = com.jd.lib.cashier.sdk.b.i.b.a(r4, r3)
                    long r4 = com.jd.lib.cashier.sdk.b.i.b.e(r2, r4)
                    if (r1 == 0) goto L98
                    if (r1 == 0) goto L99
                    r1 = 30
                    long r1 = (long) r1
                    int r6 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
                    if (r6 < 0) goto L99
                L98:
                    r0 = 1
                L99:
                    if (r0 == 0) goto Lb1
                    androidx.fragment.app.FragmentActivity r0 = r12.f2879h
                    java.lang.String r1 = r12.f2880i
                    java.lang.String r2 = com.jd.lib.cashier.sdk.b.i.b.d(r3)
                    com.jd.lib.cashier.sdk.core.utils.c0.f(r0, r1, r2)
                    com.jd.lib.cashier.sdk.core.utils.w$a r6 = com.jd.lib.cashier.sdk.core.utils.w.b
                    androidx.fragment.app.FragmentActivity r7 = r12.f2879h
                    android.view.View r8 = r12.f2881j
                    r9 = 10000(0x2710, double:4.9407E-320)
                    r6.c(r7, r8, r9, r11)
                Lb1:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.b.i.c.a.ViewTreeObserverOnGlobalLayoutListenerC0103a.onGlobalLayout():void");
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
