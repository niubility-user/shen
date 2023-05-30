package com.jd.lib.cashier.sdk.pay.dialog;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.adapter.CreditCardBankProAdapter;
import com.jd.lib.cashier.sdk.pay.bean.creditcard.CreditCard;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class c {
    @Nullable
    private List<? extends CreditCard> a;

    /* loaded from: classes14.dex */
    static final class a implements View.OnClickListener {

        /* renamed from: g  reason: collision with root package name */
        final /* synthetic */ Dialog f3893g;

        /* renamed from: h  reason: collision with root package name */
        final /* synthetic */ Function1 f3894h;

        a(c cVar, Dialog dialog, Function1 function1) {
            this.f3893g = dialog;
            this.f3894h = function1;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Dialog dialog = this.f3893g;
            if (dialog != null) {
                dialog.dismiss();
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCard;", "currentSelectedCreditCard", "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/creditcard/CreditCard;)V", "com/jd/lib/cashier/sdk/pay/dialog/CreditCardBankProDialogHelper$$special$$inlined$apply$lambda$2", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class b extends Lambda implements Function1<CreditCard, Unit> {
        final /* synthetic */ Dialog $dialog$inlined;
        final /* synthetic */ Function1 $onItemClickListener$inlined;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(Dialog dialog, Function1 function1) {
            super(1);
            this.$dialog$inlined = dialog;
            this.$onItemClickListener$inlined = function1;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(CreditCard creditCard) {
            invoke2(creditCard);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull CreditCard creditCard) {
            Dialog dialog = this.$dialog$inlined;
            if (dialog != null) {
                dialog.dismiss();
            }
            Function1 function1 = this.$onItemClickListener$inlined;
            if (function1 != null) {
                Unit unit = (Unit) function1.invoke(creditCard);
            }
        }
    }

    private final void b(View view) {
        if (view != null) {
            ((TextView) view.findViewById(R.id.lib_cashier_credit_bank_dialog_title)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_262626));
            view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        }
    }

    @Nullable
    public final CreditCard a() {
        List<? extends CreditCard> list = this.a;
        Object obj = null;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (((CreditCard) next).selected) {
                    obj = next;
                    break;
                }
            }
            return (CreditCard) obj;
        }
        return null;
    }

    public final void c(@Nullable List<? extends CreditCard> list) {
        this.a = list;
    }

    public final void d(@Nullable CreditCard creditCard) {
        List<? extends CreditCard> list = this.a;
        if (list != null) {
            for (CreditCard creditCard2 : list) {
                creditCard2.selected = Intrinsics.areEqual(creditCard2, creditCard);
            }
        }
    }

    @JvmOverloads
    public final void e(@NotNull FragmentActivity fragmentActivity, @Nullable Function1<? super CreditCard, Unit> function1) {
        if (g0.a(fragmentActivity)) {
            Dialog b2 = com.jd.lib.cashier.sdk.core.utils.j.b(fragmentActivity);
            View inflate = LayoutInflater.from(fragmentActivity).inflate(R.layout.lib_cashier_sdk_dialog_credit_card_bank_list, (ViewGroup) null, false);
            ((ImageView) inflate.findViewById(R.id.img_dialog_credit_card_bank_close)).setOnClickListener(new a(this, b2, function1));
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_credit_card_bank);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
            Intrinsics.checkExpressionValueIsNotNull(recyclerView, "this");
            CreditCardBankProAdapter creditCardBankProAdapter = new CreditCardBankProAdapter(this.a);
            creditCardBankProAdapter.n(new b(b2, function1));
            recyclerView.setAdapter(creditCardBankProAdapter);
            b(inflate);
            com.jd.lib.cashier.sdk.core.utils.j.a(b2, inflate, 0.6f);
            if (b2 != null) {
                b2.show();
            }
        }
    }
}
