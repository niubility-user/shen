package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMDpgLayerItemRecyclerView;
import com.jd.lib.productdetail.mainimage.old.PdMDpgStyleInfoView;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.common.unification.dialog.UnBottomDialog;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMDpgLayerItemRecyclerView extends RecyclerView {

    /* renamed from: g  reason: collision with root package name */
    public PdDpgLayerAdapter f5005g;

    /* loaded from: classes15.dex */
    public static class PdDpgLayerAdapter extends RecyclerView.Adapter<PdDpgLayerItemHolder> {
        public int a = -1;
        public PdDpgLayerItemHolder b;

        /* renamed from: c  reason: collision with root package name */
        public a f5006c;
        public PdDpgListLayerInfo.DetailBean.ItemInfoAndItem d;

        /* renamed from: e  reason: collision with root package name */
        public PdMainImagePresenter f5007e;

        /* loaded from: classes15.dex */
        public class PdDpgLayerItemHolder extends RecyclerView.ViewHolder {
            public SimpleDraweeView a;
            public AppCompatTextView b;

            /* renamed from: c  reason: collision with root package name */
            public RelativeLayout f5008c;
            public AppCompatTextView d;

            /* renamed from: e  reason: collision with root package name */
            public AppCompatTextView f5009e;

            /* renamed from: f  reason: collision with root package name */
            public RelativeLayout f5010f;

            /* renamed from: g  reason: collision with root package name */
            public SimpleDraweeView f5011g;

            /* loaded from: classes15.dex */
            public class a implements DialogInterface.OnDismissListener {

                /* renamed from: g  reason: collision with root package name */
                public final /* synthetic */ PdMDpgStyleInfoView f5013g;

                public a(PdDpgLayerItemHolder pdDpgLayerItemHolder, PdMDpgStyleInfoView pdMDpgStyleInfoView) {
                    this.f5013g = pdMDpgStyleInfoView;
                }

                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    PdMDpgStyleInfoView pdMDpgStyleInfoView = this.f5013g;
                    if (pdMDpgStyleInfoView != null) {
                        pdMDpgStyleInfoView.getClass();
                    }
                }
            }

            public PdDpgLayerItemHolder(@NonNull View view) {
                super(view);
                this.a = (SimpleDraweeView) view.findViewById(R.id.pd_dpg_layer_item_product_image);
                this.b = (AppCompatTextView) view.findViewById(R.id.pd_dpg_layer_item_product_title);
                this.f5008c = (RelativeLayout) view.findViewById(R.id.pd_dpg_layer_item_below_img_rl);
                this.d = (AppCompatTextView) view.findViewById(R.id.pd_dpg_layer_item_product_color_size);
                this.f5009e = (AppCompatTextView) view.findViewById(R.id.pd_dpg_layer_item_product_price);
                this.f5010f = (RelativeLayout) view.findViewById(R.id.pd_no_stock_layout);
                this.f5011g = (SimpleDraweeView) view.findViewById(R.id.pd_dpg_layer_item_product_right_choice_image);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void b(int i2, PdDpgLayerItemHolder pdDpgLayerItemHolder, Context context, PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean, View view) {
                int i3;
                if (PDUtils.repeatClick()) {
                    PdDpgLayerAdapter pdDpgLayerAdapter = PdDpgLayerAdapter.this;
                    pdDpgLayerAdapter.a = i2;
                    pdDpgLayerAdapter.b = pdDpgLayerItemHolder;
                    c(context, itemsBean);
                    String str = itemsBean.skuId;
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) str);
                    PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem = PdDpgLayerAdapter.this.d;
                    if (itemInfoAndItem != null && (i3 = itemInfoAndItem.type) > 0) {
                        jDJSONObject.put("type", (Object) Integer.valueOf(i3));
                    } else {
                        jDJSONObject.put("type", (Object) "-100");
                    }
                    PdDpgLayerAdapter.this.f5007e.mtaClick("Productdetail_DapeiBuySpecification", jDJSONObject.toJSONString());
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void d(Context context, PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean, View view) {
                if (PDUtils.repeatClick()) {
                    j0.b(context, Long.valueOf(Long.parseLong(itemsBean.skuId)), null, null);
                    h(itemsBean.skuId);
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void f(PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean, Context context, int i2, PdDpgLayerItemHolder pdDpgLayerItemHolder, View view) {
                int i3;
                if (PDUtils.repeatClick()) {
                    PdDpgLayerAdapter pdDpgLayerAdapter = PdDpgLayerAdapter.this;
                    PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem = pdDpgLayerAdapter.d;
                    if (itemInfoAndItem != null && itemInfoAndItem.type != 2) {
                        pdDpgLayerAdapter.a = i2;
                        pdDpgLayerAdapter.b = pdDpgLayerItemHolder;
                        if (itemsBean.isValid) {
                            if (itemsBean.isSelectedProductCheckBoxNative) {
                                pdDpgLayerAdapter.f5006c.a(itemInfoAndItem, itemsBean, false, true);
                            } else if (itemsBean.isSelectedTextNative) {
                                pdDpgLayerAdapter.f5006c.a(itemInfoAndItem, itemsBean, true, true);
                            } else {
                                c(context, itemsBean);
                            }
                        }
                        String str = itemsBean.skuId;
                        JDJSONObject jDJSONObject = new JDJSONObject();
                        jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) str);
                        PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem2 = PdDpgLayerAdapter.this.d;
                        if (itemInfoAndItem2 != null && (i3 = itemInfoAndItem2.type) > 0) {
                            jDJSONObject.put("type", (Object) Integer.valueOf(i3));
                        } else {
                            jDJSONObject.put("type", (Object) "-100");
                        }
                        PdDpgLayerAdapter.this.f5007e.mtaClick("Productdetail_DapeiBuyChoose", jDJSONObject.toJSONString());
                    } else if (itemsBean.isValid && itemsBean.isSelectedProductCheckBoxNative) {
                        PDUtils.showToastCenterNormal(context, context.getString(R.string.lib_pd_image_only_buy_dpg));
                    }
                }
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void g(PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean, UnBottomDialog unBottomDialog, PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean2) {
                PdDpgLayerAdapter pdDpgLayerAdapter;
                PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem;
                if (itemsBean2 != null && (itemInfoAndItem = (pdDpgLayerAdapter = PdDpgLayerAdapter.this).d) != null && itemInfoAndItem.type != 2) {
                    itemsBean.isValid = itemsBean2.isValid;
                    itemsBean.skuId = itemsBean2.skuId;
                    itemsBean.itemUrl = itemsBean2.itemUrl;
                    itemsBean.itemName = itemsBean2.itemName;
                    itemsBean.price = itemsBean2.price;
                    itemsBean.color = itemsBean2.color;
                    itemsBean.size = itemsBean2.size;
                    itemsBean.isSelectedTextNative = true;
                    itemsBean.isSelectedProductCheckBoxNative = true;
                    pdDpgLayerAdapter.f5006c.a(itemInfoAndItem, itemsBean, true, false);
                }
                if (unBottomDialog == null || !unBottomDialog.isShowing()) {
                    return;
                }
                unBottomDialog.dismiss();
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void i(Context context, PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean, View view) {
                if (PDUtils.repeatClick()) {
                    j0.b(context, Long.valueOf(Long.parseLong(itemsBean.skuId)), null, null);
                    h(itemsBean.skuId);
                }
            }

            public final void c(Context context, final PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean) {
                String str;
                Context context2;
                int i2;
                Context context3;
                int i3;
                Context context4;
                int i4;
                MutableLiveData<WareBusinessUnitMainImageEntity> mutableLiveData;
                PdMDpgStyleInfoView pdMDpgStyleInfoView = (PdMDpgStyleInfoView) LayoutInflater.from(context).inflate(R.layout.lib_pd_mainimage_dpg_style_info, (ViewGroup) null);
                PdDpgLayerAdapter pdDpgLayerAdapter = PdDpgLayerAdapter.this;
                PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem = pdDpgLayerAdapter.d;
                if (itemInfoAndItem != null && itemInfoAndItem.type == 2) {
                    int i5 = PdMDpgStyleInfoView.H;
                    str = "productDetail";
                } else {
                    int i6 = PdMDpgStyleInfoView.H;
                    str = "matchbuy";
                }
                pdMDpgStyleInfoView.g(pdDpgLayerAdapter.f5007e);
                pdMDpgStyleInfoView.w = itemsBean.skuId;
                pdMDpgStyleInfoView.x = pdMDpgStyleInfoView.v.getMainImageParams().skuId;
                PdMainImagePresenter pdMainImagePresenter = pdMDpgStyleInfoView.v;
                if (pdMainImagePresenter != null && (mutableLiveData = pdMainImagePresenter.mainImageData) != null && mutableLiveData.getValue() != null && pdMDpgStyleInfoView.v.mainImageData.getValue().extMap != null) {
                    pdMDpgStyleInfoView.G = pdMDpgStyleInfoView.v.mainImageData.getValue().extMap.storeId;
                }
                pdMDpgStyleInfoView.y = str;
                pdMDpgStyleInfoView.f5024h.setBackgroundResource(pdMDpgStyleInfoView.v.getMainImageParams().isDark ? R.drawable.lib_pd_common_dialog_bg_dark : R.drawable.lib_pd_common_dialog_bg);
                pdMDpgStyleInfoView.f5025i.setBackgroundResource(pdMDpgStyleInfoView.v.getMainImageParams().isDark ? R.drawable.lib_pd_common_dialog_bg_dark : R.drawable.lib_pd_common_dialog_bg);
                TextView textView = pdMDpgStyleInfoView.f5028l;
                if (pdMDpgStyleInfoView.v.getMainImageParams().isDark) {
                    context2 = pdMDpgStyleInfoView.f5023g;
                    i2 = R.color.lib_pd_image_848383;
                } else {
                    context2 = pdMDpgStyleInfoView.f5023g;
                    i2 = R.color.lib_pd_image_gray;
                }
                textView.setTextColor(ContextCompat.getColor(context2, i2));
                LinearLayout linearLayout = pdMDpgStyleInfoView.f5029m;
                if (pdMDpgStyleInfoView.v.getMainImageParams().isDark) {
                    context3 = pdMDpgStyleInfoView.f5023g;
                    i3 = R.color.lib_pd_image_color_1d1b1b;
                } else {
                    context3 = pdMDpgStyleInfoView.f5023g;
                    i3 = R.color.lib_pd_image_white;
                }
                linearLayout.setBackgroundColor(ContextCompat.getColor(context3, i3));
                ScrollView scrollView = pdMDpgStyleInfoView.f5030n;
                if (pdMDpgStyleInfoView.v.getMainImageParams().isDark) {
                    context4 = pdMDpgStyleInfoView.f5023g;
                    i4 = R.color.lib_pd_image_color_1d1b1b;
                } else {
                    context4 = pdMDpgStyleInfoView.f5023g;
                    i4 = R.color.lib_pd_image_white;
                }
                scrollView.setBackgroundColor(ContextCompat.getColor(context4, i4));
                pdMDpgStyleInfoView.s.setImageResource(pdMDpgStyleInfoView.v.getMainImageParams().isDark ? R.drawable.lib_pd_mainimage_topimage_gift_layer_close_dark : R.drawable.lib_pd_mainimage_big_plus_layer_btn_close);
                pdMDpgStyleInfoView.setBackgroundResource(pdMDpgStyleInfoView.v.getMainImageParams().isDark ? R.drawable.lib_pd_mainimage_parts_recommend_dark_bg : R.drawable.lib_pd_mainimage_parts_recommend_bg);
                pdMDpgStyleInfoView.e(true);
                final UnBottomDialog unBottomDialog = new UnBottomDialog(context);
                unBottomDialog.setDarkMode(PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark);
                unBottomDialog.setOnDismissListener(new a(this, pdMDpgStyleInfoView));
                pdMDpgStyleInfoView.h(new PdMDpgStyleInfoView.c() { // from class: com.jd.lib.productdetail.mainimage.old.l
                    @Override // com.jd.lib.productdetail.mainimage.old.PdMDpgStyleInfoView.c
                    public final void a(PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean2) {
                        PdMDpgLayerItemRecyclerView.PdDpgLayerAdapter.PdDpgLayerItemHolder.this.g(itemsBean, unBottomDialog, itemsBean2);
                    }
                });
                unBottomDialog.addContentWithHeight((View) pdMDpgStyleInfoView, (String) null, false);
                unBottomDialog.show();
            }

            public void e(final PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean, final int i2, final Context context, final PdDpgLayerItemHolder pdDpgLayerItemHolder) {
                Double valueOf;
                JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                float dip2px = PDUtils.dip2px(6.0f);
                createSimple.displayer(new JDRoundedBitmapDisplayer(dip2px, dip2px, 0.0f, 0.0f));
                JDImageUtils.displayImage(itemsBean.itemUrl, this.a, createSimple);
                this.a.setTag(itemsBean.itemUrl);
                if (this.itemView.getLayoutParams() instanceof RecyclerView.LayoutParams) {
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.itemView.getLayoutParams();
                    if (i2 == 0) {
                        layoutParams.setMargins(0, 0, 0, 0);
                    } else {
                        layoutParams.setMargins(PDUtils.dip2px(8.0f), 0, 0, 0);
                    }
                }
                String str = "";
                if (!TextUtils.isEmpty(itemsBean.itemName)) {
                    this.b.setText(itemsBean.itemName);
                } else {
                    this.b.setText("");
                }
                if (itemsBean.isValid) {
                    if (itemsBean.isSelectedProductCheckBoxNative) {
                        this.f5011g.setImageResource(R.drawable.lib_pd_mainimage_checkbox_select);
                        this.itemView.setBackgroundResource(PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark ? R.drawable.lib_pd_mainimage_color_ff3826_corner_6 : R.drawable.lib_pd_mainimage_color_fa2c19_corner_6);
                    } else {
                        this.f5011g.setImageResource(R.drawable.lib_pd_mainimage_checkbox_null);
                        this.itemView.setBackgroundResource(PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark ? R.drawable.lib_pd_mainimage_color_555353_corner_6 : R.drawable.lib_pd_mainimage_color_cccccc_corner_6);
                    }
                    this.f5010f.setVisibility(8);
                    this.d.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(context, R.color.lib_pd_image_color_808080, PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark));
                    this.b.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(context, R.color.lib_pd_image_color_1A1A1A, PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark));
                } else {
                    this.itemView.setBackgroundResource(PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark ? R.drawable.lib_pd_mainimage_color_555353_corner_6 : R.drawable.lib_pd_mainimage_color_cccccc_corner_6);
                    this.f5010f.setVisibility(0);
                    this.f5011g.setImageResource(R.drawable.lib_pd_mainimage_dpg_layer_item_no_stock_right_icon);
                    if (PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark) {
                        this.b.setTextColor(ContextCompat.getColor(context, R.color.lib_pd_image_color_ececec));
                        this.d.setTextColor(ContextCompat.getColor(context, R.color.lib_pd_image_848383));
                    } else {
                        AppCompatTextView appCompatTextView = this.b;
                        int i3 = R.color.lib_pd_image_color_cccccc;
                        appCompatTextView.setTextColor(ContextCompat.getColor(context, i3));
                        this.d.setTextColor(ContextCompat.getColor(context, i3));
                    }
                }
                Drawable drawable = context.getResources().getDrawable(R.drawable.lib_pd_mainimage_whitebar_newuser_arrow_down);
                drawable.setBounds(0, 0, PDUtils.dip2px(6.0f), PDUtils.dip2px(6.0f));
                this.d.setCompoundDrawables(null, null, drawable, null);
                this.f5009e.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(context, R.color.lib_pd_image_color_FA2C19, PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark));
                this.d.setBackgroundResource(PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark ? R.drawable.lib_pd_mainimage_color_0a0909_corner_10 : R.drawable.lib_pd_mainimage_color_f2f2f2_corner_10);
                PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem = PdDpgLayerAdapter.this.d;
                if (itemInfoAndItem != null && itemInfoAndItem.type == 2) {
                    this.f5009e.setText("");
                } else {
                    try {
                        valueOf = Double.valueOf(itemsBean.price);
                    } catch (Exception unused) {
                        valueOf = Double.valueOf(0.0d);
                    }
                    if (valueOf.doubleValue() > 0.0d) {
                        this.f5009e.setText(PDUtils.getJPriceText(itemsBean.price, 0.67f));
                    } else {
                        this.f5009e.setText(context.getString(R.string.lib_pd_image_no_price));
                    }
                }
                FontsUtil.changeTextFont(this.f5009e, 4099);
                if (!TextUtils.isEmpty(itemsBean.color) && !TextUtils.isEmpty(itemsBean.size)) {
                    str = itemsBean.color + itemsBean.size;
                } else if (!TextUtils.isEmpty(itemsBean.color) && TextUtils.isEmpty(itemsBean.size)) {
                    str = itemsBean.color;
                } else if (TextUtils.isEmpty(itemsBean.color) && !TextUtils.isEmpty(itemsBean.size)) {
                    str = itemsBean.size;
                }
                if (itemsBean.isSelectedTextNative && itemsBean.isValid) {
                    if (!TextUtils.isEmpty(str)) {
                        this.d.setText(str);
                    } else {
                        this.d.setText(context.getString(R.string.lib_pd_image_default));
                    }
                } else {
                    this.d.setText(context.getString(R.string.lib_pd_image_choice_product_attr));
                }
                this.f5008c.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.old.n
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PdMDpgLayerItemRecyclerView.PdDpgLayerAdapter.PdDpgLayerItemHolder.this.b(i2, pdDpgLayerItemHolder, context, itemsBean, view);
                    }
                });
                this.f5011g.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.old.p
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PdMDpgLayerItemRecyclerView.PdDpgLayerAdapter.PdDpgLayerItemHolder.this.f(itemsBean, context, i2, pdDpgLayerItemHolder, view);
                    }
                });
                this.f5010f.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.old.m
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PdMDpgLayerItemRecyclerView.PdDpgLayerAdapter.PdDpgLayerItemHolder.this.d(context, itemsBean, view);
                    }
                });
                this.a.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.old.j
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PdMDpgLayerItemRecyclerView.PdDpgLayerAdapter.PdDpgLayerItemHolder.this.i(context, itemsBean, view);
                    }
                });
            }

            public final void h(String str) {
                JDJSONObject jDJSONObject = new JDJSONObject();
                jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) str);
                PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem = PdDpgLayerAdapter.this.d;
                if (itemInfoAndItem != null) {
                    int i2 = itemInfoAndItem.type;
                    if (i2 > 0) {
                        jDJSONObject.put("type", (Object) Integer.valueOf(i2));
                    } else {
                        jDJSONObject.put("type", (Object) "-100");
                    }
                    PdDpgListLayerInfo.DetailBean.InfoBean infoBean = PdDpgLayerAdapter.this.d.info;
                    if (infoBean != null && !TextUtils.isEmpty(infoBean.matchId)) {
                        jDJSONObject.put("matchid", (Object) PdDpgLayerAdapter.this.d.info.matchId);
                    } else {
                        jDJSONObject.put("matchid", (Object) "-100");
                    }
                }
                PdDpgLayerAdapter.this.f5007e.mtaClick("Productdetail_DapeiBuyProduct", jDJSONObject.toJSONString());
            }
        }

        @NonNull
        public PdDpgLayerItemHolder a(@NonNull ViewGroup viewGroup) {
            return new PdDpgLayerItemHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lib_pd_mainimage_dpg_layer_item_product_layout, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<PdDpgListLayerInfo.DetailBean.ItemsBean> list;
            PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem = this.d;
            if (itemInfoAndItem == null || (list = itemInfoAndItem.items) == null) {
                return 0;
            }
            return list.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull PdDpgLayerItemHolder pdDpgLayerItemHolder, int i2) {
            View view;
            List<PdDpgListLayerInfo.DetailBean.ItemsBean> list;
            PdDpgLayerItemHolder pdDpgLayerItemHolder2 = pdDpgLayerItemHolder;
            PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem = this.d;
            PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean = (itemInfoAndItem == null || (list = itemInfoAndItem.items) == null || i2 >= list.size()) ? null : this.d.items.get(i2);
            if (itemsBean == null || pdDpgLayerItemHolder2 == null || (view = pdDpgLayerItemHolder2.itemView) == null || view.getContext() == null) {
                return;
            }
            pdDpgLayerItemHolder2.e(itemsBean, i2, pdDpgLayerItemHolder2.itemView.getContext(), pdDpgLayerItemHolder2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public /* bridge */ /* synthetic */ PdDpgLayerItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            return a(viewGroup);
        }
    }

    /* loaded from: classes15.dex */
    public interface a {
        void a(PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem, PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean, boolean z, boolean z2);
    }

    public PdMDpgLayerItemRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public final void a() {
        PdDpgLayerAdapter pdDpgLayerAdapter = new PdDpgLayerAdapter();
        this.f5005g = pdDpgLayerAdapter;
        setAdapter(pdDpgLayerAdapter);
        setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
    }

    public void b(a aVar) {
        PdDpgLayerAdapter pdDpgLayerAdapter = this.f5005g;
        if (pdDpgLayerAdapter != null) {
            pdDpgLayerAdapter.f5006c = aVar;
        }
    }

    public void c(PdMainImagePresenter pdMainImagePresenter) {
        PdDpgLayerAdapter pdDpgLayerAdapter = this.f5005g;
        if (pdDpgLayerAdapter != null) {
            pdDpgLayerAdapter.f5007e = pdMainImagePresenter;
        }
    }

    public PdMDpgLayerItemRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }
}
