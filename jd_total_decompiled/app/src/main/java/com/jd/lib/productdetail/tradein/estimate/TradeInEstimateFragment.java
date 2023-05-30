package com.jd.lib.productdetail.tradein.estimate;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInAddressInfo;
import com.jd.lib.productdetail.tradein.TradeInDialogFragment;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.d.f;
import com.jd.lib.productdetail.tradein.estimate.TradeInEstimateData;
import com.jd.lib.productdetail.tradein.selectdevice.TradeInSelectDeviceData;
import com.jd.lib.productdetail.tradein.widget.TradeInInquiryStepProgressView;
import com.jd.lib.productdetail.tradein.widget.TradeInTitle;
import com.jd.lib.productdetail.tradein.widget.TradeinErrorView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jdmiaosha.utils.cache.JDNetCacheManager;
import com.jingdong.common.unification.customtheme.CustomThemeConstance;
import com.jingdong.jdsdk.utils.NetUtils;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.HashMap;

/* loaded from: classes16.dex */
public class TradeInEstimateFragment extends Fragment {

    /* renamed from: g */
    public TradeInViewModel f5294g;

    /* renamed from: h */
    public TradeInDialogFragment f5295h;

    /* renamed from: i */
    public int f5296i;

    /* renamed from: j */
    public TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries f5297j;

    /* renamed from: k */
    public TradeInSelectDeviceData.Data.CategoriesInfo.CateItem f5298k;

    /* renamed from: l */
    public TradeInSelectDeviceData.Data.TagsInfo.TagItem f5299l;

    /* renamed from: m */
    public MutableLiveData<PdBaseProtocolLiveData.Result<TradeInEstimateData.Data>> f5300m;

    /* renamed from: n */
    public View f5301n;
    public TradeinErrorView o;
    public TradeInTitle p;
    public TextView q;
    public View r;
    public boolean s;
    public View t;
    public TradeInInquiryStepProgressView u;
    public TradeInEstimateOldDevice v;
    public RecyclerView w;
    public View x;

