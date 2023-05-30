package com.jd.lib.productdetail.tradein.bank;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.core.protocol.PdBaseProtocolLiveData;
import com.jd.lib.productdetail.core.utils.PDUtils;
import com.jd.lib.productdetail.tradein.R;
import com.jd.lib.productdetail.tradein.TradeInDialogFragment;
import com.jd.lib.productdetail.tradein.TradeInViewModel;
import com.jd.lib.productdetail.tradein.bank.TradeInSelectBankResp;
import com.jd.lib.productdetail.tradein.result.TradeInResultData;
import com.jd.lib.productdetail.tradein.widget.TradeinErrorView;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDBaseDeepLinkHelper;
import com.jingdong.common.BaseActivity;
import com.jingdong.jdsdk.utils.NetUtils;
import java.util.HashMap;
import java.util.List;
import rx.functions.Action1;

/* loaded from: classes16.dex */
public class TradeInSelectBankFragment extends Fragment {

    /* renamed from: g  reason: collision with root package name */
    public TradeInViewModel f5268g;

    /* renamed from: h  reason: collision with root package name */
    public BaseActivity f5269h;

    /* renamed from: i  reason: collision with root package name */
    public TradeInDialogFragment f5270i;

    /* renamed from: j  reason: collision with root package name */
    public View f5271j;

    /* renamed from: k  reason: collision with root package name */
    public TradeinErrorView f5272k;

    /* renamed from: l  reason: collision with root package name */
    public View f5273l;

    /* renamed from: m  reason: collision with root package name */
    public RecyclerView f5274m;

    /* renamed from: n  reason: collision with root package name */
    public com.jd.lib.productdetail.tradein.e.a f5275n;
    public boolean o;

    public TradeInSelectBankFragment(BaseActivity baseActivity, TradeInDialogFragment tradeInDialogFragment) {
        super(R.layout.tradein_select_bank_fragment_root);
        this.f5269h = baseActivity;
        this.f5270i = tradeInDialogFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        com.jd.lib.productdetail.tradein.e.a aVar = this.f5275n;
        if (aVar == null) {
            return;
        }
        aVar.request(this.f5269h);
    }

    public static /* synthetic */ void h(View view) {
    }

