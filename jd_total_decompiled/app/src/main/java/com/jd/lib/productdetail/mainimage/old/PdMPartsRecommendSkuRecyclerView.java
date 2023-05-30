package com.jd.lib.productdetail.mainimage.old;

import android.app.Activity;
import android.content.Context;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.common.base.Joiner;
import com.jd.framework.json.JDJSONArray;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.entitys.PdPreferentialRecommendProductExtInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.AddCartParamsEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.AppStaticInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.PreferentialRecommendItemEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.PreferentialRecommendItemGoodsFieldEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBuinessUnitMainImageBizDataEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessMagicHeadPicInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareImageRecommendRankEntity;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jd.lib.productdetail.core.views.PdAutoChangeTextSize;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.old.PdMPartsRecommendView;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.abmta.ABMtaUtils;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMPartsRecommendSkuRecyclerView extends RecyclerView {
    public static final /* synthetic */ int p = 0;

    /* renamed from: g */
    public List<PreferentialRecommendItemEntity> f5075g;

    /* renamed from: h */
    public HashSet<String> f5076h;

    /* renamed from: i */
    public HashSet<String> f5077i;

    /* renamed from: j */
    public PartsRecommendSkuAdapter f5078j;

    /* renamed from: k */
    public boolean f5079k;

    /* renamed from: l */
    public String f5080l;

    /* renamed from: m */
    public Context f5081m;

    /* renamed from: n */
    public PdMainImagePresenter f5082n;
    public WareBusinessUnitMainImageEntity o;

    /* loaded from: classes15.dex */
    public static class PartsRecommendSkuAdapter extends RecyclerView.Adapter<PartsRecommendSkuHolder> implements View.OnClickListener {

        /* renamed from: g */
        public PdMainImagePresenter f5083g;

        /* renamed from: h */
        public String f5084h;

        /* renamed from: i */
        public List<PreferentialRecommendItemEntity> f5085i;

        /* renamed from: j */
        public String f5086j;

        /* renamed from: k */
        public d f5087k;

        /* renamed from: l */
        public c f5088l;

        /* renamed from: m */
        public PdPreferentialRecommendProductExtInfo f5089m;

        /* loaded from: classes15.dex */
        public class PartsRecommendSkuHolder extends RecyclerView.ViewHolder {
            public View a;
            public SimpleDraweeView b;

            /* renamed from: c */
            public PdAutoChangeTextSize f5090c;
            public TextView d;

            /* renamed from: e */
            public SimpleDraweeView f5091e;

            /* renamed from: f */
            public TextView f5092f;

            /* renamed from: g */
            public TextView f5093g;

            /* renamed from: h */
            public View f5094h;

            /* renamed from: i */
            public TextView f5095i;

            /* renamed from: j */
            public TextView f5096j;

            /* renamed from: k */
            public SimpleDraweeView f5097k;

            /* renamed from: l */
            public TextView f5098l;

            /* renamed from: m */
            public ImageView f5099m;

            /* loaded from: classes15.dex */
            public class a implements Runnable {

                /* renamed from: g */
                public final /* synthetic */ TextView f5101g;

                /* renamed from: h */
                public final /* synthetic */ TextView f5102h;

                /* renamed from: i */
                public final /* synthetic */ int f5103i;

                /* renamed from: j */
                public final /* synthetic */ int f5104j;

                public a(PartsRecommendSkuHolder partsRecommendSkuHolder, TextView textView, TextView textView2, int i2, int i3) {
                    this.f5101g = textView;
                    this.f5102h = textView2;
                    this.f5103i = i2;
                    this.f5104j = i3;
                }

                @Override // java.lang.Runnable
                public void run() {
                    TextView textView = this.f5101g;
                    if (textView == null || this.f5102h == null) {
                        return;
                    }
                    if (((this.f5103i - this.f5104j) - textView.getMeasuredWidth()) - this.f5102h.getMeasuredWidth() < 0) {
                        this.f5102h.setVisibility(8);
                    } else {
                        this.f5102h.setVisibility(0);
                    }
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public PartsRecommendSkuHolder(@NonNull View view) {
                super(view);
                PartsRecommendSkuAdapter.this = r1;
                this.a = view.findViewById(R.id.lib_pd_parts_recommend_sku);
                this.b = (SimpleDraweeView) view.findViewById(R.id.lib_pd_parts_recommend_sku_img);
                this.f5090c = (PdAutoChangeTextSize) view.findViewById(R.id.lib_pd_parts_recommend_sku_title);
                this.d = (TextView) view.findViewById(R.id.lib_pd_parts_recommend_sku_price);
                this.f5092f = (TextView) view.findViewById(R.id.lib_pd_parts_recommend_sku_hand_price);
                this.f5091e = (SimpleDraweeView) view.findViewById(R.id.lib_pd_parts_recommend_sku_cart);
                this.f5093g = (TextView) view.findViewById(R.id.lib_pd_parts_recommend_point);
                this.f5095i = (TextView) view.findViewById(R.id.lib_pd_rank_gift_type);
                this.f5096j = (TextView) view.findViewById(R.id.lib_pd_rank_gift_price);
                this.f5094h = view.findViewById(R.id.lib_pd_rank_gift_info);
                this.f5097k = (SimpleDraweeView) view.findViewById(R.id.lib_pd_rank_num_bg);
                this.f5098l = (TextView) view.findViewById(R.id.lib_pd_rank_num_text);
                this.f5099m = (ImageView) view.findViewById(R.id.lib_pd_parts_recommend_sku_hand_price_img);
                FontsUtil.changeTextFont(this.d, 4099);
                FontsUtil.changeTextFont(this.f5092f, 4099);
            }

            public final void b(TextView textView, TextView textView2) {
                if (textView == null || textView2 == null) {
                    return;
                }
                int dip2px = (PDUtils.dip2px(10.0f) * 2) + PDUtils.dip2px(88.0f) + PDUtils.dip2px(12.0f) + PDUtils.dip2px(10.0f) + PDUtils.dip2px(24.0f) + PDUtils.dip2px(32.0f);
                int screenWidth = PDUtils.getScreenWidth();
                if (this.itemView.getContext() instanceof Activity) {
                    screenWidth = PDUtils.getAppWidth((Activity) this.itemView.getContext());
                }
                textView.post(new a(this, textView, textView2, screenWidth, dip2px));
            }
        }

        public static boolean h(PartsRecommendSkuAdapter partsRecommendSkuAdapter) {
            PdMainImagePresenter pdMainImagePresenter = partsRecommendSkuAdapter.f5083g;
            return pdMainImagePresenter != null && pdMainImagePresenter.getMainImageParams().isDark;
        }

        public final void a(@NonNull PartsRecommendSkuHolder partsRecommendSkuHolder, int i2) {
            partsRecommendSkuHolder.f5091e.setTag(Integer.valueOf(i2));
            partsRecommendSkuHolder.a.setTag(Integer.valueOf(i2));
            partsRecommendSkuHolder.f5091e.setOnClickListener(this);
            partsRecommendSkuHolder.a.setOnClickListener(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            List<PreferentialRecommendItemEntity> list = this.f5085i;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull PartsRecommendSkuHolder partsRecommendSkuHolder, int i2) {
            View view;
            PartsRecommendSkuHolder partsRecommendSkuHolder2 = partsRecommendSkuHolder;
            List<PreferentialRecommendItemEntity> list = this.f5085i;
            PreferentialRecommendItemEntity preferentialRecommendItemEntity = (list == null || i2 >= list.size()) ? null : this.f5085i.get(i2);
            if (preferentialRecommendItemEntity == null || partsRecommendSkuHolder2 == null || (view = partsRecommendSkuHolder2.itemView) == null || view.getContext() == null) {
                return;
            }
            String str = this.f5084h;
            String str2 = this.f5086j;
            if (preferentialRecommendItemEntity.goodsField != null) {
                if (preferentialRecommendItemEntity.LocaShowType == 1) {
                    partsRecommendSkuHolder2.itemView.setVisibility(0);
                    if (!TextUtils.isEmpty(preferentialRecommendItemEntity.goodsField.cashGiftType)) {
                        partsRecommendSkuHolder2.f5095i.setText(preferentialRecommendItemEntity.goodsField.cashGiftType);
                        partsRecommendSkuHolder2.f5096j.setText(preferentialRecommendItemEntity.goodsField.cashGiftAmount + partsRecommendSkuHolder2.itemView.getContext().getString(R.string.lib_pd_image_money_unit));
                        partsRecommendSkuHolder2.f5094h.setVisibility(0);
                    } else {
                        partsRecommendSkuHolder2.f5094h.setVisibility(8);
                    }
                    StringBuilder sb = new StringBuilder();
                    if (!TextUtils.isEmpty(preferentialRecommendItemEntity.goodsField.buyedStr)) {
                        sb.append(preferentialRecommendItemEntity.goodsField.buyedStr);
                    }
                    if (preferentialRecommendItemEntity.goodsField.sellPoints != null) {
                        String join = Joiner.on(" | ").join(preferentialRecommendItemEntity.goodsField.sellPoints);
                        if (!TextUtils.isEmpty(join)) {
                            if (sb.length() > 0) {
                                sb.append(" | ");
                            }
                            sb.append(join);
                        }
                    }
                    if (sb.length() > 0) {
                        partsRecommendSkuHolder2.f5093g.setText(sb.toString());
                        partsRecommendSkuHolder2.f5093g.setVisibility(0);
                    } else {
                        partsRecommendSkuHolder2.f5093g.setVisibility(8);
                    }
                    TextView textView = partsRecommendSkuHolder2.f5098l;
                    StringBuilder sb2 = new StringBuilder();
                    String str3 = "";
                    sb2.append("");
                    sb2.append(preferentialRecommendItemEntity.goodsField.skuNum);
                    textView.setText(sb2.toString());
                    int i3 = preferentialRecommendItemEntity.goodsField.skuNum;
                    PdPreferentialRecommendProductExtInfo pdPreferentialRecommendProductExtInfo = PartsRecommendSkuAdapter.this.f5089m;
                    if (pdPreferentialRecommendProductExtInfo != null) {
                        if (i3 == 1) {
                            str3 = pdPreferentialRecommendProductExtInfo.no1Img;
                        } else if (i3 == 2) {
                            str3 = pdPreferentialRecommendProductExtInfo.no2Img;
                        } else if (i3 != 3) {
                            str3 = pdPreferentialRecommendProductExtInfo.no4Img;
                        } else {
                            str3 = pdPreferentialRecommendProductExtInfo.no3Img;
                        }
                    }
                    JDImageUtils.displayImage(str3, partsRecommendSkuHolder2.f5097k);
                    JDImageUtils.displayImage(preferentialRecommendItemEntity.goodsField.whiteBackground, (ImageView) partsRecommendSkuHolder2.b, (JDDisplayImageOptions) null, true);
                    ArrayList arrayList = new ArrayList();
                    if (TextUtils.equals("1", preferentialRecommendItemEntity.pop) && !TextUtils.isEmpty(str2)) {
                        arrayList.add(str2);
                    }
                    SpannableString imageSpan = PDUtils.getImageSpan(partsRecommendSkuHolder2.itemView.getContext(), arrayList, preferentialRecommendItemEntity.goodsField.title, h(PartsRecommendSkuAdapter.this));
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                    spannableStringBuilder.append((CharSequence) imageSpan);
                    partsRecommendSkuHolder2.f5090c.setText(spannableStringBuilder);
                    if (!TextUtils.isEmpty(preferentialRecommendItemEntity.goodsField.realPrice)) {
                        partsRecommendSkuHolder2.f5092f.setVisibility(0);
                        partsRecommendSkuHolder2.f5092f.setText(PDUtils.getJPriceTextForTen(PDUtils.formatPrice(preferentialRecommendItemEntity.goodsField.realPrice), 0.67f));
                        partsRecommendSkuHolder2.d.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(partsRecommendSkuHolder2.itemView.getContext(), R.color.lib_pd_image_757575, h(PartsRecommendSkuAdapter.this)));
                        partsRecommendSkuHolder2.f5092f.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(partsRecommendSkuHolder2.itemView.getContext(), R.color.lib_pd_image_color_FA2C19, h(PartsRecommendSkuAdapter.this)));
                        partsRecommendSkuHolder2.f5099m.setVisibility(0);
                        partsRecommendSkuHolder2.d.setText(PDUtils.getJPriceTextForTen(preferentialRecommendItemEntity.goodsField.jdPrice, 1.0f));
                        partsRecommendSkuHolder2.d.setTextSize(2, 12.0f);
                        partsRecommendSkuHolder2.b(partsRecommendSkuHolder2.f5092f, partsRecommendSkuHolder2.d);
                    } else {
                        partsRecommendSkuHolder2.f5092f.setVisibility(8);
                        partsRecommendSkuHolder2.f5099m.setVisibility(8);
                        partsRecommendSkuHolder2.d.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(partsRecommendSkuHolder2.itemView.getContext(), R.color.lib_pd_image_color_FA2C19, h(PartsRecommendSkuAdapter.this)));
                        partsRecommendSkuHolder2.d.setText(PDUtils.getJPriceTextForTen(PDUtils.formatPrice(preferentialRecommendItemEntity.goodsField.jdPrice), 0.67f));
                        partsRecommendSkuHolder2.d.setTextSize(2, 18.0f);
                    }
                    partsRecommendSkuHolder2.f5090c.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(partsRecommendSkuHolder2.itemView.getContext(), R.color.lib_pd_image_color_1A1A1A, h(PartsRecommendSkuAdapter.this)));
                    partsRecommendSkuHolder2.a.setBackgroundResource(h(PartsRecommendSkuAdapter.this) ? R.drawable.lib_pd_mainimage_parts_recommend_sku_dark_bg : R.drawable.lib_pd_mainimage_parts_recommend_sku_white_bg);
                    partsRecommendSkuHolder2.f5090c.setMaxLines(1);
                } else {
                    StringBuilder sb3 = new StringBuilder();
                    if (preferentialRecommendItemEntity.goodsField.sellPoint != null) {
                        String join2 = Joiner.on(" | ").join(preferentialRecommendItemEntity.goodsField.sellPoint);
                        if (!TextUtils.isEmpty(join2)) {
                            if (sb3.length() > 0) {
                                sb3.append(" | ");
                            }
                            sb3.append(join2);
                        }
                    }
                    if (sb3.length() > 0) {
                        partsRecommendSkuHolder2.f5093g.setText(sb3.toString());
                        partsRecommendSkuHolder2.f5093g.setVisibility(0);
                    } else {
                        partsRecommendSkuHolder2.f5093g.setVisibility(8);
                    }
                    if (partsRecommendSkuHolder2.f5093g.getVisibility() == 8) {
                        partsRecommendSkuHolder2.f5090c.setMaxLines(2);
                    } else {
                        partsRecommendSkuHolder2.f5090c.setMaxLines(1);
                    }
                    ArrayList arrayList2 = new ArrayList();
                    if (TextUtils.equals("1", preferentialRecommendItemEntity.pop) && !TextUtils.isEmpty(str2)) {
                        arrayList2.add(str2);
                    }
                    String str4 = preferentialRecommendItemEntity.goodsField.img_dfs_url;
                    if (!TextUtils.isEmpty(str)) {
                        str4 = str + preferentialRecommendItemEntity.goodsField.img_dfs_url;
                    }
                    JDImageUtils.displayImage(str4, (ImageView) partsRecommendSkuHolder2.b, (JDDisplayImageOptions) null, false);
                    SpannableString imageSpan2 = PDUtils.getImageSpan(partsRecommendSkuHolder2.itemView.getContext(), arrayList2, preferentialRecommendItemEntity.goodsField.title, h(PartsRecommendSkuAdapter.this));
                    SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder();
                    spannableStringBuilder2.append((CharSequence) imageSpan2);
                    partsRecommendSkuHolder2.f5090c.setText(spannableStringBuilder2);
                    if (!TextUtils.isEmpty(preferentialRecommendItemEntity.goodsField.realPrice)) {
                        partsRecommendSkuHolder2.f5092f.setVisibility(0);
                        partsRecommendSkuHolder2.f5099m.setVisibility(0);
                        partsRecommendSkuHolder2.f5092f.setText(PDUtils.getJPriceTextForTen(PDUtils.formatPrice(preferentialRecommendItemEntity.goodsField.realPrice), 0.67f));
                        partsRecommendSkuHolder2.d.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(partsRecommendSkuHolder2.itemView.getContext(), R.color.lib_pd_image_color_1A1A1A, h(PartsRecommendSkuAdapter.this)));
                        partsRecommendSkuHolder2.f5092f.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(partsRecommendSkuHolder2.itemView.getContext(), R.color.lib_pd_image_color_FA2C19, h(PartsRecommendSkuAdapter.this)));
                        partsRecommendSkuHolder2.d.setText(PDUtils.getJPriceTextForTen(preferentialRecommendItemEntity.goodsField.jdPrice, 1.0f));
                        partsRecommendSkuHolder2.d.setTextSize(2, 12.0f);
                        partsRecommendSkuHolder2.b(partsRecommendSkuHolder2.f5092f, partsRecommendSkuHolder2.d);
                    } else {
                        partsRecommendSkuHolder2.d.setText(PDUtils.getJPriceTextForTen(PDUtils.formatPrice(preferentialRecommendItemEntity.goodsField.jdPrice), 0.67f));
                        partsRecommendSkuHolder2.f5092f.setVisibility(8);
                        partsRecommendSkuHolder2.f5099m.setVisibility(8);
                        partsRecommendSkuHolder2.d.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(partsRecommendSkuHolder2.itemView.getContext(), R.color.lib_pd_image_color_FA2C19, h(PartsRecommendSkuAdapter.this)));
                        partsRecommendSkuHolder2.d.setTextSize(2, 18.0f);
                    }
                    partsRecommendSkuHolder2.f5090c.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(partsRecommendSkuHolder2.itemView.getContext(), R.color.lib_pd_image_color_1A1A1A, h(PartsRecommendSkuAdapter.this)));
                    partsRecommendSkuHolder2.a.setBackgroundResource(h(PartsRecommendSkuAdapter.this) ? R.drawable.lib_pd_mainimage_parts_recommend_sku_dark_bg : R.drawable.lib_pd_mainimage_parts_recommend_sku_white_bg);
                }
            }
            a(partsRecommendSkuHolder2, i2);
            c cVar = this.f5088l;
            if (cVar != null) {
                String str5 = preferentialRecommendItemEntity.skuId;
                a aVar = (a) cVar;
                PdMPartsRecommendSkuRecyclerView pdMPartsRecommendSkuRecyclerView = PdMPartsRecommendSkuRecyclerView.this;
                if (pdMPartsRecommendSkuRecyclerView.f5079k) {
                    return;
                }
                pdMPartsRecommendSkuRecyclerView.f5076h.add(str5);
                PdMPartsRecommendSkuRecyclerView.b(PdMPartsRecommendSkuRecyclerView.this);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:118:0x0032  */
        /* JADX WARN: Removed duplicated region for block: B:140:0x009c  */
        @Override // android.view.View.OnClickListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void onClick(View view) {
            PreferentialRecommendItemEntity preferentialRecommendItemEntity;
            int id;
            WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
            AppStaticInfo appStaticInfo;
            PdMPartsRecommendView.a aVar;
            if (this.f5087k == null) {
                return;
            }
            Object tag = view.getTag();
            if (tag instanceof Integer) {
                int intValue = ((Integer) tag).intValue();
                List<PreferentialRecommendItemEntity> list = this.f5085i;
                if (list != null && intValue >= 0 && intValue < list.size()) {
                    preferentialRecommendItemEntity = this.f5085i.get(intValue);
                    id = view.getId();
                    if (id != R.id.lib_pd_parts_recommend_sku) {
                        e0 e0Var = (e0) this.f5087k;
                        e0Var.getClass();
                        if (preferentialRecommendItemEntity != null) {
                            PdMPartsRecommendView pdMPartsRecommendView = e0Var.a;
                            String str = preferentialRecommendItemEntity.skuId;
                            if (pdMPartsRecommendView.x) {
                                if (TextUtils.equals(str, pdMPartsRecommendView.f5124m.getMainImageParams().skuId)) {
                                    PdMPartsRecommendView.a aVar2 = pdMPartsRecommendView.A;
                                    if (aVar2 != null) {
                                        aVar2.a();
                                    }
                                } else {
                                    j0.b(pdMPartsRecommendView.f5118g, Long.valueOf(Long.parseLong(str)), null, null);
                                }
                            } else {
                                j0.b(pdMPartsRecommendView.f5118g, Long.valueOf(Long.parseLong(str)), null, null);
                                WareBusinessUnitMainImageEntity wareBusinessUnitMainImageEntity = pdMPartsRecommendView.D;
                                if (wareBusinessUnitMainImageEntity != null && (extMapEntity = wareBusinessUnitMainImageEntity.extMap) != null && (appStaticInfo = extMapEntity.appStaticInfo) != null && appStaticInfo.isCloseRecommendDialog && (aVar = pdMPartsRecommendView.A) != null) {
                                    aVar.a();
                                }
                            }
                            PdMPartsRecommendView pdMPartsRecommendView2 = e0Var.a;
                            pdMPartsRecommendView2.g(true, "Productdetail_AccessoryProduct", pdMPartsRecommendView2.w, preferentialRecommendItemEntity.skuId, pdMPartsRecommendView2.z, true, true);
                            return;
                        }
                        return;
                    } else if (id == R.id.lib_pd_parts_recommend_sku_cart) {
                        e0 e0Var2 = (e0) this.f5087k;
                        e0Var2.getClass();
                        if (preferentialRecommendItemEntity != null) {
                            PdMPartsRecommendView pdMPartsRecommendView3 = e0Var2.a;
                            String str2 = preferentialRecommendItemEntity.skuId;
                            if (pdMPartsRecommendView3.f5118g instanceof BaseActivity) {
                                ArrayList arrayList = new ArrayList();
                                arrayList.add(new CartSkuSummary(str2, 1));
                                AddCartParamsEntity addCartParamsEntity = new AddCartParamsEntity();
                                addCartParamsEntity.businessName = AddCartParamsEntity.BusinessNameEnum.PD_NORMAL.toString();
                                PDBaseDeepLinkHelper.addCart(arrayList, (BaseActivity) pdMPartsRecommendView3.f5118g, null, addCartParamsEntity);
                            }
                            PdMPartsRecommendView pdMPartsRecommendView4 = e0Var2.a;
                            pdMPartsRecommendView4.g(true, "Productdetail_AccessoryAddtoCart", pdMPartsRecommendView4.w, preferentialRecommendItemEntity.skuId, pdMPartsRecommendView4.z, true, true);
                            return;
                        }
                        return;
                    } else {
                        return;
                    }
                }
            }
            preferentialRecommendItemEntity = null;
            id = view.getId();
            if (id != R.id.lib_pd_parts_recommend_sku) {
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        @NonNull
        public PartsRecommendSkuHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
            LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
            List<PreferentialRecommendItemEntity> list = this.f5085i;
            PreferentialRecommendItemEntity preferentialRecommendItemEntity = (list == null || i2 >= list.size()) ? null : this.f5085i.get(i2);
            int i3 = R.layout.lib_pd_mainimage_parts_recommend_sku_item;
            if (preferentialRecommendItemEntity != null && preferentialRecommendItemEntity.LocaShowType == 1) {
                i3 = R.layout.lib_pd_mainimage_rank_recommend_sku_item;
            }
            return new PartsRecommendSkuHolder(from.inflate(i3, viewGroup, false));
        }
    }

    /* loaded from: classes15.dex */
    public class a implements c {
        public a() {
            PdMPartsRecommendSkuRecyclerView.this = r1;
        }
    }

    /* loaded from: classes15.dex */
    public class b extends RecyclerView.OnScrollListener {
        public int a = 0;
        public boolean b = false;

        public b() {
            PdMPartsRecommendSkuRecyclerView.this = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i2) {
            LinearLayoutManager linearLayoutManager;
            int findLastVisibleItemPosition;
            super.onScrollStateChanged(recyclerView, i2);
            if (this.a == 0 && i2 == 1) {
                this.b = true;
            } else if (i2 == 0) {
                PdMPartsRecommendSkuRecyclerView pdMPartsRecommendSkuRecyclerView = PdMPartsRecommendSkuRecyclerView.this;
                int i3 = PdMPartsRecommendSkuRecyclerView.p;
                if (pdMPartsRecommendSkuRecyclerView.getVisibility() == 0 && (pdMPartsRecommendSkuRecyclerView.getLayoutManager() instanceof LinearLayoutManager) && (findLastVisibleItemPosition = (linearLayoutManager = (LinearLayoutManager) pdMPartsRecommendSkuRecyclerView.getLayoutManager()).findLastVisibleItemPosition()) != -1) {
                    View childAt = pdMPartsRecommendSkuRecyclerView.getChildAt(findLastVisibleItemPosition - linearLayoutManager.findFirstVisibleItemPosition());
                    if (childAt.getTop() + ((childAt.getBottom() - childAt.getTop()) / 2) >= pdMPartsRecommendSkuRecyclerView.getHeight() && findLastVisibleItemPosition > 1) {
                        findLastVisibleItemPosition--;
                    }
                    List<PreferentialRecommendItemEntity> list = pdMPartsRecommendSkuRecyclerView.f5075g;
                    if (list != null && list.size() > findLastVisibleItemPosition) {
                        for (int i4 = 0; i4 <= findLastVisibleItemPosition; i4++) {
                            PreferentialRecommendItemEntity preferentialRecommendItemEntity = pdMPartsRecommendSkuRecyclerView.f5075g.get(i4);
                            if (preferentialRecommendItemEntity != null) {
                                pdMPartsRecommendSkuRecyclerView.f5076h.add(preferentialRecommendItemEntity.skuId);
                            }
                        }
                    }
                }
                PdMPartsRecommendSkuRecyclerView.b(PdMPartsRecommendSkuRecyclerView.this);
            }
            this.a = i2;
            PdMPartsRecommendSkuRecyclerView.this.f5079k = true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i2, int i3) {
            super.onScrolled(recyclerView, i2, i3);
            if (!this.b || i3 == 0) {
                return;
            }
            this.b = false;
        }
    }

    /* loaded from: classes15.dex */
    public interface c {
    }

    /* loaded from: classes15.dex */
    public interface d {
    }

    public PdMPartsRecommendSkuRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5076h = new HashSet<>();
        this.f5077i = new HashSet<>();
        this.f5079k = false;
        this.f5081m = context;
        a();
    }

    public static void b(PdMPartsRecommendSkuRecyclerView pdMPartsRecommendSkuRecyclerView) {
        WareBusinessMagicHeadPicInfoEntity topImageBanDanData;
        WareBuinessUnitMainImageBizDataEntity wareBuinessUnitMainImageBizDataEntity;
        WareImageRecommendRankEntity wareImageRecommendRankEntity;
        List<String> list;
        pdMPartsRecommendSkuRecyclerView.getClass();
        JDJSONArray jDJSONArray = new JDJSONArray();
        ArrayList<PreferentialRecommendItemEntity> d2 = pdMPartsRecommendSkuRecyclerView.d();
        if (d2 == null || d2.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < d2.size(); i2++) {
            JDJSONObject jDJSONObject = new JDJSONObject();
            if (d2.get(i2) != null) {
                PreferentialRecommendItemEntity preferentialRecommendItemEntity = d2.get(i2);
                jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) d2.get(i2).skuId);
                String str = "0";
                jDJSONObject.put("is_purchase_price", TextUtils.isEmpty(d2.get(i2).realPrice) ? "0" : "1");
                jDJSONObject.put("tabid", (Object) d2.get(i2).tabId);
                PreferentialRecommendItemGoodsFieldEntity preferentialRecommendItemGoodsFieldEntity = preferentialRecommendItemEntity.goodsField;
                if (preferentialRecommendItemGoodsFieldEntity != null) {
                    jDJSONObject.put("is_gifticon", TextUtils.isEmpty(preferentialRecommendItemGoodsFieldEntity.cashGiftType) ? "0" : "1");
                    List<String> list2 = preferentialRecommendItemEntity.goodsField.sellPoints;
                    if (list2 != null && !list2.isEmpty()) {
                        str = "1";
                    }
                    jDJSONObject.put("is_sell_point", (Object) str);
                    if (!TextUtils.isEmpty(preferentialRecommendItemEntity.tabId)) {
                        String[] split = preferentialRecommendItemEntity.tabId.split(CartConstant.KEY_YB_INFO_LINK);
                        if (split.length > 0) {
                            jDJSONObject.put("rankid", (Object) split[0]);
                        }
                    }
                    if (preferentialRecommendItemEntity.LocaShowType == 1) {
                        jDJSONObject.put("abtestid", (Object) "");
                    }
                }
                PdMainImagePresenter pdMainImagePresenter = pdMPartsRecommendSkuRecyclerView.f5082n;
                if (pdMainImagePresenter != null && (topImageBanDanData = pdMainImagePresenter.getTopImageBanDanData()) != null && (wareBuinessUnitMainImageBizDataEntity = topImageBanDanData.bizData) != null && (wareImageRecommendRankEntity = wareBuinessUnitMainImageBizDataEntity.bangDanInfo) != null && (list = wareImageRecommendRankEntity.bangDanExperiment) != null) {
                    jDJSONObject.put("touchstone_expids", (Object) list);
                }
            }
            jDJSONArray.add(jDJSONObject);
            pdMPartsRecommendSkuRecyclerView.f5077i.add(d2.get(i2).skuId);
        }
        ABMtaUtils.sendExposureDataWithExt(pdMPartsRecommendSkuRecyclerView.f5081m, "Productdetail_AccessoryProductExpo", null, PDHelper.getPageId("com.jd.lib.productdetail.ProductDetailActivity"), "com.jd.lib.productdetail.ProductDetailActivity", pdMPartsRecommendSkuRecyclerView.o.extMap.skuId, jDJSONArray.toJSONString(), "", "", pdMPartsRecommendSkuRecyclerView.f5082n.getMainImageParams().mSkuTag, null, j0.a(pdMPartsRecommendSkuRecyclerView.f5080l));
    }

    private ArrayList<PreferentialRecommendItemEntity> d() {
        if (this.f5075g == null) {
            return null;
        }
        ArrayList<PreferentialRecommendItemEntity> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < this.f5075g.size(); i2++) {
            String str = this.f5075g.get(i2).skuId;
            if (this.f5076h.contains(str) && !this.f5077i.contains(str)) {
                arrayList.add(this.f5075g.get(i2));
            }
        }
        return arrayList;
    }

    private void g(c cVar) {
        PartsRecommendSkuAdapter partsRecommendSkuAdapter = this.f5078j;
        if (partsRecommendSkuAdapter != null) {
            partsRecommendSkuAdapter.f5088l = cVar;
        }
    }

    public final void a() {
        PartsRecommendSkuAdapter partsRecommendSkuAdapter = new PartsRecommendSkuAdapter();
        this.f5078j = partsRecommendSkuAdapter;
        setAdapter(partsRecommendSkuAdapter);
        setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        setHasFixedSize(true);
        g(new a());
        addOnScrollListener(new b());
    }

    public void e(d dVar) {
        PartsRecommendSkuAdapter partsRecommendSkuAdapter = this.f5078j;
        if (partsRecommendSkuAdapter != null) {
            partsRecommendSkuAdapter.f5087k = dVar;
        }
    }

    public void f(PdMainImagePresenter pdMainImagePresenter) {
        this.f5082n = pdMainImagePresenter;
    }

    public PdMPartsRecommendSkuRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f5076h = new HashSet<>();
        this.f5077i = new HashSet<>();
        this.f5079k = false;
        this.f5081m = context;
        a();
    }
}
