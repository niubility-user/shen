package com.jd.lib.productdetail.mainimage.holder.gift;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.HeadPicGiftInfoEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.views.PdAutoChangeTextSize;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMImageGiftLayerGiftsAdapter extends RecyclerView.Adapter<GiftsItemHolder> {
    public Context a;
    public List<HeadPicGiftInfoEntity.GiftLayerInfosEntity> b;

    /* renamed from: c  reason: collision with root package name */
    public PdMainImagePresenter f4824c;

    /* loaded from: classes15.dex */
    public class GiftsItemHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView a;
        public PdAutoChangeTextSize b;

        /* renamed from: c  reason: collision with root package name */
        public PdAutoChangeTextSize f4825c;
        public SimpleDraweeView d;

        /* renamed from: e  reason: collision with root package name */
        public LinearLayout f4826e;

        /* renamed from: f  reason: collision with root package name */
        public LinearLayout f4827f;

        public GiftsItemHolder(@NonNull View view) {
            super(view);
            this.a = (SimpleDraweeView) view.findViewById(R.id.pd_topimage_gif_layer_itemtop_image);
            this.b = (PdAutoChangeTextSize) view.findViewById(R.id.pd_topimage_gif_layer_itemtop_title);
            this.f4825c = (PdAutoChangeTextSize) view.findViewById(R.id.pd_topimage_gif_layer_itemtop_join);
            this.d = (SimpleDraweeView) view.findViewById(R.id.pd_topimage_gif_layer_itemtop_joinarr);
            this.f4826e = (LinearLayout) view.findViewById(R.id.pd_topimage_gif_layer_item_layout);
            this.f4827f = (LinearLayout) view.findViewById(R.id.pd_topimage_gif_layer_item_rootview);
        }
    }

    public PdMImageGiftLayerGiftsAdapter(Context context, PdMainImagePresenter pdMainImagePresenter) {
        this.f4824c = pdMainImagePresenter;
        this.a = context;
    }

    @NonNull
    public GiftsItemHolder a(@NonNull ViewGroup viewGroup) {
        return new GiftsItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lib_pd_mainimage_holder_topimage_item_gift_bottomlayer_itemlayout, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<HeadPicGiftInfoEntity.GiftLayerInfosEntity> list = this.b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void h(@NonNull GiftsItemHolder giftsItemHolder) {
        List<HeadPicGiftInfoEntity.GiftLayerInfosEntity> list = this.b;
        if (list == null || list.isEmpty() || this.a == null) {
            return;
        }
        HeadPicGiftInfoEntity.GiftLayerInfosEntity giftLayerInfosEntity = this.b.get(giftsItemHolder.getAdapterPosition());
        giftsItemHolder.f4826e.removeAllViews();
        if (!PdMImageGiftLayerGiftsAdapter.this.f4824c.getMainImageParams().isDark) {
            giftsItemHolder.f4827f.setBackgroundResource(R.drawable.lib_pd_mainimage_gift_layer_item_bg);
        } else {
            giftsItemHolder.f4827f.setBackgroundResource(R.drawable.lib_pd_mainimage_gift_layer_item_bg_dark);
        }
        if (giftLayerInfosEntity != null) {
            if (!TextUtils.isEmpty(giftLayerInfosEntity.titleIcon)) {
                JDImageLoader.display(giftLayerInfosEntity.titleIcon, giftsItemHolder.a);
            } else {
                giftsItemHolder.a.setVisibility(8);
            }
            if (!TextUtils.isEmpty(giftLayerInfosEntity.title)) {
                giftsItemHolder.b.setText(giftLayerInfosEntity.title);
            }
            if (!TextUtils.isEmpty(giftLayerInfosEntity.titleColor)) {
                giftsItemHolder.b.setTextColor(Color.parseColor(giftLayerInfosEntity.titleColor));
                giftsItemHolder.f4825c.setTextColor(Color.parseColor(giftLayerInfosEntity.titleColor));
            } else {
                PdAutoChangeTextSize pdAutoChangeTextSize = giftsItemHolder.b;
                PdMImageGiftLayerGiftsAdapter pdMImageGiftLayerGiftsAdapter = PdMImageGiftLayerGiftsAdapter.this;
                Context context = pdMImageGiftLayerGiftsAdapter.a;
                int i2 = R.color.lib_pd_image_color_1A1A1A;
                pdAutoChangeTextSize.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(context, i2, pdMImageGiftLayerGiftsAdapter.f4824c.getMainImageParams().isDark));
                PdAutoChangeTextSize pdAutoChangeTextSize2 = giftsItemHolder.f4825c;
                PdMImageGiftLayerGiftsAdapter pdMImageGiftLayerGiftsAdapter2 = PdMImageGiftLayerGiftsAdapter.this;
                pdAutoChangeTextSize2.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(pdMImageGiftLayerGiftsAdapter2.a, i2, pdMImageGiftLayerGiftsAdapter2.f4824c.getMainImageParams().isDark));
            }
            if (!TextUtils.isEmpty(giftLayerInfosEntity.jumpName) && !TextUtils.isEmpty(giftLayerInfosEntity.jumplink)) {
                giftsItemHolder.f4825c.setVisibility(0);
                giftsItemHolder.d.setVisibility(0);
                giftsItemHolder.f4825c.setText(giftLayerInfosEntity.jumpName);
                if (!TextUtils.isEmpty(giftLayerInfosEntity.arrow)) {
                    JDImageLoader.display(giftLayerInfosEntity.arrow, giftsItemHolder.d, (JDDisplayImageOptions) null, new a(giftsItemHolder));
                } else {
                    SimpleDraweeView simpleDraweeView = giftsItemHolder.d;
                    if (simpleDraweeView != null) {
                        simpleDraweeView.setVisibility(4);
                    }
                }
                giftsItemHolder.f4825c.setOnClickListener(new b(giftsItemHolder, giftLayerInfosEntity));
            } else {
                giftsItemHolder.f4825c.setVisibility(8);
                giftsItemHolder.d.setVisibility(8);
            }
            List<HeadPicGiftInfoEntity.GiftsEntity> list2 = giftLayerInfosEntity.gifts;
            if (list2 == null || list2.isEmpty()) {
                return;
            }
            for (int i3 = 0; i3 < giftLayerInfosEntity.gifts.size(); i3++) {
                HeadPicGiftInfoEntity.GiftsEntity giftsEntity = giftLayerInfosEntity.gifts.get(i3);
                ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(PdMImageGiftLayerGiftsAdapter.this.a).inflate(R.layout.lib_pd_mainimage_holder_topimage_item_gift_bottomlayer_gifts, (ViewGroup) null);
                PdAutoChangeTextSize pdAutoChangeTextSize3 = (PdAutoChangeTextSize) viewGroup.findViewById(R.id.pd_topimage_gift_item_title);
                PdAutoChangeTextSize pdAutoChangeTextSize4 = (PdAutoChangeTextSize) viewGroup.findViewById(R.id.pd_topimage_gift_item_num);
                PdAutoChangeTextSize pdAutoChangeTextSize5 = (PdAutoChangeTextSize) viewGroup.findViewById(R.id.pd_topimage_gift_item_x);
                PdMImageGiftLayerGiftsAdapter pdMImageGiftLayerGiftsAdapter3 = PdMImageGiftLayerGiftsAdapter.this;
                Context context2 = pdMImageGiftLayerGiftsAdapter3.a;
                int i4 = R.color.lib_pd_image_color_1A1A1A;
                pdAutoChangeTextSize3.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(context2, i4, pdMImageGiftLayerGiftsAdapter3.f4824c.getMainImageParams().isDark));
                PdMImageGiftLayerGiftsAdapter pdMImageGiftLayerGiftsAdapter4 = PdMImageGiftLayerGiftsAdapter.this;
                pdAutoChangeTextSize4.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(pdMImageGiftLayerGiftsAdapter4.a, i4, pdMImageGiftLayerGiftsAdapter4.f4824c.getMainImageParams().isDark));
                PdMImageGiftLayerGiftsAdapter pdMImageGiftLayerGiftsAdapter5 = PdMImageGiftLayerGiftsAdapter.this;
                pdAutoChangeTextSize5.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(pdMImageGiftLayerGiftsAdapter5.a, i4, pdMImageGiftLayerGiftsAdapter5.f4824c.getMainImageParams().isDark));
                FontsUtil.changeTextFont(pdAutoChangeTextSize5, 4099);
                FontsUtil.changeTextFont(pdAutoChangeTextSize4, 4099);
                int dip2px = PDUtils.dip2px(6.0f);
                JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                float f2 = dip2px;
                createSimple.setRoundingParams(RoundingParams.fromCornersRadii(f2, f2, f2, f2));
                JDImageLoader.display(giftsEntity.img, (SimpleDraweeView) viewGroup.findViewById(R.id.pd_topimage_gift_item_image), createSimple, (ImageRequestListener<ImageInfo>) null);
                if (!TextUtils.isEmpty(giftsEntity.desc)) {
                    pdAutoChangeTextSize3.setText(giftsEntity.desc);
                }
                if (!TextUtils.isEmpty(giftsEntity.num)) {
                    pdAutoChangeTextSize4.setText(giftsEntity.num);
                    pdAutoChangeTextSize5.setText(JshopConst.JSHOP_PROMOTIO_X);
                }
                giftsItemHolder.f4826e.addView(viewGroup, new ViewGroup.LayoutParams(-1, -1));
                giftsItemHolder.f4826e.setOnClickListener(new c(giftsItemHolder, giftLayerInfosEntity, i3));
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(@NonNull GiftsItemHolder giftsItemHolder, int i2) {
        h(giftsItemHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public /* bridge */ /* synthetic */ GiftsItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return a(viewGroup);
    }
}
