package com.jd.lib.cashier.sdk.pay.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.model.ExitRetainOptionEntity;
import com.jingdong.common.entity.personal.PersonalConstants;
import com.meizu.cloud.pushsdk.notification.model.NotifyType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\u0012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00130\u0016\u00a2\u0006\u0004\b\u001e\u0010\u001fJ#\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\r\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000bH\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015R\"\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00130\u00168\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u00038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001b\u00a8\u0006 "}, d2 = {"Lcom/jd/lib/cashier/sdk/pay/adapter/ExitDefaultStayAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/jd/lib/cashier/sdk/pay/adapter/DefaultExitGridViewHolder;", "", "Lcom/jd/lib/cashier/sdk/core/model/ExitRetainOptionEntity;", "retainBtnList", "Lcom/jd/lib/cashier/sdk/pay/dialog/k/e/e;", NotifyType.LIGHTS, "(Ljava/util/List;)Ljava/util/List;", "Landroid/view/ViewGroup;", "parent", "", "viewType", PersonalConstants.ICON_STYLE_N, "(Landroid/view/ViewGroup;I)Lcom/jd/lib/cashier/sdk/pay/adapter/DefaultExitGridViewHolder;", "getItemCount", "()I", "holder", "position", "", "m", "(Lcom/jd/lib/cashier/sdk/pay/adapter/DefaultExitGridViewHolder;I)V", "Lkotlin/Function1;", "b", "Lkotlin/jvm/functions/Function1;", "onClickItemListener", com.jingdong.jdsdk.a.a.a, "Ljava/util/List;", "dataList", "rawDatList", "<init>", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "cashier_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes14.dex */
public final class ExitDefaultStayAdapter extends RecyclerView.Adapter<DefaultExitGridViewHolder> {

    /* renamed from: a  reason: from kotlin metadata */
    private List<com.jd.lib.cashier.sdk.pay.dialog.k.e.e> dataList;

    /* renamed from: b  reason: from kotlin metadata */
    private final Function1<ExitRetainOptionEntity, Unit> onClickItemListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "columnIndex", "", "invoke", "(I)V", "<anonymous>"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes14.dex */
    public static final class a extends Lambda implements Function1<Integer, Unit> {
        final /* synthetic */ int $position;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(int i2) {
            super(1);
            this.$position = i2;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
            invoke(num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i2) {
            int collectionSizeOrDefault;
            int collectionSizeOrDefault2;
            List list = ExitDefaultStayAdapter.this.dataList;
            collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
            ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ArrayList<ExitRetainOptionEntity> a = ((com.jd.lib.cashier.sdk.pay.dialog.k.e.e) it.next()).a();
                collectionSizeOrDefault2 = CollectionsKt__IterablesKt.collectionSizeOrDefault(a, 10);
                ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault2);
                Iterator<T> it2 = a.iterator();
                while (it2.hasNext()) {
                    ((ExitRetainOptionEntity) it2.next()).selected = false;
                    arrayList2.add(Unit.INSTANCE);
                }
                arrayList.add(arrayList2);
            }
            ExitRetainOptionEntity exitRetainOptionEntity = ((com.jd.lib.cashier.sdk.pay.dialog.k.e.e) ExitDefaultStayAdapter.this.dataList.get(this.$position)).a().get(i2);
            Intrinsics.checkExpressionValueIsNotNull(exitRetainOptionEntity, "dataList[position].columns[columnIndex]");
            ExitRetainOptionEntity exitRetainOptionEntity2 = exitRetainOptionEntity;
            exitRetainOptionEntity2.selected = true;
            ExitDefaultStayAdapter.this.notifyDataSetChanged();
            ExitDefaultStayAdapter.this.onClickItemListener.invoke(exitRetainOptionEntity2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ExitDefaultStayAdapter(@NotNull List<? extends ExitRetainOptionEntity> list, @NotNull Function1<? super ExitRetainOptionEntity, Unit> function1) {
        this.onClickItemListener = function1;
        this.dataList = l(list);
    }

    /* JADX WARN: Type inference failed for: r5v0, types: [kotlin.jvm.internal.DefaultConstructorMarker, java.util.ArrayList] */
    private final List<com.jd.lib.cashier.sdk.pay.dialog.k.e.e> l(List<? extends ExitRetainOptionEntity> retainBtnList) {
        ArrayList arrayList = new ArrayList();
        int size = retainBtnList.size();
        int i2 = ((size % 2) + size) / 2;
        int i3 = 1;
        if (1 <= i2) {
            int i4 = 1;
            while (true) {
                ?? r5 = 0;
                arrayList.add(new com.jd.lib.cashier.sdk.pay.dialog.k.e.e(r5, i3, r5));
                if (i4 == i2) {
                    break;
                }
                i4++;
            }
        }
        int size2 = retainBtnList.size();
        for (int i5 = 0; i5 < size2; i5++) {
            ((com.jd.lib.cashier.sdk.pay.dialog.k.e.e) arrayList.get(i5 / 2)).a().add(retainBtnList.get(i5));
        }
        return arrayList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.dataList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: m  reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NotNull DefaultExitGridViewHolder holder, int position) {
        if (position < 0 || position >= this.dataList.size()) {
            return;
        }
        holder.b(this.dataList.get(position));
        holder.c(this.dataList.get(position));
        holder.d(new a(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    /* renamed from: n  reason: merged with bridge method [inline-methods] */
    public DefaultExitGridViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lib_cashier_sdk_dialog_exit_grid_item, parent, false);
        Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
        return new DefaultExitGridViewHolder(itemView);
    }
}
