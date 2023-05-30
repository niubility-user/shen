package com.jd.lib.productdetail.tradein.ways;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInDialogFragment;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreFragment;
import com.jd.lib.productdetail.tradein.time.TradeInSelectWishTimeForAppliancesFragment;
import com.jd.lib.productdetail.tradein.time.TradeInSelectWishTimeFragment;
import com.jd.lib.productdetail.tradein.ways.TradeInTradeWaysResp;
import com.jd.lib.productdetail.tradein.widget.TradeinErrorView;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.network.toolbox.ExceptionReporter;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.jdsdk.utils.NetUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeInTradeWaysFragment extends Fragment {
    public PdBaseProtocolLiveData.Result.DataStatus A;

    /* renamed from: g  reason: collision with root package name */
    public BaseActivity f5609g;

    /* renamed from: h  reason: collision with root package name */
    public TradeInDialogFragment f5610h;

    /* renamed from: i  reason: collision with root package name */
    public final List<TradeInTradeWaysResp.Data.TradeModeItem> f5611i;

    /* renamed from: j  reason: collision with root package name */
    public TradeInViewModel f5612j;

    /* renamed from: k  reason: collision with root package name */
    public View f5613k;

    /* renamed from: l  reason: collision with root package name */
    public TradeinErrorView f5614l;

    /* renamed from: m  reason: collision with root package name */
    public View f5615m;

    /* renamed from: n  reason: collision with root package name */
    public RecyclerView f5616n;
    public TextView o;
    public View p;
    public View q;
    public TextView r;
    public ViewGroup s;
    public TextView t;
    public View u;
    public TextView v;
    public TextView w;
    public TextView x;
    public View y;
    public TradeInResultData.TradeInWareCardInfo z;

    /* loaded from: classes16.dex */
    public class a extends RecyclerView.ItemDecoration {
        public a(TradeInTradeWaysFragment tradeInTradeWaysFragment) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            rect.right = PDUtils.dip2px(12.0f);
        }
    }

    public TradeInTradeWaysFragment(BaseActivity baseActivity, TradeInDialogFragment tradeInDialogFragment) {
        super(R.layout.tradein_trade_ways_fragment_root);
        this.f5611i = new ArrayList();
        this.A = PdBaseProtocolLiveData.Result.DataStatus.NONE;
        this.f5609g = baseActivity;
        this.f5610h = tradeInDialogFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        k();
    }

    public static /* synthetic */ void h(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l(View view) {
        o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void n(View view) {
        o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q(View view) {
        TradeInTradeWaysResp.Data.TradeModeItem m2;
        if (this.A == PdBaseProtocolLiveData.Result.DataStatus.FETCHING || !PDUtils.repeatClick() || (m2 = m()) == null) {
            return;
        }
        this.z.setCurrentTranMode(getContext(), m2.tradeModeId, m2.tradeModeName, m2.defaultPromiseDate, m2.storeInfo);
        boolean z = this.z.cardType == 4;
        PdBaseProtocolLiveData.Result<TradeInResultData> value = this.f5612j.u.getValue();
        if (z && value != null) {
            value.mStatus = PdBaseProtocolLiveData.Result.DataStatus.DIRTY;
        }
        this.f5612j.u.setValue(value);
        getParentFragmentManager().popBackStack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u() {
        TradeInDialogFragment tradeInDialogFragment;
        if (getChildFragmentManager().getBackStackEntryCount() == 0 && (tradeInDialogFragment = this.f5610h) != null) {
            tradeInDialogFragment.setTitle(getString(R.string.tradein_step_tranway_title));
        }
    }

    public final void a() {
        final FragmentManager.OnBackStackChangedListener onBackStackChangedListener = new FragmentManager.OnBackStackChangedListener() { // from class: com.jd.lib.productdetail.tradein.ways.i
            @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
            public final void onBackStackChanged() {
                TradeInTradeWaysFragment.this.u();
            }
        };
        getChildFragmentManager().addOnBackStackChangedListener(onBackStackChangedListener);
        getViewLifecycleOwner().getLifecycle().addObserver(new LifecycleEventObserver() { // from class: com.jd.lib.productdetail.tradein.ways.TradeInTradeWaysFragment.1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    TradeInTradeWaysFragment.this.getViewLifecycleOwner().getLifecycle().removeObserver(this);
                    TradeInTradeWaysFragment.this.getChildFragmentManager().removeOnBackStackChangedListener(onBackStackChangedListener);
                }
            }
        });
    }

    public final void c(PdBaseProtocolLiveData.Result<TradeInTradeWaysResp> result) {
        TradeInTradeWaysResp.Data data;
        List<TradeInTradeWaysResp.Data.TradeModeItem> list;
        PdBaseProtocolLiveData.Result.DataStatus dataStatus = result.mStatus;
        this.A = dataStatus;
        if (PdBaseProtocolLiveData.Result.DataStatus.FETCHING == dataStatus) {
            if (m() == null) {
                r();
            }
            this.f5614l.setVisibility(8);
            this.f5613k.setVisibility(0);
        } else if (PdBaseProtocolLiveData.Result.DataStatus.SUCCESS == dataStatus) {
            t();
            this.f5614l.setVisibility(8);
            TradeInTradeWaysResp tradeInTradeWaysResp = result.mData;
            if (tradeInTradeWaysResp != null && "0".equals(tradeInTradeWaysResp.code) && (data = tradeInTradeWaysResp.data) != null && (list = data.tradeModes) != null && list.size() != 0) {
                this.y.setVisibility(0);
                this.f5616n.setVisibility(0);
                this.o.setVisibility(0);
                this.p.setVisibility(0);
                this.q.setVisibility(0);
                this.r.setVisibility(0);
                this.t.setVisibility(0);
                this.u.setVisibility(0);
                List<TradeInTradeWaysResp.Data.TradeModeItem> list2 = tradeInTradeWaysResp.data.tradeModes;
                if (this.f5611i.size() == 0) {
                    this.f5611i.addAll(list2);
                    w();
                    for (TradeInTradeWaysResp.Data.TradeModeItem tradeModeItem : list2) {
                        if (tradeModeItem.selected != 1) {
                            tradeModeItem.storeInfo = null;
                            tradeModeItem.defaultPromiseDate = null;
                        }
                    }
                } else {
                    Iterator<TradeInTradeWaysResp.Data.TradeModeItem> it = list2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        TradeInTradeWaysResp.Data.TradeModeItem next = it.next();
                        if (next.selected == 1) {
                            Iterator<TradeInTradeWaysResp.Data.TradeModeItem> it2 = this.f5611i.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                TradeInTradeWaysResp.Data.TradeModeItem next2 = it2.next();
                                int i2 = next.tradeModeId;
                                if (i2 == next2.tradeModeId) {
                                    if (i2 == 5) {
                                        next2.storeInfo = next.storeInfo;
                                    } else {
                                        next2.defaultPromiseDate = next.defaultPromiseDate;
                                    }
                                }
                            }
                        }
                    }
                }
                for (TradeInTradeWaysResp.Data.TradeModeItem tradeModeItem2 : list2) {
                    if (tradeModeItem2.selected == 1) {
                        j(tradeModeItem2);
                        return;
                    }
                }
                return;
            }
            t();
            r();
            x();
        } else if (PdBaseProtocolLiveData.Result.DataStatus.FAIL == dataStatus) {
            t();
            r();
            x();
        }
    }

    public final void d(TradeInTradeWaysResp.Data.TradeModeItem tradeModeItem) {
        if (this.A == PdBaseProtocolLiveData.Result.DataStatus.FETCHING) {
            return;
        }
        if (tradeModeItem.defaultPromiseDate == null && tradeModeItem.storeInfo == null) {
            k();
        } else {
            j(tradeModeItem);
        }
    }

    public final void e(String str) {
        getChildFragmentManager().setFragmentResultListener(str, getViewLifecycleOwner(), new FragmentResultListener() { // from class: com.jd.lib.productdetail.tradein.ways.k
            @Override // androidx.fragment.app.FragmentResultListener
            public final void onFragmentResult(String str2, Bundle bundle) {
                TradeInTradeWaysFragment.this.f(str2, bundle);
            }
        });
    }

    public final void f(String str, Bundle bundle) {
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo;
        TradeInResultData.BarterText.BarterOperateEvent.Data currentTranMode;
        TradeInTradeWaysResp.Data.TradeModeItem m2;
        if ("extra.key.from.tradein.tradeways.req".equals(str)) {
            TradeInSelectWishTimeOrStoreRet tradeInSelectWishTimeOrStoreRet = (TradeInSelectWishTimeOrStoreRet) bundle.getParcelable("extra.key.from.tradein.tradeways.ret");
            if (tradeInSelectWishTimeOrStoreRet == null || (m2 = m()) == null) {
                return;
            }
            m2.defaultPromiseDate = tradeInSelectWishTimeOrStoreRet.date;
            m2.storeInfo = tradeInSelectWishTimeOrStoreRet.store;
            j(m2);
            return;
        }
        TradeInSelectWishTimeOrStoreRet tradeInSelectWishTimeOrStoreRet2 = (TradeInSelectWishTimeOrStoreRet) bundle.getParcelable("extra.key.from.tradein.tradeways.ret");
        if (tradeInSelectWishTimeOrStoreRet2 == null || (tradeInWareCardInfo = this.z) == null || (currentTranMode = tradeInWareCardInfo.getCurrentTranMode()) == null) {
            return;
        }
        tradeInWareCardInfo.setCurrentTranMode(getContext(), currentTranMode.tradeModeId, currentTranMode.tradeModeName, tradeInSelectWishTimeOrStoreRet2.date, currentTranMode.storeInfo);
        boolean z = tradeInWareCardInfo.cardType == 4;
        PdBaseProtocolLiveData.Result<TradeInResultData> value = this.f5612j.u.getValue();
        if (z && value != null) {
            value.mStatus = PdBaseProtocolLiveData.Result.DataStatus.DIRTY;
        }
        this.f5612j.u.setValue(value);
        getParentFragmentManager().popBackStack();
    }

    public final void g() {
        this.f5615m.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.ways.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInTradeWaysFragment.this.b(view);
            }
        });
    }

    public final void j(TradeInTradeWaysResp.Data.TradeModeItem tradeModeItem) {
        this.o.setText(tradeModeItem.desc);
        this.o.setContentDescription(tradeModeItem.desc);
        int i2 = tradeModeItem.tradeModeId;
        if (i2 == 3) {
            this.u.setVisibility(8);
            this.t.setVisibility(0);
            this.r.setText(getString(R.string.tradein_step_time_title));
            TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate defaultPromiseDate = tradeModeItem.defaultPromiseDate;
            if (defaultPromiseDate != null) {
                this.t.setText(defaultPromiseDate.displayText);
            }
        } else if (i2 == 4) {
            this.u.setVisibility(8);
            this.t.setVisibility(0);
            this.r.setText(getString(R.string.tradein_step_time_title));
            TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate defaultPromiseDate2 = tradeModeItem.defaultPromiseDate;
            if (defaultPromiseDate2 != null) {
                this.t.setText(defaultPromiseDate2.displayText);
            }
        } else if (i2 == 5) {
            this.t.setVisibility(8);
            this.u.setVisibility(0);
            this.r.setText(getString(R.string.tradein_step_store_address_title));
            TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo storeInfo = tradeModeItem.storeInfo;
            if (storeInfo != null) {
                this.v.setText(storeInfo.storeName);
                this.w.setText(getString(R.string.tradein_step_store_address_detail, storeInfo.storeAddress));
                this.x.setText(storeInfo.openTime);
            }
        }
        try {
            StringBuilder sb = new StringBuilder();
            CharSequence text = this.r.getText();
            if (!TextUtils.isEmpty(text)) {
                sb.append(text);
            }
            if (this.t.getVisibility() == 0) {
                sb.append(this.t.getText());
            }
            this.s.setContentDescription(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            if (this.u.getVisibility() == 0) {
                sb2.append(this.v.getText());
                sb2.append(this.w.getText());
                sb2.append(this.x.getText());
                this.u.setContentDescription(sb2.toString());
            }
        } catch (Exception e2) {
            ExceptionReporter.reportExceptionToBugly(e2);
        }
    }

    public final void k() {
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo;
        ArrayList<TradeInResultData.TradeInWareCardInfo.TradeInWareInfo> arrayList;
        TradeInViewModel tradeInViewModel = this.f5612j;
        if (tradeInViewModel == null || (tradeInWareCardInfo = this.z) == null || (arrayList = tradeInWareCardInfo.wareList) == null || arrayList.size() == 0 || this.z.wareList.get(0) == null || this.z.wareList.get(0).tagInfo == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(tradeInViewModel.t)) {
            hashMap.put("extend", tradeInViewModel.t);
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("tagId", this.z.wareList.get(0).tagInfo.tagId);
        hashMap2.put("tagType", this.z.wareList.get(0).tagInfo.tagType + "");
        hashMap.put("tagInfo", hashMap2);
        TradeInResultData.TradeInWareCardInfo.TradeInWareInfo.OldProductInfoForClap oldProductInfoForClap = this.z.wareList.get(0).oldProductInfoForClap;
        if (this.z.cardType == 4 && oldProductInfoForClap != null && !TextUtils.isEmpty(oldProductInfoForClap.venderId)) {
            hashMap.put("venderId", oldProductInfoForClap.venderId);
        }
        hashMap.put("tradeType", Integer.valueOf(tradeInViewModel.f5256h));
        HashMap hashMap3 = new HashMap();
        hashMap3.put("inquiryIds", this.z.getInquiryIds());
        hashMap.put("inquiryVenderProductInfo", hashMap3);
        JDJSONObject jDJSONObject = tradeInViewModel.s;
        if (jDJSONObject != null) {
            hashMap.put("extension", jDJSONObject);
        }
        hashMap.put("addressInfo", tradeInViewModel.c());
        TradeInTradeWaysResp.Data.TradeModeItem m2 = m();
        if (m2 != null) {
            HashMap hashMap4 = new HashMap();
            hashMap4.put("tradeModeId", Integer.valueOf(m2.tradeModeId));
            hashMap.put("selectedTradeModeInfo", hashMap4);
        } else {
            TradeInResultData.BarterText.BarterOperateEvent.Data currentTranMode = this.z.getCurrentTranMode();
            if (currentTranMode != null) {
                HashMap hashMap5 = new HashMap();
                hashMap5.put("tradeModeId", Integer.valueOf(currentTranMode.tradeModeId));
                TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo storeInfo = currentTranMode.storeInfo;
                if (storeInfo != null) {
                    hashMap5.put("storeId", Integer.valueOf(storeInfo.storeId));
                }
                TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate defaultPromiseDate = currentTranMode.defaultPromiseDate;
                if (defaultPromiseDate != null) {
                    hashMap5.put("recycleDate", defaultPromiseDate.date);
                    hashMap5.put("startTime", currentTranMode.defaultPromiseDate.startTime);
                    hashMap5.put("endTime", currentTranMode.defaultPromiseDate.endTime);
                }
                hashMap.put("selectedTradeModeInfo", hashMap5);
            }
        }
        com.jd.lib.productdetail.tradein.j.d dVar = new com.jd.lib.productdetail.tradein.j.d(hashMap);
        dVar.mResult.observe(getViewLifecycleOwner(), new Observer() { // from class: com.jd.lib.productdetail.tradein.ways.b
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TradeInTradeWaysFragment.this.c((PdBaseProtocolLiveData.Result) obj);
            }
        });
        dVar.request(this.f5609g);
    }

    public final TradeInTradeWaysResp.Data.TradeModeItem m() {
        for (TradeInTradeWaysResp.Data.TradeModeItem tradeModeItem : this.f5611i) {
            if (tradeModeItem.selected == 1) {
                return tradeModeItem;
            }
        }
        return null;
    }

    public final void o() {
        TradeInTradeWaysResp.Data.TradeModeItem m2;
        Fragment tradeInSelectWishTimeFragment;
        if (this.A == PdBaseProtocolLiveData.Result.DataStatus.FETCHING || this.f5612j == null || (m2 = m()) == null) {
            return;
        }
        TradeInSelectWishTimeOrStoreRet tradeInSelectWishTimeOrStoreRet = new TradeInSelectWishTimeOrStoreRet();
        int i2 = m2.tradeModeId;
        tradeInSelectWishTimeOrStoreRet.tradeModeId = i2;
        if (i2 == 5) {
            tradeInSelectWishTimeFragment = new TradeInSelectStoreFragment(this.f5609g, this.f5610h);
            tradeInSelectWishTimeOrStoreRet.store = m2.storeInfo;
        } else {
            tradeInSelectWishTimeFragment = new TradeInSelectWishTimeFragment(this.f5609g, this.f5610h);
            tradeInSelectWishTimeOrStoreRet.date = m2.defaultPromiseDate;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra.key.from.tradein.tradeways.ret", tradeInSelectWishTimeOrStoreRet);
        bundle.putSerializable("key.oldware", this.z);
        tradeInSelectWishTimeFragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction().addToBackStack(null).add(R.id.tradein_main_fragment_container, tradeInSelectWishTimeFragment).commit();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f5612j = (TradeInViewModel) new ViewModelProvider(this.f5610h).get(TradeInViewModel.class);
        if (getArguments() == null || !getArguments().containsKey("key.oldWare")) {
            return;
        }
        this.z = (TradeInResultData.TradeInWareCardInfo) getArguments().getSerializable("key.oldWare");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        ArrayList<TradeInResultData.TradeInWareCardInfo.TradeInWareInfo> arrayList;
        String str;
        super.onViewCreated(view, bundle);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.ways.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInTradeWaysFragment.h(view2);
            }
        });
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo = this.z;
        if (tradeInWareCardInfo == null || (arrayList = tradeInWareCardInfo.wareList) == null || arrayList.size() == 0 || this.z.wareList.get(0) == null || this.z.wareList.get(0).tagInfo == null) {
            return;
        }
        if (this.z.wareList.get(0).tagInfo.tagType == 1) {
            TradeInSelectWishTimeForAppliancesFragment tradeInSelectWishTimeForAppliancesFragment = new TradeInSelectWishTimeForAppliancesFragment(this.f5609g, this.f5610h);
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("key.oldware", tradeInWareCardInfo);
            tradeInSelectWishTimeForAppliancesFragment.setArguments(bundle2);
            getChildFragmentManager().beginTransaction().addToBackStack(null).add(R.id.tradein_main_fragment_container, tradeInSelectWishTimeForAppliancesFragment).commit();
            str = "extra.key.from.tradein.tradeways.appliances_req";
        } else {
            s(view);
            k();
            g();
            str = "extra.key.from.tradein.tradeways.req";
        }
        e(str);
        a();
    }

    public final void r() {
        this.y.setVisibility(8);
        this.f5616n.setVisibility(8);
        this.o.setVisibility(8);
        this.p.setVisibility(8);
        this.q.setVisibility(8);
        this.r.setVisibility(8);
        this.t.setVisibility(8);
        this.u.setVisibility(8);
    }

    public final void s(View view) {
        this.f5613k = view.findViewById(R.id.tradein_selectway_loading);
        TradeinErrorView tradeinErrorView = (TradeinErrorView) view.findViewById(R.id.tradein_selectway_error_view);
        this.f5614l = tradeinErrorView;
        this.f5615m = tradeinErrorView.findViewById(R.id.tradein_common_error_btn_retry);
        this.y = view.findViewById(R.id.tradein_select_way_confirm);
        this.f5616n = (RecyclerView) view.findViewById(R.id.tradein_trade_ways_tab);
        this.o = (TextView) view.findViewById(R.id.tradein_trade_ways_tips);
        this.p = view.findViewById(R.id.tradein_trade_ways_divider_view);
        this.q = view.findViewById(R.id.tradein_trade_ways_divider_view_bottom);
        this.r = (TextView) view.findViewById(R.id.tradein_trade_wish_ways_label);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.tradein_trade_wish_ways_label_container);
        this.s = viewGroup;
        viewGroup.setClickable(true);
        TextView textView = (TextView) view.findViewById(R.id.tradein_trade_ways_select_time);
        this.t = textView;
        FontsUtil.changeTextFont(textView, 4099);
        View findViewById = view.findViewById(R.id.tradein_trade_ways_container_select_address);
        this.u = findViewById;
        this.v = (TextView) findViewById.findViewById(R.id.tradein_trade_ways_addrtitle);
        this.w = (TextView) this.u.findViewById(R.id.tradein_trade_ways_wish_address);
        this.x = (TextView) this.u.findViewById(R.id.tradein_trade_ways_wish_address_opentime);
        this.f5616n.addItemDecoration(new a(this));
        this.f5616n.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(0);
        this.f5616n.setLayoutManager(linearLayoutManager);
        this.u.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.ways.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInTradeWaysFragment.this.l(view2);
            }
        });
        this.t.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.ways.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInTradeWaysFragment.this.n(view2);
            }
        });
        this.y.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.ways.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInTradeWaysFragment.this.q(view2);
            }
        });
    }

    public final void t() {
        this.f5613k.setVisibility(8);
    }

    public boolean v() {
        ArrayList<TradeInResultData.TradeInWareCardInfo.TradeInWareInfo> arrayList;
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo = this.z;
        if (tradeInWareCardInfo == null || (arrayList = tradeInWareCardInfo.wareList) == null || arrayList.size() <= 0 || tradeInWareCardInfo.wareList.get(0).tagInfo == null || tradeInWareCardInfo.wareList.get(0).tagInfo.tagType != 1) {
            FragmentManager childFragmentManager = getChildFragmentManager();
            if (childFragmentManager.getBackStackEntryCount() > 0) {
                childFragmentManager.popBackStack();
                return true;
            }
            return false;
        }
        return false;
    }

    public final void w() {
        TradeinTradeWaysTabAdapter tradeinTradeWaysTabAdapter = new TradeinTradeWaysTabAdapter(this.f5611i);
        this.f5616n.setAdapter(tradeinTradeWaysTabAdapter);
        tradeinTradeWaysTabAdapter.b = new Action1() { // from class: com.jd.lib.productdetail.tradein.ways.c
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                TradeInTradeWaysFragment.this.d((TradeInTradeWaysResp.Data.TradeModeItem) obj);
            }
        };
    }

    public final void x() {
        this.f5614l.a(NetUtils.isNetworkAvailable() ? TradeinErrorView.a.UNKNOWN : TradeinErrorView.a.NONET);
        this.f5614l.setVisibility(0);
    }
}
