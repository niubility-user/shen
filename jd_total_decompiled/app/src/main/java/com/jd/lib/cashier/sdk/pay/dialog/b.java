package com.jd.lib.cashier.sdk.pay.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
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
import com.jd.lib.cashier.sdk.core.utils.o0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.h.h.m;
import com.jd.lib.cashier.sdk.pay.adapter.AllCouponAdapter;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: classes14.dex */
public final class b {

    /* renamed from: h  reason: collision with root package name */
    private static final String f3884h = "b";
    @Nullable
    private Function1<? super com.jd.lib.cashier.sdk.pay.dialog.e, Unit> a;
    @Nullable
    private Function0<Unit> b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.pay.dialog.e f3885c;
    private final List<com.jd.lib.cashier.sdk.pay.dialog.e> d = new ArrayList();

    /* renamed from: e  reason: collision with root package name */
    private AllCouponAdapter f3886e;

    /* renamed from: f  reason: collision with root package name */
    private Dialog f3887f;
    @NotNull

    /* renamed from: g  reason: collision with root package name */
    private final FragmentActivity f3888g;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/dialog/e;", "couponItemEntity", "", "invoke", "(Lcom/jd/lib/cashier/sdk/pay/dialog/e;)V", "com/jd/lib/cashier/sdk/pay/dialog/CouponDialogHelper$generateContentView$4$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class a extends Lambda implements Function1<com.jd.lib.cashier.sdk.pay.dialog.e, Unit> {

        /* JADX INFO: Access modifiers changed from: package-private */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/h/h;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/h/h;)V", "com/jd/lib/cashier/sdk/pay/dialog/CouponDialogHelper$generateContentView$4$1$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* renamed from: com.jd.lib.cashier.sdk.pay.dialog.b$a$a  reason: collision with other inner class name */
        /* loaded from: classes14.dex */
        public static final class C0136a extends Lambda implements Function1<com.jd.lib.cashier.sdk.h.h.h, Unit> {
            final /* synthetic */ com.jd.lib.cashier.sdk.pay.dialog.e $couponItemEntity;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C0136a(com.jd.lib.cashier.sdk.pay.dialog.e eVar) {
                super(1);
                this.$couponItemEntity = eVar;
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.h.h.h hVar) {
                invoke2(hVar);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull com.jd.lib.cashier.sdk.h.h.h hVar) {
                com.jd.lib.cashier.sdk.h.e.a.d().u(b.this.g(), hVar.c(), hVar.b(), TextUtils.equals(hVar.e(), this.$couponItemEntity.getPayMarketingUUID()), this.$couponItemEntity.getPayMarketingUUID());
            }
        }

