package com.jd.lib.productdetail.tradein.time;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import com.jd.lib.productdetail.tradein.time.TradeInSelectWishTimesResp;
import com.jd.lib.productdetail.tradein.ways.TradeInSelectWishTimeOrStoreRet;
import com.jd.lib.productdetail.tradein.ways.TradeInTradeWaysResp;
import com.jd.lib.productdetail.tradein.widget.TradeinErrorView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.model.datetime.JDDateTimePickerDialog;
import com.jingdong.jdsdk.utils.NetUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeInSelectWishTimeFragment extends Fragment {

    /* renamed from: g  reason: collision with root package name */
    public TradeInViewModel f5578g;

    /* renamed from: h  reason: collision with root package name */
    public BaseActivity f5579h;

    /* renamed from: i  reason: collision with root package name */
    public TradeInDialogFragment f5580i;

    /* renamed from: j  reason: collision with root package name */
    public View f5581j;

    /* renamed from: k  reason: collision with root package name */
    public TradeinErrorView f5582k;

    /* renamed from: l  reason: collision with root package name */
    public View f5583l;

    /* renamed from: m  reason: collision with root package name */
    public RecyclerView f5584m;

    /* renamed from: n  reason: collision with root package name */
    public RecyclerView f5585n;
    public View o;
    public Guideline p;
    public View q;
    public View r;
    public View s;
    public com.jd.lib.productdetail.tradein.i.b t;
    public final TradeInSelectWishTimeOrStoreRet u;
    public final ArrayMap<String, Integer> v;
    public TradeInResultData.TradeInWareCardInfo w;

    /* loaded from: classes16.dex */
    public class a extends RecyclerView.ItemDecoration {
        public a(TradeInSelectWishTimeFragment tradeInSelectWishTimeFragment) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            rect.bottom = PDUtils.dip2px(8.0f);
        }
    }

    /* loaded from: classes16.dex */
    public class b extends RecyclerView.ItemDecoration {
        public b(TradeInSelectWishTimeFragment tradeInSelectWishTimeFragment) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            rect.bottom = PDUtils.dip2px(8.0f);
        }
    }

    public TradeInSelectWishTimeFragment(BaseActivity baseActivity, TradeInDialogFragment tradeInDialogFragment) {
        super(R.layout.tradein_select_wish_time_fragment_root);
        this.u = new TradeInSelectWishTimeOrStoreRet();
        this.v = new ArrayMap<>();
        this.f5579h = baseActivity;
        this.f5580i = tradeInDialogFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        com.jd.lib.productdetail.tradein.i.b bVar = this.t;
        if (bVar != null) {
            bVar.request(this.f5579h);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(TradeInSelectWishTimesResp.Data.PromiseDateItem promiseDateItem, View view) {
        if (PDUtils.repeatClick()) {
            TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem timeMomentItem = null;
            Iterator<TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem> it = promiseDateItem.timeMoments.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem next = it.next();
                if (next.selected == 1) {
                    timeMomentItem = next;
                    break;
                }
            }
            if (timeMomentItem == null) {
                PDUtils.showToastCenterNormal(getContext(), "\u8bf7\u9009\u62e9\u65f6\u95f4");
                return;
            }
            TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate defaultPromiseDate = new TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate();
            defaultPromiseDate.date = promiseDateItem.recycleDate;
            defaultPromiseDate.dayOfWeek = promiseDateItem.dayOfWeek;
            defaultPromiseDate.startTime = timeMomentItem.startTime;
            defaultPromiseDate.endTime = timeMomentItem.endTime;
            defaultPromiseDate.displayText = timeMomentItem.displayText;
            this.u.date = defaultPromiseDate;
            Bundle bundle = new Bundle();
            bundle.putParcelable("extra.key.from.tradein.tradeways.ret", this.u);
            FragmentManager parentFragmentManager = getParentFragmentManager();
            parentFragmentManager.popBackStack();
            parentFragmentManager.setFragmentResult("extra.key.from.tradein.tradeways.req", bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(TradeInSelectWishTimesResp.Data.PromiseDateItem promiseDateItem, TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem timeMomentItem) {
        if (timeMomentItem.state == 0) {
            PDUtils.showToastCenterNormal(getContext(), "\u5f53\u524d\u65f6\u95f4\u4e0d\u53ef\u9009");
            return;
        }
        Iterator<TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem> it = promiseDateItem.timeMoments.iterator();
        while (it.hasNext()) {
            TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem next = it.next();
            next.selected = next == timeMomentItem ? 1 : 0;
        }
        RecyclerView.Adapter adapter = this.f5585n.getAdapter();
        if (adapter == null) {
            return;
        }
        adapter.notifyDataSetChanged();
    }

    public static /* synthetic */ void j(View view) {
    }

    public final void a() {
        this.f5583l.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.time.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInSelectWishTimeFragment.this.b(view);
            }
        });
    }

    public final void c(PdBaseProtocolLiveData.Result<TradeInSelectWishTimesResp> result) {
        TradeInSelectWishTimesResp.Data data;
        List<TradeInSelectWishTimesResp.Data.PromiseDateItem> list;
        PdBaseProtocolLiveData.Result.DataStatus dataStatus = PdBaseProtocolLiveData.Result.DataStatus.FETCHING;
        PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = result.mStatus;
        if (dataStatus == dataStatus2) {
            h();
            this.f5582k.setVisibility(8);
            this.f5581j.setVisibility(0);
        } else if (PdBaseProtocolLiveData.Result.DataStatus.SUCCESS == dataStatus2) {
            m();
            this.f5582k.setVisibility(8);
            TradeInSelectWishTimesResp tradeInSelectWishTimesResp = result.mData;
            if (tradeInSelectWishTimesResp != null && "0".equals(tradeInSelectWishTimesResp.code) && (data = tradeInSelectWishTimesResp.data) != null && (list = data.promiseDates) != null && list.size() != 0) {
                TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo = this.w;
                if (tradeInWareCardInfo == null) {
                    return;
                }
                if (tradeInWareCardInfo.cardType == 4 && this.u.tradeModeId == 4) {
                    this.p.setGuidelinePercent(0.32f);
                    this.q.setVisibility(0);
                    this.f5584m.setVisibility(0);
                    this.o.setVisibility(0);
                    this.f5585n.setVisibility(0);
                    this.s.setVisibility(0);
                    this.r.setVisibility(0);
                    l(tradeInSelectWishTimesResp.data.promiseDates);
                    return;
                }
                this.p.setGuidelinePercent(0.0f);
                this.q.setVisibility(8);
                this.f5584m.setVisibility(8);
                this.o.setVisibility(8);
                this.s.setVisibility(8);
                this.r.setVisibility(8);
                this.f5585n.setVisibility(0);
                g(tradeInSelectWishTimesResp.data.promiseDates);
                return;
            }
            m();
            h();
            o();
        } else if (PdBaseProtocolLiveData.Result.DataStatus.FAIL == dataStatus2) {
            m();
            h();
            o();
        }
    }

    public final void d(TradeInSelectWishTimesResp.Data.PromiseDateItem promiseDateItem) {
        if (PDUtils.repeatClick()) {
            TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate defaultPromiseDate = new TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate();
            defaultPromiseDate.date = promiseDateItem.recycleDate;
            defaultPromiseDate.displayText = promiseDateItem.displayText;
            this.u.date = defaultPromiseDate;
            Bundle bundle = new Bundle();
            bundle.putParcelable("extra.key.from.tradein.tradeways.ret", this.u);
            FragmentManager parentFragmentManager = getParentFragmentManager();
            parentFragmentManager.popBackStack();
            parentFragmentManager.setFragmentResult("extra.key.from.tradein.tradeways.req", bundle);
        }
    }

    public final void g(List<TradeInSelectWishTimesResp.Data.PromiseDateItem> list) {
        if (this.f5585n.getItemDecorationCount() == 0) {
            this.f5585n.addItemDecoration(new a(this));
            this.f5585n.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            this.f5585n.setLayoutManager(linearLayoutManager);
            TradeInSelectWishTimeDateAdapter tradeInSelectWishTimeDateAdapter = new TradeInSelectWishTimeDateAdapter(list);
            this.f5585n.setAdapter(tradeInSelectWishTimeDateAdapter);
            tradeInSelectWishTimeDateAdapter.b = new Action1() { // from class: com.jd.lib.productdetail.tradein.time.k
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    TradeInSelectWishTimeFragment.this.d((TradeInSelectWishTimesResp.Data.PromiseDateItem) obj);
                }
            };
            linearLayoutManager.scrollToPositionWithOffset(Math.min(list.size() - 1, tradeInSelectWishTimeDateAdapter.h()), PDUtils.dip2px(20.0f));
        }
    }

    public final void h() {
        this.f5584m.setVisibility(8);
        this.f5585n.setVisibility(8);
        this.q.setVisibility(8);
        this.r.setVisibility(8);
        this.s.setVisibility(8);
    }

    public final void k(final TradeInSelectWishTimesResp.Data.PromiseDateItem promiseDateItem) {
        List<TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem> list = promiseDateItem.timeMoments;
        if (list == null || list.size() == 0) {
            return;
        }
        TradeInSelectWishTimeItemAdapter tradeInSelectWishTimeItemAdapter = new TradeInSelectWishTimeItemAdapter(promiseDateItem.timeMoments);
        this.f5585n.setAdapter(tradeInSelectWishTimeItemAdapter);
        tradeInSelectWishTimeItemAdapter.b = new Action1() { // from class: com.jd.lib.productdetail.tradein.time.e
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                TradeInSelectWishTimeFragment.this.f(promiseDateItem, (TradeInSelectWishTimesResp.Data.PromiseDateItem.TimeMomentItem) obj);
            }
        };
        RecyclerView.LayoutManager layoutManager = this.f5585n.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            Integer num = this.v.get(promiseDateItem.recycleDate);
            int i2 = 0;
            int intValue = num != null ? num.intValue() : 0;
            if (intValue == 0) {
                intValue = 0;
                while (intValue < tradeInSelectWishTimeItemAdapter.a.size()) {
                    if (tradeInSelectWishTimeItemAdapter.a.get(intValue).selected != 1) {
                        intValue++;
                    }
                }
                ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(Math.min(promiseDateItem.timeMoments.size() - 1, i2), PDUtils.dip2px(20.0f));
            }
            i2 = intValue;
            ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(Math.min(promiseDateItem.timeMoments.size() - 1, i2), PDUtils.dip2px(20.0f));
        }
        this.s.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.time.g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInSelectWishTimeFragment.this.e(promiseDateItem, view);
            }
        });
    }

    public final void l(List<TradeInSelectWishTimesResp.Data.PromiseDateItem> list) {
        this.f5584m.setHasFixedSize(true);
        this.f5584m.setLayoutManager(new LinearLayoutManager(getContext()));
        TradeInSelectWishTimeGroupAdapter tradeInSelectWishTimeGroupAdapter = new TradeInSelectWishTimeGroupAdapter(list);
        this.f5584m.setAdapter(tradeInSelectWishTimeGroupAdapter);
        tradeInSelectWishTimeGroupAdapter.b = new Action1() { // from class: com.jd.lib.productdetail.tradein.time.j
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                TradeInSelectWishTimeFragment.this.k((TradeInSelectWishTimesResp.Data.PromiseDateItem) obj);
            }
        };
        if (this.f5585n.getItemDecorationCount() == 0) {
            this.f5585n.addItemDecoration(new b(this));
            this.f5585n.setHasFixedSize(true);
            this.f5585n.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        for (TradeInSelectWishTimesResp.Data.PromiseDateItem promiseDateItem : list) {
            if (promiseDateItem.selected == 1) {
                k(promiseDateItem);
                return;
            }
        }
    }

    public final void m() {
        this.f5581j.setVisibility(8);
    }

    public final void n() {
        com.jd.lib.productdetail.tradein.i.b bVar = this.t;
        if (bVar == null) {
            return;
        }
        bVar.mResult.observe(getViewLifecycleOwner(), new Observer() { // from class: com.jd.lib.productdetail.tradein.time.l
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TradeInSelectWishTimeFragment.this.c((PdBaseProtocolLiveData.Result) obj);
            }
        });
    }

    public final void o() {
        this.f5582k.a(NetUtils.isNetworkAvailable() ? TradeinErrorView.a.UNKNOWN : TradeinErrorView.a.NONET);
        this.f5582k.setVisibility(0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f5578g = (TradeInViewModel) new ViewModelProvider(this.f5580i).get(TradeInViewModel.class);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        Bundle arguments;
        ArrayList<TradeInResultData.TradeInWareCardInfo.TradeInWareInfo> arrayList;
        ArrayList<TradeInResultData.TradeInWareCardInfo.TradeInWareInfo> arrayList2;
        TradeInSelectWishTimeOrStoreRet tradeInSelectWishTimeOrStoreRet;
        super.onViewCreated(view, bundle);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.time.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInSelectWishTimeFragment.j(view2);
            }
        });
        TradeInViewModel tradeInViewModel = this.f5578g;
        if (tradeInViewModel == null || (arguments = getArguments()) == null || arguments.isEmpty() || tradeInViewModel.u.getValue() == null || tradeInViewModel.u.getValue().mData == null) {
            return;
        }
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo = tradeInViewModel.u.getValue().mData.wareNew;
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo2 = (TradeInResultData.TradeInWareCardInfo) getArguments().getSerializable("key.oldware");
        this.w = tradeInWareCardInfo2;
        if (tradeInWareCardInfo2 == null || (arrayList = tradeInWareCardInfo2.wareList) == null || arrayList.size() == 0 || this.w.wareList.get(0) == null || this.w.wareList.get(0).tagInfo == null || tradeInViewModel.u.getValue() == null || tradeInViewModel.u.getValue().mData == null || tradeInWareCardInfo == null || (arrayList2 = tradeInWareCardInfo.wareList) == null || arrayList2.size() == 0 || tradeInWareCardInfo.wareList.get(0) == null || (tradeInSelectWishTimeOrStoreRet = (TradeInSelectWishTimeOrStoreRet) arguments.getParcelable("extra.key.from.tradein.tradeways.ret")) == null) {
            return;
        }
        this.f5580i.setTitle(getString(R.string.tradein_step_time_title));
        TradeInSelectWishTimeOrStoreRet tradeInSelectWishTimeOrStoreRet2 = this.u;
        tradeInSelectWishTimeOrStoreRet2.tradeModeId = tradeInSelectWishTimeOrStoreRet.tradeModeId;
        tradeInSelectWishTimeOrStoreRet2.date = tradeInSelectWishTimeOrStoreRet.date;
        this.f5581j = view.findViewById(R.id.tradein_select_time_loading);
        this.f5582k = (TradeinErrorView) view.findViewById(R.id.tradein_select_time_error_view);
        this.r = view.findViewById(R.id.tradein_trade_ways_divider_view_bottom);
        this.s = view.findViewById(R.id.tradein_select_time_confirm);
        this.f5583l = this.f5582k.findViewById(R.id.tradein_common_error_btn_retry);
        this.q = view.findViewById(R.id.tradein_select_wish_time_bg);
        this.f5584m = (RecyclerView) view.findViewById(R.id.tradein_select_wish_time_group);
        this.f5585n = (RecyclerView) view.findViewById(R.id.tradein_select_wish_time_item);
        this.o = view.findViewById(R.id.tradein_select_time_space);
        this.p = (Guideline) view.findViewById(R.id.tradein_guideline);
        this.f5585n.addOnScrollListener(new com.jd.lib.productdetail.tradein.i.a(this));
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(tradeInViewModel.t)) {
            hashMap.put("extend", tradeInViewModel.t);
        }
        TradeInResultData.TradeInWareCardInfo.TradeInWareInfo.OldProductInfoForClap oldProductInfoForClap = this.w.wareList.get(0).oldProductInfoForClap;
        if (this.w.cardType == 4 && oldProductInfoForClap != null && !TextUtils.isEmpty(oldProductInfoForClap.venderId)) {
            hashMap.put("venderId", oldProductInfoForClap.venderId);
        }
        hashMap.put("skuId", tradeInWareCardInfo.wareList.get(0).wareId);
        hashMap.put("tradeMode", Integer.valueOf(tradeInSelectWishTimeOrStoreRet.tradeModeId));
        hashMap.put("tradeType", Integer.valueOf(tradeInViewModel.f5256h));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("inquiryId", this.w.getInquiryIds());
        hashMap.put("inquiryProductInfo", hashMap2);
        JDJSONObject jDJSONObject = tradeInViewModel.s;
        if (jDJSONObject != null) {
            hashMap.put("extension", jDJSONObject);
        }
        hashMap.put("extend", tradeInViewModel.t);
        hashMap.put("addressInfo", tradeInViewModel.c());
        HashMap hashMap3 = new HashMap();
        hashMap3.put("tagId", this.w.wareList.get(0).tagInfo.tagId);
        hashMap3.put("tagType", this.w.wareList.get(0).tagInfo.tagType + "");
        hashMap.put("tagInfo", hashMap3);
        if (tradeInSelectWishTimeOrStoreRet.date != null) {
            HashMap hashMap4 = new HashMap();
            hashMap4.put(JDDateTimePickerDialog.SELECT_DATE_MODE, tradeInSelectWishTimeOrStoreRet.date.date);
            hashMap4.put("startTime", tradeInSelectWishTimeOrStoreRet.date.startTime);
            hashMap4.put("endTime", tradeInSelectWishTimeOrStoreRet.date.endTime);
            hashMap.put("selectedPromiseDate", hashMap4);
        }
        this.t = new com.jd.lib.productdetail.tradein.i.b(hashMap);
        n();
        this.t.request(this.f5579h);
        a();
    }
}
