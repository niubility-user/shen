package com.jingdong.common.jdmiaosha.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.common.jdmiaosha.adapter.MiaoShaBannerAdapter;
import com.jingdong.common.jdmiaosha.entity.MiaoShaBannerEntity;
import com.jingdong.common.jump.JumpUtil;
import com.jingdong.common.utils.JDImageUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 ,2\u00020\u0001:\u0002,-B\u001d\u0012\u0006\u0010(\u001a\u00020\u001c\u0012\f\u0010)\u001a\b\u0012\u0004\u0012\u00020%0$\u00a2\u0006\u0004\b*\u0010+J\u001f\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0007\u0010\bJ'\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0006H\u0016\u00a2\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\u0004H\u0016\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0016\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001d\u001a\u00020\u001c8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R\u001c\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0!8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\"\u0010#R\u001c\u0010&\u001a\b\u0012\u0004\u0012\u00020%0$8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b&\u0010'\u00a8\u0006."}, d2 = {"Lcom/jingdong/common/jdmiaosha/adapter/MiaoShaBannerAdapter;", "Landroidx/viewpager/widget/PagerAdapter;", "Landroid/view/ViewGroup;", "container", "", "position1", "", "instantiateItem", "(Landroid/view/ViewGroup;I)Ljava/lang/Object;", "position", "object", "", "destroyItem", "(Landroid/view/ViewGroup;ILjava/lang/Object;)V", "Landroid/view/View;", "view", "o", "", "isViewFromObject", "(Landroid/view/View;Ljava/lang/Object;)Z", "getCount", "()I", "Lcom/jingdong/common/jdmiaosha/adapter/MiaoShaBannerAdapter$IBannerClickListener;", CartCleanConstants.CART_CLEAN_DIALOG_LISTENER, "setOnBannerClickListener", "(Lcom/jingdong/common/jdmiaosha/adapter/MiaoShaBannerAdapter$IBannerClickListener;)V", "mOnBannerClickListener", "Lcom/jingdong/common/jdmiaosha/adapter/MiaoShaBannerAdapter$IBannerClickListener;", "Landroid/content/Context;", "mContext", "Landroid/content/Context;", "mPosition", "I", "Landroid/util/SparseArray;", "tabBanners", "Landroid/util/SparseArray;", "", "Lcom/jingdong/common/jdmiaosha/entity/MiaoShaBannerEntity;", "mBannerList", "Ljava/util/List;", AnnoConst.Constructor_Context, "banners", "<init>", "(Landroid/content/Context;Ljava/util/List;)V", "Companion", "IBannerClickListener", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes5.dex */
public final class MiaoShaBannerAdapter extends PagerAdapter {
    private static final int TYPE_BANNER_IMG = 0;
    private static final int TYPE_BANNER_IMG_SKU = 2;
    private static final int TYPE_BANNER_LIVE = 1;
    private List<MiaoShaBannerEntity> mBannerList;
    private final Context mContext;
    private IBannerClickListener mOnBannerClickListener;
    private int mPosition;
    private final SparseArray<View> tabBanners = new SparseArray<>();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J!\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H&\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/jingdong/common/jdmiaosha/adapter/MiaoShaBannerAdapter$IBannerClickListener;", "", "", "pos", "Lcom/jingdong/common/jdmiaosha/entity/MiaoShaBannerEntity;", "entity", "", "onBannerClick", "(ILcom/jingdong/common/jdmiaosha/entity/MiaoShaBannerEntity;)V", "AndroidJD-Lib_androidRelease"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes5.dex */
    public interface IBannerClickListener {
        void onBannerClick(int pos, @Nullable MiaoShaBannerEntity entity);
    }

    public MiaoShaBannerAdapter(@NotNull Context context, @NotNull List<MiaoShaBannerEntity> list) {
        ArrayList arrayList = new ArrayList();
        this.mBannerList = arrayList;
        arrayList.addAll(list);
        this.mContext = context;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        container.removeView(this.tabBanners.get(position));
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.mBannerList.size() + (this.mBannerList.size() < 2 ? 0 : 2);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @NotNull
    public Object instantiateItem(@NotNull ViewGroup container, final int position1) {
        int size = this.mBannerList.size();
        int i2 = position1 % size;
        this.mPosition = i2;
        this.mPosition = ((i2 + size) - 1) % size;
        if (this.tabBanners.get(position1) == null) {
            final MiaoShaBannerEntity miaoShaBannerEntity = this.mBannerList.get(this.mPosition);
            SimpleDraweeView simpleDraweeView = null;
            int i3 = miaoShaBannerEntity.type;
            if (i3 == 0) {
                simpleDraweeView = new SimpleDraweeView(this.mContext);
                simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                simpleDraweeView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                JDImageUtils.displayImage(miaoShaBannerEntity.bannerImg, simpleDraweeView);
            } else if (i3 != 1 && i3 != 2) {
                simpleDraweeView = new SimpleDraweeView(this.mContext);
                simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
                simpleDraweeView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                JDImageUtils.displayImage(miaoShaBannerEntity.bannerImg, simpleDraweeView);
            }
            if (simpleDraweeView != null) {
                simpleDraweeView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.jdmiaosha.adapter.MiaoShaBannerAdapter$instantiateItem$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        Context context;
                        MiaoShaBannerAdapter.IBannerClickListener iBannerClickListener;
                        context = MiaoShaBannerAdapter.this.mContext;
                        JumpUtil.execJump(context, miaoShaBannerEntity.jump, -1);
                        iBannerClickListener = MiaoShaBannerAdapter.this.mOnBannerClickListener;
                        if (iBannerClickListener != null) {
                            iBannerClickListener.onBannerClick(position1, miaoShaBannerEntity);
                        }
                    }
                });
            }
            this.tabBanners.put(position1, simpleDraweeView);
        }
        container.addView(this.tabBanners.get(position1));
        View view = this.tabBanners.get(position1);
        Intrinsics.checkExpressionValueIsNotNull(view, "tabBanners[position1]");
        return view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NotNull View view, @NotNull Object o) {
        return view == o;
    }

    public final void setOnBannerClickListener(@NotNull IBannerClickListener listener) {
        this.mOnBannerClickListener = listener;
    }
}
