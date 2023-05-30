package com.jd.lib.cashier.sdk.pay.dialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.j0;
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.adapter.LargePaymentInstructionAdapter;
import com.jd.lib.cashier.sdk.pay.bean.CashierPayEntity;
import com.jd.lib.cashier.sdk.pay.bean.GradualPayInfo;
import com.jd.lib.cashier.sdk.pay.bean.TopFloor;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
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
        /* JADX WARN: Removed duplicated region for block: B:166:0x006e A[Catch: NumberFormatException -> 0x0073, TRY_LEAVE, TryCatch #1 {NumberFormatException -> 0x0073, blocks: (B:164:0x0068, B:166:0x006e), top: B:188:0x0068 }] */
        /* JADX WARN: Removed duplicated region for block: B:171:0x007a  */
        /* JADX WARN: Removed duplicated region for block: B:173:0x009f  */
        @Override // android.view.View.OnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void onClick(View view) {
            float f2;
            CashierPayViewModel x;
            String str;
            EditText editText;
            Editable text;
            String obj;
            CharSequence trim;
            Editable text2;
            String obj2;
            CharSequence trim2;
            com.jd.lib.cashier.sdk.h.e.a d = com.jd.lib.cashier.sdk.h.e.a.d();
            FragmentActivity fragmentActivity = this.f3905g;
            GradualPayInfo gradualPayInfo = this.f3906h;
            String str2 = gradualPayInfo.totalAmount;
            String str3 = "";
            if (str2 == null) {
                str2 = "";
            }
            String str4 = gradualPayInfo.suggestedAmount;
            if (str4 == null) {
                str4 = "";
            }
            EditText editText2 = this.f3907i;
            if (editText2 != null && (text2 = editText2.getText()) != null && (obj2 = text2.toString()) != null) {
                if (obj2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                trim2 = StringsKt__StringsKt.trim((CharSequence) obj2);
                String obj3 = trim2.toString();
                if (obj3 != null) {
                    str3 = obj3;
                }
            }
            d.X(fragmentActivity, str2, str4, str3);
            float f3 = 0.0f;
            try {
                editText = this.f3907i;
            } catch (NumberFormatException unused) {
            }
            if (editText != null && (text = editText.getText()) != null && (obj = text.toString()) != null) {
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
                trim = StringsKt__StringsKt.trim((CharSequence) obj);
                String obj4 = trim.toString();
                if (obj4 != null) {
                    f2 = Float.parseFloat(obj4);
                    str = this.f3906h.minAmount;
                    if (str != null) {
                        f3 = Float.parseFloat(str);
                    }
                    if (f2 <= this.f3908j) {
                        f0.c("\u4ed8\u6b3e\u91d1\u989d\u4e0d\u53ef\u8d85\u8fc7" + this.f3909k + com.jd.lib.cashier.sdk.h.h.f.a(this.f3908j));
                        this.f3910l.invoke2();
                        return;
                    } else if (f2 < f3) {
                        f0.c("\u4ed8\u6b3e\u91d1\u989d\u4e0d\u53ef\u4f4e\u4e8e" + this.f3909k + f3);
                        this.f3910l.invoke2();
                        return;
                    } else {
                        this.f3911m.dismiss();
                        FragmentActivity fragmentActivity2 = this.f3905g;
                        if (!(fragmentActivity2 instanceof CashierPayActivity)) {
                            fragmentActivity2 = null;
                        }
                        CashierPayActivity cashierPayActivity = (CashierPayActivity) fragmentActivity2;
                        if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null) {
                            return;
                        }
                        x.l(this.f3905g, "1", String.valueOf(f2));
                        return;
                    }
                }
            }
            f2 = 0.0f;
            str = this.f3906h.minAmount;
            if (str != null) {
            }
            if (f2 <= this.f3908j) {
            }
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
    /* JADX WARN: Code restructure failed: missing block: B:152:0x013c, code lost:
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x013d, code lost:
        r4 = com.jd.lib.cashier.sdk.pay.dialog.g.a;
        r0.printStackTrace();
        com.jd.lib.cashier.sdk.core.utils.r.a(r4, kotlin.Unit.INSTANCE);
        r0 = 0.0f;
     */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x012f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0130 A[Catch: Exception -> 0x013c, TryCatch #0 {Exception -> 0x013c, blocks: (B:136:0x0114, B:143:0x0127, B:146:0x0130, B:148:0x0134, B:139:0x011d, B:141:0x0121), top: B:163:0x0114 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0209  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final View d(FragmentActivity fragmentActivity, Dialog dialog, GradualPayInfo gradualPayInfo) {
        String str;
        TextView textView;
        String str2;
        String str3;
        String str4;
        float parseFloat;
        String str5;
        float parseFloat2;
        CashierPayViewModel x;
        CashierPayEntity cashierPayEntity;
        TopFloor topFloor;
        String str6;
        View inflate = LayoutInflater.from(fragmentActivity).inflate(R.layout.lib_cashier_sdk_dialog_cashier_large_payment, (ViewGroup) null, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(acti\u2026rge_payment, null, false)");
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.container_large_payment_edit);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_large_payment_dialog_instruction);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.img_large_payment_dialog_close);
        EditText edSuggestAmount = (EditText) inflate.findViewById(R.id.ed_large_payment_suggest_amount_input);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.img_large_payment_dialog_input_delete);
        TextView tvTotalAmount = (TextView) inflate.findViewById(R.id.tv_large_payment_total_amount);
        TextView tvRemainingAmount = (TextView) inflate.findViewById(R.id.tv_large_payment_remaining_amount);
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_large_payment_confirm);
        TextView textView4 = (TextView) inflate.findViewById(R.id.tv_large_payment_cancel);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.container_large_payment_instruction);
        ImageView imageView3 = (ImageView) inflate.findViewById(R.id.img_large_payment_instruction_back);
        ImageView imageView4 = (ImageView) inflate.findViewById(R.id.img_large_payment_instruction_close);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_large_payment_instruction_content);
        LinearLayout largePaymentAmountRoot = (LinearLayout) inflate.findViewById(R.id.lib_cashier_large_payment_amount_root);
        TextView totalAmountTitle = (TextView) inflate.findViewById(R.id.lib_cashier_payment_total_amount_title);
        TextView textView5 = (TextView) inflate.findViewById(R.id.lib_cashier_large_payment_remaining_amount_title);
        c cVar = new c(gradualPayInfo, edSuggestAmount);
        cVar.invoke2();
        CashierPayActivity cashierPayActivity = (CashierPayActivity) (!(fragmentActivity instanceof CashierPayActivity) ? null : fragmentActivity);
        if (cashierPayActivity == null || (x = cashierPayActivity.x()) == null) {
            str = "";
        } else {
            str = "";
            com.jd.lib.cashier.sdk.h.c.a b2 = x.b();
            if (b2 != null && (cashierPayEntity = b2.K) != null && (topFloor = cashierPayEntity.topFloor) != null && (str6 = topFloor.moneyFlag) != null) {
                textView = textView5;
                str2 = str6;
                Intrinsics.checkExpressionValueIsNotNull(tvTotalAmount, "tvTotalAmount");
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                str3 = gradualPayInfo.totalAmount;
                if (str3 == null) {
                    str3 = str;
                }
                sb.append(str3);
                tvTotalAmount.setText(sb.toString());
                if (!TextUtils.isEmpty(gradualPayInfo.totalAmount) && (str4 = gradualPayInfo.totalAmount) != null) {
                    parseFloat = Float.parseFloat(str4);
                    if (!TextUtils.isEmpty(gradualPayInfo.alreadyPaidAmount) && (str5 = gradualPayInfo.alreadyPaidAmount) != null) {
                        parseFloat2 = Float.parseFloat(str5);
                        float f2 = parseFloat - parseFloat2;
                        Intrinsics.checkExpressionValueIsNotNull(tvRemainingAmount, "tvRemainingAmount");
                        tvRemainingAmount.setText(str2 + com.jd.lib.cashier.sdk.h.h.f.a(f2));
                        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
                        recyclerView.setAdapter(new LargePaymentInstructionAdapter(gradualPayInfo.graduallyPayReadme));
                        if (!Intrinsics.areEqual("1", gradualPayInfo.alreadyPaid)) {
                            j0.b(textView4);
                        } else {
                            j0.d(textView4);
                            textView4.setOnClickListener(new d(dialog, fragmentActivity));
                        }
                        textView3.setOnClickListener(new e(fragmentActivity, gradualPayInfo, edSuggestAmount, f2, str2, cVar, dialog));
                        imageView.setOnClickListener(new f(dialog));
                        imageView4.setOnClickListener(new ViewOnClickListenerC0138g(dialog));
                        edSuggestAmount.setOnFocusChangeListener(new h(imageView2));
                        imageView2.setOnClickListener(new i(edSuggestAmount));
                        float appWidth = DpiUtil.getAppWidth(fragmentActivity);
                        textView2.setOnClickListener(new j(appWidth, linearLayout, linearLayout2, fragmentActivity));
                        imageView3.setOnClickListener(new b(appWidth, linearLayout, linearLayout2));
                        Intrinsics.checkExpressionValueIsNotNull(edSuggestAmount, "edSuggestAmount");
                        if (!TextUtils.isEmpty(edSuggestAmount.getText())) {
                            edSuggestAmount.setContentDescription(edSuggestAmount.getText().toString() + str2);
                        }
                        Intrinsics.checkExpressionValueIsNotNull(largePaymentAmountRoot, "largePaymentAmountRoot");
                        StringBuilder sb2 = new StringBuilder();
                        Intrinsics.checkExpressionValueIsNotNull(totalAmountTitle, "totalAmountTitle");
                        sb2.append(totalAmountTitle.getContentDescription().toString());
                        sb2.append(tvTotalAmount.getText());
                        TextView remainAmountTitle = textView;
                        Intrinsics.checkExpressionValueIsNotNull(remainAmountTitle, "remainAmountTitle");
                        sb2.append(remainAmountTitle.getContentDescription().toString());
                        sb2.append(tvRemainingAmount.getText());
                        largePaymentAmountRoot.setContentDescription(sb2.toString());
                        return inflate;
                    }
                    parseFloat2 = 0.0f;
                    float f22 = parseFloat - parseFloat2;
                    Intrinsics.checkExpressionValueIsNotNull(tvRemainingAmount, "tvRemainingAmount");
                    tvRemainingAmount.setText(str2 + com.jd.lib.cashier.sdk.h.h.f.a(f22));
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
                    recyclerView.setAdapter(new LargePaymentInstructionAdapter(gradualPayInfo.graduallyPayReadme));
                    if (!Intrinsics.areEqual("1", gradualPayInfo.alreadyPaid)) {
                    }
                    textView3.setOnClickListener(new e(fragmentActivity, gradualPayInfo, edSuggestAmount, f22, str2, cVar, dialog));
                    imageView.setOnClickListener(new f(dialog));
                    imageView4.setOnClickListener(new ViewOnClickListenerC0138g(dialog));
                    edSuggestAmount.setOnFocusChangeListener(new h(imageView2));
                    imageView2.setOnClickListener(new i(edSuggestAmount));
                    float appWidth2 = DpiUtil.getAppWidth(fragmentActivity);
                    textView2.setOnClickListener(new j(appWidth2, linearLayout, linearLayout2, fragmentActivity));
                    imageView3.setOnClickListener(new b(appWidth2, linearLayout, linearLayout2));
                    Intrinsics.checkExpressionValueIsNotNull(edSuggestAmount, "edSuggestAmount");
                    if (!TextUtils.isEmpty(edSuggestAmount.getText())) {
                    }
                    Intrinsics.checkExpressionValueIsNotNull(largePaymentAmountRoot, "largePaymentAmountRoot");
                    StringBuilder sb22 = new StringBuilder();
                    Intrinsics.checkExpressionValueIsNotNull(totalAmountTitle, "totalAmountTitle");
                    sb22.append(totalAmountTitle.getContentDescription().toString());
                    sb22.append(tvTotalAmount.getText());
                    TextView remainAmountTitle2 = textView;
                    Intrinsics.checkExpressionValueIsNotNull(remainAmountTitle2, "remainAmountTitle");
                    sb22.append(remainAmountTitle2.getContentDescription().toString());
                    sb22.append(tvRemainingAmount.getText());
                    largePaymentAmountRoot.setContentDescription(sb22.toString());
                    return inflate;
                }
                parseFloat = 0.0f;
                if (!TextUtils.isEmpty(gradualPayInfo.alreadyPaidAmount)) {
                    parseFloat2 = Float.parseFloat(str5);
                    float f222 = parseFloat - parseFloat2;
                    Intrinsics.checkExpressionValueIsNotNull(tvRemainingAmount, "tvRemainingAmount");
                    tvRemainingAmount.setText(str2 + com.jd.lib.cashier.sdk.h.h.f.a(f222));
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
                    recyclerView.setAdapter(new LargePaymentInstructionAdapter(gradualPayInfo.graduallyPayReadme));
                    if (!Intrinsics.areEqual("1", gradualPayInfo.alreadyPaid)) {
                    }
                    textView3.setOnClickListener(new e(fragmentActivity, gradualPayInfo, edSuggestAmount, f222, str2, cVar, dialog));
                    imageView.setOnClickListener(new f(dialog));
                    imageView4.setOnClickListener(new ViewOnClickListenerC0138g(dialog));
                    edSuggestAmount.setOnFocusChangeListener(new h(imageView2));
                    imageView2.setOnClickListener(new i(edSuggestAmount));
                    float appWidth22 = DpiUtil.getAppWidth(fragmentActivity);
                    textView2.setOnClickListener(new j(appWidth22, linearLayout, linearLayout2, fragmentActivity));
                    imageView3.setOnClickListener(new b(appWidth22, linearLayout, linearLayout2));
                    Intrinsics.checkExpressionValueIsNotNull(edSuggestAmount, "edSuggestAmount");
                    if (!TextUtils.isEmpty(edSuggestAmount.getText())) {
                    }
                    Intrinsics.checkExpressionValueIsNotNull(largePaymentAmountRoot, "largePaymentAmountRoot");
                    StringBuilder sb222 = new StringBuilder();
                    Intrinsics.checkExpressionValueIsNotNull(totalAmountTitle, "totalAmountTitle");
                    sb222.append(totalAmountTitle.getContentDescription().toString());
                    sb222.append(tvTotalAmount.getText());
                    TextView remainAmountTitle22 = textView;
                    Intrinsics.checkExpressionValueIsNotNull(remainAmountTitle22, "remainAmountTitle");
                    sb222.append(remainAmountTitle22.getContentDescription().toString());
                    sb222.append(tvRemainingAmount.getText());
                    largePaymentAmountRoot.setContentDescription(sb222.toString());
                    return inflate;
                }
                parseFloat2 = 0.0f;
                float f2222 = parseFloat - parseFloat2;
                Intrinsics.checkExpressionValueIsNotNull(tvRemainingAmount, "tvRemainingAmount");
                tvRemainingAmount.setText(str2 + com.jd.lib.cashier.sdk.h.h.f.a(f2222));
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
                recyclerView.setAdapter(new LargePaymentInstructionAdapter(gradualPayInfo.graduallyPayReadme));
                if (!Intrinsics.areEqual("1", gradualPayInfo.alreadyPaid)) {
                }
                textView3.setOnClickListener(new e(fragmentActivity, gradualPayInfo, edSuggestAmount, f2222, str2, cVar, dialog));
                imageView.setOnClickListener(new f(dialog));
                imageView4.setOnClickListener(new ViewOnClickListenerC0138g(dialog));
                edSuggestAmount.setOnFocusChangeListener(new h(imageView2));
                imageView2.setOnClickListener(new i(edSuggestAmount));
                float appWidth222 = DpiUtil.getAppWidth(fragmentActivity);
                textView2.setOnClickListener(new j(appWidth222, linearLayout, linearLayout2, fragmentActivity));
                imageView3.setOnClickListener(new b(appWidth222, linearLayout, linearLayout2));
                Intrinsics.checkExpressionValueIsNotNull(edSuggestAmount, "edSuggestAmount");
                if (!TextUtils.isEmpty(edSuggestAmount.getText())) {
                }
                Intrinsics.checkExpressionValueIsNotNull(largePaymentAmountRoot, "largePaymentAmountRoot");
                StringBuilder sb2222 = new StringBuilder();
                Intrinsics.checkExpressionValueIsNotNull(totalAmountTitle, "totalAmountTitle");
                sb2222.append(totalAmountTitle.getContentDescription().toString());
                sb2222.append(tvTotalAmount.getText());
                TextView remainAmountTitle222 = textView;
                Intrinsics.checkExpressionValueIsNotNull(remainAmountTitle222, "remainAmountTitle");
                sb2222.append(remainAmountTitle222.getContentDescription().toString());
                sb2222.append(tvRemainingAmount.getText());
                largePaymentAmountRoot.setContentDescription(sb2222.toString());
                return inflate;
            }
        }
        textView = textView5;
        str2 = str;
        Intrinsics.checkExpressionValueIsNotNull(tvTotalAmount, "tvTotalAmount");
        StringBuilder sb3 = new StringBuilder();
        sb3.append(str2);
        str3 = gradualPayInfo.totalAmount;
        if (str3 == null) {
        }
        sb3.append(str3);
        tvTotalAmount.setText(sb3.toString());
        if (!TextUtils.isEmpty(gradualPayInfo.totalAmount)) {
            parseFloat = Float.parseFloat(str4);
            if (!TextUtils.isEmpty(gradualPayInfo.alreadyPaidAmount)) {
            }
            parseFloat2 = 0.0f;
            float f22222 = parseFloat - parseFloat2;
            Intrinsics.checkExpressionValueIsNotNull(tvRemainingAmount, "tvRemainingAmount");
            tvRemainingAmount.setText(str2 + com.jd.lib.cashier.sdk.h.h.f.a(f22222));
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
            recyclerView.setAdapter(new LargePaymentInstructionAdapter(gradualPayInfo.graduallyPayReadme));
            if (!Intrinsics.areEqual("1", gradualPayInfo.alreadyPaid)) {
            }
            textView3.setOnClickListener(new e(fragmentActivity, gradualPayInfo, edSuggestAmount, f22222, str2, cVar, dialog));
            imageView.setOnClickListener(new f(dialog));
            imageView4.setOnClickListener(new ViewOnClickListenerC0138g(dialog));
            edSuggestAmount.setOnFocusChangeListener(new h(imageView2));
            imageView2.setOnClickListener(new i(edSuggestAmount));
            float appWidth2222 = DpiUtil.getAppWidth(fragmentActivity);
            textView2.setOnClickListener(new j(appWidth2222, linearLayout, linearLayout2, fragmentActivity));
            imageView3.setOnClickListener(new b(appWidth2222, linearLayout, linearLayout2));
            Intrinsics.checkExpressionValueIsNotNull(edSuggestAmount, "edSuggestAmount");
            if (!TextUtils.isEmpty(edSuggestAmount.getText())) {
            }
            Intrinsics.checkExpressionValueIsNotNull(largePaymentAmountRoot, "largePaymentAmountRoot");
            StringBuilder sb22222 = new StringBuilder();
            Intrinsics.checkExpressionValueIsNotNull(totalAmountTitle, "totalAmountTitle");
            sb22222.append(totalAmountTitle.getContentDescription().toString());
            sb22222.append(tvTotalAmount.getText());
            TextView remainAmountTitle2222 = textView;
            Intrinsics.checkExpressionValueIsNotNull(remainAmountTitle2222, "remainAmountTitle");
            sb22222.append(remainAmountTitle2222.getContentDescription().toString());
            sb22222.append(tvRemainingAmount.getText());
            largePaymentAmountRoot.setContentDescription(sb22222.toString());
            return inflate;
        }
        parseFloat = 0.0f;
        if (!TextUtils.isEmpty(gradualPayInfo.alreadyPaidAmount)) {
        }
        parseFloat2 = 0.0f;
        float f222222 = parseFloat - parseFloat2;
        Intrinsics.checkExpressionValueIsNotNull(tvRemainingAmount, "tvRemainingAmount");
        tvRemainingAmount.setText(str2 + com.jd.lib.cashier.sdk.h.h.f.a(f222222));
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
        recyclerView.setAdapter(new LargePaymentInstructionAdapter(gradualPayInfo.graduallyPayReadme));
        if (!Intrinsics.areEqual("1", gradualPayInfo.alreadyPaid)) {
        }
        textView3.setOnClickListener(new e(fragmentActivity, gradualPayInfo, edSuggestAmount, f222222, str2, cVar, dialog));
        imageView.setOnClickListener(new f(dialog));
        imageView4.setOnClickListener(new ViewOnClickListenerC0138g(dialog));
        edSuggestAmount.setOnFocusChangeListener(new h(imageView2));
        imageView2.setOnClickListener(new i(edSuggestAmount));
        float appWidth22222 = DpiUtil.getAppWidth(fragmentActivity);
        textView2.setOnClickListener(new j(appWidth22222, linearLayout, linearLayout2, fragmentActivity));
        imageView3.setOnClickListener(new b(appWidth22222, linearLayout, linearLayout2));
        Intrinsics.checkExpressionValueIsNotNull(edSuggestAmount, "edSuggestAmount");
        if (!TextUtils.isEmpty(edSuggestAmount.getText())) {
        }
        Intrinsics.checkExpressionValueIsNotNull(largePaymentAmountRoot, "largePaymentAmountRoot");
        StringBuilder sb222222 = new StringBuilder();
        Intrinsics.checkExpressionValueIsNotNull(totalAmountTitle, "totalAmountTitle");
        sb222222.append(totalAmountTitle.getContentDescription().toString());
        sb222222.append(tvTotalAmount.getText());
        TextView remainAmountTitle22222 = textView;
        Intrinsics.checkExpressionValueIsNotNull(remainAmountTitle22222, "remainAmountTitle");
        sb222222.append(remainAmountTitle22222.getContentDescription().toString());
        sb222222.append(tvRemainingAmount.getText());
        largePaymentAmountRoot.setContentDescription(sb222222.toString());
        return inflate;
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
