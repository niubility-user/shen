package com.jingdong.app.mall.home.category.floor;

import android.content.Context;
import android.util.SparseArray;
import android.widget.LinearLayout;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.app.mall.home.R;
import com.jingdong.app.mall.home.category.adapter.CaAdapter;
import com.jingdong.app.mall.home.category.floor.base.BaseCaEventFloor;
import com.jingdong.app.mall.home.category.floor.base.BaseCaRecycleItem;
import com.jingdong.app.mall.home.category.view.CaTitleView;
import com.jingdong.app.mall.home.floor.common.f;
import com.jingdong.app.mall.home.n.b;
import com.jingdong.app.mall.home.n.g.u.d;
import com.jingdong.app.mall.home.n.g.u.e;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003:\u00010B-\u0012\u0006\u0010*\u001a\u00020)\u0012\u0006\u0010,\u001a\u00020+\u0012\f\u0010-\u001a\b\u0012\u0004\u0012\u00020%0$\u0012\u0006\u0010#\u001a\u00020 \u00a2\u0006\u0004\b.\u0010/J\u001d\u0010\b\u001a\u00020\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ+\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\n2\u0012\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0017\u001a\u00020\u00148\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010#\u001a\u00020 8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b!\u0010\"R\u001c\u0010(\u001a\b\u0012\u0004\u0012\u00020%0$8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b&\u0010'\u00a8\u00061"}, d2 = {"Lcom/jingdong/app/mall/home/category/floor/CaHorizontalLinearFloor;", "Lcom/jingdong/app/mall/home/n/g/u/d;", "M", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaEventFloor;", "", "Lcom/jingdong/app/mall/home/n/g/u/e;", "itemList", "", "R", "(Ljava/util/List;)V", "Landroid/widget/LinearLayout;", "content", "Landroid/util/SparseArray;", "Lcom/jingdong/app/mall/home/category/floor/base/BaseCaRecycleItem;", CartConstant.KEY_ITEMS, IShareAdapter.SHARE_ACTION_PANE, "(Landroid/widget/LinearLayout;Landroid/util/SparseArray;)V", CartConstant.KEY_VENDOR_ITEM, "Q", "(Lcom/jingdong/app/mall/home/n/g/u/d;)V", "", "t", "I", "mLineCount", "Lcom/jingdong/app/mall/home/category/view/CaTitleView;", "v", "Lcom/jingdong/app/mall/home/category/view/CaTitleView;", "mTitleView", "Lcom/jingdong/app/mall/home/category/floor/CaHorizontalLinearFloor$a;", "s", "Landroid/util/SparseArray;", "mLinearList", "Lcom/jingdong/app/mall/home/n/g/w/b;", JshopConst.JSHOP_PROMOTIO_W, "Lcom/jingdong/app/mall/home/n/g/w/b;", "mFloorInfo", "", "Lcom/jingdong/app/mall/home/n/b;", "u", "[Lcom/jingdong/app/mall/home/category/CTypeSubEnum;", "mSubTypeEnums", "Landroid/content/Context;", AnnoConst.Constructor_Context, "Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;", "adapter", "subTypeEnums", "<init>", "(Landroid/content/Context;Lcom/jingdong/app/mall/home/category/adapter/CaAdapter;[Lcom/jingdong/app/mall/home/category/CTypeSubEnum;Lcom/jingdong/app/mall/home/n/g/w/b;)V", com.jingdong.jdsdk.a.a.a, "home_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes4.dex */
public class CaHorizontalLinearFloor<M extends d> extends BaseCaEventFloor<M> {
    private static final int[] x = {R.id.mallfloor_item1, R.id.mallfloor_item2, R.id.mallfloor_item3, R.id.mallfloor_item4};

    /* renamed from: s  reason: from kotlin metadata */
    private final SparseArray<a> mLinearList;

    /* renamed from: t  reason: from kotlin metadata */
    private int mLineCount;
    private final b[] u;

    /* renamed from: v  reason: from kotlin metadata */
    private CaTitleView mTitleView;

    /* renamed from: w  reason: from kotlin metadata */
    private final com.jingdong.app.mall.home.n.g.w.b mFloorInfo;

    /* loaded from: classes4.dex */
    public static final class a {
        @NotNull
        private LinearLayout a;
        @NotNull
        private SparseArray<BaseCaRecycleItem<e>> b;

        public a(@NotNull LinearLayout linearLayout, @NotNull SparseArray<BaseCaRecycleItem<e>> sparseArray) {
            this.a = linearLayout;
            this.b = sparseArray;
        }

        @NotNull
        public final LinearLayout a() {
            return this.a;
        }

        @NotNull
        public final SparseArray<BaseCaRecycleItem<e>> b() {
            return this.b;
        }
    }

    public CaHorizontalLinearFloor(@NotNull Context context, @NotNull CaAdapter caAdapter, @NotNull b[] bVarArr, @NotNull com.jingdong.app.mall.home.n.g.w.b bVar) {
        super(context, caAdapter);
        this.mFloorInfo = bVar;
        this.mLinearList = new SparseArray<>();
        boolean z = bVar.d;
        if (z) {
            CaTitleView caTitleView = new CaTitleView(context);
            this.mTitleView = caTitleView;
            if (caTitleView != null) {
                caTitleView.setId(R.id.mallfloor_floor_title);
            }
            f fVar = new f(-1, 0);
            CaTitleView caTitleView2 = this.mTitleView;
            if (caTitleView2 == null) {
                Intrinsics.throwNpe();
            }
            addView(caTitleView2, fVar.u(caTitleView2));
        }
        this.u = bVarArr;
        for (b bVar2 : bVarArr) {
            this.mLineCount += bVar2.getSpanSize();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void P(LinearLayout content, SparseArray<BaseCaRecycleItem<e>> items) {
        b[] bVarArr = this.u;
        if (bVarArr.length == 1) {
            int i2 = this.mLineCount;
            for (int i3 = 0; i3 < i2; i3++) {
                b bVar = this.u[0];
                BaseCaRecycleItem floorView = bVar.getFloorView(getContext());
                items.put(i3, floorView);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, bVar.getFloorHeight());
                layoutParams.weight = bVar.getItemSpanSize();
                content.addView(floorView, layoutParams);
            }
            return;
        }
        int length = bVarArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            b bVar2 = this.u[i4];
            BaseCaRecycleItem floorView2 = bVar2.getFloorView(getContext());
            items.put(i4, floorView2);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, bVar2.getFloorHeight());
            layoutParams2.weight = bVar2.getItemSpanSize();
            content.addView(floorView2, layoutParams2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x012b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void R(java.util.List<? extends com.jingdong.app.mall.home.n.g.u.e> r14) {
        /*
            Method dump skipped, instructions count: 384
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.app.mall.home.category.floor.CaHorizontalLinearFloor.R(java.util.List):void");
    }

    @Override // com.jingdong.app.mall.home.category.floor.base.BaseCaFloor
    /* renamed from: Q  reason: merged with bridge method [inline-methods] */
    public void j(@NotNull M item) {
        CaTitleView caTitleView = this.mTitleView;
        if (caTitleView != null) {
            f.b(caTitleView, -1, r());
            CaTitleView caTitleView2 = this.mTitleView;
            if (caTitleView2 != null) {
                caTitleView2.a(item.D(), item.i());
            }
        }
        List<e> I = item.I();
        Intrinsics.checkExpressionValueIsNotNull(I, "item!!.itemList");
        R(I);
    }
}
