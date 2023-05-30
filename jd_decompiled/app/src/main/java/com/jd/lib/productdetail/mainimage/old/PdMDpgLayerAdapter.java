package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.handmark.pulltorefresh.library.JDLoadingMoreLayout;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PdDpgListLayerInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.AddCartParamsEntity;
import com.jd.lib.productdetail.core.protocol.PdDpgLayerRecalculateProtocol;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.utils.PdAddShopCartCallback;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.bean.PdImageEventCode;
import com.jd.lib.productdetail.mainimage.bean.PdMImageEventEntity;
import com.jd.lib.productdetail.mainimage.old.PdMDpgLayerAdapter;
import com.jd.lib.productdetail.mainimage.old.PdMDpgLayerItemRecyclerView;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.controller.ShoppingBaseController;
import com.jingdong.common.entity.cart.CartPackSummary;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.entity.cart.methodEntity.CartAddUniformEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMDpgLayerAdapter extends RecyclerView.Adapter {
    public String a;
    public Context b;
    public List<PdDpgListLayerInfo.DetailBean.ItemsBean> d;

    /* renamed from: e */
    public List<PdDpgListLayerInfo.DetailBean.ItemsBean> f4994e;

    /* renamed from: f */
    public b f4995f;

    /* renamed from: h */
    public PdMainImagePresenter f4997h;

    /* renamed from: g */
    public int f4996g = 1;

    /* renamed from: i */
    public PdAddShopCartCallback f4998i = new a();

    /* renamed from: c */
    public List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> f4993c = new ArrayList();

    /* loaded from: classes15.dex */
    public static class FootHolder extends RecyclerView.ViewHolder {
        public JDLoadingMoreLayout a;

        public FootHolder(@NonNull View view) {
            super(view);
            JDLoadingMoreLayout jDLoadingMoreLayout = (JDLoadingMoreLayout) view;
            this.a = jDLoadingMoreLayout;
            jDLoadingMoreLayout.setLoadMsg(view.getContext().getResources().getString(R.string.lib_pd_image_loading));
        }
    }

    /* loaded from: classes15.dex */
    public class PdDpgLayerHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView a;
        public AppCompatTextView b;

        /* renamed from: c */
        public PdMDpgLayerItemRecyclerView f4999c;
        public AppCompatTextView d;

        /* renamed from: e */
        public AppCompatTextView f5000e;

        /* renamed from: f */
        public AppCompatTextView f5001f;

        /* renamed from: g */
        public AppCompatTextView f5002g;

        /* loaded from: classes15.dex */
        public class a extends RecyclerView.OnScrollListener {
            public final /* synthetic */ int a;
            public final /* synthetic */ PdDpgListLayerInfo.DetailBean.ItemInfoAndItem b;

            public a(int i2, PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem) {
                PdDpgLayerHolder.this = r1;
                this.a = i2;
                this.b = itemInfoAndItem;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                if (i2 == 0) {
                    try {
                        View view = PdDpgLayerHolder.this.itemView;
                        if (view != null && (view.getTag() instanceof Integer) && ((Integer) PdDpgLayerHolder.this.itemView.getTag()).intValue() == this.a) {
                            PdDpgLayerHolder.e(PdDpgLayerHolder.this, this.b);
                            PdDpgLayerHolder.f(PdDpgLayerHolder.this, this.b);
                        }
                    } catch (Exception e2) {
                        ExceptionReporter.reportExceptionToBugly(e2);
                    }
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PdDpgLayerHolder(@NonNull View view) {
            super(view);
            PdMDpgLayerAdapter.this = r1;
            this.a = (AppCompatTextView) view.findViewById(R.id.pd_dpg_layer_item_title);
            this.b = (AppCompatTextView) view.findViewById(R.id.pd_dpg_layer_item_desc);
            this.f4999c = (PdMDpgLayerItemRecyclerView) view.findViewById(R.id.pd_dpg_layer_item_product_rv);
            this.f5000e = (AppCompatTextView) view.findViewById(R.id.pd_dpg_layer_item_total_price);
            this.d = (AppCompatTextView) view.findViewById(R.id.pd_dpg_layer_item_total_number);
            this.f5001f = (AppCompatTextView) view.findViewById(R.id.pd_dpg_layer_item_discount_price);
            this.f5002g = (AppCompatTextView) view.findViewById(R.id.pd_dpg_layer_item_add_2_car);
        }

        public void b(int i2, PdDpgLayerHolder pdDpgLayerHolder, PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem, PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean, boolean z, boolean z2) {
            PdMDpgLayerView pdMDpgLayerView = PdMDpgLayerView.this;
            int i3 = PdMDpgLayerView.M;
            pdMDpgLayerView.getClass();
            ArrayList arrayList = new ArrayList();
            if (itemInfoAndItem == null || itemsBean == null) {
                return;
            }
            pdMDpgLayerView.f5021n = itemInfoAndItem;
            pdMDpgLayerView.o = itemsBean;
            pdMDpgLayerView.p = z;
            pdMDpgLayerView.q = pdDpgLayerHolder;
            pdMDpgLayerView.s = i2;
            pdMDpgLayerView.r = z2;
            List<PdDpgListLayerInfo.DetailBean.ItemsBean> list = itemInfoAndItem.items;
            if (list != null && list.size() > 0) {
                for (int i4 = 0; i4 < list.size(); i4++) {
                    PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean2 = list.get(i4);
                    if (TextUtils.equals(itemsBean2.skuId, itemsBean.skuId)) {
                        if (z) {
                            PdDpgLayerRecalculateProtocol.SkuInfoParam skuInfoParam = new PdDpgLayerRecalculateProtocol.SkuInfoParam();
                            skuInfoParam.num = 1;
                            skuInfoParam.skuId = itemsBean2.skuId;
                            arrayList.add(skuInfoParam);
                        }
                    } else if (itemsBean2.isSelectedProductCheckBoxNative) {
                        PdDpgLayerRecalculateProtocol.SkuInfoParam skuInfoParam2 = new PdDpgLayerRecalculateProtocol.SkuInfoParam();
                        skuInfoParam2.skuId = itemsBean2.skuId;
                        skuInfoParam2.num = 1;
                        arrayList.add(skuInfoParam2);
                    }
                }
            }
            if (arrayList.size() == 0) {
                PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem2 = pdMDpgLayerView.f5021n;
                PdDpgListLayerInfo.DetailBean.InfoBean infoBean = itemInfoAndItem2.info;
                if (infoBean != null) {
                    infoBean.nativeTotalPriceStr = "";
                    infoBean.nativeTotalNumber = 0;
                    infoBean.discount = "";
                }
                pdMDpgLayerView.o.isSelectedProductCheckBoxNative = false;
                pdDpgLayerHolder.c(itemInfoAndItem2, pdMDpgLayerView.s, pdDpgLayerHolder, true, z2);
                return;
            }
            PdDpgListLayerInfo.DetailBean.InfoBean infoBean2 = itemInfoAndItem.info;
            if (infoBean2 != null) {
                String str = infoBean2.matchId;
                y yVar = pdMDpgLayerView.F.mPdMDpgLayerContainer;
                BaseActivity baseActivity = (BaseActivity) pdMDpgLayerView.L;
                yVar.getClass();
                PdDpgLayerRecalculateProtocol pdDpgLayerRecalculateProtocol = new PdDpgLayerRecalculateProtocol();
                PdDpgLayerRecalculateProtocol.PdDpgLayerRecalculateRequestParams pdDpgLayerRecalculateRequestParams = new PdDpgLayerRecalculateProtocol.PdDpgLayerRecalculateRequestParams();
                pdDpgLayerRecalculateRequestParams.matchId = str;
                pdDpgLayerRecalculateRequestParams.skuInfoParamList = arrayList;
                pdDpgLayerRecalculateProtocol.mRequestParams = pdDpgLayerRecalculateRequestParams;
                pdDpgLayerRecalculateProtocol.request(baseActivity);
                pdDpgLayerRecalculateProtocol.mResult.observe(baseActivity, new com.jd.lib.productdetail.mainimage.old.b(yVar, pdDpgLayerRecalculateProtocol));
            }
        }

        public void d(PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem, View view) {
            if (itemInfoAndItem != null) {
                int i2 = 0;
                if (itemInfoAndItem.type == 2) {
                    PdMDpgLayerAdapter pdMDpgLayerAdapter = PdMDpgLayerAdapter.this;
                    pdMDpgLayerAdapter.getClass();
                    CartAddUniformEntity cartAddUniformEntity = new CartAddUniformEntity();
                    cartAddUniformEntity.myActivity = (IMyActivity) pdMDpgLayerAdapter.b;
                    ArrayList<CartPackSummary> arrayList = null;
                    cartAddUniformEntity.skuList = null;
                    cartAddUniformEntity.isNoResponse = true;
                    cartAddUniformEntity.isEffect = true;
                    cartAddUniformEntity.isNotify = true;
                    cartAddUniformEntity.businessName = AddCartParamsEntity.BusinessNameEnum.PD_PACK_SUCCESS.toString();
                    if (itemInfoAndItem.info != null) {
                        arrayList = new ArrayList<>();
                        CartPackSummary cartPackSummary = new CartPackSummary(itemInfoAndItem.info.packId, 1);
                        boolean equals = TextUtils.equals("2", itemInfoAndItem.info.business);
                        if (pdMDpgLayerAdapter.f4997h != null && equals) {
                            cartPackSummary.storeId = pdMDpgLayerAdapter.a;
                        }
                        List<PdDpgListLayerInfo.DetailBean.ItemsBean> list = itemInfoAndItem.items;
                        if (list != null && list.size() > 0) {
                            while (i2 < itemInfoAndItem.items.size()) {
                                PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean = itemInfoAndItem.items.get(i2);
                                if (itemsBean != null && itemsBean.isSelectedProductCheckBoxNative && itemsBean.isValid) {
                                    CartSkuSummary cartSkuSummary = new CartSkuSummary(itemsBean.skuId, 1);
                                    if (pdMDpgLayerAdapter.f4997h != null && equals) {
                                        cartPackSummary.storeId = pdMDpgLayerAdapter.a;
                                    }
                                    cartPackSummary.addSku(cartSkuSummary);
                                }
                                i2++;
                            }
                        }
                        arrayList.add(cartPackSummary);
                    }
                    cartAddUniformEntity.packList = arrayList;
                    cartAddUniformEntity.listener = new x(pdMDpgLayerAdapter);
                    ShoppingBaseController.addCartUniform(cartAddUniformEntity);
                    return;
                }
                PdMDpgLayerAdapter pdMDpgLayerAdapter2 = PdMDpgLayerAdapter.this;
                pdMDpgLayerAdapter2.getClass();
                List<PdDpgListLayerInfo.DetailBean.ItemsBean> list2 = itemInfoAndItem.items;
                if (list2 == null || list2.size() == 0) {
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                for (int i3 = 0; i3 < itemInfoAndItem.items.size(); i3++) {
                    PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean2 = itemInfoAndItem.items.get(i3);
                    if (itemsBean2 != null && itemsBean2.isSelectedProductCheckBoxNative && itemsBean2.isValid) {
                        arrayList2.add(new CartSkuSummary(itemsBean2.skuId, 1));
                    }
                }
                AddCartParamsEntity addCartParamsEntity = new AddCartParamsEntity();
                addCartParamsEntity.businessName = AddCartParamsEntity.BusinessNameEnum.PD_REFRESH_RECOMMEND.toString();
                PDBaseDeepLinkHelper.addCart(arrayList2, (BaseActivity) pdMDpgLayerAdapter2.b, pdMDpgLayerAdapter2.f4998i, addCartParamsEntity);
                if (arrayList2.size() == 0) {
                    return;
                }
                JDJSONObject jDJSONObject = new JDJSONObject();
                PdDpgListLayerInfo.DetailBean.InfoBean infoBean = itemInfoAndItem.info;
                if (infoBean != null) {
                    jDJSONObject.put("matchid", (Object) infoBean.matchId);
                }
                int i4 = itemInfoAndItem.type;
                if (i4 > 0) {
                    jDJSONObject.put("type", (Object) Integer.valueOf(i4));
                } else {
                    jDJSONObject.put("type", (Object) "-100");
                }
                StringBuilder sb = new StringBuilder();
                while (i2 < arrayList2.size()) {
                    CartSkuSummary cartSkuSummary2 = (CartSkuSummary) arrayList2.get(i2);
                    if (cartSkuSummary2 != null) {
                        if (i2 == arrayList2.size() - 1) {
                            sb.append(cartSkuSummary2.getSkuId());
                        } else {
                            sb.append(cartSkuSummary2.getSkuId());
                            sb.append("#");
                        }
                    }
                    i2++;
                }
                jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) sb.toString());
                pdMDpgLayerAdapter2.f4997h.mtaClick("Productdetail_DapeiBuyAddtoCart", jDJSONObject.toJSONString());
            }
        }

        public static void e(PdDpgLayerHolder pdDpgLayerHolder, PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem) {
            int findLastVisibleItemPosition;
            List<PdDpgListLayerInfo.DetailBean.ItemsBean> list;
            List<PdDpgListLayerInfo.DetailBean.ItemsBean> list2;
            PdMDpgLayerItemRecyclerView pdMDpgLayerItemRecyclerView = pdDpgLayerHolder.f4999c;
            if (pdMDpgLayerItemRecyclerView == null || pdMDpgLayerItemRecyclerView.getVisibility() != 0 || !(pdDpgLayerHolder.f4999c.getLayoutManager() instanceof LinearLayoutManager) || (findLastVisibleItemPosition = ((LinearLayoutManager) pdDpgLayerHolder.f4999c.getLayoutManager()).findLastVisibleItemPosition()) == -1 || itemInfoAndItem == null || (list = itemInfoAndItem.items) == null || list.size() <= findLastVisibleItemPosition) {
                return;
            }
            for (int i2 = 0; i2 <= findLastVisibleItemPosition; i2++) {
                PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean = itemInfoAndItem.items.get(i2);
                if (itemsBean != null && (list2 = PdMDpgLayerAdapter.this.d) != null) {
                    list2.add(itemsBean);
                }
            }
        }

        public static void f(PdDpgLayerHolder pdDpgLayerHolder, PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem) {
            ArrayList arrayList;
            PdDpgListLayerInfo.DetailBean.InfoBean infoBean;
            List<PdDpgListLayerInfo.DetailBean.ItemsBean> list;
            List<PdDpgListLayerInfo.DetailBean.ItemsBean> list2;
            pdDpgLayerHolder.getClass();
            JDJSONArray jDJSONArray = new JDJSONArray();
            if (itemInfoAndItem == null || itemInfoAndItem.items == null) {
                arrayList = null;
            } else {
                arrayList = new ArrayList();
                for (int i2 = 0; i2 < itemInfoAndItem.items.size(); i2++) {
                    PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean = itemInfoAndItem.items.get(i2);
                    if (itemsBean != null && (list = PdMDpgLayerAdapter.this.d) != null && list.contains(itemsBean) && (list2 = PdMDpgLayerAdapter.this.f4994e) != null && !list2.contains(itemsBean)) {
                        arrayList.add(itemsBean);
                    }
                }
            }
            if (arrayList == null || arrayList.size() <= 0) {
                return;
            }
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                JDJSONObject jDJSONObject = new JDJSONObject();
                if (arrayList.get(i3) != null) {
                    jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) ((PdDpgListLayerInfo.DetailBean.ItemsBean) arrayList.get(i3)).skuId);
                    List<PdDpgListLayerInfo.DetailBean.ItemsBean> list3 = PdMDpgLayerAdapter.this.f4994e;
                    if (list3 != null) {
                        list3.add((PdDpgListLayerInfo.DetailBean.ItemsBean) arrayList.get(i3));
                    }
                }
                if (itemInfoAndItem != null && (infoBean = itemInfoAndItem.info) != null && !TextUtils.isEmpty(infoBean.matchId)) {
                    jDJSONObject.put("matchid", (Object) itemInfoAndItem.info.matchId);
                } else {
                    jDJSONObject.put("matchid", (Object) "-100");
                }
                int i4 = itemInfoAndItem.type;
                if (i4 > 0) {
                    jDJSONObject.put("type", (Object) Integer.valueOf(i4));
                } else {
                    jDJSONObject.put("type", (Object) "-100");
                }
                jDJSONArray.add(jDJSONObject);
            }
            PdMDpgLayerAdapter.this.f4997h.mtaExposure("Productdetail_DapeiBuyProductExpo", jDJSONArray.toJSONString());
        }

        public void c(final PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem, final int i2, final PdDpgLayerHolder pdDpgLayerHolder, boolean z, boolean z2) {
            boolean z3;
            int color;
            List<PdDpgListLayerInfo.DetailBean.ItemsBean> list;
            PdMDpgLayerItemRecyclerView.PdDpgLayerAdapter.PdDpgLayerItemHolder pdDpgLayerItemHolder;
            PdDpgListLayerInfo.DetailBean.InfoBean infoBean = itemInfoAndItem.info;
            if (infoBean != null) {
                if (!TextUtils.isEmpty(infoBean.matchTitle)) {
                    this.a.setText(infoBean.matchTitle);
                } else {
                    this.a.setText("");
                }
                if (!TextUtils.isEmpty(infoBean.matchAdWord)) {
                    this.b.setText(infoBean.matchAdWord);
                    this.b.setVisibility(0);
                } else {
                    this.b.setText("");
                    this.b.setVisibility(8);
                }
                if (!TextUtils.isEmpty(infoBean.discount)) {
                    this.f5001f.setText(infoBean.discount);
                    this.f5001f.setVisibility(0);
                } else {
                    this.f5001f.setText("");
                    this.f5001f.setVisibility(8);
                }
                if (!TextUtils.isEmpty(infoBean.nativeTotalPriceStr)) {
                    if (infoBean.nativeTotalPriceStr.contains("\u00a5")) {
                        this.f5000e.setText(PDUtils.getPriceText(infoBean.nativeTotalPriceStr, 0.67f));
                    } else {
                        this.f5000e.setText(PDUtils.getPriceText("\u00a5" + infoBean.nativeTotalPriceStr, 0.67f));
                    }
                } else {
                    this.f5000e.setText("");
                }
                if (itemInfoAndItem.items != null) {
                    for (int i3 = 0; i3 < itemInfoAndItem.items.size(); i3++) {
                        PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean = itemInfoAndItem.items.get(i3);
                        if (itemsBean.isValid && itemsBean.isSelectedProductCheckBoxNative) {
                            z3 = true;
                            break;
                        }
                    }
                }
                z3 = false;
                if (z3) {
                    this.f5002g.setEnabled(true);
                } else {
                    this.f5002g.setEnabled(false);
                }
                if (this.itemView.getLayoutParams() instanceof RecyclerView.LayoutParams) {
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.itemView.getLayoutParams();
                    List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> list2 = PdMDpgLayerAdapter.this.f4993c;
                    if (list2 != null && list2.size() > 1 && i2 == PdMDpgLayerAdapter.this.f4993c.size() - 1) {
                        layoutParams.setMargins(0, 0, 0, PDUtils.dip2px(20.0f));
                    } else {
                        layoutParams.setMargins(0, 0, 0, PDUtils.dip2px(8.0f));
                    }
                }
                FontsUtil.changeTextFont(this.f5000e, 4099);
                FontsUtil.changeTextFont(this.d, 4099);
                this.d.setText(PdMDpgLayerAdapter.this.b.getString(R.string.lib_pd_image_dpg_total_number, Integer.valueOf(infoBean.nativeTotalNumber)));
                AppCompatTextView appCompatTextView = this.b;
                PdMDpgLayerAdapter pdMDpgLayerAdapter = PdMDpgLayerAdapter.this;
                Context context = pdMDpgLayerAdapter.b;
                int i4 = R.color.lib_pd_image_color_808080;
                appCompatTextView.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(context, i4, pdMDpgLayerAdapter.f4997h.getMainImageParams().isDark));
                AppCompatTextView appCompatTextView2 = this.f5001f;
                PdMDpgLayerAdapter pdMDpgLayerAdapter2 = PdMDpgLayerAdapter.this;
                appCompatTextView2.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(pdMDpgLayerAdapter2.b, R.color.lib_pd_image_color_FA2C19, pdMDpgLayerAdapter2.f4997h.getMainImageParams().isDark));
                AppCompatTextView appCompatTextView3 = this.d;
                PdMDpgLayerAdapter pdMDpgLayerAdapter3 = PdMDpgLayerAdapter.this;
                Context context2 = pdMDpgLayerAdapter3.b;
                int i5 = R.color.lib_pd_image_color_1A1A1A;
                appCompatTextView3.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(context2, i5, pdMDpgLayerAdapter3.f4997h.getMainImageParams().isDark));
                AppCompatTextView appCompatTextView4 = this.f5000e;
                PdMDpgLayerAdapter pdMDpgLayerAdapter4 = PdMDpgLayerAdapter.this;
                appCompatTextView4.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(pdMDpgLayerAdapter4.b, i5, pdMDpgLayerAdapter4.f4997h.getMainImageParams().isDark));
                AppCompatTextView appCompatTextView5 = this.a;
                if (PdMDpgLayerAdapter.this.f4997h.getMainImageParams().isDark) {
                    color = PdMDpgLayerAdapter.this.b.getResources().getColor(R.color.lib_pd_image_color_ececec);
                } else {
                    color = PdMDpgLayerAdapter.this.b.getResources().getColor(R.color.lib_pd_image_black);
                }
                appCompatTextView5.setTextColor(color);
                this.itemView.setBackgroundResource(PdMDpgLayerAdapter.this.f4997h.getMainImageParams().isDark ? R.drawable.lib_pd_mainimage_color_1d1b1b_corner_12 : R.drawable.lib_pd_mainimage_color_ffffff_corner_12);
                this.itemView.setTag(Integer.valueOf(i2));
                PdMDpgLayerItemRecyclerView.PdDpgLayerAdapter pdDpgLayerAdapter = this.f4999c.f5005g;
                if (pdDpgLayerAdapter != null) {
                    pdDpgLayerAdapter.d = itemInfoAndItem;
                    if (pdDpgLayerAdapter.a != -1 && z && (list = itemInfoAndItem.items) != null) {
                        int size = list.size();
                        int i6 = pdDpgLayerAdapter.a;
                        if (size > i6) {
                            if (z2 && (pdDpgLayerItemHolder = pdDpgLayerAdapter.b) != null && pdDpgLayerItemHolder.itemView != null) {
                                PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean2 = pdDpgLayerAdapter.d.items.get(i6);
                                Context context3 = pdDpgLayerAdapter.b.itemView.getContext();
                                if (itemsBean2.isValid) {
                                    if (itemsBean2.isSelectedProductCheckBoxNative) {
                                        pdDpgLayerItemHolder.f5011g.setImageResource(R.drawable.lib_pd_mainimage_checkbox_select);
                                        View view = pdDpgLayerItemHolder.itemView;
                                        if (view != null) {
                                            view.setBackgroundResource(PdMDpgLayerItemRecyclerView.PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark ? R.drawable.lib_pd_mainimage_color_ff3826_corner_6 : R.drawable.lib_pd_mainimage_color_fa2c19_corner_6);
                                        }
                                    } else {
                                        pdDpgLayerItemHolder.f5011g.setImageResource(R.drawable.lib_pd_mainimage_checkbox_null);
                                        View view2 = pdDpgLayerItemHolder.itemView;
                                        if (view2 != null) {
                                            view2.setBackgroundResource(PdMDpgLayerItemRecyclerView.PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark ? R.drawable.lib_pd_mainimage_color_555353_corner_6 : R.drawable.lib_pd_mainimage_color_cccccc_corner_6);
                                        }
                                    }
                                    pdDpgLayerItemHolder.d.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(context3, i4, PdMDpgLayerItemRecyclerView.PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark));
                                } else {
                                    pdDpgLayerItemHolder.f5011g.setImageResource(R.drawable.lib_pd_mainimage_dpg_layer_item_no_stock_right_icon);
                                    View view3 = pdDpgLayerItemHolder.itemView;
                                    if (view3 != null) {
                                        view3.setBackgroundResource(PdMDpgLayerItemRecyclerView.PdDpgLayerAdapter.this.f5007e.getMainImageParams().isDark ? R.drawable.lib_pd_mainimage_color_555353_corner_6 : R.drawable.lib_pd_mainimage_color_cccccc_corner_6);
                                    }
                                }
                            } else {
                                pdDpgLayerAdapter.notifyItemChanged(i6);
                            }
                            pdDpgLayerAdapter.a = -1;
                        }
                    }
                    pdDpgLayerAdapter.notifyDataSetChanged();
                    pdDpgLayerAdapter.a = -1;
                }
                this.f4999c.addOnScrollListener(new a(i2, itemInfoAndItem));
                this.f4999c.b(new PdMDpgLayerItemRecyclerView.a() { // from class: com.jd.lib.productdetail.mainimage.old.a
                    {
                        PdMDpgLayerAdapter.PdDpgLayerHolder.this = this;
                    }

                    @Override // com.jd.lib.productdetail.mainimage.old.PdMDpgLayerItemRecyclerView.a
                    public final void a(PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem2, PdDpgListLayerInfo.DetailBean.ItemsBean itemsBean3, boolean z4, boolean z5) {
                        PdMDpgLayerAdapter.PdDpgLayerHolder.this.b(i2, pdDpgLayerHolder, itemInfoAndItem2, itemsBean3, z4, z5);
                    }
                });
                this.f5002g.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.mainimage.old.e
                    {
                        PdMDpgLayerAdapter.PdDpgLayerHolder.this = this;
                    }

                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view4) {
                        PdMDpgLayerAdapter.PdDpgLayerHolder.this.d(itemInfoAndItem, view4);
                    }
                });
                if (this.f5000e.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.f5000e.getLayoutParams();
                    if (!TextUtils.isEmpty(infoBean.discount)) {
                        layoutParams2.topMargin = PDUtils.dip2px(12.0f);
                    } else {
                        layoutParams2.topMargin = PDUtils.dip2px(20.0f);
                    }
                    this.f5000e.setLayoutParams(layoutParams2);
                }
            }
        }
    }

    /* loaded from: classes15.dex */
    public class a implements PdAddShopCartCallback {
        public a() {
            PdMDpgLayerAdapter.this = r1;
        }

        @Override // com.jd.lib.productdetail.core.utils.PdAddShopCartCallback
        public void addShopCartCallback(boolean z) {
            if (z) {
                PdMDpgLayerAdapter.this.f4997h.viewCallBackMutableLiveData.postValue(new PdMImageEventEntity(PdImageEventCode.FRESH_CART_COUNT, "addcart"));
            }
        }
    }

    /* loaded from: classes15.dex */
    public interface b {
    }

    public PdMDpgLayerAdapter(Context context) {
        this.b = context;
    }

    public void a() {
        b bVar = this.f4995f;
        if (bVar != null) {
            PdMDpgLayerView pdMDpgLayerView = PdMDpgLayerView.this;
            pdMDpgLayerView.i(pdMDpgLayerView.v);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> list = this.f4993c;
        if (list == null || list.size() <= 0) {
            return 0;
        }
        return this.f4993c.size() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> list = this.f4993c;
        if (list == null || i2 != list.size()) {
            return 0;
        }
        return this.f4996g;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem;
        View view;
        List<PdDpgListLayerInfo.DetailBean.ItemInfoAndItem> list = this.f4993c;
        if (list != null && i2 < list.size()) {
            itemInfoAndItem = this.f4993c.get(i2);
        } else {
            itemInfoAndItem = new PdDpgListLayerInfo.DetailBean.ItemInfoAndItem();
        }
        PdDpgListLayerInfo.DetailBean.ItemInfoAndItem itemInfoAndItem2 = itemInfoAndItem;
        if (itemInfoAndItem2 == null || viewHolder == null || (view = viewHolder.itemView) == null || view.getContext() == null) {
            return;
        }
        if (getItemViewType(i2) == 0) {
            if (viewHolder instanceof PdDpgLayerHolder) {
                PdDpgLayerHolder pdDpgLayerHolder = (PdDpgLayerHolder) viewHolder;
                PdMainImagePresenter pdMainImagePresenter = this.f4997h;
                PdMDpgLayerItemRecyclerView pdMDpgLayerItemRecyclerView = pdDpgLayerHolder.f4999c;
                if (pdMDpgLayerItemRecyclerView != null) {
                    pdMDpgLayerItemRecyclerView.c(pdMainImagePresenter);
                }
                pdDpgLayerHolder.c(itemInfoAndItem2, i2, pdDpgLayerHolder, false, false);
            }
        } else if (getItemViewType(i2) == this.f4996g && (viewHolder instanceof FootHolder)) {
            ((FootHolder) viewHolder).a.setOnRetryListener(new JDLoadingMoreLayout.RetryListener() { // from class: com.jd.lib.productdetail.mainimage.old.i
                {
                    PdMDpgLayerAdapter.this = this;
                }

                @Override // com.handmark.pulltorefresh.library.JDLoadingMoreLayout.RetryListener
                public final void onRetry() {
                    PdMDpgLayerAdapter.this.a();
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        if (i2 == this.f4996g) {
            return new FootHolder(LayoutInflater.from(this.b).inflate(R.layout.lib_pd_mainimage_dpg_load_more_footer, viewGroup, false));
        }
        return new PdDpgLayerHolder(ImageUtil.inflate(this.b, R.layout.lib_pd_mainimage_dpg_layer_item_layout, viewGroup, false));
    }
}
