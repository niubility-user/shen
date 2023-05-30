package com.jd.lib.productdetail.mainimage.holder.gift;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.HeadPicGiftInfoEntity;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMImageGiftLayer extends RelativeLayout {
    private ImageView closeIcon;
    private FrameLayout closeImage;
    private List<HeadPicGiftInfoEntity.GiftLayerInfosEntity> giftLayerInfos;
    private LinearLayoutManager giftsItemManager;
    private RecyclerView giftsRecycle;
    private boolean isBigScreen;
    private boolean isSlided;
    private PdMImageGiftLayerGiftsAdapter layerGiftsAdapter;
    private PdMImageGiftLayerTitleAdapter layerTitleAdapter;
    private PdMainImagePresenter mainImagePresenter;
    private OnGiftLayerClickListener onGiftLayerClickListener;
    private LinearLayoutManager titleManager;
    private RecyclerView titleRecycle;
    private View viewLine;

    /* loaded from: classes15.dex */
    public interface OnGiftLayerClickListener {
        void onClose();
    }

    public PdMImageGiftLayer(Context context, boolean z, PdMainImagePresenter pdMainImagePresenter) {
        super(context);
        this.mainImagePresenter = pdMainImagePresenter;
        this.isBigScreen = z;
        initView();
    }

    private void buildGiftsRecycle(List<HeadPicGiftInfoEntity.GiftLayerInfosEntity> list, boolean z) {
        if (this.layerGiftsAdapter == null) {
            PdMImageGiftLayerGiftsAdapter pdMImageGiftLayerGiftsAdapter = new PdMImageGiftLayerGiftsAdapter(getContext(), this.mainImagePresenter);
            this.layerGiftsAdapter = pdMImageGiftLayerGiftsAdapter;
            this.giftsRecycle.setAdapter(pdMImageGiftLayerGiftsAdapter);
            this.giftsRecycle.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayer.3
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
                    super.onScrolled(recyclerView, i2, i3);
                    int findFirstVisibleItemPosition = PdMImageGiftLayer.this.giftsItemManager.findFirstVisibleItemPosition();
                    if (PdMImageGiftLayer.this.layerTitleAdapter != null) {
                        PdMImageGiftLayer.this.layerTitleAdapter.h(findFirstVisibleItemPosition);
                    }
                    if (PdMImageGiftLayer.this.isSlided) {
                        return;
                    }
                    PdMImageGiftLayer.this.isSlided = true;
                    if (PdMImageGiftLayer.this.mainImagePresenter != null) {
                        PdMImageGiftLayer.this.mainImagePresenter.mtaClick("Productdetail_ZPSpecSlide");
                    }
                }
            });
        }
        this.layerGiftsAdapter.b = list;
    }

    private void buildTitleRecycle(List<String> list, boolean z) {
        if (this.layerTitleAdapter == null) {
            PdMImageGiftLayerTitleAdapter pdMImageGiftLayerTitleAdapter = new PdMImageGiftLayerTitleAdapter(this.titleRecycle, getContext(), this.isBigScreen);
            this.layerTitleAdapter = pdMImageGiftLayerTitleAdapter;
            pdMImageGiftLayerTitleAdapter.f4831f = new PdMImageGiftLayerTitleAdapter.a() { // from class: com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayer.2
                @Override // com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayerTitleAdapter.a
                public void onClick(View view, int i2) {
                    if (PdMImageGiftLayer.this.giftsItemManager != null) {
                        PdMImageGiftLayer.this.giftsItemManager.scrollToPositionWithOffset(i2, 0);
                    }
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    String str = ((HeadPicGiftInfoEntity.GiftLayerInfosEntity) PdMImageGiftLayer.this.giftLayerInfos.get(i2)).skuType;
                    if (!TextUtils.isEmpty(str)) {
                        jDJSONObject.put("type", (Object) str);
                    }
                    if (PdMImageGiftLayer.this.mainImagePresenter != null) {
                        PdMImageGiftLayer.this.mainImagePresenter.mtaClick("Productdetail_ZPSpecTab", jDJSONObject.toJSONString());
                    }
                }
            };
            this.titleRecycle.setAdapter(this.layerTitleAdapter);
        }
        PdMImageGiftLayerTitleAdapter pdMImageGiftLayerTitleAdapter2 = this.layerTitleAdapter;
        pdMImageGiftLayerTitleAdapter2.a = list;
        pdMImageGiftLayerTitleAdapter2.b = 0;
        pdMImageGiftLayerTitleAdapter2.d = z;
        if (list == null || list.size() != 1) {
            return;
        }
        pdMImageGiftLayerTitleAdapter2.f4829c = true;
    }

    private void initView() {
        View inflate = View.inflate(getContext(), R.layout.lib_pd_mainimage_holder_topimage_item_gift_bottomlayer, this);
        this.titleRecycle = (RecyclerView) inflate.findViewById(R.id.pd_topimage_gif_layer_titlerecycle);
        this.giftsRecycle = (RecyclerView) inflate.findViewById(R.id.pd_topimage_gif_layer_gifts);
        this.closeImage = (FrameLayout) inflate.findViewById(R.id.pd_topimage_gif_layer_close);
        this.closeIcon = (ImageView) inflate.findViewById(R.id.pd_topimage_gif_layer_close_image);
        this.viewLine = inflate.findViewById(R.id.pd_topimage_gif_layer_title_viewline);
        if (this.giftsItemManager == null) {
            this.giftsItemManager = new LinearLayoutManager(getContext(), 1, false);
        }
        if (this.titleManager == null) {
            this.titleManager = new LinearLayoutManager(getContext(), 0, false);
        }
        this.titleRecycle.setLayoutManager(this.titleManager);
        this.giftsRecycle.setLayoutManager(this.giftsItemManager);
        this.titleRecycle.setItemAnimator(null);
        this.titleRecycle.setHasFixedSize(true);
        this.giftsRecycle.setHasFixedSize(true);
        this.closeImage.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.holder.gift.PdMImageGiftLayer.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (PdMImageGiftLayer.this.mainImagePresenter != null && PdMImageGiftLayer.this.mainImagePresenter.mLayerDialog != null) {
                    PdMImageGiftLayer.this.mainImagePresenter.mLayerDialog.dismiss();
                }
                if (PdMImageGiftLayer.this.onGiftLayerClickListener != null) {
                    PdMImageGiftLayer.this.onGiftLayerClickListener.onClose();
                }
            }
        });
    }

    private void setDark(boolean z) {
        if (z) {
            setBackgroundResource(R.drawable.lib_pd_mainimage_gift_layer_bg_dark);
            View view = this.viewLine;
            if (view != null) {
                view.setVisibility(8);
            }
            this.closeIcon.setBackgroundResource(R.drawable.lib_pd_mainimage_topimage_gift_layer_close_dark);
            return;
        }
        setBackgroundResource(R.drawable.lib_pd_mainimage_gift_layer_bg);
        View view2 = this.viewLine;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        this.closeIcon.setBackgroundResource(R.drawable.lib_pd_mainimage_big_plus_layer_btn_close);
    }

    public void buildData2View(List<HeadPicGiftInfoEntity.GiftLayerInfosEntity> list) {
        this.giftLayerInfos = list;
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < list.size(); i2++) {
                arrayList.add(list.get(i2).giftAllName);
            }
            buildTitleRecycle(arrayList, this.mainImagePresenter.getMainImageParams().isDark);
            buildGiftsRecycle(list, this.mainImagePresenter.getMainImageParams().isDark);
        }
        setDark(this.mainImagePresenter.getMainImageParams().isDark);
    }

    public void layerIsShow(boolean z) {
        this.isSlided = false;
        List<HeadPicGiftInfoEntity.GiftLayerInfosEntity> list = this.giftLayerInfos;
        if (list == null || list.isEmpty() || !z) {
            return;
        }
        JDJSONArray jDJSONArray = new JDJSONArray();
        for (int i2 = 0; i2 < this.giftLayerInfos.size(); i2++) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            String str = this.giftLayerInfos.get(i2).skuType;
            if (!TextUtils.isEmpty(str)) {
                jDJSONObject.put("sku_type", (Object) str);
            }
            jDJSONArray.add(jDJSONObject);
        }
        this.mainImagePresenter.mtaExposure("Productdetail_ZPSpecExpo", jDJSONArray.toJSONString());
    }

    public void setOnGiftLayerClickListener(OnGiftLayerClickListener onGiftLayerClickListener) {
        this.onGiftLayerClickListener = onGiftLayerClickListener;
    }

    public PdMImageGiftLayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
