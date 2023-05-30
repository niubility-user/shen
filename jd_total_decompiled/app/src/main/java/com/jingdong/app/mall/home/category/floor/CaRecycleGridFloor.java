package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.adapter.CaItemAdapter;
import com.jingdong.app.mall.home.category.floor.base.BaseCaEventFloor;
import com.jingdong.app.mall.home.category.floor.base.a;
import com.jingdong.app.mall.home.category.view.CaTitleView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.b;
import com.jingdong.app.mall.home.n.g.u.d;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B%\u0012\u0006\u00101\u001a\u000200\u0012\u0006\u00103\u001a\u000202\u0012\f\u00106\u001a\b\u0012\u0004\u0012\u00020504\u00a2\u0006\u0004\b7\u00108J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\t\u0010\nR\"\u0010\u0012\u001a\u00020\u000b8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0015\u001a\u00020\b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\"\u0010\u001d\u001a\u00020\u00168\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010%\u001a\u00020\u001e8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\"\u0010-\u001a\u00020&8\u0004@\u0004X\u0084\u000e\u00a2\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0016\u0010/\u001a\u00020\b8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b.\u0010\u0014\u00a8\u00069"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaRecycleGridFloor;", "Lcom/jingdong/app/mall/home/n/g/u/d;", "M", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaEventFloor;", CartConstant.KEY_VENDOR_ITEM, "", IShareAdapter.SHARE_ACTION_PANE, "(Lcom/jingdong/app/mall/home/n/g/u/d;)V", "Lcom/jingdong/app/mall/home/floor/common/f;", "R", "()Lcom/jingdong/app/mall/home/floor/common/f;", "Lcom/jingdong/app/mall/home/category/adapter/CaItemAdapter;", "u", "Lcom/jingdong/app/mall/home/category/adapter/CaItemAdapter;", "Q", "()Lcom/jingdong/app/mall/home/category/adapter/CaItemAdapter;", "setMAdapter", "(Lcom/jingdong/app/mall/home/category/adapter/CaItemAdapter;)V", "mAdapter", "v", "Lcom/jingdong/app/mall/home/floor/common/f;", "mRecycleSize", "Lcom/jingdong/app/mall/home/category/view/CaTitleView;", JshopConst.JSHOP_PROMOTIO_W, "Lcom/jingdong/app/mall/home/category/view/CaTitleView;", "getMTitleView", "()Lcom/jingdong/app/mall/home/category/view/CaTitleView;", "setMTitleView", "(Lcom/jingdong/app/mall/home/category/view/CaTitleView;)V", "mTitleView", "Landroidx/recyclerview/widget/GridLayoutManager;", "t", "Landroidx/recyclerview/widget/GridLayoutManager;", "getMLayoutManager", "()Landroidx/recyclerview/widget/GridLayoutManager;", "setMLayoutManager", "(Landroidx/recyclerview/widget/GridLayoutManager;)V", "mLayoutManager", "Landroidx/recyclerview/widget/RecyclerView;", "s", "Landroidx/recyclerview/widget/RecyclerView;", "getMRecycleView", "()Landroidx/recyclerview/widget/RecyclerView;", "setMRecycleView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "mRecycleView", JshopConst.JSHOP_PROMOTIO_X, "mTitleSize", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "", "Lcom/jingdong/app/mall/home/n/b;", "subTypeEnums", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;[Lcom/jingdong/app/mall/home/category/CTypeSubEnum;)V", "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public class CaRecycleGridFloor<M extends d> extends BaseCaEventFloor<M> {
    @NotNull

    /* renamed from: s  reason: from kotlin metadata */
    private RecyclerView mRecycleView;
    @NotNull

    /* renamed from: t  reason: from kotlin metadata */
    private GridLayoutManager mLayoutManager;
    @NotNull

    /* renamed from: u  reason: from kotlin metadata */
    private CaItemAdapter mAdapter;

    /* renamed from: v  reason: from kotlin metadata */
    private f mRecycleSize;
    @NotNull

    /* renamed from: w  reason: from kotlin metadata */
    private CaTitleView mTitleView;

    /* renamed from: x  reason: from kotlin metadata */
    private final f mTitleSize;

    public CaRecycleGridFloor(@NotNull Context context, @NotNull CaAdapter caAdapter, @NotNull b[] bVarArr) {
        super(context, caAdapter);
        CaTitleView caTitleView = new CaTitleView(context);
        this.mTitleView = caTitleView;
        caTitleView.setId(R.id.home_category_floor_title);
        f fVar = new f(-1, 0);
        this.mTitleSize = fVar;
        CaTitleView caTitleView2 = this.mTitleView;
        addView(caTitleView2, fVar.u(caTitleView2));
        this.mRecycleView = new RecyclerView(context);
        this.mAdapter = new CaItemAdapter(context, this, this.mRecycleView);
        this.mRecycleView.setNestedScrollingEnabled(false);
        this.mRecycleView.setHasFixedSize(true);
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 0;
        for (b bVar : bVarArr) {
            intRef.element += bVar.getSpanSize();
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, intRef, context, intRef.element) { // from class: com.jingdong.app.mall.home.category.floor.CaRecycleGridFloor.1
            {
                super(context, r4);
            }

            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
        this.mLayoutManager = gridLayoutManager;
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.jingdong.app.mall.home.category.floor.CaRecycleGridFloor.2
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int position) {
                b viewType = CaRecycleGridFloor.this.getMAdapter().h(position);
                Intrinsics.checkExpressionValueIsNotNull(viewType, "viewType");
                return viewType.getItemSpanSize();
            }
        });
        this.mLayoutManager.setAutoMeasureEnabled(true);
        this.mLayoutManager.setOrientation(1);
        this.mRecycleView.setLayoutManager(this.mLayoutManager);
        this.mRecycleView.setItemViewCacheSize(0);
        this.mRecycleView.setAdapter(this.mAdapter);
        f R = R();
        this.mRecycleSize = R;
        RelativeLayout.LayoutParams u = R.u(this.mRecycleView);
        u.addRule(14);
        u.addRule(3, this.mTitleView.getId());
        f.H(this.mRecycleSize, u);
        f.M(this.mRecycleSize, this.mRecycleView);
        addView(this.mRecycleView, u);
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: P  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull M item) {
        f.b(this.mTitleView, -1, r());
        f.c(this.mRecycleView, this.mRecycleSize);
        a.a(this.mRecycleView, item.G(), this.mLayoutManager.getSpanCount());
        this.mAdapter.q(item.I());
        this.mTitleView.a(item.D(), item.i());
    }

    @NotNull
    /* renamed from: Q  reason: from getter */
    protected final CaItemAdapter getMAdapter() {
        return this.mAdapter;
    }

    @NotNull
    public f R() {
        return new f(-1, -1);
    }
}
