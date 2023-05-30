package com.jd.lib.productdetail.tradein.stores;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.framework.json.JDJSONObject;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInDialogFragment;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp;
import com.jd.lib.productdetail.tradein.ways.TradeInSelectWishTimeOrStoreRet;
import com.jd.lib.productdetail.tradein.ways.TradeInTradeWaysResp;
import com.jd.lib.productdetail.tradein.widget.TradeInCenterLinearLayoutManager;
import com.jd.lib.productdetail.tradein.widget.TradeinErrorView;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.utils.NetUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeInSelectStoreFragment extends Fragment {

    /* renamed from: g  reason: collision with root package name */
    public TradeInViewModel f5547g;

    /* renamed from: h  reason: collision with root package name */
    public BaseActivity f5548h;

    /* renamed from: i  reason: collision with root package name */
    public TradeInDialogFragment f5549i;

    /* renamed from: j  reason: collision with root package name */
    public View f5550j;

    /* renamed from: k  reason: collision with root package name */
    public TradeinErrorView f5551k;

    /* renamed from: l  reason: collision with root package name */
    public View f5552l;

    /* renamed from: m  reason: collision with root package name */
    public TextView f5553m;

    /* renamed from: n  reason: collision with root package name */
    public RecyclerView f5554n;
    public RecyclerView o;
    public com.jd.lib.productdetail.tradein.h.d p;
    public final TradeInSelectWishTimeOrStoreRet q;
    public final SparseIntArray r;
    public final Handler s;

    public TradeInSelectStoreFragment(BaseActivity baseActivity, TradeInDialogFragment tradeInDialogFragment) {
        super(R.layout.tradein_select_store_fragment_root);
        this.q = new TradeInSelectWishTimeOrStoreRet();
        this.r = new SparseIntArray();
        this.s = new Handler(Looper.getMainLooper());
        this.f5548h = baseActivity;
        this.f5549i = tradeInDialogFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        com.jd.lib.productdetail.tradein.h.d dVar = this.p;
        if (dVar == null) {
            return;
        }
        dVar.request(this.f5548h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(RecyclerView.LayoutManager layoutManager, int i2) {
        ((TradeInCenterLinearLayoutManager) layoutManager).smoothScrollToPosition(this.f5554n, null, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(TradeInSelectStoreResp.Data.StoreTagItem storeTagItem, TradeInSelectStoreResp.Data.StoreTagItem.StoreInfoItem storeInfoItem) {
        e(storeInfoItem);
    }

    public static /* synthetic */ void k(View view) {
    }

    public final void a() {
        this.f5552l.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.stores.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInSelectStoreFragment.this.b(view);
            }
        });
    }

    public final void d(PdBaseProtocolLiveData.Result<TradeInSelectStoreResp> result) {
        TradeInSelectStoreResp.Data data;
        List<TradeInSelectStoreResp.Data.StoreTagItem> list;
        PdBaseProtocolLiveData.Result.DataStatus dataStatus = PdBaseProtocolLiveData.Result.DataStatus.FETCHING;
        PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = result.mStatus;
        if (dataStatus == dataStatus2) {
            j();
            this.f5551k.setVisibility(8);
            this.f5550j.setVisibility(0);
        } else if (PdBaseProtocolLiveData.Result.DataStatus.SUCCESS == dataStatus2) {
            l();
            this.f5551k.setVisibility(8);
            TradeInSelectStoreResp tradeInSelectStoreResp = result.mData;
            if (tradeInSelectStoreResp != null && "0".equals(tradeInSelectStoreResp.code) && (data = tradeInSelectStoreResp.data) != null && (list = data.storeTags) != null && list.size() != 0) {
                this.f5553m.setVisibility(0);
                h(tradeInSelectStoreResp.data);
                return;
            }
            l();
            j();
            n();
        } else if (PdBaseProtocolLiveData.Result.DataStatus.FAIL == dataStatus2) {
            l();
            j();
            n();
        }
    }

    public final void e(TradeInSelectStoreResp.Data.StoreTagItem.StoreInfoItem storeInfoItem) {
        TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo storeInfo = new TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo();
        storeInfo.storeId = storeInfoItem.storeId;
        storeInfo.storeName = storeInfoItem.storeName;
        storeInfo.storeAddress = storeInfoItem.storeAddress;
        storeInfo.openTime = storeInfoItem.openTime;
        this.q.store = storeInfo;
        Bundle bundle = new Bundle();
        bundle.putParcelable("extra.key.from.tradein.tradeways.ret", this.q);
        FragmentManager parentFragmentManager = getParentFragmentManager();
        parentFragmentManager.popBackStack();
        parentFragmentManager.setFragmentResult("extra.key.from.tradein.tradeways.req", bundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(final com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp.Data.StoreTagItem r6) {
        /*
            r5 = this;
            java.util.List<com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp$Data$StoreTagItem$StoreInfoItem> r0 = r6.storeInfos
            if (r0 == 0) goto L8d
            int r0 = r0.size()
            if (r0 != 0) goto Lc
            goto L8d
        Lc:
            androidx.recyclerview.widget.RecyclerView r0 = r5.f5554n
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            boolean r1 = r0 instanceof com.jd.lib.productdetail.tradein.widget.TradeInCenterLinearLayoutManager
            if (r1 == 0) goto L34
            androidx.recyclerview.widget.RecyclerView r1 = r5.f5554n
            androidx.recyclerview.widget.RecyclerView$Adapter r1 = r1.getAdapter()
            boolean r2 = r1 instanceof com.jd.lib.productdetail.tradein.stores.TradeinSelectStoreTabAdapter
            if (r2 == 0) goto L34
            com.jd.lib.productdetail.tradein.stores.TradeinSelectStoreTabAdapter r1 = (com.jd.lib.productdetail.tradein.stores.TradeinSelectStoreTabAdapter) r1
            java.util.List<com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp$Data$StoreTagItem> r1 = r1.a
            int r1 = r1.indexOf(r6)
            if (r1 < 0) goto L34
            android.os.Handler r2 = r5.s
            com.jd.lib.productdetail.tradein.stores.b r3 = new com.jd.lib.productdetail.tradein.stores.b
            r3.<init>()
            r2.post(r3)
        L34:
            com.jd.lib.productdetail.tradein.stores.TradeinSelectStoreItemAdapter r0 = new com.jd.lib.productdetail.tradein.stores.TradeinSelectStoreItemAdapter
            java.util.List<com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp$Data$StoreTagItem$StoreInfoItem> r1 = r6.storeInfos
            r0.<init>(r1)
            androidx.recyclerview.widget.RecyclerView r1 = r5.o
            r1.setAdapter(r0)
            com.jd.lib.productdetail.tradein.stores.c r1 = new com.jd.lib.productdetail.tradein.stores.c
            r1.<init>()
            r0.b = r1
            android.util.SparseIntArray r1 = r5.r
            int r2 = r6.tagId
            r3 = 0
            int r1 = r1.get(r2, r3)
            r2 = 1
            if (r1 != 0) goto L6c
            r1 = 0
        L54:
            java.util.List<com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp$Data$StoreTagItem$StoreInfoItem> r4 = r0.a
            int r4 = r4.size()
            if (r1 >= r4) goto L6d
            java.util.List<com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp$Data$StoreTagItem$StoreInfoItem> r4 = r0.a
            java.lang.Object r4 = r4.get(r1)
            com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp$Data$StoreTagItem$StoreInfoItem r4 = (com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp.Data.StoreTagItem.StoreInfoItem) r4
            int r4 = r4.selected
            if (r4 != r2) goto L69
            goto L6c
        L69:
            int r1 = r1 + 1
            goto L54
        L6c:
            r3 = r1
        L6d:
            androidx.recyclerview.widget.RecyclerView r0 = r5.o
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            boolean r1 = r0 instanceof androidx.recyclerview.widget.LinearLayoutManager
            if (r1 == 0) goto L8d
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0
            java.util.List<com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp$Data$StoreTagItem$StoreInfoItem> r6 = r6.storeInfos
            int r6 = r6.size()
            int r6 = r6 - r2
            int r6 = java.lang.Math.min(r6, r3)
            r1 = 1101004800(0x41a00000, float:20.0)
            int r1 = com.jd.lib.productdetail.core.utils.PDUtils.dip2px(r1)
            r0.scrollToPositionWithOffset(r6, r1)
        L8d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreFragment.f(com.jd.lib.productdetail.tradein.stores.TradeInSelectStoreResp$Data$StoreTagItem):void");
    }

    public final void h(TradeInSelectStoreResp.Data data) {
        this.f5553m.setText(data.provinceName);
        List<TradeInSelectStoreResp.Data.StoreTagItem> list = data.storeTags;
        TradeinSelectStoreTabAdapter tradeinSelectStoreTabAdapter = new TradeinSelectStoreTabAdapter(list);
        this.f5554n.setAdapter(tradeinSelectStoreTabAdapter);
        tradeinSelectStoreTabAdapter.b = new Action1() { // from class: com.jd.lib.productdetail.tradein.stores.a
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                TradeInSelectStoreFragment.this.f((TradeInSelectStoreResp.Data.StoreTagItem) obj);
            }
        };
        for (TradeInSelectStoreResp.Data.StoreTagItem storeTagItem : list) {
            if (storeTagItem.selected == 1) {
                f(storeTagItem);
                return;
            }
        }
    }

    public final void j() {
        this.f5553m.setVisibility(4);
    }

    public final void l() {
        this.f5550j.setVisibility(8);
    }

    public final void m() {
        com.jd.lib.productdetail.tradein.h.d dVar = this.p;
        if (dVar == null) {
            return;
        }
        dVar.mResult.observe(getViewLifecycleOwner(), new Observer() { // from class: com.jd.lib.productdetail.tradein.stores.g
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TradeInSelectStoreFragment.this.d((PdBaseProtocolLiveData.Result) obj);
            }
        });
    }

    public final void n() {
        this.f5551k.a(NetUtils.isNetworkAvailable() ? TradeinErrorView.a.UNKNOWN : TradeinErrorView.a.NONET);
        this.f5551k.setVisibility(0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f5547g = (TradeInViewModel) new ViewModelProvider(this.f5549i).get(TradeInViewModel.class);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.s.removeCallbacksAndMessages(null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        Bundle arguments;
        ArrayList<TradeInResultData.TradeInWareCardInfo.TradeInWareInfo> arrayList;
        ArrayList<TradeInResultData.TradeInWareCardInfo.TradeInWareInfo> arrayList2;
        TradeInSelectWishTimeOrStoreRet tradeInSelectWishTimeOrStoreRet;
        super.onViewCreated(view, bundle);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.stores.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInSelectStoreFragment.k(view2);
            }
        });
        TradeInViewModel tradeInViewModel = this.f5547g;
        if (tradeInViewModel == null || (arguments = getArguments()) == null || arguments.isEmpty() || tradeInViewModel.u.getValue() == null || tradeInViewModel.u.getValue().mData == null) {
            return;
        }
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo = tradeInViewModel.u.getValue().mData.wareNew;
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo2 = (TradeInResultData.TradeInWareCardInfo) getArguments().getSerializable("key.oldware");
        if (tradeInWareCardInfo2 == null || (arrayList = tradeInWareCardInfo2.wareList) == null || arrayList.size() == 0 || tradeInWareCardInfo2.wareList.get(0) == null || tradeInWareCardInfo2.wareList.get(0).tagInfo == null || tradeInViewModel.u.getValue() == null || tradeInViewModel.u.getValue().mData == null || tradeInWareCardInfo == null || (arrayList2 = tradeInWareCardInfo.wareList) == null || arrayList2.size() == 0 || tradeInWareCardInfo.wareList.get(0) == null || (tradeInSelectWishTimeOrStoreRet = (TradeInSelectWishTimeOrStoreRet) arguments.getParcelable("extra.key.from.tradein.tradeways.ret")) == null) {
            return;
        }
        this.f5549i.setTitle(getString(R.string.tradein_step_store_title));
        TradeInSelectWishTimeOrStoreRet tradeInSelectWishTimeOrStoreRet2 = this.q;
        tradeInSelectWishTimeOrStoreRet2.tradeModeId = tradeInSelectWishTimeOrStoreRet.tradeModeId;
        tradeInSelectWishTimeOrStoreRet2.date = tradeInSelectWishTimeOrStoreRet.date;
        this.f5550j = view.findViewById(R.id.tradein_selectstore_loading);
        TradeinErrorView tradeinErrorView = (TradeinErrorView) view.findViewById(R.id.tradein_selectstore_error_view);
        this.f5551k = tradeinErrorView;
        this.f5552l = tradeinErrorView.findViewById(R.id.tradein_common_error_btn_retry);
        this.f5553m = (TextView) view.findViewById(R.id.tradein_select_store_local_addr);
        this.f5554n = (RecyclerView) view.findViewById(R.id.tradein_select_store_tab);
        this.o = (RecyclerView) view.findViewById(R.id.tradein_address_infos);
        this.f5554n.addItemDecoration(new com.jd.lib.productdetail.tradein.h.a(this));
        this.f5554n.setHasFixedSize(true);
        TradeInCenterLinearLayoutManager tradeInCenterLinearLayoutManager = new TradeInCenterLinearLayoutManager(getContext());
        tradeInCenterLinearLayoutManager.setOrientation(0);
        this.f5554n.setLayoutManager(tradeInCenterLinearLayoutManager);
        this.o.addItemDecoration(new com.jd.lib.productdetail.tradein.h.b(this));
        this.o.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.o.setLayoutManager(linearLayoutManager);
        this.o.addOnScrollListener(new com.jd.lib.productdetail.tradein.h.c(this, linearLayoutManager));
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(tradeInViewModel.t)) {
            hashMap.put("extend", tradeInViewModel.t);
        }
        TradeInResultData.TradeInWareCardInfo.TradeInWareInfo.OldProductInfoForClap oldProductInfoForClap = tradeInWareCardInfo2.wareList.get(0).oldProductInfoForClap;
        if (tradeInWareCardInfo2.cardType == 4 && oldProductInfoForClap != null && !TextUtils.isEmpty(oldProductInfoForClap.venderId)) {
            hashMap.put("venderId", oldProductInfoForClap.venderId);
        }
        hashMap.put("tradeType", Integer.valueOf(tradeInViewModel.f5256h));
        hashMap.put("addressInfo", tradeInViewModel.c());
        JDJSONObject jDJSONObject = tradeInViewModel.s;
        if (jDJSONObject != null) {
            hashMap.put("extension", jDJSONObject);
        }
        TradeInTradeWaysResp.Data.TradeModeItem.StoreInfo storeInfo = tradeInSelectWishTimeOrStoreRet.store;
        if (storeInfo != null) {
            hashMap.put("selectedStoreId", Integer.valueOf(storeInfo.storeId));
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("tagId", tradeInWareCardInfo2.wareList.get(0).tagInfo.tagId);
        hashMap2.put("tagType", tradeInWareCardInfo2.wareList.get(0).tagInfo.tagType + "");
        hashMap.put("tagInfo", hashMap2);
        this.p = new com.jd.lib.productdetail.tradein.h.d(hashMap);
        m();
        this.p.request(this.f5548h);
        a();
    }
}
