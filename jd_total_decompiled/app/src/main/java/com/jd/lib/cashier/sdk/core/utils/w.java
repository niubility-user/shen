package com.jd.lib.cashier.sdk.core.utils;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;
import androidx.fragment.app.FragmentActivity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class w {
    public static final a b = new a(null);
    private static final n a = new n();

    /* loaded from: classes14.dex */
    public static final class a {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.jd.lib.cashier.sdk.core.utils.w$a$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        public static final class RunnableC0105a implements Runnable {

            /* renamed from: g  reason: collision with root package name */
            final /* synthetic */ PopupWindow f3098g;

            /* renamed from: h  reason: collision with root package name */
            final /* synthetic */ Activity f3099h;

            RunnableC0105a(PopupWindow popupWindow, Activity activity) {
                this.f3098g = popupWindow;
                this.f3099h = activity;
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (g0.a(this.f3099h)) {
                    PopupWindow popupWindow = this.f3098g;
                    if ((popupWindow != null ? Boolean.valueOf(popupWindow.isShowing()) : null).booleanValue()) {
                        this.f3098g.dismiss();
                    }
                }
            }
        }

        private a() {
        }

        private final void a(Activity activity, PopupWindow popupWindow, long j2) {
            if (j2 > 0) {
                w.a.postDelayed(new RunnableC0105a(popupWindow, activity), j2);
            }
        }

        private final void b(PopupWindow popupWindow, b bVar) {
            View a;
            if (bVar == null || (a = bVar.a()) == null) {
                return;
            }
            popupWindow.showAsDropDown(a, bVar.b(), bVar.c());
        }

        public final void c(@Nullable FragmentActivity fragmentActivity, @Nullable View view, long j2, @Nullable b bVar) {
            if (!g0.a(fragmentActivity) || view == null) {
                return;
            }
            PopupWindow popupWindow = new PopupWindow(fragmentActivity);
            popupWindow.setWidth(-2);
            popupWindow.setHeight(-2);
            popupWindow.setFocusable(true);
            popupWindow.setTouchable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
            popupWindow.setContentView(view);
            b(popupWindow, bVar);
            a(fragmentActivity, popupWindow, j2);
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* loaded from: classes14.dex */
    public static final class b {
        @Nullable
        private final View a;
        private final int b;

        /* renamed from: c  reason: collision with root package name */
        private final int f3100c;

        public b(@Nullable View view, int i2, int i3) {
            this.a = view;
            this.b = i2;
            this.f3100c = i3;
        }

        @Nullable
        public final View a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public final int c() {
            return this.f3100c;
        }

        public /* synthetic */ b(View view, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this((i4 & 1) != 0 ? null : view, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3);
        }
    }
}