    /* loaded from: classes16.dex */
    public class a implements Observer<PdBaseProtocolLiveData.Result<TradeInEstimateData.Data>> {
        public a() {
            TradeInEstimateFragment.this = r1;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInEstimateData.Data> result) {
            PdBaseProtocolLiveData.Result<TradeInEstimateData.Data> result2 = result;
            StringBuilder sb = new StringBuilder();
            sb.append("mEstimateInfoLiveData onChanged result = ");
            sb.append(result2 != null ? result2.mStatus : " null");
            sb.toString();
            if (result2 != null) {
                PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
                if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FETCHING) {
                    TradeInEstimateFragment.this.e(true, false);
                } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.SUCCESS) {
                    TradeInEstimateData.Data data = result2.mData;
                    if (data != null && data.isValid()) {
                        TradeInEstimateFragment.this.e(false, false);
                        TradeInEstimateFragment tradeInEstimateFragment = TradeInEstimateFragment.this;
                        TradeInEstimateData.Data data2 = result2.mData;
                        tradeInEstimateFragment.getClass();
                        if (data2 == null || !data2.isValid()) {
                            return;
                        }
                        tradeInEstimateFragment.p.b(data2.ruleInfo);
                        if (!TextUtils.isEmpty(data2.servicePointText)) {
                            tradeInEstimateFragment.q.setVisibility(0);
                            tradeInEstimateFragment.q.setText(data2.servicePointText);
                        } else {
                            tradeInEstimateFragment.q.setVisibility(8);
                        }
                        TradeInEstimateOldDevice tradeInEstimateOldDevice = tradeInEstimateFragment.v;
                        tradeInEstimateOldDevice.f5313n = tradeInEstimateFragment.f5296i;
                        tradeInEstimateOldDevice.o = data2;
                        tradeInEstimateOldDevice.p = tradeInEstimateFragment.s;
                        tradeInEstimateOldDevice.q = tradeInEstimateFragment.f5295h;
                        tradeInEstimateOldDevice.a(data2.oldProductInfo);
                        tradeInEstimateFragment.v.r = new com.jd.lib.productdetail.tradein.d.b(tradeInEstimateFragment);
                        TradeInEstimatePropAdapter tradeInEstimatePropAdapter = new TradeInEstimatePropAdapter(tradeInEstimateFragment.getContext(), data2);
                        tradeInEstimatePropAdapter.d = new com.jd.lib.productdetail.tradein.d.d(tradeInEstimateFragment, data2, tradeInEstimatePropAdapter);
                        tradeInEstimateFragment.w.setAdapter(tradeInEstimatePropAdapter);
                        return;
                    }
                    TradeInEstimateFragment.this.e(false, true);
                } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                    TradeInEstimateFragment.this.e(false, true);
                }
            }
        }
    }

    /* loaded from: classes16.dex */
    public class b implements View.OnClickListener {
        public b() {
            TradeInEstimateFragment.this = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TradeInEstimateFragment.this.a();
        }
    }

    /* loaded from: classes16.dex */
    public class c implements Observer<PdBaseProtocolLiveData.Result<TradeInEstimateData>> {

        /* renamed from: g */
        public final /* synthetic */ MutableLiveData f5304g;

        public c(MutableLiveData mutableLiveData) {
            TradeInEstimateFragment.this = r1;
            this.f5304g = mutableLiveData;
        }

        @Override // androidx.lifecycle.Observer
        public void onChanged(PdBaseProtocolLiveData.Result<TradeInEstimateData> result) {
            PdBaseProtocolLiveData.Result<TradeInEstimateData> result2 = result;
            PdBaseProtocolLiveData.Result<TradeInEstimateData.Data> result3 = new PdBaseProtocolLiveData.Result<>(result2.mStatus, null, "");
            PdBaseProtocolLiveData.Result.DataStatus dataStatus = result2.mStatus;
            PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = PdBaseProtocolLiveData.Result.DataStatus.SUCCESS;
            if (dataStatus == dataStatus2) {
                TradeInEstimateData tradeInEstimateData = result2.mData;
                if (tradeInEstimateData != null && tradeInEstimateData.isValid()) {
                    result3 = new PdBaseProtocolLiveData.Result<>(dataStatus2, result2.mData.data, "");
                } else {
                    result3 = new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.FAIL, null, "");
                }
                this.f5304g.removeObserver(this);
            } else if (dataStatus == PdBaseProtocolLiveData.Result.DataStatus.FAIL) {
                this.f5304g.removeObserver(this);
            }
            TradeInEstimateFragment.this.f5300m.setValue(result3);
        }
    }

    public TradeInEstimateFragment(BaseActivity baseActivity, TradeInDialogFragment tradeInDialogFragment) {
        super(R.layout.tradein_estimate_fragment_root);
        this.f5300m = new MutableLiveData<>();
        this.s = false;
        this.f5295h = tradeInDialogFragment;
    }

    public static /* synthetic */ void c(View view) {
    }

    public static void d(TradeInEstimateFragment tradeInEstimateFragment, int i2, int i3) {
        TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem;
        if (i2 > 0 && tradeInEstimateFragment.u.getVisibility() != 0 && (tagItem = tradeInEstimateFragment.f5299l) != null && tagItem.tagType == 2) {
            tradeInEstimateFragment.u.setVisibility(0);
            tradeInEstimateFragment.p.setVisibility(4);
        }
        TradeInInquiryStepProgressView tradeInInquiryStepProgressView = tradeInEstimateFragment.u;
        tradeInInquiryStepProgressView.getClass();
        if (i2 < 0 || i3 <= 0) {
            return;
        }
        tradeInInquiryStepProgressView.f5634l = Math.min(i2, i3);
        tradeInInquiryStepProgressView.f5633k = i3;
        tradeInInquiryStepProgressView.invalidate();
    }

    public static /* synthetic */ void f(View view) {
    }

    public void a() {
        e(true, false);
        TradeInViewModel tradeInViewModel = this.f5294g;
        TradeInSelectDeviceData.Data.TagsInfo.TagItem tagItem = this.f5299l;
        TradeInSelectDeviceData.Data.CategoriesInfo.CateItem cateItem = this.f5298k;
        TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries oldProductInquiries = this.f5297j;
        int i2 = this.f5296i;
        tradeInViewModel.getClass();
        HashMap hashMap = new HashMap();
        hashMap.put("settleType", Integer.valueOf(tradeInViewModel.f5258j));
        hashMap.put("tradeType", Integer.valueOf(tradeInViewModel.f5256h));
        hashMap.put("bizCode", Integer.valueOf(tradeInViewModel.f5255g));
        hashMap.put("skuId", tradeInViewModel.f5253e);
        hashMap.put(JDNetCacheManager.BRAND_BIZKEY, BaseInfo.getDeviceBrand());
        hashMap.put(CustomThemeConstance.NAVI_MODEL, BaseInfo.getDeviceModel());
        hashMap.put("extend", tradeInViewModel.t);
        JDJSONObject jDJSONObject = tradeInViewModel.s;
        if (jDJSONObject != null) {
            hashMap.put("extension", jDJSONObject);
        }
        if (oldProductInquiries != null) {
            hashMap.put("oldProductId", oldProductInquiries.oldProductId);
            TradeInAddressInfo c2 = tradeInViewModel.c();
            if (c2 != null) {
                hashMap.put("addressInfo", c2);
            }
            if (tagItem != null) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("tagId", tagItem.tagId);
                hashMap2.put("tagType", tagItem.tagType + "");
                hashMap.put("tagInfo", hashMap2);
                hashMap.put("inquiryType", Integer.valueOf(i2));
                int i3 = tagItem.tagType;
                if (i3 == 1) {
                    hashMap.put("inquiryId", oldProductInquiries.inquiryId);
                    hashMap.put("oldProductName", oldProductInquiries.oldProductName);
                    if (cateItem != null) {
                        hashMap.put("jdCat", cateItem.jdCat);
                    }
                } else if (i3 == 2) {
                    hashMap.put("venderId", oldProductInquiries.venderId);
                    if (i2 == 2 || i2 == 3) {
                        hashMap.put("inquiryId", oldProductInquiries.inquiryId);
                    }
                }
            }
        }
        f fVar = new f(hashMap);
        fVar.request(tradeInViewModel.b);
        MutableLiveData<PdBaseProtocolLiveData.Result<T>> mutableLiveData = fVar.mResult;
        mutableLiveData.observe(getViewLifecycleOwner(), new c(mutableLiveData));
    }

    public final void b(int i2) {
        TradeInTitle tradeInTitle = this.p;
        if (tradeInTitle != null) {
            tradeInTitle.setVisibility(i2);
        }
        TextView textView = this.q;
        if (textView != null) {
            textView.setVisibility(i2);
        }
        View view = this.r;
        if (view != null) {
            view.setVisibility(i2);
        }
    }

    public final void e(boolean z, boolean z2) {
        View view;
        if (this.o == null || (view = this.f5301n) == null) {
            return;
        }
        if (z) {
            b(8);
            this.o.setVisibility(8);
            this.f5301n.setVisibility(0);
            return;
        }
        view.setVisibility(8);
        if (z2) {
            b(8);
            this.o.setVisibility(0);
            if (NetUtils.isNetworkAvailable()) {
                this.o.a(TradeinErrorView.a.UNKNOWN);
                return;
            } else {
                this.o.a(TradeinErrorView.a.NONET);
                return;
            }
        }
        this.o.setVisibility(8);
        b(0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f5294g = (TradeInViewModel) new ViewModelProvider(this.f5295h).get(TradeInViewModel.class);
        if (getArguments() != null) {
            this.s = getArguments().getBoolean("extra.key.from.tradein.page", false);
            this.f5296i = getArguments().getInt("extra.key.estimate.type", 1);
            this.f5297j = (TradeInSelectDeviceData.Data.InquiriesInfo.OldProductInquiries) getArguments().getSerializable("extra.key.old.device");
            this.f5299l = (TradeInSelectDeviceData.Data.TagsInfo.TagItem) getArguments().getSerializable("extra.key.old.device.tag");
            this.f5298k = (TradeInSelectDeviceData.Data.CategoriesInfo.CateItem) getArguments().getSerializable("extra.key.old.device.cate");
            TradeInEstimateData.Data data = (TradeInEstimateData.Data) getArguments().getSerializable("extra.key.old.device.estimate");
            if (data != null) {
                this.f5300m.setValue(new PdBaseProtocolLiveData.Result<>(PdBaseProtocolLiveData.Result.DataStatus.SUCCESS, data, ""));
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.estimate.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInEstimateFragment.c(view2);
            }
        });
        this.f5301n = view.findViewById(R.id.tradein_estimate_loading);
        this.o = (TradeinErrorView) view.findViewById(R.id.tradein_estimate_error_view);
        this.r = view.findViewById(R.id.tradein_estimate_content);
        this.p = (TradeInTitle) view.findViewById(R.id.tradein_estimate_title);
        this.q = (TextView) view.findViewById(R.id.tradein_estimate_servicePointText);
        this.t = view.findViewById(R.id.tradein_estimate_btn_ok);
        this.u = (TradeInInquiryStepProgressView) view.findViewById(R.id.tradein_estimate_progressbar);
        this.v = (TradeInEstimateOldDevice) view.findViewById(R.id.tradein_estimate_old_local_device);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.tradein_estimate_estimate_prop_list);
        this.w = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        View findViewById = view.findViewById(R.id.tradein_estimate_function_loading);
        this.x = findViewById;
        findViewById.setVisibility(8);
        this.x.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.estimate.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInEstimateFragment.f(view2);
            }
        });
        if (this.f5300m.getValue() == null && this.f5297j != null && this.f5299l != null) {
            a();
        }
        this.f5300m.observe(getViewLifecycleOwner(), new a());
        this.o.f5654i.setOnClickListener(new b());
    }
}
