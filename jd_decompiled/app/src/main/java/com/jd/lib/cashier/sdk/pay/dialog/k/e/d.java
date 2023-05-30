package com.jd.lib.cashier.sdk.pay.dialog.k.e;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.model.ExitRetainOptionEntity;
import com.jd.lib.cashier.sdk.core.model.PopBusinessMap;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.pay.adapter.ExitDefaultStayAdapter;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class d extends com.jd.lib.cashier.sdk.pay.dialog.k.e.a {
    private final String d;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/model/ExitRetainOptionEntity;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/core/model/ExitRetainOptionEntity;)V", "com/jd/lib/cashier/sdk/pay/dialog/exitdialog/basic/ExitDefaultStayDialog$$special$$inlined$apply$lambda$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    static final class a extends Lambda implements Function1<ExitRetainOptionEntity, Unit> {
        final /* synthetic */ PopBusinessMap $businessMap$inlined;
        final /* synthetic */ CashierCommonPopConfig $commonPopConfig$inlined;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(CashierCommonPopConfig cashierCommonPopConfig, PopBusinessMap popBusinessMap) {
            super(1);
            this.$commonPopConfig$inlined = cashierCommonPopConfig;
            this.$businessMap$inlined = popBusinessMap;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ExitRetainOptionEntity exitRetainOptionEntity) {
            invoke2(exitRetainOptionEntity);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull ExitRetainOptionEntity exitRetainOptionEntity) {
            d.super.g(exitRetainOptionEntity);
        }
    }

    public d(@NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.d = "{$cancelTime}";
    }

    @Override // com.jd.lib.cashier.sdk.pay.dialog.k.e.a
    @Nullable
    public View b(@NotNull CashierCommonPopConfig cashierCommonPopConfig) {
        List arrayList;
        boolean contains$default;
        try {
            PopBusinessMap popBusinessMap = cashierCommonPopConfig.businessMap;
            View inflate = LayoutInflater.from(c()).inflate(R.layout.lib_cashier_sdk_dialog_exit_default_stay, (ViewGroup) null, false);
            TextView textView = (TextView) inflate.findViewById(R.id.lib_cashier_dialog_exit_stay_subtitle);
            if (textView != null) {
                textView.setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
                if (!TextUtils.isEmpty(cashierCommonPopConfig.message)) {
                    String str = cashierCommonPopConfig.message;
                    Intrinsics.checkExpressionValueIsNotNull(str, "commonPopConfig?.message");
                    contains$default = StringsKt__StringsKt.contains$default((CharSequence) str, (CharSequence) this.d, false, 2, (Object) null);
                    if (contains$default) {
                        textView.setText(cashierCommonPopConfig.replacedMessage);
                    }
                }
                textView.setText(cashierCommonPopConfig.message);
            }
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.lib_cashier_dialog_exit_stay_recycler);
            recyclerView.setLayoutManager(new LinearLayoutManager(c(), 1, false));
            if (popBusinessMap == null || (arrayList = popBusinessMap.retainBtnList) == null) {
                arrayList = new ArrayList();
            }
            recyclerView.setAdapter(new ExitDefaultStayAdapter(arrayList, new a(cashierCommonPopConfig, popBusinessMap)));
            inflate.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            return inflate;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