        a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.pay.dialog.e eVar) {
            invoke2(eVar);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull com.jd.lib.cashier.sdk.pay.dialog.e eVar) {
            com.jd.lib.cashier.sdk.h.h.c.b(b.this.g(), new C0136a(eVar));
            r.b(b.f3884h, "onItemClickListener couponItemEntity = " + eVar);
            boolean checked = eVar.getChecked();
            Iterator it = b.this.d.iterator();
            while (it.hasNext()) {
                ((com.jd.lib.cashier.sdk.pay.dialog.e) it.next()).setChecked(false);
            }
            eVar.setChecked(!checked);
            AllCouponAdapter allCouponAdapter = b.this.f3886e;
            if (allCouponAdapter != null) {
                allCouponAdapter.notifyDataSetChanged();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jd.lib.cashier.sdk.pay.dialog.b$b  reason: collision with other inner class name */
    /* loaded from: classes14.dex */
    public static final class ViewOnClickListenerC0137b implements View.OnClickListener {
        ViewOnClickListenerC0137b() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Dialog dialog;
            Function0<Unit> k2;
            Iterator it = b.this.d.iterator();
            boolean z = false;
            boolean z2 = false;
            while (it.hasNext()) {
                if (((com.jd.lib.cashier.sdk.pay.dialog.e) it.next()).getViewType() == 1) {
                    z2 = true;
                }
            }
            for (com.jd.lib.cashier.sdk.pay.dialog.e eVar : b.this.d) {
                if (eVar.getChecked()) {
                    Function1<com.jd.lib.cashier.sdk.pay.dialog.e, Unit> l2 = b.this.l();
                    if (l2 != null) {
                        l2.invoke(eVar);
                    }
                    z = true;
                }
            }
            if (!z && z2 && (k2 = b.this.k()) != null) {
                k2.invoke();
            }
            Dialog dialog2 = b.this.f3887f;
            if (dialog2 != null && dialog2.isShowing() && (dialog = b.this.f3887f) != null) {
                dialog.dismiss();
            }
            com.jd.lib.cashier.sdk.h.e.a.d().x(b.this.g());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Dialog dialog;
            Dialog dialog2 = b.this.f3887f;
            if (dialog2 == null || !dialog2.isShowing() || (dialog = b.this.f3887f) == null) {
                return;
            }
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class d implements View.OnClickListener {
        d() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            Dialog dialog;
            Dialog dialog2 = b.this.f3887f;
            if (dialog2 != null && dialog2.isShowing() && (dialog = b.this.f3887f) != null) {
                dialog.dismiss();
            }
            Function0<Unit> k2 = b.this.k();
            if (k2 != null) {
                k2.invoke();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class e implements DialogInterface.OnDismissListener {

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lcom/jd/lib/cashier/sdk/h/h/h;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Lcom/jd/lib/cashier/sdk/h/h/h;)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
        /* loaded from: classes14.dex */
        static final class a extends Lambda implements Function1<com.jd.lib.cashier.sdk.h.h.h, Unit> {
            a() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(com.jd.lib.cashier.sdk.h.h.h hVar) {
                invoke2(hVar);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull com.jd.lib.cashier.sdk.h.h.h hVar) {
                com.jd.lib.cashier.sdk.h.e.a.d().w(b.this.g(), hVar.c(), hVar.b());
            }
        }

        e() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public final void onDismiss(DialogInterface dialogInterface) {
            com.jd.lib.cashier.sdk.h.h.c.b(b.this.g(), new a());
        }
    }

    public b(@NotNull FragmentActivity fragmentActivity) {
        this.f3888g = fragmentActivity;
    }

    private final View f() {
        View contentView = LayoutInflater.from(this.f3888g).inflate(R.layout.lib_cashier_sdk_dialog_cashier_pay_coupon_list, (ViewGroup) null, false);
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_pay_coupon_dialog);
        contentView.findViewById(R.id.lib_cashier_multi_coupon_confirm_btn).setOnClickListener(new ViewOnClickListenerC0137b());
        ((ImageView) contentView.findViewById(R.id.img_pay_coupon_dialog_close)).setOnClickListener(new c());
        ((TextView) contentView.findViewById(R.id.tv_pay_coupon_dialog_cannot_use)).setOnClickListener(new d());
        AllCouponAdapter allCouponAdapter = new AllCouponAdapter(this.f3888g, this.d);
        allCouponAdapter.m(new a());
        this.f3886e = allCouponAdapter;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.f3888g, 1, false));
        recyclerView.setAdapter(this.f3886e);
        Intrinsics.checkExpressionValueIsNotNull(contentView, "contentView");
        return contentView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void t(b bVar, List list, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = new ArrayList();
        }
        bVar.s(list, function1);
    }

    public final void e() {
        Iterator<T> it = this.d.iterator();
        while (it.hasNext()) {
            ((com.jd.lib.cashier.sdk.pay.dialog.e) it.next()).setChecked(false);
        }
        AllCouponAdapter allCouponAdapter = this.f3886e;
        if (allCouponAdapter != null) {
            allCouponAdapter.notifyDataSetChanged();
        }
    }

    @NotNull
    public final FragmentActivity g() {
        return this.f3888g;
    }

    @Nullable
    public final CouponEntity h(@Nullable CouponEntity couponEntity) {
        boolean z;
        Object obj = null;
        if (couponEntity == null) {
            List<com.jd.lib.cashier.sdk.pay.dialog.e> list = this.d;
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : list) {
                if (obj2 instanceof CouponEntity) {
                    arrayList.add(obj2);
                }
            }
            boolean z2 = true;
            if (!arrayList.isEmpty()) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (((CouponEntity) it.next()).getCanUse()) {
                        z = true;
                        break;
                    }
                }
            }
            z = false;
            if (z) {
                return com.jd.lib.cashier.sdk.b.c.c.a();
            }
            List<com.jd.lib.cashier.sdk.pay.dialog.e> list2 = this.d;
            ArrayList arrayList2 = new ArrayList();
            for (Object obj3 : list2) {
                if (obj3 instanceof CouponEntity) {
                    arrayList2.add(obj3);
                }
            }
            if (!arrayList2.isEmpty()) {
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    if ((!((CouponEntity) it2.next()).getCanUse()) != false) {
                        break;
                    }
                }
            }
            z2 = false;
            if (z2) {
                return com.jd.lib.cashier.sdk.b.c.c.b();
            }
            return null;
        }
        List<com.jd.lib.cashier.sdk.pay.dialog.e> list3 = this.d;
        ArrayList arrayList3 = new ArrayList();
        for (Object obj4 : list3) {
            if (obj4 instanceof CouponEntity) {
                arrayList3.add(obj4);
            }
        }
        Iterator it3 = arrayList3.iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            Object next = it3.next();
            if (Intrinsics.areEqual((CouponEntity) next, couponEntity)) {
                obj = next;
                break;
            }
        }
        CouponEntity couponEntity2 = (CouponEntity) obj;
        if (couponEntity2 != null) {
            couponEntity = couponEntity2;
        }
        r.b(f3884h, "\u8c03\u8bd5 updateAndGetCurrentSelectedCoupon foundCouponEntity = " + couponEntity);
        return couponEntity;
    }

    @Nullable
    public final com.jd.lib.cashier.sdk.pay.dialog.e i() {
        return this.f3885c;
    }

    @NotNull
    public final List<com.jd.lib.cashier.sdk.pay.dialog.e> j(@Nullable List<? extends com.jd.lib.cashier.sdk.pay.dialog.e> list, @Nullable List<? extends com.jd.lib.cashier.sdk.pay.dialog.e> list2, @Nullable String str) {
        ArrayList arrayList = new ArrayList();
        if (list != null && (!list.isEmpty()) != false) {
            if (!TextUtils.isEmpty(str)) {
                for (com.jd.lib.cashier.sdk.pay.dialog.e eVar : list) {
                    eVar.setChecked(Intrinsics.areEqual(eVar.getPayMarketingUUID(), str));
                }
            } else {
                list.get(0).setChecked(true);
            }
            arrayList.addAll(list);
        }
        if (list2 != null && (!list2.isEmpty()) != false) {
            arrayList.addAll(list2);
        }
        return arrayList;
    }

    @Nullable
    public final Function0<Unit> k() {
        return this.b;
    }

    @Nullable
    public final Function1<com.jd.lib.cashier.sdk.pay.dialog.e, Unit> l() {
        return this.a;
    }

    public final void m(@NotNull View view) {
        View findViewById = view.findViewById(R.id.lib_cashier_sdk_coupon_confirm_btn_root);
        ((TextView) view.findViewById(R.id.lib_cashier_pay_coupon_dialog_title)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_1A1A1A));
        ((TextView) view.findViewById(R.id.tv_pay_coupon_dialog_cannot_use)).setTextColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_808080));
        view.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
        findViewById.setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
    }

    public final void n(@Nullable com.jd.lib.cashier.sdk.pay.dialog.e eVar) {
        this.f3885c = eVar;
    }

    public final void o(@Nullable Function0<Unit> function0) {
        this.b = function0;
    }

    public final void p(@Nullable List<CouponEntity> list, @Nullable List<CouponEntity> list2) {
        int collectionSizeOrDefault;
        String joinToString$default;
        this.d.clear();
        if (list != null && (!list.isEmpty()) != false) {
            this.d.addAll(list);
        }
        if (list2 != null && (!list2.isEmpty()) != false) {
            this.d.addAll(list2);
        }
        String str = f3884h;
        StringBuilder sb = new StringBuilder();
        sb.append("\u8c03\u8bd5 setupCouponSourceList couponSourceList = ");
        List<com.jd.lib.cashier.sdk.pay.dialog.e> list3 = this.d;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list3, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        Iterator<T> it = list3.iterator();
        while (it.hasNext()) {
            arrayList.add(((com.jd.lib.cashier.sdk.pay.dialog.e) it.next()).getPayMarketingUUID());
        }
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null);
        sb.append(joinToString$default);
        r.b(str, sb.toString());
    }

    public final void q(@Nullable CouponEntity couponEntity, @NotNull FragmentActivity fragmentActivity) {
        String str;
        String str2;
        str = "";
        if (couponEntity == null || Intrinsics.areEqual(couponEntity, com.jd.lib.cashier.sdk.b.c.c.a()) || Intrinsics.areEqual(couponEntity, com.jd.lib.cashier.sdk.b.c.c.b())) {
            str2 = "";
        } else {
            String couponId = couponEntity.getCouponId();
            if (couponId == null) {
                couponId = "";
            }
            String activityId = couponEntity.getActivityId();
            str2 = activityId != null ? activityId : "";
            str = couponId;
        }
        m.q(fragmentActivity, str, str2);
    }

    public final void r(@Nullable CouponEntity couponEntity) {
        for (com.jd.lib.cashier.sdk.pay.dialog.e eVar : this.d) {
            if ((eVar instanceof CouponEntity) && ((CouponEntity) eVar).getCanUse()) {
                eVar.setChecked(Intrinsics.areEqual(eVar, couponEntity));
            } else {
                eVar.setChecked(false);
            }
        }
    }

    public final void s(@NotNull List<? extends com.jd.lib.cashier.sdk.pay.dialog.e> list, @Nullable Function1<? super com.jd.lib.cashier.sdk.pay.dialog.e, Unit> function1) {
        Dialog dialog;
        Dialog dialog2;
        Dialog dialog3;
        StringBuilder sb = new StringBuilder();
        String str = f3884h;
        sb.append(str);
        sb.append("dialogCoupon");
        if (!o0.a(sb.toString()) && g0.a(this.f3888g)) {
            this.a = function1;
            View f2 = f();
            m(f2);
            Dialog b = com.jd.lib.cashier.sdk.core.utils.j.b(this.f3888g);
            this.f3887f = b;
            if (b != null) {
                if (b != null) {
                    b.setCanceledOnTouchOutside(true);
                }
                com.jd.lib.cashier.sdk.core.utils.j.a(this.f3887f, f2, 0.6f);
                if ((!list.isEmpty()) != false) {
                    r.b(str, "\u5c55\u793a\u8c03 couponList NotEmpty()");
                    this.d.clear();
                    this.d.addAll(list);
                    AllCouponAdapter allCouponAdapter = this.f3886e;
                    if (allCouponAdapter != null) {
                        allCouponAdapter.notifyDataSetChanged();
                    }
                    Dialog dialog4 = this.f3887f;
                    if (dialog4 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!dialog4.isShowing() && (dialog3 = this.f3887f) != null) {
                        dialog3.show();
                    }
                } else if ((!this.d.isEmpty()) != false) {
                    r.b(str, "\u5c55\u793a\u8c03 couponSourceList NotEmpty()");
                    r.b(str, "\u5c55\u793a\u8c03 sourceListSize = " + this.d.size());
                    AllCouponAdapter allCouponAdapter2 = this.f3886e;
                    if (allCouponAdapter2 != null) {
                        allCouponAdapter2.notifyDataSetChanged();
                    }
                    Dialog dialog5 = this.f3887f;
                    if (dialog5 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (!dialog5.isShowing() && (dialog = this.f3887f) != null) {
                        dialog.show();
                    }
                }
                Dialog dialog6 = this.f3887f;
                if (dialog6 == null || true != dialog6.isShowing() || (dialog2 = this.f3887f) == null) {
                    return;
                }
                dialog2.setOnDismissListener(new e());
            }
        }
    }
}
