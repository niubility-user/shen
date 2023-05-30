package com.jd.lib.productdetail.mainimage.holder.recommend;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.topimagerecommend.PdSkuInfoListEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendRankEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMImageRecommendProductRecycleView extends RecyclerView {

    /* renamed from: g */
    public String f4915g;

    /* renamed from: h */
    public WareImageRecommendRankEntity f4916h;

    /* renamed from: i */
    public PdMainImagePresenter f4917i;

    /* loaded from: classes15.dex */
    public class RecommendAdapter extends RecyclerView.Adapter<RecommendHolder> {
        public List<PdSkuInfoListEntity> a;
        public int b;

        /* loaded from: classes15.dex */
        public class RecommendHolder extends RecyclerView.ViewHolder {
            public SimpleDraweeView a;
            public SimpleDraweeView b;

            /* renamed from: c */
            public SimpleDraweeView f4919c;
            public AppCompatTextView d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public RecommendHolder(@NonNull View view) {
                super(view);
                RecommendAdapter.this = r1;
                this.a = (SimpleDraweeView) view.findViewById(R.id.pd_top_recommend_product_image);
                this.b = (SimpleDraweeView) view.findViewById(R.id.pd_top_recommend_left_rank);
                this.f4919c = (SimpleDraweeView) view.findViewById(R.id.lib_pd_top_recommend_current_img);
                AppCompatTextView appCompatTextView = (AppCompatTextView) view.findViewById(R.id.pd_top_recommend_product_price);
                this.d = appCompatTextView;
                FontsUtil.changeTextFont(appCompatTextView, 4099);
            }
        }

        public RecommendAdapter(List<PdSkuInfoListEntity> list, int i2) {
            PdMImageRecommendProductRecycleView.this = r1;
            this.a = list;
            this.b = i2;
        }

        public static void l(RecommendAdapter recommendAdapter, String str) {
            recommendAdapter.getClass();
            JDJSONObject jDJSONObject = new JDJSONObject();
            jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) str);
            WareImageRecommendRankEntity wareImageRecommendRankEntity = PdMImageRecommendProductRecycleView.this.f4916h;
            if (wareImageRecommendRankEntity != null) {
                jDJSONObject.put("touchstone_expids", (Object) wareImageRecommendRankEntity.bangDanExperiment);
                jDJSONObject.put("rankid", (Object) PdMImageRecommendProductRecycleView.this.f4916h.rankId);
                PdMainImagePresenter pdMainImagePresenter = PdMImageRecommendProductRecycleView.this.f4917i;
                if (pdMainImagePresenter != null) {
                    pdMainImagePresenter.mtaClick("Productdetail_MainRankProduct", jDJSONObject.toJSONString());
                }
            }
        }

        @NonNull
        public RecommendHolder a(@NonNull ViewGroup viewGroup) {
            return new RecommendHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lib_pd_mainimage_top_recommend_product_item_new, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<PdSkuInfoListEntity> list = this.a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        public final void h(@NonNull RecommendHolder recommendHolder, PdSkuInfoListEntity pdSkuInfoListEntity) {
            int i2 = this.b;
            int i3 = PdMImageRecommendItemView.s;
            if (i2 == 1) {
                recommendHolder.itemView.setClickable(false);
            } else {
                recommendHolder.itemView.setClickable(true);
            }
            recommendHolder.itemView.setOnClickListener(new f(this, recommendHolder, pdSkuInfoListEntity));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull RecommendHolder recommendHolder, int i2) {
            View view;
            RecommendHolder recommendHolder2 = recommendHolder;
            List<PdSkuInfoListEntity> list = this.a;
            PdSkuInfoListEntity pdSkuInfoListEntity = (list == null || i2 >= list.size()) ? null : this.a.get(i2);
            if (pdSkuInfoListEntity == null || recommendHolder2 == null || (view = recommendHolder2.itemView) == null || view.getContext() == null) {
                return;
            }
            Context context = recommendHolder2.itemView.getContext();
            int i3 = PdMImageRecommendProductRecycleView.this.f4917i.appImageWidth;
            int dip2px = i3 - PDUtils.dip2px(54.0f);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) recommendHolder2.itemView.getLayoutParams();
            ((ViewGroup.MarginLayoutParams) layoutParams).width = dip2px / 3;
            ((ViewGroup.MarginLayoutParams) layoutParams).height = (i3 - PDUtils.dip2px(183.0f)) / 2;
            if (i2 != 4 && i2 != 5) {
                ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = PDUtils.dip2px(7.0f);
            } else {
                ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = 0;
            }
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = PDUtils.dip2px(8.0f);
            recommendHolder2.itemView.setLayoutParams(layoutParams);
            JDImageUtils.displayImage(pdSkuInfoListEntity.img, recommendHolder2.a);
            JDImageUtils.displayImage(pdSkuInfoListEntity.rankNumIcon, recommendHolder2.b);
            if (pdSkuInfoListEntity.isCurrentSku == 1) {
                recommendHolder2.f4919c.setVisibility(0);
                JDImageUtils.displayImage(pdSkuInfoListEntity.currentSkuIcon, recommendHolder2.f4919c);
            } else {
                recommendHolder2.f4919c.setVisibility(8);
            }
            if (!TextUtils.isEmpty(pdSkuInfoListEntity.buyedStr)) {
                recommendHolder2.d.setTypeface(FontsUtil.getTypeFace(context, 4099));
                recommendHolder2.d.setText(pdSkuInfoListEntity.buyedStr);
            } else if (!TextUtils.isEmpty(pdSkuInfoListEntity.finalPrice)) {
                FontsUtil.changeTextFont(recommendHolder2.d, 4099);
                recommendHolder2.d.setText(String.format(recommendHolder2.itemView.getContext().getString(R.string.lib_pd_image_discount_price), PDUtils.formatPrice(pdSkuInfoListEntity.finalPrice)));
            } else {
                FontsUtil.changeTextFont(recommendHolder2.d, 4099);
                recommendHolder2.d.setText(PDUtils.getJPriceText(pdSkuInfoListEntity.jdPrice, 1.0f));
            }
            h(recommendHolder2, pdSkuInfoListEntity);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public /* bridge */ /* synthetic */ RecommendHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            return a(viewGroup);
        }
    }

    /* loaded from: classes15.dex */
    public class a extends GridLayoutManager {
        public a(PdMImageRecommendProductRecycleView pdMImageRecommendProductRecycleView, Context context, int i2, int i3, boolean z) {
            super(context, i2, i3, z);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean canScrollHorizontally() {
            return false;
        }
    }

    public PdMImageRecommendProductRecycleView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(List<PdSkuInfoListEntity> list, int i2, String str, PdMainImagePresenter pdMainImagePresenter, WareImageRecommendRankEntity wareImageRecommendRankEntity) {
        this.f4915g = str;
        this.f4916h = wareImageRecommendRankEntity;
        this.f4917i = pdMainImagePresenter;
        setAdapter(new RecommendAdapter(list, i2));
        setLayoutManager(new a(this, getContext(), 3, 1, false));
        setHasFixedSize(true);
    }

    public PdMImageRecommendProductRecycleView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
