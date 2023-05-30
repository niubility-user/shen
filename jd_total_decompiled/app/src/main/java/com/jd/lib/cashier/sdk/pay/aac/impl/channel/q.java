package com.jd.lib.cashier.sdk.pay.aac.impl.channel;

import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.bean.syncevent.ClickGWJIconItemEvent;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes14.dex */
public final class q implements com.jd.lib.cashier.sdk.core.aac.d, com.jd.lib.cashier.sdk.d.d.a {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickGWJIconItemEvent;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/bean/syncevent/ClickGWJIconItemEvent;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class a extends Lambda implements Function1<ClickGWJIconItemEvent, Unit> {
        final /* synthetic */ FragmentActivity $activity;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(FragmentActivity fragmentActivity) {
            super(1);
            this.$activity = fragmentActivity;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ClickGWJIconItemEvent clickGWJIconItemEvent) {
            invoke2(clickGWJIconItemEvent);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ClickGWJIconItemEvent clickGWJIconItemEvent) {
            if (g0.a(this.$activity)) {
                com.jd.lib.cashier.sdk.pay.dialog.d.a.c(this.$activity, clickGWJIconItemEvent.getGouWuJinModelVO());
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.d
    public void f(@NotNull FragmentActivity fragmentActivity) {
        com.jd.lib.cashier.sdk.b.i.e.e("CLICK_PAYMENT_ITEM_GWJ_ICON", null, new a(fragmentActivity), 2, null);
    }
}
