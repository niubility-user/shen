package com.jd.lib.productdetail.mainimage.holder.iconsnew;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicPicIcons;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessServiceIconEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessServiceIconTrustEntity;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDCalorieImageUtil;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.views.CustomTypefaceSpan;
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
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMIconNewViewHolder extends PdMainImageBaseHolder {
    public SimpleDraweeView B;
    public TextView C;
    public PdMIconNewViewHolderListItem[] D;
    public PdMIconNewViewHolderListItem[] E;
    public View F;
    public View G;
    public ViewGroup H;
    public SimpleDraweeView I;
    public AppCompatTextView J;
    public TextView K;
    public View L;
    public RecyclerView M;
    public PdMImageRcServiceIconAdapterB N;
    public WareBusinessServiceIconTrustEntity O;
    public PdMImageRcServiceIconAdapterB.d P;

    /* loaded from: classes15.dex */
    public class a extends JDSimpleImageLoadingListener {
        public a() {
        }

        @Override // com.jingdong.app.util.image.listener.JDSimpleImageLoadingListener, com.jingdong.app.util.image.listener.JDImageLoadingListener
        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            super.onLoadingComplete(str, view, bitmap);
            SimpleDraweeView simpleDraweeView = PdMIconNewViewHolder.this.B;
            if (simpleDraweeView == null || ViewTreeLifecycleOwner.get(simpleDraweeView) == null || ViewTreeLifecycleOwner.get(PdMIconNewViewHolder.this.B).getLifecycle() == null || !ViewTreeLifecycleOwner.get(PdMIconNewViewHolder.this.B).getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED) || bitmap == null || bitmap.isRecycled()) {
                return;
            }
            float width = bitmap.getWidth();
            float height = bitmap.getHeight();
            if (width == 0.0f || height == 0.0f) {
                return;
            }
            float f2 = width / height;
            float dip2px = PDUtils.dip2px(20.0f);
            PdMIconNewViewHolder.this.B.getLayoutParams().width = (int) (f2 * dip2px);
            PdMIconNewViewHolder.this.B.getLayoutParams().height = (int) dip2px;
            PdMIconNewViewHolder.this.B.requestLayout();
        }
    }

    /* loaded from: classes15.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            PdMIconNewViewHolder.this.s();
        }
    }

    /* loaded from: classes15.dex */
    public class c implements PdMImageRcServiceIconAdapterB.d {
        public c() {
        }

        @Override // com.jd.lib.productdetail.mainimage.old.PdMImageRcServiceIconAdapterB.d
        public void a(WareBusinessServiceIconEntity wareBusinessServiceIconEntity) {
            if (wareBusinessServiceIconEntity != null) {
                PDBaseDeepLinkHelper.gotoMWithUrl(PdMIconNewViewHolder.this.f4649i, "", wareBusinessServiceIconEntity.jumpUrl);
            }
        }
    }

    public PdMIconNewViewHolder(@NonNull View view, View view2) {
        super(view, view2);
        this.P = new c();
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public boolean A() {
        List<WareBusinessServiceIconEntity> list;
        PdMainImagePresenter pdMainImagePresenter = this.f4654n;
        if (pdMainImagePresenter != null && pdMainImagePresenter.imageFromType == PdImageFromType.PRODUCTDETAIL_MINI) {
            pdMainImagePresenter.toDetailPage.setValue(Boolean.TRUE);
            return true;
        }
        WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity = this.O;
        if (wareBusinessServiceIconTrustEntity != null && (list = wareBusinessServiceIconTrustEntity.iconList) != null && list.size() > 0) {
            View H = H();
            this.L = H;
            e(H, null, true, false);
            G("icon");
        }
        return true;
    }

    public void G(String str) {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity2;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        JDJSONObject jDJSONObject = new JDJSONObject();
        jDJSONObject.put("type", (Object) str);
        jDJSONObject.put("LableNum", (Object) "");
        jDJSONObject.put("QuesNum", (Object) "");
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        if (wareBusinessMagicHeadPicInfoEntity != null && (wareBuinessUnitMainImageBizDataEntity2 = wareBusinessMagicHeadPicInfoEntity.bizData) != null && wareBuinessUnitMainImageBizDataEntity2.serviceBizData != null) {
            jDJSONObject.put("frame_info", (Object) ("" + this.r.bizData.serviceBizData.ztfwTemplateType));
            jDJSONObject.put("touchstone_expids", (Object) this.r.bizData.serviceBizData.ztfwTimeExpPoint);
        }
        jDJSONObject.put("isPhoto", (Object) "0");
        jDJSONObject.put("rankid", (Object) "");
        jDJSONObject.put("click_pos", (Object) "-100");
        this.f4654n.mtaClick("Productdetail_FunctionEntrance", jDJSONObject.toJSONString());
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity2 = this.r;
        if (wareBusinessMagicHeadPicInfoEntity2 == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity2.bizData) == null || wareBuinessUnitMainImageBizDataEntity.serviceBizData == null) {
            return;
        }
        JDJSONObject jDJSONObject2 = new JDJSONObject();
        jDJSONObject2.put("frame_info", (Object) ("" + this.r.bizData.serviceBizData.ztfwTemplateType));
        this.f4654n.mtaExposure("Productdetail_MainPhotoToastExpo", jDJSONObject2.toJSONString());
    }

    public View H() {
        View inflate = ImageUtil.inflate(this.f4649i, R.layout.lib_pd_mainimage_service_layer_b, (ViewGroup) null);
        boolean z = this.f4654n.getMainImageParams().isDark;
        inflate.setBackgroundResource(PDUtils.getColorWithTheme(this.f4654n.getMainImageParams().isDark, R.drawable.lib_pd_common_dialog_bg, R.drawable.lib_pd_common_dialog_bg_dark));
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.rl_title);
        this.K = (TextView) inflate.findViewById(R.id.tv_title_service);
        WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity = this.O;
        if (wareBusinessServiceIconTrustEntity != null && !TextUtils.isEmpty(wareBusinessServiceIconTrustEntity.title)) {
            this.K.setText(this.O.title);
        } else {
            this.K.setText(this.f4649i.getString(R.string.lib_pd_image_selection));
        }
        this.M = (RecyclerView) inflate.findViewById(R.id.lib_pd_services_list);
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
            this.K.setTextColor(ThemeTitleHelper.getTitleTextColor(this.f4649i, "DETAILPOPLAYER", z));
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
            this.K.setTextColor(ContextCompat.getColor(this.f4649i, z ? R.color.lib_pd_image_color_ececec : R.color.lib_pd_image_black_262626));
            imageView.setImageResource(com.jingdong.common.R.drawable.common_dialog_close);
        }
        imageView.setOnClickListener(new b());
        this.M.setLayoutManager(new LinearLayoutManager(this.f4649i));
        this.M.setVerticalScrollBarEnabled(false);
        this.N = new PdMImageRcServiceIconAdapterB(this.f4649i, this.f4654n.getMainImageParams().isDark, this.f4654n.getMainImageParams().isElder);
        if (PDUtils.getImageInfoEntity() && titleBg != null) {
            this.M.setAdapter(new PdMImageHeaderAdapter(this.f4649i, this.N));
        } else {
            this.M.setAdapter(this.N);
        }
        PdMImageRcServiceIconAdapterB pdMImageRcServiceIconAdapterB = this.N;
        pdMImageRcServiceIconAdapterB.d = this.P;
        pdMImageRcServiceIconAdapterB.b = this.O.iconList;
        pdMImageRcServiceIconAdapterB.f5052g = false;
        pdMImageRcServiceIconAdapterB.notifyDataSetChanged();
        return inflate;
    }

    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void d(View view) {
        this.B = (SimpleDraweeView) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_new_content_title);
        this.C = (TextView) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_new_content_subtitle);
        this.F = view.findViewById(R.id.lib_pd_holder_topimage_item_icon_new_content_black);
        this.G = view.findViewById(R.id.lib_pd_holder_topimage_item_icon_new_content_black_bg);
        this.H = (ViewGroup) view.findViewById(R.id.lib_pd_new_style_container);
        this.J = (AppCompatTextView) view.findViewById(R.id.lib_pd_new_style_text);
        this.I = (SimpleDraweeView) view.findViewById(R.id.lib_pd_new_style_icon);
        this.D = new PdMIconNewViewHolderListItem[]{(PdMIconNewViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_black_1), (PdMIconNewViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_black_2)};
        this.E = new PdMIconNewViewHolderListItem[]{(PdMIconNewViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_1), (PdMIconNewViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_2), (PdMIconNewViewHolderListItem) view.findViewById(R.id.lib_pd_holder_topimage_item_icon_list_item_3)};
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jd.lib.productdetail.mainimage.holder.PdMainImageBaseHolder
    public void n() {
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareBuinessUnitMainImageBizDataEntity.PdServiceBizData pdServiceBizData;
        TextView textView;
        WareBusinessMagicHeadPicInfoEntity.NewStyleText newStyleText;
        List<WareBusinessServiceIconEntity> list;
        boolean z;
        WareBusinessMagicHeadPicInfoEntity wareBusinessMagicHeadPicInfoEntity = this.r;
        if (wareBusinessMagicHeadPicInfoEntity == null || (wareBuinessUnitMainImageBizDataEntity = wareBusinessMagicHeadPicInfoEntity.bizData) == null || (pdServiceBizData = wareBuinessUnitMainImageBizDataEntity.serviceBizData) == null) {
            return;
        }
        WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity = pdServiceBizData.trustworthy;
        this.O = wareBusinessServiceIconTrustEntity;
        boolean z2 = true;
        if (wareBusinessServiceIconTrustEntity != null && (list = wareBusinessServiceIconTrustEntity.iconList) != null && list.size() > 0) {
            Iterator<WareBusinessServiceIconEntity> it = this.O.iconList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (it.next().serviceType == 2) {
                    z = true;
                    break;
                }
            }
            if (!z && !TextUtils.isEmpty(this.O.iconInDialog) && !TextUtils.isEmpty(this.O.guideJumpUrl) && !TextUtils.isEmpty(this.O.iconUrl)) {
                WareBusinessServiceIconEntity wareBusinessServiceIconEntity = new WareBusinessServiceIconEntity();
                wareBusinessServiceIconEntity.isTrustItem = true;
                WareBusinessServiceIconTrustEntity wareBusinessServiceIconTrustEntity2 = this.O;
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
        if (this.F != null) {
            for (PdMIconNewViewHolderListItem pdMIconNewViewHolderListItem : this.D) {
                pdMIconNewViewHolderListItem.setVisibility(8);
            }
            for (PdMIconNewViewHolderListItem pdMIconNewViewHolderListItem2 : this.E) {
                pdMIconNewViewHolderListItem2.setVisibility(8);
            }
            this.F.setVisibility(8);
            this.C.setVisibility(8);
            this.H.setVisibility(8);
        }
        JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
        createSimple.useDefaultPlaceholder(false);
        createSimple.showImageOnLoading(17170445);
        PDCalorieImageUtil.get().imageOptions(createSimple).display("2734", this.G);
        JDDisplayImageOptions createSimple2 = JDDisplayImageOptions.createSimple();
        int i2 = R.drawable.lib_pd_mainimage_holder_item_icon_content_title_default;
        createSimple2.showImageOnFail(i2);
        createSimple2.showImageForEmptyUri(i2);
        JDImageUtils.displayImage(pdServiceBizData.title, this.B, createSimple2, new a());
        if (!TextUtils.isEmpty(pdServiceBizData.subTitle)) {
            this.C.setText(pdServiceBizData.subTitle);
            this.C.setVisibility(0);
        }
        boolean z3 = ((float) this.f4654n.appImageWidth) < PDUtils.getScaledDensity() * 360.0f;
        ArrayList arrayList = new ArrayList();
        List<WareBusinessMagicPicIcons> list2 = pdServiceBizData.icons;
        if (list2 == null || list2.size() < 2) {
            return;
        }
        this.F.setVisibility(0);
        if (pdServiceBizData.hitFWNewStyle && (newStyleText = pdServiceBizData.newStyleText) != null && !TextUtils.isEmpty(newStyleText.allText)) {
            arrayList.add(this.D[0]);
            arrayList.add(this.D[1]);
            if (!z3 && pdServiceBizData.newStyleText != null) {
                this.H.setVisibility(0);
                if (TextUtils.isEmpty(pdServiceBizData.newStyleIcon)) {
                    this.I.setVisibility(8);
                } else {
                    JDImageUtils.displayImage(pdServiceBizData.newStyleIcon, this.I);
                    this.I.setVisibility(0);
                }
                AppCompatTextView appCompatTextView = this.J;
                WareBusinessMagicHeadPicInfoEntity.NewStyleText newStyleText2 = pdServiceBizData.newStyleText;
                String str2 = "";
                if (newStyleText2 != null && !TextUtils.isEmpty(newStyleText2.allText)) {
                    SpannableString spannableString = new SpannableString(newStyleText2.allText);
                    if (!TextUtils.isEmpty(newStyleText2.specialText)) {
                        try {
                            int indexOf = newStyleText2.allText.indexOf(newStyleText2.specialText);
                            spannableString.setSpan(new CustomTypefaceSpan("", FontsUtil.getTypeFace(this.f4649i, 4097)), indexOf, newStyleText2.specialText.length() + indexOf, 17);
                        } catch (Exception e2) {
                            ExceptionReporter.reportExceptionToBugly(e2);
                        }
                    }
                    str2 = spannableString;
                }
                appCompatTextView.setText(str2);
                this.J.setMaxWidth(this.f4654n.appImageWidth - PDUtils.dip2px(75.0f));
            }
        } else if (list2.size() == 2) {
            if (!z3) {
                arrayList.add(this.D[0]);
                arrayList.add(this.E[0]);
                this.E[0].setGravity(17);
                z2 = false;
            } else {
                arrayList.add(this.D[0]);
                arrayList.add(this.D[1]);
            }
        } else if (list2.size() == 3) {
            if (!z3) {
                arrayList.add(this.D[0]);
                arrayList.add(this.E[0]);
                arrayList.add(this.E[1]);
                this.E[0].setGravity(17);
                this.E[1].setGravity(17);
                z2 = false;
            } else {
                arrayList.add(this.D[0]);
                arrayList.add(this.D[1]);
            }
        } else if (list2.size() == 4) {
            arrayList.add(this.D[0]);
            arrayList.add(this.D[1]);
            if (!z3) {
                arrayList.add(this.E[0]);
                arrayList.add(this.E[1]);
                this.E[0].setGravity(17);
                this.E[1].setGravity(17);
            }
        } else {
            if (list2.size() >= 5) {
                arrayList.add(this.D[0]);
                arrayList.add(this.D[1]);
                if (!z3) {
                    arrayList.add(this.E[0]);
                    arrayList.add(this.E[1]);
                    arrayList.add(this.E[2]);
                    this.E[0].setGravity(19);
                    this.E[1].setGravity(17);
                    this.E[2].setGravity(21);
                }
            }
            z2 = false;
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            PdMIconNewViewHolderListItem pdMIconNewViewHolderListItem3 = (PdMIconNewViewHolderListItem) arrayList.get(i3);
            if (list2.size() > i3) {
                pdMIconNewViewHolderListItem3.setVisibility(0);
                WareBusinessMagicPicIcons wareBusinessMagicPicIcons = list2.get(i3);
                if (wareBusinessMagicPicIcons != null && (textView = pdMIconNewViewHolderListItem3.b) != null) {
                    textView.setText(wareBusinessMagicPicIcons.name);
                    if (pdMIconNewViewHolderListItem3.d != null && pdMIconNewViewHolderListItem3.f4894c != null) {
                        if (!TextUtils.isEmpty(wareBusinessMagicPicIcons.mImgUrl2)) {
                            pdMIconNewViewHolderListItem3.a.setVisibility(0);
                            JDDisplayImageOptions createSimple3 = JDDisplayImageOptions.createSimple();
                            createSimple3.useDefaultPlaceholder(false);
                            createSimple3.showImageOnLoading(17170445);
                            JDImageUtils.displayImage(wareBusinessMagicPicIcons.mImgUrl2, pdMIconNewViewHolderListItem3.a, createSimple3);
                        } else {
                            pdMIconNewViewHolderListItem3.a.setVisibility(8);
                        }
                        if (!TextUtils.isEmpty(wareBusinessMagicPicIcons.subName)) {
                            pdMIconNewViewHolderListItem3.f4894c.setText(wareBusinessMagicPicIcons.subName);
                            pdMIconNewViewHolderListItem3.f4894c.setVisibility(0);
                        } else {
                            pdMIconNewViewHolderListItem3.f4894c.setVisibility(8);
                        }
                        if (z2) {
                            if (!TextUtils.isEmpty(wareBusinessMagicPicIcons.shortDesc)) {
                                pdMIconNewViewHolderListItem3.d.setText(wareBusinessMagicPicIcons.shortDesc);
                                pdMIconNewViewHolderListItem3.d.setVisibility(0);
                            } else {
                                pdMIconNewViewHolderListItem3.d.setVisibility(8);
                            }
                        } else if (!TextUtils.isEmpty(wareBusinessMagicPicIcons.desc)) {
                            pdMIconNewViewHolderListItem3.d.setText(wareBusinessMagicPicIcons.desc);
                            pdMIconNewViewHolderListItem3.d.setVisibility(0);
                        } else {
                            pdMIconNewViewHolderListItem3.d.setVisibility(8);
                        }
                    } else if (!TextUtils.isEmpty(wareBusinessMagicPicIcons.mImgUrl)) {
                        pdMIconNewViewHolderListItem3.a.setVisibility(0);
                        JDDisplayImageOptions createSimple4 = JDDisplayImageOptions.createSimple();
                        createSimple4.useDefaultPlaceholder(false);
                        createSimple4.showImageOnLoading(17170445);
                        JDImageUtils.displayImage(wareBusinessMagicPicIcons.mImgUrl, pdMIconNewViewHolderListItem3.a, createSimple4);
                    } else {
                        pdMIconNewViewHolderListItem3.a.setVisibility(8);
                    }
                }
            } else {
                pdMIconNewViewHolderListItem3.setVisibility(8);
            }
        }
    }
}
