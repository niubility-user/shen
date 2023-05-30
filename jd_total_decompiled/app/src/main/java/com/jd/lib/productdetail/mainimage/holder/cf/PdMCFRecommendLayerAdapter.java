package com.jd.lib.productdetail.mainimage.holder.cf;

import android.app.Activity;
import android.os.Build;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.common.PDFlowLayoutFix;
import com.jd.lib.productdetail.core.entitys.SkinRecommendInfoEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.AddCartParamsEntity;
import com.jd.lib.productdetail.core.entitys.warebusiness.AppStaticInfo;
import com.jd.lib.productdetail.core.entitys.warebusiness.WareBusinessUnitMainImageEntity;
import com.jd.lib.productdetail.core.utils.PDBaseDeepLinkHelper;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.core.utils.PdMtaUtil;
import com.jd.lib.productdetail.mainimage.R;
import com.jd.lib.productdetail.mainimage.holder.cf.PdMCFRecommendLayerView;
import com.jd.lib.productdetail.mainimage.holder.helper.PdMImageExpandableTextView;
import com.jd.lib.productdetail.mainimage.old.j0;
import com.jd.lib.productdetail.mainimage.presenter.PdMainImagePresenter;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.entity.cart.CartSkuSummary;
import com.jingdong.common.unification.dialog.UnBottomDialog;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.FontsUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes15.dex */
public class PdMCFRecommendLayerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public int a = 1;
    public List<SkinRecommendInfoEntity.CompleteSkus> b;

    /* renamed from: c */
    public String f4676c;
    public a d;

    /* renamed from: e */
    public PdMainImagePresenter f4677e;

    /* loaded from: classes15.dex */
    public class CFProposalHolder extends RecyclerView.ViewHolder {
        public PdMImageExpandableTextView a;
        public FrameLayout b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CFProposalHolder(@NonNull View view) {
            super(view);
            PdMCFRecommendLayerAdapter.this = r1;
            this.a = (PdMImageExpandableTextView) view.findViewById(R.id.lib_pd_cf_recommend_proposal_text);
            this.b = (FrameLayout) view.findViewById(R.id.lib_pd_cf_recommend_proposal);
        }
    }

    /* loaded from: classes15.dex */
    public class CFRecommendSkuHolder extends RecyclerView.ViewHolder {
        public LinearLayout a;
        public SimpleDraweeView b;

        /* renamed from: c */
        public TextView f4679c;
        public TextView d;

        /* renamed from: e */
        public SimpleDraweeView f4680e;

        /* renamed from: f */
        public PDFlowLayoutFix f4681f;

        /* loaded from: classes15.dex */
        public class a implements View.OnClickListener {

            /* renamed from: g */
            public final /* synthetic */ SkinRecommendInfoEntity.CompleteSkus f4683g;

            public a(SkinRecommendInfoEntity.CompleteSkus completeSkus) {
                CFRecommendSkuHolder.this = r1;
                this.f4683g = completeSkus;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a aVar = PdMCFRecommendLayerAdapter.this.d;
                if (aVar != null) {
                    String str = this.f4683g.skuId;
                    c cVar = (c) aVar;
                    PdMCFRecommendLayerView pdMCFRecommendLayerView = cVar.a;
                    if (pdMCFRecommendLayerView.f4687g instanceof BaseActivity) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(new CartSkuSummary(str, 1));
                        AddCartParamsEntity addCartParamsEntity = new AddCartParamsEntity();
                        addCartParamsEntity.businessName = AddCartParamsEntity.BusinessNameEnum.PD_NORMAL.toString();
                        PDBaseDeepLinkHelper.addCart(arrayList, (BaseActivity) pdMCFRecommendLayerView.f4687g, null, addCartParamsEntity);
                    }
                    JDJSONObject jDJSONObject = new JDJSONObject();
                    jDJSONObject.put(PdMtaUtil.PARAM_KEY_SKUID, (Object) str);
                    cVar.a.s.mtaClick("Productdetail_ScanSkinCart", jDJSONObject.toJSONString());
                }
            }
        }

        /* loaded from: classes15.dex */
        public class b implements View.OnClickListener {

            /* renamed from: g */
            public final /* synthetic */ SkinRecommendInfoEntity.CompleteSkus f4685g;

            public b(SkinRecommendInfoEntity.CompleteSkus completeSkus) {
                CFRecommendSkuHolder.this = r1;
                this.f4685g = completeSkus;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WareBusinessUnitMainImageEntity.ExtMapEntity extMapEntity;
                AppStaticInfo appStaticInfo;
                PdMCFRecommendLayerView.a aVar;
                PdMainImagePresenter pdMainImagePresenter;
                UnBottomDialog unBottomDialog;
                a aVar2 = PdMCFRecommendLayerAdapter.this.d;
                if (aVar2 != null) {
                    String str = this.f4685g.skuId;
                    PdMCFRecommendLayerView pdMCFRecommendLayerView = ((c) aVar2).a;
                    j0.b(pdMCFRecommendLayerView.f4687g, Long.valueOf(Long.parseLong(str)), null, null);
                    MutableLiveData<WareBusinessUnitMainImageEntity> mutableLiveData = pdMCFRecommendLayerView.s.mainImageData;
                    WareBusinessUnitMainImageEntity value = mutableLiveData != null ? mutableLiveData.getValue() : null;
                    if (value == null || (extMapEntity = value.extMap) == null || (appStaticInfo = extMapEntity.appStaticInfo) == null || !appStaticInfo.isCloseRecommendDialog || (aVar = pdMCFRecommendLayerView.o) == null || (pdMainImagePresenter = ((e) aVar).a.f4654n) == null || (unBottomDialog = pdMainImagePresenter.mLayerDialog) == null) {
                        return;
                    }
                    unBottomDialog.dismiss();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CFRecommendSkuHolder(@NonNull View view) {
            super(view);
            PdMCFRecommendLayerAdapter.this = r1;
            this.a = (LinearLayout) view.findViewById(R.id.lib_pd_parts_recommend_sku);
            this.b = (SimpleDraweeView) view.findViewById(R.id.lib_pd_parts_recommend_sku_img);
            this.f4679c = (TextView) view.findViewById(R.id.lib_pd_parts_recommend_sku_title);
            this.d = (TextView) view.findViewById(R.id.lib_pd_parts_recommend_sku_price);
            this.f4680e = (SimpleDraweeView) view.findViewById(R.id.lib_pd_parts_recommend_sku_cart);
            this.f4681f = (PDFlowLayoutFix) view.findViewById(R.id.lib_pd_parts_recommend_sku_tag);
            FontsUtil.changeTextFont(this.d, 4099);
        }

        public final TextView b(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            TextView textView = new TextView(this.itemView.getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            textView.setTextSize(2, 10.0f);
            textView.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(this.itemView.getContext(), R.color.lib_pd_image_color_FA2C19, PdMCFRecommendLayerAdapter.a(PdMCFRecommendLayerAdapter.this)));
            int dip2px = PDUtils.dip2px(2.0f);
            textView.setPadding(dip2px, dip2px, dip2px, dip2px);
            textView.setBackgroundResource(R.drawable.lib_pd_mainimage_holder_recommend_item_tag_bg);
            textView.setText(str);
            return textView;
        }

        public void c(SkinRecommendInfoEntity.CompleteSkus completeSkus, String str) {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(str)) {
                arrayList.add(str);
            }
            JDImageUtils.displayImage(completeSkus.image, (ImageView) this.b, (JDDisplayImageOptions) null, false);
            SpannableString imageSpan = PDUtils.getImageSpan(this.itemView.getContext(), arrayList, completeSkus.name, PdMCFRecommendLayerAdapter.a(PdMCFRecommendLayerAdapter.this));
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) imageSpan);
            this.f4679c.setText(spannableStringBuilder);
            this.d.setText(PDUtils.getJPriceTextForTen(PDUtils.formatPrice(completeSkus.price), 0.67f));
            this.d.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(this.itemView.getContext(), R.color.lib_pd_image_color_FA2C19, PdMCFRecommendLayerAdapter.a(PdMCFRecommendLayerAdapter.this)));
            this.f4679c.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(this.itemView.getContext(), R.color.lib_pd_image_color_1A1A1A, PdMCFRecommendLayerAdapter.a(PdMCFRecommendLayerAdapter.this)));
            this.a.setBackgroundResource(PdMCFRecommendLayerAdapter.a(PdMCFRecommendLayerAdapter.this) ? R.drawable.lib_pd_mainimage_parts_recommend_sku_dark_bg : R.drawable.lib_pd_mainimage_parts_recommend_sku_white_bg);
            this.f4681f.removeAllViews();
            this.f4681f.setMaxRows(1);
            this.f4681f.setSpace(PDUtils.dip2px(8.0f), 0);
            if (completeSkus.functions != null) {
                for (int i2 = 0; i2 < completeSkus.functions.size(); i2++) {
                    TextView b2 = b(completeSkus.functions.get(i2).function);
                    TextView b3 = b(completeSkus.functions.get(i2).ingredient);
                    if (b2 != null) {
                        this.f4681f.addView(b2);
                    }
                    if (b3 != null) {
                        this.f4681f.addView(b3);
                    }
                }
            }
            this.f4680e.setOnClickListener(new a(completeSkus));
            this.a.setOnClickListener(new b(completeSkus));
        }
    }

    /* loaded from: classes15.dex */
    public interface a {
    }

    public static boolean a(PdMCFRecommendLayerAdapter pdMCFRecommendLayerAdapter) {
        PdMainImagePresenter pdMainImagePresenter = pdMCFRecommendLayerAdapter.f4677e;
        return pdMainImagePresenter != null && pdMainImagePresenter.getMainImageParams().isDark;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<SkinRecommendInfoEntity.CompleteSkus> list;
        List<SkinRecommendInfoEntity.CompleteSkus> list2;
        if (!TextUtils.isEmpty(this.f4676c) && (list2 = this.b) != null) {
            return list2.size() + 1;
        }
        if (!TextUtils.isEmpty(this.f4676c) || (list = this.b) == null) {
            return (TextUtils.isEmpty(this.f4676c) || this.b != null) ? 0 : 1;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        if (i2 != 0 || TextUtils.isEmpty(this.f4676c)) {
            return this.a;
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        int i3;
        float f2;
        StaticLayout staticLayout;
        int i4;
        if (viewHolder instanceof CFProposalHolder) {
            CFProposalHolder cFProposalHolder = (CFProposalHolder) viewHolder;
            String str = this.f4676c;
            cFProposalHolder.a.e(false);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) str);
            cFProposalHolder.b.setBackgroundResource(a(PdMCFRecommendLayerAdapter.this) ? R.drawable.lib_pd_mainimage_parts_recommend_sku_dark_bg : R.drawable.lib_pd_mainimage_parts_recommend_sku_white_bg);
            if (cFProposalHolder.itemView.getContext() instanceof Activity) {
                cFProposalHolder.a.f4880i = PDUtils.getAppWidth((Activity) cFProposalHolder.itemView.getContext()) - PDUtils.dip2px(40.0f);
            }
            cFProposalHolder.a.setMaxLines(3);
            PdMImageExpandableTextView pdMImageExpandableTextView = cFProposalHolder.a;
            boolean a2 = a(PdMCFRecommendLayerAdapter.this);
            pdMImageExpandableTextView.p = false;
            SpannableString spannableString = a2 ? pdMImageExpandableTextView.f4884m : pdMImageExpandableTextView.f4883l;
            SpannableString spannableString2 = a2 ? pdMImageExpandableTextView.o : pdMImageExpandableTextView.f4885n;
            pdMImageExpandableTextView.f4882k = new SpannableStringBuilder(spannableStringBuilder);
            int i5 = pdMImageExpandableTextView.f4879h;
            pdMImageExpandableTextView.f4881j = new SpannableStringBuilder(spannableStringBuilder);
            if (i5 != -1) {
                int i6 = pdMImageExpandableTextView.f4880i;
                int i7 = Build.VERSION.SDK_INT;
                if (i7 >= 23) {
                    StaticLayout.Builder obtain = StaticLayout.Builder.obtain(spannableStringBuilder, 0, spannableStringBuilder.length(), pdMImageExpandableTextView.getPaint(), i6);
                    obtain.setAlignment(Layout.Alignment.ALIGN_NORMAL);
                    obtain.setIncludePad(pdMImageExpandableTextView.getIncludeFontPadding());
                    obtain.setLineSpacing(pdMImageExpandableTextView.getLineSpacingExtra(), pdMImageExpandableTextView.getLineSpacingMultiplier());
                    staticLayout = obtain.build();
                    i3 = i5;
                    f2 = 0.0f;
                } else if (i7 >= 16) {
                    f2 = 0.0f;
                    i3 = i5;
                    staticLayout = new StaticLayout(spannableStringBuilder, pdMImageExpandableTextView.getPaint(), i6, Layout.Alignment.ALIGN_NORMAL, pdMImageExpandableTextView.getLineSpacingMultiplier(), pdMImageExpandableTextView.getLineSpacingExtra(), pdMImageExpandableTextView.getIncludeFontPadding());
                } else {
                    i3 = i5;
                    f2 = 0.0f;
                    staticLayout = new StaticLayout(spannableStringBuilder, pdMImageExpandableTextView.getPaint(), i6, Layout.Alignment.ALIGN_NORMAL, pdMImageExpandableTextView.a("mSpacingMult", 1.0f), pdMImageExpandableTextView.a("mSpacingAdd", 0.0f), true);
                }
                boolean z = staticLayout.getLineCount() > i3 || pdMImageExpandableTextView.q;
                pdMImageExpandableTextView.p = z;
                if (z) {
                    pdMImageExpandableTextView.f4881j.append((CharSequence) spannableString2);
                    int min = Math.min(i3, staticLayout.getLineCount()) - 1;
                    int lineEnd = staticLayout.getLineEnd(min);
                    int lineStart = staticLayout.getLineStart(min);
                    if (lineEnd != 0) {
                        try {
                            float desiredWidth = (Layout.getDesiredWidth(pdMImageExpandableTextView.f4885n, pdMImageExpandableTextView.getPaint()) * 3.0f) / 2.0f;
                            if ((pdMImageExpandableTextView.f4880i - Layout.getDesiredWidth(spannableStringBuilder.subSequence(lineStart, lineEnd), pdMImageExpandableTextView.getPaint())) - desiredWidth < f2) {
                                char[] cArr = new char[5];
                                spannableStringBuilder.getChars(lineEnd - Math.min(5, spannableStringBuilder.length()), lineEnd, cArr, 0);
                                for (int i8 = 1; i8 < 5; i8++) {
                                    if (Layout.getDesiredWidth(new String(cArr, 4 - i8, i8), pdMImageExpandableTextView.getPaint()) >= desiredWidth) {
                                        i4 = i8;
                                        break;
                                    }
                                }
                            }
                        } catch (Exception unused) {
                        }
                        i4 = 0;
                        pdMImageExpandableTextView.f4882k = new SpannableStringBuilder(pdMImageExpandableTextView.f4882k, 0, lineEnd - i4);
                    }
                    pdMImageExpandableTextView.f4882k.append((CharSequence) spannableString);
                }
            }
            boolean z2 = pdMImageExpandableTextView.p;
            pdMImageExpandableTextView.f4878g = z2;
            if (z2) {
                pdMImageExpandableTextView.setText(pdMImageExpandableTextView.f4882k);
            } else {
                pdMImageExpandableTextView.setText(pdMImageExpandableTextView.f4881j);
            }
            cFProposalHolder.a.setTextColor(com.jd.lib.productdetail.mainimage.utils.a.a(cFProposalHolder.itemView.getContext(), R.color.lib_pd_image_color_1A1A1A, a(PdMCFRecommendLayerAdapter.this)));
        } else if (viewHolder instanceof CFRecommendSkuHolder) {
            CFRecommendSkuHolder cFRecommendSkuHolder = (CFRecommendSkuHolder) viewHolder;
            if (!TextUtils.isEmpty(this.f4676c)) {
                cFRecommendSkuHolder.c(this.b.get(viewHolder.getAdapterPosition() - 1), "");
            } else {
                cFRecommendSkuHolder.c(this.b.get(viewHolder.getAdapterPosition()), "");
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i2 == 0) {
            return new CFProposalHolder(from.inflate(R.layout.lib_pd_mainimage_holder_cf_recommend_layer_head_item, viewGroup, false));
        }
        if (i2 == this.a) {
            return new CFRecommendSkuHolder(from.inflate(R.layout.lib_pd_mainimage_holder_cf_recommend_layer_item, viewGroup, false));
        }
        return null;
    }
}
