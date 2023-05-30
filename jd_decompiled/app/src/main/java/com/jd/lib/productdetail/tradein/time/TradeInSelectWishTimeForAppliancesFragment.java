package com.jd.lib.productdetail.tradein.time;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeInSelectWishTimeForAppliancesFragment extends Fragment {

    /* renamed from: g  reason: collision with root package name */
    public TradeInViewModel f5570g;

    /* renamed from: h  reason: collision with root package name */
    public BaseActivity f5571h;

    /* renamed from: i  reason: collision with root package name */
    public TradeInDialogFragment f5572i;

    /* renamed from: j  reason: collision with root package name */
    public View f5573j;

    /* renamed from: k  reason: collision with root package name */
    public TradeinErrorView f5574k;

    /* renamed from: l  reason: collision with root package name */
    public View f5575l;

    /* renamed from: m  reason: collision with root package name */
    public RecyclerView f5576m;

    /* renamed from: n  reason: collision with root package name */
    public com.jd.lib.productdetail.tradein.i.b f5577n;
    public TradeInResultData.TradeInWareCardInfo o;

    /* loaded from: classes16.dex */
    public class a extends RecyclerView.ItemDecoration {
        public a(TradeInSelectWishTimeForAppliancesFragment tradeInSelectWishTimeForAppliancesFragment) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            rect.bottom = PDUtils.dip2px(8.0f);
        }
    }

    public TradeInSelectWishTimeForAppliancesFragment(BaseActivity baseActivity, TradeInDialogFragment tradeInDialogFragment) {
        super(R.layout.tradein_select_wish_time_for_appliances_fragment_root);
        this.f5571h = baseActivity;
        this.f5572i = tradeInDialogFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        com.jd.lib.productdetail.tradein.i.b bVar = this.f5577n;
        if (bVar != null) {
            bVar.request(this.f5571h);
        }
    }

    public static /* synthetic */ void g(View view) {
    }

    public final void a() {
        this.f5575l.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.time.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInSelectWishTimeForAppliancesFragment.this.b(view);
            }
        });
    }

    public final void c(PdBaseProtocolLiveData.Result<TradeInSelectWishTimesResp> result) {
        TradeInSelectWishTimesResp.Data data;
        List<TradeInSelectWishTimesResp.Data.PromiseDateItem> list;
        PdBaseProtocolLiveData.Result.DataStatus dataStatus = PdBaseProtocolLiveData.Result.DataStatus.FETCHING;
        PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = result.mStatus;
        if (dataStatus == dataStatus2) {
            f();
            this.f5574k.setVisibility(8);
            this.f5573j.setVisibility(0);
        } else if (PdBaseProtocolLiveData.Result.DataStatus.SUCCESS == dataStatus2) {
            h();
            this.f5574k.setVisibility(8);
            TradeInSelectWishTimesResp tradeInSelectWishTimesResp = result.mData;
            if (tradeInSelectWishTimesResp != null && "0".equals(tradeInSelectWishTimesResp.code) && (data = tradeInSelectWishTimesResp.data) != null && (list = data.promiseDates) != null && list.size() != 0) {
                this.f5576m.setVisibility(0);
                e(tradeInSelectWishTimesResp.data.promiseDates);
                return;
            }
            h();
            f();
            k();
        } else if (PdBaseProtocolLiveData.Result.DataStatus.FAIL == dataStatus2) {
            h();
            f();
            k();
        }
    }

    public final void d(TradeInSelectWishTimesResp.Data.PromiseDateItem promiseDateItem) {
        if (PDUtils.repeatClick()) {
            TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate defaultPromiseDate = new TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate();
            defaultPromiseDate.date = promiseDateItem.recycleDate;
            defaultPromiseDate.dayOfWeek = promiseDateItem.dayOfWeek;
            defaultPromiseDate.displayText = promiseDateItem.displayText;
            TradeInSelectWishTimeOrStoreRet tradeInSelectWishTimeOrStoreRet = new TradeInSelectWishTimeOrStoreRet();
            tradeInSelectWishTimeOrStoreRet.date = defaultPromiseDate;
            Bundle bundle = new Bundle();
            bundle.putParcelable("extra.key.from.tradein.tradeways.ret", tradeInSelectWishTimeOrStoreRet);
            FragmentManager parentFragmentManager = getParentFragmentManager();
            parentFragmentManager.popBackStack();
            parentFragmentManager.setFragmentResult("extra.key.from.tradein.tradeways.appliances_req", bundle);
        }
    }

    public final void e(List<TradeInSelectWishTimesResp.Data.PromiseDateItem> list) {
        TradeInResultData.BarterText.BarterOperateEvent.Data currentTranMode;
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo = this.o;
        if (tradeInWareCardInfo == null || (currentTranMode = tradeInWareCardInfo.getCurrentTranMode()) == null || this.f5576m.getItemDecorationCount() != 0) {
            return;
        }
        if (currentTranMode.defaultPromiseDate != null) {
            for (TradeInSelectWishTimesResp.Data.PromiseDateItem promiseDateItem : list) {
                promiseDateItem.selected = TextUtils.equals(currentTranMode.defaultPromiseDate.date, promiseDateItem.recycleDate) ? 1 : 0;
            }
        }
        this.f5576m.addItemDecoration(new a(this));
        this.f5576m.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.f5576m.setLayoutManager(linearLayoutManager);
        TradeInSelectWishTimeDateAdapter tradeInSelectWishTimeDateAdapter = new TradeInSelectWishTimeDateAdapter(list);
        this.f5576m.setAdapter(tradeInSelectWishTimeDateAdapter);
        tradeInSelectWishTimeDateAdapter.b = new Action1() { // from class: com.jd.lib.productdetail.tradein.time.n
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                TradeInSelectWishTimeForAppliancesFragment.this.d((TradeInSelectWishTimesResp.Data.PromiseDateItem) obj);
            }
        };
        linearLayoutManager.scrollToPositionWithOffset(Math.min(list.size() - 1, tradeInSelectWishTimeDateAdapter.h()), PDUtils.dip2px(20.0f));
    }

    public final void f() {
        this.f5576m.setVisibility(8);
    }

    public final void h() {
        this.f5573j.setVisibility(8);
    }

    public final void j() {
        com.jd.lib.productdetail.tradein.i.b bVar = this.f5577n;
        if (bVar == null) {
            return;
        }
        bVar.mResult.observe(getViewLifecycleOwner(), new Observer() { // from class: com.jd.lib.productdetail.tradein.time.m
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TradeInSelectWishTimeForAppliancesFragment.this.c((PdBaseProtocolLiveData.Result) obj);
            }
        });
    }

    public final void k() {
        this.f5574k.a(NetUtils.isNetworkAvailable() ? TradeinErrorView.a.UNKNOWN : TradeinErrorView.a.NONET);
        this.f5574k.setVisibility(0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        TradeInDialogFragment tradeInDialogFragment = this.f5572i;
        if (tradeInDialogFragment == null) {
            return;
        }
        this.f5570g = (TradeInViewModel) new ViewModelProvider(tradeInDialogFragment).get(TradeInViewModel.class);
        tradeInDialogFragment.setTitle(getString(R.string.tradein_step_time_title));
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        ArrayList<TradeInResultData.TradeInWareCardInfo.TradeInWareInfo> arrayList;
        ArrayList<TradeInResultData.TradeInWareCardInfo.TradeInWareInfo> arrayList2;
        TradeInTradeWaysResp.Data.TradeModeItem.DefaultPromiseDate defaultPromiseDate;
        super.onViewCreated(view, bundle);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.time.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInSelectWishTimeForAppliancesFragment.g(view2);
            }
        });
        TradeInViewModel tradeInViewModel = this.f5570g;
        if (tradeInViewModel == null || tradeInViewModel.u.getValue() == null || tradeInViewModel.u.getValue().mData == null) {
            return;
        }
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo = tradeInViewModel.u.getValue().mData.wareNew;
        Bundle arguments = getArguments();
        if (arguments == null || arguments.isEmpty()) {
            return;
        }
        TradeInResultData.TradeInWareCardInfo tradeInWareCardInfo2 = (TradeInResultData.TradeInWareCardInfo) arguments.getSerializable("key.oldware");
        this.o = tradeInWareCardInfo2;
        if (tradeInWareCardInfo2 == null || (arrayList = tradeInWareCardInfo2.wareList) == null || arrayList.size() == 0 || this.o.wareList.get(0) == null || this.o.wareList.get(0).tagInfo == null || tradeInViewModel.u.getValue() == null || tradeInViewModel.u.getValue().mData == null || tradeInWareCardInfo == null || (arrayList2 = tradeInWareCardInfo.wareList) == null || arrayList2.size() == 0 || tradeInWareCardInfo.wareList.get(0) == null) {
            return;
        }
        this.f5573j = view.findViewById(R.id.tradein_select_time_loading);
        TradeinErrorView tradeinErrorView = (TradeinErrorView) view.findViewById(R.id.tradein_select_time_error_view);
        this.f5574k = tradeinErrorView;
        this.f5575l = tradeinErrorView.findViewById(R.id.tradein_common_error_btn_retry);
        this.f5576m = (RecyclerView) view.findViewById(R.id.tradein_select_wish_time_item);
        TradeInResultData.BarterText.BarterOperateEvent.Data currentTranMode = this.o.getCurrentTranMode();
        HashMap hashMap = new HashMap();
        TradeInResultData.TradeInWareCardInfo.TradeInWareInfo.OldProductInfoForClap oldProductInfoForClap = this.o.wareList.get(0).oldProductInfoForClap;
        if (this.o.cardType == 4 && oldProductInfoForClap != null && !TextUtils.isEmpty(oldProductInfoForClap.venderId)) {
            hashMap.put("venderId", oldProductInfoForClap.venderId);
        }
        hashMap.put("skuId", tradeInWareCardInfo.wareList.get(0).wareId);
        if (currentTranMode != null) {
            hashMap.put("tradeMode", Integer.valueOf(currentTranMode.tradeModeId));
        }
        hashMap.put("tradeType", Integer.valueOf(tradeInViewModel.f5256h));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("inquiryIds", this.o.getInquiryIds());
        hashMap.put("inquiryProductInfo", hashMap2);
        JDJSONObject jDJSONObject = tradeInViewModel.s;
        if (jDJSONObject != null) {
            hashMap.put("extension", jDJSONObject);
        }
        hashMap.put("addressInfo", tradeInViewModel.c());
        HashMap hashMap3 = new HashMap();
        hashMap3.put("tagId", this.o.wareList.get(0).tagInfo.tagId);
        hashMap3.put("tagType", this.o.wareList.get(0).tagInfo.tagType + "");
        hashMap.put("tagInfo", hashMap3);
        hashMap.put("extend", tradeInViewModel.t);
        if (currentTranMode != null && (defaultPromiseDate = currentTranMode.defaultPromiseDate) != null) {
            HashMap hashMap4 = new HashMap();
            hashMap4.put(JDDateTimePickerDialog.SELECT_DATE_MODE, defaultPromiseDate.date);
            hashMap.put("selectedPromiseDate", hashMap4);
        }
        this.f5577n = new com.jd.lib.productdetail.tradein.i.b(hashMap);
        j();
        this.f5577n.request(this.f5571h);
        a();
    }
}
