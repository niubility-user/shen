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
import com.jd.lib.cashier.sdk.h.g.x;
import com.jd.lib.cashier.sdk.pay.adapter.WholePaymentAdapter;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class j {
    public static final j a = new j();

    /* loaded from: classes14.dex */
    public static final class a implements View.OnClickListener {

        /* renamed from: g */
        final /* synthetic */ FragmentActivity f3929g;

        /* renamed from: h */
        final /* synthetic */ Dialog f3930h;

        /* renamed from: i */
        final /* synthetic */ List f3931i;

        /* renamed from: j */
        final /* synthetic */ Function2 f3932j;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/h/h;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/h/h;)V", "com/jd/lib/cashier/sdk/pay/dialog/WholePaymentDialogUtils$showDialog$contentView$1$1$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* renamed from: com.jd.lib.cashier.sdk.pay.dialog.j$a$a */
        /* loaded from: classes14.dex */
        static final class C0140a extends Lambda implements Function1<com.jd.lib.cashier.sdk.h.h.h, Unit> {
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0140a() {
                super(1);
                a.this = r1;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.h.h.h hVar) {
                invoke2(hVar);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke */
            public final void invoke2(@NotNull com.jd.lib.cashier.sdk.h.h.h hVar) {
                com.jd.lib.cashier.sdk.h.e.a.d().J(a.this.f3929g);
            }
        }

        a(FragmentActivity fragmentActivity, Dialog dialog, List list, Function2 function2) {
            this.f3929g = fragmentActivity;
            this.f3930h = dialog;
            this.f3931i = list;
            this.f3932j = function2;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            com.jd.lib.cashier.sdk.h.h.c.b(this.f3929g, new C0140a());
            this.f3930h.dismiss();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\b\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n\u00a2\u0006\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/g/x;", "clickChannelTemplate", "", "position", "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/g/x;I)V", "com/jd/lib/cashier/sdk/pay/dialog/WholePaymentDialogUtils$$special$$inlined$apply$lambda$2", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class b extends Lambda implements Function2<x, Integer, Unit> {
        final /* synthetic */ FragmentActivity $activity$inlined;
        final /* synthetic */ Dialog $dialog$inlined;
        final /* synthetic */ Function2 $onItemClickListener$inlined;
        final /* synthetic */ List $payChannelTemplateList$inlined;
        final /* synthetic */ WholePaymentAdapter $this_apply;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(WholePaymentAdapter wholePaymentAdapter, FragmentActivity fragmentActivity, Dialog dialog, List list, Function2 function2) {
            super(2);
            this.$this_apply = wholePaymentAdapter;
            this.$activity$inlined = fragmentActivity;
            this.$dialog$inlined = dialog;
            this.$payChannelTemplateList$inlined = list;
            this.$onItemClickListener$inlined = function2;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(x xVar, Integer num) {
            invoke(xVar, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(@NotNull x xVar, int i2) {
            com.jd.lib.cashier.sdk.h.e.a.d().G(this.$activity$inlined, xVar.a().code, com.jd.lib.cashier.sdk.h.h.e.a.f(xVar.a()), String.valueOf(i2 + 1), xVar.a().defaultSelected);
            this.$onItemClickListener$inlined.invoke(xVar, this.$this_apply);
            this.$dialog$inlined.dismiss();
        }
    }

    private j() {
    }

    private final void a(View view) {
        if (view != null) {
            ((TextView) view.findViewById(R.id.lib_cashier_all_pay_channel_dialog_title)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
            view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        }
    }

    public final void b(@NotNull FragmentActivity fragmentActivity, @NotNull List<? extends x> list, @NotNull Function2<? super x, ? super WholePaymentAdapter, Unit> function2) {
        Dialog b2;
        if (!g0.a(fragmentActivity) || (b2 = com.jd.lib.cashier.sdk.core.utils.j.b(fragmentActivity)) == null) {
            return;
        }
        View inflate = LayoutInflater.from(fragmentActivity).inflate(R.layout.lib_cashier_sdk_dialog_cashier_whole_payment_list, (ViewGroup) null, false);
        ((ImageView) inflate.findViewById(R.id.img_whole_payment_dialog_close)).setOnClickListener(new a(fragmentActivity, b2, list, function2));
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_whole_payment_dialog);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 1, false));
        WholePaymentAdapter wholePaymentAdapter = new WholePaymentAdapter(fragmentActivity, list);
        wholePaymentAdapter.n(new b(wholePaymentAdapter, fragmentActivity, b2, list, function2));
        recyclerView.setAdapter(wholePaymentAdapter);
        a(inflate);
        com.jd.lib.cashier.sdk.core.utils.j.a(b2, inflate, 0.6f);
        b2.show();
    }
}
