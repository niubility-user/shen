package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.m0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B#\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u00108\u001a\u0004\u0018\u000107\u0012\u0006\u0010:\u001a\u000209\u00a2\u0006\u0004\b;\u0010<B\u001b\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u00108\u001a\u0004\u0018\u000107\u00a2\u0006\u0004\b;\u0010=J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J)\u0010\u000e\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH\u0002\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0015\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\n\u00a2\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0015\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007\u00a2\u0006\u0004\b\u0015\u0010\u0016J0\u0010\u001d\u001a\u00020\u00052!\u0010\u001c\u001a\u001d\u0012\u0013\u0012\u00110\u0018\u00a2\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u00050\u0017\u00a2\u0006\u0004\b\u001d\u0010\u001eJ\r\u0010\u001f\u001a\u00020\u0005\u00a2\u0006\u0004\b\u001f\u0010\u0011J\r\u0010 \u001a\u00020\u0005\u00a2\u0006\u0004\b \u0010\u0011J\u000f\u0010!\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b!\u0010\u0011J\r\u0010\"\u001a\u00020\u0005\u00a2\u0006\u0004\b\"\u0010\u0011R\u0016\u0010%\u001a\u00020#8\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\b\u0015\u0010$R\u0016\u0010'\u001a\u00020#8\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\b&\u0010$R\u0016\u0010)\u001a\u00020#8\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\b(\u0010$R\u0018\u0010-\u001a\u0004\u0018\u00010*8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b+\u0010,R\u0016\u0010/\u001a\u00020\u00188\u0002@\u0002X\u0082.\u00a2\u0006\u0006\n\u0004\b\u000e\u0010.R\"\u00106\u001a\u0002008\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0013\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105\u00a8\u0006>"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/PaymentTriggerView;", "Landroid/widget/FrameLayout;", "Lcom/jd/lib/cashier/sdk/core/aac/e;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", com.jingdong.app.mall.e.a, "(Landroid/content/Context;)V", "Lcom/jd/lib/cashier/sdk/core/ui/widget/e;", "textPair", "", "moneyFlag", "", "needSetTag", "j", "(Lcom/jd/lib/cashier/sdk/core/ui/widget/e;Ljava/lang/String;Z)V", "b", "()V", "text", "k", "(Ljava/lang/String;)V", JshopConst.JSHOP_PROMOTIO_H, "(Lcom/jd/lib/cashier/sdk/core/ui/widget/e;Ljava/lang/String;)V", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "v", JDReactConstant.BLOCK, "f", "(Lkotlin/jvm/functions/Function1;)V", "onPause", "onResume", "onChangeSkin", "c", "Landroid/widget/TextView;", "Landroid/widget/TextView;", "titleTvPrice", "i", "subtitleTv", "g", "titleTv", "Landroid/view/View$OnClickListener;", NotifyType.LIGHTS, "Landroid/view/View$OnClickListener;", "onPaymentClickListener", "Landroid/view/View;", "paymentView", "Lcom/jd/lib/cashier/sdk/core/ui/widget/c;", "Lcom/jd/lib/cashier/sdk/core/ui/widget/c;", "getTriggerViewPaymentStatus", "()Lcom/jd/lib/cashier/sdk/core/ui/widget/c;", "m", "(Lcom/jd/lib/cashier/sdk/core/ui/widget/c;)V", "triggerViewPaymentStatus", "Landroid/util/AttributeSet;", "attributeSet", "", "defStyleAttr", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class PaymentTriggerView extends FrameLayout implements com.jd.lib.cashier.sdk.core.aac.e {

    /* renamed from: m  reason: collision with root package name */
    private static final String f3068m = PaymentTriggerView.class.getSimpleName();

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    private TextView titleTv;

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private TextView titleTvPrice;

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private TextView subtitleTv;

    /* renamed from: j  reason: collision with root package name and from kotlin metadata */
    private View paymentView;
    @NotNull

    /* renamed from: k  reason: collision with root package name and from kotlin metadata */
    private c triggerViewPaymentStatus;

    /* renamed from: l  reason: collision with root package name and from kotlin metadata */
    private View.OnClickListener onPaymentClickListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            PaymentTriggerView.this.m(c.IN_PAYMENT);
            View.OnClickListener onClickListener = PaymentTriggerView.this.onPaymentClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }
    }

    public PaymentTriggerView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.triggerViewPaymentStatus = c.DEFAULT;
        e(context);
    }

    private final void b() {
        View view = this.paymentView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentView");
        }
        view.setBackgroundResource(R.drawable.lib_cashier_sdk_pay_do_pay_btn_bg);
        this.triggerViewPaymentStatus = c.DEFAULT;
        onChangeSkin();
    }

    private final void e(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_cashier_sdk_payment_trigger_view, (ViewGroup) this, false);
        View findViewById = inflate.findViewById(R.id.lib_cashier_pay_do_pay_btn_title);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "bodyView.findViewById(R.\u2026ier_pay_do_pay_btn_title)");
        this.titleTv = (TextView) findViewById;
        View findViewById2 = inflate.findViewById(R.id.lib_cashier_pay_do_pay_btn_title_2);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "bodyView.findViewById(R.\u2026r_pay_do_pay_btn_title_2)");
        this.titleTvPrice = (TextView) findViewById2;
        View findViewById3 = inflate.findViewById(R.id.lib_cashier_pay_do_pay_btn_subtitle);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "bodyView.findViewById(R.\u2026_pay_do_pay_btn_subtitle)");
        this.subtitleTv = (TextView) findViewById3;
        View findViewById4 = inflate.findViewById(R.id.lib_cashier_pay_do_pay_btn);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "bodyView.findViewById(R.\u2026b_cashier_pay_do_pay_btn)");
        this.paymentView = findViewById4;
        if (findViewById4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentView");
        }
        findViewById4.setOnClickListener(new a());
        addView(inflate);
        onChangeSkin();
    }

    private final void j(e textPair, String moneyFlag, boolean needSetTag) {
        List split$default;
        b();
        if (needSetTag) {
            setTag(textPair);
        }
        String a2 = textPair.a();
        String b = textPair.b();
        int i2 = 8;
        if (this.titleTv != null) {
            if (!TextUtils.isEmpty(a2) && !TextUtils.isEmpty(moneyFlag)) {
                split$default = StringsKt__StringsKt.split$default((CharSequence) a2, new String[]{moneyFlag}, false, 0, 6, (Object) null);
                if (split$default == null) {
                    split$default = new ArrayList();
                }
                if (split$default.size() >= 2) {
                    TextView textView = this.titleTvPrice;
                    if (textView == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("titleTvPrice");
                    }
                    textView.setVisibility(0);
                    String str = (String) split$default.get(0);
                    String str2 = (String) split$default.get(1);
                    TextView textView2 = this.titleTv;
                    if (textView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("titleTv");
                    }
                    textView2.setText(str);
                    TextView textView3 = this.titleTvPrice;
                    if (textView3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("titleTvPrice");
                    }
                    m0.a(textView3, (byte) 3);
                    TextView textView4 = this.titleTvPrice;
                    if (textView4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("titleTvPrice");
                    }
                    textView4.setText(moneyFlag + str2);
                }
            } else {
                TextView textView5 = this.titleTv;
                if (textView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("titleTv");
                }
                textView5.setText(a2);
                TextView textView6 = this.titleTvPrice;
                if (textView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("titleTvPrice");
                }
                textView6.setVisibility(8);
                TextView textView7 = this.titleTvPrice;
                if (textView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("titleTvPrice");
                }
                textView7.setText("");
            }
        }
        TextView textView8 = this.subtitleTv;
        if (textView8 != null) {
            if (textView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("subtitleTv");
            }
            if (TextUtils.isEmpty(b)) {
                textView8.setText("");
            } else {
                textView8.setText(textPair.c());
                i2 = 0;
            }
            textView8.setVisibility(i2);
        }
    }

    public static /* synthetic */ void l(PaymentTriggerView paymentTriggerView, e eVar, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = "";
        }
        paymentTriggerView.h(eVar, str);
    }

    public final void c() {
        View view = this.paymentView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("paymentView");
        }
        if (view != null) {
            View view2 = this.paymentView;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("paymentView");
            }
            view2.setBackgroundResource(R.drawable.lib_cashier_sdk_pay_do_pay_btn_bg);
        }
    }

    public final void f(@NotNull Function1<? super View, Unit> block) {
        this.onPaymentClickListener = new d(block);
    }

    @JvmOverloads
    public final void h(@NotNull e textPair, @NotNull String moneyFlag) {
        j(textPair, moneyFlag, true);
    }

    public final void k(@NotNull String text) {
        TextView textView = this.titleTv;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("titleTv");
        }
        textView.setText(text);
        b();
    }

    public final void m(@NotNull c cVar) {
        this.triggerViewPaymentStatus = cVar;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        c();
    }

    public final void onPause() {
    }

    public final void onResume() {
        try {
            if (c.IN_PAYMENT == this.triggerViewPaymentStatus) {
                Object tag = getTag();
                if (!(tag instanceof e)) {
                    tag = null;
                }
                e eVar = (e) tag;
                if (eVar != null) {
                    l(this, eVar, null, 2, null);
                    this.triggerViewPaymentStatus = c.DEFAULT;
                }
            }
        } catch (Exception e2) {
            r.b(f3068m, String.valueOf(e2));
        }
    }

    public PaymentTriggerView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
