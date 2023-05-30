package com.jd.lib.cashier.sdk.core.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.cashier.app.jdlibcutter.protocol.utils.DpiUtil;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.cashier.sdk.pay.adapter.CashierAPayPlanAdapter;
import com.jd.lib.cashier.sdk.pay.decoration.CashierSpacesItemDecoration;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.jdsdk.constant.JshopConst;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010I\u001a\u0004\u0018\u00010H\u00a2\u0006\u0004\bJ\u0010KB#\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010I\u001a\u0004\u0018\u00010H\u0012\u0006\u0010L\u001a\u00020\u000f\u00a2\u0006\u0004\bJ\u0010MJ\u001f\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u001f\u0010\n\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\n\u0010\tJ\u0017\u0010\r\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000bH\u0002\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u000fH\u0002\u00a2\u0006\u0004\b\u0011\u0010\u0012J7\u0010\u0019\u001a\u00020\u0007\"\b\b\u0000\u0010\u0014*\u00020\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\u0006\u0010\f\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\r\u0010\u001b\u001a\u00020\u0007\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001d\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\u001d\u0010\u001cR\"\u0010%\u001a\u00020\u001e8\u0006@\u0006X\u0086.\u00a2\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010-\u001a\u0004\u0018\u00010&8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R$\u00104\u001a\u0004\u0018\u00010.8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b+\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R(\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00130\u00158\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R$\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\r\u0010<\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R$\u0010G\u001a\u0004\u0018\u00010A8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0019\u0010B\u001a\u0004\bC\u0010D\"\u0004\bE\u0010F\u00a8\u0006N"}, d2 = {"Lcom/jd/lib/cashier/sdk/core/ui/widget/CashierAPayPlanView;", "Landroid/widget/FrameLayout;", "Lcom/jd/lib/cashier/sdk/core/aac/e;", "Landroid/content/Context;", AnnoConst.Constructor_Context, "", "space", "", "f", "(Landroid/content/Context;F)V", com.jingdong.app.mall.e.a, "", "viewNeedPadding", "k", "(Z)V", "", "position", "b", "(I)V", "Lcom/jd/lib/cashier/sdk/core/ui/widget/IPlanItemViewEntity;", "W", "", "data", "", "mianxiHighlight", JshopConst.JSHOP_PROMOTIO_H, "(Ljava/util/List;Ljava/lang/String;Z)V", com.jingdong.jdsdk.a.a.a, "()V", "onChangeSkin", "Landroid/view/View;", "g", "Landroid/view/View;", "getPlanRootView", "()Landroid/view/View;", "setPlanRootView", "(Landroid/view/View;)V", "planRootView", "Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;", NotifyType.LIGHTS, "Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;", "c", "()Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;", "j", "(Lcom/jd/lib/cashier/sdk/core/ui/widget/OnPlanViewClickListener;)V", "onItemClickListener", "Lcom/jd/lib/cashier/sdk/pay/adapter/CashierAPayPlanAdapter;", "Lcom/jd/lib/cashier/sdk/pay/adapter/CashierAPayPlanAdapter;", "getAdapter", "()Lcom/jd/lib/cashier/sdk/pay/adapter/CashierAPayPlanAdapter;", "setAdapter", "(Lcom/jd/lib/cashier/sdk/pay/adapter/CashierAPayPlanAdapter;)V", "adapter", "i", "Ljava/util/List;", "getDataList", "()Ljava/util/List;", "setDataList", "(Ljava/util/List;)V", "dataList", "Ljava/lang/String;", "getMianxiHighlight", "()Ljava/lang/String;", "setMianxiHighlight", "(Ljava/lang/String;)V", "Landroidx/recyclerview/widget/RecyclerView;", "Landroidx/recyclerview/widget/RecyclerView;", "getPlanRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setPlanRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "planRecyclerView", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class CashierAPayPlanView extends FrameLayout implements com.jd.lib.cashier.sdk.core.aac.e {
    @NotNull

    /* renamed from: g  reason: collision with root package name and from kotlin metadata */
    public View planRootView;
    @Nullable

    /* renamed from: h  reason: collision with root package name and from kotlin metadata */
    private RecyclerView planRecyclerView;
    @NotNull

    /* renamed from: i  reason: collision with root package name and from kotlin metadata */
    private List<? extends IPlanItemViewEntity> dataList;
    @Nullable

    /* renamed from: j  reason: collision with root package name and from kotlin metadata */
    private CashierAPayPlanAdapter adapter;
    @Nullable

    /* renamed from: k  reason: collision with root package name and from kotlin metadata */
    private String mianxiHighlight;
    @Nullable

    /* renamed from: l  reason: collision with root package name and from kotlin metadata */
    private OnPlanViewClickListener onItemClickListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes14.dex */
    public static final class a implements OnPlanViewClickListener {
        a() {
        }

        @Override // com.jd.lib.cashier.sdk.core.ui.widget.OnPlanViewClickListener
        public final void onClick(IPlanItemViewEntity iPlanItemViewEntity, IPlanItemViewEntity iPlanItemViewEntity2) {
            OnPlanViewClickListener onItemClickListener = CashierAPayPlanView.this.getOnItemClickListener();
            if (onItemClickListener != null) {
                onItemClickListener.onClick(iPlanItemViewEntity, iPlanItemViewEntity2);
            }
        }
    }

    static {
        Intrinsics.checkExpressionValueIsNotNull(CashierAPayPlanView.class.getSimpleName(), "CashierAPayPlanView::class.java.simpleName");
    }

    public CashierAPayPlanView(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private final void b(int position) {
        RecyclerView recyclerView;
        if (position >= 0) {
            List<? extends IPlanItemViewEntity> list = this.dataList;
            if (position < (list != null ? Integer.valueOf(list.size()) : null).intValue() && (recyclerView = this.planRecyclerView) != null) {
                recyclerView.smoothScrollToPosition(position);
            }
        }
    }

    private final void e(Context context, float space) {
        if (this.adapter == null) {
            this.adapter = new CashierAPayPlanAdapter(context, this.dataList);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, 0, false);
        RecyclerView recyclerView = this.planRecyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(linearLayoutManager);
        }
        RecyclerView recyclerView2 = this.planRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.addItemDecoration(new CashierSpacesItemDecoration(DpiUtil.dip2px(context, space)));
        }
        RecyclerView recyclerView3 = this.planRecyclerView;
        if (recyclerView3 != null) {
            recyclerView3.setAdapter(this.adapter);
        }
    }

    private final void f(Context context, float space) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.lib_cashier_sdk_a_pay_plan_container, (ViewGroup) this, true);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(cont\u2026an_container, this, true)");
        this.planRootView = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("planRootView");
        }
        this.planRecyclerView = (RecyclerView) inflate.findViewById(R.id.lib_cashier_a_pay_plan_recyclerview);
        e(context, space);
    }

    private final void k(boolean viewNeedPadding) {
        List<? extends IPlanItemViewEntity> list = this.dataList;
        if (list == null || list.isEmpty()) {
            return;
        }
        CashierAPayPlanAdapter cashierAPayPlanAdapter = this.adapter;
        if (cashierAPayPlanAdapter != null) {
            cashierAPayPlanAdapter.n(this.mianxiHighlight);
        }
        CashierAPayPlanAdapter cashierAPayPlanAdapter2 = this.adapter;
        if (cashierAPayPlanAdapter2 != null) {
            cashierAPayPlanAdapter2.o(viewNeedPadding);
        }
        CashierAPayPlanAdapter cashierAPayPlanAdapter3 = this.adapter;
        if (cashierAPayPlanAdapter3 != null) {
            cashierAPayPlanAdapter3.q(this.dataList);
        }
        CashierAPayPlanAdapter cashierAPayPlanAdapter4 = this.adapter;
        if (cashierAPayPlanAdapter4 != null) {
            cashierAPayPlanAdapter4.p(new a());
        }
        CashierAPayPlanAdapter cashierAPayPlanAdapter5 = this.adapter;
        if (cashierAPayPlanAdapter5 != null) {
            cashierAPayPlanAdapter5.notifyDataSetChanged();
        }
    }

    public final void a() {
        List<? extends IPlanItemViewEntity> list = this.dataList;
        int i2 = 0;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.dataList.size();
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            } else if (this.dataList.get(i2).isChecked()) {
                break;
            } else {
                i2++;
            }
        }
        b(i2);
    }

    @Nullable
    /* renamed from: c  reason: from getter */
    public final OnPlanViewClickListener getOnItemClickListener() {
        return this.onItemClickListener;
    }

    public final <W extends IPlanItemViewEntity> void h(@NotNull List<? extends W> data, @Nullable String mianxiHighlight, boolean viewNeedPadding) {
        this.dataList = data;
        this.mianxiHighlight = mianxiHighlight;
        k(viewNeedPadding);
        a();
    }

    public final void j(@Nullable OnPlanViewClickListener onPlanViewClickListener) {
        this.onItemClickListener = onPlanViewClickListener;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.e
    public void onChangeSkin() {
        setBackgroundColor(JDDarkUtil.getDarkColor(JDDarkUtil.COLOR_FFFFFFF));
    }

    public CashierAPayPlanView(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.dataList = new ArrayList();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.APayPlanViewSdk);
        Intrinsics.checkExpressionValueIsNotNull(obtainStyledAttributes, "context.obtainStyledAttr\u2026tyleable.APayPlanViewSdk)");
        float f2 = obtainStyledAttributes.getFloat(R.styleable.APayPlanViewSdk_cashierSpace, 8.0f);
        obtainStyledAttributes.recycle();
        f(context, f2);
    }
}