    public final void a() {
        this.f5273l.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.bank.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TradeInSelectBankFragment.this.b(view);
            }
        });
    }

    public final void c(PdBaseProtocolLiveData.Result<TradeInSelectBankResp> result) {
        PdBaseProtocolLiveData.Result.DataStatus dataStatus = PdBaseProtocolLiveData.Result.DataStatus.FETCHING;
        PdBaseProtocolLiveData.Result.DataStatus dataStatus2 = result.mStatus;
        if (dataStatus == dataStatus2) {
            RecyclerView.Adapter adapter = this.f5274m.getAdapter();
            if ((adapter == null ? 0 : adapter.getItemCount()) == 0) {
                g();
            }
            this.f5272k.setVisibility(8);
            this.f5271j.setVisibility(0);
        } else if (PdBaseProtocolLiveData.Result.DataStatus.SUCCESS == dataStatus2) {
            j();
            this.f5272k.setVisibility(8);
            e(result.mData);
        } else if (PdBaseProtocolLiveData.Result.DataStatus.FAIL == dataStatus2) {
            j();
            g();
            l();
        }
    }

    public final void d(TradeInSelectBankResp.Data.BankCardItem bankCardItem) {
        TradeInResultData tradeInResultData;
        if (PDUtils.repeatClick()) {
            if (bankCardItem.type == 2) {
                if (TextUtils.isEmpty(bankCardItem.h5Url)) {
                    return;
                }
                this.o = true;
                PDBaseDeepLinkHelper.gotoMWithUrl(getContext(), bankCardItem.h5Url);
                return;
            }
            PdBaseProtocolLiveData.Result<TradeInResultData> value = this.f5268g.u.getValue();
            if (value != null && (tradeInResultData = value.mData) != null) {
                tradeInResultData.setCurrentBank(getContext(), bankCardItem.cardNoEnd, true, bankCardItem.bankName, bankCardItem.id);
                MutableLiveData<PdBaseProtocolLiveData.Result<TradeInResultData>> mutableLiveData = this.f5268g.u;
                mutableLiveData.setValue(mutableLiveData.getValue());
            }
            getParentFragmentManager().popBackStack();
        }
    }

    public final void e(TradeInSelectBankResp tradeInSelectBankResp) {
        TradeInSelectBankResp.Data data;
        List<TradeInSelectBankResp.Data.BankCardItem> list;
        if (tradeInSelectBankResp != null && "0".equals(tradeInSelectBankResp.code) && (data = tradeInSelectBankResp.data) != null && (list = data.cardList) != null && list.size() != 0) {
            this.f5274m.setVisibility(0);
            TradeInSelectBankAdapter tradeInSelectBankAdapter = new TradeInSelectBankAdapter(tradeInSelectBankResp.data.cardList);
            this.f5274m.setAdapter(tradeInSelectBankAdapter);
            tradeInSelectBankAdapter.b = new Action1() { // from class: com.jd.lib.productdetail.tradein.bank.b
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    TradeInSelectBankFragment.this.d((TradeInSelectBankResp.Data.BankCardItem) obj);
                }
            };
            return;
        }
        j();
        g();
        l();
    }

    public final void g() {
        this.f5274m.setVisibility(8);
    }

    public final void j() {
        this.f5271j.setVisibility(8);
    }

    public void k() {
        com.jd.lib.productdetail.tradein.e.a aVar = this.f5275n;
        if (aVar == null) {
            return;
        }
        aVar.mResult.observe(getViewLifecycleOwner(), new Observer() { // from class: com.jd.lib.productdetail.tradein.bank.e
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TradeInSelectBankFragment.this.c((PdBaseProtocolLiveData.Result) obj);
            }
        });
    }

    public final void l() {
        this.f5272k.a(NetUtils.isNetworkAvailable() ? TradeinErrorView.a.UNKNOWN : TradeinErrorView.a.NONET);
        this.f5272k.setVisibility(0);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f5268g = (TradeInViewModel) new ViewModelProvider(this.f5270i).get(TradeInViewModel.class);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        TradeInResultData tradeInResultData;
        super.onViewCreated(view, bundle);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.jd.lib.productdetail.tradein.bank.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TradeInSelectBankFragment.h(view2);
            }
        });
        TradeInViewModel tradeInViewModel = this.f5268g;
        if (tradeInViewModel == null) {
            return;
        }
        String str = null;
        PdBaseProtocolLiveData.Result<TradeInResultData> value = tradeInViewModel.u.getValue();
        if (value != null && (tradeInResultData = value.mData) != null && tradeInResultData.getCurrentBank1() != null) {
            str = value.mData.getCurrentBank1().bankCardId;
        }
        this.f5271j = view.findViewById(R.id.tradein_select_bank_loading);
        this.f5272k = (TradeinErrorView) view.findViewById(R.id.tradein_select_bank_error_view);
        this.f5274m = (RecyclerView) view.findViewById(R.id.tradein_select_bank_view);
        this.f5273l = this.f5272k.findViewById(R.id.tradein_common_error_btn_retry);
        this.f5274m.setHasFixedSize(true);
        this.f5274m.setLayoutManager(new LinearLayoutManager(getContext()));
        getViewLifecycleOwner().getLifecycle().addObserver(new LifecycleEventObserver() { // from class: com.jd.lib.productdetail.tradein.bank.TradeInSelectBankFragment.1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    TradeInSelectBankFragment.this.getViewLifecycleOwner().getLifecycle().removeObserver(this);
                } else if (event != Lifecycle.Event.ON_START) {
                } else {
                    TradeInSelectBankFragment tradeInSelectBankFragment = TradeInSelectBankFragment.this;
                    if (tradeInSelectBankFragment.o) {
                        tradeInSelectBankFragment.o = false;
                        TradeInSelectBankFragment tradeInSelectBankFragment2 = TradeInSelectBankFragment.this;
                        com.jd.lib.productdetail.tradein.e.a aVar = tradeInSelectBankFragment2.f5275n;
                        if (aVar == null) {
                            return;
                        }
                        aVar.request(tradeInSelectBankFragment2.f5269h);
                    }
                }
            }
        });
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(tradeInViewModel.t)) {
            hashMap.put("extend", tradeInViewModel.t);
        }
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("selectedId", str);
        }
        this.f5275n = new com.jd.lib.productdetail.tradein.e.a(hashMap);
        k();
        this.f5275n.request(this.f5269h);
        a();
    }
}
