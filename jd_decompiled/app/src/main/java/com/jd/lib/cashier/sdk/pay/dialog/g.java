package com.jd.lib.cashier.sdk.pay.dialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.GradualPayInfo;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class g {
    private static final String a = "g";
    public static final g b = new g();

    /* loaded from: classes14.dex */
    public static final class a implements Animator.AnimatorListener {

        /* renamed from: g */
        final /* synthetic */ View f3898g;

        /* renamed from: h */
        final /* synthetic */ View f3899h;

        a(View view, View view2) {
            this.f3898g = view;
            this.f3899h = view2;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(@Nullable Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(@Nullable Animator animator) {
            View view = this.f3899h;
            if (view != null) {
                view.setVisibility(8);
            }
            View view2 = this.f3898g;
            if (view2 != null) {
                view2.bringToFront();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(@Nullable Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(@Nullable Animator animator) {
            View view = this.f3898g;
            if (view != null) {
                view.setVisibility(0);
            }
        }
    }

    /* loaded from: classes14.dex */
    public static final class b implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ float f3900g;

        /* renamed from: h */
        final /* synthetic */ LinearLayout f3901h;

        /* renamed from: i */
        final /* synthetic */ LinearLayout f3902i;

        b(float f2, LinearLayout linearLayout, LinearLayout linearLayout2) {
            this.f3900g = f2;
            this.f3901h = linearLayout;
            this.f3902i = linearLayout2;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            g.b.c(this.f3900g, this.f3901h, this.f3902i);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "invoke", "()V", "bindSuggestionAmount"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class c extends Lambda implements Function0<Unit> {
        final /* synthetic */ EditText $edSuggestAmount;
        final /* synthetic */ GradualPayInfo $gradualPayInfo;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(GradualPayInfo gradualPayInfo, EditText editText) {
            super(0);
            this.$gradualPayInfo = gradualPayInfo;
            this.$edSuggestAmount = editText;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke */
        public final void invoke2() {
            String str = this.$gradualPayInfo.suggestedAmount;
            if (str == null) {
                str = "";
            }
            this.$edSuggestAmount.setText(str);
            this.$edSuggestAmount.setSelection(str.length());
        }
    }

    /* loaded from: classes14.dex */
    public static final class d implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ Dialog f3903g;

        /* renamed from: h */
        final /* synthetic */ FragmentActivity f3904h;

        d(Dialog dialog, FragmentActivity fragmentActivity) {
            this.f3903g = dialog;
            this.f3904h = fragmentActivity;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            CashierPayViewModel x;
            this.f3903g.dismiss();
            FragmentActivity fragmentActivity = this.f3904h;
            if (!(fragmentActivity instanceof CashierPayActivity)) {
                fragmentActivity = null;
            }
            CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity;
            if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null) {
                return;
            }
            CashierPayViewModel.m(x, this.f3904h, null, null, 6, null);
        }
    }

    /* loaded from: classes14.dex */
    public static final class e implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ FragmentActivity f3905g;

        /* renamed from: h */
        final /* synthetic */ GradualPayInfo f3906h;

        /* renamed from: i */
        final /* synthetic */ EditText f3907i;

        /* renamed from: j */
        final /* synthetic */ float f3908j;

        /* renamed from: k */
        final /* synthetic */ String f3909k;

        /* renamed from: l */
        final /* synthetic */ c f3910l;

        /* renamed from: m */
        final /* synthetic */ Dialog f3911m;

        e(FragmentActivity fragmentActivity, GradualPayInfo gradualPayInfo, EditText editText, float f2, String str, c cVar, Dialog dialog) {
            this.f3905g = fragmentActivity;
            this.f3906h = gradualPayInfo;
            this.f3907i = editText;
            this.f3908j = f2;
            this.f3909k = str;
            this.f3910l = cVar;
            this.f3911m = dialog;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(15:1|(1:3)|4|(1:6)|7|(1:(2:14|(1:16))(2:17|18))|20|(2:21|22)|(1:(2:29|(6:31|32|33|(1:35)|37|(2:39|40)(2:41|(2:43|44)(4:45|(1:47)|48|(1:54)(2:52|53)))))(2:57|58))|59|32|33|(0)|37|(0)(0)) */
        /* JADX WARN: Removed duplicated region for block: B:102:0x006e A[Catch: NumberFormatException -> 0x0073, TRY_LEAVE, TryCatch #1 {NumberFormatException -> 0x0073, blocks: (B:100:0x0068, B:102:0x006e), top: B:124:0x0068 }] */
        /* JADX WARN: Removed duplicated region for block: B:107:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:109:0x009f  */
        @Override // android.view.View.OnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void onClick(android.view.View r7) {
            /*
                r6 = this;
                com.jd.lib.cashier.sdk.h.e.a r7 = com.jd.lib.cashier.sdk.h.e.a.d()
                androidx.fragment.app.FragmentActivity r0 = r6.f3905g
                com.jd.lib.cashier.sdk.pay.bean.GradualPayInfo r1 = r6.f3906h
                java.lang.String r2 = r1.totalAmount
                java.lang.String r3 = ""
                if (r2 == 0) goto Lf
                goto L10
            Lf:
                r2 = r3
            L10:
                java.lang.String r1 = r1.suggestedAmount
                if (r1 == 0) goto L15
                goto L16
            L15:
                r1 = r3
            L16:
                android.widget.EditText r4 = r6.f3907i
                java.lang.String r5 = "null cannot be cast to non-null type kotlin.CharSequence"
                if (r4 == 0) goto L3c
                android.text.Editable r4 = r4.getText()
                if (r4 == 0) goto L3c
                java.lang.String r4 = r4.toString()
                if (r4 == 0) goto L3c
                if (r4 == 0) goto L36
                java.lang.CharSequence r4 = kotlin.text.StringsKt.trim(r4)
                java.lang.String r4 = r4.toString()
                if (r4 == 0) goto L3c
                r3 = r4
                goto L3c
            L36:
                kotlin.TypeCastException r7 = new kotlin.TypeCastException
                r7.<init>(r5)
                throw r7
            L3c:
                r7.X(r0, r2, r1, r3)
                r7 = 0
                android.widget.EditText r0 = r6.f3907i     // Catch: java.lang.NumberFormatException -> L67
                if (r0 == 0) goto L67
                android.text.Editable r0 = r0.getText()     // Catch: java.lang.NumberFormatException -> L67
                if (r0 == 0) goto L67
                java.lang.String r0 = r0.toString()     // Catch: java.lang.NumberFormatException -> L67
                if (r0 == 0) goto L67
                if (r0 == 0) goto L61
                java.lang.CharSequence r0 = kotlin.text.StringsKt.trim(r0)     // Catch: java.lang.NumberFormatException -> L67
                java.lang.String r0 = r0.toString()     // Catch: java.lang.NumberFormatException -> L67
                if (r0 == 0) goto L67
                float r0 = java.lang.Float.parseFloat(r0)     // Catch: java.lang.NumberFormatException -> L67
                goto L68
            L61:
                kotlin.TypeCastException r0 = new kotlin.TypeCastException     // Catch: java.lang.NumberFormatException -> L67
                r0.<init>(r5)     // Catch: java.lang.NumberFormatException -> L67
                throw r0     // Catch: java.lang.NumberFormatException -> L67
            L67:
                r0 = 0
            L68:
                com.jd.lib.cashier.sdk.pay.bean.GradualPayInfo r1 = r6.f3906h     // Catch: java.lang.NumberFormatException -> L73
                java.lang.String r1 = r1.minAmount     // Catch: java.lang.NumberFormatException -> L73
                if (r1 == 0) goto L74
                float r7 = java.lang.Float.parseFloat(r1)     // Catch: java.lang.NumberFormatException -> L73
                goto L74
            L73:
            L74:
                float r1 = r6.f3908j
                int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r1 <= 0) goto L9f
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                java.lang.String r0 = "\u4ed8\u6b3e\u91d1\u989d\u4e0d\u53ef\u8d85\u8fc7"
                r7.append(r0)
                java.lang.String r0 = r6.f3909k
                r7.append(r0)
                float r0 = r6.f3908j
                java.lang.String r0 = com.jd.lib.cashier.sdk.h.h.f.a(r0)
                r7.append(r0)
                java.lang.String r7 = r7.toString()
                com.jd.lib.cashier.sdk.core.utils.f0.c(r7)
                com.jd.lib.cashier.sdk.pay.dialog.g$c r7 = r6.f3910l
                r7.invoke2()
                return
            L9f:
                int r1 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
                if (r1 >= 0) goto Lc2
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "\u4ed8\u6b3e\u91d1\u989d\u4e0d\u53ef\u4f4e\u4e8e"
                r0.append(r1)
                java.lang.String r1 = r6.f3909k
                r0.append(r1)
                r0.append(r7)
                java.lang.String r7 = r0.toString()
                com.jd.lib.cashier.sdk.core.utils.f0.c(r7)
                com.jd.lib.cashier.sdk.pay.dialog.g$c r7 = r6.f3910l
                r7.invoke2()
                return
            Lc2:
                android.app.Dialog r7 = r6.f3911m
                r7.dismiss()
                androidx.fragment.app.FragmentActivity r7 = r6.f3905g
                boolean r1 = r7 instanceof com.jd.lib.cashier.sdk.pay.view.CashierPayActivity
                if (r1 != 0) goto Lce
                r7 = 0
            Lce:
                com.jd.lib.cashier.sdk.pay.view.CashierPayActivity r7 = (com.jd.lib.cashier.sdk.pay.view.CashierPayActivity) r7
                if (r7 == 0) goto Le5
                com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel r7 = r7.x()
                com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel r7 = (com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel) r7
                if (r7 == 0) goto Le5
                androidx.fragment.app.FragmentActivity r1 = r6.f3905g
                java.lang.String r0 = java.lang.String.valueOf(r0)
                java.lang.String r2 = "1"
                r7.l(r1, r2, r0)
            Le5:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.dialog.g.e.onClick(android.view.View):void");
        }
    }

    /* loaded from: classes14.dex */
    public static final class f implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ Dialog f3912g;

        f(Dialog dialog) {
            this.f3912g = dialog;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3912g.dismiss();
        }
    }

    /* renamed from: com.jd.lib.cashier.sdk.pay.dialog.g$g */
    /* loaded from: classes14.dex */
    public static final class ViewOnClickListenerC0138g implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ Dialog f3913g;

        ViewOnClickListenerC0138g(Dialog dialog) {
            this.f3913g = dialog;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3913g.dismiss();
        }
    }

    /* loaded from: classes14.dex */
    public static final class h implements View.OnFocusChangeListener {

        /* renamed from: g */
        final /* synthetic */ ImageView f3914g;

        h(ImageView imageView) {
            this.f3914g = imageView;
        }

        @Override // android.view.View.OnFocusChangeListener
        public final void onFocusChange(View view, boolean z) {
            ImageView imgDelete = this.f3914g;
            Intrinsics.checkExpressionValueIsNotNull(imgDelete, "imgDelete");
            imgDelete.setVisibility(z ? 0 : 8);
        }
    }

    /* loaded from: classes14.dex */
    public static final class i implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ EditText f3915g;

        i(EditText editText) {
            this.f3915g = editText;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f3915g.setText("");
        }
    }

    /* loaded from: classes14.dex */
    public static final class j implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ float f3916g;

        /* renamed from: h */
        final /* synthetic */ LinearLayout f3917h;

        /* renamed from: i */
        final /* synthetic */ LinearLayout f3918i;

        /* renamed from: j */
        final /* synthetic */ FragmentActivity f3919j;

        j(float f2, LinearLayout linearLayout, LinearLayout linearLayout2, FragmentActivity fragmentActivity) {
            this.f3916g = f2;
            this.f3917h = linearLayout;
            this.f3918i = linearLayout2;
            this.f3919j = fragmentActivity;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            g.b.g(this.f3916g, this.f3917h, this.f3918i, this.f3919j);
        }
    }

    /* loaded from: classes14.dex */
    public static final class k implements Animator.AnimatorListener {

        /* renamed from: g */
        final /* synthetic */ View f3920g;

        /* renamed from: h */
        final /* synthetic */ View f3921h;

        k(View view, View view2) {
            this.f3920g = view;
            this.f3921h = view2;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(@Nullable Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(@Nullable Animator animator) {
            View view = this.f3921h;
            if (view != null) {
                view.setVisibility(8);
            }
            View view2 = this.f3920g;
            if (view2 != null) {
                view2.bringToFront();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(@Nullable Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(@Nullable Animator animator) {
            View view = this.f3920g;
            if (view != null) {
                view.setVisibility(0);
            }
        }
    }

    private g() {
    }

    public final void c(float f2, View view, View view2) {
        if (view == null || view.getVisibility() != 8) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "TranslationX", -f2, 0.0f), ObjectAnimator.ofFloat(view2, "TranslationX", 0.0f, f2));
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(300L);
        animatorSet.addListener(new a(view, view2));
        animatorSet.start();
    }

    /* JADX WARN: Can't wrap try/catch for region: R(22:1|(1:3)(1:52)|4|(1:51)(2:8|(18:16|17|(1:19)|20|21|22|(11:47|26|(8:43|30|31|(1:33)(1:40)|34|(1:36)|37|38)|29|30|31|(0)(0)|34|(0)|37|38)|25|26|(1:28)(9:41|43|30|31|(0)(0)|34|(0)|37|38)|29|30|31|(0)(0)|34|(0)|37|38))|50|17|(0)|20|21|22|(1:24)(12:45|47|26|(0)(0)|29|30|31|(0)(0)|34|(0)|37|38)|25|26|(0)(0)|29|30|31|(0)(0)|34|(0)|37|38) */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x013c, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x013d, code lost:
        r4 = com.jd.lib.cashier.sdk.pay.dialog.g.a;
        r0.printStackTrace();
        com.jd.lib.cashier.sdk.core.utils.r.a(r4, kotlin.Unit.INSTANCE);
        r0 = 0.0f;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x012f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0130 A[Catch: Exception -> 0x013c, TryCatch #0 {Exception -> 0x013c, blocks: (B:81:0x0114, B:88:0x0127, B:91:0x0130, B:93:0x0134, B:84:0x011d, B:86:0x0121), top: B:108:0x0114 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final android.view.View d(androidx.fragment.app.FragmentActivity r29, android.app.Dialog r30, com.jd.lib.cashier.sdk.pay.bean.GradualPayInfo r31) {
        /*
            Method dump skipped, instructions count: 617
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.cashier.sdk.pay.dialog.g.d(androidx.fragment.app.FragmentActivity, android.app.Dialog, com.jd.lib.cashier.sdk.pay.bean.GradualPayInfo):android.view.View");
    }

    private final void e(View view) {
        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(R.id.img_large_payment_instruction_back);
            View findViewById = view.findViewById(R.id.lib_cashier_large_payment_split_line);
            ((TextView) view.findViewById(R.id.tv_large_payment_left_title)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_2E2D2D));
            ((TextView) view.findViewById(R.id.lib_cashier_large_payment_introduction_title)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            ((TextView) view.findViewById(R.id.tv_large_payment_dialog_instruction)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            ((EditText) view.findViewById(R.id.ed_large_payment_suggest_amount_input)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C));
            ((TextView) view.findViewById(R.id.tv_large_payment_total_amount)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            ((TextView) view.findViewById(R.id.tv_large_payment_remaining_amount)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2270C));
            ((TextView) view.findViewById(R.id.lib_cashier_large_payment_suggest_title)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            findViewById.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_F2F2F2));
            ((TextView) view.findViewById(R.id.lib_cashier_payment_total_amount_title)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            ((TextView) view.findViewById(R.id.lib_cashier_large_payment_remaining_amount_title)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
            imageView.setImageResource(JDDarkUtil.isDarkMode() ? R.drawable.lib_cashier_sdk_bg_back_selector_dark : R.drawable.lib_cashier_sdk_bg_back_selector);
        }
    }

    @JvmStatic
    public static final void f(@NotNull FragmentActivity fragmentActivity, @Nullable GradualPayInfo gradualPayInfo) {
        if (!g0.a(fragmentActivity) || gradualPayInfo == null) {
            return;
        }
        if (o0.a(a + "util")) {
            return;
        }
        com.jd.lib.cashier.sdk.h.e.a.d().f0(fragmentActivity);
        Dialog bottomDialog = com.jd.lib.cashier.sdk.core.utils.j.b(fragmentActivity);
        g gVar = b;
        Intrinsics.checkExpressionValueIsNotNull(bottomDialog, "bottomDialog");
        View d2 = gVar.d(fragmentActivity, bottomDialog, gradualPayInfo);
        gVar.e(d2);
        com.jd.lib.cashier.sdk.core.utils.j.a(bottomDialog, d2, -1.0f);
        bottomDialog.show();
    }

    public final void g(float f2, View view, View view2, FragmentActivity fragmentActivity) {
        if (view2 == null || view2.getVisibility() != 8) {
            return;
        }
        com.jd.lib.cashier.sdk.h.e.a.d().g0(fragmentActivity);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "TranslationX", 0.0f, -f2), ObjectAnimator.ofFloat(view2, "TranslationX", f2, 0.0f));
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(300L);
        animatorSet.addListener(new k(view2, view));
        animatorSet.start();
    }
}
