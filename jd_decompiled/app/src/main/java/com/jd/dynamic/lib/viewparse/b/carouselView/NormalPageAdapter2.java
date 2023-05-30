package com.jd.dynamic.lib.viewparse.b.carouselView;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.dynamic.lib.views.ItemView;
import com.jd.dynamic.yoga.android.YogaLayout;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001 B\u0017\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001f\u0010\b\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0011\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00198\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00138\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u00a8\u0006!"}, d2 = {"Lcom/jd/dynamic/lib/viewparse/iviews/carouselView/NormalPageAdapter2;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/jd/dynamic/lib/viewparse/iviews/carouselView/ICarouselView;", "Landroid/view/ViewGroup;", "p0", "", "p1", "onCreateViewHolder", "(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "viewHolder", "position", "", "onBindViewHolder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V", "getItemViewType", "(I)I", "getItemCount", "()I", "Landroid/view/View;", "getCurrentView", "()Landroid/view/View;", "Lcom/jd/dynamic/lib/viewparse/iviews/carouselView/CarouselView;", "carouselView", "Lcom/jd/dynamic/lib/viewparse/iviews/carouselView/CarouselView;", "", "isCycle", "Z", "view", "Landroid/view/View;", "<init>", "(Lcom/jd/dynamic/lib/viewparse/iviews/carouselView/CarouselView;Z)V", "ItemViewHolder", "lib_release"}, k = 1, mv = {1, 4, 0})
/* renamed from: com.jd.dynamic.lib.viewparse.b.a.l */
/* loaded from: classes13.dex */
public final class NormalPageAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements o {

    /* renamed from: g */
    private View f2357g;

    /* renamed from: h */
    private final f f2358h;

    /* renamed from: i */
    private final boolean f2359i;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/jd/dynamic/lib/viewparse/iviews/carouselView/NormalPageAdapter2$ItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View;", "itemView", "<init>", "(Landroid/view/View;)V", "lib_release"}, k = 1, mv = {1, 4, 0})
    /* renamed from: com.jd.dynamic.lib.viewparse.b.a.l$a */
    /* loaded from: classes13.dex */
    public static final class a extends RecyclerView.ViewHolder {
        public a(@NotNull View view) {
            super(view);
        }
    }

    public NormalPageAdapter2(@NotNull f fVar, boolean z) {
        this.f2358h = fVar;
        this.f2359i = z;
    }

    @Override // com.jd.dynamic.lib.viewparse.b.carouselView.o
    @Nullable
    /* renamed from: a  reason: from getter */
    public View getF2357g() {
        return this.f2357g;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.f2359i) {
            return Integer.MAX_VALUE;
        }
        return this.f2358h.u();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        String b = this.f2358h.b(position % this.f2358h.u());
        Intrinsics.checkExpressionValueIsNotNull(b, "carouselView.getItemTypeByPosition(calcPos)");
        return Integer.parseInt(b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder viewHolder, int position) {
        int u = position % this.f2358h.u();
        f fVar = this.f2358h;
        ItemView j2 = fVar.j(fVar.b(u));
        if (j2 != null) {
            j2.bindData(viewHolder.itemView, this.f2358h.k(u));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup p0, int p1) {
        FrameLayout frameLayout = new FrameLayout(p0.getContext());
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        View parse = this.f2358h.j(String.valueOf(p1) + "").parse();
        Intrinsics.checkExpressionValueIsNotNull(parse, "carouselView.getCarousel\u2026.toString() + \"\").parse()");
        YogaLayout yogaLayout = (YogaLayout) (!(parse instanceof YogaLayout) ? null : parse);
        if (yogaLayout != null) {
            if (parse == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.jd.dynamic.yoga.android.YogaLayout");
            }
            yogaLayout.setLayoutParams(new RecyclerView.LayoutParams((ViewGroup.MarginLayoutParams) ((YogaLayout) parse).getYogaLayoutLayoutParams()));
        }
        frameLayout.addView(parse);
        return new a(frameLayout);
    }
}
