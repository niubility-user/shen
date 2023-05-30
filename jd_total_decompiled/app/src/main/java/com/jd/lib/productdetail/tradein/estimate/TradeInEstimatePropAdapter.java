package com.jd.lib.productdetail.tradein.estimate;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.jd.dynamic.DYConstants;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimateData;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimatePropAdapter;
import com.jd.lib.productdetail.tradein.widget.TradeInPropIDialog;
import com.jd.mobile.image.ImageRequestListener;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.display.JDRoundedBitmapDisplayer;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

/* loaded from: classes16.dex */
public class TradeInEstimatePropAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context a;
    public final TradeInEstimateData.Data b;
    public a d;

    /* renamed from: e  reason: collision with root package name */
    public ArrayList<String> f5316e;

    /* renamed from: f  reason: collision with root package name */
    public ArrayList<ArrayList<String>> f5317f;

    /* renamed from: g  reason: collision with root package name */
    public final Drawable f5318g;

    /* renamed from: h  reason: collision with root package name */
    public final Drawable f5319h;

    /* renamed from: c  reason: collision with root package name */
    public ArrayList<TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap> f5315c = new ArrayList<>();

    /* renamed from: i  reason: collision with root package name */
    public boolean f5320i = false;

    /* loaded from: classes16.dex */
    public class TradeInEstimatePropMultiVH extends RecyclerView.ViewHolder {
        public TextView a;
        public TextView b;

        /* renamed from: c  reason: collision with root package name */
        public RecyclerView f5321c;
        public ArrayList<TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap> d;

        /* renamed from: e  reason: collision with root package name */
        public boolean f5322e;

        /* loaded from: classes16.dex */
        public class TradeInPropAttrMultiAdapter extends RecyclerView.Adapter<TradeInMultiPropAttrVH> {

            /* loaded from: classes16.dex */
            public class TradeInMultiPropAttrVH extends RecyclerView.ViewHolder {
                public TextView a;
                public SimpleDraweeView b;

                /* renamed from: c  reason: collision with root package name */
                public View f5324c;
                public View d;

                /* loaded from: classes16.dex */
                public class a implements ImageRequestListener<ImageInfo> {
                    public a() {
                    }

                    @Override // com.jd.mobile.image.ImageRequestListener
                    public void onCancel() {
                    }

                    @Override // com.jd.mobile.image.ImageRequestListener
                    public void onFailure(Throwable th) {
                        TradeInMultiPropAttrVH.this.b.setVisibility(8);
                        TradeInMultiPropAttrVH.this.f5324c.setVisibility(8);
                    }

                    @Override // com.jd.mobile.image.ImageRequestListener
                    public void onSuccess(ImageInfo imageInfo) {
                    }
                }

                /* loaded from: classes16.dex */
                public class b implements DialogInterface.OnClickListener {
                    public b(TradeInMultiPropAttrVH tradeInMultiPropAttrVH) {
                    }

                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        dialogInterface.dismiss();
                    }
                }

                /* loaded from: classes16.dex */
                public class c implements View.OnClickListener {

                    /* renamed from: g  reason: collision with root package name */
                    public final /* synthetic */ TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList f5326g;

                    /* renamed from: h  reason: collision with root package name */
                    public final /* synthetic */ TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap f5327h;

                    public c(TradeInMultiPropAttrVH tradeInMultiPropAttrVH, TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList inquiryItemBasePropValList, TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap) {
                        this.f5326g = inquiryItemBasePropValList;
                        this.f5327h = inquiryItemPropertiesMap;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (this.f5326g.mIsSelectAble) {
                            view.setSelected(!view.isSelected());
                            this.f5327h.reverseCurrentAttrWhenMulti();
                        }
                    }
                }

                public TradeInMultiPropAttrVH(@NonNull View view) {
                    super(view);
                    this.a = (TextView) view.findViewById(R.id.tradein_estimate_old_device_prop_multi_attr);
                    this.b = (SimpleDraweeView) view.findViewById(R.id.tradein_estimate_old_device_prop_multi_attr_desc);
                    this.f5324c = view.findViewById(R.id.tradein_estimate_old_device_prop_multi_attr_desc_shadow);
                    this.d = view.findViewById(R.id.tradein_estimate_old_device_prop_multi_attr_shadow);
                }

                /* JADX INFO: Access modifiers changed from: private */
                public void b(TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList inquiryItemBasePropValList, View view) {
                    if (inquiryItemBasePropValList != null) {
                        TradeInPropIDialog.a aVar = new TradeInPropIDialog.a(TradeInEstimatePropAdapter.this.a);
                        aVar.d = inquiryItemBasePropValList.imageUrls;
                        aVar.f5638c = inquiryItemBasePropValList.textTip;
                        aVar.b = inquiryItemBasePropValList.valueName;
                        aVar.f5639e = new b(this);
                        aVar.a().show();
                    }
                }

                public void c(TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap) {
                    final TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList multiAttrByValType = inquiryItemPropertiesMap.getMultiAttrByValType(0);
                    if (multiAttrByValType == null) {
                        this.itemView.setVisibility(8);
                        return;
                    }
                    this.itemView.setVisibility(0);
                    if (TextUtils.isEmpty(multiAttrByValType.textTip) && TextUtils.isEmpty(multiAttrByValType.imageUrls)) {
                        this.a.setPadding(PDUtils.dip2px(10.0f), 0, PDUtils.dip2px(10.0f), 0);
                        this.b.setVisibility(8);
                        this.f5324c.setVisibility(8);
                    } else {
                        this.a.setPadding(PDUtils.dip2px(10.0f), 0, PDUtils.dip2px(64.0f), 0);
                        this.b.setVisibility(0);
                        String[] strArr = null;
                        if ((TextUtils.isEmpty(multiAttrByValType.imageUrls) || (strArr = multiAttrByValType.imageUrls.split(";")) == null || strArr.length <= 0) ? false : true) {
                            this.b.setBackgroundResource(R.drawable.tradein_estimate_old_device_prop_attr_btn_big_background);
                            this.f5324c.setVisibility(0);
                            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                            createSimple.displayer(new JDRoundedBitmapDisplayer(PDUtils.dip2px(3.0f)));
                            createSimple.resetViewBeforeLoading(false);
                            createSimple.setPlaceholder(17);
                            JDImageLoader.display(strArr[0], this.b, createSimple, new a());
                        } else {
                            this.b.setBackgroundResource(0);
                            this.b.setImageResource(R.drawable.tradein_icon_i);
                            this.f5324c.setVisibility(8);
                        }
                        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.estimate.b
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                TradeInEstimatePropAdapter.TradeInEstimatePropMultiVH.TradeInPropAttrMultiAdapter.TradeInMultiPropAttrVH.this.b(multiAttrByValType, view);
                            }
                        });
                    }
                    multiAttrByValType.mIsSelectAble = true;
                    this.d.setVisibility(8);
                    this.a.setText(multiAttrByValType.valueName);
                    TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList inquiryItemBasePropValList = inquiryItemPropertiesMap.mCurrentVal;
                    if (inquiryItemBasePropValList != null && inquiryItemBasePropValList.valType != 1) {
                        this.itemView.setSelected(true);
                    } else {
                        this.itemView.setSelected(false);
                    }
                    this.itemView.setOnClickListener(new c(this, multiAttrByValType, inquiryItemPropertiesMap));
                }
            }

            public TradeInPropAttrMultiAdapter() {
            }

            @NonNull
            public TradeInMultiPropAttrVH a(@NonNull ViewGroup viewGroup) {
                return new TradeInMultiPropAttrVH(LayoutInflater.from(TradeInEstimatePropAdapter.this.a).inflate(R.layout.tradein_estimate_old_device_multi_attr_item, viewGroup, false));
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return TradeInEstimatePropMultiVH.this.d.size();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(@NonNull TradeInMultiPropAttrVH tradeInMultiPropAttrVH, int i2) {
                tradeInMultiPropAttrVH.c(TradeInEstimatePropMultiVH.this.d.get(i2));
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            @NonNull
            public /* bridge */ /* synthetic */ TradeInMultiPropAttrVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
                return a(viewGroup);
            }
        }

        /* loaded from: classes16.dex */
        public class a extends RecyclerView.ItemDecoration {
            public a(TradeInEstimatePropMultiVH tradeInEstimatePropMultiVH, TradeInEstimatePropAdapter tradeInEstimatePropAdapter) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                if (recyclerView == null || recyclerView.getAdapter() == null) {
                    return;
                }
                int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
                if (childAdapterPosition % 2 == 0) {
                    rect.left = PDUtils.dip2px(10.0f);
                    rect.right = PDUtils.dip2px(5.0f);
                } else {
                    rect.left = PDUtils.dip2px(5.0f);
                    rect.right = PDUtils.dip2px(10.0f);
                }
                if (childAdapterPosition > 1) {
                    rect.top = PDUtils.dip2px(8.0f);
                }
            }
        }

        /* loaded from: classes16.dex */
        public class b implements View.OnClickListener {
            public b(TradeInEstimatePropAdapter tradeInEstimatePropAdapter) {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TradeInEstimatePropMultiVH tradeInEstimatePropMultiVH = TradeInEstimatePropMultiVH.this;
                if (!tradeInEstimatePropMultiVH.f5322e) {
                    tradeInEstimatePropMultiVH.d();
                } else {
                    tradeInEstimatePropMultiVH.a();
                }
            }
        }

        public TradeInEstimatePropMultiVH(@NonNull View view) {
            super(view);
            this.f5322e = true;
            this.a = (TextView) view.findViewById(R.id.tradein_estimate_old_device_prop_multi_name);
            this.b = (TextView) view.findViewById(R.id.tradein_estimate_old_device_prop_multi_value);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.tradein_estimate_old_device_prop_multi_value_container);
            this.f5321c = recyclerView;
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2, 1, false));
            this.f5321c.addItemDecoration(new a(this, TradeInEstimatePropAdapter.this));
            this.b.setOnClickListener(new b(TradeInEstimatePropAdapter.this));
        }

        public void a() {
            this.f5321c.setVisibility(8);
            this.b.setCompoundDrawables(null, null, TradeInEstimatePropAdapter.this.f5318g, null);
            this.f5322e = false;
        }

        public void d() {
            this.f5321c.setVisibility(0);
            this.b.setCompoundDrawables(null, null, TradeInEstimatePropAdapter.this.f5319h, null);
            this.f5322e = true;
        }
    }

    /* loaded from: classes16.dex */
    public class TradeInEstimatePropVH extends RecyclerView.ViewHolder {
        public TextView a;
        public TextView b;

        /* renamed from: c  reason: collision with root package name */
        public TextView f5329c;
        public RecyclerView d;

        /* renamed from: e  reason: collision with root package name */
        public TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap f5330e;

        /* renamed from: f  reason: collision with root package name */
        public View f5331f;

        /* loaded from: classes16.dex */
        public class TradeInPropAttrAdapter extends RecyclerView.Adapter<TradeInPropAttrVH> {

            /* loaded from: classes16.dex */
            public class TradeInPropAttrVH extends RecyclerView.ViewHolder {
                public TextView a;
                public SimpleDraweeView b;

                /* renamed from: c  reason: collision with root package name */
                public View f5333c;
                public View d;

                /* loaded from: classes16.dex */
                public class a implements ImageRequestListener<ImageInfo> {
                    public a() {
                    }

                    @Override // com.jd.mobile.image.ImageRequestListener
                    public void onCancel() {
                    }

                    @Override // com.jd.mobile.image.ImageRequestListener
                    public void onFailure(Throwable th) {
                        TradeInPropAttrVH.this.b.setVisibility(8);
                        TradeInPropAttrVH.this.f5333c.setVisibility(8);
                    }

                    @Override // com.jd.mobile.image.ImageRequestListener
                    public void onSuccess(ImageInfo imageInfo) {
                    }
                }

                /* loaded from: classes16.dex */
                public class b implements DialogInterface.OnClickListener {
                    public b(TradeInPropAttrVH tradeInPropAttrVH) {
                    }

                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        dialogInterface.dismiss();
                    }
                }

                /* loaded from: classes16.dex */
                public class c implements View.OnClickListener {

                    /* renamed from: g  reason: collision with root package name */
                    public final /* synthetic */ TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList f5335g;

                    /* renamed from: h  reason: collision with root package name */
                    public final /* synthetic */ TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap f5336h;

                    public c(TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList inquiryItemBasePropValList, TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap) {
                        this.f5335g = inquiryItemBasePropValList;
                        this.f5336h = inquiryItemPropertiesMap;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (this.f5335g.mIsSelectAble) {
                            if (view.isSelected()) {
                                return;
                            }
                            View view2 = TradeInEstimatePropVH.this.f5331f;
                            if (view2 != null) {
                                view2.setSelected(false);
                            }
                            view.setSelected(true);
                            TradeInEstimatePropVH.this.f5329c.setText(this.f5335g.valueName);
                            TradeInEstimatePropVH.this.f5329c.setVisibility(0);
                            TradeInEstimatePropVH tradeInEstimatePropVH = TradeInEstimatePropVH.this;
                            tradeInEstimatePropVH.f5331f = view;
                            TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap = this.f5336h;
                            inquiryItemPropertiesMap.mCurrentVal = this.f5335g;
                            TradeInEstimatePropAdapter tradeInEstimatePropAdapter = TradeInEstimatePropAdapter.this;
                            tradeInEstimatePropAdapter.f5320i = false;
                            boolean contains = tradeInEstimatePropAdapter.f5315c.contains(inquiryItemPropertiesMap);
                            String str = DYConstants.DY_NULL_STR;
                            if (contains) {
                                int indexOf = tradeInEstimatePropAdapter.f5315c.indexOf(inquiryItemPropertiesMap);
                                tradeInEstimatePropAdapter.f5315c.size();
                                for (int size = tradeInEstimatePropAdapter.f5315c.size() - 1; size > indexOf; size--) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("removePropAfter prop = ");
                                    sb.append(tradeInEstimatePropAdapter.f5315c.get(size) != null ? tradeInEstimatePropAdapter.f5315c.get(size).attrName : DYConstants.DY_NULL_STR);
                                    sb.toString();
                                    tradeInEstimatePropAdapter.f5315c.get(size).mCurrentVal = null;
                                    tradeInEstimatePropAdapter.f5315c.get(size).mExpanded = true;
                                    tradeInEstimatePropAdapter.f5315c.remove(size);
                                }
                            }
                            if (this.f5335g.hasNextKey) {
                                TradeInEstimatePropAdapter tradeInEstimatePropAdapter2 = TradeInEstimatePropAdapter.this;
                                TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap2 = this.f5336h;
                                TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap nextProp = tradeInEstimatePropAdapter2.b.inquiryItemInfo.getNextProp(inquiryItemPropertiesMap2);
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("showNextProp prop = ");
                                sb2.append(inquiryItemPropertiesMap2 != null ? inquiryItemPropertiesMap2.attrName : DYConstants.DY_NULL_STR);
                                sb2.append(" nextProp = ");
                                if (nextProp != null) {
                                    str = nextProp.attrName;
                                }
                                sb2.append(str);
                                sb2.toString();
                                if (nextProp != null) {
                                    if (!tradeInEstimatePropAdapter2.f5315c.contains(nextProp)) {
                                        tradeInEstimatePropAdapter2.f5315c.add(nextProp);
                                    }
                                } else {
                                    ArrayList<TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap> arrayList = tradeInEstimatePropAdapter2.b.inquiryItemInfo.mMultiInquiryItems;
                                    if (arrayList != null && arrayList.size() > 0) {
                                        tradeInEstimatePropAdapter2.f5320i = true;
                                    }
                                }
                                TradeInEstimatePropVH.this.a();
                            } else {
                                ArrayList<TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap> arrayList2 = TradeInEstimatePropAdapter.this.b.inquiryItemInfo.mMultiInquiryItems;
                                if (arrayList2 != null && arrayList2.size() > 0) {
                                    TradeInEstimatePropVH tradeInEstimatePropVH2 = TradeInEstimatePropVH.this;
                                    TradeInEstimatePropAdapter.this.f5320i = true;
                                    tradeInEstimatePropVH2.a();
                                }
                            }
                            TradeInEstimatePropAdapter.this.notifyDataSetChanged();
                            a aVar = TradeInEstimatePropAdapter.this.d;
                            if (aVar != null) {
                                TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap3 = this.f5336h;
                                com.jd.lib.productdetail.tradein.d.d dVar = (com.jd.lib.productdetail.tradein.d.d) aVar;
                                MutableLiveData<PdBaseProtocolLiveData.Result<TradeInEstimateData.Data>> mutableLiveData = dVar.f5287c.f5300m;
                                int maxProgress = (mutableLiveData == null || mutableLiveData.getValue() == null || dVar.f5287c.f5300m.getValue().mData == null) ? 0 : dVar.f5287c.f5300m.getValue().mData.getMaxProgress();
                                if (dVar.a.inquiryItemInfo.getNextProp(inquiryItemPropertiesMap3) == null) {
                                    TradeInEstimateFragment.d(dVar.f5287c, Math.max(0, dVar.b.getItemCount() - (dVar.b.f5320i ? 1 : 0)), maxProgress);
                                    if (!dVar.f5287c.t.isEnabled()) {
                                        dVar.f5287c.t.setEnabled(true);
                                        TradeInViewModel tradeInViewModel = dVar.f5287c.f5294g;
                                        if (tradeInViewModel != null) {
                                            tradeInViewModel.h("Productdetail_yjhxViewPriceExpo", null);
                                        }
                                    }
                                    dVar.f5287c.t.setOnClickListener(new com.jd.lib.productdetail.tradein.d.c(dVar));
                                    return;
                                }
                                try {
                                    RecyclerView recyclerView = dVar.f5287c.w;
                                    if (recyclerView != null && recyclerView.getLayoutManager() != null) {
                                        if (dVar.f5287c.w.getLayoutManager() instanceof LinearLayoutManager) {
                                            ((LinearLayoutManager) dVar.f5287c.w.getLayoutManager()).scrollToPositionWithOffset(dVar.b.getItemCount() - 1, Math.max(0, dVar.f5287c.w.getHeight()) / 3);
                                        } else {
                                            dVar.f5287c.w.getLayoutManager().scrollToPosition(dVar.b.getItemCount() - 1);
                                        }
                                    }
                                } catch (Exception e2) {
                                    ExceptionReporter.reportExceptionToBugly(e2);
                                }
                                TradeInEstimateFragment.d(dVar.f5287c, Math.max(0, dVar.b.getItemCount() - (dVar.b.f5320i ? 2 : 1)), maxProgress);
                                dVar.f5287c.t.setEnabled(false);
                                return;
                            }
                            return;
                        }
                        PDUtils.showToastCenterNormal(TradeInEstimatePropAdapter.this.a, view.getResources().getString(R.string.tradein_estimate_select_able_click_toast, this.f5335g.valueName));
                    }
                }

                public TradeInPropAttrVH(@NonNull View view) {
                    super(view);
                    this.a = (TextView) view.findViewById(R.id.tradein_estimate_old_device_prop_attr);
                    this.b = (SimpleDraweeView) view.findViewById(R.id.tradein_estimate_old_device_prop_attr_desc);
                    this.f5333c = view.findViewById(R.id.tradein_estimate_old_device_prop_attr_desc_shadow);
                    this.d = view.findViewById(R.id.tradein_estimate_old_device_prop_attr_shadow);
                }

                /* JADX INFO: Access modifiers changed from: private */
                public void b(TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList inquiryItemBasePropValList, View view) {
                    if (inquiryItemBasePropValList != null) {
                        TradeInPropIDialog.a aVar = new TradeInPropIDialog.a(TradeInEstimatePropAdapter.this.a);
                        aVar.d = inquiryItemBasePropValList.imageUrls;
                        aVar.f5638c = inquiryItemBasePropValList.textTip;
                        aVar.b = inquiryItemBasePropValList.valueName;
                        aVar.f5639e = new b(this);
                        aVar.a().show();
                    }
                }

                public void c(TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap, final TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList inquiryItemBasePropValList) {
                    boolean z;
                    TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList inquiryItemBasePropValList2;
                    if (inquiryItemPropertiesMap == null || inquiryItemBasePropValList == null) {
                        return;
                    }
                    String str = "TradeInPropAttrAdapter::setupWithData prop = " + inquiryItemPropertiesMap.attrName;
                    if (TextUtils.isEmpty(inquiryItemBasePropValList.textTip) && TextUtils.isEmpty(inquiryItemBasePropValList.imageUrls)) {
                        this.a.setPadding(PDUtils.dip2px(10.0f), 0, PDUtils.dip2px(10.0f), 0);
                        this.b.setVisibility(8);
                        this.f5333c.setVisibility(8);
                    } else {
                        this.a.setPadding(PDUtils.dip2px(10.0f), 0, PDUtils.dip2px(64.0f), 0);
                        this.b.setVisibility(0);
                        String[] strArr = null;
                        if ((TextUtils.isEmpty(inquiryItemBasePropValList.imageUrls) || (strArr = inquiryItemBasePropValList.imageUrls.split(";")) == null || strArr.length <= 0) ? false : true) {
                            this.b.setBackgroundResource(R.drawable.tradein_estimate_old_device_prop_attr_btn_big_background);
                            this.f5333c.setVisibility(0);
                            JDDisplayImageOptions createSimple = JDDisplayImageOptions.createSimple();
                            createSimple.displayer(new JDRoundedBitmapDisplayer(PDUtils.dip2px(3.0f)));
                            createSimple.resetViewBeforeLoading(false);
                            createSimple.setPlaceholder(17);
                            JDImageLoader.display(strArr[0], this.b, createSimple, new a());
                        } else {
                            this.b.setBackgroundResource(0);
                            this.b.setImageResource(R.drawable.tradein_icon_i);
                            this.f5333c.setVisibility(8);
                        }
                        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.estimate.c
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                TradeInEstimatePropAdapter.TradeInEstimatePropVH.TradeInPropAttrAdapter.TradeInPropAttrVH.this.b(inquiryItemBasePropValList, view);
                            }
                        });
                    }
                    inquiryItemBasePropValList.mIsSelectAble = false;
                    ArrayList<String> arrayList = TradeInEstimatePropAdapter.this.f5316e;
                    if (arrayList != null && arrayList.contains(inquiryItemBasePropValList.attrValId)) {
                        inquiryItemBasePropValList.mIsSelectAble = false;
                    } else if (inquiryItemPropertiesMap.attrType == 1) {
                        if (TradeInEstimatePropAdapter.this.f5317f != null) {
                            ArrayList arrayList2 = new ArrayList();
                            int indexOf = TradeInEstimatePropAdapter.this.f5315c.indexOf(inquiryItemPropertiesMap);
                            if (indexOf >= 1) {
                                for (int i2 = 0; i2 < indexOf; i2++) {
                                    TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap2 = TradeInEstimatePropAdapter.this.f5315c.get(i2);
                                    if (inquiryItemPropertiesMap2.attrType == 1 && (inquiryItemBasePropValList2 = inquiryItemPropertiesMap2.mCurrentVal) != null && !TextUtils.isEmpty(inquiryItemBasePropValList2.attrValId)) {
                                        arrayList2.add(inquiryItemPropertiesMap2.mCurrentVal.attrValId);
                                    }
                                }
                            }
                            String str2 = "tBasePropAttrId = " + arrayList2;
                            Iterator<ArrayList<String>> it = TradeInEstimatePropAdapter.this.f5317f.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    z = false;
                                    break;
                                }
                                ArrayList<String> next = it.next();
                                if (next != null && (arrayList2.size() == 0 || next.containsAll(arrayList2))) {
                                    if (next.contains(inquiryItemBasePropValList.attrValId)) {
                                        z = true;
                                        break;
                                    }
                                }
                            }
                            String str3 = "validItemPvIdGroup attrValId = " + inquiryItemBasePropValList.valueName + "-" + inquiryItemBasePropValList.attrValId + "-----result-----" + z;
                            inquiryItemBasePropValList.mIsSelectAble = z;
                        }
                    } else {
                        inquiryItemBasePropValList.mIsSelectAble = true;
                    }
                    this.a.setText(inquiryItemBasePropValList.valueName);
                    if (inquiryItemPropertiesMap.mCurrentVal == inquiryItemBasePropValList) {
                        this.itemView.setSelected(true);
                        TradeInEstimatePropVH.this.f5331f = this.itemView;
                    } else {
                        this.itemView.setSelected(false);
                    }
                    if (inquiryItemBasePropValList.mIsSelectAble) {
                        this.d.setVisibility(8);
                    } else {
                        this.d.setVisibility(0);
                    }
                    this.itemView.setOnClickListener(new c(inquiryItemBasePropValList, inquiryItemPropertiesMap));
                }
            }

            public TradeInPropAttrAdapter() {
            }

            @NonNull
            public TradeInPropAttrVH a(@NonNull ViewGroup viewGroup) {
                return new TradeInPropAttrVH(LayoutInflater.from(TradeInEstimatePropAdapter.this.a).inflate(R.layout.tradein_estimate_old_device_attr_item, viewGroup, false));
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                return TradeInEstimatePropVH.this.f5330e.inquiryItemBasePropValList.size();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(@NonNull TradeInPropAttrVH tradeInPropAttrVH, int i2) {
                TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap = TradeInEstimatePropVH.this.f5330e;
                tradeInPropAttrVH.c(inquiryItemPropertiesMap, inquiryItemPropertiesMap.inquiryItemBasePropValList.get(i2));
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            @NonNull
            public /* bridge */ /* synthetic */ TradeInPropAttrVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
                return a(viewGroup);
            }
        }

        /* loaded from: classes16.dex */
        public class a extends RecyclerView.ItemDecoration {
            public a(TradeInEstimatePropVH tradeInEstimatePropVH, TradeInEstimatePropAdapter tradeInEstimatePropAdapter) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
                super.getItemOffsets(rect, view, recyclerView, state);
                if (recyclerView == null || recyclerView.getAdapter() == null || recyclerView.getChildAdapterPosition(view) <= 0) {
                    return;
                }
                rect.top = PDUtils.dip2px(8.0f);
            }
        }

        public TradeInEstimatePropVH(@NonNull View view) {
            super(view);
            this.a = (TextView) view.findViewById(R.id.tradein_estimate_old_device_prop_name);
            this.b = (TextView) view.findViewById(R.id.tradein_estimate_old_device_prop_hint);
            this.f5329c = (TextView) view.findViewById(R.id.tradein_estimate_old_device_prop_value);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.tradein_estimate_old_device_prop_value_container);
            this.d = recyclerView;
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), 1, false));
            this.d.addItemDecoration(new a(this, TradeInEstimatePropAdapter.this));
            this.f5329c.setVisibility(8);
            this.f5329c.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.estimate.d
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    TradeInEstimatePropAdapter.TradeInEstimatePropVH.this.b(view2);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(View view) {
            if (!this.f5330e.mExpanded) {
                d();
            } else {
                a();
            }
        }

        public void a() {
            this.d.setVisibility(8);
            this.b.setVisibility(8);
            this.f5329c.setCompoundDrawables(null, null, TradeInEstimatePropAdapter.this.f5318g, null);
            this.f5330e.mExpanded = false;
        }

        public void d() {
            if (!TextUtils.isEmpty(this.f5330e.textTip) || !TextUtils.isEmpty(this.f5330e.imageUrls)) {
                this.b.setVisibility(0);
            }
            this.d.setVisibility(0);
            this.f5329c.setCompoundDrawables(null, null, TradeInEstimatePropAdapter.this.f5319h, null);
            this.f5330e.mExpanded = true;
        }
    }

    /* loaded from: classes16.dex */
    public interface a {
    }

    public TradeInEstimatePropAdapter(Context context, TradeInEstimateData.Data data) {
        this.a = context;
        this.b = data;
        TradeInEstimateData.Data.InquiryItemInfo inquiryItemInfo = data.inquiryItemInfo;
        this.f5316e = inquiryItemInfo.unRecyclablePvIds;
        this.f5317f = inquiryItemInfo.validItemPvIdGroup;
        Drawable drawable = context.getResources().getDrawable(R.drawable.tradein_icon_arrow_down_black);
        this.f5318g = drawable;
        Drawable drawable2 = context.getResources().getDrawable(R.drawable.tradein_icon_arrow_up_black);
        this.f5319h = drawable2;
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable2.setBounds(0, 0, drawable2.getIntrinsicWidth(), drawable2.getIntrinsicHeight());
        a(null);
    }

    public final void a(String str) {
        LinkedHashMap<String, TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap> linkedHashMap;
        TradeInEstimateData.Data.InquiryItemInfo inquiryItemInfo = this.b.inquiryItemInfo;
        if (inquiryItemInfo == null || (linkedHashMap = inquiryItemInfo.inquiryItemPropertiesMap) == null || linkedHashMap.size() <= 0 || !TextUtils.isEmpty(null)) {
            return;
        }
        if (!TextUtils.isEmpty(this.b.inquiryItemInfo.headNodeId)) {
            TradeInEstimateData.Data.InquiryItemInfo inquiryItemInfo2 = this.b.inquiryItemInfo;
            if (inquiryItemInfo2.inquiryItemPropertiesMap.containsKey(inquiryItemInfo2.headNodeId)) {
                ArrayList<TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap> arrayList = this.f5315c;
                TradeInEstimateData.Data.InquiryItemInfo inquiryItemInfo3 = this.b.inquiryItemInfo;
                arrayList.add(inquiryItemInfo3.inquiryItemPropertiesMap.get(inquiryItemInfo3.headNodeId));
                return;
            }
        }
        Set<String> keySet = this.b.inquiryItemInfo.inquiryItemPropertiesMap.keySet();
        if (keySet == null || keySet.size() <= 0) {
            return;
        }
        Iterator<String> it = keySet.iterator();
        if (it.hasNext()) {
            this.f5315c.add(this.b.inquiryItemInfo.inquiryItemPropertiesMap.get(it.next()));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5315c.size() + (((this.b.inquiryItemInfo.mMultiInquiryItems.size() > 0) && this.f5320i) ? 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return i2 < this.b.inquiryItemInfo.getMaxProgress() ? 3 : 4;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        String str = "TradeInEstimatePropAdapter::onBindViewHolder position = " + i2 + " holder = " + viewHolder;
        if (viewHolder instanceof TradeInEstimatePropVH) {
            ArrayList<TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap> arrayList = this.f5315c;
            if (arrayList != null) {
                TradeInEstimatePropVH tradeInEstimatePropVH = (TradeInEstimatePropVH) viewHolder;
                TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap inquiryItemPropertiesMap = arrayList.get(Math.min(i2, arrayList.size() - 1));
                tradeInEstimatePropVH.getClass();
                StringBuilder sb = new StringBuilder();
                sb.append("TradeInEstimatePropVH::setupWithData prop = ");
                sb.append(inquiryItemPropertiesMap != null ? inquiryItemPropertiesMap.attrName : DYConstants.DY_NULL_STR);
                sb.toString();
                tradeInEstimatePropVH.f5330e = inquiryItemPropertiesMap;
                if (inquiryItemPropertiesMap.isMulti()) {
                    tradeInEstimatePropVH.a.setVisibility(8);
                    tradeInEstimatePropVH.d.setVisibility(8);
                    tradeInEstimatePropVH.f5329c.setVisibility(8);
                } else {
                    tradeInEstimatePropVH.a.setVisibility(0);
                    tradeInEstimatePropVH.d.setVisibility(0);
                    tradeInEstimatePropVH.f5329c.setVisibility(0);
                    if (tradeInEstimatePropVH.f5330e.mExpanded) {
                        tradeInEstimatePropVH.d();
                    } else {
                        tradeInEstimatePropVH.a();
                    }
                    tradeInEstimatePropVH.a.setText(inquiryItemPropertiesMap.attrName);
                    TradeInEstimateData.Data.InquiryItemInfo.InquiryItemPropertiesMap.InquiryItemBasePropValList inquiryItemBasePropValList = inquiryItemPropertiesMap.mCurrentVal;
                    if (inquiryItemBasePropValList != null && !TextUtils.isEmpty(inquiryItemBasePropValList.valueName)) {
                        tradeInEstimatePropVH.f5329c.setVisibility(0);
                        tradeInEstimatePropVH.f5329c.setText(inquiryItemPropertiesMap.mCurrentVal.valueName);
                    } else {
                        tradeInEstimatePropVH.f5329c.setVisibility(8);
                    }
                    tradeInEstimatePropVH.b.setVisibility(8);
                    if (!TextUtils.isEmpty(inquiryItemPropertiesMap.textTip) || !TextUtils.isEmpty(inquiryItemPropertiesMap.imageUrls)) {
                        if (inquiryItemPropertiesMap.mExpanded) {
                            tradeInEstimatePropVH.b.setVisibility(0);
                        }
                        tradeInEstimatePropVH.b.setText("\u5982\u4f55\u67e5\u770b" + inquiryItemPropertiesMap.attrName);
                        tradeInEstimatePropVH.b.setOnClickListener(new com.jd.lib.productdetail.tradein.d.e(tradeInEstimatePropVH, inquiryItemPropertiesMap));
                    }
                    tradeInEstimatePropVH.d.setAdapter(new TradeInEstimatePropVH.TradeInPropAttrAdapter());
                }
                if (i2 == 0) {
                    viewHolder.itemView.setPadding(0, PDUtils.dip2px(8.0f), 0, PDUtils.dip2px(12.0f));
                } else {
                    viewHolder.itemView.setPadding(0, PDUtils.dip2px(12.0f), 0, PDUtils.dip2px(12.0f));
                }
            }
        } else if (viewHolder instanceof TradeInEstimatePropMultiVH) {
            TradeInEstimatePropMultiVH tradeInEstimatePropMultiVH = (TradeInEstimatePropMultiVH) viewHolder;
            tradeInEstimatePropMultiVH.d = this.b.inquiryItemInfo.mMultiInquiryItems;
            if (tradeInEstimatePropMultiVH.f5322e) {
                tradeInEstimatePropMultiVH.d();
            } else {
                tradeInEstimatePropMultiVH.a();
            }
            String string = tradeInEstimatePropMultiVH.itemView.getResources().getString(R.string.tradein_estimate_multi_prop_title);
            String string2 = tradeInEstimatePropMultiVH.itemView.getResources().getString(R.string.tradein_estimate_multi_prop_sub_title);
            SpannableString spannableString = new SpannableString(string + string2);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor(JDDarkUtil.COLOR_808080)), string.length(), string.length() + string2.length(), 17);
            spannableString.setSpan(new AbsoluteSizeSpan(PDUtils.sp2px(tradeInEstimatePropMultiVH.itemView.getContext(), 14.0f)), string.length(), string.length() + string2.length(), 17);
            spannableString.setSpan(new StyleSpan(0), string.length(), string.length() + string2.length(), 17);
            tradeInEstimatePropMultiVH.a.setText(spannableString);
            tradeInEstimatePropMultiVH.f5321c.setAdapter(new TradeInEstimatePropMultiVH.TradeInPropAttrMultiAdapter());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        RecyclerView.ViewHolder tradeInEstimatePropVH;
        if (i2 == 3) {
            tradeInEstimatePropVH = new TradeInEstimatePropVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_estimate_old_device_prop, viewGroup, false));
        } else if (i2 != 4) {
            return null;
        } else {
            tradeInEstimatePropVH = new TradeInEstimatePropMultiVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tradein_estimate_old_device_prop_multi, viewGroup, false));
        }
        return tradeInEstimatePropVH;
    }
}
