package com.jd.lib.productdetail.mainimage.holder.icons;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicPicIcons;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessServiceIconEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessServiceIconTrustEntity;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDCalorieImageUtil;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder;
import com.jd.lib.productdetail.mainimage.old.PdMImageHeaderAdapter;
import com.jd.lib.productdetail.mainimage.old.PdMImageRcServiceIconAdapterB;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jd.lib.productdetail.mainimage.view.PdImageFromType;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener;
import com.jingdong.common.unification.title.theme.ThemeTitleHelper;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMIconViewHolder extends PdMainImageBaseHolder {
    public SimpleDraweeView B;
    public SimpleDraweeView C;
    public TextView D;
    public ViewGroup E;
    public PdMIconViewHolderListItem[] F;
    public ViewGroup G;
    public PdMIconViewHolderListItem[] H;
    public TextView I;
    public View J;
    public RecyclerView K;
    public PdMImageRcServiceIconAdapterB L;
    public WareBusinessServiceIconTrustEntity M;
    public PdMImageRcServiceIconAdapterB.d N;

    /* loaded from: classes15.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            List<WareBusinessServiceIconEntity> list;
            PdMIconViewHolder pdMIconViewHolder = PdMIconViewHolder.this;
            PdMainImagePresenter pdMainImagePresenter = pdMIconViewHolder.f4654n;
            if (pdMainImagePresenter != null && pdMainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
                pdMainImagePresenter.toDetailPage.setValue(Boolean.TRUE);
                return;
            }
            WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity = pdMIconViewHolder.M;
            if (wareBusinessServiceIconTrustEntity == null || (list = wareBusinessServiceIconTrustEntity.iconList) == null || list.size() <= 0) {
                return;
            }
            PdMIconViewHolder pdMIconViewHolder2 = PdMIconViewHolder.this;
            pdMIconViewHolder2.J = pdMIconViewHolder2.H();
            PdMIconViewHolder pdMIconViewHolder3 = PdMIconViewHolder.this;
            pdMIconViewHolder3.e(pdMIconViewHolder3.J, pdMIconViewHolder3.f4649i.getString(R.string.lib_pd_image_ok), true, true);
        }
    }

    /* loaded from: classes15.dex */
    public class b extends JDSimpleImageLoadingListener {
        public b() {
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            super.onLoadingComplete(str, view, bitmap);
            SimpleDraweeView simpleDraweeView = PdMIconViewHolder.this.B;
            if (simpleDraweeView == null || ViewTreeLifecycleOwner.get(simpleDraweeView) == null || ViewTreeLifecycleOwner.get(PdMIconViewHolder.this.B).getLifecycle() == null || !ViewTreeLifecycleOwner.get(PdMIconViewHolder.this.B).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) || bitmap == null || bitmap.isRecycled()) {
                return;
            }
            float width = bitmap.getWidth();
            float height = bitmap.getHeight();
            if (width != 0.0f) {
                float f2 = width / height;
                float dip2px = PDUtils.dip2px(16.0f);
                PdMIconViewHolder.this.B.getLayoutParams().width = (int) (f2 * dip2px);
                PdMIconViewHolder.this.B.getLayoutParams().height = (int) dip2px;
                PdMIconViewHolder.this.B.requestLayout();
            }
        }
    }

    /* loaded from: classes15.dex */
    public class c implements ViewTreeObserver.OnGlobalLayoutListener {
        public c() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            PdMIconViewHolder pdMIconViewHolder = PdMIconViewHolder.this;
            ViewGroup viewGroup = pdMIconViewHolder.G;
            if (viewGroup != null) {
                if (!pdMIconViewHolder.f4651k && ViewTreeLifecycleOwner.get(viewGroup) != null && ViewTreeLifecycleOwner.get(PdMIconViewHolder.this.G).getLifecycle() != null && ViewTreeLifecycleOwner.get(PdMIconViewHolder.this.G).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                    int i2 = 0;
                    for (PdMIconViewHolderListItem pdMIconViewHolderListItem : PdMIconViewHolder.this.H) {
                        if (pdMIconViewHolderListItem.getVisibility() == 0) {
                            i2 += pdMIconViewHolderListItem.getHeight();
                        }
                    }
                    if (PdMIconViewHolder.this.G.getHeight() - i2 > PDUtils.dip2px(40.0f)) {
                        PdMIconViewHolder.this.G.setVisibility(0);
                    } else {
                        PdMIconViewHolder.this.E.setVisibility(0);
                    }
                }
                if (PdMIconViewHolder.this.G.getViewTreeObserver() != null) {
                    PdMIconViewHolder.this.G.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        }
    }

    /* loaded from: classes15.dex */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdMIconViewHolder.this.s();
        }
    }

    /* loaded from: classes15.dex */
    public class e implements PdMImageRcServiceIconAdapterB.d {
        public e() {
        }

        @Override // com.jd.lib.productdetail.mainimage.old.PdMImageRcServiceIconAdapterB.d
        public void a(WareBusinessServiceIconEntity wareBusinessServiceIconEntity) {
            if (wareBusinessServiceIconEntity != null) {
                PDBaseDeepLinkHelper.gotoMWithUrl(PdMIconViewHolder.this.f4649i, "", wareBusinessServiceIconEntity.jumpUrl);
            }
        }
    }

    public PdMIconViewHolder(@NonNull View view, View view2) {
        super(view, view2);
        this.N = new e();
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public boolean A() {
        PdMainImagePresenter pdMainImagePresenter = this.f4654n;
        if (pdMainImagePresenter != null && pdMainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
            pdMainImagePresenter.toDetailPage.setValue(Boolean.TRUE);
            return true;
        }
        if (pdMainImagePresenter != null && pdMainImagePresenter.imageFromType != PdImageFromType.PRODUCTDETAIL_MINI) {
            Context context = this.f4649i;
            PDUtils.showToastCenterNormal(context, context.getString(R.string.lib_pd_image_topimage_item_holder_icon_click_msg));
        }
        return true;
    }

    public final void G(PdMIconViewHolderListItem[] pdMIconViewHolderListItemArr, List<WareBusinessMagicPicIcons> list) {
        if (pdMIconViewHolderListItemArr == null || list == null) {
            return;
        }
        for (int i2 = 0; i2 < pdMIconViewHolderListItemArr.length; i2++) {
            PdMIconViewHolderListItem pdMIconViewHolderListItem = pdMIconViewHolderListItemArr[i2];
            if (list.size() > i2) {
                pdMIconViewHolderListItem.setVisibility(0);
                pdMIconViewHolderListItem.setupWithData(list.get(i2));
            } else {
                pdMIconViewHolderListItem.setVisibility(8);
            }
        }
    }

    public View H() {
        View inflate = ImageUtil.inflate(this.f4649i, R.layout.lib_pd_mainimage_service_layer_b, (ViewGroup) null);
        boolean z = this.f4654n.getMainImageParams().isDark;
        inflate.setBackgroundResource(PDUtils.getColorWithTheme(this.f4654n.getMainImageParams().isDark, R.drawable.lib_pd_common_dialog_bg, R.drawable.lib_pd_common_dialog_bg_dark));
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.rl_title);
        this.I = (TextView) inflate.findViewById(R.id.tv_title_service);
        WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity = this.M;
        if (wareBusinessServiceIconTrustEntity != null && !TextUtils.isEmpty(wareBusinessServiceIconTrustEntity.title)) {
            this.I.setText(this.M.title);
        } else {
            this.I.setText(this.f4649i.getString(R.string.lib_pd_image_selection));
        }
        this.K = (RecyclerView) inflate.findViewById(R.id.lib_pd_services_list);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.lib_pd_dialog_cancel);
        Drawable titleBg = ThemeTitleHelper.getTitleBg(this.f4649i, "DETAILPOPLAYER", z);
        RelativeLayout.LayoutParams layoutParams = relativeLayout.getLayoutParams() instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) relativeLayout.getLayoutParams() : null;
        if (PDUtils.getImageInfoEntity() && titleBg != null) {
            if (layoutParams != null) {
                layoutParams.height = PDUtils.dip2px(68.0f);
                relativeLayout.setLayoutParams(layoutParams);
            }
            if (Build.VERSION.SDK_INT >= 16) {
                relativeLayout.setBackground(titleBg);
            } else {
                relativeLayout.setBackgroundDrawable(titleBg);
            }
            this.I.setTextColor(ThemeTitleHelper.getTitleTextColor(this.f4649i, "DETAILPOPLAYER", z));
            imageView.setImageResource(com.jd.lib.productdetail.core.R.drawable.lib_pd_core_dialog_hf_close);
        } else {
            if (layoutParams != null) {
                layoutParams.height = PDUtils.dip2px(50.0f);
                relativeLayout.setLayoutParams(layoutParams);
            }
            if (Build.VERSION.SDK_INT >= 16) {
                relativeLayout.setBackground(null);
            } else {
                relativeLayout.setBackgroundDrawable(null);
            }
            this.I.setTextColor(ContextCompat.getColor(this.f4649i, z ? R.color.lib_pd_image_color_ececec : R.color.lib_pd_image_black_262626));
            imageView.setImageResource(com.jingdong.common.R.drawable.common_dialog_close);
        }
        imageView.setOnClickListener(new d());
        this.K.setLayoutManager(new LinearLayoutManager(this.f4649i));
        this.K.setVerticalScrollBarEnabled(false);
        this.L = new PdMImageRcServiceIconAdapterB(this.f4649i, this.f4654n.getMainImageParams().isDark, this.f4654n.getMainImageParams().isElder);
        if (PDUtils.getImageInfoEntity() && titleBg != null) {
            this.K.setAdapter(new PdMImageHeaderAdapter(this.f4649i, this.L));
        } else {
            this.K.setAdapter(this.L);
        }
        PdMImageRcServiceIconAdapterB pdMImageRcServiceIconAdapterB = this.L;
        pdMImageRcServiceIconAdapterB.d = this.N;
        pdMImageRcServiceIconAdapterB.b = this.M.iconList;
        pdMImageRcServiceIconAdapterB.f5052g = false;
        pdMImageRcServiceIconAdapterB.notifyDataSetChanged();
        return inflate;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        this.B = (SimpleDraweeView) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_content_title);
        this.D = (TextView) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_content_subtitle);
        this.C = (SimpleDraweeView) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_content_icon);
        this.G = (ViewGroup) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_big);
        this.H = new PdMIconViewHolderListItem[]{(PdMIconViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_big_1), (PdMIconViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_big_2), (PdMIconViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_big_3)};
        this.E = (ViewGroup) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_small);
        this.F = new PdMIconViewHolderListItem[]{(PdMIconViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_small_1), (PdMIconViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_small_2), (PdMIconViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_small_3), (PdMIconViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_small_4), (PdMIconViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_small_5), (PdMIconViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_small_6)};
        ((TextView) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_content_btn_ok)).setOnClickListener(new a());
        PDCalorieImageUtil.get().display("2661", this.C);
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdServiceBizData pdServiceBizData;
        List<WareBusinessMagicPicIcons> list;
        List<WareBusinessServiceIconEntity> list2;
        boolean z;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        if (wareBusinessMagicHeadPicInfoEntity == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || (pdServiceBizData = wareBuinessUnitMainImageBizDataEntity.serviceBizData) == null) {
            return;
        }
        WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity = pdServiceBizData.trustworthy;
        this.M = wareBusinessServiceIconTrustEntity;
        if (wareBusinessServiceIconTrustEntity != null && (list2 = wareBusinessServiceIconTrustEntity.iconList) != null && list2.size() > 0) {
            Iterator<WareBusinessServiceIconEntity> it = this.M.iconList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (it.next().serviceType == 2) {
                    z = true;
                    break;
                }
            }
            if (!z && !TextUtils.isEmpty(this.M.iconInDialog) && !TextUtils.isEmpty(this.M.guideJumpUrl) && !TextUtils.isEmpty(this.M.iconUrl)) {
                WareBusinessServiceIconEntity wareBusinessServiceIconEntity = new WareBusinessServiceIconEntity();
                wareBusinessServiceIconEntity.isTrustItem = true;
                WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity2 = this.M;
                String str = wareBusinessServiceIconTrustEntity2.iconInDialog;
                wareBusinessServiceIconEntity.iconInDialog = str;
                wareBusinessServiceIconEntity.imageUrl = str;
                wareBusinessServiceIconEntity.jumpUrl = wareBusinessServiceIconTrustEntity2.guideJumpUrl;
                wareBusinessServiceIconEntity.guideText = wareBusinessServiceIconTrustEntity2.guideText;
                wareBusinessServiceIconEntity.currentType = "serviceTrust";
                wareBusinessServiceIconEntity.serviceType = 2;
                wareBusinessServiceIconTrustEntity2.iconList.add(0, wareBusinessServiceIconEntity);
            }
        }
        JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
        int i2 = R.drawable.lib_pd_mainimage_holder_item_icon_content_title_default;
        createSimple.showImageOnFail(i2);
        createSimple.showImageForEmptyUri(i2);
        JDImageUtils.displayImage(this.r.bizData.serviceBizData.title, this.B, createSimple, new b());
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity2 = this.r.bizData;
        if (wareBuinessUnitMainImageBizDataEntity2 != null && !TextUtils.isEmpty(wareBuinessUnitMainImageBizDataEntity2.serviceBizData.subTitle)) {
            this.D.setText(this.r.bizData.serviceBizData.subTitle);
            this.D.setVisibility(0);
        } else {
            this.D.setVisibility(8);
        }
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity3 = this.r.bizData;
        if (wareBuinessUnitMainImageBizDataEntity3 == null || (list = wareBuinessUnitMainImageBizDataEntity3.serviceBizData.icons) == null) {
            return;
        }
        if (list.size() < 3) {
            this.G.setVisibility(0);
            this.E.setVisibility(8);
            G(this.H, this.r.bizData.serviceBizData.icons);
        } else if (this.r.bizData.serviceBizData.icons.size() == 3) {
            this.E.setVisibility(4);
            this.G.setVisibility(4);
            G(this.H, this.r.bizData.serviceBizData.icons);
            G(this.F, this.r.bizData.serviceBizData.icons);
            this.G.getViewTreeObserver().addOnGlobalLayoutListener(new c());
        } else {
            this.E.setVisibility(0);
            this.G.setVisibility(8);
            G(this.F, this.r.bizData.serviceBizData.icons);
        }
    }
}
